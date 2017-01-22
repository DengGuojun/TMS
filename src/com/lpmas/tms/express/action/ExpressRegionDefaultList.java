package com.lpmas.tms.express.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.system.bean.StoreInfoBean;
import com.lpmas.system.business.SystemInfoHelper;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.business.ExpressRegionDefaultBusiness;
import com.lpmas.tms.express.config.ExpressConsoleConfig;
import com.lpmas.tms.express.config.ExpressResource;

/**
 * Servlet implementation class ExpressRegionDefaultList
 */
@WebServlet("/express/ExpressRegionDefaultList.do")
public class ExpressRegionDefaultList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpressRegionDefaultList() {
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
		if (!adminHelper.checkPermission(ExpressResource.EXPRESS_REGION_DEFAULT, OperationConfig.SEARCH)) {
			return;
		}

		int pageNum = ParamKit.getIntParameter(request, "pageNum", TmsConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", TmsConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		String condStr = "storeId,country,province,city,status";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);

		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);
		List<StoreInfoBean> storeList = infoHelper.getUserValidStoreList();
		if (!condMap.containsKey("storeId")) {
			if (!adminHelper.isSuperAdminUser()) {
				List<Integer> storeIds = new ArrayList<Integer>();
				for (StoreInfoBean storeInfoBean : storeList) {
					storeIds.add(storeInfoBean.getStoreId());
				}
				condMap.put("storeIds", ListKit.list2String(storeIds, ","));
			}
		}

		ExpressRegionDefaultBusiness business = new ExpressRegionDefaultBusiness();
		PageResultBean<ExpressRegionDefaultBean> result = business.getExpressRegionDefaultPageListByMap(condMap,
				pageBean);
		request.setAttribute("ExpressRegionList", result.getRecordList());

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);

		request.setAttribute("CondList", MapKit.map2List(condMap));

		request.setAttribute("StoreList", storeList);

		ExpressCompanyInfoBusiness companyBusiness = new ExpressCompanyInfoBusiness();
		request.setAttribute("CompanyList", companyBusiness.getExpressCompanyInfoAllList());

		request.setAttribute("AdminUserHelper", adminHelper);

		String path = ExpressConsoleConfig.PAGE_PATH + "ExpressRegionDefaultList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
