package com.boco.zg.bom.base.action;

import java.util.ArrayList;
import java.util.List;

import javacommon.base.BaseDwrAction;

import com.boco.zg.bom.base.service.ZgTbomManager;
import com.boco.zg.storage.service.ZgTstorageExBo;

import cn.org.rapid_framework.util.ApplicationContextHolder;


public class ZgTbomDwrAction extends BaseDwrAction{

	private ZgTbomManager getZgTbomManager() {
		return (ZgTbomManager)ApplicationContextHolder.getBean("zgTbomManager");
	}
	private ZgTstorageExBo getZgTstorageExBo(){
		return (ZgTstorageExBo)ApplicationContextHolder.getBean("zgTstorageExBo");
	}
	/**
	 * 批量领料bom出备料库库，更新已出库数量
	 */
	public String updateOutNumForBathchOut(String orderPlanbomId,String newOutNumStr ){
		if(newOutNumStr!=null||"".equals(newOutNumStr)){
			getZgTbomManager().updateOutNumForBathchOut(orderPlanbomId, Double.parseDouble(newOutNumStr));
			return "success";
		}else{
			return "failure";
		}
		
	}
	/**
	 * 半成品入库校验其所包含的bom是否够数量
	 */
	public String isEnoughForProductOut(String orderId,String idnrk,String inNum){
		List<String> resultListStr=new ArrayList<String>();
		resultListStr=getZgTstorageExBo().isEnoughBomForProductOut(orderId,idnrk,inNum);
		if(resultListStr.size()==0){
			return "OK";
		}else{
			return resultListStr.get(0).toString();
		}
	}
	
}
