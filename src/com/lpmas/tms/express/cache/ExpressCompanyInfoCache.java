package com.lpmas.tms.express.cache;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.business.ExpressCompanyInfoBusiness;
import com.lpmas.tms.express.config.ExpressCacheConfig;

public class ExpressCompanyInfoCache {

	public ExpressCompanyInfoBean getExpressCompanyInfoByKey(int companyId) {
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();
		String key = ExpressCacheConfig.getExpressCompanyInfoKey(companyId);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (ExpressCompanyInfoBean) obj;
		} else {
			ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();
			bean = business.getExpressCompanyInfoByKey(companyId);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshExpressCompanyInfoByKey(int companyId) {
		String key = ExpressCacheConfig.getExpressCompanyInfoKey(companyId);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByCode(String companyCode) {
		ExpressCompanyInfoBean bean = new ExpressCompanyInfoBean();
		String key = ExpressCacheConfig.getExpressCompanyInfoByCodeKey(companyCode);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (ExpressCompanyInfoBean) obj;
		} else {
			ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();
			bean = business.getExpressCompanyInfoByCode(companyCode);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshExpressCompanyInfoByCode(String companyCode) {
		String key = ExpressCacheConfig.getExpressCompanyInfoByCodeKey(companyCode);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

	public List<ExpressCompanyInfoBean> getExpressCompanyInfoAllList() {
		List<ExpressCompanyInfoBean> list = new ArrayList<ExpressCompanyInfoBean>();
		String key = ExpressCacheConfig.EXPRESS_COMPANY_INFO_ALL_LIST_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			list = (List<ExpressCompanyInfoBean>) obj;
		} else {
			ExpressCompanyInfoBusiness business = new ExpressCompanyInfoBusiness();
			list = business.getExpressCompanyInfoAllList();
			if (list != null) {
				remoteCache.set(TmsConfig.APP_ID, key, list, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return list;
	}

	public boolean refreshExpressCompanyInfoAllList() {
		String key = ExpressCacheConfig.EXPRESS_COMPANY_INFO_ALL_LIST_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}

}
