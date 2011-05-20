/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;

import javax.servlet.ServletException;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.zg.bom.base.dao.ZgCarInfoDao;
import com.boco.zg.bom.base.model.ZgCarInfo;
import com.boco.zg.bom.base.service.ZgCarInfoManager;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.Constants.OrderStatus;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.Key;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTorderbomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/plan/ZgTorderbom/query_ZgTorderbom.jsp";
	protected static final String LIST_JSP= "/zg/plan/ZgTorderbom/list_ZgTorderbom.jsp";
	protected static final String CREATE_JSP = "/zg/plan/ZgTorderbom/create_ZgTorderbom.jsp";
	protected static final String EDIT_JSP = "/zg/plan/ZgTorderbom/edit_ZgTorderbom.jsp";
	protected static final String SHOW_JSP = "/zg/plan/ZgTorderbom/show_ZgTorderbom.jsp";
	
	protected static final String ORDER_BOM="/zg/plan/ZgTorderbom/orderboms.jsp";
	protected static final String ORDER_BOMCHILDREN="/zg/plan/ZgTorderbom/bom_list.jsp";
	
	protected static final String ORDER_BOM_FORM="/zg/plan/ZgTorderbom/orderBomForm.jsp";
	protected static final String ORDER_BOM_PANEL="/zg/plan/ZgTorderbom/orderBomPanel.jsp";
	
	protected static final String ORDER_BOM_SHOW="/zg/plan/ZgTorderbom/orderBomShow.jsp";
	private static final String ORDER_BOM_SHOW1 = "/zg/plan/ZgTorderbom/orderBomShow1.jsp";
	protected static final String ORDER_BOM_PANEL_VIEW="/zg/plan/ZgTorderbom/ViewZgTorderbom/orderBomPanel.jsp";
	protected static final String ORDER_BOM_PANEL_VIEW1="/zg/plan/ZgTorderbom/ViewZgTorderbom/orderBomPanel1.jsp";
	protected static final String VIEW_ORDER_BOM="/zg/plan/ZgTorderbom/ViewZgTorderbom/orderboms.jsp";
	protected static final String VIEW_ORDER_BOMCHILDREN="/zg/plan/ZgTorderbom/ViewZgTorderbom/bom_list.jsp";
	
	protected static final String ORDER_BOM_VIEW="/zg/plan/ZgTorderbom/ViewZgTorderbom/orderBomView.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/plan/ZgTorderbom/list.do";

	private static final String ORDERBOM_PUSH = "/zg/plan/ZgTorderbom/orderBompush.jsp";

	
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	private ZgTorderBo zgTorderBo;
	
	private IVmModelBo vmModelBo;
	
	private ZgCarInfoManager zgCarInfoManager;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private ZgTorderbom zgTorderbom;
	java.lang.String id = null;
	private String[] items;
	
	private String orderId;
	
	private String[] matnrs;

	public void setMatnrs(String[] matnrs) {
		this.matnrs = matnrs;
	}

	public String[] getMatnrs() {
		return matnrs;
	}
	
	private String[] sortfs;
	
	public String[] getSortfs() {
		return sortfs;
	}

	public void setSortfs(String[] sortfs) {
		this.sortfs = sortfs;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTorderbom = new ZgTorderbom();
		} else {
			zgTorderbom = (ZgTorderbom)zgTorderbomExBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTorderbomExBo(ZgTorderbomExBo bo) {
		this.zgTorderbomExBo = bo;
	}	
	
	public Object getModel() {
		return zgTorderbom;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	private List<TmdEnumevalue> sortFEnume;
	
	public List<TmdEnumevalue> getSortFEnume() {
		return vmModelBo.getEnumValue("SORTF");
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
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTorderbom.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTorderbomExBo.findByPageRequest(pageRequest);
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
		zgTorderbomExBo.save(zgTorderbom);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTorderbomExBo.update(this.zgTorderbom);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTorderbomExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}
	
	public String orderBomPanel() {
		String isView = this.getRequest().getParameter("isView");
		List<ZgTorderbom> list = zgTorderbomExBo.findBomByOrderId(orderId);
//		Map<String,List<ZgTorderbom>> map = new HashMap<String,List<ZgTorderbom>>();
		LinkedHashMap<ZgTorderbom,List<ZgTorderbom>> map =new  LinkedHashMap<ZgTorderbom,List<ZgTorderbom>>();
		List<ZgTorderbom> fatherList=new ArrayList<ZgTorderbom>();
		String matnr="";
		ZgTorderbom cpBom=new ZgTorderbom();
	/*	
		ArrayList<String> keys=new ArrayList<String>();
		
		//获取所有半成品包括成品的 bom id
		for(ZgTorderbom keyObj:list)
		{
			String keyString=keyObj.getMatnr1();
			
			if (!keys.contains(keyString))
			{
				keys.add(keyString);
			}
		}
		

		

		// 把半成品推入序列
		for(ZgTorderbom keyObj:list)
		{
			
			if (keys.contains(keyObj.getIdnrk()))
			{
				fatherList.add(keyObj);
			}
			
		}
		getRequest().setAttribute("fatherList", fatherList);
		getRequest().setAttribute("bomList", list);
		
		// 把原始bom推入序列
		for(ZgTorderbom keyObj:list)
		{
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				   Map.Entry entry = (Map.Entry) it.next();
				   ZgTorderbom key = (ZgTorderbom)entry.getKey();

				   if (key.getIdnrk()==keyObj.getMatnr1())
				   {
					   map.get(key).add(keyObj);
				   }
			}
			
		}
		
		*/
		
		if(list!=null&&list.size()>0){//创建成品物料
			matnr=list.get(0).getMatnr();
			getRequest().setAttribute("matnr", matnr);
			cpBom.setIdnrk(matnr);
		}
		
		//查询半成品一层
		for(ZgTorderbom bom : list) {
			if("DE".equals(bom.getSortf())||"CE".equals(bom.getSortf())||"C".equals(bom.getSortf())||"D".equals(bom.getSortf())||"E".equals(bom.getSortf())){
				List<ZgTorderbom> 	boms = new ArrayList<ZgTorderbom>();
				map.put(bom, boms);
			}
		}
		
		for(ZgTorderbom bom : list) {//循环bom列表
			String matnr1 = bom.getMatnr1();//
		
			int count=0;
			for(ZgTorderbom temp:list){
				
				if(temp.getIdnrk().equals(matnr1)){//找bom的父结点
					List<ZgTorderbom> boms = map.get(temp);
					if(boms == null) {
						boms = new ArrayList<ZgTorderbom>();
					}
				
					boms.add(bom);
					
					map.put(temp, boms);
					break;
				}
				count++;
			}
			
			if(count==list.size()){//找不到父结点，则该bom是ABE或是半成品
				if(matnr1.equals(matnr)){
					if("DE".equals(bom.getSortf())||"CE".equals(bom.getSortf())||"C".equals(bom.getSortf())||"D".equals(bom.getSortf())||"E".equals(bom.getSortf())){
					}else {
						List<ZgTorderbom> boms = map.get(cpBom);
						if(boms == null) {
							boms = new ArrayList<ZgTorderbom>();
						}
						boms.add(bom);
						map.put(cpBom, boms);
					}
				}
			}
		}
		
		//把成品位置放到最后
		List<ZgTorderbom> boms = map.get(cpBom);
		map.remove(cpBom);
		map.put(cpBom, boms);
		
		this.getRequest().setAttribute("orderboms", map);
		if("true".equals(isView)) {
			return ORDER_BOM_PANEL_VIEW;
		}
		if("excep".equals(isView)){
			return ORDER_BOM_PANEL_VIEW1;
		}
		getRequest().setAttribute("pflag", getRequest().getParameter("pflag"));
		return ORDER_BOM_PANEL;
	}
	/**
	 * 订单生产条件查询
	 * @return
	 */
	public String orderProdConQuery(){
		List<ZgTorderbom> list = zgTorderbomExBo.findBomByOrderId(orderId);
//		Map<String,List<ZgTorderbom>> map = new HashMap<String,List<ZgTorderbom>>();
		LinkedHashMap<ZgTorderbom,List<ZgTorderbom>> map =new  LinkedHashMap<ZgTorderbom,List<ZgTorderbom>>();
		List<ZgTorderbom> fatherList=new ArrayList<ZgTorderbom>();
		String matnr="";
		ZgTorderbom cpBom=new ZgTorderbom();
		if(list!=null&&list.size()>0){//创建成品物料
			matnr=list.get(0).getMatnr();
			getRequest().setAttribute("matnr", matnr);
			cpBom.setIdnrk(matnr);
		}
		
		for(ZgTorderbom bom : list) {//循环bom列表
			String matnr1 = bom.getMatnr1();//
		
			int count=0;
			for(ZgTorderbom temp:list){
				
				if(temp.getIdnrk().equals(matnr1)){//找bom的父结点
					List<ZgTorderbom> boms = map.get(temp);
					if(boms == null) {
						boms = new ArrayList<ZgTorderbom>();
					}
				
					boms.add(bom);
					
					map.put(temp, boms);
					break;
				}
				count++;
			}
			
			if(count==list.size()){//找不到父结点，则该bom是ABE或是半成品
				if(matnr1.equals(matnr)){
					if("DE".equals(bom.getSortf())||"CE".equals(bom.getSortf())||"C".equals(bom.getSortf())||"D".equals(bom.getSortf())||"E".equals(bom.getSortf())){
					}else {
						List<ZgTorderbom> boms = map.get(cpBom);
						if(boms == null) {
							boms = new ArrayList<ZgTorderbom>();
						}
						boms.add(bom);
						map.put(cpBom, boms);
					}
				}
			}
		}
		
		//把成品位置放到最后
		List<ZgTorderbom> boms = map.get(cpBom);
		map.remove(cpBom);
		map.put(cpBom, boms);
		this.getRequest().setAttribute("orderboms", map);
		this.getRequest().setAttribute("publishNum", getRequest().getParameter("publishNum"));
		return ORDER_BOM_VIEW;
	}
	public String orderBomForm() {
		List<ZgTorderbom> list = zgTorderbomExBo.findBomByOrderId(orderId);
		Map<String,List<ZgTorderbom>> map = new HashMap<String,List<ZgTorderbom>>();
		for(ZgTorderbom bom : list) {
			String matnr = bom.getMatnr1();
			List<ZgTorderbom> boms = map.get(matnr);
			if(boms == null) {
				boms = new ArrayList<ZgTorderbom>();
			}
			boms.add(bom);
			map.put(matnr, boms);
		}
		this.getRequest().setAttribute("orderboms", map);
		return ORDER_BOM_FORM;
	}
	
	/**
	 * 获得所有成品列表
	 * @return
	 */
	public String findBomDE() {
		List<Map> list = zgTorderbomExBo.findBomDEByOrderId(orderId);
		this.getRequest().setAttribute("bomList", list);
		if(list.size() > 0)
			this.getRequest().setAttribute("firstBom", list.get(0));
		return ORDER_BOM;
	}
	
	/**
	 * 获得所有成品列表(界面只展示)
	 * @return
	 */
	public String findViewBomDE() {
		List<Map> list = zgTorderbomExBo.findBomDEByOrderId(orderId);
		this.getRequest().setAttribute("bomList", list);
		if(list.size() > 0)
			this.getRequest().setAttribute("firstBom", list.get(0));
		return VIEW_ORDER_BOM;
	}
	/**
	 * 获得所有组成成品的半成品和bom列表
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String findBomList() throws ServletException, IOException {
		String matnr = this.getRequest().getParameter("matnr")==null?"":this.getRequest().getParameter("matnr");
		if(!StringUtils.isBlank(matnr)) {
			matnrs = new String[]{matnr};
		}
		if(matnrs != null && matnrs.length > 0) {
			List<ZgTorderbom> list = zgTorderbomExBo.findBomByMatnrs(orderId, matnrs);
			this.getRequest().setAttribute("bomList", list);
		}
	    return ORDER_BOMCHILDREN;
	}
	
	/**
	 * 获得所有组成成品的半成品和bom列表(只查看)
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String findViewBomList() throws ServletException, IOException {
		String matnr = this.getRequest().getParameter("matnr")==null?"":this.getRequest().getParameter("matnr");
		if(!StringUtils.isBlank(matnr)) {
			matnrs = new String[]{matnr};
		}
		if(matnrs != null && matnrs.length > 0) {
			List<ZgTorderbom> list = zgTorderbomExBo.findBomByMatnrs(orderId, matnrs);
			this.getRequest().setAttribute("bomList", list);
		}
	    return VIEW_ORDER_BOMCHILDREN;
	}
	
	public String changeSortF() {
		for(int j = 0;j<sortfs.length;j++) {
			if(!StringUtils.isBlank(sortfs[j])) {
				String[] s = sortfs[j].split("_");
				zgTorderbomExBo.updateOrderBomSortf(s[0],s[1]);
			}
		}	
		return SUCCESS;
	}
	
	/**
	 * 获取移单bom
	 *  orderId1 源订单
	 *  orderId2 目标订单
	 *  both：是否两个订单都不为空
	 * @return
	 */
	public String showBom(){
		String orderId1=getRequest().getParameter("orderId1");
		
		String orderId2=getRequest().getParameter("orderId2");
		String both=getRequest().getParameter("both");
		
		ZgTorder zgTorder=zgTorderBo.getById(orderId1);
		
		String isDel="0";
		
		if(zgTorder!=null){
			if(Constants.OrderStatus.DEL.value().equals(zgTorder.getOrderState())){
				isDel="-1";
			}
		}
		
		List<Map> bomList=zgTorderbomExBo.getBomListByOrderId(orderId1,orderId2,both,isDel);
		getRequest().setAttribute("bomList", bomList);
		getRequest().setAttribute("order", zgTorder);
		return ORDER_BOM_SHOW;
	}
	
	/**
	 * 获取移单bom
	 *  orderId1 目标订单
	 *  orderId2 源订单
	 *  both：是否两个订单都不为空
	 * @return
	 */
	public String showBom1(){
		String orderId1=getRequest().getParameter("orderId1");
		
		String orderId2=getRequest().getParameter("orderId2");
		
		ZgTorder zgTorder=zgTorderBo.getById(orderId2);
		String isDel="0";
		if(zgTorder!=null){
			if(Constants.OrderStatus.DEL.value().equals(zgTorder.getOrderState())){
				isDel="-1";
			}
		}
		
		String both=getRequest().getParameter("both");
		List<Map> bomList=zgTorderbomExBo.getBomListByOrderId1(orderId1,orderId2,both,isDel);
		getRequest().setAttribute("bomList", bomList);
		return ORDER_BOM_SHOW1;
	}
	
	public String orderBomPush(){
		return ORDERBOM_PUSH;
	}
	
	public List<ZgTorderbom> bomList;
	
	public void bomMove(){
		String sourceOrderId=getRequest().getParameter("sourceOrderId");
		String targetOrderId=getRequest().getParameter("targetOrderId");
	}
	
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setBomList(List<ZgTorderbom> bomList) {
		this.bomList = bomList;
	}

	public ZgTorderBo getZgTorderBo() {
		return zgTorderBo;
	}

	public void setZgTorderBo(ZgTorderBo zgTorderBo) {
		this.zgTorderBo = zgTorderBo;
	}
	
	public String editBomCarInfo(){
		String bomId=getRequest().getParameter("bomId");
		List<ZgCarInfo> carList=zgCarInfoManager.findAll();
		getRequest().setAttribute("carList", carList);
		getRequest().setAttribute("bomId", bomId);
		return "/zg/plan/ZgTorderbom/edit_bomCarInfo.jsp";
	}

	public void setZgCarInfoManager(ZgCarInfoManager zgCarInfoManager) {
		this.zgCarInfoManager = zgCarInfoManager;
	}
	

}
