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
	<title><%=ZgTstoragebom.TABLE_ALIAS%> 维护</title>
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
<form id="ec" action="<c:url value="/zg/storage/ZgTstoragebom/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_ZgTstoragebom.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="8" >
							<fw:page deleteUrl="/zg/storage/ZgTstoragebom/delete.do"
									addUrl="/zg/storage/ZgTstoragebom/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['LABEL_CN'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=ZgTstoragebom.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ZG_T_STORAGE_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ZG_T_STORAGE_ID')"  title="排序 zgTstorageId" ><%=ZgTstoragebom.ALIAS_ZG_T_STORAGE_ID %><%=map.get("ec_image_t0_ZG_T_STORAGE_ID")==null?"":map.get("ec_image_t0_ZG_T_STORAGE_ID")%></td>
							<c:set var="SHOW_ZG_T_STORAGE_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['MANTR'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MANTR')"  title="排序 mantr" ><%=ZgTstoragebom.ALIAS_MANTR %><%=map.get("ec_image_t0_MANTR")==null?"":map.get("ec_image_t0_MANTR")%></td>
							<c:set var="SHOW_MANTR" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['NUM'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_NUM')"  title="排序 num" ><%=ZgTstoragebom.ALIAS_NUM %><%=map.get("ec_image_t0_NUM")==null?"":map.get("ec_image_t0_NUM")%></td>
							<c:set var="SHOW_NUM" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['ZBZ'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ZBZ')"  title="排序 zbz" ><%=ZgTstoragebom.ALIAS_ZBZ %><%=map.get("ec_image_t0_ZBZ")==null?"":map.get("ec_image_t0_ZBZ")%></td>
							<c:set var="SHOW_ZBZ" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/zg/storage/ZgTstoragebom/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_LABEL_CN)}">
							<td>${obj.labelCn}</td>
								</c:if>
								<c:if test="${!(empty SHOW_ZG_T_STORAGE_ID)}">
							<td><a onclick="javascript:showObject('${obj.zgTstorageId}','ZG_T_STORAGE','${obj.zgTstorageId_related.relatedBmClassId}','zg/storage');return false;" href="#">${obj.zgTstorageId_related.value}</a></td>
								</c:if>
								<c:if test="${!(empty SHOW_MANTR)}">
							<td>${obj.mantr}</td>
								</c:if>
								<c:if test="${!(empty SHOW_NUM)}">
							<td>${obj.num}</td>
								</c:if>
								<c:if test="${!(empty SHOW_ZBZ)}">
							<td>${obj.zbz}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/zg/storage/ZgTstoragebom/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/zg/storage/ZgTstoragebom/edit.do?id=${obj.cuid}&"/>">修改</a>
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
