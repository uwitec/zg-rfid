/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.EnumModel;
import javacommon.base.service.IVmModelBo;
import javacommon.util.StringHelper;

import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.meta.service.FwOrganizationExBo;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.bom.base.model.ZgTbom;
import com.boco.zg.bom.base.service.ZgTbomManager;
import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarbomSuppliersBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTorderSuppliersBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.model.ZgTcarbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTcarbomExBo;
import com.boco.zg.plan.service.ZgTcarplanExBo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderPlanGroupExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.Constants.CarPlanType;
import com.boco.zg.virtualorg.base.service.ZgTvirtualorgBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTcarplanAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTcarplan/query_ZgTcarplan.jsp";
	protected static final String QUERY_STORAGE_JSP = "/zg/plan/ZgTcarplan/query_ZgTcarplan_Storage.jsp";
	protected static final String QUERY_STORAGE_JSP2 = "/zg/plan/ZgTcarplan/query_ZgTcarplan_Storage2.jsp";
	protected static final String QUERY_STORAGE_JSP1 = "/zg/plan/ZgTcarplan/query_ZgTcarplan_Storage1.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTcarplan/list_ZgTcarplan.jsp";
	protected static final String LIST_STORAGE_JSP= "/zg/plan/ZgTcarplan/list_ZgTcarplan_Storage.jsp";
	protected static final String LIST_STORAGE_JSP2= "/zg/plan/ZgTcarplan/list_ZgTcarplan_Storage2.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTcarplan/create_ZgTcarplan.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTcarplan/edit_ZgTcarplan.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTcarplan/show_ZgTcarplan.jsp";
	
	protected static final String CAR_PLAN_CREATE = "/zg/plan/ZgTcarplan/carplan_create.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTcarplan/list.do";
	

	protected static final String ORDER_BOMCHILDREN="/zg/plan/ZgTcarplan/bom_list.jsp";
	
	protected static final String BOMS="/zg/plan/ZgTcarplan/boms.jsp";
	
	protected static final String VIEW_CARPLAN = "/zg/plan/ZgTcarplan/view_ZgTcarplan.jsp";
	
	protected static final String SET_OVERTIME_JSP="/zg/plan/ZgTcarplan/overTimeSet.jsp";

	private static final String ARBPLIST = "/zg/plan/ZgTcarplan/arbpl_Menu.jsp";

	private static final String CAR_BOM_LIST = "/zg/plan/ZgTcarbom/car_bom_list.jsp";
		
	private static final String CAR_BOM = "/zg/plan/ZgTcarbom/car_bom.jsp";

	private static final String CAR_BOM_LIST_PRINT = "/zg/plan/ZgTcarbom/car_bom_list_print.jsp";

	private static final String VIEW_CARPLAN_LIST = "/zg/plan/ZgTcarplan/list_CarPlan.jsp";
	
	private ZgTcarplanBo zgTcarplanBo;

	private ZgTcarplanExBo zgTcarplanExBo;

	
	private IVmModelBo vmModelBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	private ZgTbomManager zgTbomManager;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private FwEmployeeBo fwEmployeeBo;	
	
	
	private ZgTcarbomExBo zgTcarbomExBo;
	
	private ZgTorderPlanGroupExBo zgTorderPlanGroupExBo;
	
	private ZgTorderExBo zgTorderExBo;
	
	private List<ZgTcarbomEx> carbomList;
	
	private ZgTvirtualorgBo zgTvirtualorgBo;
	
	private FwOrganizationExBo fwOrganizationExBo;
	
	private ZgTcarbomSuppliersBo zgTcarbomSuppliersBo;
	
	private ZgTorderSuppliersBo zgTorderSuppliersBo;
	
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	
	
	private List<EnumModel> overTimeList;
	public List<TmdEnumevalue> getOverTimeList() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		return service.getEnumValue("OVER_TIME");
	}
	
	private ZgTcarplan zgTcarplan;
	java.lang.String id = null;
	private String[] items;
	private String carPlanId;
	private String orderPlanType;
	
	//领料组角色
	private List<FwOrganization> roles;
	
	private FwOrganizationBo fwOrganizationBo;
	
	public List<FwOrganization> getRoles() {
		if(roles == null) {
			FwOrganization fwOrganization=new FwOrganization();
			fwOrganization.setType("4");
			
			roles = fwOrganizationBo.findByProperty(fwOrganization, "t0_LABEL_CN", true);
		}
		return roles;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTcarplan = new ZgTcarplan();
		} else {
			zgTcarplan = (ZgTcarplan)zgTcarplanExBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTcarplanExBo(ZgTcarplanExBo bo) {
		this.zgTcarplanExBo = bo;
	}	
	
	public Object getModel() {
		return zgTcarplan;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public ZgTorderbomExBo getZgTorderbomExBo() {
		return zgTorderbomExBo;
	}

	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public void setZgTbomManager(ZgTbomManager zgTbomManager) {
		this.zgTbomManager = zgTbomManager;
	}
	
	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}
	

	/** 进入查询页面 */
	public String query() {
		
		CommonService.defultDateSet(getRequest(), "createDate_start", "createDate_end");
		
		//type为装车计划的属性， 1为装车 2为领料
		String type=this.getRequest().getParameter("type");
		String orderPlanType=this.getRequest().getParameter("orderPlanType");
		getRequest().setAttribute("type",type);
		getRequest().setAttribute("orderPlanType", orderPlanType);
		
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		
		if(CarPlanType.CARPLAN.value().equals(type)){//领料计划
			return QUERY_JSP;
		}else{//仓库领料
			return QUERY_STORAGE_JSP;
		}
		
	
		
	}
	
	/**
	 * 查询登陆人所涉及的领料生产厂　，默认显示第一个生产产的订单
	 * @return
	 */
	public String query1() {
		
		String onload=getRequest().getParameter("onload");
		
		//type为装车计划的属性， 1为装车 2为领料
		String type=this.getRequest().getParameter("type");
		String orderPlanType=this.getRequest().getParameter("orderPlanType");
		String arbpl=getRequest().getParameter("arbpl");
		getRequest().setAttribute("type",type);
		getRequest().setAttribute("orderPlanType", orderPlanType);
		getRequest().setAttribute("onload", onload);
		getRequest().setAttribute("arbpl", arbpl);
		
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		
		String plant=this.getRequest().getParameter("plant");
		
		
		List<Map> plantList=(List<Map>) getSession().getAttribute("plantList"+orderPlanType);
		String defaultPlant="";
		Date curDate=new Date();
		if(null==plantList){//session中没有生产厂列表，则查询，以列表的第一条记录作为默认的生产厂记录
			plantList=zgTvirtualorgBo.getPlantListByOperatorId(getSessionOperatorId(),orderPlanType); 
			
			if(plantList.size()>0){
				defaultPlant=plantList.get(0).get("ORG_ID").toString();
				curDate=(Date)plantList.get(0).get("PXDATE");
			}
			
		}else {
			if(StringHelper.isEmpty(plant)){//没有选择生产厂，则默认为原来的生产厂,如果是从预装的菜单跳到总装，原来默认的生产厂是C02,总装的生产厂列表是C01,C04,则这是以总装生产厂的第一个作为默认生产厂
				defaultPlant=getSession().getAttribute("defaultPlant")==null?"":getSession().getAttribute("defaultPlant").toString();
				if(plantList.size()>0){
					boolean flag=false;
					for(Map obj:plantList){//从负责生产厂中找上次默认的生产厂，如果没有找到，则默认为负责生产厂列表中的第一个
						if(defaultPlant.equals(obj.get("ORG_ID").toString())){
							//获取该生产厂目前的排序日期
							curDate=fwOrganizationExBo.getPxdateByOrgId(defaultPlant);
							flag=true;
							break;
						}
					}
					if(!flag){
						defaultPlant=plantList.get(0).get("ORG_ID").toString();
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultPlant);
					}
				}
			}else{//如果用户选择了某个生产厂
				for(Map obj:plantList){
					if(plant.equals(obj.get("ORG_ID").toString())){
						defaultPlant=plant;
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultPlant);
						break;
					}
				}
			}
		}
		
		
		getSession().setAttribute("plantList"+orderPlanType,plantList);
		getSession().setAttribute("defaultPlant",defaultPlant);
		
		Object object=getSession().getAttribute(getSessionOperatorId()+"_matkl");
		String matkls=object==null?"null":object.toString();
		
		//获取领料计划组列表
		List<ZgTorderPlanGroup> planGroupList =zgTorderPlanGroupExBo.getPlanGroupList(getSessionOperatorId(),orderPlanType,arbpl,curDate,defaultPlant,matkls);
		
		getRequest().setAttribute("planGroupList", planGroupList);
		
		for(int i=0;i<5;i++){
			if(i<planGroupList.size()){
				ZgTorderPlanGroup group=planGroupList.get(i);
				List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByGroupId(group.getCuid());
				getRequest().setAttribute("orderList"+i, orderList);
			}
			
		}
		
		
		return QUERY_STORAGE_JSP1;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		
	//	String plant=getSession().getAttribute("defaultPlant")==null?"":getSession().getAttribute("defaultPlant").toString();
		
		String onload=getRequest().getParameter("onload");
		getRequest().setAttribute("onload", onload);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTcarplan.BM_CLASS_ID,super.getSessionUserId()));
		
		Object object=getSession().getAttribute(getSessionOperatorId()+"_matkl");
		String carMatkls=object==null?"null":object.toString();
		pageRequest.getFilters().put("carMatkls", carMatkls);
		
		List<ZgTorderbomEx> list = zgTorderbomExBo.getBomListByProperty(pageRequest);
		
		Map<ZgTorderbomEx,List<ZgTorderbomEx>> mapList = new HashMap<ZgTorderbomEx,List<ZgTorderbomEx>>();
		for(ZgTorderbomEx bom : list) {
			String idnrk = bom.getIdnrk();
			ZgTorderbomEx temp=new ZgTorderbomEx();
			temp.setIdnrk(idnrk);
			List<ZgTorderbomEx> boms = mapList.get(temp);
			if(boms == null) {
				boms = new ArrayList<ZgTorderbomEx>();
			}
			boms.add(bom);
			mapList.put(temp, boms);
		}
		this.getRequest().setAttribute("orderboms", mapList);
		
		
		getRequest().setAttribute("resultList", list);
		getRequest().setAttribute("pageRequest", pageRequest);
		return LIST_JSP;
	}
	
	/**
	 * 仓库领料计划
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listStorage(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTcarplan.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTcarplanBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		
		Map<String, Object> params=pageRequest.getFilters();
		String orderPlanType=params.get("orderPlanType").toString();
		getRequest().setAttribute("orderPlanType", orderPlanType);
		getRequest().setAttribute("type", this.getRequest().getParameter("s_type"));
		
		return LIST_STORAGE_JSP;
	}
	
	
	/** 查看对象*/
	public String show() {
		String type=this.getRequest().getParameter("type");
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		zgTcarplanExBo.save(zgTcarplan);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTcarplanExBo.update(this.zgTcarplan);
		return LIST_ACTION;
	}
	
	/**删除对象
	 * @throws IOException */
	public void delete() throws IOException {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTcarplanExBo.removeById((java.lang.String)params.get("id"));
		}
		
		promtAndQuery("操作成功");
