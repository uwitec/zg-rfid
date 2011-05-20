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

import com.sun.corba.se.spi.orbutil.fsm.State;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderTask extends BaseEntity {
	public static String deletState="-1";//删除状态
	public static String numalState="0";
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_TASK";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_TASK";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_ORDER_ID = "订单表ID";
	public static final String ALIAS_ARBPL = "生产线";
	public static final String ALIAS_PX_DATE = "排序日期";
	public static final String ALIAS_POSKEY = "POSKEY";
	public static final String ALIAS_PLANT = "生产厂";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
	//date formats
	public static final String FORMAT_PX_DATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getArbpl()==null?"":this.getArbpl().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;
	private java.lang.String arbpl;
	private java.util.Date pxDate_start;
	private java.util.Date pxDate_end;
	private java.util.Date pxDate;
	private java.lang.String poskey;
	private java.lang.String plant;
	private String taskState;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
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
	public java.lang.String getArbpl() {
		return this.arbpl;
	}
	
	public void setArbpl(java.lang.String value) {
		this.arbpl = value;
	}
	public String getPxDateString() {
		return date2String(getPxDate(), FORMAT_PX_DATE);
	}
	public void setPxDateString(String value) {
		setPxDate(string2Date(value, FORMAT_PX_DATE,java.util.Date.class));
	}
	public java.util.Date getPxDate_start() {
		return this.pxDate_start;
	}
	
	public void setPxDate_start(java.util.Date value) {
		this.pxDate_start = value;
	}
	
	public java.util.Date getPxDate_end() {
		return this.pxDate_end;
	}
	
	public void setPxDate_end(java.util.Date value) {
		this.pxDate_end = value;
	}
	public java.util.Date getPxDate() {
		return this.pxDate;
	}
	
	public void setPxDate(java.util.Date value) {
		this.pxDate = value;
	}
	public java.lang.String getPoskey() {
		return this.poskey;
	}
	
	public void setPoskey(java.lang.String value) {
		this.poskey = value;
	}
	public java.lang.String getPlant() {
		return this.plant;
	}
	
	public void setPlant(java.lang.String value) {
		this.plant = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OrderId",getOrderId())
			.append("Arbpl",getArbpl())
			.append("PxDate",getPxDate())
			.append("Poskey",getPoskey())
			.append("Plant",getPlant())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOrderId())
			.append(getArbpl())
			.append(getPxDate())
			.append(getPoskey())
			.append(getPlant())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderTask == false) return false;
		if(this == obj) return true;
		ZgTorderTask other = (ZgTorderTask)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOrderId(),other.getOrderId())
			.append(getArbpl(),other.getArbpl())
			.append(getPxDate(),other.getPxDate())
			.append(getPoskey(),other.getPoskey())
			.append(getPlant(),other.getPlant())
			.isEquals();
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
}
