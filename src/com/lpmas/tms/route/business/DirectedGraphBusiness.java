package com.lpmas.tms.route.business;

import java.util.List;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.lpmas.tms.route.bean.EdgeInfoBean;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.bean.VertexInfoBean;
import com.lpmas.tms.route.bean.WeightedEdgeInfoBean;

public class DirectedGraphBusiness {

	public DefaultDirectedGraph<VertexInfoBean, EdgeInfoBean> createDirectedGraph() {
		DefaultDirectedGraph<VertexInfoBean, EdgeInfoBean> result = new DefaultDirectedGraph<VertexInfoBean, EdgeInfoBean>(EdgeInfoBean.class);

		// 查出对应的路径信息列表
		RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
		List<RouteInfoBean> routeInfoList = routeInfoBusiness.getRouteInfoAllList();

		// 根据报价信息构造顶点和边
		VertexInfoBean sourceVertex = null;
		VertexInfoBean destinationVertex = null;
		for (RouteInfoBean routeInfoBean : routeInfoList) {
			// 插入端点
			sourceVertex = getSourceVertex(routeInfoBean);
			destinationVertex = getDestinationVertex(routeInfoBean);
			result.addVertex(sourceVertex);
			result.addVertex(destinationVertex);

			// 插入边和权重
			result.addEdge(sourceVertex, destinationVertex,getEdgeInfo(routeInfoBean));
		}

		return result;
	}
	
	public DefaultDirectedGraph<WeightedEdgeInfoBean, DefaultEdge> createDirectedGraph(List<List<WeightedEdgeInfoBean>> edgeOfferList){
		DefaultDirectedGraph<WeightedEdgeInfoBean, DefaultEdge> result = new DefaultDirectedGraph<WeightedEdgeInfoBean, DefaultEdge>(DefaultEdge.class);
		//加入顶点
		for(List<WeightedEdgeInfoBean> subList : edgeOfferList){
			for(WeightedEdgeInfoBean bean : subList){
				result.addVertex(bean);
			}
		}
		//加入边
		for(int i=0;i<edgeOfferList.size();i++){
			if((i+1)!=edgeOfferList.size()){
				for(WeightedEdgeInfoBean source : edgeOfferList.get(i)){
					for(WeightedEdgeInfoBean destination : edgeOfferList.get(i+1)){
						result.addEdge(source, destination);
					}
				}
			}
		}
		return result;
	}

	private VertexInfoBean getSourceVertex(RouteInfoBean routeInfoBean) {
		VertexInfoBean sourceVertex = new VertexInfoBean();
		sourceVertex.setInfoType(routeInfoBean.getSourceInfoType());
		sourceVertex.setInfoId(routeInfoBean.getSourceInfoId());
		return sourceVertex;
	}

	private VertexInfoBean getDestinationVertex(RouteInfoBean routeInfoBean) {
		VertexInfoBean destinationVertex = new VertexInfoBean();
		destinationVertex.setInfoType(routeInfoBean.getDestinationInfoType());
		destinationVertex.setInfoId(routeInfoBean.getDestinationInfoId());
		return destinationVertex;
	}

	private EdgeInfoBean getEdgeInfo(RouteInfoBean routeInfoBean) {
		EdgeInfoBean edgeInfoBean = new EdgeInfoBean();
		edgeInfoBean.setSourceInfoType(routeInfoBean.getSourceInfoType());
		edgeInfoBean.setSourceInfoId(routeInfoBean.getSourceInfoId());
		edgeInfoBean.setDestinationInfoType(routeInfoBean.getDestinationInfoType());
		edgeInfoBean.setDestinationInfoId(routeInfoBean.getDestinationInfoId());
		return edgeInfoBean;
	}

}
