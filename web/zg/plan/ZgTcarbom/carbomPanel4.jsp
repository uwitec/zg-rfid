<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String noexpandIcon = basePath+"/resources/images/frame/ico_noexpand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
		<link href="${ctx}/widgets/extremecomponents/extremecomponents.css"
			type="text/css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
	<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$(window).bind("load",initLayoutOwner);
		});
		function initLayoutOwner() {
			try {
				var gridDataPanelH = $(top.bodyFrame).height()-$("#infoPanel").height();
				document.getElementById("grid-data-panel").style.height= gridDataPanelH - 67 + 'px';
				$(window).bind("resize",initLayoutOwner);
			}catch(e){}
		}
		function checkChild(key){
			$("*[attr='"+key+"'] input:checkbox").attr("checked",function() { return !this.checked});
		}
		
		function submitBom(){
			if(!checkNum()){
				return;
			}
			
			var result = openDialog1("${ctx}/zg/plan/ZgTcarbom/rfid.jsp?lgort=${carPlan.storageId }",600,280);
			
			if(result==undefined){
				return;
			}
			
			if(!checkForConfirmCarPlan()){
				return;
			}
			document.getElementById('storageUserId').value=result.cuid;
			var form=document.getElementById("listFrame");
			form.action="${ctx}/zg/plan/ZgTcarplan/confirmCarPlan1.do";
			form.submit();
		}
		
		function checkForConfirmCarPlan(){
			DWREngine.setAsync(false);
			var result;
			ZgTcarplanDwrAction.checkForSubmitCarplan1("${carPlanId }",function(data){
				if(data){
					result = true;
				}else{
					alert("该清单已提交，请确认!");
					result = false;
				}
			});
			return result;
		}
		
		
		
		
		function storageUserCheck(){
			if($("#storageUserId").val()==""){
				alert("请先刷卡确认仓管员信息....");
				return false;
			}
			return true;
		}
		
		function checkNum(){
	    	var jsonStr='';
			var items = document.frames['listFrame'].document.getElementsByName("items");
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		        	var carNum=document.frames['listFrame'].document.getElementById("carbomList["+i+"].planNum").value*1;
		        	if(carNum=='0'){
		        		alert("确认领料数量不能为零，请确认！");
						return;
		        	}
		        	var supItems=document.frames['listFrame'].document.getElementsByName("items"+i);
		        	var carSupNumAll=0;
		        	for (var j = 0; j < supItems.length; j++){
		        	   var carSupNum=document.frames['listFrame'].document.getElementById("carbomList["+i+"].supList["+j+"].carNum").value;
		        	   carSupNumAll=carSupNumAll+carSupNum*1;
		        	}
		        	if(carNum!=carSupNumAll&&supItems.length>0){
		        		alert("确认领料数量与具体供应商数量不相等，请确认！");
		        		return false;
		        	}
		        	
		        }
		    }
		    
		    return true;
		}
		
		function checkSupPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			if(value=='0'||value==''){
				return;
			}
			if(isNumber(value)) {
				if(max < value*1) {
					alert("供应商领料数量必须小于确认领料数量！");
					obj.value = oldValue;
					return;
				}
			}else {
				alert("供应商领料数量必须为数字！");
				obj.value = oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
		}
		
		
		
	</script>
</head>
  
<body >
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<c:if test="${empty flag }">
						<a href="javascript:submitBom()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
					</c:if>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</td>
			</tr>
		</thead>
	</table>
	<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
		<thead>
			<tr>
				<td class="title" colspan="8">
					<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
						style="cursor: pointer"  alt="" id="img_1"
						border="0" onclick="changeV('1')" />
					补领单据
				</td>
			</tr>
		</thead>
		<tbody id="tbody_1" style="display: block">
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
						${carPlanId}
					</td>
				<th>
						生产订单号：
				</th>
				<td width="15%">
						${aufnr}
					</td>
				<th>
					提交人编码：
				</th>
				<td width="15%">
					${carPlan.createUserId}
				</td>
				<th>
					提交人：
				</th>
				<td width="15%">
					${carPlan.createUserId_related.value}
				</td>

				
			</tr>
			<tr>
				<th>
					仓库编号：
				</th>
				<td width="15%">
					${carPlan.storageId }
				</td>
				<th>
					仓库名称：
				</th>
				<td width="15%">
					${carPlan.storageId_related.value}
					</td>
				<th>
					仓管员：
				</th>
				<td width="15%">	
					<input type="text" name="storageUserName" id="storageUserName" readonly="true" value="${ carPlan.storageUserId_related.value}"/>
				</td>
				<th>
				</th>
				<td width="15%">
				</td>

			</tr>
		</tbody>
	</table>
