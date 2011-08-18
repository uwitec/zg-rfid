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
import javacommon.util.StringHelper;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.meta.dao.IbatisDAOHelper;
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
    private ZgTorderPlanBo zgTorderPlanBo;

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
		String apply=pageRequest.getFilters().get("apply").toString();
		String type=pageRequest.getFilters().get("type").toString();
		if("manul".endsWith(apply)){
			if(StringHelper.isEmpty(type)){
				pageRequest.getFilters().put("type", Constants.MANULCHANGESORTF);
			}
		}else {
			if(StringHelper.isEmpty(type)){
				pageRequest.getFilters().put("type", Constants.ORDERCHANGESORTF);
			}else if ("BACK".equals(type)) {
				pageRequest.getFilters().put("type", Constants.ORDERCHANGESORTF);
			}
		}
		
		return zgTBomManagerDao.pageOrderPlanForChange(pageRequest);
	}
	
	public Page pageOrderPlanForChange1(PageRequest<Map> pageRequest) {
		String type=pageRequest.getFilters().get("type").toString();
		if(StringHelper.isEmpty(type)){
				pageRequest.getFilters().put("type", Constants.ALLCHANGESORTF);
		}
		return zgTBomManagerDao.pageOrderPlanForChange1(pageRequest);
	}
	
	public Page pageOrderPlanForChange2(PageRequest<Map> pageRequest) {
		String type=pageRequest.getFilters().get("type").toString();
		if(StringHelper.isEmpty(type)){
				pageRequest.getFilters().put("type", Constants.ALLCHANGESORTF);
		}
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
			String bomType=IbatisDAOHelper.getStringValue(obj, "BOM_TYPE");
			 boolean b=((Boolean)obj.get("isDel")).booleanValue();
			 if(b==false){//删除状态为假，则直接插入
					ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
					
					zgTorderPlanbom.setBomType(bomType);
					zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
					zgTorderPlanbom.setZbz(String.valueOf(obj.get("ZBZ")));
					
					
				   //zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Double.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					if(planType.contains("BACK")){
						
					}else {
						zgTorderPlanbom.setCarNum(Double.parseDouble(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					//获取该退换料记录的主记录
					ZgTorderPlanbom parentPlanbom=zgTorderPlanbomBo.getParentPlanBomByTaskBomId(String.valueOf(obj.get("CUID")));
					if(!planType.equals(Constants.OrderPlanType.CHANGE.value())){//注：换料不需要汇总，因为一退一领备料数量是不会改变的
						parentPlanbom.setWaitBackNum(parentPlanbom.getWaitBackNum()+Double.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
						zgTorderPlanbomBo.update(parentPlanbom);
					}
					
					
					zgTorderPlanbom.setParentId(parentPlanbom.getCuid());
					zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
					zgTorderPlanbom.setStorageNum(0d);  
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
			}else if(((Boolean)obj.get("isModity")).booleanValue()==true){ //代表数据修改过但这个是从数据库中获取
				if((obj.get("isDel")==null||((Boolean)obj.get("isDel")).booleanValue()==false)&&obj.get("PBID")!=null){ //删除为空代表数据库中获取的数据没有删除状态
					zgTorderPlanbom=(ZgTorderPlanbom)zgTorderPlanbomBo.getById(obj.get("PBID").toString());
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){//补领料时不需要退料
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Double.valueOf(obj.get("WAIT_BACK_NUM").toString()));
					}
					
					if(planType.contains("BACK")){//退料时不需要领料
						
					}else {
						zgTorderPlanbom.setCarNum(Double.parseDouble(obj.get("WAIT_BACK_NUM").toString()));
					}
				   
					zgTorderPlanbom.setZbz(IbatisDAOHelper.getStringValue(obj, "ZBZ"));
				 	zgTorderPlanbom.setState("0");
				    zgTorderPlanbomBo.update(zgTorderPlanbom);
				    
				    //汇总父领料记录的待退料数
					if(!planType.equals(Constants.OrderPlanType.CHANGE.value())){//注：换料不需要汇总，因为一退一领备料数量是不会改变的
						ZgTorderPlanbom parentPbom=zgTorderPlanbomBo.getById(zgTorderPlanbom.getParentId());
					    zgTorderPlanbomBo.upDateParentWaitBackNumByPlanBom(parentPbom,zgTorderPlanbom.getOrderPlanId(),null);
					}
				    
				}else if(((Boolean)obj.get("isDel")).booleanValue()==true){//删除为TRUE代表数据库中获取的数据有删除状态
					String pbId=obj.get("PBID")==null?"":obj.get("PBID").toString();
					if(!StringHelper.isEmpty(pbId)){
						zgTorderPlanbom=(ZgTorderPlanbom)zgTorderPlanbomBo.getById(pbId);
						zgTorderPlanbomBo.removeById(pbId);
						
						if(!planType.equals(Constants.OrderPlanType.CHANGE.value())){//注：换料不需要汇总，因为一退一领备料数量是不会改变
							//汇总父领料记录的待退料数
						    ZgTorderPlanbom parentPbom=zgTorderPlanbomBo.getById(zgTorderPlanbom.getParentId());
						    zgTorderPlanbomBo.upDateParentWaitBackNumByPlanBom(parentPbom,zgTorderPlanbom.getOrderPlanId(),null);
						}
						 
					}
					
				}else if(obj.get("PBID")==null){  //表示新添加的bom
					ZgTorderPlanbom zgTorderPlanbom=new ZgTorderPlanbom();
					
					zgTorderPlanbom.setOrderPlanId(zgTorderPlan.getCuid());
					
					zgTorderPlanbom.setOrderTaskId(zgTorderPlan.getOrderTaskId());
					zgTorderPlanbom.setZbz(String.valueOf(obj.get("ZBZ")));
					
					//zgTorderPlanbom.setCuid(String.valueOf(obj.get("CUID")));
					if(Constants.OrderPlanType.RENEW.value().equals(planType)){//补领料时不需要退料
						
					}else {
						zgTorderPlanbom.setWaitBackNum(Double.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
					
					if(planType.contains("BACK")){//退料时不需要领料
						
					}else {
						zgTorderPlanbom.setCarNum(Double.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
					}
						
					//获取该退换料记录的主记录
					ZgTorderPlanbom parentPlanbom=zgTorderPlanbomBo.getParentPlanBomByTaskBomId(String.valueOf(obj.get("CUID")));
					if(!planType.equals(Constants.OrderPlanType.CHANGE.value())){//注：换料不需要汇总，因为一退一领备料数量是不会改变
						parentPlanbom.setWaitBackNum(parentPlanbom.getWaitBackNum()+Double.valueOf(String.valueOf(obj.get("WAIT_BACK_NUM"))));
						zgTorderPlanbomBo.update(parentPlanbom);
					}
					
					
					zgTorderPlanbom.setParentId(parentPlanbom.getCuid());
					zgTorderPlanbom.setTaskBomId(String.valueOf(obj.get("CUID")));
					zgTorderPlanbom.setStorageNum(0d);  
					zgTorderPlanbom.setState("0");
					zgTorderPlanbomBo.save1(zgTorderPlanbom);
				}
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

	/**
	 * @param planId
	 * @return
	 */
	public void deletePlan(String planId) {
	
		ZgTorderPlan plan=zgTorderPlanBo.getById(planId);
		 //汇总父领料记录的待退料数
		List<Map> pbList=findBomListByPlanID(planId);
		for(Map pbom:pbList){
			String planBomId=IbatisDAOHelper.getStringValue(pbom, "PBID");
			String parentId=IbatisDAOHelper.getStringValue(pbom, "PARENT_ID");
			String orderPlanId=IbatisDAOHelper.getStringValue(pbom, "ORDER_PLAN_ID");
			zgTorderPlanbomBo.removeById(planBomId);
			
			if(!plan.getPlanType().equals(Constants.OrderPlanType.CHANGE.value())){//注：换料不需要汇总，因为一退一领备料数量是不会改变
				//汇总计算退料数量
				ZgTorderPlanbom parentPbom=zgTorderPlanbomBo.getById(parentId);
				zgTorderPlanbomBo.upDateParentWaitBackNumByPlanBom(parentPbom,orderPlanId,null);
			}
			
			
		}
		
		zgTorderPlanBo.removeById(planId);
	}

	/**
	 * @return the zgTorderPlanBo
	 */
	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}

	/**
	 * @param zgTorderPlanBo the zgTorderPlanBo to set
	 */
	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}

	/**
	 * 手工完结退料物料
	 * @param pbId
	 */
	public void manulFinishBom(String pbId) {
		zgTBomManagerDao.manulFinishBom(pbId);
	}
 }

