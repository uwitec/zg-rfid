<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String expandIcon = basePath
			+ "/resources/images/frame/ico_expand.gif";
	String noexpandIcon = basePath
			+ "/resources/images/frame/ico_noexpand.gif";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>BOM详细列表</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/CommonDwrAction.js"></script>
		<script type="text/javascript"
			src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}

body,html {
	
	margin: 0;
	padding: 0
}

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

.graph .bar1 {
	display: block;
	background: #ff0000;;
	text-align: center;
	color: #333;
	height: 2em;
	line-height: 2em;
	
}


.graph .bar span {
	left: 1em;
}
</style>
		<script type="text/javascript">
		var num=${fn:length(orderList)};
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight =  top.dialogHeight;
			maxHeight = maxHeight.replace("px","")*1;
			var formHeight=document.getElementById("formitem").offsetHeight;
			var height = maxHeight - formHeight-28;
			if(dealIEVersion(6)){
				height=height-25;
			}
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-panel").style.height=height+'px';
			
			//document.getElementById("orderInfoDive").style.height=maxHeight+'px';
			
		}
	</script>
	</head>
	<body>
		<div id="orderInfoDive">
		<table class="formitem" id="formitem" width="100%" cellpadding="0" cellspacing="1"
			style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
			<thead>
				<tr>
					<td class="title" colspan="8">
						订单信息
					</td>
				</tr>
			</thead>
			
			<c:if test="${fn:length(orderList)>1}">
			<tbody id="tbody_1" style="display: block">
				<tr>
					<th width="10%">
						包含订单：
					</th>
					
					<c:set var="orders" value=""></c:set>
								<c:forEach items="${orderList}" var="obj" varStatus="n">
									<c:if test="${n.count==1}">
										<c:set var="orders" value="${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count>1}">
										<c:set var="orders" value="${orders},${obj.aufnr }"></c:set>
									</c:if>
									<c:if test="${n.count%8==0}">
										<c:set var="orders" value="${orders}<br/>"></c:set>
									</c:if>
								</c:forEach>
					<td width="90%" title="${fn: replace(orders,'<br/>','')}">
								<c:if test="${fn:length(orders)>320}">
								${fn:substring(orders,0,320)}...
								</c:if>
									<c:if test="${fn:length(orders)<=320}">
								${orders }
								</c:if>
							</td>
				</tr>
			</tbody>
			</c:if>
			<c:if test="${fn:length(orderList)==1}">
			<c:forEach items="${orderList}" var ="obj">
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
						生产排产日期：
					</th>
					<td width="15%">
					<fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" />
					</td>
					<th>
						生产订单编号：
					</th>
					<td width="15%">
					${obj.aufnr }
					</td>
					<th>
						生产线：
					</th>
					<td width="15%">
					${obj.arbpl }
					</td>
<th>
						客户机型：
					</th>
					<td width="15%">
						${obj.maktx2 }
					</td>
					
				</tr>
				<tr>

					<th>
						销售单号：
					</th>
					<td width="15%">
					${obj.kdauf }
					</td>
					<th>
						生产厂：
					</th>
					<td width="15%">
							${obj.plant }
					</td>
					<th>
						公司机型 ：
					</th>
					<td >
						${obj.maktx1 }
					</td>
						<th>
						成品需求数量 ：
					</th>
					<td>
						${obj.psmng }
					</td>
				
				</tr>
				<tr>
					
