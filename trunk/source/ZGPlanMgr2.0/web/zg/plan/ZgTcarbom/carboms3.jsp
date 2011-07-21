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
	<script type="text/javascript">
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
				$("#bomListForm").submit();
			}catch(e){}
		});
		function saveBom(){
			document.frames['bomListFrame'].saveBom();
		}
		function carPlanSubmit(carPlanId) {
			ZgTcarplanDwrAction.submitOneCarPlan(carPlanId,function(data){
				if(data) {
					alert("提交成功！");
					if(parent.doQuery){
						parent.doQuery();
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
				<c:if test="${empty view}">
					<a href="javascript:"carPlanSubmit('${carPlanId}')><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
					<a href="javascript:saveBom()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
				</c:if>
					<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
				</div>
			</td>
		</tr>
	</thead>
</table>
<iframe id="orderDetailFrame" src="<c:url value="/zg/plan/ZgTcarplan/show.do?id=${carPlanId}&type=storage"/>" autolayout="true" width="100%" scrolling="no" frameborder="0"></iframe>
<form id="bomListForm" action="${ctx}/zg/plan/ZgTcarbom/findBomList3.do?view=${view }" method="post" target="bomListFrame">
	<input type="hidden" name="carPlanId" value="${carPlanId}"/>
	<input type="hidden" id="matnrHide" name="matnr"/>
		<iframe name="bomListFrame" src="" scrolling="auto" width="100%" height="100%" frameborder="0" autolayout="true"  ></iframe>
</form>
</body>
</html>