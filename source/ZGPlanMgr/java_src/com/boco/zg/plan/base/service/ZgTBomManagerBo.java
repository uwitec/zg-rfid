/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.dao.ZgTBomManagerDao;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.util.Constants;

/**
 * @author 熊警
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTBomManagerBo extends BaseManager<ZgTorderPlan,java.lang.String>{
	private ZgTBomManagerDao zgTBomManagerDao;
    private ZgTorderPlanbomBo zgTorderPlanbomBo;
    private ZgTorderPlanbom zgTorderPlanbom;
    private ZgTcarplanBo zgTcarplanBo;
    private ZgTcarbomBo zgTcarbomBo;

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
	
	public Page pageOrderPlanForChange2(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.pageOrderPlanForChange2(pageRequest);
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
	
	
	public void synSessionBomToDataBase(List<Map> bol,ZgTorderPlan zgTorderPlan,String planType) {
		
		for (Map obj : bol) {
			//if(obj.get("isDel")==null){
			 boolean b=((Boolean)obj.get("isDel")).booleanValue();
			 if(b==false){
					ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
					
					
					zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
					
					
				   //zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					if(Constants.OrderPlanType.BACK.value().equals(planType)){
						
					}else {
						zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
					zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
					zgTorderPlanbom.setState("0");
					zgTorderPlanbomBo.save1(zgTorderPlanbom);
				}
			 }
			
			 
		   }
	
	/**
	 * create by 熊警
	 * @param bol
	 * @param zgTorderPlan
	 * @param cuid
	 * @param waitBackNum
	 */
	@SuppressWarnings("unchecked")
	public void updateOrderPlanBomWaitBackNum(List<Map> bol,ZgTorderPlan zgTorderPlan,String planType) {
		//System.out.print(obj.get("CUID"));
		for (Map obj : bol) {
			//System.out.print(obj.get("CUID").toString());
			
			if(obj.get("isModity")==null||"".equals(obj.get("isModity"))){//空代表从数据库中获取而且没有修改过
			
				System.out.print("a");
			}
//			else if(((Boolean)obj.get("isModity")).booleanValue()==false){//FALSE代表新添加但没有修改
//	            ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
//				
//				zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
//				
//				zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
//				
//				//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
//				zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
//				zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
//				zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
//				zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
//				zgTorderPlanbom.setState("0");
//				zgTorderPlanbomBo.save1(zgTorderPlanbom);
//			}else 
			else if(((Boolean)obj.get("isModity")).booleanValue()==true){ //代表数据修改过但这个是从数据库中获取
				if(obj.get("isDel")==null&&obj.get("PBID")!=null){ //删除为空代表数据库中获取的数据没有删除状态
					zgTorderPlanbom=(ZgTorderPlanbom)zgTorderPlanbomBo.getById(obj.get("PBID").toString());
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){//补领料时不需要退料
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Long.valueOf(obj.get("WAIT_BACK_NUM").toString()));
					}
					
					if(Constants.OrderPlanType.BACK.value().equals(planType)){//退料时不需要领料
						
					}else {
						zgTorderPlanbom.setCarNum(Long.valueOf(obj.get("WAIT_BACK_NUM").toString()));
					}
				   
				    
				 	zgTorderPlanbom.setState("0");
				    zgTorderPlanbomBo.update(zgTorderPlanbom);
				}else if(((Boolean)obj.get("isDel")).booleanValue()==true){//删除为TRUE代表数据库中获取的数据有删除状态
					String pbId=obj.get("PBID")==null?"":obj.get("PBID").toString();
					zgTorderPlanbomBo.removeById(pbId);
				}else if(obj.get("PBID")==null){  //表示新添加的bom
					ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
					
					zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
					
					zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
					
					//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){//补领料时不需要退料
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					if(Constants.OrderPlanType.BACK.value().equals(planType)){//退料时不需要领料
						
					}else {
						zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
						
					zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
					zgTorderPlanbom.setState("0");
					zgTorderPlanbomBo.save1(zgTorderPlanbom);
				}
		  }
				
//				
//			else{  //新添orderPlanBom
//				ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
//				
//				zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
//				
//				zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
//				
//				//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
//				zgTorderPlanbom.setWaitBackNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
//				zgTorderPlanbom.setCarNum(Long.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
//				zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
//				zgTorderPlanbom.setStorageNum(Long.valueOf(0));  
//				zgTorderPlanbom.setState("0");
//				zgTorderPlanbomBo.save1(zgTorderPlanbom);
//			  }
			
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

	/**
	 * @param pageRequest
	 * @return
	 */
	public Page pageListBackBom(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.pageListBackBom(pageRequest);
	}

	/**
	 * 获取登陆领料人负责的生产厂
	 * @param operatorId
	 * @return
	 */
	public List<Map> getPlanListByOperatorId(String operatorId) {
		return zgTBomManagerDao.getPlanListByOperatorId(operatorId);
	}

	/**
	 * 检查退料数量
	 * @param bom
	 */
	public boolean checkBackNum(ZgTorderbomEx bom) {
		return zgTBomManagerDao.checkBackNum(bom);
		
	}

	/**
	 * 退料处理
	 * @param bomList
	 */
	public void backBom(List<ZgTorderbomEx> bomList,String operatorId,String storageId) {
		int num=0;
		String carPlanId="";
		for(ZgTorderbomEx obj:bomList){
			if(num==0){//生产退料单据
				ZgTcarplan zgTcarplan = new ZgTcarplan(); 
				zgTcarplan.setCreateUserId(operatorId);
				zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
				zgTcarplan.setCarUser(operatorId);
				zgTcarplan.setCarState(Constants.CarPlanStatus.DONE.value());
				zgTcarplan.setOrderPlanType(Constants.OrderPlanType.BACK.value());
				zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
				zgTcarplan.setIsManul(Constants.isNotManulFinished);
				carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
			}
			
			ZgTorderPlanbom planbom=zgTorderPlanbomBo.getById(obj.getOrderPlanbomId());
			if(planbom.getWaitBackNum()<obj.getBackNum()){
				continue;
			}
			
			//退料单据明细
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(carPlanId);
			bom.setOrderPlanbomId(bom.getOrderPlanbomId());
			bom.setPlanNum(obj.getBackNum());
			bom.setRealNum(obj.getBackNum());
			zgTcarbomBo.save(bom);
			
			//更新退料BOM数量
			planbom.setWaitBackNum(planbom.getWaitBackNum()-obj.getBackNum());
			planbom.setBackNum(planbom.getBackNum()+obj.getBackNum());
			planbom.setStorageNum(planbom.getStorageNum()+obj.getBackNum());
			if(!planbom.getState().equals(Constants.OrderPlanStatus.FINISHED.value())&&planbom.getCarNum()==planbom.getCompleteNum()){
				if(planbom.getWaitBackNum()==0){
					planbom.setState(Constants.OrderPlanStatus.FINISHED.value());
				}
			}
			
			zgTorderPlanbomBo.save(planbom);
			
			//更新退料申请单状态
		}
	}

	public ZgTcarplanBo getZgTcarplanBo() {
		return zgTcarplanBo;
	}

	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	} 
 }

