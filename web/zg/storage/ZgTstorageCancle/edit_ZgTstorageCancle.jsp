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
		<script src="${ctx}/dwr/interface/ZgTbomCancelDwrAction.js"	type="text/javascript"></script>
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
			$("#arbpl_value").after(img);
			$("#lgort_value").after(img);
			
			try{
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
			}catch(e){}
		});
		
		
		
		//选择生产线及订单
		function selectOrderArbpl(){
			var type='${type}';
			var productType='${productType}';
			var lgort=$("#lgort_value").val();
			
			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
		
			if(type=='1'){//入库
				var url='${ctx}/zg/storage/ZgTstorage/findOrderArbplList.do';
			}if(type=='2'){//出库
				var url='${ctx}/zg/storage/ZgTstorage/findOrderArbplListByStorageId.do?lgort='+lgort+'&type='+type+'&productType='+productType;
				
			}
			
			
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
				var orderID = returnValue.orderID;
				var aufnr =	returnValue.aufnr;
				var arbpl = returnValue.arbpl;
				var arbplName =	returnValue.arbplName;
				
				if(orderID!=$("#orderId_value").val()){//订单和生产线值改变 则删除相应的半成品
					document.frames['listFrame'].document.location="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=del";
					//document.frames['listFrame'].document.location.reload();
					//document.frames['listFrame'].document.location="${ctx}/zg/storage/ZgTstorage/findBomlList.do?id=${model.cuid}&type=${type}&productType=${productType}&flag=temp";
				}
				
				$("#orderId_label").val(aufnr);
				$("#orderId_value").val(orderID);
				$("#arbpl_label").val(arbplName);
				$("#arbpl_value").val(arbpl);
			}
		}
		
		function save(){
			document.frames['listFrame'].saveBom();
			if(!checkforstorageCancelEdit()){
			return;
			}
			var form=document.forms[0];
			form.action="${ctx}/zg/storage/ZgTstorageCancle/save.do?update=true";
			//alert(form.action);
			form.submit();
		}
		
		function submitPlan(){
			document.frames['listFrame'].saveBom();
			if(!checkforstorageCancelEdit()){
			return;
			}
			var form=document.forms[0];
			form.action="${ctx}/zg/storage/ZgTstorageCancle/submit.do?update=true";
			form.submit();
		}
		
		function checkforstorageCancelEdit(){
			DWREngine.setAsync(false);
			var result = '';
			ZgTbomCancelDwrAction.checkForGenerateOrderChanged("${model.cuid }",function(data){
				if(data=='OK'){
					result = true;
				}else{
					alert(data);
					result = false;
				}
			});
			return result;
		}
		
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<div class="eXtremeTable">
			<form action="${ctx}/zg/storage/ZgTstorage/save.do" method="post">
				<input type="hidden" name="productType"
					value="${ model.productType }" />
				<input type="hidden" name="state" value="${ model.state }" />
				<table width="100%" cellpadding="0" cellspacing="1"
					style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<c:if test="${empty view}">
									<a href="javascript:save()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
								<a href="javascript:submitPlan()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
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
									style="cursor: pointer" alt="" id="img_1" border="0"
									onclick="changeV('1')" />
								单据
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
								单据编号:
							</th>
							<td width="15%">
								<input type="text" size="25" readonly="true" id="cuid" maxlength="40"
									name="cuid" value="${model.cuid }" />
							</td>

							<th>
								单据日期:
							</th>
							<td width="15%">
								<input type="text" size="15" readonly="true" id="createDate" maxlength="40"
									name="createDate"
									value="<fmt:formatDate value="${model.createDate}" pattern="yyyy-MM-dd" />" />
							</td>
							<th>
								部门名称:
							</th>
							<td width="15%">
								${model.deptId_related.value}
								<input type="hidden" id="deptId" name="deptId"
									value="${model.deptId}" />
							</td>
						</tr>
						<tr>
							<th>
								录单人:
							</th>

							<td width="15%">
								${model.creatorId_related.value}
								<input type="hidden" id="creatorId" name="creatorId"
									value="${model.creatorId}" />
							</td>

							<th>
								备注:
							</th>
							<td colspan="7">
								<input type="text" size="15" maxLength="170"
									value="${model.zbz }" name="zbz" id="zbz" />
							</td>
						</tr>
						<tr>

						</tr>
					</tbody>
				</table>
				<p></p>
				<br />
			</form>
			<iframe
				src="${ctx}/zg/storage/ZgTstorageCancle/findBomlList.do?id=${model.cuid}&productType=${productType}&view=${view }"
				autolayout="true" name="listFrame" frameborder="0" width="100%"
				height="100%" align="top" scrolling="no" />
			<br />
			<br />
			<br />
			<br />
			<br />
		</div>
	</body>
</html>
