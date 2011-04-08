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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.service.ZgTBomManagerBo;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanCommentBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanForBatchExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.ZgTstorage;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wengq
 * @version 1.0
 * @since 1.0
 */


public class ZgTBomManagerAction extends BaseStruts2Action implements Preparable,ModelDriven{
	


	


	private static final String LIST_FOR_CHANGEBOM = "!/zg/plan/ZgTBomManager/listForChange.do";


	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	
	// forward paths
	private static final String QUERYFORCHANGE = "/zg/plan/ZgTChangeBom/query_for_changebom.jsp";
	private static final String LISTFORCHANGE = "/zg/plan/ZgTChangeBom/list_for_changebom.jsp";
	
	private static final String CREATE_FOR_CHANGE = "/zg/plan/ZgTChangeBom/create_for_changebom.jsp";

	private static final String BOM_LIST = "/zg/plan/ZgTChangeBom/bom_list.jsp";

	private static final String QUERY_ORDER_BOM_LIST = "/zg/plan/ZgTChangeBom/list_order_bom.jsp";

	private static final String QUERY_ORDER_BOM = "/zg/plan/ZgTChangeBom/query_order_bom.jsp";

	private static final String QUERYFORCHANGEAUDIT = "/zg/plan/ZgTChangeBom/query_for_changebom_audit.jsp";

	private static final String LISTFORCHANGEAUDIT = "/zg/plan/ZgTChangeBom/list_for_changebom_audit.jsp";
	
	private static final String QUERYFORCHANGELEAD = "/zg/plan/ZgTChangeBom/query_for_changebom_lead.jsp";
	
	private static final String LISTFORCHANGELEAD = "/zg/plan/ZgTChangeBom/list_for_changebom_lead.jsp";
	
	private static final String LIST_QUERYHISTORY = "/zg/plan/ZgTChangeBom/list_queryhistory.jsp";
	
	private static final String LOOK = "/zg/plan/ZgTChangeBom/list_for_changebom_look.jsp";
    
	protected static final String EDIT_JSP = "/zg/plan/ZgTChangeBom/edit.jsp";
	
	protected static final String VIEW_JSP = "/zg/plan/ZgTChangeBom/view.jsp";

	private static final String CHANGE_BOM_AUDIT_UI = "/zg/plan/ZgTChangeBom/change_bom_audit_frame.jsp";


	private static final String LIST_CHANGEBOM = "/zg/plan/ZgTChangeBom/list_changebom.jsp";
	
	private static final String ORDERARBPL_LIST = "/zg/plan/ZgTChangeBom/query_orderArbplList.jsp";
	private static final String ORDER_BOM = "/zg/plan/ZgTChangeBom/orderArbplList.jsp";

	private ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo;
	private ZgTorderPlanCommentBo zgTorderPlanCommentBo;
	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	private ZgTBomManagerBo zgTBomManagerBo;
	private FwEmployeeBo fwEmployeeBo;
	private ZgTorderPlan zgTorderPlan;
	private String id;
	private String[] items;
	private ZgTorderbomEx zgTorderbomEx;
	private ZgTorderbomExBo zgTorderbomExBo;
	private ZgTorderPlanBo zgTorderPlanBo;
	private List<ZgTorderbomEx> bomList;
	private List<Map> bol;
	private ZgTorderPlanbom zgTorderPlanbom;
	private String cuid;
	private String s_state;
	private Long waitBackNum;
	private ZgTcarbomBo zgTcarbomBo;
	private List<ZgTorderPlanComment> zgTorderPlanCommentList;
	
	
	public List<ZgTorderPlanComment> getZgTorderPlanCommentList() {
		return zgTorderPlanCommentList;
	}

	public void setZgTorderPlanCommentList(
			List<ZgTorderPlanComment> zgTorderPlanCommentList) {
		this.zgTorderPlanCommentList = zgTorderPlanCommentList;
	}

	public Long getWaitBackNum() {
		return waitBackNum;
	}

