package sap.service;

import java.util.List;
import java.util.Map;

public interface ParseExcelService {
	public List parseExcelFile( String filePath,String[] orderFields,String tableName) throws Exception;
}
