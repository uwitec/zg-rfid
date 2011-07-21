<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String orderPlanId = request.getParameter("orderPlanId");
	System.out.println(orderPlanId);
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link href="${ctx}/widgets/extremecomponents/extremecomponents.css"
			type="text/css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}
</style>
		<script type="text/javascript">
		
		
		
		$(function() {
			try{
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
				doLayout() ;
				
			}catch(e){}
		});
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("resconfigResult").style.height;
		//	alert(maxHeight);
		//	maxHeight = maxHeight.replace("px","")*1;
		//	var height = maxHeight - 17;
		//	document.getElementById("grid-data-panel").style.height=height+'px';
		}
		
		
	
		
		
		
	
		
	</script>
	</head>
	<body>
		<form
			action="${ctx}/zg/plan/ZgTBomManager/queryHistory.do"
			method="post" validator="true">
			
			<div class="datalist">
				<h2>
					意见历史
				</h2>
					<div class="toolbar">
					</div>
				<div class="eXtremeTable">
					<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="20%">
									审核时间
								</td>
								<td class="tableHeader">
									审核人
								</td>
								<td class="tableHeader">
									审核意见
								</td>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${map}" var="objlist" varStatus="n">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<tr class="${trcss}"
											onmouseover="this.style.backgroundColor = '#EBF1FF'"
											onmouseout="this.style.backgroundColor = '#FFFFFF'">
									<td align="center">
										<fmt:formatDate value="${objlist.CREATE_TIME}" pattern="yyyy-MM-dd" />
									</td>
									<td align="center">
										${objlist.LABEL_CN}
									</td>
									<td align="center">
										${objlist.CONTENT}
									</td>
									</tr>
							</c:forEach>
							
						</tbody>
						</table>
				</div>
			
		</form>
		
	</body>
</html>
