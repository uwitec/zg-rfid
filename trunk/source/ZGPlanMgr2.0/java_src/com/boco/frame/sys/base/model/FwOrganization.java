/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.model;

import java.util.List;

import javacommon.base.BaseEntity;
import javacommon.base.model.EnumModel;
import javacommon.base.model.RelatedModel;
import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwOrganization extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_ORGANIZATION";
	public static final String BM_CLASS_ID = "FW_ORGANIZATION";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "机构名称";
	public static final String ALIAS_PARENT_ORG_ID = "上层机构";
	public static final String ALIAS_LEVEL_NUM = "等级";
	public static final String ALIAS_SEQ = "序列";
	public static final String ALIAS_NOTE = "描述";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_RELATED_BM_CLASS_ID = "RELATED_BM_CLASS_ID";
	public static final String PARENT_ORG_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_0_1.t0_";
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
	private java.lang.String parentOrgId_labelCn;
	private RelatedModel parentOrgId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String parentOrgId;
	private java.lang.Long levelNum;
	private java.lang.String seq;
	private java.lang.String note;
	private java.lang.String type;
	private java.lang.String principalsName;//负责人(全部)name
	private java.lang.String principalsId;//负责人(全部)ID
	private EnumModel type_enum = new EnumModel("SEX");
	private java.lang.String relatedBmClassId;
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
	public java.lang.String getParentOrgId_labelCn() {
		return this.parentOrgId_labelCn;
	}
	
	public void setParentOrgId_labelCn(java.lang.String value) {
		this.parentOrgId_labelCn = value;
	}
	
	public RelatedModel getParentOrgId_related() {
		return this.parentOrgId_related;
	}
	
	public void setParentOrgId_related(RelatedModel value) {
		this.parentOrgId_related = value;
	}
	public java.lang.String getParentOrgId() {
		return this.parentOrgId;
	}
	
	public void setParentOrgId(java.lang.String value) {
		this.parentOrgId = value;
	}
	public java.lang.Long getLevelNum() {
		return this.levelNum;
	}
	
	public void setLevelNum(java.lang.Long value) {
		this.levelNum = value;
	}
	public java.lang.String getSeq() {
		return this.seq;
	}
	
	public void setSeq(java.lang.String value) {
		this.seq = value;
	}
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getRelatedBmClassId() {
		return this.relatedBmClassId;
	}
	
	public void setRelatedBmClassId(java.lang.String value) {
		this.relatedBmClassId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("ParentOrgId",getParentOrgId())
			.append("LevelNum",getLevelNum())
			.append("Seq",getSeq())
			.append("Note",getNote())
			.append("Type",getType())
			.append("RelatedBmClassId",getRelatedBmClassId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getParentOrgId())
			.append(getLevelNum())
			.append(getSeq())
			.append(getNote())
			.append(getType())
			.append(getRelatedBmClassId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwOrganization == false) return false;
		if(this == obj) return true;
		FwOrganization other = (FwOrganization)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getParentOrgId(),other.getParentOrgId())
			.append(getLevelNum(),other.getLevelNum())
			.append(getSeq(),other.getSeq())
			.append(getNote(),other.getNote())
			.append(getType(),other.getType())
			.append(getRelatedBmClassId(),other.getRelatedBmClassId())
			.isEquals();
	}

	public EnumModel getType_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("ORG_TYPE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getType())){
				this.type_enum.setValue(value.getName());
				this.type_enum.setId(this.getType());
			}
		}
		return this.type_enum;
	}

	public void setType_enum(EnumModel type_enum) {
		this.type_enum = type_enum;
	}

	public java.lang.String getPrincipalsId() {
		return principalsId;
	}

	public void setPrincipalsId(java.lang.String principalsId) {
		this.principalsId = principalsId;
	}

	public java.lang.String getPrincipalsName() {
		return principalsName;
	}

	public void setPrincipalsName(java.lang.String principalsName) {
		this.principalsName = principalsName;
	}

	
}
