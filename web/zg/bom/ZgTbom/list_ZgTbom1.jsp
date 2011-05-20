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
		
		function edit(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE2.do?carPlanId='+cuid);
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel4.do?carPlanId='+cuid);
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
<form id="ec" action="<c:url value="/zg/bom/ZgTbom/list1.do"/>" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_ZgTbom.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
			备料库存</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">生产订单编码<%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader">销售订单号 <%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader">项目号 <%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader">生产厂<%=map.get("ec_image_CREATE_DATE")==null?"":map.get("ec_image_CREATE_DATE")%></td>
							<td class="tableHeader">生产线<%=map.get("ec_image_CREATE_DATE")==null?"":map.get("ec_image_CREATE_DATE")%></td>
							<td class="tableHeader">排产日期<%=map.get("ec_image_CARUSER_LABELCN")==null?"":map.get("ec_image_CARUSER_LABELCN")%></td>
							<td class="tableHeader">物料编码<%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
							<td class="tableHeader">物料描述<%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
							<td class="tableHeader">大小量岗<%=map.get("ec_image_STORAGE_NAME")==null?"":map.get("ec_image_STORAGE_NAME")%></td>
							<td class="tableHeader">库存量<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<td class="tableHeader">待退料数量<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}">						
							<td>${obj.AUFNR}</td>
							<td>${obj.KDAUF}</td>
							<td>${obj.POSNR}</td>
							<td>${obj.PLANT}</td>
							<td>${obj.ARBPL}</td>
							<td>${obj.PCDAT}</td>
							<td>${obj.IDNRK}</td>
							<td>${obj.MAKTX2}</td>
							<td>${obj.ZBZ}</td>
							<td>${obj.STORAGE_NUM}</td>
							<td>${obj.WAIT_BACK_NUM}</td>
						</tr>
					</c:forEach>
					<tr>
							<td colspan="11" >
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
