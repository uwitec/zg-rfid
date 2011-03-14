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


public class TmdCardtypeSlottype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdCardtypeSlottype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_CARDTYPE_CUID = "relatedCardtypeCuid";
	public static final String ALIAS_RELATED_SLOTTYPE_CUID = "relatedSlottypeCuid";
	public static final String ALIAS_SEQUENCE = "sequence";
	public static final String ALIAS_ROWINCARD = "rowincard";
	public static final String ALIAS_COLINCARD = "colincard";
	public static final String ALIAS_ORIENTATION = "orientation";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedCardtypeCuid;
	private java.lang.String relatedSlottypeCuid;
	private java.math.BigDecimal sequence;
	private java.math.BigDecimal rowincard;
	private java.math.BigDecimal colincard;
	private java.math.BigDecimal orientation;
	//columns END


	public TmdCardtypeSlottype(){
	}

	public TmdCardtypeSlottype(
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


	
	
	public java.lang.String getRelatedCardtypeCuid() {
		return this.relatedCardtypeCuid;
	}
	
	public void setRelatedCardtypeCuid(java.lang.String value) {
		this.relatedCardtypeCuid = value;
	}

	
	public java.lang.String getRelatedSlottypeCuid() {
		return this.relatedSlottypeCuid;
	}
	
	public void setRelatedSlottypeCuid(java.lang.String value) {
		this.relatedSlottypeCuid = value;
	}

	
	public java.math.BigDecimal getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.math.BigDecimal value) {
		this.sequence = value;
	}

	
	public java.math.BigDecimal getRowincard() {
		return this.rowincard;
	}
	
	public void setRowincard(java.math.BigDecimal value) {
		this.rowincard = value;
	}

	
	public java.math.BigDecimal getColincard() {
		return this.colincard;
	}
	
	public void setColincard(java.math.BigDecimal value) {
		this.colincard = value;
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
			.append("RelatedCardtypeCuid",getRelatedCardtypeCuid())
			.append("RelatedSlottypeCuid",getRelatedSlottypeCuid())
			.append("Sequence",getSequence())
			.append("Rowincard",getRowincard())
			.append("Colincard",getColincard())
			.append("Orientation",getOrientation())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedCardtypeCuid())
			.append(getRelatedSlottypeCuid())
			.append(getSequence())
			.append(getRowincard())
			.append(getColincard())
			.append(getOrientation())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdCardtypeSlottype == false) return false;
		if(this == obj) return true;
		TmdCardtypeSlottype other = (TmdCardtypeSlottype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedCardtypeCuid(),other.getRelatedCardtypeCuid())
			.append(getRelatedSlottypeCuid(),other.getRelatedSlottypeCuid())
			.append(getSequence(),other.getSequence())
			.append(getRowincard(),other.getRowincard())
			.append(getColincard(),other.getColincard())
			.append(getOrientation(),other.getOrientation())
			.isEquals();
	}
}
