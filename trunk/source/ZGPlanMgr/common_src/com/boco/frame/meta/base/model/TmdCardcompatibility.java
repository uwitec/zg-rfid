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


public class TmdCardcompatibility extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdCardcompatibility";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_CARDTYPE_CUID = "relatedCardtypeCuid";
	public static final String ALIAS_RELATED_SLOTTYPE_CUID = "relatedSlottypeCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedCardtypeCuid;
	private java.lang.String relatedSlottypeCuid;
	//columns END


	public TmdCardcompatibility(){
	}

	public TmdCardcompatibility(
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


	
	
	public java.lang.String getRelatedCardtypeCuid() {
		return this.relatedCardtypeCuid;
	}
	
	public void setRelatedCardtypeCuid(java.lang.String value) {
		this.relatedCardtypeCuid = value;
	}

	
	public java.lang.String getRelatedSlottypeCuid() {
		return this.relatedSlottypeCuid;
	}
	
	public void setRelatedSlottypeCuid(java.lang.String value) {
		this.relatedSlottypeCuid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedCardtypeCuid",getRelatedCardtypeCuid())
			.append("RelatedSlottypeCuid",getRelatedSlottypeCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedCardtypeCuid())
			.append(getRelatedSlottypeCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdCardcompatibility == false) return false;
		if(this == obj) return true;
		TmdCardcompatibility other = (TmdCardcompatibility)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedCardtypeCuid(),other.getRelatedCardtypeCuid())
			.append(getRelatedSlottypeCuid(),other.getRelatedSlottypeCuid())
			.isEquals();
	}
}
