package com.boco.frame.excel.xlsimp;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;

import com.boco.frame.excel.*;
import com.boco.frame.excel.template.*;
import com.boco.frame.excel.util.*;

/**
 * ExcelExcute类
 * @author xh
 * 说明：使用DataSet输出Excel
 */
public class ExcelExcute implements IExcute {
	
	public ExcelExcute()
	{}
	
	// 执行的方法
	/*
	 * 参数说明：ds-需要导出的dataset
	 * tm-配置信息中的template
	 * params-输出文件路径
	 * */ 
	public Object excute(DataSet ds,Template tm,Object params)
	{		
		
		// 定义临时文件的输出
		String tempFilePath = params.toString() +"\\"+ System.currentTimeMillis()+".xls";
		
		// 声明一个工作薄 
		HSSFWorkbook workbook = new HSSFWorkbook();

		for (Table table : ds) {
			// 生成一个表格 

			HSSFSheet sheet = workbook.createSheet(table.getName());

			// 设置表格默认列宽度为15个字节 
			sheet.setDefaultColumnWidth((short) 15);

			//产生表格标题行 

			HSSFRow row = sheet.createRow(0);

			for (short i = 0; i < table.getColumnCount(); i++) {

				HSSFCell cell = row.createCell(i);
				
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);

				cell.setCellValue(table.getMeta().get(i).getName());

			}

			// 循环填充值
			for (int r = 0; r < table.getRowCount(); r++) {
				HSSFRow row2 = sheet.createRow(r + 1);

				for (short i = 0; i < table.getColumnCount(); i++) {

					HSSFCell cell = row2.createCell(i);
					
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);

					cell.setCellValue(table.getRow(r).get(i).toString());

				}
			}
	

		}
		         
		try { 

			FileOutputStream fOut = new FileOutputStream(tempFilePath);
			
		      workbook.write(fOut); 
		      
		      fOut.flush();
		      fOut.close();
	
		      } catch (IOException e) { 
	
		         // TODO Auto-generated catch block 
	
		         e.printStackTrace(); 
	
		      } 

		
		return tempFilePath;
	   
	}


}
