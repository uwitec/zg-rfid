<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
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
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderbomDwrAction.js'></script>
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderDwrAction.js'></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js"></script>
	
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
		function saveBom() {
			var table=document.getElementById('bomTable');
			if(table.rows.length<=2){
				alert('装车BOM组件不能为空，请确认');
				return;
			}
			if(!check()){
				return;
			}
			document.saveCarPlanForm.action = "${ctx}/zg/plan/ZgTcarbom/saveCarPlan.do";
			document.saveCarPlanForm.submit();
		}
		
		function delBom(){
		    if (!hasOneChecked("items")){
		            alert('请选择要操作的对象!');
		            return;
		    }
		    if (confirm('确定执行[删除]操作?')){
		        document.saveCarPlanForm.action = "${ctx}/zg/plan/ZgTcarbom/delOrderBomForCarPlan.do?orderPlanType=${orderPlanType}";
		        document.saveCarPlanForm.submit();
		    }
		}
		
		function checkPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			var realObj = document.getElementById('carbomList['+num+'].realNum');
			if(isNumber(value)) {
				if(max < value*1) {
					alert("计划领取数量必须小于可领取数量！");
					obj.value = document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
					realObj.value=	obj.value;
				}
			}else {
				alert("计划领取数量必须为数字！");
				obj.value = document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
				realObj.value=	obj.value;
			}
			realObj.value=	obj.value;
			//获取‘计划领取数量’改动后的相差
			var discrepancy=obj.value-document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
			//把 ‘计划领取数量’的汇总-‘计划领取数量’改动后的相差
			document.getElementById(document.getElementById('carbomList1['+num+'].key').value).innerText=parseInt(document.getElementById(document.getElementById('carbomList1['+num+'].key').value).innerText)+discrepancy;
			
			document.getElementById('carbomList1['+num+'].oldRealNumForUse').value=obj.value;
		}
		function addBom(carPlanId){
			var table=document.getElementById('bomTable');
			if(table.rows.length<=2){//bom为空时则可以选择仓库
				window.location.href='${ctx }/zg/plan/ZgTcarplan/carPlanCreate.do?orderPlanType=${orderPlanType}&carPlanId=${carPlanId}';
			}else{
				//bom不为空时只能在原来的仓库上选择新增的bom组件
				window.location.href = "${ctx}/zg/plan/ZgTcarbom/addBom.do?carPlanId="+carPlanId+'&orderPlanType=${orderPlanType}';
			}
	
		}
		
		function carPlanSubmit(carPlanId) {
			var table=document.getElementById('bomTable');
			if(table.rows.length<=2){
				alert('装车BOM组件不能为空，请确认');
				return;
			}
			if(!check()){
				return;
			}
			document.saveCarPlanForm.action = "${ctx}/zg/plan/ZgTcarbom/submitCarPlan.do";
			document.saveCarPlanForm.submit();
			//ZgTcarplanDwrAction.submitOneCarPlan(carPlanId,function(data){
			//	if(data) {
			//		alert("提交成功！");
			//		if(parent.doQuery){
			//			parent.doQuery()
			//		}
			//	}else {
			//		alert("提交失败！");
			//	}
			//});	
		}
		
		function exportBom(carPlanId){
			ZgTcarplanDwrAction.ExportCarPlan(carPlanId, function(url) {
				window.open(url);
			});
		}
		
		function viewBom(carPlanId){
			var url = "${ctx}/zg/plan/ZgTcarplan/viewCarPlan.do?carPlanId="+carPlanId;
			window.showModalDialog(encodeURI(url),"","dialogWidth:"+(screen.width)/1.2+"px;dialogHeight:"+(screen.height)/1.2+"px;help:no;status:no");
			//window.open(url);
		}
		
		function check(){
			if(checkNum()) {
				var jsonStr=buildupSubmitParams();
				DWREngine.setAsync(false);
				var result=false;
				ZgTcarplanDwrAction.checkForSaveOrSubmitCarPlan(jsonStr,'${carPlanId}',function(data){
					if(data){
						result = true;
					}else{
						alert("目前数据不是最新数据，重新进入页面处理！");
						result = false;
					}
				});
				return result;
			}else {
				alert("存在数据未通过验证，请核查！");
			}
		}
		
		function checkNum() {
			var count=${count};
			var flag = true;
			for (var i = 0 ; i < count ; i++){
				var obj = document.getElementById("carbomList["+i+"].planNum");
				var max = obj.getAttribute("maxValue")*1;
				var value = obj.value;
				if(isNumber(value)) {
					if(max < value*1) {
						document.getElementById('carbomList['+i+'].planNum').style.color="red";
						flag = false;
					}else {
						document.getElementById('carbomList['+i+'].planNum').style.color="black";
					}
				}else {
					document.getElementById('carbomList['+i+'].planNum').style.color="red";
					flag = false;
				}
			}
			return flag;
		}
		
		function buildupSubmitParams(){
			var count=${count};
			var jsonStr='';
			
		    if (count> 0) {
		        for (var i = 0 ; i < count ; i++){
		            	jsonStr=jsonStr+'{' ;
		           		var orderPlanbomId=document.getElementById("carbomList["+i+"].orderPlanbomId").value;
		           		var carPlanNum =document.getElementById("carbomList["+i+"].oldPlanNum").value;
		           		var carPlanId ="${carPlanId}";
		           		var cuid=document.getElementById("carbomList["+i+"].cuid").value;
		           		jsonStr = jsonStr + '"cuid":"'+cuid+'",';
		           		jsonStr = jsonStr + '"carPlanId":"'+carPlanId+'",';
		           		jsonStr = jsonStr + '"carPlanNum":"'+carPlanNum+'",';
		           		jsonStr = jsonStr +'"orderPlanbomId":"'+orderPlanbomId+'"},';
		        }
		    }
		     if(jsonStr.length>0){
	        	jsonStr=jsonStr.substr(0,jsonStr.length-1);
	        }
	        jsonStr='['+jsonStr+']';
	        return jsonStr;
	    }
	</script>
