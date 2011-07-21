/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.zg.plan.base.model.ZgTorderTemp;
import com.boco.zg.plan.base.service.ZgTorderTempBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderTempAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/modules/base/ZgTorderTemp/query_ZgTorderTemp.jsp";
	protected static final String LIST_JSP= "/modules/base/ZgTorderTemp/list_ZgTorderTemp.jsp";
	protected static final String CREATE_JSP = "/modules/base/ZgTorderTemp/create_ZgTorderTemp.jsp";
	protected static final String EDIT_JSP = "/modules/base/ZgTorderTemp/edit_ZgTorderTemp.jsp";
	protected static final String SHOW_JSP = "/modules/base/ZgTorderTemp/show_ZgTorderTemp.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/ZgTorderTemp/list.do";
	
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
	private String items;

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

	public void setItems(String items) {
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
//		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorderTemp.BM_CLASS_ID,super.getSessionUserId()));
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
	public void save() throws IOException {
		zgTorderTempBo.save(zgTorderTemp);
		promtAndCloseWindow("操作成功!");
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public void update() throws IOException  {
		zgTorderTempBo.update(this.zgTorderTemp);
		promtAndCloseWindow("操作成功!");
	}
	
	/**删除对象
	 * @throws IOException */
	public void delete() throws IOException {
		String params[]=items.split(",");
		for(int i = 0; i < params.length; i++) {
			zgTorderTempBo.removeById(params[i]);
		}
		rendHtml("parent.query();");
	}

}
