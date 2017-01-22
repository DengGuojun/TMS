package com.lpmas.tms.route.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.business.LogisticsCompanyInfoBusiness;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.bean.RouteOfferBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.business.RouteOfferBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;

@WebServlet("/route/RouteOfferList.do")
public class RouteOfferList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RouteOfferList() {
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
		String condStr = "routeId,companyId";
		int routeId = ParamKit.getIntParameter(request, "routeId", 0);
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);

		String status = ParamKit.getParameter(request, "status", Constants.STATUS_VALID + "");
		condMap.put("status", status);

		RouteOfferBusiness business = new RouteOfferBusiness();
		PageResultBean<RouteOfferBean> result = business.getRouteOfferPageListByMap(condMap, pageBean);
		request.setAttribute("RouteOfferList", result.getRecordList());

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);

		request.setAttribute("CondList", MapKit.map2List(condMap));

		request.setAttribute("AdminUserHelper", adminHelper);
		// 查询路径信息
		RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
		List<RouteInfoBean> routeInfoList = routeInfoBusiness.getRouteInfoAllList();
		request.setAttribute("RouteInfoList", routeInfoList);
		request.setAttribute("RouteInfoBean", routeInfoBusiness.getRouteInfoByKey(routeId));
		request.setAttribute("RouteInfoMap", ListKit.list2Map(routeInfoList, "routeId", "routeName"));
		// 查询物流公司信息
		LogisticsCompanyInfoBusiness logisticsCompanyInfoBusiness = new LogisticsCompanyInfoBusiness();
		List<LogisticsCompanyInfoBean> companyInfoList = logisticsCompanyInfoBusiness.getLogisticsCompanyInfoAllList();
		request.setAttribute("CompanyInfoList", companyInfoList);
		request.setAttribute("CompanyInfoMap", ListKit.list2Map(companyInfoList, "companyId", "companyName"));

		String path = RouteConsoleConfig.PAGE_PATH + "RouteOfferList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
