package sap;

import java.util.HashMap;
import java.util.Map;

import javacommon.base.dao.BaseDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.TsysIfaceLog;
import com.boco.frame.sys.base.service.TsysIfaceLogBo;
import com.boco.zg.util.Constants;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

@Component
public class SapClient extends Object {
	
	private static final Log log=LogFactory.getLog(SapClient.class);
	
    static String ABAP_AS = "ABAP_AS_WITHOUT_POOL";
    static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
    static String ABAP_MS = "ABAP_MS_WITHOUT_POOL";
	
	public static void main(String str[]) {
		SapClient app = new SapClient();
//		app.getDataByOrderId("006000068168");
//		app.getOrderDataByDate("20100622","");
//		app.getProduceDataByDate("20100401");
//		app.getItem();
	}
	
//	private int batchNo;
//	private String functionName="";
	
	
	
	private static TsysIfaceLogBo getTsysIfaceLogBo() {
		return (TsysIfaceLogBo) ApplicationContextHolder
				.getBean("tsysIfaceLogBo");
	}
	
	private SapBusiService sapBusiService = (SapBusiService)ApplicationContextHolder.getBean("sapBusiService");

	
	public void businessHandler(String dataType,String data,int batchNo,String plant) {
		
//		this.batchNo = batchNo;
		
		TsysIfaceLog tsysIfaceLog = null;
		String functionName="";
		try{	
			if("1".equals(dataType)){//排产
				functionName="ZSTFC_CONNECTION_RFID_02";
				tsysIfaceLog = sapBusiService.beforeInvokeDeal(functionName,batchNo);
				getProduceDataByDate(data,batchNo,functionName);
			}else if ("2".equals(dataType)) {//排序
				functionName="ZSTFC_CONNECTION_RFID_01";
				tsysIfaceLog = sapBusiService.beforeInvokeDeal(functionName,batchNo);
				getOrderDataByDate(data,batchNo,functionName,plant);
			}else if("3".equals(dataType)) {//订单调整
				functionName="ZSTFC_CONNECTION_RFID_04";
				tsysIfaceLog = sapBusiService.beforeInvokeDeal(functionName,batchNo);
				getDataByOrderId(data,batchNo,functionName);
			}else if("4".equals(dataType)) {//批量领料bom
				functionName="ZSTFC_CONNECTION_RFID_03";
				tsysIfaceLog = sapBusiService.beforeInvokeDeal(functionName,batchNo);
				getItem(batchNo,functionName);
			}else if("5".equals(dataType)) {//SAP领料回传接口
				functionName="ZSTFC_CONNECTION_RFID_05";
				tsysIfaceLog = sapBusiService.beforeInvokeDeal(functionName,batchNo);
			}
		
		
			//线程处理数据
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName(functionName);
			thread.setData(data);
			thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		}catch(Exception e){
			tsysIfaceLog.setRemark("调用失败，方法名：" + functionName + "，不处理！");
//			tsysIfaceLog.setResult(e.getMessage());
			tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
			getTsysIfaceLogBo().update(tsysIfaceLog);
			log.error("调用失败，方法名：" + functionName + "，不处理！",e);
//			log
		}
	}
	
	
	public void parseData(JCoTable table, String functionName,	String input,int batchNo) throws Exception {
		JCoMetaData meta = table.getMetaData();
		table.setRow(0);
		for (int n = 0; n < meta.getFieldCount(); n++) {
			String fieldName = meta.getName(n);
			String value = table.getString(meta.getName(n));
			log.info(fieldName+":"+value);
		}
	}

	public void getSupplye(int batchNo) throws JCoException{
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction("ZRFC_GET_LLD");
    	JCoParameterList input = function.getImportParameterList();
        if(function == null)
            throw new RuntimeException("ZRFC_GET_LLD not found in SAP.");
        try
        {
            function.execute(destination);
        	JCoParameterList list = function.getTableParameterList();
			JCoMetaData meta = list.getMetaData();
			for(int i=0;i<meta.getFieldCount();i++){
				String tableName = meta.getName(i);
				JCoTable table = function.getTableParameterList().getTable(tableName);
//				sapBusiService.parseData(table,functionName,"",getBaseDao().getSeq("SEQ_BATCH_NO"));
			}
        }
        catch(AbapException e)
        {
			log.error("getSupplye方法错误，批次--"+batchNo+":",e);
        }
		
		
		
	}
	
	public void getItem(int batchNo,String functionName) throws Exception{
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction("ZRFC_GET_ZBMAT");
    	JCoParameterList input = function.getImportParameterList();
        if(function == null)
            throw new RuntimeException("ZRFC_GET_ZBMAT not found in SAP.");
        try
        {
            function.execute(destination);
			JCoTable table = function.getTableParameterList().getTable("ZBMAT");
			JCoTable table1 = function.getTableParameterList().getTable("ZGYS");
//			sapBusiService.parseDataXls(table, functionName, "",batchNo);
			sapBusiService.parseData(table,functionName,"",batchNo);
			sapBusiService.parseData(table1,functionName,"",batchNo);
        }
        catch(AbapException e)
        {
        	e.printStackTrace();
			log.error("getItem方法错误，批次--"+batchNo+":",e);
			throw e;
        }
		
		
	}

