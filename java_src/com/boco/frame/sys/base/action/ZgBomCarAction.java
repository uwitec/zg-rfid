package com.boco.frame.sys.base.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.sys.base.model.ZgBomCar;
import com.boco.frame.sys.service.ZgBomCarBo;
import com.boco.zg.bom.base.model.ZgCarInfo;
import com.boco.zg.bom.base.service.ZgCarInfoManager;
//import com.boco.zg.bom.base.service.ZgCarInfoManager;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ZgBomCarAction extends BaseStruts2Action implements Preparable,ModelDriven{

	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	protected static final String QUERY_FOR_STORECAR_JSP = "//zg/storeAndCar/query_storeCar.jsp";
	protected static final String LIST_FOR_STORECAR_JSP = "//zg/storeAndCar/list_storeCar.jsp";
	protected static final String EDIT_JSP="//zg/storeAndCar/edit_storeCar.jsp";
	protected static final String QUERY_FORCAR="//zg/storeAndCar/query_forCar.jsp";
	protected static final String LIST_FORCAR="//zg/storeAndCar/list_forCar.jsp";
	protected static final String LIST_ACTION="!/frame/sys/ZgBomCar/queryForStoreCar.do";
	protected static final String QUERY_FORCARBOMSET="//zg/storeAndCar/query_forCarBomSet.jsp";
	protected static final String LIST_FORCARBOMSET="//zg/storeAndCar/list_forCarBomSet.jsp";
	
	protected static final String EXPORT_STORECAR="!/frame/excel/all/DataTrans/export.do";
	
	private ZgBomCarBo zgBomCarBo;
	
	private ZgBomCar zgBomCar;
	
	private ZgCarInfoManager  zgCarInfoManager ;
	
