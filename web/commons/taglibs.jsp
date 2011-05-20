<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/dwr/engine.js"></script>
<script src="${ctx}/dwr/util.js"></script>
<c:choose>
	<c:when test="${empty sessionScope._theme}">
		<c:set var="_theme" value="default"/>
	</c:when>
	<c:otherwise>
		<c:set var="_theme" value="${sessionScope._theme}"/>
	</c:otherwise>
</c:choose>