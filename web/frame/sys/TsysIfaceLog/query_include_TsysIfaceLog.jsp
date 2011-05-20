<%@page import="com.boco.frame.sys.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_SER_CALLER" value="<%=map.get("ec_s_t0_SER_CALLER")==null?"":map.get("ec_s_t0_SER_CALLER")%>"/>
	<input type="hidden"  name="ec_a_SER_CALLER" value="serCaller" />
	<input type="hidden"  name="s_serCaller"  value="${pageRequest.filters.serCaller}" />
	<input type="hidden"  name="ec_s_t0_SER_SUPPLIER" value="<%=map.get("ec_s_t0_SER_SUPPLIER")==null?"":map.get("ec_s_t0_SER_SUPPLIER")%>"/>
	<input type="hidden"  name="ec_a_SER_SUPPLIER" value="serSupplier" />
	<input type="hidden"  name="s_serSupplier"  value="${pageRequest.filters.serSupplier}" />
	<input type="hidden"  name="ec_s_t0_INTERFACE_NAME" value="<%=map.get("ec_s_t0_INTERFACE_NAME")==null?"":map.get("ec_s_t0_INTERFACE_NAME")%>"/>
	<input type="hidden"  name="ec_a_INTERFACE_NAME" value="interfaceName" />
	<input type="hidden"  name="s_interfaceName"  value="${pageRequest.filters.interfaceName}" />
	<input type="hidden"  name="ec_s_t0_METHOD_NAME" value="<%=map.get("ec_s_t0_METHOD_NAME")==null?"":map.get("ec_s_t0_METHOD_NAME")%>"/>
	<input type="hidden"  name="ec_a_METHOD_NAME" value="methodName" />
	<input type="hidden"  name="s_methodName"  value="${pageRequest.filters.methodName}" />
	<input type="hidden"  name="ec_s_t0_DATA_STAUTS" value="<%=map.get("ec_s_t0_DATA_STAUTS")==null?"":map.get("ec_s_t0_DATA_STAUTS")%>"/>
	<input type="hidden"  name="ec_a_DATA_STAUTS" value="dataStauts" />
	<input type="hidden"  name="s_dataStauts"  value="${pageRequest.filters.dataStauts}" />
	<input type="hidden"  name="ec_s_t0_PARAMETERS" value="<%=map.get("ec_s_t0_PARAMETERS")==null?"":map.get("ec_s_t0_PARAMETERS")%>"/>
	<input type="hidden"  name="ec_a_PARAMETERS" value="parameters" />
	<input type="hidden"  name="s_parameters"  value="${pageRequest.filters.parameters}" />
	<input type="hidden"  name="ec_s_t0_RESULT" value="<%=map.get("ec_s_t0_RESULT")==null?"":map.get("ec_s_t0_RESULT")%>"/>
	<input type="hidden"  name="ec_a_RESULT" value="result" />
	<input type="hidden"  name="s_result"  value="${pageRequest.filters.result}" />
	<input type="hidden"  name="ec_s_t0_CALL_TIME" value="<%=map.get("ec_s_t0_CALL_TIME")==null?"":map.get("ec_s_t0_CALL_TIME")%>"/>
	<input type="hidden"  name="ec_a_CALL_TIME" value="callTime" />
	<input type="hidden"  name="s_callTime_start"  value="${pageRequest.filters.callTime_start}" />
	<input type="hidden"  name="s_callTime_end"  value="${pageRequest.filters.callTime_end}" />
	<input type="hidden"  name="ec_s_t0_REMARK" value="<%=map.get("ec_s_t0_REMARK")==null?"":map.get("ec_s_t0_REMARK")%>"/>
	<input type="hidden"  name="ec_a_REMARK" value="remark" />
	<input type="hidden"  name="s_remark"  value="${pageRequest.filters.remark}" />
	<input type="hidden"  name="ec_s_T0_BATCH_NO" value="<%=map.get("ec_s_T0_BATCH_NO")==null?"":map.get("ec_s_T0_BATCH_NO")%>"/>
	
	
