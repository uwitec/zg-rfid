/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTgroupOrderPlan extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "ZgTgroupOrderPlan";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_GROUP_ID = "groupId";
	public static final String ALIAS_ORDER_PLAN_ID = "orderPlanId";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String groupId_labelCn;
	private java.lang.String groupId;
	private java.lang.String orderPlanId_labelCn;
	private java.lang.String orderPlanId;
	//columns END


	public ZgTgroupOrderPlan(){
	}

	public ZgTgroupOrderPlan(
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


	
	
		public java.lang.String getGroupId_labelCn() {
			return this.groupId_labelCn;
		}
		
		public void setGroupId_labelCn(java.lang.String value) {
			this.groupId_labelCn = value;
		}
	public java.lang.String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(java.lang.String value) {
		this.groupId = value;
	}

	
		public java.lang.String getOrderPlanId_labelCn() {
			return this.orderPlanId_labelCn;
		}
		
		public void setOrderPlanId_labelCn(java.lang.String value) {
			this.orderPlanId_labelCn = value;
		}
	public java.lang.String getOrderPlanId() {
		return this.orderPlanId;
	}
	
	public void setOrderPlanId(java.lang.String value) {
		this.orderPlanId = value;
	}

	private ZgTorderPlan zgTorderPlan;
	
	public void setZgTorderPlan(ZgTorderPlan zgTorderPlan){
		this.zgTorderPlan = zgTorderPlan;
	}
	
	public ZgTorderPlan getZgTorderPlan() {
		return zgTorderPlan;
	}	
	private ZgTorderPlanGroup zgTorderPlanGroup;
	
	public void setZgTorderPlanGroup(ZgTorderPlanGroup zgTorderPlanGroup){
		this.zgTorderPlanGroup = zgTorderPlanGroup;
	}
	
	public ZgTorderPlanGroup getZgTorderPlanGroup() {
		return zgTorderPlanGroup;
	}	

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("GroupId",getGroupId())
			.append("OrderPlanId",getOrderPlanId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getGroupId())
			.append(getOrderPlanId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ZgTgroupOrderPlan == false) return false;
		if(this == obj) return true;
		ZgTgroupOrderPlan other = (ZgTgroupOrderPlan)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getGroupId(),other.getGroupId())
			.append(getOrderPlanId(),other.getOrderPlanId())
			.isEquals();
	}
}
