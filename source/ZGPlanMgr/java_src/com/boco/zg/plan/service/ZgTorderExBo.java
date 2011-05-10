package com.boco.zg.plan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.util.StringHelper;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTorderDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanDao;
import com.boco.zg.plan.base.dao.ZgTorderPlanbomDao;
import com.boco.zg.plan.base.dao.ZgTorderbomDao;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTgroupOrderPlan;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTgroupOrderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanGroupBo;
import com.boco.zg.plan.dao.ZgTorderExDao;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.util.Constants;
import com.boco.zg.util.Constants.OrderStatus;
import com.sap.tc.logging.interfaces.IBaseLog;

@Component
public class ZgTorderExBo extends ZgTorderBo {
	
	private static String PLAN_TYPE_ABC = "2"; //预焊
	
	private static String PLAN_TYPE_ABD = "1"; //预装
	
	private static String PLAN_TYPE_ABE = "4"; //总装
	
	public static String PLAN_STATE_CREATE = "0";
	
	public static String PLAN_STATE_SAVE = "4";
	
	public static String PLAN_STATE_SUBMIT = "8";
	
	private ZgTorderbomDao zgTorderbomDao;
	
	private ZgTorderExDao zgTorderExDao;
	
	private ZgTorderPlanGroupBo zgTorderPlanGroupBo;
	
	private ZgTgroupOrderPlanBo zgTgroupOrderPlanBo;
	
	private static Map<String, List<Map>> sorftPlantList;
	
	private ZgTorderPlanBo zgTorderPlanBo;
	
	private ZgTorderPlanExBo zgTorderPlanExBo;
	
	private ZgTorderPlanGroupExBo zgTorderPlanGroupExBo;
	
	private ZgTorderPlanbomExBo zgTorderPlanbomExBo;
	
	private ZgTcarplanBo zgTcarplanBo;
	
	private ZgTcarbomBo zgTcarbomBo;
	
	
	public void setZgTorderPlanExBo(ZgTorderPlanExBo zgTorderPlanExBo) {
		this.zgTorderPlanExBo = zgTorderPlanExBo;
	}



	/**
	 * 初始化
	 */
	public void initSorftPlantList(){
		if(sorftPlantList==null){
			sorftPlantList=new HashMap<String, List<Map>>();
			String sql="select distinct t.type from t_plant_enumevalue t where t.state='0' and t.needplan='0'";
			List<Map> list=zgTorderExDao.findDynQuery(sql);
			for(Map obj:list){
				String sortf=obj.get("TYPE").toString();
				sql="select t.* from t_plant_enumevalue t where t.type='"+sortf+"' and t.state='0' and t.needplan='0'";
				List<Map> tempList=zgTorderExDao.findDynQuery(sql);

				sorftPlantList.put(sortf, tempList);
			}
		}
	}
	
	
	
	public void setZgTgroupOrderPlanBo(ZgTgroupOrderPlanBo zgTgroupOrderPlanBo) {
		this.zgTgroupOrderPlanBo = zgTgroupOrderPlanBo;
	}

	public void setZgTorderbomDao(ZgTorderbomDao zgTorderbomDao) {
		this.zgTorderbomDao = zgTorderbomDao;
	}
	
	private ZgTorderPlanDao zgTorderPlanDao;
	
	public void setZgTorderPlanDao(ZgTorderPlanDao zgTorderPlanDao) {
		this.zgTorderPlanDao = zgTorderPlanDao;
	}
	
	private ZgTorderPlanbomDao zgTorderPlanbomDao;

	public void setZgTorderPlanbomDao(ZgTorderPlanbomDao zgTorderPlanbomDao) {
		this.zgTorderPlanbomDao = zgTorderPlanbomDao;
	}

	
	/**
	 * 获取生成的该领料计划的生产厂
	 * @param sortf
	 * @param plant
	 * @return
	 */
	private String getPlant(String sortf,List<String> plantList){
		if(plantList.size()==0) return "";
		
		if(sorftPlantList==null){
			initSorftPlantList();
		}
		
		Set set = sorftPlantList.keySet();
		
		List<String> sortfList=new ArrayList<String>();

		for (Object key : set) {
			if(sortf.equals(key.toString())){
				List<Map> list=sorftPlantList.get(key);
				for(Map obj:list){
					String cPlant=obj.get("PLANTID").toString();
					for(String plant:plantList)
					if(plant.equals(cPlant)){
						return cPlant;
					}
				}
			}
			
		}
		return "";
	}
	
