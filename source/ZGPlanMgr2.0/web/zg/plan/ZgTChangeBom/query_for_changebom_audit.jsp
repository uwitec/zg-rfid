<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_noexpand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderPlan.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<style>
		body,html {overflow:hidden}
	</style>

	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listForChangeLead.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function query(){
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listForChangeLead.do',document.forms[0]))document.forms[0].submit();
			document.forms[0].submit();
			
		}
		
		function exportData(){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
			document.forms[0].submit();
		}
		
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTBomManager/listForChangeAudit.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTorderPlan.BM_CLASS_ID%>"/>
		<input type="hidden" name="s_planType" value="${pageRequest.filters.planType}"/>
		<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
		<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		
		
		<table id="query_table" class="query_panel" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="querybar">
					<td width="20px" align="center">
						<label class="expandbtn">
							<img src="<%=expandIcon %>" for="tbody_1"/>
						</label>
					</td>
					<td width="80px" class="label">单据类型：</td>
					<td  width="80px">
						<select name="s_type" id="s_type" >
						<option value="">	全部</option>
						    <option value="CHANGE">	换料</option>
							<option value="BACK" >退料</option>
							<option value="RENEW" >补领料</option>
						</select>
					</td>
					<td width="80px" class="label">
						开始时间：
					</td>
					<td width="120px">
						<input type="text" dateFlag="true" id="s_createDate_start" name="s_createDate_start" value="${planDate_start }" size="20"  maxlength="40" readonly="true"/>
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="120px">
						<input type="text" dateFlag="true" id="s_createDate_end" name="s_createDate_end" value="${planDate_end }" size="20"  maxlength="40"　readonly="true"/>
					</td>
				
					
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar expand">
				<tr>
				
					<td width="20px" align="center" rowspan="3" ></td>
					<td width="80px" class="label">单据状态：</td>
					<td width="80px">
				<select name="s_state" id="s_state">
					<option value="-4">待品质部审核</option>
				</select>
					</td>
						<td class="label" width="80px">生产订单：</td>
						<td><input type="text" size="20" name="s_aufnr"/></td>
						<td class="label">生产厂：</td>
					<td>
					<select name="s_plant">
					<option value="">全部</option>
						<c:forEach items="${plantList}" var="item">
							<option value="${item.PLANTID }">${item.PLANTID }</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				
				<tr>
				
					<td width="80px" class="label">生产线：</td>
					<td width="80px">
						<select name="s_arbpl">
					<option value="">全部</option>
					<c:forEach items="${arbplList}" var="item">
						<option value="${item.cuid }">${item.cuid }</option>
					</c:forEach>
					</select>
					</td>
						<td width="80px" class="label">
					</td>
					<td width="120px">
					</td>
					<td width="80px" class="label"></td>
					<td width="120px">
					</td>
				</tr>
					
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							<a href="javascript:query()" id="queryBtn"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
		
		
		
	</form>
	<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no" ></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe id="operateFrame" src="" name="operateFrame" frameborder="0" width="100%" align="top" scrolling="no"></iframe>
</div>
</body>
</html>
