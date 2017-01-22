package com.lpmas.tms.express.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.cache.ExpressCompanyInfoCache;
import com.lpmas.tms.express.dao.ExpressCompanyInfoDao;
import com.lpmas.tms.transporter.cache.TransporterInfoCache;

public class ExpressCompanyInfoBusiness {
	public int addExpressCompanyInfo(ExpressCompanyInfoBean bean) {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		int result = dao.insertExpressCompanyInfo(bean);
		if (result > 0) {
			refreshCache(bean);
		}
		return result;
	}

	public int updateExpressCompanyInfo(ExpressCompanyInfoBean bean) {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		int result = dao.updateExpressCompanyInfo(bean);
		if (result > 0) {
			refreshCache(bean);
		}
		return result;
	}

	private void refreshCache(ExpressCompanyInfoBean bean) {
		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		if (bean.getCompanyId() > 0) {
			cache.refreshExpressCompanyInfoByKey(bean.getCompanyId());
			cache.refreshExpressCompanyInfoByCode(bean.getCompanyCode());
		}
		cache.refreshExpressCompanyInfoAllList();

		TransporterInfoCache transporterInfoCache = new TransporterInfoCache();
		transporterInfoCache.refreshTransporterInfoListByType(TmsConfig.TRANSPROTER_TYPE_EXPRESS);
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByKey(int companyId) {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		return dao.getExpressCompanyInfoByKey(companyId);
	}

	public ExpressCompanyInfoBean getExpressCompanyInfoByCode(String companyCode) {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		return dao.getExpressCompanyInfoByCode(companyCode);
	}

	public boolean isDuplicateCompanyCode(int companyId, String companyCode) {
		ExpressCompanyInfoBean bean = getExpressCompanyInfoByCode(companyCode);
		if (bean == null) {
			return false;
		} else {
			if (companyId > 0 && companyId == bean.getCompanyId()) {
				return false;
			}
		}
		return true;
	}

	public List<ExpressCompanyInfoBean> getExpressCompanyInfoAllList() {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		return dao.getExpressCompanyInfoAllList();
	}

	public PageResultBean<ExpressCompanyInfoBean> getExpressCompanyInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		ExpressCompanyInfoDao dao = new ExpressCompanyInfoDao();
		return dao.getExpressCompanyInfoPageListByMap(condMap, pageBean);
	}
}