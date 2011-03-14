package com.boco.frame.excel.util;

import java.util.*;
import com.boco.frame.excel.util.type.*;
//数据存储的行，每个数据单元在设置值的时候都有类型检查
public class Row {
	private Meta meta;
	private Object[] values;

	public Row(){}
	public Row(Meta meta){
		this.meta=meta;
		if(values==null){
			this.values=new Object[meta.getCount()];
		}
	}

	public void setMeta(Meta meta){
		this.meta=meta;
		if(values==null){
			this.values=new Object[meta.getCount()];
		}
	}

	public Meta getMeta(){
		return this.meta;
	}

	public Column getColumn(String name){
		return meta.get(name);
	}

	public Column getColumn(int index){
		return meta.get(index);
	}

	public void set(int index,Object value) throws DataException{

		if(!this.meta.get(index).isNULL()&&value==null){
			throw new DataException("the index[" + index + "]can not be null!");
		}
		if(value!=null&&TypeCompare.typeCompare(value, this.meta.get(index).getType())){
			this.values[index]=value;
		}else if(value==null){
			this.values[index]=null;
		}else{
			throw new DataException("the index["+ index+"]data["+value.toString()+"] type is wrong!");
		}
	}
	public void set(String name,Object value) throws DataException{
		this.set(this.meta.get(name).getIndex(), value);
	}

	public Object get(int index){
		return this.values[index];
	}
	public Object get(String name){
		return this.values[this.meta.get(name).getIndex()];
	}
}
