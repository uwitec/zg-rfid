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
	<title><%=ZgTbackBom.TABLE_ALIAS%> 维护</title>
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
				parent.targetFrame('${ctx}/zg/plan/ZgTbackBom/edit.do?id='+cuid);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTbackBom/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/plan/ZgTbackBom/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTbackBom.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
						<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_AUFNR')"  title="排序 aufnr" ><%=ZgTbackBom.ALIAS_AUFNR %><%=map.get("ec_image_t0_AUFNR")==null?"":map.get("ec_image_t0_AUFNR")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MATNR')"  title="排序 matnr" ><%=ZgTbackBom.ALIAS_MATNR %><%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MAKTX')"  title="排序 maktx" ><%=ZgTbackBom.ALIAS_MAKTX %><%=map.get("ec_image_t0_MAKTX")==null?"":map.get("ec_image_t0_MAKTX")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MENGE_D')"  title="排序 mengeD" ><%=ZgTbackBom.ALIAS_MENGE_D %><%=map.get("ec_image_t0_MENGE_D")==null?"":map.get("ec_image_t0_MENGE_D")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_LGORT_D')"  title="排序 lgortD" ><%=ZgTbackBom.ALIAS_LGORT_D %><%=map.get("ec_image_t0_LGORT_D")==null?"":map.get("ec_image_t0_LGORT_D")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_BUDAT')"  title="排序 budat" ><%=ZgTbackBom.ALIAS_BUDAT %><%=map.get("ec_image_t0_BUDAT")==null?"":map.get("ec_image_t0_BUDAT")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_CPUTM')"  title="排序 cputm" ><%=ZgTbackBom.ALIAS_CPUTM %><%=map.get("ec_image_t0_CPUTM")==null?"":map.get("ec_image_t0_CPUTM")%></td>
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
							<td>${obj.aufnr}</td>
							<td>${obj.matnr}</td>
							<td>${obj.maktx}</td>
							<td>${obj.mengeD}</td>
							<td>${obj.lgortD}</td>
							<td>${obj.budatString}</td>
							<td>${obj.cputmString}</td>
							<td>
								<a attr="viewLink" href="javascript:" onclick="view('${obj.cuid}&')"/>查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="javascript:" onclick="edit('${obj.cuid}&')"/>修改</a>&nbsp;&nbsp;&nbsp;
							</td>
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
