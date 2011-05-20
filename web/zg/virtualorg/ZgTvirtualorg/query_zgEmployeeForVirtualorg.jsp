<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*"%>
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
	<title>领料人列表</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
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
		});
		
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
		
		function doLayout() {
			document.getElementById("div_window").style.height = 420;
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<div id="div_window">
<form action="${ctx}/zg/virtualorg/ZgTvirtualorg/employeeForVirtualorg.do" method="post" target="listFrame">
	<input type="hidden" name="s_virtualorgId" value="${virtualorgId}"/>
	<input type="hidden" name="s_userId" id="s_userId" />
	<input type="hidden" name="s_userSex" id="s_userSex" value="0"/>
	<table class="query_panel" cellpadding="0" cellspacing="0" name="query_table" id="query_table">
					<tr>
						<td class="label">
							姓名：
						</td>
						<td>
							<input type="text" name="s_userName" id="s_userName" />
						</td >
						<td class="label">
							部门名称：
						</td>
						<td>
							<select name="s_userDepartmentId" id="s_userDepartmentId">
							<option value="">请选择...</option>
								<c:forEach items="${roles}" var="role">
									<option value="${role.cuid}">${role.labelCn}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<div class="button" >
								<a href="javascript:"><span
									onclick="if(batchValidation('listFrame','${ctx}/zg/virtualorg/ZgTvirtualorg/employeeForVirtualorg.do',document.forms[0]));document.forms[0].submit();"><img
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
		</table>
</form>
<iframe src=""  name="listFrame" id="listFrame" frameborder="0" style="overflow-y:auto;overflow-x:hidden" width="100%" height="100%" align="top"/>
</div>
</body>
</html>