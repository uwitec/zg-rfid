package com.boco.frame.sys.service;

import java.util.List;

import com.boco.frame.sys.model.ContextMenu;

public interface IContextMenuBo {
	
	public List<ContextMenu> findContextmenu(String relatedBmClassId,String type);
}
