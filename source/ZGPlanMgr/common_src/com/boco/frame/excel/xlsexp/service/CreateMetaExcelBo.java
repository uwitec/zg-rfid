package com.boco.frame.excel.xlsexp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javacommon.base.model.EnumModel;
import javacommon.base.model.RelatedModel;
import javacommon.util.StringHelper;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.meta.model.BMAttrMeta;

/**
 * 通过META创建EXCEL
 * @author Administrator
 *
 */
@Component
@SuppressWarnings("unchecked")
public class CreateMetaExcelBo extends CreateExcelBo {

	@Override
	public String createWorkBookByMap(String bmClassId, PageRequest<Map> pageRequest,
			Map showMap,String tempFolderPath) {
		String beanName = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(bmClassId));
		String boName = beanName+"Bo";
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
	private String createWorkBook(String bmClassId,List dataList,Map<String,BMAttrMeta> showMap,String tempFolderPath) throws IOException {
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
	
	private void writeHeadSheet(HSSFSheet sheet,String sheetName,int sheetIndex,Map<String,BMAttrMeta> showMap) {
		int colnum = 0;
		HSSFRow row = sheet.createRow((short)0);
		for(BMAttrMeta attrMeta : showMap.values()) {
			if(attrMeta != null) {
				HSSFCell cell = this.createCell(row,colnum);
				//设置表头样式异常 !"
				cell.setCellValue(attrMeta.getLabelCn());
				colnum++;
			}
		}
	}
	
	
	
	private void writeDateSheet(HSSFSheet sheet,List list,Map<String,BMAttrMeta> showMap) {
		//创建工作表
		int rownum = 1;
		for(Object o : list) {
			int colnum = 0;
			HSSFRow row = sheet.createRow((short)rownum);
			for(BMAttrMeta attrMeta : showMap.values()) {
				if(attrMeta != null) {
					String propertyName = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(attrMeta.getAttrId()));
					String value = cn.org.rapid_framework.beanutils.BeanUtils.getProperty(o, propertyName);
					//转换枚举
					if(attrMeta.isEnum()){
						if(value!=null && !value.equals("")){
							value = this.getEnumData(value, attrMeta,o);
						}
					}
					//转换日期
					if(attrMeta.getAttrClassType().getName().equals("java.util.Date")){
						if(value!=null && !value.equals("")){
							value = this.getDateStrValue(attrMeta, o);
							/*Date d = TimeFormatHelper.convertDate(value, TimeFormatHelper.TIME_FORMAT_A);
							value = TimeFormatHelper.getFormatDate(d, TimeFormatHelper.TIME_FORMAT_A);*/
						}
					}
					//转换关联
					if(attrMeta.getIsRelation()){
						if(value!=null && !value.equals("")){
							value = this.getRelationValue(attrMeta,o);
						}
					}
					HSSFCell cell = this.createCell(row,colnum);
					//设置表头样式异常 !"
					cell.setCellValue(value);
					colnum++;
				}
			}
			rownum++;
		}
	}
	
	private String getRelationValue(BMAttrMeta attrMeta, Object o) {
		String properties = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(attrMeta.getAttrId()));
		String s = properties.substring(0,1).toUpperCase()+properties.substring(1,properties.length());
		String getEnumMethod = "get"+s+"_related";
		Method beanMethod = BeanUtils.findMethod(o.getClass(), getEnumMethod);
		RelatedModel relatedModel = null;
		try {
			relatedModel = (RelatedModel)beanMethod.invoke(o, new Object[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String result = relatedModel.getValue();
		return result;
	}

	private String getDateStrValue(BMAttrMeta attrMeta, Object o) {
		String properties = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(attrMeta.getAttrId()));
		String s = properties.substring(0,1).toUpperCase()+properties.substring(1,properties.length());
		String getEnumMethod = "get"+s+"String";
		Method beanMethod = BeanUtils.findMethod(o.getClass(), getEnumMethod);
		String value = "";
		try {
			value = (String)beanMethod.invoke(o, new Object[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}

	private String getEnumData(String value, BMAttrMeta attrMeta,Object o) {
		String properties = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName(attrMeta.getAttrId()));
		String s = properties.substring(0,1).toUpperCase()+properties.substring(1,properties.length());
		String getEnumMethod = "get"+s+"_enum";
		Method beanMethod = BeanUtils.findMethod(o.getClass(), getEnumMethod);
		EnumModel enumModel = null;
		try {
			enumModel = (EnumModel)beanMethod.invoke(o, new Object[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String result = enumModel.getValue();
		return result;
	}
}
