<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderPlanForBatch/listForBatch.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		function query(){
			var planStat= $("#s_state").val();
			if(planStat=='2'||planStat=='4'){
				$("#deletePlanButton").attr("style","display:''")
			}else{
				$("#deletePlanButton").attr("style","display:none");
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTorderPlanForBatch/listForBatch.do',document.forms[0]))document.forms[0].submit();
			document.forms[0].submit();
			
		}
		//思路：在提交审核之前要先选择‘审核人’--弹出模式窗体
		function orderPlanSubmit(obj) {
			obj.disabled=true;
			var form = listFrame.document.getElementById("ec");
			var items = listFrame.document.getElementsByName("items");
			
			//这里用来判断是否有打钩的
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag = true;
				}
			}
			
			var batch_state=listFrame.document.getElementById("stateData").value;//批量状态
			//如果是选择了‘待审核’的话
			if(batch_state=="1"&&flag==true){
				alert("待审核状态的不能再度提交审核了！");
				obj.disabled=false;
				return;
			}
			//如果是选择了‘已提交’的话
			if(batch_state=="8"&&flag==true){
				alert("已提交状态的不能再度提交审核了！");
				obj.disabled=false;
				return;
			}
			
			if(flag) {
				//弹出选择‘审核’人的窗体  url,'',sFeatures
				var sFeatures="dialogHeight: 500px;dialogWidth:760px";
				var firstShowQueryUrl="${ctx}/zg/plan/ZgTorderPlanForBatch/queryForAuditingPeople.do";
				var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
				//returnValue这个是用来取决是否能够提交的returnValue==undefined||returnValue==''
				if(returnValue){
					form.action = "${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlan.do?userId="+returnValue.userId;
					form.submit();
				}
			}else {
				alert("请选择要提交的计划！");
			}
			obj.disabled=false;
		}
		function exportData(){
			document.forms[0].action = "${ctx}/frame/excel/sys/export.do";
			document.forms[0].submit();
		}
		function deletePlan(){
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
				if(confirm("确认删除所选批量领料计划单！")){
					form.action="${ctx}/zg/plan/ZgTorderPlanForBatch/deletePlan.do";
					form.submit();
				}
			}else {
				alert("请选择所要删除的对象！");
			}
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTorderPlanForBatch/listForBatch.do" method="post" target="listFrame">
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
					<td width="80px" class="label">
						开始时间：
					</td>
					<td width="180px">
				<input type="hidden" id="beforeTime_oldValue" value="${ planDate_start}"/>
				<input type="text" dateFlag="true" value="${ planDate_start}" readonly = "true" name="s_planDate_start" id="s_planDate_start" onchange="checkBeforeAndAfterTime('s_planDate_start','s_planDate_end','beforeTime_oldValue','开始时间','结束时间',1)"/>
					</td>
					<td width="80px" class="label">结束时间：</td>
					<td width="180px">
				<input type="hidden" id="afterTime_oldValue" value="${ planDate_end}"/>
				<input type="text" dateFlag="true" value="${ planDate_end}" readonly = "true" name="s_planDate_end" id="s_planDate_end" onchange="checkBeforeAndAfterTime('s_planDate_start','s_planDate_end','afterTime_oldValue','开始时间','结束时间',2)"/>
					</td>
					<td width="80px" class="label">订单状态：</td>
					<td>
				<select name="s_state" id="s_state">
					<option value="1">待审核</option>
					<option value="2">审核退回</option>
					<option value="4">保存</option>
					<option value="8">已提交</option>
				</select>
					</td>
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar unexpand">
				<tr>
					<td width="20px" align="center" rowspan="3" ></td>
						<td class="label">单据编号</td>
						<td><input type="text" size="20" name="s_cuid"/></td>
						<td class="label">领料组：</td>
						<td>
							<select id="groupSel" name="s_departmentId"  onchange="changeGroup(this)">
								<option value="">请选择...</option>
								<c:forEach items="${roles}" var="role">
									<option value="${role.cuid}">${role.labelCn}</option>
								</c:forEach>
							</select>
						</td>
					<td class="label">领料人:</td>
					<td>
						<select id="userSel" name="s_userId" >
							<option value="">请选择...</option>
						</select>
					</td>
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							<a href="javascript:query()" id="queryBtn"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a id="deletePlanButton" style="display:none" href="javascript:deletePlan()"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
							<c:if test="${pageRequest.filters.planType=='3'}">
							<a href="javascript:"><span onclick="orderPlanSubmit(this)"><img src="<%=iconPath%>/true.gif" />提交审核</span></a>
							<a href="javascript:targetFrame('${ctx }/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do')"><span><img src="<%=iconPath%>/addition.gif" />新单</span></a>
							</c:if>
							<a href="javascript:exportData()"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
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
