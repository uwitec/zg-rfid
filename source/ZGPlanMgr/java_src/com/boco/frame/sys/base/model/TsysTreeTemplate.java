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


public class TsysTreeTemplate extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "T_SYS_TREE_TEMPLATE";
	public static final String BM_CLASS_ID = "T_SYS_TREE_TEMPLATE";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_SQL_TEMPLATE = "SQL_TEMPLATE";
	public static final String ALIAS_PARAM1 = "PARAM1";
	public static final String ALIAS_PARAM2 = "PARAM2";
	public static final String ALIAS_PARAM3 = "PARAM3";
	public static final String ALIAS_PARAM4 = "PARAM4";
	public static final String ALIAS_PARAM5 = "PARAM5";
	public static final String ALIAS_PARAM6 = "PARAM6";
	public static final String ALIAS_REMARK = "REMARK";
	public static final String ALIAS_ISDISTINCT = "ISDISTINCT";
	public static final String ALIAS_PRIMARYCOLUMN = "PRIMARYCOLUMN";
	public static final String ALIAS_IS_OPEN_URL = "IS_OPEN_URL";
	public static final String ALIAS_OPEN_URL = "OPEN_URL";
	public static final String ALIAS_PAGE_SIZE = "PAGE_SIZE";
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
	private java.lang.String sqlTemplate;
	private java.lang.Long param1;
	private java.lang.Long param2;
	private java.lang.Long param3;
	private java.lang.Long param4;
	private java.lang.Long param5;
	private java.lang.Long param6;
	private java.lang.String remark;
	private java.lang.String isdistinct;
	private java.lang.String primarycolumn;
	private java.lang.String isOpenUrl;
	private java.lang.String openUrl;
	private java.lang.String pageSize;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getSqlTemplate() {
		return this.sqlTemplate;
	}
	
	public void setSqlTemplate(java.lang.String value) {
		this.sqlTemplate = value;
	}
	public java.lang.Long getParam1() {
		return this.param1;
	}
	
	public void setParam1(java.lang.Long value) {
		this.param1 = value;
	}
	public java.lang.Long getParam2() {
		return this.param2;
	}
	
	public void setParam2(java.lang.Long value) {
		this.param2 = value;
	}
	public java.lang.Long getParam3() {
		return this.param3;
	}
	
	public void setParam3(java.lang.Long value) {
		this.param3 = value;
	}
	public java.lang.Long getParam4() {
		return this.param4;
	}
	
	public void setParam4(java.lang.Long value) {
		this.param4 = value;
	}
	public java.lang.Long getParam5() {
		return this.param5;
	}
	
	public void setParam5(java.lang.Long value) {
		this.param5 = value;
	}
	public java.lang.Long getParam6() {
		return this.param6;
	}
	
	public void setParam6(java.lang.Long value) {
		this.param6 = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.lang.String getIsdistinct() {
		return this.isdistinct;
	}
	
	public void setIsdistinct(java.lang.String value) {
		this.isdistinct = value;
	}
	public java.lang.String getPrimarycolumn() {
		return this.primarycolumn;
	}
	
	public void setPrimarycolumn(java.lang.String value) {
		this.primarycolumn = value;
	}
	public java.lang.String getIsOpenUrl() {
		return this.isOpenUrl;
	}
	
	public void setIsOpenUrl(java.lang.String value) {
		this.isOpenUrl = value;
	}
	public java.lang.String getOpenUrl() {
		return this.openUrl;
	}
	
	public void setOpenUrl(java.lang.String value) {
		this.openUrl = value;
	}
	public java.lang.String getPageSize() {
		return this.pageSize;
	}
	
	public void setPageSize(java.lang.String value) {
		this.pageSize = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("SqlTemplate",getSqlTemplate())
			.append("Param1",getParam1())
			.append("Param2",getParam2())
			.append("Param3",getParam3())
			.append("Param4",getParam4())
			.append("Param5",getParam5())
			.append("Param6",getParam6())
			.append("Remark",getRemark())
			.append("Isdistinct",getIsdistinct())
			.append("Primarycolumn",getPrimarycolumn())
			.append("IsOpenUrl",getIsOpenUrl())
			.append("OpenUrl",getOpenUrl())
			.append("PageSize",getPageSize())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getSqlTemplate())
			.append(getParam1())
			.append(getParam2())
			.append(getParam3())
			.append(getParam4())
			.append(getParam5())
			.append(getParam6())
			.append(getRemark())
			.append(getIsdistinct())
			.append(getPrimarycolumn())
			.append(getIsOpenUrl())
			.append(getOpenUrl())
			.append(getPageSize())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysTreeTemplate == false) return false;
		if(this == obj) return true;
		TsysTreeTemplate other = (TsysTreeTemplate)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getSqlTemplate(),other.getSqlTemplate())
			.append(getParam1(),other.getParam1())
			.append(getParam2(),other.getParam2())
			.append(getParam3(),other.getParam3())
			.append(getParam4(),other.getParam4())
			.append(getParam5(),other.getParam5())
			.append(getParam6(),other.getParam6())
			.append(getRemark(),other.getRemark())
			.append(getIsdistinct(),other.getIsdistinct())
			.append(getPrimarycolumn(),other.getPrimarycolumn())
			.append(getIsOpenUrl(),other.getIsOpenUrl())
			.append(getOpenUrl(),other.getOpenUrl())
			.append(getPageSize(),other.getPageSize())
			.isEquals();
	}
}
