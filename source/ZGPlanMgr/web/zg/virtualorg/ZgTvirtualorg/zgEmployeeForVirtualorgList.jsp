<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*"%>
<%@page import="cn.org.rapid_framework.page.*"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/framework/tag" prefix="fw"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
List<SortInfo> list = ((PageRequest)request.getAttribute("pageRequest")).getSortInfos();
Map map = new HashMap();
for(SortInfo si:list){
	map.put("ec_s_"+si.getColumnName(),si.getSortOrder());
	String image="";
	if("asc".equalsIgnoreCase(si.getSortOrder())){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-bottom.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
		map.put("ec_image_"+si.getColumnName(),image);
	}else if("desc".equalsIgnoreCase(si.getSortOrder())){
		image="&#160;<img src=\""+request.getContextPath()+"/widgets/extremecomponents/images/col-move-top.gif\"  style=\"border:0\"  alt=\"Arrow\" />";
		map.put("ec_image_"+si.getColumnName(),image);
	}
}
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<base target="_self"/>
		<title>领料人列表</title>
		<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
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
			
		function returnAndClose(){
			var items=document.getElementsByName("items");
			var allId="";
			//var allName="";
			for(var v=0;v<items.length;v++){
				if(items[v].checked){//打钩的
					var oneId=items[v].value;
					allId+="'"+oneId+"',";	
					//allName+=name+",";		
				}
			}
			var obj=new Object();
			obj.allId=allId;
			window.returnValue=obj;
			window.close();
		}
		
	</script>
	<body scrolling="no">
	<%@ include file="/commons/messages.jsp" %>
		<form id="ec" action="${ctx}/zg/virtualorg/ZgTvirtualorg/employeeForVirtualorg.do"
			method="post">
			<div>
				 <%@ include file="query_include_VirtualorgEmployee.jsp" %> 
				 <input type="hidden" name="s_virtualorgId" value="${pageRequest.filters.virtualorgId }"/>
			</div>
			<div class="datalist">
			<h2>领料人列表</h2>
			<div class="toolbar">
			</div>
			<div id="grid-panel" class="grid-data-panel" >
				<table id="ec_table"  border="0"  cellspacing="1"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr>
							<td class="tableHeader" width="3%">
									<input type='checkbox'
										onclick="setAllCheckboxState('items',this.checked)" />
								</td>
							<td class="tableHeader">
								领料员ID
							</td>
							<td class="tableHeader">
								领料员姓名
							</td>
							<td class="tableHeader">
								性别
							</td>
							<td class="tableHeader">
								部门
							</td>
							<td class="tableHeader">
								邮箱
							</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
							<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
								<td>
									<input type="checkbox" name="items" value="${obj.userCuid}" />
								</td>
								<td align="center">
									${obj.userId}
								</td>
								<td align="center">
									${obj.userName}
								</td>
								<td align="center">
									<c:if test="${obj.sex eq '2'}">
										女
									</c:if>
									<c:if test="${obj.sex eq '1'}">
										男
									</c:if>
								</td>
								<td  align="center">
									${obj.orgName}
								</td>
								<td  align="center">
									${obj.email}
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="12" >
								<%@include file="/frame/default/ux/pagebar.jsp" %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		</form>
	</body>
</html>