package com.lpmas.tms.route.bean;

import org.jgrapht.graph.DefaultEdge;

public class EdgeInfoBean extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sourceInfoType = 0;
	private int sourceInfoId = 0;
	private int destinationInfoType = 0;
	private int destinationInfoId = 0;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EdgeInfoBean) {
			EdgeInfoBean param = (EdgeInfoBean) obj;
			if (sourceInfoType == param.getSourceInfoType() && sourceInfoId == param.getSourceInfoId()
					&& destinationInfoType == param.getDestinationInfoType() && destinationInfoId == param.getDestinationInfoId()) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result*31+String.valueOf(sourceInfoType).hashCode();
		result = result*31+String.valueOf(sourceInfoId).hashCode();
		result = result*31+String.valueOf(destinationInfoType).hashCode();
		result = result*31+String.valueOf(destinationInfoId).hashCode();
		return result;
	}

}
