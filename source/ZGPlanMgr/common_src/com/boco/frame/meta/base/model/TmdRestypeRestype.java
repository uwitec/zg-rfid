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


public class TmdRestypeRestype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRestypeRestype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_RELATED_LEFT_TYPE = "relatedLeftType";
	public static final String ALIAS_LEFT_INHIBIT = "leftInhibit";
	public static final String ALIAS_RELATED_RIGHT_TYPE = "relatedRightType";
	public static final String ALIAS_RIGHT_INHIBIT = "rightInhibit";
	public static final String ALIAS_LEFT_ATTR_NAME = "leftAttrName";
	public static final String ALIAS_RIGHT_ATTR_NAME = "rightAttrName";
	public static final String ALIAS_CENTER_TABLE_NAME = "centerTableName";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_DIC_CODE = "dicCode";
	public static final String ALIAS_IS_HAVE_INSTANCE = "isHaveInstance";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String relatedLeftType;
	private java.lang.String leftInhibit;
	private java.lang.String relatedRightType;
	private java.lang.String rightInhibit;
	private java.lang.String leftAttrName;
	private java.lang.String rightAttrName;
	private java.lang.String centerTableName;
	private java.lang.String description;
	private java.lang.String dicCode;
	private java.math.BigDecimal isHaveInstance;
	//columns END


	public TmdRestypeRestype(){
	}

	public TmdRestypeRestype(
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

	
	public java.lang.String getRelatedLeftType() {
		return this.relatedLeftType;
	}
	
	public void setRelatedLeftType(java.lang.String value) {
		this.relatedLeftType = value;
	}

	
	public java.lang.String getLeftInhibit() {
		return this.leftInhibit;
	}
	
	public void setLeftInhibit(java.lang.String value) {
		this.leftInhibit = value;
	}

	
	public java.lang.String getRelatedRightType() {
		return this.relatedRightType;
	}
	
	public void setRelatedRightType(java.lang.String value) {
		this.relatedRightType = value;
	}

	
	public java.lang.String getRightInhibit() {
		return this.rightInhibit;
	}
	
	public void setRightInhibit(java.lang.String value) {
		this.rightInhibit = value;
	}

	
	public java.lang.String getLeftAttrName() {
		return this.leftAttrName;
	}
	
	public void setLeftAttrName(java.lang.String value) {
		this.leftAttrName = value;
	}

	
	public java.lang.String getRightAttrName() {
		return this.rightAttrName;
	}
	
	public void setRightAttrName(java.lang.String value) {
		this.rightAttrName = value;
	}

	
	public java.lang.String getCenterTableName() {
		return this.centerTableName;
	}
	
	public void setCenterTableName(java.lang.String value) {
		this.centerTableName = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getDicCode() {
		return this.dicCode;
	}
	
	public void setDicCode(java.lang.String value) {
		this.dicCode = value;
	}

	
	public java.math.BigDecimal getIsHaveInstance() {
		return this.isHaveInstance;
	}
	
	public void setIsHaveInstance(java.math.BigDecimal value) {
		this.isHaveInstance = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("RelatedLeftType",getRelatedLeftType())
			.append("LeftInhibit",getLeftInhibit())
			.append("RelatedRightType",getRelatedRightType())
			.append("RightInhibit",getRightInhibit())
			.append("LeftAttrName",getLeftAttrName())
			.append("RightAttrName",getRightAttrName())
			.append("CenterTableName",getCenterTableName())
			.append("Description",getDescription())
			.append("DicCode",getDicCode())
			.append("IsHaveInstance",getIsHaveInstance())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getRelatedLeftType())
			.append(getLeftInhibit())
			.append(getRelatedRightType())
			.append(getRightInhibit())
			.append(getLeftAttrName())
			.append(getRightAttrName())
			.append(getCenterTableName())
			.append(getDescription())
			.append(getDicCode())
			.append(getIsHaveInstance())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRestypeRestype == false) return false;
		if(this == obj) return true;
		TmdRestypeRestype other = (TmdRestypeRestype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getRelatedLeftType(),other.getRelatedLeftType())
			.append(getLeftInhibit(),other.getLeftInhibit())
			.append(getRelatedRightType(),other.getRelatedRightType())
			.append(getRightInhibit(),other.getRightInhibit())
			.append(getLeftAttrName(),other.getLeftAttrName())
			.append(getRightAttrName(),other.getRightAttrName())
			.append(getCenterTableName(),other.getCenterTableName())
			.append(getDescription(),other.getDescription())
			.append(getDicCode(),other.getDicCode())
			.append(getIsHaveInstance(),other.getIsHaveInstance())
			.isEquals();
	}
}
