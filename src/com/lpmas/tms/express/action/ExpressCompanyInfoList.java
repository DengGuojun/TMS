package com.lpmas.tms.express.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressCompanyInfoList
 */
@WebServlet("/express/ExpressCompanyInfoList.do")
public class ExpressCompanyInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressCompanyInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(ExpressResource.EXPRESS_COMPANY, OperationConfig.SEARCH)) {
			return;
		}

		int pageNum = ParamKit.getIntParameter(request, "pageNum", TmsConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", TmsConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		String condStr = "companyCode,companyName";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);

		String status = ParamKit.getParameter(request, "status", Constants.STATUS_VALID + "");
		condMap.put("status", status);

		ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();
		PageResultBean<ExpressCompanyInfoBean> result = business.getExpressCompanyInfoPageListByMap(condMap, pageBean);
		request.setAttribute("CompanyList", result.getRecordList());

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);

		request.setAttribute("CondList", MapKit.map2List(condMap));

		request.setAttribute("AdminUserHelper", adminHelper);

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressCompanyInfoList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
