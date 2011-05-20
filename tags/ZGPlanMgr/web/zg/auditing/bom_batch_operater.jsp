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
	String orderPlanId = request.getParameter("orderPlanId");
	System.out.println(orderPlanId);
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
				doLayout() ;
				
			}catch(e){}
		});
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("resconfigResult").style.height;
		//	alert(maxHeight);
		//	maxHeight = maxHeight.replace("px","")*1;
		//	var height = maxHeight - 17;
		//	document.getElementById("grid-data-panel").style.height=height+'px';
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
			var id = '<%=orderPlanId%>';
			if(id=='自动生成'){
				id="";
			}
	
			var url='${ctx}/zg/plan/ZgTorderPlanbomForBatch/createOrderPlanForBatch.do?orderPlanId='+id;
			
			window.location.href = url;
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
		
		var cuids = new Array();
		
	
		
		
		
		function buildJsonString(){
			var josnString='';
			for(var i=0;i<cuids.length;i++)
			{
				var cuid=cuids[i];
				var carNum = document.getElementById(cuid+"auditNum").value;
				if(carNum==""){
					alert("计划领取数量不能为空！");
					return "false";
				} 
				josnString=josnString+'{' ;
			    josnString = josnString +'"auditNum":"'+carNum+'",';
			    josnString = josnString +'"cuid":"'+cuid+'"},';
			}
			if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
		}
		
		
		function orderSubmitAudit(){
			var jsonStr=buildJsonString();
			ZgTorderPlanbomDwrAction.saveAuditNumForBatchplan(jsonStr,function(data){
			});
			
		}
		
		function changeAuditNum(obj,cuid){
			checkNum(obj,cuid);
			changeOther(cuid);
		}
		
		function changeOther(id){
			if(cuids.length==0)
				cuids[cuids.length] = id;
			else{
				for(i=0;i<cuids.length;i++){//判断是否有相同的
					if(cuids[i]==id){//如果有相同的话
						return;
					}
				}
				cuids[cuids.length] = id;
			}
		}
		
		function checkNum(obj,cuid) {
			var value = obj.value;
			if(!isNumber(value)) {
				alert("计划领取必须为数字！");
				obj.value =0;
				return false;
			}
			var carNum=document.getElementById(cuid+'carnum').value;
			if(value*1>carNum*1){
				alert('审核数量不能大于计划领取数量，请确认!');
				obj.value=carNum;
				return false;
			}
			return true;
		}
		
	</script>
	</head>
	<body>
		<form
			action="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId=${orderPlanId}"
			method="post" validator="true">
			
			<c:if test="${not empty zgTorderPlanCommentList}">
			<div class="datalist">
				<h2>
					意见历史
				</h2>
					<div class="toolbar">
					</div>
				<div class="eXtremeTable">
					<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="20%">
									审核时间
								</td>
								<td class="tableHeader">
									审核人
								</td>
								<td class="tableHeader">
									审核意见
								</td>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${zgTorderPlanCommentList}" var="objlist" varStatus="nn">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<tr class="${trcss}"
											onmouseover="this.style.backgroundColor = '#EBF1FF'"
											onmouseout="this.style.backgroundColor = '#FFFFFF'">
									<td align="center">
										<fmt:formatDate value="${objlist.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td align="center">
										${objlist.chname}
									</td>
									<td align="center">
										${objlist.content}
									</td>
									</tr>
							</c:forEach>
						</tbody>
						</table>
				</div>
				</div>
			</c:if>
			
			<div class="datalist">
				<h2>
					BOM组件列表
				</h2>
				<div class="toolbar">
				</div>
				
				<div class="eXtremeTable">
					<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="8%">
									仓库名称
								</td>
								<td class="tableHeader" width="12%">
									BOM组件编号
								</td>
								<td class="tableHeader">
									BOM组件描述
								</td>
								<td class="tableHeader">
									基本单位
								</td>
								<td class="tableHeader">
									计划领取数量
								</td>

								<td class="tableHeader">
									审核数量
								</td>
								
							</tr>
							
						</thead>
						<tbody>
							<c:forEach items="${bomForBatchList}" var="obj" varStatus="n">
								<c:if test="${!obj.isDel}">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<c:choose>
										<c:when test="${obj.state=='9' }">
											<tr class="${trcss}"
												onmouseover="this.style.backgroundColor = '#EBF1FF'"
												onmouseout="this.style.backgroundColor = '#FFFFFF'">
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${zgTorderPlan.state eq '8'}">
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
												</c:when>
												<c:otherwise>
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
										
									<td align="center">
										${obj.lgort_lableCn}
									</td>
									<td align="center">
										${obj.idnrk}
									</td>
									<td align="center">
										${obj.maktx1}
									</td>
									<td align="center">
										${obj.msehl1}
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${(zgTorderPlan.state eq '8')||(zgTorderPlan.state eq '1')||(zgTorderPlan.state eq '2')}">
											<input type="hidden" id="${obj.cuid}carnum" maxlength = "13"
													value="${obj.carNum}"
													onchange="changeCarNum(this,'${obj.cuid}')" />
											${obj.carNum}</c:when>
											<c:otherwise>
												<input type="text" id="${obj.cuid}carnum" maxlength = "13"
													value="${obj.carNum}"
													onchange="changeCarNum(this,'${obj.cuid}')" />
											</c:otherwise>
										</c:choose>

									</td>
									<td align="center">
										<c:choose>
											<c:when test="${(zgTorderPlan.state eq '1')}">
											<input type="text" id="${obj.cuid}auditNum" maxlength = "13" width="50px"
													value="${obj.auditNum}"	onchange="changeAuditNum(this,'${obj.cuid}')" />	</c:when>
											<c:otherwise>
												${obj.auditNum}
											</c:otherwise>
										</c:choose>

									</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<br />
		<br />
		<br />
		<br />
		<br />
	</body>
</html>
