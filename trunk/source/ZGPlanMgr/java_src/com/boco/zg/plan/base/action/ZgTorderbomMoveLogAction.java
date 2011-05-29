/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.zg.plan.base.model.ZgTorderbomMoveLog;
import com.boco.zg.plan.base.service.ZgTorderbomMoveLogBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderbomMoveLogAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTorderbomMoveLog/query_ZgTorderbomMoveLog.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTorderbomMoveLog/list_ZgTorderbomMoveLog.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTorderbomMoveLog/create_ZgTorderbomMoveLog.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTorderbomMoveLog/edit_ZgTorderbomMoveLog.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTorderbomMoveLog/show_ZgTorderbomMoveLog.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderbomMoveLog/list.do";
	
	private ZgTorderbomMoveLogBo zgTorderbomMoveLogBo;
	
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
	
	private ZgTorderbomMoveLog zgTorderbomMoveLog;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderbomMoveLog = new ZgTorderbomMoveLog();
		} else {
			zgTorderbomMoveLog = (ZgTorderbomMoveLog)zgTorderbomMoveLogBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderbomMoveLogBo(ZgTorderbomMoveLogBo bo) {
		this.zgTorderbomMoveLogBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderbomMoveLog;
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
//		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorderbomMoveLog.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderbomMoveLogBo.findByPageRequest(pageRequest);
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
	public void save() throws IOException {
		zgTorderbomMoveLogBo.save(zgTorderbomMoveLog);
		forwardQuery("操作成功");
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public void update() throws IOException  {
		zgTorderbomMoveLogBo.update(this.zgTorderbomMoveLog);
		forwardQuery("操作成功");
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderbomMoveLogBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

}
