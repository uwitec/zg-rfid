package com.boco.zg.virtualorg.base.model;

import javacommon.base.BaseEntity;
/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */
public class ZgMaterrielVirtualorg extends BaseEntity {

	private String cuid;
	private String matkl;//物料组CUID
	private String orgId;//虚拟组ID
	
	private String virtualorgName;//虚拟组名
	
	private String materielId;//物料组ID
	private String materielName;//物料组名
	private String storageName;//仓库名
	private String storageId;//仓库ID
	private String materielType;//物料组类型（仓库或者是物料组）
	
	private String materielStorageId;//本物料组真正仓库ID
	private String materielStorageName;//本物料组真正仓库名字
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getMatkl() {
		return matkl;
	}
	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public String getVirtualorgName() {
		return virtualorgName;
	}
	public void setVirtualorgName(String virtualorgName) {
		this.virtualorgName = virtualorgName;
	}
	public String getStorageId() {
		return storageId;
	}
	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}
	public String getMaterielType() {
		return materielType;
	}
	public void setMaterielType(String materielType) {
		this.materielType = materielType;
	}
	public String getMaterielStorageId() {
		return materielStorageId;
	}
	public void setMaterielStorageId(String materielStorageId) {
		this.materielStorageId = materielStorageId;
	}
	public String getMaterielStorageName() {
		return materielStorageName;
	}
	public void setMaterielStorageName(String materielStorageName) {
		this.materielStorageName = materielStorageName;
	}
	public String getMaterielId() {
		return materielId;
	}
	public void setMaterielId(String materielId) {
		this.materielId = materielId;
	}
}
