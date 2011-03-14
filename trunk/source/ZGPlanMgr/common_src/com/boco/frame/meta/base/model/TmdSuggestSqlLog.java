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


public class TmdSuggestSqlLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdSuggestSqlLog";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_OPER_ID = "operId";
	public static final String ALIAS_SUG_SQL = "sugSql";
	public static final String ALIAS_STATUS = "status";
	
	//date formats
	

	//columns START
	private java.lang.String cuid;
	private java.lang.String operId;
	private java.lang.String sugSql;
	private java.lang.String status;
	//columns END


	public TmdSuggestSqlLog(){
	}

	public TmdSuggestSqlLog(
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


	
	
	public java.lang.String getOperId() {
		return this.operId;
	}
	
	public void setOperId(java.lang.String value) {
		this.operId = value;
	}

	
	public java.lang.String getSugSql() {
		return this.sugSql;
	}
	
	public void setSugSql(java.lang.String value) {
		this.sugSql = value;
	}

	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("OperId",getOperId())
			.append("SugSql",getSugSql())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getOperId())
			.append(getSugSql())
			.append(getStatus())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdSuggestSqlLog == false) return false;
		if(this == obj) return true;
		TmdSuggestSqlLog other = (TmdSuggestSqlLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getOperId(),other.getOperId())
			.append(getSugSql(),other.getSugSql())
			.append(getStatus(),other.getStatus())
			.isEquals();
	}
}
