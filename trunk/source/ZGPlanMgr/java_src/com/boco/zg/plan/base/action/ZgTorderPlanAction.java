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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.EnumModel;
import javacommon.base.service.IVmModelBo;
import javacommon.util.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.base.service.FwOrganizationManagerBo;
import com.boco.frame.sys.service.FwOrganizationExBo;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanGroupBo;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class ZgTorderPlanAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTorderPlan/query_ZgTorderPlan.jsp";
	protected static final String QUERY_JSP_FORBATCH = "/zg/plan/ZgTorderPlan/query_ZgTorderPlan_ForBatch.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTorderPlan/list_ZgTorderPlan.jsp";
	private static final String LIST_JSP1 = "/zg/plan/ZgTorderPlan/list_ZgTorderPlan1.jsp";
	private static final String LIST_JSP_FOR_PSBH_CHANGE = "/zg/plan/ZgTorderPlan/list_ZgTorderPlan_psbh.jsp";
	protected static final String LIST_JSP_BATCH= "/zg/plan/ZgTorderPlan/list_ZgTorderPlan_ForBatch.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTorderPlan/create_ZgTorderPlan.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTorderPlan/edit_ZgTorderPlan.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTorderPlan/show_ZgTorderPlan.jsp";
	protected static final String BOM_LIST = "/zg/plan/ZgTorderPlanbom/bom_list1.jsp";

	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderPlan/list.do";
	private static final String ARBPLIST = "/zg/plan/ZgTorderPlan/arbpl_Menu.jsp";
	private static final String QUERY_JSP1 = "/zg/plan/ZgTorderPlan/query_ZgTorderPlan1.jsp";
	private static final String BOM_LIST1 = "/zg/plan/ZgTorderPlanbom/bom_list2.jsp";


	private static final String TOADDPLANZBZ = "/zg/plan/ZgTorderPlan/add_plan_zbz.jsp";
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTorderPlanExBo zgTorderPlanExBo;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTorderPlanEx zgTorderPlanEx;
	
	private ZgTorderExBo zgTorderExBo;
	
	private ZgTorderPlanGroupBo zgTorderPlanGroupBo;
	
	private ZgTorderPlan zgTorderPlan;
	
	java.lang.String id = null;
	
	private String[] items;
	
	private String orderPlanId;
	
	//领料组角色
	private List<FwOrganization> roles;
	
	private FwOrganizationBo fwOrganizationBo;
	
	private FwOrganizationManagerBo fwOrganizationManagerBo;
	
	private FwOrganizationExBo fwOrganizationExBo;
	

	public List<FwOrganization> getRoles() {
		if(roles == null) {
			FwOrganization fwOrganization=new FwOrganization();
			fwOrganization.setType("4");
			
			roles = fwOrganizationBo.findByProperty(fwOrganization, "t0_LABEL_CN", true);
		}
		return roles;
	}
	
	private List<EnumModel> times;
	public List<TmdEnumevalue> getTimes() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		return service.getEnumValue("TIME");
	}
	
	
	
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderPlanEx = new ZgTorderPlanEx();
			zgTorderPlan=new ZgTorderPlan();
		} else {
			zgTorderPlanEx = (ZgTorderPlanEx)zgTorderPlanExBo.getById(id);
			zgTorderPlan=(ZgTorderPlan)zgTorderPlanBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanExBo(ZgTorderPlanExBo bo) {
		this.zgTorderPlanExBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderPlan;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}

	public String getOrderPlanId() {
		return orderPlanId;
	}

	public void setOrderPlanId(String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}

	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}



	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}
	

	public void setZgTorderPlanGroupBo(ZgTorderPlanGroupBo zgTorderPlanGroupBo) {
		this.zgTorderPlanGroupBo = zgTorderPlanGroupBo;
	}



	public void setZgTorderExBo(ZgTorderExBo zgTorderExBo) {
		this.zgTorderExBo = zgTorderExBo;
	}

	/** modify by wengqin 2010/08/17 17:24
	 * 加上生产厂的排序时间功能（实际上生产时间不是按照自然时间0~24点来处理，而是需要生厂线长手动切换时间，才算是跳到第二天，如果生产线得不触发切换时间，则时间上会一直停留在某一天）
	 * 每个生产厂都有自己独立的排序时间，所以在查询列表里面生产线线长一次只能查询一个生产厂，先判断该线长拥有几个生产厂的权限，就显示几个出来
	  */
	public String query() {
		String plant=this.getRequest().getParameter("plant");
		String type = this.getRequest().getParameter("type");
		String viewModel = this.getRequest().getParameter("viewModel");
		getRequest().setAttribute("viewModel", viewModel);
		String arbpl="";
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		
		
		if(Constants.sorft.ABE.value().equals(type)){//是总装的话，加上生产线参数
			arbpl=this.getRequest().getParameter("arbpl");
			getRequest().setAttribute("arbpl", arbpl);
		}
		
		                                             
		pageRequest.getFilters().put("planType", type);
		getRequest().setAttribute("pageRequest", pageRequest);
	
		List<Date> dateList=getDateList(null);
		Date curDate=new Date();
		
		List<FwOrganizationManager> mangerOrgList=null;
		mangerOrgList=(List<FwOrganizationManager>) getSession().getAttribute("mangerOrgList"+type);
		//List<Date> dateList=null;
		String defaultOrgId="";
		if(mangerOrgList==null){
			//查询登线长负责的生产厂
			mangerOrgList=fwOrganizationManagerBo.getManagerOrgListByOperatorSorftType(getSessionOperatorId(),type);
			if(mangerOrgList.size()>0){
				defaultOrgId=(mangerOrgList.get(0)).getOrgId();
				dateList=getDateList((mangerOrgList.get(0)).getExtend2());
				 
				getSession().setAttribute("mangerOrgList"+type, mangerOrgList);
				getSession().setAttribute("defaultOrgId",defaultOrgId);
			}
		
		}else {
			if(StringHelper.isEmpty(plant)){//没有选择生产厂，则默认为原来的生产厂,如果是从预装的菜单跳到总装，则原来默认的生产厂是C02,总装的生产厂列表是C01,C04,则这是以总装生产厂的第一个作为默认生产厂
				defaultOrgId=getSession().getAttribute("defaultOrgId")==null?"":getSession().getAttribute("defaultOrgId").toString();
				if(mangerOrgList.size()>0){
					boolean flag=false;
					for(FwOrganizationManager org:mangerOrgList){//从负责生产厂中找上次默认的生产厂，如果没有找到，则默认为负责生产厂列表中的第一个
						if(org.getOrgId().equals(defaultOrgId)){
							//获取该生产厂目前的排序日期
							curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
							dateList=getDateList(curDate);
							flag=true;
							break;
						}
					}
					if(!flag){
						defaultOrgId=(mangerOrgList.get(0)).getOrgId();
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
						dateList=getDateList(curDate);
						getSession().setAttribute("defaultOrgId",defaultOrgId);
					}
				}
			}else{//如果用户选择了某个生产厂
				for(FwOrganizationManager org:mangerOrgList){
					if(org.getOrgId().equals(plant)){
						defaultOrgId=plant;
						getSession().setAttribute("defaultOrgId",plant);
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
						dateList=getDateList(curDate);
						break;
					}
				}
			}
			
			
		}
		
		
		
		//临时添加的查看之前的领料计划功能
		String viewDate=getRequest().getParameter("viewDate");
		getRequest().setAttribute("viewDate", viewDate);
		if(!StringHelper.isEmpty(viewDate)){
			dateList=getDateList(TimeFormatHelper.convertDate(viewDate, TimeFormatHelper.DATE_FORMAT));
		}
		
		getRequest().setAttribute("curDate", dateList.get(0));
		getRequest().setAttribute("dateList", dateList);
		
		int totalCount=zgTorderPlanExBo.getCurDateTotalOrderCount(type,arbpl,pageRequest.getFilters().get("operatorId").toString(),dateList.get(0),defaultOrgId,viewDate);
		int finishCount=zgTorderPlanExBo.getCurDateFinishedOrderCount(type,arbpl,pageRequest.getFilters().get("operatorId").toString(),dateList.get(0),defaultOrgId);
		getRequest().setAttribute("totalCount", totalCount);
		getRequest().setAttribute("finishCount", finishCount);
		

		String view=getRequest().getParameter("view");
		if("order".equals(view)){
			return QUERY_JSP1;	
		}else {
			return QUERY_JSP;
		}
		
		
	}
	
	/** 总装查询页面
	 * 
	 * @return
	 */
	public String query1() {
		String plant=this.getRequest().getParameter("plant");
		String type = this.getRequest().getParameter("type");
		String arbpl="";
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String viewModel = this.getRequest().getParameter("viewModel");
		getRequest().setAttribute("viewModel", viewModel);
		
//		if(Constants.sorft.ABE.value().equals(type)){//是总装的话，加上生产线参数
			arbpl=this.getRequest().getParameter("arbpl");
			getRequest().setAttribute("arbpl", arbpl);
//		}
		
		                                             
		pageRequest.getFilters().put("planType", type);
		getRequest().setAttribute("pageRequest", pageRequest);
	
		List<Date> dateList=getDateList(null);
		Date curDate=new Date();
	
		getRequest().setAttribute("view", "arbpl");
		List<FwOrganizationManager> mangerOrgList=null;
		mangerOrgList=(List<FwOrganizationManager>) getSession().getAttribute("mangerOrgList"+type);
		//List<Date> dateList=null;
		String defaultOrgId="";
		if(mangerOrgList==null){
			//查询登线长负责的生产厂
			mangerOrgList=fwOrganizationManagerBo.getManagerOrgListByOperatorSorftType(getSessionOperatorId(),type);
			if(mangerOrgList.size()>0){
				defaultOrgId=(mangerOrgList.get(0)).getOrgId();
				dateList=getDateList((mangerOrgList.get(0)).getExtend2());
				 
				getSession().setAttribute("mangerOrgList"+type, mangerOrgList);
				getSession().setAttribute("defaultOrgId",defaultOrgId);
			}
		
		}else {
			if(StringHelper.isEmpty(plant)){//没有选择生产厂，则默认为原来的生产厂,如果是从预装的菜单跳到总装，则原来默认的生产厂是C02,总装的生产厂列表是C01,C04,则这是以总装生产厂的第一个作为默认生产厂
				defaultOrgId=getSession().getAttribute("defaultOrgId")==null?"":getSession().getAttribute("defaultOrgId").toString();
				if(mangerOrgList.size()>0){
					boolean flag=false;
					for(FwOrganizationManager org:mangerOrgList){//从负责生产厂中找上次默认的生产厂，如果没有找到，则默认为负责生产厂列表中的第一个
						if(org.getOrgId().equals(defaultOrgId)){
							curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
							dateList=getDateList(curDate);
							flag=true;
							break;
						}
					}
					if(!flag){
						defaultOrgId=(mangerOrgList.get(0)).getOrgId();
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
						dateList=getDateList(curDate);
						getSession().setAttribute("defaultOrgId",defaultOrgId);
					}
				}
			}else{
				for(FwOrganizationManager org:mangerOrgList){
					if(org.getOrgId().equals(plant)){
						defaultOrgId=plant;
						getSession().setAttribute("defaultOrgId",plant);
						curDate=fwOrganizationExBo.getPxdateByOrgId(defaultOrgId);
						dateList=getDateList(curDate);
						break;
					}
				}
			}
			
			
		}
		
		//临时添加的查看之前的领料计划功能
		String viewDate=getRequest().getParameter("viewDate");
		getRequest().setAttribute("viewDate", viewDate);
		if(!StringHelper.isEmpty(viewDate)){
			dateList=getDateList(TimeFormatHelper.convertDate(viewDate, TimeFormatHelper.DATE_FORMAT));
		}
		
		getRequest().setAttribute("curDate", dateList.get(0));
		getRequest().setAttribute("dateList", dateList);
		
		int totalCount=zgTorderPlanExBo.getCurDateTotalOrderCount(type,arbpl,pageRequest.getFilters().get("operatorId").toString(),dateList.get(0),defaultOrgId,viewDate);
		int finishCount=zgTorderPlanExBo.getCurDateFinishedOrderCount(type,arbpl,pageRequest.getFilters().get("operatorId").toString(),dateList.get(0),defaultOrgId);
		getRequest().setAttribute("totalCount", totalCount);
		getRequest().setAttribute("finishCount", finishCount);
		
		return QUERY_JSP;
		
		
	}
	
	
	/**
	 * 获取今天以后五天的日期列表
	 * @return
	 */
	private List<Date> getDateList(Date date) {
		List<Date>  dateList=new ArrayList<Date>();
		Calendar calendar=Calendar.getInstance();
		if(date==null){
			date=calendar.getTime();
		}
		calendar.setTime(date);
		dateList.add(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		dateList.add(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		dateList.add(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		dateList.add(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		dateList.add(calendar.getTime());
		
		return dateList;
	}



	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String isPx=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "px", "");
		String viewModel = this.getRequest().getParameter("viewModel");
		getRequest().setAttribute("viewModel", viewModel);
		String plant=getSession().getAttribute("defaultOrgId")==null?"":getSession().getAttribute("defaultOrgId").toString();
		String isCurDay= pageRequest.getFilters().get("isCurDay")==null?"1":pageRequest.getFilters().get("isCurDay").toString();
		pageRequest.getFilters().put("plant", plant);
		String planType=pageRequest.getFilters().get("planType").toString();
		String pcDate=pageRequest.getFilters().get("pxDate").toString();
		
		List<ZgTorderPlanGroup> groupList =null;
		List<ZgTorderPlanEx> planList=null;
		if(!StringHelper.isEmpty(isPx)){//排序调整
			pageRequest.getFilters().put("psbhChange", "psbhChange");
			groupList =zgTorderPlanGroupBo.findGroupByList(pageRequest);
			//获取领料计划列表
			planList = zgTorderPlanExBo.findZgTorderPlanList(planType,pcDate,pageRequest.getFilters().get("operatorId").toString(),"",plant,isCurDay,pageRequest);
			
		}else {
			groupList =zgTorderPlanGroupBo.findGroupByList(pageRequest);
			//获取领料计划列表
			planList = zgTorderPlanExBo.findZgTorderPlanList(planType,pcDate,pageRequest.getFilters().get("operatorId").toString(),"",plant,isCurDay,pageRequest);
			
		}
		
		
		
		
		LinkedHashMap<ZgTorderPlanGroup,List<ZgTorderPlanEx>> resultMap =new  LinkedHashMap<ZgTorderPlanGroup,List<ZgTorderPlanEx>>();
		
		//因为要获取生产线一列，又要按生产线排序，所以sql中查询出来的group会存在重复的，这里的目的是去掉重复的生产组
		for(int i=0;i<groupList.size();i++){
			ZgTorderPlanGroup temp1=groupList.get(i);
			for(int j=i+1;j<groupList.size();j++){
				ZgTorderPlanGroup temp2=groupList.get(j);
				if(temp1.getCuid().equals(temp2.getCuid())){
					temp1.setNum(temp1.getNum()+1);
					temp2.setNum(temp1.getNum());
				}

			}
		}
		
		for (ZgTorderPlanGroup group:groupList) {
			List<ZgTorderPlanEx> tempList=new ArrayList<ZgTorderPlanEx>();
			for(ZgTorderPlanEx planEx:planList){
				if(planEx.getGroupId().equals(group.getCuid())){
					tempList.add(planEx);
				}
			}
			resultMap.put(group, tempList);
		}
		
		this.getRequest().setAttribute("resultMap", resultMap);
		getRequest().setAttribute("pageRequest", pageRequest);
		
		
		if(!StringHelper.isEmpty(isPx)){//排序调整
			return LIST_JSP_FOR_PSBH_CHANGE;
		}else {
			return LIST_JSP;
		}

		
	}
	
	/**
	 * 总装
	 * @return
	 */
	public String list1() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String viewModel = this.getRequest().getParameter("viewModel");
		getRequest().setAttribute("viewModel", viewModel);
		String plant=getSession().getAttribute("defaultOrgId")==null?"":getSession().getAttribute("defaultOrgId").toString();
		pageRequest.getFilters().put("plant", plant);
		String isCurDay= pageRequest.getFilters().get("isCurDay")==null?"1":pageRequest.getFilters().get("isCurDay").toString();

		
		String planType=pageRequest.getFilters().get("planType").toString();
		String pxDate=pageRequest.getFilters().get("pxDate").toString();
		
		//获取领料计划列表
		List<ZgTorderPlanEx> planList = zgTorderPlanExBo.findZgTorderPlanList(planType,pxDate,pageRequest.getFilters().get("operatorId").toString(),IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "arbpl"),plant,isCurDay,pageRequest);
		
		this.getRequest().setAttribute("planList", planList);
		getRequest().setAttribute("pageRequest", pageRequest);
		return LIST_JSP1;
	}
	
	
	
	/** 执行搜索 */
	public String listOrder() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String type = this.getRequest().getParameter("type");
		String plant=getSession().getAttribute("defaultOrgId")==null?"":getSession().getAttribute("defaultOrgId").toString();
		String isCurDay= pageRequest.getFilters().get("isCurDay")==null?"1":pageRequest.getFilters().get("isCurDay").toString();

