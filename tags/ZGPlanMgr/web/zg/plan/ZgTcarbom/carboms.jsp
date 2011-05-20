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
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js"></script>
	<style>
		.extendTd{
		}
		.extendTd .extendBtn{
			height:18px;
		}
		.extendTd .extendBtn img{
			cursor: pointer;
			width:16px;
			height:18px;
		}
		.extendTd .extendBtn label{
			height:18px;
			line-height: 18px;
		}
    	#matnrListDiv {width:15%;height:100%;float:left;text-align: center;}
    	#matnrChildListDiv {width:84%;height:100%;float:left;overflow:auto; }
		#matnrListDiv ul{
			
			
			border: 1px solid #d0e5f5;
			text-align: left;
		}
		#matnrListDiv ul li{
			
			list-style:none;
			white-space:nowrap;
			cursor: pointer;
			font-size: 120%;
		}
		#matnrListDiv ul li:hover{
			 
			white-space:nowrap;
			color: red;
			font-weight: bold;
		}
		#matnrCode {
			font-size: 130%;
			font-weight: bold;
			background-color: #d0e5f5;
			line-height: 20px;
			color: #1d5987;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
				<c:if test="${!(empty firstBom)}">
					openBomList('${firstBom["MATNR"]}');
				</c:if>
			}catch(e){}
		});
		function openBomList(matnr) {
			$("#matnrHide").val(matnr);
			$("#bomListForm").submit();
		}	
		
		function saveBom(){
			document.frames['bomListFrame'].saveBom();
		}
		function delBom(){
			document.frames['bomListFrame'].delBom();
		}
		function addBom(carPlanId){
			document.location.href = "${ctx}/zg/plan/ZgTcarbom/addBom.do?carPlanId="+carPlanId;
		}
		
		function carPlanSubmit(carPlanId) {
			ZgTcarplanDwrAction.submitOneCarPlan(carPlanId,function(data){
				if(data) {
					alert("提交成功！");
					if(parent.doQuery){
						parent.doQuery()
					}
				}else {
					alert("提交失败！");
				}
			});	
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<table width="100%" cellpadding="0" cellspacing="1" style="border:1px solid #A8CFEB;">
	<thead>
		<tr>
			<td class="formToolbar">
				<div class="button" style="text-align: left;">
					<a href="javascript:carPlanSubmit('${carPlanId}')"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
					<a href="javascript:saveBom()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
					<a href="javascript:addBom('${carPlanId}')"><span><img src="<%=iconPath%>/addition.gif" />新增BOM</span></a>
					<a href="javascript:delBom('${carPlanId}')"><span><img src="<%=iconPath%>/ico_005.gif" />删除BOM</span></a>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</div>
			</td>
		</tr>
	</thead>
</table>

<iframe id="orderDetailFrame" src="<c:url value="/zg/plan/ZgTcarplan/show.do?id=${carPlanId}&type=carPlan"/>" autolayout="true" height="100%" width="100%" scrolling="no" frameborder="0"></iframe>
<form id="bomListForm" action="${ctx}/zg/plan/ZgTcarbom/findBomList.do" method="post" target="bomListFrame">
	<input type="hidden" name="carPlanId" value="${carPlanId}"/>
	<input type="hidden" name="items" value="${carPlanId}"/>
	<input type="hidden" id="matnrHide" name="matnr"/>
	<div id="matnrListDiv">
		<div id="matnrCode">
			<input type="checkbox" onclick="setAllCheckboxState('matnrs',this.checked)" value=""/>
			物料编号
			<button class="commonButton queryButton" onclick="openBomList('')">查询</button>
		</div>
		<ul>
			<c:forEach items="${bomList}" var="obj" varStatus="n">
			<li>
				<input type="checkbox" name="matnrs" value="${obj["MATNR"]}"/>
				<label onclick="openBomList('${obj['MATNR']}')">${obj["MATNR"]}</label>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div id="matnrChildListDiv">
		<iframe name="bomListFrame" src="" scrolling="auto" width="100%" height="100%" frameborder="0" autolayout="true" ></iframe>
	</div>

</form>
</body>
</html>