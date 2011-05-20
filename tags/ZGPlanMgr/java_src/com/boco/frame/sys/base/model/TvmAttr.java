/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.model;

import java.util.List;

import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class TvmAttr extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_VM_ATTR";
	public static final String BM_CLASS_ID = "T_VM_ATTR";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_BM_CLASS_ID = "BM_CLASS_ID";
	public static final String ALIAS_RELATED_GROUP_CUID = "RELATED_GROUP_CUID";
	public static final String ALIAS_STYLE = "STYLE";
	public static final String RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP = "t0_0_1.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getLabelCn()==null?"":this.getLabelCn().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String bmClassId;
	private java.lang.String relatedGroupCuid_labelCn;
	private RelatedModel relatedGroupCuid_related = new RelatedModel("T_VM_GROUP","CUID","LABEL_CN");
	private java.lang.String relatedGroupCuid;
	private java.lang.String style;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getBmClassId() {
		return this.bmClassId;
	}
	
	public void setBmClassId(java.lang.String value) {
		this.bmClassId = value;
	}
	public java.lang.String getRelatedGroupCuid_labelCn() {
		return this.relatedGroupCuid_labelCn;
	}
	
	public void setRelatedGroupCuid_labelCn(java.lang.String value) {
		this.relatedGroupCuid_labelCn = value;
	}
	
	public RelatedModel getRelatedGroupCuid_related() {
		return this.relatedGroupCuid_related;
	}
	
	public void setRelatedGroupCuid_related(RelatedModel value) {
		this.relatedGroupCuid_related = value;
	}
	public java.lang.String getRelatedGroupCuid() {
		return this.relatedGroupCuid;
	}
	
	public void setRelatedGroupCuid(java.lang.String value) {
		this.relatedGroupCuid = value;
	}
	public java.lang.String getStyle() {
		return this.style;
	}
	
	public void setStyle(java.lang.String value) {
		this.style = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("BmClassId",getBmClassId())
			.append("RelatedGroupCuid",getRelatedGroupCuid())
			.append("Style",getStyle())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getBmClassId())
			.append(getRelatedGroupCuid())
			.append(getStyle())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TvmAttr == false) return false;
		if(this == obj) return true;
		TvmAttr other = (TvmAttr)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getBmClassId(),other.getBmClassId())
			.append(getRelatedGroupCuid(),other.getRelatedGroupCuid())
			.append(getStyle(),other.getStyle())
			.isEquals();
	}
}
