package sap.service;

import javacommon.base.dao.BaseDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CompareSapDataServiceImpl implements CompareSapDataService {
	
	private static final Log log=LogFactory.getLog(CompareSapDataServiceImpl.class);
	
	private BaseDao baseDao;

	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 对比批量bom
	 * 对生idnrk和生产厂字段，如果原来zgtbom表中已经存在，则标记为修改状态（2）
	 * @param batchNo
	 */
	public void compareBatchBomByBatchNo(int batchNo){
		StringBuffer updateBuffer=new StringBuffer();
		updateBuffer.append("update zg_t_bom_temp t set t.operate_type=2 ");
		updateBuffer.append(" where t.batch_no="+batchNo+" and  exists (select 1 from  zg_t_bom bom where bom.idnrk=t.idnrk ");
		updateBuffer.append(" and bom.plantid=t.lifnr) ");
		this.baseDao.executeSql(updateBuffer.toString());
	}

	public void compareOrderDataByBatchNo(int batchNo)   throws Exception{
		try{
			//比对出新增的订单
			this.compareAddOrderData(batchNo);
			//比对出修改的订单
			this.compareEditOrderData(batchNo);
			//比对出已生成领料计划但未提交的订单
			this.compareOrderPlanData(batchNo);
			//比对出已生成领料计划并已提交的订单
			this.compareOrderPlanSubmitData(batchNo);
			//比对出已装车的订单
			this.compareOrderCarData(batchNo);
		}catch(Exception e){
			log.error("方法compareOrderDataByBatchNo 对比数据失败,批次--"+batchNo+" :",e);
			throw new Exception("比对数据失败,"+e.getMessage());
		}
	}


	private void compareOrderCarData(int batchNo) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("update ZG_T_ORDER_TEMP temp set operate_type = ").append(CompareSapDataService.EDIT_CAR).append(" ");
		updateBuffer.append(" where temp.operate_type =2 and temp.batch_no = ");
		updateBuffer.append(batchNo);
		updateBuffer.append(" and exists (select 1 from ");
		updateBuffer.append(" ZG_T_ORDER s where s.AUFNR = temp.AUFNR and exists" +
				"(select 1 from ZG_T_CARBOM p where p.order_id = s.cuid))");
		this.baseDao.executeSql(updateBuffer.toString());
	}

	private void compareOrderPlanData(int batchNo) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("update ZG_T_ORDER_TEMP temp set operate_type = ").append(CompareSapDataService.EDIT_PLAN).append(" ");
		updateBuffer.append(" where temp.operate_type =2 and temp.batch_no = ");
		updateBuffer.append(batchNo);
		updateBuffer.append(" and exists (select 1 from ");
		updateBuffer.append(" ZG_T_ORDER s where s.AUFNR = temp.AUFNR and exists" +
				"(select 1 from zg_t_order_plan p where p.order_id = s.cuid and p.state<>'8'))");
		this.baseDao.executeSql(updateBuffer.toString());
	}
	
	private void compareOrderPlanSubmitData(int batchNo) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("update ZG_T_ORDER_TEMP temp set operate_type = ").append(CompareSapDataService.EDIT_PLAN_SUBMIT).append(" ");
		updateBuffer.append(" where temp.operate_type =2 and temp.batch_no = ");
		updateBuffer.append(batchNo);
		updateBuffer.append(" and exists (select 1 from ");
		updateBuffer.append(" ZG_T_ORDER s where s.AUFNR = temp.AUFNR and exists" +
				"(select 1 from zg_t_order_plan p where p.order_id = s.cuid and p.state='8'))");
		this.baseDao.executeSql(updateBuffer.toString());
	}

	private void compareEditOrderData(int batchNo) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("update ZG_T_ORDER_TEMP temp set operate_type = ").append(CompareSapDataService.EDIT).append(" ");
		updateBuffer.append(" where temp.operate_type is null and temp.batch_no = ");
		updateBuffer.append(batchNo);
		updateBuffer.append(" and exists (select 1 from ");
		updateBuffer.append(" ZG_T_ORDER s where s.AUFNR = temp.AUFNR)");
		this.baseDao.executeSql(updateBuffer.toString());
	}

	private void compareAddOrderData(int batchNo) {
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("update ZG_T_ORDER_TEMP temp set operate_type = ").append(CompareSapDataService.ADD).append(" ");
		updateBuffer.append(" where temp.operate_type is null and temp.batch_no = ");
		updateBuffer.append(batchNo);
		updateBuffer.append(" and not exists (select 1 from ");
		updateBuffer.append(" ZG_T_ORDER s where s.AUFNR = temp.AUFNR)");
		this.baseDao.executeSql(updateBuffer.toString());
	}

	//数据变更时比对BOM组件
	public void compareBomDataByBatchNoAndAufnr(int batchNo, String aufnr,String arbpl) throws Exception {
		try{
			//比对新增BOM数据
			this.compareAddOrderBoms(batchNo, aufnr,arbpl);
			//比对修改BOM数据
			this.compareEditOrderBoms(batchNo, aufnr,arbpl);
			//比对删除BOM数据
			this.compareDeleteOrderBoms(batchNo, aufnr,arbpl);
		}catch(Exception e){
			log.error("compareBomDataByBatchNoAndAufnr方法，对比数据失败,批次--"+batchNo+" :",e);
			throw new Exception("比对数据失败,"+e.getMessage());
		}
		
	}
	
	private void compareDeleteOrderBoms(int batchNo, String aufnr,String arbpl) {
		StringBuffer deleteBufferSql = new StringBuffer();
		deleteBufferSql.append("insert into  zg_t_orderbom_temp_all (ZDTYL,MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,CUID ,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_STATE ,STORAGE_NUM,posnr,operate_type,batch_no)");
		deleteBufferSql.append("select ZDTYL,0 as MENGE,MATKL,SORTF,LGORT,ZBZ,ZRZQD,CUID ,IDNRK,ORDER_ID,AUFNR,ARBPL,MATNR,MAKTX1,MAKTX2,MSEHL1,MSEHL2,LABEL_CN,SORTF_H,MATNR1,STORAGE_STATE ,STORAGE_NUM,posnr,").append(CompareSapDataService.DELETE).append(",").append(batchNo);
		deleteBufferSql.append(" from zg_t_orderbom m where m.aufnr='"+aufnr+"' and m.arbpl='"+arbpl+"' and not exists(select 1 from zg_t_orderbom_temp_all t");
		deleteBufferSql.append(" where t.batch_no = ").append(batchNo).append(" and t.aufnr = m.aufnr  and t.arbpl=m.arbpl and t.idnrk=m.idnrk and m.posnr=t.posnr)");
		this.baseDao.executeSql(deleteBufferSql.toString());
	}

	private void compareEditOrderBoms(int batchNo,String aufnr,String arbpl) {
		StringBuffer updateBufferSql = new StringBuffer();
		updateBufferSql.append("update ZG_T_ORDERBOM_TEMP_ALL s set s.operate_type= ").append(CompareSapDataService.EDIT);
		updateBufferSql.append(" where batch_no="+batchNo);
		updateBufferSql.append(" and exists (");
		updateBufferSql.append("select 1 from (");
		updateBufferSql.append("select ZDTYL,MENGE,MATKL,LGORT,ZRZQD,IDNRK,AUFNR,ARBPL,MATNR,MSEHL1,MSEHL2,MATNR1,posnr from ZG_T_ORDERBOM_TEMP_ALL t where t.batch_no=").append(batchNo).append(" and t.operate_type is null ").append(" and t.aufnr = '"+aufnr+"'  and t.arbpl='"+arbpl+"'");
		updateBufferSql.append(" minus ");
		updateBufferSql.append("select ZDTYL,MENGE,MATKL,LGORT,ZRZQD,IDNRK,AUFNR,ARBPL,MATNR,MSEHL1,MSEHL2,MATNR1,posnr from ZG_T_ORDERBOM S WHERE S.aufnr='"+aufnr+"' and s.arbpl='"+arbpl+"') m where  s.idnrk=m.idnrk and  s.aufnr=m.aufnr and  s.matnr=m.matnr");
		updateBufferSql.append(")");
		this.baseDao.executeSql(updateBufferSql.toString());
	}

	private void compareAddOrderBoms(int batchNo,String aufnr,String arbpl) {
		StringBuffer addBufferSql = new StringBuffer();
		addBufferSql.append("update ZG_T_ORDERBOM_TEMP_ALL temp set operate_type = ").append(CompareSapDataService.ADD).append(" ");
		addBufferSql.append(" where temp.operate_type is null and temp.batch_no = ");
		addBufferSql.append(batchNo);
		addBufferSql.append("    and temp.aufnr='"+aufnr+"'  and temp.arbpl='"+arbpl+"' and not exists (select 1 from ");
		addBufferSql.append(" ZG_T_ORDERBOM s where  S.aufnr='"+aufnr+"'  and s.arbpl='"+arbpl+"' and s.IDNRK = temp.IDNRK  and temp.posnr=s.posnr)");
		this.baseDao.executeSql(addBufferSql.toString());
	}
	
public static void main(String[] args) {
	new CompareSapDataServiceImpl().compareEditOrderBoms(30542,"6000075621","J1-N01");
}
	

}
