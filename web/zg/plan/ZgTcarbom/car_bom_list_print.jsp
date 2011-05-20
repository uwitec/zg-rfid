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
String noexpandIcon = basePath+"/resources/images/frame/ico_noexpand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
		
			alert(parent.document.getElementById("s_bomCuids"));
		});
		
	</script>
	
	
<style>

</style>
</head>
<body>
<%
//String abc=request.getAttribute("abc").toString();
//List<Map> bomList=(List<Map>) request.getAttribute("bomList");
//System.out.print(bomList.size());

 %>
	
	<c:forEach items="${bomList}" var="sup" varStatus="v">asdfasd</c:forEach>
	
	<%
	//for(Map bom:bomList)
	//{
	//	out.print("BOM"+bom.get("IDNRK"));
	//}
	 %>
		</div>s
</body>
</html>