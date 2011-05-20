/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2009 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 */

package com.boco.frame.sys.model;

public class TreeParam {
	/**
	 * 节点cuid
	 */
	private String cuid;
	/**
	 * 节点bmClassId
	 */
	private String bmClassId;
	/**
	 * 节点所属树
	 */
	private ExplorerTree tree;
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getBmClassId() {
		return bmClassId;
	}
	public void setBmClassId(String bmClassId) {
		this.bmClassId = bmClassId;
	}
	public ExplorerTree getTree() {
		return tree;
	}
	public void setTree(ExplorerTree tree) {
		this.tree = tree;
	}
	
}
