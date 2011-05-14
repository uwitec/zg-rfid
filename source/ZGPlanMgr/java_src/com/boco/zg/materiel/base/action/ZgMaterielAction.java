/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.materiel.base.action;

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
import com.boco.frame.sys.base.model.ZgMateriel;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.mail.handlers.message_rfc822;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.materiel.base.model.*;
import com.boco.zg.materiel.base.dao.*;
import com.boco.zg.materiel.base.service.*;
import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;
import com.boco.zg.virtualorg.base.service.ZgTvirtualorgBo;

/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */


public class ZgMaterielAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/materiel/ZgMateriel/query_ZgMateriel.jsp";
	protected static final String LIST_JSP= "/zg/materiel/ZgMateriel/list_ZgMateriel.jsp";
	protected static final String CREATE_JSP = "/zg/materiel/ZgMateriel/create_ZgMateriel.jsp";
	protected static final String EDIT_JSP = "/zg/materiel/ZgMateriel/edit_ZgMateriel.jsp";
	protected static final String SHOW_JSP = "/zg/materiel/ZgMateriel/show_ZgMateriel.jsp";
	protected static final String QUERY_ADVNCE_JSP = "/zg/materiel/ZgMateriel/query_advance_ZgMateriel.jsp";
	protected static final String LIST_ADVANCE_JSP= "/zg/materiel/ZgMateriel/list_advance_ZgMateriel.jsp";
	
	protected static final String ADD_MATERIEL_JSP="/zg/materiel/ZgMateriel/add_materiel.jsp";
	protected static final String EDIT_MATERIEL_JSP="/zg/materiel/ZgMateriel/edit_materiel.jsp";
	
	protected static final String QUERY_JSP_FOR_MATERIEL = "/zg/virtualorg/ZgTvirtualorg/query_zgMaterielForVirtualorg.jsp";
	protected static final String QUERY_JSP_FOR_MATERIEL_LIST = "/zg/virtualorg/ZgTvirtualorg/zgMaterielForVirtualorgList.jsp";
	
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/materiel/ZgMateriel/list.do";
	
	private ZgMaterielBo zgMaterielBo;
	private ZgTvirtualorgBo zgTvirtualorgBo;
	public void setZgTvirtualorgBo(ZgTvirtualorgBo zgTvirtualorgBo) {
		this.zgTvirtualorgBo = zgTvirtualorgBo;
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
	
	private ZgMateriel zgMateriel;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgMateriel = new ZgMateriel();
		} else {
			zgMateriel = (ZgMateriel)zgMaterielBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgMaterielBo(ZgMaterielBo bo) {
		this.zgMaterielBo = bo;
	}	
	
	public Object getModel() {
		return zgMateriel;
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
		String whetherParent=this.getRequest().getParameter("whetherParent");//treeCuid
		String treeCuid=this.getRequest().getParameter("treeCuid");
		if(whetherParent==null){//证明不是点击树
			whetherParent="";
		}
		pageRequest.getFilters().put("whetherParent",whetherParent);     //add custom filter
		pageRequest.getFilters().put("treeCuid",treeCuid);
		this.getRequest().setAttribute("whetherParent", pageRequest.getFilters().get("whetherParent"));
		this.getRequest().setAttribute("treeCuid", pageRequest.getFilters().get("treeCuid"));
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgMateriel.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgMaterielBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	

	public String createMaterielForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String virtualorgId=this.getRequest().getParameter("virtualorgId");//虚拟组ID
		this.getRequest().setAttribute("virtualorgId", virtualorgId);
		return QUERY_JSP_FOR_MATERIEL;
	}
	
	/**
	 * 查看物料组
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public String materielForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page=zgTvirtualorgBo.findByRequestForMateriel(pageRequest);
		savePage(page,pageRequest);
		return QUERY_JSP_FOR_MATERIEL_LIST;
	}
	
	/**
	 * 插入 领料组物料组关系表 
	 * @return
	 */
	public String submitMaterielForVirtualorg(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String allMaterielId=this.getRequest().getParameter("allMaterielId");//全部的userId,是用‘，’分隔开的
		String virtualorgId=this.getRequest().getParameter("virtualorgId");//虚拟组的ID
		
		//先删除掉之前的
//		zgTvirtualorgBo.deleteZgMaterielVirtualorgByOrgId(virtualorgId);
		
		int num=repeatCountNum(allMaterielId,",");//数组的长度
		String arrayString[]=new String[num];
	    for(int i=0;i<num;i++){
	    	String oneMaterielId=allMaterielId.substring(0, allMaterielId.indexOf(","));
	    	zgTvirtualorgBo.insertZgTvirtualorgMateriel(virtualorgId,oneMaterielId);
	    	allMaterielId=allMaterielId.substring(allMaterielId.indexOf(",")+1);
	    }
	    
	    return list();
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
	
	public String updateMaterielAndInsertZgMaterielVirtualorg() throws IOException {
		
		String materielCuid=this.getRequest().getParameter("materielCuid");//null
		String materielId=this.getRequest().getParameter("materielId");
		String storageId=this.getRequest().getParameter("storageId");
		String materielName=this.getRequest().getParameter("materielName");
		String materielStorageId=this.getRequest().getParameter("materielStorageId");
		
		String materielVirtualorgId=this.getRequest().getParameter("materielVirtualorgId");//中间表的主键  null
		
		ZgMateriel zgMateriel=new ZgMateriel();
		zgMateriel.setCuid(materielCuid);
		zgMateriel.setId(materielId);
		zgMateriel.setParentId(storageId);
		zgMateriel.setMaterielName(materielName);
		zgMateriel.setLgort(materielStorageId);
		
		
		String allVirtualorgId=this.getRequest().getParameter("orgId");//全部的虚拟组ID
		zgMaterielBo.updateMaterielAndInsertZgMaterielVirtualorg(zgMateriel,materielVirtualorgId,allVirtualorgId);
		forwardQuery("操作成功");
		return list();
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
		zgMaterielBo.save(zgMateriel);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		String materielVirtualorgId=this.getRequest().getParameter("materielVirtualorgId");//拿到 物料组虚拟领料组关系表 主键
		ZgMaterrielVirtualorg zgMaterrielVirtualorg=zgMaterielBo.findOneInfoMaterielVirtualorgCuid(materielVirtualorgId);
		this.getRequest().setAttribute("materielVirtualorgId", materielVirtualorgId);
		this.getRequest().setAttribute("zgMaterrielVirtualorg", zgMaterrielVirtualorg);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgMaterielBo.update(this.zgMateriel);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgMaterielBo.removeVorgById((java.lang.String)params.get("id"),(String)params.get("vorgId"));//删除ZG_MATERIEL_VIRTUALORG关系表中的记录
		}
		//返回list
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String whetherParent=this.getRequest().getParameter("whetherParent");//treeCuid
		String treeCuid=this.getRequest().getParameter("treeCuid");
		if(whetherParent==null){//证明不是点击树
			whetherParent="";
		}
		pageRequest.getFilters().put("whetherParent",whetherParent);     //add custom filter
		pageRequest.getFilters().put("treeCuid",treeCuid);
		getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgMateriel.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgMaterielBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	
	public String addMateriel(){
		String parentOrgId=getRequest().getParameter("parentOrgId");
		String parentName=getRequest().getParameter("parentOrgName");
		getRequest().setAttribute("parentOrgId", parentOrgId);
		getRequest().setAttribute("parentName", parentName);
		//获取父物料组的仓库列表
		List<Map> list=zgMaterielBo.findLgortListById(parentOrgId);
		getRequest().setAttribute("mList", list);
		return ADD_MATERIEL_JSP;
	}
	
	
	public String editMateriel(){
		String id=getRequest().getParameter("id");
		zgMateriel=zgMaterielBo.getByCuid(id);
		getRequest().setAttribute("parentName", getRequest().getParameter("parentName"));
		getRequest().setAttribute("id", zgMateriel.getId());
		
		getRequest().setAttribute("type", zgMateriel.getType());
		getRequest().setAttribute("lgort", zgMateriel.getLgort());
		getRequest().setAttribute("id",id );
		getRequest().setAttribute("materielName",zgMateriel.getMaterielName());
		
		ZgMateriel parentMat=zgMaterielBo.getById(zgMateriel.getParentId());
		getRequest().setAttribute("parentId", parentMat.getId());
		
		//获取父结点的仓库列表
		List<Map> parentLgortList=zgMaterielBo.findLgortListById(parentMat.getId());
		
		//该物料组的仓库列表
		List<Map>	lgortList=zgMaterielBo.findLgortListById(id);
		
		for(Map mat:parentLgortList){
			for(Map temp:lgortList){
				if(temp.get("LGORT").toString().equals(mat.get("LGORT").toString())){
					mat.put("checked", "check");
					break;
				}
			}
		}
		
		getRequest().setAttribute("parentLgortList", parentLgortList);
		
//		getRequest().setAttribute("parentName", getRequest().getParameter("orgName"));
		return EDIT_MATERIEL_JSP;
	}
	
	public String queryForAdvance(){
		return QUERY_ADVNCE_JSP;
	}
	
	public String listForAdvance(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		pageRequest.getFilters().put("advance", "1");
		Page page = zgMaterielBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_ADVANCE_JSP;
	}
	
	public void addAdvance() throws IOException{
		String matId=getRequest().getParameter("matId");
		zgMaterielBo.addAddvanceMat(matId);
		rendHtml("window.close();");
	}
	
	public void delAdvance() throws IOException{
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgMaterielBo.removeById((java.lang.String)params.get("id"));//删除ZG_MATERIEL_VIRTUALORG关系表中的记录
		}
	}
	
	

}
