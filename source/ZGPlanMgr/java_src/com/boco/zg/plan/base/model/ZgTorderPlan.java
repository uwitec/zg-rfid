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


public class ZgTorderPlan extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_PLAN";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_PLAN";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "领料计划ID";
	public static final String ALIAS_LABEL_CN = "名称属性";
	public static final String ALIAS_ORDER_ID = "订单ID";
	public static final String ALIAS_DEPARTMENU_ID = "领料组ID";
	public static final String ALIAS_USER_ID = "领料人ID";
	public static final String ALIAS_PLAN_DATE = "领料日期";
	public static final String ALIAS_PLAN_START_TIME = "开始时间";
	public static final String ALIAS_PLAN_END_TIME = "结束时间";
	public static final String ALIAS_STATE = "计划表状态";
	public static final String ALIAS_PLAN_TYPE = "计划类型";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
	public static final String DEPARTMENU_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_1_1.t0_";
	public static final String USER_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_2_1.t1_";
	public static final String USER_ID_FW_EMPLOYEE_FW_EMPLOYEE = "t0_2_1.t0_";
	//date formats
	public static final String FORMAT_PLAN_DATE = DATE_FORMAT;
	public static final String FORMAT_PLAN_START_TIME = DATE_FORMAT;
	public static final String FORMAT_PLAN_END_TIME = DATE_FORMAT;
	
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
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;
	private java.lang.String departmenuId_labelCn;
	private RelatedModel departmenuId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String departmenuId;
	private java.lang.String userId_labelCn;
	private RelatedModel userId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String userId;
	private java.util.Date planDate_start;
	private java.util.Date planDate_end;
	private java.util.Date planDate;
	private java.util.Date planStartTime_start;
	private java.util.Date planStartTime_end;
	private java.util.Date planStartTime;
	private java.util.Date planEndTime_start;
	private java.util.Date planEndTime_end;
	private java.util.Date planEndTime;
	private java.lang.String state;
	private java.lang.String planType;
	private java.lang.String plant;
	private Double percent;
	private String isManul;
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
	public java.lang.String getOrderId_labelCn() {
		return this.orderId_labelCn;
	}
	
	public void setOrderId_labelCn(java.lang.String value) {
		this.orderId_labelCn = value;
	}
	
	public RelatedModel getOrderId_related() {
		return this.orderId_related;
	}
	
	public void setOrderId_related(RelatedModel value) {
		this.orderId_related = value;
	}
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	public java.lang.String getDepartmenuId_labelCn() {
		return this.departmenuId_labelCn;
	}
	
	public void setDepartmenuId_labelCn(java.lang.String value) {
		this.departmenuId_labelCn = value;
	}
	
	public RelatedModel getDepartmenuId_related() {
		return this.departmenuId_related;
	}
	
	public void setDepartmenuId_related(RelatedModel value) {
		this.departmenuId_related = value;
	}
	public java.lang.String getDepartmenuId() {
		return this.departmenuId;
	}
	
	public void setDepartmenuId(java.lang.String value) {
		this.departmenuId = value;
	}
	public java.lang.String getUserId_labelCn() {
		return this.userId_labelCn;
	}
	
	public void setUserId_labelCn(java.lang.String value) {
		this.userId_labelCn = value;
	}
	
	public RelatedModel getUserId_related() {
		return this.userId_related;
	}
	
	public void setUserId_related(RelatedModel value) {
		this.userId_related = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public String getPlanDateString() {
		return date2String(getPlanDate(), FORMAT_PLAN_DATE);
	}
	public void setPlanDateString(String value) {
		setPlanDate(string2Date(value, FORMAT_PLAN_DATE,java.util.Date.class));
	}
	public java.util.Date getPlanDate_start() {
		return this.planDate_start;
	}
	
	public void setPlanDate_start(java.util.Date value) {
		this.planDate_start = value;
	}
	
	public java.util.Date getPlanDate_end() {
		return this.planDate_end;
	}
	
	public void setPlanDate_end(java.util.Date value) {
		this.planDate_end = value;
	}
	public java.util.Date getPlanDate() {
		return this.planDate;
	}
	
	public void setPlanDate(java.util.Date value) {
		this.planDate = value;
	}
	public String getPlanStartTimeString() {
		return date2String(getPlanStartTime(), FORMAT_PLAN_START_TIME);
	}
	public void setPlanStartTimeString(String value) {
		setPlanStartTime(string2Date(value, FORMAT_PLAN_START_TIME,java.util.Date.class));
	}
	public java.util.Date getPlanStartTime_start() {
		return this.planStartTime_start;
	}
	
	public void setPlanStartTime_start(java.util.Date value) {
		this.planStartTime_start = value;
	}
	
	public java.util.Date getPlanStartTime_end() {
		return this.planStartTime_end;
	}
	
	public void setPlanStartTime_end(java.util.Date value) {
		this.planStartTime_end = value;
	}
	public java.util.Date getPlanStartTime() {
		return this.planStartTime;
	}
	
	public void setPlanStartTime(java.util.Date value) {
		this.planStartTime = value;
	}
	public String getPlanEndTimeString() {
		return date2String(getPlanEndTime(), FORMAT_PLAN_END_TIME);
	}
	public void setPlanEndTimeString(String value) {
		setPlanEndTime(string2Date(value, FORMAT_PLAN_END_TIME,java.util.Date.class));
	}
	public java.util.Date getPlanEndTime_start() {
		return this.planEndTime_start;
	}
	
	public void setPlanEndTime_start(java.util.Date value) {
		this.planEndTime_start = value;
	}
	
	public java.util.Date getPlanEndTime_end() {
		return this.planEndTime_end;
	}
	
	public void setPlanEndTime_end(java.util.Date value) {
		this.planEndTime_end = value;
	}
	public java.util.Date getPlanEndTime() {
		return this.planEndTime;
	}
	
	public void setPlanEndTime(java.util.Date value) {
		this.planEndTime = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getPlanType() {
		return this.planType;
	}
	
	public void setPlanType(java.lang.String value) {
		this.planType = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrderId",getOrderId())
			.append("DepartmenuId",getDepartmenuId())
			.append("UserId",getUserId())
			.append("PlanDate",getPlanDate())
			.append("PlanStartTime",getPlanStartTime())
			.append("PlanEndTime",getPlanEndTime())
			.append("State",getState())
			.append("PlanType",getPlanType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getOrderId())
			.append(getDepartmenuId())
			.append(getUserId())
			.append(getPlanDate())
			.append(getPlanStartTime())
			.append(getPlanEndTime())
			.append(getState())
			.append(getPlanType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderPlan == false) return false;
		if(this == obj) return true;
		ZgTorderPlan other = (ZgTorderPlan)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOrderId(),other.getOrderId())
			.append(getDepartmenuId(),other.getDepartmenuId())
			.append(getUserId(),other.getUserId())
			.append(getPlanDate(),other.getPlanDate())
			.append(getPlanStartTime(),other.getPlanStartTime())
			.append(getPlanEndTime(),other.getPlanEndTime())
			.append(getState(),other.getState())
			.append(getPlanType(),other.getPlanType())
			.isEquals();
	}

	public java.lang.String getPlant() {
		return plant;
	}

	public void setPlant(java.lang.String plant) {
		this.plant = plant;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getIsManul() {
		return isManul;
	}

	public void setIsManul(String isManul) {
		this.isManul = isManul;
	}
}
