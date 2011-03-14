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


public class TmdPackage2bmclasstype extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdPackage2bmclasstype";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_PACKAGE_ID = "packageId";
	public static final String ALIAS_TYPE_ID = "typeId";
	public static final String ALIAS_SORT = "sort";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String packageId;
	private java.lang.String typeId;
	private java.math.BigDecimal sort;
	//columns END


	public TmdPackage2bmclasstype(){
	}

	public TmdPackage2bmclasstype(
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

	
	public java.math.BigDecimal getSort() {
		return this.sort;
	}
	
	public void setSort(java.math.BigDecimal value) {
		this.sort = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("PackageId",getPackageId())
			.append("TypeId",getTypeId())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getPackageId())
			.append(getTypeId())
			.append(getSort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdPackage2bmclasstype == false) return false;
		if(this == obj) return true;
		TmdPackage2bmclasstype other = (TmdPackage2bmclasstype)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getPackageId(),other.getPackageId())
			.append(getTypeId(),other.getTypeId())
			.append(getSort(),other.getSort())
			.isEquals();
	}
}
