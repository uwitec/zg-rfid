<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.bom.base.model.*" %>
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
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<title><%=ZgTbom.TABLE_ALIAS%> 维护</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel=stylesheet/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		function doLayout() {
			parent.$('iframe[src*=<c:url value="/zg/bom/ZgCarInfo/list.do"/>]').css('height',$(document).height());

			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
	
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/bom/ZgCarInfo/edit.do?id='+cuid);
			}
		}
		
		function show(cuid){
				parent.targetFrame('${ctx}/zg/bom/ZgCarInfo/show.do?id='+cuid);
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/bom/ZgCarInfo/list.do"/>" method="post" style="display: inline;">
<input type="hidden" name="labelCn" value="${labelCn }"/>
<input type="hidden" name="code" value="${code }"/>
<% 
	List<SortInfo> list = ((PageRequest)request.getAttribute("pageRequest")).getSortInfos();
	Map map = new HashMap();
	for(SortInfo si:list){
		map.put("ec_s_"+si.getColumnName(),si.getSortOrder());
		String image="";
		if("asc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/sortAsc.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}else if("desc".equalsIgnoreCase(si.getSortOrder())){
			image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/sortDesc.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
			map.put("ec_image_"+si.getColumnName(),image);
		}
	}
%>
	<div>
		<div>
			<%@ include file="query_include_ZgCarInfo.jsp" %>
		</div>
			<div class="grid-panel" id="grid-panel">
				<div class="title">车辆列表</div>
				<div class="grid-data-panel" id="grid-data-panel">
					<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader" >
							<input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"/>
							</td>
							<td class="tableHeader"  onclick="queryColumn(this,'CODE')"  title="排序 code" ><%=ZgCarInfo.ALIAS_CODE %><%=map.get("ec_image_CODE")==null?"":map.get("ec_image_CODE")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'LABEL_CN')"  title="排序 labelcn" ><%=ZgCarInfo.ALIAS_LABEL_CN %><%=map.get("ec_image_LABEL_CN")==null?"":map.get("ec_image_LABEL_CN")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'DESCRIPTION')"  title="排序 description" ><%=ZgCarInfo.ALIAS_DESCRIPTION %><%=map.get("ec_image_DESCRIPTION")==null?"":map.get("ec_image_DESCRIPTION")%></td>
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
							<td align="center">${obj.code}</td>
							<td align="center">${obj.labelCn}</td>
							<td align="center">${obj.description}</td>
							<td align="center">
								<a href="javascript:show('${obj.cuid }')">查看</a>&nbsp;&nbsp;&nbsp;
								<a href="javascript:edit('${obj.cuid }')">修改</a>
							</td>
						</tr>						
					</c:forEach>
						<td colspan="13" >
								<%@include file="/frame/default/ux/pagebar.jsp" %>
						</td>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</form>
</body>
</html>

