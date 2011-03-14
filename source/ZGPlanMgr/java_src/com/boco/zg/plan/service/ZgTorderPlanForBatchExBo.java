package com.boco.zg.plan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.dao.ZgTorderPlanExDao;
import com.boco.zg.plan.dao.ZgTorderPlanFoBatchExDao;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.util.Constants;

@Component
public class ZgTorderPlanForBatchExBo extends BaseManager<ZgTorderPlanEx,java.lang.String>{
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	
	private ZgTcarplanBo zgTcarplanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTorderPlanExDao zgTorderPlanExDao;
	private ZgTorderPlanFoBatchExDao zgTorderPlanFoBatchExDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanExDao(ZgTorderPlanExDao dao) {
		this.zgTorderPlanExDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanExDao;
	}
	
	public void setZgTorderPlanFoBatchExDao(
			ZgTorderPlanFoBatchExDao zgTorderPlanFoBatchExDao) {
		this.zgTorderPlanFoBatchExDao = zgTorderPlanFoBatchExDao;
	}
	
	/**
	 * 事务思路：把单ID orderPlanId 和 userId 插进去
	 *     	这里插入后，是为了在审核的时候能够把 审核人 跟 审核单 关联上
	 * @param orderPlanId
	 * @param state
	 * @param userId 审核人ID
	 * @typeId 看是否要进行插入历史表
	 */
	public void updateOrderPlanState(String orderPlanId,String state,String userId,int typeId) {
		zgTorderPlanExDao.updateOrderPlanState(orderPlanId, state);
		
		if(typeId==1){
			//进行插入审核意见记录表 为了以后的关联
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			zgTorderPlanComment.setOrderplanid(orderPlanId);
			zgTorderPlanComment.setUserid(userId);
			zgTorderPlanExDao.insertZgTorderPlanCommentOrderIdUserId(zgTorderPlanComment);
		}
	}
	
