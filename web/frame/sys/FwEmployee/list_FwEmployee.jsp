<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
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
	<title><%=FwEmployee.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
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
				
				var maxWidth = top.getContentWidth()-206;
				document.getElementById("grid-data-panel").style.width=maxWidth+'px';
				document.getElementById("grid-panel").style.width=maxWidth+'px';
			}
		
		function edit(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwEmployee/edit.do?id='+cuid+'&code='+parent.parent.code);
			}
		}
		
		function view(cuid){
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/frame/sys/FwEmployee/show.do?id='+cuid);
			}
		}
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="//frame/sys/FwEmployee/list.do"/>" method="post" style="display: inline;">
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
			<%@ include file="query_include_FwEmployee.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">用户列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t1_USER_ID')"  title="排序 userId" >用户ID<%=map.get("ec_image_t1_USER_ID")==null?"":map.get("ec_image_t1_USER_ID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'rfidcode')"  title="排序 rfidcode" >RFID编号<%=map.get("ec_image_rfidcode")==null?"":map.get("ec_image_t1_USER_ID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t1_LABEL_CN')"  title="排序 labelCn" ><%=FwEmployee.ALIAS_LABEL_CN %><%=map.get("ec_image_rfidcode")==null?"":map.get("ec_image_rfidcode")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t1_PASSWORD')"  title="排序 password" ><%=FwEmployee.ALIAS_PASSWORD %><%=map.get("ec_image_t1_PASSWORD")==null?"":map.get("ec_image_t1_PASSWORD")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t1_EFFECT_TIME')"  title="排序 effectTime" ><%=FwEmployee.ALIAS_EFFECT_TIME %><%=map.get("ec_image_t1_EFFECT_TIME")==null?"":map.get("ec_image_t1_EFFECT_TIME")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ORG_ID')"  title="排序 orgId" ><%=FwEmployeeOrganazation.ALIAS_ORG_Name %><%=map.get("ec_image_t0_ORG_ID")==null?"":map.get("ec_image_t0_ORG_ID")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_SEX')"  title="排序 sex" ><%=FwEmployee.ALIAS_SEX %><%=map.get("ec_image_t0_SEX")==null?"":map.get("ec_image_t0_SEX")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MOBILE')"  title="排序 mobile" ><%=FwEmployee.ALIAS_MOBILE %><%=map.get("ec_image_t0_MOBILE")==null?"":map.get("ec_image_t0_MOBILE")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_TELEPHONE')"  title="排序 telephone" ><%=FwEmployee.ALIAS_TELEPHONE %><%=map.get("ec_image_t0_TELEPHONE")==null?"":map.get("ec_image_t0_TELEPHONE")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ADDRESS')"  title="排序 address" ><%=FwEmployee.ALIAS_ADDRESS %><%=map.get("ec_image_t0_ADDRESS")==null?"":map.get("ec_image_t0_ADDRESS")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_EMAIL')"  title="排序 email" ><%=FwEmployee.ALIAS_EMAIL %><%=map.get("ec_image_t0_EMAIL")==null?"":map.get("ec_image_t0_EMAIL")%></td>
							<td class="tableHeader" >操作</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid }')">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							
							<td>${obj.userId}</td>
							<td>${obj.rfidCode}</td>
							<td>${obj.labelCn}</td>
							<td>****</td>
							<td>${obj.effectTimeString}</td>
							<td>${obj.orgId_related.value }</a></td>
							<td>${obj.sex_enum.value}</td>	
							<td>${obj.mobile}</td>
							<td>${obj.telephone}</td>
							<td>${obj.address}</td>
							<td>${obj.email}</td>
							<td>
								<a attr="viewLink" href="javascript:edit('${obj.cuid }')">修改</a>&nbsp;&nbsp;&nbsp;
								<a attr="viewLink" href="javascript:view('${obj.cuid }')">查看</a>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="13" >
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
