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


public class TmdResourceIcon extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdResourceIcon";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_TYPE_CUID = "relatedTypeCuid";
	public static final String ALIAS_IMAGE_PATH = "只记录父路径和文件名";
	public static final String ALIAS_SMAGE_PATH = "一般用来树上做显示用";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedTypeCuid;
	private java.lang.String imagePath;
	private java.lang.String smagePath;
	//columns END


	public TmdResourceIcon(){
	}

	public TmdResourceIcon(
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


	
	
	public java.lang.String getRelatedTypeCuid() {
		return this.relatedTypeCuid;
	}
	
	public void setRelatedTypeCuid(java.lang.String value) {
		this.relatedTypeCuid = value;
	}

	
	public java.lang.String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(java.lang.String value) {
		this.imagePath = value;
	}

	
	public java.lang.String getSmagePath() {
		return this.smagePath;
	}
	
	public void setSmagePath(java.lang.String value) {
		this.smagePath = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedTypeCuid",getRelatedTypeCuid())
			.append("ImagePath",getImagePath())
			.append("SmagePath",getSmagePath())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedTypeCuid())
			.append(getImagePath())
			.append(getSmagePath())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdResourceIcon == false) return false;
		if(this == obj) return true;
		TmdResourceIcon other = (TmdResourceIcon)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedTypeCuid(),other.getRelatedTypeCuid())
			.append(getImagePath(),other.getImagePath())
			.append(getSmagePath(),other.getSmagePath())
			.isEquals();
	}
}
