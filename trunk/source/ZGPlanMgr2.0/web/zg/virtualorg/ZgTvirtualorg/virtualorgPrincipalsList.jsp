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
		<title>订单生产线</title>
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
			var allName="";
			for(var v=0;v<items.length;v++){
				if(items[v].checked){//打钩的
					var idAndName=items[v].value;
					//把name跟id分开
					var id=idAndName.substring(0,idAndName.indexOf(','));
					var name=idAndName.substring(idAndName.indexOf(',')+1);
					allId+=""+id+",";	
					allName+=name+",";		
				}
			}
			//去除最后的那个,号
			allId=allId.substring(0,allId.lastIndexOf(','));
			allName=allName.substring(0,allName.lastIndexOf(','));
			alert(allId);
			alert(allName);
			//这里要修改，要返回值的
			var obj=new Object();
			obj.allId=allId;
			obj.allName=allName;
			window.returnValue=obj;
			window.close();
		}
		
	</script>
	<body scrolling="no">
	<%@ include file="/commons/messages.jsp" %>
		<form id="ec" action="${ctx}/zg/virtualorg/ZgTvirtualorg/findByRequestForVirtualorg.do"
			method="post">
			<div>
				 <%@ include file="query_include_VirtualorgPrincipals.jsp" %>
				 <input type="hidden" name="s_virtualorgCuid" id="s_virtualorgCuid" value="${pageRequest.filters.virtualorgCuid }"/>
				 <input type="hidden" name="s_virtualorgName" id="s_virtualorgName" value="${pageRequest.filters.virtualorgName}"/>
				 <input type="hidden" name="s_departmentId" id="s_departmentId" value="${pageRequest.filters.departmentId}"/>
				 <input type="hidden" name="s_virtualorgType" id="s_virtualorgType" value="${pageRequest.filters.virtualorgType}"/>
			</div>
			<div class="datalist">
			<h2>负责人列表</h2>
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
								领料组ID
							</td>
							<td class="tableHeader">
								领料组名称
							</td>
							<td class="tableHeader">
								所属机构
							</td>
							<td class="tableHeader">
								领料组类型
							</td>
							<td class="tableHeader">
								描述
							</td>
						</tr>
					</thead>
					<tbody>
					<c:set var="empleeAllId" value="${pageRequest.filters.employeesId}," />
					<c:forEach items="${page.result}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
							<tr class="${trcss}" onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
								<td>
									<input type="checkbox" name="items" value="${obj.cuid},${obj.labelCn}" />
								</td>
								<td align="center">
									${obj.cuid}
								</td>
								<td align="center">
									${obj.labelCn}
								</td>
								<td align="center">
									${obj.orgName}
								</td>
								<td  align="center">
									<c:if test="${obj.type eq 'ABC'}">预焊</c:if>
									<c:if test="${obj.type eq 'ABD'}">预装</c:if>
									<c:if test="${obj.type eq 'ABE'}">总装</c:if>
									<c:if test="${obj.type eq ''}"></c:if>
								</td>
								<td  align="center">
									${obj.note}
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