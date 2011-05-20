<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<base target="_parent" />
	<title><%=ZgTorder.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("#<%=ZgTorderPlanbom.TABLE_ALIAS%>EditDiv").accordion();
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
			initAutoCompleteForLogot("${ctx}/autoComplate/findRelationData.do");
		});
		
		function submitForm(){
			//alert(window.opener.location.href) ;
			//document.forms[0].submit();
			//window.close();
		}
		
		function initAutoCompleteForLogot(url) {
	var a = $("input[autocomplete]");
	for(var i = 0 ; i < a.length; i++) {
		var o = a[i];
		var xtype = $(o).attr("xtype");
		var columnNameLower = $(o).attr("columnNameLower");
		var bmClassId = $(o).attr("bmClassId");
		var column = $(o).attr("column");
		$("#"+columnNameLower+"_label").css("cursor","pointer");
		var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
		$("#"+columnNameLower+"_value").after(img);
		if(xtype == 'drm-complex-select') {
			var source = url+"?bmClassId="+bmClassId+"&column="+column;
			$(o).autocomplete_ex({
				source: source,
				minLength: 2,
				select: function(event, ui) {
					$("#"+columnNameLower+"_value").val(ui.item.value);
				}
			});
		}else if(xtype.indexOf("tree:") != -1){
			var templateId = xtype.substring(xtype.indexOf(":")+1);
			$("#"+columnNameLower+"_label").attr("readonly",true);
			var sFeatures="dialogHeight: 400px;dialogWidth:300px";
			$(o).bind("click",function() {
				var returnValue = window.showModalDialog(ctx+"/explorer/tree/commonTree.jsp?templateId="+templateId,'',sFeatures);
				if(returnValue) {
					var id = returnValue.id;
					var label = returnValue.label;
					$("#"+columnNameLower+"_lableCn").val(label);
					$("#"+columnNameLower+"_value").val(id);
				}
			});
		}else if(xtype.indexOf("url:") != -1) {
			var url = xtype.substring(xtype.indexOf(":")+1);
			$(o).bind("click",function() {
				var sFeatures="dialogHeight: 400px;dialogWidth:300px";
				if(url.indexOf("http://") == -1) url = ctx+"/"+url;
				var returnValue = window.showModalDialog(url,'',sFeatures);
				if(returnValue) {
					var id = returnValue.id;
					var label = returnValue.label;
					$("#"+columnNameLower+"_label").val(label);
					$("#"+columnNameLower+"_value").val(id);
				}
			});
		}
	}
}
		
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/plan/ZgTorderPlanbomForBatch/updateOrderPlanForBatch.do" method="post" validator="true">
<table class="formTable">
		<tr class="toolbar">
			<td width="220px" style="padding-left:10px">
				<input type="submit" onclick="submitForm()" value="保存"  class="commonButton queryButton"/>
				<input type="button" value="退出" class="commonButton queryExButton" 
					onclick="window.close()"/>
			</td>
		</tr>
	</table>
	<div id="<%=ZgTorderPlanbom.TABLE_ALIAS%>EditDiv">
		<div>
		<table class="formTable" >
			<%@ include file="form_include_ZgTorderPlanbom_ForBatch.jsp" %>
		</table>
		</div>
	</div>
</form>
</body>
</html>