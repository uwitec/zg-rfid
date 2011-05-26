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


public class ZgTorderTemp extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_TEMP";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_TEMP";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_PXDAT = "PXDAT";
	public static final String ALIAS_PLANT = "PLANT";
	public static final String ALIAS_MIPOS = "MIPOS";
	public static final String ALIAS_PCDAT = "PCDAT";
	public static final String ALIAS_ARBPL = "ARBPL";
	public static final String ALIAS_MATNR = "MATNR";
	public static final String ALIAS_KDAUF = "KDAUF";
	public static final String ALIAS_KDPOS = "KDPOS";
	public static final String ALIAS_KDTXT = "KDTXT";
	public static final String ALIAS_ZCKPP = "ZCKPP";
	public static final String ALIAS_MAKTX2 = "MAKTX2";
	public static final String ALIAS_MAKTX1 = "MAKTX1";
	public static final String ALIAS_ZZCKS = "ZZCKS";
	public static final String ALIAS_ATWRT2 = "ATWRT2";
	public static final String ALIAS_PSMNG = "PSMNG";
	public static final String ALIAS_PMENGE = "PMENGE";
	public static final String ALIAS_ZTXT02 = "ZTXT02";
	public static final String ALIAS_ZDBLC = "ZDBLC";
	public static final String ALIAS_BRGEW2 = "BRGEW2";
	public static final String ALIAS_CRDAT = "CRDAT";
	public static final String ALIAS_CPUTM = "CPUTM";
	public static final String ALIAS_CRNAM = "CRNAM";
	public static final String ALIAS_MRNAM = "MRNAM";
	public static final String ALIAS_ZMUZE = "ZMUZE";
	public static final String ALIAS_MNAME = "MNAME";
	public static final String ALIAS_FBDAT = "FBDAT";
	public static final String ALIAS_FNAME = "FNAME";
	public static final String ALIAS_FBUZE = "FBUZE";
	public static final String ALIAS_PFLAG = "PFLAG";
	public static final String ALIAS_AUFNR = "AUFNR";
	public static final String ALIAS_ORDER_STATE = "ORDER_STATE";
	public static final String ALIAS_SUBMIT_USER = "SUBMIT_USER";
	public static final String ALIAS_SUBMIT_DATE = "SUBMIT_DATE";
	public static final String ALIAS_MANDT = "MANDT";
	public static final String ALIAS_BATCH_NO = "BATCH_NO";
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_LABEL_CN = "LABEL_CN";
	public static final String ALIAS_OPERATE_TYPE = "OPERATE_TYPE";
	public static final String ALIAS_PSBH = "PSBH";
	public static final String ALIAS_POSKEY = "POSKEY";
	public static final String ALIAS_ARBPL1 = "ARBPL1";
	//date formats
	public static final String FORMAT_PXDAT = DATE_FORMAT;
	public static final String FORMAT_PCDAT = DATE_FORMAT;
	public static final String FORMAT_CRDAT = DATE_FORMAT;
	public static final String FORMAT_CPUTM = DATE_FORMAT;
	public static final String FORMAT_MRNAM = DATE_FORMAT;
	public static final String FORMAT_ZMUZE = DATE_FORMAT;
	public static final String FORMAT_FBDAT = DATE_FORMAT;
	public static final String FORMAT_FBUZE = DATE_FORMAT;
	public static final String FORMAT_SUBMIT_DATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getLabelCn()==null?"":this.getLabelCn().toString();
		return labelValue;
	}
	
	//columns START
	private java.util.Date pxdat_start;
	private java.util.Date pxdat_end;
	private java.util.Date pxdat;
	private java.lang.String plant;
	private java.lang.String mipos;
	private java.util.Date pcdat_start;
	private java.util.Date pcdat_end;
	private java.util.Date pcdat;
	private java.lang.String arbpl;
	private java.lang.String matnr;
	private java.lang.String kdauf;
	private java.lang.Long kdpos;
	private java.lang.String kdtxt;
	private java.lang.String zckpp;
	private java.lang.String maktx2;
	private java.lang.String maktx1;
	private java.lang.String zzcks;
	private java.lang.String atwrt2;
	private java.lang.Long psmng;
	private java.lang.Long pmenge;
	private java.lang.String ztxt02;
	private java.lang.String zdblc;
	private java.lang.String brgew2;
	private java.util.Date crdat_start;
	private java.util.Date crdat_end;
	private java.util.Date crdat;
	private java.util.Date cputm_start;
	private java.util.Date cputm_end;
	private java.util.Date cputm;
	private java.lang.String crnam;
	private java.util.Date mrnam_start;
	private java.util.Date mrnam_end;
	private java.util.Date mrnam;
	private java.util.Date zmuze_start;
	private java.util.Date zmuze_end;
	private java.util.Date zmuze;
	private java.lang.String mname;
	private java.util.Date fbdat_start;
	private java.util.Date fbdat_end;
	private java.util.Date fbdat;
	private java.lang.String fname;
	private java.util.Date fbuze_start;
	private java.util.Date fbuze_end;
	private java.util.Date fbuze;
	private java.lang.String pflag;
	private java.lang.String aufnr;
	private java.lang.String orderState;
	private java.lang.String submitUser;
	private java.util.Date submitDate_start;
	private java.util.Date submitDate_end;
	private java.util.Date submitDate;
	private java.lang.String mandt;
	private java.lang.String batchNo;
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.Long operateType;
	private java.lang.Long psbh;
	private java.lang.String poskey;
	private java.lang.String arbpl1;
	//columns END
	public String getPxdatString() {
		return date2String(getPxdat(), FORMAT_PXDAT);
	}
	public void setPxdatString(String value) {
		setPxdat(string2Date(value, FORMAT_PXDAT,java.util.Date.class));
	}
	public java.util.Date getPxdat_start() {
		return this.pxdat_start;
	}
	
	public void setPxdat_start(java.util.Date value) {
		this.pxdat_start = value;
	}
	
	public java.util.Date getPxdat_end() {
		return this.pxdat_end;
	}
	
	public void setPxdat_end(java.util.Date value) {
		this.pxdat_end = value;
	}
	public java.util.Date getPxdat() {
		return this.pxdat;
	}
	
	public void setPxdat(java.util.Date value) {
		this.pxdat = value;
	}
	public java.lang.String getPlant() {
		return this.plant;
	}
	
	public void setPlant(java.lang.String value) {
		this.plant = value;
	}
	public java.lang.String getMipos() {
		return this.mipos;
	}
	
	public void setMipos(java.lang.String value) {
		this.mipos = value;
	}
	public String getPcdatString() {
		return date2String(getPcdat(), FORMAT_PCDAT);
	}
	public void setPcdatString(String value) {
		setPcdat(string2Date(value, FORMAT_PCDAT,java.util.Date.class));
	}
	public java.util.Date getPcdat_start() {
		return this.pcdat_start;
	}
	
	public void setPcdat_start(java.util.Date value) {
		this.pcdat_start = value;
	}
	
	public java.util.Date getPcdat_end() {
		return this.pcdat_end;
	}
	
	public void setPcdat_end(java.util.Date value) {
		this.pcdat_end = value;
	}
	public java.util.Date getPcdat() {
		return this.pcdat;
	}
	
	public void setPcdat(java.util.Date value) {
		this.pcdat = value;
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
	public java.lang.String getKdauf() {
		return this.kdauf;
	}
	
	public void setKdauf(java.lang.String value) {
		this.kdauf = value;
	}
	public java.lang.Long getKdpos() {
		return this.kdpos;
	}
	
	public void setKdpos(java.lang.Long value) {
		this.kdpos = value;
	}
	public java.lang.String getKdtxt() {
		return this.kdtxt;
	}
	
	public void setKdtxt(java.lang.String value) {
		this.kdtxt = value;
	}
	public java.lang.String getZckpp() {
		return this.zckpp;
	}
	
	public void setZckpp(java.lang.String value) {
		this.zckpp = value;
	}
	public java.lang.String getMaktx2() {
		return this.maktx2;
	}
	
	public void setMaktx2(java.lang.String value) {
		this.maktx2 = value;
	}
	public java.lang.String getMaktx1() {
		return this.maktx1;
	}
	
	public void setMaktx1(java.lang.String value) {
		this.maktx1 = value;
	}
	public java.lang.String getZzcks() {
		return this.zzcks;
	}
	
	public void setZzcks(java.lang.String value) {
		this.zzcks = value;
	}
	public java.lang.String getAtwrt2() {
		return this.atwrt2;
	}
	
	public void setAtwrt2(java.lang.String value) {
		this.atwrt2 = value;
	}
	public java.lang.Long getPsmng() {
		return this.psmng;
	}
	
	public void setPsmng(java.lang.Long value) {
		this.psmng = value;
	}
	public java.lang.Long getPmenge() {
		return this.pmenge;
	}
	
	public void setPmenge(java.lang.Long value) {
		this.pmenge = value;
	}
	public java.lang.String getZtxt02() {
		return this.ztxt02;
	}
	
	public void setZtxt02(java.lang.String value) {
		this.ztxt02 = value;
	}
	public java.lang.String getZdblc() {
		return this.zdblc;
	}
	
	public void setZdblc(java.lang.String value) {
		this.zdblc = value;
	}
	public java.lang.String getBrgew2() {
		return this.brgew2;
	}
	
	public void setBrgew2(java.lang.String value) {
		this.brgew2 = value;
	}
	public String getCrdatString() {
		return date2String(getCrdat(), FORMAT_CRDAT);
	}
	public void setCrdatString(String value) {
		setCrdat(string2Date(value, FORMAT_CRDAT,java.util.Date.class));
	}
	public java.util.Date getCrdat_start() {
		return this.crdat_start;
	}
	
	public void setCrdat_start(java.util.Date value) {
		this.crdat_start = value;
	}
	
	public java.util.Date getCrdat_end() {
		return this.crdat_end;
	}
	
	public void setCrdat_end(java.util.Date value) {
		this.crdat_end = value;
	}
	public java.util.Date getCrdat() {
		return this.crdat;
	}
	
	public void setCrdat(java.util.Date value) {
		this.crdat = value;
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
	public java.lang.String getCrnam() {
		return this.crnam;
	}
	
	public void setCrnam(java.lang.String value) {
		this.crnam = value;
	}
	public String getMrnamString() {
		return date2String(getMrnam(), FORMAT_MRNAM);
	}
	public void setMrnamString(String value) {
		setMrnam(string2Date(value, FORMAT_MRNAM,java.util.Date.class));
	}
	public java.util.Date getMrnam_start() {
		return this.mrnam_start;
	}
	
	public void setMrnam_start(java.util.Date value) {
		this.mrnam_start = value;
	}
	
	public java.util.Date getMrnam_end() {
		return this.mrnam_end;
	}
	
	public void setMrnam_end(java.util.Date value) {
		this.mrnam_end = value;
	}
	public java.util.Date getMrnam() {
		return this.mrnam;
	}
	
	public void setMrnam(java.util.Date value) {
		this.mrnam = value;
	}
	public String getZmuzeString() {
		return date2String(getZmuze(), FORMAT_ZMUZE);
	}
	public void setZmuzeString(String value) {
		setZmuze(string2Date(value, FORMAT_ZMUZE,java.util.Date.class));
	}
	public java.util.Date getZmuze_start() {
		return this.zmuze_start;
	}
	
	public void setZmuze_start(java.util.Date value) {
		this.zmuze_start = value;
	}
	
	public java.util.Date getZmuze_end() {
		return this.zmuze_end;
	}
	
	public void setZmuze_end(java.util.Date value) {
		this.zmuze_end = value;
	}
	public java.util.Date getZmuze() {
		return this.zmuze;
	}
	
	public void setZmuze(java.util.Date value) {
		this.zmuze = value;
	}
	public java.lang.String getMname() {
		return this.mname;
	}
	
	public void setMname(java.lang.String value) {
		this.mname = value;
	}
	public String getFbdatString() {
		return date2String(getFbdat(), FORMAT_FBDAT);
	}
	public void setFbdatString(String value) {
		setFbdat(string2Date(value, FORMAT_FBDAT,java.util.Date.class));
	}
	public java.util.Date getFbdat_start() {
		return this.fbdat_start;
	}
	
	public void setFbdat_start(java.util.Date value) {
		this.fbdat_start = value;
	}
	
	public java.util.Date getFbdat_end() {
		return this.fbdat_end;
	}
	
	public void setFbdat_end(java.util.Date value) {
		this.fbdat_end = value;
	}
	public java.util.Date getFbdat() {
		return this.fbdat;
	}
	
	public void setFbdat(java.util.Date value) {
		this.fbdat = value;
	}
	public java.lang.String getFname() {
		return this.fname;
	}
	
	public void setFname(java.lang.String value) {
		this.fname = value;
	}
	public String getFbuzeString() {
		return date2String(getFbuze(), FORMAT_FBUZE);
	}
	public void setFbuzeString(String value) {
		setFbuze(string2Date(value, FORMAT_FBUZE,java.util.Date.class));
	}
	public java.util.Date getFbuze_start() {
		return this.fbuze_start;
	}
	
	public void setFbuze_start(java.util.Date value) {
		this.fbuze_start = value;
	}
	
	public java.util.Date getFbuze_end() {
		return this.fbuze_end;
	}
	
	public void setFbuze_end(java.util.Date value) {
		this.fbuze_end = value;
	}
	public java.util.Date getFbuze() {
		return this.fbuze;
	}
	
	public void setFbuze(java.util.Date value) {
		this.fbuze = value;
	}
	public java.lang.String getPflag() {
		return this.pflag;
	}
	
	public void setPflag(java.lang.String value) {
		this.pflag = value;
	}
	public java.lang.String getAufnr() {
		return this.aufnr;
	}
	
	public void setAufnr(java.lang.String value) {
		this.aufnr = value;
	}
	public java.lang.String getOrderState() {
		return this.orderState;
	}
	
	public void setOrderState(java.lang.String value) {
		this.orderState = value;
	}
	public java.lang.String getSubmitUser() {
		return this.submitUser;
	}
	
	public void setSubmitUser(java.lang.String value) {
		this.submitUser = value;
	}
	public String getSubmitDateString() {
		return date2String(getSubmitDate(), FORMAT_SUBMIT_DATE);
	}
	public void setSubmitDateString(String value) {
		setSubmitDate(string2Date(value, FORMAT_SUBMIT_DATE,java.util.Date.class));
	}
	public java.util.Date getSubmitDate_start() {
		return this.submitDate_start;
	}
	
	public void setSubmitDate_start(java.util.Date value) {
		this.submitDate_start = value;
	}
	
	public java.util.Date getSubmitDate_end() {
		return this.submitDate_end;
	}
	
	public void setSubmitDate_end(java.util.Date value) {
		this.submitDate_end = value;
	}
	public java.util.Date getSubmitDate() {
		return this.submitDate;
	}
	
	public void setSubmitDate(java.util.Date value) {
		this.submitDate = value;
	}
	public java.lang.String getMandt() {
		return this.mandt;
	}
	
	public void setMandt(java.lang.String value) {
		this.mandt = value;
	}
	public java.lang.String getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(java.lang.String value) {
		this.batchNo = value;
	}
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}
	public java.lang.Long getOperateType() {
		return this.operateType;
	}
	
	public void setOperateType(java.lang.Long value) {
		this.operateType = value;
	}
	public java.lang.Long getPsbh() {
		return this.psbh;
	}
	
	public void setPsbh(java.lang.Long value) {
		this.psbh = value;
	}
	public java.lang.String getPoskey() {
		return this.poskey;
	}
	
	public void setPoskey(java.lang.String value) {
		this.poskey = value;
	}
	public java.lang.String getArbpl1() {
		return this.arbpl1;
	}
	
	public void setArbpl1(java.lang.String value) {
		this.arbpl1 = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Pxdat",getPxdat())
			.append("Plant",getPlant())
			.append("Mipos",getMipos())
			.append("Pcdat",getPcdat())
			.append("Arbpl",getArbpl())
			.append("Matnr",getMatnr())
			.append("Kdauf",getKdauf())
			.append("Kdpos",getKdpos())
			.append("Kdtxt",getKdtxt())
			.append("Zckpp",getZckpp())
			.append("Maktx2",getMaktx2())
			.append("Maktx1",getMaktx1())
			.append("Zzcks",getZzcks())
			.append("Atwrt2",getAtwrt2())
			.append("Psmng",getPsmng())
			.append("Pmenge",getPmenge())
			.append("Ztxt02",getZtxt02())
			.append("Zdblc",getZdblc())
			.append("Brgew2",getBrgew2())
			.append("Crdat",getCrdat())
			.append("Cputm",getCputm())
			.append("Crnam",getCrnam())
			.append("Mrnam",getMrnam())
			.append("Zmuze",getZmuze())
			.append("Mname",getMname())
			.append("Fbdat",getFbdat())
			.append("Fname",getFname())
			.append("Fbuze",getFbuze())
			.append("Pflag",getPflag())
			.append("Aufnr",getAufnr())
			.append("OrderState",getOrderState())
			.append("SubmitUser",getSubmitUser())
			.append("SubmitDate",getSubmitDate())
			.append("Mandt",getMandt())
			.append("BatchNo",getBatchNo())
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("OperateType",getOperateType())
			.append("Psbh",getPsbh())
			.append("Poskey",getPoskey())
			.append("Arbpl1",getArbpl1())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPxdat())
			.append(getPlant())
			.append(getMipos())
			.append(getPcdat())
			.append(getArbpl())
			.append(getMatnr())
			.append(getKdauf())
			.append(getKdpos())
			.append(getKdtxt())
			.append(getZckpp())
			.append(getMaktx2())
			.append(getMaktx1())
			.append(getZzcks())
			.append(getAtwrt2())
			.append(getPsmng())
			.append(getPmenge())
			.append(getZtxt02())
			.append(getZdblc())
			.append(getBrgew2())
			.append(getCrdat())
			.append(getCputm())
			.append(getCrnam())
			.append(getMrnam())
			.append(getZmuze())
			.append(getMname())
			.append(getFbdat())
			.append(getFname())
			.append(getFbuze())
			.append(getPflag())
			.append(getAufnr())
			.append(getOrderState())
			.append(getSubmitUser())
			.append(getSubmitDate())
			.append(getMandt())
			.append(getBatchNo())
			.append(getCuid())
			.append(getLabelCn())
			.append(getOperateType())
			.append(getPsbh())
			.append(getPoskey())
			.append(getArbpl1())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderTemp == false) return false;
		if(this == obj) return true;
		ZgTorderTemp other = (ZgTorderTemp)obj;
		return new EqualsBuilder()
			.append(getPxdat(),other.getPxdat())
			.append(getPlant(),other.getPlant())
			.append(getMipos(),other.getMipos())
			.append(getPcdat(),other.getPcdat())
			.append(getArbpl(),other.getArbpl())
			.append(getMatnr(),other.getMatnr())
			.append(getKdauf(),other.getKdauf())
			.append(getKdpos(),other.getKdpos())
			.append(getKdtxt(),other.getKdtxt())
			.append(getZckpp(),other.getZckpp())
			.append(getMaktx2(),other.getMaktx2())
			.append(getMaktx1(),other.getMaktx1())
			.append(getZzcks(),other.getZzcks())
			.append(getAtwrt2(),other.getAtwrt2())
			.append(getPsmng(),other.getPsmng())
			.append(getPmenge(),other.getPmenge())
			.append(getZtxt02(),other.getZtxt02())
			.append(getZdblc(),other.getZdblc())
			.append(getBrgew2(),other.getBrgew2())
			.append(getCrdat(),other.getCrdat())
			.append(getCputm(),other.getCputm())
			.append(getCrnam(),other.getCrnam())
			.append(getMrnam(),other.getMrnam())
			.append(getZmuze(),other.getZmuze())
			.append(getMname(),other.getMname())
			.append(getFbdat(),other.getFbdat())
			.append(getFname(),other.getFname())
			.append(getFbuze(),other.getFbuze())
			.append(getPflag(),other.getPflag())
			.append(getAufnr(),other.getAufnr())
			.append(getOrderState(),other.getOrderState())
			.append(getSubmitUser(),other.getSubmitUser())
			.append(getSubmitDate(),other.getSubmitDate())
			.append(getMandt(),other.getMandt())
			.append(getBatchNo(),other.getBatchNo())
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getOperateType(),other.getOperateType())
			.append(getPsbh(),other.getPsbh())
			.append(getPoskey(),other.getPoskey())
			.append(getArbpl1(),other.getArbpl1())
			.isEquals();
	}
}
