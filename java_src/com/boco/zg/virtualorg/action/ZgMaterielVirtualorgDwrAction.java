package com.boco.zg.virtualorg.action;

import java.util.List;

import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;
import com.boco.zg.virtualorg.base.service.ZgTvirtualorgBo;

import cn.org.rapid_framework.util.ApplicationContextHolder;


import javacommon.base.BaseDwrAction;

public class ZgMaterielVirtualorgDwrAction extends BaseDwrAction {

	public ZgTvirtualorgBo getZgTvirtualorgBo() {
		return (ZgTvirtualorgBo)ApplicationContextHolder.getBean("zgTvirtualorgBo");
	}
	
	/**
	 * 用虚拟组的ID来查询出它里面的物料组
	 * @param OrgId 虚拟组ID
	 * @return
	 */
	public String getAllMatklsByOrgId(String OrgId){
		List<ZgMaterrielVirtualorg> zgMaterrielVirtualorgList=getZgTvirtualorgBo().getListById(OrgId);
		StringBuffer allMatkls=new StringBuffer();
		for(int i=0;i<zgMaterrielVirtualorgList.size();i++){
			ZgMaterrielVirtualorg zgMaterrielVirtualorg=zgMaterrielVirtualorgList.get(i);
			if(i!=0)
				allMatkls.append(",");
			allMatkls.append(zgMaterrielVirtualorg.getMatkl());
		}
		return allMatkls.toString();
	}
}
