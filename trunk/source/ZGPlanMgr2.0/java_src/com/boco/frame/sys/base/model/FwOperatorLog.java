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
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class FwOperatorLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_OPERATOR_LOG";
	public static final String BM_CLASS_ID = "FW_OPERATOR_LOG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_CREATE_ID = "操作人员";
	public static final String ALIAS_CREATE_DATE = "操作时间";
	public static final String ALIAS_TARGET_ID = "操作对象";
	public static final String ALIAS_ACTION = "具体操作";
	public static final String ALIAS_OPERATOR_TYPE = "操作类型";
	public static final String ALIAS_TARGET_NAME = "操作对象名称";
	public static final String CREATE_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_0_1.t0_";
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getOperatorType()==null?"":this.getOperatorType().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String createId_labelCn;
	private RelatedModel createId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String createId;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private java.lang.String targetId;
	private java.lang.String action;
	private java.lang.String operatorType;
	private java.lang.String targetName;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getCreateId_labelCn() {
		return this.createId_labelCn;
	}
	
	public void setCreateId_labelCn(java.lang.String value) {
		this.createId_labelCn = value;
	}
	
	public RelatedModel getCreateId_related() {
		return this.createId_related;
	}
	
	public void setCreateId_related(RelatedModel value) {
		this.createId_related = value;
	}
	public java.lang.String getCreateId() {
		return this.createId;
	}
	
	public void setCreateId(java.lang.String value) {
		this.createId = value;
	}
	public String getCreateDateString() {
		return date2String(getCreateDate(), FORMAT_CREATE_DATE);
	}
	public void setCreateDateString(String value) {
		setCreateDate(string2Date(value, FORMAT_CREATE_DATE,java.util.Date.class));
	}
	public java.util.Date getCreateDate_start() {
		return this.createDate_start;
	}
	
	public void setCreateDate_start(java.util.Date value) {
		this.createDate_start = value;
	}
	
	public java.util.Date getCreateDate_end() {
		return this.createDate_end;
	}
	
	public void setCreateDate_end(java.util.Date value) {
		this.createDate_end = value;
	}
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	public java.lang.String getTargetId() {
		return this.targetId;
	}
	
	public void setTargetId(java.lang.String value) {
		this.targetId = value;
	}
	public java.lang.String getAction() {
		return this.action;
	}
	
	public void setAction(java.lang.String value) {
		this.action = value;
	}
	public java.lang.String getOperatorType() {
		return this.operatorType;
	}
	
	public void setOperatorType(java.lang.String value) {
		this.operatorType = value;
	}
	public java.lang.String getTargetName() {
		return this.targetName;
	}
	
	public void setTargetName(java.lang.String value) {
		this.targetName = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("CreateId",getCreateId())
			.append("CreateDate",getCreateDate())
			.append("TargetId",getTargetId())
			.append("Action",getAction())
			.append("OperatorType",getOperatorType())
			.append("TargetName",getTargetName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getCreateId())
			.append(getCreateDate())
			.append(getTargetId())
			.append(getAction())
			.append(getOperatorType())
			.append(getTargetName())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwOperatorLog == false) return false;
		if(this == obj) return true;
		FwOperatorLog other = (FwOperatorLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getCreateId(),other.getCreateId())
			.append(getCreateDate(),other.getCreateDate())
			.append(getTargetId(),other.getTargetId())
			.append(getAction(),other.getAction())
			.append(getOperatorType(),other.getOperatorType())
			.append(getTargetName(),other.getTargetName())
			.isEquals();
	}
}
