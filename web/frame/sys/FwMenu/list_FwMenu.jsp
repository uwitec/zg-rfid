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
	<title><%=FwMenu.TABLE_ALIAS%> 维护</title>
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
					var maxHeight = parent.$("#listFrame").height() - 33;
			$("#grid-data-panel").height(maxHeight);
				
				var maxWidth = top.getContentWidth();
				document.getElementById("grid-data-panel").style.width=maxWidth+'px';
				document.getElementById("grid-panel").style.width=maxWidth+'px';
			}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/frame/sys/FwMenu/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwMenu.jsp" %>
		</div>
			<div id="grid-panel" class="grid-panel">
			<div class="title">菜单列表</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" >菜单名称<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PARENT_MENU_ID')"  title="排序 parentMenuId" >父菜单<%=map.get("ec_image_t0_PARENT_MENU_ID")==null?"":map.get("ec_image_t0_PARENT_MENU_ID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_URL')"  title="排序 url" >菜单链接<%=map.get("ec_image_t0_URL")==null?"":map.get("ec_image_t0_URL")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_DISPLAY_ORDER')"  title="排序 displayOrder" >顺序<%=map.get("ec_image_t0_DISPLAY_ORDER")==null?"":map.get("ec_image_t0_DISPLAY_ORDER")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LEVEL_NUM')"  title="排序 levelNum" >级别<%=map.get("ec_image_t0_LEVEL_NUM")==null?"":map.get("ec_image_t0_LEVEL_NUM")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_TYPE')"  title="排序 type" >菜单控制类型<%=map.get("ec_image_t0_TYPE")==null?"":map.get("ec_image_t0_TYPE")%></td>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="parent.targetFrame('${ctx}/frame/sys/FwMenu/edit.do?id=${obj.cuid}&')" >
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>${obj.labelCn}</td>
							<td><a onclick="javascript:showObject('${obj.parentMenuId}','FW_MENU','${obj.parentMenuId_related.relatedBmClassId}','frame/sys');return false;" href="#">${obj.parentMenuId_related.value}</a></td>
							<td>${obj.url}</td>
							<td>${obj.displayOrder}</td>
							<td>${obj.levelNum}</td>
							<td>${obj.type}</td>
							<td>
								<a attr="viewLink" href="javascript:parent.targetFrame('${ctx}/frame/sys/FwMenu/show.do?id=${obj.cuid}')">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="javascript:parent.targetFrame('${ctx}/frame/sys/FwMenu/edit.do?id=${obj.cuid}')">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="9" >
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
