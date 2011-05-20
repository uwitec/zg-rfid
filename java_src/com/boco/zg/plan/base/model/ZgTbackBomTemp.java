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
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTbackBomTemp extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_BACK_BOM_TEMP";
	public static final String BM_CLASS_ID = "ZG_T_BACK_BOM_TEMP";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_AUFNR = "订单";
	public static final String ALIAS_MATNR = "物料编码";
	public static final String ALIAS_MAKTX = "物料描述";
	public static final String ALIAS_MENGE_D = "退料数量";
	public static final String ALIAS_BUDAT = "过帐日期";
	public static final String ALIAS_CPUTM = "输入时间";
	public static final String ALIAS_BATCH_NO = "BATCH_NO";
	//date formats
	public static final String FORMAT_BUDAT = DATE_FORMAT;
	public static final String FORMAT_CPUTM = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getAufnr()==null?"":this.getAufnr().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String aufnr;
	private java.lang.String matnr;
	private java.lang.String maktx;
	private java.lang.Long mengeD;
	private java.util.Date budat_start;
	private java.util.Date budat_end;
	private java.util.Date budat;
	private java.util.Date cputm_start;
	private java.util.Date cputm_end;
	private java.util.Date cputm;
	private java.lang.Long batchNo;
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
	public java.lang.String getMatnr() {
		return this.matnr;
	}
	
	public void setMatnr(java.lang.String value) {
		this.matnr = value;
	}
	public java.lang.String getMaktx() {
		return this.maktx;
	}
	
	public void setMaktx(java.lang.String value) {
		this.maktx = value;
	}
	public java.lang.Long getMengeD() {
		return this.mengeD;
	}
	
	public void setMengeD(java.lang.Long value) {
		this.mengeD = value;
	}
	public String getBudatString() {
		return date2String(getBudat(), FORMAT_BUDAT);
	}
	public void setBudatString(String value) {
		setBudat(string2Date(value, FORMAT_BUDAT,java.util.Date.class));
	}
	public java.util.Date getBudat_start() {
		return this.budat_start;
	}
	
	public void setBudat_start(java.util.Date value) {
		this.budat_start = value;
	}
	
	public java.util.Date getBudat_end() {
		return this.budat_end;
	}
	
	public void setBudat_end(java.util.Date value) {
		this.budat_end = value;
	}
	public java.util.Date getBudat() {
		return this.budat;
	}
	
	public void setBudat(java.util.Date value) {
		this.budat = value;
	}
	public String getCputmString() {
		return date2String(getCputm(), FORMAT_CPUTM);
	}
	public void setCputmString(String value) {
		setCputm(string2Date(value, FORMAT_CPUTM,java.util.Date.class));
	}
	public java.util.Date getCputm_start() {
		return this.cputm_start;
	}
	
	public void setCputm_start(java.util.Date value) {
		this.cputm_start = value;
	}
	
	public java.util.Date getCputm_end() {
		return this.cputm_end;
	}
	
	public void setCputm_end(java.util.Date value) {
		this.cputm_end = value;
	}
	public java.util.Date getCputm() {
		return this.cputm;
	}
	
	public void setCputm(java.util.Date value) {
		this.cputm = value;
	}
	public java.lang.Long getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(java.lang.Long value) {
		this.batchNo = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Aufnr",getAufnr())
			.append("Matnr",getMatnr())
			.append("Maktx",getMaktx())
			.append("MengeD",getMengeD())
			.append("Budat",getBudat())
			.append("Cputm",getCputm())
			.append("BatchNo",getBatchNo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getAufnr())
			.append(getMatnr())
			.append(getMaktx())
			.append(getMengeD())
			.append(getBudat())
			.append(getCputm())
			.append(getBatchNo())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTbackBomTemp == false) return false;
		if(this == obj) return true;
		ZgTbackBomTemp other = (ZgTbackBomTemp)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getAufnr(),other.getAufnr())
			.append(getMatnr(),other.getMatnr())
			.append(getMaktx(),other.getMaktx())
			.append(getMengeD(),other.getMengeD())
			.append(getBudat(),other.getBudat())
			.append(getCputm(),other.getCputm())
			.append(getBatchNo(),other.getBatchNo())
			.isEquals();
	}
}
