<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.frame.sys.base.model.*" %>
<%@page import="cn.org.rapid_framework.page.*" %>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/framework/tag" prefix="fw" %>
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
	<title><%=FwEmployee.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx }/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx }/resources/css/${_theme}/grid.css" rel="stylesheet" />
	<script src="${ctx}/dwr/interface/ZgMaterielDwrAction.js" type=""></script>
	<script type="text/javascript" src="${ctx}/scripts/gridEditor.js"></script>
	<script type="text/javascript">
		$(function() {
			init();
			initGridEditor();
		});
		
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
		}
		
		function doLayout() {
			var maxHeight = parent.document.getElementById("listFrame").style.height;
			var maxWidth = parent.document.getElementById("listFrame").style.width;
			maxHeight = maxHeight.replace("px","")*1;
			maxWidth=maxWidth.replace("px","")*1;
			var height = maxHeight-15;
			maxWidth=maxWidth-202;
			document.getElementById("grid-data-panel").style.height=height+'px';
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		
		function edit(materielId,numCode){
			var sFeatures="dialogHeight: 150px;dialogWidth:300px";
			var firstShowQueryUrl="${ctx}/zg/storeAndCar/materiel_level_set.jsp?materielId="+materielId;
			var returnValue = window.showModalDialog(firstShowQueryUrl,'',sFeatures);
			if(returnValue!=undefined){
				//调用dwr更新后台
				ZgMaterielDwrAction.updateMaterielLevel(materielId,returnValue,function(data){
					if(data){
						alert("设置成功");
						document.getElementById(numCode).innerText=returnValue;	
					}else{
						alert("系统繁忙，请稍后再试");
					}
				});
				
			}
		}
		
		
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<form id="ec" action="<c:url value="//frame/sys/ZgBomCar/listForStoreCar.do"/>" method="post" style="display: inline;">
<input type="hidden" name="s_orgId" id="s_orgId" value="${orgId }"/>
<input type="hidden" name="s_parentOrgId" id="s_parentOrgId" value="${parentOrgId }"/>
<input type="hidden" name="s_idnrk" id="s_idnrk" value="${pageRequest.filters.idnrk }"/>
<input type="hidden" name="s_carId" id="s_carId" value="${carId }"/>
<input type="hidden" name="s_carName" id="s_carName" value="${carName }"/>
<input type="hidden" name="s_bomCarState" id="s_bomCarState" value="${bomCarState }"/>

	<div>
		<div>
			<%@ include file="query_include_FwEmployee.jsp" %>
		</div>
		<div class="grid-panel" id="grid-panel">
			<div class="title">物料组列表</div>
			<div class="grid-data-panel" id="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  >物料组编号<%=map.get("ec_image_idnrk")==null?"":map.get("ec_image_idnrk")%></td>
							<td class="tableHeader"   >物料组名称<%=map.get("ec_image_msehl")==null?"":map.get("ec_image_msehl")%></td>
							<td class="tableHeader" >物料等级<%=map.get("ec_image_code")==null?"":map.get("ec_image_code")%></td>
							<td class="tableHeader"  >仓库编号<%=map.get("ec_image_materiel_name")==null?"":map.get("ec_image_materiel_name")%></td>
							<td class="tableHeader"   >仓库名称<%=map.get("ec_image_maktx")==null?"":map.get("ec_image_maktx")%></td>
							
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" title="双击修改物料组类型" ondblclick="edit('${obj.ID}','${n.count-1}MARERIEL_LEVEL')" >
							
							<td>${obj.ID}</td>
							<td>${obj.MATERIEL_NAME}</td>
							<td>
								<label id="${n.count-1}MARERIEL_LEVEL">${obj.MATERIEL_LEVEL}</label>
							</td>
							<td  align="left">${obj.ORGID}</td>
							<td align="left">${obj.ORGNAME}</td>
							
							
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
	</div>
</form>
</body>
</html>
