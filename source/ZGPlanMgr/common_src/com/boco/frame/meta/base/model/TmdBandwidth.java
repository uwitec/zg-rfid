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


public class TmdBandwidth extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdBandwidth";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_KBPSVALUE = "kbpsvalue";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PK_CUID = "pkCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.math.BigDecimal kbpsvalue;
	private java.lang.String description;
	private java.lang.String pkCuid;
	//columns END


	public TmdBandwidth(){
	}

	public TmdBandwidth(
		java.lang.String pkCuid
	){
		this.pkCuid = pkCuid;
	}



	public void setPkCuid(java.lang.String value) {
		this.pkCuid = value;
	}
	
	public java.lang.String getPkCuid() {
		return this.pkCuid;
	}


	
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}

	
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}

	
	public java.math.BigDecimal getKbpsvalue() {
		return this.kbpsvalue;
	}
	
	public void setKbpsvalue(java.math.BigDecimal value) {
		this.kbpsvalue = value;
	}

	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("Kbpsvalue",getKbpsvalue())
			.append("Description",getDescription())
			.append("PkCuid",getPkCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getKbpsvalue())
			.append(getDescription())
			.append(getPkCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdBandwidth == false) return false;
		if(this == obj) return true;
		TmdBandwidth other = (TmdBandwidth)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getKbpsvalue(),other.getKbpsvalue())
			.append(getDescription(),other.getDescription())
			.append(getPkCuid(),other.getPkCuid())
			.isEquals();
	}
}
