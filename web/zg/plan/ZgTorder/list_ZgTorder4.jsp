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
<meta http-equiv="Page-Enter" content="RevealTrans (Duration=1, Transition=12)"> 
<meta http-equiv="Page-Exit" content="RevealTrans (Duration=1, Transition=12)"> 
<head>
	<base href="<%=basePath%>" />
	<title><%=ZgTorder.TABLE_ALIAS%> 维护</title>
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
		function edit(orderId,pflag) {
			if(parent.targetFrame) {
				parent.targetFrame('${ctx}/zg/plan/ZgTorderbom/orderBomPush.do?orderId='+orderId+'&pflag='+pflag);
			}
		}
		function view(orderId) {
			if(parent.targetFrame) {
				//parent.targetFrame('${ctx}/zg/plan/ZgTorderbom/findViewBomDE.do?orderId='+orderId);
				parent.targetFrame('${ctx}/zg/plan/ZgTorderbom/orderBomPanel.do?orderId='+orderId+'&isView=true');
			}
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
		function checkItem(id){
			document.getElementById(id).checked=true;
		}
		
		function checkPubNum(obj) {
			var max = obj.getAttribute("maxValue")*1;
			var oldValue=obj.getAttribute("oldValue")*1;
			var value = obj.value;
			if(isNumber(value)) {
				if(max < value*1) {
					alert("下线数量不能超过成品排序数量！");
					obj.value = oldValue;
					return;
				}
			}else {
				alert("下线数量数量必须为数字！");
				obj.value = oldValue;
				return;
			}
			
			obj.setAttribute("oldValue",value);
		}
	</script>
</head>
<body>
<form id="ec" action="${ctx}/zg/plan/ZgTorder/list2.do" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_ZgTorder.jsp" %>
		</div>
		<div id="grid-panel" class="grid-panel">
			<div class="title">成品下线</div>
			<div class="toolbar">
			</div>
			<div id="grid-data-panel" class="grid-data-panel">
				<table cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_PCDAT')" title="排序 排产日期" >排产日期<%=map.get("ec_image_t0_PCDAT")==null?"":map.get("ec_image_t0_PCDAT")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_AUFNR')" title="排序 生产订单编号" >生产订单编号<%=map.get("ec_image_t0_AUFNR")==null?"":map.get("ec_image_t0_AUFNR")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_ARBPL1')" title="排序 生产线" >原生产线<%=map.get("ec_image_t0_ARBPL")==null?"":map.get("ec_image_t0_ARBPL")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_ARBPL')" title="排序 生产线" >生产线<%=map.get("ec_image_t0_ARBPL")==null?"":map.get("ec_image_t0_ARBPL")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MAKTX2')" title="排序 客户机型" >客户机型<%=map.get("ec_image_t0_MAKTX2")==null?"":map.get("ec_image_t0_MAKTX2")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_KDAUF')" title="排序 销售订单号" >销售订单号<%=map.get("ec_image_t0_KDAUF")==null?"":map.get("ec_image_t0_KDAUF")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_PLANT')" title="排序 生产厂" >生产厂<%=map.get("ec_image_t0_PLANT")==null?"":map.get("ec_image_t0_PLANT")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MAKTX1')" title="排序 公司机型" >公司机型<%=map.get("ec_image_t0_MAKTX1")==null?"":map.get("ec_image_t0_MAKTX1")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_MATNR')" title="排序 物料号" >成品编号<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_PSMNG')" title="排序 订单项数量" >成品需求数量<%=map.get("ec_image_t0_PSMNG")==null?"":map.get("ec_image_t0_PSMNG")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_PMENGE')" title="排序 排序数量" >成品排序数量<%=map.get("ec_image_t0_PMENGE")==null?"":map.get("ec_image_t0_PMENGE")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_ORDER_STATE')" title="排序 单据状态" >已下线数量<%=map.get("ec_image_t0_ORDER_STATE")==null?"":map.get("ec_image_t0_ORDER_STATE")%></td>
							<td class="tableHeader" onclick="queryColumn(this,'t0_ORDER_STATE')" title="排序 单据状态" >本次下线数量<%=map.get("ec_image_t0_ORDER_STATE")==null?"":map.get("ec_image_t0_ORDER_STATE")%></td>
						
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
					<!-- <c:choose>
							<c:when test="${obj.orderState eq '8'}">
						<tr class="${trcss}" title="双击查看" ondblclick="view('${obj.cuid}')">
							</c:when>
							<c:when test="${obj.orderState eq '-1'}">
						<tr class="${trcss}" title="双击查看" ondblclick="view('${obj.cuid}')">
							</c:when>
							<c:otherwise>
						<tr class="${trcss}" title="双击修改" ondblclick="edit('${obj.cuid}','${obj.pflag}')">
							</c:otherwise>
						</c:choose>   -->	
						<tr class="${trcss}" >
							<td width="3%" >
								<input type="checkbox" name="items" value="${obj.cuid}/${obj.aufnr}" id="${obj.cuid}"/>
							</td>
							<td>${obj.pcdatString}</td>
							<td>${obj.aufnr}</td>
							<td>${obj.arbpl1}</td>
							<td>${obj.arbpl}</td>
							<td align="left">${obj.maktx2}</td>
							<td>${obj.kdauf}</td>
							<td>${obj.plant}</td>
							<td align="left">${obj.maktx1}</td>
							<td>${obj.matnr}</td>
							<td align="center">${obj.psmng}</td>
							<td align="center">${obj.pmenge}</td>
							<td>${obj.publish_num}</td>	
							<td align="center" width="6%">
								<input type="text" maxValue="${obj.pmenge-obj.publish_num}" oldValue="0" maxlength="13" onchange="checkPubNum(this)" name="thisPubNum" class="number" maxlength="5" size="8" onclick="checkItem('${obj.cuid}')"/>
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="14" >
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