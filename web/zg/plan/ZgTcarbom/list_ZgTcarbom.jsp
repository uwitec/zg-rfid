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
	<title><%=ZgTcarbom.TABLE_ALIAS%> 维护</title>
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
					
				}
			}catch(e){}
		});
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="/zg/plan/ZgTcarbom/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTcarbom.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="9" >
							<fw:page deleteUrl="/zg/plan/ZgTcarbom/delete.do"
									addUrl="/zg/plan/ZgTcarbom/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['LABEL_CN'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=ZgTcarbom.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ORDER_PLAN_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_PLAN_ID')"  title="排序 orderPlanId" ><%=ZgTcarbom.ALIAS_ORDER_PLAN_ID %><%=map.get("ec_image_t0_ORDER_PLAN_ID")==null?"":map.get("ec_image_t0_ORDER_PLAN_ID")%></td>
							<c:set var="SHOW_ORDER_PLAN_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ORDER_BOM_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_BOM_ID')"  title="排序 orderBomId" ><%=ZgTcarbom.ALIAS_ORDER_BOM_ID %><%=map.get("ec_image_t0_ORDER_BOM_ID")==null?"":map.get("ec_image_t0_ORDER_BOM_ID")%></td>
							<c:set var="SHOW_ORDER_BOM_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ORDER_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_ID')"  title="排序 orderId" ><%=ZgTcarbom.ALIAS_ORDER_ID %><%=map.get("ec_image_t0_ORDER_ID")==null?"":map.get("ec_image_t0_ORDER_ID")%></td>
							<c:set var="SHOW_ORDER_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['PLAN_NUM'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PLAN_NUM')"  title="排序 planNum" ><%=ZgTcarbom.ALIAS_PLAN_NUM %><%=map.get("ec_image_t0_PLAN_NUM")==null?"":map.get("ec_image_t0_PLAN_NUM")%></td>
							<c:set var="SHOW_PLAN_NUM" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['REAL_NUM'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_REAL_NUM')"  title="排序 realNum" ><%=ZgTcarbom.ALIAS_REAL_NUM %><%=map.get("ec_image_t0_REAL_NUM")==null?"":map.get("ec_image_t0_REAL_NUM")%></td>
							<c:set var="SHOW_REAL_NUM" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/zg/plan/ZgTcarbom/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_LABEL_CN)}">
							<td>${obj.labelCn}</td>
								</c:if>
								<c:if test="${!(empty SHOW_ORDER_PLAN_ID)}">
							<td><a onclick="javascript:showObject('${obj.orderPlanId}','ZG_T_ORDERBOM','${obj.orderPlanId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.orderPlanId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_ORDER_BOM_ID)}">
							<td><a onclick="javascript:showObject('${obj.orderBomId}','ZG_T_ORDERBOM','${obj.orderBomId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.orderBomId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_ORDER_ID)}">
							<td><a onclick="javascript:showObject('${obj.orderId}','ZG_T_ORDER','${obj.orderId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.orderId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_PLAN_NUM)}">
							<td>${obj.planNum}</td>
								</c:if>
								<c:if test="${!(empty SHOW_REAL_NUM)}">
							<td>${obj.realNum}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTcarbom/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTcarbom/edit.do?id=${obj.cuid}&"/>">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="16" style="line-height: 13px">&nbsp;
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
