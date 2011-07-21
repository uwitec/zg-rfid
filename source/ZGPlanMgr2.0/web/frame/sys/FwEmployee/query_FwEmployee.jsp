<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=FwEmployee.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>

	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<style>
		body,html {overflow:hidden}
	</style>
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
			if(batchValidation('listFrame','${ctx}/frame/sys/FwEmployee/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function delEmployee(url){
			var items = document.frames('listFrame').document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择要删除的员工！");
				return;
			}
			if (confirm('确定执行[删除]操作?')){
				document.frames('listFrame').document.forms[0].action = url;
				document.frames('listFrame').document.forms[0].submit();
			}
			
		}
		function exportData(type){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do?exportType="+type;
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/frame/sys/FwEmployee/list.do?orgId=${orgId}";
		}
	</script>
</head>

<body>
<div id="_queryPanel">
<form action="${ctx}/frame/sys/FwEmployee/list.do?orgId=${orgId}" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=FwEmployee.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden" name="s_orgId" value="${orgId}"/>
		<table  id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="20px" align="center">
							<label class="expandbtn">
								<img src="<%=expandIcon %>" for="tbody_1"/>
							</label>
					</td>
				   <td class="label"  width="80px"><%=FwEmployee.ALIAS_EFFECT_TIME%>：</td>
				   <td colspan="3">
			   			<input type="text" dateFlag="true" name="s_effectTime_start" readonly ="true"/>
			   			至　<input type="text" dateFlag="true" name="s_effectTime_end" readonly ="true"/>
				   </td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" rowspan="2"></td>
				   <td class="label"><%=FwEmployee.ALIAS_LABEL_CN%>：</td>
				   <td colspan="3">
			   			<input type="text" maxlength="4" name="s_labelCn"/>
				   </td>
				</tr>
				<tr>
					<td class="label"  width="80px">
						<%=FwEmployee.ALIAS_USER_ID%>：
					</td>	
					<td colspan="3">
				   		<input type="text" maxlength="10" name="s_userId"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="toolbar">
								<a id="queryBtn" href="javascript:batchValidation('listFrame','${ctx}//frame/sys/FwEmployee/list.do?orgId=${orgId}',document.forms[0]);document.forms[0].submit()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
								<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
								<a href="javascript:targetFrame('${ctx}/frame/sys/FwEmployee/create.do?orgId=${orgId}')"><span><img src="<%=iconPath%>/addition.gif" />新增</span></a>
								<a href="javascript:delEmployee('${ctx}/frame/sys/FwEmployee/delete.do?orgId=${orgId}')"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
								<a href="javascript:exportData(1)"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
								<a href="javascript:exportData(2)"><span><img src="<%=iconPath%>/page_excel.png" />导出全部</span></a>
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