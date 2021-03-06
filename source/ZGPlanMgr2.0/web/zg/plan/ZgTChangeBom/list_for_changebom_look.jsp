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
		<script type="text/javascript" src="${ctx}/dwr/interface/ZgTBomManagerDwrAction.js"></script>
		<style>
.readOnlyInput {
	border: 0;
	background: transparent;
}
</style>
		<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		});
		
		
		function doLayout() {
			var maxHeight = top.getContentHeight();
			document.getElementById("_orderPanel").style.height = maxHeight + 'px';
			var headTableH = document.getElementById("headTable").offsetHeight;
			var orderTableH= document.getElementById("orderTable").offsetHeight;
			var listFrameH = maxHeight - headTableH-orderTableH;
			var commentTableObj=document.getElementById("commentTable");
			if(commentTableObj!=null){
				listFrameH=listFrameH-commentTableObj.offsetHeight;
			}else{
				listFrameH=listFrameH-50;
			}
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		
		function choose(){
		var back_reason=$("#back_reason").val();
		if(back_reason=='6'){
			$("#text").attr("style","display:''")
				
		}else{
			$("#text").attr("style","display:none");
			}
		}
		//提交领料计划,num:1为通过；2为驳回    cuid为单id
		function submitOrRejectPlan(num,cuid,obj){
			obj.disabled=true;
			var items=document.frames('listFrame').document.getElementsByName("items");
			var buildupSubmitParams=document.frames('listFrame').buildupSubmitParams();
			ZgTBomManagerDwrAction.saveChangOrderBom(buildupSubmitParams,function(data){
				if(data=="success") {
					if(num==2){
							rejectOpinionText=document.getElementById("rejectOpinion").value;
							if(rejectOpinionText.Trim()=="请填写审核意见......"){
								alert("请填写审核意见");
								obj.disabled=false;
								return;
							}
						}
					   rejectOpinionText=document.getElementById("rejectOpinion").value;
					   if(rejectOpinionText.Trim()=="请填写审核意见......"||rejectOpinionText.Trim()==""){
					        rejectOpinionText="通过";
					   }else{
					        rejectOpinionText=document.getElementById("rejectOpinion").value;
					        
					   }
					   document.forms[0].target="_self";
						document.forms[0].action="${ctx}/zg/plan/ZgTBomManager/examineOrderPlan.do?submitType="+num+"&rejectOpinionText="+rejectOpinionText+"&id="+cuid;
						document.forms[0].submit();
				}else {
					alert("系统繁忙，请稍后再试");
						butsetDisable(fasle);
				}
			});
			
			
			
			
			
		}
		
		function submitOrRejectPlan1(cuid,obj){
			obj.disabled=true;
			var items=document.frames('listFrame').document.getElementsByName("items");
			var buildupSubmitParams=document.frames('listFrame').buildupSubmitParams();
			ZgTBomManagerDwrAction.saveChangOrderBom(buildupSubmitParams,function(data){
				if(data=="success") {
					var back_reason=$("#back_reason").val();
					if(back_reason==""){
						alert("请选择退换料原因");
						obj.disabled=false;
						return;
					}
				
			   		var text=document.getElementById("text").value;
			   
			  
			
		  	 		document.forms[0].target="_self";
					document.forms[0].action="${ctx}/zg/plan/ZgTBomManager/examineOrderPlan1.do?back_reason="+back_reason+"&id="+cuid+"&text="+text;
			
					document.forms[0].submit();
				}else {
					alert("系统繁忙，请稍后再试");
					butsetDisable(fasle);
				}
			});
			
				
		}
		
		//去除空格的
		String.prototype.Trim = function() 
		{ 
			return this.replace(/(^\s*)|(\s*$)/g, ""); 
		}
		
		
		
		function backToList(){
			parent.document.forms[0].submit();
		}
		//点击多行文本
		function clickTextarea(){
			if(document.getElementById("rejectOpinion").value.Trim()=="请填写审核意见......")
				document.getElementById("rejectOpinion").value="";
		}
		//移开
		function moveTextarea(){
			if(document.getElementById("rejectOpinion").value.Trim()=="")
				document.getElementById("rejectOpinion").value="请填写审核意见......";
		}
	</script>
	</head>
	<body>
	<div id="_orderPanel" style="height:100px">		
	<form action="${ctx}/zg/plan/ZgTBomManager/listChangeBom.do?id=${model.cuid}&planType=${model.planType}" method="post" target="listFrame">
		<table id="headTable" width="100%" cellpadding="0" cellspacing="1"
			style="border: 1px solid #A8CFEB;">
			<thead>
				<tr>
					<td class="formToolbar">
						<div class="button" style="text-align: left;">
										
						     <c:if test="${model.state=='-6'}">
							 <a href="javascript:submitOrRejectPlan(1,'${cuid}',this)"><span>
							 <img src="<%=iconPath%>/true.gif" />通过</span> </a>
							 &nbsp;<a href="javascript:submitOrRejectPlan(2,'${cuid}',this)"><span>
							 <img src="<%=iconPath%>/ico_009a.gif" />退回</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
                             <c:if test="${model.state=='-4'}">
                             <a href="javascript:submitOrRejectPlan1('${cuid}',this)"><span>
							 <img src="<%=iconPath%>/true.gif" />通过</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
                          
                             <c:if test="${model.state=='-7'||model.state=='-5'}">
                             <a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
						</div>
					</td>
				</tr>
			</thead>
		</table>
		
			<table id="orderTable" class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="10">
							<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
								style="cursor: pointer" title="高级查询" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
								<c:choose>
								<c:when test="${model.planType=='CHANGE'}">换料申请单</c:when>
								<c:when test="${fn:indexOf(model.planType,'BACK')>=0}">退料申请单</c:when>
								<c:when test="${model.planType=='RENEW'}">补领料申请单</c:when>
								<c:otherwise>退料申请单</c:otherwise>
								</c:choose>：${model.cuid}
						</td>
					</tr>
				</thead>
				<tbody id="tbody_1" style="display: block">
					<tr>
						<td colspan="10"
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
							单据日期：
						</th>
						<td width="15%">
								<fmt:formatDate value="${model.create_date}" pattern="yyyy-MM-dd HH:mm:ss" />
								<input type="hidden" id="planType" name="planType"  value="${ model.planType}"/>
								<input type="hidden" id="state" name="state"  value="${ model.state}"/>
							</td>
			<th>
							订单号：
						</th>
						<td width="15%">
							${model.orderTaskId_related.value}
							<input type="hidden" id="orderId_value" name="orderTaskId" value="${model.orderTaskId }"/>
						</td>
						<th>
							物料等级：
						</th>
						<td width="12%">
						
							<input type="hidden" id="extend1"  name="extend1" value="${model.extend1}"/>
							${model.extend1}
						</td>
						</tr>
						<tr>	
						<th>
							生产厂：
						</th>
						<td width="5%">
						${model.plant}
						<input type="hidden"  readonly="true" name="plant" id="plant" value="${model.plant}"/>
						</td>
						<th>
							生产线：
						</th>
						<td width="5%">
						${orderTask.arbpl}
						<input type="hidden"  readonly="true" name="arbpl" id="arbpl" value="${orderTask.arbpl}"/>
						</td>

						<th>
							录单人：
						</th>
						<td width="15%">
							${operatorInfo.userName}
							<input type="hidden" id="userId" name="userId"
								value="${model.userId}" />
						</td>

					</tr>
					<tr>	
						<th>
							备注：
						</th>
						<td colspan="5">
							<textarea name="zbz" id="zbz" onpropertychange="if(value.length>85) value=value.substr(0,85)"	 cols=100 rows=2>${model.zbz }</textarea>
						</td>
				
					</tr>
				</tbody>
			</table>
			   <c:if test="${model.state=='-6'||model.state=='-7'}">
				<table id="commentTable" class="formitem" width="100%" cellpadding="0" cellspacing="1"
					style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
					<thead>
						<tr>
							<td class="title" colspan="5">
								<center>
									<h2>
										审<br />核<br />意<br />见
									</h2>
								</center>
							</td>
							<td>
								<textarea name="rejectOpinion" id="rejectOpinion" onpropertychange="if(value.length>50) value=value.substr(0,85)"
								onpropertychange="checkLength(this,85);" maxlength="10" cols=130 rows=4 onFocus="clickTextarea()" onblur="moveTextarea()">请填写审核意见......</textarea>
							</td>
						</tr>
					</thead>
				</table>
				</c:if>
		 <c:if test="${model.state=='-4'}">
		<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
		 <tr rowspan="2">
		 <td  class="title" width="7%">		<c:if test="${model.planType=='CHANGE'}">换料原因</c:if>
								<c:if test="${fn:indexOf(model.planType,'BACK')>=0}">退料原因</c:if>
								：</td>
		 <td onclick="choose()"  class="title">
		 <select name="back_reason" id="back_reason" align="left" >
		            <option value="">请选择</option>
					<option value="1">人为原因</option>
					<option value="2">原材料</option>
					<option value="3">留仓待用</option>
					<option value="4">建议报废</option>
					<option value="5">退回工厂返工</option>
					<option value="6">其他</option>
				</select>
		 </td>
	
		 </tr>
		 <tr>
		  <td width="7%"></td>
		 <td><textarea name="text" id="text" cols=130 rows=4 style="display:none" onpropertychange="if(value.length>50) value=value.substr(0,50)"></textarea>
		 </td>
		 </tr>
		 </table>
		 </c:if>

	</form>
		<iframe id="listFrame" src="" name="listFrame" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
	</div>
			
	

	</body>
</html>
