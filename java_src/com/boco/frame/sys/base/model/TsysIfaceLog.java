/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.model;

import java.util.List;

import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class TsysIfaceLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SYS_IFACE_LOG";
	public static final String BM_CLASS_ID = "T_SYS_IFACE_LOG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "日志ID";
	public static final String ALIAS_SER_CALLER = "接口调用方";
	public static final String ALIAS_SER_SUPPLIER = "接口消费方";
	public static final String ALIAS_INTERFACE_NAME = "接口名称";
	public static final String ALIAS_METHOD_NAME = "接口方法";
	public static final String ALIAS_DATA_STAUTS = "接收数据状态";
	public static final String ALIAS_PARAMETERS = "接口参数";
	public static final String ALIAS_RESULT = "调用结果";
	public static final String ALIAS_CALL_TIME = "调用时间";
	public static final String ALIAS_REMARK = "描述";
	//date formats
	public static final String FORMAT_CALL_TIME = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String serCaller;
	private java.lang.String serSupplier;
	private java.lang.String interfaceName;
	private java.lang.String methodName;
	private java.lang.String dataStauts;
	private java.lang.String parameters;
	private java.lang.String result;
	private java.util.Date callTime_start;
	private java.util.Date callTime_end;
	private java.util.Date callTime;
	private java.lang.String remark;
	private String batchNo;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getSerCaller() {
		return this.serCaller;
	}
	
	public void setSerCaller(java.lang.String value) {
		this.serCaller = value;
	}
	public java.lang.String getSerSupplier() {
		return this.serSupplier;
	}
	
	public void setSerSupplier(java.lang.String value) {
		this.serSupplier = value;
	}
	public java.lang.String getInterfaceName() {
		return this.interfaceName;
	}
	
	public void setInterfaceName(java.lang.String value) {
		this.interfaceName = value;
	}
	public java.lang.String getMethodName() {
		return this.methodName;
	}
	
	public void setMethodName(java.lang.String value) {
		this.methodName = value;
	}
	public java.lang.String getDataStauts() {
		return this.dataStauts;
	}
	
	public void setDataStauts(java.lang.String value) {
		this.dataStauts = value;
	}
	public java.lang.String getParameters() {
		return this.parameters;
	}
	
	public void setParameters(java.lang.String value) {
		this.parameters = value;
	}
	public java.lang.String getResult() {
		return this.result;
	}
	
	public void setResult(java.lang.String value) {
		this.result = value;
	}
	public String getCallTimeString() {
		return date2String(getCallTime(), FORMAT_CALL_TIME);
	}
	public void setCallTimeString(String value) {
		setCallTime(string2Date(value, FORMAT_CALL_TIME,java.util.Date.class));
	}
	public java.util.Date getCallTime_start() {
		return this.callTime_start;
	}
	
	public void setCallTime_start(java.util.Date value) {
		this.callTime_start = value;
	}
	
	public java.util.Date getCallTime_end() {
		return this.callTime_end;
	}
	
	public void setCallTime_end(java.util.Date value) {
		this.callTime_end = value;
	}
	public java.util.Date getCallTime() {
		return this.callTime;
	}
	
	public void setCallTime(java.util.Date value) {
		this.callTime = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("SerCaller",getSerCaller())
			.append("SerSupplier",getSerSupplier())
			.append("InterfaceName",getInterfaceName())
			.append("MethodName",getMethodName())
			.append("DataStauts",getDataStauts())
			.append("Parameters",getParameters())
			.append("Result",getResult())
			.append("CallTime",getCallTime())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getSerCaller())
			.append(getSerSupplier())
			.append(getInterfaceName())
			.append(getMethodName())
			.append(getDataStauts())
			.append(getParameters())
			.append(getResult())
			.append(getCallTime())
			.append(getRemark())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysIfaceLog == false) return false;
		if(this == obj) return true;
		TsysIfaceLog other = (TsysIfaceLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getSerCaller(),other.getSerCaller())
			.append(getSerSupplier(),other.getSerSupplier())
			.append(getInterfaceName(),other.getInterfaceName())
			.append(getMethodName(),other.getMethodName())
			.append(getDataStauts(),other.getDataStauts())
			.append(getParameters(),other.getParameters())
			.append(getResult(),other.getResult())
			.append(getCallTime(),other.getCallTime())
			.append(getRemark(),other.getRemark())
			.isEquals();
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
