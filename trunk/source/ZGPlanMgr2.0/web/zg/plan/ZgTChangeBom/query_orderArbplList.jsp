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
	<title>bom列表查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		$(function() {
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
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
				form.action = "${ctx}/zg/plan/ZgTBomManager/generateBom.do";
				form.submit();
			}else {
				alert("请选择相应的入库单！");
			}
		}
		
		function doLayout() {
			var maxHeight = top.dialogHeight;
			maxHeight = maxHeight.replace("px","")*1;
			var queryPanelH = maxHeight ;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<div id="_queryPanel">
<form action="${ctx}/zg/plan/ZgTBomManager/findOrderArbplList.do" method="post" target="listFrame">
		<input  type="hidden" id="s_lgort" name="s_lgort" value="${lgort }"/>
	<input  type="hidden" id="s_productType" name="s_productType" value="${productType }"/>
	<input  type="hidden" id="type" name="type" value="${type }"/>
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="80px" class="label">
						订单编号：
					</td>
					<td width="120px">
						<input type="text"   name="s_aufnr"  maxlength="40"/>
					</td >
					<td width="80px" class="label">
						生产线：
					</td>
					<td width="120px">
						<input type="text"   name="s_arbpl" maxlength="40" />
					</td >
				</tr>
			</thead>
			
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a href="javascript:"><span
									onclick="if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/findOrderArbplList.do',document.forms[0]));document.forms[0].submit();"><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
								&nbsp;<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
								</a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
</div>

</body>
</html>