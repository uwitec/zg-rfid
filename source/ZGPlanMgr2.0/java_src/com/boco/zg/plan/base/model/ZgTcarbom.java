/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTcarbom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_CARBOM";
	public static final String BM_CLASS_ID = "ZG_T_CARBOM";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_ORDER_BOM_ID = "orderBomId";
	public static final String ALIAS_ORDER_ID = "orderId";
	public static final String ALIAS_PLAN_NUM = "planNum";
	public static final String ALIAS_REAL_NUM = "realNum";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_CAR_PLAN_ID = "carPlanId";
	public static final String ALIAS_ORDER_PLANBOM_ID = "orderPlanbomId";
	//date formats
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String taskBomId;
	private java.lang.String orderTaskId;
	private java.lang.Double planNum;
	private java.lang.Double realNum;
	private java.lang.String labelCn;
	private java.lang.String carPlanId;
	private java.lang.String orderPlanbomId;
	private Long oldRealNum;
	private long oldPlanNum;
	private String storageUserId;
	private String storageUserName;
	private java.util.Date carDate;
	
	

	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	/**
	 * @return the planNum
	 */
	public java.lang.Double getPlanNum() {
		return planNum;
	}

	/**
	 * @param planNum the planNum to set
	 */
	public void setPlanNum(java.lang.Double planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return the realNum
	 */
	public java.lang.Double getRealNum() {
		return realNum;
	}

	/**
	 * @param realNum the realNum to set
	 */
	public void setRealNum(java.lang.Double realNum) {
		this.realNum = realNum;
	}

	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getCarPlanId() {
		return this.carPlanId;
	}
	
	public void setCarPlanId(java.lang.String value) {
		this.carPlanId = value;
	}
	public java.lang.String getOrderPlanbomId() {
		return this.orderPlanbomId;
	}
	
	public void setOrderPlanbomId(java.lang.String value) {
		this.orderPlanbomId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OrderBomId",getTaskBomId())
			.append("OrderId",getOrderTaskId())
			.append("PlanNum",getPlanNum())
			.append("RealNum",getRealNum())
			.append("LabelCn",getLabelCn())
			.append("CarPlanId",getCarPlanId())
			.append("OrderPlanbomId",getOrderPlanbomId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getTaskBomId())
			.append(getOrderTaskId())
			.append(getPlanNum())
			.append(getRealNum())
			.append(getLabelCn())
			.append(getCarPlanId())
			.append(getOrderPlanbomId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTcarbom == false) return false;
		if(this == obj) return true;
		ZgTcarbom other = (ZgTcarbom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append("OrderBomId",getTaskBomId())
			.append("OrderId",getOrderTaskId())
			.append(getPlanNum(),other.getPlanNum())
			.append(getRealNum(),other.getRealNum())
			.append(getLabelCn(),other.getLabelCn())
			.append(getCarPlanId(),other.getCarPlanId())
			.append(getOrderPlanbomId(),other.getOrderPlanbomId())
			.isEquals();
	}

	public Long getOldRealNum() {
		return oldRealNum;
	}

	public void setOldRealNum(Long oldRealNum) {
		this.oldRealNum = oldRealNum;
	}

	public long getOldPlanNum() {
		return oldPlanNum;
	}

	public void setOldPlanNum(long oldPlanNum) {
		this.oldPlanNum = oldPlanNum;
	}

	public String getStorageUserId() {
		return storageUserId;
	}

	public void setStorageUserId(String storageUserId) {
		this.storageUserId = storageUserId;
	}

	public java.util.Date getCarDate() {
		return carDate;
	}

	public void setCarDate(java.util.Date carDate) {
		this.carDate = carDate;
	}

	/**
	 * @return the taskBomId
	 */
	public java.lang.String getTaskBomId() {
		return taskBomId;
	}

	/**
	 * @param taskBomId the taskBomId to set
	 */
	public void setTaskBomId(java.lang.String taskBomId) {
		this.taskBomId = taskBomId;
	}

	/**
	 * @return the orderTaskId
	 */
	public java.lang.String getOrderTaskId() {
		return orderTaskId;
	}

	/**
	 * @param orderTaskId the orderTaskId to set
	 */
	public void setOrderTaskId(java.lang.String orderTaskId) {
		this.orderTaskId = orderTaskId;
	}

	/**
	 * @return the storageUserName
	 */
	public String getStorageUserName() {
		return storageUserName;
	}

	/**
	 * @param storageUserName the storageUserName to set
	 */
	public void setStorageUserName(String storageUserName) {
		this.storageUserName = storageUserName;
	}
}
