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


public class TmdRacktypeunit extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRacktypeunit";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_RACKTYPE_CUID = "relatedRacktypeCuid";
	public static final String ALIAS_SEQUENCE = "sequence";
	public static final String ALIAS_ROWINRACK = "rowinrack";
	public static final String ALIAS_COLINRACK = "colinrack";
	public static final String ALIAS_ORIENTATION = "orientation";
	public static final String ALIAS_ISAVAILABLE = "isavailable";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedRacktypeCuid;
	private java.math.BigDecimal sequence;
	private java.math.BigDecimal rowinrack;
	private java.math.BigDecimal colinrack;
	private java.math.BigDecimal orientation;
	private java.math.BigDecimal isavailable;
	//columns END


	public TmdRacktypeunit(){
	}

	public TmdRacktypeunit(
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


	
	
	public java.lang.String getRelatedRacktypeCuid() {
		return this.relatedRacktypeCuid;
	}
	
	public void setRelatedRacktypeCuid(java.lang.String value) {
		this.relatedRacktypeCuid = value;
	}

	
	public java.math.BigDecimal getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.math.BigDecimal value) {
		this.sequence = value;
	}

	
	public java.math.BigDecimal getRowinrack() {
		return this.rowinrack;
	}
	
	public void setRowinrack(java.math.BigDecimal value) {
		this.rowinrack = value;
	}

	
	public java.math.BigDecimal getColinrack() {
		return this.colinrack;
	}
	
	public void setColinrack(java.math.BigDecimal value) {
		this.colinrack = value;
	}

	
	public java.math.BigDecimal getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(java.math.BigDecimal value) {
		this.orientation = value;
	}

	
	public java.math.BigDecimal getIsavailable() {
		return this.isavailable;
	}
	
	public void setIsavailable(java.math.BigDecimal value) {
		this.isavailable = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedRacktypeCuid",getRelatedRacktypeCuid())
			.append("Sequence",getSequence())
			.append("Rowinrack",getRowinrack())
			.append("Colinrack",getColinrack())
			.append("Orientation",getOrientation())
			.append("Isavailable",getIsavailable())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedRacktypeCuid())
			.append(getSequence())
			.append(getRowinrack())
			.append(getColinrack())
			.append(getOrientation())
			.append(getIsavailable())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRacktypeunit == false) return false;
		if(this == obj) return true;
		TmdRacktypeunit other = (TmdRacktypeunit)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedRacktypeCuid(),other.getRelatedRacktypeCuid())
			.append(getSequence(),other.getSequence())
			.append(getRowinrack(),other.getRowinrack())
			.append(getColinrack(),other.getColinrack())
			.append(getOrientation(),other.getOrientation())
			.append(getIsavailable(),other.getIsavailable())
			.isEquals();
	}
}
