/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ZgCarInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/bom/ZgCarInfo/query_ZgCarInfo.jsp";
	protected static final String LIST_JSP= "/zg/bom/ZgCarInfo/list_ZgCarInfo.jsp";
	protected static final String CREATE_JSP = "/zg/bom/ZgCarInfo/create_ZgCarInfo.jsp";
	protected static final String EDIT_JSP = "/zg/bom/ZgCarInfo/edit_ZgCarInfo.jsp";
	protected static final String SHOW_JSP = "/zg/bom/ZgCarInfo/show_ZgCarInfo.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/bom/ZgCarInfo/list.do";
	protected static final String QUERY_ACTION = "!/zg/bom/ZgCarInfo/query.do";
	

	private ZgCarInfoManager zgCarInfoManager;
	
	private ZgCarInfo zgCarInfo;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgCarInfo = new ZgCarInfo();
		} else {
			zgCarInfo = (ZgCarInfo)zgCarInfoManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgCarInfoManager(ZgCarInfoManager zgCarInfoManager) {
		this.zgCarInfoManager = zgCarInfoManager;
	}

	public Object getModel() {
		return zgCarInfo;
	}
	
	public void setCuid(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 进入查询页面 */
	public String query() {
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		String code = this.getRequest().getParameter("code");
		String labelCn=getRequest().getParameter("labelCn");
		if(!isNullOrEmptyString(labelCn)|| !isNullOrEmptyString(code)){
			pageRequest.getFilters().put("labelCn", labelCn);
			pageRequest.getFilters().put("code", code);
		}
		Page page = zgCarInfoManager.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		String cuid = this.getRequest().getParameter("id");
		zgCarInfo = zgCarInfoManager.getById(cuid);
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		zgCarInfoManager.save(zgCarInfo);
		return QUERY_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		String cuid = this.getRequest().getParameter("id");
		zgCarInfo = zgCarInfoManager.getById(cuid);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		String cuid = this.getRequest().getParameter("cuid");
		zgCarInfo.setCuid(cuid);
		zgCarInfoManager.update(zgCarInfo);
		return QUERY_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
	    Map map=pageRequest.getFilters();
	    
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgCarInfoManager.removeById((java.lang.String)params.get("id"));
		}
		return QUERY_ACTION;
	}

	public void setZgCarInfo(ZgCarInfo zgCarInfo) {
		this.zgCarInfo = zgCarInfo;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


}
