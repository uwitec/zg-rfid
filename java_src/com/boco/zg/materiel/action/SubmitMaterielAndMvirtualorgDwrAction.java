package com.boco.zg.materiel.action;

import java.io.IOException;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.materiel.base.service.ZgMaterielBo;

import javacommon.base.BaseDwrAction;

public class SubmitMaterielAndMvirtualorgDwrAction extends BaseDwrAction {

	public ZgMaterielBo getZgMaterielBo() {
		return (ZgMaterielBo)ApplicationContextHolder.getBean("zgMaterielBo");
	}
	
	public String updateMaterielAndInsertZgMaterielVirtualorg(String materielCuid,
			String materielId,
			String storageId,
			String materielName,
			String materielStorageId,
			String materielVirtualorgId,
			String allVirtualorgId) throws IOException {
		
		ZgMateriel zgMateriel=new ZgMateriel();
		zgMateriel.setCuid(materielCuid);
		zgMateriel.setId(materielId);
		zgMateriel.setParentId(storageId);
		zgMateriel.setMaterielName(materielName);
		zgMateriel.setLgort(materielStorageId);
		
		getZgMaterielBo().updateMaterielAndInsertZgMaterielVirtualorg(zgMateriel,materielVirtualorgId,allVirtualorgId);
		
		return "操作成功!";
	}
}
