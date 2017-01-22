/*
 * JSP generated by Resin Professional 4.0.48 (built Wed, 17 Feb 2016 11:03:24 PST)
 */

package _jsp._web_22dinf._pages._tms._route;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import com.lpmas.framework.config.*;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.admin.bean.*;
import com.lpmas.admin.business.*;
import com.lpmas.tms.route.bean.*;
import com.lpmas.tms.logistics.bean.*;
import com.lpmas.framework.util.*;
import com.lpmas.tms.config.*;

public class _routeoffermanage__jsp extends com.caucho.jsp.JavaPage
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
    
	RouteOfferBean bean = (RouteOfferBean) request.getAttribute("RouteOffer");
	List<RouteInfoBean> routeInfoList = (List<RouteInfoBean>) request.getAttribute("RouteInfoList");
	List<LogisticsCompanyInfoBean> companyInfoList = (List<LogisticsCompanyInfoBean>) request.getAttribute("CompanyInfoList");
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	String readOnly = ParamKit.getParameter(request, "readOnly","false").trim();
	request.setAttribute("readOnly", readOnly);

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
    out.print((STATIC_URL ));
    out.write(_jsp_string7, 0, _jsp_string7.length);
     if(bean.getCompanyId() > 0 && bean.getRouteId() > 0) {
    out.write(_jsp_string8, 0, _jsp_string8.length);
    }else{ 
    out.write(_jsp_string9, 0, _jsp_string9.length);
    }
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((bean.getCurrency()));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    
						for (RouteInfoBean routeInfoBean : routeInfoList) {
					
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((routeInfoBean.getRouteId()));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((routeInfoBean.getRouteId() == bean.getRouteId() ? "selected" : ""));
    out.write('>');
    out.print((routeInfoBean.getRouteName()));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    
						}
					
    out.write(_jsp_string15, 0, _jsp_string15.length);
    
						for (LogisticsCompanyInfoBean logisticsCompanyInfoBean : companyInfoList) {
					
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((logisticsCompanyInfoBean.getCompanyId()));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((logisticsCompanyInfoBean.getCompanyId() == bean.getCompanyId() ? "selected" : ""));
    out.write('>');
    out.print((logisticsCompanyInfoBean.getCompanyName()));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    
						}
					
    out.write(_jsp_string16, 0, _jsp_string16.length);
    out.print((bean.getDuration() >0 ? bean.getDuration() : "" ));
    out.write(_jsp_string17, 0, _jsp_string17.length);
    out.print((bean.getTransportPrice() >0 ? bean.getTransportPrice() : ""));
    out.write(_jsp_string18, 0, _jsp_string18.length);
    out.print((Constants.STATUS_VALID));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print(((bean.getStatus() == Constants.STATUS_VALID) ? "checked" : ""));
    out.write(_jsp_string19, 0, _jsp_string19.length);
    out.print((bean.getMemo()));
    out.write(_jsp_string20, 0, _jsp_string20.length);
    _caucho_expr_0.print(out, _jsp_env, false);
    out.write(_jsp_string21, 0, _jsp_string21.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/tms/route/RouteOfferManage.jsp"), 7639695723142069760L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/header.jsp"), 6309790488545470124L, false);
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
      _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${readOnly}");
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }
  private static com.caucho.el.Expr _caucho_expr_0;

  private final static char []_jsp_string19;
  private final static char []_jsp_string3;
  private final static char []_jsp_string13;
  private final static char []_jsp_string5;
  private final static char []_jsp_string8;
  private final static char []_jsp_string17;
  private final static char []_jsp_string21;
  private final static char []_jsp_string16;
  private final static char []_jsp_string9;
  private final static char []_jsp_string11;
  private final static char []_jsp_string20;
  private final static char []_jsp_string1;
  private final static char []_jsp_string7;
  private final static char []_jsp_string15;
  private final static char []_jsp_string2;
  private final static char []_jsp_string12;
  private final static char []_jsp_string10;
  private final static char []_jsp_string6;
  private final static char []_jsp_string4;
  private final static char []_jsp_string18;
  private final static char []_jsp_string0;
  private final static char []_jsp_string14;
  static {
    _jsp_string19 = " />\r\n			</p>\r\n			<p class=\"p_top_border\">\r\n				<em class=\"int_label\">\u5907\u6ce8\uff1a</em>\r\n				<textarea name=\"memo\" id=\"memo\" cols=\"60\" rows=\"3\" checkStr=\"\u5907\u6ce8;txt;false;;1000\">".toCharArray();
    _jsp_string3 = "/css/main.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string13 = "\" ".toCharArray();
    _jsp_string5 = "/js/common.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string8 = "\r\n			<li>\u4fee\u6539\u8def\u5f84\u62a5\u4ef7\u4fe1\u606f</li>\r\n			".toCharArray();
    _jsp_string17 = "\"\r\n					onkeyup='this.value=this.value.replace(/[^\\-?\\d.]/g,\"\")' checkStr=\"\u65f6\u6548;num;true;;20\" />\r\n			</p>\r\n			<p>\r\n				<em class=\"int_label\"><span>*</span>\u8fd0\u8d39\uff1a</em> <input type=\"text\" name=\"transportPrice\" id=\"transportPrice\" size=\"30\" maxlength=\"100\"\r\n					value=\"".toCharArray();
    _jsp_string21 = "';\r\n	if(readOnly=='true') {\r\n		disablePageElement();\r\n	}\r\n});\r\n</script>\r\n</html>".toCharArray();
    _jsp_string16 = "\r\n				</select>\r\n			</p>\r\n			<p>\r\n				<em class=\"int_label\"><span>*</span>\u65f6\u6548\uff1a</em> <input type=\"text\" name=\"duration\" id=\"duration\" size=\"20\" maxlength=\"20\" value=\"".toCharArray();
    _jsp_string9 = "\r\n			<li>\u65b0\u5efa\u8def\u5f84\u62a5\u4ef7\u4fe1\u606f</li>\r\n			".toCharArray();
    _jsp_string11 = "\" />\r\n		<div class=\"modify_form\">\r\n			<p>\r\n				<em class=\"int_label\"><span>*</span>\u8def\u5f84\u540d\u79f0\uff1a</em> <select name=\"routeId\" id=\"routeId\"  checkStr=\"\u8def\u5f84;num;true;;20\">\r\n					".toCharArray();
    _jsp_string20 = "</textarea>\r\n			</p>\r\n		</div>\r\n		<div class=\"div_center\">\r\n			<input type=\"submit\" name=\"button\" id=\"button\" class=\"modifysubbtn\" value=\"\u63d0\u4ea4\" />\r\n			<a href=\"javascript:history.back()\"><input type=\"button\" name=\"cancel\" id=\"cancel\" value=\"\u53d6\u6d88\" ></a>\r\n		</div>\r\n	</form>\r\n</body>\r\n<script>\r\n$(document).ready(function() {\r\n	var readOnly = '".toCharArray();
    _jsp_string1 = "\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>\u8def\u5f84\u62a5\u4ef7\u7ba1\u7406</title>\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string7 = "/images/back_forward.jpg\"/></a> \r\n		<ul class=\"art_nav\">\r\n			<li><a href=\"RouteOfferList.do\">\u8def\u5f84\u62a5\u4ef7\u5217\u8868</a>&nbsp;>&nbsp;</li>\r\n			".toCharArray();
    _jsp_string15 = "\r\n				</select>\r\n			</p>\r\n			<p>\r\n				<em class=\"int_label\"><span>*</span>\u7269\u6d41\u516c\u53f8\uff1a</em> <select name=\"companyId\" id=\"companyId\" checkStr=\"\u7269\u6d41\u516c\u53f8;num;true;;20\" >\r\n					".toCharArray();
    _jsp_string2 = "\r\n<link href=\"".toCharArray();
    _jsp_string12 = "<option value=\"".toCharArray();
    _jsp_string10 = "\r\n		</ul>\r\n	</div>\r\n	<form id=\"formData\" name=\"formData\" method=\"post\" action=\"RouteOfferManage.do\" onsubmit=\"javascript:return checkForm('formData');\">\r\n	    <input type=\"hidden\" name=\"currency\" id=\"currency\" value=\"".toCharArray();
    _jsp_string6 = "/js/ui.js\"></script>\r\n<script type=\"text/javascript\" src=\"../js/tms-common.js\"></script>\r\n</head>\r\n<body class=\"article_bg\">\r\n    <div class=\"article_tit\">\r\n		<a href=\"javascript:history.back()\" ><img src=\"".toCharArray();
    _jsp_string4 = "/js/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string18 = "\" onkeyup='this.value=this.value.replace(/[^\\-?\\d.]/g,\"\")' checkStr=\"\u8fd0\u8d39;num;true;;20\" />\r\n			</p>\r\n			<p>\r\n				<em class=\"int_label\">\u72b6\u6001\uff1a</em> <input type=\"checkbox\" name=\"status\" id=\"status\" value=\"".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string14 = "</option>\r\n					".toCharArray();
  }
}