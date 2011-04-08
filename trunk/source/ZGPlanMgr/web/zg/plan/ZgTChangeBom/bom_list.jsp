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
		<script type="text/javascript">
		$(function() {
			try{
				$("a[attr='viewLink'],input[attr='viewLink']").bind("click",function(e){
					var o = parent.accordion;
					if(o) {
						if(o.accordion( "option" , "active")+'' == '0') {
							o.accordion("activate", 0);
						}
					}
				});
				$("input[dateFlag=true]")
				.datepicker({
					showAnim:'',showOtherMonths: true,
					selectOtherMonths: true,
					dateFormat:"yy-mm-dd"
				});
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
			}catch(e){}
		});
		function initLayoutOwner() {
			if(parent) {
				var iframes = parent.document.getElementsByTagName("iframe");
				for(var i = 0; i < iframes.length; i++) {
					if(iframes[i].contentWindow == window) {
						if(iframes[i].getAttribute("autolayout") == "true") {
							iframes[i].style.height = (document.body.offsetHeight+20) +"px";
						}
					}
				}
			}
		}
		
		//选择生产线及订单半成品
		function addBom(){
		
			var orderId=parent.document.getElementById("orderId_value").value;
			
			if(orderId==''){
				alert('请先选择订单号');
				return;
			}
			var extend1=parent.document.getElementById("extend1").value;
			var url='${ctx}/zg/plan/ZgTBomManager/queryBomlListByOrderId.do?orderId='+orderId+'&extend1='+extend1;
			var sFeatures="dialogHeight: 500px;dialogWidth:860px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			window.location.href ='${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do?id=${id}&plant=${plant}&flag=temp';
			
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
					alert("换料数量数量必须小于备料库存！");
					obj.value = wait_back_num;
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
				<div class="eXtremeTable">
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
						<c:if test="${model.state =='1' || model.state =='0' || empty model.state}">
							<a href="javascript:addBom()"><span><img
										src="<%=iconPath%>/addition.gif" />添加</span>
							</a>
							&nbsp;<a href="javascript:batchDelete('${ctx}/zg/plan/ZgTBomManager/deleteBom.do','items',document.forms[0])"><span><img
										src="<%=iconPath%>/ico_009a.gif" />删除</span>
							</a>

						</c:if>
						
					</div>
					<div class="eXtremeTable">
						<table id="ec_table" border="0" cellspacing="1" cellpadding="0"
							class="tableRegion" width="100%">
							<thead>
								<tr>
								   <c:if test="${model.state=='0'||model.state=='1'||empty model.state}">
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
										换料数量
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
                                      <c:if test="${model.state=='0'||model.state=='1'||empty model.state}">
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
										    <c:choose>
								            <c:when test="${model.state=='0'}">	
								            <input type="text" maxValue="${obj.STORAGE_NUM}" maxLength="13" size="8" 
											onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}')" size="8"
											name="bomList[${num}].waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${num}"/>
											<input type="hidden" name="bomList[${num}].cuid" value="${obj.CUID}" />
											</c:when>
								            <c:when test="${model.state=='1'}">	
								            <input type="text" maxValue="${obj.STORAGE_NUM}" maxLength="13" size="8" 
											onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}')" size="8"
											name="bomList[${num}].waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${num}"/>
											<input type="hidden" name="bomList[${num}].cuid"value="${obj.CUID}" />
											</c:when>
								            <c:when test="${model.state=='2'}">${obj.WAIT_BACK_NUM}</c:when>
								            <c:when test="${model.state=='4'}">${obj.WAIT_BACK_NUM}</c:when>
								             <c:when test="${model.state=='8'}">${obj.WAIT_BACK_NUM}</c:when>
								              <c:otherwise> <input type="text" maxValue="${obj.STORAGE_NUM}" maxLength="13" size="8" 
											onchange="checkWait_back_num(this,'${obj.WAIT_BACK_NUM}')" size="8"
											name="bomList[${num}].waitBackNum" value="${obj.WAIT_BACK_NUM}" id="WAIT_BACK_NUM_${num}"/>
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
		</form>
		
		
	</body>
</html>
