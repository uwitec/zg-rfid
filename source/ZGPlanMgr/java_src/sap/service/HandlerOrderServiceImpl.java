/**
 * 
 */
package sap.service;

import java.util.List;
import java.util.Map;

import javacommon.base.dao.BaseDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.plan.base.dao.ZgTorderPlanbomDao;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderTaskbom;
import com.boco.zg.plan.base.model.ZgTorderTemp;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.model.ZgTorderbomTempAll;
import com.boco.zg.plan.base.service.ZgTorderBo;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderTaskbomBo;
import com.boco.zg.plan.base.service.ZgTorderTempBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.base.service.ZgTorderbomTempAllBo;
import com.boco.zg.util.Constants;

/** 
 * @author wengq 
 * @date: May 25, 2011 5:37:17 PM
 * @vresion V 1.0 
 * @reade me:
 *
 */

@Component
public class HandlerOrderServiceImpl {
	public BaseDao baseDao;
	
	private static final Log log=LogFactory.getLog(HandlerOrderServiceImpl.class);
	
	public static Map<String, String> plantSortfMap=null;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public  Map<String, String>  getPlantSortfMap(){
		if(plantSortfMap==null){
			plantSortfMap=getZgTorderBo().getPlantSortfMap();
		}
		return plantSortfMap;
	}
	
	public ZgTorderBo getZgTorderBo(){
		return (ZgTorderBo) ApplicationContextHolder
		.getBean("zgTorderBo");
	}
	
	public ZgTorderbomTempAllBo getZgTorderbomTempAllBo(){
		return (ZgTorderbomTempAllBo) ApplicationContextHolder
		.getBean("zgTorderbomTempAllBo");
	}
	
	public ZgTorderTaskbomBo getZgTorderTaskbomBo(){
		return (ZgTorderTaskbomBo) ApplicationContextHolder
		.getBean("zgTorderTaskbomBo");
	}
	
	/**
	 * @return
	 */
	public ZgTorderbomBo getZgTorderbomBo() {
		return (ZgTorderbomBo) ApplicationContextHolder
				.getBean("zgTorderbomBo");
	}
	
	public ZgTorderPlanbomBo getZgTorderPlanbomBo(){
		return (ZgTorderPlanbomBo) ApplicationContextHolder
		.getBean("zgTorderPlanbomBo");
	}
	
	public ZgTorderTempBo getZgTorderTempBo(){
		return (ZgTorderTempBo) ApplicationContextHolder
		.getBean("zgTorderTempBo");
	}
	
	public ZgTorderPlanBo getZgTorderPlanBo(){
		return (ZgTorderPlanBo) ApplicationContextHolder
		.getBean("zgTorderPlanBo");
	}
	
	public ZgTorderPlanbomDao getZgTorderPlanbomDao(){
		return (ZgTorderPlanbomDao) ApplicationContextHolder
		.getBean("zgTorderPlanbomDao");
	}
	
	
	/**
	 * 查询原生产线订单是否已经开始领料
	 * @param planId
	 * @return
	 */
	public boolean isStartCar(String planId) {
		//
		String sql="select * from zg_t_order_planbom bom  where bom.order_plan_id='"+planId+"' and bom.plan_num>0";
		return this.baseDao.queryBySql(sql).size()>0;
	}
	
