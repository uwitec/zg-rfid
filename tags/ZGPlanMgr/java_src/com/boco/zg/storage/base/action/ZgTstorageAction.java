/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.ZgTstorage;
import com.boco.zg.storage.base.model.ZgTstoragebom;
import com.boco.zg.storage.base.service.ZgTstorageBo;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.storage.service.ZgTstorageExBo;
import com.boco.zg.storage.service.ZgTstoragebomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/storage/ZgTstorage/query_ZgTstorage.jsp";
	protected static final String LIST_JSP= "/zg/storage/ZgTstorage/list_ZgTstorage.jsp";
	protected static final String CREATE_JSP = "/zg/storage/ZgTstorage/create_ZgTstorage.jsp";
	protected static final String EDIT_JSP = "/zg/storage/ZgTstorage/edit_ZgTstorage.jsp";
	protected static final String SHOW_JSP = "/zg/storage/ZgTstorage/show_ZgTstorage.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/storage/ZgTstorage/list.do";

	private static final String ORDER_BOM = "/zg/storage/ZgTstorage/orderArbplList.jsp";
	private static final String ORDERARBPL_LIST = "/zg/storage/ZgTstorage/query_orderArbplList.jsp";
	
	
	private static final String BOM_LIST_OUT = "/zg/storage/ZgTstorage/bom_list_out.jsp";
	private static final String BOM_LIST_IN = "/zg/storage/ZgTstorage/bom_list_in.jsp";

	private ZgTstorageExBo zgTstorageExBo;
	private ZgTstorageBo zgTstorageBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	private ZgTstoragebomExBo zgTstoragebomExBo;
	private FwEmployeeBo fwEmployeeBo;
	
	private ZgTstorage zgTstorage;
	java.lang.String id = null;
	private String[] items;
	
	private String type;
	private String productType;
	
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
	
	//库存管理员
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
	


	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTstorage = new ZgTstorage();
		} else {
			zgTstorage = (ZgTstorage)zgTstorageExBo.getById(id);
		}
		enumMap.put("STORAGE_STATE", vmModelBo.getEnumValue("STORAGE_STATE"));		
		enumMap.put("STORAGE_TYPE", vmModelBo.getEnumValue("STORAGE_TYPE"));		
		enumMap.put("PRODUCT_TYPE", vmModelBo.getEnumValue("PRODUCT_TYPE"));		
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	
	public Object getModel() {
		return zgTstorage;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public java.lang.String getId() {
		return id;
	}


	public void setZgTstorageExBo(ZgTstorageExBo zgTstorageExBo) {
		this.zgTstorageExBo = zgTstorageExBo;
	}

	public void setZgTstoragebomExBo(ZgTstoragebomExBo zgTstoragebomExBo) {
		this.zgTstoragebomExBo = zgTstoragebomExBo;
	}

	public void setZgTstorageBo(ZgTstorageBo zgTstorageBo) {
		this.zgTstorageBo = zgTstorageBo;
	}
	
	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}


	/** 进入查询页面 */
	public String query() {
		CommonService.defultDateSet(getRequest(), "createDate_start", "createDate_end");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP;
	}
	
	/** 执行搜索 */
	public String list() {
		CommonService.defultDateSet(getRequest(), "createDate_start", "createDate_end");
	
		
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTstorage.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTstorageExBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		//获取登陆人基本信息
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		FwEmployee fwEmployee = (FwEmployee)fwEmployeeBo.getById(operatorInfo.getOperatorId());
		
		zgTstorage.getCreatorId_related().setValue(fwEmployee.getLabelCn());
		zgTstorage.setCreatorId(fwEmployee.getCuid());
//		zgTstorage.setDeptId(fwEmployee.getOrgId());
//		zgTstorage.getDeptId_related().setValue(fwEmployee.getOrgId_related().getValue());
		
		String guid=zgTstorageExBo.getGuid();
		zgTstorage.setCuid(guid);
		
		Date nowDate=Calendar.getInstance().getTime();
		zgTstorage.setCreateDate(nowDate);
		
		return CREATE_JSP;
	}
	
	/**
	 * 保存出入库存记录
	 * 处理逻辑：
	 * 	  保存出入库存表，设置状态为保存
	 *    读取session中的出入库的半成品，并保存
	 *    
	 * @return
	 * @throws IOException 
	 */
	public void save() throws IOException {
		List<ZgTstoragebomEx> bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		String update=this.getRequest().getParameter("update");//区别是新建的时候提交，还是编辑的时候提交
		
		if(isNullOrEmptyString(update)){
			zgTstorage.setState(Constants.OrderPlanStatus.SAVE.value());
			zgTstorageBo.save(zgTstorage);
		}else {
			zgTstorageBo.update(zgTstorage);
		}
		
		zgTstorageExBo.synSessionBomToDataBase(bomEList);
		             
		
		forwardQuery("操作成功");
		
	}
	/**
	 * 保存出入库存记录
	 * 处理逻辑：
	 * 	  保存出入库存表信息，设置状态为提交
	 *    同步session中的出入库半成品到数据中
	 *    更新zgtorderbom表记录，（更新入库数量和该表的相关的半成品的状态）
	 * 	  更新库存统计表
	 *    
	 * @return
	 * @throws IOException 
	 */
	public void submit() throws IOException {
		List<ZgTstoragebomEx> bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		
		String update=this.getRequest().getParameter("update");//区别是新建的时候提交，还是编辑的时候提交
		
		if(isNullOrEmptyString(update)){
			zgTstorage.setState(Constants.OrderPlanStatus.SUBMIT.value());
			zgTstorageBo.save(zgTstorage);
		}else {
			zgTstorage.setState(Constants.OrderPlanStatus.SUBMIT.value());
			zgTstorageBo.update(zgTstorage);
		}
		
		// 同步session中的出入库半成品到数据中
		zgTstorageExBo.synSessionBomToDataBase(bomEList);
		
	
		
		bomEList=zgTstoragebomExBo.findBomDEByStorageId(zgTstorage.getCuid(),productType,type);
	
		//更新zgtorderbom表记录，（更新入库数量和该表的相关的半成品的状态）
		//更新库存统计表
		zgTstorageExBo.updateOrderBomAndStorageStat(bomEList,zgTstorage,type,productType);
		
		// 入库，并且BOM数量够半成品入库
		if(Constants.InOutStorageType.IN.value().equals(type)){
			zgTstorageExBo.updateOrderPlanBomOutNum(bomEList);
		}
		forwardQuery("操作成功");
	}

	

	/**
	 * 更新库存统计表
	 * @param bomEList
	 */
	private void updateStorageStat(List<ZgTorderbomEx> bomEList) {
	
	}

	/**
	 * 同步session中的出入库半成品到数据中
	 * 处理逻辑：
	 * 	 遍历session中的list,判断该半成品的修改标志是否为true,是则处理（删除标志为true,则删除该半成品，否则更新）
	 * 	
	 * @param bomEList
	 * @param actionType 操作类型 save(保存)/submit(提交)
	 */
	private void synSessionBomToDataBase(List<ZgTstoragebomEx> bomEList,String actionType) {
		for (ZgTstoragebomEx obj : bomEList) {
			
			ZgTstoragebom zgTstoragebom=new ZgTstoragebom();
			zgTstoragebom.setCuid(obj.getCuid());
			zgTstoragebom.setZgTstorageId(obj.getZgTstorageId());
			zgTstoragebom.setOrderBomId(obj.getOrderBomId());
			zgTstoragebom.setNum(obj.getNum());
			zgTstoragebom.setZbz(obj.getZbz());
			
			if(obj.getIsModity()){//把标记为修改过的bom组件更新到数据库中
				if(obj.getIsDel()==true){//删除表中的该组件
					zgTstoragebomExBo.removeById(obj.getCuid());
				}else {
					int row=zgTstoragebomExBo.update1(zgTstoragebom);
					if(row<=0){//更新影响的条数小于０  则说明原来的数据中没有该组件，插入该组件
						zgTstoragebomExBo.save(zgTstoragebom);
					}
				}
			}
		}
	}
	
	/**进入更新页面*/
	public String edit() {
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTstorageExBo.update(this.zgTstorage);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTstorageExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}
	/**
	 * 获取订单生产线列表
	 */
	public String queryOrderArbplList(){
		String lgort=this.getRequest().getParameter("lgort");
		getRequest().setAttribute("lgort", lgort);
		return ORDERARBPL_LIST;
	}
	
	/**
	 * 获取订单生产线列表  使用分页
	 */
	public String findOrderArbplList(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
//		List<ZgTorderbomEx> OrderArbplList=new ArrayList<ZgTorderbomEx>() ;
		if(Constants.InOutStorageType.IN.value().equals(type)){
//			OrderArbplList = zgTorderbomExBo.findOrderArbplList(pageRequest.getFilters());
			//改用分页查询
			Page page= zgTorderbomExBo.findOrderArbplByPageRequest(pageRequest);
			savePage(page, pageRequest);
		}else if(Constants.InOutStorageType.OUT.value().equals(type)){
//			OrderArbplList = zgTorderbomExBo.findOrderArbplList1(pageRequest.getFilters());
			//改用分页查询
			Page page= zgTorderbomExBo.findOrderArbplByPageRequest1(pageRequest);
			savePage(page, pageRequest);
		}
//		this.getRequest().setAttribute("OrderArbplList", OrderArbplList);
		return ORDER_BOM;
	}
	
	/**
	 * 根据仓库编号查找还有剩余库存的生产线和订单
	 */
	public String findOrderArbplListByStorageId(){
		String lgort=this.getRequest().getParameter("lgort");
		List<Map> OrderArbplList = zgTorderbomExBo.findOrderArbplListByStorageId(lgort,productType);
		this.getRequest().setAttribute("OrderArbplList", OrderArbplList);
		return ORDER_BOM;
	}
	
	/**
	 * 根据出库或入库单编号，查找它的所进行出库或入库的半成品列表
	 * falg标志表示当前查看的数据是否为临时数据，因为出库或是入库的半成品在编辑的时候，并不是直接存入数据库，而是先保存在session
	 * 中，后面点击出入库单的“保存”或“提交”时，才从session中取出它的出入库半成品进行相应的操作
	 * @return
	 */
	public String findBomlList(){
		List<ZgTstoragebomEx> bomEList =new ArrayList<ZgTstoragebomEx>();
		String flag=this.getRequest().getParameter("flag");
		
		if("del".equals(flag)){//删除session中的半成品
			bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
			for (ZgTstoragebomEx obj : bomEList) {
				obj.setIsDel(true);
				obj.setIsModity(true);
			}
		}else if("temp".equals(flag)){//查询临时数据
			bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		}else {
			if(Constants.InOutStorageType.IN.value().equals(type)){//入库列表
				bomEList=zgTstoragebomExBo.findBomDEByStorageId(id,productType,type);
			}else if(Constants.InOutStorageType.OUT.value().equals(type)){//出库列表
				bomEList=zgTstoragebomExBo.findBomDEByStorageId1(id,productType,type);
			}
			this.getSession().removeAttribute("bomEList");
			this.getSession().setAttribute("bomEList", bomEList);
		}
		
		//保存bomIds到session中，用于页面展示可选bom的时候过滤
		String bomIds="";
		for (ZgTstoragebomEx obj : bomEList) {
			if(!obj.getIsDel()){
				bomIds=bomIds+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomIds", bomIds);
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		
		if(Constants.InOutStorageType.IN.value().equals(type)){//入库列表
			return BOM_LIST_IN;
		}else {
			return BOM_LIST_OUT;
		}
	}
	
	/**
	 * 删除出库入库单的出入库半成品
	 * 把删除的半成品置为删除状态，保存在session中，后面提交再统一处理
	 */
	public String deleteBom(){
		List<ZgTstoragebomEx> list=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String delId=(String)params.get("cuid");
			
			for (ZgTstoragebomEx obj : list) {
				if(delId.equals(obj.getCuid())){
					obj.setIsModity(true);
					obj.setIsDel(true);
				}
			}
			
		}
		
		//保存bomIds到session中，用于页面展示可选bom的时候过滤
		String bomIds="";
		for (ZgTstoragebomEx obj : list) {
			if(!obj.getIsDel()){
				bomIds=bomIds+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomIds", bomIds);
		
		return SUCCESS;
	}
	
	

	


	
}
