package com.boco.frame.excel.template;

import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class Dynamic {
	
	// 定义动态内容类型
	public enum DynamicType {
	    isNull, isNotNull, isEmpty, isNotEmpty;
	}

	
	private String id;
	
	private DynamicType dynamic;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DynamicType getDynamicType() {
		return dynamic;
	}

	public void setDynamicType(DynamicType d) {
		this.dynamic = d;
	}
	
	/*
	 * 自定义方法
	 * */
	
	public String createSql(Map<String, String> params)
	{
		String resultString="";
		
		if (params!=null)
		{
			try {
				Object obj=params.get(this.id);
				
				switch (this.getDynamicType()) {
				case isNull:
					if (obj==null)
					{
						resultString= createParams(params, this.value);
					}
					break;
					
				case isNotNull:
					if (obj!=null)
					{
						resultString= createParams(params, this.value);
					}
					break;
					
				case isEmpty:
					if (obj==null||obj.toString().equals(""))
					{
						resultString= createParams(params, this.value);
					}
					break;
					
				case isNotEmpty:
					if (obj!=null&&!obj.toString().equals(""))
					{
						resultString= createParams(params, this.value);
					}
					break;
	
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
			}
		}
		
		return resultString;
		
	}
	
	protected String createParams(Map<String, String> params,String sql) {
		
		
		for(Map.Entry<String, String> entry : params.entrySet()) 
		{ 
			sql= replaceString(sql, "#"+entry.getKey()+"#", entry.getValue());
		} 
		
		return sql;
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

