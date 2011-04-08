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
		<script src="${ctx}/dwr/interface/ZgTorderbomDwrAction.js"
			type="text/javascript"></script>
		<script type='text/javascript' src='${ctx}/dwr/interface/ZgTbomDwrAction.js'></script>
		<style>
.extendTd {
	
}

.extendTd .extendBtn {
	height: 18px;
}

.extendTd .extendBtn img {
	cursor: pointer;
	width: 16px;
	height: 18px;
}

.extendTd .extendBtn label {
	height: 18px;
	line-height: 18px;
}

#matnrListDiv {
	width: 15%;
	height: 100%;
	float: left;
	text-align: center;
}

#matnrChildListDiv {
	width: 84%;
	height: 100%;
	float: left;
	overflow: auto
}

#matnrListDiv ul {
	border: 1px solid #d0e5f5;
	text-align: left;
}

#matnrListDiv ul li {
	cursor: pointer;
	font-size: 120%;
}

#matnrListDiv ul li:hover {
	color: red;
	font-weight: bold;
}

#matnrCode {
	font-size: 130%;
	font-weight: bold;
	background-color: #d0e5f5;
	line-height: 20px;
	color: #1d5987;
}
</style>
		<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			var img = "<img src='"+ctx+"/resources/images/frame/autocomplete.png'/>";
			$("#orderId_value").after(img);
			//$("form:first").submit();
		});
		var orderID ="";
		
		
		function addStorage(){
			var items=document.frames('listFrame').document.getElementsByName("items");
			if(items.length<=0){
				alert('请添加半成品');
				return;
			}
			//document.frames['listFrame'].saveBom();
			
			var form=document.frames('listFrame').document.forms[0];
			form.action="${ctx}/zg/plan/ZgTBomManager/saveInOutBom.do";
			form.submit();
		
			
			var form=document.forms[0];
			form.action="${ctx}/zg/plan/ZgTBomManager/save.do?update=true";
			form.submit();

		}
		
		function submitStorage(){
			var items=document.frames('listFrame').document.getElementsByName("items");
			if(items.length<=0){
				alert('请添加物料');
				return;
			}
			for(var i=0;i<items.length;i++){
			wait_back_num=document.frames('listFrame').document.getElementById('WAIT_BACK_NUM_'+i).value;
			if(wait_back_num==0){
			    alert('换料数量为0，请更改');
				return;
			}
			}
			if(wait_back_num==0){
			    alert('换料数量为0，请更改');
				return;
			}
			document.frames['listFrame'].saveBom();
			var form=document.forms[0];
			form.action="${ctx}/zg/plan/ZgTBomManager/submit.do?update=true";
			form.submit();
			}
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		
		<form action="${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do" method="post" >
			<input type="hidden" id="cuid" name="cuid" value="${model.cuid}" />
			<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">

							<div class="button" style="text-align: left;">
							  <c:if test="${model.state =='0'}">
								<a href="javascript:addStorage()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
								</c:if>
								<c:if test="${model.state =='1'||model.state =='0'}">
								<a href="javascript:submitStorage()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
								</c:if>
								<a href="javascript:if(parent.doQuery)parent.doQuery()"><span><img src="<%=iconPath%>/ico_007.gif" />返回</span></a>
							</div>
						</td>
					</tr>
				</thead>
			</table>

			<table class="formitem" width="100%" cellpadding="0" cellspacing="1"
				style="border-top: 1px solid #A8CFEB; margin-top: 3px;">
				<thead>
					<tr>
						<td class="title" colspan="8">
							<img src="${ctx }/resources/images/frame/ico_noexpand.gif"
								style="cursor: pointer" title="高级查询" alt="" id="img_1"
								border="0" onclick="changeV('1')" />
								换料审核单
						</td>
					</tr>
				</thead>
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
				</tbody>
			</table>
		</form>
				
	  
	  
	
		<iframe id="resconfigResult"
			src="${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do?id=${model.cuid}"
			name="listFrame"
			width="100%" style="height:378px" frameborder="0" marginwidth="0"
	        marginheight="0" onload=""></iframe>

	</body>
</html>
