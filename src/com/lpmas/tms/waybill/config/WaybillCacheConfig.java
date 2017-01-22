package com.lpmas.tms.waybill.config;

public class WaybillCacheConfig {

	private static final String WAYBILL_CREATE_USER_LIST_KEY = "WAYBILL_CREATE_USER_LIST";
	
	public static String getWaybillCreateUserListKey(){
		return WAYBILL_CREATE_USER_LIST_KEY;
	}
}
