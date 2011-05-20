/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.virtualorg.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.materiel.base.service.ZgMaterielBo;
import com.boco.zg.virtualorg.base.model.*;
import com.boco.zg.virtualorg.base.dao.*;
import com.boco.zg.virtualorg.base.service.*;

/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */


public class ZgTvirtualorgAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/virtualorg/ZgTvirtualorg/query_ZgTvirtualorg.jsp";
	protected static final String LIST_JSP= "/zg/virtualorg/ZgTvirtualorg/list_ZgTvirtualorg.jsp";
	protected static final String QUERY_JSP_FORSELECT = "/zg/virtualorg/ZgTvirtualorg/query_ZgTvirtualorgForSelect.jsp";
	protected static final String LIST_JSP_FORSELECT= "/zg/virtualorg/ZgTvirtualorg/list_ZgTvirtualorgForSelect.jsp";
	protected static final String CREATE_JSP = "/zg/virtualorg/ZgTvirtualorg/create_ZgTvirtualorg.jsp";
	protected static final String EDIT_JSP = "/zg/virtualorg/ZgTvirtualorg/edit_ZgTvirtualorg.jsp";
	protected static final String SHOW_JSP = "/zg/virtualorg/ZgTvirtualorg/show_ZgTvirtualorg.jsp";
	
	protected static final String QUERY_JSP_FOR_EMPLOYEE = "/zg/virtualorg/ZgTvirtualorg/query_zgEmployeeForVirtualorg.jsp";
	protected static final String QUERY_JSP_FOR_EMPLOYEE_LIST="/zg/virtualorg/ZgTvirtualorg/zgEmployeeForVirtualorgList.jsp";
	
	protected static final String QUERY_JSP_FOR_VIRTUALORG = "/zg/virtualorg/ZgTvirtualorg/query_virtualorgPrincipals.jsp";
	protected static final String QUERY_JSP_FOR_VIRTUALORG_LIST = "/zg/virtualorg/ZgTvirtualorg/virtualorgPrincipalsList.jsp";
	
	protected static final String QUERY_JSP_FOR_MATERIEL = "/zg/virtualorg/ZgTvirtualorg/query_zgMaterielForVirtualorg.jsp";
	protected static final String QUERY_JSP_FOR_MATERIEL_LIST = "/zg/virtualorg/ZgTvirtualorg/zgMaterielForVirtualorgList.jsp";
	
	protected static final String VIRTUAALORG_ADD_JSP="/zg/virtualorg/ZgTvirtualorg/add_ZgTvirtualorg.jsp";
	protected static final String VIRTUAALORG_EDIT_JSP="/zg/virtualorg/ZgTvirtualorg/edit_ZgTvirtualorgForTree.jsp";
	
	protected static final String QUERY_JSP_FOR_MATERIEL_TEAM_TREE = "/zg/virtualorg/ZgTvirtualorg/materielTeamTree.jsp";
	
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/virtualorg/ZgTvirtualorg/list.do";
	
	private ZgTvirtualorgBo zgTvirtualorgBo;
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	public Map<String, List<TmdEnumevalue>> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<String, List<TmdEnumevalue>> enumMap) {
		this.enumMap = enumMap;
	}
	
	private IVmModelBo vmModelBo;
	
	private ZgMaterielBo zgMaterielBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private ZgTvirtualorg zgTvirtualorg;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTvirtualorg = new ZgTvirtualorg();
		} else {
			zgTvirtualorg = (ZgTvirtualorg)zgTvirtualorgBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTvirtualorgBo(ZgTvirtualorgBo bo) {
		this.zgTvirtualorgBo = bo;
	}	
	
	public Object getModel() {
		return zgTvirtualorg;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
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

	/** 进入查询页面 */
	public String query() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		//getRoles();
		return QUERY_JSP;
	}
	
	
	
	/** 执行搜索 领料人 */
	public String list() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String whetherParent=this.getRequest().getParameter("whetherParent");//treeCuid
		String treeCuid=this.getRequest().getParameter("treeCuid");
		if(whetherParent==null){//证明不是点击树
			whetherParent="";
		}
		pageRequest.getFilters().put("whetherParent",whetherParent);     //add custom filter
		pageRequest.getFilters().put("treeCuid",treeCuid);
		this.getRequest().setAttribute("whetherParent", pageRequest.getFilters().get("whetherParent"));
		this.getRequest().setAttribute("treeCuid", pageRequest.getFilters().get("treeCuid"));
		this.getRequest().setAttribute("pageRequest", pageRequest);
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTvirtualorg.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTvirtualorgBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	public String createEmployeeForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String virtualorgId=this.getRequest().getParameter("virtualorgId");//虚拟组ID
		this.getRequest().setAttribute("virtualorgId", virtualorgId);
		return QUERY_JSP_FOR_EMPLOYEE;
	}
	
	/**
	 * 查看领料人
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public String employeeForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page=zgTvirtualorgBo.findByRequestForEmployee(pageRequest);
		savePage(page,pageRequest);
		return QUERY_JSP_FOR_EMPLOYEE_LIST;
	}
	
	/**
	 * 插入 领料组领料人关系表 
	 * @return
	 */
	public String submitEmployeeForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String allMaterielId=this.getRequest().getParameter("allUserId");//全部的userId,是用‘，’分隔开的
		String virtualorgId=this.getRequest().getParameter("virtualorgId");//虚拟组的ID
		
		int num=repeatCountNum(allMaterielId,",");//数组的长度
	    for(int i=0;i<num;i++){
	    	String oneMaterielId=allMaterielId.substring(1, allMaterielId.indexOf("',"));
	    	zgTvirtualorgBo.insertZgTvirtualorgEmployee(virtualorgId,oneMaterielId);
	    	allMaterielId=allMaterielId.substring(allMaterielId.indexOf(",")+1);
	    }
	    
	    return this.list();
	}
	
	
	/**
	 * mainStr 字符串 
	 * subStr 字符
	 * 思路:这个方法是用来计算出数组的长度
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
	
	public String createFindByRequestForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		return QUERY_JSP_FOR_VIRTUALORG;
	}
	
	public String findByRequestForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		this.getRequest().setAttribute("pageRequest", pageRequest);
		Page page=zgTvirtualorgBo.findByRequestForVirtualorg(pageRequest);
		savePage(page,pageRequest);
		return QUERY_JSP_FOR_VIRTUALORG_LIST;
	}
	
	public String createMaterielTeamTree(){
		String orgId=this.getRequest().getParameter("orgId");
		List<ZgMaterrielVirtualorg> zgMaterrielVirtualorgList=zgTvirtualorgBo.getListById(orgId);
		StringBuffer allMatkls=new StringBuffer();
		for(int i=0;i<zgMaterrielVirtualorgList.size();i++){
			ZgMaterrielVirtualorg zgMaterrielVirtualorg=zgMaterrielVirtualorgList.get(i);
			if(i!=0)
				allMatkls.append(",");
			allMatkls.append(zgMaterrielVirtualorg.getMatkl());
		}
		this.getRequest().setAttribute("allMatkls", allMatkls.toString()+",");
		return QUERY_JSP_FOR_MATERIEL_TEAM_TREE;
		
	}
	
	
	public String queryMateridel(){
		String orgId=this.getRequest().getParameter("orgId");
		getRequest().setAttribute("orgId", orgId);
		return QUERY_JSP_FORSELECT;
		
	}
	
	public String listMateridel(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgMaterielBo.listMateridel(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP_FORSELECT;
		
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
		zgTvirtualorgBo.save(zgTvirtualorg);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTvirtualorgBo.update(this.zgTvirtualorg);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTvirtualorgBo.removeById((java.lang.String)params.get("id"));
		}
		
//		//返回list
//		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
//		String whetherParent=this.getRequest().getParameter("whetherParent");//treeCuid
//		String treeCuid=this.getRequest().getParameter("treeCuid");
//		if(whetherParent==null){//证明不是点击树
//			whetherParent="";
//		}
//		pageRequest.getFilters().put("whetherParent",whetherParent);     //add custom filter
//		pageRequest.getFilters().put("treeCuid",treeCuid);
//		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTvirtualorg.BM_CLASS_ID,super.getSessionUserId()));
//		Page page = zgTvirtualorgBo.findByPageRequest(pageRequest);
//		savePage(page,pageRequest);
		return this.list();
	}

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}
	
	public String addVirtualorg(){
		String orgId=getRequest().getParameter("orgId");
		String orgName= getRequest().getParameter("orgName");
		getRequest().setAttribute("orgId", orgId);
		getRequest().setAttribute("orgName", orgName);
		return VIRTUAALORG_ADD_JSP;
	}
//	public void saveVirtualorg()throws IOException{
//		zgTvirtualorgBo.save(zgTvirtualorg);
//	//	forwardQuery("操作成功");
//	}
//	public void delVirtualorg(){
//		String id=getRequest().getParameter("orgId");
//		zgTvirtualorgBo.removeVirtualById(id);
//	}
	public String editVirtualorg(){
		String cuid=getRequest().getParameter("cuid");
		ZgTvirtualorg entity=zgTvirtualorgBo.getByCuid(cuid);
//		zgTvirtualorg=zgTvirtualorgBo.getByCuid(cuid);
//		String labelCn=entity.getLabelCn();
//		String note=entity.getNote();
//		String orgId=entity.getOrgId();
//		String type=entity.getType();
//		String orgName=getRequest().getParameter("orgName");
		getRequest().setAttribute("cuid", cuid);
		getRequest().setAttribute("labelCn", entity.getLabelCn());
		getRequest().setAttribute("orgId", entity.getOrgId());
		getRequest().setAttribute("orgName", getRequest().getParameter("orgName"));
		getRequest().setAttribute("note", entity.getNote());
		getRequest().setAttribute("type", entity.getType());
		return VIRTUAALORG_EDIT_JSP;
	}
	public void updateVirtualorg()throws IOException{
		zgTvirtualorgBo.update(zgTvirtualorg);
	//	forwardQuery("操作成功");
	}

	public ZgMaterielBo getZgMaterielBo() {
		return zgMaterielBo;
	}

	public void setZgMaterielBo(ZgMaterielBo zgMaterielBo) {
		this.zgMaterielBo = zgMaterielBo;
	}
}
