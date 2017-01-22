package com.lpmas.tms.express.business;

import java.util.HashMap;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.express.cache.ExpressRegionDefaultCache;
import com.lpmas.tms.express.dao.ExpressRegionDefaultDao;

public class ExpressRegionDefaultBusiness {
	public int addExpressRegionDefault(ExpressRegionDefaultBean bean) {
		ExpressRegionDefaultDao dao = new ExpressRegionDefaultDao();
		return dao.insertExpressRegionDefault(bean);
	}

	public int updateExpressRegionDefault(ExpressRegionDefaultBean bean) {
		ExpressRegionDefaultDao dao = new ExpressRegionDefaultDao();
		int result = dao.updateExpressRegionDefault(bean);
		if (result > 0) {
			ExpressRegionDefaultCache cache = new ExpressRegionDefaultCache();
			cache.removeExpressRegionDefaultByKey(bean.getStoreId(), bean.getCountry(), bean.getProvince(),
					bean.getCity());
		}
		return result;
	}

	public ExpressRegionDefaultBean getExpressRegionDefaultByKey(int expressId) {
		ExpressRegionDefaultDao dao = new ExpressRegionDefaultDao();
		return dao.getExpressRegionDefaultByKey(expressId);
	}

	public ExpressRegionDefaultBean getExpressRegionDefaultByKey(int storeId, String country, String province,
			String city) {
		ExpressRegionDefaultDao dao = new ExpressRegionDefaultDao();
		return dao.getExpressRegionDefaultByKey(storeId, country, province, city);
	}

	public PageResultBean<ExpressRegionDefaultBean> getExpressRegionDefaultPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		ExpressRegionDefaultDao dao = new ExpressRegionDefaultDao();
		return dao.getExpressRegionDefaultPageListByMap(condMap, pageBean);
	}

	public boolean isDuplicateKey(int expressId, int storeId, String country, String province, String city) {
		ExpressRegionDefaultBean bean = getExpressRegionDefaultByKey(storeId, country, province, city);
		if (bean == null) {
			return false;
		} else {
			if (expressId > 0 && expressId == bean.getExpressId()) {
				return false;
			}
		}
		return true;
	}
}