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


public class TmdResRes extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdResRes";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_LEFT_CUID = "relatedLeftCuid";
	public static final String ALIAS_RELATED_L_TYPE_CUID = "relatedLtypeCuid";
	public static final String ALIAS_RELATED_RIGHT_CUID = "relatedRightCuid";
	public static final String ALIAS_RELATED_R_TYPE_CUID = "relatedRtypeCuid";
	public static final String ALIAS_RELATED_RELATION_CUID = "relatedRelationCuid";
	public static final String ALIAS_DESC1 = "desc1";
	public static final String ALIAS_DESC2 = "desc2";
	public static final String ALIAS_DESC3 = "desc3";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedLeftCuid;
	private java.lang.String relatedLtypeCuid;
	private java.lang.String relatedRightCuid;
	private java.lang.String relatedRtypeCuid;
	private java.lang.String relatedRelationCuid;
	private java.lang.String desc1;
	private java.lang.String desc2;
	private java.lang.String desc3;
	//columns END


	public TmdResRes(){
	}

	public TmdResRes(
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


	
	
	public java.lang.String getRelatedLeftCuid() {
		return this.relatedLeftCuid;
	}
	
	public void setRelatedLeftCuid(java.lang.String value) {
		this.relatedLeftCuid = value;
	}

	
	public java.lang.String getRelatedLtypeCuid() {
		return this.relatedLtypeCuid;
	}
	
	public void setRelatedLtypeCuid(java.lang.String value) {
		this.relatedLtypeCuid = value;
	}

	
	public java.lang.String getRelatedRightCuid() {
		return this.relatedRightCuid;
	}
	
	public void setRelatedRightCuid(java.lang.String value) {
		this.relatedRightCuid = value;
	}

	
	public java.lang.String getRelatedRtypeCuid() {
		return this.relatedRtypeCuid;
	}
	
	public void setRelatedRtypeCuid(java.lang.String value) {
		this.relatedRtypeCuid = value;
	}

	
	public java.lang.String getRelatedRelationCuid() {
		return this.relatedRelationCuid;
	}
	
	public void setRelatedRelationCuid(java.lang.String value) {
		this.relatedRelationCuid = value;
	}

	
	public java.lang.String getDesc1() {
		return this.desc1;
	}
	
	public void setDesc1(java.lang.String value) {
		this.desc1 = value;
	}

	
	public java.lang.String getDesc2() {
		return this.desc2;
	}
	
	public void setDesc2(java.lang.String value) {
		this.desc2 = value;
	}

	
	public java.lang.String getDesc3() {
		return this.desc3;
	}
	
	public void setDesc3(java.lang.String value) {
		this.desc3 = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedLeftCuid",getRelatedLeftCuid())
			.append("RelatedLtypeCuid",getRelatedLtypeCuid())
			.append("RelatedRightCuid",getRelatedRightCuid())
			.append("RelatedRtypeCuid",getRelatedRtypeCuid())
			.append("RelatedRelationCuid",getRelatedRelationCuid())
			.append("Desc1",getDesc1())
			.append("Desc2",getDesc2())
			.append("Desc3",getDesc3())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedLeftCuid())
			.append(getRelatedLtypeCuid())
			.append(getRelatedRightCuid())
			.append(getRelatedRtypeCuid())
			.append(getRelatedRelationCuid())
			.append(getDesc1())
			.append(getDesc2())
			.append(getDesc3())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdResRes == false) return false;
		if(this == obj) return true;
		TmdResRes other = (TmdResRes)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedLeftCuid(),other.getRelatedLeftCuid())
			.append(getRelatedLtypeCuid(),other.getRelatedLtypeCuid())
			.append(getRelatedRightCuid(),other.getRelatedRightCuid())
			.append(getRelatedRtypeCuid(),other.getRelatedRtypeCuid())
			.append(getRelatedRelationCuid(),other.getRelatedRelationCuid())
			.append(getDesc1(),other.getDesc1())
			.append(getDesc2(),other.getDesc2())
			.append(getDesc3(),other.getDesc3())
			.isEquals();
	}
}
