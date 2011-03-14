package com.boco.frame.meta.dao;

import java.math.BigDecimal;
import java.util.Map;

public class IbatisDAOHelper {
	public static String getStringValue(Map map,String key){
		Object value = map.get(key);
		if(value==null){
			return null;
		}
		if(value instanceof String){
			return (String)value;
		}else{
			return value.toString();
		}
	}
	
	public static Long getLongValue(Map map,String key){
		Object value = map.get(key);
		if(value==null){
			return null;
		}
		if(value instanceof BigDecimal){
			return ((BigDecimal)value).longValue();
		}else{
			throw new RuntimeException("�޷������"+key+"ת��ΪLong���ͣ�");
		}
	}
	
	public static String getStringValue(Map map,String key,String defaultValue){
		Object value = map.get(key);
		if(value==null){
			return defaultValue;
		}
		if(value instanceof String){
			return (String)value;
		}else{
			return value.toString();
		}
	}
}
