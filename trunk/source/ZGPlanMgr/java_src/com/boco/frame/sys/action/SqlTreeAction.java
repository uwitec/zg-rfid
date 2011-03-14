package com.boco.frame.sys.action;

import java.io.PrintWriter;

import javacommon.base.BaseStruts2Action;
import net.sf.json.JSONObject;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.model.ExplorerTree;
import com.boco.frame.sys.service.SqlTreeBo;

public class SqlTreeAction extends BaseStruts2Action{

	private static final long serialVersionUID = 1L;
	
	private SqlTreeBo sqlTreeManager;

	public void setSqlTreeManager(SqlTreeBo sqlTreeManager) {
		this.sqlTreeManager = sqlTreeManager;
	}
	
	public String typeTree() {
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8"); 
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.print(sqlTreeManager.findTypeTreeJson(this.getSessionOperatorId()));
			out.flush();
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	public String resTree() {
		String param = this.getRequest().getParameter("param");
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8"); 
		try {
			PrintWriter out = this.getResponse().getWriter();
			JSONObject jo = JSONObject.fromObject(param);
			ExplorerTree tree = (ExplorerTree)JSONObject.toBean(jo, ExplorerTree.class);
			OperatorInfo operatorInfo = (OperatorInfo)this.getSession().getAttribute("operatorInfo");
			out.print(sqlTreeManager.findResTreeJson(operatorInfo.getOperatorId(),tree));
			out.flush();
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
}
