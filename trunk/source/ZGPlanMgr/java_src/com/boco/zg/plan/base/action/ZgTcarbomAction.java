/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;
import javacommon.util.StringHelper;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import sap.SapClient;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.bom.base.service.ZgTbomManager;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarbomSuppliersBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderSuppliersBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.model.ZgTcarbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTcarbomExBo;
import com.boco.zg.plan.service.ZgTcarplanExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTcarbomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTcarbom/query_ZgTcarbom.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTcarbom/list_ZgTcarbom.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTcarbom/create_ZgTcarbom.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTcarbom/edit_ZgTcarbom.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTcarbom/show_ZgTcarbom.jsp";
	protected static final String ORDER_BOM="/zg/plan/ZgTcarbom/carboms.jsp";
	protected static final String ORDER_BOM_2="/zg/plan/ZgTcarbom/carboms2.jsp";
	protected static final String ORDER_BOM_3="/zg/plan/ZgTcarbom/carboms3.jsp";
	protected static final String ORDER_BOM_PANEL="/zg/plan/ZgTcarbom/carbomPanel.jsp";
	protected static final String ORDER_BOM_PANEL2="/zg/plan/ZgTcarbom/carbomPanel2.jsp";
	protected static final String ORDER_BOM_PANEL3="/zg/plan/ZgTcarbom/carbomPanel3.jsp";
	protected static final String ORDER_BOMCHILDREN="/zg/plan/ZgTcarbom/bom_list.jsp";
	protected static final String ORDER_BOMCHILDREN_2="/zg/plan/ZgTcarbom/bom_list2.jsp";
	protected static final String ORDER_BOMCHILDREN_3="/zg/plan/ZgTcarbom/bom_list3.jsp";
	
	protected static final String findBomDE_ACTION = "!/zg/plan/ZgTcarbom/findBomDE.do";
	protected static final String findBomList_ACTION = "!/zg/plan/ZgTcarbom/findBomList.do";
	protected static final String bomPanel_ACTION = "!/zg/plan/ZgTcarbom/bomPanel.do";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTcarbom/list.do";

	private static final String ORDER_BOM_PANEL4 = "/zg/plan/ZgTcarbom/carbomPanel4.jsp";
	
	private ZgTcarbomExBo zgTcarbomExBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTorderbomBo zgTorderbomBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	private ZgTcarplanExBo zgTcarplanExBo;
	
	private ZgTcarplanBo zgTcarplanBo;
	
	
	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	
	private ZgTbomManager zgTbomManager;
	
	private ZgTcarbomSuppliersBo zgTcarbomSuppliersBo;
	
	private ZgTorderSuppliersBo zgTorderSuppliersBo;
	
	private IVmModelBo vmModelBo;
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}
	
	private ZgTcarbom zgTcarbom;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTcarbom = new ZgTcarbom();
		} else {
			zgTcarbom = (ZgTcarbom)zgTcarbomExBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTcarbomExBo(ZgTcarbomExBo bo) {
		this.zgTcarbomExBo = bo;
	}	
	
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}

	public void setZgTorderbomBo(ZgTorderbomBo zgTorderbomBo) {
		this.zgTorderbomBo = zgTorderbomBo;
	}
	
	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public Object getModel() {
		return zgTcarbom;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public void setZgTcarplanExBo(ZgTcarplanExBo zgTcarplanExBo) {
		this.zgTcarplanExBo = zgTcarplanExBo;
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
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTcarbom.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTcarbomExBo.findByPageRequest(pageRequest);
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
		zgTcarbomExBo.save(zgTcarbom);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTcarbomExBo.update(this.zgTcarbom);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTcarbomExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}
	
	private String carPlanId;
	
	public String getCarPlanId() {
		return carPlanId;
	}

	public void setCarPlanId(String carPlanId) {
		this.carPlanId = carPlanId;
	}

	/**
	 * 装车计划查找bom组件
	 * @return
	 */
	public String findBomDE() {
		this.getRequest().setAttribute("carPlanId", carPlanId);
		List<Map> list = zgTcarbomExBo.findBomDEByCarPlanId(carPlanId);
		this.getRequest().setAttribute("bomList", list);
		if(list.size() > 0)
			this.getRequest().setAttribute("firstBom", list.get(0));
		return ORDER_BOM;
	}
	
	/**
	 * 预装 预焊 总装 仓库领料 查找bom组件(新)
	 * @return
	 */
	public String bomPanel() {
		this.getRequest().setAttribute("carPlanId", carPlanId);
		Map<String,List<Map>> map = new HashMap<String,List<Map>>();
		List<Map> list = zgTcarbomExBo.findCarPlanBomByCarPlanId(carPlanId);
		String orderPlanType = getRequest().getParameter("orderPlanType");
		for(Map bom : list) {
			String idnrk = IbatisDAOHelper.getStringValue(bom, "IDNRK");
			List<Map> boms = map.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<Map>();
			}
			boms.add(bom);
			map.put(idnrk, boms);
		}
		getRequest().setAttribute("orderboms", map);
		getRequest().setAttribute("count", list.size());
		getRequest().setAttribute("view", getRequest().getParameter("isView"));
		getRequest().setAttribute("orderPlanType", orderPlanType);
		return ORDER_BOM_PANEL;
	}
	
	/**
	 * 预装 预焊 总装 仓库领料 查找bom组件(新)
	 * @return
	 */
	public String bomPanel2() {
		this.getRequest().setAttribute("carPlanId", carPlanId);
		Map<String,List<Map>> map = new HashMap<String,List<Map>>();
		List<Map> list = zgTcarbomExBo.findStoragePlanBomByCarPlanId(carPlanId);
		for(Map bom : list) {
			String idnrk = IbatisDAOHelper.getStringValue(bom, "IDNRK");
			List<Map> boms = map.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<Map>();
			}
			boms.add(bom);
			map.put(idnrk, boms);
		}
		this.getRequest().setAttribute("orderboms", map);
		getRequest().setAttribute("view", getRequest().getParameter("isView"));
		
		//获取领料计划信息
		ZgTcarplan zgTcarplan=zgTcarplanBo.getById(carPlanId);
		getRequest().setAttribute("carPlan", zgTcarplan);
		getRequest().setAttribute("count", list.size());
		
		return ORDER_BOM_PANEL2;
	}
	
	/**
	 * 预装 预焊 总装 仓库领料 查找bom组件(新)
	 * @return
	 */
	public String bomPanel3() {
		List<Map> bomList = zgTcarbomExBo.findBomList(carPlanId);
		
		String view=getRequest().getParameter("isView");
		
		if(StringHelper.isEmpty(view)){//领取物料
			//获取相就在的供应商信息
			if(bomList.size()>0){
				String idnrks="";
				for(Map bom:bomList){
					idnrks=idnrks+bom.get("IDNRK").toString()+"','";
				}
				
				idnrks=idnrks.substring(0,idnrks.length()-3);
					
				
				//获取供应商信息
				List<ZgTcarbomSuppliers> suppliersLst=zgTbomManager.getSuppliersListByIdnrks(idnrks);
				
				for(Map bom:bomList){
					List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
					for(ZgTcarbomSuppliers sup:suppliersLst){
						if(bom.get("IDNRK").toString().equals(sup.getIdnrk())){
							tempList.add(sup);
						}
					}
					bom.put("supList", tempList);
				}
				
			}
		}else {//查看物料
			//获取相就在的供应商信息
			if(bomList.size()>0){
				String carBomIds="";
				for(Map bom:bomList){
					carBomIds=carBomIds+bom.get("CUID").toString()+"','";
				}
				
				carBomIds=carBomIds.substring(0,carBomIds.length()-3);
					
				
				//获取供应商信息
				List<ZgTcarbomSuppliers> suppliersLst=zgTcarbomSuppliersBo.getCarBomSuppliersListByCarbomIds(carBomIds);
				
				for(Map bom:bomList){
					List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
					for(ZgTcarbomSuppliers sup:suppliersLst){
						if(bom.get("CUID").toString().equals(sup.getCarBomId())){
							tempList.add(sup);
						}
					}
					bom.put("supList", tempList);
				}
				
			}
		}
		
		
		
		//获取领料计划信息
		ZgTcarplan zgTcarplan=zgTcarplanBo.getById(carPlanId);
		getRequest().setAttribute("carPlan", zgTcarplan);
		
		getRequest().setAttribute("view", getRequest().getParameter("isView"));
		getRequest().setAttribute("count", bomList.size());
		this.getRequest().setAttribute("bomList", bomList);
		return ORDER_BOM_PANEL3;
	}
	
	/**
	 * 为装车计划删除BOM组件
	 * @throws IOException 
	 */
	public String delOrderBomForCarPlan() throws IOException{
		String carPlanId=this.getRequest().getParameter("carPlanId");
		String orderPlanType=this.getRequest().getParameter("orderPlanType");
		String matnr=this.getRequest().getParameter("matnrs");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String orderPlanbomId=params.get("orderPlanbomId").toString();
			
			ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
			planbom.setState(Constants.CarPlanStatus.SUBMIT.value());
			planbom.setPlanNum(0L);
			zgTorderPlanbomBo.update(planbom);
			
			//删除装车计划bom
			zgTcarbomBo.removeById((java.lang.String)params.get("id"));
		}
		return bomPanel_ACTION+"?carPlanId=" +carPlanId+"&orderPlanType="+orderPlanType;
	}
	
	/**
	 * 为装车计划增加BOM组件
	 * @throws IOException 
	 */
	public String addOrderBomForCarPlan() throws IOException{
		String carPlanId=this.getRequest().getParameter("carPlanId");
		String orderPlanType=getRequest().getParameter("orderPlanType");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			//仓库
			String orderBomId=params.get("orderBomId").toString();
			String orderId=params.get("orderId").toString();
			String orderPlanbomId=params.get("orderPlanbomId").toString();
			
			ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
			planbom.setPlanNum(planbom.getCarNum());
			planbom.setState(Constants.CarPlanStatus.DONE.value());
			zgTorderPlanbomBo.update(planbom);
			
			//生成装车计划bom
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(carPlanId);
//			bom.setOrderBomId(orderBomId);
//			bom.setOrderId(orderId);
			bom.setPlanNum(planbom.getCarNum()-planbom.getCompleteNum());
			bom.setRealNum(0l);
			bom.setOrderPlanbomId(orderPlanbomId);
			zgTcarbomBo.save(bom);
		}
		return bomPanel_ACTION+"?carPlanId=" +carPlanId+"&orderPlanType="+orderPlanType;
	}
	
	/**
	 * 增加bom组件
	 * @return
	 */
	public String addBom() {
		String carPlanId = this.getRequest().getParameter("carPlanId");
		String orderPlanType=getRequest().getParameter("orderPlanType");
		this.getRequest().setAttribute("carPlanId", carPlanId);
		this.getRequest().setAttribute("orderPlanType", orderPlanType);
		ZgTcarbom entity = new ZgTcarbom();
		entity.setCarPlanId(carPlanId);
		//查询超时是否可新增BOM
		String overTime=zgTorderbomExBo.findOverTimeValue();
		List<ZgTcarbom> list = this.zgTcarbomBo.findByProperty(entity);
		if(list!=null && list.size()>0){
			ZgTcarbom zgTcarbom = list.get(0);
//			String  orderBomID = zgTcarbom.getOrderBomId();
			String  orderBomID = "";
			ZgTorderbom zgTorderbom = zgTorderbomBo.getById(orderBomID);
			String lgort = zgTorderbom.getLgort();
			PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
			pageRequest.getFilters().put("lgort", lgort);
			pageRequest.getFilters().put("overTime", overTime);
			pageRequest.getFilters().put("planType", orderPlanType);
			List<ZgTorderbomEx> bomList = this.zgTorderbomExBo.findBomList(pageRequest);
			this.getRequest().setAttribute("bomList", bomList);
		}
		return "/zg/plan/ZgTcarbom/boms.jsp";
	}
	
	/**
	 * 预装 预焊 总装 仓库领料 查找bom组件
	 * @return
	 */
	public String findBomDE2() {
		this.getRequest().setAttribute("carPlanId", carPlanId);
		List<Map> list = zgTcarbomExBo.findBomDEByCarPlanId(carPlanId);
		this.getRequest().setAttribute("bomList", list);
		if(list.size() > 0)
			this.getRequest().setAttribute("firstBom", list.get(0));
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return ORDER_BOM_2;
	}
	
	/**
	 * 批量仓库领料查找bom组件
	 * @return
	 */
	public String findBomDE3() {
		this.getRequest().setAttribute("carPlanId", carPlanId);
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return ORDER_BOM_3;
	}
	
	private String[] matnrs;

	public void setMatnrs(String[] matnrs) {
		this.matnrs = matnrs;
	}

	public String[] getMatnrs() {
		return matnrs;
	}
	
	public String findBomList() throws ServletException, IOException {
		String matnr = this.getRequest().getParameter("matnr")==null?"":this.getRequest().getParameter("matnr");
		if(!StringUtils.isBlank(matnr)) {
			matnrs = new String[]{matnr};
		}
		if(matnrs != null && matnrs.length > 0) {
			List<Map> list = zgTcarbomExBo.findBomByMatnrs(carPlanId, matnrs);
			this.getRequest().setAttribute("bomList", list);
		}
	    return ORDER_BOMCHILDREN;
	}
	
	public String findBomList2() throws ServletException, IOException {
		String matnr = this.getRequest().getParameter("matnr")==null?"":this.getRequest().getParameter("matnr");
		if(!StringUtils.isBlank(matnr)) {
			matnrs = new String[]{matnr};
		}
		if(matnrs != null && matnrs.length > 0) {
			List<Map> list = zgTcarbomExBo.findBomByMatnrs(carPlanId, matnrs);
			this.getRequest().setAttribute("bomList", list);
		}
		getRequest().setAttribute("view", getRequest().getParameter("view"));
	    return ORDER_BOMCHILDREN_2;
	}
	
	public String findBomList3() throws ServletException, IOException {
		List<Map> list = zgTcarbomExBo.findBomList(carPlanId);
		this.getRequest().setAttribute("bomList", list);
		getRequest().setAttribute("view", getRequest().getParameter("view"));
	    return ORDER_BOMCHILDREN_3;
	}
	
	private List<ZgTcarbomEx> carbomList;

	public List<ZgTcarbomEx> getCarbomList() {
		return carbomList;
	}

	public void setCarbomList(List<ZgTcarbomEx> carbomList) {
		this.carbomList = carbomList;
	}
	
