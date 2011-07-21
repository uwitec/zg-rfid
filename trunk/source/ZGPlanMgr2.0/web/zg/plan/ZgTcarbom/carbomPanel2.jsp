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
			if(!check()){
				return;
			}
			//if(!storageUserCheck()){
			//	return;
			//}
			var storageUserId=$("#storageUserId").val();
			$("form:first").submit();
		}
		
		function submitBom(){
			if(!check()){
				return;
			}
			var result = openDialog1("${ctx}/zg/plan/ZgTcarbom/rfid.jsp?lgort=${carPlan.storageId }",600,280);
			
			if(result==undefined){
				return;
			}

			$("#storageUserId").val(result.cuid);
			$("#storageUserName").val(result.labelCn);
						
		//	if(!storageUserCheck()){
		//		return;
		//	}
			$("form:first").attr("action","${ctx}/zg/plan/ZgTcarbom/submitStorePlan.do");
			$("form:first").submit();
		}
		
		function checkRealNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("实际领取数量必须小于计划领料数量！");
					obj.value = document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
				}
			}else {
				alert("实际领取数量必须为数字！");
				obj.value = document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
			}
			
			//获取‘计划领取数量’改动后的相差
			var discrepancy=obj.value-document.getElementById('carbomList1['+num+'].oldRealNumForUse').value;
			
			//把 ‘计划领取数量’的汇总-‘计划领取数量’改动后的相差
			document.getElementById(document.getElementById('carbomList['+num+'].key').value).innerText=parseInt(document.getElementById(document.getElementById('carbomList['+num+'].key').value).innerText)+discrepancy;
			
			document.getElementById('carbomList1['+num+'].oldRealNumForUse').value=obj.value;
			
		}
		
		
		function storageUserCheck(){
			if($("#storageUserId").val()==""){
				alert("请先刷卡确认仓管员信息....");
				return false;
			}
			return true;
		}
		
		function check(){
			if(checkNum()) {
				var jsonStr=buildupSubmitParams();
				DWREngine.setAsync(false);
				var result=false;
				ZgTcarplanDwrAction.checkForSaveOrSubmitStorage(jsonStr,'${carPlanId}',function(data){
					if(data){
						result = true;
					}else{
						alert("目前数据不是最新数据，重新进入页面处理！");
						result = false;
					}
				});
			}else {
				alert("存在数据未通过验证，请核查！");
			}
			return result;
		}
		
		function checkNum() {
			var count=${count};
			var flag = true;
			for (var i = 0 ; i < count ; i++){
				var obj = document.getElementById("carbomList["+i+"].realNum");
				var max = obj.getAttribute("maxValue")*1;
				var value = obj.value;
				if(isNumber(value)) {
					if(max < value*1) {
						document.getElementById('carbomList['+i+'].realNum').style.color="red";
						flag = false;
					}else {
						document.getElementById('carbomList['+i+'].realNum').style.color="black";
					}
				}else {
					document.getElementById('carbomList['+i+'].realNum').style.color="red";
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
		           		var oldRealNum =document.getElementById("carbomList["+i+"].oldRealNum").value;
		           		var carPlanId ="${carPlanId}";
		           		var cuid=document.getElementById("carbomList["+i+"].cuid").value;
		           		jsonStr = jsonStr + '"cuid":"'+cuid+'",';
		           		jsonStr = jsonStr + '"carPlanId":"'+carPlanId+'",';
		           		jsonStr = jsonStr +'"oldRealNum":"'+oldRealNum+'"},';
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
  
<body >
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<c:if test="${empty view}">
					<a href="javascript:saveBom()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
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
					计划领料
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
					领料部门：
				</th>
				<td width="15%">
				${carPlan.carUser_dept_Id_related.value }
					
				</td>
				<th>
					领料人：
				</th>
				<td width="15%">
					${carPlan.createUserId_related.value }
				</td>

				<th>
					仓库编号：
				</th>
				<td width="15%">
					${carPlan.storageId }
				</td>
			</tr>
			<tr>
				<th>
					仓库名称：
				</th>
				<td width="15%">
						${carPlan.storageId_related.value }
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
		<div id="title" class="title">计划领料管理</div>
		<div id="toolbar" class="toolbar">
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<form action="${ctx}/zg/plan/ZgTcarbom/saveStorePlan.do" method="post">
				<input type="hidden" name="carPlanId" value="${carPlanId}"/>
				<input type="hidden" name="storageUserId" id="storageUserId" value="${storageUserId }" readonly="true"/>
				
				<table id="bomTable" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">物料编码</td>
							<td class="tableHeader">订单编号</td>
							<td class="tableHeader">物料描述</td>
							<td class="tableHeader">度量单位</td>
							<td class="tableHeader">总需求数量</td>
							<td class="tableHeader">仓库领料完成数量</td>
							<td class="tableHeader">装车计划领取数量</td>
							<td class="tableHeader">可领取数量</td>
							<td class="tableHeader" style="color:red">实际发料数量</td>
							<td class="tableHeader">排序日期</td>
							<td class="tableHeader">销售订单号</td>
							<td class="tableHeader">生产厂</td>
							<td class="tableHeader">生产线</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="group" colspan="12" height="25">
								<div class="expandbtn">
									<img src="<%=noexpandIcon %>" type="unexpandAll"/>
									<a href="javascript:">全部展开</a>
								</div>
							</td>
						</tr>
					<c:set var="index" value="0"></c:set>
					<c:forEach items="${orderboms}" var="orderbom">
						<tr>
							<td class="group" colspan="2" height="25">
								<div class="expandbtn">
									<img src="<%=noexpandIcon %>" for="${orderbom.key}"/>
									<a href="javascript:checkChild('${orderbom.key}')" title="选择该组">${orderbom.key}</a>
								</div>
							</td>
							<c:forEach items="${orderbom.value}" var="obj" varStatus="n" begin='0' end='0'>
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
								<c:set var="carPlanNumAll1" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj3" varStatus="t">
								 	<c:set var="carPlanNumAll1" value="${carPlanNumAll1 + obj3.CAR_PLAN_NUM}"/>
								</c:forEach>
								<c:out value='${carPlanNumAll1}'/>
							</td>
							<td align="center">
								<c:set var="maxValueAll" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj4" varStatus="y">
								 	<c:set var="maxValueAll" value="${maxValueAll + obj4.MAX_VALUE}"/>
								</c:forEach>
								<c:out value='${maxValueAll}'/>
							</td>
							<td align="center">
								<c:set var="carPlanNumAll2" value="0"/>
								<c:forEach items="${orderbom.value}" var="obj5" varStatus="q">
								 	<c:set var="carPlanNumAll2" value="${carPlanNumAll2 + obj5.REAL_NUM}"/>
								</c:forEach>
								<div id="${orderbom.key}"><c:out value='${carPlanNumAll2}'/></div>
							</td>
							</c:forEach>
						</tr>
						<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" attr="${orderbom.key}">
							<td>${orderbom.key}</td>
							<td>${obj["AUFNR"]}</td>
							<td>${obj["MAKTX1"]}</td>
							<td>${obj["MSEHL1"]}</td>
							<td>${obj["CAR_NUM"]}</td>
							<td>${obj["COMPLETE_NUM"]}</td>
							<td>${obj["CAR_PLAN_NUM"]}</td>
							<td>${obj["MAX_VALUE"]}</td>
							<td>
								<input type="hidden" name="carbomList[${index}].carPlanId" value="${obj['CAR_PLAN_ID']}"/>
								<input type="hidden" name="carbomList[${index}].cuid" value="${obj['CUID']}"/>
								<input type="hidden" name="carbomList[${index}].key" id="carbomList[${index}].key" value="${orderbom.key}"/>
								<c:if test="${empty view}">
								<input type="hidden" onchange="checkRealNum(this,'${index}')" size="8" name="carbomList[${index}].oldRealNum" value="${obj['REAL_NUM']}"/>
								<input type="hidden" onchange="checkRealNum(this,'${index}')" size="8" name="carbomList1[${index}].oldRealNumForUse" id="carbomList1[${index}].oldRealNumForUse" value="${obj['REAL_NUM']}"/>
								<input type="text" maxValue="${obj["MAX_VALUE"]}" onchange="checkRealNum(this,'${index}')" size="8" name="carbomList[${index}].realNum" value="${obj['REAL_NUM']}"/>
								</c:if>
								<c:if test="${view=='true'}">
								${obj['REAL_NUM']}
								</c:if>
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