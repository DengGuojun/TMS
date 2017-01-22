package com.lpmas.tms.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TmsConfig {
	// APP ID
	public static final String APP_ID = "TMS";

	public static final int TRANSPROTER_TYPE_LOGISTICS = 1;// 物流
	public static final int TRANSPROTER_TYPE_EXPRESS = 2;// 快递
	public static List<StatusBean<Integer, String>> TRANSPORTER_TYPE_LIST;
	public static Map<Integer, String> TRANSPORTER_TYPE_MAP;

	static {
		initTransporterTypeList();
		initTransporterTypeMap();
	}

	private static void initTransporterTypeList() {
		TRANSPORTER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		TRANSPORTER_TYPE_LIST.add(new StatusBean<Integer, String>(TRANSPROTER_TYPE_LOGISTICS, "物流"));
		TRANSPORTER_TYPE_LIST.add(new StatusBean<Integer, String>(TRANSPROTER_TYPE_EXPRESS, "快递"));
	}

	private static void initTransporterTypeMap() {
		TRANSPORTER_TYPE_MAP = StatusKit.toMap(TRANSPORTER_TYPE_LIST);
	}
}
