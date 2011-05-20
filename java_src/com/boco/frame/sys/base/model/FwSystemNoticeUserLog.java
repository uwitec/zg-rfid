/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.model;

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


public class FwSystemNoticeUserLog extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "FW_SYSTEM_NOTICE_USER_LOG";
	public static final String BM_CLASS_ID = "FW_SYSTEM_NOTICE_USER_LOG";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_USER_ID = "USER_ID";
	public static final String ALIAS_NOTICE_ID = "NOTICE_ID";
	public static final String NOTICE_ID_FW_SYSTEM_NOTICE_FW_SYSTEM_NOTICE = "t0_0_1.t0_";
	//date formats
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String cuid;
	private java.lang.String userId;
	private java.lang.String noticeId_labelCn;
	private RelatedModel noticeId_related = new RelatedModel("FW_SYSTEM_NOTICE","CUID","LABEL_CN");
	private java.lang.String noticeId;
	//columns END
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getNoticeId_labelCn() {
		return this.noticeId_labelCn;
	}
	
	public void setNoticeId_labelCn(java.lang.String value) {
		this.noticeId_labelCn = value;
	}
	
	public RelatedModel getNoticeId_related() {
		return this.noticeId_related;
	}
	
	public void setNoticeId_related(RelatedModel value) {
		this.noticeId_related = value;
	}
	public java.lang.String getNoticeId() {
		return this.noticeId;
	}
	
	public void setNoticeId(java.lang.String value) {
		this.noticeId = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Cuid",getCuid())
			.append("UserId",getUserId())
			.append("NoticeId",getNoticeId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCuid())
			.append(getUserId())
			.append(getNoticeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwSystemNoticeUserLog == false) return false;
		if(this == obj) return true;
		FwSystemNoticeUserLog other = (FwSystemNoticeUserLog)obj;
		return new EqualsBuilder()
			.append(getCuid(),other.getCuid())
			.append(getUserId(),other.getUserId())
			.append(getNoticeId(),other.getNoticeId())
			.isEquals();
	}
}
