<%@page import="com.lpmas.tms.route.business.StationInfoMediator"%>
<%@page import="com.lpmas.framework.nosql.mongodb.MongoDBConfig"%>
<%@page import="com.lpmas.tms.express.cache.ExpressCompanyInfoCache"%>
<%@page import="com.lpmas.constant.info.InfoTypeConfig"%>
<%@page import="com.lpmas.tms.logistics.cache.LogisticsCompanyInfoCache"%>
<%@page import="com.lpmas.tms.route.cache.RouteInfoCache"%>
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
<%@ page import="com.lpmas.tms.route.config.*"  %>
<%@ page import="com.lpmas.tms.route.bean.*"  %>
<%
	RouteInfoCache routeCache = new RouteInfoCache();
	LogisticsCompanyInfoCache companyCache = new LogisticsCompanyInfoCache();
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<PathInfoBean> list = (List<PathInfoBean>)request.getAttribute("PathInfoList");
	PathInfoBean bestPath = (PathInfoBean)request.getAttribute("BestPath");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	int stationType = ParamKit.getIntParameter(request, "stationType", 0);
	int sourceInfoType = ParamKit.getIntParameter(request, "sourceInfoType", 0);
	int sourceInfoId = ParamKit.getIntParameter(request, "sourceInfoId", 0);
	int destinationInfoType = ParamKit.getIntParameter(request, "destinationInfoType", 0);
	int destinationInfoId = ParamKit.getIntParameter(request, "destinationInfoId", 0);
	int totalDurationOrder = ParamKit.getIntParameter(request, "totalDurationOrder", MongoDBConfig.SORT_ORDER_ASC);
	int totalPriceOrder = ParamKit.getIntParameter(request, "totalPriceOrder", MongoDBConfig.SORT_ORDER_ASC);
	StationInfoMediator mediator = new StationInfoMediator();
	String bestRoute = "";
	String bestCompany = "";
	if(bestPath!=null){
		for(WeightedEdgeInfoBean edgeInfoBean:bestPath.getPathList()){
			bestRoute+=routeCache.getRouteNameByKey(edgeInfoBean.getRouteId())+"->";
			bestCompany+=companyCache.getLogisticsCompanyInfoByKey(edgeInfoBean.getCompanyId()).getCompanyName()+"->";
		}
	}
	if(bestRoute.endsWith("->")&&bestCompany.endsWith("->")){
		bestRoute=bestRoute.substring(0, bestRoute.length()-2);
		bestCompany=bestCompany.substring(0, bestCompany.length()-2);
	}
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路由选择</title>
<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
	<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
	<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; 
	</script>
	<style type="text/css">
	.table_style td{
		line-height:25px;
	}
	</style>
