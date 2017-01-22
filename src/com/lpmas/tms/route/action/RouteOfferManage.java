package com.lpmas.tms.route.action;

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
import com.lpmas.constant.currency.CurrencyConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.logistics.business.LogisticsCompanyInfoBusiness;
import com.lpmas.tms.route.bean.RouteOfferBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.business.RouteOfferBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;

@WebServlet("/route/RouteOfferManage.do")
public class RouteOfferManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(RouteOfferManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RouteOfferManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int companyId = ParamKit.getIntParameter(request, "companyId", 0);
		int routeId = ParamKit.getIntParameter(request, "routeId", 0);
		boolean readOnly = ParamKit.getBooleanParameter(request, "readOnly", false);
		RouteOfferBean bean = new RouteOfferBean();
		if (companyId > 0 && routeId > 0) {
			if (readOnly && !adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.SEARCH)) {
				return;
			}
			if (!readOnly && !adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.UPDATE)) {
				return;
			}
			RouteOfferBusiness business = new RouteOfferBusiness();
			bean = business.getRouteOfferByKey(routeId, companyId);
		} else {
			if (!adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)) {
				return;
			}
			bean.setCurrency(CurrencyConfig.CUR_CNY);
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("RouteOffer", bean);
		// 查询路径信息
		RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
		request.setAttribute("RouteInfoList", routeInfoBusiness.getRouteInfoAllList());
		// 查询物流公司信息
		LogisticsCompanyInfoBusiness logisticsCompanyInfoBusiness = new LogisticsCompanyInfoBusiness();
		request.setAttribute("CompanyInfoList", logisticsCompanyInfoBusiness.getLogisticsCompanyInfoAllList());
		String path = RouteConsoleConfig.PAGE_PATH + "RouteOfferManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		RouteOfferBean bean = new RouteOfferBean();
		try {
			bean = BeanKit.request2Bean(request, RouteOfferBean.class);
			RouteOfferBusiness business = new RouteOfferBusiness();

			int result = 0;
			RouteOfferBean dbBean = business.getRouteOfferByKey(bean.getRouteId(), bean.getCompanyId());
			if (dbBean != null) {
				if (!adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateRouteOffer(bean);
			} else {
				if (!adminHelper.checkPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)) {
					return;
				}
				if (business.isDuplicateKey(bean.getRouteId(), bean.getCompanyId())) {
					HttpResponseKit.alertMessage(response, "该公司路径重复！", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addRouteOffer(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/route/RouteOfferList.do?routeId="+bean.getRouteId());
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
