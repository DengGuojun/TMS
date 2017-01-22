package com.lpmas.tms.logistics.cache;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.business.LogisticsCompanyInfoBusiness;
import com.lpmas.tms.logistics.config.LogisticsCacheConfig;

public class LogisticsCompanyInfoCache {

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByKey(int companyId) {
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();
		String key = LogisticsCacheConfig.getLogisticsCompanyInfoKey(companyId);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (LogisticsCompanyInfoBean) obj;
		} else {
			LogisticsCompanyInfoBusiness business = new LogisticsCompanyInfoBusiness();
			bean = business.getLogisticsCompanyInfoByKey(companyId);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshLogisticsCompanyInfoByKey(int companyId) {
		String key = LogisticsCacheConfig.getLogisticsCompanyInfoKey(companyId);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByCode(String companyCode) {
		LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();
		String key = LogisticsCacheConfig.getLogisticsCompanyInfoByCodeKey(companyCode);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (LogisticsCompanyInfoBean) obj;
		} else {
			LogisticsCompanyInfoBusiness business = new LogisticsCompanyInfoBusiness();
			bean = business.getLogisticsCompanyInfoByCode(companyCode);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshLogisticsCompanyInfoByCode(String companyCode) {
		String key = LogisticsCacheConfig.getLogisticsCompanyInfoByCodeKey(companyCode);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

	public List<LogisticsCompanyInfoBean> getLogisticsCompanyInfoAllList() {
		List<LogisticsCompanyInfoBean> list = new ArrayList<LogisticsCompanyInfoBean>();
		String key = LogisticsCacheConfig.LOGISTICS_COMPANY_INFO_ALL_LIST_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			list = (List<LogisticsCompanyInfoBean>) obj;
		} else {
			LogisticsCompanyInfoBusiness business = new LogisticsCompanyInfoBusiness();
			list = business.getLogisticsCompanyInfoAllList();
			if (list != null) {
				remoteCache.set(TmsConfig.APP_ID, key, list, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return list;
	}

	public boolean refreshLogisticsCompanyInfoAllList() {
		String key = LogisticsCacheConfig.LOGISTICS_COMPANY_INFO_ALL_LIST_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

}
