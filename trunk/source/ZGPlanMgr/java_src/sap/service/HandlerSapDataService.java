package sap.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sap.model.SapResult;

import com.boco.zg.plan.base.model.ZgTorderTemp;
import com.sap.conn.jco.JCoTable;

public interface HandlerSapDataService {

	/**
	 * @param tableName
	 * @param map
	 */
	void insertDynamicTable(String tableName, Map map);

	/**
	 * @param data
	 * @param synTable
	 * @param synBomList
	 * @param batchNo
	 */
	void getSynBomDataByOrderPlanId(String data, JCoTable synTable,
			List<Map> synBomList, int batchNo);

	/**
	 * 
	 */
	void synBomMateriel();

	/**
	 * @param batchNo
	 * @param type
	 */
	void handlerMatklSelfData(int batchNo, String type);

	/**
	 * @param substring
	 * @param synTable
	 * @param synBomList
	 * @param batchNo
	 */
	void getSynBomDataByCarPlanId(String substring, JCoTable synTable,
			List<Map> synBomList, int batchNo);

	/**
	 * @param synTable
	 * @param synBomList
	 * @param batchNo
	 */
	void getSynBomData(JCoTable synTable, List<Map> synBomList, int batchNo);

	/**
	 * @param batchNo
	 */
	void handleSuppliersData(int batchNo);

	/**
	 * @param batchNo
	 */
	void updateRelation(int batchNo);

	/**
	 * @param batchNo
	 */
	void handleBatchBomByBo(int batchNo);

	/**
	 * @param aufnr
	 * @param batchNo
	 * @return
	 */
	String saveZgTorderByAufnr(String aufnr, int batchNo);

	/**
	 * @param batchNo
	 * @param aufnr
	 * @param orderId
	 */
	void addSapBomsDataByAufnr(int batchNo, String aufnr, String orderId);

	/**
	 * @param batchNo
	 */
	void deleteNotExistOrder(int batchNo);

	/**
	 * @param batchNo
	 * @param aufnr
	 * @param plant
	 * @param pxDateStr
	 * @param orderCou
	 */
	void doProdessPxOrder(int batchNo, String aufnr, String plant,
			String pxDateStr, Long orderCou);

	/**
	 * @param batchNo
	 */
	void generateCarPlan(int batchNo);

	/**
	 * @param batchNo
	 * @param aufnr
	 */
	void doProdessChangeOrder(int batchNo, String aufnr,ZgTorderTemp sapOrder);

	/**
	 * @param batchNo
	 * @param temp
	 */
	void doProdessPcOrder(int batchNo, ZgTorderTemp temp);

	/**
	 * @param batchNo
	 */
	void handlerOrderInfoSyn(JCoTable synTable,int batchNo);



	

}
