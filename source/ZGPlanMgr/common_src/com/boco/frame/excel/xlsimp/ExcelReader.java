package com.boco.frame.excel.xlsimp;

import java.io.*;
import java.text.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;

import com.boco.frame.excel.IDataReader;
import com.boco.frame.excel.template.Template;
import com.boco.frame.excel.util.*;

/**
 * ExcelReader类
 * @author xh
 * 说明：数据读取接口的Excel实现
 */
public class ExcelReader implements IDataReader {
	
	/*
	 * 属性
	 * */
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	/*
	 * 方法
	 * */

	public ExcelReader()
	{
		
	}
	
	public DataSet readData(Template tm,Object obj)
	{
		// 获取传入的文件
		this.filePath=obj.toString();
		
		// 定义用于
		DataSet dSet=new DataSet();
		
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					this.filePath));
			
			// 循环获取Excel的内容，并存储到临时对象DataSet中
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				if (null != workbook.getSheetAt(numSheets)) {
					
					// 定义table和设置基本属性
					Table table=new Table()	;	
					
					table.setMeta(new Meta());
					
					table.setName(workbook.getSheetName(numSheets));
					
					
					// 读取Sheet到Table中
					HSSFSheet aSheet = workbook.getSheetAt(numSheets);
					
					createTable(table, aSheet);
					
					// 读取完毕后把Table加入到DataSet集合
					dSet.add(table);
				
				}
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dSet;
	}

	/*
	 * 把Sheet中的数据填充到Table中
	 * */
	protected void createTable(Table table,HSSFSheet aSheet) throws DataException 
	{
		int rowNumOfSheet = 0;
		try
		{
			// 循环Sheet中的行
			for ( rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
				if (null != aSheet.getRow(rowNumOfSheet)) {
	
					HSSFRow aRow = aSheet.getRow(rowNumOfSheet);
	
					// 第一行默认为列名，获取列名
					if (rowNumOfSheet == 0) {
	
						createTableColumn(table, aRow);
					}
	
					// 其余行为数据行，进行数据填充
					else {
	
						createTableData(table, aRow);
					}
	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	
	/*
	 * 初始化Table中的Column，根據Sheet中的第一行進行初始化
	 * */
	protected void createTableColumn(Table table,HSSFRow aRow) {
		
		for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {
			if (null != aRow.getCell(cellNumOfRow)) {

				HSSFCell aCell = aRow.getCell(cellNumOfRow);

				if (!aCell.getStringCellValue().trim().equals(""))
				{
					Column column = new Column();
	
					// 设置列的属性，这里都定义为String
					column.setType(String.class.getName());
	
					// 设置名称，为Sheet中的列名
					column.setName(aCell.getStringCellValue());
	
					// 设置索引号，因为Map中没有索引，所以需要进行增加
					column.setIndex(cellNumOfRow);
	
					table.getMeta().addColumn(cellNumOfRow, column);
				}

			}
		}
		
	}
	
	
	/*
	 * 填充Sheet中行的数据到Table对应的Row中
	 * */
	protected void createTableData(Table table,HSSFRow aRow)  {
		
		short cellNumOfRow=0;

		try {
			
			Row row = table.newRow();
			
			for (cellNumOfRow = 0; cellNumOfRow <= table.getColumnCount(); cellNumOfRow++) {
			
				if (null != aRow.getCell(cellNumOfRow)) {
					
					HSSFCell aCell = aRow.getCell(cellNumOfRow);

					String value;
					int cellType = aCell.getCellType();
					
					// 因为excel读取中对应不同类型获取的函数不一样，需要进行判断
					switch (cellType) {
					
					case HSSFCell.CELL_TYPE_NUMERIC:// Numeric 注：日期和数字在poi中的类型都是Numeric
						// 判断是否是日期类型
						if (HSSFDateUtil.isCellDateFormatted(aCell)) {
							Date date = aCell.getDateCellValue();
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");

							value = sdf.format(date);
						} else {
							// 数字类型
							DecimalFormat df = new DecimalFormat("#");
							String strCell = df.format(aCell.getNumericCellValue());
							value = strCell.trim();
						}

						break;
						
					case HSSFCell.CELL_TYPE_STRING:// String

						String strCell = aCell.getStringCellValue();
						value = strCell.trim();

						break;

					default:
						value = "";// 其它格式的数据
					}

					// 判断所获取的列数据小于Table的列，大于可能出现空指针
					if (cellNumOfRow<row.getMeta().getCount())
					{
						// 防止某些行多出数据列
						row.set(cellNumOfRow, value);
					}	

				}
			}
			
			table.addRow(row);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
	}
	
}
