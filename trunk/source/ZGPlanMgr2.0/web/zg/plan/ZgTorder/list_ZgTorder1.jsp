<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
List<SortInfo> list = ((PageRequest)request.getAttribute("pageRequest")).getSortInfos();
Map map = new HashMap();
for(SortInfo si:list){
	map.put("ec_s_"+si.getColumnName(),si.getSortOrder());
	String image="";
	if("asc".equalsIgnoreCase(si.getSortOrder())){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-bottom.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
		map.put("ec_image_"+si.getColumnName(),image);
	}else if("desc".equalsIgnoreCase(si.getSortOrder())){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-top.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
		map.put("ec_image_"+si.getColumnName(),image);
	}
}
%>
<html>
<meta http-equiv="Page-Enter" content="RevealTrans (Duration=1, Transition=12)"> 
<meta http-equiv="Page-Exit" content="RevealTrans (Duration=1, Transition=12)"> 
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
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
			var maxHeight = parent.document.getElementById("listFrame").style.height;
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
			<%@ include file="query_include_ZgTorder.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">订单管理</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">排产日期</td>
							<td class="tableHeader">生产订单编号</td>
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
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击确定" ondblclick="submitAndClose('${obj.cuid}')">
							<td>${obj.pcdatString}</td>
							<td>${obj.aufnr}</td>
							<td>${obj.arbpl}</td>
							<td align="left">${obj.maktx2}</td>
							<td>${obj.kdauf}</td>
							<td>${obj.plant}</td>
							<td align="left">${obj.maktx1}</td>
							<td align="center">${obj.psmng}</td>
							<td align="center">${obj.pmenge}</td>
							<td>${obj.matnr}</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="13" >
								<%@include file="/frame/default/ux/pagebar.jsp" %>
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