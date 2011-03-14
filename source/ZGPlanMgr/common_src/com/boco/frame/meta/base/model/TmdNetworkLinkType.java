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


public class TmdNetworkLinkType extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdNetworkLinkType";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_NETWORK_TYPE_CUID = "relatedNetworkTypeCuid";
	public static final String ALIAS_RELATED_LINKTYPE_CUID = "relatedLinktypeCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedNetworkTypeCuid;
	private java.lang.String relatedLinktypeCuid;
	//columns END


	public TmdNetworkLinkType(){
	}

	public TmdNetworkLinkType(
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


	
	
	public java.lang.String getRelatedNetworkTypeCuid() {
		return this.relatedNetworkTypeCuid;
	}
	
	public void setRelatedNetworkTypeCuid(java.lang.String value) {
		this.relatedNetworkTypeCuid = value;
	}

	
	public java.lang.String getRelatedLinktypeCuid() {
		return this.relatedLinktypeCuid;
	}
	
	public void setRelatedLinktypeCuid(java.lang.String value) {
		this.relatedLinktypeCuid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedNetworkTypeCuid",getRelatedNetworkTypeCuid())
			.append("RelatedLinktypeCuid",getRelatedLinktypeCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedNetworkTypeCuid())
			.append(getRelatedLinktypeCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdNetworkLinkType == false) return false;
		if(this == obj) return true;
		TmdNetworkLinkType other = (TmdNetworkLinkType)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedNetworkTypeCuid(),other.getRelatedNetworkTypeCuid())
			.append(getRelatedLinktypeCuid(),other.getRelatedLinktypeCuid())
			.isEquals();
	}
}
