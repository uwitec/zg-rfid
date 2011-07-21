package com.boco.frame.sys.base.model;

import java.util.List;

import javacommon.base.BaseEntity;
import javacommon.base.model.RelatedModel;

public class FwEmployeeOrganazation extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_EMPLOYEE_ORG";
	public static final String BM_CLASS_ID = "FW_EMPLOYEE_ORG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_EMPLOYEE_ID = "雇员ID";
	public static final String ALIAS_ORG_ID = "部门ID";
	public static final String ALIAS_ORG_Name = "部门";
	
	public static final String EMPLOYEE_ID_FW_EMPLOYEE_FW_EMPLOYEE = "t0_0_1.t0_";
	public static final String ORG_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_1_1.t0_";
	//date formats
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private String cuid;
	private String e_labelCn;
	private RelatedModel employeeId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String employeeId;
	private java.lang.String orgId_labelCn;
	private RelatedModel orgId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private String orgId;
	
	public FwEmployeeOrganazation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FwEmployeeOrganazation(String cuid, String cn,
			RelatedModel employeeId_related, String employeeId,
			String orgId_labelCn, RelatedModel orgId_related, String roleId) {
		super();
		this.cuid = cuid;
		e_labelCn = cn;
		this.employeeId_related = employeeId_related;
		this.employeeId = employeeId;
		orgId_labelCn = orgId_labelCn;
		this.orgId_related = orgId_related;
		this.orgId = orgId;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getE_labelCn() {
		return e_labelCn;
	}

	public void setE_labelCn(String cn) {
		e_labelCn = cn;
	}

	public RelatedModel getEmployeeId_related() {
		return employeeId_related;
	}

	public void setEmployeeId_related(RelatedModel employeeId_related) {
		this.employeeId_related = employeeId_related;
	}

	public java.lang.String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(java.lang.String employeeId) {
		this.employeeId = employeeId;
	}

	public RelatedModel getOrgId_related() {
		return orgId_related;
	}

	public void setOrgId_related(RelatedModel orgId_related) {
		this.orgId_related = orgId_related;
	}

	public java.lang.String getOrgId_labelCn() {
		return orgId_labelCn;
	}

	public void setOrgId_labelCn(java.lang.String orgId_labelCn) {
		this.orgId_labelCn = orgId_labelCn;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	
	
	
	
	
	
	
	

}
