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


public class ZgTorderTaskbom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_TASKBOM";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_TASKBOM";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_ORDER_TASK_ID = "订单任务表ID";
	public static final String ALIAS_ORDER_BOM_ID = "订单BOM表ID";
	public static final String ALIAS_MENGE = "需求数量";
	public static final String ORDER_TASK_ID_ZG_T_ORDER_TASK_ZG_T_ORDER_TASK = "t0_0_1.t0_";
	public static final String ORDER_BOM_ID_ZG_T_ORDERBOM_ZG_T_ORDERBOM = "t0_1_1.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getOrderTaskId()==null?"":this.getOrderTaskId().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String orderTaskId_labelCn;
	private RelatedModel orderTaskId_related = new RelatedModel("ZG_T_ORDER_TASK","CUID","LABEL_CN");
	private java.lang.String orderTaskId;
	private java.lang.String orderBomId_labelCn;
	private RelatedModel orderBomId_related = new RelatedModel("ZG_T_ORDERBOM","CUID","LABEL_CN");
	private java.lang.String orderBomId;
	private java.lang.Double menge;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getOrderTaskId_labelCn() {
		return this.orderTaskId_labelCn;
	}
	
	public void setOrderTaskId_labelCn(java.lang.String value) {
		this.orderTaskId_labelCn = value;
	}
	
	public RelatedModel getOrderTaskId_related() {
		return this.orderTaskId_related;
	}
	
	public void setOrderTaskId_related(RelatedModel value) {
		this.orderTaskId_related = value;
	}
	public java.lang.String getOrderTaskId() {
		return this.orderTaskId;
	}
	
	public void setOrderTaskId(java.lang.String value) {
		this.orderTaskId = value;
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
	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OrderTaskId",getOrderTaskId())
			.append("OrderBomId",getOrderBomId())
			.append("Menge",getMenge())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOrderTaskId())
			.append(getOrderBomId())
			.append(getMenge())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderTaskbom == false) return false;
		if(this == obj) return true;
		ZgTorderTaskbom other = (ZgTorderTaskbom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOrderTaskId(),other.getOrderTaskId())
			.append(getOrderBomId(),other.getOrderBomId())
			.append(getMenge(),other.getMenge())
			.isEquals();
	}

	/**
	 * @return the menge
	 */
	public java.lang.Double getMenge() {
		return menge;
	}

	/**
	 * @param menge the menge to set
	 */
	public void setMenge(java.lang.Double menge) {
		this.menge = menge;
	}
}
