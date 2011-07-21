<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.virtualorg.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTvirtualorg.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<script type="text/javascript">
			$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrameForEmployee").style.height;
			maxHeight = maxHeight.replace("px","")*1;
			var height = maxHeight - 17;
			document.getElementById("grid-data-panel").style.height=height+'px';
			
			var maxWidth = top.getContentWidth()-220;
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		//	alert(maxWidth);			
		}
		
		function hiddenParentPanel(){
			parent.document.getElementById("mainDiv").style.display="none";
		}
		function show(url){
		//	parent.document.getElementById("_operatePanel").contentWindow.location.href = url;
			window.location.href=url;
			var queryform=parent.document.getElementById('userForm');
			queryform.style.display="none";
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="${ctx}/zg/virtualorg/ZgTvirtualorg/list.do?whetherParent=${whetherParent }&treeCuid=${treeCuid }" method="post" style="display: inline;">
<input type="hidden" name="s_sex" id="s_sex" value="${pageRequest.filters.sex }"/>
<input type="hidden" name="s_userName" id="s_userName" value="${pageRequest.filters.userName }"/>
<input type="hidden" name="s_orgId" id="s_orgId" value="${pageRequest.filters.orgId }"/>
<input type="hidden" name="s_userId" id="s_userId" value="${pageRequest.filters.userId }"/>
<input type="hidden" name="s_labelCn" id="s_labelCn" value="${pageRequest.filters.labelCn }"/>
<input type="hidden" name="s_orgName" id="s_orgName" value="${pageRequest.filters.orgName }"/>
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
	<div>
		<div>
			<%@ include file="query_include_ZgTvirtualorg.jsp" %>
		</div>
			<div class="grid-panel" id="grid-panel">
				<div class="title">领料人列表</div>
				<div class="grid-data-panel" id="grid-data-panel">
					<table cellspacing="0"  cellpadding="0">
						<thead>
							<tr>
								<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
								<td class="tableHeader"  onclick="queryColumn(this,'fo_userId')"  title="排序 userId" ><%=ZgTvirtualorg.ALIAS_USERID %><%=map.get("ec_image_fo_userId")==null?"":map.get("ec_image_fo_userId")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'fo_userName')"  title="排序 userName" ><%=ZgTvirtualorg.ALIAS_USERNAME %><%=map.get("ec_image_fo_userName")==null?"":map.get("ec_image_fo_userName")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'fe_sex')"  title="排序 sex" ><%=ZgTvirtualorg.ALIAS_SEX %><%=map.get("ec_image_fe_sex")==null?"":map.get("ec_image_fe_sex")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'fe_mobile')"  title="排序 mobile" ><%=ZgTvirtualorg.ALIAS_MOBILE %><%=map.get("ec_image_fe_mobile")==null?"":map.get("ec_image_fe_mobile")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'fe_email')"  title="排序 email" ><%=ZgTvirtualorg.ALIAS_EMAIL %><%=map.get("ec_image_fe_email")==null?"":map.get("ec_image_fe_email")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'ztv_label_cn')"  title="排序 labelCn" ><%=ZgTvirtualorg.ALIAS_LABEL_CN %><%=map.get("ec_image_ztv_label_cn")==null?"":map.get("ec_image_ztv_label_cn")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'ztv_org_id')"  title="排序 orgId" ><%=ZgTvirtualorg.ALIAS_ORG_ID %><%=map.get("ec_image_ztv_org_id")==null?"":map.get("ec_image_ztv_org_id")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'ztv_note')"  title="排序 note" ><%=ZgTvirtualorg.ALIAS_NOTE %><%=map.get("ec_image_ztv_note")==null?"":map.get("ec_image_ztv_note")%></td>
								<td class="tableHeader"  onclick="queryColumn(this,'ztv_type')"  title="排序 type" ><%=ZgTvirtualorg.ALIAS_TYPE %><%=map.get("ec_image_ztv_type")==null?"":map.get("ec_image_ztv_type")%></td>
								<td class="tableHeader" >操作</td>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${page.result}" var="obj" varStatus="n">
							<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
							<tr class="${trcss}">
								<td width="3%" >
									<input type="checkbox" name="items" value="id=${obj.zgTvirtualorgEmployeeCuid}&"/>
								</td>
								<td>${obj.userId}</td>
								<td>${obj.userName}</td>
								<td>${obj.sex}</td>
								<td>${obj.mobile}</td>
								<td>${obj.email}</td>
								<td>${obj.labelCn}</td>
								<td>${obj.orgName}</td>
								<td>${obj.note}</td>
								<td>${obj.type}</td>
								<td>
									<a attr="viewLink" href="javascript:show('${ctx}/zg/virtualorg/ZgTvirtualorg/show.do?id=${obj.zgTvirtualorgEmployeeCuid}')">查看</a>&nbsp;&nbsp;&nbsp;  
								<!--	<a attr="viewLink" href="<c:url value="/zg/virtualorg/ZgTvirtualorg/show.do?id=${obj.zgTvirtualorgEmployeeCuid}&" />">查看</a>&nbsp;&nbsp;&nbsp;  -->
								</td>
							</tr>
						</c:forEach>
							<tr>
								<td colspan="13" >&nbsp;<%@include file="/frame/default/ux/pagebar.jsp" %>
								</td>
							</tr>
					</tbody>
				</table>
			</div>
	</div>
</form>
</body>
</html>
