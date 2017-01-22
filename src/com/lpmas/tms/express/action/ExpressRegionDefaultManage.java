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
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.business.ExpressRegionDefaultBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressRegionDefaultManage
 */
@WebServlet("/express/ExpressRegionDefaultManage.do")
public class ExpressRegionDefaultManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(ExpressRegionDefaultManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressRegionDefaultManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int expressId = ParamKit.getIntParameter(request, "expressId", 0);
		ExpressRegionDefaultBean bean = new ExpressRegionDefaultBean();
		if (expressId > 0) {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_REGION_DEFAULT, OperationConfig.UPDATE)) {
				return;
			}
			ExpressRegionDefaultBusiness business = new ExpressRegionDefaultBusiness();
			bean = business.getExpressRegionDefaultByKey(expressId);
		} else {
			if (!adminHelper.checkPermission(ExpressResource.EXPRESS_REGION_DEFAULT, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("ExpressInfo", bean);

		ExpressCompanyInfoBusiness companyBusiness = new ExpressCompanyInfoBusiness();
		request.setAttribute("CompanyList", companyBusiness.getExpressCompanyInfoAllList());

		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);
		request.setAttribute("StoreList", infoHelper.getUserValidStoreList());

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressRegionDefaultManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		ExpressRegionDefaultBean bean = new ExpressRegionDefaultBean();
		try {
			bean = BeanKit.request2Bean(request, ExpressRegionDefaultBean.class);
			ExpressRegionDefaultBusiness business = new ExpressRegionDefaultBusiness();

			if (bean.getExpressId() > 0) {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_REGION_DEFAULT, OperationConfig.UPDATE)) {
					return;
				}
			} else {
				if (!adminHelper.checkPermission(ExpressResource.EXPRESS_REGION_DEFAULT, OperationConfig.CREATE)) {
					return;
				}
			}

			// 判断设置是否已经存在
			if (business.isDuplicateKey(bean.getExpressId(), bean.getStoreId(), bean.getCountry(), bean.getProvince(),
					bean.getCity())) {
				HttpResponseKit.alertMessage(response, "已存在相同的设置", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			int result = 0;
			if (bean.getExpressId() > 0) {
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateExpressRegionDefault(bean);
			} else {
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addExpressRegionDefault(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/express/ExpressRegionDefaultList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
