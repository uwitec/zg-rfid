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


public class FwDictionary extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_DICTIONARY";
	public static final String BM_CLASS_ID = "FW_DICTIONARY";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_DICT_BUSINESS_ID = "DICT_BUSINESS_ID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_VALUE = "VALUE";
	public static final String ALIAS_LABEL = "LABEL";
	public static final String ALIAS_DISPLAY_ORDER = "DISPLAY_ORDER";
	public static final String ALIAS_EXTEND = "EXTEND";
	public static final String DICT_BUSINESS_ID_FW_DICT_BUSINESS_FW_DICT_BUSINESS = "t0_0_1.t0_";
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
	private java.lang.String dictBusinessId_labelCn;
	private RelatedModel dictBusinessId_related = new RelatedModel("FW_DICT_BUSINESS","CUID","LABEL_CN");
	private java.lang.String dictBusinessId;
	private java.lang.String labelCn;
	private java.lang.String value;
	private java.lang.String label;
	private java.lang.Long displayOrder;
	private java.lang.String extend;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getDictBusinessId_labelCn() {
		return this.dictBusinessId_labelCn;
	}
	
	public void setDictBusinessId_labelCn(java.lang.String value) {
		this.dictBusinessId_labelCn = value;
	}
	
	public RelatedModel getDictBusinessId_related() {
		return this.dictBusinessId_related;
	}
	
	public void setDictBusinessId_related(RelatedModel value) {
		this.dictBusinessId_related = value;
	}
	public java.lang.String getDictBusinessId() {
		return this.dictBusinessId;
	}
	
	public void setDictBusinessId(java.lang.String value) {
		this.dictBusinessId = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	public java.lang.String getLabel() {
		return this.label;
	}
	
	public void setLabel(java.lang.String value) {
		this.label = value;
	}
	public java.lang.Long getDisplayOrder() {
		return this.displayOrder;
	}
	
	public void setDisplayOrder(java.lang.Long value) {
		this.displayOrder = value;
	}
	public java.lang.String getExtend() {
		return this.extend;
	}
	
	public void setExtend(java.lang.String value) {
		this.extend = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("DictBusinessId",getDictBusinessId())
			.append("LabelCn",getLabelCn())
			.append("Value",getValue())
			.append("Label",getLabel())
			.append("DisplayOrder",getDisplayOrder())
			.append("Extend",getExtend())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getDictBusinessId())
			.append(getLabelCn())
			.append(getValue())
			.append(getLabel())
			.append(getDisplayOrder())
			.append(getExtend())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwDictionary == false) return false;
		if(this == obj) return true;
		FwDictionary other = (FwDictionary)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getDictBusinessId(),other.getDictBusinessId())
			.append(getLabelCn(),other.getLabelCn())
			.append(getValue(),other.getValue())
			.append(getLabel(),other.getLabel())
			.append(getDisplayOrder(),other.getDisplayOrder())
			.append(getExtend(),other.getExtend())
			.isEquals();
	}
}
