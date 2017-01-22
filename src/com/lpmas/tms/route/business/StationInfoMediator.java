package com.lpmas.tms.route.business;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.lpmas.constant.info.InfoTypeConfig;
import com.lpmas.erp.client.cache.WarehouseInfoCache;
import com.lpmas.erp.warehouse.bean.WarehouseInfoBean;
import com.lpmas.srm.bean.SupplierInfoBean;
import com.lpmas.srm.client.cache.SupplierInfoClientCache;

public class StationInfoMediator {

	private SupplierInfoClientCache supplierInfoCache;
	private WarehouseInfoCache warehouseInfoCache;
	private Map<String, String> stationNameByKeyMap;
	private Map<String, String> contactNameByKeyMap;
	private Map<String, String> countryByKeyMap;
	private Map<String, String> provinceByKeyMap;
	private Map<String, String> cityByKeyMap;
	private Map<String, String> regionByKeyMap;
	private Map<String, String> addressByKeyMap;
	private Map<String, String> telephoneByKeyMap;
	private Map<String, String> mobileByKeyMap;

	public StationInfoMediator() {
		supplierInfoCache = new SupplierInfoClientCache();
		warehouseInfoCache = new WarehouseInfoCache();
		stationNameByKeyMap = new HashMap<String, String>();
		contactNameByKeyMap = new HashMap<String, String>();
		countryByKeyMap = new HashMap<String, String>();
		provinceByKeyMap = new HashMap<String, String>();
		cityByKeyMap = new HashMap<String, String>();
		regionByKeyMap = new HashMap<String, String>();
		addressByKeyMap = new HashMap<String, String>();
		telephoneByKeyMap = new HashMap<String, String>();
		mobileByKeyMap = new HashMap<String, String>();
	}

	private String getMapKey(int infoType, int infoId) {
		return MessageFormat.format("{0}_{1}", infoType, infoId);
	}

	public Map<String, String> getStationNameByKeyMap() {
		return stationNameByKeyMap;
	}

	public Map<String, String> getContactNameByKeyMap() {
		return contactNameByKeyMap;
	}

	public Map<String, String> getCountryByKeyMap() {
		return countryByKeyMap;
	}

	public Map<String, String> getProvinceByKeyMap() {
		return provinceByKeyMap;
	}

	public Map<String, String> getCityByKeyMap() {
		return cityByKeyMap;
	}

	public Map<String, String> getRegionByKeyMap() {
		return regionByKeyMap;
	}

	public Map<String, String> getAddressByKeyMap() {
		return addressByKeyMap;
	}

	public Map<String, String> getTelephoneByKeyMap() {
		return telephoneByKeyMap;
	}

	public Map<String, String> getMobileByKeyMap() {
		return mobileByKeyMap;
	}

	public String getStationNameByKey(int infoType, int infoId) {
		return getValueFromMapByKey(stationNameByKeyMap, infoType, infoId);
	}

	public String getContactNameByKey(int infoType, int infoId) {
		return getValueFromMapByKey(contactNameByKeyMap, infoType, infoId);
	}

	public String getCountryByKey(int infoType, int infoId) {
		return getValueFromMapByKey(countryByKeyMap, infoType, infoId);
	}

	public String getProvinceByKey(int infoType, int infoId) {
		return getValueFromMapByKey(provinceByKeyMap, infoType, infoId);
	}

	public String getCityByKey(int infoType, int infoId) {
		return getValueFromMapByKey(cityByKeyMap, infoType, infoId);
	}

	public String getRegionByKey(int infoType, int infoId) {
		return getValueFromMapByKey(regionByKeyMap, infoType, infoId);
	}

	public String getAddressByKey(int infoType, int infoId) {
		return getValueFromMapByKey(addressByKeyMap, infoType, infoId);
	}

	public String getTelephoneByKey(int infoType, int infoId) {
		return getValueFromMapByKey(telephoneByKeyMap, infoType, infoId);
	}

	public String getMobileByKey(int infoType, int infoId) {
		return getValueFromMapByKey(mobileByKeyMap, infoType, infoId);
	}

	private void setMapValue(String key, WarehouseInfoBean warehouseInfoBean) {
		stationNameByKeyMap.put(key, warehouseInfoBean.getWarehouseName());
		contactNameByKeyMap.put(key, warehouseInfoBean.getContactName());
		countryByKeyMap.put(key, warehouseInfoBean.getCountry());
		provinceByKeyMap.put(key, warehouseInfoBean.getProvince());
		cityByKeyMap.put(key, warehouseInfoBean.getCity());
		regionByKeyMap.put(key, warehouseInfoBean.getRegion());
		addressByKeyMap.put(key, warehouseInfoBean.getAddress());
		telephoneByKeyMap.put(key, warehouseInfoBean.getTelephone());
		mobileByKeyMap.put(key, warehouseInfoBean.getMobile());
	}

	private void setMapValue(String key, SupplierInfoBean supplierInfoBean) {
		stationNameByKeyMap.put(key, supplierInfoBean.getSupplierName());
		contactNameByKeyMap.put(key, supplierInfoBean.getContactName());
		countryByKeyMap.put(key, supplierInfoBean.getCountry());
		provinceByKeyMap.put(key, supplierInfoBean.getProvince());
		cityByKeyMap.put(key, supplierInfoBean.getCity());
		regionByKeyMap.put(key, supplierInfoBean.getRegion());
		addressByKeyMap.put(key, supplierInfoBean.getAddress());
		telephoneByKeyMap.put(key, supplierInfoBean.getTelephone());
		mobileByKeyMap.put(key, supplierInfoBean.getMobile());
	}

	private String getValueFromMapByKey(Map<String, String> map, int infoType, int infoId) {
		String key = getMapKey(infoType, infoId);
		String result = map.get(key);
		if (result == null) {
			if (infoType == InfoTypeConfig.INFO_TYPE_WAREHOUSE) {
				WarehouseInfoBean warehouseInfoBean = warehouseInfoCache.getWarehouseInfoByKey(infoId);
				if (warehouseInfoBean != null) {
					setMapValue(key, warehouseInfoBean);
				}
			} else if (infoType == InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS) {
				SupplierInfoBean supplierInfoBean = supplierInfoCache.getSupplierInfoByAddressKey(infoId);
				if (supplierInfoBean != null && supplierInfoBean.getSupplierId() > 0) {
					setMapValue(key, supplierInfoBean);
				}
			}
		}
		result = map.get(key);
		return result == null ? "" : result;
	}

}