//		return LIST_ACTION;
	}
	
	public String carPlanCreate() {
		//获取登陆人信息
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");;
		getRequest().setAttribute("operatorId", operatorInfo.getOperatorId());
		String orderPlanType = this.getRequest().getParameter("orderPlanType");
		String overTime=zgTorderbomExBo.findOverTimeValue();
		String treeTemplateId = this.getTreeTemplateId(orderPlanType,overTime);
		this.getRequest().setAttribute("treeTemplateId",treeTemplateId);
	
		//if(Constants.OverTimeType.DELATE_UNFORBID.value().equals(overTime)){
			defultDateSet(getRequest());
		//}
		getRequest().setAttribute("overTime", overTime);
		return CAR_PLAN_CREATE;
	}
	
	/**'
	 * 通过总装，预装，预焊得到不同的树
	 * @param orderPlanType
	 * @return
	 */
	private String getTreeTemplateId(String orderPlanType,String overTime) {
		String result = "";
		if(Constants.OverTimeType.DELATE_UNFORBID.value().equals(overTime)){
			if(orderPlanType!=null && orderPlanType.equals("ABD")){
				result = "carplan_create_tree_yuzhuang_lv1";
			}
			if(orderPlanType!=null && orderPlanType.equals("ABC")){
				result = "carplan_create_tree_yuhan_lv1";
			}
			if(orderPlanType!=null && orderPlanType.equals("ABE")){
				result = "carplan_create_tree_zhongzhuang_lv1";
			}
		}else {
			if(orderPlanType!=null && orderPlanType.equals("ABD")){
				result = "carplan_create_tree_yuzhuang_lv1_1";
			}
			if(orderPlanType!=null && orderPlanType.equals("ABC")){
				result = "carplan_create_tree_yuhan_lv1_1";
			}
			if(orderPlanType!=null && orderPlanType.equals("ABE")){
				result = "carplan_create_tree_zhongzhuang_lv1_1";
			}
		}
		
		if(result.equals("")){
			throw new RuntimeException("传入的装车类型不正确！");
		}
		return result;
	}

	private String itemsValue;
	public String getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(String itemsValue) {
		this.itemsValue = itemsValue;
	}
	/**
	 * 查找bom组件
	 * @return
	 */
	public String findBomList(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		List<ZgTorderbomEx> bomList = zgTorderbomExBo.findBomList(pageRequest);
		getRequest().setAttribute("bomList", bomList);
		Map<String, Object> map=pageRequest.getFilters();
		String lgort=IbatisDAOHelper.getStringValue(map, "lgort");
		String planType=IbatisDAOHelper.getStringValue(map, "planType");
		getRequest().setAttribute("lgort", lgort);
		getRequest().setAttribute("planType", planType);
		getRequest().setAttribute("itemsValue", itemsValue);
		return ORDER_BOMCHILDREN;
	}
	
	/**
	 * 查找仓库bom组件
	 * @return
	 */
	public String findBomListByLgort(){
		String lgort = getRequest().getParameter("lgort");
		String orderPlanId = getRequest().getParameter("orderPlanId");
		String bomId=getRequest().getParameter("bomId");
		getRequest().setAttribute("orderPlanId",orderPlanId);
		Map mapParameter = new HashMap();
		mapParameter.put("lgort", lgort);
		mapParameter.put("orderPlanId", orderPlanId);
		if(!isNullOrEmptyString(bomId)){
			mapParameter.put("bomId", bomId);
		}
		List<ZgTbom> bomList = zgTbomManager.findByProperty(mapParameter);
		getRequest().setAttribute("bomList", bomList);
		getRequest().setAttribute("count", bomList.size());
		getRequest().setAttribute("lgort", lgort);
		return BOMS;
		
	}
	
	/**
	 * mainStr 字符串 
	 * subStr 字符
	 */
	private int repeatCountNum(String mainStr,String subStr){
	    int count = 0;
	    int offset = 0;
	    do{
	    	offset=mainStr.indexOf(subStr);//在字符串tempStr中查看一下是否有subStr字符
	    	if(offset != -1){//如果有subStr字符的话，就截断读取过的部分
	    		mainStr=mainStr.substring(offset+1);
	    		count++;
	    	}
	    }while(offset != -1);
	    return count;
	 }
	
	/**
	 * 生成装车计划单 预装 总装 预焊
	 * @throws IOException 
	 */
	public void generateCarPlan() throws IOException{
		OperatorInfo operatorInfo=(OperatorInfo) getSession().getAttribute("operatorInfo");
		zgTcarplanExBo.generateCarPlan(carbomList,operatorInfo);
		rendHtml("window.returnValue='OK';window.close()");
	}
	
	/**
	 * 仓库管理员确认装车
	 * @throws IOException 
	 */
	public void confirmCarPlan() throws IOException{
		OperatorInfo operatorInfo=(OperatorInfo) getSession().getAttribute("operatorInfo");
		String storageUserId=getRequest().getParameter("storageUserId");
		String result=zgTcarplanExBo.confirmCarPlan(items,carbomList,operatorInfo,storageUserId);
		rendHtml("alert('操作成功!');window.returnValue='"+result+"';window.close();");
	}

	
	/**
	 * 仓库管理员确认装车计划清单(手工结单的情况)
	 * @throws IOException 
	 */
	public void confirmCarPlan1() throws IOException{
		OperatorInfo operatorInfo=(OperatorInfo) getSession().getAttribute("operatorInfo");
		String storageUserId=getRequest().getParameter("storageUserId");
		String result=zgTcarplanExBo.confirmCarPlan1(carbomList,operatorInfo,storageUserId);
		rendHtml("alert('操作成功!');if(parent.doQuery)parent.doQuery();");
	}
	
	/**
	 * 取消订单
	 * @throws IOException
	 */
	public void cancleCarPlan() throws IOException{
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String planType=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "planType");
		String lgort=getRequest().getParameter("lgort");
		OperatorInfo operatorInfo=(OperatorInfo) getSession().getAttribute("operatorInfo");
		zgTcarplanExBo.cancleCarPlan(operatorInfo,planType,lgort);
		rendHtml("window.returnValue='OK';window.close()");
	}
	
	public String deleteBom() throws IOException{
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String planType=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "planType");
		String bomCuids=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "bomCuids");
		//	getRequest().getParameter("planType");
		OperatorInfo operatorInfo=(OperatorInfo) getSession().getAttribute("operatorInfo");
		
		String carPlanId="";
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			
			String cuid=(java.lang.String)params.get("cuid");
			String orderBomId=(java.lang.String)params.get("orderBomId");
			String orderPlanbomId=(java.lang.String)params.get("orderPlanbomId");
			carPlanId=(String)params.get("carPlanId");
			
			if(StringHelper.isEmpty(cuid)){
				bomCuids=bomCuids.replace(orderBomId,"");
				
			}else {
				zgTcarplanExBo.deleteBom(cuid,orderPlanbomId);
			}
			
		}
		
		//判断是否所有物料已经装车 此种情况是由于物料可以分开刷卡引起的
		if(bomCuids.length()<30&&carPlanId.length()>0){//编辑物料的时候，如果删除物料剩下的全是已经刷卡确认过的物料，则直接结掉该装车计划
			if(zgTcarbomExBo.isFinishedCarPlan(carPlanId)){
				String result=zgTcarplanExBo.doProcessPlanByCarPlanId(carPlanId);
				rendHtml("alert('操作成功!');window.returnValue='"+result+"';window.close();");
				return null;
			}
		}
		
		
		pageRequest.getFilters().put("bomCuids", bomCuids);
		String groupId=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "groupId");
		if(bomCuids.endsWith(",")){
			bomCuids=bomCuids.substring(0,bomCuids.length()-1);
		}
		
		
		String[] bomIdArr=bomCuids.split(",");
		List<String> listBomId=new ArrayList<String>();
		for(String bomId:bomIdArr){
			listBomId.add(bomId);
		}
		String lgort=getRequest().getParameter("lgort");
		
		//1.该领料员原来的装车计划bom列表
		List<Map> bomList=zgTcarplanExBo.getBomLIstByUserId(planType,getSessionOperatorId(),lgort);
		
		//获取本次追加的bom列表 
		List<Map> newBomList=zgTcarplanExBo.getBomListByBomIds(bomCuids,getSessionOperatorId());
		
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
				bom.put("subList", tempList);
			}
			
		}
		
		
		
		
		

		Long carEdNum=0l;//已装车数量
		//if(bomList.size()==0){//原来装车bom为空时，则计算目前新添加的bom的装车数量
		for(Map bom:newBomList){
//			if(bomCuids.contains(IbatisDAOHelper.getStringValue(bom, "ORDER_BOM_ID"))){//只计算本次点击的bom默认装车数量
//				Long carCount=Long.parseLong(bom.get("CARCOUNT").toString());//每车可以装车 的数量
//				Long maxValue=Long.parseLong(bom.get("MAX_VALUE").toString());//该物料目前可以装车的数量
//				if(carCount-(maxValue+carEdNum)>=0){
//					bom.put("CAR_PLAN_NUM", maxValue);
//					carEdNum=carEdNum+maxValue;
//				}else if(carCount-carEdNum>0) {
//					bom.put("CAR_PLAN_NUM", carCount-carEdNum);
//					carEdNum=carEdNum+carCount-carEdNum;
//				}
//			}
			
			//modify by wengqin 20110516 0903 所有物料默认装车规格装车数量
			Long carCount=Long.parseLong(bom.get("CARCOUNT").toString());//每车可以装车 的数量
			Long maxValue=Long.parseLong(bom.get("MAX_VALUE").toString());//该物料目前可以装车的数量
			if(maxValue<=carCount){
				bom.put("CAR_PLAN_NUM", maxValue);
			}else {
				bom.put("CAR_PLAN_NUM", carCount);
			}
			
		}
	//}
	if(bomList.size()>0) {
		carPlanId=bomList.get(0).get("CAR_PLAN_ID").toString();
	}
	
	//获取相就在的供应商信息
	if(newBomList.size()>0){
		String aufnrs="";
		String idnrks ="";
		for(Map bom:newBomList){
			aufnrs=aufnrs+bom.get("AUFNR").toString()+"','";
			idnrks=idnrks+bom.get("IDNRK").toString()+"','";
		}
		
		aufnrs=aufnrs.substring(0,aufnrs.length()-3);
		idnrks=idnrks.substring(0,idnrks.length()-3);	
		
		//获取供应商信息
		List<ZgTcarbomSuppliers> suppliersLst=zgTorderSuppliersBo.getBomSuppliersListByAufnrIdnrk(aufnrs,idnrks);
		
		for(Map bom:newBomList){
			List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
			String aufnr=bom.get("AUFNR").toString();
			String idnrk=bom.get("IDNRK").toString();
			for(ZgTcarbomSuppliers sup:suppliersLst){
				if(aufnr.equals(sup.getAufnr())&&idnrk.equals(sup.getIdnrk())){
					tempList.add(sup);
				}
			}
			bom.put("subList", tempList);
			if(tempList.size()==1){//只有一个供应商，则默认该供应商数量和领料数量一样
				tempList.get(0).setCarNum(bom.get("CAR_PLAN_NUM")==null?0l:Long.parseLong(bom.get("CAR_PLAN_NUM").toString()));
			}
		}
		
	}
	