	public List<Map> getPlantId(String sortf){
		return zgTorderExDao.findDynQuery("select * from t_plant_enumevalue t where t.type='"+sortf+"' and t.state='0'");
	}

	public void submitOrder(String orderId, OperatorInfo operatorInfo) {
		//先删除之前的领料计划
		((ZgTorderDao)this.getEntityDao()).executeSql("delete  from zg_t_order_plan_group oup where exists (select 1  from zg_t_group_order_plan gop, zg_t_order_plan plan "
												         +" where plan.cuid = gop.order_plan_id"
												         +"  and gop.group_id = oup.cuid"
												         +"  and plan.order_id='"+orderId+"')");
		((ZgTorderDao)this.getEntityDao()).executeSql("delete from zg_t_order_planbom t where t.order_id='"+orderId+"'");
		((ZgTorderDao)this.getEntityDao()).executeSql("delete from zg_t_order_plan t where t.order_id='"+orderId+"'");
		
		//查看该订单的工厂，C01,C04,:ABE  C02,C05:ABD   C03,C06:ABC
		List<String> softList=getOrderSoftByOrderId(orderId);
		
		createPlan(orderId,softList,operatorInfo,"0","",Constants.isNotManulFinished);
		updateOrderState(orderId,PLAN_STATE_SUBMIT);
	}
	
	/**
	 * 获取应该生成的领料计划类型
	 * @return
	 */
	public List<String> getOrderSoftByOrderId(String orderId){
		  List<String> planList=zgTorderExDao.getPlantListByOrderId(orderId);
		
		if(planList.size()==0){
			return new ArrayList<String>();
		}
		
		if(sorftPlantList==null){
			initSorftPlantList();
		}
		
		//遍历键，通过键取值

		Set set = sorftPlantList.keySet();
		
		List<String> sortfList=new ArrayList<String>();
		for (Object key : set) {
			List<Map> list=sorftPlantList.get(key);
			for(Map obj:list){
				String cPlant=obj.get("PLANTID").toString();
				for(String plant:planList)
				if(plant.equals(cPlant)){
					sortfList.add(key.toString());
				}
			}
		}
		
		return sortfList;
	}
	
	/**
	 * 获取应该生成的领料计划类型
	 * @return
	 */
	public List<String> getSoftByOrderId(String orderId){
		List<String> sortfList=new ArrayList<String>();
		String sql="select distinct t.type from t_plant_enumevalue t where t.state='0' and t.needplan='0'"
					+" and t.type not in (select temp.plan_type from zg_t_order_plan temp where temp.order_id='"+orderId+"')";
		List<Map> list=zgTorderExDao.findDynQuery(sql);
		for (Map obj:list) {
			sortfList.add(IbatisDAOHelper.getStringValue(obj, "TYPE"));
		}
		
		return sortfList;
	}
	
	
	
	public void updateOrderState(String orderId,String orderState) {
		ZgTorder order = (ZgTorder) this.getEntityDao().getById(orderId);
		order.setOrderState(orderState);
		this.getEntityDao().update(order);
	}
	
