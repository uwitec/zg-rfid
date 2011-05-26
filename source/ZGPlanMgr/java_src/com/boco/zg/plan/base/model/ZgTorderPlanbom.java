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


public class ZgTorderPlanbom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_PLANBOM";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_PLANBOM";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "领料计划ID";
	public static final String ALIAS_LABEL_CN = "名称属性";
	public static final String ALIAS_ORDER_PLAN_ID = "领料计划ID";
	public static final String ALIAS_ORDER_BOM_ID = "订单BOMID";
	public static final String ALIAS_ORDER_ID = "订单ID";
	public static final String ALIAS_DEPARTMENT_ID = "领料组ID";
	public static final String ALIAS_USER_ID = "领料人ID";
	public static final String ALIAS_PLAN_DATE = "领料日期";
	public static final String ALIAS_PLAN_START_TIME = "开始时间";
	public static final String ALIAS_PLAN_END_TIME = "结束时间";
	public static final String ALIAS_STATE = "计划表状态";
	public static final String ALIAS_COMPLETE_NUM = "已经领取数量";
	public static final String ALIAS_CAR_NUM = "装车计划数量";
	public static final String ORDER_BOM_ID_ZG_T_ORDERBOM_ZG_T_ORDERBOM = "t0_0_1.t0_";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_1_1.t0_";
	public static final String DEPARTMENT_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_2_1.t0_";
	public static final String USER_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_3_1.t1_";
	public static final String USER_ID_FW_EMPLOYEE_FW_EMPLOYEE = "t0_3_1.t0_";
	//date formats
	public static final String FORMAT_PLAN_DATE = DATE_FORMAT;
	
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
	private java.lang.String orderPlanId;
	private java.lang.String orderBomId_labelCn;
	private RelatedModel orderBomId_related = new RelatedModel("ZG_T_ORDERBOM","CUID","LABEL_CN");
	private java.lang.String orderBomId;
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;
	private java.lang.String departmentId_labelCn;
	private RelatedModel departmentId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String departmentId;
	private java.lang.String userId_labelCn;
	private RelatedModel userId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String userId;
	private java.util.Date planDate_start;
	private java.util.Date planDate_end;
	private java.util.Date planDate;
	private java.lang.String planStartTime;
	private java.lang.String planEndTime;
	private EnumModel state_enum = new EnumModel("ORDER_STATE");
	private java.lang.String state;
	private java.lang.Long completeNum;
	private java.lang.Long carNum;
	private java.lang.Long planNum;
	private java.lang.String bomId;
	private java.lang.String zbz;
	private java.lang.Long auditNum;
	private Long moveNum;
	private Long backNum;
	private Long outNum;
	private Long moveNumIn;
	private Long waitBackNum;
	private Long storageNum;
	private String taskBomId;
	private String orderTaskId;
	
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
	public java.lang.String getOrderPlanId() {
		return orderPlanId;
	}

	public void setOrderPlanId(java.lang.String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}

	public java.lang.String getOrderBomId_labelCn() {
		return this.orderBomId_labelCn;
	}
	
	public void setOrderBomId_labelCn(java.lang.String value) {
		this.orderBomId_labelCn = value;
	}
	
	public RelatedModel getOrderBomId_related() {
		return this.orderBomId_related;
	}
	
	public void setOrderBomId_related(RelatedModel value) {
		this.orderBomId_related = value;
	}
	public java.lang.String getOrderBomId() {
		return this.orderBomId;
	}
	
	public void setOrderBomId(java.lang.String value) {
		this.orderBomId = value;
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
	public java.lang.String getDepartmentId_labelCn() {
		return this.departmentId_labelCn;
	}
	
	public void setDepartmentId_labelCn(java.lang.String value) {
		this.departmentId_labelCn = value;
	}
	
	public RelatedModel getDepartmentId_related() {
		return this.departmentId_related;
	}
	
	public void setDepartmentId_related(RelatedModel value) {
		this.departmentId_related = value;
	}
	public java.lang.String getDepartmentId() {
		return this.departmentId;
	}
	
	public void setDepartmentId(java.lang.String value) {
		this.departmentId = value;
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
	
	public String getPlanStartTime() {
		return this.planStartTime;
	}
	
	public void setPlanStartTime(String value) {
		this.planStartTime = value;
	}
	
	public String getPlanEndTime() {
		return this.planEndTime;
	}
	
	public void setPlanEndTime(String value) {
		this.planEndTime = value;
	}
	
	public EnumModel getState_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("ORDER_STATE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getState())){
				this.state_enum.setValue(value.getName());
				this.state_enum.setId(this.getState());
			}
		}
		return this.state_enum;
	}
	
	public void setState_enum(EnumModel value) {
		this.state_enum = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.Long getCompleteNum() {
		return this.completeNum==null?0l:this.completeNum;
	}
	
	public void setCompleteNum(java.lang.Long value) {
		this.completeNum = value;
	}
	public java.lang.Long getCarNum() {
		return this.carNum==null?0l:this.carNum;
	}
	
	public void setCarNum(java.lang.Long value) {
		this.carNum = value;
	}

	public java.lang.Long getPlanNum() {
		return planNum==null?0l:this.planNum;
	}

	public void setPlanNum(java.lang.Long planNum) {
		this.planNum = planNum;
	}

	public java.lang.String getBomId() {
		return bomId;
	}

	public void setBomId(java.lang.String bomId) {
		this.bomId = bomId;
	}
	
	public java.lang.String getZbz() {
		return zbz;
	}

	public void setZbz(java.lang.String zbz) {
		this.zbz = zbz;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrderBomId",getOrderBomId())
			.append("OrderId",getOrderId())
			.append("DepartmentId",getDepartmentId())
			.append("UserId",getUserId())
			.append("PlanDate",getPlanDate())
			.append("PlanStartTime",getPlanStartTime())
			.append("PlanEndTime",getPlanEndTime())
			.append("State",getState())
			.append("CompleteNum",getCompleteNum())
			.append("CarNum",getCarNum())
			.append("PlanNum",getPlanNum())
			.append("BomId",getBomId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getOrderBomId())
			.append(getOrderId())
			.append(getDepartmentId())
			.append(getUserId())
			.append(getPlanDate())
			.append(getPlanStartTime())
			.append(getPlanEndTime())
			.append(getState())
			.append(getCompleteNum())
			.append(getCarNum())
			.append(getPlanNum())
			.append(getBomId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderPlanbom == false) return false;
		if(this == obj) return true;
		ZgTorderPlanbom other = (ZgTorderPlanbom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOrderBomId(),other.getOrderBomId())
			.append(getOrderId(),other.getOrderId())
			.append(getDepartmentId(),other.getDepartmentId())
			.append(getUserId(),other.getUserId())
			.append(getPlanDate(),other.getPlanDate())
			.append(getPlanStartTime(),other.getPlanStartTime())
			.append(getPlanEndTime(),other.getPlanEndTime())
			.append(getState(),other.getState())
			.append(getCompleteNum(),other.getCompleteNum())
			.append(getCarNum(),other.getCarNum())
			.append(getPlanNum(),other.getPlanNum())
			.append(getBomId(),other.getBomId())
			.isEquals();
	}

	public java.lang.Long getAuditNum() {
		return auditNum;
	}

	public void setAuditNum(java.lang.Long auditNum) {
		this.auditNum = auditNum;
	}

	public Long getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(Long moveNum) {
		this.moveNum = moveNum;
	}

	public Long getBackNum() {
		return backNum;
	}

	public void setBackNum(Long backNum) {
		this.backNum = backNum;
	}

	public Long getOutNum() {
		return outNum;
	}

	public void setOutNum(Long outNum) {
		this.outNum = outNum;
	}

	public Long getMoveNumIn() {
		return moveNumIn;
	}

	public void setMoveNumIn(Long moveNumIn) {
		this.moveNumIn = moveNumIn;
	}

	public Long getWaitBackNum() {
		return waitBackNum;
	}

	public void setWaitBackNum(Long waitBackNum) {
		this.waitBackNum = waitBackNum;
	}

	public Long getStorageNum() {
		return storageNum;
	}

	public void setStorageNum(Long storageNum) {
		this.storageNum = storageNum;
	}

	public String getTaskBomId() {
		return taskBomId;
	}

	public void setTaskBomId(String taskBomId) {
		this.taskBomId = taskBomId;
	}

	public String getOrderTaskId() {
		return orderTaskId;
	}

	public void setOrderTaskId(String orderTaskId) {
		this.orderTaskId = orderTaskId;
	}

}
