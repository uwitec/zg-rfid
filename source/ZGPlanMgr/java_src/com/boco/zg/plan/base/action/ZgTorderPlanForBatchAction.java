/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanForBatchExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wengq 
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderPlanForBatchAction extends BaseStruts2Action implements Preparable,ModelDriven{
	


	


	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	
	//forward paths
	protected static final String QUERY_JSP_FORBATCH = "/zg/plan/ZgTorderPlan/query_ZgTorderPlan_ForBatch.jsp";
	protected static final String LIST_JSP_BATCH= "/zg/plan/ZgTorderPlan/list_ZgTorderPlan_ForBatch.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderPlan/list.do";
	protected static final String LIST_ACTION_BATCH="!/zg/plan/ZgTorderPlanForBatch/queryForBatch.do";
	
	//选择审核人的页面 
	private static final String QUERY_AUDITING_PEOPLE = "/zg/plan/ZgTorderPlan/query_auditingPeople.jsp";
	private static final String AUDITING_PEOPLE_LIST = "/zg/plan/ZgTorderPlan/auditingPeopleList.jsp";
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	
	private ZgTorderPlan zgTorderPlan;
	
	java.lang.String id = null;
	
	private String[] items;
	
	private String orderPlanId=null;
	
	//领料组角色
	private List<FwOrganization> roles;
	
	private FwOrganizationBo fwOrganizationBo;

	public void setItems(String[] items) {
		this.items = items;
	}

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
			zgTorderPlan=new ZgTorderPlan();
		} else {
			zgTorderPlan=(ZgTorderPlan)zgTorderPlanBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanForBatchExBo(ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo) {
		this.zgTorderPlanForBatchExBo = zgTorderPlanForBatchExBo;
	}
	
	public Object getModel() {
		return zgTorderPlan;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
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
	

	/**
	 * wjz加上的__标识状态的,页面上要取得的
	 */
	private String s_state;
	public String getS_state() {
		return s_state;
	}
	
	/**
	 * wjz,进入选择审核人页面
	 */
	public String queryForAuditingPeople(){
		return QUERY_AUDITING_PEOPLE;
	}
	
	/**
	 * wjz,查询选择 审核人 页面的信息
	 */
	public String findAuditingList(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		List<FwEmployee> fwEmployeeList=zgTorderPlanForBatchExBo.findAllAuditingPeople(pageRequest);
		getRequest().setAttribute("fwEmployeeList", fwEmployeeList);
		return AUDITING_PEOPLE_LIST;
	}
	
	
	
	/** 进入查询页面 批量领料计划*/
	public String queryForBatch() {
		String type = this.getRequest().getParameter("type");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("planType", type);
		getRequest().setAttribute("pageRequest", pageRequest);
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");
		return QUERY_JSP_FORBATCH;
	}
	
	/** 执行搜索 批量领料计划 */
	public String listForBatch() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		s_state=pageRequest.getFilters().get("state")==null?"1":pageRequest.getFilters().get("state").toString();//用以取得状态的，页面上
		Page page = zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP_BATCH;
	}
	
	/**
	 * wjz,单的状态
	 */
	private String zgTorderPlanStateData;
	public String getZgTorderPlanStateData() {
		return zgTorderPlanStateData;
	}
	public void setZgTorderPlanStateData(String zgTorderPlanStateData) {
		this.zgTorderPlanStateData = zgTorderPlanStateData;
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
	public void saveOrderPlanForBatch() throws ServletException, IOException {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String createUserId=pageRequest.getFilters().get("operatorId").toString();//创建人的id
		zgTorderPlan.setUserId(createUserId);
		
		//1 遍历session中的bom组件，找出其中修改标记为true的组件更新领料计划bom表中
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		synSessionBomToDataBase(list);
		
		//2 更新领料计划表状态为“保存”
		//如果是‘退回’状态的再度保存的话，状态还是‘退回’状态
		if(zgTorderPlanStateData.equals("2")){
			zgTorderPlan.setState(Constants.OrderPlanStatus.REJECTAUDITING.value());
			zgTorderPlanForBatchExBo.saveOrderPlan(zgTorderPlan,"",Constants.OrderPlanStatus.REJECTAUDITING.value());
		}else{
		
			zgTorderPlan.setState(Constants.OrderPlanStatus.SAVE.value());
			zgTorderPlanForBatchExBo.saveOrderPlan(zgTorderPlan,"",Constants.OrderPlanStatus.REJECTAUDITING.value());
		}
		forwardQuery("操作成功");
		//return SUCCESS;
	}

	/**
	 * 将session中的bom组件同步到数据库的领料计划表中  
	 * 处理逻辑：	
	 * 	 遍历session中的半成品列表
	 *   对修改标志为true的组件进行处理
	 *     删除标志为true的组件进行删除操作
	 *     否则进行更新或插入操作
	 *    
	 * @param list
	 */
	private void synSessionBomToDataBase(List<ZgTorderPlanbomEx> list) {
		for (ZgTorderPlanbomEx obj : list) {
			obj.setAuditNum(obj.getCarNum());
			if(obj.getIsModity()){//把标记为修改过的bom组件更新到数据库中
				if(obj.getIsDel()==true){//删除表中的该组件
					zgTorderPlanbomExBo.removeByIdAndBomId(obj.getCuid());
				}else {
					int row=zgTorderPlanbomExBo.updateOrderPlanforBatch(obj);
					if(row<=0){//更新影响的条数小于０  则说明原来的数据中没有该组件，插入该组件
						zgTorderPlanbomExBo.saveOrderPlanforBatch(obj);//插入
					}
				}
			}
		}
	}
	
	/**
	 * 方法描述：提交批量领料计划
	 * 处理逻辑：： 1 如果该领料计划的状态为“已提交”，则先删除装车计划表中该领料计划的相关装车计划 --删除此逻辑 因为提交后的批量领料单不能再做调整
	 * 			  2 遍历session中的bom组件,找出其中修改标记为true的组件更新领料计划bom表中
	 *            3 为该领料计划自动生成装车计划
	 *            4 更新领料计划表状态为“已提交”
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("unchecked")
	public void submitOrderPlanForBatch() throws IOException {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String createUserId=pageRequest.getFilters().get("operatorId").toString();//拿到创建人的ID
		zgTorderPlan.setUserId(createUserId);
		//拿到所选审核人的id
		String auiditingUserId=this.getRequest().getParameter("userId");
		
		 //2 遍历session中的bom组件，找出其中修改标记为true的组件更新领料计划bom表中
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		synSessionBomToDataBase(list);
//		
//		//3 为该领料计划自动生成装车计划     
//		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
//		zgTorderPlanForBatchExBo.submitOrderPlanForBatch(zgTorderPlan,operatorInfo);
		
		//4 更新领料计划表状态为“待审核”
		zgTorderPlan.setState(Constants.OrderPlanStatus.WAITAUDITING.value());
		zgTorderPlanForBatchExBo.saveOrderPlan(zgTorderPlan,auiditingUserId,Constants.OrderPlanStatus.WAITAUDITING.value());
		//zgTorderPlanForBatchExBo.updateOrderPlanState(zgTorderPlan.getCuid(),Constants.OrderPlanStatus.SUBMIT.value());
		
		
		forwardQuery("操作成功");
	}
	
	public void submitOrderPlan() throws IOException {
		String auiditingUserId=this.getRequest().getParameter("userId");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String orderPlanId = (java.lang.String)params.get("id");
			zgTorderPlanForBatchExBo.updateOrderPlanState(orderPlanId,Constants.OrderPlanStatus.WAITAUDITING.value(),auiditingUserId,1);//这里的1是代表要插入审核意见表的标识
		}
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
	
	/**
	 * 删除待审核和保存的批量领料计划单
	 * @return
	 * @throws IOException
	 */
	public String deletePlan(){
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderPlanForBatchExBo.deleteBatchOrderPlan((java.lang.String)params.get("id"));
		}
		
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		s_state=pageRequest.getFilters().get("s_state")==null?"1":pageRequest.getFilters().get("s_state").toString();//用以取得状态的，页面上
		Page page = zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP_BATCH;
	//	return LIST_ACTION_BATCH;
	}



}
