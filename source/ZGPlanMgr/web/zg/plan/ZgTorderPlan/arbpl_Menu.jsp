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
        function OnShow2(id,arbpl) {
            if (id.style.display != "none") {
				 
            }
            else {
             	document.getElementById("h"+tempdiv).style.backgroundImage = "url(${ctx}/resources/css/${_theme}/images/frame/submenu_h3.gif)"; 
                tempdiv = id.id;
                top.bodyFrame.document.getElementById("queryFrame").src = '${ctx}/zg/plan/ZgTorderPlan/query1.do?type=${type}&arbpl='+arbpl+'&viewModel=${viewModel}';
            	document.getElementById("h"+id.id).style.backgroundImage = "url(test.gif)"; 
            }
        }
		
		function initLoad(){
			
		}
    
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body onload="initLoad()">
    <div class="submenu">
        <h2> 
            操作菜单 
        </h2>
        
        <c:forEach items="${arbpList}" var="arbpl" varStatus="n">
        
      
        <c:if test="${n.count==1}">
         	 <h3 onclick="OnShow2(div${n.count },'${arbpl.cuid }')" id="hdiv${n.count }" align="center" style="background-image:test.gif">
            	${arbpl.cuid }
        	</h3>
         	 <ul id="div${n.count }" class="treeview" style="display: none"> </ul>
         	 <script>
         	 	OnShow2(document.getElementById('div${n.count }'),'${arbpl.cuid }');
         	 	</script>
        </c:if>
          <c:if test="${n.count>1}">
                  <h3 onclick="OnShow2(div${n.count },'${arbpl.cuid }')" id="hdiv${n.count }" align="center" >
            ${arbpl.cuid } 
        </h3>
          <ul id="div${n.count }" class="treeview" style="display:none "> </ul>
        </c:if>
        </c:forEach>
        
     
        
    </div>
</body>
</html>