	public void setWaitBackNum(Long waitBackNum) {
		this.waitBackNum = waitBackNum;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public void setZgTorderPlanForBatchExBo(
			ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo) {
		this.zgTorderPlanForBatchExBo = zgTorderPlanForBatchExBo;
	}

	public void prepare() throws Exception {
		
		
		
		
		if (isNullOrEmptyString(id)){
			zgTorderPlan = new ZgTorderPlan();
		} else {
			zgTorderPlan = (ZgTorderPlan)zgTorderPlanBo.getById(id);
		}
	}

    // 页面传参的途径
	public Object getModel() {
		// TODO Auto-generated method stub
		return zgTorderPlan;
	}

   /*
	 * "planDate_start", "planDate_end" 得到前两填后五天的值
	 * 
	 * 
	 */
	public String queryForChange(){
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		// Page page =
		// zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		// savePage(page,pageRequest);
		
		return QUERYFORCHANGE;
	}
	
	
	/*
	 * 查询换料列表 根据查询结果分页
	 * 
	 */
	public String listForChange(){
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");  //显示前两天后五天的时间
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS); 
		// Map params=pageRequest.getFilters();
		// params.put("cuid", "123");
		Page page = zgTBomManagerBo.pageOrderPlanForChange(pageRequest);  
		savePage(page,pageRequest);
		return LISTFORCHANGE;
	}
	
	public String edit() {
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return EDIT_JSP;
	}
	public String view() {
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return VIEW_JSP;
	}
	
	/** 进入新增页面 */
	public String createForChange() {
		// 获取登陆人基本信息
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		FwEmployee fwEmployee = (FwEmployee)fwEmployeeBo.getById(operatorInfo.getOperatorId());
		
		zgTorderPlan =new ZgTorderPlan();
		zgTorderPlan.getUserId_related().setValue(fwEmployee.getLabelCn());
		zgTorderPlan.setUserId(fwEmployee.getCuid());
		
		// 生成领料计划单 状态为未处理
		zgTorderPlan.setState(Constants.CarPlanStatus.NEW.value());
		zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
		String orderPlanId=zgTBomManagerBo.getCuid();
		zgTorderPlan.setCuid(orderPlanId);
		
		Date nowDate=Calendar.getInstance().getTime();
		 zgTorderPlan.setCreate_date(nowDate);
		
		return CREATE_FOR_CHANGE;
	}
	
	
	/**
	 * 获取换料单BOM列表 *falg标志表示当前查看的数据是否为临时数据，因为BOM在编辑的时候，并不是直接存入数据库，而是先保存在session
	 * 中，后面点击出入库单的“保存”或“提交”时，才从session中取出进行相应的操作
	 * 
	 * @return
	 */
	public String findBomListByPlanID(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		List<Map> bol =new ArrayList<Map>();
		String flag=getRequest().getParameter("flag"); 

		if("del".equals(flag)){//
			bol=(List<Map>) this.getSession().getAttribute("bol");
			for (Map obj : bol) {
				obj.put("isDel", true);
				obj.put("isModity", true);
			}
		}
			else if("temp".equals(flag)){// session中读取数据
			bol=(List<Map>) this.getSession().getAttribute("bol");
		}else {
			bol=zgTBomManagerBo.findBomListByPlanID(id); //从数据库中获取数据  bom列表
		}
		    this.getSession().setAttribute("bol", bol);
		
	    String bomIds="";
		for (Map obj : bol) {
		   if(obj.get("isDel")==null||(!((Boolean)obj.get("isDel")).booleanValue())){
				bomIds=bomIds+obj.get("orderBomId")+",";
			}
		}
		   this.getSession().setAttribute("bomIds", bomIds);
		
		   List<Map> map =new ArrayList<Map>();
		   map=zgTBomManagerBo.findqueryHistoryPlanID(id); // 根据领料计划id查找历史审核记录
		   getRequest().setAttribute("map", map);
		
		   return BOM_LIST;
	}
	
