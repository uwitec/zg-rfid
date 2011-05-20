<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
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
	<title><%=FwDictionary.TABLE_ALIAS%> 维护</title>
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
<form id="ec" action="<c:url value="/frame/sys/FwDictionary/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwDictionary.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="9" >
							<fw:page deleteUrl="/frame/sys/FwDictionary/delete.do"
									addUrl="/frame/sys/FwDictionary/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['DICT_BUSINESS_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_DICT_BUSINESS_ID')"  title="排序 dictBusinessId" ><%=FwDictionary.ALIAS_DICT_BUSINESS_ID %><%=map.get("ec_image_t0_DICT_BUSINESS_ID")==null?"":map.get("ec_image_t0_DICT_BUSINESS_ID")%></td>
							<c:set var="SHOW_DICT_BUSINESS_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['LABEL_CN'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=FwDictionary.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['VALUE'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_VALUE')"  title="排序 value" ><%=FwDictionary.ALIAS_VALUE %><%=map.get("ec_image_t0_VALUE")==null?"":map.get("ec_image_t0_VALUE")%></td>
							<c:set var="SHOW_VALUE" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['LABEL'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL')"  title="排序 label" ><%=FwDictionary.ALIAS_LABEL %><%=map.get("ec_image_t0_LABEL")==null?"":map.get("ec_image_t0_LABEL")%></td>
							<c:set var="SHOW_LABEL" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['DISPLAY_ORDER'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_DISPLAY_ORDER')"  title="排序 displayOrder" ><%=FwDictionary.ALIAS_DISPLAY_ORDER %><%=map.get("ec_image_t0_DISPLAY_ORDER")==null?"":map.get("ec_image_t0_DISPLAY_ORDER")%></td>
							<c:set var="SHOW_DISPLAY_ORDER" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['EXTEND'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_EXTEND')"  title="排序 extend" ><%=FwDictionary.ALIAS_EXTEND %><%=map.get("ec_image_t0_EXTEND")==null?"":map.get("ec_image_t0_EXTEND")%></td>
							<c:set var="SHOW_EXTEND" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/frame/sys/FwDictionary/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_DICT_BUSINESS_ID)}">
							<td><a onclick="javascript:showObject('${obj.dictBusinessId}','FW_DICT_BUSINESS','${obj.dictBusinessId_related.relatedBmClassId}','frame/sys');return false;" href="#">${obj.dictBusinessId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_LABEL_CN)}">
							<td>${obj.labelCn}</td>
								</c:if>
								<c:if test="${!(empty SHOW_VALUE)}">
							<td>${obj.value}</td>
								</c:if>
								<c:if test="${!(empty SHOW_LABEL)}">
							<td>${obj.label}</td>
								</c:if>
								<c:if test="${!(empty SHOW_DISPLAY_ORDER)}">
							<td>${obj.displayOrder}</td>
								</c:if>
								<c:if test="${!(empty SHOW_EXTEND)}">
							<td>${obj.extend}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/frame/sys/FwDictionary/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/frame/sys/FwDictionary/edit.do?id=${obj.cuid}&"/>">修改</a>
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