	public void submitOrderPlan(String orderPlanId){
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomList(orderPlanId);
		Map<String,ZgTcarplan> map = new HashMap<String,ZgTcarplan>();
		for(ZgTorderPlanbomEx obj : list) {
			String lgort = obj.getLgort();
			ZgTcarplan zgTcarplan = map.get(lgort);
			if(zgTcarplan==null) {
				zgTcarplan = new ZgTcarplan();
				zgTcarplan.setCarState("1");
				zgTcarplanBo.save(zgTcarplan);
				map.put(lgort, zgTcarplan);
			}
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(zgTcarplan.getCuid());
			bom.setOrderBomId(obj.getOrderBomId());
			bom.setOrderPlanbomId(obj.getCuid());
			bom.setOrderId(obj.getOrderId());
			bom.setPlanNum(Long.parseLong(obj.getMenge()));
			bom.setRealNum(0l);
			zgTcarbomBo.save(bom);
		}
		updateOrderPlanState(orderPlanId,"8","",2);
	}
	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}
	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}
	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}
	
	/**
	 * 获取批量领料计划
	 * @param pr
	 * @return
	 */
	public Page findBatchPlanByPageRequest(PageRequest<Map> pr) {
		return zgTorderPlanFoBatchExDao.findBatchPlanByPageRequest(pr);
	}
	
	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 */
	public boolean saveOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
		return zgTorderPlanFoBatchExDao.saveOrderPlanForBatch(zgTorderPlan);
	}
	
	/**
	 * wjz,查处审核人信息
	 */
	public List<FwEmployee> findAllAuditingPeople(PageRequest<Map> pageRequest){
		return zgTorderPlanFoBatchExDao.findAllAuditingPeople(pageRequest);
	}
	
	/**
	 * 提交批量领料计划
	 * 处理逻辑：将该订单的bom组件按按仓库分组，每一个仓库的bom组件生成一张装车计划单
	 *         更新领料计划为提交状态
	 * @param orderPlanId 领料计划单编号
	 * @param operatorInfo 创建人
	 */
	public void submitOrderPlanForBatch(ZgTorderPlan zgTorderPlan,OperatorInfo operatorInfo) {
		//查找所有的仓库
		/*List lgortList = zgTorderPlanbomExBo.findAllLgortByOrderPlanId(zgTorderPlan.getCuid());
		if(lgortList!=null && lgortList.size()>0){
			for(int i =0;i<lgortList.size();i++){
				String lgort = (String)lgortList.get(i);
				String carPlanId=this.saveCarplan(lgort,zgTorderPlan,operatorInfo);
			}
		}*/
		//pageRequest.getFilters().put("key",value);     //add custom filter
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByPlanId(zgTorderPlan.getCuid());
		String lgortId="";
		String carPlanId="";
		for(ZgTorderPlanbomEx obj : list) {
			if(Constants.CarPlanStatus.DONE.value().equals(obj.getState())){//该bom组件已经领取，则不需要再添加装车计划
				continue;
			}
			if("".equals(lgortId)){//第一次遍历 保存装车计划单
				lgortId=obj.getLgort();
				ZgTcarplan zgTcarplan = new ZgTcarplan(); 
				zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
				zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
				zgTcarplan.setCarUser(zgTorderPlan.getUserId());
				zgTcarplan.setStorageId(obj.getLgort());
				zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
				zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
				zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
				zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
				carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
			}else {
				if(!lgortId.equals(obj.getLgort())){//仓库编号不一样，则创建新的装车单
					lgortId=obj.getLgort();
					ZgTcarplan zgTcarplan = new ZgTcarplan(); 
					zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
					zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
					zgTcarplan.setCarUser(zgTorderPlan.getUserId());
					zgTcarplan.setStorageId(obj.getLgort());
					zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
					zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
					zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
					zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
					zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
					carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
				}
			}
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(carPlanId);
			bom.setOrderPlanbomId(obj.getCuid());
			bom.setPlanNum(obj.getCarNum());
			bom.setRealNum(obj.getCarNum());
			zgTcarbomBo.save(bom);
		}
	}
	
	private String saveCarplan(String lgort, ZgTorderPlan zgTorderPlan,
			OperatorInfo operatorInfo) {
		ZgTcarplan zgTcarplan = new ZgTcarplan(); 
		zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
		zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
		zgTcarplan.setCarUser(zgTorderPlan.getUserId());
		zgTcarplan.setStorageId(lgort);
		zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
		zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
		zgTcarplan.setOrderPlanType(Constants.OrderPlanType.PLTYPE.value());
		zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
		String carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
		return carPlanId;
	}
	/**
	 * 保存批量领料计划,包括新建
	 * 思路：只能是‘新建’来提交的才去 插入审核意见记录表
	 * @param zgTorderPlan
	 * @param auiditingUserId 审核人ID
	 * @param typaId 1为新建来提交的，4为保存
	 */
	public void saveOrderPlan(ZgTorderPlan zgTorderPlan,String auiditingUserId,String typaId) {
		zgTorderPlanFoBatchExDao.updateOrderPlanForBatch(zgTorderPlan);
		
		//进行插入审核意见记录表 为了以后的关联
		//'新建'状态来提交才去 插入审核意见记录表
		if(typaId.equals(Constants.OrderPlanStatus.WAITAUDITING.value())){
			ZgTorderPlanComment zgTorderPlanComment=new ZgTorderPlanComment();
			zgTorderPlanComment.setOrderplanid(zgTorderPlan.getCuid());
			zgTorderPlanComment.setUserid(auiditingUserId);
			zgTorderPlanExDao.insertZgTorderPlanCommentOrderIdUserId(zgTorderPlanComment);
		}
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划
	 * @param orderPlanId
	 */
	public void deleteCarPlanByOrderPlanId(String orderPlanId) {
		zgTorderPlanFoBatchExDao.deleteCarPlanByOrderPlanId(orderPlanId);
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划BOM
	 * @param orderPlanId
	 */
	public void deleteCarBomByOrderPlanId(String orderPlanId) {
		zgTorderPlanFoBatchExDao.deleteCarBomByOrderPlanId(orderPlanId);
	}
	
	/**
	 * 删除待审核和保存的批量领料计划单，并且删除Bom和Comment
	 * @param orderPlanId
	 */
	public void deleteBatchOrderPlan(String orderPlanId){
		zgTorderPlanFoBatchExDao.deleteOrderPlanCommentByPlanId(orderPlanId);
		zgTorderPlanFoBatchExDao.deleteOrderPlanBomByPlanId(orderPlanId);
		zgTorderPlanFoBatchExDao.deleteOrderPlan(orderPlanId);
	}
	
	/**
	 * 查询换料列表
	 * @param pageRequest
	 * @return
	 */
	public Page zgTBomManagerBo(PageRequest<Map> pageRequest) {
		return null;
	}

	
	
}