	/**
	 * bom修改时处理
	 * 	查询有变更的BOM
	 * 	遍历 1、更新orderbom信息
	 * 		2、更新taskbom需求数量
	 * 		3、更新planbom信息处理退料
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param order
	 */
	public int doPxEditBomData(int batchNo, String aufnr, String plant,ZgTorder order) {//测试过
		
		String sortf=getPlantSortfMap().get(plant);
		//设置修改物料标识为2
		List<ZgTorderbomTempAll> editBomList=getZgTorderbomTempAllBo().getForEditBomList(batchNo,aufnr,order.getArbpl(),plant,Constants.EDIT,sortf);
		//查询修改物料标识为2
//		List<ZgTorderbomTempAll> editBomList=getZgTorderbomTempAllBo().getBomListByBatchNoAufnrArbplPlantFlag(batchNo,aufnr,order.getArbpl(),plant,Constants.EDIT);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doPxEditBomData aufnr:"+aufnr+" pxdateStr:"+plant+" 共找到:"+editBomList.size()+" 条BOM修改记录分别为：");
			for(ZgTorderbomTempAll bomTemp:editBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		
		for(ZgTorderbomTempAll tempBom:editBomList){
			tempBom.setOrderId(order.getCuid());
			
			getZgTorderbomTempAllBo().updateOrderBom(tempBom);	
		
			//获取taskbom并更新
			ZgTorderTaskbom taskbom=getZgTorderTaskbomBo().getTaskbomByTaskIdAufnrIdnrkPosnr(order.getTaskId(),tempBom.getIdnrk(),tempBom.getAufnr(),tempBom.getPosnr());
			if(taskbom==null){
				taskbom=saveZgTorderTaskBom(order.getTaskId(), tempBom.getMenge(), tempBom.getCuid());
			}else {
				taskbom.setMenge(tempBom.getMenge());
				getZgTorderTaskbomBo().update(taskbom);
			}
			
			
			
			//更新orderplanbom
			doWithPlanBomBYTaskIdPlantTaskBomId(order.getTaskId(),plant,taskbom);
			
		}
		return editBomList.size();
		
	}

	/**
	 * 删除sap过来的数据中没的物料
	 * 	查看物料是否已经有排序数据和已经装车计划
	 * 	如果已经有装车计划，则生成退料数据
	 * 	否则直接删除
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param order
	 */
	public int doPxDelBomData(int batchNo, String aufnr, String plant,ZgTorder order) {//测试
		List<ZgTorderbom> delBomList=getZgTorderbomBo().getBomListByBatchNoAufnrArbplPlant(batchNo,aufnr,order.getArbpl(),plant);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doPxDelBomData aufnr:"+aufnr+" pxdateStr:"+plant+" 共找到:"+delBomList.size()+" 条BOM删除记录分别为：");
			for(ZgTorderbom bomTemp:delBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		
		for(ZgTorderbom tempBom:delBomList){
			tempBom.setOrderId(order.getCuid());
			
			//获取taskbom
			List<ZgTorderTaskbom> taskbomList=getZgTorderTaskbomBo().getTaskbomAufnrIdnrkPosnr(tempBom.getIdnrk(),tempBom.getAufnr(),tempBom.getPosnr());
			if(taskbomList.size()==0){//找不到排序数据，直接删除
				getZgTorderbomBo().removeById(tempBom.getCuid());
				continue;
			}
			
			boolean flag=true;//orderbom是否可以删除标志 如果所有的领料BOM未开始领料则可以
			for(ZgTorderTaskbom taskbom:taskbomList){
				//获取领料计划BOM
				ZgTorderPlanbom planbom=getZgTorderPlanbomBo().getPlanBomByTaskIdPlanTypeTaskBomId(taskbom.getOrderTaskId(),Constants.NEEDPLANSORTF,taskbom.getCuid());
				if(planbom==null||planbom.getPlanNum()==0l){//领料计划bom为空或做计划的数据为0 直接删除  taskbom
					if(planbom!=null){
						int row=getZgTorderPlanbomBo().removeById1(planbom.getCuid());
						if(row>0){
							getZgTorderTaskbomBo().removeById(taskbom.getCuid());
						}
					}else {
						getZgTorderTaskbomBo().removeById(taskbom.getCuid());
					}
					
				}else {//已经做了装车计划，则生成相应的退料数据
					flag=false;
					
					taskbom.setMenge(0l);
					getZgTorderTaskbomBo().update(taskbom);
					
					planbom.setCarNum(0l);
					Long carNum=planbom.getCarNum();
					Long completeNum=planbom.getCompleteNum();
					planbom.setState(Constants.OrderPlanStatus.FINISHED.value());
					planbom.setWaitBackNum(completeNum-carNum);
					getZgTorderPlanbomBo().update(planbom);
					
				}
			}
			if(flag){//所有物料未开始领料则可以删除ORDERBOM
				getZgTorderbomBo().removeById(tempBom.getCuid());
			}else {
				tempBom.setMenge(0l);
				getZgTorderbomBo().update(tempBom);
			}
		}
		return delBomList.size();
	}
	

	/**
	 *  查看列表BOM中是否已经开始领料
	 * @param planbomList
	 * @return
	 */
	public boolean isBomStartCar(List<ZgTorderPlanbom> planbomList) {
		boolean flag=false;
		for(ZgTorderPlanbom bom:planbomList){
			if(bom.getPlanNum()>0){
				flag=true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 处理新增加物料
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param order
	 */
	public int doPxAddBomData(int batchNo, String aufnr, String plant,ZgTorder order) {//处理
		//对比bom新增或是插入
	
		//设置新增物料标识为1
		List<ZgTorderbomTempAll> addBomList=	getZgTorderbomTempAllBo().getForAddBomList(batchNo,aufnr,order.getArbpl(),plant,Constants.ADD);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doPxAddBomData aufnr:"+aufnr+" pxdateStr:"+plant+" 共找到:"+addBomList.size()+" 条BOM新增加记录分别为：");
			for(ZgTorderbomTempAll bomTemp:addBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		
		//获取新增加的BOM物料 并设置新增物料标识为1
//		List<ZgTorderbomTempAll> addBomList=getZgTorderbomTempAllBo().getBomListByBatchNoAufnrArbplPlantFlag(batchNo,aufnr,order.getArbpl(),plant,Constants.ADD);
		
		for(ZgTorderbomTempAll bomTemp:addBomList){
			//插入orderbom
			bomTemp.setOrderId(order.getCuid());
			String orderBomId=getZgTorderbomTempAllBo().saveOrderBom(bomTemp).toString();
			String sortf=getPlantSortfMap().get(plant);
			if(sortf.equals(bomTemp.getSortf())){
				//插入工单任务BOM ZG_T_ORDER_TASKBOM
				ZgTorderTaskbom taskbom=saveZgTorderTaskBom(order.getTaskId(), bomTemp.getMenge(), orderBomId);
				
				//该BOM插入领料计划中
				doWithPlanBomBYTaskIdPlantTaskBomId(order.getTaskId(),plant,taskbom);
			}
		
			
		}
		return addBomList.size();
	}
	
	/**
	 * 订单变更接口处理新增加物料
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param order
	 */
	public void doChangeAddBomData(int batchNo, String aufnr,String orderId) {//处理
		//对比bom新增或是插入
	
		//设置新增物料标识为1
		List<ZgTorderbomTempAll> addBomList=	getZgTorderbomTempAllBo().getForAddBomList(batchNo,aufnr,"","",Constants.ADD);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doChangeAddBomData aufnr:"+aufnr+"orderId:"+orderId+" 共找到:"+addBomList.size()+" 条BOM新增加记录分别为：");
			for(ZgTorderbomTempAll bomTemp:addBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		
		//获取新增加的BOM物料 并设置新增物料标识为1
//		List<ZgTorderbomTempAll> addBomList=getZgTorderbomTempAllBo().getBomListByBatchNoAufnrArbplPlantFlag(batchNo,aufnr,order.getArbpl(),plant,Constants.ADD);
		
		for(ZgTorderbomTempAll bomTemp:addBomList){
			//插入orderbom
			bomTemp.setOrderId(orderId);
			String orderBomId=getZgTorderbomTempAllBo().saveOrderBom(bomTemp).toString();
		}
	}

	/**
	 * 处理订单排产数据的插入 之前没有排产数据直接过来排序数据
	 * @param batchNo
	 * @param aufnr
	 * @param rfidOrderList
	 * @param sapOrderList
	 * return orderId
	 */
	public String doProcessPcdate(int batchNo, String aufnr,			List<ZgTorder> rfidOrderList, List<ZgTorderTemp> sapOrderList) {//测试过
		//===================处理订单排产数据的插入========================================
		String orderId="";
		ZgTorderTemp zgTorderTemp=sapOrderList.get(0);
		if(rfidOrderList.size()==0){//之前没有该生产厂的排序数据
			//判断之前是否已经有其他厂的排序数据 或是排产数据，如果没有则需要先插入订单表
			List<ZgTorder> list=isOrderExists(aufnr);
			boolean isOrderExists=list.size()>0;
			if(!isOrderExists){//处理 订单排产数据不存在，需要插入
				
				//插入订单
				orderId=getZgTorderTempBo().saveZgTOrder(zgTorderTemp);
				zgTorderTemp.setCuid(orderId);
				
				//插入物料 根据订单号，生产厂，生产线查找sap过来的物料并插入
				insertZgTorderBomByAufnrArbplPlant(orderId,zgTorderTemp,batchNo);
				
			}else {//更新ORDER表信息
				orderId=list.get(0).getCuid();
				zgTorderTemp.setCuid(orderId);
				getZgTorderTempBo().updateZgTOrder(zgTorderTemp);
			}
		}else {//更新ORDER表信息
			
			orderId=rfidOrderList.get(0).getCuid();
			zgTorderTemp.setCuid(orderId);
			getZgTorderTempBo().updateZgTOrder(zgTorderTemp);
		}
		return orderId;
		
		//==============================================================================
	}

	/**
	 * 插入物料 根据订单号，生产厂，生产线查找sap过来的物料并插入
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 */
	public void insertZgTorderBomByAufnrArbplPlant(String orderId,ZgTorderTemp temp,int batchNo) {
		StringBuffer insertBuffer = new StringBuffer();
		insertBuffer.append("   INSERT INTO zg_t_orderbom(CUID,ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr) ");
		insertBuffer.append("		select sys_guid(),ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,'"+orderId+"',AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr from      ");
		insertBuffer.append("    (SELECT distinct ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,IDNRK,ORDER_ID,a.AUFNR,a.ARBPL,a.MATNR,a.MAKTX1,a.MAKTX2,MSEHL1,MSEHL2,a.LABEL_CN,SORTF_H,MATNR1,STORAGE_NUM,STORAGE_STATE,posnr   ");
		insertBuffer.append("    FROM Zg_t_Orderbom_Temp_All a WHERE a.aufnr='"+temp.getAufnr()+"' and a.arbpl='"+temp.getArbpl()+"' and a.plant='"+temp.getPlant()+"'   and a.batch_no='"+batchNo+"')");
		this.baseDao.executeSql(insertBuffer.toString());
		
	}
	/**
	 * 判断订单数据是否存在 
	 * @param aufnr
	 * @return
	 */
	public List<ZgTorder> isOrderExists(String aufnr) {
		ZgTorder zgTorder=new ZgTorder();
		zgTorder.setAufnr(aufnr);
		List<ZgTorder> list=getZgTorderBo().findByProperty(zgTorder);
		return list;
	}

	/**
	 * 根据taskId,plant taskbom处理相应原planbom插入和变更
	 * @param taskId
	 * @param taskBomId
	 */
	public void doWithPlanBomBYTaskIdPlantTaskBomId(String taskId,String plant,ZgTorderTaskbom taskbom) {//测试过
		String sortf=getPlantSortfMap().get(plant);
		
		//根据订单任务ID
		ZgTorderPlan plan=getZgTorderPlanBo().getPlanByTaskIdPlanType(taskId,sortf);
		if(plan==null) return;
		
		
		
		//判断排序BOM在planbom中是否存在 如果不存在由新增，存在由修改
//		ZgTorderPlanbom planbom=getZgTorderPlanbomBo().getPlanBomByTaskIdPlanTypeTaskBomId(taskId,sortf,taskbom.getCuid());
		ZgTorderPlanbom planbom=getZgTorderPlanbomBo().getPlanBomByPlanIdTaskBomId(plan.getCuid(),taskbom.getCuid());
		
		if(planbom==null){//测试过 直接插入
			planbom = new ZgTorderPlanbom();
			planbom.setOrderPlanId(plan.getCuid());
			planbom.setOrderTaskId(taskId);
			planbom.setTaskBomId(taskbom.getCuid());
			planbom.setState(Constants.CarPlanStatus.NEW.value());
			planbom.setCarNum(taskbom.getMenge());
			getZgTorderPlanbomDao().save(planbom);
		}else {//测试过 更新
			planbom.setCarNum(taskbom.getMenge());
			Long carNum=planbom.getCarNum();
			Long completeNum=planbom.getCompleteNum();
			if(carNum<=completeNum){//产生退料
				planbom.setState(Constants.OrderPlanStatus.FINISHED.value());
				planbom.setWaitBackNum(completeNum-carNum);
			}else {
				planbom.setState(Constants.OrderPlanStatus.NEW.value());
				planbom.setWaitBackNum(0l);
			}
			getZgTorderPlanbomBo().update(planbom);
			
			
		}
	}

	public ZgTorderTaskbom saveZgTorderTaskBom(String taskId,Long menge, String orderBomId) {
		ZgTorderTaskbom taskbom=new ZgTorderTaskbom();
		taskbom.setOrderTaskId(taskId);
		taskbom.setOrderBomId(orderBomId);
		taskbom.setMenge(menge);
		String cuid=getZgTorderTaskbomBo().save(taskbom).toString();
		taskbom.setCuid(cuid);
		return taskbom;
	}



	

	public List<ZgTorderTemp> getSapOrderListByAufnrPlant(String aufnr,
			String plant,int batchNo) {
		ZgTorderTemp zgTorderTemp=new ZgTorderTemp();
		zgTorderTemp.setAufnr(aufnr);
		zgTorderTemp.setPlant(plant);
		zgTorderTemp.setBatchNo(batchNo+"");
		List<ZgTorderTemp> sapOrderList=getZgTorderTempBo().findByProperty(zgTorderTemp);
		return sapOrderList;
	}

	public List<ZgTorder> getRfidOrderListByAufnrPlant(String aufnr,
			String plant) {
		ZgTorder zgTorder=new ZgTorder();
		zgTorder.setAufnr(aufnr);
		zgTorder.setPlant(plant);
		List<ZgTorder> rfidOrderList=getZgTorderBo().getOrderListByAufnrPlant(zgTorder);
		return rfidOrderList;
	}

	/**
	 * @param batchNo
	 * @param aufnr
	 */
	public int doPcEditBomData(int batchNo, String aufnr,String orderId) {
		//获取信息修改过的bom
		List<ZgTorderbomTempAll> editBomList=getZgTorderbomTempAllBo().getForPcEditBomList(batchNo,aufnr,Constants.EDIT);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doPcEditBomData aufnr:"+aufnr+"orderId:"+orderId+" 共找到:"+editBomList.size()+" 条BOM修改记录分别为：");
			for(ZgTorderbomTempAll bomTemp:editBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		//查询修改物料标识为2
//		List<ZgTorderbomTempAll> editBomList=getZgTorderbomTempAllBo().getBomListByBatchNoAufnrArbplPlantFlag(batchNo,aufnr,order.getArbpl(),plant,Constants.EDIT);
		
		for(ZgTorderbomTempAll tempBom:editBomList){
			tempBom.setOrderId(orderId);
			getZgTorderbomTempAllBo().updateOrderBomForPc(tempBom);	
		}
		return editBomList.size();
	}

	/**
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param orderId
	 * @return
	 */
	public int doBgAddBomData(int batchNo, String aufnr, String plant,String orderId) {
		//对比bom新增或是插入
		
		//设置新增物料标识为1
		List<ZgTorderbomTempAll> addBomList=	getZgTorderbomTempAllBo().getForAddBomList(batchNo,aufnr,"",plant,Constants.ADD);
		
		if(log.isInfoEnabled()){
			log.info("线程："+batchNo+" enter the method doPxAddBomData aufnr:"+aufnr+" pxdateStr:"+plant+" 共找到:"+addBomList.size()+" 条BOM新增加记录分别为：");
			for(ZgTorderbomTempAll bomTemp:addBomList){
				log.info("线程："+batchNo+":"+bomTemp.getIdnrk()+"  "+bomTemp.getPosnr());
			}
		}
		
		
		for(ZgTorderbomTempAll bomTemp:addBomList){
			//插入orderbom
			bomTemp.setOrderId(orderId);
			String orderBomId=getZgTorderbomTempAllBo().saveOrderBom(bomTemp).toString();
		}
		return addBomList.size();
	}

}
