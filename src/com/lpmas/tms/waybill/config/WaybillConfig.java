package com.lpmas.tms.waybill.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.constant.info.InfoTypeConfig;
import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class WaybillConfig {
	// 承运单编码前缀
	public static final String TO_NUMBER_PREFIX = "CYD";
	// 审核状态
	public static final String REVIEW_STATUS_UNCOMMIT = "UNCOMMIT";
	public static final String REVIEW_STATUS_WAIT_APPROVE = "WAIT";
	public static final String REVIEW_STATUS_PASS = "PASS";
	public static final String REVIEW_STATUS_FAIL = "FAIL";
	public static List<StatusBean<String, String>> REVIEW_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> REVIEW_STATUS_MAP = new HashMap<String, String>();

	// 进度状态
	public static final String WAYBILL_STATUS_EDIT = "EDIT";
	public static final String WAYBILL_STATUS_WAIT_APPROVE = "WAIT_APPROVE";
	public static final String WAYBILL_STATUS_APPROVED = "APPROVED";
	public static final String WAYBILL_STATUS_PLACED_ORDER = "PLACED_ORDER";
	public static final String WAYBILL_STATUS_RECEIVED = "RECEIVED";
	public static final String WAYBILL_STATUS_CLOSED = "CLOSED";
	public static List<StatusBean<String, String>> WAYBILL_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> WAYBILL_STATUS_MAP = new HashMap<String, String>();

	// 承运单操作
	public static final String WAYBILL_ACTION_COMMIT = "COMMIT";
	public static final String WAYBILL_ACTION_CANCEL_COMMIT = "CANCEL_COMMIT";
	public static final String WAYBILL_ACTION_APPROVE = "APPROVE";
	public static final String WAYBILL_ACTION_REJECT = "REJECT";
	public static final String WAYBILL_ACTION_PLACE_ORDER = "PLACE_ORDER";
	public static final String WAYBILL_ACTION_REVEICE = "RECEIVE";
	public static final String WAYBILL_ACTION_CLOSE = "CLOSE";

	// 关联订单类型
	public static List<StatusBean<Integer, String>> WAYBILL_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> WAYBILL_INFO_TYPE_MAP = new HashMap<Integer, String>();

	static {

		initReviewStatusList();
		initReviewStatusMap();

		initWaybillStatusList();
		initWaybillStatusMap();

		initWaybillInfoTypeList();
		initWaybillInfoTypeMap();
	}

	private static void initReviewStatusList() {
		REVIEW_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(REVIEW_STATUS_UNCOMMIT, "未提交审批"));
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(REVIEW_STATUS_WAIT_APPROVE, "待审批"));
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(REVIEW_STATUS_PASS, "审批通过"));
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(REVIEW_STATUS_FAIL, "审批不通过"));
	}

	private static void initReviewStatusMap() {
		REVIEW_STATUS_MAP = StatusKit.toMap(REVIEW_STATUS_LIST);
	}

	private static void initWaybillStatusList() {
		WAYBILL_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_EDIT, "编辑"));
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_WAIT_APPROVE, "待审批"));
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_APPROVED, "审批通过"));
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_PLACED_ORDER, "已下单"));
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_RECEIVED, "已收货"));
		WAYBILL_STATUS_LIST.add(new StatusBean<String, String>(WAYBILL_STATUS_CLOSED, "已关闭"));
	}

	private static void initWaybillStatusMap() {
		WAYBILL_STATUS_MAP = StatusKit.toMap(WAYBILL_STATUS_LIST);
	}

	private static void initWaybillInfoTypeList() {
		WAYBILL_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		WAYBILL_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_DELIVERY_NOTE, "出货单"));
		WAYBILL_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_WAREHOUSE_TRANSFER_ORDER, "调拨出库单"));
	}

	private static void initWaybillInfoTypeMap() {
		WAYBILL_INFO_TYPE_MAP = StatusKit.toMap(WAYBILL_INFO_TYPE_LIST);
	}
}
