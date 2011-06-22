<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
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
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
			
			window.parent.document.getElementById("s_bomCuids").value="${pageRequest.filters.bomCuids }";
		}
		function doLayout() {
			document.getElementById("grid-panel").style.height='255px';
			document.getElementById("grid-data-panel").style.height='255px';
		}
		
		function edit(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE.do?carPlanId='+cuid);
				var orderPlanType=document.getElementById('s_orderPlanType').value;
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel.do?carPlanId='+cuid+'&orderPlanType='+orderPlanType);
			}
		}
		
		function checkPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			var realObj = document.getElementById('carbomList['+num+'].realNum');
			var viewObj=document.getElementById("carPlaNum"+num);
			if(value=='0'){
				alert("实际装车数量不能为零，请确认！");
				obj.value = oldValue;
				viewObj.innerText=oldValue;
				return;
			}
			if(isNumber(value)) {
				if(max < value*1) {
					alert("实际装车数量必须小于待装车数量！");
					obj.value = oldValue;
					viewObj.innerText=oldValue;
					return;
				}
			}else {
				alert("实际装车数量必须为数字！");
				obj.value = oldValue;
				viewObj.innerText=oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
			realObj.value=value;
			viewObj.innerText=value;
		}
		
		function checkSupPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			if(value=='0'||value==''){
			//	alert("实际装车数量不能为零，请确认！");
			//	obj.value = oldValue;
				return;
			}
			if(isNumber(value)) {
				if(max < value*1) {
					alert("实际装车数量必须小于待装车数量！");
					obj.value = oldValue;
					return;
				}
			}else {
				alert("实际装车数量必须为数字！");
				obj.value = oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
		}
		
		function deleteBom(){
			var items=document.getElementsByName("items");
			var result=false;
			
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
					result=true;
					break;
				}
			}
			if(!result){
				alert('请选择需要操作的对象');
				return;
			}
			
			if(confirm("确定移除选定的物料吗?")){
				var form=document.getElementById("carList");
				form.target="_self";
				form.action="${ctx}/zg/plan/ZgTcarplan/deleteBom.do?lgort=${pageRequest.filters.lgort }";
				form.submit();
			}
			
			
		}
		
		
	  function printScreen(block)
      {        
      
      		var items=document.getElementsByName("items");
			var result=false;
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
					result=true;
					break;
				}
			}
			if(!result){
				alert('请选择要领料的物料！');
				return;
			}		
      
	        var value = document.all.block.innerHTML;
	        var preText="";
	        var num=0;
	        for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
					var bomText=document.getElementById(num+"_bom_print").innerHTML;
				//	alert(bomText);
					preText=preText+bomText;
					num++;
				}
			}
			
	        var printdetail = window.open("","printDetail","");
	        printdetail.document.open();
	        printdetail.document.write("<HTML><head></head><BODY onload='window.print();window.close();'>"); 
	        printdetail.document.write("<PRE>"); 
	        printdetail.document.write(preText); 
	        printdetail.document.write("</PRE>");
	        printdetail.document.close("</BODY></HTML>");
      }
      
		
	</script>
	
	
<style>
.graph {

	width: 90%;
	border: 1px solid #B1D632;
	padding: 2px;
	margin-bottom: .5em;
	text-align: left;
}

.graph .bar {
	display: block;
	background: #B1D632;
	text-align: center;
	color: #333;
	height: 2em;
	line-height: 2em;
	
}

.graph .bar span {
	left: 1em;
}

   .carfont
        {
            font-size: 14px;
            font-style: normal;
            color: #FF0000;
            text-decoration: underline;
            font-family: 宋体, Arial, Helvetica, sans-serif;
            font-weight: bold;
        }
        
       .readOnlyInput {
			border: 0;
			background: transparent;
		}
        
</style>
 <style   media=print>   
        .Noprint{display:none;}   
        .PageNext{page-break-after:   always;}   
    </style> 
</head>
<body>