	/**
	 * 生成领料计划
	 * @param orderId　订单单cuid
	 * @param types  生成类型
	 * @param operatorInfo
	 * @param state　订半状态
	 * @param isManul 是否手工结单
	 * @param 工厂
	 */
	public void createPlan(String orderId,List<String> sortrList, OperatorInfo operatorInfo,String state,String plant,String isManul) {
		ZgTorderbom param = new ZgTorderbom();
		if(sortrList.size()==0){
			return;
		}else {
			param.setOrderId(orderId);
			String sortfs = "";
			for(String sortf : sortrList) {
				sortfs+= "'"+sortf+"',";
			}
			sortfs = sortfs.substring(0,sortfs.length()-1);
			param.setSqlQueryString("t0_SORTF in ("+sortfs+")");
		}
		Map<String,ZgTorderPlan> planMap = new HashMap<String,ZgTorderPlan>();
		List<ZgTorderbom> list = zgTorderbomDao.findByProperty(param);
		
		ZgTorder zgTorder=getById(orderId);
	     List<String> planList=zgTorderExDao.getPlantListByOrderId(orderId);
		
		Long count=0l;
		for(ZgTorderbom bom:list) {
			ZgTorderPlan plan = planMap.get(bom.getAufnr()+"_"+bom.getSortf());
			if(plan == null) {
				count++;
				plan = new ZgTorderPlan();
				plan.setCuid(count+"");
				plan.setOrderId(orderId);
				plan.setPlanType(bom.getSortf());
				if(StringHelper.isEmpty(plant)){
					plan.setPlant(getPlant(bom.getSortf(), planList));
				}else {
					plan.setPlant(plant);
				}
				plan.setIsManul(isManul);//手工结单
				plan.setState(state);
				String orderPlanId=(String) zgTorderPlanDao.save(plan);
				planMap.put(bom.getAufnr()+"_"+bom.getSortf(), plan);
				
				//插入计划分组表
				ZgTorderPlanGroup group=new ZgTorderPlanGroup();
				group.setLabelCn(zgTorder.getAufnr());
				group.setState(state);
				group.setPercent(0d);
				group.setPlanType(bom.getSortf());
				group.setPsbh(zgTorder.getPsbh());
				group.setIndexNo(10000l);
				String groupId=(String) zgTorderPlanGroupBo.save(group);
				
				ZgTgroupOrderPlan gop=new ZgTgroupOrderPlan();
				gop.setOrderPlanId(orderPlanId);
				gop.setGroupId(groupId);
				zgTgroupOrderPlanBo.save(gop);
		
			}
			ZgTorderPlanbom planbom = new ZgTorderPlanbom();
			planbom.setCuid(plan.getCuid());
			planbom.setOrderPlanId(plan.getCuid());
			planbom.setOrderId(orderId);
			planbom.setOrderBomId(bom.getCuid());
			planbom.setState(PLAN_STATE_CREATE);
			planbom.setCarNum(bom.getMenge());
			planbom.setCompleteNum(0l);
			planbom.setPlanNum(0l);
			//planbom.setUserId(operatorInfo.getUserId());
			zgTorderPlanbomDao.save(planbom);
		}
	}

	/**
	 * 根据领料计划组编号查询他的订单信息
	 * @param groupId
	 * @param orderPlanType
	 */
	public List<ZgTorder> getOrderInfoListByGroupId(String groupId) {
		return zgTorderExDao.getOrderInfoListByGroupId(groupId);
		
	}

	public ZgTorderExDao getZgTorderExDao() {
		return zgTorderExDao;
	}

	public void setZgTorderExDao(ZgTorderExDao zgTorderExDao) {
		this.zgTorderExDao = zgTorderExDao;
	}

	public List<ZgTorder> getOrderInfoListByorderPlanId(String orderPlanId) {
		return zgTorderExDao.getOrderInfoListByorderPlanId(orderPlanId);
	}

	public ZgTorderPlanGroupBo getZgTorderPlanGroupBo() {
		return zgTorderPlanGroupBo;
	}

	public void setZgTorderPlanGroupBo(ZgTorderPlanGroupBo zgTorderPlanGroupBo) {
		this.zgTorderPlanGroupBo = zgTorderPlanGroupBo;
	}

	/**
	 * 获取应该生成的领料计划类型
	 * @param plant
	 * @return
	 */
	public List<String> getOrderSoftByPlant(String plant) {
		if(StringHelper.isEmpty(plant)){
			return new ArrayList<String>();
		}
		
		if(sorftPlantList==null){
			initSorftPlantList();
		}
		
		Set set = sorftPlantList.keySet();
		
		List<String> sortfList=new ArrayList<String>();
		boolean flag=true;
		for (Object key : set) {
			
			List<Map> list=sorftPlantList.get(key);
			for(Map obj:list){
				String cPlant=obj.get("PLANTID").toString();
				if(plant.contains(cPlant)){
					sortfList.add(key.toString());
					flag=false;
					break;
				}
			}
			if(!flag)
				break;
		}
		
		return sortfList;
		
	}


