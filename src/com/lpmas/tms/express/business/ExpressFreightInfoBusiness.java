package com.lpmas.tms.express.business;

import java.util.HashMap;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.express.bean.ExpressFreightInfoBean;
import com.lpmas.tms.express.cache.ExpressFreightInfoCache;
import com.lpmas.tms.express.dao.ExpressFreightInfoDao;

public class ExpressFreightInfoBusiness {
	public int addExpressFreightInfo(ExpressFreightInfoBean bean) {
		ExpressFreightInfoDao dao = new ExpressFreightInfoDao();
		return dao.insertExpressFreightInfo(bean);
	}

	public int updateExpressFreightInfo(ExpressFreightInfoBean bean) {
		ExpressFreightInfoDao dao = new ExpressFreightInfoDao();
		int result = dao.updateExpressFreightInfo(bean);
		if (result > 0) {
			ExpressFreightInfoCache cache = new ExpressFreightInfoCache();
			cache.removeExpressFreightInfoByKey(bean.getCompanyId(), bean.getCountry(), bean.getProvince(),
					bean.getCity());
		}
		return result;
	}

	public ExpressFreightInfoBean getExpressFreightInfoByKey(int freightId) {
		ExpressFreightInfoDao dao = new ExpressFreightInfoDao();
		return dao.getExpressFreightInfoByKey(freightId);
	}

	public ExpressFreightInfoBean getExpressFreightInfoByKey(int companyId, String country, String province, String city) {
		ExpressFreightInfoDao dao = new ExpressFreightInfoDao();
		return dao.getExpressFreightInfoByKey(companyId, country, province, city);
	}

	public PageResultBean<ExpressFreightInfoBean> getExpressFreightInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		ExpressFreightInfoDao dao = new ExpressFreightInfoDao();
		return dao.getExpressFreightInfoPageListByMap(condMap, pageBean);
	}

	public boolean isDuplicateKey(int freightId, int companyId, String country, String province, String city) {
		ExpressFreightInfoBean bean = getExpressFreightInfoByKey(companyId, country, province, city);
		if (bean == null) {
			return false;
		} else {
			if (freightId > 0 && freightId == bean.getFreightId()) {
				return false;
			}
		}
		return true;
	}

	public double getExpressFreightByCity(int companyId, String country, String province, String city) {
		double result = 0;
		ExpressFreightInfoCache cache = new ExpressFreightInfoCache();
		ExpressFreightInfoBean bean = cache.getExpressFreightInfoByKey(companyId, country, province, city);
		if (isValidExpressFreightBean(bean)) {
			result = bean.getPrice();
		} else {
			result = getExpressFreightByProvince(companyId, country, province);
		}
		return result;
	}

	public double getExpressFreightByProvince(int companyId, String country, String province) {
		double result = 0;
		ExpressFreightInfoCache cache = new ExpressFreightInfoCache();
		ExpressFreightInfoBean bean = cache.getExpressFreightInfoByKey(companyId, country, province, "");
		if (isValidExpressFreightBean(bean)) {
			result = bean.getPrice();
		} else {
			result = getExpressFreightByCountry(companyId, country);
		}
		return result;
	}

	public double getExpressFreightByCountry(int companyId, String country) {
		double result = 0;
		ExpressFreightInfoCache cache = new ExpressFreightInfoCache();
		ExpressFreightInfoBean bean = cache.getExpressFreightInfoByKey(companyId, country, "", "");
		if (isValidExpressFreightBean(bean)) {
			result = bean.getPrice();
		}
		return result;
	}

	private boolean isValidExpressFreightBean(ExpressFreightInfoBean bean) {
		if (bean != null && bean.getStatus() == Constants.STATUS_VALID && bean.getPrice() > 0) {
			return true;
		}
		return false;
	}
}