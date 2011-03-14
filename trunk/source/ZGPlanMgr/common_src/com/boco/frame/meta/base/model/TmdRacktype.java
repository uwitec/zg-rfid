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


public class TmdRacktype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdRacktype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_MANUFACTORY = "manufactory";
	public static final String ALIAS_WIDTH = "width";
	public static final String ALIAS_DEPTH = "depth";
	public static final String ALIAS_HEIGHT = "height";
	public static final String ALIAS_DYNUNITFLAG = "dynunitflag";
	public static final String ALIAS_POWERSUPPLIED = "powersupplied";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String manufactory;
	private java.math.BigDecimal width;
	private java.math.BigDecimal depth;
	private java.math.BigDecimal height;
	private java.math.BigDecimal dynunitflag;
	private java.math.BigDecimal powersupplied;
	private java.lang.String bmClassid;
	//columns END


	public TmdRacktype(){
	}

	public TmdRacktype(
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

	
	public java.lang.String getManufactory() {
		return this.manufactory;
	}
	
	public void setManufactory(java.lang.String value) {
		this.manufactory = value;
	}

	
	public java.math.BigDecimal getWidth() {
		return this.width;
	}
	
	public void setWidth(java.math.BigDecimal value) {
		this.width = value;
	}

	
	public java.math.BigDecimal getDepth() {
		return this.depth;
	}
	
	public void setDepth(java.math.BigDecimal value) {
		this.depth = value;
	}

	
	public java.math.BigDecimal getHeight() {
		return this.height;
	}
	
	public void setHeight(java.math.BigDecimal value) {
		this.height = value;
	}

	
	public java.math.BigDecimal getDynunitflag() {
		return this.dynunitflag;
	}
	
	public void setDynunitflag(java.math.BigDecimal value) {
		this.dynunitflag = value;
	}

	
	public java.math.BigDecimal getPowersupplied() {
		return this.powersupplied;
	}
	
	public void setPowersupplied(java.math.BigDecimal value) {
		this.powersupplied = value;
	}

	
	public java.lang.String getBmClassid() {
		return this.bmClassid;
	}
	
	public void setBmClassid(java.lang.String value) {
		this.bmClassid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("Manufactory",getManufactory())
			.append("Width",getWidth())
			.append("Depth",getDepth())
			.append("Height",getHeight())
			.append("Dynunitflag",getDynunitflag())
			.append("Powersupplied",getPowersupplied())
			.append("BmClassid",getBmClassid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getManufactory())
			.append(getWidth())
			.append(getDepth())
			.append(getHeight())
			.append(getDynunitflag())
			.append(getPowersupplied())
			.append(getBmClassid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdRacktype == false) return false;
		if(this == obj) return true;
		TmdRacktype other = (TmdRacktype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getManufactory(),other.getManufactory())
			.append(getWidth(),other.getWidth())
			.append(getDepth(),other.getDepth())
			.append(getHeight(),other.getHeight())
			.append(getDynunitflag(),other.getDynunitflag())
			.append(getPowersupplied(),other.getPowersupplied())
			.append(getBmClassid(),other.getBmClassid())
			.isEquals();
	}
}
