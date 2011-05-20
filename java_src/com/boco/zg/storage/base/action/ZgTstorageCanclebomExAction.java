/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;

/**
 * @author wengq
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageCanclebomExAction extends BaseStruts2Action implements Preparable,ModelDriven{
	
	private IVmModelBo vmModelBo;
	
	private ZgTstorageCanclebomEx zgTstorageCanclebomEx;
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	java.lang.String id = null;

	public void prepare() throws Exception {
	}
	
	
	
	public Object getModel() {
		return zgTstorageCanclebomEx;
	}
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}
	
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}
	
	/**
	 * 入库冲单 保存冲单的半成品
	 * 先把该半成品保存在session中
	 * @throws IOException */
	@SuppressWarnings("unchecked")
	public void saveBom() throws IOException {
		List<ZgTstorageCanclebomEx> list=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		
		String cuid=zgTorderPlanbomExBo.getCUID();
		
		//判断sesson中的bom列表中是否存在当前需要添加的bom组件，如果存在,则删除session中的该bom组件
		for (int i = 0; i < list.size(); i++) {
			ZgTstorageCanclebomEx obj=list.get(i);
			if(obj.getOrderBomId().equals(zgTstorageCanclebomEx.getOrderBomId())){
				list.remove(i);
				break;
			}
		}
		
		zgTstorageCanclebomEx.setCuid(cuid);
		zgTstorageCanclebomEx.setIsModity(true);
		list.add(zgTstorageCanclebomEx);
		this.getSession().setAttribute("bomECancleList",list);
		
		setSessionBomIds(list);
		
		returnMsgAndCloseWindow("操作成功");
	}



	
	/**
	 * 库存管理 保存出库或入库半成品
	 * 先把该半成品保存在session中
	 * @throws IOException */
	@SuppressWarnings("unchecked")
	public void updateBom() throws IOException {
		List<ZgTstorageCanclebomEx> list=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomEList");
		
		
		//判断sesson中的bom列表中是否存在当前需要添加的bom组件，如果存在,则删除session中的该bom组件
		for (int i = 0; i < list.size(); i++) {
			ZgTstorageCanclebomEx obj=list.get(i);
			if(obj.getCuid().equals(zgTstorageCanclebomEx.getCuid())){
				list.remove(i);
				break;
			}
		}
		
		
		zgTstorageCanclebomEx.setIsModity(true);
		list.add(zgTstorageCanclebomEx);
		this.getSession().setAttribute("bomEList",list);
		
		setSessionBomIds(list);
		
		returnMsgAndCloseWindow("操作成功");
	}
	

	/**
	 * 保存bomIds到session中，用于页面展示可选bom的时候过滤
	 * @param list
	 */
	private void setSessionBomIds(List<ZgTstorageCanclebomEx> list) {
		
		String bomIds="";
		for (ZgTstorageCanclebomEx obj : list) {
			if(!obj.getIsDel()){
				bomIds=bomIds+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomIds", bomIds);
	}


	




}
