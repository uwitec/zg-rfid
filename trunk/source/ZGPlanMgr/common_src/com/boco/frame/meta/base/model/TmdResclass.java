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


public class TmdResclass extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdResclass";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_TABLE_NAME = "tableName";
	public static final String ALIAS_ID_NAME = "idName";
	public static final String ALIAS_SEQUENCE_NAME = "sequenceName";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_TYPE_TABLE_NAME = "typeTableName";
	public static final String ALIAS_IS_SYSTEM = "isSystem";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String tableName;
	private java.lang.String idName;
	private java.lang.String sequenceName;
	private java.lang.String description;
	private java.lang.String typeTableName;
	private java.math.BigDecimal isSystem;
	//columns END


	public TmdResclass(){
	}

	public TmdResclass(
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

	
	public java.lang.String getIdName() {
		return this.idName;
	}
	
	public void setIdName(java.lang.String value) {
		this.idName = value;
	}

	
	public java.lang.String getSequenceName() {
		return this.sequenceName;
	}
	
	public void setSequenceName(java.lang.String value) {
		this.sequenceName = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getTypeTableName() {
		return this.typeTableName;
	}
	
	public void setTypeTableName(java.lang.String value) {
		this.typeTableName = value;
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
			.append("LabelCn",getLabelCn())
			.append("TableName",getTableName())
			.append("IdName",getIdName())
			.append("SequenceName",getSequenceName())
			.append("Description",getDescription())
			.append("TypeTableName",getTypeTableName())
			.append("IsSystem",getIsSystem())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getTableName())
			.append(getIdName())
			.append(getSequenceName())
			.append(getDescription())
			.append(getTypeTableName())
			.append(getIsSystem())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdResclass == false) return false;
		if(this == obj) return true;
		TmdResclass other = (TmdResclass)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getTableName(),other.getTableName())
			.append(getIdName(),other.getIdName())
			.append(getSequenceName(),other.getSequenceName())
			.append(getDescription(),other.getDescription())
			.append(getTypeTableName(),other.getTypeTableName())
			.append(getIsSystem(),other.getIsSystem())
			.isEquals();
	}
}
