package com.boco.frame.excel.xlsimp;




import java.io.*;
import java.util.*;


import org.dom4j.*;
import org.dom4j.io.*;

import com.boco.frame.excel.IConfig;
import com.boco.frame.excel.template.*;
import com.opensymphony.xwork2.inject.Context;



/**
 * XmlConfig类
 * @author xh
 * 说明：XML配置文件的IConfig接口实现
 */
public class XmlConfig implements IConfig {
	
	/*
	 * 属性
	 * */
	private String xmlFile;
	

	private TemplateCollection templates;
	
	public TemplateCollection getTemplates() {
		return templates;
	}

	public void setTemplates(TemplateCollection templates) {
		this.templates = templates;
	}
	

	
	/*
	 * 方法
	 * */
	
	public XmlConfig() 
	{
		this.xmlFile=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"export.xml";
	}
	
	// 读取配置文件，进行template对象化
	public TemplateCollection readConfig() throws DocumentException
	{
		
		
		
		// 如果已经读取，直接返回，最好配置为单例模式
		if (this.templates!=null){return this.templates;}
		
		SAXReader reader = new SAXReader();   
        Document document = reader.read(new File(xmlFile));
		
        // 获取根目录
        Element rootElm = document.getRootElement(); 

        // 获取templates目录
        Element node=rootElm;
        
        TemplateCollection templates=null;
        
        if (node!=null)
        {
        	templates=new TemplateCollection();
        	
        	List childList=node.elements("template");
    		
    		// 循环获取节点
    		for(Iterator it=childList.iterator();it.hasNext();){
    			
    			 Element child = (Element) it.next();
    			 
    			 templates.add(createTemplate(child));
    		}
        	
        }
        
		return templates;
	}
	
	// 创建template
	protected Template createTemplate(Element node) {
		
		Template t=new Template();
		
		// 获取id
		Attribute id=node.attribute("id");
		t.setId(id.getValue());
		
		// 获取name
		Attribute nameAttribute=node.attribute("name");
		t.setName(nameAttribute.getValue());
		
		// 获取defaultcommand
		Attribute commandAttribute=node.attribute("defaultCommand");
		t.setDefaultCommand(commandAttribute.getValue());
		
		// 获取所有的command
		List childList=node.elements("command");
		
		t.setCommands(new ArrayList<Command>());
		
		// 循环获取command节点
		for(Iterator it=childList.iterator();it.hasNext();){
			
			 Element child = (Element) it.next();
			 
			 t.getCommands().add(createCommand(child));
		}
		
		return t;
	}
	
	// 创建command
	protected Command createCommand(Element node) {
		
		Command c=new Command();
		
		// 获取id
		Attribute idAttribute=node.attribute("id");
		c.setId(idAttribute.getValue());
		
		// 获取sheetName
		Attribute sheetAttribute=node.attribute("sheetName");
		c.setSheetName(sheetAttribute.getValue());
		
		// 获取errInfo
		Attribute errAttribute=node.attribute("errInfo");
		c.setErrInfo(errAttribute.getValue());
		
		// create cols
		Element colElement=node.element("cols");
		c.setCols(createColumns(colElement));
		
		// create sqls
		Element sqlElement=node.element("sqls");
		c.setSqls(createSqlCollection(sqlElement));	
		
		// 获取cols
		
		
		return c;
	}
	
	// 创建 ColumnCollection
	protected ColCollection createColumns(Element node) {
		
		ColCollection cols=new ColCollection();
		
		List nodesList=node.elements("col");
		// 循环获取command节点
		for(Iterator it=nodesList.iterator();it.hasNext();){
			
			 Element child = (Element) it.next();
			 
			 Col col=new Col();
			 
			 // 获取id
			 col.setId(child.attribute("id").getValue());
			 // name
			 col.setName(child.attribute("name").getValue());
			 // datatype
			 col.setType(child.attribute("datatype").getValue());
			 // desc
			 col.setDesc(child.attribute("desc").getValue());
			 
			 
			 cols.add(col);
		}
		
		return cols;
	}
	
	// 创建SqlCollection
	protected SqlCollection createSqlCollection(Element node) {
		
		SqlCollection sqls=new SqlCollection();
		
		sqls.setDefaultSql(node.attributeValue("defaultsql"));
		
		List nodesList=node.elements("sql");
		// 循环获取command节点
		for(Iterator it=nodesList.iterator();it.hasNext();){
			
			 Element child = (Element) it.next();
			 
			 SqlContent sql=new SqlContent();
			 
			 // 获取id
			 sql.setId(child.attribute("id").getValue());
			 
			 // sql 待取
			 sql.setSqlContent(child.element("content").getStringValue());
			 
			 sql.setDynamicSqls(new ArrayList<Dynamic>());
			 
			 try {
				 Element dys=child.element("dynamic");
				 
				 for (Object dy : dys.elements()) {
					 
					 Element temp=(Element)dy;
					 
					 Dynamic mic=new Dynamic();
					 
					 if (temp.getName().toLowerCase().equals("isnull"))
					 {
						 mic.setDynamicType(Dynamic.DynamicType.isNull);
						 
						 mic.setId(temp.attribute("id").getValue());
						 
						 mic.setValue(temp.getStringValue());
						 
					 }
					 
					 else if (temp.getName().toLowerCase().equals("isnotnull"))
					 {
						 mic.setDynamicType(Dynamic.DynamicType.isNotNull);
						 
						 mic.setId(temp.attribute("id").getValue());
						 
						 mic.setValue(temp.getStringValue());
						 
					 }
					 
					 else if (temp.getName().toLowerCase().equals("isempty"))
					 {
						 mic.setDynamicType(Dynamic.DynamicType.isEmpty);
						 
						 mic.setId(temp.attribute("id").getValue());
						 
						 mic.setValue(temp.getStringValue());
						 
					 }
					 
					 else if (temp.getName().toLowerCase().equals("isnotempty"))
					 {
						 mic.setDynamicType(Dynamic.DynamicType.isNotEmpty);
						 
						 mic.setId(temp.attribute("id").getValue());
						 
						 mic.setValue(temp.getStringValue());
						 
					 }
					 
					 sql.getDynamicSqls().add(mic);
				 }
			 } catch (Exception e) {
				// TODO: handle exception
			 }
			 
			 
			 sqls.add(sql);
		}
		
		return sqls;
		
	}
	
	

	// 暂不实现写入的接口
	public void writeConfig(TemplateCollection templates) {
		return ;
	}

	

}
