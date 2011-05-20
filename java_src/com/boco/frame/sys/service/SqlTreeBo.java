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
 *  @Date    Aug 12, 2009 4:09:12 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boco.frame.sys.base.dao.TsysTreeTemplateDao;
import com.boco.frame.sys.base.model.TsysTreeTemplate;
import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.model.ExplorerTreeNode;
import com.boco.frame.sys.model.FwMenuEx;
import com.boco.frame.sys.model.TreeParam;

/**
 * SQL��BO
 * @Company BOCO
 * @Author  张颖慧
 * @Date    Aug 12, 2009 9:20:15 PM
 * @Copyright Copyright (c) 2009
 * @Version 3.0
 */
@Component
@SuppressWarnings("unchecked")
public class SqlTreeBo extends AbstractExplorerTreeBo{
	
	@Autowired
	private TsysTreeTemplateDao treeTemplateDAO;
	
	public SqlTreeBo(){
	}
	
	public ExplorerTreeNode getTreeRootJson(String userId,TreeParam param) {
		ExplorerTree tree = param.getTree();
		//系统参数定义如下
		String[] defaultParams = new String[2];
		defaultParams[0] = "广东";//当前系统所属区域
		defaultParams[1] = userId;
		ExplorerTreeNode node = new ExplorerTreeNode();
		node.setActionGroupId("ITEM-ROOT-"+tree.getTreeType());
		node.setText(tree.getTemplateName());
		node.setId(tree.getTemplateId()+"_ROOT");
		node.setTree(tree);
		String templateCuids = StringUtils.trimToNull(tree.getRelatedTreeTemplateCuid());
		String []templateCuidarray=null;
		String templateCuid="";
		templateCuidarray=templateCuids.split(";");
		List<ExplorerTreeNode> allNodeList=new ArrayList<ExplorerTreeNode>();
		for(int i=0;i<templateCuidarray.length;i++){
			templateCuid=templateCuidarray[i];
			TsysTreeTemplate template = (TsysTreeTemplate)this.treeTemplateDAO.getById(templateCuid);
			List<ExplorerTreeNode> nodeList = this.getNodesByTemplate(userId,tree, node, template, defaultParams,null);
			if(nodeList.size() > 0){
				ExplorerTreeNode t = (ExplorerTreeNode)nodeList.get(0);
				int countValue = t.getCountValue();
				int offSet = t.getOffset();
				int pageSize = 50;
				ExplorerTreeNode parentnode = t.getParentNode();
				if (nodeList.size() == pageSize && countValue - offSet * pageSize > 0) {
					ExplorerTreeNode moreNode = new ExplorerTreeNode();
					moreNode.setText("更多...");
					moreNode.setCuid("moreTreeNode");
					moreNode.setTree(t.getTree());
					moreNode.setCountValue(countValue);
					moreNode.setOffset(offSet+1);
					moreNode.setFetchSize(pageSize);
					moreNode.setTemplateId("rootTemplate");
					moreNode.setCurTemplateId(template.getCuid());
					moreNode.setParentNode(parentnode);
					nodeList.add(moreNode);
				}
			}
			allNodeList.addAll(nodeList);
		}
		node.setChildNodes(allNodeList);
		return node;
	}
	
