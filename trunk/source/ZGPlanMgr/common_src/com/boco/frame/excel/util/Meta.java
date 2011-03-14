package com.boco.frame.excel.util;

import java.util.*;
//表头，没个行都有其的引用
public class Meta {
	private Map<String,Column> columns;
	private Map<Integer,String> index$name;

	public Meta(){
		columns=new HashMap<String,Column>();
		index$name=new HashMap<Integer,String>();
	}

	public Column get(String name){
		return columns.get(name);
	}
	public Column get(int index){
		return columns.get(index$name.get(index));
	}

	public void addColumn(int index,Column col){
		this.index$name.put(index, col.getName());
		this.columns.put(col.getName(), col);
	}

	public int getCount(){
		return columns.size();
	}
}
