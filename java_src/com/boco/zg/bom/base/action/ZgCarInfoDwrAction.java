package com.boco.zg.bom.base.action;

import java.util.List;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.bom.base.model.ZgCarInfo;
import com.boco.zg.bom.base.service.ZgCarInfoManager;

import javacommon.base.BaseDwrAction;




@SuppressWarnings("unchecked")
public class ZgCarInfoDwrAction extends BaseDwrAction {

	//添加SET方法，自动获取ZgCarInfoManager
	private ZgCarInfoManager getZgCarInfoManager() {
		return (ZgCarInfoManager) ApplicationContextHolder
				.getBean("zgCarInfoManager");
	}

	public boolean CheckTheOne(String code){
		ZgCarInfo zgCarInfo = new ZgCarInfo();
		zgCarInfo.setCode(code);
		List<ZgCarInfo> list = getZgCarInfoManager().findByProperty(zgCarInfo);
		boolean result = true;
		if (list.size()!= 0 ){
			result = false;
		}
		return result;
	}
	
	public boolean CheckEdit(String code,String cuid){
		ZgCarInfo zgCarInfo = new ZgCarInfo();
		zgCarInfo.setCode(code);
		List<ZgCarInfo> list = getZgCarInfoManager().findByProperty(zgCarInfo);
		boolean result = true;
		if(list.size()<=1){
			for (ZgCarInfo zgCarInfo2 : list) {
				String temp =zgCarInfo2.getCuid();
				if(!temp.equals(cuid)){
					result = false;
				}
			}
		}else{
			result = false;
		}
		return result;
	}
}