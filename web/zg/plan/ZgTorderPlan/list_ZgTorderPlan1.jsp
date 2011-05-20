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
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css"
			rel="stylesheet" />
		<script type='text/javascript'
			src='${ctx}/dwr/interface/ZgTorderPlanGroupDwrAction.js'></script>
		<script type="text/javascript">
	
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame0").style.height;
			var maxWidth = parent.document.getElementById("listFrame0").style.width;
			maxHeight = maxHeight.replace("px","")*1;
			maxWidth=maxWidth.replace("px","")*1;
			var height = maxHeight - 30;
			maxWidth=maxWidth-132;
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		function view(orderPlanId) {
			openDialog1('${ctx}/zg/plan/ZgTorderPlan/viewOrderPlanBom.do?orderPlanId='+orderPlanId,"800","530");
		}
		
		function viewAdvance(orderPlanId){
			openDialog1('${ctx}/zg/plan/ZgTorderPlan/viewOrderPlanBomAdvance.do?orderPlanId='+orderPlanId,"800","530");
		}
		
		function pauseOrder(){
			var items=document.getElementsByName("items");
			var groupIds='';
			var flag=true;
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
				    var params=new QueryString(items[i].value);
				    if(params["state"]=='9')
				    {
				    	alert("已经完成的领料单不能恢复领料合并操作，请确认");
				    	flag=false;
				    	break;
				    }
					groupIds=groupIds+params["id"]+",";
				}
			}
			
			if(!flag){
				return;
			}
			if(groupIds.length<1){
				alert("请选择需要操作的订单");
				return;
			}else{
				groupIds=groupIds.substr(0,groupIds.length-1);
			}
			if(confirm("确定暂停该订单领料吗")){
				ZgTorderPlanGroupDwrAction.pauseOrder(groupIds,function(data){
					if(data){
						alert("操作成功");
						parent.document.getElementById("form"+parent.curNum).submit();
					}else{
						alert("系统繁忙 ，请稍后再试");
					}
				});
			}
		}
		
		function revertOrder(){
			var items=document.getElementsByName("items");
			var groupIds='';
			var flag=true;
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
				    var params=new QueryString(items[i].value);
				    if(params["state"]=='4'||params["state"]=='9')
				    {
				    	alert("正在领料或已经完成的领料单不能恢复领料合并操作，请确认");
				    	flag=false;
				    	break;
				    }
					groupIds=groupIds+params["id"]+",";
				}
			}
			
			if(!flag){
				return;
			}
			if(groupIds.length<1){
				alert("请选择需要操作的订单");
				return;
			}else{
				groupIds=groupIds.substr(0,groupIds.length-1);
			}
			if(confirm("确定恢复该订单领料吗")){
				ZgTorderPlanGroupDwrAction.revertOrder(groupIds,function(data){
					if(data){
						parent.document.getElementById("form"+parent.curNum).submit();
						alert("操作成功");
					}else{
						alert("系统繁忙 ，请稍后再试");
					}
				});
			}
		}
		
		function mergeOrder(){
			var items=document.getElementsByName("items");
			var groupIds='';
			var flag=true;
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
				    var params=new QueryString(items[i].value);
				    if(params["state"]=='4'||params["state"]=='9')
				    {
				    	alert("正在领料或已经完成的领料单不能进行合并操作，请确认");
				    	flag=false;
				    	break;
				    }
					groupIds=groupIds+params["id"]+",";
				}
			}
			
			if(!flag){
				return;
			}
			
			if(groupIds.length<1){
				alert("请选择需要操作的订单");
				return;
			}else{
				groupIds=groupIds.substr(0,groupIds.length-1);
			}
			if(confirm("确定合并该订单领料吗")){
				ZgTorderPlanGroupDwrAction.mergeOrder(groupIds,parent.curDate,parent.planType,'',function(data){
					if(data){
						parent.document.getElementById("form"+parent.curNum).submit();
						alert("操作成功");
					}else{
						alert("系统繁忙 ，请稍后再试");
					}
				});
			}
		}
		
		function cancleMergeOrder(){
			var items=document.getElementsByName("items");
			var groupIds='';
			var flag=true;
			for(var i=0;i<items.length;i++){
				if(items[i].checked==true){
				    var params=new QueryString(items[i].value);
				    if(params["state"]=='4'||params["state"]=='9')
				    {
				    	alert("正在领料或已经完成的领料单不能进行取消合并操作，请确认");
				    	flag=false;
				    	break;
				    }
					groupIds=groupIds+params["id"]+",";
				}
			}
			
			if(!flag){
				return;
			}
			if(groupIds.length<1){
				alert("请选择需要操作的订单");
				return;
			}else{
				groupIds=groupIds.substr(0,groupIds.length-1);
			}
			if(confirm("确定取消该订单的合并关系吗")){
				ZgTorderPlanGroupDwrAction.cancleMergeOrder(groupIds,parent.curDate,parent.planType,'',function(data){
					if(data){
						parent.document.getElementById("form"+parent.curNum).submit();
						alert("操作成功");
					}else{
						alert("系统繁忙 ，请稍后再试");
					}
				});
			}
		}
		
		
		
	</script>
	
