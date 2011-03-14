package com.boco.frame.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Component;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.dao.FwMenuExDao;
import com.boco.frame.sys.model.FwMenuEx;

@Component
@SuppressWarnings("unchecked")
public class FwMenuExBo implements IFwMenuExBo{
	
	protected static String NAV_MENU_TREE_NODE_LV1_STYLE = "nav_tree_node lv1";
	
	protected static String NAV_MENU_TREE_NODE_STYLE = "nav_tree_node";

	private FwMenuExDao fwMenuExDao;
	
	public void setFwMenuExDao(FwMenuExDao fwMenuExDao) {
		this.fwMenuExDao = fwMenuExDao;
	}

	public List<FwMenuEx> findMenuTree(){
		return fwMenuExDao.findMenuTree();
	}
	
	public List<FwMenuEx> findMenuTreeByOperatorId(String operatorId){
		return fwMenuExDao.findMenuTreeByOperatorId(operatorId);
	}
	
	public JSONArray findMenuTreeByOperatorInfo(OperatorInfo operatorInfo) {
		List<FwMenuEx> list = new ArrayList<FwMenuEx>();
		if(operatorInfo.getUserId().equals("sysadmin")) {
			list = findMenuTree();
		}else {
			list = findMenuTreeByOperatorId(operatorInfo.getOperatorId());
		}
		return buildTreeDate(list);
	}
	
	private JSONArray buildTreeDate(List<FwMenuEx> list) {
		JSONArray ja = new JSONArray();
		Map usedMap = new HashMap();
		for(FwMenuEx menuEx : list) {
			if(menuEx.getLevelNum() == 1l) {
				JSONObject jo = new JSONObject();
				String url = menuEx.getUrl();
				String text = "";
				if(!StringUtils.isBlank(url)) {
					text = "<a href='"+url+"' target='bodyFrame'>"+menuEx.getLabelCn()+"</a>";
				}else {
					text = menuEx.getLabelCn();
				}
				jo.put("id", menuEx.getCuid());
				jo.put("expanded", true);
				jo.put("text", text);
				jo.put("classes", NAV_MENU_TREE_NODE_LV1_STYLE);
				usedMap.put(menuEx.getCuid(), "used");
				jo = buildChildTreeDate(list,jo,usedMap);
				ja.add(jo);
			}
		}
		return ja;
	}
	
	private JSONObject buildChildTreeDate(List<FwMenuEx> list,JSONObject jo,Map usedMap) {
		for(FwMenuEx menuEx : list) {
			String used = (String)usedMap.get(menuEx.getCuid());
			if(used == null) {
				if(menuEx.getParentMenuId() != null)
				if(menuEx.getParentMenuId() != null && jo.get("id").equals(menuEx.getParentMenuId())) {
					JSONArray childList = (JSONArray)jo.get("children");
					JSONObject child = new JSONObject();
					String url = menuEx.getUrl();
					String text = "";
					if(!StringUtils.isBlank(url)) {
						text = "<a href='"+url+"' target='bodyFrame'>"+menuEx.getLabelCn()+"</a>";
					}else {
						text = menuEx.getLabelCn();
					}
					child.put("id", menuEx.getCuid());
					child.put("text", text);
					child.put("classes", NAV_MENU_TREE_NODE_STYLE);
					if(childList == null || childList.size() == 0) {
						childList = new JSONArray();
					}
					childList.add(buildChildTreeDate(list,child,usedMap));
					usedMap.put(menuEx.getCuid(), "used");
					jo.put("children", childList);
				}
			}
		}
		return jo;
	}

	/**
	 * 获取父菜单id
	 * @param parentMenuId
	 * @return
	 */
	public String getSeqById(String parentMenuId) {
		return fwMenuExDao.getSeqById(parentMenuId);
	}
}