//	public void saveCarPlan() throws IOException{
//		zgTcarbomExBo.saveCarPlan(carbomList,Constants.CarPlanStatus.SAVE.value());
//		rendHtml("if(parent.doQuery)parent.doQuery()");
//	}
//	
//	/**
//	 * 装车计划提交
//	 * @throws IOException
//	 */
//	public void submitCarPlan() throws IOException{
//		zgTcarbomExBo.saveCarPlan(carbomList,Constants.CarPlanStatus.SUBMIT.value());
//		rendHtml("if(parent.doQuery)parent.doQuery()");
//	}
	
	/**
	 * 计划领料提交
	 * @throws IOException
	 */
	public void submitStorePlan() throws IOException{
		String storageUserId=getRequest().getParameter("storageUserId");
//		zgTcarplanExBo.updateCarPlanStorageUserId(carPlanId, storageUserId);
		zgTcarbomExBo.updateCarboms("",carbomList,true);
		zgTcarbomExBo.updateZgtcarPlanState(carbomList.get(0).getCarPlanId(),Constants.CarPlanStatus.SUBMIT.value(),Calendar.getInstance().getTime(),storageUserId);

		zgTcarplanExBo.storagePlanSubmitById(carPlanId,"","ALL");
		rendHtml("if(parent.doQuery)parent.doQuery()");
	}
	
	/**
	 * 计划领料保存
	 * @throws IOException
	 */
	public void saveStorePlan() throws IOException{
		String storageUserId=getRequest().getParameter("storageUserId");
		zgTcarplanExBo.updateCarPlanStorageUserId(carPlanId, storageUserId);
		zgTcarbomExBo.updateCarboms("",carbomList,true);
		zgTcarbomExBo.updateZgtcarPlanState(carbomList.get(0).getCarPlanId(),Constants.CarPlanStatus.SAVE.value(),null,null);
		rendHtml("if(parent.doQuery)parent.doQuery()");
	}
	
	/**
	 * 批量领料计划保存
	 * @return
	 * @throws IOException
	 */
	public void saveStorePlanForBatch() throws IOException{
		String storageUserId=getRequest().getParameter("storageUserId");
		zgTcarplanExBo.updateCarPlanStorageUserId(carPlanId, storageUserId);
		zgTcarbomExBo.updateCarboms("",carbomList,true);
		zgTcarbomExBo.updateZgtcarPlanState(carbomList.get(0).getCarPlanId(),Constants.CarPlanStatus.SAVE.value(),null,null);

		rendHtml("if(parent.doQuery)parent.doQuery()");
	//	return SUCCESS;
	}
	
	/**
	 * 批量领料计划提交
	 * @return
	 * @throws IOException
	 */
	public void submitStorePlanForBatch() throws IOException{
		String storageUserId=getRequest().getParameter("storageUserId");
//		zgTcarplanExBo.updateCarPlanStorageUserId(carPlanId, storageUserId);
		zgTcarbomExBo.updateCarboms("",carbomList,true);
		zgTcarplanExBo.carPlanSubmit(carPlanId);
		zgTcarbomExBo.updateZgtcarPlanState(carbomList.get(0).getCarPlanId(),Constants.CarPlanStatus.SUBMIT.value(),Calendar.getInstance().getTime(),storageUserId);
		
		//同步领料bom到sap
		int batchNo=this.zgTcarbomBo.getSeq("SEQ_BATCH_NO");
		getSapClient().businessHandler("5", carbomList.get(0).getCarPlanId(),batchNo,"");
		
		rendHtml("if(parent.doQuery)parent.doQuery()");
	//	return SUCCESS;
	}
	
	/**
	 * 预装 预焊 总装 仓库领料 查找bom组件(新)
	 * @return
	 */
	public String bomPanel4() {
		String flag=getRequest().getParameter("flag");
		
		getRequest().setAttribute("flag",flag );
		String aufnr=getRequest().getParameter("aufnr");
		getRequest().setAttribute("aufnr",aufnr);
		
		this.getRequest().setAttribute("carPlanId", carPlanId);
		
		Map<String,List<Map>> map = new HashMap<String,List<Map>>();
		
		//获取领料计划信息
		ZgTcarplan zgTcarplan=zgTcarplanBo.getById(carPlanId);
		getRequest().setAttribute("carPlan", zgTcarplan);
		
		List<Map> bomList=zgTcarbomExBo.getBomLIstCarPlanId(carPlanId);
		
		//获取相就在的供应商信息
		if(bomList.size()>0){
			String aufnrs="";
			String idnrks ="";
			for(Map bom:bomList){
				aufnrs=aufnrs+bom.get("AUFNR").toString()+"','";
				idnrks=idnrks+bom.get("IDNRK").toString()+"','";
			}
			
			aufnrs=aufnrs.substring(0,aufnrs.length()-3);
			idnrks=idnrks.substring(0,idnrks.length()-3);	
			
			if("view".equals(flag)){
				zgTcarbomExBo.doPressSupliers
				
				(bomList);
			}else{
				zgTcarbomExBo.doPressSupliers(bomList, aufnrs, idnrks);
			}
		}
		
		getRequest().setAttribute("bomList", bomList);
		
		return ORDER_BOM_PANEL4;
	}

	

	public ZgTcarplanBo getZgTcarplanBo() {
		return zgTcarplanBo;
	}

	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}

	public ZgTbomManager getZgTbomManager() {
		return zgTbomManager;
	}

	public void setZgTbomManager(ZgTbomManager zgTbomManager) {
		this.zgTbomManager = zgTbomManager;
	}

	public ZgTcarbomSuppliersBo getZgTcarbomSuppliersBo() {
		return zgTcarbomSuppliersBo;
	}

	public void setZgTcarbomSuppliersBo(ZgTcarbomSuppliersBo zgTcarbomSuppliersBo) {
		this.zgTcarbomSuppliersBo = zgTcarbomSuppliersBo;
	}

	public ZgTorderSuppliersBo getZgTorderSuppliersBo() {
		return zgTorderSuppliersBo;
	}

	public void setZgTorderSuppliersBo(ZgTorderSuppliersBo zgTorderSuppliersBo) {
		this.zgTorderSuppliersBo = zgTorderSuppliersBo;
	}

	
}
