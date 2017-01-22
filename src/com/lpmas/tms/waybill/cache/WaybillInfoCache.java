package com.lpmas.tms.waybill.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.waybill.busniess.WaybillInfoBusiness;
import com.lpmas.tms.waybill.config.WaybillCacheConfig;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class WaybillInfoCache {

	private static Logger log = LoggerFactory.getLogger(WaybillInfoCache.class);

	public List<Integer> getWaybillInfoCreaterUserAllList() {
		List<Integer> result = null;
		Object obj = null;
		String key = WaybillCacheConfig.getWaybillCreateUserListKey();
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				WaybillInfoBusiness business = new WaybillInfoBusiness();
				result = business.getWaybillInfoCreaterUserAllList();
				if (result != null && !result.isEmpty()) {
					obj = result;
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
			result = (List<Integer>) obj;
		}
		return result;
	}

	public void refreshWaybillCreateUserListCache() {
		String key = WaybillCacheConfig.getWaybillCreateUserListKey();
		LocalCache localCache = LocalCache.getInstance();
		localCache.delete(key);
	}
}
