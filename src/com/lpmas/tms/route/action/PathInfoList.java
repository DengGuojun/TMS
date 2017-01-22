package com.lpmas.tms.route.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.route.bean.PathInfoBean;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.business.PathInfoBusiness;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;
import com.mongodb.BasicDBObject;

/**
 * Servlet implementation class PathInfoList
 */
@WebServlet("/route/PathInfoList.do")
public class PathInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PathInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(RouteResource.PATH_INFO, OperationConfig.SEARCH)) {
			return;
		}
		
		Map<String, Object> condMap = new HashMap<String, Object>();
		HashMap<String, String> mysqlCondMap = new HashMap<String, String>();
		condMap.put("status", Constants.STATUS_VALID);
		String searchType = ParamKit.getParameter(request, "searchType", "normal");
		if (searchType.equals("normal")) {
			int sourceInfoType = ParamKit.getIntParameter(request, "sourceInfoType", 0);
			int sourceInfoId = ParamKit.getIntParameter(request, "sourceInfoId", 0);
			int destinationInfoType = ParamKit.getIntParameter(request, "destinationInfoType", 0);
			int destinationInfoId = ParamKit.getIntParameter(request, "destinationInfoId", 0);
			condMap.put("sourceInfoType", sourceInfoType);
			condMap.put("sourceInfoId", sourceInfoId);
			condMap.put("destinationInfoType", destinationInfoType);
			condMap.put("destinationInfoId", destinationInfoId);
		} else if (searchType.equals("routeName")) {
			String routeName = ParamKit.getParameter(request, "routeName", "").trim();
			if (StringKit.isValid(routeName)) {
				// 模糊查询routeName,路径ID列表
				RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
				mysqlCondMap.put("status", String.valueOf(Constants.STATUS_VALID));
				mysqlCondMap.put("routeName", routeName);
				List<RouteInfoBean> routeInfoList = routeInfoBusiness.getRouteInfoListByMap(mysqlCondMap);
				List<Map<String, Object>> orCondList = new ArrayList<Map<String, Object>>(routeInfoList.size());
				// 根据路径ID列表获取条件
				for (RouteInfoBean routeInfoBean : routeInfoList) {
					Map<String, Object> orCondMap = new HashMap<String, Object>();
					orCondMap.put("sourceInfoType", routeInfoBean.getSourceInfoType());
					orCondMap.put("sourceInfoId", routeInfoBean.getSourceInfoId());
					orCondMap.put("destinationInfoType", routeInfoBean.getDestinationInfoType());
					orCondMap.put("destinationInfoId", routeInfoBean.getDestinationInfoId());
					orCondList.add(orCondMap);
				}
				condMap.put("$or", orCondList);
			}else{
				HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else if (searchType.equals("stationName")) {
			String stationName = ParamKit.getParameter(request, "stationName", "").trim();
			// 根据类型查找对应的站点
			if(StringKit.isValid(stationName)){
				Pattern pattern = Pattern.compile("^.*" + stationName+ ".*$", Pattern.CASE_INSENSITIVE); 
				List<BasicDBObject> orCondList = new ArrayList<BasicDBObject>();
				orCondList.add( new BasicDBObject("sourceInfoName", pattern));
				orCondList.add( new BasicDBObject("destinationInfoName", pattern));
				condMap.put("$or", orCondList);
			}else{
				HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		double durationWeight = ParamKit.getIntParameter(request, "durationWeight", 50);
		double priceWeight = ParamKit.getIntParameter(request, "priceWeight", 50);
		Map<String, Object> orderMap = new HashMap<String, Object>();
		int totalDurationOrder = ParamKit.getIntParameter(request, "totalDurationOrder", 0);
		int totalPriceOrder = ParamKit.getIntParameter(request, "totalPriceOrder", 0);
		if (totalDurationOrder != 0) {
			orderMap.put("totalDuration", totalDurationOrder);
		}
		if (totalPriceOrder != 0) {
			orderMap.put("totalPrice", totalPriceOrder);
		}

		PathInfoBusiness pathInfoBusniess = new PathInfoBusiness();
		List<PathInfoBean> pathInfoList = pathInfoBusniess.getPathInfoListByMap(condMap, orderMap);
		PathInfoBean bestPath = pathInfoBusniess.getBestPathInfo(pathInfoList, durationWeight, priceWeight);

		request.setAttribute("BestPath", bestPath);
		request.setAttribute("PathInfoList", pathInfoList);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(RouteConsoleConfig.PAGE_PATH + "PathInfoList.jsp");
		rd.forward(request, response);
	}

}
