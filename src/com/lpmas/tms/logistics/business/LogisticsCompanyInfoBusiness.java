package com.lpmas.tms.logistics.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache;
import com.lpmas.tms.logistics.dao.LogisticsCompanyInfoDao;
import com.lpmas.tms.transporter.cache.TransporterInfoCache;

public class LogisticsCompanyInfoBusiness {
	public int addLogisticsCompanyInfo(LogisticsCompanyInfoBean bean) {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		int result = dao.insertLogisticsCompanyInfo(bean);
		if (result > 0) {
			refreshCache(bean);
		}
		return result;
	}

	public int updateLogisticsCompanyInfo(LogisticsCompanyInfoBean bean) {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		int result = dao.updateLogisticsCompanyInfo(bean);
		if (result > 0) {
			refreshCache(bean);
		}
		return result;
	}

	private void refreshCache(LogisticsCompanyInfoBean bean) {
		LogisticsCompanyInfoCache cache = new LogisticsCompanyInfoCache();
		if (bean.getCompanyId() > 0) {
			cache.refreshLogisticsCompanyInfoByKey(bean.getCompanyId());
			cache.refreshLogisticsCompanyInfoByCode(bean.getCompanyCode());
		}
		cache.refreshLogisticsCompanyInfoAllList();

		TransporterInfoCache transporterInfoCache = new TransporterInfoCache();
		transporterInfoCache.refreshTransporterInfoListByType(TmsConfig.TRANSPROTER_TYPE_EXPRESS);
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByKey(int companyId) {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		return dao.getLogisticsCompanyInfoByKey(companyId);
	}

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByCode(String companyCode) {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		return dao.getLogisticsCompanyInfoByCode(companyCode);
	}

	public boolean isDuplicateCompanyCode(int companyId, String companyCode) {
		LogisticsCompanyInfoBean bean = getLogisticsCompanyInfoByCode(companyCode);
		if (bean == null) {
			return false;
		} else {
			if (companyId > 0 && companyId == bean.getCompanyId()) {
				return false;
			}
		}
		return true;
	}

	public List<LogisticsCompanyInfoBean> getLogisticsCompanyInfoAllList() {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		return dao.getLogisticsCompanyInfoAllList();
	}

	public PageResultBean<LogisticsCompanyInfoBean> getLogisticsCompanyInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		LogisticsCompanyInfoDao dao = new LogisticsCompanyInfoDao();
		return dao.getLogisticsCompanyInfoPageListByMap(condMap, pageBean);
	}

}