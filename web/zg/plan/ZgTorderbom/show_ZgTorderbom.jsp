<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorderbom.TABLE_ALIAS%>信息</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("button ,input:button,input:submit").button();
			$("#<%=ZgTorderbom.TABLE_ALIAS%>ShowDiv").accordion({
				collapsible: true
			});
			if(initLayout) {
				$(window).bind("load",initLayout);
				
			}
		});
	</script>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<form action="${ctx}/zg/plan/ZgTorderbom/list.do" method="get" theme="simple">
		<s:hidden name="cuid" id="cuid" value="%{model.cuid}"/>
		<div id="<%=ZgTorderbom.TABLE_ALIAS%>ShowDiv">
			<h3><a href="#"><%=ZgTorderbom.TABLE_ALIAS%>信息</a></h3>
			<div>
				<table class="formTable">
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_IDNRK%></td>	
						<td><s:property value="%{model.idnrk}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_ORDER_ID%></td>	
						<td><s:property value="%{model.orderId}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_AUFNR%></td>	
						<td><s:property value="%{model.aufnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_ARBPL%></td>	
						<td><s:property value="%{model.arbpl}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MATNR%></td>	
						<td><s:property value="%{model.matnr}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MAKTX1%></td>	
						<td><s:property value="%{model.maktx1}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MAKTX2%></td>	
						<td><s:property value="%{model.maktx2}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MSEHL1%></td>	
						<td><s:property value="%{model.msehl1}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MSEHL2%></td>	
						<td><s:property value="%{model.msehl2}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_ZDTYL%></td>	
						<td><s:property value="%{model.zdtyl}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MENGE%></td>	
						<td><s:property value="%{model.menge}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_MATKL%></td>	
						<td><s:property value="%{model.matkl}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_SORTF%></td>	
						<td><s:property value="%{model.sortf}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_LGORT%></td>	
						<td><s:property value="%{model.lgort}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_ZBZ%></td>	
						<td><s:property value="%{model.zbz}" /></td>
					</tr>
					<tr>	
						<td class="tdLabel"><%=ZgTorderbom.ALIAS_ZRZQD%></td>	
						<td><s:property value="%{model.zrzqd}" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="后退" onclick="history.back();"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>