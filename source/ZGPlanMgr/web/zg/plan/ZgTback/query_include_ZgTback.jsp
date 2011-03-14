<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_BATCH_NO" value="<%=map.get("ec_s_t0_BATCH_NO")==null?"":map.get("ec_s_t0_BATCH_NO")%>"/>
	<input type="hidden"  name="ec_a_BATCH_NO" value="batchNo" />
	<input type="hidden"  name="s_batchNo"  value="${pageRequest.filters.batchNo}" />
	<input type="hidden"  name="ec_s_t0_BATCH_DATE" value="<%=map.get("ec_s_t0_BATCH_DATE")==null?"":map.get("ec_s_t0_BATCH_DATE")%>"/>
	<input type="hidden"  name="ec_a_BATCH_DATE" value="batchDate" />
	<input type="hidden"  name="s_batchDate_start"  value="${pageRequest.filters.batchDate_start}" />
	<input type="hidden"  name="s_batchDate_end"  value="${pageRequest.filters.batchDate_end}" />
	<input type="hidden"  name="ec_s_t0_CREATE_TIME" value="<%=map.get("ec_s_t0_CREATE_TIME")==null?"":map.get("ec_s_t0_CREATE_TIME")%>"/>
	<input type="hidden"  name="ec_a_CREATE_TIME" value="createTime" />
	<input type="hidden"  name="s_createTime_start"  value="${pageRequest.filters.createTime_start}" />
	<input type="hidden"  name="s_createTime_end"  value="${pageRequest.filters.createTime_end}" />
	<input type="hidden"  name="ec_s_t0_UPDATE_TIME" value="<%=map.get("ec_s_t0_UPDATE_TIME")==null?"":map.get("ec_s_t0_UPDATE_TIME")%>"/>
	<input type="hidden"  name="ec_a_UPDATE_TIME" value="updateTime" />
	<input type="hidden"  name="s_updateTime_start"  value="${pageRequest.filters.updateTime_start}" />
	<input type="hidden"  name="s_updateTime_end"  value="${pageRequest.filters.updateTime_end}" />
	<input type="hidden"  name="ec_s_t0_AUFNR" value="<%=map.get("ec_s_t0_AUFNR")==null?"":map.get("ec_s_t0_AUFNR")%>"/>
	<input type="hidden"  name="ec_a_AUFNR" value="aufnr" />
	<input type="hidden"  name="s_aufnr"  value="${pageRequest.filters.aufnr}" />
