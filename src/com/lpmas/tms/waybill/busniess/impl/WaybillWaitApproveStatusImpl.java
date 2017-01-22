package com.lpmas.tms.waybill.busniess.impl;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillStatus;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.dao.WaybillInfoDao;

public class WaybillWaitApproveStatusImpl implements WaybillStatus {

	private WaybillInfoBean bean;

	public WaybillWaitApproveStatusImpl(WaybillInfoBean bean) {
		this.bean = bean;
	}

	@Override
	public int commit() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public int cancelCommit() {
		int result = Constants.STATUS_NOT_VALID;
		WaybillInfoBean originalBean = new WaybillInfoBean();
		BeanKit.copyBean(originalBean, bean);
		bean.setApprovalStatus(WaybillConfig.REVIEW_STATUS_UNCOMMIT);
		bean.setWaybillStatus(WaybillConfig.WAYBILL_STATUS_EDIT);
		WaybillInfoDao dao = new WaybillInfoDao();
		if (dao.updateWaybillInfo(bean) > 0) {
			result = Constants.STATUS_VALID;
		}
		return result;
	}

	@Override
	public int approve() {
		int result = Constants.STATUS_NOT_VALID;
		WaybillInfoBean originalBean = new WaybillInfoBean();
		BeanKit.copyBean(originalBean, bean);
		bean.setApprovalStatus(WaybillConfig.REVIEW_STATUS_PASS);
		bean.setWaybillStatus(WaybillConfig.WAYBILL_STATUS_APPROVED);
		WaybillInfoDao dao = new WaybillInfoDao();
		if (dao.updateWaybillInfo(bean) > 0) {
			result = Constants.STATUS_VALID;
		}
		return result;
	}

	@Override
	public int reject() {
		int result = Constants.STATUS_NOT_VALID;
		WaybillInfoBean originalBean = new WaybillInfoBean();
		BeanKit.copyBean(originalBean, bean);
		bean.setApprovalStatus(WaybillConfig.REVIEW_STATUS_FAIL);
		bean.setWaybillStatus(WaybillConfig.WAYBILL_STATUS_EDIT);
		WaybillInfoDao dao = new WaybillInfoDao();
		if (dao.updateWaybillInfo(bean) > 0) {
			result = Constants.STATUS_VALID;
		}
		return result;
	}

	@Override
	public int placeOrder() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public int receive() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public int close() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public boolean committable() {
		return false;
	}

	@Override
	public boolean cancelCommittable() {
		return true;
	}

	@Override
	public boolean approvable() {
		return true;
	}

	@Override
	public boolean rejectable() {
		return true;
	}

	@Override
	public boolean placeOrderable() {
		return false;
	}

	@Override
	public boolean receiveable() {
		return false;
	}

	@Override
	public boolean closable() {
		return false;
	}
	
	@Override
	public boolean isLock() {
		return true;
	}

}
