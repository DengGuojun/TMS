package com.lpmas.tms.transporter.cache;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;
import com.lpmas.tms.transporter.business.TransporterInfoBusiness;
import com.lpmas.tms.transporter.config.TransporterCacheConfig;

public class TransporterInfoCache {
	public List<TransporterInfoBean> getTransporterInfoListByType(int transporterType) {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();
		String key = TransporterCacheConfig.getTransporterInfoListByType(transporterType);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(TmsConfig.APP_ID, key);
		if (obj != null) {
			list = (List<TransporterInfoBean>) obj;
		} else {
			TransporterInfoBusiness business = new TransporterInfoBusiness();
			list = business.getTransporterInfoListByType(transporterType);
			if (list != null) {
				remoteCache.set(TmsConfig.APP_ID, key, list, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return list;
	}

	public boolean refreshTransporterInfoListByType(int transporterType) {
		String key = TransporterCacheConfig.getTransporterInfoListByType(transporterType);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(TmsConfig.APP_ID, key);
	}
}
