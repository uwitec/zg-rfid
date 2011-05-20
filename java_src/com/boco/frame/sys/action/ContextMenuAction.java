package com.boco.frame.sys.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.model.ContextMenu;
import com.boco.frame.sys.service.IContextMenuBo;

public class ContextMenuAction extends BaseDwrAction {
	
	public IContextMenuBo getContextMenuBo() {
		return (IContextMenuBo)ApplicationContextHolder.getBean("contextMenuBo");
	}
	
	public List<ContextMenu> findTsysContextmenu(Map paramMap) {
		List<ContextMenu> list = new ArrayList<ContextMenu>();
		String relatedBmClassId = (String)paramMap.get("bmClassId");
		String type = (String)paramMap.get("type");
		if(type==null){
			type="实例";
		}else if("type".equalsIgnoreCase("type")){
			type="类型";
		}else if("type".equalsIgnoreCase("menu")){
			return list;
		}
		list = this.getContextMenuBo().findContextmenu(relatedBmClassId,type);
		return list;
	}
}