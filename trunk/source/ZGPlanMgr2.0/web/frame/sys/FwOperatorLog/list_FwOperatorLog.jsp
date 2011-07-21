<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
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
	<title><%=FwOperatorLog.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/default/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/default/grid.css" rel="stylesheet" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
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
				
				var maxWidth = top.getContentWidth();
				document.getElementById("grid-data-panel").style.width=maxWidth+'px';
				document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwOperatorLog/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwOperatorLog/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/frame/sys/FwOperatorLog/list.do"/>" method="post" style="display: inline;">
<% 
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
	<div>
	
		<div>
			<%@ include file="query_include_FwOperatorLog.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">用户操作列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CREATE_ID')"  title="排序 createId" ><%=FwOperatorLog.ALIAS_CREATE_ID %><%=map.get("ec_image_t0_CREATE_ID")==null?"":map.get("ec_image_t0_CREATE_ID")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CREATE_DATE')"  title="排序 createDate" ><%=FwOperatorLog.ALIAS_CREATE_DATE %><%=map.get("ec_image_t0_CREATE_DATE")==null?"":map.get("ec_image_t0_CREATE_DATE")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_TARGET_ID')"  title="排序 targetId" ><%=FwOperatorLog.ALIAS_TARGET_ID %><%=map.get("ec_image_t0_TARGET_ID")==null?"":map.get("ec_image_t0_TARGET_ID")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_ACTION')"  title="排序 action" ><%=FwOperatorLog.ALIAS_ACTION %><%=map.get("ec_image_t0_ACTION")==null?"":map.get("ec_image_t0_ACTION")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_OPERATOR_TYPE')"  title="排序 operatorType" ><%=FwOperatorLog.ALIAS_OPERATOR_TYPE %><%=map.get("ec_image_t0_OPERATOR_TYPE")==null?"":map.get("ec_image_t0_OPERATOR_TYPE")%></td>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid}')">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td><a onclick="javascript:showObject('${obj.createId}','FW_EMPLOYEE','${obj.createId_related.relatedBmClassId}','frame/sys');return false;" href="#">${obj.createId_related.value}</a></td>
							<td>
							<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" />
							</td>
							<td>${obj.targetName}</td>
							<td>${obj.action}</td>
							<td>${obj.operatorType}</td>
						</tr>
					</c:forEach>
					
						<tr>
							<td colspan="8" >
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
