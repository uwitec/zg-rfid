package com.boco.frame.excel.xlsexp.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import javacommon.base.service.IVmModelBo;
import javacommon.util.StringHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import com.boco.frame.meta.model.BMAttrMeta;
import com.boco.zg.util.ExcelConstants;

@Component
@SuppressWarnings("unchecked")
public class XlsExportBo implements IXlsExportBo {
	
	private String tempFolderPath;
	
	private IVmModelBo vmModelBo;
	
	public void setVmModelBo(IVmModelBo vmModelBo) {
		this.vmModelBo = vmModelBo;
	}

	public String exportXls(String bmClassId,PageRequest<Map> pageRequest,String userId) {
		Map<String,BMAttrMeta> showMap = vmModelBo.getAttrMetasByUser(bmClassId,userId);
		String tempFilePath = "";
		CreateExcelBo bo = null;
		if(showMap==null || showMap.size()==0){
			bo = (CreateExcelBo) ApplicationContextHolder.getBean("createBusiExcelBo");
			ExcelConstants bean = new ExcelConstants();
			Method beanMethod = BeanUtils.findMethodWithMinimalParameters(ExcelConstants.class, "get"+bmClassId);
			try {
				Map map = (Map) beanMethod.invoke(bean, new Object[0]);
				tempFilePath =bo.createWorkBookByMap(bmClassId, pageRequest, map, tempFolderPath);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			bo = (CreateExcelBo) ApplicationContextHolder.getBean("createMetaExcelBo");
			tempFilePath =bo.createWorkBookByMap(bmClassId, pageRequest, showMap, tempFolderPath);
		}
		return tempFilePath;
	}
	
	public String exportStorageXls(String bmClassId,String showBmClassId,
			PageRequest<Map> pageRequest, String userId) {
		Map<String,BMAttrMeta> showMap = vmModelBo.getAttrMetasByUser(bmClassId,userId);
		String tempFilePath = "";
		CreateExcelBo bo = null;
		if(showMap==null || showMap.size()==0){
			bo = (CreateExcelBo) ApplicationContextHolder.getBean("createBusiExcelBo");
			ExcelConstants bean = new ExcelConstants();
			Method beanMethod = BeanUtils.findMethodWithMinimalParameters(ExcelConstants.class, "get"+showBmClassId);
			try {
				Map map = (Map) beanMethod.invoke(bean, new Object[0]);
				tempFilePath =bo.createWorkBookByMap(bmClassId, pageRequest, map, tempFolderPath);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			bo = (CreateExcelBo) ApplicationContextHolder.getBean("createMetaExcelBo");
			tempFilePath =bo.createWorkBookByMap(bmClassId, pageRequest, showMap, tempFolderPath);
		}
		return tempFilePath;
	}
	
	public String getTempFolderPath() {
		return tempFolderPath;
	}

	public void setTempFolderPath(String tempFolderPath) {
		this.tempFolderPath = tempFolderPath;
	}
	
	public static void main(String[] args){
		System.out.println(StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName("state_enum")));
	}

	
}