</head>
  
<body>
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<a href="javascript:carPlanSubmit('${carPlanId}')"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
					<a href="javascript:saveBom()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
					<a href="javascript:addBom('${carPlanId}')"><span><img src="<%=iconPath%>/addition.gif"/>新增BOM</span></a>
					<a href="javascript:delBom('${carPlanId}')"><span><img src="<%=iconPath%>/ico_005.gif" />删除BOM</span></a>
					<a href="javascript:exportBom('${carPlanId}')"><span><img src="<%=iconPath%>/page_excel.png" />导出</span></a>
					<a href="javascript:viewBom('${carPlanId}')"><span><img src="<%=iconPath%>/ico_search.gif" />预览</span></a>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</td>
			</tr>
		</thead>
	</table>
</div>
<div id="dataPanel">
	<div class="grid-panel">
		<div id="title" class="title">装车计划管理</div>
		<div id="toolbar" class="toolbar">
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<form name="saveCarPlanForm" action="${ctx}/zg/plan/ZgTcarbom/saveCarPlan.do" method="post">
				<input type="hidden" name="carPlanId" value="${carPlanId}"/>
				<input type="hidden" name="orderPlanType" value="${orderPlanType }"/>
				<table id="bomTable" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader">BOM组件</td>
							<td class="tableHeader">订单编号</td>
							<td class="tableHeader">物料编号</td>
							<td class="tableHeader">物料描述</td>
							<td class="tableHeader">度量单位</td>
							<td class="tableHeader">总需求数量</td>
							<td class="tableHeader">仓库领料完成数量</td>
							<td class="tableHeader">可领取数量</td>
							<td class="tableHeader" style="color:red">计划领取数量</td>
							<td class="tableHeader">排序日期</td>
							<td class="tableHeader">销售订单号</td>
							<td class="tableHeader">生产厂</td>
							<td class="tableHeader">生产线</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="group" colspan="13" height="25">
								<div class="expandbtn">
									<img src="<%=noexpandIcon %>" type="unexpandAll"/>
									<a href="javascript:">全部展开</a>
								</div>
							</td>
						</tr>
					<c:set var="index" value="0"></c:set>
					<c:forEach items="${orderboms}" var="orderbom">
						<tr>
							<td class="group" colspan="3" height="25">
								<div class="expandbtn">
									<img src="<%=noexpandIcon %>" for="${orderbom.key}"/>
									<a href="javascript:checkChild('${orderbom.key}')" title="选择该组">${orderbom.key}</a>
								</div>
							</td>
							<c:forEach items="${orderbom.value}" var="obj" varStatus="n" begin='0' end='0'>
								<td align="center">
								<c:out value='${obj["MATNR"]}'/>
							</td>
							<td align="center">
								<c:out value='${obj["MAKTX1"]}'/>
							</td>
							<td align="center">
								<c:out value='${obj["MSEHL1"]}'/>
							</td>
							<td align="center">
								<c:set var="carNumAll" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj1" varStatus="m">
								 	<c:set var="carNumAll" value="${carNumAll + obj1.CAR_NUM}"/>
								</c:forEach>
								<c:out value='${carNumAll}'/>
							</td>
							<td align="center">
								<c:set var="completeNumAll" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj2" varStatus="o">
								 	<c:set var="completeNumAll" value="${completeNumAll + obj2.COMPLETE_NUM}"/>
								</c:forEach>
								<c:out value='${completeNumAll}'/>
							</td>
							<td align="center">
								<c:set var="maxValueAll" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj3" varStatus="w">
								 	<c:set var="maxValueAll" value="${maxValueAll + obj3.MAX_VALUE}"/>
								</c:forEach>
								<c:out value='${maxValueAll}'/>
							</td>
							<td align="center">
								<c:set var="carPlanNumAll" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj4" varStatus="k">
								 	<c:set var="carPlanNumAll" value="${carPlanNumAll + obj4.CAR_PLAN_NUM}"/>
								</c:forEach>
								<div id="${orderbom.key}"><c:out value='${carPlanNumAll}'/></div>
							</td>
							</c:forEach>
						</tr>
						<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" attr="${orderbom.key}">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj['CUID']}&orderPlanbomId=${obj['ORDER_PLANBOM_ID']}&carPlanId=${carPlanId }&carPlanNum=${obj['PLAN_NUM']}"/>
								<input type="hidden" name="carbomList[${index}].orderPlanbomId" id="carbomList[${index}].orderPlanbomId"  value="${obj['ORDER_PLANBOM_ID']}"/>
								<input type="hidden" name="carbomList[${index}].carPlanId" value="${obj['CAR_PLAN_ID']}"/>
								<input type="hidden" name="carbomList[${index}].cuid" id="carbomList[${index}].cuid" value="${obj['CUID']}"/>
								<input type="hidden" name="carbomList[${index}].realNum" id="carbomList[${index}].realNum" value="${obj['CAR_PLAN_NUM']}"/>
								<input type="hidden" name="carbomList[${index}].oldPlanNum" id="carbomList[${index}].oldPlanNum" value="${obj['CAR_PLAN_NUM']}"/>
								<input type="hidden" name="carbomList1[${index}].oldRealNumForUse" id="carbomList1[${index}].oldRealNumForUse" value="${obj['CAR_PLAN_NUM']}"/>
								<input type="hidden" name="carbomList1[${index}].key" id="carbomList1[${index}].key" value="${orderbom.key}"/>
							</td>
							<td>${orderbom.key}</td>
							<td>${obj["AUFNR"]}</td>
							<td>${obj["MATNR"]}</td>
							<td>${obj["MAKTX1"]}</td>
							<td>${obj["MSEHL1"]}</td>
							<td>${obj["CAR_NUM"]}</td>
							<td>${obj["COMPLETE_NUM"]}</td>
							<td>${obj["MAX_VALUE"]}</td>
							<td>
								<input type="text" maxValue="${obj["MAX_VALUE"]}" maxlength="13" onchange="checkPlanNum(this,'${index}')" size="8" name="carbomList[${index}].planNum" id="carbomList[${index}].planNum" value="${obj['CAR_PLAN_NUM']}"/>
							</td>
							<td>${obj["PXDAT"]}</td>
							<td>${obj["KDAUF"]}</td>
							<td>${obj["PLANT"]}</td>
							<td>${obj["LABEL_CN"]}</td>
						</tr>
						<c:set var="index" value="${index+1}"></c:set>
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
