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


public class TmdUniqueAttr extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdUniqueAttr";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_UNIQUE_CUID = "relatedUniqueCuid";
	public static final String ALIAS_RELATED_ATTRSET_CUID = "relatedAttrsetCuid";
	public static final String ALIAS_RELATED_ATTR_CUID = "relatedAttrCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedUniqueCuid;
	private java.lang.String relatedAttrsetCuid;
	private java.lang.String relatedAttrCuid;
	//columns END


	public TmdUniqueAttr(){
	}

	public TmdUniqueAttr(
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


	
	
	public java.lang.String getRelatedUniqueCuid() {
		return this.relatedUniqueCuid;
	}
	
	public void setRelatedUniqueCuid(java.lang.String value) {
		this.relatedUniqueCuid = value;
	}

	
	public java.lang.String getRelatedAttrsetCuid() {
		return this.relatedAttrsetCuid;
	}
	
	public void setRelatedAttrsetCuid(java.lang.String value) {
		this.relatedAttrsetCuid = value;
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
			.append("RelatedUniqueCuid",getRelatedUniqueCuid())
			.append("RelatedAttrsetCuid",getRelatedAttrsetCuid())
			.append("RelatedAttrCuid",getRelatedAttrCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedUniqueCuid())
			.append(getRelatedAttrsetCuid())
			.append(getRelatedAttrCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdUniqueAttr == false) return false;
		if(this == obj) return true;
		TmdUniqueAttr other = (TmdUniqueAttr)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedUniqueCuid(),other.getRelatedUniqueCuid())
			.append(getRelatedAttrsetCuid(),other.getRelatedAttrsetCuid())
			.append(getRelatedAttrCuid(),other.getRelatedAttrCuid())
			.isEquals();
	}
}
