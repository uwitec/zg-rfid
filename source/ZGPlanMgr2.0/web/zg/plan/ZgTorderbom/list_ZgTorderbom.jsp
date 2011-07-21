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
	<title><%=ZgTorderbom.TABLE_ALIAS%> 维护</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link href="${ctx}/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/frame.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			try{
				$("#_toolbar").prepend("");
				if(initLayout) {
					$(window).bind("load",initLayout);
					
				}
			}catch(e){}
		});
	</script>
</head>
<body>
<%@ include file="/commons/messages.jsp" %>
<iframe src="<c:url value="/zg/plan/ZgTorder/show.do?id=${orderId}"/>" autolayout="true" width="100%" frameborder="0"></iframe>
<form id="ec" action="<c:url value="/zg/plan/ZgTorderbom/list.do"/>" method="post" style="display: inline;">
	<div>
		<div>
			<%@ include file="query_include_ZgTorderbom.jsp" %>
		</div>
		<div>
			<div class="eXtremeTable" >
				<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
					<thead>
						<tr style="padding: 0px;" >
							<td colspan="12" >
								<button class="commonButton backButton" onclick="history.back()">返回</button>
								<button class="commonButton saveButton" onclick="save()">保存</button>
								<button class="commonButton acceptButton" onclick="submit()">提交</button>
								<button class="commonButton expButton" onclick="export()">导出</button>
							</td>
						</tr>
						<tr>
							<td class="tableHeader"  width="3%" ><input type='checkbox' onclick="setAllCheckboxState('items',this.checked)" ></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MATNR')"  title="排序 物料号" >物料号<%=map.get("ec_image_t0_MATNR")==null?"":map.get("ec_image_t0_MATNR")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MATNR1')"  title="排序 物料号1" >物料号1<%=map.get("ec_image_t0_MATNR1")==null?"":map.get("ec_image_t0_MATNR1")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_IDNRK')"  title="排序 bom组件" >BOM组件<%=map.get("ec_image_t0_IDNRK")==null?"":map.get("ec_image_t0_IDNRK")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MAKTX1')"  title="排序 物料描述" >物料描述<%=map.get("ec_image_t0_MAKTX1")==null?"":map.get("ec_image_t0_MAKTX1")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MSEHL1')"  title="排序 度量单位" >度量单位<%=map.get("ec_image_t0_MSEHL1")==null?"":map.get("ec_image_t0_MSEHL1")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ZDTYL')"  title="排序 组件单台用量" >组件单台用量<%=map.get("ec_image_t0_ZDTYL")==null?"":map.get("ec_image_t0_ZDTYL")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_MENGE')"  title="排序 组件需求用量" >组件需求用量<%=map.get("ec_image_t0_MENGE")==null?"":map.get("ec_image_t0_MENGE")%></td>
							<td class="tableHeader" style="color:red" onclick="queryColumn(this,'t0_SORTF')"  title="排序 排序字符串" >排序字符串<%=map.get("ec_image_t0_SORTF")==null?"":map.get("ec_image_t0_SORTF")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_SORTF_H')"  title="排序 原排序字符串" >原排序字符串<%=map.get("ec_image_t0_SORTF_H")==null?"":map.get("ec_image_t0_SORTF_H")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_LGORT')"  title="排序 库存地点" >库存地点<%=map.get("ec_image_t0_LGORT")==null?"":map.get("ec_image_t0_LGORT")%></td>
							<td class="tableHeader"  onclick="queryColumn(this,'t0_ZBZ')"  title="排序 物料备注" >物料备注<%=map.get("ec_image_t0_ZBZ")==null?"":map.get("ec_image_t0_ZBZ")%></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.result}" var="obj" varStatus="n">
						<c:set var="trcss" value="${n.count%2==0?'odd':'even'}"/>
						<tr class="${trcss}" onmouseover="this.className='highlight'"  onmouseout="this.className='${trcss}'">
							<td width="3%" >
								<input type="checkbox" name="items" value="id=${obj.cuid}&"/>
							</td>
							<td>${obj.matnr}</td>
							<td>${obj.matnr1}</td>
							<td>${obj.idnrk}</td>
							<td>${obj.maktx1}</td>
							<td>${obj.msehl1}</td>
							<td>${obj.zdtyl}</td>
							<td>${obj.menge}</td>
							<td>
								<select name="model.sortf" style="width:60px;">
									<c:forEach items="${sortFEnume}" var="map">
										<c:choose>
											<c:when test="${map['VALUE'] eq obj.sortf}">
												<option value="${map['VALUE']}" selected="selected">${map['NAME']}</option>
											</c:when>
											<c:otherwise>
												<option value="${map['VALUE']}">${map['NAME']}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td>${obj.sortfH}</td>
							<td>${obj.lgort}</td>
							<td>${obj.zbz}</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="12" style="line-height: 13px">&nbsp;
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>