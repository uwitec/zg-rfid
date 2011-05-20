package com.boco.zg.plan.action;

import java.util.ArrayList;
import java.util.List;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.plan.base.model.ZgTbackBom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTbackBomBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;

public class ZgTbackbomDwrAction extends BaseDwrAction {
	
	private ZgTbackBomBo getZgTbackBomBo() {
		return (ZgTbackBomBo)ApplicationContextHolder.getBean("zgTbackBomBo");
	}
	
	/**
	 * 修改退单bom作废数量
	 * @param objcetJOSNs
	 * @return
	 */
	public boolean updateZgTbackbomCancelNumber(String objcetJOSNs){
		boolean flag = true;
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTbackBom zgTbackBom = (ZgTbackBom)JSONObject.toBean(jsonObject, ZgTbackBom.class); 
				
				ZgTbackBom entity = getZgTbackBomBo().getById(zgTbackBom.getCuid());
//				entity.setBackNumber(entity.getBackNumber()+entity.getCancelNumber()-zgTbackBom.getCancelNumber());
//				entity.setCancelNumber(zgTbackBom.getCancelNumber());
				getZgTbackBomBo().update(entity);
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
