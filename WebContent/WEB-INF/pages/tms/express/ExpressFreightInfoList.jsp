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
<%@ page import="com.lpmas.tms.express.config.*"  %>
<%@ page import="com.lpmas.tms.express.bean.*"  %>
<%
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<ExpressFreightInfoBean> list = (List<ExpressFreightInfoBean>)request.getAttribute("FreightList");
List<ExpressCompanyInfoBean> companyList = (List<ExpressCompanyInfoBean>)request.getAttribute("CompanyList");
Map<String, String> companyMap = ListKit.list2Map(companyList, "CompanyId", "CompanyName");

PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快递价格列表</title>
<%@ include file="../../include/header.jsp" %>
</head>
<body class="article_bg">
<p class="article_tit">快递价格列表</p>
<form name="formSearch" method="post" action="/express/ExpressFreightInfoList.do">
  <div class="search_form">
  	<em class="em1">公司：</em>
    <select name="companyId" id="companyId">
        <option></option>
    	<%
    	int companyId = ParamKit.getIntParameter(request, "companyId", 0);
    	for(ExpressCompanyInfoBean companyBean:companyList){ %>
          <option value="<%=companyBean.getCompanyId() %>" <%=(companyBean.getCompanyId()==companyId)?"selected":"" %>><%=companyBean.getCompanyName() %></option>
        <%} %>
    </select>
    <em class="em1">国家：</em>
    <input type="text" name="country" id="country" value="<%=ParamKit.getParameter(request, "country", "") %>" size="20"/>
    <em class="em1">省份：</em>
    <input type="text" name="province" id="province" value="<%=ParamKit.getParameter(request, "province", "") %>" size="20"/>
    <em class="em1">城市：</em>
    <input type="text" name="city" id="city" value="<%=ParamKit.getParameter(request, "city", "") %>" size="20"/>
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
      <th>公司</th>
      <th>国家</th>
      <th>省份</th>
      <th>城市</th>
      <th>价格</th>
      <th>操作</th>
    </tr>
    <%
    for(ExpressFreightInfoBean bean:list){ 
    %>
    <tr>
      <td><%=MapKit.getValueFromMap(bean.getCompanyId(), companyMap) %></td>
      <td><%=bean.getCountry() %></td>
      <td><%=bean.getProvince() %></td>
      <td><%=bean.getCity() %></td>
      <td><%=bean.getPrice() %></td>
      <td align="center"><%if(adminHelper.hasPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.UPDATE)){ %><a href="/express/ExpressFreightInfoManage.do?freightId=<%=bean.getFreightId() %>">修改</a><%} %></td>
    </tr>
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
  <%if(adminHelper.hasPermission(ExpressResource.EXPRESS_FREIGHT, OperationConfig.CREATE)){ %><input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='/express/ExpressFreightInfoManage.do'"><%} %>
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
</html>