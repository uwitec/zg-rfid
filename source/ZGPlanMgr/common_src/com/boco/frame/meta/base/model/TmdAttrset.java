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


public class TmdAttrset extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdAttrset";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_TABLE_NAME = "tableName";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PK_COL = "pkCol";
	public static final String ALIAS_RELATED_RES_TYPE_CUID = "relatedResTypeCuid";
	public static final String ALIAS_IS_BASIC = "isBasic";
	public static final String ALIAS_IS_SYSTEM = "isSystem";
	public static final String ALIAS_IS_STATIC = "isStatic";
	public static final String ALIAS_IS_RESOURCE = "isResource";
	public static final String ALIAS_USETYPE = "usetype";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String tableName;
	private java.lang.String description;
	private java.lang.String pkCol;
	private java.lang.String relatedResTypeCuid;
	private java.math.BigDecimal isBasic;
	private java.math.BigDecimal isSystem;
	private java.math.BigDecimal isStatic;
	private java.math.BigDecimal isResource;
	private java.lang.String usetype;
	//columns END


	public TmdAttrset(){
	}

	public TmdAttrset(
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


	
	
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}

	
	public java.lang.String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getPkCol() {
		return this.pkCol;
	}
	
	public void setPkCol(java.lang.String value) {
		this.pkCol = value;
	}

	
	public java.lang.String getRelatedResTypeCuid() {
		return this.relatedResTypeCuid;
	}
	
	public void setRelatedResTypeCuid(java.lang.String value) {
		this.relatedResTypeCuid = value;
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

	
	public java.math.BigDecimal getIsStatic() {
		return this.isStatic;
	}
	
	public void setIsStatic(java.math.BigDecimal value) {
		this.isStatic = value;
	}

	
	public java.math.BigDecimal getIsResource() {
		return this.isResource;
	}
	
	public void setIsResource(java.math.BigDecimal value) {
		this.isResource = value;
	}

	
	public java.lang.String getUsetype() {
		return this.usetype;
	}
	
	public void setUsetype(java.lang.String value) {
		this.usetype = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("TableName",getTableName())
			.append("Description",getDescription())
			.append("PkCol",getPkCol())
			.append("RelatedResTypeCuid",getRelatedResTypeCuid())
			.append("IsBasic",getIsBasic())
			.append("IsSystem",getIsSystem())
			.append("IsStatic",getIsStatic())
			.append("IsResource",getIsResource())
			.append("Usetype",getUsetype())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getTableName())
			.append(getDescription())
			.append(getPkCol())
			.append(getRelatedResTypeCuid())
			.append(getIsBasic())
			.append(getIsSystem())
			.append(getIsStatic())
			.append(getIsResource())
			.append(getUsetype())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdAttrset == false) return false;
		if(this == obj) return true;
		TmdAttrset other = (TmdAttrset)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getTableName(),other.getTableName())
			.append(getDescription(),other.getDescription())
			.append(getPkCol(),other.getPkCol())
			.append(getRelatedResTypeCuid(),other.getRelatedResTypeCuid())
			.append(getIsBasic(),other.getIsBasic())
			.append(getIsSystem(),other.getIsSystem())
			.append(getIsStatic(),other.getIsStatic())
			.append(getIsResource(),other.getIsResource())
			.append(getUsetype(),other.getUsetype())
			.isEquals();
	}
}
