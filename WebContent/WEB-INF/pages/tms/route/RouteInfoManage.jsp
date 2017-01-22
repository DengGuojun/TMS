<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.constant.info.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.tms.route.bean.*"  %>
<%@ page import="com.lpmas.tms.route.config.*"  %>
<%@ page import="com.lpmas.tms.route.business.*"  %>
<% 
	RouteInfoBean bean = (RouteInfoBean)request.getAttribute("RouteInfo");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	String readOnly = ParamKit.getParameter(request, "readOnly","false").trim();
	Boolean isModifyAble = readOnly.equalsIgnoreCase("true")? false:true;
	request.setAttribute("readOnly", readOnly);
	StationInfoMediator mediator = new StationInfoMediator();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../../include/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>路径信息</title>
	<script type="text/javascript" src="../js/tms-common.js"></script>
	<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
	<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
	<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; 
	</script>
</head>
<body class="article_bg">
	<p class="article_tit">路径信息</p>
	<form id="formData" name="formData" method="post" action="RouteInfoManage.do" onsubmit="javascript:return checkForm('formData');">
	  <input type="hidden" name="routeId" id="routeId" value="<%=bean.getRouteId() %>"/>
	  <div class="modify_form">
	    <p>
	      <em class="int_label"><span>*</span>路径名称：</em>
	      <input type="text" name="routeName" id="routeName" size="50" maxlength="100" value="<%=bean.getRouteName() %>" checkStr="路径名称;txt;true;;100"/>
	    </p>
	    <p>
	      <em class="int_label"><span>*</span>起点信息类型：</em>    
	      	<select  name="sourceInfoType" id="sourceInfoType" checkStr="起点信息类型;txt;true;;50">
	      		<%for(StatusBean<Integer, String> statusBean : RouteConfig.SOURCE_INFO_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus() == bean.getSourceInfoType())?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
	      	</select>
	    </p>
	    <p>
	      <em class="int_label"><span>*</span>起点站：</em>
	  		<input type="text" name="sourceInfoName" id="sourceInfoName" value="<%=mediator.getStationNameByKey(bean.getSourceInfoType(), bean.getSourceInfoId()) %>"  readOnly size="50" checkStr="起点站;txt;true;;200"/>
	      	<input type="hidden" name="sourceInfoId" id="sourceInfoId" value="<%=bean.getSourceInfoId()%>"/>
	      	<%if(isModifyAble){ %>
	      	<input type="button" name="selectSourceInfo" id="selectSourceInfo" value="添加" />
	      	<%} %>
	    </p>
	    <p>
	      <em class="int_label"><span>*</span>终点信息类型：</em>    
	      	<select  name="destinationInfoType" id="destinationInfoType" checkStr="终点信息类型;txt;true;;50">
	      		<%for(StatusBean<Integer, String> statusBean : RouteConfig.DESTINATION_INFO_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus() == bean.getDestinationInfoType())?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
	      	</select>
	    </p>
	    <p>
	      <em class="int_label"><span>*</span>终点站：</em>
	  		<input type="text" name="destinationInfoName" id="destinationInfoName" value="<%=mediator.getStationNameByKey(bean.getDestinationInfoType(), bean.getDestinationInfoId()) %>"  readOnly size="50" checkStr="起点站;txt;true;;200"/>
	      	<input type="hidden" name="destinationInfoId" id="destinationInfoId" value="<%=bean.getDestinationInfoId()%>"/>
	      	<%if(isModifyAble){ %>
	      	<input type="button" name="selectDestinationInfo" id="selectDestinationInfo" value="添加" />
	      	<%} %>
	    </p>
	    <p>
	      <em class="int_label">有效状态：</em>
	      <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
	    </p>
	    <p class="p_top_border">
	      <em class="int_label">备注：</em>
	      <textarea  name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=bean.getMemo() %></textarea>
	    </p>
	  </div>
	  <div class="div_center">
	  <input type="submit" name="submit" id="submit" class="modifysubbtn" value="提交" />
	  <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
	  </div>
	</form>
</body>
<script>
$(document).ready(function() {
	var readOnly = '${readOnly}';
	if(readOnly=='true') {
		disablePageElement();
	}
});
$("#selectSourceInfo").click(
	function() {
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
});

$("#selectDestinationInfo").click(
		function() {
			var destinationInfoType = $("#destinationInfoType").val();
			var destinationInfoId = $("#destinationInfoId").val();
			if(destinationInfoType == '<%=InfoTypeConfig.INFO_TYPE_SUPPLIER_ADDRESS%>'){
				var url = "<%=SRM_URL%>/srm/SupplierAddressEntitySelect.do?callbackFun=selectSupplierAddress&echoParameter=destinationInfo&addressId="+ destinationInfoId ;
			}else if (destinationInfoType == '<%=InfoTypeConfig.INFO_TYPE_WAREHOUSE%>'){
				var url = "<%=ERP_URL%>/erp/WarehouseInfoSelect.do?callbackFun=selectWarehouse&echoParameter=destinationInfo&warehouseId="+ destinationInfoId;
			}
			$.fancybox.open({
				href : url,
				type : 'iframe',
				width : 760,
				minHeight : 500
		});
	});

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
</script>
</html>