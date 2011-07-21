<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<!-- optional javascript: effects.js,jquery.js -->
<link href="<c:url value="/styles/global.css"/>" type="text/css" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css"/>" type="text/css"  rel="stylesheet" />
<script language="javascript" event="onkeydown" for="document">  
	<!--
	if(event.keyCode==13)  
	{  
		if( document.getElementById("queryBtn")!=null){
			 document.getElementById("queryBtn").click();
		}
	  
	} 
	-->
	</script> 
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<script type="text/javascript" src="<c:url value="/scripts/utils.js"/>"></script>
<%
	String iconPath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/" + "resources/css/default/images/icons/";
%>