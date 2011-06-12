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
.eXtremeTable {z-index: 2;width:100%;overflow: auto;}
</style>
		<script type="text/javascript">
		
		
		
	$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 40;
			
			var historyDiv=document.getElementById("historyDiv");
			if(historyDiv!=null){
				historyDiv.style.height="120px";
				height = height - 157;
			}
			document.getElementById("bomDiv").style.height=height+"px";
			
		}	
	</script>
	</head>
	
	
	<body>
		<form
			action="${ctx}/zg/plan/ZgTBomManager/listChangeBom.do"
			method="post" validator="true">
			   <c:if test="${not empty map}">
			   <div class="datalist">
				<h2>
					意见历史
				</h2>
					<div class="toolbar">
					</div>
				<div class="eXtremeTable" style="height:120px" id="historyDiv">
					<table border="0" cellspacing="1" cellpadding="0"
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
							<c:forEach items="${map}" var="objlist" varStatus="nn">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<tr class="${trcss}"
											onmouseover="this.style.backgroundColor = '#EBF1FF'"
											onmouseout="this.style.backgroundColor = '#FFFFFF'">
									<td align="center">
									${objlist.CREATE_TIME}
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
				</div>
			</c:if>
		
						<div class="datalist">
				<h2>
					BOM组件列表
				</h2>
				<div class="toolbar">
				</div>
				
				<div class="eXtremeTable" id="bomDiv">
					<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
						class="tableRegion" width="100%">
						<thead>
							<tr>
								<td class="tableHeader" width="8%">
									订单编号
								</td>
								<td class="tableHeader" width="12%">
									BOM组件编号
								</td>
								<td class="tableHeader">
									BOM组件描述
								</td>
								<td class="tableHeader">
									基本单位
								</td>
								<td class="tableHeader">
								<c:if test="${planType=='CHANGE'}">换料数量</c:if>
								<c:if test="${planType=='BACK'}">退料数量</c:if>
								<c:if test="${planType=='RENEW'}">补领料数量</c:if>
								</td>
								
							</tr>
							
						</thead>
						<tbody>
							<c:forEach items="${bol}" var="obj" varStatus="n">
								
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
								
													<tr class="${trcss}"
														onmouseover="this.style.backgroundColor = '#EBF1FF'"
														onmouseout="this.style.backgroundColor = '#FFFFFF'">
										
										
									<td align="center">
										${obj.AUFNR}
									</td>
									<td align="center">
										${obj.IDNRK}
									</td>
									<td align="center">
										${obj.MAKTX2}
									</td>
									<td align="center">
										${obj.MSEHL2}
									</td>
									<td align="center">
								<c:if test="${planType=='CHANGE'}">${obj.WAIT_BACK_NUM}</c:if>
								<c:if test="${planType=='BACK'}">${obj.WAIT_BACK_NUM}</c:if>
								<c:if test="${planType=='RENEW'}">${obj.CAR_NUM}</c:if>
										
									</td>
								
									</tr>
								
							</c:forEach>
							
						
							
						</tbody>
					</table>
				</div>
		</form>
		
	</body>
</html>
