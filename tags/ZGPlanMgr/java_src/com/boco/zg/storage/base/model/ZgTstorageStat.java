/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.model;

import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

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


public class ZgTstorageStat extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_STORAGE_STAT";
	public static final String BM_CLASS_ID = "ZG_T_STORAGE_STAT";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "标签名";
	public static final String ALIAS_ORDER_ID = "订单号";
	public static final String ALIAS_ORDER_BOMID="订单BOM号";
	public static final String ALIAS_NUM = "库存量";
	public static final String ALIAS_LGORT = "仓库名称";
	public static final String ALIAS_PRODUCT_TYPE="半成品类型";
	public static final String ALIAS_OUTNUM="已出库量";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
//	public static final String ARBPL_FW_ORGANIZATION_FW_ORGANIZATION = "t0_1_1.t0_";
	public static final String LGORT_FW_ORGANIZATION_FW_ORGANIZATION = "t0_2_2.t0_";
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
	private java.lang.String orderBomId;
	private java.lang.String labelCn;
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;//订单号
	private java.lang.Long num;
	private java.lang.Long outNum;
	private java.lang.String lgort_labelCn;
	private RelatedModel lgort_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String lgort;
	private java.lang.String productType;
	private java.lang.String arbpl;//生产线
	private String matnr;//半成品物料号
	private String idnrk;//半成品物料名称
	private String msehl1;//单位
	
	
	public java.lang.String getArbpl() {
		return arbpl;
	}

	public void setArbpl(java.lang.String arbpl) {
		this.arbpl = arbpl;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
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
	public java.lang.String getOrderId_labelCn() {
		return this.orderId_labelCn;
	}
	
	public void setOrderId_labelCn(java.lang.String value) {
		this.orderId_labelCn = value;
	}
	
	public RelatedModel getOrderId_related() {
		return this.orderId_related;
	}
	
	public void setOrderId_related(RelatedModel value) {
		this.orderId_related = value;
	}
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}

	
	public java.lang.String getOrderBomId() {
		return orderBomId;
	}

	public void setOrderBomId(java.lang.String orderBomId) {
		this.orderBomId = orderBomId;
	}

	public java.lang.Long getOutNum() {
		return outNum;
	}

	public void setOutNum(java.lang.Long outNum) {
		this.outNum = outNum;
	}

	public java.lang.String getProductType() {
		return productType;
	}

	public void setProductType(java.lang.String productType) {
		this.productType = productType;
	}

	public java.lang.Long getNum() {
		return this.num;
	}
	
	public void setNum(java.lang.Long value) {
		this.num = value;
	}
	public java.lang.String getLgort_labelCn() {
		return this.lgort_labelCn;
	}
	
	public void setLgort_labelCn(java.lang.String value) {
		this.lgort_labelCn = value;
	}
	
	public RelatedModel getLgort_related() {
		return this.lgort_related;
	}
	
	public void setLgort_related(RelatedModel value) {
		this.lgort_related = value;
	}
	public java.lang.String getLgort() {
		return this.lgort;
	}
	
	public void setLgort(java.lang.String value) {
		this.lgort = value;
	}

	public String toString(){
		return new ToStringBuilder(this)
			.append("Cuid", getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrderId",getOrderId())
			.append("OrderBomId",getOrderBomId())
			.append("Lgort",getLgort())
			.append("Num",getNum())
			.append("OutNum",getOutNum())
			.append("ProductType",getProductType())
			.toString();
	}
/*	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrderId",getOrderId())
			.append("Arbpl",getArbpl())
			.append("Mantr",getMantr())
			.append("Num",getNum())
			.append("Lgort",getLgort())
			.toString();
	}*/
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getOrderId())
			.append(getOrderBomId())
			.append(getNum())
			.append(getOutNum())
			.append(getLgort())
			.append(getProductType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstorageStat == false) return false;
		if(this == obj) return true;
		ZgTstorageStat other = (ZgTstorageStat)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOrderId(),other.getOrderId())
			.append(getOrderBomId(),other.getOrderBomId())
			.append(getNum(),other.getNum())
			.append(getOutNum(),other.getOutNum())
			.append(getLgort(),other.getLgort())
			.append(getProductType(),other.getProductType())
			.isEquals();
	}
}
