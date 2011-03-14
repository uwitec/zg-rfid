package com.boco.frame.excel.xlsexp.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;

@Component
@SuppressWarnings("unchecked")
public abstract class CreateExcelBo {
	
	public String extension = "xls";
	
	public abstract String createWorkBookByMap(String bmClassId,PageRequest<Map> pageRequest,Map showMap,String tempFolderPath);
	
	public List selectObjectRequest(String bmClassId,PageRequest<Map> pageRequest,String boName) {
		List list = new ArrayList();
		
		Object bean = ApplicationContextHolder.getBean(boName);
		Method beanMethod = BeanUtils.findMethod(bean.getClass(), "findByRequest", pageRequest.getClass());
		try {
			list = (List)beanMethod.invoke(bean, pageRequest);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException("传入参数和调用参数不匹配！");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("方法调用 异常！");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("方法调用 异常！");
		}
		return list;
	}
	
	public HSSFCell createCell(HSSFRow row, int index) {
		HSSFCell cell = row.createCell((short) index);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		return cell;
	}

}