</head>
<body class="article_bg">
<p class="article_tit">路由选择</p>
<form name="formSearch" id="formSearch" method="post" action="PathInfoList.do">
  <div class="search_form">
  <table>
  <tr>
  <td>
  <p style="width:720px">
   		<em class="em1">起点站-终点站：</em>
      	<select  name="sourceInfoType" id="sourceInfoType" onchange="clearData('source')">
      		<%for(StatusBean<Integer, String> statusBean : RouteConfig.SOURCE_INFO_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus() == sourceInfoType)?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
      	</select>
      	<input type="text" name="sourceInfoName" id="sourceInfoName" value="<%=mediator.getStationNameByKey(sourceInfoType, sourceInfoId) %>" onclick="sourceSelect()" readOnly size="20"/>
      	<input type="hidden" name="sourceInfoId" id="sourceInfoId" value="<%=sourceInfoId%>"/>
	    <input type="button" class="search_btn_sub" value="⇄" onclick="switchInfo()"/>
      	<select  name="destinationInfoType" id="destinationInfoType" onchange="clearData('destination')">
      		<%for(StatusBean<Integer, String> statusBean : RouteConfig.DESTINATION_INFO_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus() == destinationInfoType)?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
      	</select>
  		<input type="text" name="destinationInfoName" id="destinationInfoName" value="<%=mediator.getStationNameByKey(destinationInfoType, destinationInfoId) %>" onclick="destinationSelect()" readOnly size="20"/>
      	<input type="hidden" name="destinationInfoId" id="destinationInfoId" value="<%=destinationInfoId%>"/>
      	<input type="hidden" name="totalDurationOrder" id="totalDurationOrder" value="<%=totalDurationOrder%>"/>
      	<input type="hidden" name="totalPriceOrder" id="totalPriceOrder" value="<%=totalPriceOrder%>"/>
      	<input name="" type="button" class="search_btn_sub" onclick="submitForm('normal')" value="路由一下"/>
	</p>
  </td>
  </tr>
  <tr><td>
  <p style="width:260px">
		<em class="em1">路径名称：</em>
		<input type="text" name="routeName" id="routeName" value="<%=ParamKit.getParameter(request, "routeName", "") %>" size="20"/>
		<input name="" type="button" class="search_btn_sub" onclick="submitForm('routeName')" value="路由一下"/>
	</p>
  </td></tr>
  <tr>
  <td>
  	<p style="width:340px">
		<em class="em1">站点名称：</em>
		<input type="text" name="stationName" id="stationName"  value="<%=ParamKit.getParameter(request, "stationName", "") %>" size="20"/>
		<input name="" type="button" class="search_btn_sub" onclick="submitForm('stationName')" value="路由一下"/>
	</p>
  </td>
  </tr>
  </table>
	<input type="hidden" name="searchType" id="searchType" value="<%=ParamKit.getParameter(request, "searchType", "") %>"/>
  </div>
  <table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>路径名称</th>
      <th>物流公司</th>
      <th>起点类型</th>
      <th>起点站</th>
      <th>终点类型</th>
      <th>终点站</th>
      <th><a onclick="submitOrderBy('totalDurationOrder',<%=totalDurationOrder==MongoDBConfig.SORT_ORDER_ASC? MongoDBConfig.SORT_ORDER_DESC:MongoDBConfig.SORT_ORDER_ASC%>)">时效(天)</a></th>
      <th><a onclick="submitOrderBy('totalPriceOrder',<%=totalPriceOrder==MongoDBConfig.SORT_ORDER_ASC? MongoDBConfig.SORT_ORDER_DESC:MongoDBConfig.SORT_ORDER_ASC%>)">运费(元/KG)</a></th>
    </tr>
    <%
    for(PathInfoBean bean:list){%> 
    <tr>
      <td>
      <%for(WeightedEdgeInfoBean edge:bean.getPathList()){ %>
      <div><%=routeCache.getRouteNameByKey(edge.getRouteId()) %></div>
      <%} %>
	  </td>
      <td>
      <%for(WeightedEdgeInfoBean edge:bean.getPathList()){ %>
      <div><%=companyCache.getLogisticsCompanyInfoByKey(edge.getCompanyId()).getCompanyName()%></div>
      <%} %>
	  </td>
      <td><%=MapKit.getValueFromMap(bean.getSourceInfoType(), InfoTypeConfig.INFO_TYPE_MAP)%></td>
      <td><%=mediator.getStationNameByKey(bean.getSourceInfoType(), bean.getSourceInfoId())%></td>
      <td><%=MapKit.getValueFromMap(bean.getDestinationInfoType(), InfoTypeConfig.INFO_TYPE_MAP)%></td>
      <td><%=mediator.getStationNameByKey(bean.getDestinationInfoType(), bean.getDestinationInfoId()) %></td>
      <td><%=bean.getTotalDuration()%></td>
	  <td style="text-align:right"><%=bean.getTotalPrice()%></td>
    </tr>	
    <%} %>
  </table>
