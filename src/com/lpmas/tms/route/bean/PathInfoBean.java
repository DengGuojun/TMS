package com.lpmas.tms.route.bean;

import java.sql.Timestamp;
import java.util.List;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class PathInfoBean extends MongoDBDocumentBean<String> {

	private int sourceInfoType = 0;
	private int sourceInfoId = 0;
	private String sourceInfoName = "";
	private int destinationInfoType = 0;
	private int destinationInfoId = 0;
	private String destinationInfoName = "";
	private double totalDuration = 0;
	private double totalPrice = 0;
	private int status = 0;
	private Timestamp lastUpdate = null;
	private List<WeightedEdgeInfoBean> pathList;
	public double getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<WeightedEdgeInfoBean> getPathList() {
		return pathList;
	}

	public void setPathList(List<WeightedEdgeInfoBean> pathList) {
		this.pathList = pathList;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSourceInfoName() {
		return sourceInfoName;
	}

	public void setSourceInfoName(String sourceInfoName) {
		this.sourceInfoName = sourceInfoName;
	}

	public String getDestinationInfoName() {
		return destinationInfoName;
	}

	public void setDestinationInfoName(String destinationInfoName) {
		this.destinationInfoName = destinationInfoName;
	}
	

}