	public List<ExplorerTreeNode> getTreeNodeJson(
			String userId, ExplorerTreeNode pNode) {
		Date startDate = new Date();
		String[] defaultParams = new String[2];
		defaultParams[0] = "广州";
		defaultParams[1] = userId;
		String templateCuid = StringUtils.trimToNull(pNode.getTemplateId());
		if (templateCuid == null) {
			throw new RuntimeException("节点中的模板ID不允许为空");
		}
		// 组装参数数组
		String[] params = new String[6];
		LinkedList<String> cuidList = this.getPnode(pNode);
		for (int i = 0; i < params.length; i++) {
			if (i < cuidList.size()) {
				params[i] = cuidList.get(i);
			}
		}
		List<TsysTreeTemplate> tempList=new ArrayList<TsysTreeTemplate>();
		if("rootTemplate".equals(templateCuid)){//如果是根节点级的‘更多’࡯
			TsysTreeTemplate template= (TsysTreeTemplate)this.treeTemplateDAO.getById(pNode.getCurTemplateId());
			tempList.add(template);
		}else{
			TsysTreeTemplate entity = new TsysTreeTemplate();
			entity.setSqlQueryString("t0_CUID in (select r.template_cuid2 from t_sys_tree_relation r where r.template_cuid1 = '"+templateCuid+"')");
			tempList = treeTemplateDAO.findByProperty(entity);
		}
		//处理节点过多情况，添加更多按钮，点击后再加载部分记录的实现
		List<ExplorerTreeNode> rtn = this.getPnodeTyTempList(userId, pNode, tempList,
				defaultParams, params);
		return rtn;
	}
	
	protected static String TYPE_TREE_NODE_STYLE = "type_tree_node";
	
	public JSONArray findResTreeJson(String userId,ExplorerTree tree) {
		JSONArray ja = new JSONArray();
		if(StringUtils.isBlank(tree.getRelatedTreeTemplateCuid())) return ja;
		TreeParam param = new TreeParam();
		param.setTree(tree);
		ExplorerTreeNode node = getTreeRootJson(userId,param);
		JSONObject jo = new JSONObject();
		jo.put("id", node.getTemplateId());
		jo.put("text", node.getText());
		jo.put("classes", TYPE_TREE_NODE_STYLE);
		node.setChildNodes(null);
		jo.put("tree", "'"+JSONObject.fromObject(node)+"'");
		ja.add(jo);
		return ja;
	}
	
	public JSONArray findTypeTreeJson(String userId) {
		List<ExplorerTree> list = new ArrayList<ExplorerTree>();
		try {
			list = getAllTreeList(userId);
		} catch (UserException e) {
			e.printStackTrace();
		}
		return buildTypeTreeDate(list);
	}
	
	private JSONArray buildTypeTreeDate(List<ExplorerTree> list) {
		JSONArray ja = new JSONArray();
		Map usedMap = new HashMap();
		for(ExplorerTree node : list) {
			String pId = node.getRelatedParentCuid();
			if(StringUtils.isBlank(pId)) {
				JSONObject jo = new JSONObject();
				String text = "";
				text = "<a href='#' onclick='javascript:return false'>"+node.getTemplateName()+"</a>";
				jo.put("id", node.getTemplateId());
				jo.put("expanded", true);
				jo.put("text", text);
				jo.put("classes", TYPE_TREE_NODE_STYLE);
				usedMap.put(node.getTemplateId(), "used");
				jo = buildChildTypeTreeDate(list,jo,usedMap);
				jo.put("tree","'"+JSONObject.fromObject(node).toString()+"'");
				ja.add(jo);
			}
		}
		return ja;
	}
	
	private JSONObject buildChildTypeTreeDate(List<ExplorerTree> list,JSONObject jo,Map usedMap) {
		for(ExplorerTree node : list) {
			String used = (String)usedMap.get(node.getTemplateId());
			if(used == null) {
				if(node.getRelatedParentCuid() != null) {
					if(jo.get("id").equals(node.getRelatedParentCuid())) {
						JSONArray childList = (JSONArray)jo.get("children");
						JSONObject child = new JSONObject();
						String text = "<a href='#' onclick='javascript:return false'>"+node.getTemplateName()+"</a>";
						child.put("id", node.getTemplateId());
						child.put("text", text);
						child.put("classes", TYPE_TREE_NODE_STYLE);
						child.put("tree","'"+JSONObject.fromObject(node).toString()+"'");
						if(childList == null || childList.size() == 0) {
							childList = new JSONArray();
						}
						childList.add(buildChildTypeTreeDate(list,child,usedMap));
						usedMap.put(node.getTemplateId(), "used");
						jo.put("children", childList);
					}
				}
			}
		}
		return jo;
	}
}
