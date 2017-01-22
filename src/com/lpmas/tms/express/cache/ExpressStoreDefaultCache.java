package com.lpmas.tms.express.cache;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.express.business.ExpressStoreDefaultBusiness;
import com.lpmas.tms.express.config.ExpressCacheConfig;

public class ExpressStoreDefaultCache {
	public ExpressStoreDefaultBean getExpressStoreDefaultByKey(int storeId) {
		ExpressStoreDefaultBean bean = new ExpressStoreDefaultBean();
		String key = ExpressCacheConfig.getExpressStoreDefaultKey(storeId);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			bean = (ExpressStoreDefaultBean) obj;
		} else {
			ExpressStoreDefaultBusiness business = new ExpressStoreDefaultBusiness();
			bean = business.getExpressStoreDefaultByKey(storeId);
			if (bean != null) {
				remoteCache.set(TmsConfig.APP_ID, key, bean, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean removeExpressStoreDefaultByKey(int storeId) {
		String key = ExpressCacheConfig.getExpressStoreDefaultKey(storeId);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}
}
