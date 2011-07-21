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
	<title><%=FwOrganization.TABLE_ALIAS%> 维护</title>
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
				
				var maxWidth = top.getContentWidth()-200;
				document.getElementById("grid-data-panel").style.width=maxWidth+'px';
				document.getElementById("grid-panel").style.width=maxWidth+'px';
			}
		
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwOrganization/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwOrganization/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/frame/sys/FwOrganization/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwOrganization.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">组织机构管理</div>
			<div class="toolbar" >
			</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table  cellspacing="0"  cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CUID')"  title="排序 cuid" >部门编号<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=FwOrganization.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PARENT_ORG_ID')"  title="排序 parentOrgId" ><%=FwOrganization.ALIAS_PARENT_ORG_ID %><%=map.get("ec_image_t0_PARENT_ORG_ID")==null?"":map.get("ec_image_t0_PARENT_ORG_ID")%></td>
							<c:set var="SHOW_PARENT_ORG_ID" value="true" />
						
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_TYPE')"  title="排序 t0_TYPE)" ><%=FwOrganization.ALIAS_TYPE %><%=map.get("ec_image_t0_TYPE")==null?"":map.get("ec_image_t0_TYPE")%></td>
							<c:set var="SHOW_TYPE" value="true" />
						
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LEVEL_NUM')"  title="排序 levelNum" ><%=FwOrganization.ALIAS_LEVEL_NUM %><%=map.get("ec_image_t0_LEVEL_NUM")==null?"":map.get("ec_image_t0_LEVEL_NUM")%></td>
							<c:set var="SHOW_LEVEL_NUM" value="true" />
							
							<td class="tableHeader"  onclick="queryColumn(this,'t0_NOTE')"  title="排序 note" ><%=FwOrganization.ALIAS_NOTE %><%=map.get("ec_image_t0_NOTE")==null?"":map.get("ec_image_t0_NOTE")%></td>
							<c:set var="SHOW_NOTE" value="true" />
						
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid }')">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<td>${obj.cuid}</td>
							<td>${obj.labelCn}</td>
							<td><a onclick="javascript:showObject('${obj.parentOrgId}','FW_ORGANIZATION','${obj.parentOrgId_related.relatedBmClassId}','frame/sys');return false;" href="#">${obj.parentOrgId_related.value}</a></td>
							
							<td>${obj.type_enum.value}</td>		
							
							<td>${obj.levelNum}</td>
								
							<td>${obj.note}</td>
								
							<td>
								<a attr="viewLink" href="javascript:view('${obj.cuid }')">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="javascript:edit('${obj.cuid}')">修改</a>
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
