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


public class FwMenu extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_MENU";
	public static final String BM_CLASS_ID = "FW_MENU";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_PARENT_MENU_ID = "PARENT_MENU_ID";
	public static final String ALIAS_URL = "URL";
	public static final String ALIAS_DISPLAY_ORDER = "DISPLAY_ORDER";
	public static final String ALIAS_SEQ = "SEQ";
	public static final String ALIAS_LEVEL_NUM = "LEVEL_NUM";
	public static final String ALIAS_TYPE = "TYPE";
	public static final String PARENT_MENU_ID_FW_MENU_FW_MENU = "t0_0_1.t0_";
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
	private java.lang.String parentMenuId_labelCn;
	private RelatedModel parentMenuId_related = new RelatedModel("FW_MENU","CUID","LABEL_CN");
	private java.lang.String parentMenuId;
	private java.lang.String url;
	private java.lang.Long displayOrder;
	private java.lang.String seq;
	private java.lang.Long levelNum;
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
	public java.lang.String getParentMenuId_labelCn() {
		return this.parentMenuId_labelCn;
	}
	
	public void setParentMenuId_labelCn(java.lang.String value) {
		this.parentMenuId_labelCn = value;
	}
	
	public RelatedModel getParentMenuId_related() {
		return this.parentMenuId_related;
	}
	
	public void setParentMenuId_related(RelatedModel value) {
		this.parentMenuId_related = value;
	}
	public java.lang.String getParentMenuId() {
		return this.parentMenuId;
	}
	
	public void setParentMenuId(java.lang.String value) {
		this.parentMenuId = value;
	}
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	public java.lang.Long getDisplayOrder() {
		return this.displayOrder;
	}
	
	public void setDisplayOrder(java.lang.Long value) {
		this.displayOrder = value;
	}
	public java.lang.String getSeq() {
		return this.seq;
	}
	
	public void setSeq(java.lang.String value) {
		this.seq = value;
	}
	public java.lang.Long getLevelNum() {
		return this.levelNum;
	}
	
	public void setLevelNum(java.lang.Long value) {
		this.levelNum = value;
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
			.append("ParentMenuId",getParentMenuId())
			.append("Url",getUrl())
			.append("DisplayOrder",getDisplayOrder())
			.append("Seq",getSeq())
			.append("LevelNum",getLevelNum())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getParentMenuId())
			.append(getUrl())
			.append(getDisplayOrder())
			.append(getSeq())
			.append(getLevelNum())
			.append(getType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwMenu == false) return false;
		if(this == obj) return true;
		FwMenu other = (FwMenu)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getParentMenuId(),other.getParentMenuId())
			.append(getUrl(),other.getUrl())
			.append(getDisplayOrder(),other.getDisplayOrder())
			.append(getSeq(),other.getSeq())
			.append(getLevelNum(),other.getLevelNum())
			.append(getType(),other.getType())
			.isEquals();
	}
}
