package com.boco.frame.excel.xlsimp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.boco.frame.excel.IConfig;
import com.boco.frame.excel.IDataReader;
import com.boco.frame.excel.template.Col;
import com.boco.frame.excel.template.Command;
import com.boco.frame.excel.template.Template;
import com.boco.frame.excel.util.Column;
import com.boco.frame.excel.util.DataSet;
import com.boco.frame.excel.util.Meta;
import com.boco.frame.excel.util.Row;
import com.boco.frame.excel.util.Table;


public class OracleReader implements IDataReader {

	
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
	
	public OracleReader()
	{}
	
	public DataSet readData(Template tm,Object obj)
	{
		// 定义用于
		DataSet dSet=new DataSet();
		
		try {
			
			
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url, username, password);
			
			Statement cmd=conn.createStatement();
			
			List<Command> commands=tm.getCommands();
			
			for (Command command : commands) {
				
				Table tbTable=this.createTable(command);
				
				// 获取sql
				String sqlString="";
				
				// 获取外部传入的参数
				Map<String, String> params=new HashMap<String, String>();
				if (obj!=null)
				{
					params=(Map<String, String>)obj;
				}
				
				sqlString=command.getSqls().getDefaultSqlContent().creatSql(params,null);
				
				System.out.println(sqlString);
				try {
					ResultSet rs;      
				    rs = cmd.executeQuery(sqlString);   
				    while (rs.next()) {   
				    	
				    	Row r=tbTable.newRow();
				    	
				        for (Col c : command.getCols()) {
							r.set(r.getColumn(c.getName()).getIndex(), rs.getString(c.getId())==null?"":rs.getString(c.getId()));
						}
				        
				        tbTable.addRow(r);
				    }
				    
				    rs.close();
				} catch (Exception e) {
					// TODO: handle exception
					Log log=LogFactory.getLog(OracleReader.class);
					e.printStackTrace();
					log.error(e);
				}
			    
				dSet.add(tbTable);
			}

		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReadExcelError" + e);
		}
		
		return dSet;
	}
	
	// 创建Table的Header
	protected Table createTable(Command cmd) {
		
		Table tb=new Table();
		
		tb.setName(cmd.getSheetName());
		
		tb.setMeta(new Meta());
		
		// 按照顺序填充table，列名使用中文名
		for(int i=0;i<cmd.getCols().size();i++)
		{
			Column column=new Column();
			
			column.setName(cmd.getCols().get(i).getName());
			
			// 设置列的属性，这里都定义为String
			column.setType(String.class.getName());

			// 设置索引号，因为Map中没有索引，所以需要进行增加
			column.setIndex(i);
			
			tb.getMeta().addColumn(i, column);
		}
		
		return tb;
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
