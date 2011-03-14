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


public class TmdEnumetype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdEnumetype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_DIC_CODE = "dicCode";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String description;
	private java.lang.String type;
	private java.lang.String dicCode;
	//columns END


	public TmdEnumetype(){
	}

	public TmdEnumetype(
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

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}

	
	public java.lang.String getDicCode() {
		return this.dicCode;
	}
	
	public void setDicCode(java.lang.String value) {
		this.dicCode = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("Description",getDescription())
			.append("Type",getType())
			.append("DicCode",getDicCode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getDescription())
			.append(getType())
			.append(getDicCode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdEnumetype == false) return false;
		if(this == obj) return true;
		TmdEnumetype other = (TmdEnumetype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getDescription(),other.getDescription())
			.append(getType(),other.getType())
			.append(getDicCode(),other.getDicCode())
			.isEquals();
	}
}
