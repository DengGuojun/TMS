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
import com.lpmas.tms.express.bean.ExpressFreightInfoBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.business.ExpressFreightInfoBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressFreightInfoManage
 */
@WebServlet("/express/ExpressFreightInfoManage.do")
public class ExpressFreightInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(ExpressFreightInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressFreightInfoManage() {
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
		int freightId = ParamKit.getIntParameter(request, "freightId", 0);
		ExpressFreightInfoBean bean = new ExpressFreightInfoBean();
		if (freightId > 0) {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.UPDATE)) {
				return;
			}
			ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();
			bean = business.getExpressFreightInfoByKey(freightId);
		} else {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("FreightInfo", bean);

		ExpressCompanyInfoBusiness companyBusiness = new ExpressCompanyInfoBusiness();
		request.setAttribute("CompanyList", companyBusiness.getExpressCompanyInfoAllList());

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressFreightInfoManage.jsp";
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
		ExpressFreightInfoBean bean = new ExpressFreightInfoBean();
		try {
			bean = BeanKit.request2Bean(request, ExpressFreightInfoBean.class);
			ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();

			if (bean.getFreightId() > 0) {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.UPDATE)) {
					return;
				}
			} else {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.CREATE)) {
					return;
				}
			}

			// 判断设置是否已经存在
			if (business.isDuplicateKey(bean.getFreightId(), bean.getCompanyId(), bean.getCountry(), bean.getProvince(),
					bean.getCity())) {
				HttpResponseKit.alertMessage(response, "已存在相同的设置", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			int result = 0;
			if (bean.getFreightId() > 0) {
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateExpressFreightInfo(bean);
			} else {
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addExpressFreightInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/express/ExpressFreightInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
