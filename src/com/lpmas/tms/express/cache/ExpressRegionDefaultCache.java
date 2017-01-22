package com.lpmas.tms.express.cache;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.express.business.ExpressRegionDefaultBusiness;
import com.lpmas.tms.express.config.ExpressCacheConfig;

public class ExpressRegionDefaultCache {
	public ExpressRegionDefaultBean getExpressRegionDefaultByKey(int storeId, String country, String province,
			String city) {
		ExpressRegionDefaultBean bean = new ExpressRegionDefaultBean();
		String key = ExpressCacheConfig.getExpressRegionDefaultKey(storeId, country, province, city);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (ExpressRegionDefaultBean) obj;
		} else {
			ExpressRegionDefaultBusiness business = new ExpressRegionDefaultBusiness();
			bean = business.getExpressRegionDefaultByKey(storeId, country, province, city);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean removeExpressRegionDefaultByKey(int storeId, String country, String province, String city) {
		String key = ExpressCacheConfig.getExpressRegionDefaultKey(storeId, country, province, city);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}
}
