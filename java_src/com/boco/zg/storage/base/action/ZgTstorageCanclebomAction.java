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
import java.util.Iterator;
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
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.service.ZgTstorageCanclebomExBo;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageCanclebomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/storage/ZgTstorageCanclebom/query_ZgTstorageCanclebom.jsp";
	protected static final String LIST_JSP= "/zg/storage/ZgTstorageCanclebom/list_ZgTstorageCanclebom.jsp";
	protected static final String CREATE_JSP = "/zg/storage/ZgTstorageCanclebom/create_ZgTstorageCanclebom.jsp";
	protected static final String EDIT_JSP = "/zg/storage/ZgTstorageCanclebom/edit_ZgTstorageCanclebom.jsp";
	protected static final String SHOW_JSP = "/zg/storage/ZgTstorageCanclebom/show_ZgTstorageCanclebom.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/storage/ZgTstorageCanclebom/list.do";
	
	private ZgTstorageCanclebomExBo zgTstorageCanclebomExBo;
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	private List<ZgTstorageCanclebomEx> cancleBomList;
	
	private IVmModelBo vmModelBo;
	
	
	
	private ZgTstorageCanclebom zgTstorageCanclebom;
	java.lang.String id = null;
	private String[] items;
	private String cancleId;
	private String productType;

	

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
//			zgTstorageCanclebom = new ZgTstorageCanclebom();
		} else {
//			zgTstorageCanclebom = (ZgTstorageCanclebom)zgTstorageCanclebomBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	
	public Object getModel() {
		return zgTstorageCanclebom;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public Map<String, List<TmdEnumevalue>> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<String, List<TmdEnumevalue>> enumMap) {
		this.enumMap = enumMap;
	}
	
	public String getCancleId() {
		return cancleId;
	}

	public void setCancleId(String cancleId) {
		this.cancleId = cancleId;
	}

	public java.lang.String getId() {
		return id;
	}
	
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setZgTstorageCanclebomExBo(
			ZgTstorageCanclebomExBo zgTstorageCanclebomExBo) {
		this.zgTstorageCanclebomExBo = zgTstorageCanclebomExBo;
	}

	public void setZgTstorageCanclebom(ZgTstorageCanclebom zgTstorageCanclebom) {
		this.zgTstorageCanclebom = zgTstorageCanclebom;
	}

	public String[] getItems() {
		return items;
	}

	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}

	public List<ZgTstorageCanclebomEx> getCancleBomList() {
		return cancleBomList;
	}

	public void setCancleBomList(List<ZgTstorageCanclebomEx> cancleBomList) {
		this.cancleBomList = cancleBomList;
	}

	/** 进入查询页面 */
	public String queryByCancleId() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().setAttribute("pageRequest", pageRequest);
		return QUERY_JSP;
	}
	
	/**查询可以冲单的半成品 */
	public String listByCancleId() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
	    List<ZgTstorageCanclebomEx> list= zgTstorageCanclebomExBo.listByCancleId(pageRequest.getFilters());
		
	    
		//过滤当前库存单已经存在的半成品
		String bomECancleIds= this.getSession().getAttribute("bomECancleIds")==null?"":this.getSession().getAttribute("bomECancleIds").toString();
		List<ZgTstorageCanclebomEx> bomListNew=new ArrayList<ZgTstorageCanclebomEx>();
		for (ZgTstorageCanclebomEx obj : list) {
			if(bomECancleIds.indexOf(obj.getStorageId()+obj.getOrderBomId())<0){
				bomListNew.add(obj);
			}
		}
	    
	    getRequest().setAttribute("resultList", bomListNew);
		
		return LIST_JSP;
	}
	
	/**
	 *为入库冲单生成入生成半成品
	 * @throws IOException 
	 */
	public void generateBom() throws IOException{
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			ZgTstorageCanclebomEx obj=new ZgTstorageCanclebomEx();
			String cuid=zgTorderPlanbomExBo.getCUID();
			obj.setCuid(cuid);
			obj.setAufnr((java.lang.String)params.get("aufnr"));
			
			obj.setArbpl((java.lang.String)params.get("arbpl"));
			obj.setStorageId((java.lang.String)params.get("storageId"));
			obj.setMatnr((java.lang.String)params.get("matnr"));
			obj.setIdnrk((java.lang.String)params.get("idnrk"));
			obj.setMsehl1((java.lang.String)params.get("msehl1"));
			obj.setAllNum(Long.parseLong((java.lang.String)params.get("allNum")));
			obj.setNum(Long.parseLong((java.lang.String)params.get("allNum")));
			obj.setOrderBomId((java.lang.String)params.get("orderBomId"));
			obj.setStorageCancleId((java.lang.String)params.get("storageCancleId"));
			obj.setLgort((String)params.get("lgort"));
			obj.setIsModity(true);
			bomECancleList.add(obj);
		}
		this.getSession().setAttribute("bomECancleList", bomECancleList);
		
		//保存bomIds到session中，用于页面展示可选bom的时候过滤
		String bomECancleIds="";
		for (ZgTstorageCanclebomEx obj : bomECancleList) {
			if(!obj.getIsDel()){
				bomECancleIds=bomECancleIds+obj.getStorageId()+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomECancleIds", bomECancleIds);
		
		returnMsgAndCloseWindow("操作成功");
	}
	
	/**
	 *删除入库单中的半成品
	 * @throws IOException 
	 */
	public String deleteBom() throws IOException{
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			String cuid=(String)params.get("cuid");
			for (ZgTstorageCanclebomEx obj : bomECancleList) {
				if(cuid.equals(obj.getCuid())){
					obj.setIsDel(true);
					obj.setIsModity(true);
					break;
				}
			}
		}
		
		this.getSession().setAttribute("bomECancleList",bomECancleList);
		
		//保存bomIds到session中，用于页面展示可选bom的时候过滤
		String bomECancleIds="";
		for (ZgTstorageCanclebomEx obj : bomECancleList) {
			if(!obj.getIsDel()){
				bomECancleIds=bomECancleIds+obj.getStorageId()+obj.getOrderBomId()+",";
			}
		}
		this.getSession().setAttribute("bomECancleIds", bomECancleIds);
		
		return SUCCESS;
	}
	
	/**
	 * 保存入库冲单表的半成品,保存在session
	 * @return
	 */
	public String saveBom(){
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		if(null!=cancleBomList){
			for (ZgTstorageCanclebomEx obj : cancleBomList) {
				if(obj!=null){
					for (ZgTstorageCanclebomEx temp : bomECancleList) {
						if(obj.getCuid().equals(temp.getCuid())){
							temp.setNum(obj.getNum());
							temp.setZbz(obj.getZbz());
							temp.setStorageCancleId(obj.getStorageCancleId());
							temp.setIsModity(true);
						}
					}
				}
				
			}
		}
		return SUCCESS;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	

	

}
