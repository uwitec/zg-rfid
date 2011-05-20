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
    	String orderPlanType=request.getParameter("orderPlanType")==null?"":request.getParameter("orderPlanType");
    	String onload=request.getParameter("onload")==null?"":request.getParameter("onload");
    	String defaulArbpl=request.getParameter("defaulArbpl")==null?"":request.getParameter("defaulArbpl");
    	String viewModel=request.getParameter("viewModel")==null?"":request.getParameter("viewModel");
    %>
  </head>
  	<frameset name="mainFrame" cols="120,*">
  	  	<frame name="treeFrame" src="<%=basePath%>/zg/plan/ZgTcarplan/findArbplList.do?type=<%=type %>&orderPlanType=<%=orderPlanType %>&onload=<%=onload%>&defaulArbpl=<%=defaulArbpl %>&viewModel=<%=viewModel %>" frameborder="0"/>
  		<frame name="queryFrame" src="" frameborder="0"/>
  	</frameset>
</html>
