package com.boco.frame.login.action;

import java.io.PrintWriter;

import javacommon.base.BaseStruts2Action;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.service.IFwMenuExBo;

public class FrameAction extends BaseStruts2Action {
	
	private static final long serialVersionUID = 1L;
	
	private IFwMenuExBo fwMenuExBo;

	public void setFwMenuExBo(IFwMenuExBo fwMenuExBo) {
		this.fwMenuExBo = fwMenuExBo;
	}

	@SuppressWarnings("unchecked")
	public String navTree() {
		String root = this.getRequest().getParameter("root");
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8"); 
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.print(fwMenuExBo.findMenuTreeByOperatorInfo((OperatorInfo)this.getRequest().getSession().getAttribute("operatorInfo")).toString());
			out.flush();
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
}
