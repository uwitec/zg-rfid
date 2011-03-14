/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class TmdModelLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdModelLog";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_ERROR_MSG = "errorMsg";
	public static final String ALIAS_BEFORE_CONTEXT = "beforeContext";
	public static final String ALIAS_BEFORE_CONTEXT_SQL = "beforeContextSql";
	public static final String ALIAS_LATE_CONTEXT = "lateContext";
	public static final String ALIAS_LATE_CONTEXT_SQL = "lateContextSql";
	public static final String ALIAS_IS_MUST = "isMust";
	public static final String ALIAS_IS_PEOPLE = "isPeople";
	public static final String ALIAS_STATUS = "status";
	public static final String ALIAS_OPERATOR = "operator";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_MODIFY_TIME = "modifyTime";
	public static final String ALIAS_VERSION = "version";
	public static final String ALIAS_OPER_TYPE = "operType";
	public static final String ALIAS_VALIDATE_SQL = "validateSql";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_MODIFY_TIME = DATE_TIME_FORMAT;
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String type;
	private java.lang.String errorMsg;
	private java.lang.String beforeContext;
	private java.lang.String beforeContextSql;
	private java.lang.String lateContext;
	private java.lang.String lateContextSql;
	private java.lang.String isMust;
	private java.lang.String isPeople;
	private java.lang.String status;
	private java.lang.String operator;
	private java.util.Date createTime_start;
	private java.util.Date createTime_end;
	private java.util.Date createTime;
	private java.util.Date modifyTime_start;
	private java.util.Date modifyTime_end;
	private java.util.Date modifyTime;
	private java.lang.String version;
	private java.lang.String operType;
	private java.lang.String validateSql;
	//columns END


	public TmdModelLog(){
	}

	public TmdModelLog(
		java.lang.String cuid
	){
		this.cuid = cuid;
	}



	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	
	public java.lang.String getCuid() {
		return this.cuid;
	}


	
	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}

	
	public java.lang.String getErrorMsg() {
		return this.errorMsg;
	}
	
	public void setErrorMsg(java.lang.String value) {
		this.errorMsg = value;
	}

	
	public java.lang.String getBeforeContext() {
		return this.beforeContext;
	}
	
	public void setBeforeContext(java.lang.String value) {
		this.beforeContext = value;
	}

	
	public java.lang.String getBeforeContextSql() {
		return this.beforeContextSql;
	}
	
	public void setBeforeContextSql(java.lang.String value) {
		this.beforeContextSql = value;
	}

	
	public java.lang.String getLateContext() {
		return this.lateContext;
	}
	
	public void setLateContext(java.lang.String value) {
		this.lateContext = value;
	}

	
	public java.lang.String getLateContextSql() {
		return this.lateContextSql;
	}
	
	public void setLateContextSql(java.lang.String value) {
		this.lateContextSql = value;
	}

	
	public java.lang.String getIsMust() {
		return this.isMust;
	}
	
	public void setIsMust(java.lang.String value) {
		this.isMust = value;
	}

	
	public java.lang.String getIsPeople() {
		return this.isPeople;
	}
	
	public void setIsPeople(java.lang.String value) {
		this.isPeople = value;
	}

	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}

	
	public String getCreateTimeString() {
		return date2String(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(string2Date(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	public java.util.Date getCreateTime_start() {
		return this.createTime_start;
	}
	
	public void setCreateTime_start(java.util.Date value) {
		this.createTime_start = value;
	}
	
	public java.util.Date getCreateTime_end() {
		return this.createTime_end;
	}
	
	public void setCreateTime_end(java.util.Date value) {
		this.createTime_end = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	
	public String getModifyTimeString() {
		return date2String(getModifyTime(), FORMAT_MODIFY_TIME);
	}
	public void setModifyTimeString(String value) {
		setModifyTime(string2Date(value, FORMAT_MODIFY_TIME,java.util.Date.class));
	}
	public java.util.Date getModifyTime_start() {
		return this.modifyTime_start;
	}
	
	public void setModifyTime_start(java.util.Date value) {
		this.modifyTime_start = value;
	}
	
	public java.util.Date getModifyTime_end() {
		return this.modifyTime_end;
	}
	
	public void setModifyTime_end(java.util.Date value) {
		this.modifyTime_end = value;
	}
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}

	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}

	
	public java.lang.String getOperType() {
		return this.operType;
	}
	
	public void setOperType(java.lang.String value) {
		this.operType = value;
	}

	
	public java.lang.String getValidateSql() {
		return this.validateSql;
	}
	
	public void setValidateSql(java.lang.String value) {
		this.validateSql = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Type",getType())
			.append("ErrorMsg",getErrorMsg())
			.append("BeforeContext",getBeforeContext())
			.append("BeforeContextSql",getBeforeContextSql())
			.append("LateContext",getLateContext())
			.append("LateContextSql",getLateContextSql())
			.append("IsMust",getIsMust())
			.append("IsPeople",getIsPeople())
			.append("Status",getStatus())
			.append("Operator",getOperator())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.append("Version",getVersion())
			.append("OperType",getOperType())
			.append("ValidateSql",getValidateSql())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getType())
			.append(getErrorMsg())
			.append(getBeforeContext())
			.append(getBeforeContextSql())
			.append(getLateContext())
			.append(getLateContextSql())
			.append(getIsMust())
			.append(getIsPeople())
			.append(getStatus())
			.append(getOperator())
			.append(getCreateTime())
			.append(getModifyTime())
			.append(getVersion())
			.append(getOperType())
			.append(getValidateSql())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdModelLog == false) return false;
		if(this == obj) return true;
		TmdModelLog other = (TmdModelLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getType(),other.getType())
			.append(getErrorMsg(),other.getErrorMsg())
			.append(getBeforeContext(),other.getBeforeContext())
			.append(getBeforeContextSql(),other.getBeforeContextSql())
			.append(getLateContext(),other.getLateContext())
			.append(getLateContextSql(),other.getLateContextSql())
			.append(getIsMust(),other.getIsMust())
			.append(getIsPeople(),other.getIsPeople())
			.append(getStatus(),other.getStatus())
			.append(getOperator(),other.getOperator())
			.append(getCreateTime(),other.getCreateTime())
			.append(getModifyTime(),other.getModifyTime())
			.append(getVersion(),other.getVersion())
			.append(getOperType(),other.getOperType())
			.append(getValidateSql(),other.getValidateSql())
			.isEquals();
	}
}
