/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderSuppliers extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_SUPPLIERS";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_SUPPLIERS";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "订单供应商表ID";
	public static final String ALIAS_AUFNR = "订单编码";
	public static final String ALIAS_IDNRK = "BOM编码";
	public static final String ALIAS_LIFNR = "供应商编号";
	public static final String ALIAS_LIFNR_NAME = "供应商名称";
	public static final String ALIAS_PLANT = "生产厂";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getLifnrName()==null?"":this.getLifnrName().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String aufnr;
	private java.lang.String idnrk;
	private java.lang.String lifnr;
	private java.lang.String lifnrName;
	private java.lang.String plant;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getAufnr() {
		return this.aufnr;
	}
	
	public void setAufnr(java.lang.String value) {
		this.aufnr = value;
	}
	public java.lang.String getIdnrk() {
		return this.idnrk;
	}
	
	public void setIdnrk(java.lang.String value) {
		this.idnrk = value;
	}
	public java.lang.String getLifnr() {
		return this.lifnr;
	}
	
	public void setLifnr(java.lang.String value) {
		this.lifnr = value;
	}
	public java.lang.String getLifnrName() {
		return this.lifnrName;
	}
	
	public void setLifnrName(java.lang.String value) {
		this.lifnrName = value;
	}
	public java.lang.String getPlant() {
		return this.plant;
	}
	
	public void setPlant(java.lang.String value) {
		this.plant = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Aufnr",getAufnr())
			.append("Idnrk",getIdnrk())
			.append("Lifnr",getLifnr())
			.append("LifnrName",getLifnrName())
			.append("Plant",getPlant())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getAufnr())
			.append(getIdnrk())
			.append(getLifnr())
			.append(getLifnrName())
			.append(getPlant())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderSuppliers == false) return false;
		if(this == obj) return true;
		ZgTorderSuppliers other = (ZgTorderSuppliers)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getAufnr(),other.getAufnr())
			.append(getIdnrk(),other.getIdnrk())
			.append(getLifnr(),other.getLifnr())
			.append(getLifnrName(),other.getLifnrName())
			.append(getPlant(),other.getPlant())
			.isEquals();
	}
}
