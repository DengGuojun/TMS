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
import com.lpmas.system.business.SystemInfoHelper;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.business.ExpressStoreDefaultBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressStoreDefaultManage
 */
@WebServlet("/express/ExpressStoreDefaultManage.do")
public class ExpressStoreDefaultManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(ExpressStoreDefaultManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressStoreDefaultManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int storeId = ParamKit.getIntParameter(request, "storeId", 0);
		ExpressStoreDefaultBean bean = new ExpressStoreDefaultBean();
		if (storeId > 0) {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_STORE_DEFAULT, OperationConfig.UPDATE)) {
				return;
			}
			ExpressStoreDefaultBusiness business = new ExpressStoreDefaultBusiness();
			bean = business.getExpressStoreDefaultByKey(storeId);
			if (bean == null) {
				bean = new ExpressStoreDefaultBean();
				bean.setStoreId(storeId);
				bean.setStatus(Constants.STATUS_VALID);
			}
		} else {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_STORE_DEFAULT, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("ExpressStoreInfo", bean);

		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);
		request.setAttribute("StoreList", infoHelper.getUserValidStoreList());

		ExpressCompanyInfoBusiness companyBusiness = new ExpressCompanyInfoBusiness();
		request.setAttribute("CompanyList", companyBusiness.getExpressCompanyInfoAllList());

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressStoreDefaultManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		ExpressStoreDefaultBean bean = new ExpressStoreDefaultBean();
		try {
			bean = BeanKit.request2Bean(request, ExpressStoreDefaultBean.class);
			ExpressStoreDefaultBusiness business = new ExpressStoreDefaultBusiness();

			if (bean.getStoreId() == 0) {
				HttpResponseKit.alertMessage(response, "没有选择商店,请选择商店", HttpResponseKit.ACTION_HISTORY_BACK);
			}

			int result = -1;
			if (business.getExpressStoreDefaultByKey(bean.getStoreId()) != null) {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_STORE_DEFAULT, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateExpressStoreDefault(bean);
			} else {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_STORE_DEFAULT, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addExpressStoreDefault(bean);
			}

			if (result < 0) {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/express/ExpressStoreDefaultManage.do?storeId=" + bean.getStoreId());
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
