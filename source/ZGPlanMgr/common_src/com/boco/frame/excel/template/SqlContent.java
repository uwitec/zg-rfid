package com.boco.frame.excel.template;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Command类
 * @author xh
 * 说明：用于具体定义导入的小节
 *      对应sqls中的sql小节
 *      表示sql的配置项，其中@id等表示直接读取的参数，$表示传入的参数
 */
public class SqlContent {
	
	
	/*
	 * 属性
	 * */
	private String id;
	
	private String sqlContent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSqlContent() {
		return sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}
	
	private List<Dynamic> dynamicSqls;
	
	public List<Dynamic> getDynamicSqls() {
		return dynamicSqls;
	}

	public void setDynamicSqls(List<Dynamic> dynamicSqls) {
		this.dynamicSqls = dynamicSqls;
	}

	/*
	 * 方法
	 * */
	public SqlContent()
	{
		// 构造函数
	}
	
	/* 用于创建sql语句的函数
	 * 暂时采用直接替换的方法，暂时没有支持外部参数
	 * */ 
	public String creatSql(Map<String,String> inparameters,Map<String,String> outparameters)
	{
		String sqlString=this.getSqlContent();
		
		
		if (inparameters != null) {
			
			// 循环进行参数替换
			for (Object o : inparameters.keySet()) {
				if (inparameters.get(o)!=null)
				{
					sqlString=replaceString(sqlString, "#" + o.toString()+"#", inparameters.get(o).toString());
				}
			}
		}

		if (outparameters != null) {
			for (Object o : outparameters.keySet()) {
				sqlString.replace("#" + o.toString()+"#", outparameters.get(o)
						.toString());
			}
		}
		
		// 拼装动态sql的内容
		if (this.dynamicSqls!=null)
		{
			for (Dynamic dy : this.dynamicSqls) {
				
				sqlString=sqlString+" "+dy.createSql(inparameters);
				
				sqlString=sqlString+" "+dy.createSql(outparameters);
			}
		}
		
		return sqlString;
	}
	
	 /**替换字符的通用方法                           
	  * 源字串，要替换源字串,替换为的目的字串*/ 
   public static String replaceString(String s, String org, String ob) {
		String newString = " ";
		int first = 0;
		while (s.indexOf(org) != -1) {
			first = s.indexOf(org);
			if (first != s.length()) {
				newString = newString + s.substring(0, first) + ob;
				s = s.substring(first + org.length(), s.length());
			}

		}

		newString = newString + s;
		return newString;
	}
	
	
}
