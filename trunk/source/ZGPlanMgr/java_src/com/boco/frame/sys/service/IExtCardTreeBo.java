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
 *  @Date    Aug 12, 2009 4:08:44 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.service;

import java.util.List;

import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.model.ExplorerTreeNode;
import com.boco.frame.sys.model.TreeParam;


public interface IExtCardTreeBo {
	/**
	 * ��ȡ��Դ������
	 * @Company BOCO
	 * @Author  ������
	 * @Date    Aug 12, 2009 9:23:00 PM
	 * @param actionContext
	 * @return
	 * @throws Exception
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<ExplorerTree> getAllTreeList(String userId) throws Exception;
	/**
	 * ��ȡ��Դ���ڵ�
	 * @Company BOCO
	 * @Author  ������
	 * @Date    Oct 26, 2009 11:05:01 AM
	 * @param actionContext
	 * @param param
	 * @return
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public ExplorerTreeNode getTreeRootJson(String userId,TreeParam param);
	/**
	 * ����ϼ��ڵ��ȡ�¼��ڵ�
	 * @Company BOCO
	 * @Author  ������
	 * @Date    Oct 26, 2009 11:05:18 AM
	 * @param actionContext
	 * @param pNode
	 * @return
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<ExplorerTreeNode> getTreeNodeJson(String userId , ExplorerTreeNode pNode);
}
