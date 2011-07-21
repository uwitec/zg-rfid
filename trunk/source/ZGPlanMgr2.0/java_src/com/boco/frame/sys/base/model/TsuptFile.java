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


public class TsuptFile extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SUPT_FILE";
	public static final String BM_CLASS_ID = "T_SUPT_FILE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_RELATED_TYPE_CUID = "RELATED_TYPE_CUID";
	public static final String ALIAS_PICS = "PICS";
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_CREATE_TIME = "CREATE_TIME";
	public static final String ALIAS_LAST_MODIFY_TIME = "LAST_MODIFY_TIME";
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_MODIFY_TIME = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String relatedTypeCuid;
	private java.lang.Object pics;
	private java.lang.String cuid;
	private java.util.Date createTime_start;
	private java.util.Date createTime_end;
	private java.util.Date createTime;
	private java.util.Date lastModifyTime_start;
	private java.util.Date lastModifyTime_end;
	private java.util.Date lastModifyTime;
	//columns END
	public java.lang.String getRelatedTypeCuid() {
		return this.relatedTypeCuid;
	}
	
	public void setRelatedTypeCuid(java.lang.String value) {
		this.relatedTypeCuid = value;
	}
	public java.lang.Object getPics() {
		return this.pics;
	}
	
	public void setPics(java.lang.Object value) {
		this.pics = value;
	}
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
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

	public String toString() {
		return new ToStringBuilder(this)
			.append("RelatedTypeCuid",getRelatedTypeCuid())
			.append("Pics",getPics())
			.append("Cuid",getCuid())
			.append("CreateTime",getCreateTime())
			.append("LastModifyTime",getLastModifyTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRelatedTypeCuid())
			.append(getPics())
			.append(getCuid())
			.append(getCreateTime())
			.append(getLastModifyTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsuptFile == false) return false;
		if(this == obj) return true;
		TsuptFile other = (TsuptFile)obj;
		return new EqualsBuilder()
			.append(getRelatedTypeCuid(),other.getRelatedTypeCuid())
			.append(getPics(),other.getPics())
			.append(getCuid(),other.getCuid())
			.append(getCreateTime(),other.getCreateTime())
			.append(getLastModifyTime(),other.getLastModifyTime())
			.isEquals();
	}
}
