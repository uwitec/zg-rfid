package com.boco.frame.excel.template;


import java.util.Map;


/**
 * Column类
 * @author xh
 * 说明：导入模板的列信息，用于对应Excel中的字段
 *      对应XML配置文件中的Cols小节中的col节
 */
public class Col {
	
	// 英文列名，用于对应sql中的参数
	private String id;
	
	// 中文名，用于对应sheet中的列名
	private String name;
	
	// 列的类型
	private String type;
	
	// 列的说明
	private String desc;
	
	// 附加属性
	private Map<String, String> properties;

	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	
	// 方法小节
	public Col()
	{
		// 构造函数，暂时为空
	}
		
	
}
