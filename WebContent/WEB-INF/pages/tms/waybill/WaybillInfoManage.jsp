<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.constant.info.InfoTypeConfig"%>
<%@ page import="com.lpmas.admin.bean.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.tms.route.bean.*"%>
<%@ page import="com.lpmas.tms.logistics.bean.*"%>
<%@ page import="com.lpmas.tms.waybill.bean.*"%>
<%@ page import="com.lpmas.tms.waybill.config.*"%>
<%
	WaybillInfoBean bean = (WaybillInfoBean) request.getAttribute("WaybillInfo");
	String routeName = (String)request.getAttribute("RouteName");
	Boolean isSource = (Boolean)request.getAttribute("IsSource");
	List<LogisticsCompanyInfoBean> companyInfoList = (List<LogisticsCompanyInfoBean>) request.getAttribute("CompanyInfoList");
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	String infoNumber = (String) request.getAttribute("InfoNumber");
	String readOnly = ParamKit.getParameter(request, "readOnly","false").trim();
	Boolean isReadOnly = readOnly.equalsIgnoreCase("true")? false:true;
	request.setAttribute("readOnly", readOnly);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承运单管理</title>
<%@ include file="../../include/header.jsp"%>
<script type="text/javascript" src="../js/tms-common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; 
</script>
</head>
<body class="article_bg">
	<div class="article_tit">
		<a href="javascript:history.back()"><img src="<%=STATIC_URL%>/images/back_forward.jpg" /></a>
		<ul class="art_nav">
			<li><a href="WaybillInfoList.do">承运单列表</a>&nbsp;>&nbsp;</li>
			<%
				if (bean.getWaybillId() > 0) {
			%>
			<li>修改承运单信息</li>
			<%
				} else {
			%>
			<li>新建承运单信息</li>
			<%
				}
			%>
		</ul>
	</div>
	<form id="formData" name="formData" method="post" action="WaybillInfoManage.do" onsubmit="javascript:return formOnSubmit();">
	    <input type="hidden" name="waybillId" id="waybillId" value="<%=bean.getWaybillId() %>"/>
	    <input type="hidden" name="waybillNumber" id="waybillNumber" value="<%=bean.getWaybillNumber() %>"/>
	    <input type="hidden" name="approvalStatus" id="approvalStatus" value="<%=bean.getApprovalStatus() %>"/>
	    <input type="hidden" name="syncStatus" id="syncStatus" value="<%=bean.getSyncStatus() %>"/>
	    <input type="hidden" name="waybillStatus" id="waybillStatus" value="<%=bean.getWaybillStatus() %>"/>
		<input type="hidden" name="status" id="status" value="<%=Constants.STATUS_VALID%>" />
		<p>基础信息</p>
		<div class="modify_form">
			<p>
				<em class="int_label"><span>*</span>物流公司：</em> <select name="logisticsCompanyId" id="logisticsCompanyId" checkStr="物流公司;num;true;;20">
					<%
						for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {
					%><option value="<%=logisticsCompanyInfoBean.getCompanyId()%>" <%=logisticsCompanyInfoBean.getCompanyId() == bean.getLogisticsCompanyId() ? "selected" : ""%>><%=logisticsCompanyInfoBean.getCompanyName()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label">物流路径：</em> 
				<input type="text" name="routeName" id="selectRoute" size="20"  value="<%=routeName%>"readonly/>
				<input type="hidden" name="routeId" id="routeId" value="<%=bean.getRouteId()%>" /> 
				<%if(isReadOnly){ %>
				<input type="button" name="clearRoute" id="clearRoute" value="清空" />
				<%} %>
			</p>
			<p>
				<em class="int_label"><span>*</span>总件数：</em> <input type="text" name="totalQuantity" id="totalQuantity" size="20" maxlength="20"
					value="<%=bean.getTotalQuantity() > 0 ? bean.getTotalQuantity() : ""%>" onkeyup='this.value=this.value.replace(/[^\d.]/g,"")' checkStr="总件数;num;true;;20" />件
			</p>
			<p>
				<em class="int_label"><span>*</span>总重量：</em> <input type="text" name="totalWeight" id="totalWeight" size="20" maxlength="100" value="<%=bean.getTotalWeight() > 0 ? bean.getTotalWeight() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\d.]/g,"")' checkStr="总重量;num;true;;20" />KG
			</p>
			<p>
				<em class="int_label">关联订单类型：</em> <select name="infoType" id="infoType" onchange="clearData()">
				    <option value=""></option>
					<%for (StatusBean<Integer, String> statusBean :WaybillConfig.WAYBILL_INFO_TYPE_LIST) {%>
					<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus() == bean.getInfoType() ? "selected" : ""%>><%=statusBean.getValue()%></option>
					<%}%>
				</select>
			</p>
			<p>
				<em class="int_label">关联订单号：</em> <input type="text" name="infoNumber" id="infoNumber" size="20"  value="<%=infoNumber%>" readonly/>
				<input type="hidden" name="infoId" id="infoId" value="<%=bean.getInfoId()%>" /> 
				<%if(isReadOnly){ %>
				<input type="button" name="selectInfo" id="selectInfo" value="添加" />
				<%} %>
			</p>
			<p class="p_top_border">
				<em class="int_label">备注：</em>
				<textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=bean.getMemo()%></textarea>
			</p>
		</div>
		<p>发货人信息</p>
		<div class="modify_form" id="consigner">
			<input type="hidden" name="consignerCountry" id="consignerCountry"value="中国" /> 
			<p>
				<em class="int_label"><span>*</span>发货人：</em>
				<input type="text" name="consignerName" id="consignerName" value="<%=bean.getConsignerName()%>" checkStr="发货人;txt;true;;100" /> 
			</p>
			<p>
			      <em class="int_label"><span>*</span>省:</em>    
		     	  <select name="consignerProvince" id="consignerProvince" onchange="$('#consignerRegion').empty();getCityNameList('consignerProvince','consignerCity','<%=bean.getConsignerCity()%>')" checkStr="省;txt;true;;200"></select>
		     	  <input type="hidden" id="consignerProvinceDummy" value="<%=bean.getConsignerProvince()%>"/>
		    </p>
		    <p>
			      <em class="int_label"><span>*</span>市:</em>    
		     	  <select name="consignerCity" id="consignerCity" onchange="getRegionNameList('consignerCity','consignerRegion','<%=bean.getConsignerRegion()%>')" checkStr="市;txt;true;;200"></select>
		     	  <input type="hidden" id="consignerCityDummy" value="<%=bean.getConsignerCity()%>"/>
		    </p>
		    <p>
			      <em class="int_label"><span>*</span>区:</em>    
		     	  <select name="consignerRegion" id="consignerRegion" checkStr="区;txt;true;;200"></select>
		    </p>
		    <p>
	  			<em class="int_label"><span>*</span>地址：</em>
	  			<input type="text" name="consignerAddress" id="consignerAddress" value="<%=bean.getConsignerAddress()%>" checkStr="发货人地址;txt;true;;100" /> 
  			</p>
			<p>
				<em class="int_label"><span>*</span>发货人电话：</em>
				<input type="text" name="consignerTelephone" id="consignerTelephone" value="<%=bean.getConsignerTelephone()%>" checkStr="发货人电话;txt;true;;100" />
			</p>
			<p>
				<em class="int_label"><span>*</span>发货人手机：</em>
				<input type="text" name="consignerMobile" id="consignerMobile" value="<%=bean.getConsignerMobile()%>" checkStr="发货人手机;txt;true;;100" />
			</p>
		</div>
		<p>收货人信息</p>
		<div class="modify_form" id="receiver">			
			<input type="hidden" name="receiverCountry" id="receiverCountry" value="中国" /> 
			<p>
				<em class="int_label"><span>*</span>收货人：</em> 
				<input type="text" name="receiverName" id="receiverName" value="<%=bean.getReceiverName()%>" checkStr="收货人;txt;true;;100" /> 
			</p>
			<p>
			      <em class="int_label"><span>*</span>省:</em>    
		     	  <select name="receiverProvince" id="receiverProvince" onchange="$('receiverRegion').empty();getCityNameList('receiverProvince','receiverCity','<%=bean.getReceiverCity()%>')" checkStr="省;txt;true;;200"></select>
		     	  <input type="hidden" id="receiverProvinceDummy" value="<%=bean.getReceiverProvince()%>"/>
		    </p>
		    <p>
			      <em class="int_label"><span>*</span>市:</em>    
		     	  <select name="receiverCity" id="receiverCity" onchange="getRegionNameList('receiverCity','receiverRegion','<%=bean.getReceiverRegion()%>')" checkStr="市;txt;true;;200"></select>
		     	  <input type="hidden" id="receiverCityDummy" value="<%=bean.getReceiverCity()%>"/>
		    </p>
		    <p>
			      <em class="int_label"><span>*</span>区:</em>    
		     	  <select name="receiverRegion" id="receiverRegion" checkStr="区;txt;true;;200"></select>
		    </p>
			<p>
				<em class="int_label"><span>*</span>收货人地址：</em> 
				<input type="text" name="receiverAddress" id="receiverAddress" value="<%=bean.getReceiverAddress()%>" checkStr="收货人地址;txt;true;;100" /> 
			</p>
			<p>
				<em class="int_label"><span>*</span>收货人电话：</em> 
				<input type="text" name="receiverTelephone" id="receiverTelephone"value="<%=bean.getReceiverTelephone()%>" checkStr="收货人电话;txt;true;;100" /> 
			</p>
			<p>
				<em class="int_label"><span>*</span>收货人手机：</em> 
				<input type="text" name="receiverMobile" id="receiverMobile" value="<%=bean.getReceiverMobile()%>" checkStr="收货人手机;txt;true;;100" />
			</p>
		</div>
		<p>运货明细</p>
		<div class="modify_form" id="charge">
			<p>
				<em class="int_label"><span>*</span>运费：</em> <input type="text" name="freight" id="freight" value="<%=bean.getFreight() > 0 ? bean.getFreight() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()" size="20" checkStr="运费;num;true;;100" />元
			</p>
			<p>
				<em class="int_label">提货费：</em> <input type="text" name="pickupCharge" id="pickupCharge" value="<%=bean.getPickupCharge() > 0 ? bean.getPickupCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
			<p>
				<em class="int_label">送货费：</em> <input type="text" name="deliveryCharge" id="deliveryCharge" value="<%=bean.getDeliveryCharge() > 0 ? bean.getDeliveryCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
			<p>
				<em class="int_label">进仓费：</em> <input type="text" name="warehouseEntryCharge" id="warehouseEntryCharge" value="<%=bean.getWarehouseEntryCharge() > 0 ? bean.getWarehouseEntryCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
			<p>
				<em class="int_label">包装费：</em> <input type="text" name="packageCharge" id="packageCharge" value="<%=bean.getPackageCharge() > 0 ? bean.getPackageCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
			<p>
				<em class="int_label">保险费：</em> <input type="text" name="insuranceCharge" id="insuranceCharge" value="<%=bean.getInsuranceCharge() > 0 ? bean.getInsuranceCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
			<p>
				<em class="int_label">其他费用：</em> <input type="text" name="otherCharge" id="otherCharge" value="<%=bean.getOtherCharge() > 0 ? bean.getOtherCharge() : ""%>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' onchange="modifyOnChange()"  size="20" />元
			</p>
		</div>
		<p>运费总金额:
		<input type="hidden" name="totalAmount" id="totalAmount" value="<%=bean.getTotalAmount()%>" /> 
		<span id="total" name="total"><%=bean.getTotalAmount()%></span>
		</p>
		<div class="div_center">
			<input type="submit" name="button" id="button" class="modifysubbtn" value="提交" /> <a href="WaybillInfoList.do"><input type="button" name="cancel" id="cancel" value="取消"></a>
		</div>
	</form>
</body>
<script>
function modifyOnChange(){
	var inputs = $('#charge input');
	var total = 0;
	inputs.each(function(){
		total +=  + this.value;
	});	
	$('#totalAmount').val((total).toFixed(2));
	//计算总金额保留两位小数
	$('#total').html((total).toFixed(2));
	
}
$(document).ready(function() {
	var url='<%=REGION_URL%>/region/RegionAjaxList.do';
	$.getScript(url,function(data){
		getProvinceNameList('consignerCountry','consignerProvince','<%=bean.getConsignerProvince()%>');
		getCityNameList('consignerProvinceDummy','consignerCity','<%=bean.getConsignerCity()%>');
		getRegionNameList('consignerCityDummy','consignerRegion','<%=bean.getConsignerRegion()%>');
		
		getProvinceNameList('receiverCountry','receiverProvince','<%=bean.getReceiverProvince()%>');
		getCityNameList('receiverProvinceDummy','receiverCity','<%=bean.getReceiverCity()%>');
		getRegionNameList('receiverCityDummy','receiverRegion','<%=bean.getReceiverRegion()%>');
	});
	var readOnly = '${readOnly}';
	if(readOnly=='true') {
		disablePageElement();
	}
	var isSource = '<%=isSource%>';
	if(isSource=='true') {
		$('#consigner input').attr("readonly","readonly")
		$('#consigner select').attr("disabled","disabled")
		$('#receiver input').attr("readonly","readonly")
		$('#receiver select').attr("disabled","disabled")
	}
	$("#selectInfo").click(
			function() {				
				var url = "";
				var selectfunction = "";
				var infoType = $('#infoType').val().trim();
				if(infoType==""){
					alert("请先选择订单类型");
					return;
				}else{
					if(infoType=='<%=InfoTypeConfig.INFO_TYPE_WAREHOUSE_TRANSFER_ORDER%>'){
						url = "/erp/WarehouseTransferOrderInfoSelect.do";
						selectfunction = "selectWarehouseTransfer";
					}else{
						url = "/erp/DeliveryNoteInfoSelect.do";
						selectfunction = "selectDeliveryNote";
					}
				}
				$.fancybox.open({
					href : '<%=ERP_URL%>'+url+'?callbackFun='+selectfunction,
					type : 'iframe',
					width : 560,
					minHeight : 550
			});
		});
	
	$("#selectRoute").click(
			function() {				
				var url = "";
				var logisticsCompanyId = $('#logisticsCompanyId').val().trim();
				if(logisticsCompanyId==""){
					alert("请先选择物流公司");
					return;
				}
				$.fancybox.open({
					href : '<%=TMS_URL%>/route/RouteOfferSelect.do?companyId='+logisticsCompanyId+'&callbackFun=selectRouteInfo',
					type : 'iframe',
					width : 560,
					minHeight : 550
			});
		});
	$("#clearRoute").click(
			function() {				
				$('#selectRoute').val('');
		});
});
function selectWarehouseTransfer(transferOrderJsonStr, sourceWarehouseJsonStr,targetWarehouseJsonStr){
	var warehouseTransferOrderInfoBean = jQuery.parseJSON(transferOrderJsonStr);
	var sourceWarehouseInfoBean = jQuery.parseJSON(sourceWarehouseJsonStr);
	var targetWarehouseInfoBean = jQuery.parseJSON(targetWarehouseJsonStr);
	if(warehouseTransferOrderInfoBean.toId != "" && warehouseTransferOrderInfoBean.toNumber != ""){
		$("#consignerName").val(sourceWarehouseInfoBean.contactName);
		$("#consignerCountry").val(sourceWarehouseInfoBean.country);
		$("#consignerProvince").val(sourceWarehouseInfoBean.province);
		$("#consignerCity").val(sourceWarehouseInfoBean.city);
		$("#consignerRegion").val(sourceWarehouseInfoBean.region);
		$("#consignerAddress").val(sourceWarehouseInfoBean.address);
		$("#consignerTelephone").val(sourceWarehouseInfoBean.telephone);
		$("#consignerMobile").val(sourceWarehouseInfoBean.mobile);
		$("#consignerCityDummy").val(sourceWarehouseInfoBean.city);
		getCityNameList('consignerProvince','consignerCity',sourceWarehouseInfoBean.city);
		getRegionNameList('consignerCityDummy','consignerRegion',sourceWarehouseInfoBean.region);
		$('#consigner input').attr("readonly","readonly")
		$('#consigner select').attr("disabled","disabled")
		
		$("#receiverName").val(targetWarehouseInfoBean.contactName);
		$("#receiverCountry").val(targetWarehouseInfoBean.country);
		$("#receiverProvince").val(targetWarehouseInfoBean.province);
		$("#receiverCity").val(targetWarehouseInfoBean.city);
		$("#receiverRegion").val(targetWarehouseInfoBean.region);
		$("#receiverAddress").val(targetWarehouseInfoBean.address);
		$("#receiverTelephone").val(targetWarehouseInfoBean.telephone);
		$("#receiverMobile").val(targetWarehouseInfoBean.mobile);
		$("#receiverCityDummy").val(targetWarehouseInfoBean.city);
		getCityNameList('receiverProvince','receiverCity',targetWarehouseInfoBean.city);
		getRegionNameList('receiverCityDummy','receiverRegion',targetWarehouseInfoBean.region);
		$('#receiver input').attr("readonly","readonly")
		$('#receiver select').attr("disabled","disabled")
		
		$("#infoId").val(warehouseTransferOrderInfoBean.toId);
		$("#infoNumber").val(warehouseTransferOrderInfoBean.toNumber);
	}
}
function selectDeliveryNote(deliveryNoteJsonStr, purchaseOrderJsonStr){
	var deliveryNoteInfoBean = jQuery.parseJSON(deliveryNoteJsonStr);
	var purchaseOrderInfoBean = jQuery.parseJSON(purchaseOrderJsonStr);
	if(deliveryNoteInfoBean.dnId != "" && deliveryNoteInfoBean.dnNumber != ""){
		$("#consignerName").val(purchaseOrderInfoBean.consignerName);
		$("#consignerCountry").val(purchaseOrderInfoBean.consignerCountry);
		$("#consignerProvince").val(purchaseOrderInfoBean.consignerProvince);
		$("#consignerCity").val(purchaseOrderInfoBean.consignerCity);
		$("#consignerRegion").val(purchaseOrderInfoBean.consignerRegion);
		$("#consignerAddress").val(purchaseOrderInfoBean.consignerAddress);
		$("#consignerTelephone").val(purchaseOrderInfoBean.consignerTelephone);
		$("#consignerMobile").val(purchaseOrderInfoBean.consignerMobile);
		$("#consignerCityDummy").val(purchaseOrderInfoBean.consignerCity);
		getCityNameList('consignerProvince','consignerCity',purchaseOrderInfoBean.consignerCity);
		getRegionNameList('consignerCityDummy','consignerRegion',purchaseOrderInfoBean.consignerRegion);
		$('#consigner input').attr("readonly","readonly")
		$('#consigner select').attr("disabled","disabled")
		
		$("#receiverName").val(purchaseOrderInfoBean.receiverName);
		$("#receiverCountry").val(purchaseOrderInfoBean.receiverCountry);
		$("#receiverProvince").val(purchaseOrderInfoBean.receiverProvince);
		$("#receiverCity").val(purchaseOrderInfoBean.receiverCity);
		$("#receiverRegion").val(purchaseOrderInfoBean.receiverRegion);
		$("#receiverAddress").val(purchaseOrderInfoBean.receiverAddress);
		$("#receiverTelephone").val(purchaseOrderInfoBean.receiverTelephone);
		$("#receiverMobile").val(purchaseOrderInfoBean.receiverMobile);
		$("#receiverCityDummy").val(purchaseOrderInfoBean.receiverCity);
		getCityNameList('receiverProvince','receiverCity',purchaseOrderInfoBean.receiverCity);
		getRegionNameList('receiverCityDummy','receiverRegion',purchaseOrderInfoBean.receiverRegion);
		$('#receiver input').attr("readonly","readonly")
		$('#receiver select').attr("disabled","disabled")
		
		$("#infoId").val(deliveryNoteInfoBean.dnId);
		$("#infoNumber").val(deliveryNoteInfoBean.dnNumber);
	}
}
function selectRouteInfo(routeInfoJsonStr){
	var routeInfoEntityBean = jQuery.parseJSON(routeInfoJsonStr);
	if(routeInfoEntityBean.routeId != "" && routeInfoEntityBean.routeName != ""){		
		$("#consignerName").val(routeInfoEntityBean.sourceContactName);
		$("#consignerCountry").val(routeInfoEntityBean.sourceCountry);
		$("#consignerProvince").val(routeInfoEntityBean.sourceProvince);
		$("#consignerCity").val(routeInfoEntityBean.sourceCity);
		$("#consignerRegion").val(routeInfoEntityBean.sourceRegion);
		$("#consignerAddress").val(routeInfoEntityBean.sourceAddress);
		$("#consignerTelephone").val(routeInfoEntityBean.sourceTelephone);
		$("#consignerMobile").val(routeInfoEntityBean.sourceMobile);
		$("#consignerCityDummy").val(routeInfoEntityBean.sourceCity);
		getCityNameList('consignerProvince','consignerCity',routeInfoEntityBean.sourceCity);
		getRegionNameList('consignerCityDummy','consignerRegion',routeInfoEntityBean.sourceRegion);
		$('#consigner input').attr("readonly","readonly")
		$('#consigner select').attr("disabled","disabled")
		
		$("#receiverName").val(routeInfoEntityBean.destinationContactName);
		$("#receiverCountry").val(routeInfoEntityBean.destinationCountry);
		$("#receiverProvince").val(routeInfoEntityBean.destinationProvince);
		$("#receiverCity").val(routeInfoEntityBean.destinationCity);
		$("#receiverRegion").val(routeInfoEntityBean.destinationRegion);
		$("#receiverAddress").val(routeInfoEntityBean.destinationAddress);
		$("#receiverTelephone").val(routeInfoEntityBean.destinationTelephone);
		$("#receiverMobile").val(routeInfoEntityBean.destinationMobile);
		$("#receiverCityDummy").val(routeInfoEntityBean.destinationCity);
		getCityNameList('receiverProvince','receiverCity',routeInfoEntityBean.destinationCity);
		getRegionNameList('receiverCityDummy','receiverRegion',routeInfoEntityBean.destinationRegion);
		$('#receiver input').attr("readonly","readonly")
		$('#receiver select').attr("disabled","disabled")
		
		$("#routeId").val(routeInfoEntityBean.routeId);
		$("#selectRoute").val(routeInfoEntityBean.routeName);
	}
}

function clearData(){
	var infoNumber = $("#infoNumber").val().trim();
	if(infoNumber != ''){
	$('#infoNumber').val('');
	$('#infoId').val('');
	
	$("#consignerName").val('');
	$("#consignerCountry").val('');
	$("#consignerProvince").val('');
	$("#consignerCity").val('');
	$("#consignerRegion").val('');
	$("#consignerAddress").val('');
	$("#consignerTelephone").val('');
	$("#consignerMobile").val('');
	$("#receiverName").val('');
	$("#receiverCountry").val('');
	$("#receiverProvince").val('');
	$("#receiverCity").val('');
	$("#receiverRegion").val('');
	$("#receiverAddress").val('');
	$("#receiverTelephone").val('');
	$("#receiverMobile").val('');
	
	$('select').removeAttr("disabled");
	$('#receiver input').removeAttr("readonly");
	$('#consigner input').removeAttr("readonly");
	}
}

function formOnSubmit(){	
	if(checkForm('formData')){
		var infoNumber = $("#infoNumber").val().trim();
		if(infoNumber == ''){
			$('#infoType').val('');
		}
		//验证通过后，去除disable
		$('select').removeAttr("disabled");
		return true;
	}else{
		return false;
	}
}
</script>
</html>