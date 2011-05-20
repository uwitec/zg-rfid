/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.action;

import java.io.IOException;
import java.util.List;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;

import javax.servlet.ServletException;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wjz 
 * @version 1.0
 * @since 1.0
 */


public class AuditingbomForBatchAction extends BaseStruts2Action implements Preparable,ModelDriven{
	private static final String BOM_BATCH_FRAME = "/zg/auditing/bom_batch_frame.jsp";

	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	protected static final String BOM_LIST = "/zg/auditing/bom_batch_operater.jsp";
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo bo) {
		this.zgTorderPlanbomExBo = bo;
	}	
	

	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	java.lang.String id = null;
	private String orderPlanId=null;
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}
	
	
	private ZgTorderPlanbomEx zgTorderPlanbomEx;
	
	/*
	 * 注入ZgTcarbomBo
	 */
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
	private List<ZgTorderPlanComment> zgTorderPlanCommentList;
	public List<ZgTorderPlanComment> getZgTorderPlanCommentList() {
		return zgTorderPlanCommentList;
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
	
	public String getOrderPlanId() {
		return orderPlanId;
	}

	public void setOrderPlanId(String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}
	
	public java.lang.String getId() {
		return id;
	}
	
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderPlanbomEx=new ZgTorderPlanbomEx();
		} else {
			zgTorderPlanbomEx=(ZgTorderPlanbomEx)zgTorderPlanbomExBo.getOrderPlanbomExById(id);
		}
	}
	


	/**
	 * 审核‘批量领料’=》查找bom组件
	 * 处理逻辑：以orderPlanId来查找出 批量领料计划 信息
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findBomBatchList() throws ServletException, IOException {
		ZgTorderPlan zgTorderPlan=new ZgTorderPlan();
		
		String flag=this.getRequest().getParameter("flag");
		
		//修改单据
		zgTorderPlan = (ZgTorderPlan) zgTorderPlanBo.getById(orderPlanId);//这里是把本单的信息查出来
		
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		this.getRequest().setAttribute("zgTorderPlan", zgTorderPlan);
		return BOM_BATCH_FRAME;
	}

	
	/**
	 *  方法描述： 查找批量领料计划的bom组件
	 *   处理逻辑：
	 *   	查询数据库，并把查询出来的列表设置到session中.
	 * @return
	 */
	public String list() {
		ZgTorderPlan zgTorderPlan = (ZgTorderPlan) zgTorderPlanBo.getById(orderPlanId);//以orderPlanId查找出 批量领料计划的bom组件 的全部信息
		this.getRequest().setAttribute("zgTorderPlan", zgTorderPlan);//放在request中，页面上显示
		
		List<ZgTorderPlanbomEx> list=null; 
		list = zgTorderPlanbomExBo.findBomBatchList(orderPlanId);
	
		//保存bom组件编号到session中，方便后面的过滤操作
		String bomBatchIds="";
		for (ZgTorderPlanbomEx obj : list) {
			if(!obj.getIsDel()){
				bomBatchIds=bomBatchIds+obj.getBomId()+",";
			}
		}
		this.getSession().setAttribute("bomBatchIds",bomBatchIds);
		
		this.getSession().setAttribute("bomForBatchList",list);
		
		//查处历史的意见   以某个orderid
		zgTorderPlanCommentList=zgTcarbomBo.getZgTorderPlanCommentList(orderPlanId);
		return BOM_LIST;
	}
	
}
