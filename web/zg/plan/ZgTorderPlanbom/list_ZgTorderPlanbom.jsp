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
	<title><%=ZgTorderPlanbom.TABLE_ALIAS%> 维护</title>
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
<form id="ec" action="<c:url value="/zg/plan/ZgTorderPlanbom/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTorderPlanbom.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="14" >
							<fw:page deleteUrl="/zg/plan/ZgTorderPlanbom/delete.do"
									addUrl="/zg/plan/ZgTorderPlanbom/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['LABEL_CN'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=ZgTorderPlanbom.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ORDER_BOM_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_BOM_ID')"  title="排序 orderBomId" ><%=ZgTorderPlanbom.ALIAS_ORDER_BOM_ID %><%=map.get("ec_image_t0_ORDER_BOM_ID")==null?"":map.get("ec_image_t0_ORDER_BOM_ID")%></td>
							<c:set var="SHOW_ORDER_BOM_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ORDER_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORDER_ID')"  title="排序 orderId" ><%=ZgTorderPlanbom.ALIAS_ORDER_ID %><%=map.get("ec_image_t0_ORDER_ID")==null?"":map.get("ec_image_t0_ORDER_ID")%></td>
							<c:set var="SHOW_ORDER_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['DEPARTMENT_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_DEPARTMENT_ID')"  title="排序 departmentId" ><%=ZgTorderPlanbom.ALIAS_DEPARTMENT_ID %><%=map.get("ec_image_t0_DEPARTMENT_ID")==null?"":map.get("ec_image_t0_DEPARTMENT_ID")%></td>
							<c:set var="SHOW_DEPARTMENT_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['USER_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_USER_ID')"  title="排序 userId" ><%=ZgTorderPlanbom.ALIAS_USER_ID %><%=map.get("ec_image_t0_USER_ID")==null?"":map.get("ec_image_t0_USER_ID")%></td>
							<c:set var="SHOW_USER_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['PLAN_DATE'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PLAN_DATE')"  title="排序 planDate" ><%=ZgTorderPlanbom.ALIAS_PLAN_DATE %><%=map.get("ec_image_t0_PLAN_DATE")==null?"":map.get("ec_image_t0_PLAN_DATE")%></td>
							<c:set var="SHOW_PLAN_DATE" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['PLAN_START_TIME'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PLAN_START_TIME')"  title="排序 planStartTime" ><%=ZgTorderPlanbom.ALIAS_PLAN_START_TIME %><%=map.get("ec_image_t0_PLAN_START_TIME")==null?"":map.get("ec_image_t0_PLAN_START_TIME")%></td>
							<c:set var="SHOW_PLAN_START_TIME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['PLAN_END_TIME'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PLAN_END_TIME')"  title="排序 planEndTime" ><%=ZgTorderPlanbom.ALIAS_PLAN_END_TIME %><%=map.get("ec_image_t0_PLAN_END_TIME")==null?"":map.get("ec_image_t0_PLAN_END_TIME")%></td>
							<c:set var="SHOW_PLAN_END_TIME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['STATE'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_STATE')"  title="排序 state" ><%=ZgTorderPlanbom.ALIAS_STATE %><%=map.get("ec_image_t0_STATE")==null?"":map.get("ec_image_t0_STATE")%></td>
							<c:set var="SHOW_STATE" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['COMPLETE_NUM'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_COMPLETE_NUM')"  title="排序 completeNum" ><%=ZgTorderPlanbom.ALIAS_COMPLETE_NUM %><%=map.get("ec_image_t0_COMPLETE_NUM")==null?"":map.get("ec_image_t0_COMPLETE_NUM")%></td>
							<c:set var="SHOW_COMPLETE_NUM" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['CAR_NUM'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CAR_NUM')"  title="排序 carNum" ><%=ZgTorderPlanbom.ALIAS_CAR_NUM %><%=map.get("ec_image_t0_CAR_NUM")==null?"":map.get("ec_image_t0_CAR_NUM")%></td>
							<c:set var="SHOW_CAR_NUM" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/zg/plan/ZgTorderPlanbom/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_LABEL_CN)}">
							<td>${obj.labelCn}</td>
								</c:if>
								<c:if test="${!(empty SHOW_ORDER_BOM_ID)}">
							<td><a onclick="javascript:showObject('${obj.orderBomId}','ZG_T_ORDERBOM','${obj.orderBomId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.orderBomId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_ORDER_ID)}">
							<td><a onclick="javascript:showObject('${obj.orderId}','ZG_T_ORDER','${obj.orderId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.orderId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_DEPARTMENT_ID)}">
							<td><a onclick="javascript:showObject('${obj.departmentId}','FW_ORGANIZATION','${obj.departmentId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.departmentId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_USER_ID)}">
							<td><a onclick="javascript:showObject('${obj.userId}','FW_EMPLOYEE','${obj.userId_related.relatedBmClassId}','zg/plan');return false;" href="#">${obj.userId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_PLAN_DATE)}">
							<td>${obj.planDateString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_PLAN_START_TIME)}">
							<td>${obj.planStartTimeString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_PLAN_END_TIME)}">
							<td>${obj.planEndTimeString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_STATE)}">
							<td><a onclick="javascript:return false;" href="#" title="${obj.state}">${obj.state_enum.value}</a></td>		
								</c:if>
								<c:if test="${!(empty SHOW_COMPLETE_NUM)}">
							<td>${obj.completeNum}</td>
								</c:if>
								<c:if test="${!(empty SHOW_CAR_NUM)}">
							<td>${obj.carNum}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTorderPlanbom/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/zg/plan/ZgTorderPlanbom/edit.do?id=${obj.cuid}&"/>">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="26" style="line-height: 13px">&nbsp;
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