	/**
	 * 根据日期获取生在作业排序订单的SAP数据，日期格式20010527(排序数据)
	 * @param date
	 * @throws Exception 
	 */
	public void getOrderDataByDate(String date,int batchNo,String functionName,String plant) throws Exception {
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction("ZRFC_GET_PXDAT");
    	JCoParameterList input = function.getImportParameterList();
		input.setValue("PXDAT",date);
		
		JCoTable iPlantTable = (JCoTable) function.getTableParameterList().getTable("IPLANT");
		
		String[] plantArr=plant.split(",");
		for(int i=0;i<plantArr.length;i++){
			parseDataToTable(iPlantTable,i,plantArr[i]);
		}
		
//		sapBusiService.parseData(iPlantTable,functionName,date,batchNo);
		
        if(function == null)
            throw new RuntimeException("ZRFC_GET_PXDAT not found in SAP.");
        try
        {
            function.execute(destination);
            Map<String,JCoTable> map = this.getTables(function);
    		for(JCoTable table:map.values()){
//    			sapBusiService.parseDataXls(table, functionName, "",batchNo);
    			System.out.println("");
    			sapBusiService.parseData(table,functionName,date,batchNo);
    		}
        }
        catch(AbapException e)
        {
        	e.printStackTrace();
			log.error("getProduceDataByDate方法错误，批次--"+batchNo+":",e);
			throw e;
        }
	}
	
	private void parseDataToTable(JCoTable table,  int num,String plant) {
		try {
			
			table.appendRow();
	        //定位到第0行
			table.setRow(num);
			//设定该行对应变量
//			synTable.setValue("MIPOS",mipos );
			table.setValue( "PLANT",plant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//	synTable.setValue("POSNR",posnr);
	}
	
	/**
	 * 根据日期获取生产计划排产订单的SAP数据，日期格式20010527（排产数据）
	 * @param date
	 * @throws Exception 
	 */
	public void getProduceDataByDate(String date,int batchNo,String functionName ) throws Exception{
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction("ZRFC_GET_PCDAT");
    	JCoParameterList input = function.getImportParameterList();
		input.setValue("PCDAT",date);
        if(function == null)
            throw new RuntimeException("ZRFC_GET_PCDAT not found in SAP.");
        try
        {
            function.execute(destination);
            Map<String,JCoTable> map = this.getTables(function);
    		for(JCoTable table:map.values()){
//    			sapBusiService.parseDataXls(table, functionName, "",batchNo);
    			sapBusiService.parseData(table,functionName,date,batchNo);
    		}
        }
        catch(AbapException e)
        {
        	e.printStackTrace();
			log.error("getProduceDataByDate方法错误，批次--"+batchNo+":",e);
			throw e;
        }
	}
	
	/**
	 * 生产变更
	 * @param id
	 * @param batchNo
	 * @param functionName
	 * @throws Exception
	 */
	public void getDataByOrderId(String id,int batchNo,String functionName) throws Exception {
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        JCoFunction function = destination.getRepository().getFunction("ZRFC_GET_CHANGE");
    	JCoParameterList input = function.getImportParameterList();
		input.setValue("AUFNR",id);
        if(function == null)
            throw new RuntimeException("ZRFC_GET_CHANGE not found in SAP.");
        try
        {
            function.execute(destination);
            Map<String,JCoTable> map = this.getTables(function);
    		for(JCoTable table:map.values()){
//    			sapBusiService.parseDataXls(table, functionName, "",batchNo);
				sapBusiService.parseData(table,functionName,id,batchNo);
    		}
        }
        catch(AbapException e)
        {
        	e.printStackTrace();
			log.error("getProduceDataByDate方法错误，批次--"+batchNo+":",e);
			throw e;
        }
		
	}
	
	public Map<String,JCoTable> getTables(JCoFunction function){
		Map<String,JCoTable> map = new HashMap<String, JCoTable>();
		map.put("ZLLTT", function.getTableParameterList().getTable("ZLLTT"));
		map.put("ZLLXM", function.getTableParameterList().getTable("ZLLXM"));
		map.put("ZLLXM2", function.getTableParameterList().getTable("ZLLXM2"));
		map.put("ZGYS", function.getTableParameterList().getTable("ZGYS"));
		map.put("RETURN", function.getTableParameterList().getTable("RETURN"));
		try {
			map.put("IPLANT", function.getTableParameterList().getTable("IPLANT"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return map;
	}
	
	public Map<String,JCoTable> getBGTables(JCoFunction function){
		Map<String,JCoTable> map = new HashMap<String, JCoTable>();
		map.put("ZLLTT", function.getTableParameterList().getTable("ZLLTT"));
		map.put("ZLLXM", function.getTableParameterList().getTable("ZLLXM"));
		map.put("ZLLXM2", function.getTableParameterList().getTable("ZLLXM2"));
		map.put("STATTAB", function.getTableParameterList().getTable("STATTAB"));
		map.put("ZGYS", function.getTableParameterList().getTable("ZGYS"));
		map.put("RETURN", function.getTableParameterList().getTable("RETURN"));
		return map;
	}


	

	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
}