//		getRequest().setAttribute("carPlanId", carPlanId);
		
		for(Map bom:newBomList){
			bomList.add(bom);
		}
		
		getRequest().setAttribute("bomList", bomList);
		String[]  aufnrArbpls =zgTcarplanExBo.getAufnrsByorderBomIds(bomList);
		getRequest().setAttribute("aufnrs", aufnrArbpls[0]);
		getRequest().setAttribute("arbpls", aufnrArbpls[1]);
		
		getRequest().setAttribute("kdauf", aufnrArbpls[2]);
		getRequest().setAttribute("kdpos", aufnrArbpls[3]);
		getRequest().setAttribute("maktx1", aufnrArbpls[4]);
		
		//根据领料计划分组ID
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByGroupId(groupId);
		getRequest().setAttribute("orderList", orderList);
		getRequest().setAttribute("pageRequest", pageRequest);
		return CAR_BOM_LIST;
	}
	
	/**
	 * 设置开始和结束日期 开始日期默认为前两天，结束日期默认为后五天
	 * @param req
	 */
	public void defultDateSet(HttpServletRequest req){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr=dateFormat.format(calendar.getTime());
		calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, 5);
		String endDateStr=dateFormat.format(calendar.getTime());
		req.setAttribute("pxdat_End", endDateStr);
		req.setAttribute("pxdat_Start", startDateStr);
	}

	public FwEmployeeBo getFwEmployeeBo() {
		return fwEmployeeBo;
	}

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}

	public ZgTcarbomBo getZgTcarbomBo() {
		return zgTcarbomBo;
	}

	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}

	public ZgTcarplanBo getZgTcarplanBo() {
		return zgTcarplanBo;
	}
	
	/**
	 * 预装 预焊 总装 领料计划提交
	 * @throws IOException
	 */
	public void carPlanSubmit() throws IOException {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String carPlanId = (java.lang.String)params.get("id");
			zgTcarplanExBo.carPlanSubmitByID(carPlanId);
		}
		promtAndQuery("操作成功");
	}
	
	/**
	 * 仓库领料提交
	 * @throws IOException
	 */
	public void storagePlanSubmit() throws IOException {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String carPlanId = (java.lang.String)params.get("id");
			zgTcarplanExBo.storagePlanSubmitById(carPlanId,"","ALL");
		}
		promtAndQuery("操作成功");
	}
	
	@SuppressWarnings("unchecked")
	public String viewCarPlan(){
		String carPlanId = this.getRequest().getParameter("carPlanId");
		ZgTcarplan zgTcarplan = this.zgTcarplanBo.getById(carPlanId);
		Map<String,List<Map>> map = new HashMap<String,List<Map>>();
		List<Map> list = zgTcarbomExBo.findBomByCarPlanId(carPlanId);
		for(Map bom : list) {
			String idnrk = IbatisDAOHelper.getStringValue(bom, "IDNRK");
			List<Map> boms = map.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<Map>();
			}
			boms.add(bom);
			map.put(idnrk, boms);
		}
		this.getRequest().setAttribute("zgTcarplan", zgTcarplan);
		this.getRequest().setAttribute("orderboms", map);
		return VIEW_CARPLAN;
	}

	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}


	public void setZgTcarbomExBo(ZgTcarbomExBo zgTcarbomExBo) {
		this.zgTcarbomExBo = zgTcarbomExBo;
	}

	public String getOverTime() {
		return zgTorderbomExBo.findOverTimeValue();
	}
	
	/**
	 * 查询超时领料设置参数
	 * @return
	 */
	public String  toSetOverTimeUI(){
		// 第一步 查询数据
		String overTime=zgTorderbomExBo.findOverTimeValue();
		// 第二步 数据加载到页面
		getRequest().setAttribute("overTime", overTime);
		//第三步 进入页面
		return SET_OVERTIME_JSP;
	}
	
	/**
	 * 保存超时领料设置参数
	 * @return
	 */
	public String saveOverTime(){
		//第一步 获取页面数据
		String overTimes=getRequest().getParameter("overTimes").toString();
		//第二步 更新数据库
		zgTorderbomExBo.saveOverTimeValue(overTimes);
		getRequest().setAttribute("msg", "操作成功");
		//第三步 返回页面
		return SET_OVERTIME_JSP;
	}

	public String getCarPlanId() {
		return carPlanId;
	}

	public void setCarPlanId(String carPlanId) {
		this.carPlanId = carPlanId;
	}
	/**
	 * 删除装车计划单以及相应的装车BOM
	 */
	public String deleteCarPlan(){
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTcarplanBo.deleteCarPlan((java.lang.String)params.get("id"));
		}
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTcarplan.BM_CLASS_ID,super.getSessionUserId()));
		getRequest().setAttribute("type", this.getRequest().getParameter("s_type"));
		if(pageRequest.getFilters().get("cuid")!=null && !pageRequest.getFilters().get("cuid").equals("")){
			String cuid = pageRequest.getFilters().get("cuid").toString();
			pageRequest.getFilters().put("cuid", "%"+cuid+"%");
		}
		Page page = zgTcarplanExBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}

	public String getOrderPlanType() {
		return orderPlanType;
	}

	public void setOrderPlanType(String orderPlanType) {
		this.orderPlanType = orderPlanType;
	}

	public void setZgTorderExBo(ZgTorderExBo zgTorderExBo) {
		this.zgTorderExBo = zgTorderExBo;
	}

	public ZgTorderPlanGroupExBo getZgTorderPlanGroupExBo() {
		return zgTorderPlanGroupExBo;
	}

	public void setZgTorderPlanGroupExBo(ZgTorderPlanGroupExBo zgTorderPlanGroupExBo) {
		this.zgTorderPlanGroupExBo = zgTorderPlanGroupExBo;
	}
	
	public String findArbplList(){
		String type=getRequest().getParameter("type");
		String orderPlanType=getRequest().getParameter("orderPlanType");
		getRequest().setAttribute("orderPlanType", orderPlanType);
		getRequest().setAttribute("type", type);
		String defaulArbpl=getRequest().getParameter("defaulArbpl");
		getRequest().setAttribute("onload", getRequest().getParameter("onload"));
		getRequest().setAttribute("defaulArbpl",defaulArbpl);
		FwOrganization fwOrganization=new FwOrganization();
		fwOrganization.setType(Constants.orgType.arbpl.value());
		List<FwOrganization> arbpList = fwOrganizationBo.findByProperty(fwOrganization, "t0_CUID", true);
		getRequest().setAttribute("arbpList", arbpList);
		getRequest().setAttribute("arbplNum", arbpList.size());
		

		
	    return ARBPLIST;
	}
	
	public String viewCarPlanList(){
		String groupId=getRequest().getParameter("groupId");
		String bomCuids=getRequest().getParameter("bomCuids");
		String carCode=getRequest().getParameter("carCode");
		String planType=getRequest().getParameter("planType");
		String lgort=getRequest().getParameter("lgort");
		String lgortName=getRequest().getParameter("lgortName");
		String advance=getRequest().getParameter("advance");
		
		List<Map> carPlanList=zgTcarplanExBo.getCarPlanByUserId(getSessionOperatorId(),planType,"");

		getRequest().setAttribute("carPlanList", carPlanList);
		
		getRequest().setAttribute("groupId", groupId);
		getRequest().setAttribute("bomCuids", bomCuids);
		getRequest().setAttribute("planType", planType);
		getRequest().setAttribute("advance", advance);
		getRequest().setAttribute("carCode", carCode);
		getRequest().setAttribute("lgort", lgort);
		getRequest().setAttribute("lgortName", lgortName);
		
		return VIEW_CARPLAN_LIST;
	}
	
	/**
	 * 点击装车处理事件
	 * 处理逻辑：
	 * 	1.先装车该领料员原来的装车计划bom列表
	 *  2.追加上目前的装车bom
	 *  
	 *  modify by wengq:
	 *  	改变装车方式，原来是一次只能做一个仓库的装车计划，不同仓库不让追加
	 *  　　　　　改成：选择不同仓库物料的时候，另外再创建一个装车计划
	 * @return
	 */
	public String loadCar() {
		String groupId=getRequest().getParameter("groupId");
		String bomCuids=getRequest().getParameter("bomCuids");
		String carCode=getRequest().getParameter("carCode");
		String carId=getRequest().getParameter("carId");
		String planType=getRequest().getParameter("planType");
		String lgort=getRequest().getParameter("lgort");
		String lgortName=getRequest().getParameter("lgortName");
		String advance=getRequest().getParameter("advance");
		
		
		
		if(bomCuids.endsWith(",")){
			bomCuids=bomCuids.substring(0,bomCuids.length()-1);
		}
		
		getRequest().setAttribute("groupId", groupId);
		getRequest().setAttribute("bomCuids", bomCuids);
		getRequest().setAttribute("planType", planType);
		getRequest().setAttribute("advance", advance);
		getRequest().setAttribute("groupId", groupId);
		
		List<Map> carPlanList=null;
		//carPlanId不为空时为加载某个装车计划，为空时为追加或是创建装车计划
		if(StringHelper.isEmpty(carPlanId)){
			//查看该领料员原来的计划装车单
			carPlanList=zgTcarplanExBo.getCarPlanByUserId(getSessionOperatorId(),planType,lgort);
		}else {
			carPlanList=zgTcarplanExBo.getCarPlanByCarPlanId(carPlanId);
		}
		
		
		//如果该领料员原来已经有装车计划，则用原来的信息
		if(carPlanList.size()>0){
			Map carPlan=carPlanList.get(0);
			carPlanId=carPlan.get("CUID").toString();
			carCode=carPlan.get("CODE").toString();
			carId=carPlan.get("CARID").toString();
			lgort=carPlan.get("LGORT").toString();
			lgortName=carPlan.get("LGORTNAME").toString();
			
			getRequest().setAttribute("hasCar", "1");
		}else {
			carPlanId =zgTcarplanExBo.getSysGuid();
		}
		
		//判断该领料是否暂停
		String pause=zgTcarplanExBo.checkPlanHasPause(carPlanId);
		getRequest().setAttribute("pause", pause);
		
		getRequest().setAttribute("carId", carId);
		getRequest().setAttribute("lgort", lgort);
		getRequest().setAttribute("lgortName", lgortName);
		
		getRequest().setAttribute("carCode", carCode);
		
		return CAR_BOM;
	}
	
	/**
	 * 点击装车处理事件
	 * 处理逻辑：
	 * 	1.先装车该领料员原来的装车计划bom列表
	 *  2.追加上目前的装车bom
	 * @return
	 */
	public String loadCarList() {

		Long startLong=System.currentTimeMillis();
		PageRequest<Map> pageRequest =newPageRequest(DEFAULT_SORT_COLUMNS);
		
		pageRequest.getFilters().put("operatorId", getSessionOperatorId());
		
		String groupId=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "groupId");
		String bomCuids=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "bomCuids","");
		String planType=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "planType");
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort");
		
		
		//根据领料计划分组ID
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByGroupId(groupId);
		getRequest().setAttribute("orderList", orderList);
		
		if(bomCuids.endsWith(",")){
			bomCuids=bomCuids.substring(0,bomCuids.length()-1);
		}
		
		
		String[] bomIdArr=bomCuids.split(",");
		List<String> listBomId=new ArrayList<String>();
		for(String bomId:bomIdArr){
			listBomId.add(bomId);
		}
		
		//1.该领料员原来的装车计划bom列表
		List<Map> bomList=zgTcarplanExBo.getBomLIstByUserId(planType,getSessionOperatorId(),lgort);
		
		//获取本次追加的bom列表 
		List<Map> newBomList= new ArrayList<Map>();
		if(!StringHelper.isEmpty(bomCuids)){
			
			Object object=getSession().getAttribute(getSessionOperatorId()+"_matkl");
			String carMatkls=object==null?"null":object.toString();
			pageRequest.getFilters().put("carMatkls", carMatkls);
			
			newBomList=zgTorderbomExBo.getBomListByGroupId(pageRequest);
			
		}
		
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
				bom.put("subList", tempList);
			}
			
		}
		
		
		
		
		
		
		Long carEdNum=0l;//已装车数量
		//if(bomList.size()==0){//原来装车bom为空时，则计算目前新添加的bom的装车数量
			for(Map bom:newBomList){
//				if(bomCuids.contains(IbatisDAOHelper.getStringValue(bom, "ORDER_BOM_ID"))){//只计算本次点击的bom默认装车数量
//					Long carCount=Long.parseLong(bom.get("CARCOUNT").toString());//每车可以装车 的数量
//					Long maxValue=Long.parseLong(bom.get("MAX_VALUE").toString());//该物料目前可以装车的数量
//					if(carCount-(maxValue+carEdNum)>=0){
//						bom.put("CAR_PLAN_NUM", maxValue);
//						carEdNum=carEdNum+maxValue;
//					}else if(carCount-carEdNum>0) {
//						bom.put("CAR_PLAN_NUM", carCount-carEdNum);
//						carEdNum=carEdNum+carCount-carEdNum;
//					}
//				}
				
				//modify by wengqin 20110516 0903 所有物料默认装车规格装车数量
				Long carCount=Long.parseLong(bom.get("CARCOUNT").toString());//每车可以装车 的数量
				Long maxValue=Long.parseLong(bom.get("MAX_VALUE").toString());//该物料目前可以装车的数量
				if(maxValue<=carCount){
					bom.put("CAR_PLAN_NUM", maxValue);
				}else {
					bom.put("CAR_PLAN_NUM", carCount);
				}
				
			}
		//}
		if(bomList.size()>0) {
			carPlanId=bomList.get(0).get("CAR_PLAN_ID").toString();
		}
		
		//获取相应的供应商信息
		if(newBomList.size()>0){
			String aufnrs="";
			String idnrks ="";
			for(Map bom:newBomList){
				aufnrs=aufnrs+bom.get("AUFNR").toString()+"','";
				idnrks=idnrks+bom.get("IDNRK").toString()+"','";
			}
			
			aufnrs=aufnrs.substring(0,aufnrs.length()-3);
			idnrks=idnrks.substring(0,idnrks.length()-3);	
			
			//获取供应商信息
			List<ZgTcarbomSuppliers> suppliersLst=zgTorderSuppliersBo.getBomSuppliersListByAufnrIdnrk(aufnrs,idnrks);
			
			for(Map bom:newBomList){
				List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
				String aufnr=bom.get("AUFNR").toString();
				String idnrk=bom.get("IDNRK").toString();
				for(ZgTcarbomSuppliers sup:suppliersLst){
					if(aufnr.equals(sup.getAufnr())&&idnrk.equals(sup.getIdnrk())){
						tempList.add(sup);
					}
				}
				
				if(tempList.size()==1){//只有一个供应商，则默认该供应商数量和领料数量一样
					tempList.get(0).setCarNum(bom.get("CAR_PLAN_NUM")==null?0l:Long.parseLong(bom.get("CAR_PLAN_NUM").toString()));
				}
				bom.put("subList", tempList);
			}
			
		}
		
		
		
		for(Map bom:newBomList){
			bomList.add(bom);
		}
		
		//获取物料所对应的订单
		String[]  aufnrArbpls =zgTcarplanExBo.getAufnrsByorderBomIds(bomList);
		
		
		bomCuids="";
		for(Map bom:newBomList){
			bomCuids=bomCuids+IbatisDAOHelper.getStringValue(bom, "ORDER_BOM_ID")+",";
		}
		if(bomCuids.endsWith(",")){
			bomCuids=bomCuids.substring(0,bomCuids.length()-1);
		}
		
		Long endLong=System.currentTimeMillis();
		getRequest().setAttribute("aufnrs", aufnrArbpls[0]);
		getRequest().setAttribute("arbpls", aufnrArbpls[1]);
		getRequest().setAttribute("kdauf", aufnrArbpls[2]);
		getRequest().setAttribute("kdpos", aufnrArbpls[3]);
		getRequest().setAttribute("maktx1", aufnrArbpls[4]);
		getRequest().setAttribute("bomList", bomList);
		pageRequest.getFilters().put("bomCuids", bomCuids);
		getRequest().setAttribute("pageRequest", pageRequest);
		return CAR_BOM_LIST;
	}

	public List<ZgTcarbomEx> getCarbomList() {
		return carbomList;
	}

	public void setCarbomList(List<ZgTcarbomEx> carbomList) {
		this.carbomList = carbomList;
	}
	
	public static void main(String[] args) {
		String string="BCE8F56A73F74A29B0916B82434EE6EE,0A06AA16024B4EE9956D80EF50FBC5A2,290A63EBD1494BAF9F0912F49D7F3CE4";
		string=string.replace("0A06AA16024B4EE9956D80EF50FBC5A2", "");
		System.out.println(string);	
	}

	public void setZgTvirtualorgBo(ZgTvirtualorgBo zgTvirtualorgBo) {
		this.zgTvirtualorgBo = zgTvirtualorgBo;
	}

	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}

	public void setZgTcarbomSuppliersBo(ZgTcarbomSuppliersBo zgTcarbomSuppliersBo) {
		this.zgTcarbomSuppliersBo = zgTcarbomSuppliersBo;
	}

	public void setZgTorderSuppliersBo(ZgTorderSuppliersBo zgTorderSuppliersBo) {
		this.zgTorderSuppliersBo = zgTorderSuppliersBo;
	}
	
	public String bomPrint(){
//		List<Map> list=(List<Map>) getRequest().getAttribute("bomList");
		return  CAR_BOM_LIST_PRINT;
	}
	
	/** 进入查询页面 */
	public String query2() {
		
		CommonService.defultDateSet(getRequest(), "createDate_start", "createDate_end");
		
		//type为装车计划的属性， 1为装车 2为领料
		String type=this.getRequest().getParameter("type");
		String orderPlanType=this.getRequest().getParameter("orderPlanType");
		getRequest().setAttribute("type",type);
		getRequest().setAttribute("orderPlanType", orderPlanType);
		
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		
		return QUERY_STORAGE_JSP2;
		
	
		
	}
	
	/**
	 * 仓库领料计划
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list2(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTcarplan.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTcarplanBo.findByPageRequest2(pageRequest);
		savePage(page,pageRequest);
		
		Map<String, Object> params=pageRequest.getFilters();
		String orderPlanType=params.get("orderPlanType").toString();
		getRequest().setAttribute("orderPlanType", orderPlanType);
		getRequest().setAttribute("type", this.getRequest().getParameter("s_type"));
		
		return LIST_STORAGE_JSP2;
	}
	
	
	
}
