<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link href="${ctx}/widgets/extremecomponents/extremecomponents.css"
			type="text/css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}
</style>
		<script type="text/javascript">
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
				 doLayout() ;
			}catch(e){}
		});
		
		function doLayout() {
				var maxHeight = top.getContentHeight();
				
			var queryPanelH = document.getElementById("_queryPanel").style.height;
			document.getElementById("resconfigResult").style.height = (maxHeight-queryPanelH) + 'px';
			document.getElementById("_operatePanel").style.height = (maxHeight-queryPanelH) + 'px';
			
		}
		
		//保存领料计划
		function addPlan(id) {
			if($("#planDate").val()==''){
				alert("请填写领料时间!");
				return;
			}
			document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do";
			document.forms[0].submit();
			
		}
		
		function addPlanBom(id){
			if(id=='自动生成'){
				id="";
			}
	
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/createOrderPlanForBatch.do?orderPlanId='+id;
			var dialogHeight=360;
			var dialogWidth=700;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do?orderPlanId=${orderPlanId}&flag=temp';
			if(result!=undefined){
					alert(result);
			}
		
			window.location.reload();
		}
		
		function editPlanBom(id,orderPlanId){
			
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/editOrderPlanForBatch.do?id='+id+'&orderPlanId='+orderPlanId;
			
			var dialogHeight=360;
			var dialogWidth=700;
			
			var sFeatures = "dialogHeight="+dialogHeight+"px;dialogWidth="+dialogWidth+"px;resizable=no;help=no;scroll=no;status=yes";
			var result = window.showModalDialog(url,'',sFeatures);
			
			window.location.href ='${ctx}/zg/plan/ZgTorderPlanbomForBatch/findBomBatchList.do?orderPlanId=${orderPlanId}&flag=temp';
			if(result!=undefined){
					alert(result);
			}
		
			window.location.reload();
		}
		
		//提交领料计划,num:1为通过；2为驳回    cuid为单id
		function submitOrRejectPlan(num,cuid,obj){
			obj.disabled=true;
			if($("#planDate").val()==''){
				alert("请填写领料时间!");
				obj.disabled=false;
				return;
			}
			//alert(document.getElementById('resconfigResult').document);
			//document.getElementById('resconfigResult').document.orderSubmitAudit();
		
			
			//如果是‘驳回’，但又没有写上驳回意见的话
			var rejectOpinionText="";
			if(num==2){
				rejectOpinionText=document.getElementById("rejectOpinion").value;
				if(rejectOpinionText.Trim()=="请填写审核意见......"){
					alert("请填上审核意见!");
					obj.disabled=false;
					return;
				}
			}
			
			window.frames["resconfigResult"].orderSubmitAudit();
			
			document.forms[0].action="${ctx}/wu/auditing/AuditingForBatch/submitOrderPlanForBatch.do?submitType="+num+"&rejectOpinionText="+rejectOpinionText;
			document.forms[0].submit();
		}
		
		//去除空格的
		String.prototype.Trim = function() 
		{ 
			return this.replace(/(^\s*)|(\s*$)/g, ""); 
		}
		
		
		function init(state){
			if($("#cuid").val()=='自动生成'){
			   $("#b_submit").attr("disabled",true);
			}
			//	document.forms[0].submit();
			if(state=='8'){//表单状态为提交状态
				  $("#b_save").attr("disabled",true);
			}
		}
		
		function backToList(){
			parent.document.forms[0].submit();
		}
		//点击多行文本
		function clickTextarea(){
			if(document.getElementById("rejectOpinion").value.Trim()=="请填写审核意见......")
				document.getElementById("rejectOpinion").value="";
		}
		//移开
		function moveTextarea(){
			if(document.getElementById("rejectOpinion").value.Trim()=="")
				document.getElementById("rejectOpinion").value="请填写审核意见......";
		}
	</script>
	</head>
	<body onload="init(${zgTorderPlan.state});">
		<c:if test="${zgTorderPlan.state eq '1'}">
		<div id="_queryPanel" style="height:160px">
		</c:if>
		<c:if test="${zgTorderPlan.state ne '1'}">
		<div id="_queryPanel" style="height:60px">
		</c:if>
	
		<table width="100%" cellpadding="0" cellspacing="1"
			style="border: 1px solid #A8CFEB;">
			<thead>
				<tr>
					<td class="formToolbar">
						<div class="button" style="text-align: left;">
										
						<c:choose>
							<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '2')}"></c:when>
							<c:otherwise>
							
							 <a href="javascript:"><span onclick="submitOrRejectPlan(1,'${orderPlanId}',this)"><img
										src="<%=iconPath%>/true.gif" />通过</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="submitOrRejectPlan(2,'${orderPlanId}',this)"><img
										src="<%=iconPath%>/ico_009a.gif" />退回</span> </a>
							</c:otherwise>
						</c:choose>
					
							 &nbsp;<a href="javascript:"><span
								onclick="if(parent.doQuery)parent.doQuery()"><img
										src="<%=iconPath%>/ico_007.gif" />返回</span> </a>

						</div>
					</td>
				</tr>
			</thead>
		</table>
		<form
			action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do"
			method="post">
			<input type="hidden" name="orderPlanId" value="${orderPlanId}" />
			<input type="hidden" value="5" name="planType" class="required" />
			<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="8">
							单据编码：${orderPlanId}
						</td>
					</tr>
				</thead>
				<tbody id="tbody_1" style="display: none">
					<tr>
						<td colspan="8"
							style="border: 1px solid #A8CFEB; border-width: 0 0 1px 0;">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="20"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>
								单据编码：
						</th>
						<td width="15%">
							<input type="hidden" value="${zgTorderPlan.state }"  name="state" id="state"/>
								<input type="text" value="${orderPlanId}" name="cuid" id="cuid" maxlength ="50"
									class="required" size="37" readonly="true" />
							</td>
						<th>
							领料时间：
						</th>
						<td width="15%">
							<input type="text" size="12" readonly = "true" dateFlag="true" id="planDate" name="planDate" onchange="checkTime(this.value,'领料时间','planDate')" 
									value="<fmt:formatDate value="${zgTorderPlan.planDate}" pattern="yyyy-MM-dd" />" />
							
						</td>
						<th>
							领料员：
						</th>
						<td width="15%">
							${zgTorderPlan.userId_related.value}
								<input type="hidden" value="${zgTorderPlan.userId}"
									readonly="true" name="userId" class="required" maxlength = "40" />
						</td>

						<th>
						领料部门：
						</th>
						<td width="15%">
						${zgTorderPlan.departmenuId_related.value}
								<input type="hidden" value="${zgTorderPlan.departmenuId}"
									readonly="true" name="departmenuId" class="required" />
						</td>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${zgTorderPlan.state eq '1'}">
				<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
					style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
					<thead>
						<tr>
							<td class="title" colspan="5">
								<center>
									<h2>
										审<br />核<br />意<br />见
									</h2>
								</center>
							</td>
							<td>
								<textarea name="rejectOpinion" id="rejectOpinion" onpropertychange="checkLength(this,85);" cols=130 rows=4 onFocus="clickTextarea()" onblur="moveTextarea()">请填写审核意见......</textarea>
							</td>
						</tr>
					</thead>
				</table>
			</c:if>
		</form>
	</div>

	
		<c:if test="${zgTorderPlan.state eq '1'}">
			<div id="_operatePanel"  style="height:325px">
	<iframe id="resconfigResult"
	src="${ctx}/wu/auditing/AuditingbomForBatch/list.do?orderPlanId=${orderPlanId}"
	width="100%" style="height:325px"frameborder="0" marginwidth="0"
	marginheight="0"></iframe>
		</c:if>
		<c:if test="${zgTorderPlan.state ne '1'}">
			<div id="_operatePanel"  style="height:545px">
	<iframe id="resconfigResult"
	src="${ctx}/wu/auditing/AuditingbomForBatch/list.do?orderPlanId=${orderPlanId}"
	width="100%" style="height:545px"frameborder="0" marginwidth="0"
	marginheight="0"></iframe>
		</c:if>
	
</div>	
	</body>
</html>
