package com.boco.frame.login.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OperatorInfo {

	private String operatorId;
	
	private String userId;
	
	private String orgId;
	
	private Date loginTime;
	
	private String ip;
	
	private String userName;
	
	private String roles;
	
	private int pageSizeValue;
	//private List<FwMenu> menus;
	
	//private List<FwRole> roles;


	public int getPageSizeValue() {
		return pageSizeValue;
	}

	public void setPageSizeValue(int pageSizeValue) {
		this.pageSizeValue = pageSizeValue;
	}

	public String getUserId() {
		return userId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOrgId() {
		return orgId;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
