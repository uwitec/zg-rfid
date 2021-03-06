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
<meta http-equiv="Page-Enter" content="RevealTrans (Duration=1, Transition=12)"> 
<meta http-equiv="Page-Exit" content="RevealTrans (Duration=1, Transition=12)"> 
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
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
			if(dealIEVersion(6)){
				height=height-25;
			}
			document.getElementById("grid-data-panel").style.height=height+'px';
				document.getElementById("grid-panel").style.height=height+'px';
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
		}
	</script>
</head>
<body>
<form id="ec" action="${ctx}/zg/report/Report/lgortReport.do" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_lgort.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">仓库出仓报表</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader">生产订单号<%=map.get("ec_image_t0_PCDAT")==null?"":map.get("ec_image_t0_PCDAT")%></td>
							<td class="tableHeader">产品编码<%=map.get("ec_image_t0_AUFNR")==null?"":map.get("ec_image_t0_AUFNR")%></td>
							<td class="tableHeader">产品型号<%=map.get("ec_image_t0_ARBPL")==null?"":map.get("ec_image_t0_ARBPL")%></td>
							<td class="tableHeader">销售订单号<%=map.get("ec_image_t0_AUFNR")==null?"":map.get("ec_image_t0_AUFNR")%></td>
							<td class="tableHeader">销售订单项目编号<%=map.get("ec_image_t0_ARBPL")==null?"":map.get("ec_image_t0_ARBPL")%></td>
							<td class="tableHeader">领料部门<%=map.get("ec_image_t0_MAKTX2")==null?"":map.get("ec_image_t0_MAKTX2")%></td>
							<td class="tableHeader">领料线体<%=map.get("ec_image_t0_KDAUF")==null?"":map.get("ec_image_t0_KDAUF")%></td>
							<td class="tableHeader">物料编码<%=map.get("ec_image_t0_PLANT")==null?"":map.get("ec_image_t0_PLANT")%></td>
							<td class="tableHeader">物料描述<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">项目编号<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
								<td class="tableHeader">物料类型<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">抬头备注<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">物料备注<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">包装方式<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
							<td class="tableHeader">发料仓库<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">生产批量<%=map.get("ec_image_t0_MAKTX1")==null?"":map.get("ec_image_t0_MAKTX1")%></td>
							<td class="tableHeader">领料类型<%=map.get("ec_image_t0_PSMNG")==null?"":map.get("ec_image_t0_PSMNG")%></td>
							<td class="tableHeader">领料数量<%=map.get("ec_image_t0_PSMNG")==null?"":map.get("ec_image_t0_PSMNG")%></td>
							<td class="tableHeader">供应商代码<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
							<td class="tableHeader">供应商<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
							<td class="tableHeader">领料员<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader">领料刷卡时间<%=map.get("ec_image_t0_MAKTX1")==null?"":map.get("ec_image_t0_MAKTX1")%></td>
							<td class="tableHeader">仓管员<%=map.get("ec_image_t0_PSMNG")==null?"":map.get("ec_image_t0_PSMNG")%></td>
							<td class="tableHeader">确认刷卡时间<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
								<td class="tableHeader">批次<%=map.get("ec_image_t0_PSMNG")==null?"":map.get("ec_image_t0_PSMNG")%></td>
							<td class="tableHeader">认证清单<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}"  ondblclick="view('${obj.cuid}')">
							<td>${obj.AUFNR}</td>
							<td>${obj.MATNR}</td>
							<td>${obj.MAKTX1}</td>
							<td>${obj.KDAUF}</td>
							<td>${obj.KDPOS}</td>
							<td align="center">${ obj.PLANT}
							</td>
							<td>${obj.ARBPL}</td>
							<td>${obj.IDNRK}</td>
							<td align="left">${obj.MAKTX2}</td>
							<td >${obj.POSNR}</td>
							<td>${obj.BOM_TYPE }</td>
							<td>${obj.PLANZBZ}</td>
							<td>${obj.PLANBOMZBZ}</td>
							<td align="center">${obj.SORTF}</td>
							<td>${obj.LGORT}</td>
							<td>${obj.CAR_NUM}</td>
							<td align="center">
							<c:choose>
							 <c:when test="${fn:indexOf(obj.ORDER_PLAN_TYPE,'BACK')>=0}">退料</c:when>
							 <c:when test="${obj.ORDER_PLAN_TYPE=='CHANGE'}">换料</c:when>
							 <c:when test="${obj.ORDER_PLAN_TYPE=='RENEW'}">补领料</c:when>
							 <c:otherwise>正常领料</c:otherwise>
							</c:choose>
							</td>
							<td align="center">${obj.REAL_NUM}</td>
							<td align="left">${obj.LIFNR}</td>
							<td align="left">${obj.LIFNR_NAME}</td>
							<td>${obj.CAR_USER_NAME}</td>
							<td>${obj.CREATE_DATE}</td>
							<td align="center">${obj.STORAGE_USER_NAME}</td>
							<td align="center">${obj.CAR_DATE}</td>
							
							<td align="center">${obj.BATCHNO}</td>
							<td align="center">${obj.ZRZQD}</td>
							
								
						</tr>
					</c:forEach>
						<tr>
							<td colspan="26" >
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