package com.lpmas.tms.transporter.config;

import java.text.MessageFormat;

public class TransporterCacheConfig {
	public static final String TRANSPORTER_INFO_LIST_BY_TYPE_KEY = "TRANSPORTER_INFO_LIST_BY_TYPE_{0}";

	public static String getTransporterInfoListByType(int transporterType) {
		return MessageFormat.format(TRANSPORTER_INFO_LIST_BY_TYPE_KEY, transporterType);
	}
}
