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


public class TmdPackagetypeRestype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdPackagetypeRestype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_PACKAGE_ID = "packageId";
	public static final String ALIAS_TYPE_ID = "typeId";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String packageId;
	private java.lang.String typeId;
	//columns END


	public TmdPackagetypeRestype(){
	}

	public TmdPackagetypeRestype(
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


	
	
	public java.lang.String getPackageId() {
		return this.packageId;
	}
	
	public void setPackageId(java.lang.String value) {
		this.packageId = value;
	}

	
	public java.lang.String getTypeId() {
		return this.typeId;
	}
	
	public void setTypeId(java.lang.String value) {
		this.typeId = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("PackageId",getPackageId())
			.append("TypeId",getTypeId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getPackageId())
			.append(getTypeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdPackagetypeRestype == false) return false;
		if(this == obj) return true;
		TmdPackagetypeRestype other = (TmdPackagetypeRestype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getPackageId(),other.getPackageId())
			.append(getTypeId(),other.getTypeId())
			.isEquals();
	}
}
