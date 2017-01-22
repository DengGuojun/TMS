package com.lpmas.tms.waybill.busniess;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.factory.WaybillStatusFactory;

public class WaybillStatusProcessor {

	private WaybillStatus wayBillStatus;
	public static Lock statusLock = new ReentrantLock();

	public WaybillStatusProcessor(WaybillInfoBean bean) {
		WaybillStatusFactory factory = new WaybillStatusFactory();
		wayBillStatus = factory.getWayBillStatus(bean);
	}

	public int commit() {
		return wayBillStatus.commit();
	}

	public int cancelCommit() {
		return wayBillStatus.cancelCommit();
	}

	public int approve() {
		return wayBillStatus.approve();
	}

	public int reject() {
		return wayBillStatus.reject();
	}

	public int placeOrder() {
		return wayBillStatus.placeOrder();
	}

	public int receive() {
		return wayBillStatus.receive();
	}

	public int close() {
		return wayBillStatus.close();
	}

	public boolean committable() {
		return wayBillStatus.committable();
	}

	public boolean cancelCommittable() {
		return wayBillStatus.cancelCommittable();
	}

	public boolean approvable() {
		return wayBillStatus.approvable();
	}

	public boolean rejectable() {
		return wayBillStatus.rejectable();
	}

	public boolean placeOrderable() {
		return wayBillStatus.placeOrderable();
	}

	public boolean receiveable() {
		return wayBillStatus.receiveable();
	}

	public boolean closable() {
		return wayBillStatus.closable();
	}
	
	public boolean isLock(){
		return wayBillStatus.isLock();
	}

}
