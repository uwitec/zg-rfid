<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>

<html>
<head>
	<title>目标订单列表</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function submitAndClose(orderId) {
			window.returnValue=orderId;
			window.close();
		}
		function doLayout() {
			var maxHeight = top.dialogHeight;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
		}
	</script>
</head>
<body>
<form id="ec" action="${ctx}/zg/plan/ZgTorder/list.do" method="post" style="display: inline;">
	<div>
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">订单列表</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">排产日期</td>
							<td class="tableHeader">生产订单编号</td>
							<td class="tableHeader">原生产线</td>
							<td class="tableHeader">生产线</td>
							<td class="tableHeader">客户机型</td>
							<td class="tableHeader">销售订单号</td>
							<td class="tableHeader">生产厂</td>
							<td class="tableHeader">公司机型</td>
							<td class="tableHeader">成品需求数量</td>
							<td class="tableHeader">成品排序数量</td>
							<td class="tableHeader">成品编号</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${orderList}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击确定" ondblclick="submitAndClose('${obj.CUID}')">
							<td>${obj.PCDAT}</td>
							<td>${obj.AUFNR}</td>
							<td>${obj.ARBPL1}</td>
							<td>${obj.ARBPL}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td>${obj.KDAUF}</td>
							<td>${obj.PLANT}</td>
							<td align="left">${obj.MAKTX1}</td>
							<td align="center">${obj.PSMNG}</td>
							<td align="center">${obj.PMENGE}</td>
							<td>${obj.MATNR}</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="13" >
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