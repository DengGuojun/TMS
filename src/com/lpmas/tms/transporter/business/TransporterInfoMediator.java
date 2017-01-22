package com.lpmas.tms.transporter.business;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.util.MapKit;
import com.lpmas.tms.client.cache.TransporterInfoClientCache;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;

public class TransporterInfoMediator {
	private TransporterInfoClientCache transporterInfoClientCache;
	private Map<String, String> transporterNameByKeyMap;
	private Map<String, String> transporterCodeByKeyMap;

	private TransporterInfoClientCache getTransporterInfoClientCache() {
		if (null == transporterInfoClientCache) {
			transporterInfoClientCache = new TransporterInfoClientCache();
		}
		return transporterInfoClientCache;
	}

	private Map<String, String> getTransporterNameByKeyMap() {
		if (null == transporterNameByKeyMap) {
			transporterNameByKeyMap = getTransporterNameByKeyMapFromCache();
		}
		return transporterNameByKeyMap;
	}

	private Map<String, String> getTransporterCodeByKeyMap() {
		if (null == transporterCodeByKeyMap) {
			transporterCodeByKeyMap = getTransporterCodeByKeyMapFromCache();
		}
		return transporterCodeByKeyMap;
	}

	private String getTransporterKey(int transporterType, int transporterId) {
		return MessageFormat.format("{0}_{1}", transporterType, transporterId);
	}

	public List<TransporterInfoBean> getTransporterInfoListByType(int transporterType) {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();
		if (transporterType > 0) {
			list = getTransporterInfoClientCache().getTransporterInfoListByType(transporterType);
		}
		return list;
	}

	private Map<String, String> getTransporterNameByKeyMapFromCache() {
		Map<String, String> map = getTransporterInfoClientCache().getTransporterNameByKeyMap();
		if (null == map) {
			List<TransporterInfoBean> list = getTransporterInfoAllList();

			if (list.size() > 0) {
				map = new HashMap<String, String>();
				for (TransporterInfoBean bean : list) {
					String key = getTransporterKey(bean.getTransporterType(), bean.getTransporterId());
					map.put(key, bean.getTransporterName());
				}
				getTransporterInfoClientCache().setTransporterNameByKeyMap(map);
			}
		}

		return map;
	}

	private Map<String, String> getTransporterCodeByKeyMapFromCache() {
		Map<String, String> map = getTransporterInfoClientCache().getTransporterCodeByKeyMap();
		if (null == map) {
			List<TransporterInfoBean> list = getTransporterInfoAllList();

			if (list.size() > 0) {
				map = new HashMap<String, String>();
				for (TransporterInfoBean bean : list) {
					String key = getTransporterKey(bean.getTransporterType(), bean.getTransporterId());
					map.put(key, bean.getTransporterCode());
				}
				getTransporterInfoClientCache().setTransporterCodeByKeyMap(map);
			}
		}

		return map;
	}

	public List<TransporterInfoBean> getTransporterInfoAllList() {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();
		list.addAll(getTransporterInfoListByType(TmsConfig.TRANSPROTER_TYPE_LOGISTICS));
		list.addAll(getTransporterInfoListByType(TmsConfig.TRANSPROTER_TYPE_EXPRESS));

		return list;
	}

	public String getTransporterNameByKey(int transporterType, int transporterId) {
		String result = "";
		if (transporterType > 0 && transporterId > 0) {
			String key = getTransporterKey(transporterType, transporterId);
			result = MapKit.getValueFromMap(key, getTransporterNameByKeyMap());
		}
		return result;
	}

	public String getTransporterCodeByKey(int transporterType, int transporterId) {
		String result = "";
		if (transporterType > 0 && transporterId > 0) {
			String key = getTransporterKey(transporterType, transporterId);
			result = MapKit.getValueFromMap(key, getTransporterCodeByKeyMap());
		}
		return result;
	}
}
