package com.lpmas.tms.config;

import java.text.MessageFormat;

public class TmsRedisConfig {

	public static final String REDIS_NAME = "tms";
	private static final String WATBILL_NUMBER_KEY = "WATBILL_NUMBER";

	public static String getWaybillNumberKey(String date) {
		return MessageFormat.format("{0}_{1}", WATBILL_NUMBER_KEY, date);
	}
}
