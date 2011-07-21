/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import javacommon.base.BaseEntity;
import javacommon.base.model.RelatedModel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderbomMoveLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDERBOM_MOVE_LOG";
	public static final String BM_CLASS_ID = "ZG_T_ORDERBOM_MOVE_LOG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_SOURCE_PLANBOM_ID = "SOURCE_ORDER_TASK_ID";
	public static final String ALIAS_SOURCE_ORDER_TASK_ID = "SOURCE_ORDER_TASK_ID";
	public static final String ALIAS_TARGET_ORDER_TASK_ID = "TARGET_ORDER_TASK_ID";
	public static final String ALIAS_TARGET_PLANBOM = "TARGET_PLANBOM";
	public static final String ALIAS_MOVE_NUM = "MOVE_NUM";
	public static final String ALIAS_CREATE_ID = "移单人";
	public static final String ALIAS_CREATE_DATE = "移单时间";
	public static final String ALIAS_CREATE_USERNAME = "移单人姓名";
	public static final String CREATE_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_0_1.t0_";
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getCreateUsername()==null?"":this.getCreateUsername().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String sourcePlanbomId;
	private java.lang.String sourceOrderTaskId;
	private java.lang.String targetOrderTaskId;
	private java.lang.String targetPlanbom;
	private java.lang.Long moveNum;
	private java.lang.String createId_labelCn;
	private RelatedModel createId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String createId;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private java.lang.String createUsername;
	private String orgId;
	private String orgName;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getSourcePlanbomId() {
		return this.sourcePlanbomId;
	}
	
	public void setSourcePlanbomId(java.lang.String value) {
		this.sourcePlanbomId = value;
	}
	public java.lang.String getSourceOrderTaskId() {
		return this.sourceOrderTaskId;
	}
	
	public void setSourceOrderTaskId(java.lang.String value) {
		this.sourceOrderTaskId = value;
	}
	public java.lang.String getTargetOrderTaskId() {
		return this.targetOrderTaskId;
	}
	
	public void setTargetOrderTaskId(java.lang.String value) {
		this.targetOrderTaskId = value;
	}
	public java.lang.String getTargetPlanbom() {
		return this.targetPlanbom;
	}
	
	public void setTargetPlanbom(java.lang.String value) {
		this.targetPlanbom = value;
	}
	public java.lang.Long getMoveNum() {
		return this.moveNum;
	}
	
	public void setMoveNum(java.lang.Long value) {
		this.moveNum = value;
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
	public java.lang.String getCreateUsername() {
		return this.createUsername;
	}
	
	public void setCreateUsername(java.lang.String value) {
		this.createUsername = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("SourcePlanbomId",getSourcePlanbomId())
			.append("SourceOrderTaskId",getSourceOrderTaskId())
			.append("TargetOrderTaskId",getTargetOrderTaskId())
			.append("TargetPlanbom",getTargetPlanbom())
			.append("MoveNum",getMoveNum())
			.append("CreateId",getCreateId())
			.append("CreateDate",getCreateDate())
			.append("CreateUsername",getCreateUsername())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getSourcePlanbomId())
			.append(getSourceOrderTaskId())
			.append(getTargetOrderTaskId())
			.append(getTargetPlanbom())
			.append(getMoveNum())
			.append(getCreateId())
			.append(getCreateDate())
			.append(getCreateUsername())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderbomMoveLog == false) return false;
		if(this == obj) return true;
		ZgTorderbomMoveLog other = (ZgTorderbomMoveLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getSourcePlanbomId(),other.getSourcePlanbomId())
			.append(getSourceOrderTaskId(),other.getSourceOrderTaskId())
			.append(getTargetOrderTaskId(),other.getTargetOrderTaskId())
			.append(getTargetPlanbom(),other.getTargetPlanbom())
			.append(getMoveNum(),other.getMoveNum())
			.append(getCreateId(),other.getCreateId())
			.append(getCreateDate(),other.getCreateDate())
			.append(getCreateUsername(),other.getCreateUsername())
			.isEquals();
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
