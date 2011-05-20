package com.boco.zg.materiel.action;

import com.boco.zg.materiel.base.service.ZgMaterielBo;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import javacommon.base.BaseDwrAction;

public class CheckMaterielNameDwrAction extends BaseDwrAction {

	public ZgMaterielBo getZgMaterielBo() {
		return (ZgMaterielBo)ApplicationContextHolder.getBean("zgMaterielBo");
	}
	
	public String checkMaterielName(String materielName){
		return getZgMaterielBo().checkAllMaterielName(materielName).substring(1, 2);
	}
}
