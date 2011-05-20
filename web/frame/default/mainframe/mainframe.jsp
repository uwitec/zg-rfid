<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>Framework</title>
		<script type="text/javascript"
			src="${ctx}/frame/default/mainframe/scripts/top.js"></script>
		<script>
		window.moveTo(0,0);
		window.resizeTo(  window.screen.availWidth,window.screen.availHeight);
		</script>
	</head>
	<frameset rows="85,*,25" id="mainFrame" id="mainFrame" >
		<frame name="headFrame" 
			src="<%=basePath%>/frame/default/mainframe/head.jsp" frameborder="0" />

		<frameset name="centerFrame" id="centerFrame" cols="220,*">
			<frame name="handFrame" noresize="noresize"
				src="<%=basePath%>/frame/default/mainframe/navTree.jsp"
				frameborder="0" />
			<frame id="bodyFrame" name="bodyFrame" noresize="noresize"
				src="<%=basePath%>${defaultUrl }" frameborder="0"/>
		</frameset>

		<frame name="footFrame"
			src="<%=basePath%>/frame/default/mainframe/foot.jsp" frameborder="0" />
	</frameset>
</html>
