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
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTcarplan.TABLE_ALIAS%> 维护</title>
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
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
		}
	</script>
</head>
<body>
<form id="ec" action="<c:url value="/frame/sys/TsysIfaceLog/list.do"/>" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_TsysIfaceLog.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
			日志列表</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">编号<%=map.get("ec_image_t0_SER_CALLER")==null?"":map.get("ec_image_t0_SER_CALLER")%></td>
							<td class="tableHeader"><%=TsysIfaceLog.ALIAS_INTERFACE_NAME %><%=map.get("ec_image_t0_INTERFACE_NAME")==null?"":map.get("ec_image_t0_INTERFACE_NAME")%></td>
							<td class="tableHeader"><%=TsysIfaceLog.ALIAS_DATA_STAUTS %><%=map.get("ec_image_t0_DATA_STAUTS")==null?"":map.get("ec_image_t0_DATA_STAUTS")%></td>
							<td class="tableHeader"><%=TsysIfaceLog.ALIAS_CALL_TIME %><%=map.get("ec_image_t0_CALL_TIME")==null?"":map.get("ec_image_t0_CALL_TIME")%></td>
							<td class="tableHeader"><%=TsysIfaceLog.ALIAS_REMARK %><%=map.get("ec_image_t0_REMARK")==null?"":map.get("ec_image_t0_REMARK")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" >
							<td>${obj.batchNo}</td>
								<td>${obj.methodName}</td>
						
							<td>${obj.dataStauts}</td>
							<td>
							<fmt:formatDate value="${obj.callTime}" pattern="yyyy-MM-dd  HH:mm:ss" />
							</td>
							<td>${obj.remark}</td>
						</tr>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="12" >
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
