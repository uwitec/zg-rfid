package sap.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sap.model.SapResult;

import com.sap.conn.jco.JCoTable;

public interface HandlerSapDataService {

	public void updateRelation(int batchNo);
	
	
	public void addSapOrderData(int batchNo,Integer operateType,String aufnrArbpls,String px);
	
	
	public void addSapBomsData(int batchNo,Integer operateType,String aufnrArbpls);
	
	public void insertDynamicTable(String tableName,Map map);

	public void deletePcOrderBoms(int batchNo);

	public void deletePcOrders(int batchNo);
	/**
	 * 执行订单变更
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 */
	public void doUpdateChange(int batchNo, String aufnr,String arbpl,String pxType);
	

	/**
	 * 批量领料计划bom接口bom组件更新处理
	 * @param batchNo
	 */
	public void handleBatchBomByBo(int batchNo);
	
	/**
	 * 更新订单信息
	 * @param aufnr
	 * @param arbpl 生产线
	 * @param batchNo
	 * @param isPx
	 */
	public void doUpdateOrder(String aufnr,String arbpl,int batchNo,String px,String plant);

	/**
	 * 同步bom组件及物料组
	 */
	public void synBomMateriel();


	public void saveZgTorderAide(String orderId, String newPlant, Date pxDate,String poskey);


	public void updateZgTorderAide(String orderId, String plant, Date pxDate);

	/**
	 * 删除订单，rfid中存在并且接口过来的数据中不存在的订单
	 * @param batchNo
	 */
	public void deleteNotExistOrder(int batchNo);


	/**
	 * 判断订单是否是新增订单
	 * @param posKey
	 * @param aufnr
	 * @return －1　不是新增　0　是新增　1　之前的数据是排产数据 2新增（生产厂为新增，订单不是新增）
	 */
	public int isAddOrder(String posKey, String aufnr,String arbpl);

	/**
	 * 判断需求的排序数量是否发生变化
	 * @param posKey
	 * @param psmng 订单项数量
	 * @param pmenge　排序数量
	 * @return
	 */
	public boolean isPsmngNumChanged(String posKey, String psmng, String pmenge);


	/**
	 * 根据poskey更新order生产厂
	 * @param posKey
	 * @param plant 新的生产厂
	 * @param oldPlant
	 */
	public void doUpdateZgTorderPlantByPosKey(String posKey, String plant,String oldPlant);

	/**
	 * 更新订单辅表
	 * @param posKey
	 */
	public void doUpdateZgTorderAidePoskey(String posKey,int batchNo);

	/**
	 * 根据poskey更新订单信息
	 * @param posKey
	 */
	public void doUpdateZGtorderByPosKey(String posKey,int batchNO);
	
	public void updateBomMenge(Map map,Map planBom,String pxType,String arbpl);
	

	/**
	 * 插入订单
	 * @param posKey
	 */
	public String saveZgTorder(String posKey,int batchNo);
	
	/**
	 * 插入订单
	 * @param posKey
	 */
	public String saveZgTorderByAufnr(String aufnr,int batchNo);

	/**
	 * 根据poskey插入orderbom表　
	 * @param batchNo
	 * @param posKey
	 */
	public void addSapBomsDataByPosKey(int batchNo, String posKey,String orderId);


	/**
	 * 判断需求的排序数量是否发生变化
	 * @param posKey
	 * @param psmng 订单项数量
	 * @param pmenge　排序数量
	 * @return
	 */
	public boolean isPsmngNumChangedByOrderId(String orderId, String psmng,
			String pmenge);

	/**
	 * 根据orderId更新订单信息
	 * @param orderId
	 */
	public void doUpdateZGtorderByOrderId(String orderId,String posKey,int batchNo);


	/**
	 * 更新领料计划生产厂
	 * @param orderId　订单编号
	 * @param plant　　　新的生产厂
	 * @param oldPlant　旧的生产厂
	 */
	public void doUpdateZgTorderPlanPlant(String orderId, String plant,	String oldPlant);

	/**
	 * 更新orderbom的生产线
	 * @param orderId 订单编号
	 * @param arbpl　　　新生产线
	 * @param oldArbpl　旧生产线
	 */
	public void doUpdateZgTorderBomArbpl(String orderId, String arbpl,String oldArbpl);


	/**
	 * 根据aufnr插入orderbom表　
	 * @param batchNo
	 * @param posKey
	 */
	public void addSapBomsDataByAufnr(int batchNo, String aufnr, String orderId);


	/**
	 * 更新订单表的生产厂字段
	 * @param orderId
	 * @param string
	 */
	public void doUpdateZgTorderPlanPlant(String orderId, String plant);
	
	/**
	 * 处理供应商信息
	 * @param batchNo
	 */
	public void handleSuppliersData(int batchNo);

	/**
	 * 拆分生产线时，订单新增，订单状态和原来的订单状态一样
	 * @param posKey
	 * @param batchNo
	 * @orderState 订单状态
	 * @return
	 */
	public String saveZgTorderForChangeArbpl(String posKey, int batchNo,String orderState);


	/**
	 * 更新订单bom的制作标识为原来订单的制作标识
	 * @param orderId
	 * @param oldOrderId
	 */
	public void updateOrderBomSorft(String orderId, String oldOrderId);
	
