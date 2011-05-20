<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>

	<head>
		<base href="<%=basePath%>" />
		<title>新增</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<script type="text/javascript"
			src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
		<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css"
			rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css"
			rel="stylesheet" />
		<script type="text/javascript">
		function submit(){
			document.forms[0].submit();
		}
		function initMsg(msg){
			if(msg!=''&&msg!=undefined){
				alert(msg);
			}
		}
		</script>
	</head>
	<body onload="initMsg('${ msg}')">
		<form action="${ctx}/zg/plan/ZgTcarplan/saveOverTime.do" method="post">
			<table class="form_table" cellpadding="0" cellspacing="0"
				align="left">
				<tbody>
					<tr>
						<td class="label" width="200px">
							<span>设置超时是否可领料：</span>
						</td>
						<td bgcolor="#F1F6FF" width="120px">
							<select id="overTimes" name="overTimes">
								<c:forEach items="${overTimeList}" var="overTimeValue">
									<option value="${overTimeValue.value}"
										<c:if test="${overTime==overTimeValue.value}"> selected</c:if>>
										${overTimeValue.name}
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="formToolbar" align="left">
							<div class="button" style="text-align: left;">
								<a href="javascript:submit()"><span ><img
											src="<%=iconPath%>/action_save.gif" />保存</span>
								</a>
							</div>
						</td>
					<tr>
				</tbody>
			</table>
		</form>
	</body>
</html>