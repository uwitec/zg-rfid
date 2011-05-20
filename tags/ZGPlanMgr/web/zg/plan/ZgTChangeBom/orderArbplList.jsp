<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*"%>
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
		<base target="_self"/>
		<title>订单生产线</title>
		<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
		<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			doLayout();
			$(window).bind("resize",doLayout);
		});
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.dialogWidth;
			maxWidth = maxWidth.replace("px","")*1;
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
		}
			
		function returnAndClose(orderID,aufnr,arbpl,arbplName){
			var obj=new Object();
			obj.orderID=orderID;
			obj.aufnr=aufnr;
			obj.arbpl=arbpl;
			obj.arbplName=arbplName;
			window.returnValue=obj;
			window.close();
		}
		
	</script>
	<body   scrolling="no">
		<form action="${ctx}/zg/plan/ZgTBomManager/findOrderArbplList.do" method="post"id="ec" >
			<input  type="hidden" id="type" name="type" value="${type }"/>
	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
    <div class="grid-panel" id="grid-panel">
	<div class="title">订单生产列表</div>
	<div class="grid-data-panel" id="grid-data-panel">
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
							<td class="tableHeader">
								订单编号
							</td>
							<td class="tableHeader">
								生产线
							</td>
							<td class="tableHeader">
								物料描述
							</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}"var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
							<tr class="${trcss}"							
							 title="双击选择" ondblclick="returnAndClose('${obj.orderId}','${obj.aufnr}','${obj.arbpl}','${obj.labelCn}')" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
								
								<td align="center">
									${obj.aufnr}
								</td>
								<td align="center">
									${obj.arbpl}
								</td>
								<td  align="center">
									${obj.maktx1}
								</td>
							</tr>
					</c:forEach>
					<tr>
						<td colspan="3" >
							<%@include file="/frame/default/ux/pagebar.jsp" %>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		</form>
	</body>
</html>