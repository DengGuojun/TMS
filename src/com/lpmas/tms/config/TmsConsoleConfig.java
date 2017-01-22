package com.lpmas.tms.config;

import com.lpmas.framework.config.Constants;

public class TmsConsoleConfig {
	// 配置文件路径
	public static final String TMS_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/tms_config";

	// 页面路径
	public final static String PAGE_PATH = Constants.PAGE_PATH + "tms/";

	public final static int DEFAULT_PAGE_NUM = 1;
	public final static int DEFAULT_PAGE_SIZE = 20;

	// jsonp获取，callback函数
	public final static String JSONP_CALLBACK = "jsonCallback";
}
