package com.lpmas.tms.transporter.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.tms.config.TmsConsoleConfig;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;
import com.lpmas.tms.transporter.cache.TransporterInfoCache;

/**
 * Servlet implementation class TransporterInfoJsonList
 */
@WebServlet("/transporter/TransporterInfoJsonList.do")
public class TransporterInfoJsonList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransporterInfoJsonList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int transporterType = ParamKit.getIntParameter(request, "transporterType", 0);
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();

		if (transporterType > 0) {
			TransporterInfoCache cache = new TransporterInfoCache();
			list = cache.getTransporterInfoListByType(transporterType);
		}

		HttpResponseKit.printJson(request, response, list, TmsConsoleConfig.JSONP_CALLBACK);
	}

}
