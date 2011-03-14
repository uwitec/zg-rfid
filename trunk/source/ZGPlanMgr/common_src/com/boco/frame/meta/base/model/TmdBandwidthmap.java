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


public class TmdBandwidthmap extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdBandwidthmap";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_BANDWIDTH_CUID_PARENT = "relatedBandwidthCuidParent";
	public static final String ALIAS_RELATED_BANDWIDTH_CUID_CHILD = "relatedBandwidthCuidChild";
	public static final String ALIAS_COMPOSITENUM = "compositenum";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedBandwidthCuidParent;
	private java.lang.String relatedBandwidthCuidChild;
	private java.math.BigDecimal compositenum;
	//columns END


	public TmdBandwidthmap(){
	}

	public TmdBandwidthmap(
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


	
	
	public java.lang.String getRelatedBandwidthCuidParent() {
		return this.relatedBandwidthCuidParent;
	}
	
	public void setRelatedBandwidthCuidParent(java.lang.String value) {
		this.relatedBandwidthCuidParent = value;
	}

	
	public java.lang.String getRelatedBandwidthCuidChild() {
		return this.relatedBandwidthCuidChild;
	}
	
	public void setRelatedBandwidthCuidChild(java.lang.String value) {
		this.relatedBandwidthCuidChild = value;
	}

	
	public java.math.BigDecimal getCompositenum() {
		return this.compositenum;
	}
	
	public void setCompositenum(java.math.BigDecimal value) {
		this.compositenum = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedBandwidthCuidParent",getRelatedBandwidthCuidParent())
			.append("RelatedBandwidthCuidChild",getRelatedBandwidthCuidChild())
			.append("Compositenum",getCompositenum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedBandwidthCuidParent())
			.append(getRelatedBandwidthCuidChild())
			.append(getCompositenum())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdBandwidthmap == false) return false;
		if(this == obj) return true;
		TmdBandwidthmap other = (TmdBandwidthmap)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedBandwidthCuidParent(),other.getRelatedBandwidthCuidParent())
			.append(getRelatedBandwidthCuidChild(),other.getRelatedBandwidthCuidChild())
			.append(getCompositenum(),other.getCompositenum())
			.isEquals();
	}
}
