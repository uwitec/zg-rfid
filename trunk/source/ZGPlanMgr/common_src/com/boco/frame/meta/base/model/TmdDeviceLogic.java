/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.model;

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

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class TmdDeviceLogic extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdDeviceLogic";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_DEVICE_CUID = "relatedDeviceCuid";
	public static final String ALIAS_RELATED_LOGIC_CUID = "relatedLogicCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedDeviceCuid;
	private java.lang.String relatedLogicCuid;
	//columns END


	public TmdDeviceLogic(){
	}

	public TmdDeviceLogic(
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


	
	
	public java.lang.String getRelatedDeviceCuid() {
		return this.relatedDeviceCuid;
	}
	
	public void setRelatedDeviceCuid(java.lang.String value) {
		this.relatedDeviceCuid = value;
	}

	
	public java.lang.String getRelatedLogicCuid() {
		return this.relatedLogicCuid;
	}
	
	public void setRelatedLogicCuid(java.lang.String value) {
		this.relatedLogicCuid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("RelatedDeviceCuid",getRelatedDeviceCuid())
			.append("RelatedLogicCuid",getRelatedLogicCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedDeviceCuid())
			.append(getRelatedLogicCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdDeviceLogic == false) return false;
		if(this == obj) return true;
		TmdDeviceLogic other = (TmdDeviceLogic)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedDeviceCuid(),other.getRelatedDeviceCuid())
			.append(getRelatedLogicCuid(),other.getRelatedLogicCuid())
			.isEquals();
	}
}
