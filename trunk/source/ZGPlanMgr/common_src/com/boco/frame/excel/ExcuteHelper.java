package com.boco.frame.excel;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.jsp.JspFactory;

import jxl.Sheet;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.frame.excel.template.*;
import com.boco.frame.excel.xlsimp.*;
import com.boco.frame.excel.util.*;


public class ExcuteHelper {
	/*
	 * 属性
	 * */
	
	private IConfig config;

	public IConfig getConfig() {
		return config;
	}

	public void setConfig(IConfig config) {
		this.config = config;
	}
	
	private IDataReader reader;
	
	
	public IDataReader getReader() {
		return reader;
	}

	public void setReader(IDataReader reader) {
		this.reader = reader;
	}
	
	private IExcute excute;
	
	public IExcute getExcute() {
		return excute;
	}

	public void setExcute(IExcute excute) {
		this.excute = excute;
	}
	
	
	
	
	// 返回值对象
	private Object resultObject;

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	
	
	


	/*
	 * 方法
	 * */
	
	public ExcuteHelper()
	{
		
	}
	
	public ExcuteHelper(IConfig configparam,IDataReader readerparam,IExcute excuteparam)
	{
		this.config=configparam;
		
		this.reader=readerparam;
		
		this.excute=excuteparam;
	}
	


	public void run(String templateid,Object readParams,Object excuteParams) throws Exception 
	{		
		// 获得目的Template
		Template temp=config.readConfig().getTemplateByID(templateid);
	    
		// 读取源到数据集中
		DataSet ds=reader.readData(temp,readParams);

		// 执行转换
		this.resultObject=excute.excute(ds, temp, excuteParams);
	}
	

}
