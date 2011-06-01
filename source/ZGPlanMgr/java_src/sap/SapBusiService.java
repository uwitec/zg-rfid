package sap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.base.dao.BaseDao;
import javacommon.util.StringHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import sap.service.HandlerSapDataService;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.base.model.TsysIfaceLog;
import com.boco.frame.sys.base.service.TsysIfaceLogBo;
import com.boco.zg.util.Constants;
import com.boco.zg.util.TimeFormatHelper;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

@SuppressWarnings("unchecked")
@Component
public class SapBusiService {
	
	private static final Log log=LogFactory.getLog(SapBusiService.class);
	
	public static Set<String> functionMap = new HashSet<String>();
	static {
		functionMap.add("ZSTFC_CONNECTION_RFID_01");
		functionMap.add("ZSTFC_CONNECTION_RFID_02");
		functionMap.add("ZSTFC_CONNECTION_RFID_03");
		functionMap.add("ZSTFC_CONNECTION_RFID_04");
		functionMap.add("ZSTFC_CONNECTION_RFID_05");
//		functionMap.add("ZSTFC_CONNECTION_RFID_06");
		functionMap.add("ZRFC_RFIDAUFNR_DATA");//订单信息接口
		// "ZSTFC_CONNECTION_RFID_01" 排序
		// "ZSTFC_CONNECTION_RFID_02" 排产
		// "ZSTFC_CONNECTION_RFID_03" 批量领料物料
		// "ZSTFC_CONNECTION_RFID_04" 工单变更
		// "ZSTFC_CONNECTION_RFID_05" 领料回传
		// "ZSTFC_CONNECTION_RFID_06" 退料接口
	}

