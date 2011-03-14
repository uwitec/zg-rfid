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


public class TmdAttribute extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdAttribute";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_RELATED_ATTRSET_CUID = "relatedAttrsetCuid";
	public static final String ALIAS_COLUMN_NAME = "columnName";
	public static final String ALIAS_DATA_TYPE = "dataType";
	public static final String ALIAS_SEQUENCE = "sequence";
	public static final String ALIAS_RELATED_ENUMETYPE_CUID = "relatedEnumetypeCuid";
	public static final String ALIAS_IS_NOTNULL = "isNotnull";
	public static final String ALIAS_IS_READABLE = "isReadable";
	public static final String ALIAS_IS_SYSTEM = "isSystem";
	public static final String ALIAS_DEFAULT_VALUE = "defaultValue";
	public static final String ALIAS_DATA_SOURCE = "dataSource";
	public static final String ALIAS_STR_ATTR_MAXLEN = "strAttrMaxlen";
	public static final String ALIAS_XTYPE = "xtype";
	public static final String ALIAS_VERIFY_EXPRESSION = "verifyExpression";
	public static final String ALIAS_CALCULATE_EXPRESSION = "calculateExpression";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_IS_BATCH_MODIFY = "isBatchModify";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String relatedAttrsetCuid;
	private java.lang.String columnName;
	private java.lang.String dataType;
	private java.math.BigDecimal sequence;
	private java.lang.String relatedEnumetypeCuid;
	private java.math.BigDecimal isNotnull;
	private java.math.BigDecimal isReadable;
	private java.math.BigDecimal isSystem;
	private java.lang.String defaultValue;
	private java.math.BigDecimal dataSource;
	private java.math.BigDecimal strAttrMaxlen;
	private java.lang.String xtype;
	private java.lang.String verifyExpression;
	private java.lang.String calculateExpression;
	private java.lang.String description;
	private java.math.BigDecimal isBatchModify;
	//columns END


	public TmdAttribute(){
	}

	public TmdAttribute(
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

	
	public java.lang.String getRelatedAttrsetCuid() {
		return this.relatedAttrsetCuid;
	}
	
	public void setRelatedAttrsetCuid(java.lang.String value) {
		this.relatedAttrsetCuid = value;
	}

	
	public java.lang.String getColumnName() {
		return this.columnName;
	}
	
	public void setColumnName(java.lang.String value) {
		this.columnName = value;
	}

	
	public java.lang.String getDataType() {
		return this.dataType;
	}
	
	public void setDataType(java.lang.String value) {
		this.dataType = value;
	}

	
	public java.math.BigDecimal getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.math.BigDecimal value) {
		this.sequence = value;
	}

	
	public java.lang.String getRelatedEnumetypeCuid() {
		return this.relatedEnumetypeCuid;
	}
	
	public void setRelatedEnumetypeCuid(java.lang.String value) {
		this.relatedEnumetypeCuid = value;
	}

	
	public java.math.BigDecimal getIsNotnull() {
		return this.isNotnull;
	}
	
	public void setIsNotnull(java.math.BigDecimal value) {
		this.isNotnull = value;
	}

	
	public java.math.BigDecimal getIsReadable() {
		return this.isReadable;
	}
	
	public void setIsReadable(java.math.BigDecimal value) {
		this.isReadable = value;
	}

	
	public java.math.BigDecimal getIsSystem() {
		return this.isSystem;
	}
	
	public void setIsSystem(java.math.BigDecimal value) {
		this.isSystem = value;
	}

	
	public java.lang.String getDefaultValue() {
		return this.defaultValue;
	}
	
	public void setDefaultValue(java.lang.String value) {
		this.defaultValue = value;
	}

	
	public java.math.BigDecimal getDataSource() {
		return this.dataSource;
	}
	
	public void setDataSource(java.math.BigDecimal value) {
		this.dataSource = value;
	}

	
	public java.math.BigDecimal getStrAttrMaxlen() {
		return this.strAttrMaxlen;
	}
	
	public void setStrAttrMaxlen(java.math.BigDecimal value) {
		this.strAttrMaxlen = value;
	}

	
	public java.lang.String getXtype() {
		return this.xtype;
	}
	
	public void setXtype(java.lang.String value) {
		this.xtype = value;
	}

	
	public java.lang.String getVerifyExpression() {
		return this.verifyExpression;
	}
	
	public void setVerifyExpression(java.lang.String value) {
		this.verifyExpression = value;
	}

	
	public java.lang.String getCalculateExpression() {
		return this.calculateExpression;
	}
	
	public void setCalculateExpression(java.lang.String value) {
		this.calculateExpression = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.math.BigDecimal getIsBatchModify() {
		return this.isBatchModify;
	}
	
	public void setIsBatchModify(java.math.BigDecimal value) {
		this.isBatchModify = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("RelatedAttrsetCuid",getRelatedAttrsetCuid())
			.append("ColumnName",getColumnName())
			.append("DataType",getDataType())
			.append("Sequence",getSequence())
			.append("RelatedEnumetypeCuid",getRelatedEnumetypeCuid())
			.append("IsNotnull",getIsNotnull())
			.append("IsReadable",getIsReadable())
			.append("IsSystem",getIsSystem())
			.append("DefaultValue",getDefaultValue())
			.append("DataSource",getDataSource())
			.append("StrAttrMaxlen",getStrAttrMaxlen())
			.append("Xtype",getXtype())
			.append("VerifyExpression",getVerifyExpression())
			.append("CalculateExpression",getCalculateExpression())
			.append("Description",getDescription())
			.append("IsBatchModify",getIsBatchModify())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getRelatedAttrsetCuid())
			.append(getColumnName())
			.append(getDataType())
			.append(getSequence())
			.append(getRelatedEnumetypeCuid())
			.append(getIsNotnull())
			.append(getIsReadable())
			.append(getIsSystem())
			.append(getDefaultValue())
			.append(getDataSource())
			.append(getStrAttrMaxlen())
			.append(getXtype())
			.append(getVerifyExpression())
			.append(getCalculateExpression())
			.append(getDescription())
			.append(getIsBatchModify())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdAttribute == false) return false;
		if(this == obj) return true;
		TmdAttribute other = (TmdAttribute)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getRelatedAttrsetCuid(),other.getRelatedAttrsetCuid())
			.append(getColumnName(),other.getColumnName())
			.append(getDataType(),other.getDataType())
			.append(getSequence(),other.getSequence())
			.append(getRelatedEnumetypeCuid(),other.getRelatedEnumetypeCuid())
			.append(getIsNotnull(),other.getIsNotnull())
			.append(getIsReadable(),other.getIsReadable())
			.append(getIsSystem(),other.getIsSystem())
			.append(getDefaultValue(),other.getDefaultValue())
			.append(getDataSource(),other.getDataSource())
			.append(getStrAttrMaxlen(),other.getStrAttrMaxlen())
			.append(getXtype(),other.getXtype())
			.append(getVerifyExpression(),other.getVerifyExpression())
			.append(getCalculateExpression(),other.getCalculateExpression())
			.append(getDescription(),other.getDescription())
			.append(getIsBatchModify(),other.getIsBatchModify())
			.isEquals();
	}
}
