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


public class FwRoleMenu extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_ROLE_MENU";
	public static final String BM_CLASS_ID = "FW_ROLE_MENU";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_ROLE_ID = "ROLE_ID";
	public static final String ALIAS_MENU_ID = "MENU_ID";
	public static final String ROLE_ID_FW_ROLE_FW_ROLE = "t0_0_1.t0_";
	public static final String MENU_ID_FW_MENU_FW_MENU = "t0_1_1.t0_";
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
	private java.lang.String roleId_labelCn;
	private RelatedModel roleId_related = new RelatedModel("FW_ROLE","CUID","LABEL_CN");
	private java.lang.String roleId;
	private java.lang.String menuId_labelCn;
	private RelatedModel menuId_related = new RelatedModel("FW_MENU","CUID","LABEL_CN");
	private java.lang.String menuId;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
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
	public java.lang.String getMenuId_labelCn() {
		return this.menuId_labelCn;
	}
	
	public void setMenuId_labelCn(java.lang.String value) {
		this.menuId_labelCn = value;
	}
	
	public RelatedModel getMenuId_related() {
		return this.menuId_related;
	}
	
	public void setMenuId_related(RelatedModel value) {
		this.menuId_related = value;
	}
	public java.lang.String getMenuId() {
		return this.menuId;
	}
	
	public void setMenuId(java.lang.String value) {
		this.menuId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RoleId",getRoleId())
			.append("MenuId",getMenuId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRoleId())
			.append(getMenuId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwRoleMenu == false) return false;
		if(this == obj) return true;
		FwRoleMenu other = (FwRoleMenu)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRoleId(),other.getRoleId())
			.append(getMenuId(),other.getMenuId())
			.isEquals();
	}
}
