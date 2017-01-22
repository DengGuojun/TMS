<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.tms.config.*"  %>
<%
String DOMAIN = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "DEFAULT_DOMAIN");
String STATIC_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "STATIC_URL");
String SRM_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "SRM_URL");
String ERP_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "ERP_URL");
String TMS_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "TMS_URL");
String REGION_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "REGION_URL");
%>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/ui.js"></script>