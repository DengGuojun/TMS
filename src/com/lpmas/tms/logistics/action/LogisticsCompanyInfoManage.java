package com.lpmas.tms.logistics.action;

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
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.business.LogisticsCompanyInfoBusiness;
import com.lpmas.tms.logistics.config.LogisticsConsoleConfig;
import com.lpmas.tms.logistics.config.LogisticsResource;

/**
 * Servlet implementation class ExpressCompanyInfoManage
 */
@WebServlet("/logistics/LogisticsCompanyInfoManage.do")
public class LogisticsCompanyInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(LogisticsCompanyInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogisticsCompanyInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int companyId = ParamKit.getIntParameter(request, "companyId", 0);
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();
		if (companyId > 0) {
			if (!adminHelper.checkPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.UPDATE)) {
				return;
			}
			LogisticsCompanyInfoBusiness business = new LogisticsCompanyInfoBusiness();
			bean = business.getLogisticsCompanyInfoByKey(companyId);
		} else {
			if (!adminHelper.checkPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("CompanyInfo", bean);

		String path = LogisticsConsoleConfig.PAGE_PATH + "LogisticsCompanyInfoManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();
		try {
			bean = BeanKit.request2Bean(request, LogisticsCompanyInfoBean.class);
			LogisticsCompanyInfoBusiness business = new LogisticsCompanyInfoBusiness();

			int result = 0;
			if (business.isDuplicateCompanyCode(bean.getCompanyId(), bean.getCompanyCode())) {
				HttpResponseKit.alertMessage(response, "资源代码重复！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			
			if (bean.getCompanyId() > 0) {
				if (!adminHelper.checkPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateLogisticsCompanyInfo(bean);
			} else {
				if (!adminHelper.checkPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addLogisticsCompanyInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/logistics/LogisticsCompanyInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
