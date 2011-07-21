package com.boco.frame.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.dao.TsysContextmenuDao;
import com.boco.frame.sys.model.ContextMenu;

@Component
@SuppressWarnings("unchecked")
public class ContextMenuBo implements IContextMenuBo{
	
	private TsysContextmenuDao tsysContextmenuDao;
	
	public void setTsysContextmenuDao(TsysContextmenuDao dao) {
		this.tsysContextmenuDao = dao;
	}
	
	public List<ContextMenu> findContextmenu(String relatedBmClassId,String type) {
		//如果type为实例，则取空与实例，如果为类型，则取类型与空
		StringBuffer sb = new StringBuffer();
		sb.append(" select p.bm_classid ");
		sb.append("   from t_md_abstract_type p ");
		sb.append("  start with p.bm_classid = '"+relatedBmClassId+"' ");
		sb.append("connect by prior p.parent_type_cuid = p.cuid ");
		List<Map> classList = tsysContextmenuDao.findDynQuery(sb.toString());
		
		sb = new StringBuffer();
		sb.append("select * ");
		sb.append("  from t_sys_contextmenu t ");
		sb.append(" where t.related_bm_class_id in");
		sb.append("       (select p.bm_classid ");
		sb.append("          from t_md_abstract_type p ");
		sb.append("         start with p.bm_classid = '"+relatedBmClassId+"' ");
		sb.append("       connect by prior p.parent_type_cuid = p.cuid) ");
		sb.append(" and ( t.extend1='"+type+"' or t.extend1 is null ) ");
		sb.append("union all ");
		sb.append("select t.* ");
		sb.append("  from t_sys_contextmenu t, t_md_abstract_type a ");
		sb.append(" where t.related_resclass_cuid = a.related_resclass_cuid ");
		sb.append("   and a.bm_classid = '"+relatedBmClassId+"'");
		sb.append("  and ( t.extend1='"+type+"' or t.extend1 is null ) ");
		List<Map> list = tsysContextmenuDao.findDynQuery(sb.toString());
		List<ContextMenu> menus = buildContextMenu(classList,list);
		return menus;
	}
	
	private List<ContextMenu> buildContextMenu(List<Map> classList,List<Map> list) {
		List<ContextMenu> menus = new ArrayList<ContextMenu>();
		Map<String,Integer> keySort = new HashMap<String,Integer>();
		keySort.put("0", 1000);
		for(int i = 0; i < classList.size(); i++) {
			Map map = classList.get(i);
			keySort.put(IbatisDAOHelper.getStringValue(map, "BM_CLASSID"), i);
		}
		Map<String,Map> result = new HashMap<String,Map>();
		for(Map map : list) {
			String relatedBmClassId = IbatisDAOHelper.getStringValue(map, "RELATED_BM_CLASS_ID");
			String labelCn = IbatisDAOHelper.getStringValue(map, "LABEL_CN");
			if(relatedBmClassId == null) {
				relatedBmClassId = "0";
				map.put("RELATED_BM_CLASS_ID", relatedBmClassId);
			}
			Map old = result.get(labelCn);
			if(old == null){
				result.put(labelCn, map);
			}else {
				String oldRelatedBmClassId = IbatisDAOHelper.getStringValue(old, "RELATED_BM_CLASS_ID");
				int oldIndex = keySort.get(oldRelatedBmClassId);
				String newRelatedBmClassId = IbatisDAOHelper.getStringValue(map, "RELATED_BM_CLASS_ID");
				int newIndex = keySort.get(newRelatedBmClassId);
				if(oldIndex > newIndex) {
					result.put(labelCn, map);
				}
			}
		}
		Map<String,ContextMenu> groupMap = new HashMap<String,ContextMenu>();
		for(Map map:result.values()) {
			String groupName = IbatisDAOHelper.getStringValue(map, "GROUP_NAME"); 
			String iconPath = IbatisDAOHelper.getStringValue(map, "ICON_PATH");
			String targetId = IbatisDAOHelper.getStringValue(map, "TARGET_ID");
			String handler = IbatisDAOHelper.getStringValue(map, "HANDLER"); 
			if(!StringUtils.isBlank(IbatisDAOHelper.getStringValue(map, "GROUP_NAME"))) {
				ContextMenu group = groupMap.get(groupName);
				if(group == null) {
					group = new ContextMenu();
					group.setLabelCn(groupName);
					menus.add(group);
					groupMap.put(groupName, group);
				}
				ContextMenu menu = new ContextMenu();
				menu.setLabelCn(IbatisDAOHelper.getStringValue(map, "LABEL_CN"));
				menu.setNode(map);
				menu.setHandler(handler);
				menu.setIconPath(iconPath);
				menu.setTargetId(targetId);
				group.addChild(menu);
			}else {
				ContextMenu menu = new ContextMenu();
				menu.setLabelCn(IbatisDAOHelper.getStringValue(map, "LABEL_CN"));
				menu.setNode(map);
				menu.setHandler(handler);
				menu.setIconPath(iconPath);
				menu.setTargetId(targetId);
				menus.add(menu);
			}
		}
		return menus;
	}
}