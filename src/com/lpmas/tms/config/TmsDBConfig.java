package com.lpmas.tms.config;

import com.lpmas.framework.util.PropertiesKit;

public class TmsDBConfig {
	public static String DB_LINK_TMS_W = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME,
			"DB_LINK_TMS_W");

	public static String DB_LINK_TMS_R = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME,
			"DB_LINK_TMS_R");
}
