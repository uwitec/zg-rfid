package javacommon.base.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IAutoComplateBo;

@SuppressWarnings({ "unchecked", "serial" })
public class AutoComplateAction extends BaseStruts2Action{
	
	private IAutoComplateBo autoComplateBo;
	
	public void setAutoComplateBo(IAutoComplateBo autoComplateBo) {
		this.autoComplateBo = autoComplateBo;
	}

	public String findRelationData() {
		String value = this.getRequest().getParameter("term");
		String bmClassId = this.getRequest().getParameter("bmClassId");
		String column = this.getRequest().getParameter("column");
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8"); 
		try {
			PrintWriter out = this.getResponse().getWriter();
			Map map = new HashMap();
			map.put("bmClassId", bmClassId);
			map.put("column", column);
			map.put("value", value);
			String jsonString = autoComplateBo.findByProperty(map);
			out.print(jsonString);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return SUCCESS;
	}
}
