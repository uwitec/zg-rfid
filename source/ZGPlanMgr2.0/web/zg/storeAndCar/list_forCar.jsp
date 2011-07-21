<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<base target="_self"/>
		<title>车型信息列表</title>
		<%@ include file="/commons/meta.jsp" %>
		<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
		<script type="text/javascript">
				$(function() {
				init();
			});
			function init() {
				doLayout();
				$(window).bind("resize",doLayout);
			}
			
			function doLayout() {
				var maxHeight = parent.$("#listFrame").height() - 13;
				if(dealIEVersion(6)){
					maxHeight=maxHeight-25;
				}
				$("#grid-data-panel").height(maxHeight);
				$("#grid-panel").height(maxHeight);
	
			}
			
		function returnAndClose(cuid,labelCn,code){
			var obj=new Object();
			obj.cuid=cuid;//拿回userId
			obj.labelCn=labelCn;
			obj.code=code;
			window.returnValue=obj;
			
		//	alert(obj);
			window.close();
		}
		
	</script>
<body> <!--    scrolling="no" -->
<form id="ec" action="${ctx}/frame/sys/ZgBomCar/listForCar.do"	method="post" >
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
	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
<div class="grid-panel" id="grid-panel">
	<div class="title">车型信息列表</div>
	<div class="grid-data-panel" id="grid-data-panel">
		<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
			<thead>
				<tr>
					<td class="tableHeader">
						车型名称
					</td>
					<td class="tableHeader">
						车型编号
					</td>
					<td class="tableHeader">
						车型描述
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
						<tr class="${trcss}"							
						 title="双击选择" ondblclick="returnAndClose('${obj.cuid}','${obj.labelCn}','${obj.code}')" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
							
							<td align="center">
								${obj.labelCn}
							</td>
							<td align="center">
								${obj.code}
							</td>
							<td  align="center">
								${obj.description}
							</td>
						</tr>
				</c:forEach>
				<tr style="padding: 0px;" >
					<td colspan="3" >
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