<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = request.getParameter("msg");
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<head>
	<base href="<%=basePath%>" />
	<title>系统登录</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="cache-control" content="No-store"/>
	<meta http-equiv="pragma" content="No-cach" />
	<meta http-equiv="expires" content="0" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/login.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
	<script src="${ctx}/dwr/interface/LoginDwrAction.js" type=""></script>
	
	<script language="javascript" event="onkeydown" for="document">  
	<!--
	if(event.keyCode==13)  
	{  
	  document.getElementById("submitBtn").click();  
	} 
	-->
	</script>  
	<script type=text/javascript>
		$(function() {
			window.moveTo(0,0);
			window.resizeTo(  window.screen.availWidth,window.screen.availHeight);
			var time = 0;

			$("#themeChoose").buttonset();

			//$("input:submit").button();

			$("#progressbar").progressbar({
				value: time
			});

			$("#submitBtn").bind('click',function(){
				$("#progressbar").removeClass('hide');
				waiting(time);
			});
		});
		

		function waiting(time) {
			if(time >= 100)time = 0;
			$("#progressbar").progressbar("value",time);
			setTimeout("waiting("+(time+1)+")",10);
		}

		function changeTheme(obj) {
			window.location.href = "Login/changeTheme.do?loginForm.theme="+obj.value;
		}
		
		
		function changeLoginStyle(){
				document.getElementById("loginForm.userId").value = "";
				document.getElementById("loginForm.password").value = "";
			var tips = document.getElementById("infoSpan").innerText;
			if(tips != "请输入用户名，密码登陆"){
				
				document.getElementById("loginForm.userId").readOnly  = false;
				document.getElementById("loginForm.password").readOnly  = false;
				document.getElementById("infoSpan").innerText = "请输入用户名，密码登陆";
				document.getElementById("loginType").value = "name";
				
			}else{
				document.getElementById("loginForm.userId").readOnly = "true";
				document.getElementById("loginForm.password").readOnly = "true";
				document.getElementById("infoSpan").innerText = "请刷卡登陆";
				document.getElementById("loginType").value = "card";
			}
		}
		
		
		 function scroll() {
		 		
		 	var loginType = document.getElementById("loginType").value;
		 		
		 	if(loginType != "name"){
	             var reader = document.getElementById("RFIDReader");
	             var infoSpan=document.getElementById("infoSpan");
	             var kCode=document.getElementById("kCode");
	             var userId;
	          
	             if (reader.readyState == 4) {
	             	
	                 var rl = reader.Code;
	                 if (rl == '-1') {//数据读取失败
	                    infoSpan.innerText = '数据读取失败';
	                    kCode.value=rl;
	                 }else if (rl < '-1') {//设备未正常连接
	                   // infoSpan.innerText = '设备连接失败，请重启设备';
	                   // document.getElementById("loginForm.lgort").options.length=0;
	                   // kCode.value=rl;
	             
	                 }else if(rl==''||rl==undefined){//没有卡的时候
	                 	 kCode.value=rl;
	
	                 }else {//正常读取卡号信息
	                 	if(kCode.value!=rl){
	                 		var userId="";
	                 		kCode.value=rl;
	                 		var flag=true;
	                 		DWREngine.setAsync(false);
	                 		LoginDwrAction.getUserByRfidCode(rl,function(data){//根据卡号获取员工信息
	                 			if(null!==data){
	                 				document.getElementById("loginForm.userId").value=data.userId;
	                 				document.getElementById("loginForm.password").value=data.password;
	                 				userId=data.userId;
	                 				login();
	                 			}else{
	                 			 	//infoSpan.innerText ="系统找不到该用户，请确认...";
	                 			 	alert("系统找不到该用户，请确认...");
	                 			 	flag=false;
	                 			}
	                 		});
	                 		if(!flag) return;
	                 	}
	                }
	             }else{
	             	//window.open("download.jsp");
	             }
	          }
         }
         
		function download(){
			window.open('download.jsp');
		}
		
		function login(){
			
			var userId=	document.getElementById("loginForm.userId").value ;
			var password = document.getElementById("loginForm.password").value ;
			if(userId == "" || password == ""){
				document.getElementById("loginForm.userId").value = "";
				document.getElementById("loginForm.password").value = "";
				alert("用户名或密码不能为空");
				return;
			}else{
				document.forms[0].submit();
			}
		}
		
		function init(){
			var flag='${loginFlag}';
			if(flag== "1"||flag== "2"){
				document.getElementById("loginForm.userId").readOnly  = false;
				document.getElementById("loginForm.password").readOnly  = false;
				//document.getElementById("infoSpan").innerText = "请输入用户名，密码登陆";
				document.getElementById("loginType").value = "name";
			}else{
				document.getElementById("loginForm.userId").readOnly = "true";
				document.getElementById("loginForm.password").readOnly = "true";
				//document.getElementById("infoSpan").innerText = "请刷卡登陆";
				document.getElementById("loginType").value = "card";
			}
		}
		
	</script>
