<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.storage.base.model.*" %>
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
	<title><%=ZgTstorageCancle.TABLE_ALIAS%> 维护</title>
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
			var maxHeight = parent.$("#listFrame").height() - 33;
			$("#grid-data-panel").height(maxHeight);
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}		

		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/storage/ZgTstorageCancle/edit.do?id='+cuid+'&productType=${productType }');
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/storage/ZgTstorageStat/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTstorageStat.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">库存查询</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_LGORT')"  title="排序 t0_LGORT" >仓库编号<%=map.get("ec_image_t0_LGORT")==null?"":map.get("ec_image_t0_LGORT")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_2_2_t0_LABEL_CN')"  title="排序 t0_LGORT" >仓库名称<%=map.get("ec_image_t0_2_2_t0_LABEL_CN")==null?"":map.get("ec_image_t0_2_2_t0_LABEL_CN")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_ID')"  title="排序 t0_LGORT" >生产订单编号<%=map.get("ec_image_t0_ORDER_ID")==null?"":map.get("ec_image_t0_ORDER_ID")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_3_3_t0_ARBPL')"  title="排序 t0_3_3_t0_ARBPL" >生产线<%=map.get("ec_image_t0_3_3_t0_ARBPL")==null?"":map.get("ec_image_t0_3_3_t0_ARBPL")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_3_3_t0_MATNR')"  title="排序 t0_3_3_t0_MATNR" >半成品物料号<%=map.get("ec_image_t0_3_3_t0_MATNR")==null?"":map.get("ec_image_t0_3_3_t0_ARBPL")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_IDNRK')"  title="排序 t0_IDNRK" >半成品名称<%=map.get("ec_image_t0_IDNRK")==null?"":map.get("ec_image_t0_IDNRK")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_3_3_t0_MSEHL1')"  title="排序 t0_3_3_t0_MSEHL1" >单位<%=map.get("ec_image_t0_3_3_t0_MSEHL1")==null?"":map.get("ec_image_t0_3_3_t0_MSEHL1")%></td>
						<td class="tableHeader"  onclick="queryColumn(this,'t0_NUM')"  title="排序 t0_NUM" >库存量<%=map.get("ec_image_t0_NUM")==null?"":map.get("ec_image_t0_NUM")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
							<td>${obj.lgort }</td>	
							<td>${obj.lgort_related.value}</td>
							<td>${obj.orderId_related.value}</td>	
							<td>${obj.arbpl }</td>	
							<td>${obj.matnr}</td>
							<td>${obj.idnrk }</td>	
							<td>${obj.msehl1}</td>
							<td>${obj.num}</td>
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
