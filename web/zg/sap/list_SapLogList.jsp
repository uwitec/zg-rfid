<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTstorage.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$(window).bind("resize",doLayout);
		});
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/storage/ZgTstorage/edit.do?id='+cuid+'&type=${type }&productType=${productType }');
			}
		}
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/storage/ZgTstorage/edit.do?id='+cuid+'&type=${type }&productType=${productType }&view=true');
			}
		}
		function doLayout() {
			var maxHeight = parent.$("#listFrame").height() - 33;
			$("#grid-data-panel").height(maxHeight);
		}
	</script>
</head>
<body>
<form id="ec" action="<c:url value="/zg/storage/ZgTstorage/list.do"/>" method="post" style="display: inline;">
	<div>
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
					日志列表
			</div>
			<div class="toolbar">
			</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CUID')"  title="排序 cuid" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CREATE_DATE')"  title="排序 createDate" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_ID')"  title="排序 orderId" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ARBPL')"  title="排序 arbpl" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LGORT')"  title="排序 lgort" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_DEPT_ID')"  title="排序 deptId" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CREATOR_ID')"  title="排序 creatorId" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ZBZ')"  title="排序 zbz" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_STATE')"  title="排序 state" ></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<c:if test="${obj.state!='8'}">
							<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid }')"  onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
						</c:if>
						<c:if test="${obj.state=='8'}">
							<tr class="${trcss}" title="双击查看" ondblclick="view('${obj.cuid }')"  onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
						</c:if>
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>${obj.cuid}</td>
							<td>${obj.createDateString}</td>
							<td>${obj.orderId_related.value}</td>
							<td>${obj.arbpl_related.value}</td>
							<td>${obj.lgort_related.value}</td>
							<td>${obj.deptId_related.value}</td>
							<td>${obj.creatorId_related.value}</td>
							<td>${obj.zbz}</td>
							<td><a onclick="javascript:return false;" href="#" title="${obj.state}">${obj.state_enum.value}</a></td>		
						</tr>
					</c:forEach>
						<tr>
							<td colspan="10" >
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