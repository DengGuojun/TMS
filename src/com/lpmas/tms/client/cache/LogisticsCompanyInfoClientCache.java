package com.lpmas.tms.client.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.client.TmsServiceClient;
import com.lpmas.tms.config.TmsClientCacheConfig;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class LogisticsCompanyInfoClientCache {
	private static Logger log = LoggerFactory.getLogger(LogisticsCompanyInfoClientCache.class);

	public int getLogisticsCompanyIdByCode(String companyCode) {
		int companyId = 0;
		Object obj = null;
		String key = TmsClientCacheConfig.getLogisticsCompanyIdByCodeKey(companyCode);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				LogisticsCompanyInfoBean bean = client.getLogisticsCompanyInfoByCode(companyCode);

				if (bean != null) {
					obj = bean.getCompanyId();
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
			companyId = (Integer) obj;
		}
		return companyId;
	}

	public String getLogisticsCompanyCodeByKey(int companyId) {
		String companyCode = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getLogisticsCompanyCodeByKey(companyId);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				LogisticsCompanyInfoBean bean = client.getLogisticsCompanyInfoByKey(companyId);

				if (bean != null) {
					obj = bean.getCompanyCode();
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
			companyCode = (String) obj;
		}
		return companyCode;
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByCode(String companyCode) {
		LogisticsCompanyInfoBean bean = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getLogisticsCompanyInfoByCodeKey(companyCode);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getLogisticsCompanyInfoByCode(companyCode);

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
			bean = (LogisticsCompanyInfoBean) obj;
		}
		return bean;
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByKey(int companyId) {
		LogisticsCompanyInfoBean bean = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getLogisticsCompanyInfoKey(companyId);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getLogisticsCompanyInfoByKey(companyId);

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
			bean = (LogisticsCompanyInfoBean) obj;
		}
		return bean;
	}

	public List<LogisticsCompanyInfoBean> getLogisticsCompanyInfoAllList() {
		List<LogisticsCompanyInfoBean> list = null;
		Object obj = null;
		String key = TmsClientCacheConfig.LOGISTICS_COMPANY_INFO_ALL_LIST_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getLogisticsCompanyInfoAllList();

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
			list = (List<LogisticsCompanyInfoBean>) obj;
		}
		return list;
	}
}
