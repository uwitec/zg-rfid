<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>" />
    <title>Framework</title>
    <% String type=request.getParameter("type")==null?"":request.getParameter("type"); 
    	String viewModel=request.getParameter("viewModel")==null?"":request.getParameter("viewModel");
    	viewModel=request.getParameter("viewModel")==null?"":request.getParameter("viewModel");
    %>
    <script>
    </script>
  </head>
  	<frameset name="mainFrame" cols="120,*">
  	  	<frame name="treeFrame" src="<%=basePath%>/zg/plan/ZgTorderPlan/findArbplList.do?type=<%=type %>&viewModel=<%=viewModel %>" frameborder="0"/>
  		<frame name="queryFrame" src="" frameborder="0"/>
  	</frameset>
</html>
