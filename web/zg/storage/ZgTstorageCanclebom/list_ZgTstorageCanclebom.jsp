<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
	</script>
</head>
<body scroll="auto">
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/plan/ZgTorder/list.do"/>" method="post" style="display: inline;">
	<div>
		<div>
		</div>
		<div class="datalist">
			<h2>订单管理</h2>
			<div class="toolbar">
			</div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
		<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  >订单号</td>
							<td class="tableHeader" >生产线</td>
							<td class="tableHeader" >源单号</td>
							<td class="tableHeader" >半成品物料号</td>
							<td class="tableHeader" >半成品名称</td>
							<td class="tableHeader" >单位</td>
							<td class="tableHeader" >可冲单数量</td>
							<td class="tableHeader" >备注</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${resultList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
							<td width="3%" >
								<input type="checkbox" name="items" value="aufnr=${obj.aufnr }&arbpl=${obj.arbpl }&storageId=${obj.storageId}&matnr=${obj.matnr }&idnrk=${obj.idnrk }&msehl1=${obj.msehl1 }&allNum=${obj.allNum }&orderBomId=${obj.orderBomId }&storageCancleId=${cancleId }&lgort=${obj.lgort }"/>
							</td>
							<td>${obj.aufnr }</td>
							<td>${obj.arbpl }</td>
							<td>${obj.storageId}</td>
							<td>${obj.matnr}</td>
							<td>${obj.idnrk}</td>
							<td>${obj.msehl1}</td>
							<td>${obj.allNum}</td>
							<td>${obj.zbz}</td>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="12" >
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>