package com.lpmas.tms.route.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class RouteInfoBean {
	@FieldTag(name = "路径ID")
	private int routeId = 0;
	@FieldTag(name = "路径名称")
	private String routeName = "";
	@FieldTag(name = "起点信息类型")
	private int sourceInfoType = 0;
	@FieldTag(name = "起点信息ID")
	private int sourceInfoId = 0;
	@FieldTag(name = "终点信息类型")
	private int destinationInfoType = 0;
	@FieldTag(name = "终点信息ID")
	private int destinationInfoId = 0;
	@FieldTag(name = "状态")
	private int status = 0;
	@FieldTag(name = "创建时间")
	private Timestamp createTime = null;
	@FieldTag(name = "创建用户")
	private int createUser = 0;
	@FieldTag(name = "修改时间")
	private Timestamp modifyTime = null;
	@FieldTag(name = "修改用户")
	private int modifyUser = 0;
	@FieldTag(name = "备注")
	private String memo = "";

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}