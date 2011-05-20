<%@page import="com.boco.zg.storage.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden"  name="ec_i"  value="ec" />
	<input type="hidden"  name="ec_crd" value="${page.pageSize}"/>
	<input type="hidden"  name="ec_p" value="${page.thisPageNumber}"/>
	<input type="hidden"  name="s_equalBmClassIdQuery"  value="${pageRequest.filters.equalBmClassIdQuery}"/>
	<input type="hidden"  name="s_inSubBmClassIdQuery"  value="${pageRequest.filters.inSubBmClassIdQuery}"/>
	<input type="hidden"  name="ec_s_t0_LABEL_CN" value="<%=map.get("ec_s_t0_LABEL_CN")==null?"":map.get("ec_s_t0_LABEL_CN")%>"/>
	<input type="hidden"  name="ec_a_LABEL_CN" value="labelCn" />
	<input type="hidden"  name="s_labelCn"  value="${pageRequest.filters.labelCn}" />
	<input type="hidden"  name="ec_s_t0_STORAGE_CANCLE_ID" value="<%=map.get("ec_s_t0_STORAGE_CANCLE_ID")==null?"":map.get("ec_s_t0_STORAGE_CANCLE_ID")%>"/>
	<input type="hidden"  name="ec_a_STORAGE_CANCLE_ID" value="storageCancleId" />
	<input type="hidden"  name="s_storageCancleId"  value="${pageRequest.filters.storageCancleId}" />
	<input type="hidden"  name="ec_s_t0_STORAGE_ID" value="<%=map.get("ec_s_t0_STORAGE_ID")==null?"":map.get("ec_s_t0_STORAGE_ID")%>"/>
	<input type="hidden"  name="ec_a_STORAGE_ID" value="storageId" />
	<input type="hidden"  name="s_storageId"  value="${pageRequest.filters.storageId}" />
	<input type="hidden"  name="ec_s_t0_MANTR" value="<%=map.get("ec_s_t0_MANTR")==null?"":map.get("ec_s_t0_MANTR")%>"/>
	<input type="hidden"  name="ec_a_MANTR" value="mantr" />
	<input type="hidden"  name="s_mantr"  value="${pageRequest.filters.mantr}" />
	<input type="hidden"  name="ec_s_t0_NUM" value="<%=map.get("ec_s_t0_NUM")==null?"":map.get("ec_s_t0_NUM")%>"/>
	<input type="hidden"  name="ec_a_NUM" value="num" />
	<input type="hidden"  name="s_num"  value="${pageRequest.filters.num}" />
	<input type="hidden"  name="ec_s_t0_ZBZ" value="<%=map.get("ec_s_t0_ZBZ")==null?"":map.get("ec_s_t0_ZBZ")%>"/>
	<input type="hidden"  name="ec_a_ZBZ" value="zbz" />
	<input type="hidden"  name="s_zbz"  value="${pageRequest.filters.zbz}" />
