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

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class TvmAttrAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/sys/TvmAttr/query_TvmAttr.jsp";
	protected static final String LIST_JSP= "/frame/sys/TvmAttr/list_TvmAttr.jsp";
	protected static final String CREATE_JSP = "/frame/sys/TvmAttr/create_TvmAttr.jsp";
	protected static final String EDIT_JSP = "/frame/sys/TvmAttr/edit_TvmAttr.jsp";
	protected static final String SHOW_JSP = "/frame/sys/TvmAttr/show_TvmAttr.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/TvmAttr/list.do";
	
	private TvmAttrBo tvmAttrBo;
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private TvmAttr tvmAttr;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			tvmAttr = new TvmAttr();
		} else {
			tvmAttr = (TvmAttr)tvmAttrBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setTvmAttrBo(TvmAttrBo bo) {
		this.tvmAttrBo = bo;
	}	
	
	public Object getModel() {
		return tvmAttr;
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
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(tvmAttr.BM_CLASS_ID,super.getSessionUserId()));
		Page page = tvmAttrBo.findByPageRequest(pageRequest);
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
		tvmAttrBo.save(tvmAttr);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		tvmAttrBo.update(this.tvmAttr);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			tvmAttrBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

}
