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
 *  @Date    Aug 22, 2009 4:14:53 PM
 *  @Version 3.0
 */

package com.boco.frame.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.util.PageRequestExt;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.org.rapid_framework.page.Page;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.dao.TsysTreeTypeDao;
import com.boco.frame.sys.base.model.TsysTreeTemplate;
import com.boco.frame.sys.base.model.TsysTreeType;
import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.model.ExplorerTreeNode;
import com.boco.frame.sys.model.TreeParam;



public abstract class AbstractExplorerTreeBo implements IExtCardTreeBo{
	
	@Autowired
	public TsysTreeTypeDao tsysTreeTypeDao;
	
	/**
	 * 获取资源树类型
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Aug 12, 2009 9:22:13 PM
	 * @param actionContext
	 * @return
	 * @throws Exception
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	@SuppressWarnings("unchecked")
	public List<ExplorerTree> getAllTreeList(String userId) throws UserException{
        List<ExplorerTree> ret = new ArrayList<ExplorerTree>();
        TsysTreeType query = new TsysTreeType();
      //利用TREE_PARAM参数控制树是在浏览视图显示还是在维护视图显示(1:维护视图,2:浏览视图,  1,2: 两个视图都显示)
        query.setSqlQueryString(" t0_BO_NAME IS NOT NULL AND (t0_TREE_PARAM IS NULL OR t0_TREE_PARAM LIKE '%1%')");
        List<TsysTreeType> treeTypes = tsysTreeTypeDao.findByProperty(query);
        for(TsysTreeType type:treeTypes){
        	ExplorerTree tree = new ExplorerTree();
        	tree.setBoName(type.getBoName());
        	tree.setTemplateId(type.getCuid());
        	tree.setTemplateName(type.getLabelCn());
        	tree.setTreeType(type.getTreeType());
        	tree.setRelatedTreeTemplateCuid(type.getRelatedTemplateCuid());
        	tree.setRelatedParentCuid(type.getRelatedParentCuid());
        	ret.add(tree);
        }
        return ret;
	}
	@SuppressWarnings("unchecked")
	public abstract ExplorerTreeNode getTreeRootJson(String userId,TreeParam param);
	@SuppressWarnings("unchecked")
	public abstract List<ExplorerTreeNode> getTreeNodeJson(String userId, ExplorerTreeNode pNode);
	
	
	@SuppressWarnings("unchecked")
	protected List<ExplorerTreeNode> getNodesByTemplate(String userId,ExplorerTree tree,ExplorerTreeNode pNode,TsysTreeTemplate template,String[] defalutParams,String[] params){
		List<ExplorerTreeNode> nodeList = new ArrayList<ExplorerTreeNode>();
		String sql=template.getSqlTemplate();
		//TODO 过滤重复字段...
		String[] composeColumn = null;
		boolean filter = false;
		//....	
		Long paramsConf[] = new Long[6];
		paramsConf[0]=template.getParam1();
		paramsConf[1]=template.getParam2();
		paramsConf[2]=template.getParam3();
		paramsConf[3]=template.getParam4();
		paramsConf[4]=template.getParam5();
		paramsConf[5]=template.getParam6();
		for(Long param:paramsConf){
			if(params==null||params.length==0){
				continue;
			}
			if(param==null||param.intValue()<=0l){
				continue;
			}
			int i = param.intValue();
			if(i<=params.length){
				int index = StringUtils.indexOf(sql, "n%");
				String before = StringUtils.substring(sql, 0, index)+params[i-1];
				String end = StringUtils.substring(sql, index+2, sql.length());
				sql = before+end;
			}
		}
		sql = StringUtils.replace(sql, "d%",defalutParams[0]);
		sql = StringUtils.replace(sql, "u%",defalutParams[1]);
		sql = StringUtils.replace(sql, "p$",StringUtils.trimToNull(pNode.getExtValue())==null?"":pNode.getExtValue());		
		if(tree.getTreeParams()!=null) {
			for(String key : tree.getTreeParams().keySet()) {
				sql = StringUtils.replace(sql, key+"#",tree.getTreeParams().get(key));
			}
		}
		int pageNum = pNode.getOffset();
		//获取每页条数
		int pageSize = 100;
		PageRequestExt pageRequest = new PageRequestExt();
		pageRequest.setPageNumber(1);
		pageRequest.setPageSize(pageSize);
		Page result = this.tsysTreeTypeDao.pageDynQuery(sql, pageRequest);

		int totalnum = result.getTotalCount();
		
		List<Map> mapList = result.getResult();
		Set<String> childCountKey = new HashSet<String>();//定义统计下级数量列
		Set<String> filterSet = new HashSet<String>();
		for(int i = 0 ; i < mapList.size(); i++){
			Map dbo = mapList.get(i);
			//TODO 过滤重复字段...
			if(filter){
				String columnValue = "";
				for(String column : composeColumn){
					columnValue += dbo.get(column.toUpperCase().trim());
				}
				if(filterSet.contains(columnValue))
					continue; 
				else
					filterSet.add(columnValue);
			}
			//...
			String districtCuid=String.valueOf(dbo.get("RELATED_DISTRICT_CUID"));
			if(i==0){
				Set<String> keySet = dbo.keySet();
				for(String key:keySet){
					if(key.startsWith("CHILDCOUNT")){
						childCountKey.add(key);
					}
				}
			}

			//判断是否叶子节点
			boolean isLeaf = true;
			if(childCountKey.size()==0){
				isLeaf = false;
			}
			
			int countValue = 0;//记录孩子总数
			for(String key:childCountKey){
				String value = dbo.get(key).toString().trim();
				if(!"0".equals(value)){//只要有一个的孩子数量大于0，则当前节点非叶子节点
					isLeaf = false;
					countValue += Integer.parseInt(value);
				}
			}
			ExplorerTreeNode childNode = new ExplorerTreeNode();
			childNode.setLeaf(isLeaf);
			childNode.setCountValue(totalnum);//记录此类节点总数，为更多按钮使用
			childNode.setOffset(1);//默认是第一页
			childNode.setFetchSize(pageSize);
			childNode.setExpandable(!childNode.isLeaf());
			//隐藏右键
			if(IbatisDAOHelper.getStringValue(dbo, "HIDE_CONTEXT") != null){
				childNode.setHideContext(IbatisDAOHelper.getStringValue(dbo, "HIDE_CONTEXT"));
			}
			//树节点类型
			if(IbatisDAOHelper.getStringValue(dbo, "ACTION_GROUP_ID") != null){
				childNode.setActionGroupId(IbatisDAOHelper.getStringValue(dbo, "ACTION_GROUP_ID"));
			}
			//重新构造树节点类型
			String classId = IbatisDAOHelper.getStringValue(dbo, "BM_CLASS_ID");
			if(StringUtils.trimToNull(classId) == null){
				childNode.setActionGroupId("I");
			}else{
				if(IbatisDAOHelper.getStringValue(dbo, "ITEM_TYPE") != null){
					//判断如果是设置双击打开列表的SQL，必定是类型的节点而不是实例的节点，将类型节点字体变粗
					if("type".equalsIgnoreCase(IbatisDAOHelper.getStringValue(dbo, "ITEM_TYPE"))){
						childNode.setActionGroupId("ITEM-TYPE-"+classId.trim());
					}else if("menu".equalsIgnoreCase(IbatisDAOHelper.getStringValue(dbo, "ITEM_TYPE"))){
						childNode.setActionGroupId("ITEM-MENU-"+classId.trim());
						//menu没有右键菜单
						childNode.setHideContext("true");
					}else if("cramer".equalsIgnoreCase(IbatisDAOHelper.getStringValue(dbo, "ITEM_TYPE"))){
						childNode.setActionGroupId("ITEM-CRAMER-"+classId.trim());
					}
				}else{
					childNode.setActionGroupId("ITEM-"+classId.trim());//默认是实例
				}
			}
			if(IbatisDAOHelper.getStringValue(dbo, "BM_CLASS_ID") != null)
				childNode.setBmClassId(IbatisDAOHelper.getStringValue(dbo, "BM_CLASS_ID").trim());
			
			childNode.setCuid(IbatisDAOHelper.getStringValue(dbo, "CUID"));
			childNode.setText(IbatisDAOHelper.getStringValue(dbo,"LABEL_CN"));
			childNode.setExtValue(IbatisDAOHelper.getStringValue(dbo,"EXT_VALUE"));
			childNode.setIsChecked(IbatisDAOHelper.getStringValue(dbo,"IS_CHECKED"));

			childNode.setTree(tree);
			childNode.setParentNode(pNode);
			if(template.getCuid()!=null){
				childNode.setTemplateId(template.getCuid());
			}else{
				childNode.setTemplateId(IbatisDAOHelper.getStringValue(dbo, "CUID"));
			}
			
			//为浏览树图可自定义双击触发函数而添加的代码-----
//			if(IbatisDAOHelper.getStringValue(dbo, "OPT_FN") != null){
//				childNode.setOptFn(IbatisDAOHelper.getStringValue(dbo, "OPT_FN"));
//				
//				//判断如果是设置双击打开列表的SQL，必定是类型的节点而不是实例的节点，将类型节点字体变粗
//				if("showAllRes".equals(IbatisDAOHelper.getStringValue(dbo, "OPT_FN"))){
//					childNode.setText("<b>"+childNode.getText()+"</b>");
//				}
//			}
			
			if(IbatisDAOHelper.getStringValue(dbo, "FILTER_STR") != null){
				String filterStr = IbatisDAOHelper.getStringValue(dbo, "FILTER_STR");
				String[] arr = filterStr.split(" ");
				if(arr.length == 3 && "!=<>inlike".indexOf(arr[1]) != -1){
					//----未添加太多判断，暂只支持=字符串的情况
					childNode.getExtMap().put("filterstr", arr[0]+arr[1]+"'"+arr[2]+"'");
				}
			}
			childNode.setNodeValue(dbo);
			//------end------
			nodeList.add(childNode);
		}
		return nodeList;
	}
	/**
	 * 递归得到所有的上级节点CUID
	 * @param node
	 * @return
	 */
	protected LinkedList<String> getPnode(ExplorerTreeNode node){
		LinkedList<String> cuidList = new LinkedList<String>();
		cuidList.add(node.getCuid());
		if(node.getParentNode()!=null){
			cuidList.addAll(this.getPnode(node.getParentNode()));
		}
		return cuidList;
	}
	
