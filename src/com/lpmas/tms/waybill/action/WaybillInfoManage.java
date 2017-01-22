package com.lpmas.tms.waybill.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.erp.business.OrderInfoMediator;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache;
import com.lpmas.tms.route.cache.RouteInfoCache;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.busniess.WaybillInfoNumberGenerator;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.config.WaybillConsoleConfig;
import com.lpmas.tms.waybill.config.WaybillResource;

@WebServlet("/waybill/WaybillInfoManage.do")
public class WaybillInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(WaybillInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaybillInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int waybillId = ParamKit.getIntParameter(request, "waybillId", 0);
		WaybillInfoBean bean = new WaybillInfoBean();
		String infoNumber = "";
		if (waybillId > 0) {
			if (!adminHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)) {
				return;
			}
			WaybillInfoBusiness business = new WaybillInfoBusiness();
			bean = business.getWaybillInfoByKey(waybillId);
			// 查询关联订单编号
			if (bean.getInfoType() != Constants.STATUS_NOT_VALID && bean.getInfoId() != Constants.STATUS_NOT_VALID) {
				OrderInfoMediator orderInfoMediator = new OrderInfoMediator();
				infoNumber = orderInfoMediator.getWareNumberByKey(bean.getInfoType(), bean.getInfoId());
			}
			if (bean.getInfoId() != Constants.STATUS_NOT_VALID || bean.getRouteId() != Constants.STATUS_NOT_VALID) {
				request.setAttribute("IsSource", true);
			}
		} else {
			if (!adminHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
			bean.setApprovalStatus(WaybillConfig.REVIEW_STATUS_UNCOMMIT);
			bean.setWaybillStatus(WaybillConfig.WAYBILL_STATUS_EDIT);
			bean.setSyncStatus(SyncStatusConfig.SYNCS_NONE);
		}
		request.setAttribute("WaybillInfo", bean);
		request.setAttribute("InfoNumber", infoNumber);
		// 路径名称
		RouteInfoCache cache = new RouteInfoCache();
		String routeName = bean.getRouteId() == 0 ? "" : cache.getRouteNameByKey(bean.getRouteId());
		request.setAttribute("RouteName", routeName);
		// 查询物流公司信息
		LogisticsCompanyInfoCache logisticsCompanyInfoCache = new LogisticsCompanyInfoCache();
		request.setAttribute("CompanyInfoList", logisticsCompanyInfoCache.getLogisticsCompanyInfoAllList());
		String path = WaybillConsoleConfig.PAGE_PATH + "WaybillInfoManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		WaybillInfoBean bean = new WaybillInfoBean();
		try {
			bean = BeanKit.request2Bean(request, WaybillInfoBean.class);
			if (bean.getWaybillId() == 0) {
				String waybillNumber = WaybillInfoNumberGenerator.generateWaybillInfoNumber();
				bean.setWaybillNumber(waybillNumber);
			}
			WaybillInfoBusiness business = new WaybillInfoBusiness();
			int result = 0;
			int userId = adminHelper.getAdminUserId();
			if (bean.getWaybillId() > 0) {
				if (!adminHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(userId);
				result = business.updateWaybillInfo(bean);

			} else {
				if (!adminHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(userId);
				result = business.addWaybillInfo(bean);
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/waybill/WaybillInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
