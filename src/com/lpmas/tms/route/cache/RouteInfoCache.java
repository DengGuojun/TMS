package com.lpmas.tms.route.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.business.RouteInfoBusiness;
import com.lpmas.tms.route.config.RouteCacheConfig;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class RouteInfoCache {

	private static Logger log = LoggerFactory.getLogger(RouteInfoCache.class);

	public String getRouteNameByKey(int routeId) {
		String routeName = "";
		Object obj = null;
		String key = RouteCacheConfig.getRouteNameCacheKey(routeId);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				RouteInfoBusiness routeInfoBusiness = new RouteInfoBusiness();
				RouteInfoBean bean = routeInfoBusiness.getRouteInfoByKey(routeId);
				if (bean != null) {
					obj = bean.getRouteName();
					localCache.set(key, obj, Constants.CACHE_TIME_1_HOUR);
					updated = true;
				}
			} catch (Exception e) {
				log.error("", e);
			} finally {
				// 缓存更新失败
				if (!updated) {
					// 取得一个老的版本
					obj = nre.getCacheContent();
					// 取消更新
					localCache.cancelUpdate(key);
				}
			}

		}
		if (obj != null) {
			routeName = (String) obj;
		}
		return routeName;
	}
	
	public void refreshRouteNameCache(int routeId){
		String key = RouteCacheConfig.getRouteNameCacheKey(routeId);
		LocalCache localCache = LocalCache.getInstance();
		localCache.delete(key);
	}

}
