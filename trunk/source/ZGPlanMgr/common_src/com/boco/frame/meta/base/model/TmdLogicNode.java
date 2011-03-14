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


public class TmdLogicNode extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdLogicNode";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_RELATED_NODE_CUID = "relatedNodeCuid";
	public static final String ALIAS_RELATED_LOGIC_CUID = "relatedLogicCuid";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String relatedNodeCuid;
	private java.lang.String relatedLogicCuid;
	//columns END


	public TmdLogicNode(){
	}

	public TmdLogicNode(
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


	
	
	public java.lang.String getRelatedNodeCuid() {
		return this.relatedNodeCuid;
	}
	
	public void setRelatedNodeCuid(java.lang.String value) {
		this.relatedNodeCuid = value;
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
			.append("RelatedNodeCuid",getRelatedNodeCuid())
			.append("RelatedLogicCuid",getRelatedLogicCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getRelatedNodeCuid())
			.append(getRelatedLogicCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdLogicNode == false) return false;
		if(this == obj) return true;
		TmdLogicNode other = (TmdLogicNode)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getRelatedNodeCuid(),other.getRelatedNodeCuid())
			.append(getRelatedLogicCuid(),other.getRelatedLogicCuid())
			.isEquals();
	}
}