<style>
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

.graph .bar span {
	left: 1em;
}
</style>
	</head>
	<body>
		<form id="ec" action="<c:url value="/zg/plan/ZgTorderPlan/list.do"/>"
			method="post" style="display: inline;">
			<div>
				<div>
				</div>
				<div class="grid-panel" id="grid-panel">
					<div class="title">
						领料计划管理
					</div>
					<div class="toolbar">
					</div>
					<div id="grid-data-panel" class="grid-data-panel">
						<table cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<td class="tableHeader" >
										状态
									</td>
									<td class="tableHeader" >
										领料进度
									</td>
									<td class="tableHeader" >
										领料进度
									</td>
									<td class="tableHeader" >
										生产排序号
									</td>
									<td class="tableHeader">排产日期</td>
									<td class="tableHeader">排序日期</td>
									<td class="tableHeader" >
										生产订单编号
									</td>
									<td class="tableHeader" >
										线体描述
									</td>
									<td class="tableHeader" >
										原生产线
									</td>
									<td class="tableHeader" >
										生产线
									</td>
									<td class="tableHeader" >
										生产数量
									</td>
									<td class="tableHeader" >
										排序数量
									</td>
									<td class="tableHeader" >
										是否合并
									</td>
									<td class="tableHeader" >
										成品编号
									</td>
									<td class="tableHeader" >
										公司机型
									</td>
									
								
									
									
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${planList}" var="obj" varStatus="n">
										<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									   <!-- 不是提前领料 -->
									  <c:if test="${obj.advance =='0'}">
										  	<c:if test="${obj.notSetBomNum>0}">
										 	 <tr class="${trcss}" title="双击查看(注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" ondblclick="view('${obj.cuid}')">
										  </c:if>
										  <c:if test="${!(obj.notSetBomNum>0)}">
										  	<tr class="${trcss}" title="双击查看" ondblclick="view('${obj.cuid}')">
										  </c:if>
									  </c:if>
									  
									   <!-- 不是提前领料 -->
									  <c:if test="${obj.advance =='1'}">
										  	<c:if test="${obj.notSetBomNum>0}">
										 	 <tr class="${trcss}" title="提前领料的订单，双击查看(注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" ondblclick="viewAdvance('${obj.cuid}')">
										  </c:if>
										  <c:if test="${!(obj.notSetBomNum>0)}">
										  	<tr class="${trcss}" title="提前领料的订单，双击查看" ondblclick="viewAdvance('${obj.cuid}')">
										  </c:if>
									  </c:if>
									  
										

											<td>
											<c:choose>
																	<c:when test="${(pageRequest.filters.planType=='ABE'&&obj.freeZe!='0')||obj.freeZe=='4'}">
																	<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
																	</c:when>
																	
																	<c:otherwise>
																			<c:if test="${obj.state=='-2'}">
																			<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />
																			</c:if>
																		
																			<c:if test="${obj.state=='-1'||obj.state=='-8'||obj.state=='-6'||obj.state=='-7'||obj.state=='-4'}">
																				<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />
																			</c:if>
							
																			<c:if test="${obj.state=='0'}">
																				<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />
																			</c:if>
							
																			<c:if test="${obj.state=='4'}">
																				<img src="<%=iconPath%>/ico_ing.gif" alt="领料中" />
																			</c:if>
							
																			<c:if test="${obj.state=='9'}">
																				<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />
																			</c:if>
																	    </c:otherwise>
																	</c:choose>
																	
											</td>
											<td>
											<div class="graph">
												<strong class="bar" style="width: ${ obj.percent*100}%">
												</strong>
											</div>
									   </td>
									   
									   <!-- 不是提前领料 -->
									  <c:if test="${obj.advance =='0'}">
											  	 <!-- 有未设置规格物料的 -->
											  <c:if test="${obj.notSetBomNum>0}">
												  <td color="red"><span><font color="red"> <fmt:formatNumber  value="${ obj.percent*100}"  pattern="#0.00"/>%</font></span></td>
												  <td align="center">
														<font color="red">	${n.count}</font>
														</td>
													<td align="center" ><font color="red"><fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" /></font></td>
													<td><font color="red"><fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" /></font></td>
													<td align="center">
														<font color="red">	${obj.aufnr}</font>
													</td>
													<td align="center">
														<font color="red">	${obj.plant}</font>
													</td>
												<td align="center">
														<font color="red">	${obj.arbpl1}</font>
													</td>
													<td align="center">
														<font color="red">	${obj.arbpl}</font>
													</td>
													<td align="center">
														<font color="red">	${obj.psmng}</font>
													</td>
													<td align="center">
														<font color="red">	${obj.pmenge}</font>
													</td>
													<td align="center">
														<c:if test="${obj.merge}"><font color="red">是</font></c:if>
														<c:if test="${!obj.merge}"><font color="red">否</font></c:if>
													</td>
													<td align="center">
														<font color="red">	${obj.matnr}</font>
													</td>
													<td align="center">
														<font color="red">	${obj.maktx1}</font>
													</td>
												</c:if>
											<c:if test="${!(obj.notSetBomNum>0)}">
												<td><span> <fmt:formatNumber  value="${ obj.percent*100}"  pattern="#0.00" />% </span></td>
													<td align="center">
															${n.count}
														</td>
													<td><fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" /></td>
													<td><fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" /></td>
													<td align="center">
														${obj.aufnr}
													</td>
													<td align="center">
														${obj.plant}
													</td>
													<td align="center">
														${obj.arbpl1}
													</td>
													<td align="center">
														${obj.arbpl}
													</td>
													<td align="center">
														${obj.psmng}
													</td>
													<td align="center">
														${obj.pmenge}
													</td>
													<td align="center">
														<c:if test="${obj.merge}">是</c:if>
														<c:if test="${!obj.merge}">否</c:if>
													</td>
													<td align="center">
														${obj.matnr}
													</td>
													<td align="center">
														${obj.maktx1}
													</td>
												</c:if>
									  </c:if>
									     
									   <!-- 提前领料 -->
									  <c:if test="${obj.advance =='1'}">
									  		 <td color="red"><span><font color="blue"> <fmt:formatNumber  value="${ obj.percent*100}"  pattern="#0.00"/>%</font></span></td>
												  <td align="center">
														<font color="blue">	${n.count}</font>
														</td>
													<td align="center" ><font color="blue"><fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" /></font></td>
													<td><font color="blue"><fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" /></font></td>
													<td align="center">
														<font color="blue">	${obj.aufnr}</font>
													</td>
													<td align="center">
														<font color="blue">	${obj.plant}</font>
													</td>
												<td align="center">
														<font color="blue">	${obj.arbpl1}</font>
													</td>
													<td align="center">
														<font color="blue">	${obj.arbpl}</font>
													</td>
													<td align="center">
														<font color="blue">	${obj.psmng}</font>
													</td>
													<td align="center">
														<font color="blue">	${obj.pmenge}</font>
													</td>
													<td align="center">
														<c:if test="${obj.merge}"><font color="blue">是</font></c:if>
														<c:if test="${!obj.merge}"><font color="blue">否</font></c:if>
													</td>
													<td align="center">
														<font color="blue">	${obj.matnr}</font>
													</td>
													<td align="center">
														<font color="blue">	${obj.maktx1}</font>
													</td>
									  </c:if>
									  
											
										
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
