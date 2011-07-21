<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=FwRole.TABLE_ALIAS%>查询</title>
	<title>订单查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			initSplit();
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
			var splitH = document.getElementById("split_1").offsetHeight;
			var queryPanelH = maxHeight - splitH;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
			var operateFrameH = queryPanelH;
			document.getElementById("operateFrame").style.height = operateFrameH + 'px';
			document.getElementById("_operatePanel").style.height = operateFrameH + 'px';
		}
		function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/frame/sys/FwRole/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function delRole(url){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的角色！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
       			
				document.frames('listFrame').document.forms[0].action = url;
				document.frames('listFrame').document.forms[0].submit();
   			 }
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/frame/sys/FwRole/list.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=FwRole.BM_CLASS_ID%>" />
			<input type="hidden" name="s_equalBmClassIdQuery"
				value="${pageRequest.filters.equalBmClassIdQuery}" />
			<input type="hidden" name="s_inSubBmClassIdQuery"
				value="${pageRequest.filters.inSubBmClassIdQuery}" /><table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td class="label" width="80px">
						角色名称：
					</td>
					<td>
						<input type="text" name="s_labelCn" size="30" maxlength="40"/>
					</td>
					<td class="label" width="80px">
						角色描述：
					</td>
					<td>
						<input type="text" name="s_note" size="30" maxlength="40" />
					</td>
				</tr>
			</thead>
			
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a  href="javascript:"><span id="queryBtn"
									onclick="batchValidation('listFrame','${ctx}/frame/sys/FwRole/list.do',document.forms[0]);document.forms[0].submit()"><img
											src="<%=iconPath%>/ico_search.gif" />查询</span>
								</a>
							&nbsp;	<a href="javascript:"><span
									onclick="document.forms[0].reset()"><img
											src="<%=iconPath%>/ico_007.gif" />重置</span>
								</a>
							&nbsp;		<a href="javascript:"
									onclick="targetFrame('${ctx}/frame/sys/FwRole/create.do?id=${id}')">
									<span><img src="<%=iconPath%>/addition.gif" />新增 </span> </a>
							&nbsp;		<a href="javascript:"
									onclick="delRole('${ctx}/frame/sys/FwRole/delete.do')"> <span><img
											src="<%=iconPath%>/ico_005.gif" />删除 </span> </a>
							</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe id="operateFrame" src="" name="operateFrame" frameborder="0" width="100%" scrolling="no"></iframe>
</div>
</body>
</html>