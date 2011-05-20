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
	<title>选择仓库</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			//if(${fn:length(carPlanList)}==1){
			//	window.returnValue='${carPlanList[0].CUID}';
			//	window.close();
			//}
		});
		function viewCarPlan(carPlanId){
			window.returnValue=carPlanId;
			window.close();
		}
	</script>
</head>
<body>
		
		<div class="noprint">
     
	<form id="carList" name="carList" action="<c:url value="/zg/plan/ZgTcarplan/loadCarList.do"/>" method="post" style="display: inline;" >
	
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title" >装车计划(双击选择)</div>
			<div class="toolbar">
			
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							 <td class="tableHeader" >装车编号</td>
							 <td class="tableHeader" >仓库编号</td>
							 <td class="tableHeader" >仓库名称</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${carPlanList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${index%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击选择" ondblclick="viewCarPlan('${obj.CUID}');">
							<td align="left">${obj.CUID}</td>
							<td align="center">${obj.STORAGE_ID}</td>
							<td align="left">${obj.LGORTNAME}</td>
							
						</tr>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="2" >
							
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