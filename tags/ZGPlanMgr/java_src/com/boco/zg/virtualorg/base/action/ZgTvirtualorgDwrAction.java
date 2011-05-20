package com.boco.zg.virtualorg.base.action;

import com.boco.zg.virtualorg.base.model.ZgTvirtualorg;
import com.boco.zg.virtualorg.base.service.ZgTvirtualorgBo;

import cn.org.rapid_framework.util.ApplicationContextHolder;
import javacommon.base.BaseDwrAction;

public class ZgTvirtualorgDwrAction extends BaseDwrAction{
	
	private ZgTvirtualorgBo getZgTvirtualorgBo(){
		return (ZgTvirtualorgBo)ApplicationContextHolder.getBean("zgTvirtualorgBo");
	}
	public boolean findZgTvirtualorgData(String cuid){
		return  getZgTvirtualorgBo().findZgTvirtualorgData(cuid);
	}
	public void delVirtualorg(String orgId){
		getZgTvirtualorgBo().removeVirtualById(orgId);
	}
	public String saveVirtualorgAndReturnCuid(String labelCn,String orgId,String note,String type){
		ZgTvirtualorg entity=new ZgTvirtualorg();
		entity.setLabelCn(labelCn);
		entity.setOrgId(orgId);
		entity.setNote(note);
		entity.setType(type);
		String cuid=getZgTvirtualorgBo().saveVirtualorgAndReturnCuid(entity);
		return cuid;
	}
	/**
	 * 找到同一机构下的所有labelcn，用来添加编辑验证是否同名
	 * @param orgId
	 * @param labelCn
	 * @return
	 */
	public boolean validLabelCnForAdd(String orgId,String labelCn){
		return getZgTvirtualorgBo().validLabelCnForAdd(orgId,labelCn);
	}
	
	public boolean validLabelCnForUpdate(String cuid,String orgId,String labelCn){
		return getZgTvirtualorgBo().validLabelCnForUpdate(cuid,orgId,labelCn);
	}
	
}