	/**
	 * 删除session中的某些BOM
	 * 
	 * @return
	 */
	public String deleteBom(){
		List<Map> list=(List<Map>) this.getSession().getAttribute("bol"); //session中读取数据
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String delId=(String)params.get("cuid");
			
			for (Map obj : list) {
				if(delId.equals(obj.get("CUID"))){
					obj.put("isDel",true);
					obj.put("isModity", true);
				}
			}
			
		}
		return BOM_LIST;
	}
	
	/**
	 * 根据订单编号查找其BOM列表
	 * 
	 * @return
	 */
	public String queryBomlListByOrderId(){
		// 订单编号
		getRequest().setAttribute("orderId", getRequest().getParameter("orderId"));
		// 物料等级
		getRequest().setAttribute("extend1", getRequest().getParameter("extend1"));
		
		return QUERY_ORDER_BOM;
	}
	
	/**
	 * 根据订单编号查找其BOM列表
	 * 
	 * @return
	 */
	
	public String findBomlListByOrderId(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		List<Map> bomList= zgTBomManagerBo.findBomlListByOrderId(pageRequest);
		
		List<Map> list=(List<Map>) getRequest().getSession().getAttribute("bol");
		
	    String bomIds="";
		for(int i=0;i<list.size();i++){
			Map map=list.get(i);
			if(map.get("isDel")==null||((Boolean)map.get("isDel")).booleanValue()!=true){
				bomIds=bomIds+map.get("CUID")+",";
			  }
		}
		
		List<Map> bomListNew=new ArrayList<Map>();
		for (Map obj : bomList) {
			String s1=bomIds;
			String s2=obj.get("CUID").toString();
			int i=s1.indexOf(s2);
			   if(i<0){
				   bomListNew.add(obj);
			   }
	      }
		getRequest().setAttribute("bomList", bomListNew);
	
		return QUERY_ORDER_BOM_LIST;
	}
	
	public String queryForChangeAudit(){
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");  
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERYFORCHANGEAUDIT;
	}
	/*
	 * 查询待品质部审核的列表
	 * 
	 */
	public String listForChangeAudit(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put(key, value)
		Page page = zgTBomManagerBo.pageOrderPlanForChange1(pageRequest);
		savePage(page,pageRequest);
		return LISTFORCHANGEAUDIT;
	}
	
	/*
	 * 查询待厂领导审核
	 */
	public String queryForChangeLead(){
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");  
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERYFORCHANGELEAD;
	}
	
	/*
	 * 查询待厂领导审核列表
	 */
	public String listForChangeLead(){
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		String userId=operatorInfo.getUserId();
		String roleId=zgTBomManagerBo.getRoleCuidByUserId(userId);
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("roleId", roleId);
		Page page = zgTBomManagerBo.pageOrderPlanForChange1(pageRequest);
		savePage(page,pageRequest);
		return LISTFORCHANGELEAD;
	}
	
	public String look(){
		return LOOK;
	}
	/*
	 * 根据orderPlanId查询历史审核记录
	 * 
	 */
	public String queryHistory(){
		String str=this.getRequest().getParameter("id");
		List<Map> map =new ArrayList<Map>();
		map=zgTBomManagerBo.findqueryHistoryPlanID(str);
		getRequest().setAttribute("map", map);
		return LIST_QUERYHISTORY;
	}
	
	/**
	 * 审核‘批量领料’=》查找bom组件 处理逻辑：以orderPlanId来查找出 批量领料计划 信息
	 * 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toChangeBomAuditUI() throws ServletException, IOException {
		ZgTorderPlan zgTorderPlan=new ZgTorderPlan();
		
		String flag=this.getRequest().getParameter("flag");
		return CHANGE_BOM_AUDIT_UI;
	}
   
	/*
	 * 根据单据编号查询bom列表
	 *根据orderPlanId查询历史审核记录
	 */
	public String listChangeBom() {
		String str=this.getRequest().getParameter("id");
		List<Map> bol =new ArrayList<Map>();
		bol=zgTBomManagerBo.findBomListByPlanID(str);
		getRequest().setAttribute("bol", bol);
		//List<Map> map =new ArrayList<Map>();
		List<Map> map =new ArrayList<Map>();
		map=zgTBomManagerBo.findqueryHistoryPlanID(id);
		getRequest().setAttribute("map", map);
		return LIST_CHANGEBOM;
	}
	
	/*
	 * 品质部审核 
	 * 处理逻辑 根据换料原因和领料计划id
	 * 换料原因为6 表示其他原因 修改领料计划表的退料备注、腿料原因和状态（完成） 新建审核记录表
	 * 不为6 修改领料计划表的腿料原因和状态（完成） 新建审核记录表
	 */
	public void examineOrderPlan1() throws IOException{
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		String userId=operatorInfo.getUserId();
		String back_reason=this.getRequest().getParameter("back_reason");
		String id=this.getRequest().getParameter("id");
		
		if(Integer.parseInt(back_reason)==6){//
			String back_zbz=this.getRequest().getParameter("text");
			zgTorderPlan = (ZgTorderPlan)zgTorderPlanBo.getById(id);
			zgTorderPlan.setState(Constants.ChangePlanStatus.DONE.value());
			zgTorderPlan.setBackReason(back_reason);
			zgTorderPlan.setBackZbz(back_zbz);
			zgTorderPlanBo.update(zgTorderPlan);
			
			
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			Date nowDate=Calendar.getInstance().getTime();
			zgTorderPlanComment.setCreatetime(nowDate);
			zgTorderPlanComment.setOrderplanid(id);
			zgTorderPlanComment.setUserid(userId);
			zgTorderPlanComment.setContent(back_zbz);
			zgTorderPlanComment.setAudittype(Constants.TypeStatus.FACTORY.value());
			zgTorderPlanCommentBo.savePlanComment(zgTorderPlanComment);
		}else{
			zgTorderPlan = (ZgTorderPlan)zgTorderPlanBo.getById(id);
			zgTorderPlan.setState(Constants.ChangePlanStatus.DONE.value());
			zgTorderPlan.setBackReason(back_reason);
			zgTorderPlanBo.update(zgTorderPlan);
			
			
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			Date nowDate=Calendar.getInstance().getTime();
			zgTorderPlanComment.setCreatetime(nowDate);
			zgTorderPlanComment.setOrderplanid(id);
			zgTorderPlanComment.setUserid(userId);
			if(Integer.parseInt(back_reason)==1){
				back_reason="人为原因";
			}else if(Integer.parseInt(back_reason)==2){
				back_reason="原材料";
			}else if(Integer.parseInt(back_reason)==3){
				back_reason="留仓待用";
			}else if(Integer.parseInt(back_reason)==4){
				back_reason="建议报废";
			}else{
				back_reason="退回工厂返工";
			}
			zgTorderPlanComment.setContent(back_reason);
			zgTorderPlanComment.setAudittype(Constants.TypeStatus.QUALITY.value());
			zgTorderPlanCommentBo.savePlanComment(zgTorderPlanComment);
		
		}
		
		forwardQuery("操作成功");
	}

	/*
	 * 厂领导审核
	 * 处理逻辑  根据提交类型 领料计划id 审核意见
	 * 提交类型为1 表示通过 修改领教计划状态为待品质部审核 并新增审核意见
	 * 提交类型为2 表示退回 修改领教计划状态为厂领导审核退回 并新增审核意见
	 */
	public void examineOrderPlan() throws IOException{
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		String userId=operatorInfo.getUserId();
		String submitType=this.getRequest().getParameter("submitType");
		String rejectOpinionText=this.getRequest().getParameter("rejectOpinionText");
		String id=this.getRequest().getParameter("id");
		if(Integer.parseInt(submitType)==1){
			zgTorderPlan = (ZgTorderPlan)zgTorderPlanBo.getById(id);
			zgTorderPlan.setState(Constants.ChangePlanStatus.WAITAUDITINGC.value());
			zgTorderPlanBo.update(zgTorderPlan);
			
			
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			zgTorderPlanComment.setContent(rejectOpinionText);
			Date nowDate=Calendar.getInstance().getTime();
			zgTorderPlanComment.setCreatetime(nowDate);
			zgTorderPlanComment.setOrderplanid(id);
			zgTorderPlanComment.setUserid(userId);
			zgTorderPlanComment.setAudittype(Constants.TypeStatus.FACTORY.value());
			zgTorderPlanCommentBo.savePlanComment(zgTorderPlanComment);
		}
		if(Integer.parseInt(submitType)==2){
			zgTorderPlan = (ZgTorderPlan)zgTorderPlanBo.getById(id);
			zgTorderPlan.setState(Constants.ChangePlanStatus.REJECTAUDITING.value());
			zgTorderPlanBo.update(zgTorderPlan);
			
			
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			zgTorderPlanComment.setContent(rejectOpinionText);
			Date nowDate=Calendar.getInstance().getTime();
			zgTorderPlanComment.setCreatetime(nowDate);
			zgTorderPlanComment.setOrderplanid(id);
			zgTorderPlanComment.setUserid(userId);
			zgTorderPlanComment.setAudittype(Constants.TypeStatus.FACTORY.value());
			zgTorderPlanCommentBo.savePlanComment(zgTorderPlanComment);
		}
		
		forwardQuery("操作成功");
	}

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}

	public void setZgTBomManagerBo(ZgTBomManagerBo zgTBomManagerBo) {
		this.zgTBomManagerBo = zgTBomManagerBo;
	}

	/*
	 * 为换料单添加bom物料
	 * 
	 */
	public void generateBom() throws IOException{
		List<Map> bol=(List<Map>) this.getSession().getAttribute("bol");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Map map=new HashMap();
			map.put("CUID", (java.lang.String)params.get("orderBomId"));
			map.put("PLANBOMID", (java.lang.String)params.get("orderPlanbomId"));
			map.put("AUFNR", (java.lang.String)params.get("orderAufnr"));
			map.put("ARBPL", (java.lang.String)params.get("arbpl"));
			map.put("IDNRK",(java.lang.String)params.get("idnrk"));
			map.put("MAKTX2", (java.lang.String)params.get("maktx2"));
			map.put("STORAGE_NUM",(java.lang.String)params.get("storage_num"));
			map.put("WAIT_BACK_NUM", 0);
			map.put("isDel",false);
			map.put("isModity", false);
			bol.add(map);
		}
		
		this.getSession().setAttribute("bol", bol);

		returnMsgAndCloseWindow("操作成功");
	}

	public ZgTorderPlanbomExBo getZgTorderPlanbomExBo() {
		return zgTorderPlanbomExBo;
	}

	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}

	public List<ZgTorderbomEx> getBomList() {
		return bomList;
	}

	public void setBomList(List<ZgTorderbomEx> bomList) {
		this.bomList = bomList;
	}
	
	/*
	 * 删除换料计划
	 */
	public String deletePlan(){
		// PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderPlanBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_FOR_CHANGEBOM;
	}

	/**
	 * 保存换料物料记录 处理逻辑： 保存换料句话bom表信息，设置状态为保存 
	 * 
	 * 
	 * @return
	 * @throws IOException
	 */
	
	public void save() throws IOException {
		List<Map> bol=(List<Map>) this.getSession().getAttribute("bol");
	    String update=this.getRequest().getParameter("update");
		
	    if(isNullOrEmptyString(update)){
			zgTorderPlan.setState(Constants.ChangePlanStatus.SAVE.value());
			zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
			zgTorderPlanBo.saveOrderPlan1(zgTorderPlan);
			zgTBomManagerBo.synSessionBomToDataBase(bol,zgTorderPlan);
			
		}else {
			zgTorderPlan.setState(Constants.ChangePlanStatus.SAVE.value());
			zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
			zgTorderPlanBo.update(zgTorderPlan);
		
			zgTBomManagerBo.updateOrderPlanBomWaitBackNum(bol,zgTorderPlan);
			}
			
		forwardQuery("操作成功");
		
	}
	public void submit() throws IOException {
		List<Map> bol=(List<Map>) this.getSession().getAttribute("bol");
		
		

	    String update=this.getRequest().getParameter("update");// 区别是新建的时候提交，还是编辑的时候提交
		
	    if(isNullOrEmptyString(update)){
			zgTorderPlan.setState(Constants.ChangePlanStatus.WAITAUDITING.value());
			zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
			zgTorderPlanBo.saveOrderPlan1(zgTorderPlan);
			zgTBomManagerBo.synSessionBomToDataBase(bol,zgTorderPlan);
		}else {
		    
			zgTBomManagerBo.updateOrderPlanBomWaitBackNum(bol,zgTorderPlan);
			}
			zgTorderPlan.setState(Constants.ChangePlanStatus.WAITAUDITING.value());
			zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
			zgTorderPlanBo.update(zgTorderPlan);
			
		forwardQuery("操作成功");
		
	}
	
	public ZgTorderbomEx getZgTorderbomEx() {
		return zgTorderbomEx;
	}

	public void setZgTorderbomEx(ZgTorderbomEx zgTorderbomEx) {
		this.zgTorderbomEx = zgTorderbomEx;
	}

	/**
	 * 获取订单生产线列表
	 */
	public String queryOrderArbplList(){
		String arbpl=this.getRequest().getParameter("arbpl");
		getRequest().setAttribute("arbpl", arbpl);
		return ORDERARBPL_LIST;
	}
	public String findOrderArbplList(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
//		
			Page page= zgTorderbomExBo.findOrderArbplByPageRequest(pageRequest);
			savePage(page, pageRequest);
	
		return ORDER_BOM;
	}

	public ZgTorderbomExBo getZgTorderbomExBo() {
		return zgTorderbomExBo;
	}

	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public ZgTorderPlanbom getZgTorderPlanbom() {
		return zgTorderPlanbom;
	}

	public void setZgTorderPlanbom(ZgTorderPlanbom zgTorderPlanbom) {
		this.zgTorderPlanbom = zgTorderPlanbom;
	}
	
	/**
	 * 
	 * 更改session中的换料数量
	 * @throws IOException
	 */
	public String saveInOutBom() throws IOException{
		List<Map> sessionBomList=(List<Map>) this.getSession().getAttribute("bol");
		if(null!=bomList){
			for (ZgTorderbomEx bom : bomList) {
				if(bom!=null){
					for (Map temp : sessionBomList) {
						if(bom.getCuid().equals(temp.get("CUID"))){
							//System.out.println("XXXXXXXXXXXXXXXXXXXXX"+":"+bom.getWaitBackNum());
							temp.put("WAIT_BACK_NUM",bom.getWaitBackNum());
							temp.put("CAR_NUM",bom.getWaitBackNum());
							
							// temp.put("PLANBOMID", bom.getOrderPlanbomId());
							
							temp.put("isModity", true);
						}
					}
				}
				
			}
		}
		return SUCCESS;
	}

	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}

	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getS_state() {
		return s_state;
	}

	public void setS_state(String s_state) {
		this.s_state = s_state;
	}

	public ZgTorderPlanCommentBo getZgTorderPlanCommentBo() {
		return zgTorderPlanCommentBo;
	}

	public void setZgTorderPlanCommentBo(ZgTorderPlanCommentBo zgTorderPlanCommentBo) {
		this.zgTorderPlanCommentBo = zgTorderPlanCommentBo;
	}

	public ZgTBomManagerBo getZgTBomManagerBo() {
		return zgTBomManagerBo;
	}

	public ZgTcarbomBo getZgTcarbomBo() {
		return zgTcarbomBo;
	}

	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
}
