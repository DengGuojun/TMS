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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.config.WaybillResource;

@WebServlet("/waybill/WaybillInfoRemove.do")
public class WaybillInfoRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WaybillInfoRemove.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaybillInfoRemove() {
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
		if (!adminUserHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.REMOVE)) {
			return;
		}
		int waybillId = ParamKit.getIntParameter(request, "waybillId", 0);
		try {
			WaybillInfoBusiness business = new WaybillInfoBusiness();
			WaybillInfoBean bean = business.getWaybillInfoByKey(waybillId);
			if (bean == null) {
				HttpResponseKit.alertMessage(response, "承运单ID参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_EDIT)
					|| bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_WAIT_APPROVE)) {
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				int result = business.removeWaybillInfo(bean);
				if (result > 0) {
					HttpResponseKit.alertMessage(response, "处理成功", "/waybill/WaybillInfoList.do");
				} else {
					HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				}
			} else {
				HttpResponseKit.alertMessage(response, "只允许删除审批通过之前的承运单", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
		}
	}

}
