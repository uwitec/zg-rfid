package sap;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.TsysIfaceLog;
import com.boco.frame.sys.base.service.TsysIfaceLogBo;
import com.boco.zg.util.Constants;

import javacommon.base.BaseStruts2Action;
import javacommon.base.dao.BaseDao;
import sap.service.HandlerSapDataService;
import sap.service.ParseExcelService;
import sap.service.ParseExcelServiceImpl;
import cn.org.rapid_framework.util.ApplicationContextHolder;
public class SapDataTestAction extends BaseStruts2Action
{
	
	
	public String invokeSapBgData()
	{
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		try {
			this.writeExcelBgDataToDb(batchNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName("ZSTFC_CONNECTION_RFID_04");
//			thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public String invokeSapBackBomData()
	{
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		TsysIfaceLog tsysIfaceLog = beforeInvokeDeal("ZSTFC_CONNECTION_RFID_06",batchNo);
		try {
			this.writeExcelBackBomDataToDb(batchNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName("ZSTFC_CONNECTION_RFID_06");
			thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	

	/**
	 * @param batchNo
	 * @throws Exception 
	 */
	private void writeExcelBackBomDataToDb(int batchNo) throws Exception {
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据
		String[] bomFields = {"AUFNR","MATNR","MAKTX","MENGE_D","LGORT_D","BUDAT","CPUTM"};
		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_06_FZRFTMS_2.xls",bomFields,"ZG_T_BACK_BOM_TEMP");
		int i = 0;
		for(Map map : list){
			i++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+i);
			getHandlerSapDataService().insertDynamicTable("ZG_T_BACK_BOM_TEMP", map);
		}
		
	}

	public String invokeSapPxData()
	{
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		TsysIfaceLog tsysIfaceLog = beforeInvokeDeal("ZSTFC_CONNECTION_RFID_02",batchNo);
		try {
			this.writeExcelPxDataToDb(batchNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName("ZSTFC_CONNECTION_RFID_01");
			thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public String invokeSapPcData()
	{
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		try {
//			this.writeExcelPcDataToDb(batchNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName("ZSTFC_CONNECTION_RFID_02");
//			thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public String invokeSapBatchBomData()
	{
		int batchNo = getBaseDao().getSeq("SEQ_BATCH_NO");
		try {
			this.writeExcelBatchBomDataToDb(batchNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			LoadRequestProcessThread thread = new LoadRequestProcessThread();
			thread.setBatchNo(batchNo);
			thread.setFunctionName("ZSTFC_CONNECTION_RFID_03");
			//thread.setTsysIfaceLog(tsysIfaceLog);
			Thread t = new Thread(thread);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	private void writeExcelBatchBomDataToDb(int batchNo) throws Exception {
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据
	//	BOM 组件()	物料描述()	度量单位文本(最多30个字符）()	物料组()	库存地点()	备注()	认证清单()	供应商或债权人的帐号()	名称 1()

		String[] bomFields = {"IDNRK","MAKTX2","MSEHL2","MATKL","LGORT","ZBZ","ZRZQD","LIFNR","NAME1","MEINS"};
		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZRFC_GET_ZBMAT__ZBMAT.xls",bomFields,"ZG_T_BOM_TEMP");
		int i = 0;
		for(Map map : list){
			i++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+i);
			getHandlerSapDataService().insertDynamicTable("ZG_T_BOM_TEMP", map);
		}
		
	}



	private void writeExcelBgDataToDb(int batchNo) throws Exception {
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据
		String[] bomFields = {"ARBPL","AUFNR","MATNR","MAKTX1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT","POSNR"};
		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLXM2_22070.xls",bomFields,"ZG_T_ORDERBOM_TEMP");
		int i = 0;
		for(Map map : list){
			i++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+i);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP", map);
		}
		//模拟写入BOM数据
		String[] bomFieldsAll = {"ARBPL","AUFNR","MATNR","MAKTX1","MSEHL1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT","ZBZ","ZRZQD","PLANT","POSNR"};
		List<Map> listAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLXM_22070.xls",bomFieldsAll,"ZG_T_ORDERBOM_TEMP_ALL");
		int j = 0;
		for(Map map : listAll){
			j++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+j);
			map.put("MATNR1", map.get("MATNR"));
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP_ALL", map);
		}
		//写入订单数据
		String[] orderFields = {"MANDT","PXDAT","PLANT","MIPOS","PCDAT","ARBPL","AUFNR","MATNR","KDAUF","KDPOS","KDTXT","ZCKPP","MAKTX2",
		"MAKTX1","ZZCKS","ATWRT2","PSMNG","PMENGE","ZTXT02","ZDBLC","BRGEW2","CRDAT","CPUTM","CRNAM","MRNAM",
		"ZMUZE","MNAME","FBDAT","FBUZE","FNAME","PFLAG","PSBH"};
		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLTT_22070.xls",orderFields,"ZG_T_ORDER_TEMP");
		int m = 0;
		for(Map map : listOrderAll){
			m++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+m);
			map.put("ORDER_STATE", "0");
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_TEMP", map);
		}
		

		//写入订单数据
		String[] orderSuppliersFields = {"AUFNR","IDNRK","LIFNR","NAME1","PLANT"};
		List<Map> listOrderSuppliersAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZGYS_22070.xls",orderSuppliersFields,"ZG_T_ORDER_SUPPLIERS_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		m = 0;
		for(Map map : listOrderSuppliersAll){
			map.put("BATCH_NO", batchNo);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_SUPPLIERS_TEMP", map);
		}
		
		//写入订单数据
		String[] orderStatFields = {"AUFNR","STAT","STATTXT"};
		List<Map> listOrdeSstatAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZORDERSTAT_22070.xls",orderStatFields,"ZG_T_STATTAB_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		m = 0;
		for(Map map : listOrdeSstatAll){
			map.put("BATCH_NO", batchNo);
			getHandlerSapDataService().insertDynamicTable("ZG_T_STATTAB_temp", map);
		}
	}
	
	private void writeExcelPcDataToDb(int batchNo) throws Exception{
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据ZSTFC_CONNECTION_RFID_02_ZLLXM_22358
		String[] bomFields = {"ARBPL","AUFNR","MATNR","MAKTX1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT","POSNR"};
		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLXM2_22070.xls",bomFields,"ZG_T_ORDERBOM_TEMP");
		int i = 0;
		for(Map map : list){
			i++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+i);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP", map);
		}
		//模拟写入BOM数据
		String[] bomFieldsAll = {"ARBPL","AUFNR","MATNR","MAKTX1","MSEHL1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT","ZBZ","ZRZQD","PLANT","POSNR"};
		List<Map> listAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLXM_22070.xls",bomFieldsAll,"ZG_T_ORDERBOM_TEMP_ALL");
		int j = 0;
		for(Map map : listAll){
			j++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+j);
			map.put("MATNR1", map.get("MATNR"));
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP_ALL", map);
		}
		//写入订单数据
		String[] orderFields = {"MANDT","PXDAT","PLANT","MIPOS","PCDAT","ARBPL","AUFNR","MATNR","KDAUF","KDPOS","KDTXT","ZCKPP","MAKTX2",
		"MAKTX1","ZZCKS","ATWRT2","PSMNG","PMENGE","ZTXT02","ZDBLC","BRGEW2","CRDAT","CPUTM","CRNAM","MRNAM",
		"ZMUZE","MNAME","FBDAT","FBUZE","FNAME","PFLAG","PSBH"};
		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZLLTT_22070.xls",orderFields,"ZG_T_ORDER_TEMP");
		int m = 0;
		for(Map map : listOrderAll){
			m++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+m);
			map.put("ORDER_STATE", "0");
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_TEMP", map);
		}
		
		//写入订单数据
		String[] orderSuppliersFields = {"AUFNR","IDNRK","LIFNR","LIFNR_NAME","PLANT"};
		List<Map> listOrderSuppliersAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_04_ZGYS_22070.xls",orderSuppliersFields,"ZG_T_ORDER_SUPPLIERS_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		m = 0;
		for(Map map : listOrderSuppliersAll){
			map.put("BATCH_NO", batchNo);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_SUPPLIERS_TEMP", map);
		}
		
	}
	
	private void writeExcelPxDataToDb(int batchNo) throws Exception{
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据
		String[] bomFields = {"ARBPL","AUFNR","MATNR","MAKTX1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT" ,"POSNR"};
		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_01_ZLLXM2_22070.xls",bomFields,"ZG_T_ORDERBOM_TEMP");
//		List<Map> list = excelService.parseExcelFile("E:\\sap\\ZLLXM2.xls",bomFields,"ZG_T_ORDERBOM_TEMP");
		int i = 0;
		for(Map map : list){
			i++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+i);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP", map);
		}
		//模拟写入BOM数据
		String[] bomFieldsAll = {"ARBPL","AUFNR","MATNR","MAKTX1","MSEHL1","IDNRK","MAKTX2","MSEHL2","ZDTYL","MENGE","MATKL","SORTF","LGORT","ZBZ","ZRZQD","PLANT" ,"POSNR"};
		List<Map> listAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_01_ZLLXM_22070.xls",bomFieldsAll,"ZG_T_ORDERBOM_TEMP_ALL");
//		List<Map> listAll = excelService.parseExcelFile("E:\\sap\\ZLLXM.xls",bomFieldsAll,"ZG_T_ORDERBOM_TEMP_ALL");

		int j = 0;
		for(Map map : listAll){
			j++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+j);
			map.put("MATNR1", map.get("MATNR"));
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDERBOM_TEMP_ALL", map);
		}
		//写入订单数据
		String[] orderFields = {"MANDT","PXDAT","PLANT","MIPOS","PCDAT","ARBPL","AUFNR","MATNR","KDAUF","KDPOS","KDTXT","ZCKPP","MAKTX2",
		"MAKTX1","ZZCKS","ATWRT2","PSMNG","PMENGE","ZTXT02","ZDBLC","BRGEW2","CRDAT","CPUTM","CRNAM","MRNAM",
		"ZMUZE","MNAME","FBDAT","FBUZE","FNAME","PFLAG","PSBH","POSKEY","ARBPL1"};
		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_01_ZLLTT_22070.xls",orderFields,"ZG_T_ORDER_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		int m = 0;
		for(Map map : listOrderAll){
			m++;
			map.put("BATCH_NO", batchNo);
			map.put("CUID", batchNo+"-"+m);
			map.put("ORDER_STATE", "0");
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_TEMP", map);
		}
		
		//写入订单数据
		String[] orderSuppliersFields = {"AUFNR","IDNRK","LIFNR","NAME1","PLANT"};
		List<Map> listOrderSuppliersAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_01_ZGYS_22070.xls",orderSuppliersFields,"ZG_T_ORDER_SUPPLIERS_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		m = 0;
		for(Map map : listOrderSuppliersAll){
			map.put("BATCH_NO", batchNo);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_SUPPLIERS_TEMP", map);
		}
		
		String[] orderPlant = {"PLANT"};
		List<Map> listOrderPlantAll = excelService.parseExcelFile("E:\\sap\\ZSTFC_CONNECTION_RFID_01_IPLANT_22070.xls",orderPlant,"ZG_T_ORDER_PLANT_TEMP");
//		List<Map> listOrderAll = excelService.parseExcelFile("E:\\sap\\ZLLTT.xls",orderFields,"ZG_T_ORDER_TEMP");

		m = 0;
		for(Map map : listOrderPlantAll){
			map.put("BATCH_NO", batchNo);
			getHandlerSapDataService().insertDynamicTable("ZG_T_ORDER_PLANT_TEMP", map);
		}
	}
	
	private HandlerSapDataService getHandlerSapDataService() {
		return (HandlerSapDataService) ApplicationContextHolder
				.getBean("handlerSapDataServiceImpl");
	}
	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
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
	

	private static TsysIfaceLogBo getTsysIfaceLogBo() {
		return (TsysIfaceLogBo) ApplicationContextHolder
				.getBean("tsysIfaceLogBo");
	}
	
	public  void test() throws Exception {
		
		ParseExcelService excelService = new ParseExcelServiceImpl();
		
		//写入关系数据
	//	BOM 组件()	物料描述()	度量单位文本(最多30个字符）()	物料组()	库存地点()	备注()	认证清单()	供应商或债权人的帐号()	名称 1()
		String[] bomFields = {"MANDT","ZRFPID","ZRFPOS","AUFNR","MATNR","MAKTX1","ARBPL","IDNRK","MAKTX2","MSEHL1","ZDTYL","MENGE","LGMNG","LIFNR","NAME1","SORTF","LGORT","LLTYP","MIPOS","KDAUF"};

		String filepath = "E:\\sap\\5-10";
		System.out.println(filepath + "文件夹下");
		File file = new File(filepath);
		if (!file.isDirectory()) {
		} else if (file.isDirectory()) {
			// System.out.println("文件夹");
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				System.out.println(filelist[i]);
				File readfile = new File(filepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
//					ZSTFC_CONNECTION_RFID_05_ZRFIDMESG_25556.xls
					String str[] = filepath.split("/");
					String fileNamePath=str[str.length - 1] + "\\"+ readfile.getName();
					System.out.println(fileNamePath+"   "+fileNamePath.substring(fileNamePath.lastIndexOf("_")+1,fileNamePath.lastIndexOf(".")));
					List<Map> list = excelService.parseExcelFile(fileNamePath,bomFields,"ZG_T_SYN_BOM_TEMP");
					int count = 0;
					String batchNo=fileNamePath.substring(fileNamePath.lastIndexOf("_")+1,fileNamePath.lastIndexOf("."));

					for(Map map : list){
						count++;
						map.put("BATCH_NO",batchNo );
						map.put("CUID", batchNo+"-"+count+"-"+i);
						getHandlerSapDataService().insertDynamicTable("ZG_T_SYN_BOM_TEMP", map);
					}
				}
			}
		} 

		
		
	}
}
