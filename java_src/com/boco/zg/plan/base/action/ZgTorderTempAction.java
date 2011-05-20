/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderTempAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//zg/plan/ZgTorderTemp/query_ZgTorderTemp.jsp";
	protected static final String LIST_JSP= "//zg/plan/ZgTorderTemp/list_ZgTorderTemp.jsp";
	protected static final String CREATE_JSP = "//zg/plan/ZgTorderTemp/create_ZgTorderTemp.jsp";
	protected static final String EDIT_JSP = "//zg/plan/ZgTorderTemp/edit_ZgTorderTemp.jsp";
	protected static final String SHOW_JSP = "//zg/plan/ZgTorderTemp/show_ZgTorderTemp.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//zg/plan/ZgTorderTemp/list.do";
	
	private ZgTorderTempBo zgTorderTempBo;
	
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
	
	private ZgTorderTemp zgTorderTemp;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderTemp = new ZgTorderTemp();
		} else {
			zgTorderTemp = (ZgTorderTemp)zgTorderTempBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderTempBo(ZgTorderTempBo bo) {
		this.zgTorderTempBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderTemp;
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
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorderTemp.BM_CLASS_ID,super.getSessionUserId()));
		if(pageRequest.getFilters().get("operateType")!=null && !pageRequest.getFilters().get("operateType").equals("")){
			String operateType = pageRequest.getFilters().get("operateType").toString();
			pageRequest.getFilters().put("operateType", "%"+operateType+"%");
		}
		Page page = zgTorderTempBo.findByPageRequest(pageRequest);
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
		zgTorderTempBo.save(zgTorderTemp);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTorderTempBo.update(this.zgTorderTemp);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderTempBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

}
