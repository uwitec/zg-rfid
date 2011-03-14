package com.boco.frame.excel.util.type;

import java.util.*;

/*
/
/数据库字段类型和java类型的转换
 *ResultSet有类似的方法，这里自己做了个不全自己补上*
*/

public class DBTypeConverter {

	private static Map<String,String> typeImage;
        //键值对便于查询
	public static String getType(String dbType){
		if(typeImage==null){
			typeImage=new HashMap<String,String>();
			typeImage.put("VARCHAR", String.class.getName());
			typeImage.put("CHAR", String.class.getName());
			typeImage.put("TEXT", String.class.getName());
			typeImage.put("DATETIME", String.class.getName());
			typeImage.put("INT", Integer.class.getName());
                        typeImage.put("BIT",Boolean.class.getName());
		}
		return typeImage.get(dbType);
	}



}
