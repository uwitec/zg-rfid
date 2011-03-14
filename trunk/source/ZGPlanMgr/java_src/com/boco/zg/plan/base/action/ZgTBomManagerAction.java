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
import com.boco.zg.plan.base.service.ZgTBomManagerBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanForBatchExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
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
	


	


	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	
	//forward paths
	private static final String QUERYFORCHANGE = "/zg/plan/ZgTChangeBom/query_for_changebom.jsp";
	private static final String LISTFORCHANGE = "/zg/plan/ZgTChangeBom/list_for_changebom.jsp";

	private static final String CREATE_FOR_CHANGE = "/zg/plan/ZgTChangeBom/create_for_changebom.jsp";

	private static final String BOM_LIST = "/zg/plan/ZgTChangeBom/bom_list.jsp";

	private static final String QUERY_ORDER_BOM_LIST = "/zg/plan/ZgTChangeBom/list_order_bom.jsp";

	private static final String QUERY_ORDER_BOM = "/zg/plan/ZgTChangeBom/query_order_bom.jsp";

	private static final String QUERYFORCHANGEAUDIT = "/zg/plan/ZgTChangeBom/query_for_changebom_audit.jsp";

	private static final String LISTFORCHANGEAUDIT = "/zg/plan/ZgTChangeBom/list_for_changebom_audit.jsp";


	private static final String CHANGE_BOM_AUDIT_UI = "/zg/plan/ZgTChangeBom/change_bom_audit_frame.jsp";


	private static final String LIST_CHANGEBOM = "/zg/plan/ZgTChangeBom/list_changebom.jsp";

	private ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo;
	
	private ZgTBomManagerBo zgTBomManagerBo;
	private FwEmployeeBo fwEmployeeBo;
	private ZgTorderPlan zgTorderPlan;
	private String id;
	
	public void setZgTorderPlanForBatchExBo(
			ZgTorderPlanForBatchExBo zgTorderPlanForBatchExBo) {
		this.zgTorderPlanForBatchExBo = zgTorderPlanForBatchExBo;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}


	public Object getModel() {
		// TODO Auto-generated method stub
		return zgTorderPlan;
	}
	
	public String queryForChange(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return QUERYFORCHANGE;
	}
	
	public String listForChange(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgTBomManagerBo.pageOrderPlanForChange(pageRequest);
		savePage(page,pageRequest);
		return LISTFORCHANGE;
	}
	
	/** 进入新增页面*/
	public String createForChange() {
		//获取登陆人基本信息
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		FwEmployee fwEmployee = (FwEmployee)fwEmployeeBo.getById(operatorInfo.getOperatorId());
		zgTorderPlan =new ZgTorderPlan();
		zgTorderPlan.getUserId_related().setValue(fwEmployee.getLabelCn());
		zgTorderPlan.setUserId(fwEmployee.getCuid());
		
		//生成领料计划单　状态为未处理
		zgTorderPlan.setState(Constants.CarPlanStatus.NEW.value());
		zgTorderPlan.setPlanType(Constants.OrderPlanType.CHANGE.value());
		String orderPlanId=zgTBomManagerBo.getCuid();
		zgTorderPlan.setCuid(orderPlanId);
		
		return CREATE_FOR_CHANGE;
	}
	
	
	/**
	 * 获取换料单BOM列表
	 *  *falg标志表示当前查看的数据是否为临时数据，因为BOM在编辑的时候，并不是直接存入数据库，而是先保存在session
	 * 中，后面点击出入库单的“保存”或“提交”时，才从session中取出进行相应的操作
	 * @return
	 */
	public String findBomListByPlanID(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		List<Map> bomEList =new ArrayList<Map>();
		String flag=getRequest().getParameter("flag");
		if("temp".equals(flag)){//session中读取数据
			
		}else {
			bomEList=zgTBomManagerBo.findBomListByPlanID(pageRequest);
		}
		
		
		this.getSession().setAttribute("bomEList", bomEList);
		return BOM_LIST;


	}
	
	/**
	 * 根据订单编号查找其BOM列表
	 * @return
	 */
	public String queryBomlListByOrderId(){
		//订单编号
		getRequest().setAttribute("orderId", getRequest().getParameter("orderId"));
		//物料等级
		getRequest().setAttribute("extend1", getRequest().getParameter("extend1"));
		
		return QUERY_ORDER_BOM;
	}
	
	/**
	 * 根据订单编号查找其BOM列表
	 * @return
	 */
	public String findBomlListByOrderId(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//已经选择的BOM，需要过滤
		String changeBomIds=getRequest().getSession().getAttribute("changeBomIds")==null?"":getRequest().getSession().getAttribute("changeBomIds").toString();
		pageRequest.getFilters().put("changeBomIds", changeBomIds);
		List<Map> bomList= zgTBomManagerBo.findBomlListByOrderId(pageRequest);
		getRequest().setAttribute("bomList", bomList);
		return QUERY_ORDER_BOM_LIST;
	}
	
	public String queryForChangeAudit(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return QUERYFORCHANGEAUDIT;
	}
	
	public String listForChangeAudit(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgTorderPlanForBatchExBo.findBatchPlanByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LISTFORCHANGEAUDIT;
	}
	
	/**
	 * 审核‘批量领料’=》查找bom组件
	 * 处理逻辑：以orderPlanId来查找出 批量领料计划 信息
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toChangeBomAuditUI() throws ServletException, IOException {
		ZgTorderPlan zgTorderPlan=new ZgTorderPlan();
		
		String flag=this.getRequest().getParameter("flag");
		return CHANGE_BOM_AUDIT_UI;
	}
	
	public String listChangeBom() {
		return LIST_CHANGEBOM;
	}
	

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}

	public void setZgTBomManagerBo(ZgTBomManagerBo zgTBomManagerBo) {
		this.zgTBomManagerBo = zgTBomManagerBo;
	}

	public ZgTorderPlan getZgTorderPlan() {
		return zgTorderPlan;
	}

	public void setZgTorderPlan(ZgTorderPlan zgTorderPlan) {
		this.zgTorderPlan = zgTorderPlan;
	}
	




	


}
