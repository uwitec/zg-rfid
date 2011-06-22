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
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
			<style>
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
			//alert(historyDiv);
			if(historyDiv!=null){
				historyDiv.style.height="120px";
				height = height - 157;
			}
			document.getElementById("bomDiv").style.height=height+"px";
			
		}
		
		//选择生产线及订单半成品
		function addBom(){
		
			var orderId=parent.document.getElementById("orderId_value").value;
			
			if(orderId==''){
				alert('请先选择订单号');
				return;
			}
			var extend1=parent.document.getElementById("extend1").value;
			var sortf=parent.document.getElementById("plant").value;
			var url='${ctx}/zg/plan/ZgTBomManager/queryBomlListByOrderId.do?orderId='+orderId+'&extend1='+extend1+'&sortf='+sortf+'&planType=${planType}';
			var sFeatures="dialogHeight: 500px;dialogWidth:860px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			window.location.href ='${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do?id=${id}&plant=${plant}&flag=temp&planType=${planType}';
			
			if(returnValue!=undefined){
					alert(returnValue);
			}
			window.location.reload();
		}
		
		function checkWait_back_num(obj,wait_back_num){
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					if("${planType}"!="RENEW"){
						alert("换料数量数量必须小于最大退换料数量！");
						obj.value = wait_back_num;
					}
					
				}
			}else {
				alert("换料数量必须为数字！");
				obj.value =wait_back_num;
			}
		}
		function saveBom(){
			var form=document.forms[0];
			form.action="${ctx}/zg/plan/ZgTBomManager/saveInOutBom.do";
			form.submit();
		}
		
		function buildupSubmitParams(id,state){
			var josnString='';
			var waitBackNumObj=document.getElementsByName("bomList.waitBackNum");
			var cuidObj=document.getElementsByName("bomList.cuid");
			for (var i=0; i<waitBackNumObj.length; i++) {
					var waitBackNum = waitBackNumObj[i].value;
					var cuid=cuidObj[i].value;
					
					josnString=josnString+'{' ;
					josnString = josnString +'"waitBackNum":'+waitBackNum+',';
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
	
		<%@ include file="/commons/messages.jsp"%>
	
		<form id="ec"
			action="<c:url value="/zg/plan/ZgTBomManager/findBomListByPlanID.do"/>"
			method="post" style="display: inline;">
			<input type="hidden" name="storageId" value="${id}" }/>
			<input type="hidden" name="id" value="${id}" }/>
			<input type="hidden" name="type" value="${type}" }/>
			<input type="hidden" name="productType" value="${productType}" }/>
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
									
									<c:if test="${model.state =='8'}">
									
									<c:if test="${empty objlist.CONTENT}">
									
									<c:if test="${objlist.BACK_REASON=='6'}">
									<td align="center">
										${objlist.BACK_ZBZ}
									</td>
									</c:if>
									
									<c:if test="${objlist.BACK_REASON!='6'}">
									<td align="center">
										<c:choose>
								            <c:when test="${objlist.BACK_REASON=='1'}">人为原因</c:when>
								            <c:when test="${objlist.BACK_REASON=='2'}">原材料</c:when>
								            <c:when test="${objlist.BACK_REASON=='3'}">留仓待用</c:when>
								            <c:when test="${objlist.BACK_REASON=='4'}">建议报废</c:when>
								            <c:otherwise>返回工厂返工</c:otherwise>
							            </c:choose>
									</td>
									</c:if>
									
									</c:if>
									</c:if>
									
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
						物料列表
					</h2>
					<div class="toolbar">
						<c:if test="${model.state =='-7' || model.state =='-8' || empty model.state}">
							<a href="javascript:addBom()"><span><img
										src="<%=iconPath%>/addition.gif" />添加</span>
							</a>
							&nbsp;<a href="javascript:batchDelete('${ctx}/zg/plan/ZgTBomManager/deleteBom.do','items',document.forms[0])"><span><img
										src="<%=iconPath%>/ico_009a.gif" />删除</span>
							</a>

						</c:if>
						
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
									<td class="tableHeader">
										备料库存
									</td>
									<td class="tableHeader">
										最大退换料数量
									</td>
									<td class="tableHeader">
										<c:if test="${planType=='CHANGE'}">换料数量</c:if>
										<c:if test="${planType=='BACK'}">退料数量</c:if>
										<c:if test="${planType=='RENEW'}">补领料数量</c:if>
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
										
										 <td>
										 
										  ${obj.STORAGE_NUM}
										 </td>
										  <td>
										  ${obj.MAX_WAITBACKNUM}
										 </td>
										
										<td>
										    <c:choose>
								            	<c:when test="${model.state=='-7'||model.state=='-8'||empty model.state}">
								            	<c:if test="${planType=='CHANGE'}">
								            		<input type="text" name="bomList.waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${num}" maxValue="${obj.MAX_WAITBACKNUM}" maxLength="13" size="8" 	onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}')" />
													<input type="hidden" name="bomList.cuid"value="${obj.CUID}" />
								            	</c:if>
												<c:if test="${planType=='BACK'}">
													<input type="text" name="bomList.waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${num}" maxValue="${obj.MAX_WAITBACKNUM}" maxLength="13" size="8" 	onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}')" />
													<input type="hidden" name="bomList.cuid"value="${obj.CUID}" />
												</c:if>
												<c:if test="${planType=='RENEW'}">
													<input type="text" name="bomList.waitBackNum" value="${obj.CAR_NUM}" id="WAIT_BACK_NUM_${num}"  maxLength="13" size="8" 	onchange="checkWait_back_num(this,'${obj.CAR_NUM}')" />
													<input type="hidden" name="bomList.cuid"value="${obj.CUID}" />
												</c:if>
								            	
								            	</c:when>
								       
								            	<c:otherwise> 
								               		 ${obj.WAIT_BACK_NUM}
												</c:otherwise>
							               </c:choose>
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
				</div>
				</div>
		</form>
		
		
	</body>
</html>
