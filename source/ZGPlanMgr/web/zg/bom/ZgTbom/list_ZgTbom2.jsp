<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
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
	<title><%=ZgTcarplan.TABLE_ALIAS%> 维护</title>
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
			
			var maxWidth = top.getContentWidth();
			document.getElementById("grid-data-panel").style.width=maxWidth+'px';
			document.getElementById("grid-panel").style.width=maxWidth+'px';
		}
		
		function edit(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE2.do?carPlanId='+cuid);
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel4.do?carPlanId='+cuid);
			}
		}
		
		function edit2(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE3.do?carPlanId='+cuid);
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel3.do?carPlanId='+cuid);
			}
		}
		
		function view1(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE2.do?carPlanId='+cuid+'&view=true');
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel2.do?carPlanId='+cuid+'&isView=true');
			}
		}
		
		function view2(cuid){
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/findBomDE3.do?carPlanId='+cuid+'&view=true');
				parent.targetFrame('${ctx}/zg/plan/ZgTcarbom/bomPanel3.do?carPlanId='+cuid+'&isView=true');
			}
		}
		function checkNum(obj,allNum,id) {
			var max = obj.getAttribute("maxValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("出库量必须小于库存量！");
					obj.value = "";
					return;
				}
				document.getElementById(id).checked=true;
			}else {
				alert("出库量必须为正整数！");
				obj.value ="";
			}
		}
	
	</script>
</head>
<body>
<form id="ec" action="<c:url value="/zg/bom/ZgTbom/list2.do"/>" method="post" style="display: inline;">

<input type="hidden"  name="ec_i"  value="ec" />
<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
<input type="hidden"  name="s_idnrk"  value="${pageRequest.filters.idnrk}" />
<input type="hidden"  name="s_maktx"  value="${pageRequest.filters.maktx}" />
	<div>
		<div>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">
			备料库存</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table id="ec_table" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader">物料编码<%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
							<td class="tableHeader">物料描述<%=map.get("ec_image_CARUSER")==null?"":map.get("ec_image_CARUSER")%></td>
							<td class="tableHeader">大小量岗<%=map.get("ec_image_STORAGE_NAME")==null?"":map.get("ec_image_STORAGE_NAME")%></td>
							<td class="tableHeader">库存量<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
							<td class="tableHeader">出库量<%=map.get("ec_image_t0_LABEL_CN")==null?"":map.get("ec_image_t0_LABEL_CN")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}" />
						<tr class="${trcss}"onmouseover="this.style.backgroundColor = '#EBF1FF'"  onmouseout="this.style.backgroundColor = '#FFFFFF'">
							<td width="3%" >
								<input type="checkbox" name="items" value="${obj.orderPlanbomId}/${obj.idnrk}" id="${obj.orderPlanbomId}"/>
							</td>
							<td align="center">
								${obj.idnrk}
							</td>
							<td align="center">
								${obj.maktx}
							</td>
							<td  align="center">
								${obj.msehl}
							</td>
							<td  align="center">
								${obj.remainNum}
							</td>
							<td align="center">
								<input type="text" name="thisOutNum" class="number" maxlength="8" size="10" 
								 maxValue="${obj.remainNum}" onchange="checkNum(this,'${obj.remainNum}','${obj.orderPlanbomId}')"/>
							</td>
						</tr>
					</c:forEach>
					<tr style="padding: 0px;" >
						<td colspan="6" >
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
