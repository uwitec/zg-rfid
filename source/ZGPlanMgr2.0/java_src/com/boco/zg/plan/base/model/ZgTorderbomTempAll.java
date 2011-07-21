/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderbomTempAll extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDERBOM_TEMP_ALL";
	public static final String BM_CLASS_ID = "ZG_T_ORDERBOM_TEMP_ALL";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_MENGE = "MENGE";
	public static final String ALIAS_MATKL = "MATKL";
	public static final String ALIAS_SORTF = "SORTF";
	public static final String ALIAS_LGORT = "LGORT";
	public static final String ALIAS_ZBZ = "ZBZ";
	public static final String ALIAS_ZRZQD = "ZRZQD";
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_IDNRK = "IDNRK";
	public static final String ALIAS_ORDER_ID = "ORDER_ID";
	public static final String ALIAS_AUFNR = "AUFNR";
	public static final String ALIAS_ARBPL = "ARBPL";
	public static final String ALIAS_MATNR = "MATNR";
	public static final String ALIAS_MAKTX1 = "MAKTX1";
	public static final String ALIAS_MAKTX2 = "MAKTX2";
	public static final String ALIAS_MSEHL1 = "MSEHL1";
	public static final String ALIAS_MSEHL2 = "MSEHL2";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_SORTF_H = "SORTF_H";
	public static final String ALIAS_MATNR1 = "MATNR1";
	public static final String ALIAS_STORAGE_STATE = "STORAGE_STATE";
	public static final String ALIAS_STORAGE_NUM = "STORAGE_NUM";
	public static final String ALIAS_ZDTYL = "ZDTYL";
	public static final String ALIAS_PLANT = "PLANT";
	public static final String ALIAS_BATCH_NO = "BATCH_NO";
	public static final String ALIAS_OPERATE_TYPE = "OPERATE_TYPE";
	public static final String ALIAS_POSNR = "POSNR";
	public static final String ALIAS_MATKL_SELF = "MATKL_SELF";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getMaktx2()==null?"":this.getMaktx2().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.Long menge;
	private java.lang.String matkl;
	private java.lang.String sortf;
	private java.lang.String lgort;
	private java.lang.String zbz;
	private java.lang.String zrzqd;
	private java.lang.String cuid;
	private java.lang.String idnrk;
	private java.lang.String orderId;
	private java.lang.String aufnr;
	private java.lang.String arbpl;
	private java.lang.String matnr;
	private java.lang.String maktx1;
	private java.lang.String maktx2;
	private java.lang.String msehl1;
	private java.lang.String msehl2;
	private java.lang.String labelCn;
	private java.lang.String sortfH;
	private java.lang.String matnr1;
	private java.lang.String storageState;
	private java.lang.Long storageNum;
	private java.lang.Long zdtyl;
	private java.lang.String plant;
	private java.lang.Long batchNo;
	private java.lang.Long operateType;
	private java.lang.String posnr;
	private java.lang.String matklSelf;
	//columns END
	public java.lang.Long getMenge() {
		return this.menge;
	}
	
	public void setMenge(java.lang.Long value) {
		this.menge = value;
	}
	public java.lang.String getMatkl() {
		return this.matkl;
	}
	
	public void setMatkl(java.lang.String value) {
		this.matkl = value;
	}
	public java.lang.String getSortf() {
		return this.sortf;
	}
	
	public void setSortf(java.lang.String value) {
		this.sortf = value;
	}
	public java.lang.String getLgort() {
		return this.lgort;
	}
	
	public void setLgort(java.lang.String value) {
		this.lgort = value;
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
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getIdnrk() {
		return this.idnrk;
	}
	
	public void setIdnrk(java.lang.String value) {
		this.idnrk = value;
	}
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	public java.lang.String getAufnr() {
		return this.aufnr;
	}
	
	public void setAufnr(java.lang.String value) {
		this.aufnr = value;
	}
	public java.lang.String getArbpl() {
		return this.arbpl;
	}
	
	public void setArbpl(java.lang.String value) {
		this.arbpl = value;
	}
	public java.lang.String getMatnr() {
		return this.matnr;
	}
	
	public void setMatnr(java.lang.String value) {
		this.matnr = value;
	}
	public java.lang.String getMaktx1() {
		return this.maktx1;
	}
	
	public void setMaktx1(java.lang.String value) {
		this.maktx1 = value;
	}
	public java.lang.String getMaktx2() {
		return this.maktx2;
	}
	
	public void setMaktx2(java.lang.String value) {
		this.maktx2 = value;
	}
	public java.lang.String getMsehl1() {
		return this.msehl1;
	}
	
	public void setMsehl1(java.lang.String value) {
		this.msehl1 = value;
	}
	public java.lang.String getMsehl2() {
		return this.msehl2;
	}
	
	public void setMsehl2(java.lang.String value) {
		this.msehl2 = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.String getSortfH() {
		return this.sortfH;
	}
	
	public void setSortfH(java.lang.String value) {
		this.sortfH = value;
	}
	public java.lang.String getMatnr1() {
		return this.matnr1;
	}
	
	public void setMatnr1(java.lang.String value) {
		this.matnr1 = value;
	}
	public java.lang.String getStorageState() {
		return this.storageState;
	}
	
	public void setStorageState(java.lang.String value) {
		this.storageState = value;
	}
	public java.lang.Long getStorageNum() {
		return this.storageNum;
	}
	
	public void setStorageNum(java.lang.Long value) {
		this.storageNum = value;
	}
	public java.lang.Long getZdtyl() {
		return this.zdtyl;
	}
	
	public void setZdtyl(java.lang.Long value) {
		this.zdtyl = value;
	}
	public java.lang.String getPlant() {
		return this.plant;
	}
	
	public void setPlant(java.lang.String value) {
		this.plant = value;
	}
	public java.lang.Long getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(java.lang.Long value) {
		this.batchNo = value;
	}
	public java.lang.Long getOperateType() {
		return this.operateType;
	}
	
	public void setOperateType(java.lang.Long value) {
		this.operateType = value;
	}
	public java.lang.String getPosnr() {
		return this.posnr;
	}
	
	public void setPosnr(java.lang.String value) {
		this.posnr = value;
	}
	public java.lang.String getMatklSelf() {
		return this.matklSelf;
	}
	
	public void setMatklSelf(java.lang.String value) {
		this.matklSelf = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Menge",getMenge())
			.append("Matkl",getMatkl())
			.append("Sortf",getSortf())
			.append("Lgort",getLgort())
			.append("Zbz",getZbz())
			.append("Zrzqd",getZrzqd())
			.append("Cuid",getCuid())
			.append("Idnrk",getIdnrk())
			.append("OrderId",getOrderId())
			.append("Aufnr",getAufnr())
			.append("Arbpl",getArbpl())
			.append("Matnr",getMatnr())
			.append("Maktx1",getMaktx1())
			.append("Maktx2",getMaktx2())
			.append("Msehl1",getMsehl1())
			.append("Msehl2",getMsehl2())
			.append("LabelCn",getLabelCn())
			.append("SortfH",getSortfH())
			.append("Matnr1",getMatnr1())
			.append("StorageState",getStorageState())
			.append("StorageNum",getStorageNum())
			.append("Zdtyl",getZdtyl())
			.append("Plant",getPlant())
			.append("BatchNo",getBatchNo())
			.append("OperateType",getOperateType())
			.append("Posnr",getPosnr())
			.append("MatklSelf",getMatklSelf())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMenge())
			.append(getMatkl())
			.append(getSortf())
			.append(getLgort())
			.append(getZbz())
			.append(getZrzqd())
			.append(getCuid())
			.append(getIdnrk())
			.append(getOrderId())
			.append(getAufnr())
			.append(getArbpl())
			.append(getMatnr())
			.append(getMaktx1())
			.append(getMaktx2())
			.append(getMsehl1())
			.append(getMsehl2())
			.append(getLabelCn())
			.append(getSortfH())
			.append(getMatnr1())
			.append(getStorageState())
			.append(getStorageNum())
			.append(getZdtyl())
			.append(getPlant())
			.append(getBatchNo())
			.append(getOperateType())
			.append(getPosnr())
			.append(getMatklSelf())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderbomTempAll == false) return false;
		if(this == obj) return true;
		ZgTorderbomTempAll other = (ZgTorderbomTempAll)obj;
		return new EqualsBuilder()
			.append(getMenge(),other.getMenge())
			.append(getMatkl(),other.getMatkl())
			.append(getSortf(),other.getSortf())
			.append(getLgort(),other.getLgort())
			.append(getZbz(),other.getZbz())
			.append(getZrzqd(),other.getZrzqd())
			.append(getCuid(),other.getCuid())
			.append(getIdnrk(),other.getIdnrk())
			.append(getOrderId(),other.getOrderId())
			.append(getAufnr(),other.getAufnr())
			.append(getArbpl(),other.getArbpl())
			.append(getMatnr(),other.getMatnr())
			.append(getMaktx1(),other.getMaktx1())
			.append(getMaktx2(),other.getMaktx2())
			.append(getMsehl1(),other.getMsehl1())
			.append(getMsehl2(),other.getMsehl2())
			.append(getLabelCn(),other.getLabelCn())
			.append(getSortfH(),other.getSortfH())
			.append(getMatnr1(),other.getMatnr1())
			.append(getStorageState(),other.getStorageState())
			.append(getStorageNum(),other.getStorageNum())
			.append(getZdtyl(),other.getZdtyl())
			.append(getPlant(),other.getPlant())
			.append(getBatchNo(),other.getBatchNo())
			.append(getOperateType(),other.getOperateType())
			.append(getPosnr(),other.getPosnr())
			.append(getMatklSelf(),other.getMatklSelf())
			.isEquals();
	}
}