//		Page page= zgTorderPlanExBo.findByPageRequest(pageRequest);
		
		//获取领料计划分组列表
//		Page page=zgTorderPlanGroupBo.findByPageRequest(pageRequest);
//		List<ZgTorderPlanGroup> groupList=page.getResult();
		
		List<ZgTorderPlanGroup> groupList =zgTorderPlanGroupBo.findGroupByList(pageRequest);
		
		String planType=pageRequest.getFilters().get("planType").toString();
		String pcDate=pageRequest.getFilters().get("pcDate").toString();
		
		//获取领料计划列表
		List<ZgTorderPlanEx> planList = zgTorderPlanExBo.findZgTorderPlanList(planType,pcDate,pageRequest.getFilters().get("operatorId").toString(),"",plant,isCurDay,pageRequest);
		
		LinkedHashMap<ZgTorderPlanGroup,List<ZgTorderPlanEx>> resultMap =new  LinkedHashMap<ZgTorderPlanGroup,List<ZgTorderPlanEx>>();
		
		for (ZgTorderPlanGroup group:groupList) {
			List<ZgTorderPlanEx> tempList=new ArrayList<ZgTorderPlanEx>();
			for(ZgTorderPlanEx planEx:planList){
				if(planEx.getGroupId().equals(group.getCuid())){
					tempList.add(planEx);
				}
			}
			resultMap.put(group, tempList);
		}
		
		this.getRequest().setAttribute("resultMap", resultMap);
		
		
		
