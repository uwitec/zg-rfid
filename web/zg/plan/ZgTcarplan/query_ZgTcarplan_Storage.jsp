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
	<title><%=ZgTcarplan.TABLE_ALIAS%>查询</title>
	<%@ include file="/commons/meta.jsp"%>
	<%@ include file="/commons/jquery.jsp"%>
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTcarplan/listStorage.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function carPlanCreate() {
			var url = "${ctx}/zg/plan/ZgTcarplan/carPlanCreate.do";
			var dialogHeight = top.document.body.offsetHeight;
			var dialogWidth = top.document.body.offsetWidth;
			openDialog(url,dialogWidth,dialogHeight);
		}
		function carPlanSubmit() {
			var form = listFrame.document.getElementById("ec");
			var items = listFrame.document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag = true;
				}
			}
			if(flag) {
				form.action = "${ctx}/zg/plan/ZgTcarplan/storagePlanSubmit.do";
				form.submit();
			}else {
				alert("请选择要提交的计划！");
			}
		}
		
		function exportData(orderPlanType){
			document.forms[0].action = "${ctx}/frame/excel/sys/exportStorage.do?orderPlanType="+orderPlanType;
			document.forms[0].submit();
			document.forms[0].action ="${ctx}/zg/plan/ZgTcarplan/listStorage.do";
		}
		
		function query(){
			//if($("#s_carState").val()=='8'){
			//	$("#submitButton").attr("style","display:none")
			//}else{
			//	$("#submitButton").attr("style","display:")
			//}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTcarplan/listStorage.do',document.forms[0]))
				document.forms[0].submit();
		}
	</script>
</head>
<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTcarplan/listStorage.do" method="post" target="listFrame">
		<input type="hidden" name="s_type" value="${type}" />
		<input type="hidden" name="s_orderPlanType" value="${orderPlanType}" />
		<input type="hidden" name="bmClassId" value="<%=ZgTcarplan.BM_CLASS_ID%>" />
		<input type="hidden" name="s_equalBmClassIdQuery" value="${pageRequest.filters.equalBmClassIdQuery}" />
		<input type="hidden" name="s_inSubBmClassIdQuery" value="${pageRequest.filters.inSubBmClassIdQuery}" />
		<table id="query_table" class="query_panel querybar" cellpadding="0" cellspacing="0">
			<thead>
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
						<input type="text" dateFlag="true" value="${ createDate_start}" name="s_createDate_start" readonly = "true" />
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="180px">
						<input type="text" dateFlag="true" value="${ createDate_end}" name="s_createDate_end" readonly = "true" />
					</td>
					<td width="80px" class="label">订单状态：</td>
					<td>
							<select name="s_carState" id="s_carState">
							<option value="0" selected="selected">待领料</option>
							<option value="8">已领料</option>
						</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3"></td>
					<td class="label"><%=ZgTcarplan.ALIAS_CUID%>：</td>
					<td><input type="text" name="s_cuid" maxlength="40" /></td>
						<td class="label"><%=ZgTcarplan.ALIAS_CARUSER_DEPT_LABELCN%>：</td>
					<td><select id="groupSel" name="s_carUser_dept_Id"  onchange="changeGroup(this)">
						<option value="">请选择...</option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.cuid}">${role.labelCn}</option>
						</c:forEach>
						</select></td>
					<td class="label"><%=ZgTcarplan.ALIAS_CAR_USER%>：</td>
					<td><select id="userSel" name="s_carUser" >
						<option value="">请选择...</option>
						</select></td>
				<c:if test="${empty loginLgort }">
				<tr>
					<td class="label"><%=ZgTcarplan.ALIAS_STORAGE_LABLECN%>：</td>
					<td><input type="text" autocomplete="true" xtype="tree:1" id="storageId_label" columnNameLower="storageId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN" />
						<input type="hidden" id="storageId_value" name="s_storageId" /></td>
					<td class="label"></td>
					<td></td>
					<td class="label"></td>
					<td></td>
					</tr>
				</c:if>
				<c:if test="${not empty loginLgort }">
				<input type="hidden" id="storageId_value" name="s_storageId"  value="${loginLgort }" />
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
						<!-- 	<a href="javascript:carPlanSubmit()" id="submitButton"><span><img src="<%=iconPath%>/ico_search.gif" />提交已选</span></a> -->
							<a id="queryBtn" href="javascript:query()"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<c:if test="${type eq '1'}">
								<a href="javascript:" onclick="document.frames['listFrame'].location.href='<c:url value="/zg/plan/ZgTcarplan/carPlanCreate.do"/>'">
									<span><img src="<%=iconPath%>/ico_007.gif" />新单
									</span>
								</a>
							</c:if>
							<a href="javascript:exportData('${orderPlanType}')"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
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