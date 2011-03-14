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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderTemp/list.do',document.forms[0])) {
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
	<form action="${ctx}/zg/plan/ZgTorderTemp/list.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTorderTemp.BM_CLASS_ID%>" />
		<input type="hidden" name="s_equalBmClassIdQuery" value="${pageRequest.filters.equalBmClassIdQuery}" />
		<input type="hidden" name="s_inSubBmClassIdQuery" value="${pageRequest.filters.inSubBmClassIdQuery}" />
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead class="querybar">
				<tr>
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td width="80px" class="label">
						开始时间：
					</td>
					<td width="180px">
						<input type="text" dateFlag="true" value="${ pcdat_start}"  name="s_pcdat_start" />
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="180px">
						<input type="text" dateFlag="true" value="${ pcdat_end}" name="s_pcdat_end" />
					</td>
					<td width="80px" class="label">操作类型：</td>
					<td>
						<select name="s_operateType">
							<option value="">全部</option>
							<option value="1" selected="selected">新增</option>
							<option value="2">修改</option>
						</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3"></td>
					<td class="label">订单编号：</td>
					<td><input type="text" name="s_labelCn" /></td>
					<td class="label">生产线：</td>
					<td><input type="text" name="s_arbpl" /></td>
					<td class="label">客户机型：</td>
					<td><input type="text" name="s_maktx2" /></td>
				<tr>
					<td class="label">销售订单号：</td>
					<td><input type="text" name="s_kdauf" /></td>
					<td class="label">生产厂：</td>
					<td><input type="text" name="s_plant" /></td>
					<td class="label">公司机型：</td>
					<td><input type="text" name="s_maktx1" /></td>
				<tr>
					<td class="label">物料号：</td>
					<td><input type="text" name="s_matnr" /></td>
					<td class="label"></td>
					<td></td>
					<td class="label"></td>
					<td></td>
				</tr>
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a href="javascript:"><span onclick="if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderTemp/list.do',document.forms[0]))document.forms[0].submit();"><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
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