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
	<title><%=FwRole.TABLE_ALIAS%> 维护</title>
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
				
				var maxWidth = top.getContentWidth();
				document.getElementById("grid-data-panel").style.width=maxWidth+'px';
				document.getElementById("grid-panel").style.width=maxWidth+'px';
			}
		
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwRole/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwRole/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/frame/sys/FwRole/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwRole.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">角色列表</div>
			<div id="grid-data-panel"  class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" >角色名称<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							
							<td class="tableHeader"  onclick="queryColumn(this,'t0_NOTE')"  title="排序 note" >角色描述<%=map.get("ec_image_t0_NOTE")==null?"":map.get("ec_image_t0_NOTE")%></td>
							<c:set var="SHOW_NOTE" value="true" />
					
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid }')" >
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
					
							<td>${obj.labelCn}</td>
					
								
							<td>${obj.note}</td>
							<td>
								<a attr="viewLink" href="javascript:edit('${obj.cuid }')">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="12" >
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
