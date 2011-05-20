/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

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

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class TsysIfaceLogAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//frame/sys/TsysIfaceLog/query_TsysIfaceLog.jsp";
	protected static final String LIST_JSP= "//frame/sys/TsysIfaceLog/list_TsysIfaceLog.jsp";
	protected static final String CREATE_JSP = "//frame/sys/TsysIfaceLog/create_TsysIfaceLog.jsp";
	protected static final String EDIT_JSP = "//frame/sys/TsysIfaceLog/edit_TsysIfaceLog.jsp";
	protected static final String SHOW_JSP = "//frame/sys/TsysIfaceLog/show_TsysIfaceLog.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//frame/sys/TsysIfaceLog/list.do";
	
	private TsysIfaceLogBo tsysIfaceLogBo;
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	public Map<String, List<TmdEnumevalue>> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<String, List<TmdEnumevalue>> enumMap) {
		this.enumMap = enumMap;
	}
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private TsysIfaceLog tsysIfaceLog;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			tsysIfaceLog = new TsysIfaceLog();
		} else {
			tsysIfaceLog = (TsysIfaceLog)tsysIfaceLogBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setTsysIfaceLogBo(TsysIfaceLogBo bo) {
		this.tsysIfaceLogBo = bo;
	}	
	
	public Object getModel() {
		return tsysIfaceLog;
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
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(tsysIfaceLog.BM_CLASS_ID,super.getSessionUserId()));
		Page page = tsysIfaceLogBo.findByPageRequest(pageRequest);
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
		tsysIfaceLogBo.save(tsysIfaceLog);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		tsysIfaceLogBo.update(this.tsysIfaceLog);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			tsysIfaceLogBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

}
