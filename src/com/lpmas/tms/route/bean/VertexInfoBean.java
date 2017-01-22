package com.lpmas.tms.route.bean;

public class VertexInfoBean {
	private int infoType = 0;
	private int infoId = 0;

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VertexInfoBean) {
			VertexInfoBean param = (VertexInfoBean) obj;
			if (this.infoId == param.getInfoId() && this.infoType == param.getInfoType()) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + String.valueOf(infoType).hashCode();
		result = result * 31 + String.valueOf(infoId).hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "infoType=" + infoType + "&infoId=" + infoId;
	}
}
