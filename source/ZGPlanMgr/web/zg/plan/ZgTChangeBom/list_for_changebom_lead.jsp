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
<% 
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
		<title><%=ZgTorderPlan.TABLE_ALIAS%> 维护</title>
		<%@ include file="/commons/meta.jsp"%>
		<%@ include file="/commons/jquery.jsp"%>
		<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
		<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
		<script type="text/javascript">
			$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
			
		}
	
		function look(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTBomManager/look.do?id='+cuid);
			}
		}
	</script>
	</head>
	<body>
		<form id="ec" action="<c:url value="/zg/plan/ZgTBomManager/listForChangeLead.do"/>" method="post" style="display: inline;">
			
			<div>
				<div>
					<%@ include file="query_include_ZgTorderPlan.jsp"%>
				</div>
				<div class="grid-panel" id="grid-panel">
					<div class="title">换料申请列表</div>
					<div class="toolbar">
					</div>
					<div class="grid-data-panel" id="grid-data-panel">
						<table id="ec_table" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<td class="tableHeader" width="3%">
										<input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" />
									</td>
							<td class="tableHeader"  >单据编码<%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
		                    <td class="tableHeader" >生产订单<%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
			                <td class="tableHeader" >物料等级<%=map.get("ec_image_CUID")==null?"":map.get("ec_image_CUID")%></td>
							<td class="tableHeader" >创建时间<%=map.get("ec_image_PLAN_DATE")==null?"":map.get("ec_image_PLAN_DATE")%></td>
							<td class="tableHeader" >创建人<%=map.get("ec_image_USER_NAME")==null?"":map.get("ec_image_USER_NAME")%></td>
							<td class="tableHeader"  >创建人部门<%=map.get("ec_image_DEPARTMENT_NAME")==null?"":map.get("ec_image_DEPARTMENT_NAME")%></td>
							<td class="tableHeader"  >状态<%=map.get("ec_image_STATE")==null?"":map.get("ec_image_STATE")%></td>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="obj" varStatus="n">
									<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
									
									
									
								
								
									<tr class="${trcss}" title="双击查看详情" ondblclick="look('${obj.CUID}')"  onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
									
									
						
							<td width="3%" >
							<input type="checkbox" name="items" value="id=${obj.CUID}&"/>
							</td>
										<td align="center">
										${obj.CUID}
										</td>
										<td align="center">
											${obj.AUFNR}
										</td>
										<td align="center">
											${obj.EXTEND1}
											
										</td>
										<td align="center">
										${obj.CREATEDATE}
										</td>
										<td align="center">
											${obj.CREATENAME}
										</td>
										<td align="center">
											${obj.ORGNAME}
										</td>
										
										<td align="center">
											
											<c:choose>
								           
								            <c:when test="${obj.STATE=='1'}">厂领导审核退回</c:when>
								            <c:when test="${obj.STATE=='2'}">待厂领导审核</c:when>
								           
							</c:choose>
												</td>
								
									</tr>
								</c:forEach> 
								
								<tr>
									<td colspan="10">
										<%@include file="/frame/default/ux/pagebar.jsp" %>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>