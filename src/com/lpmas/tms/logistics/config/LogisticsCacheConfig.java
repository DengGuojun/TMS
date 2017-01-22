package com.lpmas.tms.logistics.config;

import java.text.MessageFormat;

public class LogisticsCacheConfig {
	public static final String LOGISTICS_COMPANY_INFO_ALL_LIST_KEY = "LOGISTICS_COMPANY_INFO_ALL_LIST";
	public static final String LOGISTICS_COMPANY_INFO_KEY = "LOGISTICS_COMPANY_INFO_{0}";
	public static final String LOGISTICS_COMPANY_INFO_BY_CODE_KEY = "LOGISTICS_COMPANY_INFO_BY_CODE_{0}";

	public static String getLogisticsCompanyInfoKey(int companyId) {
		return MessageFormat.format(LOGISTICS_COMPANY_INFO_KEY, companyId);
	}

	public static String getLogisticsCompanyInfoByCodeKey(String companyCode) {
		return MessageFormat.format(LOGISTICS_COMPANY_INFO_BY_CODE_KEY, companyCode);
	}
}
