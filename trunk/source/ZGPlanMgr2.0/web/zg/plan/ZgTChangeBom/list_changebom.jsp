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
				historyDiv.style.height="60px";
				height = height - 97;
			}
			document.getElementById("bomDiv").style.height=height+"px";
			
		}
		
		function checkWait_back_num(obj,wait_back_num,qualityObjId,notQualityObjId){
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			var qualityCou=$("#"+qualityObjId).val();
			var notQualityCou=$("#"+notQualityObjId).val();
			var oldNotQualityCou =$("#"+notQualityObjId).attr("oldValue");
			var oldQualityCou=$("#"+qualityObjId).attr("oldValue");
			
			
						
			if(isNumber(value)) {
				qualityCou=qualityCou*1;
				notQualityCou=notQualityCou*1;
				var result=(oldNotQualityCou*1+oldQualityCou*1)-value*1;
				if(result<0) {
					if(obj.id==qualityObjId){
						alert("该物料良品退换料数量必须不能大于"+(oldNotQualityCou*1+oldQualityCou*1)+"！");
						obj.value = oldQualityCou;
					}else{
						alert("该物料不良品退换料数量必须不能大于"+(oldNotQualityCou*1+oldQualityCou*1)+"！");
						obj.value = oldNotQualityCou;
					}
					
					
				}else{
					if(obj.id==qualityObjId){
						$("#"+qualityObjId).attr("oldValue",value);
						$("#"+notQualityObjId).attr("oldValue",result);
						$("#"+notQualityObjId).val(result);
					}else{
						$("#"+notQualityObjId).attr("oldValue",value);
						$("#"+qualityObjId).attr("oldValue",result);
						$("#"+qualityObjId).val(result);
					}
				}
			}else {
				alert("退换料数量必须为数字！");
				if(obj.id==qualityObjId){
					obj.value = oldQualityCou;
				}else{
					obj.value = oldNotQualityCou;
				}
			}
		}
		
		function buildupSubmitParams(id,state){
			var josnString='';
			var waitBackNumObj=document.getElementsByName("bomList.waitBackNum");
			var cuidObj=document.getElementsByName("bomList.cuid");
			var bomTypeObj=document.getElementsByName("bomList.bomType");
			var zbzObj=document.getElementsByName("bomList.zbz");
			for (var i=0; i<waitBackNumObj.length; i++) {
					var waitBackNum = waitBackNumObj[i].value;
					var cuid=cuidObj[i].value;
					var bomType=bomTypeObj[i].value;
					var zbz=zbzObj[i].value;
					josnString=josnString+'{' ;
					josnString = josnString +'"waitBackNum":'+waitBackNum+',';
					josnString = josnString +'"bomType":'+bomType+',';
					josnString = josnString +'"zbz":"'+zbz+'",';
					josnString = josnString +'"cuid":"'+cuid+'"},';
			        
			} 
	        if(josnString.length>0){
	        	josnString=josnString.substr(0,josnString.length-1);
	        }
	        josnString='['+josnString+']';
	        return josnString;
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
				<div class="eXtremeTable" style="height:60px" id="historyDiv">
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
								   <c:if test="${model.state=='-8'||model.state=='-7'||empty model.state}">
									<td class="tableHeader" width="3%">
										<input type='checkbox'
											onclick="setAllCheckboxState('items',this.checked)" />
									</td>
									</c:if>
									<td class="tableHeader">
										生产订单编号
									</td>
									
									<td class="tableHeader">
										生产线
									</td>
									<td class="tableHeader">
										物料号
									</td>
									<td class="tableHeader">
										物料描述
									</td>
									<c:if test="${planType=='CHANGE'||fn:indexOf(planType,'BACK')>=0}">
									<td class="tableHeader">
										类型
									</td>
									</c:if>
									<td class="tableHeader">
										备料库存
									</td>
									<td class="tableHeader">
										最大退换料数量
									</td>
									<td class="tableHeader">
										<c:choose>
									<c:when test="${planType=='CHANGE'}">换料数量</c:when>
										<c:when test="${planType=='BACK'}">退料数量</c:when>
											<c:when test="${planType=='RENEW'}">补领料数量</c:when>
											<c:otherwise>退料数量</c:otherwise>
									</c:choose>
									</td>
									<td class="tableHeader">
										备注
									</td>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="0"></c:set>
								<c:forEach items="${bol}" var="obj" varStatus="n">  
								
									<c:if test="${!obj.isDel}">
										<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									    <tr class="${trcss}"
												onmouseover="this.style.backgroundColor = '#EBF1FF'"
												onmouseout="this.style.backgroundColor = '#FFFFFF'">
                                      <c:if test="${model.state=='-7'||model.state=='-8'||empty model.state}">
										<td width="3%" align="center">
											<input type="checkbox" name="items" value="cuid=${obj.CUID}&" />
										</td>
										</c:if>
										<td><input type="hidden" value="${obj.AUFNR}" id="${obj.CUID}_and" />
											${obj.AUFNR}
										</td>
										
										<td>
											${obj.ARBPL}
										</td>
										<td>
											${obj.IDNRK}
										</td>
										<td>
											${obj.MAKTX2}
										</td>
											<c:if test="${planType=='CHANGE'||fn:indexOf(planType,'BACK')>=0}">
											<td>
											<c:choose>
												<c:when test="${obj.BOM_TYPE=='1'}">良品</c:when>
												<c:otherwise>不良品</c:otherwise>
											</c:choose>
											</td>
											</c:if>
										
										 <td>
										 
										  ${obj.STORAGE_NUM}
										 </td>
										  <td>
										  ${obj.MAX_WAITBACKNUM}
										 </td>
										
										<td>
										    <c:choose>
								            	<c:when test="${model.state=='-7'||model.state=='-8'||model.state=='-6'||model.state=='-4'||empty model.state}">
								            	<c:choose>
													<c:when test="${planType=='CHANGE'}">
														<input type="text" name="bomList.waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${obj.BOM_TYPE}_${obj.CUID}" maxValue="${obj.MAX_WAITBACKNUM}" maxLength="13" size="8" oldValue="${obj.WAIT_BACK_NUM}" 	onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}','WAIT_BACK_NUM_1_${obj.CUID}','WAIT_BACK_NUM_-1_${obj.CUID}')" />
														<input type="hidden" name="bomList.cuid" value="${obj.CUID}" />
														<input type="hidden" name="bomList.bomType" value="${obj.BOM_TYPE}" />
														<input type="hidden" name="bomList.idnrk" value="${obj.IDNRK}" />
													</c:when>
													<c:when test="${fn:indexOf(planType,'BACK')>=0}">
														<input type="text" name="bomList.waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${obj.BOM_TYPE}_${obj.CUID}" maxValue="${obj.MAX_WAITBACKNUM}" maxLength="13" size="8" oldValue="${obj.WAIT_BACK_NUM}" 	onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}','WAIT_BACK_NUM_1_${obj.CUID}','WAIT_BACK_NUM_-1_${obj.CUID}')" />
														<input type="hidden" name="bomList.cuid" value="${obj.CUID}" />
														<input type="hidden" name="bomList.bomType" value="${obj.BOM_TYPE}" />
														<input type="hidden" name="bomList.idnrk" value="${obj.IDNRK}" />
													</c:when>
													<c:when test="${planType=='RENEW'}">
														<input type="text" name="bomList.waitBackNum" value="${obj.CAR_NUM}" id="WAIT_BACK_NUM_${num}"  maxLength="13" size="8" 	onchange="checkWait_back_num(this,'${obj.CAR_NUM}','WAIT_BACK_NUM_1_${obj.CUID}','WAIT_BACK_NUM_-1_${obj.CUID}')" oldValue="${obj.WAIT_BACK_NUM}" />
														<input type="hidden" name="bomList.cuid" value="${obj.CUID}" />
														<input type="hidden" name="bomList.bomType" value="${obj.BOM_TYPE}" />
														<input type="hidden" name="bomList.idnrk" value="${obj.IDNRK}" />
													</c:when>
													<c:otherwise>
														<input type="text" name="bomList.waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${obj.BOM_TYPE}_${obj.CUID}" maxValue="${obj.MAX_WAITBACKNUM}" maxLength="13" size="8" oldValue="${obj.WAIT_BACK_NUM}" 	onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}','WAIT_BACK_NUM_1_${obj.CUID}','WAIT_BACK_NUM_-1_${obj.CUID}')" />
														<input type="hidden" name="bomList.cuid" value="${obj.CUID}" />
														<input type="hidden" name="bomList.bomType" value="${obj.BOM_TYPE}" />
														<input type="hidden" name="bomList.idnrk" value="${obj.IDNRK}" />
													</c:otherwise>
												</c:choose>
								            	
								            	</c:when>
								       
								            	<c:otherwise> 
								               		 ${obj.WAIT_BACK_NUM}
												</c:otherwise>
							               </c:choose>
										</td>
										<td>
										<input type="text" name="bomList.zbz" value="${obj.ZBZ}" maxlength="30" />
										</td>
									
										</tr>
											<c:set var="num" value="${num+1}"></c:set>
									</c:if>
								
								</c:forEach>
								<tr style="padding: 0px;">
									<td colspan="12">
									</td>
								</tr>
							</tbody>
						</table>
					
				</div>
		</form>
		
	</body>
</html>
