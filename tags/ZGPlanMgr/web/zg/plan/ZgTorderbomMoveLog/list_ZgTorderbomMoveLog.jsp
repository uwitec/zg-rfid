<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
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
	<title><%=ZgTorderbomMoveLog.TABLE_ALIAS%> 维护</title>
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
				parent.targetFrame('${ctx}/zg/plan/ZgTorderbomMoveLog/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTorderbomMoveLog/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/plan/ZgTorderbomMoveLog/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTorderbomMoveLog.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_SOURCE_ORDER_ID')"  title="排序 sourceOrderId" ><%=ZgTorderbomMoveLog.ALIAS_SOURCE_ORDER_ID %><%=map.get("ec_image_t0_SOURCE_ORDER_ID")==null?"":map.get("ec_image_t0_SOURCE_ORDER_ID")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_SOURCE_IDNRK')"  title="排序 sourceIdnrk" ><%=ZgTorderbomMoveLog.ALIAS_SOURCE_IDNRK %><%=map.get("ec_image_t0_SOURCE_IDNRK")==null?"":map.get("ec_image_t0_SOURCE_IDNRK")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_TARGET_ORDER_ID')"  title="排序 targetOrderId" ><%=ZgTorderbomMoveLog.ALIAS_TARGET_ORDER_ID %><%=map.get("ec_image_t0_TARGET_ORDER_ID")==null?"":map.get("ec_image_t0_TARGET_ORDER_ID")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MOVE_NUM')"  title="排序 moveNum" ><%=ZgTorderbomMoveLog.ALIAS_MOVE_NUM %><%=map.get("ec_image_t0_MOVE_NUM")==null?"":map.get("ec_image_t0_MOVE_NUM")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_TARGET_IDNRK')"  title="排序 targetIdnrk" ><%=ZgTorderbomMoveLog.ALIAS_TARGET_IDNRK %><%=map.get("ec_image_t0_TARGET_IDNRK")==null?"":map.get("ec_image_t0_TARGET_IDNRK")%></td>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid}')">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td><a onclick="javascript:showObject('${obj.sourceOrderId}','ZG_T_ORDER','${obj.sourceOrderId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.sourceOrderId_related.value}</a></td>
							<td>${obj.sourceIdnrk}</td>
							<td><a onclick="javascript:showObject('${obj.targetOrderId}','ZG_T_ORDER','${obj.targetOrderId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.targetOrderId_related.value}</a></td>
							<td>${obj.moveNum}</td>
							<td>${obj.targetIdnrk}</td>
							<td>
								<a attr="viewLink" href="javascript:" onclick="view('${obj.cuid}&')"/>查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="javascript:" onclick="edit('${obj.cuid}&')"/>修改</a>&nbsp;&nbsp;&nbsp;
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
