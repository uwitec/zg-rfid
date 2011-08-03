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


public class ZgTunit extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_UNIT";
	public static final String BM_CLASS_ID = "ZG_T_UNIT";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_MEINS = "MEINS";
	public static final String ALIAS_MSEHL = "MSEHL";
	public static final String ALIAS_CUID = "CUID";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getMsehl()==null?"":this.getMsehl().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String meins;
	private java.lang.String msehl;
	private java.lang.String cuid;
	//columns END
	public java.lang.String getMeins() {
		return this.meins;
	}
	
	public void setMeins(java.lang.String value) {
		this.meins = value;
	}
	public java.lang.String getMsehl() {
		return this.msehl;
	}
	
	public void setMsehl(java.lang.String value) {
		this.msehl = value;
	}
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Meins",getMeins())
			.append("Msehl",getMsehl())
			.append("Cuid",getCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMeins())
			.append(getMsehl())
			.append(getCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTunit == false) return false;
		if(this == obj) return true;
		ZgTunit other = (ZgTunit)obj;
		return new EqualsBuilder()
			.append(getMeins(),other.getMeins())
			.append(getMsehl(),other.getMsehl())
			.append(getCuid(),other.getCuid())
			.isEquals();
	}
}
