package com.lpmas.tms.waybill.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillStatusProcessor;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.config.WaybillResource;

/**
 * Servlet implementation class WayBillStatusManage
 */
@WebServlet("/waybill/WaybillStatusManage.do")
public class WaybillStatusManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(WaybillStatusManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaybillStatusManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int waybillId = ParamKit.getIntParameter(request, "waybillId", 0);
		String statusAction = ParamKit.getParameter(request, "statusAction", "");
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_APPROVE) || statusAction.equals(WaybillConfig.WAYBILL_ACTION_REJECT)) {
			// 判断是否具有审批权限
			if (!adminUserHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.APPROVE)) {
				return;
			}
		} else {
			// 判断是否具有修改权限
			if (!adminUserHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)) {
				return;
			}
		}

		try {
			WaybillStatusProcessor.statusLock.lock();

			WaybillInfoBusiness business = new WaybillInfoBusiness();
			WaybillInfoBean bean = business.getWaybillInfoByKey(waybillId);
			if (bean == null) {
				HttpResponseKit.alertMessage(response, "该承运单不存在", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean.setModifyUser(adminUserHelper.getAdminUserId());
			WaybillStatusProcessor statusBusiness = new WaybillStatusProcessor(bean);
			int result = Constants.STATUS_NOT_VALID;
			if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_COMMIT)) {
				if (statusBusiness.committable()) {
					result = statusBusiness.commit();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_CANCEL_COMMIT)) {
				if (statusBusiness.cancelCommittable()) {
					result = statusBusiness.cancelCommit();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_APPROVE)) {
				if (statusBusiness.approvable()) {
					result = statusBusiness.approve();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_REJECT)) {
				if (statusBusiness.rejectable()) {
					result = statusBusiness.reject();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_PLACE_ORDER)) {
				if (statusBusiness.placeOrderable()) {
					result = statusBusiness.placeOrder();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_REVEICE)) {
				if (statusBusiness.receiveable()) {
					result = statusBusiness.receive();
				}
			} else if (statusAction.equals(WaybillConfig.WAYBILL_ACTION_CLOSE)) {
				if (statusBusiness.closable()) {
					result = statusBusiness.close();
				}
			} else {
				HttpResponseKit.alertMessage(response, "承运单操作代码不合法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (result == Constants.STATUS_VALID) {
				HttpResponseKit.alertMessage(response, "处理成功", "/waybill/WaybillInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "操作失败！您的页面已过期，请刷新页面重新操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

		} catch (Exception e) {
			logger.error("", e);
			HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} finally {
			WaybillStatusProcessor.statusLock.unlock();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
