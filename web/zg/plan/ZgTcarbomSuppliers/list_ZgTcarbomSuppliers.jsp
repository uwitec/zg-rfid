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
	<title><%=ZgTcarbomSuppliers.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			try{
				$("a[attr='viewLink'],input[attr='viewLink']").bind("click",function(e){
					var o = parent.accordion;
					if(o) {
						if(o.accordion( "option" , "active")+'' == '0') {
							o.accordion("activate", 0);
						}
					}
				});
				if(initLayout) {
					$(window).bind("load",initLayout);
					$(window).bind("resize",initLayout);
				}
			}catch(e){}
		});
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/plan/ZgTcarbomSuppliers/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTcarbomSuppliers.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="8" >
							<fw:page deleteUrl="/zg/plan/ZgTcarbomSuppliers/delete.do"
									addUrl="/zg/plan/ZgTcarbomSuppliers/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['CAR_BOM_ID'])}">
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_CAR_BOM_ID')"  title="排序 carBomId" ><%=ZgTcarbomSuppliers.ALIAS_CAR_BOM_ID %><%=map.get("ec_image_t0_CAR_BOM_ID")==null?"":map.get("ec_image_t0_CAR_BOM_ID")%></td>
							<c:set var="SHOW_CAR_BOM_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['LIFNR'])}">
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_LIFNR')"  title="排序 lifnr" ><%=ZgTcarbomSuppliers.ALIAS_LIFNR %><%=map.get("ec_image_t0_LIFNR")==null?"":map.get("ec_image_t0_LIFNR")%></td>
							<c:set var="SHOW_LIFNR" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['LIFNR_NAME'])}">
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_LIFNR_NAME')"  title="排序 lifnrName" ><%=ZgTcarbomSuppliers.ALIAS_LIFNR_NAME %><%=map.get("ec_image_t0_LIFNR_NAME")==null?"":map.get("ec_image_t0_LIFNR_NAME")%></td>
							<c:set var="SHOW_LIFNR_NAME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['CAR_NUM'])}">
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_CAR_NUM')"  title="排序 carNum" ><%=ZgTcarbomSuppliers.ALIAS_CAR_NUM %><%=map.get("ec_image_t0_CAR_NUM")==null?"":map.get("ec_image_t0_CAR_NUM")%></td>
							<c:set var="SHOW_CAR_NUM" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['SUPPLIERS_ID'])}">
							<td class="tableHeader"  onmouseover="this.className='tableHeaderSort';this.style.cursor='pointer'"  onmouseout="this.className='tableHeader';this.style.cursor='default'"  onclick="queryColumn(this,'t0_SUPPLIERS_ID')"  title="排序 suppliersId" ><%=ZgTcarbomSuppliers.ALIAS_SUPPLIERS_ID %><%=map.get("ec_image_t0_SUPPLIERS_ID")==null?"":map.get("ec_image_t0_SUPPLIERS_ID")%></td>
							<c:set var="SHOW_SUPPLIERS_ID" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/zg/plan/ZgTcarbomSuppliers/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_CAR_BOM_ID)}">
							<td>${obj.carBomId}</td>
								</c:if>
								<c:if test="${!(empty SHOW_LIFNR)}">
							<td>${obj.lifnr}</td>
								</c:if>
								<c:if test="${!(empty SHOW_LIFNR_NAME)}">
							<td>${obj.lifnrName}</td>
								</c:if>
								<c:if test="${!(empty SHOW_CAR_NUM)}">
							<td>${obj.carNum}</td>
								</c:if>
								<c:if test="${!(empty SHOW_SUPPLIERS_ID)}">
							<td>${obj.suppliersId}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTcarbomSuppliers/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTcarbomSuppliers/edit.do?id=${obj.cuid}&"/>">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="14" style="line-height: 13px">&nbsp;
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
