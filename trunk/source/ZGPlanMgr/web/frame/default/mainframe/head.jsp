<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<base href="<%=basePath%>" />
	<title>Banner</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/widgets/jquery-treeview/jquery.treeview.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script type="text/javascript">
		function login(){
			top.location.href = "${ctx}/Login/loginOut.do";
		}
		
		function exit(){
			if (confirm('是否退出系统?')){
				top.window.location.href = "${ctx}/logout.jsp";
			}
		}
		
		function setPath(path) {
			document.getElementById("pathDiv").innerHTML = path;
		}
	</script>
</head>

<body>
	<div class="banner" style="position:relative">
		<div class="logo"></div>
		<div class="banner_info">
			<div class="logo_info">
				<div class=""></div>
				<div class="logo_info_rigth">
					<div class="logo_info_text">
						<span class="head_text">欢迎</span>
						<span class="head_text_yellow">${operatorInfo.userName}</span>
						<span class="head_text">使用志高空调RFID监控系统</span>
					</div>
					<div class="logo_info_btn">
						<input type="button" value="重新登录" onclick="login()" class="login_out_btn"/>
						<input type="button" value="退出" onclick="exit()"  class="exit_btn"/>
					</div>
				</div>
				<div class="nav_info_bar">
					<div id="pathDiv" class="nav_info">当前路径：导航树</div>
					<div class="nav_info_marquee">
						<marquee scrollAmount="2">
						<c:if test="${fn:length(sysNoticeList)>0}">
							<font color="red">
								<c:forEach items="${sysNoticeList}" var="obj">
									${obj.content }&nbsp;&nbsp;&nbsp;
								</c:forEach>
							</font>
						</c:if>
						<c:if test="${empty nullCarFlag }">欢迎${operatorInfo.userName}使用志高空调RFID监控系统</c:if>&nbsp;&nbsp;&nbsp;
						<c:if test="${nullCarFlag=='1' }">
							<font color="red">目前系统中存在未设置车型的BOM组件，导致无法装车，请设置!</font>
						</c:if>
						
						</marquee>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
