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
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type='text/javascript'	src='${ctx}/dwr/interface/ZgTcarplanDwrAction.js'></script>
	<script type="text/javascript">
	
		$(function() {
			init();
			if("${onload}"=="onload"){
				ZgTcarplanDwrAction.checkHasCarPlan("${pageRequest.filters.planType}",function(data){
					if(data){
						viewCarPlan();
						window.parent.$("#onload").val("");
					}
				});
			}
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame"+parent.num).style.height;
			var maxWidth = parent.document.getElementById("listFrame0").style.width;
			maxWidth=maxWidth.replace("px","")*1;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			maxWidth=maxWidth-10;
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-data-panel").style.width=(maxWidth-122)+'px';
			document.getElementById("grid-panel").style.width=(maxWidth-122)+'px';
		}
		
		function edit(cuid){
			if(parent.targetFrame) {
				var orderPlanType=document.getElementById('s_orderPlanType').value;
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel.do?carPlanId='+cuid+'&orderPlanType='+orderPlanType);
			}
		}
		
		function loadCar(groupId,bomCuids,carCode,carId,lgortName,lgort,aufnr,maktx1){
		//	ZgTcarplanDwrAction.checkForLgort(lgort,"${pageRequest.filters.planType}",function(data){
			//	if(!data){
			//		alert("不能追加不同仓库的物料，请确认!");
			//		return;
			//	}
				var result = openDialog1("${ctx}/zg/plan/ZgTcarplan/loadCar.do?groupId="+groupId+"&bomCuids="+bomCuids+"&carCode="+carCode+"&planType=${pageRequest.filters.planType}&carId="+carId+"&lgort="+lgort+"&lgortName="+URLEncoder.encode(lgortName)+"&advance=${pageRequest.filters.advance}&groupId="+parent.groupId,800,500);
				if(result=='turn'){//重置订单
					window.parent.location.reload();
				}else{
					if(result!=undefined){
						var form=window.parent.document.getElementById("form"+parent.num);
						form.submit();
					}
					
				}
				
			//});
		
		}
		
		function viewCarPlan(){
			ZgTcarplanDwrAction.checkHasCarPlan("${pageRequest.filters.planType}",function(data){
				if(!data){
					alert("该领料员目前没有任何装车计划，请确认!");
					return;
				}
				ZgTcarplanDwrAction.getCarPlanByUserId("${pageRequest.filters.planType}",function(data){
					var carPlanId ='';
					if(data!=''){
						carPlanId=data;
					}else{
						carPlanId = openDialog1("${ctx}/zg/plan/ZgTcarplan/viewCarPlanList.do?groupId=${pageRequest.filters.groupId}&bomCuids=&carCode=&planType=${pageRequest.filters.planType}&carId=&groupId="+parent.groupId,400,300);
					
					}
					if(carPlanId!=undefined){
						var result = openDialog1("${ctx}/zg/plan/ZgTcarplan/loadCar.do?groupId=${pageRequest.filters.groupId}&bomCuids=&carCode=&planType=${pageRequest.filters.planType}&carId=&groupId="+parent.groupId+"&carPlanId="+carPlanId,800,500);
					}
			
					if(result=='turn'){//重置订单
						window.parent.location.reload();
					}else{
						if(result!=undefined){
							var form=window.parent.document.getElementById("form"+parent.num);
							form.submit();
						}
					}
				});
				
				
				
			});
			
		}
		
		function setTitle(obj,lockNum,taskBomId){
			if(lockNum>0){
				ZgTcarplanDwrAction.getLockUser(taskBomId,function(data){
					if(data.length>0){
						obj.title="提示："+data+"　用户锁定装车计划！";
					}
				});
				
			}
			
		}
		
			
		
	</script>
	
	
<style>
	
</style>
</head>
<body>
	<form id="ec" action="<c:url value="/zg/plan/ZgTcarplan/list.do"/>" method="post" style="display: inline;">
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">装车计划</div>
			<div class="toolbar">
			<a href="javascript:viewCarPlan()"><span><img
									src="<%=iconPath%>/ico_new.gif" alt="装车计划" />装车计划</span> </a> &nbsp;
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader" width="3%">
							</td>
							 <td class="tableHeader" >BOM组件编号</td>
							 <td class="tableHeader" >点击装车</td>
							 <td class="tableHeader" >领料进度</td>
							  <td class="tableHeader" >领料进度</td>
							 <td class="tableHeader" >可装车</td>
							 <td class="tableHeader" >物料描述</td>
						
							 <c:if test="${pageRequest.filters.planType=='CHANGE'||pageRequest.filters.planType=='BACK'}">
										 <td class="tableHeader" >物料类型</td>
										 <td class="tableHeader" >备注</td>
							</c:if>
							 <td class="tableHeader">订单编码 </td>
							 <td class="tableHeader">单台用量</td>
							 <td class="tableHeader">物料需求</td> 
							 <td class="tableHeader" >已装车数量</td>
							 <td class="tableHeader" >剩余数量</td>
							  <td class="tableHeader" >待退料数量</td>
							 <td class="tableHeader" >车型编号</td>
							  <td class="tableHeader" >车型名称</td>
							 <td class="tableHeader">库存地点</td>
							 <td class="tableHeader" >大小量纲</td>
							  
							
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderboms}" var="orderbom">
					<c:set var="count" value="1"></c:set>
					<c:if test="${fn:length(orderbom.value)>1}">
						<c:set var="count" value="2"></c:set>
							<tr>
							<td class="group" height="25">
								<div class="expandbtn">
									<img src="<%=noexpandIcon %>" for="${orderbom.key.idnrk}"/>
								</div>
							</td>
							
							<c:set var="mengeAll" value="0"/>
							<c:set var="carNumAll" value="0"/>
							
							<c:set var="completeAll" value="0"/>
							<c:set var="carCountAll" value="0"/>
							<c:set var="bomCuid" value=""></c:set>
							<c:set var="validNum" value="0"></c:set>
							<c:set var="waitBackNumAll" value="0"></c:set>
								<c:forEach items="${orderbom.value}" var="obj1" varStatus="m">
								 	<c:set var="carNumAll" value="${carNumAll + obj1.carNum}"/>
								 	<c:set var="mengeAll" value="${mengeAll + obj1.menge}"/>
								 	<c:set var="completeAll" value="${completeAll + obj1.completeNum}"/>
								 	<c:set var="validNumAll" value="${validNumAll + obj1.validNum}"/>
								 	<c:set var="carCountAll" value="${carCountAll + obj1.carCount}"/>
								 		 	<c:set var="waitBackNumAll" value="${waitBackNumAll + obj1.waitBackNum}"/>
								 	<c:set var="bomCuid" value="${bomCuid}${obj1.taskBomId },"></c:set>
								</c:forEach>
							<c:set var="percent" value="${completeAll/carNumAll}"></c:set>
							<td>${orderbom.key.idnrk}</td>
							
									   
							<c:forEach items="${orderbom.value}" var="obj" varStatus="n" begin='0' end='0'>
							<td align="center">
									 <a href="javascript:loadCar('${pageRequest.filters.groupId}','${bomCuid}','${obj.carCode }','${obj.carId }','${obj.lgortName}','${obj.lgort }','${orderbom.value[0].aufnr}','${orderbom.value[0].maktx1}')"><img src="<%=iconPath%>/btn_hd_exit.gif" /><a>
							</td>
							<td>
											<c:choose>
												<c:when test="${obj.waitBackNum!=0&&not empty obj.waitBackNum}">
												<div class="graph" title="待退料">
													<strong class="bar1" style="width: ${ percent*100}%">
													</strong>
													</div>
												</c:when>
												<c:otherwise>
												<div class="graph">
													<strong class="bar" style="width: ${percent*100}%">
													</strong>
												</div>	
												</c:otherwise>
											</c:choose>
							</td>
							<td align="center"><span><fmt:formatNumber value="${percent*100}" pattern="##.##"  minFractionDigits="2"/>% </span></td>
							<td align="center">
								 <span class="carfont">	${carCountAll}车</span>
							</td>
							<td class="group">
								 <c:out value='${obj.maktx2}'/>
							</td>
							<td class="group">
							</td>
							<td class="group">
							</td>
							<td align="center">
							</td>
							<td align="center">
								<c:out value='${obj.zdtyl}'/>
							</td>
							<td align="center">
								<c:out value='${carNumAll}'/>
							</td>
							<td align="center">
								<c:out value='${completeAll}'/>
							</td>
							<td align="center">
								<c:out value='${validNumAll}'/>
							</td>
							<td align="center">
							<c:if test="${waitBackNumAll!=0}"><c:out value='${waitBackNumAll}'/></c:if>
								
							</td>
							<td align="center">
								<c:out value='${obj.carCode}'/>
							</td>
								<td align="center">
								<c:out value='${obj.carName}'/>
							</td>
							<td align="center">
									<c:out value='${obj.lgortName}'/>
							</td>
							<td align="center">
									<c:out value='${obj.zbz}'/>
							</td>
							
							</c:forEach>
						</tr>
						</c:if>
						<c:forEach items="${orderbom.value}" var="obj" varStatus="n">
						<c:set var="index" value="${index+1}"></c:set>
							<c:set var="trcss" value="${index%2==0?'odd':'even'}"/>
							<c:choose>
								<c:when test="${count==1}">
									<tr class="${trcss}" onMouseOver="setTitle(this,${obj.menge-obj.completeNum-obj.validNum },'${obj.taskBomId }')">
								</c:when>
								<c:otherwise>
									<tr class="${trcss}" attr="${orderbom.key.idnrk}">
								</c:otherwise>
							</c:choose>
							
						
						<td width="3%" align="center">
						</td>
							<c:choose>
								<c:when test="${count==1}">
									<td align="left">${obj.idnrk}</td>
									<td>
									
									<c:if test="${ obj.percent<1&&(obj.carNum-obj.planNum>0)}">
									 <a href="javascript:loadCar('${pageRequest.filters.groupId}','${obj.taskBomId }','${obj.carCode }','${obj.carId }','${obj.lgortName}','${obj.lgort }','${obj.aufnr }','${obj.maktx1 }')"><img src="<%=iconPath%>/btn_hd_exit.gif" /><a>
									</c:if>
										

									<td>
											
											<c:choose>
												<c:when test="${obj.waitBackNum!=0&& not empty obj.waitBackNum}">
												<div class="graph" title="待退料">
													<strong class="bar1" style="width: ${ obj.percent*100}%">
													</strong>
												</div>
												</c:when>
												<c:otherwise>
														<div class="graph">
													<strong class="bar" style="width: ${ obj.percent*100}%">
													</strong>
														</div>
												</c:otherwise>
											</c:choose>
										
										
									   </td>
									</td>
									<td align="center"><span> 
												 <fmt:formatNumber  value="${ obj.percent*100}"  pattern="#0.00" />% </span></td>
								</c:when>
								<c:otherwise>
									<td align="left">&nbsp;&nbsp;&nbsp;${obj.idnrk}</td>
									<td></td><td></td><td></td>
								</c:otherwise>
							</c:choose>
							<td align="center">
									 <span class="carfont">${obj.carCount}车</span>
						</td>
						<td align="left">${obj.maktx2}</td>
						 <c:if test="${pageRequest.filters.planType=='CHANGE'||pageRequest.filters.planType=='BACK'}">
											<td>
											<c:choose>
												<c:when test="${obj.bomType=='1'}">良品</c:when>
												<c:otherwise>不良品</c:otherwise>
											</c:choose>
											</td>
											<td>
											${obj.backZbz }
											</td>
							</c:if>
						<td align="left">${obj.aufnr}</td>
						<td align="center">${obj.zdtyl}</td>
						<td align="center">${obj.carNum}</td>
						<td align="center">${obj.completeNum}</td>
							<td align="center">
								<c:out value='${obj.validNum}'/>
							</td>
							<td align="center">
							<c:if test="${obj.waitBackNum!=0}"><c:out value='${obj.waitBackNum}'/></c:if>
								
							</td>
							<td align="center">
								<c:out value='${obj.carCode}'/>
							</td>
							<td align="center">
								<c:out value='${obj.carName}'/>
							</td>
						<td align="center">${obj.lgortName}</td>
						<td align="center">${obj.zbz}</td>
						
						
						</tr>
						</c:forEach>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="9" >
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</body>
</html>