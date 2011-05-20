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
		
		function edit(cuid,aufnr){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel4.do?carPlanId='+cuid+'&aufnr='+aufnr);
			}
		}
		
		
		
		function view(cuid,aufnr){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel4.do?carPlanId='+cuid+'&aufnr='+aufnr+'&flag=view');
			}
		}
	
	</script>
</head>
<body>
<form id="ec" action="<c:url value="/zg/plan/ZgTcarplan/list2.do"/>" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_ZgTcarplan.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
			补领单据</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  ><%=ZgTcarplan.ALIAS_CUID %><%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader"  >生产订单号<%=map.get("ec_image_CREATE_DATE")==null?"":map.get("ec_image_CREATE_DATE")%></td>
						
							<td class="tableHeader" ><%=ZgTcarplan.ALIAS_CREATE_DATE %><%=map.get("ec_image_CREATE_DATE")==null?"":map.get("ec_image_CREATE_DATE")%></td>
							<td class="tableHeader" >提交人编码<%=map.get("ec_image_CARUSER_LABELCN")==null?"":map.get("ec_image_CARUSER_LABELCN")%></td>
							
							<td class="tableHeader">提交人<%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
							<!-- <td class="tableHeader"  ><%=ZgTcarplan.ALIAS_CARUSER_DEPT_LABELCN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td> -->
							<td class="tableHeader"  ><%=ZgTcarplan.ALIAS_STORAGE_ID %><%=map.get("ec_image_STORAGE_NAME")==null?"":map.get("ec_image_STORAGE_NAME")%></td>
							<td class="tableHeader"  ><%=ZgTcarplan.ALIAS_STORAGE_LABLECN %><%=map.get("ec_image_STORAGE_NAME")==null?"":map.get("ec_image_STORAGE_NAME")%></td>
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
								<c:when test="${ obj.carState ne '8' }">
							<tr class="${trcss}" title="双击编辑" ondblclick="edit('${obj.cuid}','${obj.aufnr} ')">
								</c:when>
									<c:when test="${obj.carState eq '8'  }">
							<tr class="${trcss}" title="双击查看详情" ondblclick="view('${obj.cuid }','${obj.aufnr} ')">
								</c:when>
						</c:choose>
							<td>${obj.cuid} </td>
							<td>${obj.aufnr} </td>
							<td><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${obj.createUserId}</td>							
							<td>${obj.createUserId_related.value}</td>
							<td>${obj.storageId}</td>
								<td>${obj.storageId_related.value}</td>
								<td>
									<c:if test="${obj.carState=='0'}">未确认</c:if>
									<c:if test="${obj.carState=='8'}">已确认</c:if>
								</td>
							
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
