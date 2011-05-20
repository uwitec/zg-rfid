/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2009 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *  @Author  王晓雨
 *  @Date    Aug 12, 2009 9:36:59 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.model;

import java.util.Map;

public class ExplorerTree{
	/**
	 * 树ID 即调用action名称,例如:ExplorerTreeAction.getTree
	 */
	private String templateId;
	
	/**
	 * 树类型 用于系统逻辑控制
	 */
	private String treeType;
	/**
	 * 树名称
	 */
	private String templateName;
	/**
	 * 树顺序
	 */
	private int sort = 0;
	/**
	 * 调用BO名称 BO必须实现IExplorerTreeBO接口
	 */
	private String boName;
	
	/**
	 * 顶级节点模板cuid；
	 */
	private String relatedTreeTemplateCuid;
	
	/**
	 * 上级节点cuid
	 */
	private String relatedParentCuid;
	
	/**
	 * 自定义树参数（应用）
	 */
	private String treeParam;
	
	private Map<String,String> treeParams;
	
	
	public String getRelatedTreeTemplateCuid() {
		return relatedTreeTemplateCuid;
	}
	public void setRelatedTreeTemplateCuid(String relatedTreeTemplateCuid) {
		this.relatedTreeTemplateCuid = relatedTreeTemplateCuid;
	}
	public String getTreeType() {
		return treeType;
	}
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getBoName() {
		return boName;
	}
	public void setBoName(String boName) {
		this.boName = boName;
	}
	public String getTreeParam() {
		return treeParam;
	}
	public void setTreeParam(String treeParam) {
		this.treeParam = treeParam;
	}
	public Map<String, String> getTreeParams() {
		return treeParams;
	}
	public void setTreeParams(Map<String, String> treeParams) {
		this.treeParams = treeParams;
	}
	public String getRelatedParentCuid() {
		return relatedParentCuid;
	}
	public void setRelatedParentCuid(String relatedParentCuid) {
		this.relatedParentCuid = relatedParentCuid;
	}
	
}
