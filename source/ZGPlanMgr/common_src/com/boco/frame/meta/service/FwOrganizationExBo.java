package com.boco.frame.meta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.dao.FwMenuExDao;
import com.boco.frame.sys.dao.FwOrganizationExDao;
import com.boco.frame.sys.model.FwMenuEx;
import com.boco.frame.sys.model.FwOrganizationEX;

@Component
@SuppressWarnings("unchecked")
public class FwOrganizationExBo extends FwOrganizationBo{
	
	protected static String NAV_MENU_TREE_NODE_LV1_STYLE = "nav_tree_node lv1";
	
	protected static String NAV_MENU_TREE_NODE_STYLE = "nav_tree_node";

	
	private FwOrganizationExDao fwOrganizationExDao;
	
	public void setFwOrganizationExDao(FwOrganizationExDao fwOrganizationExDao) {
		this.fwOrganizationExDao = fwOrganizationExDao;
	}
	

	
	
	public JSONArray findOrganizationTree() {
		List<FwOrganizationEX> list = new ArrayList<FwOrganizationEX>();
		
		list = fwOrganizationExDao.findOrganizationTree();
		
		return buildTreeDate(list);
	}
	
	private JSONArray buildTreeDate(List<FwOrganizationEX> list) {
		JSONArray ja = new JSONArray();
		Map usedMap = new HashMap();
		for(FwOrganizationEX organizationEx : list) {
			if(organizationEx.getLevelNum() == 1l) {
				JSONObject jo = new JSONObject();
				String url = "frame/sys/FwOrganization/query.do?parentOrgId="+organizationEx.getCuid();
				String text = "";
				if(!StringUtils.isBlank(url)) {
					text = "<a href='"+url+"' target='queryFrame'>"+organizationEx.getLabelCn()+"</a>";
				}else {
					text = organizationEx.getLabelCn();
				}
				jo.put("id", organizationEx.getCuid());
				jo.put("expanded", true);
				jo.put("text", text);
				jo.put("classes", NAV_MENU_TREE_NODE_LV1_STYLE);
				usedMap.put(organizationEx.getCuid(), "used");
				jo = buildChildTreeDate(list,jo,usedMap);
				ja.add(jo);
			}
		}
		return ja;
	}
	
	private JSONObject buildChildTreeDate(List<FwOrganizationEX> list,JSONObject jo,Map usedMap) {
		for(FwOrganizationEX organizationEx : list) {
			String used = (String)usedMap.get(organizationEx.getCuid());
			if(used == null) {
				if(organizationEx.getParentOrgId() != null)
				if(organizationEx.getParentOrgId() != null && jo.get("id").equals(organizationEx.getParentOrgId())) {
					JSONArray childList = (JSONArray)jo.get("children");
					JSONObject child = new JSONObject();
					String url = "frame/sys/FwOrganization/query.do?parentOrgId="+organizationEx.getCuid();
					String text = "";
					if(!StringUtils.isBlank(url)) {
						text = "<a href='"+url+"' target='queryFrame'>"+organizationEx.getLabelCn()+"</a>";
					}else {
						text = organizationEx.getLabelCn();
					}
					child.put("id", organizationEx.getCuid());
					child.put("text", text);
					child.put("classes", NAV_MENU_TREE_NODE_STYLE);
					if(childList == null || childList.size() == 0) {
						childList = new JSONArray();
					}
					childList.add(buildChildTreeDate(list,child,usedMap));
					usedMap.put(organizationEx.getCuid(), "used");
					jo.put("children", childList);
				}
			}
		}
		return jo;
	}

	public FwOrganization getById(String id) {
		return super.getById(id);
	}




	public Page findPrincipalsList(PageRequest pageRequest) {
		return fwOrganizationExDao.findPrincipalsList(pageRequest);
	}




	public void insertFwOrganizationManager(String organizationCuid,
			String oneEmployeeId) {
		fwOrganizationExDao.insertFwOrganizationManager(organizationCuid,oneEmployeeId);
		
	}




	public List<FwOrganizationManager> findEmployeesByOrgId(String id) {
		return fwOrganizationExDao.findEmployeesByOrgId(id);
	}




	public void deleteFwOrganizationManagerByOrgId(String cuid) {
		fwOrganizationExDao.deleteFwOrganizationManagerByOrgId(cuid);
		
	}



	/**
	 * 根据人员id获取其负责的部门列表
	 * @param cuid
	 * @return
	 */
	public List<FwOrganizationManager> getManagerListByUserId(String userId) {
		return fwOrganizationExDao.getManagerListByUserId(userId);
	}



	/**
	 * 根据员工id删除其负责的部门列表
	 * @param cuid
	 */
	public void removeManagerOrgByOperateId(String operatorId) {
		fwOrganizationExDao.removeManagerOrgByOperateId(operatorId);

	}



	/**
	 * 获取生产厂的目前的排序日期
	 * @param  plant
	 * @return
	 */
	public Date getPxdateByOrgId(String plant) {
		return fwOrganizationExDao.getPxdateByOrgId(plant);
	}

	/**
	 * 切换排序日期到第二天
	 * @param plant
	 */
	public int changePxDateNextDay(String plant) {
		return fwOrganizationExDao.changePxDateNextDay(plant);		
	}



	/**
	 * 重罪该生产组排序日期之后的订单分组的内部排序号，因为之前的提前领料内部排序号让改变，所以要重置
	 * @param plant
	 */
	public void revertIndexNoByPlant(String plant) {
		fwOrganizationExDao.revertIndexNoByPlant(plant);	
	}



	/**
	 * 获取生产厂列表
	 */
	public List<Map> getPlantList() {
		String sql="select t.*  from  T_PLANT_ENUMEVALUE t where t.state='0'";
		return  fwOrganizationExDao.findDynQuery(sql);	
		
	}
}