</div>
<div id="dataPanel" onload="setInterval('scroll()',1000)">
	<div class="grid-panel">
		<div id="title" class="title">补领单据</div>
		<div id="toolbar" class="toolbar">
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<form id="listFrame" action="${ctx}/zg/plan/ZgTcarbom/saveStorePlan.do" method="post">
				<input type="hidden" name="carPlanId" value="${carPlanId}"/>
				<input type="hidden" name="storageUserId" id="storageUserId" value="${storageUserId }" readonly="true"/>
				<table id="bomTable" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader" width="3%">
								<input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" />
							</td>
							 <td class="tableHeader" >BOM组件编号</td>
							 <td class="tableHeader" >BOM组件描述</td>
							 <td class="tableHeader" >生产订单编号</td>
							 <td class="tableHeader" >单台用量</td>
							 <td class="tableHeader" >物料需求</td>
							 <td class="tableHeader">确认领料数量 </td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${index%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td width="3%" >
								<input type="checkbox" name="items" value="cuid=${obj.CUID }&carNum=${obj.CAR_NUM }&planNum=${obj.PLAN_NUM }&orderBomId=${obj.ORDER_BOM_ID }&orderPlanbomId=${obj.ORDER_PLANBOM_ID }&rowNum=${n.count-1}&idnrk=${obj.IDNRK}&aufnr=${obj.AUFNR}"/>
							</td>
							<td align="left">${obj.IDNRK}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td align="center">${obj.AUFNR}</td>
							<td align="center">${obj.ZDTYL}</td>
							<td align="center">${obj.MENGE}</td>
							<td align="center">
								<input type="hidden" name="carbomList[${n.count-1}].lgort" id="carbomList[${n.count-1}].lgort" value="${pageRequest.filters.lgort }"/>
								<input type="hidden" name="carbomList[${n.count-1}].cuid" id="carbomList[${n.count-1}].lgort" value="${obj.CUID }"/>
								<input type="hidden" name="carbomList[${n.count-1}].carId" id="carbomList[${n.count-1}].lgort" value="${pageRequest.filters.carId }"/>
								<input type="hidden" name="carbomList[${n.count-1}].orderBomId" id="carbomList[${n.count-1}].lgort" value="${obj.ORDER_BOM_ID }"/>
								<input type="hidden" name="carbomList[${n.count-1}].orderPlanbomId" id="carbomList[${n.count-1}].lgort" value="${obj.ORDER_PLANBOM_ID }"/>
								<input type="hidden" name="carbomList[${n.count-1}].orderId" id="carbomList[${n.count-1}].lgort" value="${obj.ORDER_ID}"/>
								<input type="hidden" name="carbomList[${n.count-1}].carPlanId" id="carbomList[${n.count-1}].lgort" value="${carPlanId}"/>
								<input type="hidden" name="carbomList[${n.count-1}].planType" id="carbomList[${n.count-1}].lgort" value="${pageRequest.filters.planType}"/>
								<input type="hidden"  maxlength="13" onchange="checkPlanNum(this,'${n.count-1}')" size="8" name="carbomList[${n.count-1}].realNum" id="carbomList[${n.count-1}].realNum" value="${obj.CAR_PLAN_NUM}"/>
								<input type="hidden" readonly="true" maxValue="${obj["MAX_VALUE"]}" oldValue="${obj.CAR_PLAN_NUM}" maxlength="13" onchange="checkPlanNum(this,'${n.count-1}')" size="8" name="carbomList[${n.count-1}].planNum" id="carbomList[${n.count-1}].planNum" value="${obj.CAR_PLAN_NUM}"/>
								${obj.CAR_PLAN_NUM}
							</td>
						</tr>
						<c:forEach items="${obj.subList}" var="sup" varStatus="v">
							<tr class="${trcss}">
							<td width="3%" >
								<input type="hidden" name="items${n.count-1 }" />
							</td>
							<td colSpan="1" align="left">供应商编号：${sup.lifnr}</td>
							<td  colSpan="1"  align="left">&nbsp;&nbsp;供应商：${sup.lifnrName}</td>
							<td align="left" colSpan="2"> 批次:
							<c:choose>
									<c:when test="${flag=='view' }">${sup.batchno}</c:when>
									<c:otherwise>
							<input type="text"  maxlength="80" size="8" name="carbomList[${n.count-1}].supList[${v.count-1 }].batchno" id="carbomList[${n.count-1}].supList[${v.count-1 }].batchno" value="${sup.batchno}"/>
							</c:otherwise>
								</c:choose>
							</td>
							
							<td align="left" colSpan="1">
							认证清单：
								<c:choose>
									<c:when test="${flag=='view' }">${sup.zrzqd}</c:when>
									<c:otherwise><input type="text"  maxlength="80" size="8" name="carbomList[${n.count-1}].supList[${v.count-1 }].zrzqd" id="carbomList[${n.count-1}].supList[${v.count-1 }].zrzqd" value="${sup.zrzqd}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td align="center">
							<c:choose>
								<c:when test="${flag=='view' }">${sup.carNum}</c:when>
								<c:otherwise>
									<input type="hidden" name="carbomList[${n.count-1}].supList[${v.count-1 }].cuid" id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"  value="${sup.cuid}"/>
								<input type="hidden" name="carbomList[${n.count-1}].supList[${v.count-1 }].carBomId" id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"  value="${sup.carBomId}"/>
								<input type="hidden" name="carbomList[${n.count-1}].supList[${v.count-1 }].lifnr" id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"  value="${sup.lifnr}"/>
								<input type="hidden" name="carbomList[${n.count-1}].supList[${v.count-1 }].lifnrName" id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"  value="${sup.lifnrName}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" maxValue="${obj.CAR_PLAN_NUM}" oldValue="${sup.carNum}" maxlength="13" onchange="checkSupPlanNum(this,'${n.count-1}')" size="8" name="carbomList[${n.count-1}].supList[${v.count-1 }].carNum" id="carbomList[${n.count-1}].supList[${v.count-1 }].carNum" value="${sup.carNum}"/>
							
								</c:otherwise>
							</c:choose>
							</td>
							</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>

</body>
</html>