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
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTbackbomDwrAction.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<style>
		.readOnlyInput {
			border: 0;
			background: transparent;
		}
		body,html {overflow:hidden;margin: 0;padding: 0}
	</style>
	<script type="text/javascript">
	$(function(){
		$("input[dateFlag=trueNoImg]")
		.datepicker({
			showAnim:'',
			showOtherMonths: true,
			changeMonth:true,
			changeYear:true,
			selectOtherMonths: true,
			dateFormat:"yy-mm-dd"
		})
		.css("cursor","pointer");
		initGridEditor();
		var maxHeight = top.getContentHeight();
		var tooltableH = document.getElementById("tooltable").offsetHeight;
		document.getElementById("grid-data-panel").style.height = maxHeight - tooltableH - 39 + "px";
	});
	
		function buildupSubmitParams(items){
			var josnString='';
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					var cuid = document.getElementById(i+"cuid").value;
					var cancelNumber = document.getElementById(i+"cancelNumber").value;
					var backNumber = document.getElementById(i+"backNumber").value;
					var oldCancelNumber = document.getElementById(i+"oldCancelNumber").value;
					if(cancelNumber==""){
						var row = i+1;
						alert("第"+row+"行作废数量未填写，不能提交");
						return "false";
					}
					if(oldCancelNumber!="" && parseInt(oldCancelNumber)>0){
						if(parseInt(cancelNumber)>parseInt(backNumber)+parseInt(oldCancelNumber)){
							var row = i+1;
							alert("第"+row+"行作废数量大于退单数量，不能提交");
							return "false";
						}
					}else{
						if(parseInt(cancelNumber)>parseInt(backNumber)){
							var row = i+1;
							alert("第"+row+"行作废数量大于退单数量，不能提交");
							return "false";
						}
					}
					
					if(parseInt(cancelNumber)<0){
						var row = i+1;
						alert("第"+row+"行作废数量不能小于0");
						return "false";
					}
					josnString=josnString+'{' ;
		            josnString = josnString +'"cuid":"'+cuid+'",';
		            josnString = josnString +'"cancelNumber":"'+cancelNumber+'"},';  
				}
			}
	        if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
	    }
	    
	    function saveBackBoms(){
	    	var items = document.getElementsByName("items");
			var flag = false;
			for(var i = 0; i < items.length;i++) {
				if(items[i].checked) {
					flag=true;
					break;
				}
			}
			if(flag==false){
				alert("请选择退单记录！");
				return;
			}
			var jsonString = this.buildupSubmitParams(items);
	    	if(jsonString!="false"){
				ZgTbackbomDwrAction.updateZgTbackbomCancelNumber(jsonString,function(data){
						if(data) {
								window.location.href="${ctx}/zg/plan/ZgTbackBom/findBomList.do?backCuid=${backCuid}"
						}else {
								alert("提交失败！");
						}
				});	
			}
	    }
	    
	</script>
</head>
<body>
<div id="tooltable">
	<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
		<thead>
			<tr>
				<td class="toolbar">
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span onclick="if(parent.doQuery)parent.doQuery()"><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
					<a href="javascript:saveBackBoms()"><span><img src="<%=iconPath%>/action_save.gif" />保存</span></a>
				</td>
			</tr>
		</thead>
	</table>
</div>
<div class="grid-panel">
	<div class="title">退料调整</div>
	<div class="toolbar"></div>
	<div id="grid-data-panel" class="grid-data-panel">
		<form action="${ctx}/zg/plan/ZgTbackBom/saveBoms.do" method="post">
			<table id="bomTable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
						<td class="tableHeader">BOM组件编号</td>
						<td class="tableHeader">BOM组件描述</td>
						<td class="tableHeader">订单编号</td>
						<td class="tableHeader">退单数量</td>
						<td class="tableHeader">作废数量</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bomList}" var="obj" varStatus="n">
					<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<tr class="${trcss}">
						<td width="3%" align="center">
							<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							<input type="hidden" name="${n.count-1}.cuid" id="${n.count-1}cuid" value="${obj.cuid }"/>
							<input type="hidden" name="${n.count-1}.backNumber" id="${n.count-1}backNumber" value="${obj.backNumber }"/>
						</td>
						<td align="center">${obj.idnrk}</td>
						<td align="center">${obj.maktx1}</td>
						<td  align="center">${obj.orderId}</td>
						
						<td  align="center">${obj.backNumber}</td>
						<td  align="center">
						<input type="text" id="${n.count-1}cancelNumber"  name="cancelNumber"  size="6" maxlength="14" value="${obj.cancelNumber}"/>
						<input type="hidden" id="${n.count-1}oldCancelNumber"  name="oldCancelNumber"  size="6" value="${obj.cancelNumber}"/>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>