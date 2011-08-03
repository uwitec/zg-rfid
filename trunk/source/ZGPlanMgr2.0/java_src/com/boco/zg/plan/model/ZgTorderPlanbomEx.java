package com.boco.zg.plan.model;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

import javacommon.base.BaseEntity;
import javacommon.base.model.EnumModel;
import javacommon.base.model.RelatedModel;
import javacommon.base.service.IVmModelBo;

public class ZgTorderPlanbomEx extends BaseEntity{
	
	public static final String FORMAT_PLAN_DATE = DATE_FORMAT;
	
	private String cuid;
	private String orderPlanId;
	private String orderBomId;
	private String orderId;
	private String bomId;
	private RelatedModel bomId_related = new RelatedModel("ZG_T_BOM","CUID","LABEL_CN");
	private java.lang.String bomId_labelCn;
	private java.lang.String idnrk;
	private java.lang.String maktx1;
	private java.lang.String maktx2;
	private java.lang.String menge;
	private java.lang.String matnr;
	private java.lang.Long zdtyl;
	private java.lang.String msehl1;
	private java.lang.String msehl2;
	private java.lang.String lgort;
	private java.lang.String lgort_lableCn;
	private RelatedModel lgort_related = new RelatedModel("FW_ORGANIZATION","CUID","LABEL_CN");
	private java.lang.String departmentId_labelCn;
	private RelatedModel departmentId_related = new RelatedModel("FW_ROLE","CUID","LABEL_CN");
	private java.lang.String departmentId;
	private java.lang.String userId_labelCn;
	private RelatedModel userId_related = new RelatedModel("FW_EMPLOYEE","CUID","LABEL_CN");
	private java.lang.String userId;
	private java.util.Date planDate_start;
	private java.util.Date planDate_end;
	private java.util.Date planDate;//领料时间
	private java.lang.String planStartTime;//开始时间
	private java.lang.String planEndTime;//结束时间
	private java.lang.Long carNum;
	private java.lang.String zbz;
	private java.lang.String state;
	private Long planNum;
	private Long completeNum;
	private String taskBomId;
	
	private Long waitBackNum;
	
	private boolean isDel=false;//由于编辑修改bom组件时，都是放在session中，然后再统一提交修改，本字段用于标记bom组件是否补删除
	private boolean isModity=false;//同上，用于标记该组件是否被修改过
	
	private java.lang.String aufnr;//单据编号
	
	private java.lang.Long auditNum;
	private String posnr;
	private double per;
	
	private String carId;
	private Long carnum;
	private String orderTaskId;
	
	private String sortf;
	
	private String meins;
	private String msehl;
	
