package com.lpmas.tms.waybill.bean;

public class WaybillEntityBean extends WaybillInfoBean {

	private String routeName = "";
	private String wareNumber = "";

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getWareNumber() {
		return wareNumber;
	}

	public void setWareNumber(String wareNumber) {
		this.wareNumber = wareNumber;
	}

}
