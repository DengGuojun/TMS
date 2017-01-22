package com.lpmas.tms.transporter.business;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;
import com.lpmas.tms.express.cache.ExpressCompanyInfoCache;
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;
import com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache;
import com.lpmas.tms.transporter.bean.TransporterInfoBean;

public class TransporterInfoBusiness {
	public List<TransporterInfoBean> getTransporterInfoListByType(int transporterType) {
		List<TransporterInfoBean> list = null;

		if (TmsConfig.TRANSPROTER_TYPE_EXPRESS == transporterType) {
			list = getTransporterInfoListFromExpressCompanyInfoList();
		} else if (TmsConfig.TRANSPROTER_TYPE_LOGISTICS == transporterType) {
			list = getTransporterInfoListFromLogisticsCompanyInfoList();
		}

		return list;
	}

	private List<TransporterInfoBean> getTransporterInfoListFromExpressCompanyInfoList() {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();

		ExpressCompanyInfoCache cache = new ExpressCompanyInfoCache();
		List<ExpressCompanyInfoBean> expressCompanyList = cache.getExpressCompanyInfoAllList();
		for (ExpressCompanyInfoBean expressCompanyBean : expressCompanyList) {
			TransporterInfoBean bean = new TransporterInfoBean();
			bean.setTransporterType(TmsConfig.TRANSPROTER_TYPE_EXPRESS);
			bean.setTransporterId(expressCompanyBean.getCompanyId());
			bean.setTransporterName(expressCompanyBean.getCompanyName());
			bean.setTransporterCode(expressCompanyBean.getCompanyCode());
			list.add(bean);
		}

		return list;
	}

	private List<TransporterInfoBean> getTransporterInfoListFromLogisticsCompanyInfoList() {
		List<TransporterInfoBean> list = new ArrayList<TransporterInfoBean>();

		LogisticsCompanyInfoCache cache = new LogisticsCompanyInfoCache();
		List<LogisticsCompanyInfoBean> logisticsCompanyList = cache.getLogisticsCompanyInfoAllList();
		for (LogisticsCompanyInfoBean logisticsCompanyBean : logisticsCompanyList) {
			TransporterInfoBean bean = new TransporterInfoBean();
			bean.setTransporterType(TmsConfig.TRANSPROTER_TYPE_LOGISTICS);
			bean.setTransporterId(logisticsCompanyBean.getCompanyId());
			bean.setTransporterName(logisticsCompanyBean.getCompanyName());
			bean.setTransporterCode(logisticsCompanyBean.getCompanyCode());
			list.add(bean);
		}

		return list;
	}
}
