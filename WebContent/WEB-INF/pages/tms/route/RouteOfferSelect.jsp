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
<%@ page import="com.lpmas.tms.route.config.*"  %>
<%
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<RouteOfferBean> list = (List<RouteOfferBean>)request.getAttribute("RouteOfferList");
Map<Integer,RouteInfoEntityBean> routeInfoMap = (Map<Integer,RouteInfoEntityBean>) request.getAttribute("RouteInfoMap");
int companyId = (Integer)request.getAttribute("companyId");
int routeId = (Integer)request.getAttribute("routeId");

PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
String callbackFun = ParamKit.getParameter(request, "callbackFun", "callbackFun");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径选择</title>
<%@ include file="../../include/header.jsp" %>
<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; 
</script>
</head>
<body class="article_bg">
<form name="formSearch" method="post" action="RouteOfferSelect.do">
<div class="search_form">
    <input type="hidden" name="companyId" value="<%=companyId %>" />
    <input type="hidden" name="callbackFun" value="<%=callbackFun %>" />
  </div>
  <table width="100%" border="0" cellpadding="0" class="table_style">
    <tr>
      <th>选择</th>
      <th>路径名称</th>
      <th>时效</th>
      <th>运费</th>
    </tr>
    <%
    for(RouteOfferBean bean:list){%> 
    <tr>
      <td align="center"><input type="radio" name="routeId" value="<%=bean.getRouteId()%>" <%=routeId==bean.getRouteId() ? "checked" : ""%>></td>
      <td><%=routeInfoMap.get(bean.getRouteId()).getRouteName()%></td>
      <td><%=bean.getDuration()%></td>
      <td><%=bean.getTransportPrice()%></td>
    </tr>	
    <input type="hidden" id="routeInfoJsonStr_<%=bean.getRouteId()%>" value='<%=JsonKit.toJson(routeInfoMap.get(bean.getRouteId()))%>'>
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
  <input type="submit" name="button" id="button" class="modifysubbtn" value="选择" onclick="callbackTo()" />
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
<input type="button" class="btn_fixed" value="选择" onclick="callbackTo()" />
</body>
<script>
function callbackTo(){
	var routeId = $('input:radio[name=routeId]:checked').val();
	if (typeof(routeId) == 'undefined'){
		alert("请选择路径");
		return;
	}
	var routeInfoJsonStr = $("#routeInfoJsonStr_"+routeId).val();
	
	self.parent.<%=callbackFun %>(routeInfoJsonStr);
	try{ self.parent.jQuery.fancybox.close(); }catch(e){console.log(e);}
}
</script>
</html>