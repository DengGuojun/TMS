<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.admin.config.*"  %>
<%@ page import="com.lpmas.tms.route.bean.*"  %>
<%@ page import="com.lpmas.tms.logistics.bean.*"  %>
<%@ page import="com.lpmas.tms.route.config.*"  %>
<%
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<RouteOfferBean> list = (List<RouteOfferBean>)request.getAttribute("RouteOfferList");
RouteInfoBean route = (RouteInfoBean)request.getAttribute("RouteInfoBean");
List<RouteInfoBean> routeInfoList = (List<RouteInfoBean>) request.getAttribute("RouteInfoList");
List<LogisticsCompanyInfoBean> companyInfoList = (List<LogisticsCompanyInfoBean>) request.getAttribute("CompanyInfoList");
Map<Integer,String> routeInfoMap = (Map<Integer,String>) request.getAttribute("RouteInfoMap");
Map<Integer,String> CompanyInfoMap = (Map<Integer,String>) request.getAttribute("CompanyInfoMap");
Integer routeId = ParamKit.getIntParameter(request, "routeId", 0);

PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径报价</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit"><%=MapKit.getValueFromMap(routeId, routeInfoMap)%>路径报价</p>
<form name="formSearch" method="post" action="RouteOfferList.do">
  <div class="search_form">
    <%if(routeId == 0){ %>
  	<em class="em1">路径名称：</em>
    <select name="routeId" id="routeId">
    <option value=""></option>
	<%for (RouteInfoBean routeInfoBean : routeInfoList) {%>
	<option value="<%=routeInfoBean.getRouteId()%>" <%=routeInfoBean.getRouteId() == routeId ? "selected" : ""%>><%=routeInfoBean.getRouteName()%></option>
	<%}%></select>
	<%} else{%>
	<em class="em1">路径名称：</em>
	<label><%=route.getRouteName() %></label>
	<input type="hidden" name="routeId" id="routeId"  value="<%=routeId%>"/>
	<%} %>
    <em class="em1">物流公司：</em>
    <select name="companyId" id="companyId">
    <option value=""></option>
	<%for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {%>
	<option value="<%=logisticsCompanyInfoBean.getCompanyId()%>" <%=logisticsCompanyInfoBean.getCompanyId() == ParamKit.getIntParameter(request, "companyId", 0) ? "selected" : ""%>><%=logisticsCompanyInfoBean.getCompanyName()%></option>
	<%}%></select>
    <em class="em1">状态：</em>
    <select name="status" id="status">
    	<%
    	int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
          <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
        <%} %>
    </select>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
    <%if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)){ %>
    <input type="button" name="button" id="button" class="search_btn_sub" value="新建" onclick="javascript:location.href='/route/RouteOfferManage.do'">
    <%} %>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th>报价ID</th>
      <th>路径名称</th>
      <th>物流公司</th>
      <th>时效(天)</th>
      <th>运费(元/KG)</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%
    int rowCount = 0;
    for(RouteOfferBean bean:list){ 
    	rowCount++;
    %>
    <tr>
      <td><%=rowCount +(PAGE_BEAN.getCurrentPageNumber()-1)*TmsConsoleConfig.DEFAULT_PAGE_SIZE%></td>
      <td><%=MapKit.getValueFromMap(bean.getRouteId(), routeInfoMap)%></td>
      <td><%=MapKit.getValueFromMap(bean.getCompanyId(), CompanyInfoMap)%></td>
      <td align="right"><%=NumberKit.formatDouble(bean.getDuration(), 2)%></td>
      <td align="right"><%=NumberKit.formatDouble(bean.getTransportPrice(), 2)%></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
      <a href="/route/RouteOfferManage.do?companyId=<%=bean.getCompanyId()%>&routeId=<%=bean.getRouteId()%>&readOnly=true">查看</a> 
      <%if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.UPDATE)){ %>|<a href="/route/RouteOfferManage.do?companyId=<%=bean.getCompanyId()%>&routeId=<%=bean.getRouteId()%>&readOnly=false">修改</a><%} %>
      </td>
    </tr>
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
<%if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)){ %>
  <input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='/route/RouteOfferManage.do'">
<%} %>
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
</html>