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
			maxWidth=maxWidth-10;
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
				
		}
		
		function view(groupId) {
			openDialog1('${ctx}/zg/plan/ZgTorderPlan/viewPlanGroupBom.do?groupId='+groupId,"900","530");
		}
		
		function viewAvance(groupId) {
			openDialog1('${ctx}/zg/plan/ZgTorderPlan/viewPlanGroupBomAdvance.do?groupId='+groupId,"900","530");
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
				    	alert("已经完成的领料单不能进行暂停领料操作，请确认");
				    	flag=false;
				    	break;
				    }
				   if(params["freeZe"]!='0')
				    {
				    	if("${pageRequest.filters.planType}"=='ABE'||params["freeZe"]=='4'){
				    		alert("冻结的订单不能进行暂停领料操作，请确认");
				    		flag=false;
				    		break;
				    	}
				    	
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
			var resultMsg=openDialog1('${ctx}/zg/plan/ZgTorderPlan/toAddPlanZbz.do?groupIds='+groupIds,400,220);
			if(resultMsg=='OK'){
				ZgTorderPlanGroupDwrAction.pauseOrder(groupIds,"${pageRequest.filters.planType}",function(data){
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
				    	alert("正在领料或已经完成的领料单不能进行恢复领料操作，请确认");
				    	flag=false;
				    	break;
				    }
				     if(params["freeZe"]!='0')
				    {
				    	if("${pageRequest.filters.planType}"=='ABE'||params["freeZe"]=='4'){
					    	alert("冻结的订单不能进行恢复领料操作，请确认");
					    	flag=false;
					    	break;
					    }
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
				ZgTorderPlanGroupDwrAction.revertOrder(groupIds,"${pageRequest.filters.planType}",function(data){
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
				    if(params["advance"]=='1'){
				    	alert("提前领料的领料单不能进行合并操作，请确认");
				    	flag=false;
				    	break;
				    }
				    if(params["state"]=='4'||params["state"]=='9')
				    {
				    	alert("正在领料或已经完成的领料单不能进行合并操作，请确认");
				    	flag=false;
				    	break;
				    }
				     if(params["freeZe"]!='0')
				    {
				    	if("${pageRequest.filters.planType}"=='ABE'||params["freeZe"]=='4'){
					    	alert("冻结的订单不能进行合并操作，请确认");
					    	flag=false;
					    	break;
					    }
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
				     if(params["advance"]=='1'){
				    	alert("提前领料的领料单不能进行取消合并操作，请确认");
				    	flag=false;
				    	break;
				    }
				    if(params["state"]=='4'||params["state"]=='9')
				    {
				    	alert("正在领料或已经完成的领料单不能进行取消合并操作，请确认");
				    	flag=false;
				    	break;
				    }
				     if(params["freeZe"]!='0')
				    {
				    	if("${pageRequest.filters.planType}"=='ABE'||params["freeZe"]=='4'){
					    	alert("冻结的订单不能进行取消合并操作，请确认");
					    	flag=false;
					    	break;
					    }
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
		
		function psbhChange(){
			var num=parent.curNum;
			var form= parent.document.getElementById("form"+parent.curNum);
			var planType = form.document.getElementsByName("s_planType")[num].value;
			var pxDate=form.document.getElementsByName("s_pxDate")[num].value;
			var arbpl= form.document.getElementsByName("s_arbpl")[num].value ;
			var viewModel=form.document.getElementsByName("viewModel")[num].value  ;
			var isCurDay=form.document.getElementsByName("s_isCurDay")[num].value  ;
			//alert('psbh_ZgTorderPlan.jsp?planType='+planType+'&pxDate='+pxDate+'&arbpl='+arbpl+'&viewModel='+viewModel+'&isCurDay='+isCurDay);
			openDialog1('psbh_ZgTorderPlan.jsp?planType='+planType+'&pxDate='+pxDate+'&arbpl='+arbpl+'&viewModel='+viewModel+'&isCurDay='+isCurDay,"900","530");
		    //var form=parent.document.getElementById('form'+parent.curNum);
		    form.submit();
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
			method="post" style="display: inline;" target="_blank">
			<div>
				<div>
				</div>
				<div id="grid-panel" class="grid-panel">
					<div class="title">
						领料计划管理
					</div>
					<div class="toolbar">
					<c:if test="${ empty viewModel}">
						<a href="javascript:mergeOrder()"><span><img
									src="<%=iconPath%>/ico_new.gif" alt="合并订单" />合并订单</span> </a> &nbsp;
						<a href="javascript:cancleMergeOrder()"> <span> <img
									src="<%=iconPath%>/ico_upload.gif" alt="取消合并" />取消合并</span> </a> &nbsp;
						<a href="javascript:pauseOrder()"> <span> <img
									src="<%=iconPath%>/ico_upload.gif" alt="暂停领料" />暂停领料</span> </a>&nbsp;

						<a href="javascript:revertOrder()"> <span> <img
									src="<%=iconPath%>/ico_upload.gif" alt="恢复领料" />恢复领料</span> </a>&nbsp;
									
						<c:if test="${ pageRequest.filters.isCurDay=='0'}">
						<a href="javascript:psbhChange()"> <span> <img
									src="<%=iconPath%>/ico_upload.gif" alt="排序调整" />排序调整</span> </a>
									</c:if>
									</c:if>
					</div>
					<div id="grid-data-panel" class="grid-data-panel">
						<table cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<td class="tableHeader" width="3%">
										<input type='checkbox'
											onclick="setAllCheckboxState('items',this.checked)"/>
									</td>
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
										成品需求数量
									</td>
									<td class="tableHeader" >
										成品排序数量
									</td>
									<td class="tableHeader" >
										成品编号
									</td>
									<td class="tableHeader" >
										公司机型
									</td>
									<td class="tableHeader" >
										备注
									</td>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="0"></c:set>
								<c:set var="indexNo" value="0"></c:set>
								<tr>
									<td class="group" colspan="11">
										<div class="expandbtn">
											<img src="<%=expandIcon%>" type="expandAll" />
											<a href="javascript:">全部展开</a>
										</div>
									</td>
								</tr>
								<c:forEach items="${resultMap}" var="group">
									<c:set var="trcss" value="${index%2==0?'odd':'even'}" />
									<c:set var="indexNo" value="${indexNo+1 }"></c:set>
									
									
									<c:if test="${group.key.num >1 }">
										<tr class="${trcss}" title="双击查看" ondblclick="view('${group.key.cuid}')">
											<c:set var="index" value="${index+1}"></c:set>
											<td align="center">
												<div class="expandbtn">
													<img src="<%=expandIcon%>" for="${group.key.cuid}" />
														<c:if test="${group.key.state!='-1'}">
													<input type="checkbox" name="items"
														value="id=${group.key.cuid}&state=${group.key.state}&freeZe=0&advance=${group.key.advance}" />
														</c:if>
												</div>

											</td>
											<td align="center">
												<c:if test="${group.key.state=='-2'}">
													<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />
												</c:if>
											
												<c:if test="${group.key.state=='-1'}">
													<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />
												</c:if>

												<c:if test="${group.key.state=='0'}">
													<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />
												</c:if>

												<c:if test="${group.key.state=='4'}">
													<img src="<%=iconPath%>/ico_ing.gif" alt="领料中" />
												</c:if>

												<c:if test="${group.key.state=='9'}">
													<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />
												</c:if>
											</td>
												<td>
											<div class="graph"> 
												<strong class="bar" style="width: ${ group.key.percent*100}%">
												</strong>
											</div>
									   </td>
									   <td><span> <fmt:formatNumber  value="${ group.key.percent*100}"  pattern="#0.00" />% </span></td>
											<td align="center">
												${indexNo}
											</td>
											<td colspan="9" align="left">
												<span> 订单合并：包括（ ${group.key.labelCn}） 
											</td>
											
										</tr>

									</c:if>

									<c:forEach items="${group.value}" var="obj" varStatus="n">
										<c:set var="trcss" value="${index%2==0?'odd':'even'}" />
										<c:choose>
											<c:when test="${obj.advance =='1'}">
											<!-- 提前领料 -->
														<c:if test="${group.key.num >1 }">
																<tr class="${trcss} unexpand" attr="${group.key.cuid}" title="(提前领料的订单，注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" >
														</c:if>
					
															<c:if test="${group.key.num ==1 }">
																<tr class="${trcss}" title="提前领料的订单，双击查看(注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" ondblclick="viewAvance('${group.key.cuid}')">
															</c:if>
					
															<td>
																<c:if test="${group.key.num ==1 }">
																<c:if test="${group.key.state!='-1'}">
																	<input type="checkbox" name="items"
																			value="id=${group.key.cuid}&state=${group.key.state}&freeZe=${obj.freeZe }&advance=${obj.advance}" />
																			</c:if>
																</c:if>
															</td>
																<c:if test="${group.key.num ==1 }">
																<td>
																	<c:choose>
																	<c:when test="${(pageRequest.filters.planType=='ABE'&&obj.freeZe!='0')||obj.freeZe=='4'}">
																	
																	<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
																	</c:when>
																	
																	<c:otherwise>
																			<c:if test="${group.key.state=='-2'}">
																			<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />
																			</c:if>
																		
																			<c:if test="${group.key.state=='-1'}">
																				<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />
																			</c:if>
							
																			<c:if test="${group.key.state=='0'}">
																				<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />
																			</c:if>
							
																			<c:if test="${group.key.state=='4'}">
																				<img src="<%=iconPath%>/ico_ing.gif" alt="领料中" />
																			</c:if>
							
																			<c:if test="${group.key.state=='9'}">
																				<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />
																			</c:if>
																			</c:otherwise>
																	</c:choose>
																</td>
															<c:if test="${group.key.num >1 }">
																<td></td><td></td>
															</c:if>
																<c:if test="${group.key.num ==1 }">
																<td>
																<div class="graph">
																	<strong class="bar" style="width: ${ group.key.percent*100}%">
																	</strong>
																</div>
														   </td>
														   		 <td><span> <font color="blue"><fmt:formatNumber  value="${ group.key.percent*100}"  pattern="#0.00" />%</font> </span></td>
														   
															</c:if>
																<td align="center">
																	<font color="blue">${indexNo}</font>
																</td>
															</c:if>
															<c:if test="${group.key.num >1 }">
																<td colspan="4"></td>
															</c:if>
															<td><font color="blue"><fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" /></font></td>
															<td><font color="blue"><fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" /></font></td>
															<td align="center">
																<font color="blue">${obj.aufnr}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.plant}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.arbpl1}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.arbpl}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.psmng}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.pmenge}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.matnr}</font>
															</td>
															<td align="center">
																<font color="blue">${obj.maktx1}</font>
															</td>
															<td align="left" title="${obj.zbz}">
															<c:if test="${fn:length(obj.zbz)>10 }">
																<font color="blue">${fn:substring(obj.zbz,0,10) }...</font>
															</c:if>
															<c:if test="${fn:length(obj.zbz)<10 }">
																		<font color="blue">${obj.zbz}</font>
															</c:if>
														
															</td>
											</c:when>
										
											<c:otherwise>
												<c:choose>
														<c:when test="${obj.notSetBomNum>0}">
															<c:if test="${group.key.num >1 }">
																<tr class="${trcss} unexpand" attr="${group.key.cuid}" title="(注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" >
															</c:if>
					
															<c:if test="${group.key.num ==1 }">
																<tr class="${trcss}" title="双击查看(注：此订单存在尚未设置装车规格的BOM，会导致无法领料)" ondblclick="view('${group.key.cuid}')">
															</c:if>
					
															<td>
																<c:if test="${group.key.num ==1 }">
																<c:if test="${group.key.state!='-1'}">
																	<input type="checkbox" name="items"
																			value="id=${group.key.cuid}&state=${group.key.state}&freeZe=${obj.freeZe }&advance=${obj.advance}" />
																			</c:if>
																</c:if>
															</td>
																<c:if test="${group.key.num ==1 }">
																<td>
							
																	<c:choose>
																	<c:when test="${(pageRequest.filters.planType=='ABE'&&obj.freeZe!='0')||obj.freeZe=='4'}">
																	
																	<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
																	</c:when>
																	
																	<c:otherwise>
																			<c:if test="${group.key.state=='-2'}">
																			<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />
																			</c:if>
																		
																			<c:if test="${group.key.state=='-1'}">
																				<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />
																			</c:if>
							
																			<c:if test="${group.key.state=='0'}">
																				<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />
																			</c:if>
							
																			<c:if test="${group.key.state=='4'}">
																				<img src="<%=iconPath%>/ico_ing.gif" alt="领料中" />
																			</c:if>
							
																			<c:if test="${group.key.state=='9'}">
																				<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />
																			</c:if>
																			</c:otherwise>
																	</c:choose>
																
																	
																	
																	
																	
																</td>
															<c:if test="${group.key.num >1 }">
																<td></td><td></td>
															</c:if>
																<c:if test="${group.key.num ==1 }">
																<td>
																<div class="graph">
																	<strong class="bar" style="width: ${ group.key.percent*100}%">
																	</strong>
																</div>
														   </td>
														   		 <td><span> <font color="red"><fmt:formatNumber  value="${ group.key.percent*100}"  pattern="#0.00" />%</font> </span></td>
														   
															</c:if>
																<td align="center">
																	<font color="red">${indexNo}</font>
																</td>
															</c:if>
															<c:if test="${group.key.num >1 }">
																<td colspan="4"></td>
															</c:if>
															<td><font color="red"><fmt:formatDate value="${obj.pcdat}" pattern="yyyy-MM-dd" /></font></td>
															<td><font color="red"><fmt:formatDate value="${obj.pxdat}" pattern="yyyy-MM-dd" /></font></td>
															<td align="center">
																<font color="red">${obj.aufnr}</font>
															</td>
															<td align="center">
																<font color="red">${obj.plant}</font>
															</td>
															<td align="center">
																<font color="red">${obj.arbpl1}</font>
															</td>
															<td align="center">
																<font color="red">${obj.arbpl}</font>
															</td>
															<td align="center">
																<font color="red">${obj.psmng}</font>
															</td>
															<td align="center">
																<font color="red">${obj.pmenge}</font>
															</td>
															<td align="center">
																<font color="red">${obj.matnr}</font>
															</td>
															<td align="center">
																<font color="red">${obj.maktx1}</font>
															</td>
																</td>
															<td align="left" title="${obj.zbz}">
															<c:if test="${fn:length(obj.zbz)>10 }">
																<font color="red">${fn:substring(obj.zbz,0,10) }...</font>
															</c:if>
															<c:if test="${fn:length(obj.zbz)<10 }">
																		<font color="red">${obj.zbz}</font>
															</c:if>
														
															</td>
												</c:when>
												<c:otherwise>
															<c:if test="${group.key.num >1 }">
																<tr class="${trcss} unexpand" attr="${group.key.cuid}" >
															</c:if>
					
															<c:if test="${group.key.num ==1 }">
																<tr class="${trcss}" title="双击查看" ondblclick="view('${group.key.cuid}')">
															</c:if>
					
															<td>
																<c:if test="${group.key.num ==1 }">
																<c:if test="${group.key.state!='-1'}">
																	<input type="checkbox" name="items"
																			value="id=${group.key.cuid}&state=${group.key.state}&freeZe=${obj.freeZe }&advance=${obj.advance}" />
																			</c:if>
																</c:if>
															</td>
																<c:if test="${group.key.num ==1 }">
																<td>
							
																	<c:choose>
																	<c:when test="${(pageRequest.filters.planType=='ABE'&&obj.freeZe!='0')||obj.freeZe=='4'}">
																	<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
																	</c:when>
																	
																	<c:otherwise>
																			<c:if test="${group.key.state=='-2'}">
																			<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />
																			</c:if>
																		
																			<c:if test="${group.key.state=='-1'}">
																				<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />
																			</c:if>
							
																			<c:if test="${group.key.state=='0'}">
																				<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />
																			</c:if>
							
																			<c:if test="${group.key.state=='4'}">
																				<img src="<%=iconPath%>/ico_ing.gif" alt="领料中" />
																			</c:if>
							
																			<c:if test="${group.key.state=='9'}">
																				<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />
																			</c:if>
																	    </c:otherwise>
																	</c:choose>
																	
																	
																	
																	
																</td>
															<c:if test="${group.key.num >1 }">
																<td></td><td></td>
															</c:if>
																<c:if test="${group.key.num ==1 }">
																<td>
																<div class="graph">
																	<strong class="bar" style="width: ${ group.key.percent*100}%">
																	</strong>
																</div>
														   </td>
														   		 <td><span> <fmt:formatNumber  value="${ group.key.percent*100}"  pattern="#0.00" />% </span></td>
														   
															</c:if>
																<td align="center">
																	${indexNo}
																</td>
															</c:if>
															<c:if test="${group.key.num >1 }">
																<td><c:if test="${(pageRequest.filters.planType=='ABE'&&obj.freeZe!='0')||obj.freeZe=='4'}">
																<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
																</c:if></td>
																<td colspan="3"></td>
															</c:if>
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
																${obj.matnr}
															</td>
															<td align="center">
																${obj.maktx1}
															</td>
															<td align="left" title="${obj.zbz}">
															<c:if test="${fn:length(obj.zbz)>10 }">
																${fn:substring(obj.zbz,0,10) }...
															</c:if>
															<c:if test="${fn:length(obj.zbz)<10 }">
																	${obj.zbz}
															</c:if>
														
															</td>
												</c:otherwise>
												
												</c:choose>
											
											</c:otherwise>
										
										
										</c:choose>
										
										</tr>
										<c:set var="index" value="${index+1}"></c:set>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
