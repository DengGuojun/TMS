package com.lpmas.tms.express.business;

import com.lpmas.framework.config.Constants;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.express.cache.ExpressCompanyInfoCache;
import com.lpmas.tms.express.cache.ExpressRegionDefaultCache;
import com.lpmas.tms.express.cache.ExpressStoreDefaultCache;

public class ExpressCompanyDefaultBusiness {

	public ExpressCompanyInfoBean getDefaultCompanyInfoByCity(int storeId, String country, String province, String city) {
		ExpressCompanyInfoBean result = new ExpressCompanyInfoBean();
		ExpressRegionDefaultCache cache = new ExpressRegionDefaultCache();
		ExpressRegionDefaultBean bean = cache.getExpressRegionDefaultByKey(storeId, country, province, city);
		if (!isValidExpressRegionDefaultBean(bean)) {
			result = getDefaultCompanyInfoByProvince(storeId, country, province);
		} else {
			result = getExpressCompanyInfoByKey(bean.getCompanyId());
		}
		return result;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByProvince(int storeId, String country, String province) {
		ExpressCompanyInfoBean result = new ExpressCompanyInfoBean();
		ExpressRegionDefaultCache cache = new ExpressRegionDefaultCache();
		ExpressRegionDefaultBean bean = cache.getExpressRegionDefaultByKey(storeId, country, province, "");
		if (!isValidExpressRegionDefaultBean(bean)) {
			result = getDefaultCompanyInfoByCountry(storeId, country);
		} else {
			result = getExpressCompanyInfoByKey(bean.getCompanyId());
		}
		return result;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByCountry(int storeId, String country) {
		ExpressCompanyInfoBean result = new ExpressCompanyInfoBean();
		ExpressRegionDefaultCache cache = new ExpressRegionDefaultCache();
		ExpressRegionDefaultBean bean = cache.getExpressRegionDefaultByKey(storeId, country, "", "");
		if (!isValidExpressRegionDefaultBean(bean)) {
			result = getDefaultCompanyInfoByStoreId(storeId);
		} else {
			result = getExpressCompanyInfoByKey(bean.getCompanyId());
		}
		return result;
	}

	public ExpressCompanyInfoBean getDefaultCompanyInfoByStoreId(int storeId) {
		ExpressCompanyInfoBean result = new ExpressCompanyInfoBean();
		ExpressStoreDefaultCache cache = new ExpressStoreDefaultCache();
		ExpressStoreDefaultBean bean = cache.getExpressStoreDefaultByKey(storeId);
		if (bean != null && bean.getCompanyId() > 0 && bean.getStatus() == Constants.STATUS_VALID) {
			result = getExpressCompanyInfoByKey(bean.getCompanyId());
		}
		return result;
	}

	private boolean isValidExpressRegionDefaultBean(ExpressRegionDefaultBean bean) {
		if (bean != null && bean.getStatus() == Constants.STATUS_VALID && bean.getCompanyId() > 0) {
			return true;
		}
		return false;
	}

	private ExpressCompanyInfoBean getExpressCompanyInfoByKey(int companyId) {
		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		ExpressCompanyInfoBean bean = cache.getExpressCompanyInfoByKey(companyId);
		if (bean != null && bean.getCompanyId() > 0 && bean.getStatus() == Constants.STATUS_VALID) {
			return bean;
		}
		return new ExpressCompanyInfoBean();
	}

}
