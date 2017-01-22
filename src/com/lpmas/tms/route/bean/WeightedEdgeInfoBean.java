package com.lpmas.tms.route.bean;

public class WeightedEdgeInfoBean {

	/**
	 * 
	 */
	private int sourceInfoType = 0;
	private int sourceInfoId = 0;
	private int destinationInfoType = 0;
	private int destinationInfoId = 0;
	private int companyId = 0;
	private int routeId = 0;
	private double duration = 0;
	private String currency = "";
	private double transportPrice = 0;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(double transportPrice) {
		this.transportPrice = transportPrice;
	}

	public int getSourceInfoType() {
		return sourceInfoType;
	}

	public void setSourceInfoType(int sourceInfoType) {
		this.sourceInfoType = sourceInfoType;
	}

	public int getSourceInfoId() {
		return sourceInfoId;
	}

	public void setSourceInfoId(int sourceInfoId) {
		this.sourceInfoId = sourceInfoId;
	}

	public int getDestinationInfoType() {
		return destinationInfoType;
	}

	public void setDestinationInfoType(int destinationInfoType) {
		this.destinationInfoType = destinationInfoType;
	}

	public int getDestinationInfoId() {
		return destinationInfoId;
	}

	public void setDestinationInfoId(int destinationInfoId) {
		this.destinationInfoId = destinationInfoId;
	}
	
	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WeightedEdgeInfoBean) {
			WeightedEdgeInfoBean param = (WeightedEdgeInfoBean) obj;
			if (sourceInfoType == param.getSourceInfoType() && sourceInfoId == param.getSourceInfoId()
					&& destinationInfoType == param.getDestinationInfoType() && destinationInfoId == param.getDestinationInfoId()
					&& companyId == param.getCompanyId()) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + String.valueOf(sourceInfoType).hashCode();
		result = result * 31 + String.valueOf(sourceInfoId).hashCode();
		result = result * 31 + String.valueOf(destinationInfoType).hashCode();
		result = result * 31 + String.valueOf(destinationInfoId).hashCode();
		result = result * 31 + String.valueOf(companyId).hashCode();
		return result;
	}

}
