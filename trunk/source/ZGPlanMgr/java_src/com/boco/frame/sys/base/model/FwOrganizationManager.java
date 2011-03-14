package com.boco.frame.sys.base.model;

import java.util.Date;

import javacommon.base.BaseEntity;

public class FwOrganizationManager extends BaseEntity {
	
	//base start
	private String cuid;
	private String orgId;
	private String employeeId;
	private String employeeName;
	private String orgName;
	private Date extend2;
	//base end
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getExtend2() {
		return extend2;
	}
	public void setExtend2(Date extend2) {
		this.extend2 = extend2;
	}
	

}
