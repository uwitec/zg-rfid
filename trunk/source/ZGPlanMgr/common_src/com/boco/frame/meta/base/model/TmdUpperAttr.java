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


public class TmdUpperAttr extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdUpperAttr";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_TYPE_CUID = "relatedTypeCuid";
	public static final String ALIAS_RELATED_ATTR_CUID = "relatedAttrCuid";
	public static final String ALIAS_UPPER_ATTR_CUID = "upperAttrCuid";
	public static final String ALIAS_RELATED_JOIN_ATTR_CUID = "relatedJoinAttrCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedTypeCuid;
	private java.lang.String relatedAttrCuid;
	private java.lang.String upperAttrCuid;
	private java.lang.String relatedJoinAttrCuid;
	//columns END


	public TmdUpperAttr(){
	}

	public TmdUpperAttr(
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


	
	
	public java.lang.String getRelatedTypeCuid() {
		return this.relatedTypeCuid;
	}
	
	public void setRelatedTypeCuid(java.lang.String value) {
		this.relatedTypeCuid = value;
	}

	
	public java.lang.String getRelatedAttrCuid() {
		return this.relatedAttrCuid;
	}
	
	public void setRelatedAttrCuid(java.lang.String value) {
		this.relatedAttrCuid = value;
	}

	
	public java.lang.String getUpperAttrCuid() {
		return this.upperAttrCuid;
	}
	
	public void setUpperAttrCuid(java.lang.String value) {
		this.upperAttrCuid = value;
	}

	
	public java.lang.String getRelatedJoinAttrCuid() {
		return this.relatedJoinAttrCuid;
	}
	
	public void setRelatedJoinAttrCuid(java.lang.String value) {
		this.relatedJoinAttrCuid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedTypeCuid",getRelatedTypeCuid())
			.append("RelatedAttrCuid",getRelatedAttrCuid())
			.append("UpperAttrCuid",getUpperAttrCuid())
			.append("RelatedJoinAttrCuid",getRelatedJoinAttrCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedTypeCuid())
			.append(getRelatedAttrCuid())
			.append(getUpperAttrCuid())
			.append(getRelatedJoinAttrCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdUpperAttr == false) return false;
		if(this == obj) return true;
		TmdUpperAttr other = (TmdUpperAttr)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedTypeCuid(),other.getRelatedTypeCuid())
			.append(getRelatedAttrCuid(),other.getRelatedAttrCuid())
			.append(getUpperAttrCuid(),other.getUpperAttrCuid())
			.append(getRelatedJoinAttrCuid(),other.getRelatedJoinAttrCuid())
			.isEquals();
	}
}
