<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
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
	<title>退单查询</title>
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTback/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function exportData(){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
<div id="_queryPanel">
<form action="${ctx}//zg/plan/ZgTback/list.do" method="post" target="listFrame">
	<input type="hidden" name="bmClassId" value="<%=ZgTback.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="6%" class="tdLabel"><%=ZgTback.ALIAS_BATCH_DATE%>：</td>
			   <td width="30%">
		   			<input type="text" dateFlag="true" name="s_batchDate_start" size="30" readonly="true"/>
		   			至　<input type="text" dateFlag="true" name="s_batchDate_end" size="30" readonly="true"/>
			   </td>
			    <td width="5%" class="tdLabel"><%=ZgTback.ALIAS_BATCH_NO%>：</td>
			   <td width="20%">
		   			<input type="text" name="s_batchNo" size="30"/>
			   </td>
			   <td  width="5%" class="tdLabel"><%=ZgTback.ALIAS_AUFNR%>：</td>
			   <td width="20%">
		   			<input type="text" name="s_aufnr" size="30"/>
			   </td>
			   
			</tr>
			</thead>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a  href="javascript:"><span id="queryBtn" onclick="if(batchValidation('listFrame','${ctx}/zg/plan/ZgTback/list.do',document.forms[0]))document.forms[0].submit();"><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:"><span onclick="document.forms[0].reset()"><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
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