	/**
	 * 根据查询出来的模版SQL的LIST，查询出带‘更多’节点的树列表。
	 * @author gigi
	 * @param actionContext
	 * @param pNode
	 * @param tempList
	 * @param defaultParams
	 * @param params
	 * @return
	 */
	protected List<ExplorerTreeNode> getPnodeTyTempList(
			String userId, ExplorerTreeNode pNode,
			List<TsysTreeTemplate> tempList, String[] defaultParams,
			String[] params) {

		// 需要返回的结果集
		List<ExplorerTreeNode> rtn = new ArrayList<ExplorerTreeNode>();

		for (TsysTreeTemplate template : tempList) {
			//如果是点击树上的’更多‘节点进来查询的，则只查询本节点所属模版类的SQL
			if("moreTreeNode".equals(pNode.getCuid())){
				if(!template.getCuid().equals(pNode.getCurTemplateId())){
					continue;
				}else{
					//把父节点的CUID赋给’更多‘按钮，为下面的查询拼SQL使用
					if(params != null && params.length>0){
						for(int g=0;g<params.length;g++){
							if("moreTreeNode".equals(params[g]))
								params[g] = pNode.getParentNode().getCuid();
						}
					}
				}
			}
			
			//查询节点
			List<ExplorerTreeNode> lis = this.getNodesByTemplate(userId,
					pNode.getTree(), pNode, template, defaultParams, params);
			if(lis.size() == 0) continue;
			
			ExplorerTreeNode t = (ExplorerTreeNode)lis.get(0);
			
			int countValue = t.getCountValue();//获得本类节点的总数
			int offSet = t.getOffset();//获得本类节点的页数
			int pageSize = t.getFetchSize();//获得本类节点的每页条数
			ExplorerTreeNode parentnode = t.getParentNode();
			
			if("moreTreeNode".equals(pNode.getCuid())){
				offSet = pNode.getOffset();
				parentnode = pNode.getParentNode();
			}
			
			// 需要添加更多按钮
			if (lis.size() == pageSize && countValue - offSet * pageSize > 0) {
				ExplorerTreeNode moreNode = new ExplorerTreeNode();
				moreNode.setText("更多...");
				moreNode.setCuid("moreTreeNode");
				moreNode.setTree(t.getTree());
				moreNode.setCountValue(countValue);
				moreNode.setOffset(offSet+1);
				moreNode.setFetchSize(pageSize);
				moreNode.setTemplateId(pNode.getTemplateId());//上级模版的CUID
				moreNode.setCurTemplateId(template.getCuid());
				moreNode.setParentNode(parentnode);
				
				lis.add(moreNode);
			}

			rtn.addAll(lis);
		}
		return rtn;
	}
}
