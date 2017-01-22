package com.lpmas.tms.waybill.busniess.impl;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillStatus;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.dao.WaybillInfoDao;

public class WaybillEditStatusImpl implements WaybillStatus {
	private WaybillInfoBean bean;

	public WaybillEditStatusImpl(WaybillInfoBean bean) {
		this.bean = bean;
	}

	@Override
	public int commit() {
		int result = Constants.STATUS_NOT_VALID;
		WaybillInfoBean originalBean = new WaybillInfoBean();
		BeanKit.copyBean(originalBean, bean);
		bean.setApprovalStatus(WaybillConfig.REVIEW_STATUS_WAIT_APPROVE);
		bean.setWaybillStatus(WaybillConfig.WAYBILL_STATUS_WAIT_APPROVE);
		WaybillInfoDao dao = new WaybillInfoDao();
		if (dao.updateWaybillInfo(bean) > 0) {
			result = Constants.STATUS_VALID;
		}
		return result;
	}

	@Override
	public int cancelCommit() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public int approve() {
		return Constants.STATUS_NOT_VALID;
	}

	@Override
	public int reject() {
		return Constants.STATUS_NOT_VALID;
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
		return true;
	}

	@Override
	public boolean cancelCommittable() {
		return false;
	}

	@Override
	public boolean approvable() {
		return false;
	}

	@Override
	public boolean rejectable() {
		return false;
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
		return false;
	}

}
