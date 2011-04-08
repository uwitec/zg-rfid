/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.base.model.ZgTstoragebom;
import com.boco.zg.storage.model.ZgTstoragebomEx;

/**
 * @author 翁钦
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTBomManagerBo extends BaseManager<ZgTorderPlan,java.lang.String>{
	private ZgTBomManagerDao zgTBomManagerDao;
    private ZgTorderPlanbomBo zgTorderPlanbomBo;
    private ZgTorderPlanbom zgTorderPlanbom;

	/* (non-Javadoc)
	 * @see javacommon.base.BaseManager#getEntityDao()
	 */
	@Override
	protected EntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}
    
	
	public String isModity;
	/**
	 * @return
	 */
	public String getCuid() {
		return zgTBomManagerDao.queryBySql("SELECT 'A'||to_char(SYSTIMESTAMP,'yyyymmddhhmissff') cuid FROM DUAL").get(0).get("CUID").toString();
	}

	public void setZgTBomManagerDao(ZgTBomManagerDao zgTBomManagerDao) {
		this.zgTBomManagerDao = zgTBomManagerDao;
	}

	/**
	 * 查询换料列表
	 * @param pageRequest
	 * @return
	 */
	public Page pageOrderPlanForChange(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.pageOrderPlanForChange(pageRequest);
	}
	
	public Page pageOrderPlanForChange1(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.pageOrderPlanForChange1(pageRequest);
	}

	/**
	 * 根据单据编号查找其bom列表
	 * @param id
	 * @return
	 */
	public List<Map> findBomListByPlanID(String str) {
		return zgTBomManagerDao.findBomListByPlanID(str);
	}

	/**
	 * 根据领料计划id查找历史审核记录
	 * @param id str 代表orderPlanId
	 * @return
	 */
	public List<Map> findqueryHistoryPlanID(String str) {
		return zgTBomManagerDao.findqueryHistoryPlanID(str);
	}
    public String getRoleCuidByUserId(String userId){
    	  return zgTBomManagerDao.getRoleCuidByUserId(userId);
    }
	/**
	 * 根据订单编号查找其BOM列表
	 * @param pageRequest
	 */
	public List<Map> findBomlListByOrderId(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.findBomlListByOrderId(pageRequest);
	}
	
	
	public void synSessionBomToDataBase(List<Map> bol,ZgTorderPlan zgTorderPlan) {
		
		for (Map obj : bol) {
			//if(obj.get("isDel")==null){
			 boolean b=((Boolean)obj.get("isDel")).booleanValue();
			 if(b==false){
			ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
			
			
			zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
			
			zgTorderPlanbom.setOrderId(zgTorderPlan.getOrderId());
			
		   //zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
			zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
			zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
			zgTorderPlanbom.setOrderBomId(String.valueOf(obj.get("CUID")));
			zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
			zgTorderPlanbomBo.save1(zgTorderPlanbom);
				}
			 }
			
			 
		   }
	
	/**
	 * 
	 * @param bol
	 * @param zgTorderPlan
	 * @param cuid
	 * @param waitBackNum
	 */
	@SuppressWarnings("unchecked")
	public void updateOrderPlanBomWaitBackNum(List<Map> bol,ZgTorderPlan zgTorderPlan) {
		//System.out.print(obj.get("CUID"));
		for (Map obj : bol) {
			//System.out.print(obj.get("CUID").toString());
			
			if(obj.get("isModity")==null||"".equals(obj.get("isModity"))){//空代表从数据库中获取而且没有修改过
			
				System.out.print("a");
			}else if(((Boolean)obj.get("isModity")).booleanValue()==false){//FALSE代表新添加但没有修改
	            ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
				
				zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
				
				zgTorderPlanbom.setOrderId(zgTorderPlan.getOrderId());
				
				//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
				zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
				zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
				zgTorderPlanbom.setOrderBomId(String.valueOf(obj.get("CUID")));
				zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
				zgTorderPlanbomBo.save1(zgTorderPlanbom);
			}else if(((Boolean)obj.get("isModity")).booleanValue()==true&&obj.get("PBID")!=null){ //代表数据修改过但这个是从数据库中获取
				if(obj.get("isDel")==null){ //删除为空代表数据库中获取的数据没有删除状态
					System.out.println("============="+obj.get("PBID").toString()+":"+Long.valueOf(obj.get("WAIT_BACK_NUM").toString()));
					zgTorderPlanbom=(ZgTorderPlanbom)zgTorderPlanbomBo.getById(obj.get("PBID").toString());
				     zgTorderPlanbom.setWaitBackNum(Long.valueOf(obj.get("WAIT_BACK_NUM").toString()));
				     zgTorderPlanbom.setCarNum(Long.valueOf(obj.get("WAIT_BACK_NUM").toString()));
				     zgTorderPlanbomBo.update(zgTorderPlanbom);
				}else if(((Boolean)obj.get("isDel")).booleanValue()==true){//删除为TRUE代表数据库中获取的数据有删除状态
					zgTorderPlanbomBo.removeById(obj.get("PBID").toString());
				}else if(obj.get("PLANBOMID")==null){  //表示新添加的bom
					ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
					
					zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
					
					zgTorderPlanbom.setOrderId(zgTorderPlan.getOrderId());
					
					//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					zgTorderPlanbom.setOrderBomId(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
					zgTorderPlanbomBo.save1(zgTorderPlanbom);
				}
		  }
				
				
			else{  //新添orderPlanBom
				ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
				
				zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
				
				zgTorderPlanbom.setOrderId(zgTorderPlan.getOrderId());
				
				//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
				zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
				zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
				zgTorderPlanbom.setOrderBomId(String.valueOf(obj.get("CUID")));
				zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
				zgTorderPlanbomBo.save1(zgTorderPlanbom);
			  }
			
		}
	}

	     
	
	 
	public ZgTorderPlanbomBo getZgTorderPlanbomBo() {
		return zgTorderPlanbomBo;
	}

	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}

	public ZgTorderPlanbom getZgTorderPlanbom() {
		return zgTorderPlanbom;
	}

	public void setZgTorderPlanbom(ZgTorderPlanbom zgTorderPlanbom) {
		this.zgTorderPlanbom = zgTorderPlanbom;
	}

	public ZgTBomManagerDao getZgTBomManagerDao() {
		return zgTBomManagerDao;
	} 
 }

