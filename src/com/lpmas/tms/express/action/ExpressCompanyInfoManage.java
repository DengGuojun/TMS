package com.lpmas.tms.express.action;

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
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressCompanyInfoManage
 */
@WebServlet("/express/ExpressCompanyInfoManage.do")
public class ExpressCompanyInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(ExpressCompanyInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressCompanyInfoManage() {
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
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();
		if (companyId > 0) {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_COMPANY, OperationConfig.UPDATE)) {
				return;
			}
			ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();
			bean = business.getExpressCompanyInfoByKey(companyId);
		} else {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_COMPANY, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("CompanyInfo", bean);

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressCompanyInfoManage.jsp";
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
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();
		try {
			bean = BeanKit.request2Bean(request, ExpressCompanyInfoBean.class);
			ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();

			int result = 0;
			if (business.isDuplicateCompanyCode(bean.getCompanyId(), bean.getCompanyCode())) {
				HttpResponseKit.alertMessage(response, "资源代码重复！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			if (bean.getCompanyId() > 0) {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_COMPANY, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateExpressCompanyInfo(bean);
			} else {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_COMPANY, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addExpressCompanyInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/express/ExpressCompanyInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
