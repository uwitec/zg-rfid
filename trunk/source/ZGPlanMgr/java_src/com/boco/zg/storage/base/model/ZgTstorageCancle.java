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


public class ZgTstorageCancle extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_STORAGE_CANCLE";
	public static final String BM_CLASS_ID = "ZG_T_STORAGE_CANCLE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_DEPT_ID = "部门名称";
	public static final String ALIAS_CREATOR_ID = "录单人";
	public static final String ALIAS_ZBZ = "备注";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_CREATE_DATE = "单据日期";
	public static final String DEPT_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_0_1.t0_";
	public static final String CREATOR_ID_FW_EMPLOYEE_FW_OPERATOR = "t0_1_1.t0_";
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
	private java.lang.String deptId_labelCn;
	private RelatedModel deptId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String deptId;
	private java.lang.String creatorId_labelCn;
	private RelatedModel creatorId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String creatorId;
	private java.lang.String zbz;
	private EnumModel state_enum = new EnumModel("STORAGE_STATE");
	private java.lang.String state;
	private java.util.Date createDate_start;
	private java.util.Date createDate_end;
	private java.util.Date createDate;
	private String productType;
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

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("DeptId",getDeptId())
			.append("CreatorId",getCreatorId())
			.append("Zbz",getZbz())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getDeptId())
			.append(getCreatorId())
			.append(getZbz())
			.append(getState())
			.append(getCreateDate())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTstorageCancle == false) return false;
		if(this == obj) return true;
		ZgTstorageCancle other = (ZgTstorageCancle)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getDeptId(),other.getDeptId())
			.append(getCreatorId(),other.getCreatorId())
			.append(getZbz(),other.getZbz())
			.append(getState(),other.getState())
			.append(getCreateDate(),other.getCreateDate())
			.isEquals();
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
