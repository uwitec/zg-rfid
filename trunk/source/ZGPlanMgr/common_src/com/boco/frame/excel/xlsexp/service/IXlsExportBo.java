package com.boco.frame.excel.xlsexp.service;

import java.util.Map;

import cn.org.rapid_framework.page.PageRequest;

public interface IXlsExportBo {
	
	public String exportXls(String bmClassId,PageRequest<Map> pageRequest,String userId);

	public String exportStorageXls(String bmClassId,String showBmClassId,
			PageRequest<Map> pageRequest, String sessionUserId);
}
