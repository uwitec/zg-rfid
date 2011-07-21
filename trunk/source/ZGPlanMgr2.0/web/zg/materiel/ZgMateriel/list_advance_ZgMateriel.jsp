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
			try{
				$("a[attr='viewLink'],input[attr='viewLink']").bind("click",function(e){
					var o = parent.accordion;
					if(o) {
						if(o.accordion( "option" , "active")+'' == '0') {
							o.accordion("activate", 0);
						}
					}
				});
				if(initLayout) {
					$(window).bind("load",initLayout);
					$(window).bind("resize",initLayout);
				}
			}catch(e){}
		});
		function show(url){
		//	parent.document.getElementById("_operatePanel").contentWindow.location.href = url;
			window.location.href=url;
			var queryform=parent.document.getElementById('materielForm');
			queryform.style.display="none";
		}
		function edit(url){
			var queryform=parent.document.getElementById('materielForm');
			queryform.style.display="none";
			window.location.href=url;
			
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="${ctx}/zg/materiel/ZgMateriel/list.do?whetherParent=${whetherParent }&treeCuid=${treeCuid }" method="post" style="display: inline;">
<input type="hidden"  name="s_organizationName"  value="${pageRequest.filters.organizationName}" />
<input type="hidden"  name="s_virtualorgName"  value="${pageRequest.filters.virtualorgName}" />
<input type="hidden"  name="s_organizationId"  value="${pageRequest.filters.organizationId}" />
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
		<div class="grid-panel">
			<div class="title">提前领料物料组列表</div>
			<div class="grid-data-panel">
				<table cellspacing="0"  cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'zm_id')"  title="排序 id" >物料组编号<%=map.get("ec_image_zm_id")==null?"":map.get("ec_image_zm_id")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'zm_materiel_name')"  title="排序 materielName" ><%=ZgMateriel.ALIAS_MATERIEL_NAME %><%=map.get("ec_image_zm_materiel_name")==null?"":map.get("ec_image_zm_materiel_name")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'virtualorgName')"  title="排序 virtualorgName" ><%=ZgMateriel.ALIAS_VIRTUALORG_NAME %><%=map.get("ec_image_virtualorgName")==null?"":map.get("ec_image_virtualorgName")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'organizationName')"  title="排序 organizationName" ><%=ZgMateriel.ALIAS_ORGANIZATION_NAME %><%=map.get("ec_image_organizationName")==null?"":map.get("ec_image_organizationName")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'virtualorgType')"  title="排序 virtualorgType" ><%=ZgMateriel.ALIAS_VIRTUALORG_TYPE %><%=map.get("ec_image_virtualorgType")==null?"":map.get("ec_image_virtualorgType")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'virtualorgNote')"  title="排序 virtualorgNote" ><%=ZgMateriel.ALIAS_VIRTUALORG_NOTE %><%=map.get("ec_image_virtualorgNote")==null?"":map.get("ec_image_virtualorgNote")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.zgMaterielVirtualorg}&"/>
							</td>
							<td>${obj.id}</td>
							<td>${obj.materielName}</td>
							<td>${obj.virtualorgName}</td>
							<td>${obj.organizationName}</td>
							<td>${obj.virtualorgType}</td>
							<td>${obj.virtualorgNote}</td>
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
