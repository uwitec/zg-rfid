package com.boco.frame.meta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BMAttrGroup implements Serializable{
	private static final long serialVersionUID = -7713843285466010442L;
	private String cuid;
	private String labelCn;
	private String tableName;
	private String description;
	private String pkCol;
	//�Ƿ�����Լ�(�Ƿ���Ҫ)
	private boolean isBasic;
	//�Ƿ�ϵͳ
	private boolean isSystem;
	//�Ƿ�̻�
	private boolean isStatic;
	private List<BMAttrMeta> attrList = new ArrayList<BMAttrMeta>();
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getLabelCn() {
		return labelCn;
	}
	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getIsBasic() {
		return isBasic;
	}
	public void setIsBasic(boolean isBasic) {
		this.isBasic = isBasic;
	}
	public List<BMAttrMeta> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<BMAttrMeta> attrList) {
		this.attrList = attrList;
	}
	public String getPkCol() {
		return pkCol;
	}
	public void setPkCol(String pkCol) {
		this.pkCol = pkCol;
	}
	public boolean getIsStatic() {
		return isStatic;
	}
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	public boolean getIsSystem() {
		return isSystem;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuid == null) ? 0 : cuid.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isBasic ? 1231 : 1237);
		result = prime * result + (isStatic ? 1231 : 1237);
		result = prime * result + (isSystem ? 1231 : 1237);
		result = prime * result + ((labelCn == null) ? 0 : labelCn.hashCode());
		result = prime * result + ((pkCol == null) ? 0 : pkCol.hashCode());
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BMAttrGroup other = (BMAttrGroup) obj;
		if (cuid == null) {
			if (other.cuid != null)
				return false;
		} else if (!cuid.equals(other.cuid))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isBasic != other.isBasic)
			return false;
		if (isStatic != other.isStatic)
			return false;
		if (isSystem != other.isSystem)
			return false;
		if (labelCn == null) {
			if (other.labelCn != null)
				return false;
		} else if (!labelCn.equals(other.labelCn))
			return false;
		if (pkCol == null) {
			if (other.pkCol != null)
				return false;
		} else if (!pkCol.equals(other.pkCol))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	public void addAttrMeta(BMAttrMeta attrMeta){
		this.attrList.add(attrMeta);
		attrMeta.setAttrGroup(this);
	}	
}
