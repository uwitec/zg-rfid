<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
</head>
  
<body>
<div>
	<div class="grid-panel">
		<div class="title">列表标题</div>
		<div class="toolbar">
			<a href="javascript:"><span onclick="alert('操作按钮')"><img src="<%=iconPath%>/ico_search.gif" />操作按钮</span></a>
		</div>
		<div class="grid-data-panel">
			<table cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="tableHeader">第1列</td>
						<td class="tableHeader">第2列</td>
						<td class="tableHeader">第3列</td>
						<td class="tableHeader">第4列</td>
						<td class="tableHeader">第5列</td>
					</tr>
				</thead>
				<tbody>
					<tr class="odd">
						<td>1行1列</td>
						<td>1行2列</td>
						<td>1行3列</td>
						<td>1行4列</td>
						<td>1行5列</td>
					</tr>
					<tr class="even">
						<td>2行1列</td>
						<td>2行2列</td>
						<td>2行3列</td>
						<td>2行4列</td>
						<td>2行5列</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
