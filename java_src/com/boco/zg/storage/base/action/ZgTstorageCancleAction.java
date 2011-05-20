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
import java.util.List;
import java.util.Map;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwEmployeeBo;
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

import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.storage.service.ZgTstorageCancleExBo;
import com.boco.zg.storage.service.ZgTstorageCanclebomExBo;
import com.boco.zg.storage.service.ZgTstorageExBo;
import com.boco.zg.util.Constants;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class ZgTstorageCancleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/zg/storage/ZgTstorageCancle/query_ZgTstorageCancle.jsp";
	protected static final String LIST_JSP= "/zg/storage/ZgTstorageCancle/list_ZgTstorageCancle.jsp";
	protected static final String CREATE_JSP = "/zg/storage/ZgTstorageCancle/create_ZgTstorageCancle.jsp";
	protected static final String EDIT_JSP = "/zg/storage/ZgTstorageCancle/edit_ZgTstorageCancle.jsp";
	protected static final String SHOW_JSP = "/zg/storage/ZgTstorageCancle/show_ZgTstorageCancle.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/zg/storage/ZgTstorageCancle/list.do";
	private static final String BOM_LIST = "/zg/storage/ZgTstorageCancle/bom_list.jsp";

	
	private ZgTstorageCancleExBo zgTstorageCancleExBo;
	
	private ZgTstorageCancleBo zgTstorageCancleBo;
	
	private FwEmployeeBo fwEmployeeBo;
	

	private ZgTstorageExBo zgTstorageExBo;
	
	private ZgTstorageCanclebomExBo zgTstorageCanclebomExBo;
	
	private Map<String,List<TmdEnumevalue>> enumMap = new HashMap<String, List<TmdEnumevalue>>();
	
	private ZgTstorageCancle zgTstorageCancle;
	java.lang.String id = null;
	private String[] items;
	private String productType;
	private String cancleId;
	
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
			zgTstorageCancle = new ZgTstorageCancle();
		} else {
			zgTstorageCancle = (ZgTstorageCancle)zgTstorageCancleBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	
	public Object getModel() {
		return zgTstorageCancle;
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
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	public void setZgTstorageCanclebomExBo(
			ZgTstorageCanclebomExBo zgTstorageCanclebomExBo) {
		this.zgTstorageCanclebomExBo = zgTstorageCanclebomExBo;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public java.lang.String getId() {
		return id;
	}

	public String getProductType() {
		return productType;
	}

	public ZgTstorageCancleExBo getZgTstorageCancleExBo() {
		return zgTstorageCancleExBo;
	}

	public void setZgTstorageCancleExBo(ZgTstorageCancleExBo zgTstorageCancleExBo) {
		this.zgTstorageCancleExBo = zgTstorageCancleExBo;
	}

	public void setZgTstorageCancleBo(ZgTstorageCancleBo zgTstorageCancleBo) {
		this.zgTstorageCancleBo = zgTstorageCancleBo;
	}

	public String getCancleId() {
		return cancleId;
	}

	public void setCancleId(String cancleId) {
		this.cancleId = cancleId;
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
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(zgTstorageCancle.BM_CLASS_ID,super.getSessionUserId()));
		Page page = zgTstorageCancleBo.findByPageRequest(pageRequest);
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		//获取登陆人信息
		//获取登陆人基本信息
		OperatorInfo operatorInfo=(OperatorInfo) this.getRequest().getSession().getAttribute("operatorInfo");
		FwEmployee fwEmployee = (FwEmployee)fwEmployeeBo.getById(operatorInfo.getOperatorId());
		
		zgTstorageCancle.getCreatorId_related().setValue(fwEmployee.getLabelCn());
		zgTstorageCancle.setCreatorId(fwEmployee.getCuid());
//		zgTstorageCancle.setDeptId(fwEmployee.getOrgId());
//		zgTstorageCancle.getDeptId_related().setValue(fwEmployee.getOrgId_related().getValue());
		
		String guid=zgTstorageExBo.getGuid();
		zgTstorageCancle.setCuid(guid);
		
		Date nowDate=Calendar.getInstance().getTime();
		zgTstorageCancle.setCreateDate(nowDate);
		return CREATE_JSP;
	}
	
	/**
	 * 保存入库冲单计划
	 * 处理逻辑：
	 * 	更新入库冲单表状态为保存
	 *  读取session中的半成品保存
	 * @throws IOException
	 */
	public void save() throws IOException {
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		String update=this.getRequest().getParameter("update");//区别是新建的时候提交，还是编辑的时候提交
		
		if(isNullOrEmptyString(update)){
			zgTstorageCancle.setState(Constants.OrderPlanStatus.SAVE.value());
			zgTstorageCancleBo.save(zgTstorageCancle);
		}else {
			zgTstorageCancleBo.update(zgTstorageCancle);
		}
		
		zgTstorageCancleExBo.synSessionBomToDataBase(bomECancleList);
		
		forwardQuery("操作成功");
	}
	
	/**
	 * 保存入库冲单计划
	 * 处理逻辑：
	 * 	更新入库冲单表状态为提交
	 *  读取session中的半成品保存
	 *  更新zgtorderbom中的半成品状态
	 *  更新库存统计表
	 * @throws IOException
	 */
	public void submit() throws IOException{
		List<ZgTstorageCanclebomEx> bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		String update=this.getRequest().getParameter("update");//区别是新建的时候提交，还是编辑的时候提交
		
		if(isNullOrEmptyString(update)){
			zgTstorageCancle.setState(Constants.OrderPlanStatus.SUBMIT.value());
			zgTstorageCancleBo.save(zgTstorageCancle);
		}else {
			zgTstorageCancle.setState(Constants.OrderPlanStatus.SUBMIT.value());
			zgTstorageCancleBo.update(zgTstorageCancle);
		}
		
		// 读取session中的半成品保存
		zgTstorageCancleExBo.synSessionBomToDataBase(bomECancleList);
		
		//更新zgtorderbom中的半成品状态
		//更新库存统计表
		zgTstorageCancleExBo.updateOrderBomAndStorageStat(bomECancleList,zgTstorageCancle,productType);
		
		
		forwardQuery("操作成功");
	}
	
	/**进入更新页面*/
	public String edit() {
		getRequest().setAttribute("view", getRequest().getParameter("view"));
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		zgTstorageCancleBo.update(this.zgTstorageCancle);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			zgTstorageCancleBo.removeById((java.lang.String)params.get("id"));
		}
		return LIST_ACTION;
	}

	public void setFwEmployeeBo(FwEmployeeBo fwEmployeeBo) {
		this.fwEmployeeBo = fwEmployeeBo;
	}

	public void setZgTstorageExBo(ZgTstorageExBo zgTstorageExBo) {
		this.zgTstorageExBo = zgTstorageExBo;
	}
	
	/**
	 * 根据出库或入库单编号，查找它的所进行出库或入库的半成品列表
	 * falg标志表示当前查看的数据是否为临时数据，因为出库或是入库的半成品在编辑的时候，并不是直接存入数据库，而是先保存在session
	 * 中，后面点击出入库单的“保存”或“提交”时，才从session中取出它的出入库半成品进行相应的操作
	 * @return
	 */
	public String findBomlList(){
		
		List<ZgTstorageCanclebomEx> bomECancleList =new ArrayList<ZgTstorageCanclebomEx>();
		String flag=this.getRequest().getParameter("flag");
		
		if("temp".equals(flag)){//查询临时数据
			bomECancleList=(List<ZgTstorageCanclebomEx>) this.getSession().getAttribute("bomECancleList");
		}else {
			bomECancleList=zgTstorageCanclebomExBo.findBomDEByStorageCancleId(id,productType);
			this.getSession().setAttribute("bomECancleList", bomECancleList);
			
			//保存bomIds到session中，用于页面展示可选bom的时候过滤
			String bomECancleIds="";
			for (ZgTstorageCanclebomEx obj : bomECancleList) {
				if(!obj.getIsDel()){
					bomECancleIds=bomECancleIds+obj.getStorageId()+obj.getOrderBomId()+",";
				}
			}
			this.getSession().setAttribute("bomECancleIds", bomECancleIds);
		}
		getRequest().setAttribute("view", getRequest().getParameter("view"));

		return BOM_LIST;
	}

	
}
