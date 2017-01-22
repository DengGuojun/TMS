package com.lpmas.tms.express.business;

import java.util.HashMap;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.express.cache.ExpressStoreDefaultCache;
import com.lpmas.tms.express.dao.ExpressStoreDefaultDao;

public class ExpressStoreDefaultBusiness {
	public int addExpressStoreDefault(ExpressStoreDefaultBean bean) {
		ExpressStoreDefaultDao dao = new ExpressStoreDefaultDao();
		return dao.insertExpressStoreDefault(bean);
	}

	public int updateExpressStoreDefault(ExpressStoreDefaultBean bean) {
		ExpressStoreDefaultDao dao = new ExpressStoreDefaultDao();
		int result = dao.updateExpressStoreDefault(bean);
		if (result > 0) {
			ExpressStoreDefaultCache cache = new ExpressStoreDefaultCache();
			cache.removeExpressStoreDefaultByKey(bean.getStoreId());
		}
		return result;
	}

	public ExpressStoreDefaultBean getExpressStoreDefaultByKey(int storeId) {
		ExpressStoreDefaultDao dao = new ExpressStoreDefaultDao();
		return dao.getExpressStoreDefaultByKey(storeId);
	}

	public PageResultBean<ExpressStoreDefaultBean> getExpressStoreDefaultPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		ExpressStoreDefaultDao dao = new ExpressStoreDefaultDao();
		return dao.getExpressStoreDefaultPageListByMap(condMap, pageBean);
	}
}