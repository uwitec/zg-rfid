<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
	<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			init();
				
		});
		
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("employee").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.getContentWidth()-200;
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		
		function buildupSubmitParams(id,state){
			var josnString='';
			var count = ${count};
			var josnString='';
			for (var i=0; i<count; i++) {
				if(document.getElementById("checkBox"+i).checked==state){
					var carNum = document.getElementById("carNum"+i).value; 
			        
			        var bomId = document.getElementById("bomId"+i).value;
					var maktx=document.getElementById("maktx"+i).value;
					var msehl=document.getElementById("msehl"+i).value;
					var bomId_labelCn=document.getElementById("bomId_labelCn"+i).value;
					var idnrk=document.getElementById("idnrk"+i).value;
					
					
					josnString=josnString+'{' ;
					josnString = josnString +'"bomId":"'+bomId+'",';
					josnString = josnString +'"maktx1":"'+maktx+'",';
					josnString = josnString +'"msehl1":"'+msehl+'",';
					josnString = josnString +'"lgort_lableCn":"'+window.parent.lgortText+'",';
					josnString = josnString +'"bomId_labelCn":"'+bomId_labelCn+'",';
					josnString = josnString +'"idnrk":"'+idnrk+'",';
					josnString = josnString +'"orderPlanId":"'+id+'"},';
			        
			    }
			} 
	        if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
	    }
	    
		function submitPlan(id) {
			ZgTorderPlanbomDwrAction.checkOrderPlanSaved(id,function(data){
				if(data) {
					window.location.href="${ctx}/zg/plan/ZgTorderPlan/submitOrderPlan.do?orderPlanId=${orderPlanId}"
				}else {
					alert("请先完成领料计划内容（红色列名区域），保存后在提交！");
				}
			});
		}
		
		function viewPlanOrderBom(){
			window.parent.location.href="${ctx}/zg/plan/ZgTorderPlanbomForBatch/list.do?orderPlanId=${orderPlanId}&flag=temp";
			
		}
		
		function updateBom(obj,num){
			var josnString='';
			var bomId = document.getElementById("bomId"+num).value;
			var maktx=document.getElementById("maktx"+num).value;
			var msehl=document.getElementById("msehl"+num).value;
			var bomId_labelCn=document.getElementById("bomId_labelCn"+num).value;
			var idnrk=document.getElementById("idnrk"+num).value;
			
			var orderPlanId = '${orderPlanId}';
			josnString=josnString+'{' ;
			josnString = josnString +'"carNum":"0",';
			josnString = josnString +'"bomId":"'+bomId+'",';
			josnString = josnString +'"maktx1":"'+maktx+'",';
			josnString = josnString +'"msehl1":"'+msehl+'",';
			josnString = josnString +'"lgort_lableCn":"'+window.parent.lgortText+'",';
			josnString = josnString +'"bomId_labelCn":"'+bomId_labelCn+'",';
			josnString = josnString +'"idnrk":"'+idnrk+'",';
			josnString = josnString +'"orderPlanId":"'+orderPlanId+'"}';
			josnString='['+josnString+']';
			if(obj.checked){//添加bom组件
					ZgTorderPlanbomDwrAction.addPlanbomByCar(josnString,"<%=session%>",function(data){
						if(data) {
								//alert("保存成功！");
						}else {
								alert("提交失败！");
						}
					});	
			}else{//删除bom组件
					ZgTorderPlanbomDwrAction.deletePlanbomByCar(josnString,"<%=session%>",function(data){
						if(data) {
							//	alert("保存成功！");
						}else {
								alert("提交失败！");
						}
					});	
			}
		

		}
		
		function setAllCheckboxState(name,state) {
			var elms = document.getElementsByName(name);
			for(var i = 0; i < elms.length; i++) {
				elms[i].checked = state;
			}
			var jsonString = this.buildupSubmitParams('${orderPlanId}',state);
			if(state){//添加bom组件
					ZgTorderPlanbomDwrAction.addPlanbomByCar(jsonString,"<%=session%>",function(data){
						if(data) {
								//alert("保存成功！");
						}else {
								alert("提交失败！");
						}
					});	
			}else{//删除bom组件
					ZgTorderPlanbomDwrAction.deletePlanbomByCar(jsonString,"<%=session%>",function(data){
						if(data) {
							//	alert("保存成功！");
						}else {
								alert("提交失败！");
						}
					});	
			}
			
			
		}
			
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
		<form action="${ctx}/zg/plan/ZgTorderPlanbomForBatch/saveOrderPlanBom.do" method="post">
			<input type="hidden" value="${orderPlanId}" size="30"  id="orderPlanId" name="orderPlanId" class="" />
			
			<div class="grid-panel" id="grid-panel" >
			<div id="grid-data-panel"  class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
									<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">BOM组件编号</td>
						<td class="tableHeader">BOM组件描述</td>
						<td class="tableHeader">物料组</td>
						<td class="tableHeader">基本单位</td>
						<%-- <td class="tableHeader">计划领取数量</td>--%>
								</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
					<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<tr class="${trcss}">
						<c:choose>
							<c:when test="${fn:indexOf(bomBatchIds,obj.cuid) == -1}">
								<td width="3%" align="center">
											<input type="checkbox" name="items" id="checkBox${n.count-1}"
												value="matnr=${obj.matnr}&lgort=${lgort}" onclick="updateBom(this,'${n.count-1}');" />
										</td>
							</c:when>
							<c:otherwise>
								<td width="3%" align="center">
											<input type="checkbox" checked="true" name="items" id="checkBox${n.count-1}"
												value="matnr=${obj.matnr}&lgort=${lgort}" onclick="updateBom(this,'${n.count-1}');" />
										</td>
							</c:otherwise>
						</c:choose>
						
						<td>${obj.idnrk}</td>
						<td>${obj.maktx}</td>
						<td>${obj.matkl}</td>
						<td>${obj.msehl}
						<input type="hidden" name="bomId_labelCn" id="bomId_labelCn${n.count-1}" value="${obj.labelCn}"/>
						<input type="hidden" name="maktx" id="maktx${n.count-1}" value="${obj.maktx}"/>
						<input type="hidden" name=msehl id="msehl${n.count-1}" value="${obj.msehl}"/>
						<input type="hidden" name=msehl id="idnrk${n.count-1}" value="${obj.idnrk}"/>
						<input type="hidden" name="bomId" id="bomId${n.count-1}" value="${obj.cuid}"/>
						<input type="hidden" attr="orderPlanId" name="orderPlanId" id="orderPlanId${n.count-1}" value="${orderPlanId}"/>
						<input type="hidden" name="carNum" id="carNum${n.count-1}" maxlength="10" value="${obj.carNum}" class="number" size="10" />
						</td>
					
						
						
					</tr>
				</c:forEach> 
						<tr>
							<td colspan="12" >
							</td>
						</tr>
				</tbody>
			</table>
			</div>
		</div>
			
			
		</form>
</body>
</html>
