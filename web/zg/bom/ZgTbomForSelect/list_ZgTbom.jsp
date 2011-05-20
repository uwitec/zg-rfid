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
	<base href="<%=basePath%>">
	<%@ include file="/commons/meta.jsp" %>
	<title><%=ZgTbom.TABLE_ALIAS%> 维护</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel=stylesheet>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
			try{
				parent.$('iframe[src*=<c:url value="/zg/bom/ZgTbom/list.do"/>]').css('height',$(document).height());
			}catch(e){}
		});
		
		function submitValue(bomId,bomName,maktx,msehl){
			var returnValue=new Object();
			returnValue.bomId=bomId;
			returnValue.maktx=maktx;
			returnValue.msehl=msehl;
			returnValue.bomName=bomName
			window.returnValue=returnValue;
			window.close();
		}
	</script>
</head>

<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/bom/ZgTbom/listBomForSelect.do"/>" method="get" style="display: inline;">
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
			<%@ include file="query_include_ZgTbom.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						
					
						<tr>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_CUID %><%=map.get("ec_image_MAKTX")==null?"":map.get("ec_image_MAKTX")%></td>
						
							<td class="tableHeader"  ><%=ZgTbom.ALIAS_MAKTX %><%=map.get("ec_image_MAKTX")==null?"":map.get("ec_image_MAKTX")%></td>
							<td class="tableHeader"  >生产厂<%=map.get("ec_image_LGORT")==null?"":map.get("ec_image_LGORT")%></td>
							
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_MATNR %><%=map.get("ec_image_MATNR")==null?"":map.get("ec_image_MATNR")%></td>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_MSEHL %><%=map.get("ec_image_MSEHL")==null?"":map.get("ec_image_MSEHL")%></td>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_MATKL %><%=map.get("ec_image_MATKL")==null?"":map.get("ec_image_MATKL")%></td>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_ZBZ%><%=map.get("ec_image_ZBZ")==null?"":map.get("ec_image_ZBZ")%></td>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_ZRZQD %><%=map.get("ec_image_ZRZQD")==null?"":map.get("ec_image_ZRZQD")%></td>
							<td class="tableHeader"   ><%=ZgTbom.ALIAS_LGORT %><%=map.get("ec_image_LGORT")==null?"":map.get("ec_image_LGORT")%></td>
						
						</tr>
					
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击选择" ondblclick="submitValue('${obj.cuid}','${obj.labelCn}','${obj.maktx}','${obj.msehl}')" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
						<td>${obj.labelCn}</td>
							<td>${obj.maktx}</td>
							<td>${obj.plant}</td>
							<td>${obj.matnr}</td>
							<td>${obj.msehl}</td>
							<td>${obj.matkl}</td>
							<td>${obj.zbz}</td>
							<td>${obj.zrzqd}</td>
							<td>${obj.lgortLabelCn}</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>

</html>

