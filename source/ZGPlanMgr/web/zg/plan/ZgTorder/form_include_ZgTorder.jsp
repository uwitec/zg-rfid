<%@page import="com.boco.zg.plan.base.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="validate_ZgTorder.jsp" %>
	<s:hidden id="cuid" name="cuid" />
		
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=ZgTorder.ALIAS_AUFNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.aufnr}" size="30" name="aufnr"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<span class="alterlabel">*</span><%=ZgTorder.ALIAS_ORDER_STATE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.orderState}" size="30" name="orderState"  class="required"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_SUBMIT_USER%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.submitUser_related.value}" autocomplete="true" xtype="drm-complex-select" id="submitUser_label" columnNameLower="submitUser" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.submitUser}" id="submitUser_value" name="submitUser"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_SUBMIT_DATE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="submitDateString" name="submitDateString" value="${model.submitDateString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MANDT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.mandt}" size="30" name="mandt"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PXDAT%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="pxdatString" name="pxdatString" value="${model.pxdatString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PLANT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.plant}" size="30" name="plant"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MIPOS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.mipos}" size="30" name="mipos"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PCDAT%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="pcdatString" name="pcdatString" value="${model.pcdatString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ARBPL%>:
		</td>	
		<td>
	   		<input type="text" value="${model.arbpl}" size="30" name="arbpl"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MATNR%>:
		</td>	
		<td>
	   		<input type="text" value="${model.matnr}" size="30" name="matnr"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_KDAUF%>:
		</td>	
		<td>
	   		<input type="text" value="${model.kdauf}" size="30" name="kdauf"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_KDPOS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.kdpos}" size="30" name="kdpos"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_KDTXT%>:
		</td>	
		<td>
	   		<input type="text" value="${model.kdtxt}" size="30" name="kdtxt"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ZCKPP%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zckpp}" size="30" name="zckpp"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MAKTX2%>:
		</td>	
		<td>
	   		<input type="text" value="${model.maktx2}" size="30" name="maktx2"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MAKTX1%>:
		</td>	
		<td>
	   		<input type="text" value="${model.maktx1}" size="30" name="maktx1"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ZZCKS%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zzcks}" size="30" name="zzcks"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ATWRT2%>:
		</td>	
		<td>
	   		<input type="text" value="${model.atwrt2}" size="30" name="atwrt2"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PSMNG%>:
		</td>	
		<td>
	   		<input type="text" value="${model.psmng}" size="30" name="psmng"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PMENGE%>:
		</td>	
		<td>
	   		<input type="text" value="${model.pmenge}" size="30" name="pmenge"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ZTXT02%>:
		</td>	
		<td>
	   		<input type="text" value="${model.ztxt02}" size="30" name="ztxt02"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ZDBLC%>:
		</td>	
		<td>
	   		<input type="text" value="${model.zdblc}" size="30" name="zdblc"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_BRGEW2%>:
		</td>	
		<td>
	   		<input type="text" value="${model.brgew2}" size="30" name="brgew2"  class=""/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_CRDAT%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="crdatString" name="crdatString" value="${model.crdatString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_CPUTM%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="cputmString" name="cputmString" value="${model.cputmString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_CRNAM%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.crnam_related.value}" autocomplete="true" xtype="drm-complex-select" id="crnam_label" columnNameLower="crnam" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.crnam}" id="crnam_value" name="crnam"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MRNAM%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="mrnamString" name="mrnamString" value="${model.mrnamString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_ZMUZE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="zmuzeString" name="zmuzeString" value="${model.zmuzeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_MNAME%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.mname_related.value}" autocomplete="true" xtype="drm-complex-select" id="mname_label" columnNameLower="mname" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.mname}" id="mname_value" name="mname"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_FBDAT%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="fbdatString" name="fbdatString" value="${model.fbdatString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_FBUZE%>:
		</td>	
		<td>
	   		<input type="text" dateFlag="true" id="fbuzeString" name="fbuzeString" value="${model.fbuzeString}" class="" size="30"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_FNAME%>:
		</td>	
		<td>
	   		<input type="text" size="30" value="${model.fname_related.value}" autocomplete="true" xtype="drm-complex-select" id="fname_label" columnNameLower="fname" bmClassId="FW_EMPLOYEE" column="m.t1_LABEL_CN" class=""/>
	   		<input type="hidden" value="${model.fname}" id="fname_value" name="fname"/>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td class="tdLabel">
			<%=ZgTorder.ALIAS_PFLAG%>:
		</td>	
		<td>
	   		<input type="text" value="${model.pflag}" size="30" name="pflag"  class=""/>
		</td>
	</tr>
