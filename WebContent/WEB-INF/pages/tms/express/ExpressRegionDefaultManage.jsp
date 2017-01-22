<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.system.bean.*"  %>
<%@ page import="com.lpmas.tms.express.bean.*"  %>
<% 
ExpressRegionDefaultBean bean = (ExpressRegionDefaultBean)request.getAttribute("ExpressInfo");
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");

List<ExpressCompanyInfoBean> companyList = (List<ExpressCompanyInfoBean>)request.getAttribute("CompanyList");
List<StoreInfoBean> storeList = (List<StoreInfoBean>)request.getAttribute("StoreList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默认快递地区管理</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit">默认快递地区管理</p>
<form id="formData" name="formData" method="post" action="ExpressRegionDefaultManage.do" onsubmit="javascript:return checkForm('formData');">
  <input type="hidden" name="expressId" id="expressId" value="<%=bean.getExpressId() %>"/>
  <div class="modify_form">
    <p>
      <em class="int_label">商店：</em>
      <select name="storeId" id="storeId">
    	<%for(StoreInfoBean storeBean:storeList){ %>
          <option value="<%=storeBean.getStoreId() %>" <%=(storeBean.getStoreId()==bean.getStoreId())?"selected":"" %>><%=storeBean.getStoreName() %></option>
        <%} %>
      </select>
      <em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">国家：</em>
      <input type="text" name="country" id="country" size="50" maxlength="100" value="<%=bean.getCountry() %>" checkStr="国家;txt;true;;100"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">省份：</em>
      <input type="text" name="province" id="province" size="50" maxlength="100" value="<%=bean.getProvince() %>" checkStr="省份;txt;false;;100"/>
    </p>
    <p>
      <em class="int_label">城市：</em>
      <input type="text" name="city" id="city" size="50" maxlength="100" value="<%=bean.getCity() %>" checkStr="城市;txt;false;;100"/>
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