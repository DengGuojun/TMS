<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.system.bean.*"  %>
<%@ page import="com.lpmas.tms.express.bean.*"  %>
<% 
ExpressStoreDefaultBean bean = (ExpressStoreDefaultBean)request.getAttribute("ExpressStoreInfo");
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");

List<ExpressCompanyInfoBean> companyList = (List<ExpressCompanyInfoBean>)request.getAttribute("CompanyList");
List<StoreInfoBean> storeList = (List<StoreInfoBean>)request.getAttribute("StoreList");
Map<String, String> storeMap = ListKit.list2Map(storeList, "StoreId", "StoreName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商店默认快递管理</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit">商店默认快递管理</p>
<form name="formSearch" method="get" action="/express/ExpressStoreDefaultManage.do">
  <div class="search_form">
    <em class="em1">商店：</em>
    <select name="storeId" id="storeId">
    	<%
    	int storeId = ParamKit.getIntParameter(request, "storeId", 0);
    	for(StoreInfoBean storeBean:storeList){ %>
          <option value="<%=storeBean.getStoreId() %>" <%=(storeBean.getStoreId()==storeId)?"selected":"" %>><%=storeBean.getStoreName() %></option>
        <%} %>
    </select>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
  </div>
</form>
<form id="formData" name="formData" method="post" action="/shipment/ExpressStoreDefaultManage.do" onsubmit="javascript:return checkForm('formData');">
  <input type="hidden" name="storeId" id="storeId" value="<%=bean.getStoreId() %>"/>
  <div class="modify_form">
    <p>
      <em class="int_label">商店：</em>
      <%=MapKit.getValueFromMap(bean.getStoreId(), storeMap) %>
    </p>
    <p>
      <em class="int_label">公司：</em>
      <select name="companyId" id="companyId">
    	<%for(ExpressCompanyInfoBean companyBean:companyList){ %>
          <option value="<%=companyBean.getCompanyId() %>" <%=(companyBean.getCompanyId()==bean.getCompanyId())?"selected":"" %>><%=companyBean.getCompanyName() %></option>
        <%} %>
      </select>
      <em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">默认价格：</em>
      <input type="text" name="defaultPrice" id="defaultPrice" size="20" maxlength="20" value="<%=bean.getDefaultPrice() %>" checkStr="默认价格;num;false;;20"/></em>
    </p>
    <p>
      <em class="int_label">状态：</em>
      <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
    </p>
    <p class="p_top_border">
      <em class="int_label">备注：</em>
      <textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=bean.getMemo() %></textarea>
    </p>
  </div>
  <div class="div_center"><input type="submit" name="button" id="button" class="modifysubbtn" value="提交" /></div>
</form>
</body>
</html>