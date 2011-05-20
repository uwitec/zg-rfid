package com.boco.zg.util;

import sap.model.SapResult;

import com.sap.conn.jco.JCoTable;


public class CommonUtils {
	public static SapResult convertTableToResult(JCoTable table){
		SapResult result=new SapResult();
		table.setRow(0);
		String number = table.getString("NUMBER");
		String type=table.getString("TYPE");
		String message=table.getString("MESSAGE");
		result.setMessage(message);
		result.setNumber(number);
		result.setType(type);
		return result;
	 
	}
}
