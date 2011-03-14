/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.action;

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
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwOrganizationBo;


import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.common.service.CommonService;

import com.boco.zg.plan.model.ZgTorderPlanbomEx;



import com.boco.zg.plan.service.AuditingForBatchExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wjz 
 * @version 1.0
 * @since 1.0
 */


public class AuditingForBatchAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	
	//forward paths
	protected static final String QUERY_JSP_FORBATCH = "/zg/auditing/query_WuAuditing_ForBatch.jsp";
	protected static final String LIST_JSP_BATCH= "/zg/auditing/list_WuAuditing_ForBatch.jsp";
	
	
	private AuditingForBatchExBo auditingForBatchExBo;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	
	private ZgTorderPlan zgTorderPlan;
	
	java.lang.String id = null;
	
	
	private String orderPlanId=null;
	
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
	

	/**
	 * wjz,提交的类型,1为通过，2为驳回
	 */
	private int submitType;
	public int getSubmitType() {
		return submitType;
	}
	public void setSubmitType(int submitType) {
		this.submitType = submitType;
	}
	
	/**
	 * wjz,驳回意见
	 */
	private String rejectOpinionText="";
	public String getRejectOpinionText() {
		return rejectOpinionText;
	}
	public void setRejectOpinionText(String rejectOpinionText) throws Exception {
		//byte[] b = rejectOpinionText.getBytes("ISO-8859-1");
		//this.rejectOpinionText = new String(b,"utf-8");
//		rejectOpinionText=new String(rejectOpinionText.getBytes("ISO8859_1"), "UTF8");
		this.rejectOpinionText=rejectOpinionText;
	}
	
	/**
	 * wjz,单id
	 */
	private String cuid;
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	
	
	
	
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderPlan=new ZgTorderPlan();
		} else {
			zgTorderPlan=(ZgTorderPlan)zgTorderPlanBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setAuditingForBatchExBo(AuditingForBatchExBo auditingForBatchExBo) {
		this.auditingForBatchExBo = auditingForBatchExBo;
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

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}
	
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}

	

	/**
	 * wjz加上的__标识状态的,页面上要取得的
	 */
	private String s_state;
	public String getS_state() {
		return s_state;
	}
	
	
	
	/** 进入查询页面 审核‘批量领料计划’*/
	public String queryForBatch() {//修噶过，这里主要是初始化条件
		String type = this.getRequest().getParameter("type");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("planType", type);
		getRequest().setAttribute("pageRequest", pageRequest);
		//初始化条件--时间
		CommonService.defultDateSet(getRequest(), "planDate_start", "planDate_end");
		return QUERY_JSP_FORBATCH;
	}
	
	/** 执行搜索 审核‘批量领料计划’ */
	public String listForBatch() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		s_state=pageRequest.getFilters().get("state").toString();//用以取得状态的，页面上
		Page page = auditingForBatchExBo.findAuditingByPageRequest(pageRequest);//搜索拿到东西的，去到service
		savePage(page,pageRequest);
		return LIST_JSP_BATCH;
	}
	
	
	
	
	/**
	 * 方法描述：提交批量领料计划
	 * 处理逻辑：： 1 更新领料计划表状态为--'通过'或'驳回'：以传进来的submitType为标识来分辨是'通过'或'驳回';1为'通过',2为'驳回'
	 * 			    1.1 为该领料计划自动生成装车计划------如果是通过了，就自动生成 装车计划
	 *            2 插入历史数据
	 *              驳回的肯定要进去的，页面上已经限制了意见信息由值的
	 *              通过状态就不一定要插入
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("unchecked")
	public void submitOrderPlanForBatch() throws IOException {
		//1 更新领料计划表状态为--'通过'或'驳回'
		//以传进来的submitType为标识来分辨是'通过'或'驳回';1为'通过',2为'驳回'
		if(this.getSubmitType()==1){// 更新为‘通过’
			//1.1 为该领料计划自动生成装车计划------如果是通过了，就自动生成 装车计划
			OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
			auditingForBatchExBo.submitOrderPlanForBatch(zgTorderPlan,operatorInfo);
			zgTorderPlan.setState(Constants.OrderPlanStatus.SUBMIT.value());
			auditingForBatchExBo.saveOrderPlan(zgTorderPlan);
			
		}else{// 更新为‘驳回’
			zgTorderPlan.setState(Constants.OrderPlanStatus.REJECTAUDITING.value());
			auditingForBatchExBo.saveOrderPlan(zgTorderPlan);
		}
		
		//2 这里用来插入历史数据的
		//驳回的肯定要进去的，页面上已经限制了意见信息由值的
		//通过状态就不一定要插入
		if(!this.getRejectOpinionText().equals("")){
			//这里加上*************************************************************************
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			zgTorderPlanComment.setContent(this.getRejectOpinionText());
			zgTorderPlanComment.setOrderplanid(this.getCuid());//单据id
			
			zgTcarbomBo.updateZgTorderPlanComment(zgTorderPlanComment);
		}
		
		forwardQuery("操作成功");
	}
	


	
	

	

	

	

	

	

	









}
