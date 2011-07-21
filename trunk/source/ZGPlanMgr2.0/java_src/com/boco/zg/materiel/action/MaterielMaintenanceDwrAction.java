package com.boco.zg.materiel.action;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.materiel.base.service.ZgMaterielBo;

import javacommon.base.BaseDwrAction;

public class MaterielMaintenanceDwrAction extends BaseDwrAction {

	public ZgMaterielBo getZgMaterielBo() {
		return (ZgMaterielBo)ApplicationContextHolder.getBean("zgMaterielBo");
	}
	
	/**
	 * 删除 物料组 的某行信息，用主键cuid
	 */
	public void deleteMaterielByCuid(String cuid){
		getZgMaterielBo().deleteMaterielByCuid(cuid);
	}
}