//		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	public void submitOrderPlan() throws IOException {
		String orderPlanId = this.getRequest().getParameter("orderPlanId");
		String orderPlanIds[]=orderPlanId.split(",");
		for(int i=0;i<orderPlanIds.length;i++){
			zgTorderPlanExBo.submitOrderPlan(orderPlanIds[i]);
			
			//更新该领料计划的领料组 领料人
			zgTorderPlanExBo.updateOrderDeptId(orderPlanIds[i]);
		}
		forwardQuery("操作成功");
	}
	
	/** 进入查询页面 批量领料计划*/
	public String queryForBatch() {
		String type = this.getRequest().getParameter("type");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("planType", type);
		getRequest().setAttribute("pageRequest", pageRequest);
		
//		defultDateSet(getRequest());
		return QUERY_JSP_FORBATCH;
	}
	
	/** 执行搜索 批量领料计划 */
	public String listForBatch() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		
		Page page = zgTorderPlanExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP_BATCH;
	}
	
	/**
	 * 方法描述：保存批量领料计划
	 * 处理逻辑：1 遍历session中的bom组件，找出其中修改标记为true的组件更新领料计划bom表中
	 *         2 更新领料计划表状态为“保存”
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("unchecked")
	public String saveOrderPlanForBatch() throws ServletException, IOException {
		//1 遍历session中的bom组件，找出其中修改标记为true的组件更新领料计划bom表中
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		synSessionBomToDataBase(list);
		
		//2 更新领料计划表状态为“保存”
		zgTorderPlan.setState(Constants.OrderPlanStatus.SAVE.value());
		zgTorderPlanExBo.saveOrderPlan(zgTorderPlan);
		return SUCCESS;
	}

	/**
	 * 将session中的bom组件同步到数据库的领料计划表中
	 * @param list
	 */
	private void synSessionBomToDataBase(List<ZgTorderPlanbomEx> list) {
		for (ZgTorderPlanbomEx obj : list) {
			if(obj.getIsModity()){//把标记为修改过的bom组件更新到数据库中
				if(obj.getIsDel()==true){//删除表中的该组件
					zgTorderPlanbomExBo.removeByIdAndBomId(obj.getCuid());
				}else {
					int row=zgTorderPlanbomExBo.updateOrderPlanforBatch(obj);
					if(row<=0){//更新影响的条数小于０  则说明原来的数据中没有该组件，插入该组件
						zgTorderPlanbomExBo.saveOrderPlanforBatch(obj);
					}
				}
			}
		}
	}
	
	/**
	 * 方法描述：提交批量领料计划
	 * 处理逻辑：：1 遍历session中的bom组件,找出其中修改标记为true的组件更新领料计划bom表中
	 * 			 2 如果该领料计划的状态为“已提交”，则先删除装车计划表中该领料计划的相关装车计划
	 *           3 为该领料计划自动生成装车计划
	 *           4 更新领料计划表状态为“已提交”
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("unchecked")
	public void submitOrderPlanForBatch() throws IOException {
		//1 遍历session中的bom组件，找出其中修改标记为true的组件更新领料计划bom表中
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		synSessionBomToDataBase(list);
		
		//2 如果该领料计划的状态为“已提交”，则先删除装车计划表中该领料计划的相关装车计划
		String orderPlanId = this.getRequest().getParameter("orderPlanId");
		zgTorderPlan=(ZgTorderPlan)zgTorderPlanBo.getById(orderPlanId);
		if(Constants.OrderPlanStatus.SUBMIT.value().equals(zgTorderPlan.getState())){
			zgTorderPlanExBo.deleteCarPlanByOrderPlanId(orderPlanId);
			zgTorderPlanExBo.deleteCarBomByOrderPlanId(orderPlanId);
		}
		
		
		//3 为该领料计划自动生成装车计划     
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		zgTorderPlanExBo.submitOrderPlanForBatch(zgTorderPlan,operatorInfo);
		
		//4 更新领料计划表状态为“已提交”
		zgTorderPlanExBo.updateOrderPlanState(zgTorderPlan.getCuid(),Constants.OrderPlanStatus.SUBMIT.value());
		
		
		promtAndQuery("操作成功");
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
		req.setAttribute("planDate_end", endDateStr);
		req.setAttribute("planDate_start", startDateStr);
	}
	
	public void orderPlanSubmit() throws IOException {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String orderPlanId = (java.lang.String)params.get("id");
			//更新orderbom状态
			zgTorderPlanExBo.updateOrderPlanBomState(orderPlanId,Constants.OrderPlanStatus.SUBMIT.value());
			zgTorderPlanExBo.updateOrderPlanState(orderPlanId,Constants.OrderPlanStatus.SUBMIT.value());
		}
		promtAndQuery("操作成功");
	}
	
	
	/**
	 *  查看详细物料的领料进度
	 * @return
	 */
	public String viewPlanGroupBom(){
		String groupId=this.getRequest().getParameter("groupId");
		
		//获取该分组里面的订单详情信息
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByGroupId(groupId);
		
		getRequest().setAttribute("orderList", orderList);
		

		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByGroupId(groupId);
		Map<String,List<ZgTorderPlanbomEx>> mapList = new HashMap<String,List<ZgTorderPlanbomEx>>();
		for(ZgTorderPlanbomEx bom : list) {
			String idnrk = bom.getIdnrk();
			List<ZgTorderPlanbomEx> boms = mapList.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<ZgTorderPlanbomEx>();
			}
			boms.add(bom);
			mapList.put(idnrk, boms);
		}
		this.getRequest().setAttribute("orderboms", mapList);
		this.getRequest().setAttribute("count", list.size());
		this.getRequest().setAttribute("type", getRequest().getParameter("type"));
		
		
	    return BOM_LIST;
	}
	
	/**
	 *  查看详细物料的领料进度(提前领料的分组)
	 * @return
	 */
	public String viewPlanGroupBomAdvance(){
		String groupId=this.getRequest().getParameter("groupId");
		
		//获取该分组里面的订单详情信息
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByGroupId(groupId);
		
		getRequest().setAttribute("orderList", orderList);
		

		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByGroupIdAdvance(groupId);
		Map<String,List<ZgTorderPlanbomEx>> mapList = new HashMap<String,List<ZgTorderPlanbomEx>>();
		for(ZgTorderPlanbomEx bom : list) {
			String idnrk = bom.getIdnrk();
			List<ZgTorderPlanbomEx> boms = mapList.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<ZgTorderPlanbomEx>();
			}
			boms.add(bom);
			mapList.put(idnrk, boms);
		}
		this.getRequest().setAttribute("orderboms", mapList);
		this.getRequest().setAttribute("count", list.size());
		this.getRequest().setAttribute("type", getRequest().getParameter("type"));
		
		
	    return BOM_LIST;
	}
	
	/**
	 * 查看详细物料的领料进度
	 * @return
	 */
	public String viewOrderPlanBom(){
		
		String orderPlanId=this.getRequest().getParameter("orderPlanId");
	    
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByorderPlanId(orderPlanId);
		
		getRequest().setAttribute("orderList", orderList);
	    
		
		
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByOrderPlanId(orderPlanId);
		Map<String,List<ZgTorderPlanbomEx>> mapList = new HashMap<String,List<ZgTorderPlanbomEx>>();
		for(ZgTorderPlanbomEx bom : list) {
			String idnrk = bom.getIdnrk();
			List<ZgTorderPlanbomEx> boms = mapList.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<ZgTorderPlanbomEx>();
			}
			boms.add(bom);
			mapList.put(idnrk, boms);
		}
		this.getRequest().setAttribute("orderboms", mapList);
		this.getRequest().setAttribute("count", list.size());
		this.getRequest().setAttribute("type", getRequest().getParameter("type"));
		
		
	    return BOM_LIST1;
	}
	
	/**
	 * 查看详细物料的领料进度(提前领料)
	 * @return
	 */
	public String viewOrderPlanBomAdvance(){
		
		String orderPlanId=this.getRequest().getParameter("orderPlanId");
	    
		List<ZgTorder> orderList=zgTorderExBo.getOrderInfoListByorderPlanId(orderPlanId);
		
		getRequest().setAttribute("orderList", orderList);
	    
		
		
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByOrderPlanIdAdvance(orderPlanId);
		Map<String,List<ZgTorderPlanbomEx>> mapList = new HashMap<String,List<ZgTorderPlanbomEx>>();
		for(ZgTorderPlanbomEx bom : list) {
			String idnrk = bom.getIdnrk();
			List<ZgTorderPlanbomEx> boms = mapList.get(idnrk);
			if(boms == null) {
				boms = new ArrayList<ZgTorderPlanbomEx>();
			}
			boms.add(bom);
			mapList.put(idnrk, boms);
		}
		this.getRequest().setAttribute("orderboms", mapList);
		this.getRequest().setAttribute("count", list.size());
		this.getRequest().setAttribute("type", getRequest().getParameter("type"));
		
		
	    return BOM_LIST1;
	}
	
	public String findArbplList(){
		String type=getRequest().getParameter("type");
		getRequest().setAttribute("type", type);
		FwOrganization fwOrganization=new FwOrganization();
		fwOrganization.setType(Constants.orgType.arbpl.value());
		List<FwOrganization> arbpList = fwOrganizationBo.findByProperty(fwOrganization, "t0_CUID", true);
		getRequest().setAttribute("arbpList", arbpList);
		getRequest().setAttribute("arbplNum", arbpList.size());
		String viewModel = this.getRequest().getParameter("viewModel");
		getRequest().setAttribute("viewModel", viewModel);
	    return ARBPLIST;
	}
	
	public String toAddPlanZbz(){
		getRequest().setAttribute("groupIds", getRequest().getParameter("groupIds"));
		 return TOADDPLANZBZ;
	}




	public void setFwOrganizationManagerBo(
			FwOrganizationManagerBo fwOrganizationManagerBo) {
		this.fwOrganizationManagerBo = fwOrganizationManagerBo;
	}



	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}







	



}
