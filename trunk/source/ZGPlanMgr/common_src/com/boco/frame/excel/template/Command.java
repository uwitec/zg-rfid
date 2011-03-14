package com.boco.frame.excel.template;

import java.util.List;

/**
 * Command类
 * @author xh
 * 说明：用于具体定义导入的小节
 *      对应Template中的command小节
 */
public class Command {
	
	/** 
	 * 属性
	 * 
	 */
	private String id;
	
	// 对应的sheet页的name
	private String sheetName;
	
	// 发生错误时返回错误信息
	private String errInfo;
	
	// 列描述，对应cols
	private ColCollection cols;
	
	// sql列表，对应sqls
	private SqlCollection sqls;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public ColCollection getCols() {
		return cols;
	}

	public void setCols(ColCollection cols) {
		this.cols = cols;
	}

	public SqlCollection getSqls() {
		return sqls;
	}

	public void setSqls(SqlCollection sql) {
		this.sqls = sql;
	}
	
	
	/*
	 * 方法
	 * */
	public Command()
	{}
}
