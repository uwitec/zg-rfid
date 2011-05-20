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


public class TsysTreeType extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SYS_TREE_TYPE";
	public static final String BM_CLASS_ID = "T_SYS_TREE_TYPE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_BO_NAME = "BO_NAME";
	public static final String ALIAS_TREE_TYPE = "TREE_TYPE";
	public static final String ALIAS_RELATED_TEMPLATE_CUID = "RELATED_TEMPLATE_CUID";
	public static final String ALIAS_SORT = "SORT";
	public static final String ALIAS_RELATED_PARENT_CUID = "RELATED_PARENT_CUID";
	public static final String ALIAS_TREE_PARAM = "TREE_PARAM";
	public static final String RELATED_TEMPLATE_CUID_T_SYS_TREE_TEMPLATE_T_SYS_TREE_TEMPLATE = "t0_0_1.t0_";
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
	private java.lang.String boName;
	private java.lang.String treeType;
	private java.lang.String relatedTemplateCuid_labelCn;
	private RelatedModel relatedTemplateCuid_related = new RelatedModel("T_SYS_TREE_TEMPLATE","CUID","LABEL_CN");
	private java.lang.String relatedTemplateCuid;
	private java.lang.Long sort;
	private java.lang.String relatedParentCuid;
	private java.lang.String treeParam;
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
	public java.lang.String getBoName() {
		return this.boName;
	}
	
	public void setBoName(java.lang.String value) {
		this.boName = value;
	}
	public java.lang.String getTreeType() {
		return this.treeType;
	}
	
	public void setTreeType(java.lang.String value) {
		this.treeType = value;
	}
	public java.lang.String getRelatedTemplateCuid_labelCn() {
		return this.relatedTemplateCuid_labelCn;
	}
	
	public void setRelatedTemplateCuid_labelCn(java.lang.String value) {
		this.relatedTemplateCuid_labelCn = value;
	}
	
	public RelatedModel getRelatedTemplateCuid_related() {
		return this.relatedTemplateCuid_related;
	}
	
	public void setRelatedTemplateCuid_related(RelatedModel value) {
		this.relatedTemplateCuid_related = value;
	}
	public java.lang.String getRelatedTemplateCuid() {
		return this.relatedTemplateCuid;
	}
	
	public void setRelatedTemplateCuid(java.lang.String value) {
		this.relatedTemplateCuid = value;
	}
	public java.lang.Long getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Long value) {
		this.sort = value;
	}
	public java.lang.String getRelatedParentCuid() {
		return this.relatedParentCuid;
	}
	
	public void setRelatedParentCuid(java.lang.String value) {
		this.relatedParentCuid = value;
	}
	public java.lang.String getTreeParam() {
		return this.treeParam;
	}
	
	public void setTreeParam(java.lang.String value) {
		this.treeParam = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("BoName",getBoName())
			.append("TreeType",getTreeType())
			.append("RelatedTemplateCuid",getRelatedTemplateCuid())
			.append("Sort",getSort())
			.append("RelatedParentCuid",getRelatedParentCuid())
			.append("TreeParam",getTreeParam())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getBoName())
			.append(getTreeType())
			.append(getRelatedTemplateCuid())
			.append(getSort())
			.append(getRelatedParentCuid())
			.append(getTreeParam())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysTreeType == false) return false;
		if(this == obj) return true;
		TsysTreeType other = (TsysTreeType)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getBoName(),other.getBoName())
			.append(getTreeType(),other.getTreeType())
			.append(getRelatedTemplateCuid(),other.getRelatedTemplateCuid())
			.append(getSort(),other.getSort())
			.append(getRelatedParentCuid(),other.getRelatedParentCuid())
			.append(getTreeParam(),other.getTreeParam())
			.isEquals();
	}
}
