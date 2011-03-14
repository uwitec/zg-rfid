package com.boco.frame.sys.base.model;

import javacommon.base.BaseEntity;
import javacommon.base.model.RelatedModel;

public class ZgBomCar extends BaseEntity {

	private java.lang.String cuid;//中间关系表的主键ID
	private Long carNum;
	
	private java.lang.String bomId;
	private java.lang.String idnrk;//IDNRK
	private java.lang.String maktx;//物料描述
	private java.lang.String matnr;//物料号
	private java.lang.String msehl;//度量单位文本
	private java.lang.String matkl;//物料组ID
	private java.lang.String matkl_labelCn;//物料组名字
	private java.lang.String zbz;//物料备注
	private java.lang.String zrzqd;//认证清单
	private java.lang.String lgort;//库存地点
	private java.lang.String bom_label_cn;//BOM组件名称
	private java.lang.String plant;//生产厂
	private java.lang.String plantid;//生产厂编号
	private java.lang.String bom_type;//BOM类型
	
	private java.lang.String carinfoId;//车型表主键
	private java.lang.String code;//车型编号
	private java.lang.String carLabelCn;//车型名称
	private String carId;
//	private RelatedModel carId_related = new RelatedModel("ZG_CARINFO","CUID","LABEL_CN");
	private java.lang.String carId_labelCn;
	private String bomCarState;
	
	public java.lang.String getCuid() {
		return cuid;
	}
	public void setCuid(java.lang.String cuid) {
		this.cuid = cuid;
	}
	public java.lang.String getCarinfoId() {
		return carinfoId;
	}
	public void setCarinfoId(java.lang.String carinfoId) {
		this.carinfoId = carinfoId;
	}
	public java.lang.String getBomId() {
		return bomId;
	}
	public void setBomId(java.lang.String bomId) {
		this.bomId = bomId;
	}

	public java.lang.String getIdnrk() {
		return idnrk;
	}
	public void setIdnrk(java.lang.String idnrk) {
		this.idnrk = idnrk;
	}
	public java.lang.String getMaktx() {
		return maktx;
	}
	public void setMaktx(java.lang.String maktx) {
		this.maktx = maktx;
	}
	public java.lang.String getMatnr() {
		return matnr;
	}
	public void setMatnr(java.lang.String matnr) {
		this.matnr = matnr;
	}
	public java.lang.String getMsehl() {
		return msehl;
	}
	public void setMsehl(java.lang.String msehl) {
		this.msehl = msehl;
	}
	public java.lang.String getMatkl() {
		return matkl;
	}
	public void setMatkl(java.lang.String matkl) {
		this.matkl = matkl;
	}
	public java.lang.String getZbz() {
		return zbz;
	}
	public void setZbz(java.lang.String zbz) {
		this.zbz = zbz;
	}
	public java.lang.String getZrzqd() {
		return zrzqd;
	}
	public void setZrzqd(java.lang.String zrzqd) {
		this.zrzqd = zrzqd;
	}
	public java.lang.String getLgort() {
		return lgort;
	}
	public void setLgort(java.lang.String lgort) {
		this.lgort = lgort;
	}
	public java.lang.String getBom_label_cn() {
		return bom_label_cn;
	}
	public void setBom_label_cn(java.lang.String bom_label_cn) {
		this.bom_label_cn = bom_label_cn;
	}
	public java.lang.String getPlant() {
		return plant;
	}
	public void setPlant(java.lang.String plant) {
		this.plant = plant;
	}
	public java.lang.String getPlantid() {
		return plantid;
	}
	public void setPlantid(java.lang.String plantid) {
		this.plantid = plantid;
	}
	public java.lang.String getBom_type() {
		return bom_type;
	}
	public void setBom_type(java.lang.String bom_type) {
		this.bom_type = bom_type;
	}
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	public java.lang.String getMatkl_labelCn() {
		return matkl_labelCn;
	}
	public void setMatkl_labelCn(java.lang.String matkl_labelCn) {
		this.matkl_labelCn = matkl_labelCn;
	}
	public java.lang.String getCarLabelCn() {
		return carLabelCn;
	}
	public void setCarLabelCn(java.lang.String carLabelCn) {
		this.carLabelCn = carLabelCn;
	}
	public Long getCarNum() {
		return carNum;
	}
	public void setCarNum(Long carNum) {
		this.carNum = carNum;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
//	public RelatedModel getCarId_related() {
//		return carId_related;
//	}
//	public void setCarId_related(RelatedModel carId_related) {
//		this.carId_related = carId_related;
//	}
	public java.lang.String getCarId_labelCn() {
		return carId_labelCn;
	}
	public void setCarId_labelCn(java.lang.String carId_labelCn) {
		this.carId_labelCn = carId_labelCn;
	}
	public String getBomCarState() {
		return bomCarState;
	}
	public void setBomCarState(String bomCarState) {
		this.bomCarState = bomCarState;
	}
}
