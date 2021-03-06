/*
 * JSP generated by Resin Professional 4.0.48 (built Wed, 17 Feb 2016 11:03:24 PST)
 */

package _jsp._web_22dinf._pages._tms._route;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import com.lpmas.framework.config.*;
import com.lpmas.framework.bean.*;
import com.lpmas.framework.page.*;
import com.lpmas.framework.util.*;
import com.lpmas.framework.web.*;
import com.lpmas.admin.bean.*;
import com.lpmas.admin.business.*;
import com.lpmas.admin.config.*;
import com.lpmas.tms.route.bean.*;
import com.lpmas.tms.logistics.bean.*;
import com.lpmas.tms.route.config.*;
import com.lpmas.tms.config.*;

public class _routeofferlist__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  private boolean _caucho_isNotModified;
  private com.caucho.jsp.PageManager _jsp_pageManager;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);

    TagState _jsp_state = null;

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_pageManager.freePageContext(pageContext);
    }
  }
  
  private void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response,
              com.caucho.jsp.PageContextImpl pageContext,
              javax.servlet.ServletContext application,
              javax.servlet.http.HttpSession session,
              TagState _jsp_state)
    throws Throwable
  {
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.jsp.tagext.JspTag _jsp_parent_tag = null;
    com.caucho.jsp.PageContextImpl _jsp_parentContext = pageContext;
    response.setContentType("text/html; charset=UTF-8");

    out.write(_jsp_string0, 0, _jsp_string0.length);
    
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

    out.write(_jsp_string1, 0, _jsp_string1.length);
    
String DOMAIN = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "DEFAULT_DOMAIN");
String STATIC_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "STATIC_URL");
String SRM_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "SRM_URL");
String ERP_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "ERP_URL");
String TMS_URL = PropertiesKit.getBundleProperties(TmsConsoleConfig.TMS_PROP_FILE_NAME, "TMS_URL");

    out.write(_jsp_string2, 0, _jsp_string2.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string3, 0, _jsp_string3.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string4, 0, _jsp_string4.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string5, 0, _jsp_string5.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((MapKit.getValueFromMap(routeId, routeInfoMap)));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    if(routeId == 0){ 
    out.write(_jsp_string8, 0, _jsp_string8.length);
    for (RouteInfoBean routeInfoBean : routeInfoList) {
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((routeInfoBean.getRouteId()));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((routeInfoBean.getRouteId() == routeId ? "selected" : ""));
    out.write('>');
    out.print((routeInfoBean.getRouteName()));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    }
    out.write(_jsp_string12, 0, _jsp_string12.length);
    } else{
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((route.getRouteName() ));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((routeId));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    } 
    out.write(_jsp_string16, 0, _jsp_string16.length);
    for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((logisticsCompanyInfoBean.getCompanyId()));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((logisticsCompanyInfoBean.getCompanyId() == ParamKit.getIntParameter(request, "companyId", 0) ? "selected" : ""));
    out.write('>');
    out.print((logisticsCompanyInfoBean.getCompanyName()));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    }
    out.write(_jsp_string17, 0, _jsp_string17.length);
    
    	int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ 
    out.write(_jsp_string18, 0, _jsp_string18.length);
    out.print((statusBean.getStatus() ));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print(((statusBean.getStatus()==status)?"selected":"" ));
    out.write('>');
    out.print((statusBean.getValue() ));
    out.write(_jsp_string19, 0, _jsp_string19.length);
    } 
    out.write(_jsp_string20, 0, _jsp_string20.length);
    if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)){ 
    out.write(_jsp_string21, 0, _jsp_string21.length);
    } 
    out.write(_jsp_string22, 0, _jsp_string22.length);
    
    int rowCount = 0;
    for(RouteOfferBean bean:list){ 
    	rowCount++;
    
    out.write(_jsp_string23, 0, _jsp_string23.length);
    out.print((rowCount +(PAGE_BEAN.getCurrentPageNumber()-1)*TmsConsoleConfig.DEFAULT_PAGE_SIZE));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((MapKit.getValueFromMap(bean.getRouteId(), routeInfoMap)));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((MapKit.getValueFromMap(bean.getCompanyId(), CompanyInfoMap)));
    out.write(_jsp_string25, 0, _jsp_string25.length);
    out.print((NumberKit.formatDouble(bean.getDuration(), 2)));
    out.write(_jsp_string25, 0, _jsp_string25.length);
    out.print((NumberKit.formatDouble(bean.getTransportPrice(), 2)));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((Constants.STATUS_MAP.get(bean.getStatus())));
    out.write(_jsp_string26, 0, _jsp_string26.length);
    out.print((bean.getCompanyId()));
    out.write(_jsp_string27, 0, _jsp_string27.length);
    out.print((bean.getRouteId()));
    out.write(_jsp_string28, 0, _jsp_string28.length);
    if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.UPDATE)){ 
    out.write(_jsp_string29, 0, _jsp_string29.length);
    out.print((bean.getCompanyId()));
    out.write(_jsp_string27, 0, _jsp_string27.length);
    out.print((bean.getRouteId()));
    out.write(_jsp_string30, 0, _jsp_string30.length);
    } 
    out.write(_jsp_string31, 0, _jsp_string31.length);
    } 
    out.write(_jsp_string32, 0, _jsp_string32.length);
    if(adminHelper.hasPermission(RouteResource.ROUTE_OFFER, OperationConfig.CREATE)){ 
    out.write(_jsp_string33, 0, _jsp_string33.length);
    } 
    out.write(_jsp_string34, 0, _jsp_string34.length);
    for(String[] condArray :COND_LIST){ 
    out.write(_jsp_string35, 0, _jsp_string35.length);
    out.print((condArray[0] ));
    out.write(_jsp_string36, 0, _jsp_string36.length);
    out.print((condArray[0] ));
    out.write(_jsp_string37, 0, _jsp_string37.length);
    out.print((condArray[1] ));
    out.write(_jsp_string38, 0, _jsp_string38.length);
    } 
    out.write(_jsp_string39, 0, _jsp_string39.length);
    if(PAGE_BEAN.getCurrentPageNumber()>1){ 
    out.write(_jsp_string40, 0, _jsp_string40.length);
    out.print((PAGE_BEAN.getPrePageNumber() ));
    out.write(_jsp_string41, 0, _jsp_string41.length);
    } 
    out.write(_jsp_string42, 0, _jsp_string42.length);
    out.print((PAGE_BEAN.getTotalRecordNumber() ));
    out.write(_jsp_string43, 0, _jsp_string43.length);
    out.print((PAGE_BEAN.getPageSize() ));
    out.write(_jsp_string44, 0, _jsp_string44.length);
    out.print((PAGE_BEAN.getCurrentPageNumber() ));
    out.write(_jsp_string45, 0, _jsp_string45.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string46, 0, _jsp_string46.length);
    if(PAGE_BEAN.getCurrentPageNumber() < PAGE_BEAN.getTotalPageNumber()){ 
    out.write(_jsp_string47, 0, _jsp_string47.length);
    out.print((PAGE_BEAN.getNextPageNumber() ));
    out.write(_jsp_string48, 0, _jsp_string48.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string49, 0, _jsp_string49.length);
    } 
    out.write(_jsp_string50, 0, _jsp_string50.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string51, 0, _jsp_string51.length);
  }

  private com.caucho.make.DependencyContainer _caucho_depends
    = new com.caucho.make.DependencyContainer();

  public java.util.ArrayList<com.caucho.vfs.Dependency> _caucho_getDependList()
  {
    return _caucho_depends.getDependencies();
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    _caucho_depends.add(depend);
  }

  protected void _caucho_setNeverModified(boolean isNotModified)
  {
    _caucho_isNotModified = true;
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;

    if (_caucho_isNotModified)
      return false;

    if (com.caucho.server.util.CauchoSystem.getVersionId() != -8002497470487589159L)
      return true;

    return _caucho_depends.isModified();
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
    TagState tagState;
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/tms/route/RouteOfferList.jsp"), 6816381281906724968L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/header.jsp"), 6309790488545470124L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/page.jsp"), -7400274417948715423L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
  }

  final static class TagState {

    void release()
    {
    }
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void caucho_init(ServletConfig config)
  {
    try {
      com.caucho.server.webapp.WebApp webApp
        = (com.caucho.server.webapp.WebApp) config.getServletContext();
      init(config);
      if (com.caucho.jsp.JspManager.getCheckInterval() >= 0)
        _caucho_depends.setCheckInterval(com.caucho.jsp.JspManager.getCheckInterval());
      _jsp_pageManager = webApp.getJspApplicationContext().getPageManager();
      com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }

  private final static char []_jsp_string28;
  private final static char []_jsp_string49;
  private final static char []_jsp_string43;
  private final static char []_jsp_string32;
  private final static char []_jsp_string3;
  private final static char []_jsp_string22;
  private final static char []_jsp_string25;
  private final static char []_jsp_string10;
  private final static char []_jsp_string5;
  private final static char []_jsp_string40;
  private final static char []_jsp_string18;
  private final static char []_jsp_string23;
  private final static char []_jsp_string16;
  private final static char []_jsp_string24;
  private final static char []_jsp_string48;
  private final static char []_jsp_string44;
  private final static char []_jsp_string26;
  private final static char []_jsp_string34;
  private final static char []_jsp_string19;
  private final static char []_jsp_string20;
  private final static char []_jsp_string11;
  private final static char []_jsp_string37;
  private final static char []_jsp_string39;
  private final static char []_jsp_string35;
  private final static char []_jsp_string15;
  private final static char []_jsp_string6;
  private final static char []_jsp_string51;
  private final static char []_jsp_string50;
  private final static char []_jsp_string36;
  private final static char []_jsp_string30;
  private final static char []_jsp_string46;
  private final static char []_jsp_string29;
  private final static char []_jsp_string2;
  private final static char []_jsp_string45;
  private final static char []_jsp_string31;
  private final static char []_jsp_string33;
  private final static char []_jsp_string4;
  private final static char []_jsp_string21;
  private final static char []_jsp_string0;
  private final static char []_jsp_string47;
  private final static char []_jsp_string38;
  private final static char []_jsp_string8;
  private final static char []_jsp_string27;
  private final static char []_jsp_string14;
  private final static char []_jsp_string17;
  private final static char []_jsp_string9;
  private final static char []_jsp_string12;
  private final static char []_jsp_string41;
  private final static char []_jsp_string1;
  private final static char []_jsp_string42;
  private final static char []_jsp_string13;
  private final static char []_jsp_string7;
  static {
    _jsp_string28 = "&readOnly=true\">\u67e5\u770b</a> \r\n      ".toCharArray();
    _jsp_string49 = ");\">&nbsp;</em>".toCharArray();
    _jsp_string43 = "</b>\u6761  \u6bcf\u9875\u663e\u793a<b>".toCharArray();
    _jsp_string32 = "\r\n  </table>\r\n</form>\r\n<ul class=\"page_info\">\r\n<li class=\"page_left_btn\">\r\n".toCharArray();
    _jsp_string3 = "/css/main.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string22 = "\r\n  </div>\r\n  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_style\">\r\n    <tr>\r\n      <th>\u62a5\u4ef7ID</th>\r\n      <th>\u8def\u5f84\u540d\u79f0</th>\r\n      <th>\u7269\u6d41\u516c\u53f8</th>\r\n      <th>\u65f6\u6548(\u5929)</th>\r\n      <th>\u8fd0\u8d39(\u5143/KG)</th>\r\n      <th>\u72b6\u6001</th>\r\n      <th>\u64cd\u4f5c</th>\r\n    </tr>\r\n    ".toCharArray();
    _jsp_string25 = "</td>\r\n      <td align=\"right\">".toCharArray();
    _jsp_string10 = "\" ".toCharArray();
    _jsp_string5 = "/js/common.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string40 = "<em class=\"first\" onclick=\"javascript:goPage('formPage',1);\">&nbsp;</em>\r\n  <em class=\"pre\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string18 = "\r\n          <option value=\"".toCharArray();
    _jsp_string23 = "\r\n    <tr>\r\n      <td>".toCharArray();
    _jsp_string16 = "\r\n    <em class=\"em1\">\u7269\u6d41\u516c\u53f8\uff1a</em>\r\n    <select name=\"companyId\" id=\"companyId\">\r\n    <option value=\"\"></option>\r\n	".toCharArray();
    _jsp_string24 = "</td>\r\n      <td>".toCharArray();
    _jsp_string48 = ");\">&nbsp;</em>\r\n  <em class=\"last\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string44 = "</b>\u6761 \u5f53\u524d\u7b2c<b>".toCharArray();
    _jsp_string26 = "</td>\r\n      <td align=\"center\">\r\n      <a href=\"/route/RouteOfferManage.do?companyId=".toCharArray();
    _jsp_string34 = "\r\n</li>\r\n\r\n<form name=\"formPage\" method=\"post\" action=\"\">\r\n<input type=\"hidden\" name=\"pageNum\" id=\"pageNum\" value=\"\"/>\r\n".toCharArray();
    _jsp_string19 = "</option>\r\n        ".toCharArray();
    _jsp_string20 = "\r\n    </select>\r\n    <input name=\"\" type=\"submit\" class=\"search_btn_sub\" value=\"\u67e5\u8be2\"/>\r\n    ".toCharArray();
    _jsp_string11 = "</option>\r\n	".toCharArray();
    _jsp_string37 = "\" value=\"".toCharArray();
    _jsp_string39 = "\r\n</form>\r\n<li class=\"page_num\">\r\n  ".toCharArray();
    _jsp_string35 = "\r\n<input type=\"hidden\" name=\"".toCharArray();
    _jsp_string15 = "\"/>\r\n	".toCharArray();
    _jsp_string6 = "/js/ui.js\"></script>\r\n</head>\r\n<body class=\"article_bg\">\r\n<p class=\"article_tit\">".toCharArray();
    _jsp_string51 = ");\"/> \r\n</li>\r\n</ul>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string50 = "\r\n  <input name=\"goPageNum\" id=\"goPageNum\" type=\"text\" class=\"page_int_txt\"/><input name=\"\" type=\"button\" class=\"page_btn_go\" value=\"GO\" onclick=\"javascript:goInputPage('formPage',".toCharArray();
    _jsp_string36 = "\" id=\"".toCharArray();
    _jsp_string30 = "&readOnly=false\">\u4fee\u6539</a>".toCharArray();
    _jsp_string46 = "\u9875 \r\n  ".toCharArray();
    _jsp_string29 = "|<a href=\"/route/RouteOfferManage.do?companyId=".toCharArray();
    _jsp_string2 = "\r\n<link href=\"".toCharArray();
    _jsp_string45 = "</b>/".toCharArray();
    _jsp_string31 = "\r\n      </td>\r\n    </tr>\r\n    ".toCharArray();
    _jsp_string33 = "\r\n  <input type=\"button\" name=\"button\" id=\"button\" value=\"\u65b0\u5efa\" onclick=\"javascript:location.href='/route/RouteOfferManage.do'\">\r\n".toCharArray();
    _jsp_string4 = "/js/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string21 = "\r\n    <input type=\"button\" name=\"button\" id=\"button\" class=\"search_btn_sub\" value=\"\u65b0\u5efa\" onclick=\"javascript:location.href='/route/RouteOfferManage.do'\">\r\n    ".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string47 = "<em class=\"next\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string38 = "\"/>".toCharArray();
    _jsp_string8 = "\r\n  	<em class=\"em1\">\u8def\u5f84\u540d\u79f0\uff1a</em>\r\n    <select name=\"routeId\" id=\"routeId\">\r\n    <option value=\"\"></option>\r\n	".toCharArray();
    _jsp_string27 = "&routeId=".toCharArray();
    _jsp_string14 = "</label>\r\n	<input type=\"hidden\" name=\"routeId\" id=\"routeId\"  value=\"".toCharArray();
    _jsp_string17 = "</select>\r\n    <em class=\"em1\">\u72b6\u6001\uff1a</em>\r\n    <select name=\"status\" id=\"status\">\r\n    	".toCharArray();
    _jsp_string9 = "\r\n	<option value=\"".toCharArray();
    _jsp_string12 = "</select>\r\n	".toCharArray();
    _jsp_string41 = ");\"> &nbsp;</em>".toCharArray();
    _jsp_string1 = "\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>\u8def\u5f84\u62a5\u4ef7</title>\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string42 = "\r\n       \u603b\u8bb0\u5f55<b>".toCharArray();
    _jsp_string13 = "\r\n	<em class=\"em1\">\u8def\u5f84\u540d\u79f0\uff1a</em>\r\n	<label>".toCharArray();
    _jsp_string7 = "\u8def\u5f84\u62a5\u4ef7</p>\r\n<form name=\"formSearch\" method=\"post\" action=\"RouteOfferList.do\">\r\n  <div class=\"search_form\">\r\n    ".toCharArray();
  }
}
