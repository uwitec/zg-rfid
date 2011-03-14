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


public class ZgTorderbomMoveLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZG_T_ORDERBOM_MOVE_LOG";
	public static final String BM_CLASS_ID = "ZG_T_ORDERBOM_MOVE_LOG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_SOURCE_ORDER_ID = "源订单";
	public static final String ALIAS_SOURCE_IDNRK = "源BOM";
	public static final String ALIAS_TARGET_ORDER_ID = "目标订单";
	public static final String ALIAS_MOVE_NUM = "移单数量";
	public static final String ALIAS_TARGET_IDNRK = "目标bom";
	public static final String CUID_ZG_T_ORDER_ZG_T_ORDER = "t0_0_1.t0_";
	public static final String SOURCE_ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_1_2.t0_";
	public static final String TARGET_ORDER_ID_ZG_T_ORDER_ZG_T_ORDER = "t0_2_3.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getSourceOrderId()==null?"":this.getSourceOrderId().toString();
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid_labelCn;
	private RelatedModel cuid_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String cuid;
	private java.lang.String sourceOrderId_labelCn;
	private RelatedModel sourceOrderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String sourceOrderId;
	private java.lang.String sourceIdnrk;
	private java.lang.String targetOrderId_labelCn;
	private RelatedModel targetOrderId_related = new RelatedModel("ZG_T_ORDER","CUID","LABEL_CN");
	private java.lang.String targetOrderId;
	private java.lang.Long moveNum;
	private java.lang.String targetIdnrk;
	//columns END
	public java.lang.String getCuid_labelCn() {
		return this.cuid_labelCn;
	}
	
	public void setCuid_labelCn(java.lang.String value) {
		this.cuid_labelCn = value;
	}
	
	public RelatedModel getCuid_related() {
		return this.cuid_related;
	}
	
	public void setCuid_related(RelatedModel value) {
		this.cuid_related = value;
	}
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getSourceOrderId_labelCn() {
		return this.sourceOrderId_labelCn;
	}
	
	public void setSourceOrderId_labelCn(java.lang.String value) {
		this.sourceOrderId_labelCn = value;
	}
	
	public RelatedModel getSourceOrderId_related() {
		return this.sourceOrderId_related;
	}
	
	public void setSourceOrderId_related(RelatedModel value) {
		this.sourceOrderId_related = value;
	}
	public java.lang.String getSourceOrderId() {
		return this.sourceOrderId;
	}
	
	public void setSourceOrderId(java.lang.String value) {
		this.sourceOrderId = value;
	}
	public java.lang.String getSourceIdnrk() {
		return this.sourceIdnrk;
	}
	
	public void setSourceIdnrk(java.lang.String value) {
		this.sourceIdnrk = value;
	}
	public java.lang.String getTargetOrderId_labelCn() {
		return this.targetOrderId_labelCn;
	}
	
	public void setTargetOrderId_labelCn(java.lang.String value) {
		this.targetOrderId_labelCn = value;
	}
	
	public RelatedModel getTargetOrderId_related() {
		return this.targetOrderId_related;
	}
	
	public void setTargetOrderId_related(RelatedModel value) {
		this.targetOrderId_related = value;
	}
	public java.lang.String getTargetOrderId() {
		return this.targetOrderId;
	}
	
	public void setTargetOrderId(java.lang.String value) {
		this.targetOrderId = value;
	}
	public java.lang.Long getMoveNum() {
		return this.moveNum;
	}
	
	public void setMoveNum(java.lang.Long value) {
		this.moveNum = value;
	}
	public java.lang.String getTargetIdnrk() {
		return this.targetIdnrk;
	}
	
	public void setTargetIdnrk(java.lang.String value) {
		this.targetIdnrk = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("SourceOrderId",getSourceOrderId())
			.append("SourceIdnrk",getSourceIdnrk())
			.append("TargetOrderId",getTargetOrderId())
			.append("MoveNum",getMoveNum())
			.append("TargetIdnrk",getTargetIdnrk())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getSourceOrderId())
			.append(getSourceIdnrk())
			.append(getTargetOrderId())
			.append(getMoveNum())
			.append(getTargetIdnrk())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTorderbomMoveLog == false) return false;
		if(this == obj) return true;
		ZgTorderbomMoveLog other = (ZgTorderbomMoveLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getSourceOrderId(),other.getSourceOrderId())
			.append(getSourceIdnrk(),other.getSourceIdnrk())
			.append(getTargetOrderId(),other.getTargetOrderId())
			.append(getMoveNum(),other.getMoveNum())
			.append(getTargetIdnrk(),other.getTargetIdnrk())
			.isEquals();
	}
}
