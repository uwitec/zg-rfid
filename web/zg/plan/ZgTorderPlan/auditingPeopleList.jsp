<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
			String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<base target="_self"/>
		<title>订单生产线</title>
		<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
		<script type="text/javascript">
		$(function() {
			initAutoComplete("${ctx}/autoComplate/findRelationData.do");
			$("button ,input:button,input:submit").button();
			try{
				if(initLayout) {
					$(window).bind("load",initLayoutOwner);
					$(window).bind("resize",initLayoutOwner);
				}
			}catch(e){}
		});
		function initLayoutOwner() {
			if(parent) {
				var iframes = parent.document.getElementsByTagName("iframe");
				for(var i = 0; i < iframes.length; i++) {
					if(iframes[i].contentWindow == window) {
						if(iframes[i].getAttribute("autolayout") == "true") {
							try{
								var H = top.centerFrame.offsetHeight;
								var h = parent.document.getElementById("orderDetailFrame").offsetHeight;
								iframes[i].style.height = ( H - h - 50) +"px";
							}catch(e){}
						}
					}
				}
			}
		}
			
		function returnAndClose(userId){
			var obj=new Object();
			obj.userId=userId;//拿回userId
			window.returnValue=obj;
			window.close();
		}
		
	</script>
	<body   scrolling="no">
		<form action="${ctx}/zg/storage/ZgTstorage/findOrderArbplList.do"
			method="post">
			<div class="datalist">
			<h2>审核人列表</h2>
			<div class="toolbar">
			</div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
							<td class="tableHeader">
								姓名
							</td>
							<td class="tableHeader">
								性别
							</td>
							<td class="tableHeader">
								部门
							</td>
							<td class="tableHeader">
								电话
							</td>
							<td class="tableHeader">
								邮箱
							</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${fwEmployeeList}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
							<tr class="${trcss}"							
							 title="双击选择" ondblclick="returnAndClose('${obj.cuid}')" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
								
								<td align="center">
									${obj.auditingName}
								</td>
								<td align="center">
								<c:if test="${obj.sex eq '1'}">
									男
								</c:if>
								<c:if test="${obj.sex eq '2'}">
									女
								</c:if>
								</td>
								<td  align="center">
									${obj.departmentName}
								</td>
								<td  align="center">
									${obj.mobile}
								</td>
								<td  align="center">
									${obj.email}
								</td>
							</tr>
						</c:forEach>
						<tr style="padding: 0px;" >
							<td colspan="12" >
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		</form>
	</body>
</html>