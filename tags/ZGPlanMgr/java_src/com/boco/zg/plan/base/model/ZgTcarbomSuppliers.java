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


public class ZgTcarbomSuppliers extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_CARBOM_SUPPLIERS";
	public static final String BM_CLASS_ID = "ZG_T_CARBOM_SUPPLIERS";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "装车BOM供应商表ID";
	public static final String ALIAS_CAR_BOM_ID = "装车BOM表ID";
	public static final String ALIAS_LIFNR = "供应商编号";
	public static final String ALIAS_LIFNR_NAME = "供应商名称";
	public static final String ALIAS_CAR_NUM = "装车数量";
	public static final String ALIAS_SUPPLIERS_ID = "订单供应商表ID";
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
	private java.lang.String carBomId;
	private java.lang.String lifnr;
	private java.lang.String lifnrName;
	private java.lang.Long carNum;
	private String aufnr;
	private String idnrk;
	private String batchno;
	private String zrzqd;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getCarBomId() {
		return this.carBomId;
	}
	
	public void setCarBomId(java.lang.String value) {
		this.carBomId = value;
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
	public java.lang.Long getCarNum() {
		return this.carNum;
	}
	
	public void setCarNum(java.lang.Long value) {
		this.carNum = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("CarBomId",getCarBomId())
			.append("Lifnr",getLifnr())
			.append("LifnrName",getLifnrName())
			.append("CarNum",getCarNum())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getCarBomId())
			.append(getLifnr())
			.append(getLifnrName())
			.append(getCarNum())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTcarbomSuppliers == false) return false;
		if(this == obj) return true;
		ZgTcarbomSuppliers other = (ZgTcarbomSuppliers)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getCarBomId(),other.getCarBomId())
			.append(getLifnr(),other.getLifnr())
			.append(getLifnrName(),other.getLifnrName())
			.append(getCarNum(),other.getCarNum())
			.isEquals();
	}

	public String getAufnr() {
		return aufnr;
	}

	public void setAufnr(String aufnr) {
		this.aufnr = aufnr;
	}

	public String getIdnrk() {
		return idnrk;
	}

	public void setIdnrk(String idnrk) {
		this.idnrk = idnrk;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getZrzqd() {
		return zrzqd;
	}

	public void setZrzqd(String zrzqd) {
		this.zrzqd = zrzqd;
	}
}
