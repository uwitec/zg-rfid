package com.boco.zg.plan.base.model;

import java.util.Date;

import javacommon.base.BaseEntity;

public class ZgTorderPlanComment extends BaseEntity {

	String cuid;//主键
	Date createtime;//审核时间
	String userid;//审核人id
	String content;//审核意见
	String orderplanid;//单id
	
	String chname;//中文名
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrderplanid() {
		return orderplanid;
	}
	public void setOrderplanid(String orderplanid) {
		this.orderplanid = orderplanid;
	}
	public String getChname() {
		return chname;
	}
	public void setChname(String chname) {
		this.chname = chname;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
