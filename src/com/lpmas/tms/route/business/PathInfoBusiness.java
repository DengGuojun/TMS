package com.lpmas.tms.route.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.tms.config.TmsMongoConfig;
import com.lpmas.tms.route.bean.EdgeInfoBean;
import com.lpmas.tms.route.bean.PathInfoBean;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.bean.RouteOfferBean;
import com.lpmas.tms.route.bean.VertexInfoBean;
import com.lpmas.tms.route.bean.WeightedEdgeInfoBean;
import com.lpmas.tms.route.dao.PathInfoDao;

public class PathInfoBusiness {

	public void tiggerMaintianAllPathInfo() {
		synchronized (PathInfoBusiness.class) {
			deletePathInfoByMap(new HashMap<String, Object>());
			maintainAllPathInfo();
		}
	}

	private void maintainAllPathInfo() {
		Map<Integer, List<RouteOfferBean>> routeOfferMap = new HashMap<Integer, List<RouteOfferBean>>();
		// 从缓存去除图
		DirectedGraphBusiness graphBusniess = new DirectedGraphBusiness();
		DefaultDirectedGraph<VertexInfoBean, EdgeInfoBean> graph = graphBusniess.createDirectedGraph();
		// 获取图中所有路径
		AllDirectedPaths<VertexInfoBean, EdgeInfoBean> allDirectedPaths = new AllDirectedPaths<VertexInfoBean, EdgeInfoBean>(graph);
		List<GraphPath<VertexInfoBean, EdgeInfoBean>> list = allDirectedPaths.getAllPaths(graph.vertexSet(), graph.vertexSet(), true,
				Integer.MAX_VALUE);

		// 遍历路径
		StationInfoMediator mediator = new StationInfoMediator();
		VertexInfoBean sourceVertex = null;
		VertexInfoBean destinationVertex = null;
		PathInfoBean pathInfoBean = null;
		RouteOfferBusiness routeOfferBusiness = new RouteOfferBusiness();
		RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
		RouteInfoBean routeInfoBean = null;
		List<RouteOfferBean> routeOfferList = null;
		List<List<WeightedEdgeInfoBean>> edgeOfferList = null;
		List<WeightedEdgeInfoBean> weigthedEdgeList = null;
		WeightedEdgeInfoBean weightedEdgeInfoBean = null;
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		for (GraphPath<VertexInfoBean, EdgeInfoBean> gp : list) {
			sourceVertex = gp.getStartVertex();
			destinationVertex = gp.getEndVertex();

			if (!sourceVertex.equals(destinationVertex)) {
				edgeOfferList = new ArrayList<List<WeightedEdgeInfoBean>>();
				for (EdgeInfoBean edge : gp.getEdgeList()) {
					weigthedEdgeList = new ArrayList<WeightedEdgeInfoBean>();
					// 根据路径信息查出路径报价
					routeInfoBean = routeInfoBusiness.getRouteInfoByCondition(edge.getSourceInfoType(), edge.getSourceInfoId(),
							edge.getDestinationInfoType(), edge.getDestinationInfoId());
					if (routeInfoBean != null) {
						routeOfferList = routeOfferMap.get(routeInfoBean.getRouteId());
						if (routeOfferList == null) {
							condMap.put("routeId", String.valueOf(routeInfoBean.getRouteId()));
							routeOfferList = routeOfferBusiness.getRouteOfferListByMap(condMap);
						}
						for (RouteOfferBean offerBean : routeOfferList) {
							weightedEdgeInfoBean = new WeightedEdgeInfoBean();
							weightedEdgeInfoBean.setSourceInfoId(edge.getSourceInfoId());
							weightedEdgeInfoBean.setSourceInfoType(edge.getSourceInfoType());
							weightedEdgeInfoBean.setDestinationInfoId(edge.getDestinationInfoId());
							weightedEdgeInfoBean.setDestinationInfoType(edge.getDestinationInfoType());
							weightedEdgeInfoBean.setCompanyId(offerBean.getCompanyId());
							weightedEdgeInfoBean.setRouteId(routeInfoBean.getRouteId());
							weightedEdgeInfoBean.setCurrency(offerBean.getCurrency());
							weightedEdgeInfoBean.setTransportPrice(offerBean.getTransportPrice());
							weightedEdgeInfoBean.setDuration(offerBean.getDuration());
							weigthedEdgeList.add(weightedEdgeInfoBean);
						}
						if (!weigthedEdgeList.isEmpty()) {
							edgeOfferList.add(weigthedEdgeList);
						}
					}
				}
				// 利用有向图找到所有的组合
				if (!edgeOfferList.isEmpty()) {
					if (edgeOfferList.size() > 1) {
						DefaultDirectedGraph<WeightedEdgeInfoBean, DefaultEdge> pathGraph = graphBusniess.createDirectedGraph(edgeOfferList);
						AllDirectedPaths<WeightedEdgeInfoBean, DefaultEdge> paths = new AllDirectedPaths<WeightedEdgeInfoBean, DefaultEdge>(
								pathGraph);
						List<GraphPath<WeightedEdgeInfoBean, DefaultEdge>> combination = paths.getAllPaths(pathGraph.vertexSet(),
								pathGraph.vertexSet(), true, Integer.MAX_VALUE);
						for (GraphPath<WeightedEdgeInfoBean, DefaultEdge> pathgp : combination) {
							if (pathgp.getLength() < edgeOfferList.size() - 1)
								continue;
							pathInfoBean = assemblePathInfoBean(sourceVertex, destinationVertex, pathgp.getVertexList(),mediator);
							addPathInfo(pathInfoBean);
						}
					} else {
						// 对于点对点的路径做处理
						for (WeightedEdgeInfoBean bean : edgeOfferList.get(0)) {
							List<WeightedEdgeInfoBean> pathList = new ArrayList<WeightedEdgeInfoBean>(1);
							pathList.add(bean);
							pathInfoBean = assemblePathInfoBean(sourceVertex, destinationVertex, pathList,mediator);
							addPathInfo(pathInfoBean);
						}
					}
				}
			}
		}
	}

