/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.action;

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

import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageStatAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/storage/ZgTstorageStat/query_ZgTstorageStat.jsp";
	protected static final String LIST_JSP= "/zg/storage/ZgTstorageStat/list_ZgTstorageStat.jsp";
	protected static final String CREATE_JSP = "/zg/storage/ZgTstorageStat/create_ZgTstorageStat.jsp";
	protected static final String EDIT_JSP = "/zg/storage/ZgTstorageStat/edit_ZgTstorageStat.jsp";
	protected static final String SHOW_JSP = "/zg/storage/ZgTstorageStat/show_ZgTstorageStat.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/storage/ZgTstorageStat/list.do";
	
	private ZgTstorageStatBo zgTstorageStatBo;
	
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
	
	private ZgTstorageStat zgTstorageStat;
	java.lang.String id = null;
	private String[] items;
	private String type;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTstorageStat = new ZgTstorageStat();
		} else {
			zgTstorageStat = (ZgTstorageStat)zgTstorageStatBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTstorageStatBo(ZgTstorageStatBo bo) {
		this.zgTstorageStatBo = bo;
	}	
	
	public Object getModel() {
		return zgTstorageStat;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 进入查询页面 */
	public String query() {
		CommonService.defultDateSet(getRequest(), "createDate_start", "createDate_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTstorageStat.BM_CLASS_ID,super.getSessionUserId()));
		//预装预焊分类
		Page page = zgTstorageStatBo.findByPageRequest(pageRequest);
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
		zgTstorageStatBo.save(zgTstorageStat);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTstorageStatBo.update(this.zgTstorageStat);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTstorageStatBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
