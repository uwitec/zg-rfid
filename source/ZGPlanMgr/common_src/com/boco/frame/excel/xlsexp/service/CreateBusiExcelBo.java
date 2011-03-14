package com.boco.frame.excel.xlsexp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javacommon.util.StringHelper;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

/**
 * 通过配置创建EXCEL
 * @author Administrator
 *
 */
@Component
@SuppressWarnings("unchecked")
public class CreateBusiExcelBo extends CreateExcelBo {

	@Override
	public String createWorkBookByMap(String bmClassId, PageRequest<Map> pageRequest,
			Map showMap,String tempFolderPath) {
		String beanName = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(bmClassId));
		String boName = "";
		if(beanName!=null && beanName.equals("zgTorderPlan")){
			boName = beanName+"ExBo";
		}else{
			boName = beanName+"Bo";
		}
		List dataList = super.selectObjectRequest(bmClassId, pageRequest, boName);
		String path="";
		try {
			path = this.createWorkBook(bmClassId, dataList, showMap,tempFolderPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 写入xls数据
	 * @throws IOException 
	 */
	private String createWorkBook(String bmClassId,List dataList,Map showMap,String tempFolderPath) throws IOException {
		tempFolderPath = tempFolderPath.replaceAll("'\'", "//");
		String tempFilePath = tempFolderPath +"\\"+ System.currentTimeMillis()+"."+extension;
		//创建临时文件目录
		File folder = new File(tempFolderPath);
		if(!folder.exists()) {
			try {
				FileUtils.forceMkdir(folder);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("创建文件夹失败！");
			}
		}
		//创建xls文件模板
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		//创建表头
		writeHeadSheet(sheet,bmClassId,0,showMap);
		//写入内容
		writeDateSheet(sheet,dataList,showMap);
		
		FileOutputStream fOut = new FileOutputStream(tempFilePath);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
		return tempFilePath;
	}
	
	private void writeDateSheet(HSSFSheet sheet, List list, Map showMap) {
		//创建工作表
		int rownum = 1;
		for(Object o : list) {
			int colnum = 0;
			HSSFRow row = sheet.createRow((short)rownum);
			Iterator ite = showMap.keySet().iterator();
			while(ite.hasNext()){
				String key = ite.next().toString();
				String propertyName = (String)showMap.get(key);
				String value = "";
				if(propertyName!=null && (propertyName.endsWith("_enum") || propertyName.endsWith("related"))){
					String methodName = "get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1,propertyName.length());
					Method beanMethod = BeanUtils.findMethodWithMinimalParameters(o.getClass(), methodName);
					try {
						Object enumObj = beanMethod.invoke(o, new Object[0]);
						value = cn.org.rapid_framework.beanutils.BeanUtils.getProperty(enumObj, "value");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}else{
					value = cn.org.rapid_framework.beanutils.BeanUtils.getProperty(o, propertyName);
				}
				HSSFCell cell = this.createCell(row,colnum);
					//设置表头样式异常 !"
				cell.setCellValue(value);
				colnum++;
			}
			rownum++;
		}
	}

	private void writeHeadSheet(HSSFSheet sheet, String bmClassId, int i,
			Map showMap) {
		int colnum = 0;
		HSSFRow row = sheet.createRow((short)0);
		Iterator ite = showMap.keySet().iterator();
		while(ite.hasNext()){
			HSSFCell cell = this.createCell(row,colnum);
			//设置表头样式异常 !"
			cell.setCellValue(ite.next().toString());
			colnum++;
		}
	}
}
