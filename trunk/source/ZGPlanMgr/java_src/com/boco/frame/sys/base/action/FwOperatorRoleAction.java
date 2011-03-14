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


public class FwOperatorRoleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/sys/FwOperatorRole/query_FwOperatorRole.jsp";
	protected static final String LIST_JSP= "/frame/sys/FwOperatorRole/list_FwOperatorRole.jsp";
	protected static final String CREATE_JSP = "/frame/sys/FwOperatorRole/create_FwOperatorRole.jsp";
	protected static final String EDIT_JSP = "/frame/sys/FwOperatorRole/edit_FwOperatorRole.jsp";
	protected static final String SHOW_JSP = "/frame/sys/FwOperatorRole/show_FwOperatorRole.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/FwOperatorRole/list.do";
	
	private FwOperatorRoleBo fwOperatorRoleBo;
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private FwOperatorRole fwOperatorRole;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			fwOperatorRole = new FwOperatorRole();
		} else {
			fwOperatorRole = (FwOperatorRole)fwOperatorRoleBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setFwOperatorRoleBo(FwOperatorRoleBo bo) {
		this.fwOperatorRoleBo = bo;
	}	
	
	public Object getModel() {
		return fwOperatorRole;
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
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(fwOperatorRole.BM_CLASS_ID,super.getSessionUserId()));
		Page page = fwOperatorRoleBo.findByPageRequest(pageRequest);
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
		fwOperatorRoleBo.save(fwOperatorRole);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		fwOperatorRoleBo.update(this.fwOperatorRole);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			fwOperatorRoleBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

}
