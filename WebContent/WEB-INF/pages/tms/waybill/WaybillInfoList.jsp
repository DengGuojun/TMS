<%@page import="com.lpmas.tms.waybill.busniess.WaybillStatusProcessor"%>
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
<%@page import="com.lpmas.admin.client.cache.AdminUserInfoClientCache"%>
<%@page import="com.lpmas.constant.info.InfoTypeConfig"%>
<%@ page import="com.lpmas.tms.waybill.bean.*"  %>
<%@ page import="com.lpmas.tms.logistics.bean.*"  %>
<%@ page import="com.lpmas.tms.waybill.config.*"  %>
<%
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<WaybillEntityBean> list = (List<WaybillEntityBean>)request.getAttribute("WaybillEntityList");
List<LogisticsCompanyInfoBean> companyInfoList = (List<LogisticsCompanyInfoBean>) request.getAttribute("CompanyInfoList");
Map<Integer,String> routeInfoMap = (Map<Integer,String>) request.getAttribute("RouteInfoMap");
Map<Integer,String> CompanyInfoMap = (Map<Integer,String>) request.getAttribute("CompanyInfoMap");
List<Integer> waybillInfoCreaterUserList = (List<Integer>) request.getAttribute("WaybillInfoCreaterUserList");
int infoId = ParamKit.getIntParameter(request, "infoId", 0);
AdminUserInfoClientCache adminCache = new AdminUserInfoClientCache();
PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>承运单</title>
<%@ include file="../../include/header.jsp" %>
<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; 
</script>
</head>
<body class="article_bg">
<p class="article_tit">承运单</p>
<form name="formSearch" method="post" action="/waybill/WaybillInfoList.do">
  <div class="search_form">
    <em class="em1">物流公司：</em>
    <select name="companyId" id="companyId">
    <option value=""></option>
	<%int companyId = ParamKit.getIntParameter(request, "companyId", 0);
	for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {%>
	<option value="<%=logisticsCompanyInfoBean.getCompanyId()%>" <%=logisticsCompanyInfoBean.getCompanyId() == companyId ? "selected" : ""%>><%=logisticsCompanyInfoBean.getCompanyName()%></option>
	<%}%></select>
	<em class="em1">关联订单类型：</em>
    <select name="infoType" id="infoType">
    <option value=""></option>
	<%int infoType = ParamKit.getIntParameter(request, "infoType", 0);
	for (StatusBean<Integer, String> statusBean :WaybillConfig.WAYBILL_INFO_TYPE_LIST) {%>
	<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus() == infoType ? "selected" : ""%>><%=statusBean.getValue()%></option>
	<%}%></select>
	<em class="em1">关联订单号：</em> 
	<input type="text" name="infoNumber" value="<%=ParamKit.getParameter(request, "infoNumber", "") %>" size="20" id="selectInfo" readonly/>
	<input type="hidden" name="infoId" id="infoId" value="<%=infoId%>"/> 
	<em class="em1">创建人:</em> 
	<select name="createUser" id="createUser">
		<option value=""></option>
		<%
			int createUser = ParamKit.getIntParameter(request, "createUser", 0);
			for (Integer user : waybillInfoCreaterUserList) {
		%>
		<option value="<%=user%>"
			<%=(user == createUser) ? "selected" : ""%>><%=adminCache.getAdminUserNameByKey(user)%>
		</option>
		<%
			}
		%>
	</select>
	<em class="em1">审批状态：</em> 
	<select name="approvalStatus" id="approvalStatus">
	<option value=""></option>
	<%
		String approvalStatus = ParamKit.getParameter(request, "approvalStatus", "");
		for (StatusBean<String, String> statusBean :WaybillConfig.REVIEW_STATUS_LIST) {
	%>
	<option value="<%=statusBean.getStatus()%>"
		<%=(statusBean.getStatus().equals(approvalStatus)) ? "selected" : ""%>><%=statusBean.getValue()%></option>
	<%
		}
	%>
	</select>
	<em class="em1">进度：</em> 
	<select name="waybillStatus" id="waybillStatus">
	<option value=""></option>
	<%
		String waybillStatus = ParamKit.getParameter(request, "waybillStatus", "");
		for (StatusBean<String, String> statusBean :WaybillConfig.WAYBILL_STATUS_LIST) {
	%>
	<option value="<%=statusBean.getStatus()%>"
		<%=(statusBean.getStatus().equals(waybillStatus)) ? "selected" : ""%>><%=statusBean.getValue()%></option>
	<%
		}
	%>
	</select>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
    <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.CREATE)){ %>
    <input type="button" name="button" id="button" class="search_btn_sub" value="新建" onclick="javascript:location.href='/waybill/WaybillInfoManage.do'">
    <%} %>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th>选择</th>
      <th>承运单ID</th>
      <th>承运单编号</th>
      <th>物流公司</th>
      <th>物流路径</th>
      <th>关联订单类型</th>
      <th>关联订单号</th>
      <th>总运费</th>
      <th>审批状态</th>
      <th>进度状态</th>
      <th>操作</th>
    </tr>
    <%
    WaybillStatusProcessor processor = null;
    for(WaybillEntityBean bean:list){ 
    	processor = new WaybillStatusProcessor(bean);
    %>
    <tr>
      <td><input type="radio" name="waybillId" id="radio_<%=bean.getWaybillId()%>" value="<%=bean.getWaybillId()%>" onclick="changeDisable()"></td>
      <td><%=bean.getWaybillId() %></td>
      <td id="waybillNumber_<%=bean.getWaybillId()%>"><%=bean.getWaybillNumber()%></td>
      <td><%=MapKit.getValueFromMap(bean.getLogisticsCompanyId(), CompanyInfoMap)%></td>
      <td><%=bean.getRouteName()%></td>
      <td><%=MapKit.getValueFromMap(bean.getInfoType(), WaybillConfig.WAYBILL_INFO_TYPE_MAP)%></td>
      <td><%=bean.getWareNumber()%></td>
      <td align="right"><%=bean.getTotalAmount()%></td>
      <td id="reviewStatus_<%=bean.getWaybillId()%>"><%=MapKit.getValueFromMap(bean.getApprovalStatus(), WaybillConfig.REVIEW_STATUS_MAP)%></td>
      <td><%=MapKit.getValueFromMap(bean.getWaybillStatus(), WaybillConfig.WAYBILL_STATUS_MAP)%></td>
      <td align="center">
      <a href="/waybill/WaybillInfoManage.do?waybillId=<%=bean.getWaybillId()%>&readOnly=true">查看</a> 
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& !processor.isLock()){ %>|<a href="/waybill/WaybillInfoManage.do?waybillId=<%=bean.getWaybillId()%>&readOnly=false">修改</a><%} %>
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& processor.committable()){ %>|<a href="/waybill/WaybillStatusManage.do?waybillId=<%=bean.getWaybillId()%>&statusAction=<%=WaybillConfig.WAYBILL_ACTION_COMMIT%>">提交审批</a><%} %>
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& processor.cancelCommittable()){ %>|<a href="/waybill/WaybillStatusManage.do?waybillId=<%=bean.getWaybillId()%>&statusAction=<%=WaybillConfig.WAYBILL_ACTION_CANCEL_COMMIT%>">取消审批</a><%} %>
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& processor.placeOrderable()){ %>|<a href="/waybill/WaybillStatusManage.do?waybillId=<%=bean.getWaybillId()%>&statusAction=<%=WaybillConfig.WAYBILL_ACTION_PLACE_ORDER%>">下单</a><%} %>
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& processor.receiveable()){ %>|<a href="/waybill/WaybillStatusManage.do?waybillId=<%=bean.getWaybillId()%>&statusAction=<%=WaybillConfig.WAYBILL_ACTION_REVEICE%>">收货</a><%} %>
      <%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.UPDATE)&& processor.closable()){ %>|<a href="/waybill/WaybillStatusManage.do?waybillId=<%=bean.getWaybillId()%>&statusAction=<%=WaybillConfig.WAYBILL_ACTION_CLOSE%>">关闭</a><%} %>
      </td>
    </tr>
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
<%if(adminHelper.hasPermission(WaybillResource.WAYBILL_INFO, OperationConfig.CREATE)){ %>
  <input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='/waybill/WaybillInfoManage.do'">
<%} %>
 <%if (adminHelper.hasPermission(WaybillResource.WAYBILL_INFO,OperationConfig.REMOVE)) {%>
	<input type="button" name="delete" id="delete" value="删除"> 
 <%}%>
