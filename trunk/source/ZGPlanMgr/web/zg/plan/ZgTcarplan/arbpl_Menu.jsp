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
	  <title>导航页面</title>
	<link type="text/css" href="${ctx}/resources/css/${_theme}/images/frame/style.css" rel="stylesheet" />
    <script>
   		 var num=${arbplNum};

        function setheight() {
          //  document.getElementById("div1").style.height = document.body.clientHeight - num*15-150;
        }

        tempdiv = "div1";
        function OnShow2(id,arbpl,onload) {
            if (id.style.display != "none") {
				 
            }
            else {
               // id.style.display = "block";
              //  id.style.height = document.body.clientHeight - num*15-150;
              //  document.getElementById(tempdiv).style.display = "none";
             	document.getElementById("h"+tempdiv).style.backgroundImage = "url(${ctx}/resources/css/${_theme}/images/frame/submenu_h3.gif)"; 
                tempdiv = id.id;
                top.bodyFrame.document.getElementById("queryFrame").src = '${ctx}/zg/plan/ZgTcarplan/query1.do?type=${type}&arbpl='+arbpl+"&orderPlanType=${orderPlanType}&onload="+onload;
            	//alert("h"+id.id);
            	document.getElementById("h"+id.id).style.backgroundImage = "url(test.gif)"; 
            	
            	
            }
        }
		
    
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
    <div class="submenu">
        <h2> 
            操作菜单 
        </h2>
        
        <c:forEach items="${arbpList}" var="arbpl" varStatus="n">
        
        	<h3 onclick="OnShow2(div${n.count },'${arbpl.cuid }','')" id="hdiv${n.count }" align="center">
            ${arbpl.cuid } 
        </h3>
        <c:if test="${n.count==1}">
          <ul id="div${n.count }" class="treeview" style="display: none"> </ul>
               	 <script>
               	 if('${defaulArbpl}'==''){
               	 	OnShow2(document.getElementById('div${n.count }'),'${arbpl.cuid }','onload');
               	 }else{    
               	 	if('${arbpl.cuid }'=='${defaulArbpl}'){
               	 			OnShow2(document.getElementById('div${n.count }'),'${arbpl.cuid }','onload');
               	 	}
               	 }         	 	
         	 	</script>
        </c:if>
          <c:if test="${n.count>1}">
          <ul id="div${n.count }" class="treeview" style="display:none "> </ul>
          <script>
          	if('${arbpl.cuid }'=='${defaulArbpl}'){
               	 OnShow2(document.getElementById('div${n.count }'),'${arbpl.cuid }','onload');
            }
          </script>
          
        </c:if>
      
       
        </c:forEach>
        
     
        
    </div>
</body>
</html>