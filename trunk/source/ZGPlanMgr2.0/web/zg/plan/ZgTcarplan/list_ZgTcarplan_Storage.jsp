<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
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
	<title><%=ZgTcarplan.TABLE_ALIAS%> 维护</title>
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
		
		function edit1(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE2.do?carPlanId='+cuid);
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel2.do?carPlanId='+cuid);
			}
		}
		
		function edit2(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE3.do?carPlanId='+cuid);
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel3.do?carPlanId='+cuid);
			}
		}
		
		function view1(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE2.do?carPlanId='+cuid+'&view=true');
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel2.do?carPlanId='+cuid+'&isView=true');
			}
		}
		
		function view2(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE3.do?carPlanId='+cuid+'&view=true');
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel3.do?carPlanId='+cuid+'&isView=true');
			}
		}
	
	</script>
</head>
<body>
<form id="ec" action="<c:url value="/zg/plan/ZgTcarplan/listStorage.do"/>" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_ZgTcarplan.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
			<c:choose>
			<c:when test="${orderPlanType=='3'}">批量领料</c:when>
			<c:otherwise>计划领料</c:otherwise>
			</c:choose></div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							
							<td class="tableHeader"  onclick="queryColumn(this,'CUID')"  title="排序 CUID" ><%=ZgTcarplan.ALIAS_CUID %><%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'CREATE_DATE')"  title="排序 CREATE_DATE" ><%=ZgTcarplan.ALIAS_CREATE_DATE %><%=map.get("ec_image_CREATE_DATE")==null?"":map.get("ec_image_CREATE_DATE")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'CARUSER_LABELCN')"  title="排序 CARUSER_LABELCN" ><%=ZgTcarplan.ALIAS_CAR_USER_ID %><%=map.get("ec_image_CARUSER_LABELCN")==null?"":map.get("ec_image_CARUSER_LABELCN")%></td>
							
							<td class="tableHeader"  onclick="queryColumn(this,'CARUSER')"  title="排序 carUser" ><%=ZgTcarplan.ALIAS_CAR_USER %><%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
								<c:if test="${orderPlanType=='3'}">
							<td class="tableHeader"  onclick="queryColumn(this,'CAR_DATE')"  title="排序 CAR_DATE" >领料时间<%=map.get("ec_image_CAR_DATE")==null?"":map.get("ec_image_CAR_DATE")%></td>
							</c:if>
							<!-- <td class="tableHeader"  onclick="queryColumn(this,'DEPARTMENT_NAME')"  title="排序 departMent" ><%=ZgTcarplan.ALIAS_CARUSER_DEPT_LABELCN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td> -->
							<c:if test="${orderPlanType!='3'}">
							<td class="tableHeader"  onclick=""   >线体描述<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							</c:if>
							<td class="tableHeader"  onclick="queryColumn(this,'STORAGE_NAME')"  title="排序 storageName" ><%=ZgTcarplan.ALIAS_STORAGE_LABLECN %><%=map.get("ec_image_STORAGE_NAME")==null?"":map.get("ec_image_STORAGE_NAME")%></td>
							<td class="tableHeader"   >仓管员<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<td class="tableHeader"   >单据状态<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>

							
							<c:if test="${!(empty attrMap['CAR_STATE'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'CAR_STATE')"  title="排序 carState" ><%=ZgTcarplan.ALIAS_CAR_STATE %><%=map.get("ec_image_t0_CAR_STATE")==null?"":map.get("ec_image_t0_CAR_STATE")%></td>
							<c:set var="SHOW_CAR_STATE" value="true" />
							</c:if>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<c:choose>
								<c:when test="${type eq '2' and obj.carState ne '8' and orderPlanType  ne '3' }">
							<tr class="${trcss}" title="双击编辑" ondblclick="edit1('${obj.cuid }')">
								</c:when>
									<c:when test="${type eq '2' and obj.carState eq '8' and orderPlanType  ne '3' }">
							<tr class="${trcss}" title="双击查看详情" ondblclick="view1('${obj.cuid }')">
								</c:when>
								<c:when test="${type eq '2' and obj.carState ne '8' and orderPlanType  eq '3' }">
							<tr class="${trcss}" title="双击编辑" ondblclick="edit2('${obj.cuid }')">
								</c:when>
								<c:when test="${type eq '2' and obj.carState eq '8' and orderPlanType  eq '3' }">
							<tr class="${trcss}" title="双击查看详情" ondblclick="view2('${obj.cuid }')">
								</c:when>
								<c:otherwise>
							<tr class="${trcss}">
								</c:otherwise>
						</c:choose>
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>${obj.cuid} </td>
							<td><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${obj.carUser}</td>							
							<td><a onclick="javascript:showObject('${obj.carUser}','FW_EMPLOYEE','${obj.carUser_related.relatedBmClassId}','frame/sys');return false;" href="#">${obj.carUser_related.value}</a></td>
							<c:if test="${orderPlanType=='3'}">
							
							<td><fmt:formatDate value="${obj.carDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</c:if>
							<!-- <td>${obj.carUser_dept_Id_related.value}</td> -->
							<c:if test="${orderPlanType!='3'}">
								<td>${obj.sortf}</td>
								</c:if>
								<td>${obj.storageId_related.value}</td>
								<td>${obj.storageUserId_related.value}</td>	
								<td>${obj.carState_enum.value}</td>
							
						</tr>
					</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="12" >
							<%@include file="/frame/default/ux/pagebar.jsp" %>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
