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


public class ZgTstoragebom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_STORAGEBOM";
	public static final String BM_CLASS_ID = "ZG_T_STORAGEBOM";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_ZG_T_STORAGE_ID = "出入库存表ID";
	public static final String ALIAS_MANTR = "半成品物料号";
	public static final String ALIAS_NUM = "数量";
	public static final String ALIAS_ZBZ = "备注";
	public static final String ZG_T_STORAGE_ID_ZG_T_STORAGE_ZG_T_STORAGE = "t0_0_1.t0_";
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
	private java.lang.String zgTstorageId_labelCn;
	private RelatedModel zgTstorageId_related = new RelatedModel("ZG_T_STORAGE","CUID","LABEL_CN");
	private java.lang.String zgTstorageId;
	private java.lang.String orderBomId;
	private java.lang.Long num;
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
	public java.lang.String getZgTstorageId_labelCn() {
		return this.zgTstorageId_labelCn;
	}
	
	public void setZgTstorageId_labelCn(java.lang.String value) {
		this.zgTstorageId_labelCn = value;
	}
	
	public RelatedModel getZgTstorageId_related() {
		return this.zgTstorageId_related;
	}
	
	public void setZgTstorageId_related(RelatedModel value) {
		this.zgTstorageId_related = value;
	}
	public java.lang.String getZgTstorageId() {
		return this.zgTstorageId;
	}
	
	public void setZgTstorageId(java.lang.String value) {
		this.zgTstorageId = value;
	}

	public java.lang.Long getNum() {
		return this.num;
	}
	
	public void setNum(java.lang.Long value) {
		this.num = value;
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
			.append("ZgTstorageId",getZgTstorageId())
			.append("Mantr",getOrderBomId())
			.append("Num",getNum())
			.append("Zbz",getZbz())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getZgTstorageId())
			.append(getOrderBomId())
			.append(getNum())
			.append(getZbz())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstoragebom == false) return false;
		if(this == obj) return true;
		ZgTstoragebom other = (ZgTstoragebom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getZgTstorageId(),other.getZgTstorageId())
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
