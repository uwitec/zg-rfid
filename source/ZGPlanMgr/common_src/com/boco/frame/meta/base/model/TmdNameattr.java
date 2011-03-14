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


public class TmdNameattr extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdNameattr";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_TYPE_CUID = "relatedTypeCuid";
	public static final String ALIAS_RELATED_ATTR_CUID = "relatedAttrCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedTypeCuid;
	private java.lang.String relatedAttrCuid;
	//columns END


	public TmdNameattr(){
	}

	public TmdNameattr(
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


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedTypeCuid",getRelatedTypeCuid())
			.append("RelatedAttrCuid",getRelatedAttrCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedTypeCuid())
			.append(getRelatedAttrCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdNameattr == false) return false;
		if(this == obj) return true;
		TmdNameattr other = (TmdNameattr)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedTypeCuid(),other.getRelatedTypeCuid())
			.append(getRelatedAttrCuid(),other.getRelatedAttrCuid())
			.isEquals();
	}
}