//	private ZgCarInfoManager zgCarInfoManager;
	
	private String orgId;
	
	private String parentOrgId;//父节点的ID
	
	private String[] items;
	
	
	public void setItems(String[] items) {
		this.items = items;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setZgBomCarBo(ZgBomCarBo zgBomCarBo) {
		this.zgBomCarBo = zgBomCarBo;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 进入库存与车型关系的查询页面 */
	public String queryForStoreCar(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		//getRequest().setAttribute("orgId", orgId);
		//getRequest().setAttribute("parentOrgId", parentOrgId);//把父节点的ID也放进去
		//String parentName=zgBomCarBo.findParentOrgIdName(parentOrgId);//parentOrgId其实这个是父ID的cuid
		//String materielGroupName=zgBomCarBo.findmaterielGroupName(orgId);//parentOrgId其实这个是父ID的cuid
		//getRequest().setAttribute("parentName", parentName);
		//getRequest().setAttribute("materielGroupName", materielGroupName);
		return QUERY_FOR_STORECAR_JSP;
	}
	
	/** 执行库存与车型关系的搜索 */
	public String listForStoreCar() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String orgId=getRequest().getParameter("orgId");//materielGroupName
		if(!isNullOrEmptyString(orgId)){
			pageRequest.getFilters().put("orgId", orgId);
		}
		//这里判断是为了在跳转页的时候用到的
		if(pageRequest.getFilters().get("parentOrgId")!=null
				||pageRequest.getFilters().get("orgId")!=null
				||pageRequest.getFilters().get("idnrk")!=null
				||pageRequest.getFilters().get("carId")!=null
				||pageRequest.getFilters().get("carName")!=null
				||pageRequest.getFilters().get("bomCarState")!=null){
			String parentOrgId=pageRequest.getFilters().get("parentOrgId").toString();
			orgId=pageRequest.getFilters().get("orgId").toString();
			
			//3个查询条件
			String idnrk=pageRequest.getFilters().get("idnrk").toString(); 
			String carId=pageRequest.getFilters().get("carId").toString();
			String carName=pageRequest.getFilters().get("carName").toString();
			String bomCarState=pageRequest.getFilters().get("bomCarState").toString();
			
			getRequest().setAttribute("orgId", orgId);
			getRequest().setAttribute("parentOrgId", parentOrgId);
			
			getRequest().setAttribute("idnrk", idnrk);
			getRequest().setAttribute("carId", carId);
			getRequest().setAttribute("carName", carName);
			getRequest().setAttribute("bomCarState", bomCarState);
			
		}
		Page page = zgBomCarBo.findByPageRequestForStoreCar(pageRequest);
		savePage(page,pageRequest);
		
		return LIST_FOR_STORECAR_JSP;
	}
//	public String edit(){
//		String bomId=getRequest().getParameter("id");
//		getRequest().setAttribute("bomId", bomId);
//		zgBomCar=zgBomCarBo.getById(bomId);
//		getRequest().setAttribute("zgBomCar", zgBomCar);
//		return EDIT_JSP;
//	}
//	public void update(){
//		zgBomCarBo.update(zgBomCar);
//	}
	public String queryForCar(){
		return QUERY_FORCAR;
	}
	
	//在bom调整的时候设置车型 彭磊
	public String queryForCarBomSet(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		List<ZgCarInfo> list = zgCarInfoManager.findAll();
//		Page page = zgBomCarBo.findCarInfoByPageRequest(pageRequest);
//		savePage(page,pageRequest);
		String idnrk=getRequest().getParameter("idnrk");
		String matkl=getRequest().getParameter("matkl");
		getRequest().setAttribute("idnrk", idnrk);
		getRequest().setAttribute("matkl", matkl);
		getRequest().setAttribute("carList", list);
		return QUERY_FORCARBOMSET;
	}
	//设置车型和装载数量
	public void carSetForBom(){
		String carNumStr=getRequest().getParameter("carNum");
		String carCuid=getRequest().getParameter("carCuid");
		String idnrk=getRequest().getParameter("idnrk");
		String matkl=getRequest().getParameter("matkl");
		Long carNum=Long.parseLong(carNumStr);
		ZgBomCar entity=new ZgBomCar();
		entity.setIdnrk(idnrk);
		entity.setCarNum(carNum);
		entity.setCarinfoId(carCuid);
		entity.setMatkl(matkl);
//		zgBomCarBo.updateCarNum(entity);
		zgBomCarBo.updateCarBomNum(entity);
		
	}
	public String delete(){
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgBomCarBo.removeById((java.lang.String)params.get("id"));//只是把ZG_T_BOM(bom表)里的数据删除掉的
			//zgBomCarBo.removeByIdNotRealDet((java.lang.String)params.get("id"));//只是把ZG_T_BOM(bom表)的'车型信息表id'和'装载数量'清空而已
		}
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		String orgId=getRequest().getParameter("orgId");//materielGroupName
		if(!isNullOrEmptyString(orgId)){
			pageRequest.getFilters().put("orgId", orgId);
		}
		//这里判断是为了在跳转页的时候用到的
		if(pageRequest.getFilters().get("parentOrgId")!=null
				||pageRequest.getFilters().get("orgId")!=null
				||pageRequest.getFilters().get("idnrk")!=null
				||pageRequest.getFilters().get("carId")!=null
				||pageRequest.getFilters().get("carName")!=null){
			String parentOrgId=pageRequest.getFilters().get("parentOrgId").toString();
			orgId=pageRequest.getFilters().get("orgId").toString();
			
			//3个查询条件
			String idnrk=pageRequest.getFilters().get("idnrk").toString(); 
			String carId=pageRequest.getFilters().get("carId").toString();
			String carName=pageRequest.getFilters().get("carName").toString();
			
			getRequest().setAttribute("orgId", orgId);
			getRequest().setAttribute("parentOrgId", parentOrgId);
			
			getRequest().setAttribute("idnrk", idnrk);
			getRequest().setAttribute("carId", carId);
			getRequest().setAttribute("carName", carName);
			
		}
		Page page = zgBomCarBo.findByPageRequestForStoreCar(pageRequest);
		savePage(page,pageRequest);
		
		return LIST_FOR_STORECAR_JSP;
	}
	
	public String listForCar(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		/**查询，返回list **/
//		List<ZgCarInfo> carList=zgBomCarBo.findCarList(pageRequest);
//		getRequest().setAttribute("carList", carList);
		/**page分页查询**/
		Page page = zgBomCarBo.findCarInfoByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_FORCAR;
	}
	public String listForCarBomSet(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		Page page = zgBomCarBo.findCarInfoByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_FORCARBOMSET;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public ZgBomCar getZgBomCar() {
		return zgBomCar;
	}

	public void setZgBomCar(ZgBomCar zgBomCar) {
		this.zgBomCar = zgBomCar;
	}
//
//	public void setZgCarInfoManager(ZgCarInfoManager zgCarInfoManager) {
//		this.zgCarInfoManager = zgCarInfoManager;
//	}
//
//	
	
	
	public String exportStoreCar()
	{
		getRequest().getSession().setAttribute("ex_template","exportBomCar");
		
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("orgId", getRequest().getParameter("s_orgId").toString().equals("undefined")?"":getRequest().getParameter("s_orgId").toString());
		params.put("parentOrgId", getRequest().getParameter("s_parentOrgId").toString().equals("undefined")?"":getRequest().getParameter("s_parentOrgId").toString());
		String state=getRequest().getParameter("s_bomCarState");
		if (state.equals("8"))
		{
			params.put("carid", null);
		}
		else if (state.equals("4"))
		{
			params.put("carid", "");
		}
		else if (state.equals("0"))
		{
			params.put("carid", "1");
		}
		getRequest().getSession().setAttribute("ex_in", params);
		//return "/frame/excel/all/BomCar/export.do?template=exportBomCar"+inString;
		
		
		return EXPORT_STORECAR;
	}
	
	 /**替换字符的通用方法                           
	  * 源字串，要替换源字串,替换为的目的字串*/ 
  public static String replaceString(String s, String org, String ob) {
		String newString = " ";
		int first = 0;
		while (s.indexOf(org) != -1) {
			first = s.indexOf(org);
			if (first != s.length()) {
				newString = newString + s.substring(0, first) + ob;
				s = s.substring(first + org.length(), s.length());
			}

		}

		newString = newString + s;
		return newString;
	}

	public ZgCarInfoManager getZgCarInfoManager() {
		return zgCarInfoManager;
	}

	public void setZgCarInfoManager(ZgCarInfoManager zgCarInfoManager) {
		this.zgCarInfoManager = zgCarInfoManager;
	}

}
