package sap.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javacommon.base.dao.BaseDao;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.zg.util.TimeFormatHelper;

import cn.org.rapid_framework.util.ApplicationContextHolder;

@SuppressWarnings("unchecked")
public class ParseExcelServiceImpl implements ParseExcelService{
	
	
	public List parseExcelFile(String filePath,String[] orderFields,String tableName) throws Exception {
		InputStream excelStream = new FileInputStream(filePath);
		POIFSFileSystem pfs = new POIFSFileSystem(excelStream);
		HSSFWorkbook workBook = new HSSFWorkbook(pfs);
		HSSFSheet sheet = workBook.getSheetAt(0);//获取第i个SHEET页
		List list = this.parseLineCalculate(sheet,orderFields,tableName);
		return list;
	}
	
	private List parseLineCalculate(HSSFSheet sheet,String[] orderedFields,String tableName) throws Exception {
		Map<String,String> keyMap = this.getKeyMap(tableName);
		List list = new ArrayList();
		Iterator ite = sheet.rowIterator();
		int rows = 0;
		
		while(ite.hasNext()){
			HSSFRow row = (HSSFRow) ite.next();
			if (rows >= 1) {
				Map model = new HashMap();
				for(int i=0;i<orderedFields.length;i++){
					HSSFCell cell = row.getCell((short)i);
					String value="";
					try{
						value = cell.getStringCellValue();
						value = StringUtils.substringBeforeLast(value, ".0");
					}catch (Exception e) {
						e.printStackTrace();
					}
				
					//System.out.println(value);
					String fieldName = orderedFields[i];
					if(keyMap!=null && keyMap.get(fieldName).equals("DATE")){
						/** <！--add by wengq 2010-06-23 30:30 
						 * 排产 排序数据
						  创建时间(CPUTM) 创建日期(CRDAT)
						  更改时间(ZMUZE) 更改日期(MRNAM)
						  发布时间(FBUZE) 发布日期(FBDAT)
						  以上几个时间 相当于日期是天数，时间是小时分秒数，所以直接把“ 创建时间”转化为Date类型会出错，应该把“创建日期”和“创建时间”拼在一起形成创建时间
						  */
						Date date;
						String dateStr="";
						if("CPUTM".equals(fieldName)){
							dateStr=TimeFormatHelper.getFormatDate((Date)model.get("CRDAT"),TimeFormatHelper.DATE_FORMAT)+" "+value;
							date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
							model.put(fieldName, date);
						}else if ("ZMUZE".equals(fieldName)) {
							dateStr=TimeFormatHelper.getFormatDate((Date)model.get("MRNAM"),TimeFormatHelper.DATE_FORMAT)+" "+value;
							date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
							dateStr=TimeFormatHelper.getFormatDate(date, TimeFormatHelper.TIME_FORMAT_A);
							model.put(fieldName, date);
						}else if ("FBUZE".equals(fieldName)) {
							dateStr=TimeFormatHelper.getFormatDate((Date)model.get("FBDAT"),TimeFormatHelper.DATE_FORMAT)+" "+value;
							date = TimeFormatHelper.convertDate(dateStr, TimeFormatHelper.TIME_FORMAT_A);
							model.put(fieldName, date);
						}else{
							date = TimeFormatHelper.convertDate(value, TimeFormatHelper.DATE_FORMAT);
							model.put(fieldName, date);
						}
						
						
						
					}else{
						model.put(fieldName, value.trim());
					}
				}
				list.add(model);
			}
			rows++;	
		}
		return list;
	}

	private Map getKeyMap(String tableName) {
		Map keyMap = new HashMap();
		List<Map> columnTypeList = this.getColumnTypeList(tableName);
		for(Map map : columnTypeList){
			keyMap.put(map.get("COLUMN_NAME"), map.get("DATA_TYPE"));
		}
		return keyMap;
	}

	private List<Map> getColumnTypeList(String tableName) {
		String sql = "select t.COLUMN_NAME,t.DATA_TYPE from all_tab_columns  t where t.TABLE_NAME = '"+tableName+"'";
		return this.getBaseDao().queryBySql(sql);
	}

	private BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}	
	
	public static void main(String[] args) {
		
	 Date	date = 	date = TimeFormatHelper.convertDate("2010-12-3  12:09:09", TimeFormatHelper.DATE_FORMAT);
	}
}
