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


public class TmdNetworktypeNodetype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdNetworktypeNodetype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_NETWORKTYPE_CUID = "relatedNetworktypeCuid";
	public static final String ALIAS_RELATED_NODETYPE_CUID = "relatedNodetypeCuid";
	public static final String ALIAS_NODEFILTER = "nodefilter";
	public static final String ALIAS_REMARK = "remark";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedNetworktypeCuid;
	private java.lang.String relatedNodetypeCuid;
	private java.lang.String nodefilter;
	private java.lang.String remark;
	//columns END


	public TmdNetworktypeNodetype(){
	}

	public TmdNetworktypeNodetype(
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


	
	
	public java.lang.String getRelatedNetworktypeCuid() {
		return this.relatedNetworktypeCuid;
	}
	
	public void setRelatedNetworktypeCuid(java.lang.String value) {
		this.relatedNetworktypeCuid = value;
	}

	
	public java.lang.String getRelatedNodetypeCuid() {
		return this.relatedNodetypeCuid;
	}
	
	public void setRelatedNodetypeCuid(java.lang.String value) {
		this.relatedNodetypeCuid = value;
	}

	
	public java.lang.String getNodefilter() {
		return this.nodefilter;
	}
	
	public void setNodefilter(java.lang.String value) {
		this.nodefilter = value;
	}

	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedNetworktypeCuid",getRelatedNetworktypeCuid())
			.append("RelatedNodetypeCuid",getRelatedNodetypeCuid())
			.append("Nodefilter",getNodefilter())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedNetworktypeCuid())
			.append(getRelatedNodetypeCuid())
			.append(getNodefilter())
			.append(getRemark())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdNetworktypeNodetype == false) return false;
		if(this == obj) return true;
		TmdNetworktypeNodetype other = (TmdNetworktypeNodetype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedNetworktypeCuid(),other.getRelatedNetworktypeCuid())
			.append(getRelatedNodetypeCuid(),other.getRelatedNodetypeCuid())
			.append(getNodefilter(),other.getNodefilter())
			.append(getRemark(),other.getRemark())
			.isEquals();
	}
}
