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


public class TmdDevicetype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdDevicetype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_RELATED_MANUFACTURER_CUID = "relatedManufacturerCuid";
	public static final String ALIAS_VERSION = "version";
	public static final String ALIAS_SHELFSTARTPOS = "shelfstartpos";
	public static final String ALIAS_DYNSHELF = "dynshelf";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PORTSTARTPOS = "portstartpos";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String bmClassid;
	private java.lang.String labelCn;
	private java.lang.String relatedManufacturerCuid;
	private java.lang.String version;
	private java.math.BigDecimal shelfstartpos;
	private java.math.BigDecimal dynshelf;
	private java.lang.String description;
	private java.math.BigDecimal portstartpos;
	//columns END


	public TmdDevicetype(){
	}

	public TmdDevicetype(
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

	
	public java.lang.String getRelatedManufacturerCuid() {
		return this.relatedManufacturerCuid;
	}
	
	public void setRelatedManufacturerCuid(java.lang.String value) {
		this.relatedManufacturerCuid = value;
	}

	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}

	
	public java.math.BigDecimal getShelfstartpos() {
		return this.shelfstartpos;
	}
	
	public void setShelfstartpos(java.math.BigDecimal value) {
		this.shelfstartpos = value;
	}

	
	public java.math.BigDecimal getDynshelf() {
		return this.dynshelf;
	}
	
	public void setDynshelf(java.math.BigDecimal value) {
		this.dynshelf = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	
	public java.math.BigDecimal getPortstartpos() {
		return this.portstartpos;
	}
	
	public void setPortstartpos(java.math.BigDecimal value) {
		this.portstartpos = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("BmClassid",getBmClassid())
			.append("LabelCn",getLabelCn())
			.append("RelatedManufacturerCuid",getRelatedManufacturerCuid())
			.append("Version",getVersion())
			.append("Shelfstartpos",getShelfstartpos())
			.append("Dynshelf",getDynshelf())
			.append("Description",getDescription())
			.append("Portstartpos",getPortstartpos())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getBmClassid())
			.append(getLabelCn())
			.append(getRelatedManufacturerCuid())
			.append(getVersion())
			.append(getShelfstartpos())
			.append(getDynshelf())
			.append(getDescription())
			.append(getPortstartpos())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdDevicetype == false) return false;
		if(this == obj) return true;
		TmdDevicetype other = (TmdDevicetype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getBmClassid(),other.getBmClassid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getRelatedManufacturerCuid(),other.getRelatedManufacturerCuid())
			.append(getVersion(),other.getVersion())
			.append(getShelfstartpos(),other.getShelfstartpos())
			.append(getDynshelf(),other.getDynshelf())
			.append(getDescription(),other.getDescription())
			.append(getPortstartpos(),other.getPortstartpos())
			.isEquals();
	}
}
