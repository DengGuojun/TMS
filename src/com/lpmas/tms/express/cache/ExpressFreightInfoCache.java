package com.lpmas.tms.express.cache;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressFreightInfoBean;
import com.lpmas.tms.express.business.ExpressFreightInfoBusiness;
import com.lpmas.tms.express.config.ExpressCacheConfig;

public class ExpressFreightInfoCache {
	public ExpressFreightInfoBean getExpressFreightInfoByKey(int companyId, String country, String province, String city) {
		ExpressFreightInfoBean bean = new ExpressFreightInfoBean();
		String key = ExpressCacheConfig.getExpressFreightInfoKey(companyId, country, province, city);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (ExpressFreightInfoBean) obj;
		} else {
			ExpressFreightInfoBusiness business = new ExpressFreightInfoBusiness();
			bean = business.getExpressFreightInfoByKey(companyId, country, province, city);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean removeExpressFreightInfoByKey(int companyId, String country, String province, String city) {
		String key = ExpressCacheConfig.getExpressFreightInfoKey(companyId, country, province, city);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}
}
