/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.virtualorg.base.model;

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

import com.boco.zg.virtualorg.base.model.*;
import com.boco.zg.virtualorg.base.dao.*;
import com.boco.zg.virtualorg.base.service.*;

/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */


public class ZgTvirtualorg extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_VIRTUALORG";
	public static final String BM_CLASS_ID = "ZG_T_VIRTUALORG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "领料组编号";
	public static final String ALIAS_LABEL_CN = "领料组名称";
	public static final String ALIAS_ORG_ID = "所属机构";
	public static final String ALIAS_NOTE = "描述";
	public static final String ALIAS_TYPE = "领料组类型";
	
	public static final String ALIAS_USERID="领料人ID";
	public static final String ALIAS_USERNAME="领料人名字";
	public static final String ALIAS_SEX="性别";
	public static final String ALIAS_MOBILE="电话";
	public static final String ALIAS_EMAIL="邮箱";
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
	private java.lang.String labelCn;//虚拟组名字
	private java.lang.String orgId;//所属机构ID
	private java.lang.String orgName;//所属机构名字,即部门名字
	private java.lang.String note;
	private java.lang.String type;
	
	private java.lang.String userId;//领料人ID
	private java.lang.String userName;
	private java.lang.String sex;
	private java.lang.String mobile;
	private java.lang.String email;
	
	private java.lang.String zgTvirtualorgEmployeeCuid;//领料组领料人关系表 主键
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
	public java.lang.String getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
			this.type = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OrgId",getOrgId())
			.append("Note",getNote())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getOrgId())
			.append(getNote())
			.append(getType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTvirtualorg == false) return false;
		if(this == obj) return true;
		ZgTvirtualorg other = (ZgTvirtualorg)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOrgId(),other.getOrgId())
			.append(getNote(),other.getNote())
			.append(getType(),other.getType())
			.isEquals();
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
			if("1".equals(sex))
				this.sex = "男";
			else if("2".equals(sex))
				this.sex = "女";
			else
				this.sex =sex;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getZgTvirtualorgEmployeeCuid() {
		return zgTvirtualorgEmployeeCuid;
	}

	public void setZgTvirtualorgEmployeeCuid(
			java.lang.String zgTvirtualorgEmployeeCuid) {
		this.zgTvirtualorgEmployeeCuid = zgTvirtualorgEmployeeCuid;
	}
}
