package com.lpmas.tms.route.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;

/**
 * Servlet implementation class BrandInfoList
 */
@WebServlet("/route/RouteInfoList.do")
public class RouteInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RouteInfoList() {
		super();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.SEARCH)) {
			return;
		}
		
		int pageNum = ParamKit.getIntParameter(request, "pageNum", TmsConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", TmsConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		HashMap<String, String> condMap = new HashMap<String, String>();
		String routeName = ParamKit.getParameter(request, "routeName", "").trim();
		if (StringKit.isValid(routeName)) {
			condMap.put("routeName", routeName);
		}
		String sourceInfoType = ParamKit.getParameter(request, "sourceInfoType", "").trim();
		if (StringKit.isValid(sourceInfoType)) {
			condMap.put("sourceInfoType", sourceInfoType);
		}
		String destinationInfoType = ParamKit.getParameter(request, "destinationInfoType", "").trim();
		if (StringKit.isValid(destinationInfoType)) {
			condMap.put("destinationInfoType", destinationInfoType);
		}
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID)).trim();
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}

		RouteInfoBusiness business = new RouteInfoBusiness();
		PageResultBean<RouteInfoBean> result = business.getRouteInfoPageListByMap(condMap, pageBean);
		request.setAttribute("RouteInfoList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(RouteConsoleConfig.PAGE_PATH + "RouteInfoList.jsp");
		rd.forward(request, response);
	}

}
