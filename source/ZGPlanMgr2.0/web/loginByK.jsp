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
	<script src="${ctx}/dwr/interface/ZgTcarplanDwrAction.js" type=""></script>
	<script src="${ctx}/dwr/interface/LoginDwrAction.js" type=""></script>
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
		
        
        
        function scroll() {
             var reader = document.getElementById('RFIDReader');
             var infoSpan=document.getElementById('infoSpan');
             var kCode=document.getElementById("kCode");
             var userId;
              
             if (reader.readyState == 4) {
         
             
                 var rl = reader.Code;
                 if (rl == '-1') {//数据读取失败
                    infoSpan.innerText = '数据读取失败';
                    document.getElementById("loginForm.lgort").options.length=0;
                    kCode.value=rl;
                 }else if (rl < '-1') {//设备未正常连接
                   // infoSpan.innerText = '设备连接失败，请重启设备';
                   // document.getElementById("loginForm.lgort").options.length=0;
                   // kCode.value=rl;
                 }else if(rl==''||rl==undefined){//没有卡的时候
                 	 infoSpan.innerText ="请刷卡登陆...";
                 	 document.getElementById("loginForm.lgort").options.length=0;
                 	 document.getElementById("loginForm.userId").value="";
                 	 kCode.value=rl;
                 }else {//正常读取卡号信息
                 	if(kCode.value!=rl){
                 		var userId="";
                 		kCode.value=rl;
                 		infoSpan.innerText = "请选择仓库进行登陆...";
                 		var flag=true;
                 		DWREngine.setAsync(false);
                 		LoginDwrAction.getUserByRfidCode(rl,function(data){//根据卡号获取员工信息
                 			if(null!==data){
                 				document.getElementById("loginForm.userId").value=data.userId;
                 				document.getElementById("loginForm.password").value=data.password;
                 				userId=data.userId;
                 			}else{
                 			 	infoSpan.innerText ="系统找不到该用户，请确认...";
                 			 	flag=false;
                 			}
                 			
                 			
                 		});
                 		
                 		if(!flag) return;
                 		
                     	ZgTcarplanDwrAction.getLgortListByUserId(userId,function(data){//获取该员工的可领料仓库列表
	                     	if(null!=data&&data.length>0){
	                     	
	                     		buildSel('loginForm.lgort',data);
	                     	}else{
	                     		document.getElementById('loginForm.lgort').options.length=0;
	                     		infoSpan.innerText = "找不到领料信息...";
	                     	}
                     });
                 	}
                    
                 }
             }else{
             	window.open("download.jsp");
             }
         }
         
         function buildSel(id,data) {
         	var targetEl = document.getElementById(id);
			if(targetEl) {
				targetEl.innerHTML = "";
				if(data.length>0){
					var emptyOpt = document.createElement("option");
					emptyOpt.value="";
					emptyOpt.innerHTML = "请选择...";
					targetEl.appendChild(emptyOpt);
				}
				for(var i = 0; i< data.length; i++) {
					var labelCn = data[i].LABEL_CN;
					var cuid = data[i].CUID;
					var opt = document.createElement("option");
					opt.value=cuid;
					opt.innerHTML = labelCn;
					targetEl.appendChild(opt);
				}
			}
		}
		
		function login(){
			var lgort=document.getElementById('loginForm.lgort').value;
			if(lgort==""||lgort==undefined){
				alert("请选择仓库...");
				return;
			}
			document.forms[0].submit();
		}
		
		function download(){
			window.open('download.jsp');
		}

	</script>
</head>

<body onload="setInterval('scroll()',1000)">
<form action="Login/loginByK.do" method="post" >
   <OBJECT id="RFIDReader" classid="clsid:93D9C558-C72F-4348-A071-BC594E06A0AA" style="visibility:hidden;"> </OBJECT>
			<input type="hidden" name="loginForm.theme" value="default"/>
			<input type="hidden" id="kCode"/>
			<div class="bei">
				<div class="center">
				</div>
				<div class="bottom">
					<table>
						<tr>
							<td colspan="2">
							<span id="infoSpan" style="color:red"></span>
							
							</td>
							</tr>
						<tr>
							<td>
								用户名：
							</td>
							<td>
								<input type="text" readonly="true" name="loginForm.userId" id="loginForm.userId"
									value="${loginForm.userId}" class="wenben" />
										<input type="password" style="display:none" name="loginForm.password" id="loginForm.password" value=""
									class="wenben" />
							</td>

						</tr>
						<tr>
							<td>
								仓  库：
							</td>
							<td>
								<select id="loginForm.lgort" name="loginForm.lgort" style="width:142px"></select>
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
										<span style="color: red;">用户卡不存在，请确认！</span>
							</c:when>
							<c:when test="${loginFlag eq 2}">
										<span style="color: red;">用户不存在！</span>
							
							</c:when>
						</c:choose>
								</td>
						</tr>
						<tr>
							<td colspan="2">
								<span class="one"> <input type="button" name="btnLogin"
										value="" id="submitBtn" onclick="login()" id="btnLogin" class="btn" /> </span>
									<span class="download"><a href="RFIDSetup.exe"  > 下载RFID客户端</a>
									</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="laberr"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							</td>
						</tr>


					</table>
				</div>
			</div>

			<div>
		</form>
</body>
</html>