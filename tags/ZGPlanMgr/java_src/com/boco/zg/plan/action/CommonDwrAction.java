package com.boco.zg.plan.action;

import java.util.List;

import javacommon.base.BaseDwrAction;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.base.model.FwMenu;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.service.FwMenuBo;
import com.boco.frame.sys.service.FwOperatorExBo;
import com.boco.frame.sys.service.FwRoleExBo;

public class CommonDwrAction extends BaseDwrAction {
	
	public FwRoleExBo getFwRoleExBo() {
		return (FwRoleExBo)ApplicationContextHolder.getBean("fwRoleExBo");
	}
	
	public FwMenuBo getFwMenuBo() {
		return (FwMenuBo)ApplicationContextHolder.getBean("fwMenuBo");
	}
	
	public FwOperatorExBo getFwOperatorExBo() {
		return (FwOperatorExBo)ApplicationContextHolder.getBean("fwOperatorExBo");
	}
	
	public List<FwOperator> getUsersByGroup(String roleId) {
		List<FwOperator> list = getFwRoleExBo().findUserByRoleId(roleId);
		return list;
	}
	
	public List<FwOperator> getUsersByOrgId(String orgId) {
		List<FwOperator> list = getFwOperatorExBo().findUserByOrgId(orgId);
		return list;
	}
	
	public String getCurrentPath(String id) {
		String result = "";
		FwMenu fwMenu = getFwMenuBo().getById(id);
		if(fwMenu!=null){
			String seq = fwMenu.getSeq();
			String[] seqList = StringUtils.split(seq,".");
			for(String menuId : seqList){
				FwMenu parentFwMenu = getFwMenuBo().getById(menuId);
				if(parentFwMenu!=null){
					result = result+parentFwMenu.getLabelCn()+",";
				}
			}
			if(result.indexOf(",")!=-1){
				result =  StringUtils.substringBeforeLast(result, ",");
			}
		}
		return result;
	}
}
