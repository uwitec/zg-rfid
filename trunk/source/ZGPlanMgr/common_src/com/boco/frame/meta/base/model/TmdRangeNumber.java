/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class TmdRangeNumber extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRangeNumber";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RANGE_BMCLASSID = "rangeBmclassid";
	public static final String ALIAS_NUMBER_BMCLASSID = "numberBmclassid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String rangeBmclassid;
	private java.lang.String numberBmclassid;
	//columns END


	public TmdRangeNumber(){
	}

	public TmdRangeNumber(
		java.lang.String cuid
	){
		this.cuid = cuid;
	}



	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	
	public java.lang.String getCuid() {
		return this.cuid;
	}


	
	
	public java.lang.String getRangeBmclassid() {
		return this.rangeBmclassid;
	}
	
	public void setRangeBmclassid(java.lang.String value) {
		this.rangeBmclassid = value;
	}

	
	public java.lang.String getNumberBmclassid() {
		return this.numberBmclassid;
	}
	
	public void setNumberBmclassid(java.lang.String value) {
		this.numberBmclassid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RangeBmclassid",getRangeBmclassid())
			.append("NumberBmclassid",getNumberBmclassid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRangeBmclassid())
			.append(getNumberBmclassid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRangeNumber == false) return false;
		if(this == obj) return true;
		TmdRangeNumber other = (TmdRangeNumber)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRangeBmclassid(),other.getRangeBmclassid())
			.append(getNumberBmclassid(),other.getNumberBmclassid())
			.isEquals();
	}
}
