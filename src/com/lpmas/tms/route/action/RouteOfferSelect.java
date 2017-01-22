package com.lpmas.tms.route.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.bean.RouteInfoEntityBean;
import com.lpmas.tms.route.bean.RouteOfferBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.business.RouteOfferBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;

@WebServlet("/route/RouteOfferSelect.do")
public class RouteOfferSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RouteOfferSelect() {
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.SEARCH)) {
			return;
		}
		int pageNum = ParamKit.getIntParameter(request, "pageNum", TmsConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", TmsConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		int companyId = ParamKit.getIntParameter(request, "companyId", 0);
		if (companyId == 0) {
			HttpResponseKit.alertMessage(response, "参数有误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int routeId = ParamKit.getIntParameter(request, "routeId", 0);
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("companyId", String.valueOf(companyId));
		String status = ParamKit.getParameter(request, "status", Constants.STATUS_VALID + "");
		condMap.put("status", status);
		if (routeId != 0) {
			condMap.put("routeId", String.valueOf(routeId));
		}

		RouteOfferBusiness business = new RouteOfferBusiness();
		PageResultBean<RouteOfferBean> result = business.getRouteOfferPageListByMap(condMap, pageBean);
		request.setAttribute("RouteOfferList", result.getRecordList());

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("companyId", companyId);
		request.setAttribute("routeId", routeId);
		request.setAttribute("AdminUserHelper", adminHelper);
		// 查询路径信息
		RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
		Map<Integer, RouteInfoEntityBean> routeInfoMap = new HashMap<Integer, RouteInfoEntityBean>();
		for (RouteOfferBean routeOfferBean : result.getRecordList()) {
			RouteInfoBean routeInfoBean = routeInfoBusiness.getRouteInfoByKey(routeOfferBean.getRouteId());
			if (routeInfoBean != null) {
				routeInfoMap.put(routeInfoBean.getRouteId(), routeInfoBusiness.getRouteInfoEntityByRouteInfo(routeInfoBean));
			}
		}
		request.setAttribute("RouteInfoMap", routeInfoMap);

		String path = RouteConsoleConfig.PAGE_PATH + "RouteOfferSelect.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
