package com.lpmas.tms.component.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.tms.component._TmsServiceDisp;
import com.lpmas.tms.config.TmsClientConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.express.business.ExpressCompanyDefaultBusiness;
import com.lpmas.tms.express.business.ExpressFreightInfoBusiness;
import com.lpmas.tms.express.cache.ExpressCompanyInfoCache;
import com.lpmas.tms.express.cache.ExpressStoreDefaultCache;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;
import com.lpmas.tms.transporter.cache.TransporterInfoCache;

import Ice.Current;

public class TmsServiceImpl extends _TmsServiceDisp {
	private static Logger log = LoggerFactory.getLogger(TmsServiceImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8767851612392594640L;

	@Override
	public String rpc(String method, String params, Current __current) {
		log.info("rpc method : {}", method);
		String result = "";
		if (method.equals(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_CITY)) {
			result = getDefaultExpressCompanyInfoByCity(params);
		} else if (method.equals(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_PROVINCE)) {
			result = getDefaultExpressCompanyInfoByProvince(params);
		} else if (method.equals(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_COUNTRY)) {
			result = getDefaultExpressCompanyInfoByCountry(params);
		} else if (method.equals(TmsClientConfig.GET_DEFAULT_EXPRESS_COMPANY_INFO_BY_STORE_ID)) {
			result = getDefaultExpressCompanyInfoByStoreId(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_CITY)) {
			result = getExpressFreightByCity(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_PROVINCE)) {
			result = getExpressFreightByProvince(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_FREIGHT_BY_COUNTRY)) {
			result = getExpressFreightByCountry(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_STORE_DEFAULT_BY_KEY)) {
			result = getExpressStoreDefaultByKey(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_BY_KEY)) {
			result = getExpressCompanyInfoByKey(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_BY_CODE)) {
			result = getExpressCompanyInfoByCode(params);
		} else if (method.equals(TmsClientConfig.GET_EXPRESS_COMPANY_INFO_ALL_LIST)) {
			result = getExpressCompanyInfoAllList(params);
		} else if (method.equals(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_BY_KEY)) {
			result = getLogisticsCompanyInfoByKey(params);
		} else if (method.equals(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_BY_CODE)) {
			result = getLogisticsCompanyInfoByCode(params);
		} else if (method.equals(TmsClientConfig.GET_LOGISTICS_COMPANY_INFO_ALL_LIST)) {
			result = getLogisticsCompanyInfoAllList(params);
		}
		else if (method.equals(TmsClientConfig.GET_TRANSPORTER_INFO_LIST_BY_TYPE)) {
			result = getTransporterInfoListByType(params);
		}
		return result;
	}

	private String getDefaultExpressCompanyInfoByCity(String params) {
		ExpressCompanyDefaultBusiness business = new ExpressCompanyDefaultBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		ExpressCompanyInfoBean bean = business.getDefaultCompanyInfoByCity(Integer.parseInt(paramMap.get("storeId")),
				paramMap.get("country"), paramMap.get("province"), paramMap.get("city"));
		return JsonKit.toJson(bean);
	}

	private String getDefaultExpressCompanyInfoByProvince(String params) {
		ExpressCompanyDefaultBusiness business = new ExpressCompanyDefaultBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		ExpressCompanyInfoBean bean = business.getDefaultCompanyInfoByProvince(
				Integer.parseInt(paramMap.get("storeId")), paramMap.get("country"), paramMap.get("province"));
		return JsonKit.toJson(bean);
	}

	private String getDefaultExpressCompanyInfoByCountry(String params) {
		ExpressCompanyDefaultBusiness business = new ExpressCompanyDefaultBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		ExpressCompanyInfoBean bean = business.getDefaultCompanyInfoByCountry(Integer.parseInt(paramMap.get("storeId")),
				paramMap.get("country"));
		return JsonKit.toJson(bean);
	}

	private String getDefaultExpressCompanyInfoByStoreId(String params) {
		ExpressCompanyDefaultBusiness business = new ExpressCompanyDefaultBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		ExpressCompanyInfoBean bean = business
				.getDefaultCompanyInfoByStoreId(Integer.parseInt(paramMap.get("storeId")));
		return JsonKit.toJson(bean);
	}

	private String getExpressFreightByCity(String params) {
		ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		double result = business.getExpressFreightByCity(Integer.parseInt(paramMap.get("companyId")),
				paramMap.get("country"), paramMap.get("province"), paramMap.get("city"));
		return JsonKit.toJson(result);
	}

	private String getExpressFreightByProvince(String params) {
		ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		double result = business.getExpressFreightByProvince(Integer.parseInt(paramMap.get("companyId")),
				paramMap.get("country"), paramMap.get("province"));
		return JsonKit.toJson(result);
	}

	private String getExpressFreightByCountry(String params) {
		ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		double result = business.getExpressFreightByCountry(Integer.parseInt(paramMap.get("companyId")),
				paramMap.get("country"));
		return JsonKit.toJson(result);
	}

	private String getExpressStoreDefaultByKey(String params) {
		ExpressStoreDefaultCache cache = new ExpressStoreDefaultCache();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		ExpressStoreDefaultBean bean = cache.getExpressStoreDefaultByKey(Integer.parseInt(paramMap.get("storeId")));
		return JsonKit.toJson(bean);
	}

	private String getExpressCompanyInfoByKey(String params) {
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		int companyId = Integer.parseInt(paramMap.get("companyId"));
		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		ExpressCompanyInfoBean bean = cache.getExpressCompanyInfoByKey(companyId);
		return JsonKit.toJson(bean);
	}

	private String getExpressCompanyInfoByCode(String params) {
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		String companyCode = paramMap.get("companyCode");
		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		ExpressCompanyInfoBean bean = cache.getExpressCompanyInfoByCode(companyCode);
		return JsonKit.toJson(bean);
	}

	private String getExpressCompanyInfoAllList(String params) {
		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		List<ExpressCompanyInfoBean> list = cache.getExpressCompanyInfoAllList();
		return JsonKit.toJson(list);
	}

	private String getLogisticsCompanyInfoByKey(String params) {
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		int companyId = Integer.parseInt(paramMap.get("companyId"));
		LogisticsCompanyInfoCache cache = new LogisticsCompanyInfoCache();
		LogisticsCompanyInfoBean bean = cache.getLogisticsCompanyInfoByKey(companyId);
		return JsonKit.toJson(bean);
	}

	private String getLogisticsCompanyInfoByCode(String params) {
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		String companyCode = paramMap.get("companyCode");
		LogisticsCompanyInfoCache cache = new LogisticsCompanyInfoCache();
		LogisticsCompanyInfoBean bean = cache.getLogisticsCompanyInfoByCode(companyCode);
		return JsonKit.toJson(bean);
	}

	private String getLogisticsCompanyInfoAllList(String params) {
		LogisticsCompanyInfoCache cache = new LogisticsCompanyInfoCache();
		List<LogisticsCompanyInfoBean> list = cache.getLogisticsCompanyInfoAllList();
		return JsonKit.toJson(list);
	}
	
	private String getTransporterInfoListByType(String params) {
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		int transporterType = Integer.parseInt(paramMap.get("transporterType"));
		TransporterInfoCache cache = new TransporterInfoCache();
		List<TransporterInfoBean> list = cache.getTransporterInfoListByType(transporterType);
		return JsonKit.toJson(list);
	}
}
