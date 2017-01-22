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
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.config.RouteConsoleConfig;
import com.lpmas.tms.route.config.RouteResource;

/**
 * Servlet implementation class RouteInfoManage
 */
@WebServlet("/route/RouteInfoManage.do")
public class RouteInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(RouteInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RouteInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		int routeId = ParamKit.getIntParameter(request, "routeId", 0);
		boolean readOnly = ParamKit.getBooleanParameter(request, "readOnly", false);
		RouteInfoBean bean = new RouteInfoBean();
		if (routeId > 0) {
			if (readOnly && !adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.SEARCH)){
				return;
			}
			if (!readOnly && !adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.UPDATE)) {
				return;
			}
			RouteInfoBusiness business = new RouteInfoBusiness();
			bean = business.getRouteInfoByKey(routeId);
		} else {
			if (!adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("RouteInfo", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);

		RequestDispatcher rd = request.getRequestDispatcher(RouteConsoleConfig.PAGE_PATH + "RouteInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		RouteInfoBean bean = new RouteInfoBean();
		try {
			bean = BeanKit.request2Bean(request, RouteInfoBean.class);
			RouteInfoBusiness business = new RouteInfoBusiness();
			ReturnMessageBean validateResult = business.velidateRouteInfoBean(bean);
			if(!StringKit.isValid(validateResult.getMessage())){
				HttpResponseKit.alertMessage(response, validateResult.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			int result = 0;
			if (bean.getRouteId() > 0) {
				if (!adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateRouteInfo(bean);
			} else {
				RouteInfoBean exiteBean = business.getRouteInfoByCondition(bean.getSourceInfoType(), bean.getSourceInfoId(), bean.getDestinationInfoType(), bean.getDestinationInfoId());
				if(exiteBean!=null){
					HttpResponseKit.alertMessage(response, "该路径已经存在不能新增", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				if (!adminUserHelper.checkPermission(RouteResource.ROUTE_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addRouteInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/route/RouteInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
