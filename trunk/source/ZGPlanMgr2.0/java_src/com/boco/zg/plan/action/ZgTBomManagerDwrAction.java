package com.boco.zg.plan.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.service.ZgTBomManagerBo;
import com.boco.zg.plan.model.ZgTorderbomEx;

public class ZgTBomManagerDwrAction extends BaseDwrAction {
	
	private ZgTBomManagerBo geZgTBomManagerBo() {
		return (ZgTBomManagerBo) ApplicationContextHolder
				.getBean("zgTBomManagerBo");
	}
	/**
	 * 
	 * 更改session中的换料数量
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String saveChangOrderBom(HttpServletRequest request,String objcetJOSNs) {
		List<Map> sessionBomList=(List<Map>) request.getSession().getAttribute("bol");
		JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
		for(int i=0;i<josnArray.size();i++){
			JSONObject jsonObject = (JSONObject)josnArray.get(i);
			
			ZgTorderbomEx bom = (ZgTorderbomEx)JSONObject.toBean(jsonObject, ZgTorderbomEx.class);  
			System.out.println("原"+bom.getCuid()+"  "+bom.getBomType());
			for (Map temp : sessionBomList) {
				System.out.println("旧"+temp.get("CUID")+"  "+temp.get("BOM_TYPE"));
				if(bom.getCuid().equals(temp.get("CUID"))&&bom.getBomType().equals(temp.get("BOM_TYPE"))){
					if(bom.getWaitBackNum().equals(Long.parseLong(temp.get("WAIT_BACK_NUM").toString()))&&bom.getZbz().equals(IbatisDAOHelper.getStringValue(temp, "ZBZ"))){
					}else {
						temp.put("WAIT_BACK_NUM",bom.getWaitBackNum());
						temp.put("CAR_NUM",bom.getWaitBackNum());
						temp.put("isModity", true);
						temp.put("ZBZ", bom.getZbz());
						break;
					}
					
				}
			}
			
		}
		return "success";
	}
	
	/**
	 * 
	 * 检查退料数量
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String chckBackBomNum(HttpServletRequest request,String objcetJOSNs) {
		JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
		for(int i=0;i<josnArray.size();i++){
			JSONObject jsonObject = (JSONObject)josnArray.get(i);
			
			ZgTorderbomEx bom = (ZgTorderbomEx)JSONObject.toBean(jsonObject, ZgTorderbomEx.class);  
			if(!geZgTBomManagerBo().checkBackNum(bom)){
				return "BOM组件："+bom.getIdnrk()+" 退料数量大于待退料数量，请联系管理员，或刷新页面操作";
			}
			
		}
		return "success";
	}
	
	
	
}
