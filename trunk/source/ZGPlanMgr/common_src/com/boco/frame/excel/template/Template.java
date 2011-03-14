package com.boco.frame.excel.template;

import java.util.List;



/**
 * Template类
 * @author xh
 * 说明：定义的导入模板对象，主要包含了导入的列定义、Sql定义等信息
 *      对应XML配置文件中的template小节
 */
public class Template {
	
	// template ID
	private String id;
	
	// template Name
	private String name;
	
	// template中默认的Command小节，通过commands 中的defaultcommand获得
	private String defaultCommand;
	
	// template中的command集合
	private List<Command> commands;

	


	public Template()
	{
		
	}

	// 属性小节
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultCommand() {
		return defaultCommand;
	}

	public void setDefaultCommand(String defaultCommand) {
		this.defaultCommand = defaultCommand;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}


	/*
	 * 方法小节
	 */ 
	
	// 获取Command配置节
	public Command getCommand(String commandID)
	{
		Command cmdTemp=null;
		
		for (Command cmdCommand : commands) {
			if(cmdCommand.getId()==commandID)
			{
				cmdTemp=cmdCommand;
			}
		}
		
		return cmdTemp;
	}

	
}
