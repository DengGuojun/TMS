package com.lpmas.tms.client.cache;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.client.TmsServiceClient;
import com.lpmas.tms.config.TmsClientCacheConfig;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class TransporterInfoClientCache {
	private static Logger log = LoggerFactory.getLogger(TransporterInfoClientCache.class);

	public Map<String, String> getTransporterNameByKeyMap() {
		Map<String, String> map = null;
		Object obj = null;
		String key = TmsClientCacheConfig.TRANSPORTER_NAME_BY_KEY_MAP_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			localCache.cancelUpdate(key);
		}
		if (obj != null) {
			map = (Map<String, String>) obj;
		}
		return map;
	}

	public boolean setTransporterNameByKeyMap(Map<String, String> map) {
		boolean result = false;
		String key = TmsClientCacheConfig.TRANSPORTER_NAME_BY_KEY_MAP_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			localCache.set(key, map, Constants.CACHE_TIME_1_HOUR);
			result = true;
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}

	public List<TransporterInfoBean> getTransporterInfoListByType(int transporterType) {
		List<TransporterInfoBean> list = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getTransporterInfoListByTypeKey(transporterType);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getTransporterInfoListByType(transporterType);

				if (obj != null) {
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
			list = (List<TransporterInfoBean>) obj;
		}
		return list;
	}

	public Map<String, String> getTransporterCodeByKeyMap() {
		Map<String, String> map = null;
		Object obj = null;
		String key = TmsClientCacheConfig.TRANSPORTER_CODE_BY_KEY_MAP_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			localCache.cancelUpdate(key);
		}
		if (obj != null) {
			map = (Map<String, String>) obj;
		}
		return map;
	}

	public boolean setTransporterCodeByKeyMap(Map<String, String> map) {
		boolean result = false;
		String key = TmsClientCacheConfig.TRANSPORTER_CODE_BY_KEY_MAP_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			localCache.set(key, map, Constants.CACHE_TIME_1_HOUR);
			result = true;
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}
}
