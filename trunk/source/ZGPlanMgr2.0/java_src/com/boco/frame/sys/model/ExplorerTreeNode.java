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
 *  @Date    Aug 12, 2009 7:40:05 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源树节点
 * @Company BOCO
 * @Author  王晓雨
 * @Date    Aug 12, 2009 9:20:34 PM
 * @Copyright Copyright (c) 2009
 * @Version 3.0
 */
public class ExplorerTreeNode{
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 节点名称
	 */
	private String text;
	/**
	 * 节点类型
	 */
	private String nodeType;//District  or  Room  or  rootNode
	/**
	 * 图标样式
	 */
	private String iconCls;
	
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 用于右键菜单,例如:ITEM-DISTRICT  ,   "CATEGORY-"+bmClassId
	 */
	private String actionGroupId;
	/**
	 * CUID
	 */
	private String cuid;
	/**
	 * bmClassId
	 */
	private String bmClassId;
	/**
	 * 是否叶子节点
	 */
	private boolean leaf;
	/**
	 * 子节点集合
	 */
	private List<ExplorerTreeNode> childNodes;
	/**
	 * 父节点
	 */
	private ExplorerTreeNode parentNode;
	/**
	 * 节点对象值
	 */
	private Map nodeValue = new HashMap();
	/**
	 * 是否展开
	 */
	private boolean expandable;
	/**
	 * 是否模板树节点
	 */
	private String templateId;
	/**
	 * ’更多‘按钮存储本类资源查询模版的ID
	 */
	private String curTemplateId;
	/**
	 * 所属树
	 */
	private ExplorerTree tree;
	/**
	 * 显示更多节点的每页查询数量
	 */
	private int fetchSize;
	/**
	 * 节点总数
	 */
	private int countValue;
	/**
	 * 起始位置
	 */
	private int offset;
	/**
	 * 更多节点的查询条件
	 */
	private String sqlCond;
	/**
	 * 关联树保存原始节点
	 */
	private TreeParam param;
	/**
	 * 树节点扩展值
	 */
	private TreeNodeValue treeNodeValue;
	/**
	 * 关系树上配置外系统的页面展示节点，记录外系统的URL
	 */
	private String openUrl;
	/**
	 * 此节点的双击打开方式
	 */
	private String optFn;
	/**
	 * 是否出右键菜单
	 */
	private String hideContext;
	
	private String extValue;
	
	private String isChecked;
	
	/**
	 * 放入需要带入
	 */
	private Map extMap = new HashMap();
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getActionGroupId() {
		return actionGroupId;
	}
	public void setActionGroupId(String actionGroupId) {
		this.actionGroupId = actionGroupId;
	}
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
	public Map getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(Map nodeValue) {
		this.nodeValue = nodeValue;
	}
	public Object getAttrValue(String attrName) {
        return this.nodeValue.get(attrName);
    }
    public void setAttrValue(String attrName, Object attrValue) {
        this.nodeValue.put(attrName, attrValue);
    }
	public List<ExplorerTreeNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<ExplorerTreeNode> childNodes) {
		this.childNodes = childNodes;
	}
	public ExplorerTreeNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(ExplorerTreeNode parentNode) {
		this.parentNode = parentNode;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public ExplorerTree getTree() {
		return tree;
	}
	public void setTree(ExplorerTree tree) {
		this.tree = tree;
	}
	public int getFetchSize() {
		return fetchSize;
	}
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}
	public int getCountValue() {
		return countValue;
	}
	public void setCountValue(int countValue) {
		this.countValue = countValue;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSqlCond() {
		return sqlCond;
	}
	public void setSqlCond(String sqlCond) {
		this.sqlCond = sqlCond;
	}
	public TreeParam getParam() {
		return param;
	}
	public void setParam(TreeParam param) {
		this.param = param;
	}
	public TreeNodeValue getTreeNodeValue() {
		return treeNodeValue;
	}
	public void setTreeNodeValue(TreeNodeValue treeNodeValue) {
		this.treeNodeValue = treeNodeValue;
	}
	public String getExtValue() {
		return extValue;
	}
	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}
	public String getCurTemplateId() {
		return curTemplateId;
	}
	public void setCurTemplateId(String curTemplateId) {
		this.curTemplateId = curTemplateId;
	}
	public String getOpenUrl() {
		return openUrl;
	}
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	public String getOptFn() {
		return optFn;
	}
	public void setOptFn(String optFn) {
		this.optFn = optFn;
	}
	public String getHideContext() {
		return hideContext;
	}
	public void setHideContext(String hideContext) {
		this.hideContext = hideContext;
	}
	public Map getExtMap() {
		return extMap;
	}
	public void setExtMap(Map extMap) {
		this.extMap = extMap;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	
}
