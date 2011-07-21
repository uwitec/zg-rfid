<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <%@ include file="/commons/meta.jsp" %>
	<meta http-equiv="refresh" content="0;url=${url}">
</head>
  
<body>
	页面加载中,请稍后...
</body>
</html>