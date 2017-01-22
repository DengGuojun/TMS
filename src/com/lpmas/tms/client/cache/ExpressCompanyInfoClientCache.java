package com.lpmas.tms.client.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.client.TmsServiceClient;
import com.lpmas.tms.config.TmsClientCacheConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class ExpressCompanyInfoClientCache {
	private static Logger log = LoggerFactory.getLogger(ExpressCompanyInfoClientCache.class);

	public int getExpressCompanyIdByCode(String companyCode) {
		int companyId = 0;
		Object obj = null;
		String key = TmsClientCacheConfig.getExpressCompanyIdByCodeKey(companyCode);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				ExpressCompanyInfoBean bean = client.getExpressCompanyInfoByCode(companyCode);

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

	public String getExpressCompanyCodeByKey(int companyId) {
		String companyCode = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getExpressCompanyCodeByKey(companyId);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				ExpressCompanyInfoBean bean = client.getExpressCompanyInfoByKey(companyId);

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

	public ExpressCompanyInfoBean getExpressCompanyInfoByCode(String companyCode) {
		ExpressCompanyInfoBean bean = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getExpressCompanyInfoByCodeKey(companyCode);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getExpressCompanyInfoByCode(companyCode);

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
			bean = (ExpressCompanyInfoBean) obj;
		}
		return bean;
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByKey(int companyId) {
		ExpressCompanyInfoBean bean = null;
		Object obj = null;
		String key = TmsClientCacheConfig.getExpressCompanyInfoKey(companyId);
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getExpressCompanyInfoByKey(companyId);

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
			bean = (ExpressCompanyInfoBean) obj;
		}
		return bean;
	}

	public List<ExpressCompanyInfoBean> getExpressCompanyInfoAllList() {
		List<ExpressCompanyInfoBean> list = null;
		Object obj = null;
		String key = TmsClientCacheConfig.EXPRESS_COMPANY_INFO_ALL_LIST_KEY;
		LocalCache localCache = LocalCache.getInstance();
		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean updated = false;
			try {
				TmsServiceClient client = new TmsServiceClient();
				obj = client.getExpressCompanyInfoAllList();

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
			list = (List<ExpressCompanyInfoBean>) obj;
		}
		return list;
	}
}
