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
	<title><%=FwOperator.TABLE_ALIAS%> 维护</title>
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
<form id="ec" action="<c:url value="/frame/sys/FwOperator/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwOperator.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="11" >
							<fw:page deleteUrl="/frame/sys/FwOperator/delete.do"
									addUrl="/frame/sys/FwOperator/create.do"
									exportUrl="/frame/excel/sys/export.do"/>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<c:if test="${!(empty attrMap['LABEL_CN'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LABEL_CN')"  title="排序 labelCn" ><%=FwOperator.ALIAS_LABEL_CN %><%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<c:set var="SHOW_LABEL_CN" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['USER_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_USER_ID')"  title="排序 userId" ><%=FwOperator.ALIAS_USER_ID %><%=map.get("ec_image_t0_USER_ID")==null?"":map.get("ec_image_t0_USER_ID")%></td>
							<c:set var="SHOW_USER_ID" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['PASSWORD'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_PASSWORD')"  title="排序 password" ><%=FwOperator.ALIAS_PASSWORD %><%=map.get("ec_image_t0_PASSWORD")==null?"":map.get("ec_image_t0_PASSWORD")%></td>
							<c:set var="SHOW_PASSWORD" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['CREATE_TIME'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_CREATE_TIME')"  title="排序 createTime" ><%=FwOperator.ALIAS_CREATE_TIME %><%=map.get("ec_image_t0_CREATE_TIME")==null?"":map.get("ec_image_t0_CREATE_TIME")%></td>
							<c:set var="SHOW_CREATE_TIME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['LAST_MODIFY_TIME'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LAST_MODIFY_TIME')"  title="排序 lastModifyTime" ><%=FwOperator.ALIAS_LAST_MODIFY_TIME %><%=map.get("ec_image_t0_LAST_MODIFY_TIME")==null?"":map.get("ec_image_t0_LAST_MODIFY_TIME")%></td>
							<c:set var="SHOW_LAST_MODIFY_TIME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['EFFECT_TIME'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_EFFECT_TIME')"  title="排序 effectTime" ><%=FwOperator.ALIAS_EFFECT_TIME %><%=map.get("ec_image_t0_EFFECT_TIME")==null?"":map.get("ec_image_t0_EFFECT_TIME")%></td>
							<c:set var="SHOW_EFFECT_TIME" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['STATUS'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_STATUS')"  title="排序 status" ><%=FwOperator.ALIAS_STATUS %><%=map.get("ec_image_t0_STATUS")==null?"":map.get("ec_image_t0_STATUS")%></td>
							<c:set var="SHOW_STATUS" value="true" />
							</c:if>
							<c:if test="${!(empty attrMap['RELATED_BM_CLASS_ID'])}">
							<td class="tableHeader"  onclick="queryColumn(this,'t0_RELATED_BM_CLASS_ID')"  title="排序 relatedBmClassId" ><%=FwOperator.ALIAS_RELATED_BM_CLASS_ID %><%=map.get("ec_image_t0_RELATED_BM_CLASS_ID")==null?"":map.get("ec_image_t0_RELATED_BM_CLASS_ID")%></td>
							<c:set var="SHOW_RELATED_BM_CLASS_ID" value="true" />
							</c:if>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="window.location.href='<c:url value="/frame/sys/FwOperator/edit.do?id=${obj.cuid}&"/>'" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
								<c:if test="${!(empty SHOW_LABEL_CN)}">
							<td>${obj.labelCn}</td>
								</c:if>
								<c:if test="${!(empty SHOW_USER_ID)}">
							<td>${obj.userId}</td>
								</c:if>
								<c:if test="${!(empty SHOW_PASSWORD)}">
							<td>${obj.password}</td>
								</c:if>
								<c:if test="${!(empty SHOW_CREATE_TIME)}">
							<td>${obj.createTimeString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_LAST_MODIFY_TIME)}">
							<td>${obj.lastModifyTimeString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_EFFECT_TIME)}">
							<td>${obj.effectTimeString}</td>
								</c:if>
								<c:if test="${!(empty SHOW_STATUS)}">
							<td>${obj.status}</td>
								</c:if>
								<c:if test="${!(empty SHOW_RELATED_BM_CLASS_ID)}">
							<td>${obj.relatedBmClassId}</td>
								</c:if>
							<td>
								<a attr="viewLink" href="<c:url value="/frame/sys/FwOperator/show.do?id=${obj.cuid}&"/>">查看</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="<c:url value="/frame/sys/FwOperator/edit.do?id=${obj.cuid}&"/>">修改</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="20" style="line-height: 13px">&nbsp;
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>
