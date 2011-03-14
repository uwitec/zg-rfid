package com.boco.frame.excel.template;

import java.util.ArrayList;


/**
 * TemplateCollection类
 * @author xh
 * 说明：从配置文件中读取的配置集合，暂时存在，为扩展准备
 */
public class TemplateCollection extends ArrayList<Template> {

	public TemplateCollection()
	{
		// 默认构造函数
	}
	
	public Template getTemplateByID(String templateID)
	{
		Template result=null;
		
		for (Template temp : this) {
			
			if (temp.getId().equals(templateID))
			{
				result=temp;
			}
			
		}
		
		return result;
	}
	
	public Template getTemplateByName(String templateName) {
		
		Template result=null;
		
		for (Template temp : this) {
			if (temp.getName()==templateName)
			{
				result=temp;
			}
		}
		return result;
	}
}
