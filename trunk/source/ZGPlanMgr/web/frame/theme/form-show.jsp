<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
</head>
<body>
    <div class="form_panel">
		<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top:3px;">
			<caption>
				<label class="expandbtn">
					<img src="<%=expandIcon %>" for="tbody_1"/>
					<a href="javascript:"><span>表单标题</span></a>
				</label>
			</caption>
			<tbody class="unexpand" attr="tbody_1">
				<tr>	
					<td class="label">字段1</td>
					<td></td>
					<td class="label">字段2</td>
					<td></td>
					<td class="label">字段3</td>
					<td></td>
					<td class="label">字段4</td>
					<td></td>
				</tr>
				<tr>	
					<td class="label">字段5</td>
					<td></td>
					<td class="label">字段6</td>
					<td></td>
					<td class="label">字段7</td>
					<td></td>
					<td class="label">字段8</td>
					<td></td>
				</tr>
				<tr>	
					<td class="label">字段9</td>
					<td></td>
					<td class="label">字段10</td>
					<td></td>
					<td class="label">字段11</td>
					<td></td>
					<td class="label">字段12</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>