	/**
	 * 根据源订单获取目标订单
	 * @param sourceOrderId
	 * @return
	 */
	public List<Map> listForSelectByOrderId(String sourceOrderId) {
		return zgTorderExDao.listForSelectByOrderId(sourceOrderId);
	}
	
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderExDao.findByPageRequest(pr);
	}



	/**
	 * 手工结单
	 * 处理逻辑：
	 * 	1 生成领料计划 更新领料计划标记为手工完结单
	 *  2 领料计划设置成完成
	 *  3 按照BOM的仓库分配，每个仓库生成一张装车计划
	 * @param orderId
	 */
	public void manulFinishOrderByOrderId(String orderId,String operatorId) {
		List<String> softList=getSoftByOrderId(orderId);
		createPlan(orderId,softList,null,"0","",Constants.isManulFinished);
		
		// 更新领料计划标记为手工完结单
		zgTorderExDao.updateOrderPlanToManul(orderId);
		
		List<ZgTorderPlan> planList= zgTorderPlanExBo.getOrderPlanListByOrderId(orderId);
		
		//领料计划设置成完成
		for(ZgTorderPlan plan:planList){
			plan.setState(Constants.OrderPlanStatus.FINISHED.value());
			plan.setPercent(1d);
			zgTorderPlanBo.update(plan);
		}
		
		//领料计划设置成完成
		List<ZgTorderPlanGroup> planGrouList=zgTorderPlanGroupExBo.getPlanGroupListByOrderId(orderId);
		for(ZgTorderPlanGroup group:planGrouList){
			group.setState(Constants.OrderPlanStatus.FINISHED.value());
			double percent=zgTorderPlanGroupExBo.getPercent(group.getCuid());
			group.setPercent(1d);
			zgTorderPlanGroupExBo.update(group);
		}
		
		//按照BOM的仓库分配，每个仓库生成一张装车计划
		submitOrderPlan(orderId,operatorId);
		
		
		
		
	}
	
	/**
	 * 提交领料计划
	 * 处理逻辑：将该订单的bom组件按按仓库分组，每一个仓库的bom组件生成一张装车计划单
	 *         更新领料计划为提交状态
	 * @param orderPlanId 领料计划单编号
	 * @param operatorInfo 创建人
	 */
	public void submitOrderPlan(String orderId,String operatorId) {
		List<ZgTorderPlanbomEx> list = zgTorderPlanbomExBo.findBomListByOrderId(orderId);
		String lgortId="";
		String carPlanId="";
		int num=0;
		for(ZgTorderPlanbomEx obj : list) {
			if(Constants.CarPlanStatus.DONE.value().equals(obj.getState())){//该bom组件已经领取，则不需要再添加装车计划 或是领料人为空，直接不生成装车计划
				continue;
			}
			if(!lgortId.equals(obj.getLgort())){//仓库或领料人编号不一样，则创建新的装车单
					lgortId=obj.getLgort();
					ZgTcarplan zgTcarplan = new ZgTcarplan(); 
					zgTcarplan.setCreateUserId(operatorId);
					zgTcarplan.setCreateDate(Calendar.getInstance().getTime());
					zgTcarplan.setCarUser(operatorId);
					zgTcarplan.setStorageId(obj.getLgort());
					zgTcarplan.setCarState(Constants.CarPlanStatus.NEW.value());
					zgTcarplan.setType(Constants.CarPlanType.STOREGETDATA.value());
					zgTcarplan.setIsManul(Constants.isManulFinished);
					carPlanId=zgTcarplanBo.getCuid(num);
					zgTcarplan.setCuid(carPlanId);
					zgTcarplanBo.saveCarplan3(zgTcarplan);
					num++;
			}
			if(obj.getCarnum()>obj.getCompleteNum()){
				ZgTcarbom bom = new ZgTcarbom();
				bom.setCarPlanId(carPlanId);
				bom.setOrderId(obj.getOrderId());
				bom.setOrderBomId(obj.getOrderBomId());
				bom.setOrderPlanbomId(obj.getCuid());
				bom.setPlanNum(obj.getCarnum()-obj.getCompleteNum());
				bom.setRealNum(obj.getCarnum()-obj.getCompleteNum());
				zgTcarbomBo.save(bom);
				zgTorderPlanbomExBo.finishBom(obj.getCuid());
			}
			
		}
	}
	
	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}



	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}


	public void setZgTorderPlanGroupExBo(ZgTorderPlanGroupExBo zgTorderPlanGroupExBo) {
		this.zgTorderPlanGroupExBo = zgTorderPlanGroupExBo;
	}



	public void setZgTorderPlanbomExBo(ZgTorderPlanbomExBo zgTorderPlanbomExBo) {
		this.zgTorderPlanbomExBo = zgTorderPlanbomExBo;
	}




	public ZgTcarbomBo getZgTcarbomBo() {
		return zgTcarbomBo;
	}



	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}


