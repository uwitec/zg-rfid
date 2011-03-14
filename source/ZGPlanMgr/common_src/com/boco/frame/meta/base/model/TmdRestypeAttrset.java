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


public class TmdRestypeAttrset extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRestypeAttrset";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_TYPE_CUID = "relatedTypeCuid";
	public static final String ALIAS_RELATED_ATTRSET_CUID = "relatedAttrsetCuid";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_IS_BASIC = "isBasic";
	public static final String ALIAS_IS_SYSTEM = "isSystem";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedTypeCuid;
	private java.lang.String relatedAttrsetCuid;
	private java.lang.String description;
	private java.math.BigDecimal isBasic;
	private java.math.BigDecimal isSystem;
	//columns END


	public TmdRestypeAttrset(){
	}

	public TmdRestypeAttrset(
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

	
	public java.lang.String getRelatedAttrsetCuid() {
		return this.relatedAttrsetCuid;
	}
	
	public void setRelatedAttrsetCuid(java.lang.String value) {
		this.relatedAttrsetCuid = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.math.BigDecimal getIsBasic() {
		return this.isBasic;
	}
	
	public void setIsBasic(java.math.BigDecimal value) {
		this.isBasic = value;
	}

	
	public java.math.BigDecimal getIsSystem() {
		return this.isSystem;
	}
	
	public void setIsSystem(java.math.BigDecimal value) {
		this.isSystem = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedTypeCuid",getRelatedTypeCuid())
			.append("RelatedAttrsetCuid",getRelatedAttrsetCuid())
			.append("Description",getDescription())
			.append("IsBasic",getIsBasic())
			.append("IsSystem",getIsSystem())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedTypeCuid())
			.append(getRelatedAttrsetCuid())
			.append(getDescription())
			.append(getIsBasic())
			.append(getIsSystem())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRestypeAttrset == false) return false;
		if(this == obj) return true;
		TmdRestypeAttrset other = (TmdRestypeAttrset)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedTypeCuid(),other.getRelatedTypeCuid())
			.append(getRelatedAttrsetCuid(),other.getRelatedAttrsetCuid())
			.append(getDescription(),other.getDescription())
			.append(getIsBasic(),other.getIsBasic())
			.append(getIsSystem(),other.getIsSystem())
			.isEquals();
	}
}
