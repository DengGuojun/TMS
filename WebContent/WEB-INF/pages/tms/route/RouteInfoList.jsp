<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.constant.info.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.admin.config.*"  %>
<%@ page import="com.lpmas.tms.route.config.*"  %>
<%@ page import="com.lpmas.tms.route.bean.*"  %>
<%@ page import="com.lpmas.tms.route.business.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<RouteInfoBean> list = (List<RouteInfoBean>)request.getAttribute("RouteInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	StationInfoMediator mediator = new StationInfoMediator();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../include/header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径信息</title>
</head>
<body class="article_bg">
<p class="article_tit">路径信息</p>
<form name="formSearch" method="post" action="RouteInfoList.do">
  <div class="search_form">
  	<em class="em1">路径名称：</em>
    <input type="text" name="routeName" id=""routeName"" value="<%=ParamKit.getParameter(request, "routeName", "") %>" size="20"/>
    <em class="em1">状态：</em>
    <select name="status" id="status">
    	<%
    	int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
        <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
    <%} %>
    </select>
    <%if(adminUserHelper.hasPermission(RouteResource.ROUTE_INFO, OperationConfig.SEARCH)){ %>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
    <%} %>
    <%if(adminUserHelper.hasPermission(RouteResource.ROUTE_INFO, OperationConfig.CREATE)){ %>
  	<input type="button" class="search_btn_sub" name="button" id="button" value="新建" onclick="javascript:location.href='RouteInfoManage.do'">
  	<%} %>
  </div>
  <table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>路径ID</th>
      <th>路径名称</th>
      <th>起点类型</th>
      <th>起点站</th>
      <th>终点类型</th>
      <th>终点站</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%
    for(RouteInfoBean bean:list){%> 
    <tr>
      <td><%=bean.getRouteId() %></td>
      <td><%=bean.getRouteName() %></td>
      <td><%=MapKit.getValueFromMap(bean.getSourceInfoType(), RouteConfig.SOURCE_INFO_TYPE_MAP)%></td>
      <td><%=mediator.getStationNameByKey(bean.getSourceInfoType(), bean.getSourceInfoId())%></td>
      <td><%=MapKit.getValueFromMap(bean.getDestinationInfoType(), RouteConfig.DESTINATION_INFO_TYPE_MAP)%></td>
      <td><%=mediator.getStationNameByKey(bean.getDestinationInfoType(), bean.getDestinationInfoId()) %></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
      	<a href="/route/RouteInfoManage.do?routeId=<%=bean.getRouteId()%>&readOnly=true">查看</a> 
      	<%if(adminUserHelper.hasPermission(RouteResource.ROUTE_INFO, OperationConfig.UPDATE)){ %>
      	| <a href="/route/RouteInfoManage.do?routeId=<%=bean.getRouteId()%>&readOnly=false">修改</a>
      	<%} %>
      	<%if(adminUserHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.SEARCH)){ %>
      	| <a href="/route/RouteOfferList.do?routeId=<%=bean.getRouteId()%>">查看报价</a>
      	<%} %>
      </td>
    </tr>	
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
	<%if(adminUserHelper.hasPermission(RouteResource.ROUTE_INFO, OperationConfig.CREATE)){ %>
  	<input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='RouteInfoManage.do'">
  	<%} %>
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
</html>