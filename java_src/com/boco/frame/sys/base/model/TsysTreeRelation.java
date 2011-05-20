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


public class TsysTreeRelation extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SYS_TREE_RELATION";
	public static final String BM_CLASS_ID = "T_SYS_TREE_RELATION";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_TEMPLATE_CUID1 = "TEMPLATE_CUID1";
	public static final String ALIAS_TEMPLATE_CUID2 = "TEMPLATE_CUID2";
	public static final String ALIAS_REMARK = "REMARK";
	public static final String TEMPLATE_CUID1_T_SYS_TREE_TEMPLATE_T_SYS_TREE_TEMPLATE = "t0_0_1.t0_";
	public static final String TEMPLATE_CUID2_T_SYS_TREE_TEMPLATE_T_SYS_TREE_TEMPLATE = "t0_1_2.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String templateCuid1_labelCn;
	private RelatedModel templateCuid1_related = new RelatedModel("T_SYS_TREE_TEMPLATE","CUID","LABEL_CN");
	private java.lang.String templateCuid1;
	private java.lang.String templateCuid2_labelCn;
	private RelatedModel templateCuid2_related = new RelatedModel("T_SYS_TREE_TEMPLATE","CUID","LABEL_CN");
	private java.lang.String templateCuid2;
	private java.lang.String remark;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getTemplateCuid1_labelCn() {
		return this.templateCuid1_labelCn;
	}
	
	public void setTemplateCuid1_labelCn(java.lang.String value) {
		this.templateCuid1_labelCn = value;
	}
	
	public RelatedModel getTemplateCuid1_related() {
		return this.templateCuid1_related;
	}
	
	public void setTemplateCuid1_related(RelatedModel value) {
		this.templateCuid1_related = value;
	}
	public java.lang.String getTemplateCuid1() {
		return this.templateCuid1;
	}
	
	public void setTemplateCuid1(java.lang.String value) {
		this.templateCuid1 = value;
	}
	public java.lang.String getTemplateCuid2_labelCn() {
		return this.templateCuid2_labelCn;
	}
	
	public void setTemplateCuid2_labelCn(java.lang.String value) {
		this.templateCuid2_labelCn = value;
	}
	
	public RelatedModel getTemplateCuid2_related() {
		return this.templateCuid2_related;
	}
	
	public void setTemplateCuid2_related(RelatedModel value) {
		this.templateCuid2_related = value;
	}
	public java.lang.String getTemplateCuid2() {
		return this.templateCuid2;
	}
	
	public void setTemplateCuid2(java.lang.String value) {
		this.templateCuid2 = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("TemplateCuid1",getTemplateCuid1())
			.append("TemplateCuid2",getTemplateCuid2())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getTemplateCuid1())
			.append(getTemplateCuid2())
			.append(getRemark())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysTreeRelation == false) return false;
		if(this == obj) return true;
		TsysTreeRelation other = (TsysTreeRelation)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getTemplateCuid1(),other.getTemplateCuid1())
			.append(getTemplateCuid2(),other.getTemplateCuid2())
			.append(getRemark(),other.getRemark())
			.isEquals();
	}
}
