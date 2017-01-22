package com.lpmas.tms.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.component.ComponentClient;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.tms.component.TmsServicePrx;
import com.lpmas.tms.config.TmsClientConfig;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;

public class TmsServiceClient {
	private static Logger log = LoggerFactory.getLogger(TmsServiceClient.class);

	public ExpressCompanyInfoBean getDefaultCompanyInfoByCity(int storeId, String country, String province,
			String city) {
		ExpressCompanyInfoBean bean = null;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("storeId", String.valueOf(storeId));
		params.put("country", String.valueOf(country));
		params.put("province", String.valueOf(province));
		params.put("city", String.valueOf(city));
		String result = rpc(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_CITY, JsonKit.toJson(params));
		log.debug("getDefaultCompanyInfoByCity : {}", result);
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);
		return bean;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByProvince(int storeId, String country, String province) {
		ExpressCompanyInfoBean bean = null;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("storeId", String.valueOf(storeId));
		params.put("country", String.valueOf(country));
		params.put("province", String.valueOf(province));
		String result = rpc(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_PROVINCE, JsonKit.toJson(params));
		log.debug("getDefaultCompanyInfoByProvince : {}", result);
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);
		return bean;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByCountry(int storeId, String country) {
		ExpressCompanyInfoBean bean = null;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("storeId", String.valueOf(storeId));
		params.put("country", String.valueOf(country));
		String result = rpc(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_COUNTRY, JsonKit.toJson(params));
		log.debug("getDefaultCompanyInfoByCountry : {}", result);
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);
		return bean;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByStoreId(int storeId) {
		ExpressCompanyInfoBean bean = null;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("storeId", String.valueOf(storeId));
		String result = rpc(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_STORE_ID, JsonKit.toJson(params));
		log.debug("getDefaultCompanyInfoByStoreId : {}", result);
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);
		return bean;
	}

	public double getExpressFreightByCity(int companyId, String country, String province, String city) {
		double freight = 0;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyId", String.valueOf(companyId));
		params.put("country", String.valueOf(country));
		params.put("province", String.valueOf(province));
		params.put("city", String.valueOf(city));
		String result = rpc(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_CITY, JsonKit.toJson(params));
		log.debug("getExpressFreightByCity : {}", result);
		freight = JsonKit.toBean(result, Double.class);
		return freight;
	}

	public double getExpressFreightByProvince(int companyId, String country, String province) {
		double freight = 0;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyId", String.valueOf(companyId));
		params.put("country", String.valueOf(country));
		params.put("province", String.valueOf(province));
		String result = rpc(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_PROVINCE, JsonKit.toJson(params));
		log.debug("getExpressFreightByProvince : {}", result);
		freight = JsonKit.toBean(result, Double.class);
		return freight;
	}

	public double getExpressFreightByCountry(int companyId, String country) {
		double freight = 0;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyId", String.valueOf(companyId));
		params.put("country", String.valueOf(country));
		String result = rpc(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_COUNTRY, JsonKit.toJson(params));
		log.debug("getExpressFreightByCountry : {}", result);
		freight = JsonKit.toBean(result, Double.class);
		return freight;
	}

	public ExpressStoreDefaultBean getExpressStoreDefaultByKey(int storeId) {
		ExpressStoreDefaultBean bean = null;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("storeId", String.valueOf(storeId));
		String result = rpc(TmsClientConfig.GET_EXPRESS_STORE_DEFAULT_BY_KEY, JsonKit.toJson(params));
		log.debug("getExpressStoreDefaultByKey : {}", result);
		bean = JsonKit.toBean(result, ExpressStoreDefaultBean.class);
		return bean;
	}

	public List<ExpressCompanyInfoBean> getExpressCompanyInfoAllList() {
		List<ExpressCompanyInfoBean> list = new ArrayList<ExpressCompanyInfoBean>();

		HashMap<String, String> params = new HashMap<String, String>();
		String result = rpc(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_ALL_LIST, JsonKit.toJson(params));
		log.debug("getExpressCompanyInfoAllList : {}", result);
		list = JsonKit.toList(result, ExpressCompanyInfoBean.class);

		return list;
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByKey(int companyId) {
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyId", String.valueOf(companyId));

		String result = rpc(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_BY_KEY, JsonKit.toJson(params));
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);

		return bean;
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByCode(String companyCode) {
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);

		String result = rpc(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_BY_CODE, JsonKit.toJson(params));
		bean = JsonKit.toBean(result, ExpressCompanyInfoBean.class);

		return bean;
	}

	public List<LogisticsCompanyInfoBean> getLogisticsCompanyInfoAllList() {
		List<LogisticsCompanyInfoBean> list = new ArrayList<LogisticsCompanyInfoBean>();

		HashMap<String, String> params = new HashMap<String, String>();
		String result = rpc(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_ALL_LIST, JsonKit.toJson(params));
		list = JsonKit.toList(result, LogisticsCompanyInfoBean.class);

		return list;
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByKey(int companyId) {
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyId", String.valueOf(companyId));

		String result = rpc(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_BY_KEY, JsonKit.toJson(params));
		bean = JsonKit.toBean(result, LogisticsCompanyInfoBean.class);

		return bean;
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByCode(String companyCode) {
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("companyCode", companyCode);

		String result = rpc(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_BY_CODE, JsonKit.toJson(params));
		bean = JsonKit.toBean(result, LogisticsCompanyInfoBean.class);

		return bean;
	}

	public List<TransporterInfoBean> getTransporterInfoListByType(int transporterType) {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("transporterType", String.valueOf(transporterType));

		String result = rpc(TmsClientConfig.GET_TRANSPORTER_INFO_LIST_BY_TYPE, JsonKit.toJson(params));
		list = JsonKit.toList(result, TransporterInfoBean.class);

		return list;
	}

	private String rpc(String method, String params) {
		ComponentClient client = new ComponentClient();
		TmsServicePrx tmsService = (TmsServicePrx) client.getProxy(TmsConfig.APP_ID, TmsServicePrx.class);
		String result = tmsService.rpc(method, params);
		// log.info("result : {}", result);
		return result;
	}
}
