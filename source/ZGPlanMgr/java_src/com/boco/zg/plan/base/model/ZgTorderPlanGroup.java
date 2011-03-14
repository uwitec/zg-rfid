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


public class ZgTorderPlanGroup extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDER_PLAN_GROUP";
	public static final String BM_CLASS_ID = "ZG_T_ORDER_PLAN_GROUP";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "分组表ID";
	public static final String ALIAS_LABEL_CN = "名称";
	public static final String ALIAS_STATE = "领料状态";
	public static final String ALIAS_PERCENT = "领料进度";
	public static final String ALIAS_PLAN_TYPE = "类型";
	public static final String ALIAS_POSNR = "排序号";
	//date formats
	
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
	private java.lang.String state;
	private java.lang.Double percent;
	private java.lang.String planType;
	private java.lang.Long psbh;
	private java.lang.Long num;
	private String advance;//是滞提前领料
	private Long indexNo;//内部排序号
	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public Long getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Long indexNo) {
		this.indexNo = indexNo;
	}

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
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getPlanType() {
		return this.planType;
	}
	
	public void setPlanType(java.lang.String value) {
		this.planType = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("LabelCn",getLabelCn())
			.append("State",getState())
			.append("Percent",getPercent())
			.append("PlanType",getPlanType())
			.append("Posnr",getPsbh())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getLabelCn())
			.append(getState())
			.append(getPercent())
			.append(getPlanType())
			.append(getPsbh())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderPlanGroup == false) return false;
		if(this == obj) return true;
		ZgTorderPlanGroup other = (ZgTorderPlanGroup)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getLabelCn(),other.getLabelCn())
			.append(getState(),other.getState())
			.append(getPercent(),other.getPercent())
			.append(getPlanType(),other.getPlanType())
			.append(getPsbh(),other.getPsbh())
			.isEquals();
	}

	public java.lang.Long getNum() {
		return num;
	}

	public void setNum(java.lang.Long num) {
		this.num = num;
	}

	public java.lang.Long getPsbh() {
		return psbh;
	}

	public void setPsbh(java.lang.Long psbh) {
		this.psbh = psbh;
	}

	public java.lang.Double getPercent() {
		return percent;
	}

	public void setPercent(java.lang.Double percent) {
		this.percent = percent;
	}
}
