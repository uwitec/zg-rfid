/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.RelatedModel;
import javacommon.base.service.IVmModelBo;
import javacommon.util.StringHelper;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.model.BMAttrMeta;
import com.boco.frame.meta.service.FwOrganizationExBo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwEmployeeOrganazation;
import com.boco.frame.sys.base.model.FwOperatorRole;
import com.boco.frame.sys.base.model.FwOperatorLog;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.frame.sys.base.model.FwRole;
import com.boco.frame.sys.base.model.FwRoleMenu;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwEmployeeOrganazationBo;
import com.boco.frame.sys.base.service.FwOperatorRoleBo;
import com.boco.frame.sys.base.service.FwOperatorLogBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.base.service.FwRoleBo;
import com.boco.frame.sys.base.service.FwRoleMenuBo;
import com.boco.frame.sys.service.ZgBomCarBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwEmployeeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//frame/sys/FwEmployee/query_FwEmployee.jsp";
	
	
	protected static final String LIST_JSP= "//frame/sys/FwEmployee/list_FwEmployee.jsp";
	
	protected static final String CREATE_JSP = "//frame/sys/FwEmployee/create_FwEmployee.jsp";
	protected static final String EDIT_JSP = "//frame/sys/FwEmployee/edit_FwEmployee.jsp";
	protected static final String SHOW_JSP = "//frame/sys/FwEmployee/show_FwEmployee.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//frame/sys/FwEmployee/list.do";
	protected static final String QUERY_ACTION = "!//frame/sys/FwEmployee/query.do";

	private static final String SELF_EDIT_JSP = "//frame/sys/FwEmployee/self_edit_FwEmployee.jsp";
	
	private FwEmployeeBo fwEmployeeBo;
	
	private FwRoleBo fwRoleBo;
	
	private FwOperatorRoleBo fwOperatorRoleBo;
	
	private FwOrganizationExBo fwOrganizationExBo;
	
	private FwEmployeeOrganazationBo fwEmployeeOrganazationBo;
	
	private FwOperatorLogBo fwOperatorLogBo;
	
	private List<String> roles;
	
	private String orgId;
	
	private String parentOrgId;//父节点的ID
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setFwRoleBo(FwRoleBo fwRoleBo) {
		this.fwRoleBo = fwRoleBo;
	}
	
	public void setFwOperatorRoleBo(FwOperatorRoleBo fwOperatorRoleBo) {
		this.fwOperatorRoleBo = fwOperatorRoleBo;
	}
	
	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	public Map<String, List<TmdEnumevalue>> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<String, List<TmdEnumevalue>> enumMap) {
		this.enumMap = enumMap;
	}
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private FwEmployee fwEmployee;
	java.lang.String id = null;
	private String[] items;
	
	private FwEmployeeOrganazation fwEmployeeOrganazation;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			fwEmployee = new FwEmployee();
		} else {
			fwEmployee = (FwEmployee)fwEmployeeBo.getById(id);
		}
		enumMap.put("SEX", vmModelBo.getEnumValue("SEX"));		
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setFwEmployeeBo(FwEmployeeBo bo) {
		this.fwEmployeeBo = bo;
	}	
	
	public Object getModel() {
		return fwEmployee;
	}
	
	public Object getModel1(){
		return fwEmployeeOrganazation;
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
		getRequest().setAttribute("orgId", orgId);
		if(!isNullOrEmptyString(orgId)){//用于添加组织机构的时候生成默认的上级机构信息
			FwOrganization fwOrganization=(FwOrganization)fwOrganizationExBo.getById(orgId);
			String orgName = fwOrganization.getLabelCn();
			getRequest().setAttribute("orgName", orgName);
		}
		return QUERY_JSP;
	}
	
	
	
	
	
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);

		String orgId=getRequest().getParameter("orgId");
		if(!isNullOrEmptyString(orgId)){
			pageRequest.getFilters().put("orgId", orgId);
		}
		
		//Map attrMap = vmModelBo.getAttrsByUser(fwEmployee.BM_CLASS_ID,super.getSessionUserId());
		Page page = fwEmployeeBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		getRequest().setAttribute("orgId", orgId);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String orgId = this.getRequest().getParameter("orgId");
		if(!isNullOrEmptyString(orgId)){//用于添加组织机构的时候生成默认的上级机构信息
			FwOrganization fwOrganization=(FwOrganization)fwOrganizationExBo.getById(orgId);
			String orgName = fwOrganization.getLabelCn();
			getRequest().setAttribute("orgName", orgName);
			fwEmployee.setOrgId(orgId);
			RelatedModel model = new RelatedModel(FwOrganization.TABLE_ALIAS,orgId,orgName);
			model.setValue(orgName);
			fwEmployee.setOrgId_related(model);
			this.getRequest().setAttribute("model", fwEmployee);
		}
		List<FwRole> fwRoleList = this.fwRoleBo.findAll();
		this.getRequest().setAttribute("fwRoleList", fwRoleList);
		return CREATE_JSP;
	}
	
	/** 保存新增对象 
	 * @throws IOException */
	public void save() throws IOException {
		fwEmployeeBo.save(fwEmployee);
		String orgIds = this.getRequest().getParameter("manageOrgIds");

		if(roles!=null  && roles.size()>0){
			for(String role:roles){
				FwOperatorRole fwOperatorRole = new FwOperatorRole();
				fwOperatorRole.setRoleId(role);
				fwOperatorRole.setOperatorId(fwEmployee.getUserId());
				this.fwOperatorRoleBo.save(fwOperatorRole);
			}
		}
		
		if(!StringHelper.isEmpty(orgIds)){
			String[] orgArr=orgIds.split(",");
			//进行循环插入
			for(String orgId:orgArr){
				fwOrganizationExBo.insertFwOrganizationManager(orgId,fwEmployee.getUserId());
			}
		}
		
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(fwEmployee.getCuid());
		fwOperatorLog.setTargetName(fwEmployee.getLabelCn());
		fwOperatorLog.setAction("add");
		fwOperatorLog.setOperatorType("雇员管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		forwardQuery("操作成功");
	}
	
	/**进入更新页面*/
	public String edit() {
		
		String code=getRequest().getParameter("code");
		getRequest().setAttribute("code", code);
		
		String cuid = this.fwEmployee.getCuid();
		FwOperatorRole entity = new FwOperatorRole();
		entity.setOperatorId(cuid);
		List<FwOperatorRole> fwOperatorRolelist = this.fwOperatorRoleBo.findByProperty(entity);
		Map fwOperatorRoleMap = new HashMap();
		for(FwOperatorRole fwOperatorRole : fwOperatorRolelist){
			fwOperatorRoleMap.put(fwOperatorRole.getRoleId(), fwOperatorRole.getRoleId());
		}
		this.getRequest().setAttribute("fwOperatorRoleMap", fwOperatorRoleMap);
		
		List<FwRole> fwRoleList = this.fwRoleBo.findAll();
		this.getRequest().setAttribute("fwRoleList", fwRoleList);
		
		List<FwOrganizationManager> mangerOrgList=fwOrganizationExBo.getManagerListByUserId(fwEmployee.getCuid());
		if(mangerOrgList.size()>0){
			String orgIds="";
			String orgNames="";
			for(FwOrganizationManager manager:mangerOrgList){
				orgIds=orgIds+manager.getOrgId()+",";
				orgNames=orgNames+manager.getOrgName()+",";
			}
			if(orgIds.endsWith(",")){
				orgIds=orgIds.substring(0,orgIds.length()-1);
				orgNames=orgNames.substring(0,orgNames.length()-1);
			}
			getRequest().setAttribute("orgIds", orgIds);
			getRequest().setAttribute("orgNames", orgNames);
		}
		
		return EDIT_JSP;
	}
	
	/**进入员工自己的个人信息更新界面*/
	public String selfInfoEdit() {
		getRequest().setAttribute("flag",getRequest().getParameter("msg"));
		id=getSessionOperatorId();
		fwEmployee = (FwEmployee)fwEmployeeBo.getById(id);
		
		String cuid = this.fwEmployee.getCuid();
		FwOperatorRole entity = new FwOperatorRole();
		entity.setOperatorId(cuid);
		List<FwOperatorRole> fwOperatorRolelist = this.fwOperatorRoleBo.findByProperty(entity);
		Map fwOperatorRoleMap = new HashMap();
		for(FwOperatorRole fwOperatorRole : fwOperatorRolelist){
			fwOperatorRoleMap.put(fwOperatorRole.getRoleId(), fwOperatorRole.getRoleId());
		}
		this.getRequest().setAttribute("fwOperatorRoleMap", fwOperatorRoleMap);
		getRequest().setAttribute("fwOperatorRolelist", fwOperatorRolelist);
		
		List<FwRole> fwRoleList = this.fwRoleBo.findAll();
		this.getRequest().setAttribute("fwRoleList", fwRoleList);
		
//		fw
		List<FwOrganizationManager> mangerOrgList=fwOrganizationExBo.getManagerListByUserId(fwEmployee.getCuid());
		if(mangerOrgList.size()>0){
			String orgIds="";
			String orgNames="";
			for(FwOrganizationManager manager:mangerOrgList){
				orgIds=orgIds+manager.getOrgId()+",";
				orgNames=orgNames+manager.getOrgName()+",";
			}
			if(orgIds.endsWith(",")){
				orgIds=orgIds.substring(0,orgIds.length()-1);
				orgNames=orgNames.substring(0,orgNames.length()-1);
			}
			getRequest().setAttribute("orgIds", orgIds);
			getRequest().setAttribute("orgNames", orgNames);
		}
		
		//拿到某人的全部部门
//		List<FwOrganization> fwOrganizationList1=fwEmployeeBo.selectPartAllNameByOnePeople(cuid);
//		StringBuffer partment_label=new StringBuffer();
//		StringBuffer partment_value=new StringBuffer();
//		for(int i=0;i<fwOrganizationList1.size();i++){
//			FwOrganization fwOrganization=fwOrganizationList1.get(i);
//			if(i!=0){
//				partment_label.append(",");
//				partment_value.append(",");
//			}
//			partment_label.append(fwOrganization.getLabelCn());
//			partment_value.append(fwOrganization.getCuid());
//		}
//		fwEmployee.getOrgId_related().setValue(partment_label.toString());
//		fwEmployee.setOrgId(partment_value.toString());
		//this.getRequest().setAttribute("partment_label",partment_label.toString());
		//this.getRequest().setAttribute("partment_value",partment_value.toString());
		return SELF_EDIT_JSP;
	}
	
	/**保存更新对象
	 * @throws IOException */
	public void update() throws IOException {
		fwEmployeeBo.update(this.fwEmployee);
		
		String orgIds = this.getRequest().getParameter("manageOrgIds");
		
		fwOperatorRoleBo.removeByOperateId(fwEmployee.getCuid());
		if(roles!=null  && roles.size()>0){
			for(String role:roles){
				FwOperatorRole fwOperatorRole = new FwOperatorRole();
				fwOperatorRole.setRoleId(role);
				fwOperatorRole.setOperatorId(fwEmployee.getCuid());
				this.fwOperatorRoleBo.save(fwOperatorRole);
			}
		}
		
		fwOrganizationExBo.removeManagerOrgByOperateId(fwEmployee.getCuid());
		if(!StringHelper.isEmpty(orgIds)){
			String[] orgArr=orgIds.split(",");
			//进行循环插入
			for(String orgId:orgArr){
				fwOrganizationExBo.insertFwOrganizationManager(orgId,fwEmployee.getCuid());
			}
		}
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(fwEmployee.getCuid());
		fwOperatorLog.setTargetName(fwEmployee.getLabelCn());
		fwOperatorLog.setAction("update");
		fwOperatorLog.setOperatorType("雇员管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		
		forwardQuery("操作成功");
		//return LIST_ACTION+"?orgId=" +fwEmployee.getOrgId();
	}
	
	public String updateSelfIno() throws IOException{
		fwEmployeeBo.updateSelfInfo(this.fwEmployee);
		//rendHtml("alert('aaa')");
		return "!//frame/sys/FwEmployee/selfInfoEdit.do?msg=success";
	}
	
	/**删除对象*/
	public String delete() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
	    Map map=	pageRequest.getFilters();
	    
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String id=(java.lang.String)params.get("id");
			
			fwEmployee=fwEmployeeBo.getById(id);
			
			fwEmployeeBo.removeById((java.lang.String)params.get("id"));
			
			
			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			fwOperatorLog.setCreateId(getSessionOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(fwEmployee.getCuid());
			fwOperatorLog.setTargetName(fwEmployee.getLabelCn());
			fwOperatorLog.setAction("delete");
			fwOperatorLog.setOperatorType("雇员管理");
			
			fwOperatorLogBo.save(fwOperatorLog);
		}
		return QUERY_ACTION+"?orgId="+orgId;
	}

	public void setFwEmployeeOrganazationBo(
			FwEmployeeOrganazationBo fwEmployeeOrganazationBo) {
		this.fwEmployeeOrganazationBo = fwEmployeeOrganazationBo;
	}
	
	public String getTheFisrtDept(String treeIds){
		this.getRequest().setAttribute("treeIds", treeIds);
		String[] orgIds = treeIds.split(",");
		String orgId = orgIds[1];
		FwOrganization fwOrganization = new FwOrganization();
		fwOrganization.setCuid(orgId);
		List<FwOrganization> list = fwOrganizationExBo.findByProperty(fwOrganization);
		String labelCn ="";
		for(FwOrganization fwOrganization2 : list){
			labelCn = fwOrganization2.getLabelCn();
		}
		return labelCn;
	}

	public void setFwEmployeeOrganazation(
			FwEmployeeOrganazation fwEmployeeOrganazation) {
		this.fwEmployeeOrganazation = fwEmployeeOrganazation;
	}

	

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public FwOperatorLogBo getFwOperatorLogBo() {
		return fwOperatorLogBo;
	}

	public void setFwOperatorLogBo(FwOperatorLogBo fwOperatorLogBo) {
		this.fwOperatorLogBo = fwOperatorLogBo;
	}
	
	

	

}