<th>
						成品排序数量：
					</th>
					<td width="15%">
					${obj.pmenge }
					</td>
					<th>
						成品编号：
					</th>
					<td width="15%">
							${obj.matnr }
					</td>
					<th>
					</th>
					<td >
					</td>
				</tr>
			</tbody>
			</c:forEach>
			</c:if>
		</table>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">
				BOM列表
			</div>
			<div class="toolbar"></div>
			<div id="grid-data-panel" class="grid-data-panel">
				<form action="${ctx}/zg/plan/ZgTorderPlanbom/saveOrderPlan.do"
					method="post">
					<c:set var="index" value="0"></c:set>
					<table id="bomTable" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<td class="tableHeader" width="3%"></td>
								<td class="tableHeader">
									BOM编码
								</td>
								<td class="tableHeader" width="20%">
									领料进度
								</td>
								<td class="tableHeader" width="20%">
									领料进度
								</td>
								<td class="tableHeader"  width="10%">
									物料描述
								</td>
								<td class="tableHeader"  width="10%">
									订单编号
								</td>
								<td class="tableHeader"  width="10%">
									单台用量
								</td>
								<td class="tableHeader"  width="10%">
									需求数量
								</td>
								<td class="tableHeader"  width="10%">
									已装车数量
								</td>
								<td class="tableHeader"  width="10%">
									库存地点
								</td>
								<td class="tableHeader"  width="10%">
									单位
								</td>
								<td class="tableHeader"  width="*">
									大小量岗
								</td>
								<td class="tableHeader"  width="*">
									待退料数量
								</td>
								
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="group" colspan="11" height="25">
									<div class="expandbtn">
										<img src="<%=noexpandIcon%>" type="unexpandAll" />
										<a href="javascript:">全部展开</a>
									</div>
								</td>
							</tr>
							<c:forEach items="${orderboms}" var="orderbom">
								<c:if test="${fn:length(orderbom.value)>1}">
									<tr>
										<td class="group" height="25">
											<div class="expandbtn">
												<img src="<%=noexpandIcon%>" for="${orderbom.key}" />
											</div>
										</td>
										<td colspan="1">
											${orderbom.key}
										</td>
										<c:set var="mengeAll" value="0" />
										<c:set var="completeNumAll" value="0" />
										<c:set var="waitBackNumAll" value="0" />
												<c:forEach items="${orderbom.value}" var="obj1"
													varStatus="m">
													<c:set var="mengeAll" value="${mengeAll + obj1.menge}" />
														<c:set var="waitBackNumAll" value="${waitBackNumAll + obj1.waitBackNum}" />
													<c:set var="completeNumAll"	value="${completeNumAll + obj1.completeNum}" />
												</c:forEach>
												
										
										<td>
											<c:set var="per" value="${completeNumAll/mengeAll*100}"></c:set>
											
											<c:if test="${completeNumAll>mengeAll}">
														<div class="graph">
															<strong class="bar1" style="width: ${per }%;">
															</strong>
														</div>
													</c:if>
													<c:if test="${completeNumAll<=mengeAll}">
														<div class="graph">
															<strong class="bar" style="width: ${per }%;">
															</strong>
														</div>
													</c:if>
											</td>
										<td align="center">
											<c:if test="${completeNumAll>mengeAll}">
														<font  color="#ff0000" > <fmt:formatNumber  value="${per}"  pattern="#0.00" />%</font>
													</c:if>
												<c:if test="${completeNumAll<=mengeAll}">
														<font> <fmt:formatNumber  value="${per}"  pattern="#0.00" />%</font>
													</c:if>
											</td>
										<c:forEach items="${orderbom.value}" var="obj" varStatus="n"
											begin='0' end='0'>
											<td class="group">
												<c:out value='${obj.maktx2}' />
											</td>
											<td align="center">

											</td>
											<td align="center">
												<c:out value='${obj.zdtyl}' />
											</td>
											<td align="center">
												
												<c:out value='${mengeAll}' />
											</td>
											<td align="center">
												<c:out value='${completeNumAll}' />
												
											</td>
											
											

											<td align="center"><c:out value='${obj.lgort_lableCn}' /></td>
											<td align="center"><c:out value='${obj.msehl2}' /></td>
											<td align="center"><c:out value='${obj.zbz}' /></td>
											<td align="center"><c:out value='${waitBackNumAll}' /></td>
										</c:forEach>
									</tr>
								</c:if>
								<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
									<c:set var="index" value="${index+1}"></c:set>
									<c:set var="trcss" value="${index%2==0?'odd':'even'}" />
									
									<c:choose>
										<c:when test="${(empty obj.carId) || (empty obj.carnum ) }">
											<c:if test="${fn:length(orderbom.value)>1}">
												<tr class="${trcss}" attr="${orderbom.key}" title="注：该BOM物料没有设定装车规格，会导致无法领料">
											</c:if>
											<c:if test="${fn:length(orderbom.value)==1}">
												<tr class="${trcss}" title="注：该BOM物料没有设定装车规格，会导致无法领料">
											</c:if>
		
											<td width="3%" align="center">
											</td>
											<c:if test="${fn:length(orderbom.value)>1}">
												<td align="left">
													<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;${obj.idnrk}</font>
												</td>
												<td>
												</td>
												<td>
												</td>
											</c:if>
											<c:if test="${fn:length(orderbom.value)==1}">
												<td align="left">
												<font color="red">${obj.idnrk}</font>
												</td>
												<td>
													<c:if test="${obj.completeNum>obj.menge}">
														<div class="graph">
															<strong class="bar1" style="width: ${obj.per }%;">
															</strong>
														</div>
													</c:if>
													<c:if test="${obj.completeNum<=obj.menge}">
														<div class="graph">
															<strong class="bar" style="width: ${obj.per }%;">
															</strong>
														</div>
													</c:if>
											</td>
											<td>
											<c:if test="${obj.completeNum>obj.menge}">
														<font  color="#ff0000" > <fmt:formatNumber  value="${obj.per}"  pattern="#0.00" />%</font>
													</c:if>
													<c:if test="${obj.completeNum<=obj.menge}">
														<font> <fmt:formatNumber  value="${obj.per}"  pattern="#0.00" />%</font>
													</c:if>
											</td>
											</c:if>
											
											<td align="left"><font  color="red">${obj.maktx2}</font></td>
											<td align="left" ><font color="red">${obj.aufnr}</font></td>
											<td align="center" ><font color="red">${obj.zdtyl}</font></td>
											<td align="center" ><font color="red">${obj.menge}</font></td>
											<td align="center" ><font color="red">${obj.completeNum}</font>	</td>
											<td align="center" ><font color="red">${obj.lgort_lableCn}</font></td>
											<td align="center" ><font color="red">${obj.msehl2}</font></td>
											<td align="center" >
												<font color="red"><c:out value='${obj.zbz}' /></font>
											</td>
									
										</c:when>
										<c:otherwise>
											<c:if test="${fn:length(orderbom.value)>1}">
												<tr class="${trcss}" attr="${orderbom.key}">
											</c:if>
											<c:if test="${fn:length(orderbom.value)==1}">
												<tr class="${trcss}">
											</c:if>
		
											<td width="3%" align="center">
											</td>
											<c:if test="${fn:length(orderbom.value)>1}">
												<td align="left">
													&nbsp;&nbsp;&nbsp;&nbsp;${obj.idnrk}
												</td>
												<td>
												</td>
												<td>
												</td>
											</c:if>
											<c:if test="${fn:length(orderbom.value)==1}">
												<td align="left">
												 ${obj.idnrk}
												</td>
													<td>
													<c:if test="${obj.completeNum>obj.menge}">
														<div class="graph">
															<strong class="bar1" style="width: ${obj.per }%;">
															</strong>
														</div>
													</c:if>
													<c:if test="${obj.completeNum<=obj.menge}">
														<div class="graph">
															<strong class="bar" style="width: ${obj.per }%;">
															</strong>
														</div>
													</c:if>
											</td>
											<td>
												<c:if test="${obj.completeNum>obj.menge}">
														<font  color="#ff0000" > <fmt:formatNumber  value="${obj.per}"  pattern="#0.00" />%</font>
													</c:if>
													<c:if test="${obj.completeNum<=obj.menge}">
														<font> <fmt:formatNumber  value="${obj.per}"  pattern="#0.00" />%</font>
													</c:if>
											</td>
											</c:if>
											
											<td align="left">${obj.maktx2}</td>
											<td align="left">${obj.aufnr}</td>
											<td align="center">${obj.zdtyl}</td>
											<td align="center">${obj.menge}</td>
											<td align="center">${obj.completeNum}	</td>
											<td align="center">${obj.lgort_lableCn}</td>
											<td align="center">${obj.msehl2}</td>
											<td align="center">
												<c:out value='${obj.zbz}' />
											</td>
													<td align="center">
												<c:out value='${obj.waitBackNum}' />
											</td>
										</c:otherwise>
									</c:choose>

									


									</tr>
								</c:forEach>
							</c:forEach>

						</tbody>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>