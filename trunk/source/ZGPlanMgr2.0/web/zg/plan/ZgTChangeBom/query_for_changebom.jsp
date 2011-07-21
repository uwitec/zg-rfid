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
		function query(){
		    var createDate_start=$("#s_createDate_start").val()
		    var createDate_end=$("#s_createDate_end").val()
		    if(createDate_start>createDate_end){
		      alert("开始时间不能大于结束时间")
		      
		    }
		   
		    
			var planStat= $("#s_state").val();
			
			
			if(planStat=='-8'){
			  
			//	$("#deletePlanButton").attr("style","display:''")
				
			}else{
			//	$("#deletePlanButton").attr("style","display:none");
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listForChange.do',document.forms[0]))document.forms[0].submit();
			document.forms[0].submit();
			
		  
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
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listForChange.do',document.forms[0])) {
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
				if(confirm("确认删除所选的换料计划单")){
					form.action="${ctx}/zg/plan/ZgTBomManager/deletePlan.do";
					form.submit();
				}
			}else {
				alert("请选择所要删除的对象！");
			}
		}
		function doQuery() {
			if(document.getElementById("_queryPanel").style.display == 'none') {
				changePanel(document.getElementById("split_1"));
			}
			if(batchValidation('listFrame','${ctx}/zg/plan/ZgTBomManager/listForChange.do',document.forms[0])) {
				document.forms[0].submit();
			}
		}
		
		function addPlan(){
			var type=$("#s_type").val();
			if(type==''){
				alert('请选择单据类型');
				return;
			}
			targetFrame('${ctx }/zg/plan/ZgTBomManager/createForChange.do?type='+type);
		}
	</script>
</head>

<body>
<div id="_queryPanel">
	<form action="${ctx}/zg/plan/ZgTBomManager/listForChange.do" method="post" target="listFrame">
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
						<option value="">全部</option>
						    <option value="CHANGE">	换料</option>
							<option value="BACK" >退料</option>
							<option value="RENEW" >补领料</option>
						</select>
					</td>
					<td width="80px" class="label">申请类型：</td>
					<td  width="80px">
						<select name="s_apply" id="s_apply" >
						    <option value="manul">人工申请</option>
							<option value="system" >工单变更引起</option>
						</select>
					</td>
					<td width="80px" class="label">单据状态：</td>
					<td width="80px">
				<select name="s_state" id="s_state">
				      <option value="">全部</option>
				    <option value="-8">未提交</option>
					<option value="-7" selected="selected">厂领导审核退回</option>
					<option value="-6">待厂领导审核</option>
					<option value="-4">待品质部审核</option>
					<option value="0">审核完成</option>
					<option value="4">领料中</option>
					<option value="8">领料完成（待退料）</option>
					<option value="9">领料退料完成</option>
				</select>
					</td>
					
				
					
				</tr>
			</thead>
			<tbody attr="tbody_1" class="querybar expand">
				<tr>
				
					<td width="20px" align="center" rowspan="3" ></td>
					
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
					
						<td class="label" width="80px">生产订单：</td>
						<td><input type="text" size="20" name="s_aufnr"/></td>
				
				</tr>
				
				<tr>
						<td class="label">生产厂：</td>
					<td>
					<select name="s_plant">
					<option value="">全部</option>
						<c:forEach items="${plantList}" var="item">
							<option value="${item.PLANTID }">${item.PLANTID }</option>
						</c:forEach>
					</select>
					</td>
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
				</tr>
					
			</tbody>
			<tfoot class="querybar">
				<tr>
					<td colspan="7" align="right">
						<div class="toolbar">
							
							<a href="javascript:query()" id="queryBtn"><span><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
							<a href="javascript:document.forms[0].reset()"><span><img src="<%=iconPath%>/ico_007.gif" />重置</span></a>
							<a id="deletePlanButton" href="javascript:deletePlan()"><span><img src="<%=iconPath%>/ico_005.gif" />删除</span></a>
							<a href="javascript:addPlan();"><span><img src="<%=iconPath%>/addition.gif" />新单</span></a>
							
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
