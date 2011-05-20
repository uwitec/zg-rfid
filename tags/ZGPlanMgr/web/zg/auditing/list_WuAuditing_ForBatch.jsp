<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
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
<html>
	<head>
		<base href="<%=basePath%>" />
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
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
			var maxHeight = top.getContentHeight();
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
		}
		
		function edit(orderPlanId) {
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/wu/auditing/AuditingbomForBatch/findBomBatchList.do?orderPlanId='+orderPlanId);
			}
		}
	</script>
	</head>
	<body>
		<form id="ec" action="<c:url value="/wu/auditing/AuditingForBatch/listForBatch.do"/>" method="post" style="display: inline;">
			<input type="hidden" id="stateData" value="${s_state }"/>
			<div>
				<div>
					<%@ include file="query_include_WuAuditing.jsp"%>
				</div>
				<div class="grid-panel" id="grid-panel">
					<div class="title">审核批量领料计划</div>
					<div class="toolbar">
					</div>
					<div class="grid-data-panel" id="grid-data-panel">
						<table id="ec_table" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
							<td class="tableHeader"  onclick="queryColumn(this,'CUID')"  title="排序 CUID" >单据编码<%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'PLAN_DATE')"  title="排序 PLAN_DATE" >创建时间<%=map.get("ec_image_PLAN_DATE")==null?"":map.get("ec_image_PLAN_DATE")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'USER_NAME')"  title="排序 USER_NAME" >创建人<%=map.get("ec_image_USER_NAME")==null?"":map.get("ec_image_USER_NAME")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'DEPARTMENT_NAME')"  title="排序 DEPARTMENT_NAME" >部门<%=map.get("ec_image_DEPARTMENT_NAME")==null?"":map.get("ec_image_DEPARTMENT_NAME")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'STATE')"  title="排序 PLAN_END_TIME" >状态<%=map.get("ec_image_STATE")==null?"":map.get("ec_image_STATE")%></td>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="obj" varStatus="n">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									<tr class="${trcss}" title="双击查看详情"
										ondblclick="edit('${obj.cuid }')">
										<td align="center">
											${obj.cuid }
										</td>
										<td align="center">
											<fmt:formatDate value="${obj.planDate}" pattern="yyyy-MM-dd  HH:mm:ss" />
										</td>
										<td align="center">
											${obj.userName}
										</td>
										<td align="center">
											${obj.departmentName}
										</td>
										<td align="center">
											<a onclick="javascript:return false;" href="#" title="${obj.state}">${obj.state_enum.value}</a>
										</td>
									</tr>
								</c:forEach>
								<tr style="padding: 0px;">
									<td colspan="12">
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