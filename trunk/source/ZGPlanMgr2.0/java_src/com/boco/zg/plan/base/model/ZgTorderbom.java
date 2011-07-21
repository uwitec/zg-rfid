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


public class ZgTorderbom extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDERBOM";
	public static final String BM_CLASS_ID = "ZG_T_ORDERBOM";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "订单BOMID";
	public static final String ALIAS_LABEL_CN = "名称属性";
	public static final String ALIAS_IDNRK = "BOM组件";
	public static final String ALIAS_ORDER_ID = "订单ID";
	public static final String ALIAS_AUFNR = "订单编号";
	public static final String ALIAS_ARBPL = "生成线";
	public static final String ALIAS_MATNR = "物料号";
	public static final String ALIAS_MATNR1 = "物料号1";
	public static final String ALIAS_MAKTX1 = "物料描述1";
	public static final String ALIAS_MAKTX2 = "物料描述2";
	public static final String ALIAS_MSEHL1 = "度量单位文本1(最多30个字符）";
	public static final String ALIAS_MSEHL2 = "度量单位文本2(最多30个字符）";
	public static final String ALIAS_ZDTYL = "组件单位用量";
	public static final String ALIAS_MENGE = "组件需求用量";
	public static final String ALIAS_MATKL = "物料组";
	public static final String ALIAS_SORTF = "排序字符串";
	public static final String ALIAS_SORTF_H = "原排序字符串";
	public static final String ALIAS_LGORT = "库存地点";
	public static final String ALIAS_ZBZ = "物料备注";
	public static final String ALIAS_ZRZQD = "认真清单";
	public static final String ALIAS_BOM_ID = "BOM表ID";
	public static final String ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
	//date formats
	
	// 增加BOM排序号
	public static final String ALIAS_POSNR="BOM排序号";
	
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
	private java.lang.String cuid;
	private java.lang.String labelCn;
	private java.lang.String idnrk;
	private java.lang.String orderId_labelCn;
	private RelatedModel orderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String orderId;
	private java.lang.String aufnr;
	private java.lang.String arbpl;
	private java.lang.String matnr;
	private java.lang.String matnr1;
	private java.lang.String maktx1;
	private java.lang.String maktx2;
	private java.lang.String msehl1;
	private java.lang.String msehl2;
	private java.lang.Long zdtyl;
	private java.lang.Long menge;
	private java.lang.String matkl;
	private java.lang.String sortf;
	private java.lang.String sortfH;
	private java.lang.String lgort;
	private java.lang.String zbz;
	private java.lang.String zrzqd;
	private Long storageNum;
	private String storageState;
	private java.lang.String posnr;
	private String lgort_label_cn;//库存的中文名
	private Date planDate;
	private String planStartTime;
	private String planEndTime;
	private String carNum;
	private String matklSelf;
	private Long remainNum;// 备料库存数量
	private Long moveNum;
	private Long planNum;
	private Long backNum;
	private Long outNum;
	private Long completeNum;
	private Long semifinStoreNum;//半成品库存数量
	
	private Long bomNum;
	private String taskBomId;
	//columns END
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
	public java.lang.String getIdnrk() {
		return this.idnrk;
	}
	
	public void setIdnrk(java.lang.String value) {
		this.idnrk = value;
	}
	public java.lang.String getOrderId_labelCn() {
		return this.orderId_labelCn;
	}
	
	public void setOrderId_labelCn(java.lang.String value) {
		this.orderId_labelCn = value;
	}
	
	public RelatedModel getOrderId_related() {
		return this.orderId_related;
	}
	
	public void setOrderId_related(RelatedModel value) {
		this.orderId_related = value;
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
	public java.lang.String getMatnr1() {
		return this.matnr1;
	}
	
	public void setMatnr1(java.lang.String value) {
		this.matnr1 = value;
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
	public java.lang.Long getZdtyl() {
		return this.zdtyl;
	}
	
	public void setZdtyl(java.lang.Long value) {
		this.zdtyl = value;
	}
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
	public java.lang.String getSortfH() {
		return this.sortfH;
	}
	
	public void setSortfH(java.lang.String value) {
		this.sortfH = value;
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

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("Idnrk",getIdnrk())
			.append("OrderId",getOrderId())
			.append("Aufnr",getAufnr())
			.append("Arbpl",getArbpl())
			.append("Matnr",getMatnr())
			.append("Matnr1",getMatnr1())
			.append("Maktx1",getMaktx1())
			.append("Maktx2",getMaktx2())
			.append("Msehl1",getMsehl1())
			.append("Msehl2",getMsehl2())
			.append("Zdtyl",getZdtyl())
			.append("Menge",getMenge())
			.append("Matkl",getMatkl())
			.append("Sortf",getSortf())
			.append("SortfH",getSortfH())
			.append("Lgort",getLgort())
			.append("Zbz",getZbz())
			.append("Zrzqd",getZrzqd())
			.toString();
	}
	
	public static void main(String[] args) {
		ZgTorderbom t =new ZgTorderbom();
		t.setCuid("123");
		t.setLabelCn("name");
		System.out.println(t.toString());
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getIdnrk())
			.append(getOrderId())
			.append(getAufnr())
			.append(getArbpl())
			.append(getMatnr())
			.append(getMatnr1())
			.append(getMaktx1())
			.append(getMaktx2())
			.append(getMsehl1())
			.append(getMsehl2())
			.append(getZdtyl())
			.append(getMenge())
			.append(getMatkl())
			.append(getSortf())
			.append(getSortfH())
			.append(getLgort())
			.append(getZbz())
			.append(getZrzqd())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderbom == false) return false;
		if(this == obj) return true;
		ZgTorderbom other = (ZgTorderbom)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getIdnrk(),other.getIdnrk())
			.append(getOrderId(),other.getOrderId())
			.append(getAufnr(),other.getAufnr())
			.append(getArbpl(),other.getArbpl())
			.append(getMatnr(),other.getMatnr())
			.append(getMatnr1(),other.getMatnr1())
			.append(getMaktx1(),other.getMaktx1())
			.append(getMaktx2(),other.getMaktx2())
			.append(getMsehl1(),other.getMsehl1())
			.append(getMsehl2(),other.getMsehl2())
			.append(getZdtyl(),other.getZdtyl())
			.append(getMenge(),other.getMenge())
			.append(getMatkl(),other.getMatkl())
			.append(getSortf(),other.getSortf())
			.append(getSortfH(),other.getSortfH())
			.append(getLgort(),other.getLgort())
			.append(getZbz(),other.getZbz())
			.append(getZrzqd(),other.getZrzqd())
			.isEquals();
	}



	public String getStorageState() {
		return storageState;
	}

	public void setStorageState(String storageState) {
		this.storageState = storageState;
	}

	public Long getStorageNum() {
		return storageNum;
	}

	public void setStorageNum(Long storageNum) {
		this.storageNum = storageNum;
	}

	public String getLgort_label_cn() {
		return lgort_label_cn;
	}

	public void setLgort_label_cn(String lgort_label_cn) {
		this.lgort_label_cn = lgort_label_cn;
	}

	public java.lang.String getPosnr() {
		return posnr;
	}

	public void setPosnr(java.lang.String posnr) {
		this.posnr = posnr;
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

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}


	public Long getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(Long moveNum) {
		this.moveNum = moveNum;
	}

	public Long getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Long planNum) {
		this.planNum = planNum;
	}
	
	public Long getBackNum() {
		return backNum;
	}

	public void setBackNum(Long backNum) {
		this.backNum = backNum;
	}

	public Long getOutNum() {
		return outNum;
	}

	public void setOutNum(Long outNum) {
		this.outNum = outNum;
	}

	public Long getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(Long completeNum) {
		this.completeNum = completeNum;
	}

	public Long getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Long remainNum) {
		this.remainNum = remainNum;
	}

	public Long getSemifinStoreNum() {
		return semifinStoreNum;
	}

	public void setSemifinStoreNum(Long semifinStoreNum) {
		this.semifinStoreNum = semifinStoreNum;
	}

	public Long getBomNum() {
		return bomNum;
	}

	public void setBomNum(Long bomNum) {
		this.bomNum = bomNum;
	}

	public String getMatklSelf() {
		return matklSelf;
	}

	public void setMatklSelf(String matklSelf) {
		this.matklSelf = matklSelf;
	}

	public String getTaskBomId() {
		return taskBomId;
	}

	public void setTaskBomId(String taskBomId) {
		this.taskBomId = taskBomId;
	}

	
}
