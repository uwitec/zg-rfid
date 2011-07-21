package com.boco.zg.plan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTgroupOrderPlanDao;
import com.boco.zg.plan.base.model.ZgTgroupOrderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanGroupBo;
import com.boco.zg.plan.dao.ZgTorderPlanExDao;
import com.boco.zg.plan.dao.ZgTorderPlanGroupExDao;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;

@Component
public class ZgTorderPlanGroupExBo extends BaseManager<ZgTorderPlanGroup,java.lang.String>{
	
	private ZgTorderPlanGroupExDao zgTorderPlanGroupExDao;
	
	private ZgTgroupOrderPlanDao zgTgroupOrderPlanDao;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTorderPlanGroupBo zgTorderPlanGroupBo;
	
	public EntityDao getEntityDao() {
		return this.zgTorderPlanGroupExDao;
	}

	public void setZgTorderPlanGroupExDao(
			ZgTorderPlanGroupExDao zgTorderPlanGroupExDao) {
		this.zgTorderPlanGroupExDao = zgTorderPlanGroupExDao;
	}

	/**
	 * 改变订单领料状态
	 * @param id
	 */
	public boolean changeOrderState(String id,String state) {
		return zgTorderPlanGroupExDao.changeOrderState(id,state)==1;
	}

	/**
	 * 订单合并 
	 * 处理逻辑：(假如001,002,003合并)
	 * 	     1：删除002,003，并把002,003后面的订单的排序号前移两位
	 * 		 2 把002 003的计划领料单合并到001 
	 *       3 001的名称改为001,002,003三个单的订单号，以','号分隔
	 * @param groupIds 合并的领料计划组编号
	 * @param date 日期
	 * @param sorft 排序号
	 * @param arbpl 生产线
	 */
	public boolean mergeOrder(String groupIds,String date,String sorft,String arbpl,String plant){
		String[] groupId=groupIds.split(",");
		
	
		List<String> groupList=new ArrayList<String>();
		if(groupId.length==1){
			return true;
		}
		
		for(int i=1;i<groupId.length;i++){
			groupList.add(groupId[i]);
		}
		
		//获取分组里面的所有订单号,及计划领料单号
		List<Map> listOrderNOList=zgTgroupOrderPlanDao.getListOrderNOByGroupIds(groupList);
		
		
		
		//分组合并最终以第一个分组为基准,删除除第一个分组之外的其他分组
		for(int i=1;i<groupId.length;i++){
			ZgTorderPlanGroup zgTorderPlanGroup=(ZgTorderPlanGroup) zgTorderPlanGroupExDao.getById(groupId[i]);
			//排在该分组后的分组排序号统一向前移一位
//			zgTorderPlanGroupExDao.changeGroupPsbh(zgTorderPlanGroup.getIndexNo(),date,sorft,arbpl,-1,plant);
			
			//删除该分组
			zgTorderPlanGroupExDao.deleteById(groupId[i]);
		}
		
		
		
		String cuid=groupId[0];
		String orderNos="";
		
		//	 2 把002 003的计划领料单合并到001 
		//   3 001的名称改为001,002,003三个单的订单号，以','号分隔
		for(Map map:listOrderNOList){
			String orderPlanId=map.get("ORDER_PLAN_ID").toString();
			ZgTgroupOrderPlan zgTgroupOrderPlan=new ZgTgroupOrderPlan();
			zgTgroupOrderPlan.setGroupId(cuid);
			zgTgroupOrderPlan.setOrderPlanId(orderPlanId);
			zgTgroupOrderPlanDao.save(zgTgroupOrderPlan);
			
			ZgTorderPlan zgTorderPlan=zgTorderPlanBo.getById(orderPlanId);
			zgTorderPlan.setState(Constants.OrderPlanStatus.NEW.value());
			zgTorderPlanBo.update(zgTorderPlan);
			
			orderNos=orderNos+map.get("AUFNR")+",";
		}
		if(orderNos.length()>1){
			orderNos=orderNos.substring(0,orderNos.length()-1);
		}
		
		//更新状态及进度
		ZgTorderPlanGroup zgTorderPlanGroup=(ZgTorderPlanGroup) zgTorderPlanGroupExDao.getById(cuid);
		zgTorderPlanGroup.setCuid(cuid);
		zgTorderPlanGroup.setLabelCn(zgTorderPlanGroup.getLabelCn()+","+orderNos);
		String state=getState(cuid);
		zgTorderPlanGroup.setState(state);
		double percent=getPercent(cuid);
		if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
			if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
				percent=0.99;
			}
		}
		zgTorderPlanGroup.setPercent(percent);
		update(zgTorderPlanGroup);
		
		zgTorderPlanGroupExDao.updateZgtorderPlanStateByGroupId(cuid,Constants.OrderPlanStatus.NEW.value());
		
		return true;
		
	}

	public void setZgTgroupOrderPlanDao(ZgTgroupOrderPlanDao zgTgroupOrderPlanDao) {
		this.zgTgroupOrderPlanDao = zgTgroupOrderPlanDao;
	}

	/**
	 * 订单取消合并 
	 * 处理逻辑：(假如取消001合并)
	 * 	     1 查询001组的订单列表
	 * 	     2：循环订单列表　每个订单生成一个分组，排序号为原来订单的排序呺
	 *       3 重新计算分组的状态及领料进度
	 * @param groupIds 合并的领料计划组编号
	 * @param date 日期
	 * @param sorft 排序号
	 * @param arbpl 生产线
	 */
	public void cancleMergeOrder(String groupIds, String date, String sorft,String arbpl,String plant) {
		String[] groupId=groupIds.split(",");
		
		for(int i=0;i<groupId.length;i++){
			List<String> groupList=new ArrayList<String>();
			groupList.add(groupId[i]);
			
			//获取该分组里面的所有订单号,及计划领料单号
			List<Map> listOrderNOList=zgTgroupOrderPlanDao.getListOrderNOByGroupIds(groupList);
			
			if(listOrderNOList.size()==0||listOrderNOList.size()==1){//该分组只有一张订单的情况下，不用处理
				continue;
			}
			
			
			ZgTorderPlanGroup zgTorderPlanGroup=(ZgTorderPlanGroup) zgTorderPlanGroupExDao.getById(groupId[i]);
		//	zgTorderPlanGroupExDao.changeGroupPsbh(zgTorderPlanGroup.getIndexNo(),date,sorft,arbpl,listOrderNOList.size()-1,plant);
			
			
			//删除原来的分组信息
			zgTorderPlanGroupExDao.deleteById(groupId[i]);
			
			zgTorderPlanGroup.setLabelCn(listOrderNOList.get(0).get("AUFNR").toString());
			zgTorderPlanGroupExDao.updateGroupName(zgTorderPlanGroup);
			Long psbh=zgTorderPlanGroup.getIndexNo();
			for(int j=0;j<listOrderNOList.size();j++){
				Map map=listOrderNOList.get(j);
				String orderPlanId=map.get("ORDER_PLAN_ID").toString();
				String aufnr=map.get("AUFNR").toString();
				
				ZgTorderPlanGroup temPlanGroup=new ZgTorderPlanGroup();
				temPlanGroup.setPercent(0d);
				temPlanGroup.setPlanType(sorft);
				temPlanGroup.setState(Constants.OrderPlanStatus.NEW.value());
				temPlanGroup.setIndexNo(psbh);
				temPlanGroup.setLabelCn(aufnr);
				String cuid=zgTorderPlanGroupExDao.save(temPlanGroup).toString();
				
				
				//更新组关系
				ZgTgroupOrderPlan temPlan=new ZgTgroupOrderPlan();
				temPlan.setGroupId(cuid);
				temPlan.setOrderPlanId(orderPlanId);
				
				zgTgroupOrderPlanDao.save(temPlan);
				
				ZgTorderPlan zgTorderPlan=zgTorderPlanBo.getById(orderPlanId);
				zgTorderPlan.setState(Constants.OrderPlanStatus.NEW.value());
				zgTorderPlanBo.update(zgTorderPlan);
				
				//更新状态及进度
				temPlanGroup.setCuid(cuid);
				String state=getState(cuid);
				temPlanGroup.setState(state);
				double percent=getPercent(cuid);
				if(percent>=1){//领料为100%,但是领料状态没有完成，是因为有一些领的工艺规则没有配置完全,设置领料进度为99%
					if(!state.equals(Constants.OrderPlanStatus.FINISHED.value())&&!state.equals(Constants.OrderPlanStatus.PAUSE.value())&&!state.equals(Constants.OrderPlanStatus.PLAN.value())&&!state.equals(Constants.OrderPlanStatus.SUBMIT.value())){
						percent=0.99;
					}
				}
				temPlanGroup.setPercent(percent);
				update(temPlanGroup);
				
			}
		}
	}

	/**
	 * 获取领料员的领料计划列表
	 * @param operatorId 登录人id
	 * @param orderPlanType 预装 总装 预焊类型
	 * @param arbpl 生产线
	 * @param pxDate 排序日期
	 * @param plant 生产厂
	 * @return
	 */
	public List<ZgTorderPlanGroup> getPlanGroupList(String operatorId, String orderPlanType,String arbpl,Date pxDate,String plant,String matkls) {
		Map paramsMap=new HashMap();
		paramsMap.put("operatorId", operatorId);
		paramsMap.put("planType", orderPlanType);
		//if(orderPlanType.equals(Constants.OrderPlanType.CHANGE.value())||orderPlanType.equals(Constants.OrderPlanType.ADD.value())){//被 料，换料不需要生产厂条件来限制
		//	paramsMap.put("notPlant", 1);
		//}
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("pxDate",  TimeFormatHelper.getFormatDate(pxDate, TimeFormatHelper.DATE_FORMAT));
		paramsMap.put("plant", plant);
		paramsMap.put("carMatkls", matkls);
		if(orderPlanType.contains("BACK")){
			paramsMap.put("planType", "BACK");
			return zgTorderPlanGroupExDao.getPlanGroupList1(paramsMap);
		}else {
			return zgTorderPlanGroupExDao.getPlanGroupList(paramsMap);
		}
		
	}

	/**
	 * 根据装车计划表编号获取其涉及的领料计划组列表
	 * @param carPlanId
	 */
	public List<ZgTorderPlanGroup> getPlanGroupListByCarPlanId(String carPlanId) {
		Map paramsMap=new HashMap();
		paramsMap.put("carPlanId", carPlanId);
		return zgTorderPlanGroupExDao.getPlanGroupListByCarPlanId(paramsMap);
	}

		/**
	 * 计算领料计划单的状态
	 * @param cuid
	 * @return
	 */
	public String getState(String groupId) {
		ZgTorderPlanGroup group=zgTorderPlanGroupBo.getById(groupId);
		if(Constants.OrderPlanStatus.PLAN.value().equals(group.getState())){
			return group.getState();
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select planbom.* ");
		sql.append(" from zg_t_order_plan plan, zg_t_order_planbom planbom,zg_t_group_order_plan gop");
		sql.append(" where plan.cuid = planbom.order_plan_id");
		sql.append(" and plan.cuid=gop.order_plan_id ");
		sql.append(" and nvl(planbom.complete_num,0)<planbom.car_num");
		sql.append(" and planbom.car_num>'0' ");
		sql.append(" and gop.group_id = '"+groupId+"'");
		List<Map> list=((ZgTorderPlanGroupExDao)this.getEntityDao()).findDynQuery(sql.toString());
		
		if(list.size()==0){//领料已经完成，检查是否有退料
//			sql=new StringBuffer();
//			sql.append("select planbom.* ");
//			sql.append(" from zg_t_order_plan plan, zg_t_order_planbom planbom,zg_t_group_order_plan gop");
//			sql.append(" where plan.cuid = planbom.order_plan_id");
//			sql.append(" and plan.cuid=gop.order_plan_id ");
//			sql.append(" and gop.group_id = '"+groupId+"'");
//			sql.append(" and planbom.wait_back_num>0 ");
//			List<Map> backList=((ZgTorderPlanGroupExDao)this.getEntityDao()).findDynQuery(sql.toString());
//			if(backList.size()>0){
//				return Constants.OrderPlanStatus.SUBMIT.value();
//			}else {
			return Constants.OrderPlanStatus.FINISHED.value();
//			}
			
		}
		
		
		return Integer.parseInt(group.getState())>=0?Constants.OrderPlanStatus.SAVE.value():group.getState() ;
	}
	
	public String getStateBack(String groupId) {
		ZgTorderPlanGroup group=zgTorderPlanGroupBo.getById(groupId);
		if(Constants.OrderPlanStatus.PLAN.value().equals(group.getState())){
			return group.getState();
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select planbom.* ");
		sql.append(" from zg_t_order_plan plan, zg_t_order_planbom planbom,zg_t_group_order_plan gop");
		sql.append(" where plan.cuid = planbom.order_plan_id");
		sql.append(" and plan.cuid=gop.order_plan_id ");
		sql.append(" and nvl(planbom.wait_back_num,0)>0");
		sql.append(" and gop.group_id = '"+groupId+"'");
		List<Map> list=((ZgTorderPlanGroupExDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()==0){
			return Constants.OrderPlanStatus.FINISHED.value();
		}else {
			sql=new StringBuffer();
			sql.append("select planbom.* ");
			sql.append(" from zg_t_order_plan plan, zg_t_order_planbom planbom,zg_t_group_order_plan gop");
			sql.append(" where plan.cuid = planbom.order_plan_id");
			sql.append(" and plan.cuid=gop.order_plan_id ");
			sql.append(" and nvl(planbom.back_num,0)>0");
			sql.append(" and gop.group_id = '"+groupId+"'");
			list=((ZgTorderPlanGroupExDao)this.getEntityDao()).findDynQuery(sql.toString());
			return list.size()>0?Constants.OrderPlanStatus.SAVE.value():group.getState();
		}
//		return list.size()==0?Constants.OrderPlanStatus.FINISHED.value():(group.getState().equals(Constants.OrderPlanStatus.FINISHED.value())?Constants.OrderPlanStatus.SAVE.value():group.getState()) ;
	}

	/**
	 * 计算领料进度
	 * @param cuid
	 * @return
	 */
	public double getPercent(String orderPlanId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select  decode(sum(mengeCarNum),0,1,nvl(trunc((sum(completeCarnum)) / sum(mengeCarNum), 4),0)) percent  ");
		sql.append("  from (select planbom.car_num / bom.carnum mengeCarNum,");
		sql.append("               nvl(planbom.complete_num, 0) / bom.carnum completeCarnum");
		sql.append("          from zg_t_order_plan    plan,");
		sql.append("               zg_t_order_planbom planbom,  zg_t_order_taskbom taskbom,");
		sql.append("               zg_t_orderbom      orderbom,");
		sql.append("              zg_t_bom           bom , zg_t_group_order_plan gop");
		sql.append("         where plan.cuid = planbom.order_plan_id");
		sql.append("           and planbom.taskbom_id=taskbom.cuid and taskbom.order_bom_id = orderbom.cuid");
		sql.append("           and orderbom.idnrk = bom.idnrk and planbom.car_num>0 ");
		sql.append("           and bom.car_id is not null");
		sql.append("          and bom.carnum is not null  and gop.order_plan_id=plan.cuid");
		sql.append("          and gop.group_id = '"+orderPlanId+"')");
		List<Map> list=((ZgTorderPlanGroupExDao)this.getEntityDao()).findDynQuery(sql.toString());
		double percent=1.0;
		if(list.size()>0){
			String perStr=list.get(0).get("PERCENT")==null?"0":list.get(0).get("PERCENT").toString();
			percent=Double.parseDouble(perStr);
		}
		return percent ;
	}

	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}

	/**
	 * 根据id改变订单分组的内部排序号
	 * @param groupId
	 * @param indexNo
	 */
	public void changeIndexNoByGroupId(String groupId, int indexNo) {
		ZgTorderPlanGroup ouPlanGroup=new ZgTorderPlanGroup();
		ouPlanGroup.setCuid(groupId);
		ouPlanGroup.setIndexNo((long)indexNo);
		zgTorderPlanGroupExDao.changeIndexNoByGroupId(ouPlanGroup);
	}

	/**
	 * 根据订单编号查找其订单计划分组启示录
	 * @param orderId
	 */
	public  List<ZgTorderPlanGroup>  getPlanGroupListByOrderTaskId(String orderTaskId) {
		Map paramsMap=new HashMap();
		paramsMap.put("orderTaskId", orderTaskId);
		return zgTorderPlanGroupExDao.getPlanGroupListByOrderTaskId(paramsMap);
	}


	public ZgTorderPlanGroupBo getZgTorderPlanGroupBo() {
		return zgTorderPlanGroupBo;
	}

	public void setZgTorderPlanGroupBo(ZgTorderPlanGroupBo zgTorderPlanGroupBo) {
		this.zgTorderPlanGroupBo = zgTorderPlanGroupBo;
	}

	/**
	 * @param planId
	 * @param state
	 */
	public void updateGroupStateByOrderPlanId(String planId, String state) {
		zgTorderPlanGroupExDao.updateGroupStateByOrderPlanId(planId,state);
		
	}

	/**
	 * @param id
	 * @return
	 */
	public List<ZgTorderPlanGroup> getPlanGroupListByOrderId(String orderId) {
		Map paramsMap=new HashMap();
		paramsMap.put("orderId", orderId);
		return zgTorderPlanGroupExDao.getPlanGroupListByOrderId(paramsMap);
	}
}
