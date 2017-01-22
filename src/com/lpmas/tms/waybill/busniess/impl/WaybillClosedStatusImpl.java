package com.lpmas.tms.waybill.busniess.impl;

import com.lpmas.framework.config.Constants;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillStatus;

public class WaybillClosedStatusImpl implements WaybillStatus {
	
	private WaybillInfoBean bean;

	public WaybillClosedStatusImpl(WaybillInfoBean bean) {
		this.bean = bean;
	}

	@Override
	public int commit() {
		return Constants.STATUS_NOT_VALID;
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
		return false;
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
		return true;
	}

}
