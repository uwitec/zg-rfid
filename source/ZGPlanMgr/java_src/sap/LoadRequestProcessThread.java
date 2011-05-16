/**
 * 
 */
package sap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.dao.BaseDao;
import javacommon.util.StringHelper;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.record.BarRecord;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import sap.model.SapResult;
import sap.service.CompareSapDataService;
import sap.service.HandlerSapDataService;
import sap.service.SapServiceException;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.TsysIfaceLog;
import com.boco.frame.sys.base.service.TsysIfaceLogBo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.util.CommonUtils;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

public class LoadRequestProcessThread implements Runnable {
	
    static String ABAP_AS = "ABAP_AS_WITHOUT_POOL";
    static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
    static String ABAP_MS = "ABAP_MS_WITHOUT_POOL";
	
	private static Log log=LogFactory.getLog(LoadRequestProcessThread.class);
	private int batchNo;

	private String functionName;
	
	private String data;

	private TsysIfaceLog tsysIfaceLog;
	
	private JCoFunction function;
	
	
	private static Object synLock=new Object();
	private static Object planLock=new Object();
	private static Object orderLock=new Object();
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}
	
	private ZgTorderExBo getZgTorderExBo(){
		return (ZgTorderExBo) ApplicationContextHolder
		.getBean("zgTorderExBo");
	}

	public void run() {
		try {
			// 处理排序数据
			if (functionName.equals("ZSTFC_CONNECTION_RFID_01")) {
				this.handlerPxData();
				this.handleSuppliersData();
				
				//处理自有物料组数据
				handlerMatklSelfData(batchNo,"px");
			}
			// 处理排产数据
			if (functionName.equals("ZSTFC_CONNECTION_RFID_02")) {
				this.handlerPcData();
				this.handleSuppliersData();
				
				//处理自有物料组数据
				handlerMatklSelfData(batchNo,"pc");
			}
			//批量领料bom组件
			if (functionName.equals("ZSTFC_CONNECTION_RFID_03")) {
				this.handlerBatchData();
			}
			// 处理变更数据
			if (functionName.equals("ZSTFC_CONNECTION_RFID_04")) {
				this.handlerChangeData();
				this.handleSuppliersData();
				
				//处理自有物料组数据
				handlerMatklSelfData(batchNo,"bg");
			}
			
			// 领料数据回传sap接口
			if (functionName.equals("ZSTFC_CONNECTION_RFID_05")) {
				this.handlerSynOrderBOM();
			}
			
			// 退料接口
			if (functionName.equals("ZSTFC_CONNECTION_RFID_06")) {
				this.handlerBackBOM();
			}

			
			getHandlerSapDataService().synBomMateriel();
			
		} catch (Exception e) {
			tsysIfaceLog.setRemark("run方法错误，批次--"+batchNo);
//			tsysIfaceLog.setResult(e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("run方法错误，批次--"+batchNo+":",e);
		}
	}
	
	

	/**
	 * 
	 * @param batchNo
	 * @param type 排序px 排产pc  变更bg
	 */
	private void handlerMatklSelfData(int batchNo,String type) {
		HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
		handlerSapDataService.handlerMatklSelfData(batchNo,type);
		
	}

	private DataSourceTransactionManager getTransactionManager() {
		return (DataSourceTransactionManager)ApplicationContextHolder.getBean("transactionManager");
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate)ApplicationContextHolder.getBean("jdbcTemplate");
	}
	
	private BasicDataSource getDataSource(){
		return (BasicDataSource)ApplicationContextHolder.getBean("dataSource");
	}
	
	/**
	 * 退料接口
	 * 处理逻辑：
	 * 		1 同步退料数据 ，planbom中相应的bom ：WAIT_BACK_NUM=WAIT_BACK_NUM-num
	 * 										  BACK_NUM=BACK_NUM+num
	 * 										  COMPLETE_NUM= COMPLETE_NUM-num
	 * 										  PLAN_NUM=PLAN_NUM-num
	 * 										  STORAGE_NUM=STORAGE_NUM-num
	 *      2 插入退料记录到zg_t_back_bom表
	 *      3 回传操作结果给sap(RETURN表：	BAPI_MTYPE		消息类型: S 成功,E 错误,W 警告,I 信息,A 中断
										SYMSGID	消息, 消息类  001 成功 002 系统异常 003 没有退料记录
										SYMSGNO	消息编号 001 成功 002 系统异常 003 没有退料记录
										BAPI_MSG 消息文本
										BALOGNR	应用程序日志: 日志号 
										BALMNR	应用日志：内部邮件序列号
										SYMSGV	消息,消息变量
										SYMSGV	消息,消息变量
										SYMSGV	消息,消息变量
										SYMSGV	消息,消息变量
										BAPI_PARAM	参数名称
										BAPI_LINE	参数中的行 
										BAPI_FLD	参数中的字段
										BAPILOGSYS	引发消息的逻辑系统
										
										00
)
	 *      注：如果整个过程不成功，则数据回滚
	 *       4 重新计算领料进度
	 * 
	 */
	private void handlerBackBOM() {
		try {
			HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
			SapResult result=handlerSapDataService.handlerBackBOM(batchNo);
			JCoTable returnTable =(JCoTable) function.getTableParameterList().getTable("RETURN");
			
			returnTable.appendRows(1);
			returnTable.setValue("BAPI_MTYPE",result.getType() );
			returnTable.setValue( "SYMSGID",result.getNumber());
			returnTable.setValue("SYMSGNO",result.getNumber());
			returnTable.setValue("BAPI_MSG",result.getMessage());
			
			if(result.getNumber().equals("001")){
				afterInvokeDeal(tsysIfaceLog,
						Constants.INTERFACE_DATA_STAUTS_SUCCESS,"");
			}else {
				afterInvokeDeal(tsysIfaceLog,
						Constants.INTERFACE_DATA_STAUTS_FAILURE,result.getNumber()+":"+result.getMessage());
			}
		
		} catch (Exception e) {
			tsysIfaceLog.setRemark("handlerBackBOM方法错误，批次--"+batchNo+":"+e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("handlerSynOrderBOM错误，批次--"+batchNo+":",e);
			
			JCoTable returnTable =(JCoTable) function.getTableParameterList().getTable("RETURN");
			
			returnTable.appendRows(1);
			returnTable.setValue("BAPI_MTYPE","E" );
			returnTable.setValue( "SYMSGID","002");
			returnTable.setValue("SYMSGNO","002");
			returnTable.setValue("BAPI_MSG","系统异常");
			
		}


	}
	

	/**
	 * 回传同步领料数据到sap接口
	 * 处理逻辑：
	 * 		1找到本次需要同步的装车计划bom数据
	 * 		2把装车计划都标记为已同步（设置事务）
	 * 		3把装车计划同步到sap接口
	 * 		4如果同步成功则提交第2步的事务　否则回滚
	 * @throws SQLException 
	 * @throws JCoException 
	 */
	@SuppressWarnings("unchecked")
	private void handlerSynOrderBOM() throws SQLException, JCoException {
		 DataSourceTransactionManager transactionManager=getTransactionManager();
		 TransactionDefinition def = new DefaultTransactionDefinition(); 
		 TransactionStatus status=null;
		
		
		HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
		
		String callName = Constants.SYN_BOM_SAP_METHOD;
		
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction(callName);
        
		JCoTable synTable = (JCoTable) function.getTableParameterList().getTable("FZRFIDMESG");
        
    	JCoParameterList input = function.getImportParameterList();
        if(function == null)
            throw new RuntimeException(callName+ " not found in SAP.");
		
		try {
	
			
			synchronized (synLock) {//加上同步锁
				
				List<Map> synBomList=new ArrayList<Map>();
				
				//date 为空时回传所有的bom 以"C:"开头，说明是carplanId,否则是orderplanId
				if(StringHelper.isEmpty(data)){//这里的data表示装车计划编号，如果为空，则代表要回传所有的bom
					handlerSapDataService.getSynBomData(synTable,synBomList,batchNo);
				}else if (data.startsWith("C:")) {//同步某张装车计划单
					handlerSapDataService.getSynBomDataByCarPlanId(data.substring(2,data.length()),synTable,synBomList,batchNo);
				}else {//同步某张领料单
					handlerSapDataService.getSynBomDataByOrderPlanId(data,synTable,synBomList,batchNo);
				}
				
				status= transactionManager.getTransaction(def); 

				 try {//本次同步的装车计划标识为已同步    用jdbc来控制事务
					for(Map bom:synBomList){
						String carPlanIds=IbatisDAOHelper.getStringValue(bom, "CARPLANCUID");
						carPlanIds=carPlanIds.replace(",", "','");
						getJdbcTemplate().update(" update zg_t_carplan t set t.syn='1' where t.cuid in ('"+carPlanIds+"')");
					}
				        
				 } catch (DataAccessException ex) { //出错则事务回滚
					 transactionManager.rollback(status); 
					 tsysIfaceLog.setRemark("更新数据库失败");
					 tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
					getTsysIfaceLogBo().update(tsysIfaceLog);
				     throw ex;  
				 } 
				
				
				 function.execute(destination);
				
				
				SapResult result=CommonUtils.convertTableToResult((JCoTable) function.getTableParameterList().getTable("RETURN"));
				
				if(Constants.InterFaceSynBomSap.SUCCESS.value().equals(result.getNumber())){//接口处理成功　
					//事务提交
					transactionManager.commit(status); 
					afterInvokeDeal(tsysIfaceLog,
							Constants.INTERFACE_DATA_STAUTS_SUCCESS,result.getMessage());
				}else {
					//事务回滚
					transactionManager.rollback(status); 
					tsysIfaceLog.setRemark(result.getNumber()+":"+result.getMessage());
					tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
					getTsysIfaceLogBo().update(tsysIfaceLog);
					
					
				}
				
			}
			
			
		} catch (Exception e) {
			tsysIfaceLog.setRemark("handlerPcData方法错误，批次--"+batchNo+":"+e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("handlerSynOrderBOM错误，批次--"+batchNo+":",e);
			
			//事务回滚
			transactionManager.rollback(status); 
		}finally{
		}
		
	}

	/**
	 * 更新供应商数据
	 */
	private void handleSuppliersData() {
		getHandlerSapDataService().handleSuppliersData(batchNo);
	}

	/**
	 * 批量领料计划bom接口处理
	 */
	private void handlerBatchData() {
		try {
		// 比对
		CompareSapDataService service = getCompareSapDataService();
		service.compareBatchBomByBatchNo(batchNo);
		
		HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
		
		handlerSapDataService.handleBatchBomByBo(batchNo);
	
		afterInvokeDeal(tsysIfaceLog,
				Constants.INTERFACE_DATA_STAUTS_SUCCESS,"");
	} catch (Exception e) {
		tsysIfaceLog.setRemark("handlerBatchData方法错误，批次--"+batchNo+":");
		tsysIfaceLog.setResult(e.getMessage());
		tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
		getTsysIfaceLogBo().update(tsysIfaceLog);
		log.error("handlerBatchData方法错误，批次--"+batchNo+":",e);
	}
	}

	/**
	 * modify by wengqin:原来是订单号生产线变更，现在是根据订单号变更
	 * @throws SapServiceException
	 */
	private void handlerChangeData()  throws SapServiceException{
		try {
			// 更新关系
			HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
			handlerSapDataService.updateRelation(batchNo);
			String aufnr = "";
			List<Map> list = this.getBaseDao().queryBySql("select AUFNR,plant,arbpl,pxdat from zg_t_order_temp where batch_no="+batchNo);
			if(list==null || list.size()==0){
				log.warn("变更数据中没有任何订单！,批次--"+batchNo);
				throw new SapServiceException("变更数据中没有任何订单！");
			}else if(list.size()>1){
				log.warn("变更数据中有多个订单！,批次--"+batchNo);
				throw new SapServiceException("变更数据中有多个订单！");
			}else{
				aufnr = ((String)list.get(0).get("AUFNR")).trim();
//				String arbpl=IbatisDAOHelper.getStringValue(list.get(0), "ARBPL", "").trim();
//				List<Map> listOrder = this.getBaseDao().queryBySql("select cuid,AUFNR,t.plant,t.pflag from zg_t_order t where t.AUFNR='"+aufnr+"' and arbpl= '"+arbpl+"'");
				List<Map> listOrder = this.getBaseDao().queryBySql("select cuid,AUFNR,t.plant,t.pflag,t.arbpl from zg_t_order t where t.AUFNR='"+aufnr+"'");
				if(listOrder==null || listOrder.size()==0){
					log.warn("变更数据中的订单在生产库中找不到对应的记录！,批次--"+batchNo+"  订单--"+aufnr);
					throw new SapServiceException("变更数据中的订单在生产库中找不到对应的记录！");
				}
				
				for(Map order:listOrder){
					
					String arbpl=IbatisDAOHelper.getStringValue(order, "ARBPL", "").trim();
					// 比对
					CompareSapDataService service = getCompareSapDataService();
					
					//清空操作类型 
					service.deleteOperatorTypeByBatchNo(batchNo);
					
					service.compareBomDataByBatchNoAndAufnr(batchNo,aufnr,arbpl,Constants.PxType.BG.value());
					// 比对完后处理数据
					
					Map<String, Object> map=listOrder.get(0);
					String plant=IbatisDAOHelper.getStringValue(map, "PLANT", "").trim();
					String pflag=IbatisDAOHelper.getStringValue(map, "PFLAG","").trim(); 
					
					handlerSapDataService.doUpdateOrder(aufnr,arbpl, batchNo, pflag, plant);
					handlerSapDataService.doUpdateChange(batchNo,aufnr,arbpl,Constants.PxType.BG.value());
					
					//变更接口处理订单状态
					handlerSapDataService.doProcessOrderState(batchNo,aufnr,arbpl);
					
				}
				
				
			}
			// 执行完后修改日志状态
			afterInvokeDeal(tsysIfaceLog,
					Constants.INTERFACE_DATA_STAUTS_SUCCESS,"");
		} catch (SapServiceException ex) {
			tsysIfaceLog.setRemark("数据异常--"+batchNo+ex.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_EXP);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("数据异常--"+batchNo+":",ex);
		}catch (Exception e) {
			tsysIfaceLog.setRemark("handlerChangeData方法错误，批次--"+batchNo+"");
//			tsysIfaceLog.setResult(e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			e.printStackTrace();
			log.error("handlerChangeData方法错误，批次--"+batchNo+":",e);
		}
	}

	/**
	 * 逻辑：
	 * 	  原来不存在的订单，直接添加
	 *    原来存在的订单，并且是未排序状态，则更新
	 *    原来存在的订单，并且是已排序状态，则不处理
	 *    (注：订单是否存在，以aufnr、arbpl来判断)
	 */
	@SuppressWarnings("unchecked")
	private void handlerPcData() {
		log.debug("开始处理排产数据，批次："+batchNo);
		try {
			// 更新关系
			HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
			handlerSapDataService.updateRelation(batchNo);
			
			List<Map> list= getBaseDao().queryBySql("select temp.aufnr ,temp.arbpl from zg_t_order_temp temp where temp.batch_no="+batchNo);
			for (Map map:list) {
				String aufnr=IbatisDAOHelper.getStringValue(map, "AUFNR", "").trim();
				
				List<Map> tempList=getBaseDao().queryBySql("select * from zg_t_order t where t.aufnr='"+aufnr+"' ");
				if(tempList.size()==0){//订单不存在则进行插入
					String orderId=handlerSapDataService.saveZgTorderByAufnr(aufnr,batchNo);
					handlerSapDataService.addSapBomsDataByAufnr(batchNo, aufnr,orderId);
				}
			}
			
			// 执行完后修改日志状态
			afterInvokeDeal(tsysIfaceLog,
					Constants.INTERFACE_DATA_STAUTS_SUCCESS,"");
		} catch (Exception e) {
			tsysIfaceLog.setRemark("handlerPcData方法错误，批次--"+batchNo+":"+e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("handlerPcData方法错误，批次--"+batchNo+":",e);
		}
	}

	/**
	 * modify by wengq  2010-06-23 增加：排序数据 重复推送 就是第一次推送有订单1，第二次推送也有订单1，但是第二次推送不属于订单调整的时候处理，只更新订单及订单bom的数据，不做订单调整
	 * modify by wengq  2010-06-26 
	 *    逻辑：
	 *      1   订单不存在，则直接插入
            2   原来存在排产数据，或是排序数据重复推送 就是第一次推送有订单1，第二次推送也有订单1，不做订单及bom调整，则只更新订单的工厂plant
                （注：工厂plant字段更新的时候，如果原来的值为C03,后面的值为C02，则变成C03,C02,则不是直接覆盖更新）
            3   生成领料计划单，但是领料计划单的状态为不可分配，生产线线长只可以看，不可以分配领料员等信息
            
     * modify by wengq:2010-08-24 10:50 修改(修改逻辑：订单逐条处理)
     　     　排序接口增加POSKEY字段，用来唯一标识一个订单元组
     　　　逻辑：
     　			1　poskey	对比：如果RFID中当天订单中的poskey从接口过来的排序数据中找不到　则删除　
     　　　　　　　　　		删除情况：该订单没有进行任何领料则直接删除
     　　　　　　　　　　　　　　	该订单已经开始领料则应该存入备料库（注：目前还没有备料库模型，所以也是直接删除订单及其领料计划）
            2 poskey 存在则更新现在的订单数据（更新zg_t_order表及zg_t_order_aide）
            3 poskey　如果接口数据中的poskey在rfid中没有，则新增
	 */
	@SuppressWarnings("unchecked")
	private void handlerPxData() {
		try {
			HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
			// 更新关系
			handlerSapDataService.updateRelation(batchNo);
			
			//删除本次对比：如果RFID中当天订单中的poskey从接口过来的排序数据中找不到　则删除　
			handlerSapDataService.deleteNotExistOrder(batchNo);
			
			
			//读取本次要处理的订单
			List<Map> orderMapList=this.getBaseDao().queryBySql("select temp.* from zg_t_order_temp temp where temp.batch_no = "+batchNo);
			
			Map plantPxDateMap=getPlantPxDateMap();
			
			//循环逐条处理
			for(Map map:orderMapList){
				String aufnr=IbatisDAOHelper.getStringValue(map, "AUFNR", "").trim();
				String plant=IbatisDAOHelper.getStringValue(map, "PLANT", "").trim();
				String newPlant=IbatisDAOHelper.getStringValue(map, "PLANT", "").trim();
				String arbpl=IbatisDAOHelper.getStringValue(map, "ARBPL", "").trim();
				String posKey=IbatisDAOHelper.getStringValue(map, "POSKEY", "").trim();
				String psmng=IbatisDAOHelper.getStringValue(map, "PSMNG", "").trim();
				String pmenge=IbatisDAOHelper.getStringValue(map, "PMENGE","").trim();
				String psbh=IbatisDAOHelper.getStringValue(map, "PSBH");
				Date pxDate=(Date) map.get("PXDAT");
				boolean isPsbhChange=false;
				if(plantPxDateMap.get(plant)!=null){
					if(pxDate.after((Date)plantPxDateMap.get(plant))){//订单排序日期大于当前排序日期，说明是提前领料的，需要改变订单排序号
						isPsbhChange=true;
					}
				}
				
				
				synchronized (orderLock) {//同步锁
					//判断订单是否新增订单
					int isAddOrder=handlerSapDataService.isAddOrder(posKey,aufnr,arbpl);
					if(isAddOrder==-1){//不是新增 1 更新订单辅表　更新订单信息　　判断需求的排序数量是否发生变化，如果发生变化，则得去变更相应的　orderbom,orderplanBom表　
						handlerSapDataService.pxOrderInfoModify(arbpl, posKey,	psmng, pmenge,batchNo,isPsbhChange,psbh);
					}else if (isAddOrder==0) {//该订单新增的，则直接插入订单辅表，订单表，订单bom表，再由后面统一生成领料计划
						handlerSapDataService.pxOrderAdd(plant, posKey, pxDate,batchNo);
					}else if(isAddOrder==3) {//折分生产线
						handlerSapDataService.addOrderForChangeArbpl( aufnr, plant,posKey, pxDate,batchNo);
					}else if (isAddOrder==1) {//之前的数据是排产数据  1 保存订单辅表　更新订单信息　　判断需求的排序数量是否发生变化，如果发生变化，则得去变更相应的　orderbom,orderplanBom表　
						handlerSapDataService.handlePcToPxData(aufnr, plant,arbpl, posKey, psmng, pmenge, pxDate,batchNo);
					}else if (isAddOrder==2) {//2新增（生产厂为新增，订单不是新增）
						handlerSapDataService.pxOrderAddPlanInfo(aufnr, plant,	newPlant, arbpl, posKey, pxDate);
					}
				}
				
				
			}
			//生成计划领料
			synchronized (planLock) {//同步锁
				handlerSapDataService.generateCarPlan(batchNo);
			}
			
					
			
			// 处理数据完后删除临时表的数据
			// 执行完后修改日志状态
			afterInvokeDeal(tsysIfaceLog,
					Constants.INTERFACE_DATA_STAUTS_SUCCESS,"");
		} catch (Exception e) {
			tsysIfaceLog.setRemark("handlerPxData方法出错,批次："+batchNo);
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("handlerPxData方法出错,批次："+batchNo+" :",e);
		}
	}

	

	/**
	 * 获取各个生产厂的排序日期
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map getPlantPxDateMap() {
		Map plantPxDateMap=new HashMap();
		List<Map> list=this.getBaseDao().queryBySql("select t.cuid,t.extend2 from fw_organization t where t.type='5'");
		for(Map map:list){
			plantPxDateMap.put(IbatisDAOHelper.getStringValue(map, "CUID"), (Date)map.get("EXTEND2"));
		}

		return plantPxDateMap;
	}

	private void afterInvokeDeal(TsysIfaceLog ifaceLog,
			String interfaceDataStauts,String content) {
		if (ifaceLog != null) {
			ifaceLog.setDataStauts(interfaceDataStauts);
			ifaceLog.setRemark(content);
			getTsysIfaceLogBo().update(ifaceLog);
		}
	}

	private static TsysIfaceLogBo getTsysIfaceLogBo() {
		return (TsysIfaceLogBo) ApplicationContextHolder
				.getBean("tsysIfaceLogBo");
	}

	private HandlerSapDataService getHandlerSapDataService() {
		return (HandlerSapDataService) ApplicationContextHolder
				.getBean("handlerSapDataServiceImpl");
	}

	private CompareSapDataService getCompareSapDataService() {
		return (CompareSapDataService) ApplicationContextHolder
				.getBean("compareSapDataServiceImpl");
	}
	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public TsysIfaceLog getTsysIfaceLog() {
		return tsysIfaceLog;
	}

	public void setTsysIfaceLog(TsysIfaceLog tsysIfaceLog) {
		this.tsysIfaceLog = tsysIfaceLog;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public JCoFunction getFunction() {
		return function;
	}

	public void setFunction(JCoFunction function) {
		this.function = function;
	}


}
