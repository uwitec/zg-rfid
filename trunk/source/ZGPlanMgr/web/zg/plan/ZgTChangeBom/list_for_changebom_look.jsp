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
				var maxHeight = top.getContentHeight();
				
			var queryPanelH = document.getElementById("_queryPanel").style.height;
			document.getElementById("resconfigResult").style.height = (maxHeight-queryPanelH) + 'px';
			document.getElementById("_operatePanel").style.height = (maxHeight-queryPanelH) + 'px';
			
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
		   
			document.forms[0].action="${ctx}/zg/plan/ZgTBomManager/examineOrderPlan.do?submitType="+num+"&rejectOpinionText="+rejectOpinionText+"&id="+cuid;
			
			document.forms[0].submit();
		}
		
			function submitOrRejectPlan1(cuid,obj){
			obj.disabled=true;
			
				var back_reason=$("#back_reason").val();
				if(back_reason==""){
					alert("请选择换料原因");
					obj.disabled=false;
					return;
				}
				
			   var text=document.getElementById("text").value;
			   
			  
			
		   
			document.forms[0].action="${ctx}/zg/plan/ZgTBomManager/examineOrderPlan1.do?back_reason="+back_reason+"&id="+cuid+"&text="+text;
			
			document.forms[0].submit();
		}
		
		//去除空格的
		String.prototype.Trim = function() 
		{ 
			return this.replace(/(^\s*)|(\s*$)/g, ""); 
		}
		
		
		function init(state){
			if($("#cuid").val()=='自动生成'){
			   $("#b_submit").attr("disabled",true);
			}
			//	document.forms[0].submit();
			if(state=='8'){//表单状态为提交状态
				  $("#b_save").attr("disabled",true);
			}
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
	<form action="${ctx}/zg/plan/ZgTBomManager/queryHistory.do" method="post" target="listFrame">
		<table width="100%" cellpadding="0" cellspacing="1"
			style="border: 1px solid #A8CFEB;">
			<thead>
				<tr>
					<td class="formToolbar">
						<div class="button" style="text-align: left;">
										
						     <c:if test="${model.state=='2'}">
							 <a href="javascript:"><span onclick="submitOrRejectPlan(1,'${cuid}',this)">
							 <img src="<%=iconPath%>/true.gif" />通过</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="submitOrRejectPlan(2,'${cuid}',this)">
							 <img src="<%=iconPath%>/ico_009a.gif" />退回</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
                             <c:if test="${model.state=='4'}">
                             <a href="javascript:"><span onclick="submitOrRejectPlan1('${cuid}',this)">
							 <img src="<%=iconPath%>/true.gif" />通过</span> </a>
							 &nbsp;<a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
                          
                             <c:if test="${model.state=='1'||model.state=='3'}">
                             <a href="javascript:"><span onclick="if(parent.doQuery)parent.doQuery()">
							 <img src="<%=iconPath%>/ico_007.gif" />返回</span> </a>
                             </c:if>
						</div>
					</td>
				</tr>
			</thead>
		</table>
		
			<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<th>
							单据日期：
						</th>
						
						<td width="15%" >
								
								<fmt:formatDate value="${model.create_date}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						
						<th>
							订单号：
						</th>
						<td width="15%">
							${model.orderId_related.value}
							<input type="hidden" id="orderId_value" name="orderId" value="${model.orderId }"/>
						</td>
						<th>
							物料等级：
						</th>
						<td width="15%">
							${model.extend1}
						</td>

						 <th>
								录单人:
							</th>
							<td width="15%">
								${model.userId_related.value}
							
							</td>
					</tr>
				</thead>
			</table>
			   <c:if test="${model.state=='2'}">
				<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
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
								<textarea name="rejectOpinion" id="rejectOpinion" onpropertychange="if(value.length>50) value=value.substr(0,50)"
								onpropertychange="checkLength(this,85);" maxlength="10" cols=130 rows=4 onFocus="clickTextarea()" onblur="moveTextarea()">请填写审核意见......</textarea>
							</td>
						</tr>
					</thead>
				</table>
				</c:if>
		 <c:if test="${model.state=='4'}">
		<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
		 <tr rowspan="2">
		 <td  class="title" width="7%">换料原因：</td>
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
			
	
	<iframe id="resconfigResult"
	    src="${ctx}/zg/plan/ZgTBomManager/listChangeBom.do?id=${model.cuid}"
	   width="100%" style="height:378px" frameborder="0" marginwidth="0"
	marginheight="0" onload=""></iframe>
	

	</body>
</html>
