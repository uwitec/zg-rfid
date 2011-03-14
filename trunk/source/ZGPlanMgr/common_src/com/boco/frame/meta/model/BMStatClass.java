/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2009 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *  @Author  ������
 *  @Date    Aug 12, 2009 10:10:20 PM
 *  @Version 3.0
 */

package com.boco.frame.meta.model;

import java.io.Serializable;
/**
 * ͳ�������
 * @Company BOCO
 * @Author  ������
 * @Date    Aug 12, 2009 10:12:19 PM
 * @Copyright Copyright (c) 2009
 * @Version 3.0
 */
public class BMStatClass implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * ��j���bmClassId
	 */
	private String bmClassId;
	/**
	 * ��j��ͱ����j��attrId
	 */
	private String attrId;
	/**
	 * ��j����
	 */
	private String cascadeType;
	/**
	 * ͳ������
	 */
	private String statType;
	/**
	 * ��ݹ��˷���
	 */
	private String filter;
	public String getBmClassId() {
		return bmClassId;
	}
	public void setBmClassId(String bmClassId) {
		this.bmClassId = bmClassId;
	}
	public String getAttrId() {
		return attrId;
	}
	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	public String getCascadeType() {
		return cascadeType;
	}
	public void setCascadeType(String cascadeType) {
		this.cascadeType = cascadeType;
	}
	public String getStatType() {
		return statType;
	}
	public void setStatType(String statType) {
		this.statType = statType;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