	/**
	 * 排序接口订单修改
	 * 	逻辑：
	 * 　　　　1　判断订单的生产线是否改变，如果改变，则要相应的去更新orderbom的生产线信息
	 *        2 更新订单辅表，更新订单信息
	 *        3　判断订单需求数是否发生变化，如果发生变化，则需要更新相应的物料bom的需求数量等信息
 	 * @param arbpl
	 * @param posKey
	 * @param psmng
	 * @param pmenge
	 * @param isPsbhChange 订单排序号是否需要改变
	 */
	public void pxOrderInfoModify(	String arbpl, String posKey, String psmng, String pmenge,int batchNo,boolean isPsbhChange,String psbh)  throws Exception;
	
	/**
	 * 排序数据处理（订单新增）
	 * 处理逻辑：1　插入order表
	 * 　　　　　2　插入订单辅表
	 * 		　　3　插入订单bom表
	 * @param plant
	 * @param posKey
	 * @param pxDate
	 */
	public void pxOrderAdd(String plant, String posKey, Date pxDate,int batchNo) ;
	
	/**
	 * 排序数据处理（排序订单新增生产厂）
	 * 处理逻辑：　添加订单辅表
	 * 　　　　　　更新订单表
	 * @param aufnr
	 * @param plant
	 * @param newPlant
	 * @param arbpl
	 * @param posKey
	 * @param pxDate
	 */
	public void pxOrderAddPlanInfo(String aufnr,String plant, String newPlant, String arbpl, String posKey,
			Date pxDate);
	
	/**
	 * 排序订单数据处理（某一张订单，之前是排序数据，目前来了排序数据时的处理逻辑）
	 * 处理逻辑：1　如果生产线发生变化，则更新订单bom的生产线
	 * 　　　　　2　更新订单辅表　更新订单表
	 * 　　　　　3　如果订单需要数量改变，则相应的更新订单bom的数量
	 * @param aufnr
	 * @param plant
	 * @param arbpl
	 * @param posKey
	 * @param psmng
	 * @param pmenge
	 * @param pxDate
	 * @throws Exception 
	 */
	public void handlePcToPxData(String aufnr, String plant, String arbpl, String posKey,String psmng, String pmenge, Date pxDate,int batchNo) throws Exception;
	
	/**
	 * 生成领料计划
	 * 处理逻辑：　判断之前是否已经生成相应的领料计划，如果没有则生成，有则不处理
	 */
	public void generateCarPlan(int batchNo);
	
	/**
	 * 添加订单（该订单原来存在，拆分生产线 ）
	 * 处理逻辑：
	 * 			1　插入订单表（插入的订单状态和原来已经存在的订单一致）
	 * 　　　　　　2　插入订单辅表、
	 * 　　　　　　3　插入订单bom
	 *          4 更新订单bom的制作标识为原来存在的bom的制作标识一致
	 * @param aufnr
	 * @param plant
	 * @param posKey
	 * @param pxDate
	 */
	public void addOrderForChangeArbpl( String aufnr,	String plant, String posKey, Date pxDate,int batchNo);

	/**
	 * 变更接口处理订单状态
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 */
	public void doProcessOrderState(int batchNo, String aufnr, String arbpl);


	/**
	 * 获取需求同步回sap的bom领料数据
	 * @param synTable
	 */
	public void getSynBomData(JCoTable synTable,List<Map> synBomList,int batchNo);


	/**
	 * 标记装车计划为已同步
	 * @param synBomList
	 */
	public void updateCarPlanSynFlag(List<Map> synBomList);


	/**
	 *获取指定领料号单的待同步装车bom数据
	 * @param orderPlanId 如果是订单领料　则些字段是领料计划单id ,如果是批量领料，则些字段是装车单id
	 * @param synTable
	 * @param synBomList
	 */
	public void getSynBomDataByOrderPlanId(String orderPlanId, JCoTable synTable,
			List<Map> synBomList,int batchNo);


	/**
	 * 获取指定装车计划编号的待同步的装车bom数据
	 * @param carPlanId
	 * @param synTable
	 * @param synBomList
	 * @param batchNo
	 */
	public void getSynBomDataByCarPlanId(String carPlanId, JCoTable synTable,
			List<Map> synBomList, int batchNo);


	/**
	 * 		退料逻辑处理
	 *   	1 同步退料数据 ，planbom中相应的bom ：WAIT_BACK_NUM=WAIT_BACK_NUM-num
	 * 										  BACK_NUM=BACK_NUM+num
	 * 										  COMPLETE_NUM= COMPLETE_NUM-num
	 * 										  PLAN_NUM=PLAN_NUM-num
	 * 										  STORAGE_NUM=STORAGE_NUM-num
	 *      2 插入退料记录到zg_t_back_bom表
	 * @param batchNo
	 */
	public SapResult handlerBackBOM(int batchNo);


	/**
	 * 处理自有物料组
	 * 需在系统中增加一物料组，该物料组是在原来物料组的基础上进行细分，对于SAP每个同步过来的物料都需维护该物料组，
	 * 只要维护过一次下一次就下载时就不用再进行给维护，系统进行记忆功能，维护是在BOM标识调整时进行维护，
	 * 没有维护不能把生产订单下发领料。
	 * @param batchNo
	 * @param type 排序px 排产pc  变更bg
	 */
	public void handlerMatklSelfData(int batchNo,String type);


	

}
