package sap.service;

public interface CompareSapDataService {

	public final static int ADD = 1;
	public final static int EDIT = 2;  //未做任何操作 /修改
	public final static int EDIT_PLAN = 21; //已生成领料计划未提交
	public final static int EDIT_PLAN_SUBMIT = 22;//已生成领料计划已提交
	public final static int EDIT_CAR = 23;//已生成装车计划未提交
	public final static int EDIT_CAR_SUBMIT = 24;//已生成装车计划未提交
	public final static int EDIT_OVER = 25;//已提交
	public final static int DELETE = 3;
	
	public void compareOrderDataByBatchNo(int batchNo) throws Exception;

	public void compareBomDataByBatchNoAndAufnr(int batchNo, String aufnr,String arbpl,String type)  throws Exception;
	
	/**
	 * 对比批量bom
	 * @param batchNo
	 */
	public void compareBatchBomByBatchNo(int batchNo) ;

	/**
	 * 清空操作类型 
	 * @param batchNo
	 */
	public void deleteOperatorTypeByBatchNo(int batchNo);

	
	
}
