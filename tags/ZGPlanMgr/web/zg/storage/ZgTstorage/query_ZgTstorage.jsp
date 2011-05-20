<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
	<title><%=ZgTstorage.TABLE_ALIAS%>查询</title>
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
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
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
		function targetFrame(url) {
			changePanel(document.getElementById("split_1"));
			document.frames('operateFrame').location.href = url;
		}
		function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorder/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorder/list.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function batchValidation(target,action,form) {
			var oldAction=form.action;
			form.target=target;
			form.action = action;
			form.submit();
			form.action = oldAction;
			return true;
		}
		
		function exportData(){
				document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
				document.forms[0].submit();
				document.forms[0].action = "${ctx}/zg/storage/ZgTstorage/list.do";
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/storage/ZgTstorage/list.do" method="post" target="listFrame">
		<input type="hidden" name="bmClassId" value="<%=ZgTstorage.BM_CLASS_ID%>"/>
		<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
		<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
		<input type="hidden" name="type" id="type"   value="${ type }"/>
		<input type="hidden" name="productType" id="productType" value="${ productType }"/>
		<input type="hidden" name="s_type" id="s_type"   value="${ type }"/>
		<input type="hidden" name="s_productType" id="s_productType" value="${ productType }"/>
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
						<input type="text" dateFlag="true" name="s_createDate_start" size="20" value="${createDate_start }" maxlength="40" readonly="true"/>
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="180px">
						<input type="text" dateFlag="true" name="s_createDate_end" size="20" value="${createDate_end }" maxlength="40"　readonly="true"/>
					</td>
					<td width="80px" class="label">订单状态：</td>
					<td>
						<select name="s_state">
							<option value="0">待调整</option>
							<option value="4">保存</option>
							<option value="8">已提交</option>
						</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3" ></td>
					<td class="label" width="80px"><%=ZgTstorage.ALIAS_ORDER_ID%>：</td>
					<td><input type="text"  name="s_aufnr" maxlength="40"/></td>
					<td class="label"><%=ZgTstorage.ALIAS_ARBPL%>：</td>
					<td>
						<input type="text" size="20" autocomplete="true" xtype="tree:1" id="arbpl_label" columnNameLower="arbpl" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" maxlength="40"/>
		   				<input type="hidden" id="arbpl_value" name="s_arbpl"/>
		   			</td>
					<td class="label"><%=ZgTstorage.ALIAS_LGORT%>：</td>
					<td>
						<input type="text" size="20" autocomplete="true" xtype="tree:1" id="lgort_label" columnNameLower="lgort" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" maxlength="40"/>
			   			<input type="hidden" id="lgort_value" name="s_lgort"/>
			   		</td>
				<tr>
					<td width="80px"  class="label"><%=ZgTstorage.ALIAS_DEPT_ID%>：</td>
					<td>
						<select id="groupSel" name="s_deptId"  onchange="changeGroup(this)">
							<option value="">请选择...</option>
							<c:forEach items="${roles}" var="role">
							<option value="${role.cuid}">${role.labelCn}</option>
							</c:forEach>
						</select>
					</td>
					<td class="label"><%=ZgTstorage.ALIAS_CREATOR_ID%>：</td>
					<td>
						<select id="userSel" name="s_carUser" >
							<option value="">请选择...</option>
						</select>
					</td>
					<td class="label"></td>
					<td></td>
				</tr>
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right" class="toolbar">
						<div class="toolbar">
							<a id="queryBtn" href="javascript:if(batchValidation('listFrame','${ctx}/zg/storage/ZgTstorage/list.do',document.forms[0]))document.forms[0].submit();"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a href="javascript:targetFrame('${ctx}/zg/storage/ZgTstorage/create.do?type=${type }&productType=${productType }')"><span><img src="<%=iconPath%>/addition.gif" />新单</span></a>
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