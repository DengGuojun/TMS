package com.lpmas.tms.express.config;

import java.text.MessageFormat;

public class ExpressCacheConfig {
	public static final String EXPRESS_COMPANY_INFO_ALL_LIST_KEY = "EXPRESS_COMPANY_INFO_ALL_LIST";
	public static final String EXPRESS_COMPANY_INFO_KEY = "EXPRESS_COMPANY_INFO_{0}";
	public static final String EXPRESS_COMPANY_INFO_BY_CODE_KEY = "EXPRESS_COMPANY_INFO_BY_CODE_{0}";
	public static final String EXPRESS_FREIGHT_INFO_KEY = "EXPRESS_FREIGHT_INFO_{0}_{1}_{2}_{3}";
	public static final String EXPRESS_REGION_DEFAULT_KEY = "EXPRESS_REGION_DEFAULT_{0}_{1}_{2}_{3}";
	public static final String EXPRESS_STORE_DEFAULT_KEY = "EXPRESS_STORE_DEFAULT_{0}";

	public static String getExpressCompanyInfoKey(int companyId) {
		return MessageFormat.format(EXPRESS_COMPANY_INFO_KEY, companyId);
	}

	public static String getExpressCompanyInfoByCodeKey(String companyCode) {
		return MessageFormat.format(EXPRESS_COMPANY_INFO_BY_CODE_KEY, companyCode);
	}

	public static String getExpressFreightInfoKey(int companyId, String country, String province, String city) {
		return MessageFormat.format(EXPRESS_FREIGHT_INFO_KEY, companyId, country, province, city);
	}

	public static String getExpressRegionDefaultKey(int storeId, String country, String province, String city) {
		return MessageFormat.format(EXPRESS_REGION_DEFAULT_KEY, storeId, country, province, city);
	}

	public static String getExpressStoreDefaultKey(int storeId) {
		return MessageFormat.format(EXPRESS_STORE_DEFAULT_KEY, storeId);
	}
}
