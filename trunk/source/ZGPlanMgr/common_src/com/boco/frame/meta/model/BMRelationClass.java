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
 *  @Date    Aug 14, 2009 5:21:18 PM
 *  @Version 3.0
 */

package com.boco.frame.meta.model;
/**
 * ��Դ��ϵ������
 * @Company BOCO
 * @Author  ������
 * @Date    Aug 14, 2009 5:28:08 PM
 * @Copyright Copyright (c) 2009
 * @Version 3.0
 */
public class BMRelationClass implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * ��ϵΨһCODE
	 */
	private String relationCode;
	/**
	 * ��ϵ�������
	 */
	private String relationName;
	/**
	 * ��ϵ���͡���1:1, 1:n, n:1, 3:1, 1:8
	 * ����ݿ��е���Լ��һ����Լ���}���ֶ�ƴ�϶��
	 */
	private String relationType;
	/**
	 * ������(������������)��� : �����͵�bmClassId
	 */

	private String leftBmClassId;
	/**
	 * ������(����j����)��� : �����͵�bmClassId
	 */
	private String rightBmClassId;
	/**
	 * ������labelCn
	 */
	private String leftTypeName;
	/**
	 * ������labelCn
	 */
	private String rightTypeName;
	/**
	 * �м�����ֶ����
	 */
	private String leftAttr;
	/**
	 * �м�����ֶ����
	 */
	private String rightAttr;
	/**
	 * �м�����
	 */
	private String centerTableName;
	/**
	 * ����
	 */
	private String desc;
	/**
	 * �Ƿ񱻰�
	 */
	private boolean include;
	
	public String getLeftBmClassId() {
		return leftBmClassId;
	}
	public void setLeftBmClassId(String leftBmClassId) {
		this.leftBmClassId = leftBmClassId;
	}
	public String getRightBmClassId() {
		return rightBmClassId;
	}
	public void setRightBmClassId(String rightBmClassId) {
		this.rightBmClassId = rightBmClassId;
	}
	public String getLeftAttr() {
		return leftAttr;
	}
	public void setLeftAttr(String leftAttr) {
		this.leftAttr = leftAttr;
	}
	public String getRightAttr() {
		return rightAttr;
	}
	public void setRightAttr(String rightAttr) {
		this.rightAttr = rightAttr;
	}
	public String getCenterTableName() {
		return centerTableName;
	}
	public void setCenterTableName(String centerTableName) {
		this.centerTableName = centerTableName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public boolean isInclude() {
		return include;
	}
	public void setInclude(boolean include) {
		this.include = include;
	}
	public String getLeftTypeName() {
		return leftTypeName;
	}
	public void setLeftTypeName(String leftTypeName) {
		this.leftTypeName = leftTypeName;
	}
	public String getRightTypeName() {
		return rightTypeName;
	}
	public void setRightTypeName(String rightTypeName) {
		this.rightTypeName = rightTypeName;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	
}
