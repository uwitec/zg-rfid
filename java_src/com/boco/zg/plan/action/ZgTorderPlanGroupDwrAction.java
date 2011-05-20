package com.boco.zg.plan.action;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;
import javacommon.util.StringHelper;

import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanGroupExBo;
import com.boco.zg.util.Constants;

public class ZgTorderPlanGroupDwrAction extends BaseDwrAction {
	
	private ZgTorderPlanGroupExBo getZgTorderPlanGroupExBo() {
		return (ZgTorderPlanGroupExBo)ApplicationContextHolder.getBean("zgTorderPlanGroupExBo");
	}
	
	private ZgTorderPlanExBo getZgTorderPlanExBo() {
		return (ZgTorderPlanExBo)ApplicationContextHolder.getBean("zgTorderPlanExBo");
	}
	
	private ZgTorderBo getZgTorderBo(){
		return (ZgTorderBo)ApplicationContextHolder.getBean("zgTorderBo");

	}
	
	
	/**
	 * 暂停订单领料
	 * @param groupIds
	 * @return
	 */
	public boolean pauseOrder(String groupIds,String planType){
		String[] groupId=groupIds.split(",");
		for(String id:groupId){
			getZgTorderPlanGroupExBo().changeOrderState(id,Constants.OrderPlanStatus.PAUSE.value());
			
			//暂停分组下的订单
			getZgTorderPlanExBo().changeOrderStateByGroupId(id,Constants.OrderPlanStatus.PAUSE.value());
			
			
			
			//冻结订单,如果是预装，预焊订单暂停，则涉及到订单冻结
			if(!planType.equals(Constants.sorft.ABE.value())){
				
				//获取该订单分组下面的所有订单id
				List<Map> orderList=getZgTorderPlanExBo().getOrderListByGroupId(id);
				for(Map orderMap:orderList){
					ZgTorder zgTorder= getZgTorderBo().getById(IbatisDAOHelper.getStringValue(orderMap, "CUID"));
					if(Constants.sorft.ABC.value().equals(planType)){//预焊处理
						if (Constants.OrderFreezeState.ABD.value().equals(zgTorder.getFreeZe())) {
							//原来订单为预装冻结　则置状态为预焊预装状态　
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABCABD.value());
						}else{
							//否则置为预焊冻结状态
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABC.value());
						} 
					}
					
					if(Constants.sorft.ABD.value().equals(planType)){//预焊处理
						if (Constants.OrderFreezeState.ABC.value().equals(zgTorder.getFreeZe())) {
							//原来订单为预装冻结　则置状态为预焊预装状态　
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABCABD.value());
						}else{
							//否则置为预装冻结状态
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABD.value());
						} 
					}
					
					getZgTorderBo().update(zgTorder);
				}
			}
			
			
			
		}
		return true;
	}
	
	/**
	 * 恢复订单领料
	 * @param groupIds
	 * @return
	 */
	public boolean revertOrder(String groupIds,String planType){
		String[] groupId=groupIds.split(",");
		for(String id:groupId){
			getZgTorderPlanGroupExBo().changeOrderState(id,Constants.OrderPlanStatus.NEW.value());
			
			//恢复分组下的订单
			getZgTorderPlanExBo().changeOrderStateByGroupId(id,Constants.OrderPlanStatus.NEW.value());
			
			getZgTorderPlanExBo().updateZbzByGroupId(id,"");
			
			//冻结订单,如果是预装，预焊订单暂停，则涉及到订单解冻结
			if(!planType.equals(Constants.sorft.ABE.value())){
				
				//获取该订单分组下面的所有订单id
				List<Map> orderList=getZgTorderPlanExBo().getOrderListByGroupId(id);
				for(Map orderMap:orderList){
					ZgTorder zgTorder= getZgTorderBo().getById(IbatisDAOHelper.getStringValue(orderMap, "CUID"));
					if(Constants.sorft.ABC.value().equals(planType)){//预焊处理
						if (Constants.OrderFreezeState.ABCABD.value().equals(zgTorder.getFreeZe())) {
							//原来订单为预焊预装冻结　则置预装冻结
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABD.value());
						}else{
							//否则置为正常状态
							zgTorder.setFreeZe(Constants.OrderFreezeState.normal.value());
						} 
					}
					
					if(Constants.sorft.ABD.value().equals(planType)){//预装处理
						if (Constants.OrderFreezeState.ABCABD.value().equals(zgTorder.getFreeZe())) {
							//原来订单为预焊预装冻结　则置预焊冻结
							zgTorder.setFreeZe(Constants.OrderFreezeState.ABC.value());
						}else{
							//否则置为正常状态
							zgTorder.setFreeZe(Constants.OrderFreezeState.normal.value());
						} 
					}
					
					getZgTorderBo().update(zgTorder);
				}
			}
		}
		return true;
	}
	
	/**
	 * 订单合并
	 * @param groupIds 合并的领料计划组编号
	 * @param date 日期
	 * @param sorft 排序号
	 * @param arbpl 生产线
	 * @return
	 */
	public boolean mergeOrder(String groupIds,String date,String sorft,String arbpl,HttpSession session){
		String plant=session.getAttribute("defaultOrgId")==null?"":session.getAttribute("defaultOrgId").toString();
		getZgTorderPlanGroupExBo().mergeOrder(groupIds,date,sorft,arbpl,plant);
		return true;
	}
	

	/**
	 * 订单取消合并
	 * @param groupIds 合并的领料计划组编号
	 * @param date 日期
	 * @param sorft 排序号
	 * @param arbpl 生产线
	 * @return
	 */
	public boolean cancleMergeOrder(String groupIds,String date,String sorft,String arbpl,HttpSession session){
		String plant=session.getAttribute("defaultOrgId")==null?"":session.getAttribute("defaultOrgId").toString();
		getZgTorderPlanGroupExBo().cancleMergeOrder(groupIds,date,sorft,arbpl,plant);
		return true;
	}
	
	/**
	 * 改变订单的排序号
	 * @param groupIds
	 */
	public String changeOrderPlanGroupPsbh(String groupIds){
		if(!StringHelper.isEmpty(groupIds)){
			String[] groupId=groupIds.split(",");
			int count=1;
			for(String id:groupId){
				getZgTorderPlanGroupExBo().changeIndexNoByGroupId(id,count);
				count++;
			}
		}
		
		
		return "OK";
	}
	
	public String addPlanZbz(String groupIds,String zbz){
		String[] groupId=groupIds.split(",");
		for(String id:groupId){
			getZgTorderPlanExBo().updateZbzByGroupId(id,zbz);
			
		}
		return "OK";
	}
	
}
