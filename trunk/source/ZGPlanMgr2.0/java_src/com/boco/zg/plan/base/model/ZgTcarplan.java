/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import java.util.List;

import javacommon.base.BaseEntity;
import javacommon.base.model.EnumModel;
import javacommon.base.model.RelatedModel;
import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTcarplan extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_CARPLAN";
	public static final String BM_CLASS_ID = "ZG_T_CARPLAN";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "单据编号";
	public static final String ALIAS_LABEL_CN = "名称属性";
	public static final String ALIAS_CAR_STATE = "状态";
	public static final String ALIAS_CREATE_USER_ID = "创建人ID";
	public static final String ALIAS_CREATE_DATE = "单据日期";
	public static final String ALIAS_CAR_USER = "领料人";
	public static final String ALIAS_CAR_USER_ID = "领料人编码";
	public static final String ALIAS_CAR_DATE = "领料时间";
	public static final String ALIAS_STORAGE_USER_ID = "仓管员ID";
	public static final String ALIAS_STORAGE_ID = "仓库编码";
	public static final String ALIAS_STORAGE_LABLECN = "仓库名称";
	public static final String ALIAS_REMARK = "备注";
	public static final String CREATE_USER_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_0_1.t1_";
	public static final String CREATE_USER_ID_FW_EMPLOYEE_FW_EMPLOYEE = "t0_0_1.t0_";
	public static final String CAR_USER_FW_EMPLOYEE_FW_OPERATOR = "t0_1_2.t1_";
	public static final String CAR_USER_FW_EMPLOYEE_FW_EMPLOYEE = "t0_1_2.t0_";
	public static final String STORAGE_USER_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_2_3.t1_";
	public static final String STORAGE_USER_ID_FW_EMPLOYEE_FW_EMPLOYEE = "t0_2_3.t0_";
	public static final String STORAGE_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_3_1.t0_";
	public static final String ALIAS_CARUSER_DEPT_ID="部门编码";
	public static final String ALIAS_CARUSER_DEPT_LABELCN="领料组";
	
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	public static final String FORMAT_CAR_DATE = DATE_FORMAT;
	
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
	private java.lang.String carState;
	
	private EnumModel carState_enum = new EnumModel("CAR_PLAN_STATE");
	private java.lang.String createUserId_labelCn;
	private RelatedModel createUserId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String createUserId;
	private java.lang.String createUserName;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private java.lang.String carUser_labelCn;
	private java.lang.String carUserName;
	private RelatedModel carUser_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String carUser;
	private java.util.Date carDate_start;
	private java.util.Date carDate_end;
	private java.util.Date carDate;
	private java.lang.String storageUserId_labelCn;
	private java.lang.String storageUserName;
	private RelatedModel storageUserId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String storageUserId;
	private java.lang.String storageId_labelCn;
	private RelatedModel storageId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String storageId;
	private java.lang.String remark;
	private String type;
	private String orderPlanType;
	
	private String carUser_dept_Id;
	private String carUser_dept_labelCn;
	private RelatedModel carUser_dept_Id_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private String sortf;
	private String departmentId;
	
	private String carId;
	
	private String isManul;
	
	private String aufnr;

	
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
	public java.lang.String getCarState() {
		return this.carState;
	}
	
	public void setCarState(java.lang.String value) {
		this.carState = value;
	}
	public java.lang.String getCreateUserId_labelCn() {
		return this.createUserId_labelCn;
	}
	
	public void setCreateUserId_labelCn(java.lang.String value) {
		this.createUserId_labelCn = value;
	}
	
	public RelatedModel getCreateUserId_related() {
		return this.createUserId_related;
	}
	
	public void setCreateUserId_related(RelatedModel value) {
		this.createUserId_related = value;
	}
	public java.lang.String getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.String value) {
		this.createUserId = value;
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
	public java.lang.String getCarUser_labelCn() {
		return this.carUser_labelCn;
	}
	
	public void setCarUser_labelCn(java.lang.String value) {
		this.carUser_labelCn = value;
	}
	
	public RelatedModel getCarUser_related() {
		return this.carUser_related;
	}
	
	public void setCarUser_related(RelatedModel value) {
		this.carUser_related = value;
	}
	public java.lang.String getCarUser() {
		return this.carUser;
	}
	
	public void setCarUser(java.lang.String value) {
		this.carUser = value;
	}
	public String getCarDateString() {
		return date2String(getCarDate(), FORMAT_CAR_DATE);
	}
	public void setCarDateString(String value) {
		setCarDate(string2Date(value, FORMAT_CAR_DATE,java.util.Date.class));
	}
	public java.util.Date getCarDate_start() {
		return this.carDate_start;
	}
	
	public void setCarDate_start(java.util.Date value) {
		this.carDate_start = value;
	}
	
	public java.util.Date getCarDate_end() {
		return this.carDate_end;
	}
	
	public void setCarDate_end(java.util.Date value) {
		this.carDate_end = value;
	}
	public java.util.Date getCarDate() {
		return this.carDate;
	}
	
	public void setCarDate(java.util.Date value) {
		this.carDate = value;
	}
	public java.lang.String getStorageUserId_labelCn() {
		return this.storageUserId_labelCn;
	}
	
	public void setStorageUserId_labelCn(java.lang.String value) {
		this.storageUserId_labelCn = value;
	}
	
	public RelatedModel getStorageUserId_related() {
		return this.storageUserId_related;
	}
	
	public void setStorageUserId_related(RelatedModel value) {
		this.storageUserId_related = value;
	}
	public java.lang.String getStorageUserId() {
		return this.storageUserId;
	}
	
	public void setStorageUserId(java.lang.String value) {
		this.storageUserId = value;
	}
	public java.lang.String getStorageId_labelCn() {
		return this.storageId_labelCn;
	}
	
	public void setStorageId_labelCn(java.lang.String value) {
		this.storageId_labelCn = value;
	}
	
	public RelatedModel getStorageId_related() {
		return this.storageId_related;
	}
	
	public void setStorageId_related(RelatedModel value) {
		this.storageId_related = value;
	}
	public java.lang.String getStorageId() {
		return this.storageId;
	}
	
	public void setStorageId(java.lang.String value) {
		this.storageId = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("CarState",getCarState())
			.append("CreateUserId",getCreateUserId())
			.append("CreateDate",getCreateDate())
			.append("CarUser",getCarUser())
			.append("CarDate",getCarDate())
			.append("StorageUserId",getStorageUserId())
			.append("StorageId",getStorageId())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getCarState())
			.append(getCreateUserId())
			.append(getCreateDate())
			.append(getCarUser())
			.append(getCarDate())
			.append(getStorageUserId())
			.append(getStorageId())
			.append(getRemark())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTcarplan == false) return false;
		if(this == obj) return true;
		ZgTcarplan other = (ZgTcarplan)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getCarState(),other.getCarState())
			.append(getCreateUserId(),other.getCreateUserId())
			.append(getCreateDate(),other.getCreateDate())
			.append(getCarUser(),other.getCarUser())
			.append(getCarDate(),other.getCarDate())
			.append(getStorageUserId(),other.getStorageUserId())
			.append(getStorageId(),other.getStorageId())
			.append(getRemark(),other.getRemark())
			.isEquals();
	}

	public String getCarUser_dept_Id() {
		return carUser_dept_Id;
	}

	public void setCarUser_dept_Id(String carUser_dept_Id) {
		this.carUser_dept_Id = carUser_dept_Id;
	}

	public String getCarUser_dept_labelCn() {
		return carUser_dept_labelCn;
	}

	public void setCarUser_dept_labelCn(String carUser_dept_labelCn) {
		this.carUser_dept_labelCn = carUser_dept_labelCn;
	}

	public RelatedModel getCarUser_dept_Id_related() {
		return carUser_dept_Id_related;
	}

	public void setCarUser_dept_Id_related(RelatedModel carUser_dept_Id_related) {
		this.carUser_dept_Id_related = carUser_dept_Id_related;
	}

	public EnumModel getCarState_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("CAR_PLAN_STATE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getCarState())){
				this.carState_enum.setValue(value.getName());
				this.carState_enum.setId(this.getCarState());
			}
		}
		return this.carState_enum;
	}

	public void setCarState_enum(EnumModel carState_enum) {
		this.carState_enum = carState_enum;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getSortf() {
		return sortf;
	}

	public void setSortf(String sortf) {
		this.sortf = sortf;
	}

	public String getOrderPlanType() {
		return orderPlanType;
	}

	public void setOrderPlanType(String orderPlanType) {
		this.orderPlanType = orderPlanType;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getIsManul() {
		return isManul;
	}

	public void setIsManul(String isManul) {
		this.isManul = isManul;
	}

	public String getAufnr() {
		return aufnr;
	}

	public void setAufnr(String aufnr) {
		this.aufnr = aufnr;
	}

	/**
	 * @return the createUserName
	 */
	public java.lang.String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(java.lang.String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the carUserName
	 */
	public java.lang.String getCarUserName() {
		return carUserName;
	}

	/**
	 * @param carUserName the carUserName to set
	 */
	public void setCarUserName(java.lang.String carUserName) {
		this.carUserName = carUserName;
	}

	/**
	 * @return the storageUserName
	 */
	public java.lang.String getStorageUserName() {
		return storageUserName;
	}

	/**
	 * @param storageUserName the storageUserName to set
	 */
	public void setStorageUserName(java.lang.String storageUserName) {
		this.storageUserName = storageUserName;
	}
}
