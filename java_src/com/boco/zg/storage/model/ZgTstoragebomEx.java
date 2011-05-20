/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.model;

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
 * @author wengq
 * @version 1.0
 * @since 1.0
 */


public class ZgTstoragebomEx extends BaseEntity {
	
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
	private java.lang.Long num;
	private java.lang.String zbz;
	
	private String orderAufnr;
	private String matnr;
	private String maktx1;
	private String idnrk;
	private String msehl1;
	private Long menge;
	private String arbpl;
	private String zdtyl;
	private String orderBomId;
	private String orderId;
	
	private boolean isDel=false;//由于编辑修改bom组件时，都是放在session中，然后再统一提交修改，本字段用于标记bom组件是否补删除
	private boolean isModity=false;//同上，用于标记该组件是否被修改过
	
	
//	private String 
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
			.append("Mantr",getMatnr())
			.append("Num",getNum())
			.append("Zbz",getZbz())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getZgTstorageId())
			.append(getMatnr())
			.append(getNum())
			.append(getZbz())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstoragebomEx == false) return false;
		if(this == obj) return true;
		ZgTstoragebomEx other = (ZgTstoragebomEx)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getZgTstorageId(),other.getZgTstorageId())
			.append(getMatnr(),other.getMatnr())
			.append(getNum(),other.getNum())
			.append(getZbz(),other.getZbz())
			.isEquals();
	}

	public String getOrderAufnr() {
		return orderAufnr;
	}

	public void setOrderAufnr(String orderAufnr) {
		this.orderAufnr = orderAufnr;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getMaktx1() {
		return maktx1;
	}

	public void setMaktx1(String maktx1) {
		this.maktx1 = maktx1;
	}

	public String getIdnrk() {
		return idnrk;
	}

	public void setIdnrk(String idnrk) {
		this.idnrk = idnrk;
	}

	public String getMsehl1() {
		return msehl1;
	}

	public void setMsehl1(String msehl1) {
		this.msehl1 = msehl1;
	}

	public Long getMenge() {
		return menge;
	}

	public void setMenge(Long menge) {
		this.menge = menge;
	}

	public String getArbpl() {
		return arbpl;
	}

	public void setArbpl(String arbpl) {
		this.arbpl = arbpl;
	}

	public String getZdtyl() {
		return zdtyl;
	}

	public void setZdtyl(String zdtyl) {
		this.zdtyl = zdtyl;
	}


	
	public boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}
	public boolean getIsModity() {
		return isModity;
	}
	public void setIsModity(boolean isModity) {
		this.isModity = isModity;
	}

	public String getOrderBomId() {
		return orderBomId;
	}

	public void setOrderBomId(String orderBomId) {
		this.orderBomId = orderBomId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