//////////////////订单成品下线确认3个约束条件//////////////////////
	/**
	 * 约束条件一：本次下线数量<=已排序数量-已下线数量
	 * @param cuid  订单表ID
	 * @return
	 */
	public boolean getMaxFinishNum(String cuid,String finishNumStr) {
		Long finishNum=Long.parseLong(finishNumStr);
		Long maxFinishNumFromOrder=zgTorderExDao.getMaxFinishNumFromOrder(cuid);
		if(maxFinishNumFromOrder==null||maxFinishNumFromOrder<=0){
			return false;
		}
		if(finishNum>maxFinishNumFromOrder){
			return false;
		}
		return true;
	}
	/**
	 * 约束条件二：判断订单BOM表和领料计划BOM中的BOM数量是不是一样
	 * @param orderId
	 * @return
	 */
	public List<Map> isCountInOrderBomAndOrderPlanBomEqual(String orderId){
		return zgTorderExDao.isCountInOrderBomAndOrderPlanBomEqual(orderId);
//		if(listOrderBom==null||listOrderBom.size()==0){
//			return false;
//		}
//		List listOrderPlanBom=zgTorderExDao.getFromOrderPlanBom(orderId);
//		if(listOrderPlanBom==null||listOrderPlanBom.size()==0){
//			return false;
//		}
//		if(listOrderBom.size()!=listOrderPlanBom.size()){
//			return false;
//		}
	}
	/**
	 * 约束条件三：判断每一个BOM 够不够数量下线
	 * @return
	 */
	public String isEnoughBomForOrderFinish(String orderId,String finishNumStr){
		List<Map> allBomList=(List<Map>)zgTorderExDao.getAllBomFromOrderBomAndOrderPlanBom(orderId);
		Map map=new HashMap();
		Long minTempFinishOrderNum=Long.parseLong("99999999".toString());
		Long zdtyl;
		Long completeNum;
		Long moveNum;
		Long backNum;
		Long outNum;
		for(int i=0;i<allBomList.size();i++){//sdlkfjslkfjsdf
			
			map=(Map)allBomList.get(i);
			String idnrk=IbatisDAOHelper.getStringValue(map, "IDNRK");
			if(map.get("ZDTYL")==null||"".equals(map.get("ZDTYL").toString())||"0".equals(map.get("ZDTYL").toString())){
				return idnrk;
			}else{
				zdtyl=Long.parseLong(map.get("ZDTYL").toString());
				completeNum=Long.parseLong(map.get("COMPLETE_NUM").toString());
				moveNum=Long.parseLong(map.get("MOVE_NUM").toString());
				backNum=Long.parseLong(map.get("BACK_NUM").toString());
				outNum=Long.parseLong(map.get("OUT_NUM").toString());
//				if(minTempFinishOrderNum>(completeNum-moveNum-backNum-outNum)/zdtyl){//本次下线数量 <=(已领取数量-已出库数量)/单台用量
					if(Long.parseLong(finishNumStr)>(completeNum-outNum)/zdtyl){
						return idnrk;
					}
//				}
			}
		}
//		if(Long.parseLong(finishNumStr)>minTempFinishOrderNum){
//			return false;
//		}
		return "OK";
	}
	/**
	 * 更新订单表中成品已下线数量
	 */
	public void updateOrderPublishNum(String orderId,String finishNumStr){
		Long finishNum=Long.parseLong(finishNumStr);
		zgTorderExDao.updateOrderPublishNum(orderId, finishNum);
	}
	/**
	 * 更新领料计划BOM表中的已领料数  
	 * 更新后的已出库数量=原已出库数量+本次下线成品数量*单台用量
	 * @throws Exception 
	 */
	public void updatePlanBomPublishNum(String orderId,String finishNumStr) {
		List<Map> allBomList=(List<Map>)zgTorderExDao.getAllBomFromOrderBomAndOrderPlanBom(orderId);
		Map map=new HashMap();
		Long finishNumThisTime=Long.parseLong(finishNumStr);//本次下线成品数量
		String planBomId="";//领料计划bom表ID
		Long finishBomNum;//更新后的已出库数量
		Long outNum;//原已出库数量
		Long zdtyl;//单台用量
		
		for(int i=0;i<allBomList.size();i++){
			map=(Map)allBomList.get(i);
			planBomId=map.get("CUID").toString();
			outNum=Long.parseLong(map.get("OUT_NUM").toString());
			zdtyl=Long.parseLong(map.get("ZDTYL").toString());
//			finishBomNum=outNum+finishNumThisTime*zdtyl;
			zgTorderExDao.updatePlanBomPublishNum(planBomId, finishNumThisTime*zdtyl);
		}
		
	}
	/**
	 * 成品下线
	 *  1 更新成品已下线数量（原来成品下线数量+本次成品下线数量）
	 *  2 更新领BOM表中已出库数量（原来出库数量+单台用量*本次下线数量）
	 * @param orderId
	 * @param finishNumStr
	 * @throws Exception 
	 */
	public void doProcessPublish(String orderId, String finishNumStr)  {
		updateOrderPublishNum(orderId, finishNumStr);// 1 更新成品已下线数量
		updatePlanBomPublishNum(orderId, finishNumStr);//2 更新领料bom已出库数量
		/*updateProductNum(orderId, finishNumStr);//3 更新半成品 投入数用数量
*/	}
	/**
	 * 成品下线约束：半成品库存数量满足：已投入使用+本次下线数量*单台用量<=出库数量
	 * @param orderId 订单ID
	 * @param finishNumStr 本次下线数量
	 */
	public String isEnoughProductForOrderFinish(String orderId,String finishNumStr) {
		List<Map> prodListFromOrderBom=zgTorderExDao.getProdCountFromOrderBom(orderId);//查看订单是否有半成品 
		if(prodListFromOrderBom!=null&&prodListFromOrderBom.size()>0){
			Long finishNum=Long.parseLong(finishNumStr);
			List<Map> alllProdList=zgTorderExDao.getAllProduct(orderId);//查出所有半成品
			Long zdtyl;
			Long usedNum;
			Long outNum;
			if(alllProdList==null||alllProdList.size()==0||prodListFromOrderBom.size()!=alllProdList.size()){//半成品没有入库,或者半成品没有全部入库
				return "";
			}
			for(int i=0;i<alllProdList.size();i++){//半成品入库查看半成品的已投入使用数量是否满足下线
				zdtyl=Long.parseLong((alllProdList.get(i)).get("ZDTYL").toString());//单台用量
				usedNum=Long.parseLong((alllProdList.get(i)).get("USED_NUM").toString());//已投入使用数量
				outNum=Long.parseLong((alllProdList.get(i)).get("OUTNUM").toString());//出库数量
				if(usedNum+finishNum*zdtyl>outNum){//已投入使用+本次下线数量*单台用量<=出库数量 才可以下线
					return (alllProdList.get(i)).get("IDNRK").toString();
				}
			}
			return "OK";
		}else{
			return "OK";
		}
	}
	/**
	 * 产品下线更新半成品 投入数用数量
	 */
	public void updateProductNum(String orderId,String finishNumStr){
		Long finishNum=Long.parseLong(finishNumStr);
		List<Map> alllProdList=zgTorderExDao.getAllProduct(orderId);
		String storageCuid="";
		Long usedNum;
		Long zdtyl;
		for(int i=0;i<alllProdList.size();i++){
			storageCuid=(alllProdList.get(i)).get("STORAGES_CUID").toString();//库存汇总表ID
			zdtyl=Long.parseLong((alllProdList.get(i)).get("ZDTYL").toString());//单台用量
			usedNum=Long.parseLong((alllProdList.get(i)).get("USED_NUM").toString());//已投入使用数量
			zgTorderExDao.updateProductNum(storageCuid,usedNum+finishNum*zdtyl);
		}
	}



	/**
	 * 根据生产订单编号,登陆人编号获取其负责的该订单的生产厂
	 * @param orderId
	 * @param userId
	 */
	public List<Map> getPlantByOrderId(String orderId, String userId) {
		return zgTorderExDao.getPlantByOrderId(orderId,userId);
	}
}
