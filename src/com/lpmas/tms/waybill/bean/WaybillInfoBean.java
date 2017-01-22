package com.lpmas.tms.waybill.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class WaybillInfoBean {
	@FieldTag(name = "运单ID")
	private int waybillId = 0;
	@FieldTag(name = "运单编号")
	private String waybillNumber = "";
	@FieldTag(name = "承运方ID")
	private int logisticsCompanyId = 0;
	@FieldTag(name = "路径ID")
	private int routeId = 0;
	@FieldTag(name = "信息类型")
	private int infoType = 0;
	@FieldTag(name = "信息ID")
	private int infoId = 0;
	@FieldTag(name = "发货人")
	private String consignerName = "";
	@FieldTag(name = "发货人国家")
	private String consignerCountry = "";
	@FieldTag(name = "发货人省份")
	private String consignerProvince = "";
	@FieldTag(name = "发货人城市")
	private String consignerCity = "";
	@FieldTag(name = "发货人地区")
	private String consignerRegion = "";
	@FieldTag(name = "发货人地址")
	private String consignerAddress = "";
	@FieldTag(name = "发货人电话")
	private String consignerTelephone = "";
	@FieldTag(name = "发货人手机")
	private String consignerMobile = "";
	@FieldTag(name = "收货方")
	private int receiverType = 0;
	@FieldTag(name = "收货人")
	private String receiverName = "";
	@FieldTag(name = "收货人国家")
	private String receiverCountry = "";
	@FieldTag(name = "收货人省份")
	private String receiverProvince = "";
	@FieldTag(name = "收货人城市")
	private String receiverCity = "";
	@FieldTag(name = "收货人地区")
	private String receiverRegion = "";
	@FieldTag(name = "收货人地址")
	private String receiverAddress = "";
	@FieldTag(name = "收货人电话")
	private String receiverTelephone = "";
	@FieldTag(name = "收货人手机")
	private String receiverMobile = "";
	@FieldTag(name = "总件数")
	private int totalQuantity = 0;
	@FieldTag(name = "总重量")
	private double totalWeight = 0;
	@FieldTag(name = "运费")
	private double freight = 0;
	@FieldTag(name = "提货费")
	private double pickupCharge = 0;
	@FieldTag(name = "送货费")
	private double deliveryCharge = 0;
	@FieldTag(name = "进仓费")
	private double warehouseEntryCharge = 0;
	@FieldTag(name = "包装费")
	private double packageCharge = 0;
	@FieldTag(name = "保险费")
	private double insuranceCharge = 0;
	@FieldTag(name = "其他费用")
	private double otherCharge = 0;
	@FieldTag(name = "总金额")
	private double totalAmount = 0;
	@FieldTag(name = "审批状态")
	private String approvalStatus = "";
	@FieldTag(name = "运单状态")
	private String waybillStatus = "";
	@FieldTag(name = "同步状态")
	private String syncStatus = "";
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

	public int getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(int waybillId) {
		this.waybillId = waybillId;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public int getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(int logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

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

	public String getConsignerName() {
		return consignerName;
	}

	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}

	public String getConsignerCountry() {
		return consignerCountry;
	}

	public void setConsignerCountry(String consignerCountry) {
		this.consignerCountry = consignerCountry;
	}

	public String getConsignerProvince() {
		return consignerProvince;
	}

	public void setConsignerProvince(String consignerProvince) {
		this.consignerProvince = consignerProvince;
	}

	public String getConsignerCity() {
		return consignerCity;
	}

	public void setConsignerCity(String consignerCity) {
		this.consignerCity = consignerCity;
	}

	public String getConsignerRegion() {
		return consignerRegion;
	}

	public void setConsignerRegion(String consignerRegion) {
		this.consignerRegion = consignerRegion;
	}

	public String getConsignerAddress() {
		return consignerAddress;
	}

	public void setConsignerAddress(String consignerAddress) {
		this.consignerAddress = consignerAddress;
	}

	public String getConsignerTelephone() {
		return consignerTelephone;
	}

	public void setConsignerTelephone(String consignerTelephone) {
		this.consignerTelephone = consignerTelephone;
	}

	public String getConsignerMobile() {
		return consignerMobile;
	}

	public void setConsignerMobile(String consignerMobile) {
		this.consignerMobile = consignerMobile;
	}

	public int getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(int receiverType) {
		this.receiverType = receiverType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverCountry() {
		return receiverCountry;
	}

	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverRegion() {
		return receiverRegion;
	}

	public void setReceiverRegion(String receiverRegion) {
		this.receiverRegion = receiverRegion;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverTelephone() {
		return receiverTelephone;
	}

	public void setReceiverTelephone(String receiverTelephone) {
		this.receiverTelephone = receiverTelephone;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public double getPickupCharge() {
		return pickupCharge;
	}

	public void setPickupCharge(double pickupCharge) {
		this.pickupCharge = pickupCharge;
	}

	public double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public double getWarehouseEntryCharge() {
		return warehouseEntryCharge;
	}

	public void setWarehouseEntryCharge(double warehouseEntryCharge) {
		this.warehouseEntryCharge = warehouseEntryCharge;
	}

	public double getPackageCharge() {
		return packageCharge;
	}

	public void setPackageCharge(double packageCharge) {
		this.packageCharge = packageCharge;
	}

	public double getInsuranceCharge() {
		return insuranceCharge;
	}

	public void setInsuranceCharge(double insuranceCharge) {
		this.insuranceCharge = insuranceCharge;
	}

	public double getOtherCharge() {
		return otherCharge;
	}

	public void setOtherCharge(double otherCharge) {
		this.otherCharge = otherCharge;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getWaybillStatus() {
		return waybillStatus;
	}

	public void setWaybillStatus(String waybillStatus) {
		this.waybillStatus = waybillStatus;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
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