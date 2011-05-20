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
 *  @Date    Aug 12, 2009 4:09:30 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.model.ExplorerTreeNode;
import com.boco.frame.sys.model.TreeParam;
import com.boco.frame.sys.service.IExtCardTreeBo;
/**
 * 资源浏览器树Action
 * @Company BOCO
 * @Author  王晓雨
 * @Date    Aug 12, 2009 9:20:42 PM
 * @Copyright Copyright (c) 2009
 * @Version 3.0
 */
public class ExplorerTreeAction extends BaseDwrAction{
	/**
	 * 获取所有树
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Aug 12, 2009 9:21:01 PM
	 * @param request
	 * @return
	 * @throws Exception 
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public Collection<ExplorerTree> getAllTreeList(HttpServletRequest request,String boName) throws Exception {
        Collection<ExplorerTree> treeList = getExplorerTreeBO(boName==null?"sqlTreeBo":boName).getAllTreeList(this.getSessionOperatorId(request));
        return treeList;
    }
	/**
	 * 获取资源浏览树根节点
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Oct 14, 2009 8:46:39 PM
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public Map<String, Object> getTreeRootJson(HttpServletRequest request,TreeParam param){
		ExplorerTree tree = param.getTree();
		Map<String, Object> treeJson = new HashMap<String, Object>();
		treeJson.put("root", getExplorerTreeBO(tree.getBoName()).getTreeRootJson(this.getSessionOperatorId(request),param));
        return treeJson;
	}
	/**
	 * 获取下级节点
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Oct 14, 2009 8:47:05 PM
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<ExplorerTreeNode> getTreeNodeJson(HttpServletRequest request,ExplorerTreeNode pNode){
		return getExplorerTreeBO(pNode.getTree().getBoName()).getTreeNodeJson(getSessionOperatorId(request), pNode);
	}
	
	protected IExtCardTreeBo getExplorerTreeBO(String boName) {
		return (IExtCardTreeBo)ApplicationContextHolder.getBean(boName);
    }
	
}
