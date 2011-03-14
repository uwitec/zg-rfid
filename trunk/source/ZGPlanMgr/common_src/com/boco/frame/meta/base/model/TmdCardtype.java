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


public class TmdCardtype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdCardtype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_LENGTH = "length";
	public static final String ALIAS_WIDTH = "width";
	public static final String ALIAS_PORTSTARTPOS = "portstartpos";
	public static final String ALIAS_SLOTSTARTPOS = "slotstartpos";
	public static final String ALIAS_DYNPORT = "dynport";
	public static final String ALIAS_DYNSLOT = "dynslot";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_IS_SUBCARD = "isSubcard";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String bmClassid;
	private java.lang.String labelCn;
	private java.math.BigDecimal length;
	private java.math.BigDecimal width;
	private java.math.BigDecimal portstartpos;
	private java.math.BigDecimal slotstartpos;
	private java.math.BigDecimal dynport;
	private java.math.BigDecimal dynslot;
	private java.lang.String description;
	private java.math.BigDecimal isSubcard;
	//columns END


	public TmdCardtype(){
	}

	public TmdCardtype(
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


	
	
	public java.lang.String getBmClassid() {
		return this.bmClassid;
	}
	
	public void setBmClassid(java.lang.String value) {
		this.bmClassid = value;
	}

	
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}

	
	public java.math.BigDecimal getLength() {
		return this.length;
	}
	
	public void setLength(java.math.BigDecimal value) {
		this.length = value;
	}

	
	public java.math.BigDecimal getWidth() {
		return this.width;
	}
	
	public void setWidth(java.math.BigDecimal value) {
		this.width = value;
	}

	
	public java.math.BigDecimal getPortstartpos() {
		return this.portstartpos;
	}
	
	public void setPortstartpos(java.math.BigDecimal value) {
		this.portstartpos = value;
	}

	
	public java.math.BigDecimal getSlotstartpos() {
		return this.slotstartpos;
	}
	
	public void setSlotstartpos(java.math.BigDecimal value) {
		this.slotstartpos = value;
	}

	
	public java.math.BigDecimal getDynport() {
		return this.dynport;
	}
	
	public void setDynport(java.math.BigDecimal value) {
		this.dynport = value;
	}

	
	public java.math.BigDecimal getDynslot() {
		return this.dynslot;
	}
	
	public void setDynslot(java.math.BigDecimal value) {
		this.dynslot = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.math.BigDecimal getIsSubcard() {
		return this.isSubcard;
	}
	
	public void setIsSubcard(java.math.BigDecimal value) {
		this.isSubcard = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("BmClassid",getBmClassid())
			.append("LabelCn",getLabelCn())
			.append("Length",getLength())
			.append("Width",getWidth())
			.append("Portstartpos",getPortstartpos())
			.append("Slotstartpos",getSlotstartpos())
			.append("Dynport",getDynport())
			.append("Dynslot",getDynslot())
			.append("Description",getDescription())
			.append("IsSubcard",getIsSubcard())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getBmClassid())
			.append(getLabelCn())
			.append(getLength())
			.append(getWidth())
			.append(getPortstartpos())
			.append(getSlotstartpos())
			.append(getDynport())
			.append(getDynslot())
			.append(getDescription())
			.append(getIsSubcard())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdCardtype == false) return false;
		if(this == obj) return true;
		TmdCardtype other = (TmdCardtype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getBmClassid(),other.getBmClassid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getLength(),other.getLength())
			.append(getWidth(),other.getWidth())
			.append(getPortstartpos(),other.getPortstartpos())
			.append(getSlotstartpos(),other.getSlotstartpos())
			.append(getDynport(),other.getDynport())
			.append(getDynslot(),other.getDynslot())
			.append(getDescription(),other.getDescription())
			.append(getIsSubcard(),other.getIsSubcard())
			.isEquals();
	}
}
