<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title>领料计划排序调整qweqwe</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
	
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/FwOrganizationDwrAction.js"></script>
	
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		var curNum=0; 
		var curDate="<fmt:formatDate value='${curDate}' pattern="yyyy-MM-dd" />";
		var planType="${pageRequest.filters.planType}";
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		      
		}
		function doLayout() {
			//var maxHeight = top.dialogHeight;
			//var maxWidth = top.dialogWidth;
			var maxHeight = '530px';
			var maxWidth='900px';
			maxHeight=maxHeight.replace("px","");
			maxWidth=maxWidth.replace("px","");
			document.getElementById("_queryPanel").style.height = maxHeight + 'px';
			var tableInfo2 = document.getElementById("tableInfo2").offsetHeight;
			var listFrameH = maxHeight - tableInfo2-36;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
			document.getElementById("listFrame").style.width = maxWidth + 'px';
		}
	
		
	</script>
	
</head>

<body>
<div id="_queryPanel">
    
        <div class="tabpage" id="tab">
        <table id="tableInfo2" width="100%"><tr><td width="100%">
        <tr><td>
        
          <form id="formPsbh" action="${ctx}/zg/plan/ZgTorderPlan/list.do" method="post" target="listFrame">
			<input type="hidden" name="s_planType" value="${param.planType}"/>
			<input type="hidden" name="s_arbpl" value="${param.arbpl }"/>
			<input type="hidden" name="s_pxDate" value="${param.pxDate}"/>
			<input type="hidden" name="viewModel" value="${param.viewModel }"/>
           	<input type="hidden" id="s_isCurDay" name="s_isCurDay" value="${param.isCurDay }" />
           	<input type="text" id="s_px" name="s_px" value="1" />
           	<input type="hidden" id="psbhChange" name="psbhChange" value="1" />
            </form>
        </td></tr></table>
      
        <div class="main">
            	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
        </div>
    </div>
</div>
</body>
</html>