package com.boco.frame.excel.xlsexp.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.excel.xlsexp.service.IXlsExportBo;

@SuppressWarnings("serial")
public class XlsExportAction extends BaseStruts2Action {
	
	private IXlsExportBo xlsExportBo;
	
	public void setxlsExportBo(IXlsExportBo xlsExportBo) {
		this.xlsExportBo = xlsExportBo;
	}
	
	private String exportFilePath;
	//1 带条件导出 2 导出全部
	private String exportType;
	
	private String fileName;
	
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	public String export() {
		String bmClassId = this.getRequest().getParameter("bmClassId");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		if(exportType!=null && exportType.equals("2")){
			pageRequest.setFilters(new HashMap());
		}
		this.exportFilePath = exportFilePath = xlsExportBo.exportXls(bmClassId,pageRequest,super.getSessionUserId());
		fileName = System.currentTimeMillis()+".xls";
		return SUCCESS;
	}
	
	/**
	 *  导出仓库领料计划
	 * @return
	 */
	public String exportStorage() {
		String bmClassId = this.getRequest().getParameter("bmClassId");
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		if(exportType!=null && exportType.equals("2")){
			pageRequest.setFilters(new HashMap());
		}
		String orderPlanType = this.getRequest().getParameter("orderPlanType");
		String showBmClassId = "";
		if(orderPlanType!=null &&  orderPlanType.equals("3")){
			showBmClassId = "ZG_T_CARPLAN_BATCH_STORAGE";
		}else{
			showBmClassId = "ZG_T_CARPLAN_STORAGE";
		}
		this.exportFilePath = exportFilePath = xlsExportBo.exportStorageXls(bmClassId,showBmClassId,pageRequest,super.getSessionUserId());
		fileName = System.currentTimeMillis()+".xls";
		return SUCCESS;
	}

	public String getExportFilePath() {
		return exportFilePath;
	}

	public void setExportFilePath(String exportFilePath) {
		this.exportFilePath = exportFilePath;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() throws Exception {
		InputStream is = null;
		try {
			is = new FileInputStream(exportFilePath);
		} catch (RuntimeException e) {
			is = new ByteArrayInputStream("文件下载失败".getBytes());
			e.printStackTrace();
		}
		return is;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	
	
}
