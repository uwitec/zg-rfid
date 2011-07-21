/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.model.RelatedModel;
import javacommon.base.service.IVmModelBo;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.sys.base.model.FwOperatorLog;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.frame.sys.base.service.FwOperatorLogBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.frame.sys.service.FwOrganizationExBo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author wengq
 * @version 1.0
 * @since 1.0
 */


public class FwOrganizationAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/sys/FwOrganization/query_FwOrganization.jsp";
	protected static final String LIST_JSP= "/frame/sys/FwOrganization/list_FwOrganization.jsp";
	protected static final String CREATE_JSP = "/frame/sys/FwOrganization/create_FwOrganization.jsp";
	
	protected static final String QUERY_FOPS = "/frame/sys/FwOrganization/query_fwOrganizationPrincipals.jsp";
	protected static final String LIST_FOPS = "/frame/sys/FwOrganization/fwOrganizationPrincipalsList.jsp";
	
	protected static final String EDIT_JSP = "/frame/sys/FwOrganization/edit_FwOrganization.jsp";
	protected static final String SHOW_JSP = "/frame/sys/FwOrganization/show_FwOrganization.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/FwOrganization/list.do";
	
	private FwOrganizationExBo fwOrganizationExBo;
	
	private IVmModelBo vmModelBo;
	
	private FwOperatorLogBo fwOperatorLogBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private FwOrganization fwOrganization;
	java.lang.String id = null;
	java.lang.String parentOrgId=null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			fwOrganization = new FwOrganization();
			if(!isNullOrEmptyString(parentOrgId)){//用于添加组织机构的时候生成默认的上级机构信息
				FwOrganization paraentOrg=(FwOrganization)fwOrganizationExBo.getById(parentOrgId);
				RelatedModel parentOrgId_related = new RelatedModel(FwOrganization.TABLE_ALIAS,paraentOrg.getCuid(),paraentOrg.getLabelValue());
				parentOrgId_related.setValue(paraentOrg.getLabelValue());
				fwOrganization.setParentOrgId_related(parentOrgId_related);
				fwOrganization.setParentOrgId(paraentOrg.getCuid());
				fwOrganization.setLevelNum(paraentOrg.getLevelNum()+1);
			}
		} else {
			fwOrganization = (FwOrganization)fwOrganizationExBo.getById(id);
		}
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
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setFwOrganizationExBo(FwOrganizationExBo fwOrganizationExBo) {
		this.fwOrganizationExBo = fwOrganizationExBo;
	}
	
	public Object getModel() {
		return fwOrganization;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}
	
	public void setParentOrgId(java.lang.String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 进入查询页面 */
	public String query() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		getRequest().setAttribute("parentOrgId", parentOrgId);
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		//String parentOrgId=getRequest().getParameter("parentOrgId");
		//pageRequest.getFilters().put("parentOrgId", getRequest().getParameter("parentOrgId"));
		
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(fwOrganization.BM_CLASS_ID,super.getSessionUserId()));
		Page page = fwOrganizationExBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		
		getRequest().setAttribute("parentOrgId", pageRequest.getFilters().get("parentOrgId"));
		
		return LIST_JSP;
	}
	
	/*
	 * wjz,进入查询负责人列表页面
	 */
	public String queryForFwOrganizationPrincipals(){
		String employeesId=this.getRequest().getParameter("principalsId");
		this.getRequest().setAttribute("employeesId", employeesId);
		return QUERY_FOPS;
	}
	
	/*
	 * wjz,查询负责人列表，返回
	 */
	public String findPrincipalsList(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page=fwOrganizationExBo.findPrincipalsList(pageRequest);
		savePage(page,pageRequest);
		return LIST_FOPS;
	}
	
	@SuppressWarnings("unchecked")
	public String navTree() {
		String root = this.getRequest().getParameter("root");
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8"); 
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.print(fwOrganizationExBo.findOrganizationTree());
			out.flush();
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	/** 查看对象*/
	public String show() {
		//查询出本组的全部负责人
		findTheAllEmpleeByOrgId();
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 
	 * @throws IOException */
	public void save() throws IOException {
		String cuid=(String) fwOrganizationExBo.save(fwOrganization);
		if(!fwOrganization.getPrincipalsId().equals("")){
			//插入 组织机构负责人表　，这里要循环插入
			String employeesId=fwOrganization.getPrincipalsId()+",";//雇员们ID,他们是用,分开的
			String organizationCuid=fwOrganization.getCuid();//组织的主键
			int arrayNum=repeatCountNum(employeesId,",");//数组的长度
			//进行循环插入
			for(int i=0;i<arrayNum;i++){
				String oneEmployeeId=employeesId.substring(1, employeesId.indexOf("',"));
				//一个一个来插
				fwOrganizationExBo.insertFwOrganizationManager(organizationCuid,oneEmployeeId);
				employeesId=employeesId.substring(employeesId.indexOf(",")+1);
			}
		}
		

		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(cuid);
		fwOperatorLog.setTargetName(fwOrganization.getLabelCn());
		fwOperatorLog.setAction("add");
		fwOperatorLog.setOperatorType("机构管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		forwardQuery("操作成功");
//		return LIST_ACTION+"?parentOrgId=" +fwOrganization.getParentOrgId();	
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
	
	/**进入更新页面*/
	public String edit() {
		//查出本组的全部负责人
		findTheAllEmpleeByOrgId();
		return EDIT_JSP;
	}
	
	//查询出本组的全部负责人
	public void findTheAllEmpleeByOrgId(){
		//组织的id
		List<FwOrganizationManager> fwOrganizationManagerList=fwOrganizationExBo.findEmployeesByOrgId(id);
		StringBuffer employeeIds=new StringBuffer();
		StringBuffer employeeNames=new StringBuffer();
		if(fwOrganizationManagerList.size()!=0){
			for(int i=0;i<fwOrganizationManagerList.size();i++){
				FwOrganizationManager fwOrganizationManager=fwOrganizationManagerList.get(i);
				employeeIds.append("'"+fwOrganizationManager.getEmployeeId()+"',");
				employeeNames.append(fwOrganizationManager.getEmployeeName()+",");
			}
			String allemployeeIds=employeeIds.toString().substring(0, employeeIds.toString().lastIndexOf(","));
			String allemployeeNames=employeeNames.toString().substring(0, employeeNames.toString().lastIndexOf(","));
			fwOrganization.setPrincipalsId(allemployeeIds);
			fwOrganization.setPrincipalsName(allemployeeNames);
		}
	}
	
	/**保存更新对象
	 * @throws IOException */
	public void update() throws IOException {
		fwOrganizationExBo.update(this.fwOrganization);//FW_ORGANIZATION_MANAGER
		String organizationCuid=this.fwOrganization.getCuid();//组织的主键
		//先把某组的负责人全部删除了
		fwOrganizationExBo.deleteFwOrganizationManagerByOrgId(organizationCuid);
		
		if(!fwOrganization.getPrincipalsId().equals("")){
			//再插入
			//插入 组织机构负责人表　，这里要循环插入
			String employeesId=this.fwOrganization.getPrincipalsId()+",";//雇员们ID,他们是用,分开的
			int arrayNum=repeatCountNum(employeesId,",");//数组的长度
			//进行循环插入
			for(int i=0;i<arrayNum;i++){
				String oneEmployeeId=employeesId.substring(1, employeesId.indexOf("',"));
				//一个一个来插
				fwOrganizationExBo.insertFwOrganizationManager(organizationCuid,oneEmployeeId);
				employeesId=employeesId.substring(employeesId.indexOf(",")+1);
			}
		}
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(fwOrganization.getCuid());
		fwOperatorLog.setTargetName(fwOrganization.getLabelCn());
		fwOperatorLog.setAction("update");
		fwOperatorLog.setOperatorType("机构管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		forwardQuery("操作成功");
	}
	
	/**删除对象*/
	public String delete() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
	    Map map=	pageRequest.getFilters();
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			
			fwOrganization=fwOrganizationBo.getById((java.lang.String)params.get("id"));
			
			fwOrganizationExBo.removeById((java.lang.String)params.get("id"));
			//删除中间表的数据
			fwOrganizationExBo.deleteFwOrganizationManagerByOrgId((java.lang.String)params.get("id"));
			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			fwOperatorLog.setCreateId(getSessionOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(fwOrganization.getCuid());
			fwOperatorLog.setTargetName(fwOrganization.getLabelCn());
			fwOperatorLog.setAction("delete");
			fwOperatorLog.setOperatorType("机构管理");
			
			fwOperatorLogBo.save(fwOperatorLog);
			
		}
		
		return LIST_ACTION+"?parentOrgId=" +map.get("parentOrgId");
	}

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}

	public FwOperatorLogBo getFwOperatorLogBo() {
		return fwOperatorLogBo;
	}

	public void setFwOperatorLogBo(FwOperatorLogBo fwOperatorLogBo) {
		this.fwOperatorLogBo = fwOperatorLogBo;
	}

	

	

}
