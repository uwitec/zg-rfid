<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
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
	<base target="_self"/>
	<title>负责人列表查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			if(true){
				$("form:first").submit();//一进这个页面马上提交
			}
			try{
				if(fitLayout) {
					$(window).bind("load",fitLayout);
				}
				if(initSplit) {
					$(window).bind("load",initSplit);
				}
				doLayout();
			}catch(e){}
			
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
							$("#s_departmentName").val(label);
							$("#s_departmentId").val(id);
						}
					});
				}
			}
		});
		
		function doLayout() {
			document.getElementById("div_window").style.height = 420;
		}
		
		var groupList = [];
		<c:forEach items="${roles}" var="role">
		groupList.push({value:'${role.cuid}',labelCn:'${role.labelCn}'});
		</c:forEach>
		
		function generate(){
			var form = listFrame.document.getElementById("ec");
			var items = listFrame.document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag = true;
				}
			}
			if(flag) {
				form.action = "${ctx}/zg/storage/ZgTstoragebom/generateBom.do";
				form.submit();
			}else {
				alert("请选择相应的入库单！");
			}
		}
		
		
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="div_window">
<form action="${ctx}/frame/sys/FwOrganization/findPrincipalsList.do" method="post" target="listFrame">
	<table class="formTable" cellpadding="0" cellspacing="0">
		<input type="hidden" name="s_employeesId" value="${employeesId }"/>
			<thead>
				<tr class="querybar">
					<td width="50px" class="label">
						姓名：
					</td>
					<td width="50px">
						<input type="text"   name="s_principalName" />
					</td >
					<td width="80px" class="label">
						部门名称：
					</td>
					<td>
						<input type="text" size="15" maxlength="15" autocomplete="true"
							xtype="tree:1" id="s_departmentName" name="s_departmentName" columnNameLower="parentOrgId"
							bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" class="" style=""/>
						<input type="hidden" id="s_departmentId" name="s_departmentId" />
					</td>
						<td  width="240px">
							<div class="button" >
								<a href="javascript:"><span
									onclick="if(batchValidation('listFrame','${ctx}/frame/sys/FwOrganization/findPrincipalsList.do',document.forms[0]));document.forms[0].submit();"><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="window.frames['listFrame'].returnAndClose()"><img
											src="<%=iconPath%>/true.gif" />确定</span>
								</a>
							</div>
						</td>
						<td width="*"></td>
					</tr>
			</thead>
		</table>
</form>
<iframe src=""  name="listFrame" frameborder="0" style="overflow-x:hidden" width="100%" height="100%" align="top" />
</div>
</body>
</html>