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


public class TmdRelated extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRelated";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_ATTR_CUID = "relatedAttrCuid";
	public static final String ALIAS_RELATED_JOIN_TYPE_CUID = "relatedJoinTypeCuid";
	public static final String ALIAS_RELATED_JOIN_ATTR_CUID = "relatedJoinAttrCuid";
	public static final String ALIAS_CASCADE_TYPE = "cascadeType";
	public static final String ALIAS_STAT_TYPE = "statType";
	public static final String ALIAS_FILTER = "filter";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedAttrCuid;
	private java.lang.String relatedJoinTypeCuid;
	private java.lang.String relatedJoinAttrCuid;
	private java.math.BigDecimal cascadeType;
	private java.math.BigDecimal statType;
	private java.lang.String filter;
	//columns END


	public TmdRelated(){
	}

	public TmdRelated(
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


	
	
	public java.lang.String getRelatedAttrCuid() {
		return this.relatedAttrCuid;
	}
	
	public void setRelatedAttrCuid(java.lang.String value) {
		this.relatedAttrCuid = value;
	}

	
	public java.lang.String getRelatedJoinTypeCuid() {
		return this.relatedJoinTypeCuid;
	}
	
	public void setRelatedJoinTypeCuid(java.lang.String value) {
		this.relatedJoinTypeCuid = value;
	}

	
	public java.lang.String getRelatedJoinAttrCuid() {
		return this.relatedJoinAttrCuid;
	}
	
	public void setRelatedJoinAttrCuid(java.lang.String value) {
		this.relatedJoinAttrCuid = value;
	}

	
	public java.math.BigDecimal getCascadeType() {
		return this.cascadeType;
	}
	
	public void setCascadeType(java.math.BigDecimal value) {
		this.cascadeType = value;
	}

	
	public java.math.BigDecimal getStatType() {
		return this.statType;
	}
	
	public void setStatType(java.math.BigDecimal value) {
		this.statType = value;
	}

	
	public java.lang.String getFilter() {
		return this.filter;
	}
	
	public void setFilter(java.lang.String value) {
		this.filter = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedAttrCuid",getRelatedAttrCuid())
			.append("RelatedJoinTypeCuid",getRelatedJoinTypeCuid())
			.append("RelatedJoinAttrCuid",getRelatedJoinAttrCuid())
			.append("CascadeType",getCascadeType())
			.append("StatType",getStatType())
			.append("Filter",getFilter())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedAttrCuid())
			.append(getRelatedJoinTypeCuid())
			.append(getRelatedJoinAttrCuid())
			.append(getCascadeType())
			.append(getStatType())
			.append(getFilter())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRelated == false) return false;
		if(this == obj) return true;
		TmdRelated other = (TmdRelated)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedAttrCuid(),other.getRelatedAttrCuid())
			.append(getRelatedJoinTypeCuid(),other.getRelatedJoinTypeCuid())
			.append(getRelatedJoinAttrCuid(),other.getRelatedJoinAttrCuid())
			.append(getCascadeType(),other.getCascadeType())
			.append(getStatType(),other.getStatType())
			.append(getFilter(),other.getFilter())
			.isEquals();
	}
}
