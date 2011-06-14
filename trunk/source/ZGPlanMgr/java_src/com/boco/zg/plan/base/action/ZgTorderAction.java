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

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.service.FwOrganizationExBo;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderTask;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.base.service.ZgTorderTaskBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	String string="";
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTorder/query_ZgTorder.jsp";
	protected static final String QUERY_JSP3 = "/zg/plan/ZgTorder/query_ZgTorder3.jsp";
	protected static final String LIST_JSP3= "/zg/plan/ZgTorder/list_ZgTorder3.jsp";
	protected static final String QUERY_JSP4 = "/zg/plan/ZgTorder/query_ZgTorder4.jsp";
	protected static final String LIST_JSP4= "/zg/plan/ZgTorder/list_ZgTorder4.jsp";
	protected static final String QUERY_JSP_FORSELECT = "/zg/plan/ZgTorder/query_ZgTorder1.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTorder/list_ZgTorder.jsp";
	protected static final String LIST_JSP_FORSELECT1= "/zg/plan/ZgTorder/list_ZgTorder1.jsp";
	protected static final String LIST_JSP_FORSELECT2= "/zg/plan/ZgTorder/list_ZgTorder2.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTorder/create_ZgTorder.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTorder/edit_ZgTorder.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTorder/show_ZgTorder.jsp";
	
	protected static final String SHOW_VIEW_JSP = "/zg/plan/ZgTorderbom/ViewZgTorderbom/show_ZgTorder.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorder/list.do";
	protected static final String LIST_ACTION2 = "!/zg/plan/ZgTorder/list1.do";
	protected static final String BOMMOVEUI = "/zg/plan/ZgTorder/bomMoveUI.jsp";
	//用于订单生产条件查询
	protected static final String QUERY_JSP5="/zg/plan/ZgTorder/query_ZgTorderManage.jsp";
	protected static final String LIST_JSP5="/zg/plan/ZgTorder/list_ZgTorderManage.jsp";
	 
	// 用于导出数据
	protected static final String EXPORT_STORECAR="!/frame/excel/all/DataTrans/export.do";
	
	private ZgTorderExBo zgTorderExBo;
	
	private ZgTorderBo zgTorderBo;
	
	private IVmModelBo vmModelBo;
	
	private ZgTorderTaskBo zgTorderTaskBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private ZgTorder zgTorder;
	java.lang.String id = null;
	private String[] items;
	
	private List<FwOrganization> arbplList;
	private FwOrganizationBo fwOrganizationBo;
	
	private FwOrganizationExBo fwOrganizationExBo;
	
	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}

	public List<FwOrganization> getArbplList() {
		FwOrganization fwOrganization=new FwOrganization();
		fwOrganization.setType("3");
		return fwOrganizationBo.findByProperty(fwOrganization);
	}

	public void setArbplList(List<FwOrganization> arbplList) {
		this.arbplList = arbplList;
	}
	
	public List<Map> getPlantList(){
		return fwOrganizationExBo.getPlantList();
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorder = new ZgTorder();
		} else {
			zgTorder = (ZgTorder)zgTorderExBo.getById(id);
		}
	}
	
	public Object getModel() {
		return zgTorder;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 进入查询页面 */
	public String query() {
		CommonService.defultDateSet(getRequest(), "pcdat_start", "pcdat_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		getRequest().getRequestURL();
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorder.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		String publishNum=this.getRequest().getParameter("publishNum");
		if(publishNum!=null){
			getRequest().setAttribute("publishNum", publishNum);
		}
		return SHOW_JSP;
	}
	
	/** 查看对象*/
	public String showByTaskId() {
		String publishNum=this.getRequest().getParameter("publishNum");
		if(publishNum!=null){
			getRequest().setAttribute("publishNum", publishNum);
		}
		zgTorder = (ZgTorder)zgTorderBo.getByTaskId(id);
		return SHOW_JSP;
	}
	
	/** 查看对象*/
	public String showInfo() {
		return SHOW_JSP;
	}
	
	/** 查看对象(不带任何操作界面)*/
	public String showView() {
		return SHOW_VIEW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		zgTorderExBo.save(zgTorder);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTorderExBo.update(this.zgTorder);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}
	
	public String submitOrder() {
		String orderId = this.getRequest().getParameter("orderId");
		OperatorInfo operatorInfo = (OperatorInfo)this.getSession().getAttribute("operatorInfo");	
		zgTorderExBo.submitOrder(orderId,operatorInfo);
		this.getRequest().setAttribute("actionMessages", "提交成功！");
		return MESSAGE_PAGE;
	}

	public void setZgTorderExBo(ZgTorderExBo zgTorderExBo) {
		this.zgTorderExBo = zgTorderExBo;
	}

	
	public String exportBomCar()
	{
		
		getRequest().getSession().setAttribute("ex_template","exportOrderBom");
		
		Map parmsMap=new HashMap();
		parmsMap.put("operatorId", getSessionOperatorId());
		getRequest().getSession().setAttribute("ex_in", parmsMap);
		//return "/frame/excel/all/BomCar/export.do?template=exportBomCar"+inString;
		
		
		return EXPORT_STORECAR;
	}
	
	public String exportNotMatklSelfBom()
	{
		
		getRequest().getSession().setAttribute("ex_template","exportNotMatklSelfBom");
		
		Map parmsMap=new HashMap();
		parmsMap.put("operatorId", getSessionOperatorId());
		getRequest().getSession().setAttribute("ex_in", parmsMap);
		
		
		return EXPORT_STORECAR;
	}

	public FwOrganizationExBo getFwOrganizationExBo() {
		return fwOrganizationExBo;
	}

	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}
	
	/**
	 * BOM移单页面
	 */
	public String toOrdeBomMoveUI(){
		return BOMMOVEUI;
	}
	

	/** 进入查询页面 */
	public String queryForSelect() {
		CommonService.defultDateSet(getRequest(), "pcdat_start", "pcdat_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		getRequest().setAttribute("flag", getRequest().getParameter("flag"));
		getRequest().setAttribute("sourceOrderId", getRequest().getParameter("sourceOrderId"));
		return QUERY_JSP_FORSELECT;
	}
	
	public String listForSelect(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("bomMove", true);
		Page page = zgTorderExBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP_FORSELECT1;
	}
	
	public String listForSelectByOrderId() {
		String sourceOrderId=getRequest().getParameter("sourceOrderId");
		List<Map> orderList=zgTorderExBo.listForSelectByOrderId(sourceOrderId);
		getRequest().setAttribute("orderList", orderList);
		return LIST_JSP_FORSELECT2;
	}

	public ZgTorderBo getZgTorderBo() {
		return zgTorderBo;
	}

	public void setZgTorderBo(ZgTorderBo zgTorderBo) {
		this.zgTorderBo = zgTorderBo;
	}
	
	/** 进入查询页面 */
	public String query1() {
		CommonService.defultDateSet(getRequest(), "pcdat_start", "pcdat_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP3;
	}
	
	/** 执行搜索 */
	public String list1() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorder.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderBo.findByPageRequest1(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP3;
	}
	
	/** 进入查询页面 */
	public String query2() {
		CommonService.defultDateSet(getRequest(), "pcdat_start", "pcdat_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP4;
	}
	
	/** 执行搜索 */
	public String list2() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorder.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP4;
	}
	
	/**手工结单
	 * @throws IOException */
	public void finishOrder() throws IOException {
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String orderTaskId=(java.lang.String)params.get("id");
			
			zgTorderExBo.manulFinishOrderByOrderId(orderTaskId,getSessionOperatorId());
			
			ZgTorderTask task=zgTorderTaskBo.getById(orderTaskId);
			ZgTorder order =zgTorderBo.getById(task.getOrderId());
			order.setOrderState(Constants.OrderStatus.SUBMIT.value());
			zgTorderBo.update(order);
		}
		rendHtml("alert('操作成功');window.parent.doQuery();");
	}
	/** 进入查询页面 */
	public String query5() {
		CommonService.defultDateSet(getRequest(), "pcdat_start", "pcdat_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		getRequest().getRequestURL();
		return QUERY_JSP5;
	}
	
	/** 执行搜索 */
	public String list5() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorder.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP5;
	}

	/**
	 * @return the zgTorderTaskBo
	 */
	public ZgTorderTaskBo getZgTorderTaskBo() {
		return zgTorderTaskBo;
	}

	/**
	 * @param zgTorderTaskBo the zgTorderTaskBo to set
	 */
	public void setZgTorderTaskBo(ZgTorderTaskBo zgTorderTaskBo) {
		this.zgTorderTaskBo = zgTorderTaskBo;
	}
	
	

}
