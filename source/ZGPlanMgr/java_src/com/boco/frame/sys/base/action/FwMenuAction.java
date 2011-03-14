/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.io.IOException;
import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;
import com.boco.frame.sys.service.FwMenuExBo;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwMenuAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/sys/FwMenu/query_FwMenu.jsp";
	protected static final String LIST_JSP= "/frame/sys/FwMenu/list_FwMenu.jsp";
	protected static final String CREATE_JSP = "/frame/sys/FwMenu/create_FwMenu.jsp";
	protected static final String EDIT_JSP = "/frame/sys/FwMenu/edit_FwMenu.jsp";
	protected static final String SHOW_JSP = "/frame/sys/FwMenu/show_FwMenu.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/FwMenu/list.do";
	protected static final String QUERY_ACTION = "!/frame/sys/FwMenu/query.do";
	
	private FwMenuBo fwMenuBo;
	
	private FwMenuExBo fwMenuExBo;
	
	private IVmModelBo vmModelBo;
	
	private FwOperatorLogBo fwOperatorLogBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private FwMenu fwMenu;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			fwMenu = new FwMenu();
		} else {
			fwMenu = (FwMenu)fwMenuBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setFwMenuBo(FwMenuBo bo) {
		this.fwMenuBo = bo;
	}	
	
	public Object getModel() {
		return fwMenu;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 进入查询页面 */
	public String query() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(fwMenu.BM_CLASS_ID,super.getSessionUserId()));
		Page page = fwMenuBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 
	 * @throws IOException */
	public void save() throws IOException {
		//获取你菜单seq
		String seq=fwMenuExBo.getSeqById(fwMenu.getParentMenuId());
		fwMenu.setSeq(seq);
		String cuid=(String) fwMenuBo.save(fwMenu);
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(cuid);
		fwOperatorLog.setTargetName(fwMenu.getLabelCn());
		fwOperatorLog.setAction("add");
		fwOperatorLog.setOperatorType("菜单管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		forwardQuery("操作成功");
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象
	 * @throws IOException */
	public void update() throws IOException {
		fwMenuBo.update(this.fwMenu);
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(fwMenu.getCuid());
		fwOperatorLog.setTargetName(fwMenu.getLabelCn());
		fwOperatorLog.setAction("update");
		fwOperatorLog.setOperatorType("菜单管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		forwardQuery("操作成功");
//		return QUERY_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			fwMenu=fwMenuBo.getById((java.lang.String)params.get("id"));
			fwMenuBo.removeById((java.lang.String)params.get("id"));
			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			fwOperatorLog.setCreateId(getSessionOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(fwMenu.getCuid());
			fwOperatorLog.setTargetName(fwMenu.getLabelCn());
			fwOperatorLog.setAction("delete");
			fwOperatorLog.setOperatorType("菜单管理");
			
			fwOperatorLogBo.save(fwOperatorLog);
		}
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(fwMenu.BM_CLASS_ID,super.getSessionUserId()));
		Page page = fwMenuBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}

	public void setFwMenuExBo(FwMenuExBo fwMenuExBo) {
		this.fwMenuExBo = fwMenuExBo;
	}

	public FwOperatorLogBo getFwOperatorLogBo() {
		return fwOperatorLogBo;
	}

	public void setFwOperatorLogBo(FwOperatorLogBo fwOperatorLogBo) {
		this.fwOperatorLogBo = fwOperatorLogBo;
	}

}
