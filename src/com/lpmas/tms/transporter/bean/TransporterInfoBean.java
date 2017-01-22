package com.lpmas.tms.transporter.bean;

public class TransporterInfoBean {
	private int transporterType = 0;
	private int transporterId = 0;
	private String transporterName = "";
	private String transporterCode = "";

	public int getTransporterType() {
		return transporterType;
	}

	public void setTransporterType(int transporterType) {
		this.transporterType = transporterType;
	}

	public int getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(int transporterId) {
		this.transporterId = transporterId;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getTransporterCode() {
		return transporterCode;
	}

	public void setTransporterCode(String transporterCode) {
		this.transporterCode = transporterCode;
	}
}
