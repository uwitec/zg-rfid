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


public class TmdEnumevalue extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdEnumevalue";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_ET_CUID = "relatedEtCuid";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_VALUE = "value";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_COLOR = "color";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedEtCuid;
	private java.lang.String name;
	private java.lang.String value;
	private java.lang.String description;
	private java.lang.String color;
	//columns END


	public TmdEnumevalue(){
	}

	public TmdEnumevalue(
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


	
	
	public java.lang.String getRelatedEtCuid() {
		return this.relatedEtCuid;
	}
	
	public void setRelatedEtCuid(java.lang.String value) {
		this.relatedEtCuid = value;
	}

	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}

	
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.lang.String getColor() {
		return this.color;
	}
	
	public void setColor(java.lang.String value) {
		this.color = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedEtCuid",getRelatedEtCuid())
			.append("Name",getName())
			.append("Value",getValue())
			.append("Description",getDescription())
			.append("Color",getColor())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedEtCuid())
			.append(getName())
			.append(getValue())
			.append(getDescription())
			.append(getColor())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdEnumevalue == false) return false;
		if(this == obj) return true;
		TmdEnumevalue other = (TmdEnumevalue)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedEtCuid(),other.getRelatedEtCuid())
			.append(getName(),other.getName())
			.append(getValue(),other.getValue())
			.append(getDescription(),other.getDescription())
			.append(getColor(),other.getColor())
			.isEquals();
	}
}
