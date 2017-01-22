package com.lpmas.tms.route.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.constant.info.InfoTypeConfig;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class RouteConfig {

	//起点信息类型
	public static List<StatusBean<Integer, String>> SOURCE_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> SOURCE_INFO_TYPE_MAP = new HashMap<Integer, String>();
	
	//终点信息类型
	public static List<StatusBean<Integer, String>> DESTINATION_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> DESTINATION_INFO_TYPE_MAP = new HashMap<Integer, String>();

	static {
		initSourceInfoTypeList();
		initSourceInfoTypeMap();
		
		initDestinationInfoTypeList();
		initDestinationInfoTypeMap();
	}

	private static void initSourceInfoTypeList() {
		SOURCE_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		SOURCE_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS, "供应商"));
		SOURCE_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_WAREHOUSE, "仓库"));
	}

	private static void initSourceInfoTypeMap() {
		SOURCE_INFO_TYPE_MAP = StatusKit.toMap(SOURCE_INFO_TYPE_LIST);
	}

	private static void initDestinationInfoTypeList() {
		DESTINATION_INFO_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		DESTINATION_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS, "供应商"));
		DESTINATION_INFO_TYPE_LIST.add(new StatusBean<Integer, String>(InfoTypeConfig.INFO_TYPE_WAREHOUSE, "仓库"));
	}

	private static void initDestinationInfoTypeMap() {
		DESTINATION_INFO_TYPE_MAP = StatusKit.toMap(DESTINATION_INFO_TYPE_LIST);
	}
}
