/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
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

import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.storage.service.ZgTstoragebomExBo;
import com.boco.zg.util.Constants;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstoragebomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/storage/ZgTstoragebom/query_ZgTstoragebom.jsp";
	protected static final String LIST_JSP= "/zg/storage/ZgTstoragebom/list_ZgTstoragebom.jsp";
	protected static final String CREATE_JSP = "/zg/storage/ZgTstoragebom/create_ZgTstoragebom.jsp";
	protected static final String EDIT_JSP = "/zg/storage/ZgTstoragebom/edit_ZgTstoragebom.jsp";
	protected static final String SHOW_JSP = "/zg/storage/ZgTstoragebom/show_ZgTstoragebom.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/storage/ZgTstoragebom/list.do";

	private static final String BOM_LIST = "/zg/storage/ZgTstorage/bom_list1.jsp";
	private static final String QUERY_BOM_LIST="/zg/storage/ZgTstorage/query_bom_list1.jsp";
	
	private ZgTstoragebomExBo zgTstoragebomExBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private IVmModelBo vmModelBo;
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	private ZgTstoragebom zgTstoragebom;
	java.lang.String id = null;
	private String[] items;
	
	private String storageId=null;
	private String  arbpl=null;
	private String  orderId=null;
	private String type;
	private String productType;
	private String lgort;
	
	private List<ZgTstoragebomEx> bomList;
		
	

	


	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			zgTstoragebom = new ZgTstoragebom();
		} else {
//			zgTstoragebom = (ZgTstoragebom)zgTstoragebomExBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	
	public Object getModel() {
		return zgTstoragebom;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setItems(String[] items) {
		this.items = items;
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


	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public void setZgTstoragebomExBo(ZgTstoragebomExBo zgTstoragebomExBo) {
		this.zgTstoragebomExBo = zgTstoragebomExBo;
	}

	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}

	public List<ZgTstoragebomEx> getBomList() {
		return bomList;
	}

	public void setBomList(List<ZgTstoragebomEx> bomList) {
		this.bomList = bomList;
	}
	
	public Map<String, List<TmdEnumevalue>> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<String, List<TmdEnumevalue>> enumMap) {
		this.enumMap = enumMap;
	}
	
	
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
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
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTstoragebom.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTstoragebomExBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		ZgTstoragebomEx zgTstoragebomEx=new ZgTstoragebomEx();
		zgTstoragebomEx.setZgTstorageId(storageId);
		getRequest().setAttribute("bom", zgTstoragebomEx);
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		zgTstoragebomExBo.save(zgTstoragebom);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		List<ZgTstoragebomEx> bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		for (ZgTstoragebomEx obj : bomEList) {
			if(obj.getCuid().equals(id));
			this.getRequest().setAttribute("bom", obj);
			break;
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTstoragebomExBo.update(this.zgTstoragebom);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTstoragebomExBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getArbpl() {
		return arbpl;
	}

	public void setArbpl(String arbpl) {
		this.arbpl = arbpl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String queryBomlListByArbplOrderId(){
		return QUERY_BOM_LIST;
	}
	
	public String queryBomlListByArbplOrderIdStorageId(){
		return QUERY_BOM_LIST;
	}
	
	/**
	 * 根据生产线和订单编码查找半成品列表
	 * @return
	 */
	public String findBomlListByArbplOrderId(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);

		List<ZgTorderbomEx> bomList =new ArrayList<ZgTorderbomEx>();
		if(Constants.InOutStorageType.IN.value().equals(type)){//入库管理
			bomList=zgTorderbomExBo.findBomlListByArbplOrderId(pageRequest.getFilters());
		}if(Constants.InOutStorageType.OUT.value().equals(type)){//出库管理
			bomList=zgTorderbomExBo.findBomlListByArbplOrderId1(pageRequest.getFilters());
		}
		
		//过滤当前库存单已经存在的半成品
		String bomIds= this.getSession().getAttribute("bomIds")==null?"":this.getSession().getAttribute("bomIds").toString();
		List<ZgTorderbomEx> bomListNew=new ArrayList<ZgTorderbomEx>();
		for (ZgTorderbomEx obj : bomList) {
			if(bomIds.indexOf(obj.getCuid())<0){//"TE1231234124124","AWDwfWFFW","GWTEGTWEG"
				bomListNew.add(obj);
			}
		}
		
		getRequest().setAttribute("bomList", bomListNew);
		return BOM_LIST;
	}
	
	
	/**
	 *为出入库存添加生成半成品
	 * @throws IOException 
	 */
	public void generateBom() throws IOException{
		List<ZgTstoragebomEx> bomEList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			ZgTstoragebomEx obj=new ZgTstoragebomEx();
			String cuid=zgTorderPlanbomExBo.getCUID();
			obj.setCuid(cuid);
			obj.setOrderAufnr((java.lang.String)params.get("orderAufnr"));
			
			obj.setArbpl((java.lang.String)params.get("arbpl"));
			obj.setMatnr((java.lang.String)params.get("matnr"));
			obj.setIdnrk((java.lang.String)params.get("idnrk"));
			obj.setMsehl1((java.lang.String)params.get("msehl1"));
			
			
		
			obj.setMenge(Double.parseDouble((java.lang.String)params.get("menge")));
			obj.setOrderBomId((java.lang.String)params.get("orderBomId"));
			obj.setZgTstorageId((java.lang.String)params.get("storageId"));
			obj.setNum(Double.parseDouble((java.lang.String)params.get("menge")));
			obj.setIsModity(true);
			bomEList.add(obj);
		}
		this.getSession().setAttribute("bomEList", bomEList);
		

		//保存bomIds到session中，用于页面展示可选bom的时候过滤
		String bomIds="";
		for (ZgTstoragebomEx obj : bomEList) {
			if(!obj.getIsDel()){
				bomIds=bomIds+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomIds", bomIds);
		
		returnMsgAndCloseWindow("操作成功");
	}
	
	/**
	 * 保存入库冲单表的半成品,保存在session
	 * @return
	 * @throws IOException 
	 */
	public String saveInOutBom() throws IOException{
		List<ZgTstoragebomEx> sessionBomList=(List<ZgTstoragebomEx>) this.getSession().getAttribute("bomEList");
		if(null!=bomList){
			for (ZgTstoragebomEx obj : bomList) {
				if(obj!=null){
					for (ZgTstoragebomEx temp : sessionBomList) {
						if(obj.getCuid().equals(temp.getCuid())){
							temp.setNum(obj.getNum());
							temp.setZbz(obj.getZbz());
							temp.setIsModity(true);
						}
					}
				}
				
			}
		}
		return SUCCESS;
	}


	

	
	

}
