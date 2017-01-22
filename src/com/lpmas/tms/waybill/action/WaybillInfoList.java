package com.lpmas.tms.waybill.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.erp.business.OrderInfoMediator;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache;
import com.lpmas.tms.route.cache.RouteInfoCache;
import com.lpmas.tms.waybill.bean.WaybillEntityBean;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.cache.WaybillInfoCache;
import com.lpmas.tms.waybill.config.WaybillConsoleConfig;
import com.lpmas.tms.waybill.config.WaybillResource;

@WebServlet("/waybill/WaybillInfoList.do")
public class WaybillInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaybillInfoList() {
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
		if (!adminHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int pageNum = ParamKit.getIntParameter(request, "pageNum", TmsConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", TmsConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		String condStr = "infoType,companyId,createUser,infoId,approvalStatus,waybillStatus";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);

		String status = ParamKit.getParameter(request, "status", Constants.STATUS_VALID + "");
		condMap.put("status", status);

		WaybillInfoBusiness business = new WaybillInfoBusiness();
		PageResultBean<WaybillInfoBean> result = business.getWaybillInfoPageListByMap(condMap, pageBean);
		// 查询关联订单编号
		List<WaybillEntityBean> entityList = new ArrayList<WaybillEntityBean>(result.getRecordList().size());
		OrderInfoMediator orderInfoMediator = new OrderInfoMediator();
		RouteInfoCache routeInfoCache = new RouteInfoCache();
		WaybillEntityBean entityBean = null;
		for (WaybillInfoBean waybillInfoBean : result.getRecordList()) {
			entityBean = new WaybillEntityBean();
			BeanKit.copyBean(entityBean, waybillInfoBean);
			entityBean.setWareNumber(orderInfoMediator.getWareNumberByKey(waybillInfoBean.getInfoType(), waybillInfoBean.getInfoId()));
			entityBean.setRouteName(routeInfoCache.getRouteNameByKey(waybillInfoBean.getRouteId()));
			entityList.add(entityBean);
		}
		// 查询物流公司信息
		LogisticsCompanyInfoCache logisticsCompanyInfoCache = new LogisticsCompanyInfoCache();
		List<LogisticsCompanyInfoBean> companyInfoList = logisticsCompanyInfoCache.getLogisticsCompanyInfoAllList();
		request.setAttribute("CompanyInfoList", companyInfoList);
		request.setAttribute("CompanyInfoMap", ListKit.list2Map(companyInfoList, "companyId", "companyName"));
		// 查出创建人信息列表筛选用
		WaybillInfoCache cache = new WaybillInfoCache();
		List<Integer> waybillInfoCreaterUserList = cache.getWaybillInfoCreaterUserAllList();

		request.setAttribute("WaybillInfoCreaterUserList", waybillInfoCreaterUserList);
		request.setAttribute("WaybillEntityList", entityList);
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminHelper);
		String path = WaybillConsoleConfig.PAGE_PATH + "WaybillInfoList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
