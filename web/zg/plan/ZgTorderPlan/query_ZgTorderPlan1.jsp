<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String expandIcon = basePath+"/resources/images/frame/ico_expand.gif";
%>
<html>
<head>
	<base href="<%=basePath%>" />
	<title>领料计划查询</title>
	<%@ include file="/commons/meta.jsp" %>
	<%@ include file="/commons/jquery.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/tools.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/form.css" rel="stylesheet" />
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
	
	<script type="text/javascript" src="${ctx}/scripts/jquery_ex/jquery_ui_autocomplate_ex.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/interface/ZgTorderPlanbomDwrAction.js"></script>
	
	<style>
		body,html {overflow:hidden}
	</style>
	<script type="text/javascript">
		var curNum=0; 
		var curDate="<fmt:formatDate value='${curDate}' pattern="yyyy-MM-dd" />";
		var planType="${pageRequest.filters.planType}";
		$(function() {
			init();
		});
		function init() {
			doLayout();
			$(window).bind("resize",doLayout);
			$("form:first").submit();
		      
		}
		function doLayout() {
			var maxHeight = top.getContentHeight();
			document.getElementById("_queryPanel").style.height = maxHeight + 'px';
			var tableInfo1 = document.getElementById("tableInfo1").offsetHeight;
			var tableInfo2 = document.getElementById("tableInfo2").offsetHeight;
			var listFrameH = maxHeight - tableInfo1-tableInfo2-36;
			document.getElementById("listFrame0").style.height = listFrameH + 'px';
			document.getElementById("listFrame1").style.height = listFrameH + 'px';
			document.getElementById("listFrame2").style.height = listFrameH + 'px';
			document.getElementById("listFrame3").style.height = listFrameH + 'px';
			document.getElementById("listFrame4").style.height = listFrameH + 'px';
		}
	
		/* tabpages */
		function nTabs(tabObj, obj) {
		    var tabList = document.getElementById(tabObj).getElementsByTagName("li");
		    //alert(tabList);
		    for (i = 0; i < tabList.length; i++) {
		        if (tabList[i].id == obj.id) {
		        	curNum=i;
		        	curDate=document.getElementById("s_pxDate"+i).value;
		            document.getElementById(tabObj + "_title" + i).className = "current";
		            document.getElementById(tabObj + "_content" + i).style.display = "block";
		            var form=document.getElementById('form'+i);
		     	  	form.submit();
		        } else {
		            document.getElementById(tabObj + "_title" + i).className = "";
		            document.getElementById(tabObj + "_content" + i).style.display = "none";
		        }
		        
		  
		    }
		}
		
	</script>
</head>

<body>
<div id="_queryPanel">
<table id="tableInfo1" width="100%" cellpadding="0" cellspacing="1" class="formitem">
        <caption>
        <c:choose>
        <c:when test="${pageRequest.filters.planType=='ABC'}">预焊线今日领料</c:when>
         <c:when test="${pageRequest.filters.planType=='ABD'}">预装线今日领料</c:when>
          <c:when test="${pageRequest.filters.planType=='ABE'}">总装线今日领料</c:when>
        </c:choose></caption>
        <tr>
            <th>
                今日订单总计：
            </th>
            <td>
                ${totalCount }
            </td>
            <th>
                已完成订单数量：
            </th>
            <td>
               ${finishCount }
            </td>
        </tr>
    </table>
     <table id="tableInfo1" width="100%" cellpadding="0" cellspacing="1" class="formitem">
        <caption>
        	领料状态　暂停领料：<img src="<%=iconPath%>/ico_pause.gif" alt="暂停领料" />&nbsp;&nbsp;
        	计划中，不可调整：<img src="<%=iconPath%>/ico_plan.gif" alt="计划中，不可调整" />&nbsp;&nbsp;
        	等待领料：<img src="<%=iconPath%>/ico_wait.gif" alt="等待领料" />&nbsp;&nbsp;
        	领料中：<img src="<%=iconPath%>/ico_ing.gif" alt="" />&nbsp;&nbsp;
        	完成：<img src="<%=iconPath%>/ico_ok.gif" alt="完成" />&nbsp;&nbsp;
        	冻结：	<img src="<%=iconPath%>/ico_dj.gif" alt="冻结" />
        </caption>
    </table>
        <div class="tabpage" id="tab">
        <table id="tableInfo2" width="100%"><tr><td width="100%">
        
          <ul>
         <c:forEach items="${dateList}" varStatus="n" var="date">
        <form id="form${n.count-1 }" action="${ctx}/zg/plan/ZgTorderPlan/listOrder.do" method="post" target="listFrame${n.count-1 }">
           <input type="hidden" name="bmClassId" value="<%=ZgTorderPlan.BM_CLASS_ID%>"/>
			<input type="hidden" name="s_planType" value="${pageRequest.filters.planType}"/>
			<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
			<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
			<input type="hidden" name="s_pxDate" value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />"/>
			<input type="hidden" name="s_arbpl" value="${arbpl }"/>
			<input type="hidden" name="s_pxDate${n.count-1 }" id="s_pxDate${n.count-1 }"  value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />"/>

           
            <c:if test="${n.count==1}">   <li id="tab_title${n.count-1}" onclick="nTabs('tab',this);" class="current"><a href="javascript:">
                <span><fmt:formatDate value="${date}" pattern="MM月dd日" />(今日计划)</span></a><br /></li></c:if>
                <c:if test="${n.count!=1}">    
                <li id="tab_title${n.count-1}" onclick="nTabs('tab',this);"><a href="javascript:">
                <span><fmt:formatDate value="${date}" pattern="MM月dd日" /></span></a><br /></li>
            </c:if>
            </form>
            </c:forEach>
        </ul>
        </td></tr></table>
      
        <div class="main">
            <div id="tab_content0">
            	<iframe id="listFrame0" src="" name="listFrame0" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            </div>
            <div id="tab_content1" class="none">
               <iframe id="listFrame1" src="" name="listFrame1" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            </div>
            <div id="tab_content2" class="none">
                <iframe id="listFrame2" src="" name="listFrame2" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            
            </div>
            <div id="tab_content3" class="none">
                <iframe id="listFrame3" src="" name="listFrame3" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            </div>
            <div id="tab_content4" class="none">
                <iframe id="listFrame4" src="" name="listFrame4" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            </div>
            <div id="tab_content5" class="none">
                <iframe id="listFrame5" src="" name="listFrame5" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
            </div>
        </div>
    </div>
</div>
</body>
</html>