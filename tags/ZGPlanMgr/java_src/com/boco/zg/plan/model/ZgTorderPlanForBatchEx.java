package com.boco.zg.plan.model;

import java.util.Date;
import java.util.List;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

import javacommon.base.BaseEntity;
import javacommon.base.model.EnumModel;
import javacommon.base.service.IVmModelBo;

/**
 * 批量领料计划
 * @author wengq
 *
 */
public class ZgTorderPlanForBatchEx extends BaseEntity{
	
	public static final String FORMAT_PXDAT = DATE_FORMAT;
	public static final String FORMAT_PCDAT = DATE_FORMAT;
	public static final String FORMAT_PLAN_DATE = DATE_FORMAT;
	
	private String cuid;
	private Date pxdat;
	private Date pxdat_start;
	private Date pxdat_end;
	private String aufnr;
	private Date pcdat;
	private Date pcdat_start;
	private Date pcdat_end;
	private String kdauf;
	private String planType;
	private EnumModel planType_enum = new EnumModel("SORTF_PLAN_TYPE");
	private String matnr;
	private String maktx1;
	private String psmng;
	private String plant;
	private String arbpl;
	private String departmentId;
	private String departmentName;
	private String userId;
	private String userName;
	private Date planDate;
	private Date planDate_start;
	private Date planDate_end;
	private String planStartTime;
	private String planEndTime;
	private String state;
	private EnumModel state_enum = new EnumModel("ORDER_PLAN_STATE");
	
	public EnumModel getState_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("ORDER_PLAN_STATE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getState())){
				this.state_enum.setValue(value.getName());
				this.state_enum.setId(this.getState());
			}
		}
		return this.state_enum;
	}
	
	public void setState_enum(EnumModel value) {
		this.state_enum = value;
	}
	
	public EnumModel getPlanType_enum() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		List<TmdEnumevalue> list = service.getEnumValue("SORTF_PLAN_TYPE");
		for(TmdEnumevalue value:list){
			if(value.getValue().equals(this.getPlanType())){
				this.planType_enum.setValue(value.getName());
				this.planType_enum.setId(this.getPlanType());
			}
		}
		return this.planType_enum;
	}
	
	public void setPlanType_enum(EnumModel value) {
		this.planType_enum = value;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPxdatString(String value) {
		setPxdat(string2Date(value, FORMAT_PXDAT,java.util.Date.class));
	}
	public String getPxdatString() {
		return date2String(getPxdat(), FORMAT_PXDAT);
	}
	
	public void setPcdatString(String value) {
		setPcdat(string2Date(value, FORMAT_PCDAT,java.util.Date.class));
	}
	
	public String getPcdatString() {
		return date2String(getPcdat(), FORMAT_PCDAT);
	}
	
	public void setPlanDateString(String value) {
		setPlanDate(string2Date(value, FORMAT_PLAN_DATE,java.util.Date.class));
	}
	
	public String getPlanDateString() {
		return date2String(getPlanDate(), FORMAT_PLAN_DATE);
	}
	
	public Date getPlanDate_start() {
		return planDate_start;
	}
	public void setPlanDate_start(Date planDate_start) {
		this.planDate_start = planDate_start;
	}
	public Date getPlanDate_end() {
		return planDate_end;
	}
	public void setPlanDate_end(Date planDate_end) {
		this.planDate_end = planDate_end;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getAufnr() {
		return aufnr;
	}
	public void setAufnr(String aufnr) {
		this.aufnr = aufnr;
	}
	public String getKdauf() {
		return kdauf;
	}
	public void setKdauf(String kdauf) {
		this.kdauf = kdauf;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getMaktx1() {
		return maktx1;
	}
	public void setMaktx1(String maktx1) {
		this.maktx1 = maktx1;
	}
	public String getPsmng() {
		return psmng;
	}
	public void setPsmng(String psmng) {
		this.psmng = psmng;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getArbpl() {
		return arbpl;
	}
	public void setArbpl(String arbpl) {
		this.arbpl = arbpl;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getPxdat() {
		return pxdat;
	}
	public void setPxdat(Date pxdat) {
		this.pxdat = pxdat;
	}
	public Date getPcdat() {
		return pcdat;
	}
	public void setPcdat(Date pcdat) {
		this.pcdat = pcdat;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public Date getPxdat_start() {
		return pxdat_start;
	}
	public void setPxdat_start(Date pxdat_start) {
		this.pxdat_start = pxdat_start;
	}
	public Date getPxdat_end() {
		return pxdat_end;
	}
	public void setPxdat_end(Date pxdat_end) {
		this.pxdat_end = pxdat_end;
	}
	public Date getPcdat_start() {
		return pcdat_start;
	}
	public void setPcdat_start(Date pcdat_start) {
		this.pcdat_start = pcdat_start;
	}
	public Date getPcdat_end() {
		return pcdat_end;
	}
	public void setPcdat_end(Date pcdat_end) {
		this.pcdat_end = pcdat_end;
	}
}
