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
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<style>
		html,body{overflow: hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript">
		$(function() {
			init();
			selectedCheck();
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTcarplan/list.do',document.forms[0])) {
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
				form.action = "${ctx}/zg/plan/ZgTcarplan/carPlanSubmit.do";
				form.submit();
			}else {
				alert("请选择要提交的计划！");
			}
		}
		function exportData(){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
			document.forms[0].submit();
			document.forms[0].action = "${ctx}/zg/plan/ZgTcarplan/list.do";
		}
		function query(){
			var carStateValue=$("#s_carState").val();
			if(carStateValue=='0'||carStateValue=='4'){
				$("#deleteCarPlanButton").attr("style","display:''")
			}else{
				$("#deleteCarPlanButton").attr("style","display:none")
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTcarplan/list.do',document.forms[0]))
				document.forms[0].submit();
		}
		function deleteCarPlan(){
			var form = listFrame.document.getElementById("ec");
			var items = listFrame.document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag = true;
					break;
				}
			}
			if(flag) {
				if(confirm("确认删除所选装车计划单！")){
					form.action="${ctx}/zg/plan/ZgTcarplan/deleteCarPlan.do";
					form.submit();
				}
			}else {
				alert("请选择所要删除的对象！");
			}
		}
		function selectedCheck(){
			var carStateValue=$("#s_carState").val();
			if(carStateValue=='0'||carStateValue=='4'){
				$("#deleteCarPlanButton").attr("style","display:''")
			}else{
				$("#deleteCarPlanButton").attr("style","display:none")
			}
		}
	</script>
</head>

<body>
<div id="_queryPanel">
<form action="${ctx}/zg/plan/ZgTcarplan/list.do" method="post" target="listFrame">
	<input type="hidden" name="s_type" value="${type}"/>
	<input type="hidden" name="s_orderPlanType" value="${orderPlanType}"/>
	<input type="hidden" name="bmClassId" value="<%=ZgTcarplan.BM_CLASS_ID%>"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
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
					<input type="text" dateFlag="true" readonly = "true" value="${ createDate_start}"  name="s_createDate_start" />
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="180px">
					<input type="text" dateFlag="true" readonly = "true" value="${ createDate_end}" name="s_createDate_end" />
					</td>
					<td width="80px" class="label">订单状态：</td>
					<td>
					<select name="s_carState" id="s_carState">
						<option value="" >全部</option>
						<option value="0" selected="selected">待调整</option>
						<option value="4">保存</option>
					</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3"></td>
					<td class="label"><%=ZgTcarplan.ALIAS_CUID%>：</td>
					<td><input type="text" name="s_cuid"/></td>
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
					
				<tr>
					<td class="label"><%=ZgTcarplan.ALIAS_STORAGE_LABLECN%>：</td>
					<td><input type="text" autocomplete="true" xtype="tree:1" id="storageId_label" columnNameLower="storageId" bmClassId="FW_ORGANIZATION" column="m.t0_LABEL_CN"/>
		   			<input type="hidden" id="storageId_value" name="s_storageId"/></td>
					<td class="label"></td>
					<td></td>
					<td class="label"></td>
					<td></td>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
					<a href="javascript:"><span onclick="query()"><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
					<a href="javascript:"><span onclick="carPlanSubmit()"><img src="<%=iconPath%>/true.gif" />提交已选</span></a>
					<a href="javascript:"><span onclick="document.forms[0].reset()"><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
					<a id="deleteCarPlanButton" style="display:none" href="javascript:deleteCarPlan()"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
					<c:if test="${type eq '1'}">
						<a href="javascript:targetFrame('${ctx }/zg/plan/ZgTcarplan/carPlanCreate.do?orderPlanType=${orderPlanType}')"><span onclick="document.forms[0].reset()"><img src="<%=iconPath%>/addition.gif" />新单</span></a>
					</c:if>
					<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
</form>
	<iframe src="" id="listFrame" name="listFrame" frameborder="0" width="100%" height="100%" align="top" scrolling="no" ></iframe>
</div>
<div id="split_1" class="splitbar off"></div>
<div id="_operatePanel" class="operatorPanel" style="display: none;">
	<iframe src="" id="operateFrame" name="operateFrame" frameborder="0" width="100%" align="top" scrolling="no" ></iframe>
</div>
</body>
</html>