</head>

<body onload="init(),setInterval('scroll()',1000)" >
<form action="Login/login.do" method="post" >
	<OBJECT id="RFIDReader" classid="clsid:93D9C558-C72F-4348-A071-BC594E06A0AA" style="visibility:hidden;"> </OBJECT>
			<input type="hidden" name="loginForm.theme" value="default"/>
			<input type="hidden" id="kCode"/>
			<div class="bei">
				<div class="center">
				</div>
				<div class="bottom">
					<table>
						<tr>
							<td colspan="3">
							<c:choose>
							<c:when test="${loginFlag=='1'||loginFlag=='2'}">
							<label id="infoSpan" name="infoSpan" style="color:red">请输入用户名，密码登陆</label>
							</c:when>
							<c:otherwise>
								<label id="infoSpan" name="infoSpan" style="color:red">请刷卡登陆</label>
							</c:otherwise>
							</c:choose>
							
							
							<input type = "hidden" id = "loginType" name ="loginType" value = "card"/>
							</td>
							</tr>
						<tr>
							<td>用户名：</td>
							<td>
								<input type="text" id = "loginForm.userId"  name="loginForm.userId" maxlength="70" readonly="readonly" 
									value="" class="wenben-L"/>
							</td>
							<td>
								<img src ="${ctx}/images/changeloginstyle.gif" id = "loginImg" width="18" height="16" onclick="changeLoginStyle()" title = "切换登陆方式"/></span>
							</td>

						</tr>
						<tr>
							<td>
								密  码：
							</td>
							<td>
								<input type="password" id = "loginForm.password" name="loginForm.password" value="" readonly = "true" 
									class="wenben-L" maxlength="85"/>
							</td>

						</tr>
						<tr>
							<td colspan="2">
						<%if(msg!=null && !msg.equals("")) {%>
								<span style="color: red;">会话超时,请重新登陆</span>
						<%}%>
							</td>
						</tr>
						<tr>
							<td colspan="2">
						<c:choose>
							<c:when test="${loginFlag eq 1}">
										<div id = "loginFlag" style="color: red;" >用户名或密码错误！</div>
							</c:when>
							<c:when test="${loginFlag eq 2}">
										<div id = "loginFlag" style="color: red;" >用户名或密码错误！</div>
							</c:when>
							<c:otherwise>
										<div id = "loginFlag" style = "color:red" ></div>
							</c:otherwise>
						</c:choose>
								</td>
						</tr>
						<tr>
							<td colspan="2">
								<span class="one"> <input type="button" onclick="login()" name="btnLogin"
										value="" id="submitBtn" id="btnLogin" class="btn" /> </span>
									<span class="download"><a href="RFIDSetup.exe"  > 下载RFID客户端</a>
									</span>
							</td>

							
						</tr>
						<tr>
							<td colspan="2"><span id="laberr"></span>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div>
		</form>
</body>
</html>