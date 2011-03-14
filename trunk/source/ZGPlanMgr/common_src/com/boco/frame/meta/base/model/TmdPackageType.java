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


public class TmdPackageType extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdPackageType";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_PARENT_ID = "parentId";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_USETYPE = "usetype";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String bmClassid;
	private java.lang.String labelCn;
	private java.lang.String parentId;
	private java.lang.String description;
	private java.lang.String usetype;
	//columns END


	public TmdPackageType(){
	}

	public TmdPackageType(
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

	
	public java.lang.String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
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
			.append("BmClassid",getBmClassid())
			.append("LabelCn",getLabelCn())
			.append("ParentId",getParentId())
			.append("Description",getDescription())
			.append("Usetype",getUsetype())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getBmClassid())
			.append(getLabelCn())
			.append(getParentId())
			.append(getDescription())
			.append(getUsetype())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdPackageType == false) return false;
		if(this == obj) return true;
		TmdPackageType other = (TmdPackageType)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getBmClassid(),other.getBmClassid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getParentId(),other.getParentId())
			.append(getDescription(),other.getDescription())
			.append(getUsetype(),other.getUsetype())
			.isEquals();
	}
}
