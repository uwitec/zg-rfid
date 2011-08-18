/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.base.model.RelatedModel;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTbom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZgTbom";
	public static final String ALIAS_CUID = "BOM组件";
	public static final String ALIAS_LABEL_CN = "BOM组件";
	public static final String ALIAS_MAKTX = "物料描述";
	public static final String ALIAS_MATNR = "物料号";
	public static final String ALIAS_MSEHL = "度量单位";
	public static final String ALIAS_MATKL = "物料组";
	public static final String ALIAS_ZBZ = "物料备注";
	public static final String ALIAS_ZRZQD = "认证清单";
	public static final String ALIAS_LGORT = "库存地点";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String maktx;
	private java.lang.String labelCn;
	private java.lang.String matnr;
	private java.lang.String msehl;
	private java.lang.String matkl;
	private java.lang.String zbz;
	private java.lang.String zrzqd;
	private java.lang.String lgort;
	private java.lang.String lgortLabelCn;
	private java.lang.String plant;
	private java.lang.String carNum;
	private String idnrk;
	private String carId;
	private RelatedModel carId_related = new RelatedModel("ZG_CARINFO","CUID","LABEL_CN");
	private java.lang.String carId_labelCn;
	private String bomCarState;
	
	//columns END
	//columns expand start
	private java.lang.Double completeNum;
	private java.lang.Double outNum;
	private java.lang.Double backNum;
	private String planType;
	private Double remainNum;
	private String orderPlanbomId;
	private String meins;
	//columns expand end

	public String getOrderPlanbomId() {
		return orderPlanbomId;
	}

	public void setOrderPlanbomId(String orderPlanbomId) {
		this.orderPlanbomId = orderPlanbomId;
	}


	/**
	 * @return the completeNum
	 */
	public java.lang.Double getCompleteNum() {
		return completeNum;
	}

	/**
	 * @param completeNum the completeNum to set
	 */
	public void setCompleteNum(java.lang.Double completeNum) {
		this.completeNum = completeNum;
	}

	/**
	 * @return the outNum
	 */
	public java.lang.Double getOutNum() {
		return outNum;
	}

	/**
	 * @param outNum the outNum to set
	 */
	public void setOutNum(java.lang.Double outNum) {
		this.outNum = outNum;
	}

	/**
	 * @return the backNum
	 */
	public java.lang.Double getBackNum() {
		return backNum;
	}

	/**
	 * @param backNum the backNum to set
	 */
	public void setBackNum(java.lang.Double backNum) {
		this.backNum = backNum;
	}

	/**
	 * @return the remainNum
	 */
	public Double getRemainNum() {
		return remainNum;
	}

	/**
	 * @param remainNum the remainNum to set
	 */
	public void setRemainNum(Double remainNum) {
		this.remainNum = remainNum;
	}

	public String getBomCarState() {
		return bomCarState;
	}

	public void setBomCarState(String bomCarState) {
		this.bomCarState = bomCarState;
	}

	public ZgTbom(){
	}

	public ZgTbom(
		java.lang.String cuid
	){
		this.cuid = cuid;
	}



	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	
	public java.lang.String getCuid() {
		return this.cuid;
	}


	
	
	public java.lang.String getMaktx() {
		return this.maktx;
	}
	
	public void setMaktx(java.lang.String value) {
		this.maktx = value;
	}

	
	public java.lang.String getMatnr() {
		return this.matnr;
	}
	
	public void setMatnr(java.lang.String value) {
		this.matnr = value;
	}

	
	public java.lang.String getMsehl() {
		return this.msehl;
	}
	
	public void setMsehl(java.lang.String value) {
		this.msehl = value;
	}

	
	public java.lang.String getMatkl() {
		return this.matkl;
	}
	
	public void setMatkl(java.lang.String value) {
		this.matkl = value;
	}

	
	public java.lang.String getZbz() {
		return this.zbz;
	}
	
	public void setZbz(java.lang.String value) {
		this.zbz = value;
	}

	
	public java.lang.String getZrzqd() {
		return this.zrzqd;
	}
	
	public void setZrzqd(java.lang.String value) {
		this.zrzqd = value;
	}

	
	public java.lang.String getLgort() {
		return this.lgort;
	}
	
	public void setLgort(java.lang.String value) {
		this.lgort = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("Maktx",getMaktx())
			.append("Matnr",getMatnr())
			.append("Msehl",getMsehl())
			.append("Matkl",getMatkl())
			.append("Zbz",getZbz())
			.append("Zrzqd",getZrzqd())
			.append("Lgort",getLgort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getMaktx())
			.append(getMatnr())
			.append(getMsehl())
			.append(getMatkl())
			.append(getZbz())
			.append(getZrzqd())
			.append(getLgort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTbom == false) return false;
		if(this == obj) return true;
		ZgTbom other = (ZgTbom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getMaktx(),other.getMaktx())
			.append(getMatnr(),other.getMatnr())
			.append(getMsehl(),other.getMsehl())
			.append(getMatkl(),other.getMatkl())
			.append(getZbz(),other.getZbz())
			.append(getZrzqd(),other.getZrzqd())
			.append(getLgort(),other.getLgort())
			.isEquals();
	}

	public java.lang.String getLgortLabelCn() {
		return lgortLabelCn;
	}

	public void setLgortLabelCn(java.lang.String lgortLabelCn) {
		this.lgortLabelCn = lgortLabelCn;
	}

	public java.lang.String getLabelCn() {
		return labelCn;
	}

	public void setLabelCn(java.lang.String labelCn) {
		this.labelCn = labelCn;
	}

	public java.lang.String getPlant() {
		return plant;
	}

	public void setPlant(java.lang.String plant) {
		this.plant = plant;
	}

	public java.lang.String getCarNum() {
		return carNum;
	}

	public void setCarNum(java.lang.String carNum) {
		this.carNum = carNum;
	}

	public String getIdnrk() {
		return idnrk;
	}

	public void setIdnrk(String idnrk) {
		this.idnrk = idnrk;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public RelatedModel getCarId_related() {
		return carId_related;
	}

	public void setCarId_related(RelatedModel carId_related) {
		this.carId_related = carId_related;
	}

	public java.lang.String getCarId_labelCn() {
		return carId_labelCn;
	}

	public void setCarId_labelCn(java.lang.String carId_labelCn) {
		this.carId_labelCn = carId_labelCn;
	}



	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	/**
	 * @return the meins
	 */
	public String getMeins() {
		return meins;
	}

	/**
	 * @param meins the meins to set
	 */
	public void setMeins(String meins) {
		this.meins = meins;
	}

	
}
