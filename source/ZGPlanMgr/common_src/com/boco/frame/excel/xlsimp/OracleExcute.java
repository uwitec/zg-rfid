package com.boco.frame.excel.xlsimp;

import java.sql.*;
import java.util.*;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.boco.frame.excel.IExcute;
import com.boco.frame.excel.template.Col;
import com.boco.frame.excel.template.Command;
import com.boco.frame.excel.template.SqlContent;
import com.boco.frame.excel.template.Template;
import com.boco.frame.excel.util.*;

/**
 * OracleExcute类
 * @author xh
 * 说明：IExcute的Oracle导入实现
 */
public class OracleExcute implements IExcute {
	
	/*
	 * 属性
	 * */
	private String username;
	private String password;
	private String driver;
	private String url;
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	} 
	
	
	/*
	 * 方法
	 * */
	public OracleExcute()
	{}
	
	// 执行的方法
	// 1.这里暂时没有捕捉每条sql的执行结果，进行日志记录，需完善
	// 2.暂时没有支持外部参数，需要的时候请修正
	public Object excute(DataSet ds,Template tm,Object params)
	{		
		
		// 定义返回值
		Map<Integer, String> resultListMap=new LinkedHashMap<Integer, String>();
		
		// 获取外部参数
		Map<String, String> outparams=new HashMap<String, String>();
		if (params!=null)
		{
			outparams=(Map<String, String>)params;
		}
		
		for (Table table : ds) {
			
			// 获取Command
			Command c=null;
			
			for (Command cmd : tm.getCommands()) {
				
				// 当Table的Name和command的ID一置的时候执行
				if (table.getName().toLowerCase().equals(cmd.getSheetName().toLowerCase()))
				{
					c=cmd;
				}
			}
			
			// 定义sql集合
			List<String> sqlStringsList=new ArrayList<String>();
			
			// 逐行构建需要执行的Sql集合
			for(int i=0;i<table.getRowCount();i++)
			{
				Row row=table.getRow(i);
				
				// 构建列参数,其中TableRow中的列名是Templatecol中的Name
				Map<String, String> colparaMap=new HashMap<String, String>();
				
				// 循环获取列参数
				for (Col col : c.getCols()) {
					colparaMap.put(col.getId(), row.get(col.getName())==null?"":row.get(col.getName()).toString());
				}
				SqlContent content=c.getSqls().getDefaultSqlContent();
				
				// 暂时不带入外部参数
				sqlStringsList.add(content.creatSql(colparaMap, outparams));
			}
			
			
			try {
							
				// 执行sql语句集合，并写入返回值
				this.excuteSql(sqlStringsList, resultListMap);

			} catch (Exception e) {
				System.out.println("ReadExcelError" + e);
			}
			
		}	
		
		// 用于在控制台打印错误结果
		for (Iterator iterator = resultListMap.values().iterator(); iterator.hasNext();) {
	            String name = (String) iterator.next();
	            String idString=(String)iterator.toString();
	            System.out.println(idString+"_"+name);
	        }
		
		
		return resultListMap;
	}
	
	
	
	
	
	public void excuteSql(List<String> sqlStringsList,Map<Integer, String> resultListMap) throws Exception
	{
		// 执行构建的sql集合
		if (sqlStringsList.size()>0)
		{
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url, username, password);
			
			Statement stmt = conn.createStatement();
			
			for(int i=0;i<sqlStringsList.size();i++)
			{
				try {
					
					// 判断是否执行成功，如果执行失败写入返回值
					int iscomplete=stmt.executeUpdate(sqlStringsList.get(i));
					
					if (iscomplete==0)
					{
						resultListMap.put(i+1, sqlStringsList.get(i));
					}
					
				} catch (Exception e) {
					
					resultListMap.put(i+1, sqlStringsList.get(i));
					
				}
			}
		}
	}
	
	


}
