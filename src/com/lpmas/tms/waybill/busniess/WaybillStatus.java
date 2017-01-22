package com.lpmas.tms.waybill.busniess;

public interface WaybillStatus {

	/**
	 * 提交审核
	 */
	public int commit();
	
	/**
	 * 取消审核
	 */
	public int cancelCommit();

	/**
	 * 审核成功
	 */
	public int approve();
	
	/**
	 * 审核失败
	 */
	public int reject();
	
	/**
	 * 
	 * 下单
	 */
	public int placeOrder();
	
	/**
	 * 
	 * 收货
	 */
	public int receive();
	
	/**
	 * 关闭
	 */
	public int close();

	public boolean committable();
	
	public boolean cancelCommittable();
	
	public boolean approvable();
	
	public boolean rejectable();
	
	public boolean placeOrderable();
	
	public boolean receiveable();
	
	public boolean closable();
	
	public boolean isLock();
}
