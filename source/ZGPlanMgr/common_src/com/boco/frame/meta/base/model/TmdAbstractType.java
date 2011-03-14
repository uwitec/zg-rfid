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


public class TmdAbstractType extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdAbstractType";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_PARENT_TYPE_CUID = "parentTypeCuid";
	public static final String ALIAS_BM_CLASSID = "bmClassid";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_RELATED_RESCLASS_CUID = "relatedResclassCuid";
	public static final String ALIAS_LABEL_ATTR_ID = "labelAttrId";
	public static final String ALIAS_LABEL_JOIN_CHAR = "labelJoinChar";
	public static final String ALIAS_OBJECT_SELECT_XTYPE = "objectSelectXtype";
	public static final String ALIAS_IS_SHOW_ADD_BTN = "isShowAddBtn";
	public static final String ALIAS_IS_SHOW_EDIT_BTN = "isShowEditBtn";
	public static final String ALIAS_IS_SHOW_DELETE_BTN = "isShowDeleteBtn";
	public static final String ALIAS_IS_SHOW_EXPORT_BTN = "isShowExportBtn";
	public static final String ALIAS_IS_SHOW_IMPORT_BTN = "isShowImportBtn";
	public static final String ALIAS_IS_VIRTUAL = "isVirtual";
	public static final String ALIAS_PK_COL = "pkCol";
	public static final String ALIAS_TYPE_TABLE_NAME = "typeTableName";
	public static final String ALIAS_FLAG = "flag";
	public static final String ALIAS_USETYPE = "usetype";
	public static final String ALIAS_IS_SHOW_TYPE = "isShowType";
	public static final String ALIAS_TREE_SORT = "treeSort";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String parentTypeCuid;
	private java.lang.String bmClassid;
	private java.lang.String labelCn;
	private java.lang.String relatedResclassCuid;
	private java.lang.String labelAttrId;
	private java.lang.String labelJoinChar;
	private java.lang.String objectSelectXtype;
	private java.math.BigDecimal isShowAddBtn;
	private java.math.BigDecimal isShowEditBtn;
	private java.math.BigDecimal isShowDeleteBtn;
	private java.math.BigDecimal isShowExportBtn;
	private java.math.BigDecimal isShowImportBtn;
	private java.math.BigDecimal isVirtual;
	private java.lang.String pkCol;
	private java.lang.String typeTableName;
	private java.lang.String flag;
	private java.lang.String usetype;
	private java.lang.String isShowType;
	private java.lang.String treeSort;
	//columns END


	public TmdAbstractType(){
	}

	public TmdAbstractType(
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


	
	
	public java.lang.String getParentTypeCuid() {
		return this.parentTypeCuid;
	}
	
	public void setParentTypeCuid(java.lang.String value) {
		this.parentTypeCuid = value;
	}

	
	public java.lang.String getBmClassid() {
		return this.bmClassid;
	}
	
	public void setBmClassid(java.lang.String value) {
		this.bmClassid = value;
	}

	
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}

	
	public java.lang.String getRelatedResclassCuid() {
		return this.relatedResclassCuid;
	}
	
	public void setRelatedResclassCuid(java.lang.String value) {
		this.relatedResclassCuid = value;
	}

	
	public java.lang.String getLabelAttrId() {
		return this.labelAttrId;
	}
	
	public void setLabelAttrId(java.lang.String value) {
		this.labelAttrId = value;
	}

	
	public java.lang.String getLabelJoinChar() {
		return this.labelJoinChar;
	}
	
	public void setLabelJoinChar(java.lang.String value) {
		this.labelJoinChar = value;
	}

	
	public java.lang.String getObjectSelectXtype() {
		return this.objectSelectXtype;
	}
	
	public void setObjectSelectXtype(java.lang.String value) {
		this.objectSelectXtype = value;
	}

	
	public java.math.BigDecimal getIsShowAddBtn() {
		return this.isShowAddBtn;
	}
	
	public void setIsShowAddBtn(java.math.BigDecimal value) {
		this.isShowAddBtn = value;
	}

	
	public java.math.BigDecimal getIsShowEditBtn() {
		return this.isShowEditBtn;
	}
	
	public void setIsShowEditBtn(java.math.BigDecimal value) {
		this.isShowEditBtn = value;
	}

	
	public java.math.BigDecimal getIsShowDeleteBtn() {
		return this.isShowDeleteBtn;
	}
	
	public void setIsShowDeleteBtn(java.math.BigDecimal value) {
		this.isShowDeleteBtn = value;
	}

	
	public java.math.BigDecimal getIsShowExportBtn() {
		return this.isShowExportBtn;
	}
	
	public void setIsShowExportBtn(java.math.BigDecimal value) {
		this.isShowExportBtn = value;
	}

	
	public java.math.BigDecimal getIsShowImportBtn() {
		return this.isShowImportBtn;
	}
	
	public void setIsShowImportBtn(java.math.BigDecimal value) {
		this.isShowImportBtn = value;
	}

	
	public java.math.BigDecimal getIsVirtual() {
		return this.isVirtual;
	}
	
	public void setIsVirtual(java.math.BigDecimal value) {
		this.isVirtual = value;
	}

	
	public java.lang.String getPkCol() {
		return this.pkCol;
	}
	
	public void setPkCol(java.lang.String value) {
		this.pkCol = value;
	}

	
	public java.lang.String getTypeTableName() {
		return this.typeTableName;
	}
	
	public void setTypeTableName(java.lang.String value) {
		this.typeTableName = value;
	}

	
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}

	
	public java.lang.String getUsetype() {
		return this.usetype;
	}
	
	public void setUsetype(java.lang.String value) {
		this.usetype = value;
	}

	
	public java.lang.String getIsShowType() {
		return this.isShowType;
	}
	
	public void setIsShowType(java.lang.String value) {
		this.isShowType = value;
	}

	
	public java.lang.String getTreeSort() {
		return this.treeSort;
	}
	
	public void setTreeSort(java.lang.String value) {
		this.treeSort = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("ParentTypeCuid",getParentTypeCuid())
			.append("BmClassid",getBmClassid())
			.append("LabelCn",getLabelCn())
			.append("RelatedResclassCuid",getRelatedResclassCuid())
			.append("LabelAttrId",getLabelAttrId())
			.append("LabelJoinChar",getLabelJoinChar())
			.append("ObjectSelectXtype",getObjectSelectXtype())
			.append("IsShowAddBtn",getIsShowAddBtn())
			.append("IsShowEditBtn",getIsShowEditBtn())
			.append("IsShowDeleteBtn",getIsShowDeleteBtn())
			.append("IsShowExportBtn",getIsShowExportBtn())
			.append("IsShowImportBtn",getIsShowImportBtn())
			.append("IsVirtual",getIsVirtual())
			.append("PkCol",getPkCol())
			.append("TypeTableName",getTypeTableName())
			.append("Flag",getFlag())
			.append("Usetype",getUsetype())
			.append("IsShowType",getIsShowType())
			.append("TreeSort",getTreeSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getParentTypeCuid())
			.append(getBmClassid())
			.append(getLabelCn())
			.append(getRelatedResclassCuid())
			.append(getLabelAttrId())
			.append(getLabelJoinChar())
			.append(getObjectSelectXtype())
			.append(getIsShowAddBtn())
			.append(getIsShowEditBtn())
			.append(getIsShowDeleteBtn())
			.append(getIsShowExportBtn())
			.append(getIsShowImportBtn())
			.append(getIsVirtual())
			.append(getPkCol())
			.append(getTypeTableName())
			.append(getFlag())
			.append(getUsetype())
			.append(getIsShowType())
			.append(getTreeSort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdAbstractType == false) return false;
		if(this == obj) return true;
		TmdAbstractType other = (TmdAbstractType)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getParentTypeCuid(),other.getParentTypeCuid())
			.append(getBmClassid(),other.getBmClassid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getRelatedResclassCuid(),other.getRelatedResclassCuid())
			.append(getLabelAttrId(),other.getLabelAttrId())
			.append(getLabelJoinChar(),other.getLabelJoinChar())
			.append(getObjectSelectXtype(),other.getObjectSelectXtype())
			.append(getIsShowAddBtn(),other.getIsShowAddBtn())
			.append(getIsShowEditBtn(),other.getIsShowEditBtn())
			.append(getIsShowDeleteBtn(),other.getIsShowDeleteBtn())
			.append(getIsShowExportBtn(),other.getIsShowExportBtn())
			.append(getIsShowImportBtn(),other.getIsShowImportBtn())
			.append(getIsVirtual(),other.getIsVirtual())
			.append(getPkCol(),other.getPkCol())
			.append(getTypeTableName(),other.getTypeTableName())
			.append(getFlag(),other.getFlag())
			.append(getUsetype(),other.getUsetype())
			.append(getIsShowType(),other.getIsShowType())
			.append(getTreeSort(),other.getTreeSort())
			.isEquals();
	}
}
