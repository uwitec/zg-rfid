package javacommon.base.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.dao.AutoComplateDao;
import javacommon.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;

@Component
public class AutoComplateBo implements IAutoComplateBo{
	
	private AutoComplateDao autoComplateDao;
	
	public void setAutoComplateDao(AutoComplateDao autoComplateDao) {
		this.autoComplateDao = autoComplateDao;
	}
	
	public String findByProperty(Map map) {
		JSONArray ja = new JSONArray();
		String beanName = StringHelper.makeWordFirstLetterUpperCaseNotStart(StringHelper.toUnderscoreName((String)map.get("bmClassId")));
		Object bean = ApplicationContextHolder.getBean(beanName+"Bo");
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNumber(1);
		pageRequest.setPageSize(50);
		Map paramMap = new HashMap();
		String value = (String)map.get("value");
		String sqlQueryString = "";
		if(!StringUtils.isBlank(value)) {
			String columns = (String)map.get("column");
			if(!StringUtils.isBlank(columns)) {
				String[] a = columns.split("[,]");
				for(int i = 0 ; i < a.length; i++) {
					if(i == 0)
						sqlQueryString = a[i] + " like '" + value+"'";
					else 
						sqlQueryString = " or "+a[i] + " like '" + value+"'";
				}
				paramMap.put("sqlQueryString", sqlQueryString);
			}
		}
		pageRequest.setFilters(paramMap);
		Method beanMethod = BeanUtils.findMethod(bean.getClass(), "findByRequest", pageRequest.getClass());
		List list = new ArrayList();
		try {
			list = (List)beanMethod.invoke(bean, pageRequest);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException("传入参数和调用参数不匹配！");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("方法调用 异常！");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("方法调用 异常！");
		}
		for(Object o: list) {
			String cuid = cn.org.rapid_framework.beanutils.BeanUtils.getProperty(o, "cuid");
			String label = cn.org.rapid_framework.beanutils.BeanUtils.getProperty(o, "labelValue");
			JSONObject jo = new JSONObject();
			jo.put("id", cuid);
			jo.put("label", label);
			jo.put("value", cuid);
			ja.add(jo);
		}
		return ja.toString();
	}
}
