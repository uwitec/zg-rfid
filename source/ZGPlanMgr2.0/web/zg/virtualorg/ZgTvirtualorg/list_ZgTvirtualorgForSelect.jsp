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
	<title><%=ZgMateriel.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
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
			var height = maxHeight - 57;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			
		}
	</script>
</head>
<body>&nbsp; 
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="${ctx}/zg/virtualorg/ZgTvirtualorg/listMateridel.do" method="post" style="display: inline;">
	 
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
		<%@ include file="query_include_ZgMateriel.jsp" %> 
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">物料组列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0"  cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"   >物料组编号<%=map.get("ec_image_zm_id")==null?"":map.get("ec_image_zm_id")%></td>
							<td class="tableHeader"   ><%=ZgMateriel.ALIAS_MATERIEL_NAME %><%=map.get("ec_image_zm_materiel_name")==null?"":map.get("ec_image_zm_materiel_name")%></td>
							<td class="tableHeader"   >仓库编号<%=map.get("ec_image_virtualorgName")==null?"":map.get("ec_image_virtualorgName")%></td>
							<td class="tableHeader"   >仓库名称<%=map.get("ec_image_organizationName")==null?"":map.get("ec_image_organizationName")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td width="3%" >
								<input type="checkbox" name="items" value="${obj.id}"/>
							</td>
							<td>${obj.id}</td>
							<td>${obj.materielName}</td>
							<td>${obj.organizationId}</td>
							<td>${obj.organizationName}</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="13" >&nbsp;<%@include file="/frame/default/ux/pagebar.jsp" %>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
