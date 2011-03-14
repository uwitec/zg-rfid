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


public class TmdNetworktype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdNetworktype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_LAYOUT = "layout";
	public static final String ALIAS_NODE = "node";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.math.BigDecimal layout;
	private java.lang.String node;
	private java.lang.String bmClassid;
	//columns END


	public TmdNetworktype(){
	}

	public TmdNetworktype(
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

	
	public java.math.BigDecimal getLayout() {
		return this.layout;
	}
	
	public void setLayout(java.math.BigDecimal value) {
		this.layout = value;
	}

	
	public java.lang.String getNode() {
		return this.node;
	}
	
	public void setNode(java.lang.String value) {
		this.node = value;
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
			.append("Layout",getLayout())
			.append("Node",getNode())
			.append("BmClassid",getBmClassid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getLayout())
			.append(getNode())
			.append(getBmClassid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdNetworktype == false) return false;
		if(this == obj) return true;
		TmdNetworktype other = (TmdNetworktype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getLayout(),other.getLayout())
			.append(getNode(),other.getNode())
			.append(getBmClassid(),other.getBmClassid())
			.isEquals();
	}
}