	private PathInfoBean assemblePathInfoBean(VertexInfoBean sourceVertex, VertexInfoBean destinationVertex, List<WeightedEdgeInfoBean> pathList,StationInfoMediator mediator) {
		PathInfoBean pathInfoBean = new PathInfoBean();
		pathInfoBean.setSourceInfoType(sourceVertex.getInfoType());
		pathInfoBean.setSourceInfoId(sourceVertex.getInfoId());
		pathInfoBean.setSourceInfoName(mediator.getStationNameByKey(sourceVertex.getInfoType(), sourceVertex.getInfoId()));
		pathInfoBean.setDestinationInfoType(destinationVertex.getInfoType());
		pathInfoBean.setDestinationInfoId(destinationVertex.getInfoId());
		pathInfoBean.setDestinationInfoName(mediator.getStationNameByKey(destinationVertex.getInfoType(), destinationVertex.getInfoId()));
		pathInfoBean.setStatus(Constants.STATUS_VALID);
		pathInfoBean.setPathList(pathList);
		double totalDuration = 0;
		double totalPrice = 0;
		for (WeightedEdgeInfoBean edge : pathList) {
			totalDuration = NumeralOperationKit.add(totalDuration, edge.getDuration());
			totalPrice = NumeralOperationKit.add(totalPrice, edge.getTransportPrice());
		}
		pathInfoBean.setTotalDuration(totalDuration);
		pathInfoBean.setTotalPrice(totalPrice);
		pathInfoBean.set_id(TmsMongoConfig.getPathInfoKey(pathInfoBean));
		pathInfoBean.setLastUpdate(DateKit.getCurrentTimestamp());
		return pathInfoBean;
	}

	public int addOrUpdatePathInfo(PathInfoBean bean) {
		String _id = TmsMongoConfig.getPathInfoKey(bean);
		PathInfoBean dbBean = getPathInfoBeanByKey(_id);
		int result = 0;
		if (dbBean != null) {
			result = updatePathInfo(bean);
		} else {
			result = addPathInfo(bean);
		}
		return result;
	}

	public int addPathInfo(PathInfoBean bean) {
		PathInfoDao dao = new PathInfoDao();
		return dao.insertPathInfo(bean);
	}

	public int updatePathInfo(PathInfoBean bean) {
		PathInfoDao dao = new PathInfoDao();
		return dao.updatePathInfo(bean);
	}

	public PathInfoBean getPathInfoBeanByKey(String _id) {
		PathInfoDao dao = new PathInfoDao();
		return dao.getPathInfoByKey(_id);
	}

	public List<PathInfoBean> getPathInfoListByMap(Map<String, Object> condMap, Map<String, Object> orderBy) {
		PathInfoDao dao = new PathInfoDao();
		return dao.getPathInfoListByMap(condMap, orderBy);
	}
	
	public PageResultBean<PathInfoBean> getPathInfoPageListByMap(Map<String, Object> condMap, Map<String, Object> orderBy,PageBean pageBean) {
		PathInfoDao dao = new PathInfoDao();
		return dao.getPathInfoPageListByMap(condMap, orderBy, pageBean);
	}

	public PathInfoBean getBestPathInfo(List<PathInfoBean> pathInfoList, double durationWeight, double priceWeight) {
		double totalWeight = NumeralOperationKit.add(durationWeight, priceWeight);
		double duration = NumeralOperationKit.divide(durationWeight, totalWeight, 2);
		double price = NumeralOperationKit.divide(priceWeight, totalWeight, 2);
		double lastCost = Double.MAX_VALUE;
		double currentCost = 0;
		String lastId = "";
		for (PathInfoBean bean : pathInfoList) {
			double trueDurationWeight = NumeralOperationKit.multiply(duration, bean.getTotalDuration(), 2);
			double truePriceWright = NumeralOperationKit.multiply(price, bean.getTotalPrice(), 2);
			currentCost = NumeralOperationKit.add(trueDurationWeight, truePriceWright);
			if (Double.compare(currentCost, lastCost) < 0) {
				lastCost = currentCost;
				lastId = bean.get_id();
			}
		}
		return ListKit.list2Map(pathInfoList, "_id").get(lastId);
	}

	public int deletePathInfoByMap(Map<String, Object> condMap) {
		PathInfoDao dao = new PathInfoDao();
		return dao.deletePathInfoByMap(condMap);
	}
	
}