<%if(bestPath!=null){ %>
<p class="article_tit">智能路由</p>
	<p>
      <label>时效比重：</label>
      <input type="number" name="durationWeight" id="durationWeight" size="20" maxlength="20" value="<%=ParamKit.getIntParameter(request,"durationWeight",50) %>" onchange="weightChange(this)"/>%
    </p>
    <p>
      <label>运费比重：</label>
      <input type="number" name="priceWeight" id="priceWeight" size="20" maxlength="20" value="<%=ParamKit.getIntParameter(request,"priceWeight",50) %>" onchange="weightChange(this)"/>%
    </p>
    <p>
      <label>推荐最佳路径为：</label>
      <label style="color:red"><%=bestRoute %>(<%=bestCompany %>)</label>
    </p>
<%} %>
</form>
</body>
<script>
function selectSupplierAddress(supplierAddressJsonStr,echoParameter){
	var supplierAddressBean = jQuery.parseJSON(supplierAddressJsonStr);
	if(supplierAddressBean.addressId != ""){
		if(echoParameter=='sourceInfo'){
			$("#sourceInfoId").val(supplierAddressBean.addressId);
			$("#sourceInfoName").val(supplierAddressBean.supplierName);
		}else if (echoParameter=='destinationInfo'){
			$("#destinationInfoId").val(supplierAddressBean.addressId);
			$("#destinationInfoName").val(supplierAddressBean.supplierName);
		}
		
	}
}
function selectWarehouse(warehouseJsonStr,echoParameter){
	var warehouseJsonBean = jQuery.parseJSON(warehouseJsonStr);
	if(warehouseJsonBean.warehouseId != ""){
		if(echoParameter=='sourceInfo'){
			$("#sourceInfoId").val(warehouseJsonBean.warehouseId);
			$("#sourceInfoName").val(warehouseJsonBean.warehouseName);
		}else if (echoParameter=='destinationInfo'){
			$("#destinationInfoId").val(warehouseJsonBean.warehouseId);
			$("#destinationInfoName").val(warehouseJsonBean.warehouseName);
		}
		
	}
}
function sourceSelect() {
	var sourceInfoType = $("#sourceInfoType").val();
	var sourceInfoId = $("#sourceInfoId").val();
	if(sourceInfoType == '<%=InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS%>'){
		var url = "<%=SRM_URL%>/srm/SupplierAddressEntitySelect.do?callbackFun=selectSupplierAddress&echoParameter=sourceInfo&addressId="+ sourceInfoId;
	}else if (sourceInfoType == '<%=InfoTypeConfig.INFO_TYPE_WAREHOUSE%>'){
		var url = "<%=ERP_URL%>/erp/WarehouseInfoSelect.do?callbackFun=selectWarehouse&echoParameter=sourceInfo&warehouseId="+ sourceInfoId;
	}
	$.fancybox.open({
		href : url,
		type : 'iframe',
		width : 760,
		minHeight : 500
});
	}
function destinationSelect() {
	var destinationInfoType = $("#destinationInfoType").val();
	var destinationInfoId = $("#destinationInfoId").val();
	if(destinationInfoType == '<%=InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS%>'){
		var url = "<%=SRM_URL%>/srm/SupplierAddressEntitySelect.do?callbackFun=selectSupplierAddress&echoParameter=destinationInfo&addressId="+destinationInfoId;
	}else if (destinationInfoType == '<%=InfoTypeConfig.INFO_TYPE_WAREHOUSE%>'){
		var url = "<%=ERP_URL%>/erp/WarehouseInfoSelect.do?callbackFun=selectWarehouse&echoParameter=destinationInfo&warehouseId="+ destinationInfoId;
	}
	$.fancybox.open({
		href : url,
		type : 'iframe',
		width : 760,
		minHeight : 500
});
}
function submitOrderBy(id,value){
	$('#totalDurationOrder').val('');
	$('#totalPriceOrder').val('');
	$('#'+id).val(value);
	$('#formSearch').submit();
}
function switchInfo(){
	var sourceInfoType = $("#sourceInfoType").val();
	var sourceInfoId = $("#sourceInfoId").val();
	var sourceInfoName = $("#sourceInfoName").val();
	var destinationInfoType = $("#destinationInfoType").val();
	var destinationInfoId = $("#destinationInfoId").val();
	var destinationInfoName = $("#destinationInfoName").val();
	
	$("#sourceInfoType").val(destinationInfoType);
	$("#sourceInfoId").val(destinationInfoId);
	$("#sourceInfoName").val(destinationInfoName);
	$("#destinationInfoType").val(sourceInfoType);
	$("#destinationInfoId").val(sourceInfoId);
	$("#destinationInfoName").val(sourceInfoName);
}
function clearData(type){
	if(type=='source'){
		$("#sourceInfoId").val('');
		$("#sourceInfoName").val('');
	}else if(type=='destination'){
		$("#destinationInfoId").val('');
		$("#destinationInfoName").val('');
	}
}
function submitForm(searchType){
	if(searchType=='normal'){
		if($("#sourceInfoId").val()=='0'||$("#destinationInfoId").val=='0'){
			alert('请填写筛选条件');
			return;
		}
	}else{
		if($('#'+searchType).val().trim()==''){
			alert('请填写筛选条件');
			return;
		}
	}
	$('#searchType').val(searchType);
	$('#formSearch').submit();
}
function weightChange(ele){
	var id = $(ele).attr('id');
	var value= $(ele).val();
	value = parseFloat(value);
	if(value>100){
		value=100;
	}
	$(ele).val(value);
	var anotherValue = 100-value;
	if(id=='durationWeight'){
		$('#priceWeight').val(anotherValue);
	}else{
		$('#durationWeight').val(anotherValue);
	}
	submitForm($('#searchType').val());
}
</script>
</html>