<%if (adminHelper.hasPermission(WaybillResource.WAYBILL_INFO,OperationConfig.APPROVE)) {%>
	<input type="button" name="review" id="review" disabled="disabled" value="审批"> 
<%}%>
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
<script>
$(document).ready(function() {
	$("#delete").click(function() {
		var waybillId = $('input:radio[name=waybillId]:checked').val();	
		if (typeof(waybillId) == "undefined"){
		    alert("请选择需要删除的承运单");
		    return;
		}
		var waybillNumber = $('#waybillNumber_'+waybillId).html();
		if(confirm("确定要删除承运单"+waybillNumber+"吗?")){
			var url = "WaybillInfoRemove.do?waybillId="+ waybillId ;
			window.location.href= url
		 }
		
	});	
	$("#review").click(function() {
		var waybillId = $('input:radio[name=waybillId]:checked').val();			
		$.fancybox.open({
			href : 'WaybillApproveResultSelect.do?callbackFun=selectReviewResult&waybillId=' + waybillId,
			type : 'iframe',
			width : 560,
			minHeight : 150
		});		
	});
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
					minHeight : 150
			});
		});
});
function selectWarehouseTransfer(transferOrderJsonStr, sourceWarehouseJsonStr,targetWarehouseJsonStr){
	var warehouseTransferOrderInfoBean = jQuery.parseJSON(transferOrderJsonStr);
	if(warehouseTransferOrderInfoBean.toId != "" && warehouseTransferOrderInfoBean.toNumber != ""){		
		$("#infoId").val(warehouseTransferOrderInfoBean.toId);
		$("#selectInfo").val(warehouseTransferOrderInfoBean.toNumber);
	}
}
function selectDeliveryNote(deliveryNoteJsonStr, purchaseOrderJsonStr){
	var deliveryNoteInfoBean = jQuery.parseJSON(deliveryNoteJsonStr);
	if(deliveryNoteInfoBean.dnId != "" && deliveryNoteInfoBean.dnNumber != ""){		
		$("#infoId").val(deliveryNoteInfoBean.dnId);
		$("#selectInfo").val(deliveryNoteInfoBean.dnNumber);
	}
}
function changeDisable(){
	var waybillId = $('input:radio[name=waybillId]:checked').val();
	var reviewStatus = $("#reviewStatus_"+waybillId).html();
	var waitApprove = "<%=WaybillConfig.REVIEW_STATUS_MAP.get(WaybillConfig.REVIEW_STATUS_WAIT_APPROVE)%>";
	if(reviewStatus == waitApprove ){				
		$('#review').removeAttr("disabled"); 
	}else{
	   $('#review').attr("disabled","disabled"); 
	}
}
function selectReviewResult(result){
	var waybillId = $('input:radio[name=waybillId]:checked').val();		
	var url = "WaybillStatusManage.do?waybillId="+ waybillId +"&statusAction="+ result;
	window.location.href= url
}
</script>
</html>