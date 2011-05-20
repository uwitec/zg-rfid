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


public class FwOperatorRole extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_OPERATOR_ROLE";
	public static final String BM_CLASS_ID = "FW_OPERATOR_ROLE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_OPERATOR_ID = "OPERATOR_ID";
	public static final String ALIAS_ROLE_ID = "ROLE_ID";
	public static final String OPERATOR_ID_FW_OPERATOR_FW_OPERATOR = "t0_0_1.t0_";
	public static final String ROLE_ID_FW_ROLE_FW_ROLE = "t0_1_1.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String operatorId_labelCn;
	private RelatedModel operatorId_related = new RelatedModel("FW_OPERATOR","CUID","LABEL_CN");
	private java.lang.String operatorId;
	private java.lang.String roleId_labelCn;
	private RelatedModel roleId_related = new RelatedModel("FW_ROLE","CUID","LABEL_CN");
	private java.lang.String roleId;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getOperatorId_labelCn() {
		return this.operatorId_labelCn;
	}
	
	public void setOperatorId_labelCn(java.lang.String value) {
		this.operatorId_labelCn = value;
	}
	
	public RelatedModel getOperatorId_related() {
		return this.operatorId_related;
	}
	
	public void setOperatorId_related(RelatedModel value) {
		this.operatorId_related = value;
	}
	public java.lang.String getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.String value) {
		this.operatorId = value;
	}
	public java.lang.String getRoleId_labelCn() {
		return this.roleId_labelCn;
	}
	
	public void setRoleId_labelCn(java.lang.String value) {
		this.roleId_labelCn = value;
	}
	
	public RelatedModel getRoleId_related() {
		return this.roleId_related;
	}
	
	public void setRoleId_related(RelatedModel value) {
		this.roleId_related = value;
	}
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OperatorId",getOperatorId())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOperatorId())
			.append(getRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwOperatorRole == false) return false;
		if(this == obj) return true;
		FwOperatorRole other = (FwOperatorRole)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOperatorId(),other.getOperatorId())
			.append(getRoleId(),other.getRoleId())
			.isEquals();
	}
}
