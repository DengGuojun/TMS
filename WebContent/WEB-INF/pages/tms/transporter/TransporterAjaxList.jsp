<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.tms.config.*"%>
<%
	String tmsHost = "http://" + request.getServerName();
	int tmsPort = request.getServerPort();
	if (tmsPort > 0) {
		tmsHost = tmsHost + ":" + tmsPort;
	}
%>
function getTransporterNameList(transporterType, transporterId, defaulValue){ 
    jQuery.ajax({ 
        async: false, url: "<%=tmsHost%>/transporter/TransporterInfoJsonList.do", 
	    type: "GET", 
	    dataType: 'jsonp',  
	    jsonp: '<%=TmsConsoleConfig.JSONP_CALLBACK%>', 
	    data: {transporterType : jQuery("#"+transporterType).val()}, 
	    timeout: 5000, 
	    contentType: "application/json;utf-8",
	    success: function (result) { 
	    	processTransporterNameSelect(result, transporterId, defaulValue); 
	    }, 
	    error: function (jqXHR, textStatus, errorThrown) { 
	        console.error(textStatus); 
	    } 
	});
}

function processTransporterNameSelect(data, transporterId, defaulValue){
	  jQuery("#"+transporterId)[0].length = 0;
	  jQuery("#"+transporterId).append("<option></option>");
	  if(data.length > 0){
	    for(var i=0;i < data.length;i++){
	      jQuery("#"+transporterId).append("<option value='"+data[i].transporterId+"'>"+data[i].transporterName+"</option>");
	    }
	    if(defaulValue != ""){
	      jQuery("#"+transporterId).val(defaulValue);
	    }
	  }  
	}