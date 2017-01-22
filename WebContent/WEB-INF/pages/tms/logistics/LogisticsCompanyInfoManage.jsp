<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.tms.logistics.bean.*"  %>
<% 
LogisticsCompanyInfoBean bean = (LogisticsCompanyInfoBean)request.getAttribute("CompanyInfo");
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流公司管理</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit">物流公司管理</p>
<form id="formData" name="formData" method="post" action="LogisticsCompanyInfoManage.do" onsubmit="javascript:return checkForm('formData');">
  <input type="hidden" name="companyId" id="companyId" value="<%=bean.getCompanyId() %>"/>
  <div class="modify_form">
    <p>
      <em class="int_label">公司代码：</em>
      <input type="text" name="companyCode" id="companyCode" size="20" maxlength="20" value="<%=bean.getCompanyCode() %>" checkStr="公司代码;code;true;;20"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">公司名称：</em>
      <input type="text" name="companyName" id="companyName" size="30" maxlength="100" value="<%=bean.getCompanyName() %>" checkStr="公司名称;txt;true;;100"/><em><span>*</span></em>
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