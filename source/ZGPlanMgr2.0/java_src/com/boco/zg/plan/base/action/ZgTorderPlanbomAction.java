/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.EnumModel;
import javacommon.base.service.IVmModelBo;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderPlanbomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTorderPlanbom/query_ZgTorderPlanbom.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTorderPlanbom/list_ZgTorderPlanbom.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTorderPlanbom/create_ZgTorderPlanbom.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTorderPlanbom/edit_ZgTorderPlanbom.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTorderPlanbom/show_ZgTorderPlanbom.jsp";
	
	protected static final String BOM_LIST = "/zg/plan/ZgTorderPlanbom/bom_list.jsp";
	protected static final String VIEW_BOM_LIST= "/zg/plan/ZgTorderPlanbom/ViewZgTorderPlan/bom_list.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderPlanbom/list.do";
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTorderPlanExBo zgTorderPlanExBo;
	private ZgTorderPlanBo zgTorderPlanBo;
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private ZgTorderPlanbom zgTorderPlanbom;
	java.lang.String id = null;
	private String[] items;
	
	private List<EnumModel> times;
	
	public List<TmdEnumevalue> getTimes() {
		IVmModelBo service = (IVmModelBo)ApplicationContextHolder.getBean("vmModelBo");
		return service.getEnumValue("TIME");
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderPlanbom = new ZgTorderPlanbom();
		} else {
			zgTorderPlanbom = (ZgTorderPlanbom)zgTorderPlanbomExBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo bo) {
		this.zgTorderPlanbomExBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderPlanbom;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
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
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorderPlanbom.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderPlanbomExBo.findByPageRequest(pageRequest);
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
		zgTorderPlanbomExBo.save(zgTorderPlanbom);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTorderPlanbomExBo.update(this.zgTorderPlanbom);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderPlanbomExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}
	
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
	
	private String orderPlanId;
	
	public String getOrderPlanId() {
		return orderPlanId;
	}

	public void setOrderPlanId(String orderPlanId) {
		this.orderPlanId = orderPlanId;
	}

	public String findBomList() throws ServletException, IOException {
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomList(orderPlanId);
		this.getRequest().setAttribute("bomList", list);
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		ZgTorderPlan entity = this.zgTorderPlanBo.getById(orderPlanId);
		this.getRequest().setAttribute("zgTorderPlan", entity);
		this.getRequest().setAttribute("count", list.size());
		String sortColumn = this.getRequest().getParameter("sortColumn");
		String isAsc = this.getRequest().getParameter("isAsc");
		this.getRequest().setAttribute("sortColumn", sortColumn);
		this.getRequest().setAttribute("isAsc", isAsc);
		if(!StringUtils.isBlank(sortColumn)) {
			SortBomList comparator = new SortBomList(sortColumn,StringUtils.isBlank(isAsc)?true:Boolean.valueOf(isAsc));
			Collections.sort(list, comparator);
		}
		return BOM_LIST;
	}
	
	public class SortBomList implements Comparator<ZgTorderPlanbomEx>{
		
		private String sortColumn;
		
		private Boolean isAsc;
		
		public SortBomList(String sortColumn,Boolean isAsc) {
			this.sortColumn = sortColumn;
			this.isAsc = isAsc;
		}

		public int compare(ZgTorderPlanbomEx arg0, ZgTorderPlanbomEx arg1) {
			String v1 = BeanUtils.getProperty(arg0, this.sortColumn);
			String v2 = BeanUtils.getProperty(arg1, this.sortColumn);
			int flag = 0;
			if(StringUtils.isNumeric(v1)&&StringUtils.isNumeric(v2)) {
				int iv1 = Integer.parseInt(v1);
				int iv2 = Integer.parseInt(v2);
				flag = iv1 - iv2;
			}else {
				if(StringUtils.isBlank(v1) && !StringUtils.isBlank(v2)) {
					flag = 1;
				}else if(!StringUtils.isBlank(v1) && StringUtils.isBlank(v2)) {
					flag = -1;
				}else if(StringUtils.isBlank(v1) && StringUtils.isBlank(v2)){
					flag = 0;
				}else {
					flag = v1.compareToIgnoreCase(v2);
				}
			}
			if(!this.isAsc) {
				flag = flag * -1;
			}
			return flag;
		}
		
	}
	
	public String findViewBomList() throws ServletException, IOException {
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomList(orderPlanId);
		getRequest().setAttribute("state",getRequest().getParameter("state"));
		this.getRequest().setAttribute("bomList", list);
		this.getRequest().setAttribute("orderPlanId", orderPlanId);
		this.getRequest().setAttribute("count", list.size());
	    return VIEW_BOM_LIST;
	}
	
	private List<ZgTorderPlanbom> orderPlanboms = new ArrayList<ZgTorderPlanbom>();
	
	@SuppressWarnings("unchecked")
	public void saveOrderPlan() throws IOException {
		boolean isUpdated = false;
		for(ZgTorderPlanbom obj : orderPlanboms) {
			if(null!=obj){
				ZgTorderPlanbom entity = new ZgTorderPlanbom();
				entity.setCuid(obj.getCuid());
				entity.setDepartmentId(StringUtils.trimToNull(obj.getDepartmentId()));
				entity.setUserId(StringUtils.trimToNull(obj.getUserId()));
				entity.setPlanDate(obj.getPlanDate());
				entity.setPlanStartTime(StringUtils.trimToNull(obj.getPlanStartTime()));
				entity.setPlanEndTime(StringUtils.trimToNull(obj.getPlanEndTime()));
				entity.setState("4");
				zgTorderPlanbomExBo.updateOrderPlan(entity);
				isUpdated = true;
			}
		}
		
		String orderPlanIds[]=orderPlanId.split(",");
		for(int i=0;i<orderPlanIds.length;i++){
			if(isUpdated) {
				zgTorderPlanExBo.updateOrderPlanState(orderPlanIds[i], "4");
			}
			
			//更新该领料计划的领料组 领料人
			zgTorderPlanExBo.updateOrderDeptId(orderPlanIds[i]);
		}
		
		forwardQuery("操作成功");
	}
	
	/**
	 * 对已提交后的领料计划进行保存，不修改领料计划的状态，只保存领料组，领料人等信息
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void saveViewOrderPlan() throws IOException {
		for(ZgTorderPlanbom obj : orderPlanboms) {
			ZgTorderPlanbom entity = new ZgTorderPlanbom();
			entity.setCuid(obj.getCuid());
			entity.setDepartmentId(StringUtils.trimToNull(obj.getDepartmentId()));
			entity.setUserId(StringUtils.trimToNull(obj.getUserId()));
			entity.setPlanDate(obj.getPlanDate());
			entity.setPlanStartTime(StringUtils.trimToNull(obj.getPlanStartTime()));
			entity.setPlanEndTime(StringUtils.trimToNull(obj.getPlanEndTime()));
			//entity.setState("8");
			zgTorderPlanbomExBo.updateOrderPlan1(entity);
		}
		//更新该领料计划的领料组 领料人
		zgTorderPlanExBo.updateOrderDeptId(orderPlanId);
		
		forwardQuery("操作成功");
	}
	
	public void setOrderPlanboms(List<ZgTorderPlanbom> orderPlanboms) {
		this.orderPlanboms = orderPlanboms;
	}

	public List<ZgTorderPlanbom> getOrderPlanboms() {
		return orderPlanboms;
	}

	public void setZgTorderPlanExBo(ZgTorderPlanExBo zgTorderPlanExBo) {
		this.zgTorderPlanExBo = zgTorderPlanExBo;
	}
	
	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}

	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}



}