	public void businessHandler(JCoFunction function) throws Exception {
		String functionName = function.getName();
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		TsysIfaceLog tsysIfaceLog = beforeInvokeDeal(functionName,batchNo);
		if (functionMap.contains(functionName)) {
			try{
				JCoParameterList input = function.getImportParameterList();// 输入参数
				// JCoParameterList output = function.getExportParameterList();//
				// 输出参数
				// JCoParameterList tables = function.getTableParameterList();//
				// 输出的数据表格
				JCoMetaData inputMeta = input.getMetaData();
				String inputValue = "";
				for (int i = 0; i < inputMeta.getFieldCount(); i++) {
					String inputName = inputMeta.getName(i);
					inputValue += "|" + input.getString(inputName);
				}
				// 解析数据
				// 最好开启线程异步处理数据,包括入库及逻辑校验。黄雄辉和智炜讨论下是否需要先入临时库再处理业务逻辑还是直接在内存中进行业务逻辑处理写入业务库
				//写入数据
				parseData(function, inputValue,batchNo);
				//线程处理数据
				LoadRequestProcessThread thread = new LoadRequestProcessThread();
				thread.setBatchNo(batchNo);
				thread.setFunctionName(functionName);
				thread.setTsysIfaceLog(tsysIfaceLog);
				thread.setFunction(function);
				Thread t = new Thread(thread);
				t.start();
			}catch(Exception e){
				tsysIfaceLog.setRemark("businessHandler方法错误，调用方法名：" + functionName + "，不处理！"+e.getMessage());
				tsysIfaceLog.setResult(e.getMessage());
				tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
				getTsysIfaceLogBo().update(tsysIfaceLog);
				log.error("businessHandler方法错误，调用方法名：" + functionName + "，不处理！",e);
			}
				// A：以上解析数据过程中，需要与现有临时数据库中的数据进行比对及清洗
				// 1：是否已入正式库
				// 2：当前订单的数据清洗。同一订单下的多个半成品，每个半成品下的次级材料，次级材料下的bom组件。
				//    我们以前没考虑三级，今天发现数据有多级。lxm为成品与半成品的bom组件。lxm2为更详细的订单与材料的bom组件。请导入数据库进行分析
				// B：与业务数据进行比对，是否需要生成 冲单数据 
				// 1：是否已进入装车环节 
				// 2：是否已归档 
			} else {
				log.warn("未知的调用函数：" + functionName + "，不处理！");
				tsysIfaceLog.setRemark("未知的调用函数：" + functionName + "，不处理！");
				tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_FAILURE);
				getTsysIfaceLogBo().update(tsysIfaceLog);
			}
	
	}
	
	/**
	 * 写入接口日志
	 * @param functionName
	 * @return
	 */
	public TsysIfaceLog beforeInvokeDeal(String functionName,int batchNo) {
		TsysIfaceLog tsysIfaceLog = new TsysIfaceLog();
		tsysIfaceLog.setMethodName(functionName);
		tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_INIT);
		tsysIfaceLog.setSerCaller(Constants.SER_CALLER);
		tsysIfaceLog.setBatchNo(""+batchNo);
		tsysIfaceLog.setCallTime(Calendar.getInstance().getTime());
		getTsysIfaceLogBo().save(tsysIfaceLog);
		return tsysIfaceLog;
	}
	
	/**
	 * 写入接口日志
	 * @param functionName
	 * @return
	 */
	public TsysIfaceLog beforeInvokeDeal(TsysIfaceLog tsysIfaceLog) {
		tsysIfaceLog.setDataStauts(Constants.INTERFACE_DATA_STAUTS_INIT);
		tsysIfaceLog.setSerCaller(Constants.SER_CALLER);
		getTsysIfaceLogBo().save(tsysIfaceLog);
		return tsysIfaceLog;
	}

	private HandlerSapDataService getHandlerSapDataService() {
		return (HandlerSapDataService) ApplicationContextHolder
				.getBean("handlerSapDataServiceImpl");
	}
	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
	
	private static TsysIfaceLogBo getTsysIfaceLogBo() {
		return (TsysIfaceLogBo) ApplicationContextHolder
				.getBean("tsysIfaceLogBo");
	}
	/**
	 * 根据function的table的Meta信息自动解析数据
	 * 
	 * @param function
	 * @param functionName
	 * @param input
	 * @throws Exception
	 */
	public void parseData(JCoFunction function, String input,int batchNo)
			throws Exception {
		JCoParameterList list = function.getTableParameterList();
		JCoMetaData meta = list.getMetaData();
		for (int i = 0; i < meta.getFieldCount(); i++) {
			String tableName = meta.getName(i);
			JCoTable table = list.getTable(tableName);
			this.parseDataXls(table, function.getName(), input,batchNo);
			this.parseData(table, function.getName(), input,batchNo);
		}
		System.out.println("");
	}
	
	public static void parseDataXls(JCoTable table, String functionName,
			String input,int batchNo) throws Exception {
			JCoMetaData meta = table.getMetaData();
			System.out.println("正在解析" + table.getMetaData().getName() + "数据!");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("new sheet");
			short rowNum = 0;
			HSSFRow row = sheet.createRow(rowNum++);
			for (int n = 0; n < meta.getFieldCount(); n++) {
				HSSFCell cell = row.createCell((short) n);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(meta.getDescription(n) + "(" + meta.getName(n)
						+ ")");
			}
			
			for (int i = 0; i < table.getNumRows(); i++) {
				table.setRow(i);
				row = sheet.createRow(rowNum++);
				for (int n = 0; n < meta.getFieldCount(); n++) {
					HSSFCell cell = row.createCell((short) n);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(table.getString(meta.getName(n)));
					System.out.print(meta.getDescription(n) + "(" + meta.getName(n)
							+ "):" + table.getString(meta.getName(n)) + " $$ ");
				}
				System.out.println();
			}
			// 使用默认的构造方法创建workbook
			FileOutputStream fileOut = new FileOutputStream(functionName + "_"
					 + table.getMetaData().getName()+"_"+batchNo + ".xls");
			// 指定文件名
			wb.write(fileOut);
			// 输出到文件
			fileOut.close();
	}
	
	public void parseData(JCoTable table, String functionName,
			String input,int batchNo) throws Exception {
		HandlerSapDataService handlerSapDataService = getHandlerSapDataService();
		JCoMetaData meta = table.getMetaData();
		
		System.out.println("正在解析" +table.getMetaData().getName()+ "数据!");
		String tableName = "";
		boolean flag = false;
		if(table.getMetaData().getName().equals("ZLLXM")){
			flag = true;
			tableName = "ZG_T_ORDERBOM_TEMP_ALL";
		}
		if(table.getMetaData().getName().equals("ZLLXM2")){
			flag = true;
			tableName = "ZG_T_ORDERBOM_TEMP";
		}
		if(table.getMetaData().getName().equals("ZLLTT")){
			flag = true;
			tableName = "ZG_T_ORDER_TEMP";
		}
		if(table.getMetaData().getName().equals("ZBMAT")){
			flag = true;
			tableName = "ZG_T_BOM_TEMP";
		}
		if(table.getMetaData().getName().equals("ZGYS")){
			flag = true;
			tableName = "ZG_T_ORDER_SUPPLIERS_TEMP";
		}
		if(table.getMetaData().getName().equals("ZORDERSTAT")){
			flag = true;
			tableName = "ZG_T_STATTAB_TEMP";
		}
		if(table.getMetaData().getName().equals("FZRFTMS")){
			flag = true;
			tableName = "ZG_T_BACK_BOM";
		}if(table.getMetaData().getName().equals("ZRFIDMESG")&&Constants.InterFaceMethod.SYN.value().equals(functionName)){
			flag = true;
			tableName = "ZG_T_SYN_BOM_TEMP";
		}
		if(table.getMetaData().getName().equals("ZPLANT_S")){
			flag = true;
			tableName = "ZG_T_ORDER_PLANT_TEMP";
		}
		if(table.getMetaData().getName().equals("ZAUFNRF")){//领料订单排序日期状态等信息回传
			flag = true;
			tableName = "ZG_T_ORDER_TEMP_SYN";
		}
		
		Map<String,String> keyMap = getKeyMap(tableName);
		if(flag){
			for (int i = 0; i < table.getNumRows(); i++) {
				table.setRow(i);
				Map map = new HashMap();
				for (int n = 0; n < meta.getFieldCount(); n++) {
					String fieldName = meta.getName(n);
					String value = table.getString(meta.getName(n));
					if(keyMap!=null && (!StringHelper.isEmpty(keyMap.get(fieldName)))){
						if(keyMap!=null && keyMap.get(fieldName).equals("DATE")){
							if(value!=null && !value.equals("")){
								/** <！--add by wengq 2010-06-23 30:30 
								 * 排产 排序数据
								  创建时间(CPUTM) 创建日期(CRDAT)
								  更改时间(ZMUZE) 更改日期(MRNAM)
								  发布时间(FBUZE) 发布日期(FBDAT)
								  以上几个时间 相当于日期是天数，时间是小时分秒数，所以直接把“ 创建时间”转化为Date类型会出错，应该把“创建日期”和“创建时间”拼在一起形成创建时间
								  */
								Date date;
								String dateStr="";
								if (functionName.equals("ZSTFC_CONNECTION_RFID_01")||functionName.equals("ZSTFC_CONNECTION_RFID_02")){ 
									if("CPUTM".equals(fieldName)){
										dateStr=TimeFormatHelper.getFormatDate((Date)map.get("CRDAT"),TimeFormatHelper.DATE_FORMAT)+" "+value;
										date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
										map.put(fieldName, date);
									}else if ("ZMUZE".equals(fieldName)) {
										dateStr=TimeFormatHelper.getFormatDate((Date)map.get("MRNAM"),TimeFormatHelper.DATE_FORMAT)+" "+value;
										date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
										map.put(fieldName, date);
									}else if ("FBUZE".equals(fieldName)) {
										dateStr=TimeFormatHelper.getFormatDate((Date)map.get("FBDAT"),TimeFormatHelper.DATE_FORMAT)+" "+value;
										date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
										map.put(fieldName, date);
									}else {
										date = TimeFormatHelper.convertDate(value, TimeFormatHelper.DATE_FORMAT);
										map.put(fieldName, date);
									}
								}else {
									date = TimeFormatHelper.convertDate(value, TimeFormatHelper.DATE_FORMAT);
									map.put(fieldName, date);
								}
								
								 /** -->*/
							
							}
						}else{
							map.put(fieldName, value.trim());
						}
					}
				}
				if(tableName.equals("ZG_T_ORDER_TEMP")){
					String orderState = this.getOrderState(functionName);
					map.put("ORDER_STATE", orderState);
				}
				//上级物料编号默认跟订单物料号一样
				if(tableName.equals("ZG_T_ORDERBOM_TEMP_ALL")){
					map.put("MATNR1", map.get("MATNR"));
				}
				map.put("BATCH_NO", batchNo);
				handlerSapDataService.insertDynamicTable(tableName, map);
			}
		}
	}
	
	/*public static void parseDataXls(JCoTable table, String functionName,
			String input,int batchNo) throws Exception {
		JCoMetaData meta = table.getMetaData();
		System.out.println("正在解析" + table.getMetaData().getName() + "数据!");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		short rowNum = 0;
		HSSFRow row = sheet.createRow(rowNum++);
		for (int n = 0; n < meta.getFieldCount(); n++) {
			HSSFCell cell = row.createCell((short) n);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(meta.getDescription(n) + "(" + meta.getName(n)
					+ ")");
		}

		for (int i = 0; i < table.getNumRows(); i++) {
			table.setRow(i);
			row = sheet.createRow(rowNum++);
			for (int n = 0; n < meta.getFieldCount(); n++) {
				HSSFCell cell = row.createCell((short) n);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(table.getString(meta.getName(n)));
				System.out.print(meta.getDescription(n) + "(" + meta.getName(n)
						+ "):" + table.getString(meta.getName(n)) + " $$ ");
			}
			System.out.println();
		}
		// 使用默认的构造方法创建workbook
		FileOutputStream fileOut = new FileOutputStream(functionName + "_"
				 + table.getMetaData().getName()+"_"+batchNo + ".xls");
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();
	}*/
	
	
	
	private String getOrderState(String functionName) {
		//排产
//		if(functionName.equals("ZSTFC_CONNECTION_RFID_02")){
//			return "-1";
//		}else{
//			return "0";
//		}
		return "0";
	}

	private Map getKeyMap(String tableName) {
		Map keyMap = new HashMap();
		List<Map> columnTypeList = getColumnTypeList(tableName);
		for(Map map : columnTypeList){
			keyMap.put(map.get("COLUMN_NAME"), map.get("DATA_TYPE"));
		}
		return keyMap;
	}
	
	private List<Map> getColumnTypeList(String tableName) {
		String sql = "select t.COLUMN_NAME,t.DATA_TYPE from all_tab_columns  t where t.TABLE_NAME = '"+tableName+"'";
		return getBaseDao().queryBySql(sql);
	}
	
	public static void main(String args[]) throws IOException{
		Date date;
		try {
			date = TimeFormatHelper.convertDate("2010-09-02", TimeFormatHelper.TIME_FORMAT_A);
		} catch (Exception e) {
			e.printStackTrace();
			date = TimeFormatHelper.convertDate("2010-09-02", TimeFormatHelper.DATE_FORMAT);
		}
	
	}
}
