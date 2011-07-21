<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=FwEmployee.TABLE_ALIAS%>编辑</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/FwEmployeeDwrAction.js"></script>
	<script type="text/javascript">
		
		function saveEmployee() {
			if($("form[validator]").valid())
			{
				document.forms[0].submit();
			}
		}
		
		function initMsg(){
			if("${flag}"=="success"){
				alert("操作成功");
			
			}
		}
		
	function initLoad(){
	  	var orgIds='${orgIds}';
		var orgNames="${orgNames}";
		var allName=orgNames.split(',');
		var allId=orgIds.split(',');
	
		if(orgIds!=""){
			for(var v=0;v<allId.length;v++){
				addOptionToSelect("orgIds",allId[v],allName[v]);
			}
		}
	}
	</script>
</head>
<body onload="initMsg();initLoad();">
<form action="${ctx}//frame/sys/FwEmployee/updateSelfIno.do" method="post" validator="true">
<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
								<a href="javascript:saveEmployee()"><span><img src="<%=iconPath%>/true.gif" />修改</span></a>
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
			<%@ include file="form_include_self_FwEmployee.jsp" %>
					</tbody>
				</table>
			</div>
			
</form>
</body>
</html>