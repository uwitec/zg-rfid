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


public class TmdShelftypeSlottype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdShelftypeSlottype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_SHELFTYPE_CUID = "relatedShelftypeCuid";
	public static final String ALIAS_RELATED_SLOTTYPE_CUID = "relatedSlottypeCuid";
	public static final String ALIAS_SEQUENCE = "sequence";
	public static final String ALIAS_ROWINSHELF = "rowinshelf";
	public static final String ALIAS_COLINSHELF = "colinshelf";
	public static final String ALIAS_ORIENTATION = "orientation";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedShelftypeCuid;
	private java.lang.String relatedSlottypeCuid;
	private java.math.BigDecimal sequence;
	private java.math.BigDecimal rowinshelf;
	private java.math.BigDecimal colinshelf;
	private java.math.BigDecimal orientation;
	//columns END


	public TmdShelftypeSlottype(){
	}

	public TmdShelftypeSlottype(
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


	
	
	public java.lang.String getRelatedShelftypeCuid() {
		return this.relatedShelftypeCuid;
	}
	
	public void setRelatedShelftypeCuid(java.lang.String value) {
		this.relatedShelftypeCuid = value;
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

	
	public java.math.BigDecimal getRowinshelf() {
		return this.rowinshelf;
	}
	
	public void setRowinshelf(java.math.BigDecimal value) {
		this.rowinshelf = value;
	}

	
	public java.math.BigDecimal getColinshelf() {
		return this.colinshelf;
	}
	
	public void setColinshelf(java.math.BigDecimal value) {
		this.colinshelf = value;
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
			.append("RelatedShelftypeCuid",getRelatedShelftypeCuid())
			.append("RelatedSlottypeCuid",getRelatedSlottypeCuid())
			.append("Sequence",getSequence())
			.append("Rowinshelf",getRowinshelf())
			.append("Colinshelf",getColinshelf())
			.append("Orientation",getOrientation())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedShelftypeCuid())
			.append(getRelatedSlottypeCuid())
			.append(getSequence())
			.append(getRowinshelf())
			.append(getColinshelf())
			.append(getOrientation())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdShelftypeSlottype == false) return false;
		if(this == obj) return true;
		TmdShelftypeSlottype other = (TmdShelftypeSlottype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedShelftypeCuid(),other.getRelatedShelftypeCuid())
			.append(getRelatedSlottypeCuid(),other.getRelatedSlottypeCuid())
			.append(getSequence(),other.getSequence())
			.append(getRowinshelf(),other.getRowinshelf())
			.append(getColinshelf(),other.getColinshelf())
			.append(getOrientation(),other.getOrientation())
			.isEquals();
	}
}
