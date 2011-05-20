package com.boco.zg.virtualorg.base.model;

import javacommon.base.BaseEntity;

public class ZgTvirtualorgEx extends BaseEntity {

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;//虚拟组名字
	private java.lang.String orgId;//所属机构ID
	private java.lang.String orgName;//所属机构名字,即部门名字
	private java.lang.String note;
	private java.lang.String type;
	
	private java.lang.String userId;//领料人ID
	private java.lang.String userName;
	private java.lang.String sex;
	private java.lang.String mobile;
	private java.lang.String email;
	
	private java.lang.String zgTvirtualorgEmployeeCuid;//领料组领料人关系表 主键

	public java.lang.String getCuid() {
		return cuid;
	}

	public void setCuid(java.lang.String cuid) {
		this.cuid = cuid;
	}

	public java.lang.String getLabelCn() {
		return labelCn;
	}

	public void setLabelCn(java.lang.String labelCn) {
		this.labelCn = labelCn;
	}

	public java.lang.String getOrgId() {
		return orgId;
	}

	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getZgTvirtualorgEmployeeCuid() {
		return zgTvirtualorgEmployeeCuid;
	}

	public void setZgTvirtualorgEmployeeCuid(
			java.lang.String zgTvirtualorgEmployeeCuid) {
		this.zgTvirtualorgEmployeeCuid = zgTvirtualorgEmployeeCuid;
	}
}
