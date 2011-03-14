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
			$("form:first").submit();
		});
		var orderID ="";
		//选择生产线及订单
		function selectOrderArbpl(){
			url='${ctx}/zg/storage/ZgTstorage/queryOrderArbplList.do?type=1';
			var sFeatures="dialogHeight: 500px;dialogWidth:760px";
			var returnValue = window.showModalDialog(url,'',sFeatures);
			if(returnValue) {
				orderID = returnValue.orderID;
				var aufnr =	returnValue.aufnr;
				var arbpl = returnValue.arbpl;
				var arbplName =	returnValue.arbplName;
				
				if(orderID!=$("#orderId_value").val()){//订单和生产线值改变 则删除相应的半成品
					$("form:first").submit();
				}
				
				$("#orderId_label").val(aufnr);
				$("#orderId_value").val(orderID);
			}
		}
		
	</script>
	</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form action="${ctx}/zg/plan/ZgTBomManager/findBomListByPlanID.do" method="post" target="listFrame">
			<input type="hidden" id="s_cuid" name="cuid" value="${model.cuid}" />
			<table width="100%" cellpadding="0" cellspacing="1"
				style="border: 1px solid #A8CFEB;">
				<thead>
					<tr>
						<td class="formToolbar">

							<div class="button" style="text-align: left;">
								<a href="javascript:addStorage()"><span><img src="<%=iconPath%>/icon_tool_049.gif" />保存</span></a>
								<a href="javascript:submitStorage()"><span><img src="<%=iconPath%>/true.gif" />提交</span></a>
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
							换料申请单
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
						<td width="15%">
							<input type="hidden" size="15" readonly="true" id="createDate" maxlength="40"
								name="cuid"
								value="${model.cuid }" />
							<input type="text" size="15" readonly="true" id="createDate" maxlength="40"
								name="createDate"
								value="2011-02-22" />
						</td>
						<th>
							订单号：
						</th>
						<td width="15%">
							<input type="text" size="15" onclick="selectOrderArbpl()" maxlength="40"
								readonly="true" id="orderId_label" name="orderId_label"
								columnNameLower="orderId" bmClassId="FW_ORGANIZATION"
								column="m.t0_LABEL_CN"  value=""/>
							<input type="hidden" id="orderId_value" name="s_orderId" />
						</td>
						<th>
							物料等级：
						</th>
						<td width="15%">
							<select name="s_extend1">
								<option value="A">A级物料</option>
								<option value="B">B级物料</option>
								<option value="C">C级物料</option>
							</select>
						</td>

						<th>
							录单人：
						</th>
						<td width="15%">
							${operatorInfo.userName}
							<input type="hidden" id="userId" name="creatorId"
								value="${model.userId}" />
						</td>

					</tr>
				</tbody>
			</table>
		</form>
		<iframe
			src=""
			autolayout="true" name="listFrame" frameborder="0" width="100%"
			height="100%" align="top" scrolling="no" />

	</body>
</html>
