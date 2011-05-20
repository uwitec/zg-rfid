/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

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

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwEmployee extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_EMPLOYEE";
	public static final String BM_CLASS_ID = "FW_EMPLOYEE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_ORG_ID = "部门";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_MOBILE = "手机";
	public static final String ALIAS_TELEPHONE = "电话";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_LABEL_CN = "用户名称";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_LAST_MODIFY_TIME = "最后修改日期";
	public static final String ALIAS_EFFECT_TIME = "入职时间";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_RELATED_BM_CLASS_ID = "RELATED_BM_CLASS_ID";
	public static final String ORG_ID_FW_ORGANIZATION_FW_ORGANIZATION = "t0_0_1.t0_";
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_MODIFY_TIME = DATE_FORMAT;
	public static final String FORMAT_EFFECT_TIME = DATE_FORMAT;
	
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
	private java.lang.String cuid;//主键
	private java.lang.String orgId_labelCn;
	private RelatedModel orgId_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String orgId;
	private EnumModel sex_enum = new EnumModel("SEX");
	private java.lang.String sex;
	private java.lang.String mobile;
	private java.lang.String telephone;
	private java.lang.String address;
	private java.lang.String email;
	private java.lang.String labelCn;
	private java.lang.String userId;
	private java.lang.String password;
	private java.util.Date createTime_start;
	private java.util.Date createTime_end;
	private java.util.Date createTime;
	private java.util.Date lastModifyTime_start;
	private java.util.Date lastModifyTime_end;
	private java.util.Date lastModifyTime;
	private java.util.Date effectTime_start;
	private java.util.Date effectTime_end;
	private java.util.Date effectTime;
	private java.lang.Long status;
	private java.lang.String relatedBmClassId;
	private java.lang.String rfidCode;
	
//	private int pageSizeValue;
	
	/*
	 * wjz加的columns
	 */
	private String auditingName;//审核人名字
	private String departmentName;//部门名字
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getOrgId_labelCn() {
		return this.orgId_labelCn;
	}
	
	public void setOrgId_labelCn(java.lang.String value) {
		this.orgId_labelCn = value;
	}
	
	public RelatedModel getOrgId_related() {
		return this.orgId_related;
	}
	
	public void setOrgId_related(RelatedModel value) {
		this.orgId_related = value;
	}
	public java.lang.String getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	public EnumModel getSex_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("SEX");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getSex())){
				this.sex_enum.setValue(value.getName());
				this.sex_enum.setId(this.getSex());
			}
		}
		return this.sex_enum;
	}
	
	public void setSex_enum(EnumModel value) {
		this.sex_enum = value;
	}
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	public String getCreateTimeString() {
		return date2String(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(string2Date(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	public java.util.Date getCreateTime_start() {
		return this.createTime_start;
	}
	
	public void setCreateTime_start(java.util.Date value) {
		this.createTime_start = value;
	}
	
	public java.util.Date getCreateTime_end() {
		return this.createTime_end;
	}
	
	public void setCreateTime_end(java.util.Date value) {
		this.createTime_end = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public String getLastModifyTimeString() {
		return date2String(getLastModifyTime(), FORMAT_LAST_MODIFY_TIME);
	}
	public void setLastModifyTimeString(String value) {
		setLastModifyTime(string2Date(value, FORMAT_LAST_MODIFY_TIME,java.util.Date.class));
	}
	public java.util.Date getLastModifyTime_start() {
		return this.lastModifyTime_start;
	}
	
	public void setLastModifyTime_start(java.util.Date value) {
		this.lastModifyTime_start = value;
	}
	
	public java.util.Date getLastModifyTime_end() {
		return this.lastModifyTime_end;
	}
	
	public void setLastModifyTime_end(java.util.Date value) {
		this.lastModifyTime_end = value;
	}
	public java.util.Date getLastModifyTime() {
		return this.lastModifyTime;
	}
	
	public void setLastModifyTime(java.util.Date value) {
		this.lastModifyTime = value;
	}
	public String getEffectTimeString() {
		return date2String(getEffectTime(), FORMAT_EFFECT_TIME);
	}
	public void setEffectTimeString(String value) {
		setEffectTime(string2Date(value, FORMAT_EFFECT_TIME,java.util.Date.class));
	}
	public java.util.Date getEffectTime_start() {
		return this.effectTime_start;
	}
	
	public void setEffectTime_start(java.util.Date value) {
		this.effectTime_start = value;
	}
	
	public java.util.Date getEffectTime_end() {
		return this.effectTime_end;
	}
	
	public void setEffectTime_end(java.util.Date value) {
		this.effectTime_end = value;
	}
	public java.util.Date getEffectTime() {
		return this.effectTime;
	}
	
	public void setEffectTime(java.util.Date value) {
		this.effectTime = value;
	}
	public java.lang.Long getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Long value) {
		this.status = value;
	}
	public java.lang.String getRelatedBmClassId() {
		return this.relatedBmClassId;
	}
	
	public void setRelatedBmClassId(java.lang.String value) {
		this.relatedBmClassId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OrgId",getOrgId())
			.append("Sex",getSex())
			.append("Mobile",getMobile())
			.append("Telephone",getTelephone())
			.append("Address",getAddress())
			.append("Email",getEmail())
			.append("LabelCn",getLabelCn())
			.append("UserId",getUserId())
			.append("Password",getPassword())
			.append("CreateTime",getCreateTime())
			.append("LastModifyTime",getLastModifyTime())
			.append("EffectTime",getEffectTime())
			.append("Status",getStatus())
			.append("RelatedBmClassId",getRelatedBmClassId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOrgId())
			.append(getSex())
			.append(getMobile())
			.append(getTelephone())
			.append(getAddress())
			.append(getEmail())
			.append(getLabelCn())
			.append(getUserId())
			.append(getPassword())
			.append(getCreateTime())
			.append(getLastModifyTime())
			.append(getEffectTime())
			.append(getStatus())
			.append(getRelatedBmClassId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwEmployee == false) return false;
		if(this == obj) return true;
		FwEmployee other = (FwEmployee)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOrgId(),other.getOrgId())
			.append(getSex(),other.getSex())
			.append(getMobile(),other.getMobile())
			.append(getTelephone(),other.getTelephone())
			.append(getAddress(),other.getAddress())
			.append(getEmail(),other.getEmail())
			.append(getLabelCn(),other.getLabelCn())
			.append(getUserId(),other.getUserId())
			.append(getPassword(),other.getPassword())
			.append(getCreateTime(),other.getCreateTime())
			.append(getLastModifyTime(),other.getLastModifyTime())
			.append(getEffectTime(),other.getEffectTime())
			.append(getStatus(),other.getStatus())
			.append(getRelatedBmClassId(),other.getRelatedBmClassId())
			.isEquals();
	}

	public java.lang.String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(java.lang.String rfidCode) {
		this.rfidCode = rfidCode;
	}

	public String getAuditingName() {
		return auditingName;
	}

	public void setAuditingName(String auditingName) {
		this.auditingName = auditingName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

//	public int getPageSizeValue() {
//		return pageSizeValue;
//	}
//
//	public void setPageSizeValue(int pageSizeValue) {
//		this.pageSizeValue = pageSizeValue;
//	}
}
