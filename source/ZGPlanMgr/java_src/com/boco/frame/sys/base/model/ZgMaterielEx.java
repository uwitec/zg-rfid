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

import com.boco.zg.materiel.base.model.*;
import com.boco.zg.materiel.base.dao.*;
import com.boco.zg.materiel.base.service.*;

public class ZgMaterielEx extends BaseEntity {


	//alias
	public static final String TABLE_ALIAS = "ZG_MATERIEL";
	public static final String BM_CLASS_ID = "ZG_MATERIEL";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_PARENT_ID = "上层仓库物料组";
	public static final String ALIAS_MATERIEL_NAME = "物料组名称";
	public static final String ALIAS_TYPE = "标识";
	public static final String ALIAS_ID = "物料组仓库编号";
	
	public static final String ALIAS_VIRTUALORG_CUID="领料组ID";
	public static final String ALIAS_VIRTUALORG_NAME="领料组名称";
	public static final String ALIAS_VIRTUALORG_TYPE="领料组类型";
	public static final String ALIAS_VIRTUALORG_NOTE="领料组描述";
	public static final String ALIAS_ORGANIZATION_NAME="所属部门";
	//date formats
	
	
	private String cuid;
	private String id;
	private String parentId;
	private String materielName;
	private Long type;
	private String lgort;//真正仓库ID
	
	private String virtualorgCuid;//虚拟组ID
	private String virtualorgName;//虚拟组名字
	private String virtualorgType;//虚拟组类型
	private String virtualorgNote;//虚拟组描述
	private String organizationName;//所属部门
	private String organizationId;//所属部门ID
	private String advance;
	
	private String zgMaterielVirtualorg;//物料组虚拟领料组关系表 的主键
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("ParentId",getParentId())
			.append("MaterielName",getMaterielName())
			.append("Type",getType())
			.append("Id",getId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getParentId())
			.append(getMaterielName())
			.append(getType())
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgMaterielEx == false) return false;
		if(this == obj) return true;
		ZgMaterielEx other = (ZgMaterielEx)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getParentId(),other.getParentId())
			.append(getMaterielName(),other.getMaterielName())
			.append(getType(),other.getType())
			.append(getId(),other.getId())
			.isEquals();
	}
	public String getVirtualorgCuid() {
		return virtualorgCuid;
	}
	public void setVirtualorgCuid(String virtualorgCuid) {
		this.virtualorgCuid = virtualorgCuid;
	}
	public String getVirtualorgName() {
		return virtualorgName;
	}
	public void setVirtualorgName(String virtualorgName) {
		this.virtualorgName = virtualorgName;
	}
	public String getVirtualorgType() {
		return virtualorgType;
	}
	public void setVirtualorgType(String virtualorgType) {
		this.virtualorgType=virtualorgType;
	}
	public String getVirtualorgNote() {
		return virtualorgNote;
	}
	public void setVirtualorgNote(String virtualorgNote) {
		this.virtualorgNote = virtualorgNote;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getZgMaterielVirtualorg() {
		return zgMaterielVirtualorg;
	}
	public void setZgMaterielVirtualorg(String zgMaterielVirtualorg) {
		this.zgMaterielVirtualorg = zgMaterielVirtualorg;
	}
	public String getLgort() {
		return lgort;
	}
	public void setLgort(String lgort) {
		this.lgort = lgort;
	}
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
}
