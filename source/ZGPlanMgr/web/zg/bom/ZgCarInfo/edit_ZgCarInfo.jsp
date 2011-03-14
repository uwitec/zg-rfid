<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgCarInfo.TABLE_ALIAS%>编辑</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script src="${ctx}/dwr/interface/ZgCarInfoDwrAction.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgCarInfo.TABLE_ALIAS%>EditDiv").accordion();
		});
		
		function submitCheck(){
				if($("form[validator]").valid())
			{
				var code = document.getElementById("model.code").value;
				var cuid = document.getElementById("cuid").value;
				
				ZgCarInfoDwrAction.CheckEdit(code,cuid,function(data){
					if(data == true){
						document.forms[0].submit();
					}else {
						alert("车型编号已存在");
						return;
					}
				});	
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form action="${ctx}/zg/bom/ZgCarInfo/update.do" method="post"
			validator="true">

			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">
								<div class="button" style="text-align: left;">
								<a href="javascript:submitCheck()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
									<a href='${ctx}/zg/bom/ZgCarInfo/query.do'><span><img
												src="<%=iconPath%>/ico_007.gif" />返回</span> </a></div>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			<div class="form_panel">
				<table class="form_table" cellpadding="0" cellspacing="1"
					style="margin-top: 3px;">
					<tbody attr="tbody_1">
						<%@ include file="form_include_ZgCarInfo.jsp" %>
					</tbody>
				</table>
			</div>

		</form>

</body>

</html>