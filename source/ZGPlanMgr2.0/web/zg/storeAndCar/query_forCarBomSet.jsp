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
		<base href="<%=basePath%>" />
		<base target="_self"/><!-- 提交给自己 -->
		<title>车型选择</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<!-- <script src="${ctx}/dwr/interface/ZgMaterielDwrAction.js" type=""></script>   -->	
		<script type="text/javascript">
			function backTree(){
				window.close();
			}
		function doLayout() {
			var maxHeight = top.dialogHeight;
			maxHeight=maxHeight.replace("px","");
			var queryPanelH = maxHeight;
			document.getElementById("_queryPanel").style.height = queryPanelH + 'px';
			var queryTableH = document.getElementById("query_table").offsetHeight;
			var listFrameH = queryPanelH - queryTableH;
			document.getElementById("listFrame").style.height = listFrameH + 'px';
		}
		function returnAndClose(){
			//加入校验
			var idnrk=document.getElementById('idnrk').value;
			var matkl=document.getElementById('matkl').value;
			var carCuid=document.getElementById('carName').value;
			var carNum=document.getElementById('carNum').value;
			if(carCuid==""){
				alert('请选择车型!');
				return ;
			}
			if(carNum==""){
				alert('请输入装载数量!');
				return ;
			}
			if(!isInt(carNum)){
				alert('请输入正整数!');
				return ;
			}
		//	if($("form[validator]").valid()&&carCuid!=""){
				document.forms[0].action="${ctx}/frame/sys/ZgBomCar/carSetForBom.do?idnrk="+idnrk+"&matkl="+matkl+"&carCuid="+carCuid+"&carNum="+carNum;
				document.forms[0].submit();
				var obj=new Object();
				obj.idnrk=idnrk;
				obj.carCuid=carCuid;
				window.returnValue=obj;
			
				window.close();
		//	}
		}
		function isInt (theStr) { 
			var flag = true; 
			if (isEmpty(theStr)) 
			{ flag=false; } 
			else 
			{ for (var i=0; i<theStr.length; i++) { 
					if (isDigit(theStr.substring(i,i+1)) == false) { 
						flag = false;
						 break; 
					} 
				} 
			} 
			return(flag); 
		} 
		function isEmpty (str) { 
		if ((str==null)||(str.length==0)) return true; 
		else return(false); 
		} 
		function isDigit(s) 
		{ 
		var patrn=/^[0-9]{1,20}$/; 
		if (!patrn.exec(s))
			 return false ;
		return true ;
		} 


	</script>
</head>
	<body>
		<%@ include file="/commons/messages.jsp"%>
		<form id="tt" action="" method="post"
			validator="true">
			<input type="hidden" value="${idnrk }" name="idnrk" id="idnrk"/>
			<input type="hidden" value="${matkl }" name="matkl" id="matkl"/>
			<div id="infoPanel">
				<table width="100%" cellpadding="0" cellspacing="1" style="border: 1px solid #A8CFEB;">
					<thead>
						<tr>
							<td class="formToolbar">

								<div class="button" style="text-align: left;">
									<a href="javascript:returnAndClose()"><span><img
												src="<%=iconPath%>/true.gif" />保存</span> </a>&nbsp
									<a href="javascript:backTree()"><span><img
												src="<%=iconPath%>/ico_007.gif" />关闭</span> </a>
								</div>
							</td>
						</tr>
					</thead>
				</table>
			</div>
			<div class="form_panel">
					<table class="form_table" cellpadding="0" cellspacing="1" style="margin-top: 3px;">
						<tbody attr="tbody_1">
							<tr>
								<td class="label">车型名称：</td>
								<td >
									<select id="carName" name="carName">
												<option value="">请选择...</option>
												<c:forEach items="${carList}" var="obj">
													<option value="${obj.cuid}">${obj.labelCn}</option>
												</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									<span style="color:red">*</span>装载数量：
								</td>
								<td>
									<input type="text" name="carNum" id="carNum" maxlength="5" size="25" class="number"/>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
		</form>
	</body>
</html>