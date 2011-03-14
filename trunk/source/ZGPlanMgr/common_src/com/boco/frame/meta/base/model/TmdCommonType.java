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


public class TmdCommonType extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdCommonType";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_PARENT_TYPE_CUID = "parentTypeCuid";
	public static final String ALIAS_IS_REAL_TABLE = "isRealTable";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_BM_CLASS_ID = "bmClassId";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String parentTypeCuid;
	private java.math.BigDecimal isRealTable;
	private java.lang.String description;
	private java.lang.String bmClassId;
	//columns END


	public TmdCommonType(){
	}

	public TmdCommonType(
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

	
	public java.lang.String getParentTypeCuid() {
		return this.parentTypeCuid;
	}
	
	public void setParentTypeCuid(java.lang.String value) {
		this.parentTypeCuid = value;
	}

	
	public java.math.BigDecimal getIsRealTable() {
		return this.isRealTable;
	}
	
	public void setIsRealTable(java.math.BigDecimal value) {
		this.isRealTable = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getBmClassId() {
		return this.bmClassId;
	}
	
	public void setBmClassId(java.lang.String value) {
		this.bmClassId = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("ParentTypeCuid",getParentTypeCuid())
			.append("IsRealTable",getIsRealTable())
			.append("Description",getDescription())
			.append("BmClassId",getBmClassId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getParentTypeCuid())
			.append(getIsRealTable())
			.append(getDescription())
			.append(getBmClassId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdCommonType == false) return false;
		if(this == obj) return true;
		TmdCommonType other = (TmdCommonType)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getParentTypeCuid(),other.getParentTypeCuid())
			.append(getIsRealTable(),other.getIsRealTable())
			.append(getDescription(),other.getDescription())
			.append(getBmClassId(),other.getBmClassId())
			.isEquals();
	}
}
