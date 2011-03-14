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


public class TmdDevicetypeShelftype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdDevicetypeShelftype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_DEVICETYPE_CUID = "relatedDevicetypeCuid";
	public static final String ALIAS_RELATED_SHELFTYPE_CUID = "relatedShelftypeCuid";
	public static final String ALIAS_SEQUENCE = "sequence";
	public static final String ALIAS_ROWINDEVICE = "rowindevice";
	public static final String ALIAS_COLINDEVICE = "colindevice";
	public static final String ALIAS_ORIENTATION = "orientation";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedDevicetypeCuid;
	private java.lang.String relatedShelftypeCuid;
	private java.math.BigDecimal sequence;
	private java.math.BigDecimal rowindevice;
	private java.math.BigDecimal colindevice;
	private java.math.BigDecimal orientation;
	//columns END


	public TmdDevicetypeShelftype(){
	}

	public TmdDevicetypeShelftype(
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


	
	
	public java.lang.String getRelatedDevicetypeCuid() {
		return this.relatedDevicetypeCuid;
	}
	
	public void setRelatedDevicetypeCuid(java.lang.String value) {
		this.relatedDevicetypeCuid = value;
	}

	
	public java.lang.String getRelatedShelftypeCuid() {
		return this.relatedShelftypeCuid;
	}
	
	public void setRelatedShelftypeCuid(java.lang.String value) {
		this.relatedShelftypeCuid = value;
	}

	
	public java.math.BigDecimal getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.math.BigDecimal value) {
		this.sequence = value;
	}

	
	public java.math.BigDecimal getRowindevice() {
		return this.rowindevice;
	}
	
	public void setRowindevice(java.math.BigDecimal value) {
		this.rowindevice = value;
	}

	
	public java.math.BigDecimal getColindevice() {
		return this.colindevice;
	}
	
	public void setColindevice(java.math.BigDecimal value) {
		this.colindevice = value;
	}

	
	public java.math.BigDecimal getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(java.math.BigDecimal value) {
		this.orientation = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedDevicetypeCuid",getRelatedDevicetypeCuid())
			.append("RelatedShelftypeCuid",getRelatedShelftypeCuid())
			.append("Sequence",getSequence())
			.append("Rowindevice",getRowindevice())
			.append("Colindevice",getColindevice())
			.append("Orientation",getOrientation())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedDevicetypeCuid())
			.append(getRelatedShelftypeCuid())
			.append(getSequence())
			.append(getRowindevice())
			.append(getColindevice())
			.append(getOrientation())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdDevicetypeShelftype == false) return false;
		if(this == obj) return true;
		TmdDevicetypeShelftype other = (TmdDevicetypeShelftype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedDevicetypeCuid(),other.getRelatedDevicetypeCuid())
			.append(getRelatedShelftypeCuid(),other.getRelatedShelftypeCuid())
			.append(getSequence(),other.getSequence())
			.append(getRowindevice(),other.getRowindevice())
			.append(getColindevice(),other.getColindevice())
			.append(getOrientation(),other.getOrientation())
			.isEquals();
	}
}
