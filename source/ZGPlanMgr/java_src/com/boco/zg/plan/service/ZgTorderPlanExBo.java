package com.boco.zg.plan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;
import javacommon.util.StringHelper;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.dao.ZgTorderPlanExDao;
import com.boco.zg.plan.model.ZgTorderPlanEx;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.util.Constants;
import com.boco.zg.util.Constants.OrderPlanStatus;

@Component
public class ZgTorderPlanExBo extends BaseManager<ZgTorderPlanEx,java.lang.String>{
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTcarplanBo zgTcarplanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTorderPlanExDao zgTorderPlanExDao;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	
	
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanExDao(ZgTorderPlanExDao dao) {
		this.zgTorderPlanExDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanExDao;
	}
	
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderPlanExDao.findByPageRequest(pr);
	}
	
	public List<ZgTorderPlanEx> findByRequest(PageRequest pr) {
		return zgTorderPlanExDao.findByRequest(pr);
	}

	public void updateOrderPlanState(String orderPlanId,String state) {
		zgTorderPlanExDao.updateOrderPlanState(orderPlanId, state);
	}
	
	public void submitOrderPlan(String orderPlanId){
		/*List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomList(orderPlanId);
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
		}*/
		updateOrderPlanState(orderPlanId,"8");
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
		return zgTorderPlanExDao.findBatchPlanByPageRequest(pr);
	}
	
	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 */
	public boolean saveOrderPlanForBatch(ZgTorderPlan zgTorderPlan) {
		return zgTorderPlanExDao.saveOrderPlanForBatch(zgTorderPlan);
	}
	
	/**
	 * 提交批量领料计划
	 * 处理逻辑：将该订单的bom组件按按仓库分组，每一个仓库的bom组件生成一张装车计划单
	 *         更新领料计划为提交状态
	 * @param orderPlanId 领料计划单编号
	 * @param operatorInfo 创建人
	 */
	public void submitOrderPlanForBatch(ZgTorderPlan zgTorderPlan,	OperatorInfo operatorInfo) {
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByPlanId(zgTorderPlan.getCuid());
		String lgortId="";
		String carPlanId="";
		for(ZgTorderPlanbomEx obj : list) {
			if("".equals(lgortId)){//第一次遍历 保存装车计划单
				lgortId=obj.getLgort();
				ZgTcarplan zgTcarplan = new ZgTcarplan(); 
				zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
				zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
				zgTcarplan.setCarUser(zgTorderPlan.getUserId());
				zgTcarplan.setStorageId(obj.getLgort());
				zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
				zgTcarplan.setIsManul(Constants.isNotManulFinished);
				carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
			}else {
				if(!lgortId.equals(obj.getLgort())){
					lgortId=obj.getLgort();
					ZgTcarplan zgTcarplan = new ZgTcarplan(); 
					zgTcarplan.setCreateUserId(operatorInfo.getOperatorId());
					zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
					zgTcarplan.setCarUser(zgTorderPlan.getUserId());
					zgTcarplan.setStorageId(obj.getLgort());
					zgTcarplan.setCarDate(zgTorderPlan.getPlanDate());
					carPlanId=zgTcarplanBo.saveCarplan(zgTcarplan);
				}
			}
			
		
			ZgTcarbom bom = new ZgTcarbom();
			bom.setCarPlanId(carPlanId);
			bom.setOrderPlanbomId(zgTorderPlan.getCuid());
			bom.setPlanNum(obj.getCarNum());
			bom.setRealNum(0l);
			zgTcarbomBo.save(bom);
		}
	}
	
	/**
	 * 保存批量领料计划
	 * @param zgTorderPlan
	 */
	public void saveOrderPlan(ZgTorderPlan zgTorderPlan) {
		zgTorderPlanExDao.updateOrderPlanForBatch(zgTorderPlan);
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划
	 * @param orderPlanId
	 */
	public void deleteCarPlanByOrderPlanId(String orderPlanId) {
		zgTorderPlanExDao.deleteCarPlanByOrderPlanId(orderPlanId);
	}
	
	/**
	 * 根据领料计划id删除其相应的装车计划BOM
	 * @param orderPlanId
	 */
	public void deleteCarBomByOrderPlanId(String orderPlanId) {
		zgTorderPlanExDao.deleteCarBomByOrderPlanId(orderPlanId);
	}
	
	/**
	 * 设置计划的领料组，领料人
	 * 	   默认的为第一条计划的领料组和领料人
	 * @param orderPlanId
	 */
	public void updateOrderDeptId(String orderPlanId) {
		zgTorderPlanExDao.updateOrderDeptId(orderPlanId);
	}
	
	/**
	 * 领料计划生成仓库领料计划
	 *      领料计划表中的bom按领料人，领料部门，仓库分组，分别插入生成为装车计划单
	 *      把领料计划表中的bom按照相应的确领料人，领料部门，仓库插入装车计划bom表
	 *      更新zg_t_orderbom表相应的bom的状态为已完成
	 * @param items
	 * @param operatorId
	 * @param planType 领料领料计划类型
	 */
	public int generateStoragePlan(String[] carPlanIds, String operatorId,String planType) {
		//遍历要生成仓库领料的计划单
		int num=0;
		for(int i = 0; i < carPlanIds.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(carPlanIds[i]);
			String orderPlanId = (java.lang.String)params.get("id");
			
			List<Map> list=zgTorderPlanExDao.getDeptIdUserIdLgortListByOrderPlanId(orderPlanId);
			
			for (Map obj:list) {
				String storageId=IbatisDAOHelper.getStringValue(obj, "LGORT");
				String deptId=IbatisDAOHelper.getStringValue(obj, "DEPARTMENT_ID");
				String userId=IbatisDAOHelper.getStringValue(obj, "USER_ID");
				
				//判断在该装车单的相应的仓库，领料组，领料人下面有没有待装车的bom组件
				if(!zgTorderPlanExDao.hasCarBomByorderPlanId(orderPlanId,storageId,deptId,userId)){
					continue;
				}
				
				//生成装车单
				ZgTcarplan zgTcarplan = new ZgTcarplan(); 
				zgTcarplan.setCreateUserId(operatorId);
				zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
				zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
				zgTcarplan.setOrderPlanType(planType);
				zgTcarplan.setStorageId(storageId);
				zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
				zgTcarplan.setDepartmentId(deptId);
				zgTcarplan.setCarUser(userId);
				String carPlanId=zgTorderPlanExDao.getSysCuid()+num;
				zgTcarplan.setCuid(carPlanId);
				zgTcarplanBo.saveCarplan2(zgTcarplan);
				//生成装车计划bom
				int row=zgTorderPlanExDao.generateCarBomByOrderPlanId(carPlanId,orderPlanId,storageId,deptId,userId);
				
				//更改zg_t_order_planBom的计划领数量和状态（状态为已完成）
				row=zgTorderPlanExDao.updateZgtOrderPlanBomByOrderPlanId(orderPlanId,storageId,deptId,userId,Constants.CarPlanStatus.DONE.value());
				num++;
			}
			
		//	updateOrderPlanState(orderPlanId,Constants.OrderPlanStatus.SUBMIT.value());
		}
		return num;
	}
	
	/**
	 * /更新orderbom状态
	 * @param orderPlanId
	 * @param state
	 */
	public int updateOrderPlanBomState(String orderPlanId, String state) {
		return zgTorderPlanExDao. updateOrderPlanBomState(orderPlanId,state) ;
	}
	
	/**
	 * 获取当天订单总数
	 * @param type 总装、预装、预焊类型
	 * @param arbpl 生产线
	 * @param operatorId
	 * @param pxDate 排序日期
	 * @partm plant 生产厂
	 * @return
	 */
	public int getCurDateTotalOrderCount(String type,String arbpl,String operatorId,Date pxDate,String plant,String viewDate) {
		Map paramsMap=new HashMap();
		paramsMap.put("type", type);
		paramsMap.put("operatorId", operatorId);
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("pxDate", pxDate);
		paramsMap.put("plant", plant);
		if(!StringHelper.isEmpty(viewDate)){//暂时添加的报表查看
			return zgTorderPlanExDao.getCurDateTotalOrderCount1(paramsMap);
		}else {
			return zgTorderPlanExDao.getCurDateTotalOrderCount(paramsMap);
		}
		
	}
	
	/**
	 * 获取当天完成的订单数量
	 * @param type 总装、预装、预焊类型
	 * @param arbpl 生产线
	 * @param operatorId
	 * @param pxDate 排序日期
	 * @partm plant 生产厂
	 * @return
	 */
	public int getCurDateFinishedOrderCount(String type,String arbpl,String operatorId,Date pxDate,String plant) {
		Map paramsMap=new HashMap();
		paramsMap.put("type", type);
		paramsMap.put("operatorId", operatorId);
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("pxDate", pxDate);
		paramsMap.put("plant", plant);
		return zgTorderPlanExDao.getCurDateFinishedOrderCount(paramsMap);
	}
	
	
	/**
	 * 获取订单列表
	 * @param planType
	 * @param pcDate
	 * @return
	 */
	public List<ZgTorderPlanEx> findZgTorderPlanList(String planType,String pcDate,String operatorId,String arbpl,String plant,String isCurDay,PageRequest<Map> pageRequest) {
		//临时添加的查看之前领料的功能
		String viewDate=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "viewDate","");
		String psbhChange=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "psbhChange","");
		
		if("0".equals(isCurDay)&&StringHelper.isEmpty(viewDate)){
			return zgTorderPlanExDao.findZgTorderPlanList(planType,pcDate,operatorId,arbpl,plant,psbhChange,pageRequest.getFilters());
		}else {
			return zgTorderPlanExDao.findZgTorderPlanList1(planType,pcDate,operatorId,arbpl,plant,psbhChange,pageRequest.getFilters());
		}
		
	}
	
	/**
	 * 根据装车计划表编号获取其涉及的领料计划单列表
	 * @param carPlanId
	 */
	public List<ZgTorderPlan> getOrderPlanListByCarPlanId(String carPlanId) {
		return zgTorderPlanExDao.getOrderPlanListByCarPlanId(carPlanId);
	}
	
	/**
	 * 计算领料计划单的状态
	 * @param cuid
	 * @return
	 */
	public String getState(String orderPlanId) {
		ZgTorderPlan plan=zgTorderPlanBo.getById(orderPlanId);
		if(Constants.OrderPlanStatus.PLAN.value().equals(plan.getState())){
			return plan.getState();
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select planbom.* ");
		sql.append("from zg_t_order_plan plan, zg_t_order_planbom planbom ");
		sql.append(" where plan.cuid = planbom.order_plan_id");
		sql.append(" and nvl(planbom.complete_num,0)<planbom.car_num");
		sql.append(" and planbom.car_num>0 ");
		sql.append(" and plan.cuid = '"+orderPlanId+"'");
		List<Map> list=((ZgTorderPlanExDao)this.getEntityDao()).findDynQuery(sql.toString());
		
		if(list.size()==0){//领料已经完成，检查是否有退料
			if(plan.getPlanType().equals(Constants.OrderPlanType.CHANGE.value())){
				sql=new StringBuffer();
				sql.append("select planbom.* ");
				sql.append("from zg_t_order_plan plan, zg_t_order_planbom planbom ");
				sql.append(" where plan.cuid = planbom.order_plan_id");
				sql.append(" and plan.cuid = '"+orderPlanId+"'");
				sql.append(" and planbom.wait_back_num>0 ");
				List<Map> backList=((ZgTorderPlanExDao)this.getEntityDao()).findDynQuery(sql.toString());
				if(backList.size()>0){
					return Constants.OrderPlanStatus.SUBMIT.value();
				}else {
					return Constants.OrderPlanStatus.FINISHED.value();
				}
			}else {
				return Constants.OrderPlanStatus.FINISHED.value();
			}
			
			
		}
		
		return (Integer.parseInt(plan.getState()))>=0?Constants.OrderPlanStatus.SAVE.value():plan.getState() ;
	}
	
	public String getStateBack(String orderPlanId) {
		ZgTorderPlan plan=zgTorderPlanBo.getById(orderPlanId);
		if(Constants.OrderPlanStatus.PLAN.value().equals(plan.getState())){
			return plan.getState();
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select planbom.* ");
		sql.append("from zg_t_order_plan plan, zg_t_order_planbom planbom ");
		sql.append(" where plan.cuid = planbom.order_plan_id");
		sql.append(" and nvl(planbom.wait_back_num,0)>0");
		sql.append(" and plan.cuid = '"+orderPlanId+"'");
		List<Map> list=((ZgTorderPlanExDao)this.getEntityDao()).findDynQuery(sql.toString());
		return list.size()==0?Constants.OrderPlanStatus.FINISHED.value():(plan.getState().equals(Constants.OrderPlanStatus.FINISHED.value())?Constants.OrderPlanStatus.SAVE.value():plan.getState()) ;
	}
	
	public static void main(String[] args) {
		StringBuffer sql=new StringBuffer();
		sql.append("select decode(sum(mengeCarNum), 0, 1,  nvl(trunc((sum(completeCarnum)) / sum(mengeCarNum), 4), 0)) percent ");
		sql.append("  from (select planbom.car_num / bom.carnum mengeCarNum,");
		sql.append("               nvl(planbom.complete_num, 0) / bom.carnum completeCarnum");
		sql.append("          from zg_t_order_plan    plan,");
		sql.append("               zg_t_order_planbom planbom,");
		sql.append("               zg_t_orderbom      orderbom,");
		sql.append("               zg_t_bom           bom,");
		sql.append("               zg_t_order_taskbom taskbom");
		sql.append("         where plan.cuid = planbom.order_plan_id");
		sql.append("           and planbom.taskbom_id =taskbom.cuid");
		sql.append("           and taskbom.order_bom_id=orderbom.cuid");
		sql.append("           and orderbom.idnrk = bom.idnrk");
		sql.append("           and bom.car_id is not null");
		sql.append("           and bom.carnum is not null");
		sql.append("           and plan.cuid = '123')");
		System.out.println(sql.toString());
	}
	
	/**
	 * 计算领料进度
	 * @param cuid
	 * @return
	 */
	public double getPercent(String orderPlanId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select decode(sum(mengeCarNum), 0, 1,  nvl(trunc((sum(completeCarnum)) / sum(mengeCarNum), 4), 0)) percent ");
		sql.append("  from (select planbom.car_num / bom.carnum mengeCarNum,");
		sql.append("               nvl(planbom.complete_num, 0) / bom.carnum completeCarnum");
		sql.append("          from zg_t_order_plan    plan,");
		sql.append("               zg_t_order_planbom planbom,");
		sql.append("               zg_t_orderbom      orderbom,");
		sql.append("               zg_t_bom           bom,");
		sql.append("               zg_t_order_taskbom taskbom");
		sql.append("         where plan.cuid = planbom.order_plan_id");
		sql.append("           and planbom.taskbom_id =taskbom.cuid");
		sql.append("           and taskbom.order_bom_id=orderbom.cuid");
		sql.append("           and orderbom.idnrk = bom.idnrk");
		sql.append("           and bom.car_id is not null");
		sql.append("           and bom.carnum is not null");
		sql.append("          and plan.cuid = '"+orderPlanId+"')");
		List<Map> list=((ZgTorderPlanExDao)this.getEntityDao()).findDynQuery(sql.toString());
		double percent=1.0;
		if(list.size()>0){
			percent=Double.parseDouble(list.get(0).get("PERCENT").toString());
		}
		return percent ;
	}
	
	public void updateStatePercent(ZgTorderPlan plan) {
		StringBuffer sql=new StringBuffer();
		sql.append("update zg_t_order_plan plan set plan.percent="+plan.getPercent()+" ,plan.state='"+plan.getState()+"'  where plan.cuid='"+plan.getCuid()+"'");
		((ZgTorderPlanExDao)this.getEntityDao()).executeSql(sql.toString());
	}
	
	public void changeOrderStateByGroupId(String id, String state) {
		StringBuffer sql=new StringBuffer();
		sql.append("update zg_t_order_plan t                                             ");
		sql.append("   set t.state =  '"+state+"'                                        ");
		sql.append(" where exists (select 1                                              ");
		sql.append("          from zg_t_order_plan_group oup, zg_t_group_order_plan gop  ");
		sql.append("         where oup.cuid = gop.group_id                               ");
		sql.append("           and gop.order_plan_id = t.cuid                            ");
		sql.append("           and oup.cuid = '"+id+"')"                                        );
		((ZgTorderPlanExDao)this.getEntityDao()).executeSql(sql.toString());
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Map> getOrderListByGroupId(String groupId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct t.cuid cuid from zg_t_order t,zg_t_order_plan plan,zg_t_group_order_plan gop,zg_t_order_task task  ");
		sql.append(" where  t.cuid = task.order_id and task.cuid=plan.order_task_id and plan.cuid=gop.order_plan_id ");
	    sql.append("and gop.group_id='"+groupId+"'");
		return ((ZgTorderPlanExDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	/**
	 *  根据订单编号查找其领料计划
	 * @param orderId
	 */
	public List<ZgTorderPlan> getOrderPlanListByOrderTaskId(String orderTaskId) {
		return zgTorderPlanExDao.getOrderPlanListByOrderTaskId(orderTaskId);
	}
	
	/**
	 *  根据订单编号查找其领料计划
	 * @param orderId
	 */
	public List<ZgTorderPlan> getOrderPlanListByOrderId(String orderId) {
		return zgTorderPlanExDao.getOrderPlanListByOrderId(orderId);
	}
	
	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}
	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}
	
	/**
	 * 修改计划备注
	 * @param groupId
	 * @param zbz
	 */
	public void updateZbzByGroupId(String groupId, String zbz) {
		StringBuffer sql=new StringBuffer("update zg_t_order_plan t set t.zbz='"+zbz+"' where t.cuid in (select gop.order_plan_id from zg_t_group_order_plan gop where gop.group_id='"+groupId+"')");
		((ZgTorderPlanExDao)this.getEntityDao()).executeSql(sql.toString());
	}
	
	
}
