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


public class TsysContextmenu extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SYS_CONTEXTMENU";
	public static final String BM_CLASS_ID = "T_SYS_CONTEXTMENU";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_RELATED_BM_CLASS_ID = "资源类别";
	public static final String ALIAS_RELATED_RESCLASS_CUID = "所属大类";
	public static final String ALIAS_ICON_PATH = "图标路径";
	public static final String ALIAS_GROUP_NAME = "所属分组";
	public static final String ALIAS_HANDLER = "操作句柄";
	public static final String ALIAS_TARGET_ID = "目标路径";
	public static final String ALIAS_EXTEND1 = "扩展字段1";
	public static final String ALIAS_EXTEND2 = "扩展字段2";
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
	private java.lang.String relatedBmClassId;
	private java.lang.String relatedResclassCuid;
	private java.lang.String iconPath;
	private java.lang.String groupName;
	private java.lang.String handler;
	private java.lang.String targetId;
	private java.lang.String extend1;
	private java.lang.String extend2;
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
	public java.lang.String getRelatedBmClassId() {
		return this.relatedBmClassId;
	}
	
	public void setRelatedBmClassId(java.lang.String value) {
		this.relatedBmClassId = value;
	}
	public java.lang.String getRelatedResclassCuid() {
		return this.relatedResclassCuid;
	}
	
	public void setRelatedResclassCuid(java.lang.String value) {
		this.relatedResclassCuid = value;
	}
	public java.lang.String getIconPath() {
		return this.iconPath;
	}
	
	public void setIconPath(java.lang.String value) {
		this.iconPath = value;
	}
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	public java.lang.String getHandler() {
		return this.handler;
	}
	
	public void setHandler(java.lang.String value) {
		this.handler = value;
	}
	public java.lang.String getTargetId() {
		return this.targetId;
	}
	
	public void setTargetId(java.lang.String value) {
		this.targetId = value;
	}
	public java.lang.String getExtend1() {
		return this.extend1;
	}
	
	public void setExtend1(java.lang.String value) {
		this.extend1 = value;
	}
	public java.lang.String getExtend2() {
		return this.extend2;
	}
	
	public void setExtend2(java.lang.String value) {
		this.extend2 = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("RelatedBmClassId",getRelatedBmClassId())
			.append("RelatedResclassCuid",getRelatedResclassCuid())
			.append("IconPath",getIconPath())
			.append("GroupName",getGroupName())
			.append("Handler",getHandler())
			.append("TargetId",getTargetId())
			.append("Extend1",getExtend1())
			.append("Extend2",getExtend2())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getRelatedBmClassId())
			.append(getRelatedResclassCuid())
			.append(getIconPath())
			.append(getGroupName())
			.append(getHandler())
			.append(getTargetId())
			.append(getExtend1())
			.append(getExtend2())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysContextmenu == false) return false;
		if(this == obj) return true;
		TsysContextmenu other = (TsysContextmenu)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getRelatedBmClassId(),other.getRelatedBmClassId())
			.append(getRelatedResclassCuid(),other.getRelatedResclassCuid())
			.append(getIconPath(),other.getIconPath())
			.append(getGroupName(),other.getGroupName())
			.append(getHandler(),other.getHandler())
			.append(getTargetId(),other.getTargetId())
			.append(getExtend1(),other.getExtend1())
			.append(getExtend2(),other.getExtend2())
			.isEquals();
	}
}
