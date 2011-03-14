/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.action;

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

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class TmdPackagetypeRestypeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/meta/TmdPackagetypeRestype/query_TmdPackagetypeRestype.jsp";
	protected static final String LIST_JSP= "/frame/meta/TmdPackagetypeRestype/list_TmdPackagetypeRestype.jsp";
	protected static final String CREATE_JSP = "/frame/meta/TmdPackagetypeRestype/create_TmdPackagetypeRestype.jsp";
	protected static final String EDIT_JSP = "/frame/meta/TmdPackagetypeRestype/edit_TmdPackagetypeRestype.jsp";
	protected static final String SHOW_JSP = "/frame/meta/TmdPackagetypeRestype/show_TmdPackagetypeRestype.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/meta/TmdPackagetypeRestype/list.do";
	
	private TmdPackagetypeRestypeManager tmdPackagetypeRestypeManager;
	
	private TmdPackagetypeRestype tmdPackagetypeRestype;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			tmdPackagetypeRestype = new TmdPackagetypeRestype();
		} else {
			tmdPackagetypeRestype = (TmdPackagetypeRestype)tmdPackagetypeRestypeManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setTmdPackagetypeRestypeManager(TmdPackagetypeRestypeManager manager) {
		this.tmdPackagetypeRestypeManager = manager;
	}	
	
	public Object getModel() {
		return tmdPackagetypeRestype;
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
		
		Page page = tmdPackagetypeRestypeManager.findByPageRequest(pageRequest);
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
	
	/** 保存新增对象 */
	public String save() {
		tmdPackagetypeRestypeManager.save(tmdPackagetypeRestype);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		tmdPackagetypeRestypeManager.update(this.tmdPackagetypeRestype);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("cuid"));
			tmdPackagetypeRestypeManager.removeById(id);
		}
		return LIST_ACTION;
	}

}
