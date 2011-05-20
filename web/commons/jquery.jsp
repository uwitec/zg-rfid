<link type="text/css" href="${ctx}/resources/css/${_theme}/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/widgets/jquery-ui/js/jquery-ui-1.8.custom.min.js"></script>
<script src="<c:url value="/widgets/jquery-validate/jquery.validate.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/application.js"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/URLEncoder.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		if(${fn:length(noticeList)>0}){
			alert("${noticeList[0].content}");
		}
	});
$.validator.setDefaults({
	submitHandler: function(form) {
		form.submit();
	}
});
</script>
<script type="text/javascript" src="${ctx}/scripts/initFrame.js"></script>