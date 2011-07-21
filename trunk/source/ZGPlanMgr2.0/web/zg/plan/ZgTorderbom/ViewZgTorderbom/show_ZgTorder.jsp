<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
		function submitOrder(orderId) {
			if(window.confirm("温馨提示：\n    请确认对订单的调整是否均已保存？\n\n确定：提交订单\n取消：继续调整订单"))
				parent.location.href="${ctx}/zg/plan/ZgTorder/submitOrder.do?orderId="+orderId;
		}
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div>
		<table class="formTable">
			<tr>
				<td colspan="8">
					<img src="${ctx }/styles/images/detail.png" alt=""/>订单信息
				</td>
			</tr>
			<tr>	
				<td class="tdLabel" width="10%">排产日期</td>	
				<td width="15%"><s:property value="%{model.pcdatString}" /></td>
				<td class="tdLabel" width="10%">订单编号</td>	
				<td width="15%"><s:property value="%{model.aufnr}" /></td>
				<td class="tdLabel" width="10%">生产线</td>	
				<td width="15%"><s:property value="%{model.arbpl}" /></td>
				<td class="tdLabel" width="10%">客户机型</td>	
				<td width="15%"><s:property value="%{model.maktx2}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel">销售订单号</td>	
				<td><s:property value="%{model.kdauf}" /></td>
				<td class="tdLabel">生产厂</td>	
				<td><s:property value="%{model.plant}" /></td>
				<td class="tdLabel">公司机型</td>	
				<td><s:property value="%{model.maktx1}" /></td>
				<td class="tdLabel">订单项数量</td>	
				<td><s:property value="%{model.psmng}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel">物料号</td>	
				<td><s:property value="%{model.matnr}" /></td>
				<td class="tdLabel">备注</td>	
				<td><s:property value="%{model.orderState_enum.value}" /></td>
				<td class="tdLabel"></td>	
				<td></td>
				<td class="tdLabel"></td>	
				<td></td>
			</tr>
		</table>
	</div>
</body>
</html>