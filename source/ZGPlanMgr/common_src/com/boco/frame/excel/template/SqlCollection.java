package com.boco.frame.excel.template;

import java.util.ArrayList;


/**
 * Command类
 * @author xh
 * 说明：用于具体定义导入的小节
 *      对应Template中的sqls小节
 *      表示所有sql的集合，暂时存在，方便可用时扩展
 */
public class SqlCollection extends ArrayList<SqlContent> {

	private String defaultSql;
	
	
	public SqlCollection()
	{
		// 构造函数
	}


	public String getDefaultSql() {
		return defaultSql;
	}


	public void setDefaultSql(String defaultSql) {
		this.defaultSql = defaultSql;
	}
	
	public SqlContent getDefaultSqlContent() {
		
		SqlContent tContent=null;
		for (SqlContent sql : this) {
			if (sql.getId().toLowerCase().equals(defaultSql.toLowerCase()))
			{
				tContent=sql;
			}
		}
		
		return tContent;
		
	}
}
