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
	<title>审核人列表查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
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
		});
		
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
<form action="${ctx}/zg/plan/ZgTorderPlanForBatch/findAuditingList.do" method="post" target="listFrame">
	<table class="formTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="80px" class="label">
						姓名：
					</td>
					<td width="120px">
						<input type="text"   name="s_auditingName" />
					</td >
					<!-- <td width="80px" class="label">
						部门：
					</td>
					<td width="120px">
						<input type="text"   name="s_depertmentName" />
					</td > -->
						<td  width="180px" >
							<div class="button" >
								<a href="javascript:"><span
									onclick="if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderPlanForBatch/findAuditingList.do',document.forms[0]));document.forms[0].submit();"><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
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