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
	<title><%=FwSystemNotice.TABLE_ALIAS%> 维护</title>
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
				parent.targetFrame('${ctx}/frame/sys/FwSystemNotice/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwSystemNotice/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/frame/sys/FwSystemNotice/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwSystemNotice.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">公告列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_TITLE')"  title="排序 title" ><%=FwSystemNotice.ALIAS_TITLE %><%=map.get("ec_image_t0_TITLE")==null?"":map.get("ec_image_t0_TITLE")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CONTENT')"  title="排序 content" ><%=FwSystemNotice.ALIAS_CONTENT %><%=map.get("ec_image_t0_CONTENT")==null?"":map.get("ec_image_t0_CONTENT")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CREATE_ID')"  title="排序 createId" ><%=FwSystemNotice.ALIAS_CREATE_ID %><%=map.get("ec_image_t0_CREATE_ID")==null?"":map.get("ec_image_t0_CREATE_ID")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CREATE_DATE')"  title="排序 createDate" ><%=FwSystemNotice.ALIAS_CREATE_DATE %><%=map.get("ec_image_t0_CREATE_DATE")==null?"":map.get("ec_image_t0_CREATE_DATE")%></td>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid}')">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>${obj.title}</td>
							<td align="left" title="${obj.content}">
							<c:choose>
								<c:when test="${fn:length(obj.content)>45}">
									${fn:substring(obj.content,0,45)}...
								</c:when>
								<c:otherwise>
									${obj.content}
								</c:otherwise>
							</c:choose>
							</td>
							<td>${obj.createId_related.value}</td>
							<td>	<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" />
							</td>
							
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
