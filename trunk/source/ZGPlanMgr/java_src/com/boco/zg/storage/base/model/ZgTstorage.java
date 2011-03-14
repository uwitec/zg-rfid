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


public class ZgTstorage extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_STORAGE";
	public static final String BM_CLASS_ID = "ZG_T_STORAGE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "单据编号";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_ORDER_ID = "生产订单编号";
	public static final String ALIAS_ARBPL = "生产线";
	public static final String ALIAS_LGORT = "仓库名称";
	public static final String ALIAS_DEPT_ID = "部门名称";
	public static final String ALIAS_CREATOR_ID = "录单人";
	public static final String ALIAS_ZBZ = "备注";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_STATE = "单据状态";
	public static final String ALIAS_TYPE = "单据类型";
	public static final String ALIAS_PRODUCT_TYPE = "半成品类型";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
	public static final String ARBPL_FW_ORGANIZATION_FW_ORGANIZATION = "t0_1_1.t0_";
	public static final String LGORT_FW_ORGANIZATION_FW_ORGANIZATION = "t0_2_2.t0_";
	public static final String DEPT_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_3_3.t0_";
	public static final String CREATOR_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_4_1.t0_";
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	
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
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;
	private java.lang.String arbpl_labelCn;
	private RelatedModel arbpl_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String arbpl;
	private java.lang.String lgort_labelCn;
	private RelatedModel lgort_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String lgort;
	private java.lang.String deptId_labelCn;
	private RelatedModel deptId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String deptId;
	private java.lang.String creatorId_labelCn;
	private RelatedModel creatorId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String creatorId;
	private java.lang.String zbz;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private EnumModel state_enum = new EnumModel("STORAGE_STATE");
	private java.lang.String state;
	private EnumModel type_enum = new EnumModel("STORAGE_TYPE");
	private java.lang.String type;
	private EnumModel productType_enum = new EnumModel("PRODUCT_TYPE");
	private java.lang.String productType;
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
	public java.lang.String getArbpl_labelCn() {
		return this.arbpl_labelCn;
	}
	
	public void setArbpl_labelCn(java.lang.String value) {
		this.arbpl_labelCn = value;
	}
	
	public RelatedModel getArbpl_related() {
		return this.arbpl_related;
	}
	
	public void setArbpl_related(RelatedModel value) {
		this.arbpl_related = value;
	}
	public java.lang.String getArbpl() {
		return this.arbpl;
	}
	
	public void setArbpl(java.lang.String value) {
		this.arbpl = value;
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
	public java.lang.String getDeptId_labelCn() {
		return this.deptId_labelCn;
	}
	
	public void setDeptId_labelCn(java.lang.String value) {
		this.deptId_labelCn = value;
	}
	
	public RelatedModel getDeptId_related() {
		return this.deptId_related;
	}
	
	public void setDeptId_related(RelatedModel value) {
		this.deptId_related = value;
	}
	public java.lang.String getDeptId() {
		return this.deptId;
	}
	
	public void setDeptId(java.lang.String value) {
		this.deptId = value;
	}
	public java.lang.String getCreatorId_labelCn() {
		return this.creatorId_labelCn;
	}
	
	public void setCreatorId_labelCn(java.lang.String value) {
		this.creatorId_labelCn = value;
	}
	
	public RelatedModel getCreatorId_related() {
		return this.creatorId_related;
	}
	
	public void setCreatorId_related(RelatedModel value) {
		this.creatorId_related = value;
	}
	public java.lang.String getCreatorId() {
		return this.creatorId;
	}
	
	public void setCreatorId(java.lang.String value) {
		this.creatorId = value;
	}
	public java.lang.String getZbz() {
		return this.zbz;
	}
	
	public void setZbz(java.lang.String value) {
		this.zbz = value;
	}
	public String getCreateDateString() {
		return date2String(getCreateDate(), FORMAT_CREATE_DATE);
	}
	public void setCreateDateString(String value) {
		setCreateDate(string2Date(value, FORMAT_CREATE_DATE,java.util.Date.class));
	}
	public java.util.Date getCreateDate_start() {
		return this.createDate_start;
	}
	
	public void setCreateDate_start(java.util.Date value) {
		this.createDate_start = value;
	}
	
	public java.util.Date getCreateDate_end() {
		return this.createDate_end;
	}
	
	public void setCreateDate_end(java.util.Date value) {
		this.createDate_end = value;
	}
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	public EnumModel getState_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("STORAGE_STATE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getState())){
				this.state_enum.setValue(value.getName());
				this.state_enum.setId(this.getState());
			}
		}
		return this.state_enum;
	}
	
	public void setState_enum(EnumModel value) {
		this.state_enum = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public EnumModel getType_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("STORAGE_TYPE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getType())){
				this.type_enum.setValue(value.getName());
				this.type_enum.setId(this.getType());
			}
		}
		return this.type_enum;
	}
	
	public void setType_enum(EnumModel value) {
		this.type_enum = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public EnumModel getProductType_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("PRODUCT_TYPE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getProductType())){
				this.productType_enum.setValue(value.getName());
				this.productType_enum.setId(this.getProductType());
			}
		}
		return this.productType_enum;
	}
	
	public void setProductType_enum(EnumModel value) {
		this.productType_enum = value;
	}
	public java.lang.String getProductType() {
		return this.productType;
	}
	
	public void setProductType(java.lang.String value) {
		this.productType = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrderId",getOrderId())
			.append("Arbpl",getArbpl())
			.append("Lgort",getLgort())
			.append("DeptId",getDeptId())
			.append("CreatorId",getCreatorId())
			.append("Zbz",getZbz())
			.append("CreateDate",getCreateDate())
			.append("State",getState())
			.append("Type",getType())
			.append("ProductType",getProductType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getOrderId())
			.append(getArbpl())
			.append(getLgort())
			.append(getDeptId())
			.append(getCreatorId())
			.append(getZbz())
			.append(getCreateDate())
			.append(getState())
			.append(getType())
			.append(getProductType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstorage == false) return false;
		if(this == obj) return true;
		ZgTstorage other = (ZgTstorage)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOrderId(),other.getOrderId())
			.append(getArbpl(),other.getArbpl())
			.append(getLgort(),other.getLgort())
			.append(getDeptId(),other.getDeptId())
			.append(getCreatorId(),other.getCreatorId())
			.append(getZbz(),other.getZbz())
			.append(getCreateDate(),other.getCreateDate())
			.append(getState(),other.getState())
			.append(getType(),other.getType())
			.append(getProductType(),other.getProductType())
			.isEquals();
	}
}
