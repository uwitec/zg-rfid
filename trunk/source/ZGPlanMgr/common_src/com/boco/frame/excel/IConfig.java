package com.boco.frame.excel;

import org.dom4j.DocumentException;

import com.boco.frame.excel.template.TemplateCollection;

/**
 * IConfig接口
 * @author xh
 * 说明：用于读取配置文件的接口
 */
public interface IConfig {
	TemplateCollection readConfig() throws DocumentException;
	
	void writeConfig(TemplateCollection templates);
}
