/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwMenuBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.service.FwOrganizationExBo;
import com.boco.zg.bom.base.model.ZgTbom;
import com.boco.zg.bom.base.service.ZgTbomManager;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ZgTbomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/bom/ZgTbom/query_ZgTbom.jsp";
	protected static final String LIST_JSP= "/zg/bom/ZgTbom/list_ZgTbom.jsp";
	protected static final String QUERY_JSP1 = "/zg/bom/ZgTbom/query_ZgTbom1.jsp";
	protected static final String LIST_JSP1= "/zg/bom/ZgTbom/list_ZgTbom1.jsp";
	protected static final String QUERY_JSP2 = "/zg/bom/ZgTbom/query_ZgTbom2.jsp";
	protected static final String LIST_JSP2= "/zg/bom/ZgTbom/list_ZgTbom2.jsp";
	protected static final String CREATE_JSP = "/zg/bom/ZgTbom/create_ZgTbom.jsp";
	protected static final String EDIT_JSP = "/zg/bom/ZgTbom/edit_ZgTbom.jsp";
	protected static final String SHOW_JSP = "/zg/bom/ZgTbom/show_ZgTbom.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/bom/ZgTbom/list.do";
	
	protected static final String QUERY_FROSELECT_JSP = "/zg/bom/ZgTbomForSelect/query_ZgTbom.jsp";
	protected static final String LIST__FROSELECTJSP= "/zg/bom/ZgTbomForSelect/list_ZgTbom.jsp";


	
	private ZgTbomManager zgTbomManager;
	
	private FwMenuBo fwMenuBo;
	
	private ZgTbom zgTbom;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTbom = new ZgTbom();
		} else {
			zgTbom = (ZgTbom)zgTbomManager.getById(id);
		}
	}
	
	private FwOrganizationBo fwOrganizationBo;
	
	private FwOrganizationExBo fwOrganizationExBo;
	
	public List<Map> getPlantList(){
		return fwOrganizationExBo.getPlantList();
	}
	
	public List<FwOrganization> getArbplList() {
		FwOrganization fwOrganization=new FwOrganization();
		fwOrganization.setType("3");
		return fwOrganizationBo.findByProperty(fwOrganization);
	}

	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTbomManager(ZgTbomManager manager) {
		this.zgTbomManager = manager;
	}	
	
	public Object getModel() {
		return zgTbom;
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
		
		Page page = zgTbomManager.findByPageRequest(pageRequest);
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
		zgTbomManager.save(zgTbom);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTbomManager.update(this.zgTbom);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("cuid"));
			zgTbomManager.removeById(id);
		}
		return LIST_ACTION;
	}
	
	/** 查询bom组件页面供选择bom组件用 */
	public String queryBomForSelect() {
		String lgort=this.getRequest().getParameter("lgort");
		String orderPlanId=this.getRequest().getParameter("orderPlanId");
		
		this.getRequest().setAttribute("lgort", lgort);
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		return QUERY_FROSELECT_JSP;
	}
	
	/** 批量领料计划查询bom组件供选择 */
	public String listBomForSelect() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		
		Page page = zgTbomManager.findBomForSelectByPageRequest(pageRequest);
		Map<String, Object> map=pageRequest.getFilters();
		savePage(page,pageRequest);
		return LIST__FROSELECTJSP;
	}
	
	/** 进入查询页面 */
	public String query1() {
		return QUERY_JSP1;
	}
	

	/**
	 * 订单物料备料库 
	 * @return
	 */
	public String list1() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgTbomManager.findByPageRequest1(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP1;
	}
	
	/** 进入查询页面 */
	public String query2() {
		return QUERY_JSP2;
	}
	

	
	/** 执行搜索 */
	public String list2() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		
		Page page = zgTbomManager.findBomsForBatchOut(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP2;
	}
//	/**
//	 * 更新出库数量
//	 */
//	public void updateOutNumForBathchOut(){
//		List list=new ArrayList();
//		getRequest().getParameter("planBomIdAndOutNum");
//		this.list2(); 
//	}

	public FwMenuBo getFwMenuBo() {
		return fwMenuBo;
	}

	public void setFwMenuBo(FwMenuBo fwMenuBo) {
		this.fwMenuBo = fwMenuBo;
	}

	public FwOrganizationBo getFwOrganizationBo() {
		return fwOrganizationBo;
	}

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}

	public FwOrganizationExBo getFwOrganizationExBo() {
		return fwOrganizationExBo;
	}

	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}

}
