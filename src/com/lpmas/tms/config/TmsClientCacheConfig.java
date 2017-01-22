package com.lpmas.tms.config;

public class TmsClientCacheConfig {
	public static final String EXPRESS_COMPANY_ID_BY_CODE_KEY = "EXPRESS_COMPANY_ID_BY_CODE_";
	public static final String EXPRESS_COMPANY_CODE_BY_KEY = "EXPRESS_COMPANY_CODE_BY_";
	public static final String EXPRESS_COMPANY_INFO_BY_KEY = "EXPRESS_COMPANY_INFO_BY_";
	public static final String EXPRESS_COMPANY_INFO_BY_CODE_KEY = "EXPRESS_COMPANY_INFO_BY_CODE_";
	public static final String EXPRESS_COMPANY_INFO_ALL_LIST_KEY = "EXPRESS_COMPANY_INFO_ALL_LIST";

	public static final String LOGISTICS_COMPANY_ID_BY_CODE_KEY = "LOGISTICS_COMPANY_ID_BY_CODE_";
	public static final String LOGISTICS_COMPANY_CODE_BY_KEY = "LOGISTICS_COMPANY_CODE_BY_";
	public static final String LOGISTICS_COMPANY_INFO_BY_KEY = "LOGISTICS_COMPANY_INFO_BY_";
	public static final String LOGISTICS_COMPANY_INFO_BY_CODE_KEY = "LOGISTICS_COMPANY_INFO_BY_CODE_";
	public static final String LOGISTICS_COMPANY_INFO_ALL_LIST_KEY = "LOGISTICS_COMPANY_INFO_ALL_LIST";

	public static final String TRANSPORTER_NAME_BY_KEY_MAP_KEY = "TRANSPORTER_NAME_BY_KEY_MAP";
	public static final String TRANSPORTER_CODE_BY_KEY_MAP_KEY = "TRANSPORTER_CODE_BY_KEY_MAP";
	public static final String TRANSPORTER_INFO_LIST_BY_TYPE_KEY = "TRANSPORTER_INFO_LIST_BY_TYPE_";

	public static String getExpressCompanyIdByCodeKey(String companyCode) {
		return EXPRESS_COMPANY_ID_BY_CODE_KEY + companyCode;
	}

	public static String getExpressCompanyCodeByKey(int companyId) {
		return EXPRESS_COMPANY_CODE_BY_KEY + companyId;
	}

	public static String getExpressCompanyInfoKey(int companyId) {
		return EXPRESS_COMPANY_INFO_BY_KEY + companyId;
	}

	public static String getExpressCompanyInfoByCodeKey(String companyCode) {
		return EXPRESS_COMPANY_INFO_BY_CODE_KEY + companyCode;
	}

	public static String getLogisticsCompanyIdByCodeKey(String companyCode) {
		return LOGISTICS_COMPANY_ID_BY_CODE_KEY + companyCode;
	}

	public static String getLogisticsCompanyCodeByKey(int companyId) {
		return LOGISTICS_COMPANY_CODE_BY_KEY + companyId;
	}

	public static String getLogisticsCompanyInfoKey(int companyId) {
		return LOGISTICS_COMPANY_INFO_BY_KEY + companyId;
	}

	public static String getLogisticsCompanyInfoByCodeKey(String companyCode) {
		return LOGISTICS_COMPANY_INFO_BY_CODE_KEY + companyCode;
	}

	public static String getTransporterInfoListByTypeKey(int transporterType) {
		return TRANSPORTER_INFO_LIST_BY_TYPE_KEY + transporterType;
	}
}
