<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>" />
	<title><%=FwOrganization.TABLE_ALIAS%>新增</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>

	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=FwOrganization.TABLE_ALIAS%>CreateDiv").accordion();
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
				if(xtype.indexOf("tree:") != -1){
					var templateId = xtype.substring(xtype.indexOf(":")+1);
					$("#"+columnNameLower+"_label").attr("readonly",true);
					var sFeatures="dialogHeight: 400px;dialogWidth:300px";
					$(o).bind("click",function() {
					
						var returnValue = window.showModalDialog(ctx+"/explorer/tree/commonTree.jsp?templateId="+templateId,'',sFeatures);
						if(returnValue) {
						
							var id = returnValue.id;
							var label = returnValue.label;
							var depth = returnValue.depth;
							$("#"+columnNameLower+"_label").val(label);
							$("#"+columnNameLower+"_value").val(id);
							$("#levelNum").val(depth+1);
						}
					});
				}
			}
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
		function orgSubmit(){
			if($("form[validator]").valid())
			{
				document.forms[0].submit();
			}
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form id="orgForm" action="${ctx}/frame/sys/FwOrganization/save.do" method="post" validator="true">
	<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">
						
							<div class="button" style="text-align: left;">
								<a href="javascript:orgSubmit()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span> </a>				
							&nbsp;	<a href="javascript:"><span
									onclick="if(parent.doQuery)parent.doQuery()"><img
											src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
							</div>
						</td>
					</tr>
				</thead>
			</table>
</div>
		<div>
		<div class="form_panel">
	<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top:3px;">
		<tbody attr="tbody_1">
					<%@ include file="form_include_FwOrganization.jsp" %>
		</tbody>
		</table>
		</div>
</form>
</body>
</html>