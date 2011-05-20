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


public class TvmGroup extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_VM_GROUP";
	public static final String BM_CLASS_ID = "T_VM_GROUP";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_BM_CLASS_ID = "BM_CLASS_ID";
	public static final String ALIAS_RELATED_USER_CUID = "RELATED_USER_CUID";
	public static final String ALIAS_TYPE = "TYPE";
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
	private java.lang.String relatedUserCuid;
	private java.lang.String type;
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
	public java.lang.String getRelatedUserCuid() {
		return this.relatedUserCuid;
	}
	
	public void setRelatedUserCuid(java.lang.String value) {
		this.relatedUserCuid = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("BmClassId",getBmClassId())
			.append("RelatedUserCuid",getRelatedUserCuid())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getBmClassId())
			.append(getRelatedUserCuid())
			.append(getType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TvmGroup == false) return false;
		if(this == obj) return true;
		TvmGroup other = (TvmGroup)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getBmClassId(),other.getBmClassId())
			.append(getRelatedUserCuid(),other.getRelatedUserCuid())
			.append(getType(),other.getType())
			.isEquals();
	}
}
