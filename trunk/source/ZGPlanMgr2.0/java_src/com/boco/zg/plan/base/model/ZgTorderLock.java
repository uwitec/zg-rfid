/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderLock extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_LOCK";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_LOCK";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_AUFNR = "AUFNR";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getAufnr()==null?"":this.getAufnr().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String aufnr;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getAufnr() {
		return this.aufnr;
	}
	
	public void setAufnr(java.lang.String value) {
		this.aufnr = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Aufnr",getAufnr())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getAufnr())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderLock == false) return false;
		if(this == obj) return true;
		ZgTorderLock other = (ZgTorderLock)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getAufnr(),other.getAufnr())
			.isEquals();
	}
}
