package com.boco.zg.virtualorg.base.model;

import javacommon.base.BaseEntity;
/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */

public class ZgTvirtualorgEmployee extends BaseEntity {

	private String cuid;//领料组领料人关系表 主键
	private String orgId;//领料组ID
	private String orgName;//领料组名
	private String userCuid;//领料员的CUID
	private String userId;//领料员ID
	private String userName;
	private String sex;
	private String email;
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserCuid() {
		return userCuid;
	}
	public void setUserCuid(String userCuid) {
		this.userCuid = userCuid;
	}
}
