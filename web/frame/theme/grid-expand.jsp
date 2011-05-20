<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbom.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
</head>
  
<body>
<div>
	<div class="grid-panel">
		<div class="title">xxx管理</div>
		<div class="toolbar">
			<a href="javascript:"><span onclick="alert('查询')"><img src="<%=iconPath%>/ico_search.gif" />查询</span></a>
		</div>
		<div class="grid-data-panel">
			<table cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader">111</td>
						<td class="tableHeader">222</td>
						<td class="tableHeader">333</td>
						<td class="tableHeader">444</td>
						<td class="tableHeader">555</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="group" colspan="5">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" for="1232103123123"/>
								<a href="javascript:">1232103123123</a>
							</div>
						</td>
					</tr>
					<tr class="odd unexpand" attr="1232103123123">
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
					<tr class="even unexpand" attr="1232103123123">
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
					<tr>
						<td class="group" colspan="5">
							<div class="expandbtn">
								<img src="<%=expandIcon %>" for="1232103123124"/>
								<a href="javascript:">1232103123123</a>
							</div>
						</td>
					</tr>
					<tr class="odd unexpand" attr="1232103123124">
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
					<tr class="even unexpand" attr="1232103123124">
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="line-height: 13px">&nbsp;
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
</body>
</html>
