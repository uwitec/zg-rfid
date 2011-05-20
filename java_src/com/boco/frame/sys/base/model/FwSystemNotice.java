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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwSystemNotice extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_SYSTEM_NOTICE";
	public static final String BM_CLASS_ID = "FW_SYSTEM_NOTICE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_CONTENT = "公告内容";
	public static final String ALIAS_CREATE_ID = "创建人";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_STATE = "状态";
	public static final String CREATE_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_0_1.t0_";
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getContent()==null?"":this.getContent().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String content;
	private java.lang.String createId_labelCn;
	private RelatedModel createId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String createId;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private java.lang.String title;
	private java.lang.String state;
	private String modifyId;
	private Date lastModifyDate;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
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
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Content",getContent())
			.append("CreateId",getCreateId())
			.append("CreateDate",getCreateDate())
			.append("Title",getTitle())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getContent())
			.append(getCreateId())
			.append(getCreateDate())
			.append(getTitle())
			.append(getState())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwSystemNotice == false) return false;
		if(this == obj) return true;
		FwSystemNotice other = (FwSystemNotice)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getContent(),other.getContent())
			.append(getCreateId(),other.getCreateId())
			.append(getCreateDate(),other.getCreateDate())
			.append(getTitle(),other.getTitle())
			.append(getState(),other.getState())
			.isEquals();
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
}
