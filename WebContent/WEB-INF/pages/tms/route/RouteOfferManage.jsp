<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page import="com.lpmas.admin.bean.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.tms.route.bean.*"%>
<%@ page import="com.lpmas.tms.logistics.bean.*"%>
<%
	RouteOfferBean bean = (RouteOfferBean) request.getAttribute("RouteOffer");
	List<RouteInfoBean> routeInfoList = (List<RouteInfoBean>) request.getAttribute("RouteInfoList");
	List<LogisticsCompanyInfoBean> companyInfoList = (List<LogisticsCompanyInfoBean>) request.getAttribute("CompanyInfoList");
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	String readOnly = ParamKit.getParameter(request, "readOnly","false").trim();
	request.setAttribute("readOnly", readOnly);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径报价管理</title>
<%@ include file="../../include/header.jsp"%>
<script type="text/javascript" src="../js/tms-common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="RouteOfferList.do">路径报价列表</a>&nbsp;>&nbsp;</li>
			<% if(bean.getCompanyId() > 0 && bean.getRouteId() > 0) {%>
			<li>修改路径报价信息</li>
			<%}else{ %>
			<li>新建路径报价信息</li>
			<%}%>
		</ul>
	</div>
	<form id="formData" name="formData" method="post" action="RouteOfferManage.do" onsubmit="javascript:return checkForm('formData');">
	    <input type="hidden" name="currency" id="currency" value="<%=bean.getCurrency()%>" />
		<div class="modify_form">
			<p>
				<em class="int_label"><span>*</span>路径名称：</em> <select name="routeId" id="routeId"  checkStr="路径;num;true;;20">
					<%
						for (RouteInfoBean routeInfoBean : routeInfoList) {
					%><option value="<%=routeInfoBean.getRouteId()%>" <%=routeInfoBean.getRouteId() == bean.getRouteId() ? "selected" : ""%>><%=routeInfoBean.getRouteName()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label"><span>*</span>物流公司：</em> <select name="companyId" id="companyId" checkStr="物流公司;num;true;;20" >
					<%
						for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {
					%><option value="<%=logisticsCompanyInfoBean.getCompanyId()%>" <%=logisticsCompanyInfoBean.getCompanyId() == bean.getCompanyId() ? "selected" : ""%>><%=logisticsCompanyInfoBean.getCompanyName()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label"><span>*</span>时效：</em> <input type="text" name="duration" id="duration" size="20" maxlength="20" value="<%=bean.getDuration() >0 ? bean.getDuration() : "" %>"
					onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' checkStr="时效;num;true;;20" />
			</p>
			<p>
				<em class="int_label"><span>*</span>运费：</em> <input type="text" name="transportPrice" id="transportPrice" size="30" maxlength="100"
					value="<%=bean.getTransportPrice() >0 ? bean.getTransportPrice() : ""%>" onkeyup='this.value=this.value.replace(/[^\-?\d.]/g,"")' checkStr="运费;num;true;;20" />
			</p>
			<p>
				<em class="int_label">状态：</em> <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID%>" <%=(bean.getStatus() == Constants.STATUS_VALID) ? "checked" : ""%> />
			</p>
			<p class="p_top_border">
				<em class="int_label">备注：</em>
				<textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=bean.getMemo()%></textarea>
			</p>
		</div>
		<div class="div_center">
			<input type="submit" name="button" id="button" class="modifysubbtn" value="提交" />
			<a href="javascript:history.back()"><input type="button" name="cancel" id="cancel" value="取消" ></a>
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
</script>
</html>