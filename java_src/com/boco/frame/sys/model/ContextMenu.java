package com.boco.frame.sys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContextMenu {

	private String labelCn;
	private List<ContextMenu> childs;
	private ContextMenu parent;
	private Map node;
	private String iconPath;
	private String handler;
	private String targetId;
	
	public void addChild(ContextMenu child) {
		if(child != null) { 
			if(childs == null) {
				childs = new ArrayList<ContextMenu>();
			}
			childs.add(child);
			child.setParent(this);
		}
	}
	public String getLabelCn() {
		return labelCn;
	}
	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}
	public List<ContextMenu> getChilds() {
		return childs;
	}
	public void setChilds(List<ContextMenu> childs) {
		this.childs = childs;
	}
	public ContextMenu getParent() {
		return parent;
	}
	public void setParent(ContextMenu parent) {
		this.parent = parent;
	}
	public Map getNode() {
		return node;
	}
	public void setNode(Map node) {
		this.node = node;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
