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
<%@ page import="com.lpmas.tms.config.*"  %>
<%@ page import="com.lpmas.tms.logistics.config.*"  %>
<%@ page import="com.lpmas.tms.logistics.bean.*"  %>
<%
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<LogisticsCompanyInfoBean> list = (List<LogisticsCompanyInfoBean>)request.getAttribute("CompanyList");

PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流公司列表</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit">物流公司列表</p>
<form name="formSearch" method="post" action="/logistics/LogisticsCompanyInfoList.do">
  <div class="search_form">
  	<em class="em1">公司代码：</em>
    <input type="text" name="companyCode" id="companyCode" value="<%=ParamKit.getParameter(request, "companyCode", "") %>" size="20"/>
    <em class="em1">公司名称：</em>
    <input type="text" name="companyName" id="companyName" value="<%=ParamKit.getParameter(request, "companyName", "") %>" size="20"/>
    <em class="em1">状态：</em>
    <select name="status" id="status">
    	<%
    	int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
          <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
        <%} %>
    </select>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th>公司ID</th>
      <th>公司代码</th>
      <th>公司名称</th>
      <th>操作</th>
    </tr>
    <%
    for(LogisticsCompanyInfoBean bean:list){ 
    %>
    <tr>
      <td><%=bean.getCompanyId() %></td>
      <td><%=bean.getCompanyCode() %></td>
      <td><%=bean.getCompanyName() %></td>
      <td align="center"><%if(adminHelper.hasPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.UPDATE)){ %><a href="/logistics/LogisticsCompanyInfoManage.do?companyId=<%=bean.getCompanyId() %>">修改</a><%} %></td>
    </tr>
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
  <%if(adminHelper.hasPermission(LogisticsResource.LOGISTICS_COMPANY, OperationConfig.CREATE)){ %><input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='/logistics/LogisticsCompanyInfoManage.do'"><%} %>
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
</html>