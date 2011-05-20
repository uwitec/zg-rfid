<%@ page language="java" pageEncoding="GBK"%>
<%
	//request.getSession().invalidate();
	System.out.println(11);
	request.getSession().removeAttribute("operatorInfo");
	request.getSession().removeAttribute("isAdmin");
	request.getSession().removeAttribute("_theme");
	request.getSession().removeAttribute("loginLgort");
	System.out.println(22);
%>
<script>
	top.window.close();
</script>
