/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTback extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_BACK";
	public static final String BM_CLASS_ID = "ZG_T_BACK";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "退单ID";
	public static final String ALIAS_BATCH_NO = "批次";
	public static final String ALIAS_BATCH_DATE = "退单时间";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_AUFNR = "生产订单编号";
	//date formats
	public static final String FORMAT_BATCH_DATE = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	
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
	private java.lang.Long batchNo;
	private java.util.Date batchDate_start;
	private java.util.Date batchDate_end;
	private java.util.Date batchDate;
	private java.util.Date createTime_start;
	private java.util.Date createTime_end;
	private java.util.Date createTime;
	private java.util.Date updateTime_start;
	private java.util.Date updateTime_end;
	private java.util.Date updateTime;
	private java.lang.String aufnr;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.Long getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(java.lang.Long value) {
		this.batchNo = value;
	}
	public String getBatchDateString() {
		return date2String(getBatchDate(), FORMAT_BATCH_DATE);
	}
	public void setBatchDateString(String value) {
		setBatchDate(string2Date(value, FORMAT_BATCH_DATE,java.util.Date.class));
	}
	public java.util.Date getBatchDate_start() {
		return this.batchDate_start;
	}
	
	public void setBatchDate_start(java.util.Date value) {
		this.batchDate_start = value;
	}
	
	public java.util.Date getBatchDate_end() {
		return this.batchDate_end;
	}
	
	public void setBatchDate_end(java.util.Date value) {
		this.batchDate_end = value;
	}
	public java.util.Date getBatchDate() {
		return this.batchDate;
	}
	
	public void setBatchDate(java.util.Date value) {
		this.batchDate = value;
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
	public String getUpdateTimeString() {
		return date2String(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(string2Date(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	public java.util.Date getUpdateTime_start() {
		return this.updateTime_start;
	}
	
	public void setUpdateTime_start(java.util.Date value) {
		this.updateTime_start = value;
	}
	
	public java.util.Date getUpdateTime_end() {
		return this.updateTime_end;
	}
	
	public void setUpdateTime_end(java.util.Date value) {
		this.updateTime_end = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.lang.String getAufnr() {
		return this.aufnr;
	}
	
	public void setAufnr(java.lang.String value) {
		this.aufnr = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("BatchNo",getBatchNo())
			.append("BatchDate",getBatchDate())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Aufnr",getAufnr())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getBatchNo())
			.append(getBatchDate())
			.append(getCreateTime())
			.append(getUpdateTime())
			.append(getAufnr())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTback == false) return false;
		if(this == obj) return true;
		ZgTback other = (ZgTback)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getBatchNo(),other.getBatchNo())
			.append(getBatchDate(),other.getBatchDate())
			.append(getCreateTime(),other.getCreateTime())
			.append(getUpdateTime(),other.getUpdateTime())
			.append(getAufnr(),other.getAufnr())
			.isEquals();
	}
}
