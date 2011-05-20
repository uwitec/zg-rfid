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


public class FwOperator extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_OPERATOR";
	public static final String BM_CLASS_ID = "FW_OPERATOR";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_USER_ID = "USER_ID";
	public static final String ALIAS_PASSWORD = "PASSWORD";
	public static final String ALIAS_CREATE_TIME = "CREATE_TIME";
	public static final String ALIAS_LAST_MODIFY_TIME = "LAST_MODIFY_TIME";
	public static final String ALIAS_EFFECT_TIME = "EFFECT_TIME";
	public static final String ALIAS_STATUS = "STATUS";
	public static final String ALIAS_RELATED_BM_CLASS_ID = "RELATED_BM_CLASS_ID";
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_MODIFY_TIME = DATE_FORMAT;
	public static final String FORMAT_EFFECT_TIME = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getLabelCn()==null?"":this.getLabelCn().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String userId;
	private java.lang.String password;
	private java.util.Date createTime_start;
	private java.util.Date createTime_end;
	private java.util.Date createTime;
	private java.util.Date lastModifyTime_start;
	private java.util.Date lastModifyTime_end;
	private java.util.Date lastModifyTime;
	private java.util.Date effectTime_start;
	private java.util.Date effectTime_end;
	private java.util.Date effectTime;
	private java.lang.Long status;
	private java.lang.String relatedBmClassId;
	private String rfidCode;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
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
	public String getLastModifyTimeString() {
		return date2String(getLastModifyTime(), FORMAT_LAST_MODIFY_TIME);
	}
	public void setLastModifyTimeString(String value) {
		setLastModifyTime(string2Date(value, FORMAT_LAST_MODIFY_TIME,java.util.Date.class));
	}
	public java.util.Date getLastModifyTime_start() {
		return this.lastModifyTime_start;
	}
	
	public void setLastModifyTime_start(java.util.Date value) {
		this.lastModifyTime_start = value;
	}
	
	public java.util.Date getLastModifyTime_end() {
		return this.lastModifyTime_end;
	}
	
	public void setLastModifyTime_end(java.util.Date value) {
		this.lastModifyTime_end = value;
	}
	public java.util.Date getLastModifyTime() {
		return this.lastModifyTime;
	}
	
	public void setLastModifyTime(java.util.Date value) {
		this.lastModifyTime = value;
	}
	public String getEffectTimeString() {
		return date2String(getEffectTime(), FORMAT_EFFECT_TIME);
	}
	public void setEffectTimeString(String value) {
		setEffectTime(string2Date(value, FORMAT_EFFECT_TIME,java.util.Date.class));
	}
	public java.util.Date getEffectTime_start() {
		return this.effectTime_start;
	}
	
	public void setEffectTime_start(java.util.Date value) {
		this.effectTime_start = value;
	}
	
	public java.util.Date getEffectTime_end() {
		return this.effectTime_end;
	}
	
	public void setEffectTime_end(java.util.Date value) {
		this.effectTime_end = value;
	}
	public java.util.Date getEffectTime() {
		return this.effectTime;
	}
	
	public void setEffectTime(java.util.Date value) {
		this.effectTime = value;
	}
	public java.lang.Long getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Long value) {
		this.status = value;
	}
	public java.lang.String getRelatedBmClassId() {
		return this.relatedBmClassId;
	}
	
	public void setRelatedBmClassId(java.lang.String value) {
		this.relatedBmClassId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("UserId",getUserId())
			.append("Password",getPassword())
			.append("CreateTime",getCreateTime())
			.append("LastModifyTime",getLastModifyTime())
			.append("EffectTime",getEffectTime())
			.append("Status",getStatus())
			.append("RelatedBmClassId",getRelatedBmClassId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getUserId())
			.append(getPassword())
			.append(getCreateTime())
			.append(getLastModifyTime())
			.append(getEffectTime())
			.append(getStatus())
			.append(getRelatedBmClassId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwOperator == false) return false;
		if(this == obj) return true;
		FwOperator other = (FwOperator)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getUserId(),other.getUserId())
			.append(getPassword(),other.getPassword())
			.append(getCreateTime(),other.getCreateTime())
			.append(getLastModifyTime(),other.getLastModifyTime())
			.append(getEffectTime(),other.getEffectTime())
			.append(getStatus(),other.getStatus())
			.append(getRelatedBmClassId(),other.getRelatedBmClassId())
			.isEquals();
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}
}
