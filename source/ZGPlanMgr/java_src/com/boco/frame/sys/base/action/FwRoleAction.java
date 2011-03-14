/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import javacommon.base.service.IVmModelBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;

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

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;
import com.boco.zg.plan.service.ZgTorderbomExBo;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwRoleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/frame/sys/FwRole/query_FwRole.jsp";
	protected static final String LIST_JSP= "/frame/sys/FwRole/list_FwRole.jsp";
	protected static final String CREATE_JSP = "/frame/sys/FwRole/create_FwRole.jsp";
	protected static final String EDIT_JSP = "/frame/sys/FwRole/edit_FwRole.jsp";
	protected static final String SHOW_JSP = "/frame/sys/FwRole/show_FwRole.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/frame/sys/FwRole/list.do";
	
	private FwRoleBo fwRoleBo;
	
	private FwMenuBo fwMenuBo;
	
	private FwRoleMenuBo fwRoleMenuBo;
	
	private IVmModelBo vmModelBo;
	
	private FwOperatorLogBo fwOperatorLogBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}
	
	private FwRole fwRole;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			fwRole = new FwRole();
		} else {
			fwRole = (FwRole)fwRoleBo.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setFwRoleBo(FwRoleBo bo) {
		this.fwRoleBo = bo;
	}	
	
	public void setFwMenuBo(FwMenuBo fwMenuBo) {
		this.fwMenuBo = fwMenuBo;
	}

	public void setFwRoleMenuBo(FwRoleMenuBo fwRoleMenuBo) {
		this.fwRoleMenuBo = fwRoleMenuBo;
	}

	public Object getModel() {
		return fwRole;
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
		//pageRequest.getFilters().put("key",value);     //add custom filter
		//getRequest().setAttribute("attrMap",vmModelBo.getAttrsByUser(fwRole.BM_CLASS_ID,super.getSessionUserId()));
		Page page = fwRoleBo.findByPageRequest(pageRequest);
		
		/*System.out.println(page.getResult().size());
		List list  = page.getResult();
		for(int i=0;i<list.size();i++){
			FwRole obj  = (FwRole)list.get(i);
			if(obj.getNote()!=null && !obj.getNote().equals("")){
				FwMenu entity = new FwMenu();
				String note = obj.getNote();
				note = note.replace(",", "','");
				note = "'"+note+"'";
				entity.setSqlQueryString(" m.t0_CUID in ("+note+")");
				List<FwMenu> listMenu = this.fwMenuBo.findByProperty(entity);
				String menuStr = ""; 
				for(FwMenu fwMenu:listMenu){
					String labelCn = fwMenu.getLabelCn();
					menuStr=menuStr+labelCn+" ";
				}
				obj.setNote(menuStr);
			}
		}*/
		savePage(page,pageRequest);
		return LIST_JSP;
	}
	

	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return "/frame/sys/FwRole/create_FwRoleByMenu.jsp";
	}
	
	/** 保存新增对象 */
	public String save() {
		String cuid=(String) fwRoleBo.save(fwRole);
		

		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(cuid);
		fwOperatorLog.setTargetName(fwRole.getLabelCn());
		fwOperatorLog.setAction("add");
		fwOperatorLog.setOperatorType("角色管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		StringBuffer menuIds = new StringBuffer();
		FwRoleMenu entity = new FwRoleMenu();
		entity.setRoleId(fwRole.getCuid());
		List<FwRoleMenu> list = this.fwRoleMenuBo.findByProperty(entity);
		for(FwRoleMenu fwRoleMenu : list){
			menuIds.append(fwRoleMenu.getMenuId()+",");
		}
		this.getRequest().setAttribute("menuIds", menuIds.toString());
		return "/frame/sys/FwRole/edit_FwRoleByMenu.jsp";
	}
	
	/**保存更新对象*/
	public String update() {
		fwRoleBo.update(this.fwRole);
		
		//保存操作记录
		FwOperatorLog fwOperatorLog=new FwOperatorLog();
		fwOperatorLog.setCreateId(getSessionOperatorId());
		fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
		fwOperatorLog.setTargetId(fwRole.getCuid());
		fwOperatorLog.setTargetName(fwRole.getLabelCn());
		fwOperatorLog.setAction("update");
		fwOperatorLog.setOperatorType("角色管理");
		
		fwOperatorLogBo.save(fwOperatorLog);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			fwRole=fwRoleBo.getById((java.lang.String)params.get("id"));
			fwRoleBo.removeById((java.lang.String)params.get("id"));
			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			fwOperatorLog.setCreateId(getSessionOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(fwRole.getCuid());
			fwOperatorLog.setTargetName(fwRole.getLabelCn());
			fwOperatorLog.setAction("delete");
			fwOperatorLog.setOperatorType("角色管理");
			
			fwOperatorLogBo.save(fwOperatorLog);
		}
		return LIST_ACTION;
	}
	
	
	/** 保存新增对象 */
	public boolean saveRoleByTree(String lable,String treeIds,HttpSession session) {
		try{
			FwRole fwRole  = new FwRole();
			fwRole.setLabelCn(lable);
			//fwRole.setNote(treeIds);
			fwRole.setType("1");
			FwRoleBo fwRoleBo = (FwRoleBo) ApplicationContextHolder.getBean("fwRoleBo");
			String cuid=(String) fwRoleBo.save(fwRole);
			FwRoleMenuBo fwRoleMenuBo = (FwRoleMenuBo)ApplicationContextHolder.getBean("fwRoleMenuBo");
			String[] menus = treeIds.split(",");
			for(String menu:menus){
				FwRoleMenu fwRoleMenu = new FwRoleMenu();
				fwRoleMenu.setMenuId(menu);
				fwRoleMenu.setRoleId(fwRole.getCuid());
				fwRoleMenuBo.save(fwRoleMenu);
			}

			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			OperatorInfo operator=(OperatorInfo) session.getAttribute("operatorInfo");
			fwOperatorLog.setCreateId(operator.getOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(cuid);
			fwOperatorLog.setTargetName(fwRole.getLabelCn());
			fwOperatorLog.setAction("add");
			fwOperatorLog.setOperatorType("角色管理");
			
			getOperatorLogBo().save(fwOperatorLog);
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/** 修改对象 */
	public boolean updateRoleByTree(String cuid,String lable,String treeIds,HttpSession session) {
		try{
			FwRole fwRole  = new FwRole();
			fwRole.setCuid(cuid);
			fwRole.setLabelCn(lable);
			//fwRole.setNote(treeIds);
			fwRole.setType("1");
			FwRoleBo fwRoleBo = (FwRoleBo) ApplicationContextHolder.getBean("fwRoleBo");
			fwRoleBo.update(fwRole);
			FwRoleMenuBo fwRoleMenuBo = (FwRoleMenuBo)ApplicationContextHolder.getBean("fwRoleMenuBo");
			fwRoleMenuBo.removeByRoleId(cuid);
			String[] menus = treeIds.split(",");
			for(String menu:menus){
				FwRoleMenu fwRoleMenu = new FwRoleMenu();
				fwRoleMenu.setMenuId(menu);
				fwRoleMenu.setRoleId(fwRole.getCuid());
				fwRoleMenuBo.save(fwRoleMenu);
			}
			
			//保存操作记录
			FwOperatorLog fwOperatorLog=new FwOperatorLog();
			OperatorInfo operator=(OperatorInfo) session.getAttribute("operatorInfo");
			fwOperatorLog.setCreateId(operator.getOperatorId());
			fwOperatorLog.setCreateDate(Calendar.getInstance().getTime());
			fwOperatorLog.setTargetId(fwRole.getCuid());
			fwOperatorLog.setTargetName(fwRole.getLabelCn());
			fwOperatorLog.setAction("update");
			fwOperatorLog.setOperatorType("角色管理");
			
			getOperatorLogBo().save(fwOperatorLog);
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public FwOperatorLogBo getFwOperatorLogBo() {
		return fwOperatorLogBo;
	}

	public void setFwOperatorLogBo(FwOperatorLogBo fwOperatorLogBo) {
		this.fwOperatorLogBo = fwOperatorLogBo;
	}

	public FwOperatorLogBo getOperatorLogBo(){
		return (FwOperatorLogBo)ApplicationContextHolder.getBean("fwOperatorLogBo");

	}
}
