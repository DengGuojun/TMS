package com.lpmas.tms.waybill.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.busniess.WaybillStatusProcessor;
import com.lpmas.tms.waybill.config.WaybillConsoleConfig;
import com.lpmas.tms.waybill.config.WaybillResource;

/**
 * Servlet implementation class WaybillReviewResultSelect
 */
@WebServlet("/waybill/WaybillApproveResultSelect.do")
public class WaybillApproveResultSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaybillApproveResultSelect() {
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
		// 判断是否具有审批权限
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(WaybillResource.WAYBILL_INFO, OperationConfig.APPROVE)) {
			return;
		}

		int waybillId = ParamKit.getIntParameter(request, "waybillId", 0);
		WaybillInfoBusiness business = new WaybillInfoBusiness();
		WaybillInfoBean bean = business.getWaybillInfoByKey(waybillId);
		if (bean == null) {
			HttpResponseKit.alertMessage(response, "承运单ID不合法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		WaybillStatusProcessor statusBusiness = new WaybillStatusProcessor(bean);
		if (!statusBusiness.approvable() && !statusBusiness.rejectable()) {
			HttpResponseKit.alertMessage(response, "承运单目前的状态不允许审核", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher(WaybillConsoleConfig.PAGE_PATH + "WaybillApproveResultSelect.jsp");
		rd.forward(request, response);
	}

}
