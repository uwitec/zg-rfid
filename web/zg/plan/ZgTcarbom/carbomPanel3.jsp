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
		<link href="${ctx}/widgets/extremecomponents/extremecomponents.css"
			type="text/css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderbomDwrAction.js'></script>
	<script type='text/javascript' src='${ctx}/dwr/interface/ZgTorderDwrAction.js'></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js"></script>
	<script src="${ctx}/dwr/interface/FwEmployeeDwrAction.js" type=""></script>
	
	<script type="text/javascript">
	
		$(function() {
			init();
		});
		
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var gridDataPanelH = $(top.bodyFrame).height()-$("#infoPanel").height();
			document.getElementById("grid-data-panel").style.height= gridDataPanelH - 67 + 'px';
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
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
			$("form:first").submit();
		}
		
		function checkRealNum(obj) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("实际领取数量必须小于计划领料数量！");
					obj.value = "";
				}
			}else {
				alert("实际领取数量必须为数字！");
				obj.value = "";
			}
		}
		function carPlanSubmit(carPlanId,obj) {
			obj.disabled=true;
			if(!checkNum()){
				obj.disabled=false;
				return;
			}
			if(!check()){
				obj.disabled=false;
				return;
			}
		//	if(!storageUserCheck()){
		//		setInterval('scroll()',1000);
		//		return;
		//	}
		//	var storageUserId=$("#storageUserId").val();
			var result = openDialog1("${ctx}/zg/plan/ZgTcarbom/rfid.jsp?lgort=${carPlan.storageId }",600,280);
			
			if(result==undefined){
				obj.disabled=false;
				return;
			}

			$("#storageUserId").val(result.cuid);
			$("#storageUserName").val(result.labelCn);
		
			$("form:first").attr("action","${ctx}/zg/plan/ZgTcarbom/submitStorePlanForBatch.do");
			$("form:first").submit();
		}
		
		function checkNum(){
			var items = document.getElementsByName("items");
			//alert(items.length );
		    if (items.length > 0) {
		        for (var i = 0; i < items.length; i++){
		        	var carNum=document.getElementById("carbomList["+i+"].realNum").value*1;
		        	if(carNum=='0'){
		        		alert("实际装车数量不能为零，请确认！");
						return;
		        	}
		        	var supItems=document.getElementsByName("items"+i);
		        	var carSupNumAll=0;
		        	//alert(supItems.length);
		        	for (var j = 0; j < supItems.length; j++){
		        	   var carSupNum=document.getElementById("carbomList["+i+"].supList["+j+"].carNum").value;
		        	   carSupNumAll=carSupNumAll+carSupNum*1;
		        	}
		        	if(carNum!=carSupNumAll&&supItems.length>0){
		        		alert("装车数量与具体供应商数量不相等，请确认！");
		        		return false;
		        	}
		        	
		        }
		    }
		    
		    return true;
	    }
		
		function storageUserCheck(){
			if($("#storageUserId").val()==""){
				alert("请先刷卡确认仓管员信息....");
				return false;
			}
			return true;
		}
		
         
         function check(){
			var jsonStr=buildupSubmitParams();
			DWREngine.setAsync(false);
			var result=false;   
			ZgTcarplanDwrAction.checkForSaveOrSubmitBatchStorage(jsonStr,'${carPlanId}',function(data){
				if(data){
					result = true;
				}else{
					alert("目前数据不是最新数据，重新进入页面处理！");
					result = false;
				}
			});
			return result;
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
	    
	    function checkPlanNum(obj,num) {
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			//var realObj = document.getElementById('carbomList['+num+'].realNum');
			if(value=='0'){
				alert("实际发料数量不能为零，请确认！");
				obj.value = oldValue;
				return;
			}
			if(!isNumber(value)) {
				alert("实际发料数量必须为数字！");
				obj.value = oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
			obj.value=value;
		}
		
		
		
		
		
	</script>
</head>
  
<body>
<div id="infoPanel">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<c:if test="${empty view}">
						<a href="javascript:"><span onclick="carPlanSubmit('${carPlanId}',this)"><img src="<%=iconPath%>/true.gif" />确认领料</span></a>
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
							批量领料
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
						<th>
							仓库名称：
						</th>
						<td width="15%">
								${carPlan.storageId_related.value }
							</td>
						
					</tr>
					<tr>
						<th>
							仓管员：
						</th>
						<td width="15%">	
							<input type="text" name="storageUserName" id="storageUserName" readonly="true" value="${ carPlan.storageUserId_related.value}"/>
						</td>
						<th>
						</th>
					</tr>
				</tbody>
			</table>
</div>
<div id="dataPanel">
	<div class="grid-panel" id="grid-panel">
		<div id="title" class="title">批量领料管理</div>
		<div id="toolbar" class="toolbar">
		</div>
		<div id="grid-data-panel" class="grid-data-panel">
			<form action="${ctx}/zg/plan/ZgTcarbom/saveStorePlanForBatch.do" method="post">
				<input type="hidden" name="carPlanId" value="${carPlanId}"/>
				<input type="hidden" name="storageUserId" id="storageUserId" value="${storageUserId }" readonly="true"/>
				<table id="bomTable" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">BOM组件编号</td>
							<td class="tableHeader">BOM组件描述</td>
							<td class="tableHeader">基本单位</td>
							<td class="tableHeader">计划领取数量</td>
							<td class="tableHeader" style="color:red">实际发料数量</td>
							<td class="tableHeader" >备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${bomList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td align="left"><input type="hidden" name="items" />
							<b>${obj["IDNRK"]}</b></td>
							<td align="left"><b>${obj["MAKTX"]}</b></td>
							<td align="center"><b>${obj["MSEHL"]}</b></td>
								<td><b>${obj["PLAN_NUM"]}</b></td>
							<td>
								<input type="hidden" name="carbomList[${n.count-1}].carPlanId" value="${carPlanId}"/>
								<input type="hidden" name="carbomList[${n.count-1}].cuid" value="${obj['CUID']}"/>
							<c:if test="${empty view}">
								<input type="hidden" maxValue="${obj["PLAN_NUM"]}" onchange="checkRealNum(this)" size="8" name="carbomList[${n.count-1}].oldRealNum" id="carbomList[${index}].oldRealNum" value="${obj['REAL_NUM']}"/>
								<input type="text" maxValue="${obj["PLAN_NUM"]}" onchange="checkRealNum(this)" size="8" name="carbomList[${n.count-1}].realNum" value="${obj['REAL_NUM']}" maxlength="13"/>
							</c:if>
							<c:if test="${view=='true'}">
							<b>${obj['REAL_NUM']}</b>
							</c:if>
							</td>
							<td align="center"><B>${obj["ZBZ"]}</b></td>
						</tr>
						<c:forEach items="${obj.supList}" var="sup" varStatus="v">
							<tr class="${trcss}">
								<c:if test="${empty view}">
									<td colSpan="1" align="left">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="items${n.count-1 }" />
												供应商编号：${sup.lifnr}
											</td>
											<td colSpan="1" align="left">
												&nbsp;&nbsp;供应商：${sup.lifnrName}
											</td>
											<td align="center" colSpan="1">
												批次：
												<input type="text" maxlength="80" size="8"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].batchno"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].batchno"
													value="${sup.batchno}" />
											</td>
											<td colSpan="1">
												认证清单：
												<input type="text" maxlength="80" size="8"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].zrzqd"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].zrzqd"
													value="${sup.zrzqd}" />
											</td>
											</td>
											<td align="center">
												<input type="hidden"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"
													value="${sup.cuid}" />
												<input type="hidden"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].carBomId"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"
													value="${sup.carBomId}" />
												<input type="hidden"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].lifnr"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"
													value="${sup.lifnr}" />
												<input type="hidden"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].lifnrName"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].cuid"
													value="${sup.lifnrName}" />
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="text" maxValue="${obj["
													PLAN_NUM"]}" oldValue="${sup.carNum}" maxlength="13"
													onchange="checkPlanNum(this,'${n.count-1}')" size="8"
													name="carbomList[${n.count-1}].supList[${v.count-1 }].carNum"
													id="carbomList[${n.count-1}].supList[${v.count-1 }].carNum"
													value="${sup.carNum}" />
											</td>
											<td></td>
								</c:if>
								<c:if test="${view=='true'}">
									<td colSpan="1" align="left">
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="items${n.count-1 }" />
												供应商编号：${sup.lifnr}
											</td>
											<td colSpan="1" align="left">
												&nbsp;&nbsp;供应商：${sup.lifnrName}
											</td>
											<td align="center" colSpan="1">
												批次：${sup.batchno}
											</td>
											<td colSpan="1">
												认证清单：${sup.zrzqd}
											</td>
											</td>
											<td align="center">
												${sup.carNum}
											</td>
											<td></td>
								</c:if>
									
											
											
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