/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.model;

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

import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageCanclebom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_STORAGE_CANCLEBOM";
	public static final String BM_CLASS_ID = "ZG_T_STORAGE_CANCLEBOM";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_STORAGE_CANCLE_ID = "入库冲单表ID";
	public static final String ALIAS_STORAGE_ID = "出入库存表ID";
	public static final String ALIAS_MANTR = "半成品物料号";
	public static final String ALIAS_NUM = "数量";
	public static final String ALIAS_ZBZ = "备注";
	public static final String STORAGE_CANCLE_ID_ZG_T_STORAGE_CANCLE_ZG_T_STORAGE_CANCLE = "t0_0_1.t0_";
	public static final String STORAGE_ID_ZG_T_STORAGE_ZG_T_STORAGE = "t0_1_1.t0_";
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
	private java.lang.String storageCancleId_labelCn;
	private RelatedModel storageCancleId_related = new RelatedModel("ZG_T_STORAGE_CANCLE","CUID","LABEL_CN");
	private java.lang.String storageCancleId;
	private java.lang.String storageId_labelCn;
	private RelatedModel storageId_related = new RelatedModel("ZG_T_STORAGE","CUID","LABEL_CN");
	private java.lang.String storageId;
	private java.lang.String orderBomId;
	private java.lang.Double num;
	private java.lang.String zbz;

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
	public java.lang.String getStorageCancleId_labelCn() {
		return this.storageCancleId_labelCn;
	}
	
	public void setStorageCancleId_labelCn(java.lang.String value) {
		this.storageCancleId_labelCn = value;
	}
	
	public RelatedModel getStorageCancleId_related() {
		return this.storageCancleId_related;
	}
	
	public void setStorageCancleId_related(RelatedModel value) {
		this.storageCancleId_related = value;
	}
	public java.lang.String getStorageCancleId() {
		return this.storageCancleId;
	}
	
	public void setStorageCancleId(java.lang.String value) {
		this.storageCancleId = value;
	}
	public java.lang.String getStorageId_labelCn() {
		return this.storageId_labelCn;
	}
	
	public void setStorageId_labelCn(java.lang.String value) {
		this.storageId_labelCn = value;
	}
	
	public RelatedModel getStorageId_related() {
		return this.storageId_related;
	}
	
	public void setStorageId_related(RelatedModel value) {
		this.storageId_related = value;
	}
	public java.lang.String getStorageId() {
		return this.storageId;
	}
	
	public void setStorageId(java.lang.String value) {
		this.storageId = value;
	}

	/**
	 * @return the num
	 */
	public java.lang.Double getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(java.lang.Double num) {
		this.num = num;
	}

	public java.lang.String getZbz() {
		return this.zbz;
	}
	
	public void setZbz(java.lang.String value) {
		this.zbz = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("StorageCancleId",getStorageCancleId())
			.append("StorageId",getStorageId())
			.append("Mantr",getOrderBomId())
			.append("Num",getNum())
			.append("Zbz",getZbz())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getStorageCancleId())
			.append(getStorageId())
			.append(getOrderBomId())
			.append(getNum())
			.append(getZbz())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstorageCanclebom == false) return false;
		if(this == obj) return true;
		ZgTstorageCanclebom other = (ZgTstorageCanclebom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getStorageCancleId(),other.getStorageCancleId())
			.append(getStorageId(),other.getStorageId())
			.append(getOrderBomId(),other.getOrderBomId())
			.append(getNum(),other.getNum())
			.append(getZbz(),other.getZbz())
			.isEquals();
	}

	public java.lang.String getOrderBomId() {
		return orderBomId;
	}

	public void setOrderBomId(java.lang.String orderBomId) {
		this.orderBomId = orderBomId;
	}


}