<div id="block" style="display:none">
							<c:set var="num1" value="0"></c:set>
						<c:forEach items="${bomList}" var="obj" varStatus="n" >
							<c:if test="${empty obj.STORAGE_USER_ID}">
							<div id="${num1}_bom_print">
								<div>
								<span style="font-size: 10px; word-break: break-all; width: 185px;">
									<font style="font-size: 10px;"> 
								装车编号:${pageRequest.filters.carPlanId }
								订单号:${obj.AUFNR}<br/>
								生产线：${obj.ARBPL}<br/>
								仓库编号：${pageRequest.filters.lgort} <br />
								仓库名称：${pageRequest.filters.lgortName }<br />
								销售订单号：${obj.KDAUF}<br />
								项目号：${obj.KDPOS }<br />
								公司机型：${obj.MAKTX1 }<br />
								物料编码:${obj.IDNRK}<br />
								
								物料描述:${obj.MAKTX2}<br /> 
								大小量纲:${obj.ZBZ} <br />
									领取数量:<span id="carPlaNum${num1}">${obj.CAR_PLAN_NUM}</span>
								 <br /> <br /> </font>
								 </span>
								 </div>
						    </div>
								 <c:set var="num1" value="${num1+1}"></c:set>
						</c:if>
						</c:forEach>
				
		</div>
		<div class="noprint">
     
	<form id="carList" name="carList" action="<c:url value="/zg/plan/ZgTcarplan/loadCarList.do"/>" method="post" style="display: inline;" >
	<input type="hidden" name="s_bomCuids" value="${pageRequest.filters.bomCuids }"/>
	<input type="hidden" name="s_groupId" value="${pageRequest.filters.groupId }"/>
	<input type="hidden" name="s_carPlanId" value="${pageRequest.filters.carPlanId }"/>
	<input type="hidden" name="s_planType" value="${pageRequest.filters.planType }"/>
	<input type="hidden" name="s_carId" value="${pageRequest.filters.carId }"/>
	<input type="hidden" name="s_lgort" value="${pageRequest.filters.lgort }"/>
	<input type="hidden" name="s_lgortName" value="${pageRequest.filters.lgortName }"/>
	<input type="hidden"  name="storageUserId" id="storageUserId"/>
	<input type="hidden"  name="s_aufnr" id="s_aufnr" value="${pageRequest.filters.aufnr }"/>
	<input type="hidden"  name="s_remark" id="s_remark" />
	
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title" >装车计划</div>
			<div class="toolbar">
						<a href="javascript:deleteBom()"><span><img
									src="<%=iconPath%>/ico_005.gif" />删除</span>
						</a>
						<a href="javascript:window.focus();printScreen('block');" ><span><img src="${ctx}/resources/css/default/images/icons/ico_008.gif" />打印</span></a>
			
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader" width="3%">
								<input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" />
							</td>
							 <td class="tableHeader" >BOM组件编号</td>
							 <td class="tableHeader" >BOM组件描述</td>
							 <td class="tableHeader" >生产订单编号</td>
							 <td class="tableHeader" >单台用量</td>
							 <td class="tableHeader" >每车可装数量</td>
							 <td class="tableHeader" >物料需求</td>
							 <td class="tableHeader">可装车数 </td>
							 <td class="tableHeader">本次装车数量 </td>
						</tr>
					</thead>
					<tbody>
					<c:set var="num" value="0"></c:set>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${index%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td width="3%" >
							<c:if test="${empty obj.STORAGE_USER_ID}">
								<input type="checkbox" name="items" value="count=${num}&cuid=${obj.CUID }&carNum=${obj.CAR_NUM }&planNum=${obj.PLAN_NUM }&orderBomId=${obj.ORDER_BOM_ID }&taskBomId=${obj.TASKBOM_ID }&orderPlanbomId=${obj.ORDER_PLANBOM_ID }&rowNum=${num}&idnrk=${obj.IDNRK}&aufnr=${obj.AUFNR}&carPlanId=${pageRequest.filters.carPlanId}"/>
							</c:if>
							</td>
							<td align="left">${obj.IDNRK}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td align="center">${obj.AUFNR}</td>
							<td align="center">${obj.ZDTYL}</td>
							<td align="center">${obj.CARCOUNT}</td>
							<td align="center">${obj.CAR_NUM}</td>
							<td align="center">${obj.MAX_VALUE}</td>
							<td align="center">
								
							<c:if test="${empty obj.STORAGE_USER_ID}">
								<input type="hidden" name="carbomList[${num}].lgort" id="carbomList[${num}].lgort" value="${pageRequest.filters.lgort }"/>
								<input type="hidden" name="carbomList[${num}].cuid" id="carbomList[${num}].lgort" value="${obj.CUID }"/>
								<input type="hidden" name="carbomList[${num}].carId" id="carbomList[${num}].lgort" value="${pageRequest.filters.carId }"/>
								<input type="hidden" name="carbomList[${num}].idnrk" id="carbomList[${num}].idnrk" value="${obj.IDNRK}"/>
								<input type="hidden" name="carbomList[${num}].taskBomId" id="carbomList[${num}].lgort" value="${obj.TASKBOM_ID }"/>
								<input type="hidden" name="carbomList[${num}].orderPlanbomId" id="carbomList[${num}].lgort" value="${obj.ORDER_PLANBOM_ID }"/>
								<input type="hidden" name="carbomList[${num}].orderTaskId" id="carbomList[${num}].lgort" value="${obj.ORDERTASKID}"/>
								<input type="hidden" name="carbomList[${num}].carPlanId" id="carbomList[${num}].lgort" value="${pageRequest.filters.carPlanId}"/>
								<input type="hidden" name="carbomList[${num}].planType" id="carbomList[${num}].lgort" value="${pageRequest.filters.planType}"/>
								<input type="hidden"  maxlength="13" onchange="checkPlanNum(this,'${num}')" size="8" name="carbomList[${num}].realNum" id="carbomList[${num}].realNum" value="${obj.CAR_PLAN_NUM}"/>
								<input type="text" maxValue="${obj["MAX_VALUE"]}" oldValue="${obj.CAR_PLAN_NUM}" maxlength="13" onchange="checkPlanNum(this,'${num}')" size="8" name="carbomList[${num}].planNum" id="carbomList[${num}].planNum" value="${obj.CAR_PLAN_NUM}"/>
								<c:set var="num" value="${num+1}"></c:set>
							</c:if>
							<c:if test="${not empty obj.STORAGE_USER_ID}">
							<input type="hidden" name="hasConfirm"/>
								${obj.COMPLETE_NUM}
							</c:if>
							</td>
						</tr>
						<c:forEach items="${obj.subList}" var="sup" varStatus="v">
							<tr class="${trcss}">
							<td width="3%" >
							<c:if test="${empty obj.STORAGE_USER_ID}">
								<input type="hidden" name="items${num-1 }" />
							</c:if>
								
							</td>
							<td colSpan="1" align="left">供应商编号：${sup.lifnr}</td>
							<td  colSpan="2"  align="left">&nbsp;&nbsp;供应商：${sup.lifnrName}</td>
							<td align="center" colSpan="2"> 批次:
								<c:if test="${empty obj.STORAGE_USER_ID}">
									<input type="text"  maxlength="80" size="8" name="carbomList[${num-1}].supList[${v.count-1 }].batchno" id="carbomList[${num-1}].supList[${v.count-1 }].batchno" value="${sup.batchno}"/>
								</c:if>
								<c:if test="${not empty obj.STORAGE_USER_ID}">
									${sup.batchno}
								</c:if>
								
							<td colSpan="2">
							认证清单：
							<c:if test="${empty obj.STORAGE_USER_ID}">
									<input type="text"  maxlength="80" size="8" name="carbomList[${num-1}].supList[${v.count-1 }].zrzqd" id="carbomList[${num-1}].supList[${v.count-1 }].zrzqd" value="${sup.zrzqd}"/>
								</c:if>
								<c:if test="${not empty obj.STORAGE_USER_ID}">
									${sup.zrzqd}
								</c:if>
							</td>
							<td align="center">
							
							<c:if test="${empty obj.STORAGE_USER_ID}">
								<input type="hidden" name="carbomList[${num-1}].supList[${v.count-1 }].cuid" id="carbomList[${num-1}].supList[${v.count-1 }].cuid"  value="${sup.cuid}"/>
								<input type="hidden" name="carbomList[${num-1}].supList[${v.count-1 }].carBomId" id="carbomList[${num-1}].supList[${v.count-1 }].cuid"  value="${sup.carBomId}"/>
								<input type="hidden" name="carbomList[${num-1}].supList[${v.count-1 }].lifnr" id="carbomList[${num-1}].supList[${v.count-1 }].cuid"  value="${sup.lifnr}"/>
								<input type="hidden" name="carbomList[${num-1}].supList[${v.count-1 }].lifnrName" id="carbomList[${num-1}].supList[${v.count-1 }].cuid"  value="${sup.lifnrName}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" maxValue="${obj["MAX_VALUE"]}" oldValue="${sup.carNum}" maxlength="13" onchange="checkSupPlanNum(this,'${num-1}')" size="8" name="carbomList[${num-1}].supList[${v.count-1 }].carNum" id="carbomList[${num-1}].supList[${v.count-1 }].carNum" value="${sup.carNum}"/>
							</c:if>
							<c:if test="${not empty obj.STORAGE_USER_ID}">
								${sup.carNum}
							</c:if>
							
								
							</td>
							</tr>
						</c:forEach>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="9" >
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div>
	
	</form>
	</div>
	

</body>
</html>