	/*
	 * wjz，领料组，领料人
	 */
	
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getOrderPlanId() {
		return orderPlanId;
	}
	public void setOrderPlanId(String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}
	public String getOrderBomId() {
		return orderBomId;
	}
	public void setOrderBomId(String orderBomId) {
		this.orderBomId = orderBomId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public java.lang.String getIdnrk() {
		return idnrk;
	}
	public void setIdnrk(java.lang.String idnrk) {
		this.idnrk = idnrk;
	}
	public java.lang.String getMaktx1() {
		return maktx1;
	}
	public void setMaktx1(java.lang.String maktx1) {
		this.maktx1 = maktx1;
	}
	public java.lang.String getMenge() {
		return menge;
	}
	public void setMenge(java.lang.String menge) {
		this.menge = menge;
	}
	public java.lang.Long getZdtyl() {
		return zdtyl;
	}
	public void setZdtyl(java.lang.Long zdtyl) {
		this.zdtyl = zdtyl;
	}
	public java.lang.String getMsehl1() {
		return msehl1;
	}
	public void setMsehl1(java.lang.String msehl1) {
		this.msehl1 = msehl1;
	}
	public java.lang.String getLgort() {
		return lgort;
	}
	public void setLgort(java.lang.String lgort) {
		this.lgort = lgort;
	}
	public java.lang.String getDepartmentId_labelCn() {
		return departmentId_labelCn;
	}
	public void setDepartmentId_labelCn(java.lang.String departmentId_labelCn) {
		this.departmentId_labelCn = departmentId_labelCn;
	}
	public RelatedModel getDepartmentId_related() {
		return departmentId_related;
	}
	public void setDepartmentId_related(RelatedModel departmentId_related) {
		this.departmentId_related = departmentId_related;
	}
	public java.lang.String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(java.lang.String departmentId) {
		this.departmentId = departmentId;
	}
	public java.lang.String getUserId_labelCn() {
		return userId_labelCn;
	}
	public void setUserId_labelCn(java.lang.String userId_labelCn) {
		this.userId_labelCn = userId_labelCn;
	}
	public RelatedModel getUserId_related() {
		return userId_related;
	}
	public void setUserId_related(RelatedModel userId_related) {
		this.userId_related = userId_related;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.util.Date getPlanDate_start() {
		return planDate_start;
	}
	public void setPlanDate_start(java.util.Date planDate_start) {
		this.planDate_start = planDate_start;
	}
	public java.util.Date getPlanDate_end() {
		return planDate_end;
	}
	public void setPlanDate_end(java.util.Date planDate_end) {
		this.planDate_end = planDate_end;
	}
	public java.util.Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(java.util.Date planDate) {
		this.planDate = planDate;
	}
	public void setPlanDateString(String value) {
		setPlanDate(string2Date(value, FORMAT_PLAN_DATE,java.util.Date.class));
	}
	public String getPlanDateString() {
		return date2String(getPlanDate(), FORMAT_PLAN_DATE);
	}
	public java.lang.String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(java.lang.String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public java.lang.String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(java.lang.String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getBomId() {
		return bomId;
	}
	public void setBomId(String bomId) {
		this.bomId = bomId;
	}
	public java.lang.Long getCarNum() {
		return carNum;
	}
	public void setCarNum(java.lang.Long carNum) {
		this.carNum = carNum;
	}
	public java.lang.String getLgort_lableCn() {
		return lgort_lableCn;
	}
	public void setLgort_lableCn(java.lang.String lgort_lableCn) {
		this.lgort_lableCn = lgort_lableCn;
	}
	public RelatedModel getLgort_related() {
		return lgort_related;
	}
	public void setLgort_related(RelatedModel lgort_related) {
		this.lgort_related = lgort_related;
	}
	public java.lang.String getZbz() {
		return zbz;
	}
	public void setZbz(java.lang.String zbz) {
		this.zbz = zbz;
	}
	public RelatedModel getBomId_related() {
		return bomId_related;
	}
	public void setBomId_related(RelatedModel bomId_related) {
		this.bomId_related = bomId_related;
	}
	public java.lang.String getBomId_labelCn() {
		return bomId_labelCn;
	}
	public void setBomId_labelCn(java.lang.String bomId_labelCn) {
		this.bomId_labelCn = bomId_labelCn;
	}
	public boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}
	public boolean getIsModity() {
		return isModity;
	}
	public void setIsModity(boolean isModity) {
		this.isModity = isModity;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getMatnr() {
		return matnr;
	}
	public void setMatnr(java.lang.String matnr) {
		this.matnr = matnr;
	}
	public java.lang.String getAufnr() {
		return aufnr;
	}
	public void setAufnr(java.lang.String aufnr) {
		this.aufnr = aufnr;
	}
	public Long getPlanNum() {
		return planNum;
	}
	public void setPlanNum(Long planNum) {
		this.planNum = planNum;
	}
	public java.lang.String getMaktx2() {
		return maktx2;
	}
	public void setMaktx2(java.lang.String maktx2) {
		this.maktx2 = maktx2;
	}
	public java.lang.Long getAuditNum() {
		return auditNum;
	}
	public void setAuditNum(java.lang.Long auditNum) {
		this.auditNum = auditNum;
	}
	public java.lang.String getMsehl2() {
		return msehl2;
	}
	public void setMsehl2(java.lang.String msehl2) {
		this.msehl2 = msehl2;
	}
	public Long getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Long completeNum) {
		this.completeNum = completeNum;
	}
	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public Long getCarnum() {
		return carnum;
	}
	public void setCarnum(Long carnum) {
		this.carnum = carnum;
	}
	public Long getWaitBackNum() {
		return waitBackNum;
	}
	public void setWaitBackNum(Long waitBackNum) {
		this.waitBackNum = waitBackNum;
	}
	/**
	 * @return the taskBomId
	 */
	public String getTaskBomId() {
		return taskBomId;
	}
	/**
	 * @param taskBomId the taskBomId to set
	 */
	public void setTaskBomId(String taskBomId) {
		this.taskBomId = taskBomId;
	}
	/**
	 * @return the orderTaskId
	 */
	public String getOrderTaskId() {
		return orderTaskId;
	}
	/**
	 * @param orderTaskId the orderTaskId to set
	 */
	public void setOrderTaskId(String orderTaskId) {
		this.orderTaskId = orderTaskId;
	}
	/**
	 * @return the sortf
	 */
	public String getSortf() {
		return sortf;
	}
	/**
	 * @param sortf the sortf to set
	 */
	public void setSortf(String sortf) {
		this.sortf = sortf;
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
	/**
	 * @return the msehl
	 */
	public String getMsehl() {
		return msehl;
	}
	/**
	 * @param msehl the msehl to set
	 */
	public void setMsehl(String msehl) {
		this.msehl = msehl;
	}
	
}
