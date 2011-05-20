<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgMateriel.TABLE_ALIAS%>编辑</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script src="${ctx}/dwr/interface/SubmitMaterielAndMvirtualorgDwrAction.js" type=""></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgMateriel.TABLE_ALIAS%>EditDiv").accordion();
			if(initLayout) {
				$(window).bind("load",initLayout);
				$(window).bind("resize",initLayout);
			}
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			$("input[dateFlag=true]").datepicker({
				showOtherMonths: true,
				selectOtherMonths: true,
				dateFormat:"yy-mm-dd"
			});
		});
		
		function saveMateriel(){
			
			var materielCuid=document.getElementById("materielCuid").value;
			var materielId=document.getElementById("materielId").value;
			
			var storageId=document.getElementById("storageId").value;
			var materielName=document.getElementById("materielName").value;
			
			var materielStorageId=document.getElementById("materielStorageId").value;
			
			var materielVirtualorgId=document.getElementById("materielVirtualorgId").value;
			var allVirtualorgId=document.getElementById("orgId").value;
			
			DWREngine.setAsync(false);
			SubmitMaterielAndMvirtualorgDwrAction.updateMaterielAndInsertZgMaterielVirtualorg(materielCuid, materielId,
			 storageId,
			 materielName,
			 materielStorageId,
			 materielVirtualorgId,
			 allVirtualorgId,function(data){
			 	alert(data);
			 });
			if(parent.doQueryForMateriel)parent.doQueryForMateriel()
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/materiel/ZgMateriel/updateMaterielAndInsertZgMaterielVirtualorg.do" method="post" validator="true">
			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:saveMateriel()"><span><img
												src="<%=iconPath%>/true.gif" />提交</span>
									</a>
									<a href="javascript:if(parent.doQueryForMateriel)parent.doQueryForMateriel()"><span><img
												src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
								</div>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			
			<div class="form_panel">
				<table class="form_table" cellpadding="0" cellspacing="1"
					style="margin-top: 3px;">
					<tbody attr="tbody_1">
						<%@ include file="form_include_ZgMateriel.jsp" %>
					</tbody>
				</table>
			</div>
</form>
</body>
</html>