/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.EnumModel;
import javacommon.base.service.IVmModelBo;

import javax.servlet.ServletException;

import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTunit;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTunitBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wengqin 
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderPlanbomForBatchAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String CREATE_JSP_FORBATCH = "/zg/plan/ZgTorderPlanbom/create_ZgTorderPlanbom_ForBatch.jsp";
	protected static final String EDIT_JSP_FORBATCH = "/zg/plan/ZgTorderPlanbom/edit_ZgTorderPlanbom_ForBatch.jsp";
	
	protected static final String BOM_LIST = "/zg/plan/ZgTorderPlanbom/bom_batch_operater.jsp";
	protected static final String BOM_BATCH_LIST = "/zg/plan/ZgTorderPlanbom/bom_batch_list.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderPlanbom/list.do";
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	private FwEmployeeBo fwEmployeeBo;	
	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	private ZgTorderPlanBo zgTorderPlanBo;
	private ZgTunitBo zgTunitBo;
	
	
	
	private ZgTorderPlanbomEx zgTorderPlanbomEx;
	java.lang.String id = null;
	private String orderPlanId=null;
	
	private String[] items;
	
	private List<EnumModel> times;
	public List<TmdEnumevalue> getTimes() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		return service.getEnumValue("TIME");
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderPlanbomEx=new ZgTorderPlanbomEx();
		} else {
			zgTorderPlanbomEx=(ZgTorderPlanbomEx)zgTorderPlanbomExBo.getOrderPlanbomExById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo bo) {
		this.zgTorderPlanbomExBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderPlanbomEx;
	}
	
	public Object getModelEx() {
		return zgTorderPlanbomEx;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public String getOrderPlanId() {
		return orderPlanId;
	}

	public void setOrderPlanId(String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}
	
	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}

	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}




	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}

	public java.lang.String getId() {
		return id;
	}

	
	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}
	
	private List<FwOrganization> roles;
	
	private List<ZgTunit> units;
	private FwOrganizationBo fwOrganizationBo;
	public List<FwOrganization> getRoles() {
		if(roles == null) {
			FwOrganization fwOrganization=new FwOrganization();
			fwOrganization.setType("4");
			
			roles = fwOrganizationBo.findByProperty(fwOrganization, "t0_LABEL_CN", true);
		}
		return roles;
	}
	
	public List<ZgTunit> getUnits() {
		if(units == null) {
			units = zgTunitBo.findAll();
		}
		return units;
	}
	
	/**
	 * 批量领料查找bom组件
	 * 处理逻辑：orderPlanId为空则为新建批量领料计划, 否则为编辑批量领料计划
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findBomBatchList() throws ServletException, IOException {
//		List<ZgTorderPlanbomEx> list=new ArrayList<ZgTorderPlanbomEx>();
		ZgTorderPlan zgTorderPlan=new ZgTorderPlan();
		
		String flag=this.getRequest().getParameter("flag");
		getRoles();
		getTimes();
		
		if(isNullOrEmptyString(orderPlanId)){//新建单据
			orderPlanId="自动生成";
			//获取登陆人基本信息
			OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
			FwEmployee fwEmployee = (FwEmployee)fwEmployeeBo.getById(operatorInfo.getOperatorId());
			
			zgTorderPlan.getUserId_related().setValue(fwEmployee.getLabelCn());
			zgTorderPlan.setUserId(fwEmployee.getCuid());
			//zgTorderPlan.setDepartmenuId(fwEmployee.getOrgId());
			//zgTorderPlan.getDepartmenuId_related().setValue(fwEmployee.getOrgId_related().getValue());
			
			//生成领料计划单　状态为未处理
			zgTorderPlan.setState(Constants.CarPlanStatus.NEW.value());
			zgTorderPlan.setPlanType(Constants.OrderPlanType.PLTYPE.value());
			orderPlanId=zgTorderPlanBo.saveOrderPlan(zgTorderPlan);
			zgTorderPlan.setCuid(orderPlanId);
			
		}else{//修改单据
			zgTorderPlan = (ZgTorderPlan) zgTorderPlanBo.getById(orderPlanId);//多加列
		}
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		this.getRequest().setAttribute("zgTorderPlan", zgTorderPlan);
	    //return BOM_BATCH_LIST;
		return "/zg/plan/ZgTorderPlanbom/bom_batch_frame.jsp";
	    //return "/zg/plan/ZgTorderPlanbom/organiztionBoms.jsp";
	}
	
	
	
	/**进入更新页面　批量领料*/
	public String editOrderPlanForBatch() {
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		for (ZgTorderPlanbomEx obj : list) {
			if(obj.getCuid().equals(id)){
				zgTorderPlanbomEx=obj;
				break;
			}
		}
		return EDIT_JSP_FORBATCH;
	}
	
	/**更新bom组件信息 批量领料计划
	 *  把需要添加的bom组件保存到session中
	 * @throws IOException */
	public void updateOrderPlanForBatch() throws IOException {
//		zgTorderPlanbomExBo.updateOrderPlanforBatch(this.zgTorderPlanbomEx);
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		
		//删除session中的该bom组件,然后添加新修改的bom组件
		for (int i=0;i<list.size();i++) {
			ZgTorderPlanbomEx obj=list.get(i);
			if(obj.getCuid().equals(zgTorderPlanbomEx.getCuid())){
				list.remove(i);
				break;
			}
		}
		
		zgTorderPlanbomEx.setIsModity(true);
		list.add(zgTorderPlanbomEx);
		this.getSession().setAttribute("bomForBatchList",list);
		
		returnMsgAndCloseWindow("操作成功");
	}
	
	private List<ZgTorderPlanbom> orderPlanboms = new ArrayList<ZgTorderPlanbom>();
	
	@SuppressWarnings("unchecked")
	public String saveOrderPlanBom() {
		for(ZgTorderPlanbom obj : orderPlanboms) {
			/*ZgTorderPlanbom entity = new ZgTorderPlanbom();
			entity.setCuid(obj.getCuid());
			entity.setDepartmentId(StringUtils.trimToNull(obj.getDepartmentId()));
			entity.setUserId(StringUtils.trimToNull(obj.getUserId()));
			entity.setPlanDate(obj.getPlanDate());
			entity.setPlanStartTime(StringUtils.trimToNull(obj.getPlanStartTime()));
			entity.setPlanEndTime(StringUtils.trimToNull(obj.getPlanEndTime()));
			entity.setState("4");
			zgTorderPlanbomExBo.updateOrderPlan(entity);*/
			zgTorderPlanbomExBo.save(obj);
		}
		return SUCCESS;
	}
	
	/**保存bom组件信息 批量领料计划
	 * 把需要添加的bom组件保存到session中
	 * @throws IOException */
	@SuppressWarnings("unchecked")
	public void saveOrderPlanForBatch() throws IOException {
//		zgTorderPlanbomExBo.saveOrderPlanforBatch(this.zgTorderPlanbomEx);
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		
		String cuid=zgTorderPlanbomExBo.getCUID();
		
		//判断sesson中的bom列表中是否存在当前需要添加的bom组件，如果存在,则删除session中的该bom组件
		for (int i = 0; i < list.size(); i++) {
			ZgTorderPlanbomEx obj=list.get(i);
			if(obj.getBomId().equals(zgTorderPlanbomEx.getBomId())){
				list.remove(i);
				break;
			}
		}
			
		
		zgTorderPlanbomEx.setCuid(cuid);
		zgTorderPlanbomEx.setIsModity(true);
		list.add(zgTorderPlanbomEx);
		this.getSession().setAttribute("bomForBatchList",list);
		
		returnMsgAndCloseWindow("操作成功");
	}
	



	/** 进入新增页面 批量领料计划*/
	public String createOrderPlanForBatch() {
		String orderPlanId = this.getRequest().getParameter("orderPlanId");
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		return "/zg/plan/ZgTorderPlanbom/organiztionBoms.jsp";
		//return CREATE_JSP_FORBATCH;
	}
	
	private ZgTcarbomBo zgTcarbomBo;
	/*
	 * 注入ZgTcarbomBo
	 */
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
	/**
	 * wjz,页面上来来显示 意见列表的
	 */
	private List<ZgTorderPlanComment> zgTorderPlanCommentList;
	public List<ZgTorderPlanComment> getZgTorderPlanCommentList() {
		return zgTorderPlanCommentList;
	}
	
	/**
	 *  方法描述： 查找批量领料计划的bom组件
	 *   处理逻辑：
	 *   	flag 标志表示当前查看的数据是否为临时数据，因为批量领料计划中的bom组件编辑的时候，并不是立即保存,
	 *   	而是设置完之后，后面点击”保存“或“提交”才会统一保存，中间的时候只是把这些修改信息保存在session中，
	 *   	flag为“temp”时，则取session中的数据列表，否则查询数据库，并把查询出来的列表设置到session中.
	 * @return
	 */
	public String list() {
		ZgTorderPlan zgTorderPlan = (ZgTorderPlan) zgTorderPlanBo.getById(orderPlanId);
		this.getRequest().setAttribute("zgTorderPlan", zgTorderPlan);
		
		List<ZgTorderPlanbomEx> list=null; 
		
		String flag=this.getRequest().getParameter("flag");
		if(("temp").equals(flag)){
			list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		}else {
			list = zgTorderPlanbomExBo.findBomBatchList(orderPlanId);
		}
		
		//保存bom组件编号到session中，方便后面的过滤操作
		String bomBatchIds="";
		for (ZgTorderPlanbomEx obj : list) {
			if(!obj.getIsDel()){
				bomBatchIds=bomBatchIds+obj.getBomId()+",";
			}
		}
		this.getSession().setAttribute("bomBatchIds",bomBatchIds);
		
		this.getSession().setAttribute("bomForBatchList",list);
		
		//查出历史的意见   以某个orderid  思路：只能查出审核时间为非空的
		zgTorderPlanCommentList=zgTcarbomBo.getZgTorderPlanCommentList(orderPlanId);
		return BOM_LIST;
	}
	
	
	/**
	 * 方法描述：删除批量领料计划中的bom组件
	 * 处理逻辑：把session中的该bom组件设置为删除状态
	 */
	public String deleteBomForBatch() {
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) this.getSession().getAttribute("bomForBatchList");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String delId=(String)params.get("id");
			
			for (ZgTorderPlanbomEx obj : list) {
				if(delId.equals(obj.getCuid())){
					obj.setIsModity(true);
					obj.setIsDel(true);
				}
			}
		}
		return SUCCESS;
	}

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}

	/**
	 * @return the zgTunitBo
	 */
	public ZgTunitBo getZgTunitBo() {
		return zgTunitBo;
	}

	/**
	 * @param zgTunitBo the zgTunitBo to set
	 */
	public void setZgTunitBo(ZgTunitBo zgTunitBo) {
		this.zgTunitBo = zgTunitBo;
	}
}
