package com.lpmas.tms.waybill.busniess;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.cache.WaybillInfoCache;
import com.lpmas.tms.waybill.dao.WaybillInfoDao;

public class WaybillInfoBusiness {
	public int addWaybillInfo(WaybillInfoBean bean) {
		WaybillInfoDao dao = new WaybillInfoDao();
		int result = dao.insertWaybillInfo(bean);
		if(result>0){
			refreshCache();
		}
		return result;
	}

	public int updateWaybillInfo(WaybillInfoBean bean) {
		WaybillInfoDao dao = new WaybillInfoDao();
		int result = dao.updateWaybillInfo(bean);
		if(result>0){
			refreshCache();
		}
		return result;
	}
	
	private void refreshCache(){
		WaybillInfoCache cache = new WaybillInfoCache();
		cache.refreshWaybillCreateUserListCache();
	}

	public WaybillInfoBean getWaybillInfoByKey(int waybillId) {
		WaybillInfoDao dao = new WaybillInfoDao();
		return dao.getWaybillInfoByKey(waybillId);
	}

	public PageResultBean<WaybillInfoBean> getWaybillInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		WaybillInfoDao dao = new WaybillInfoDao();
		return dao.getWaybillInfoPageListByMap(condMap, pageBean);
	}

	public List<Integer> getWaybillInfoCreaterUserAllList() {
		WaybillInfoDao dao = new WaybillInfoDao();
		return dao.getWaybillInfoCreaterUserList(Constants.STATUS_VALID);
	}

	public int getWaybillInfoCountForToday() {
		WaybillInfoDao dao = new WaybillInfoDao();
		return dao.getWaybillInfoCountForToday();
	}

	public int removeWaybillInfo(WaybillInfoBean bean) {
		bean.setModifyTime(DateKit.getCurrentTimestamp());
		bean.setStatus(Constants.STATUS_NOT_VALID);
		return updateWaybillInfo(bean);
	}
}