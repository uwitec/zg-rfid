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
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
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
			}catch(e){}
		});
		
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
		
		//提交领料计划
		function submitPlan(){
			if($("#planDate").val()==''){
				alert("请填写领料时间!");
				return;
			}
			document.forms[0].action="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do";
			document.forms[0].submit();
			//window.location.href="${ctx}/zg/plan/ZgTorderPlanForBatch/submitOrderPlanForBatch.do?orderPlanId=${orderPlanId}";
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
		
	</script>
	</head>
	<body onload="init(${zgTorderPlan.state});">
		<div class="eXtremeTable">
			<form action="${ctx}/zg/plan/ZgTorderPlanForBatch/saveOrderPlanForBatch.do"
				method="post">
				<input type="hidden" name="orderPlanId" value="${orderPlanId}" />
				<input type="hidden" value="5"
									readonly="true" name="planType" class="required" />
				<div>
					<table class="formTable">
					<c:choose>
							<c:when test="${zgTorderPlan.state eq '8'}"></c:when>
							<c:otherwise>
								<tr>
									<td align="center">
									
										<button class="commonButton saveButton" id="b_save"
											onclick="addPlan('${orderPlanId}')">
											保存
										</button>
									
										<button class="commonButton acceptButton" id="b_submit" name ="b_submit"
											onclick="submitPlan('${orderPlanId}')" >
											提交
										</button>
									
										<button class="commonButton acceptButton" id="b_back" name ="b_back"
											onclick="backToList()" >
											返回
										</button>
									
									</td>
									<td colspan="7">
										<img src="${ctx }/styles/images/detail.png" alt="" />
										单据信息
									</td>
								</tr>
							</c:otherwise>
					</c:choose>
						<tr>
							<td class="tdLabel" width="10%">
								单据编码:
							</td>
							<td width="20%">
							<input type="hidden" value="${zgTorderPlan.state }"  name="state" id="state"/>
								<input type="text" value="${orderPlanId}" name="cuid" id="cuid"
									class="required" size="37" readonly="true" />
							</td>
							<td class="tdLabel" width="10%">
								领料时间:
							</td>
							<td width="10%">
								<input type="text" size="12" dateFlag="true" id="planDate" name="planDate"
									value="<fmt:formatDate value="${zgTorderPlan.planDate}" pattern="yyyy-MM-dd" />" />
							</td>
							<td class="tdLabel" width="10%">
								领料员:
							</td>
							<td width="15%">
								<input type="text" value="${zgTorderPlan.userId_related.value}"
									readonly="true" class="required" />
								<input type="hidden" value="${zgTorderPlan.userId}"
									readonly="true" name="userId" class="required" />
							</td>
							<td class="tdLabel" width="10%">
								领料部门:
							</td>
							<td width="15%">
								<input type="text"
									value="${zgTorderPlan.departmenuId_related.value}"
									readonly="true" name="" class="required" />
								<input type="hidden" value="${zgTorderPlan.departmenuId}"
									readonly="true" name="departmenuId" class="required" />
							</td>
						</tr>

					</table>
				</div>
				<p></p>
				<br />
			</form>
				<br />	<br />	<br />	<br />	<br />	
		</div>
	</body>
</html>
