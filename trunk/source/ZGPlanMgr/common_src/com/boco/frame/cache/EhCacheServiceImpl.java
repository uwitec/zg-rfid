package com.boco.frame.cache;

/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2006 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 */
/**
 * Revision Information:
 * 
 *@version 1.0 2009-5-12 release(zhangyinghui.Bright)
 */

import java.util.List;
import java.util.regex.Pattern;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EhCacheServiceImpl implements ICacheService {

	private Ehcache cache;
	private long time;

	public void putElement(String key, Object value) {
		Element element = new Element(key, value);
		this.cache.put(element);
	}

	public void removeAll() {
		this.cache.removeAll();
	}

	public void removeElement(String key) {
		this.cache.remove(key);
	}

	public Object getValueByKey(String key) {
		return this.cache.get(key);
	}

	public String assemblyKey(String[] values) {
		if ((values != null) && (values.length > 0)) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < values.length; ++i) {
				buffer.append(values[i]);
				if (i + 1 < values.length)
					buffer.append(".");
			}

			return this.time + "." + buffer.toString();
		}
		return "";
	}

	public void removeElementByReg(Pattern pattern) {
		List<String> keys = this.cache.getKeys();
		for (String key : keys) {
			if (pattern.matcher(key).find()) {
				this.cache.remove(key);
			}
		}
	}

	public void setCache(Ehcache cache) {
		this.cache = cache;
	}

	public boolean isExistKey(String key) {
		return ((this.cache.isKeyInCache(key)) && (this.cache.get(key) != null));
	}

	public Object getValue(String key) {
		Object value = this.cache.get(key);
		if (value == null)
			return null;

		return ((Element) value).getObjectValue();
	}

	public static void main(String[] args) {
		String[] paths = { "classpath*:spring/*.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		//ClassMetaDAO dao  = (ClassMetaDAO)ctx.getBean("ClassMetaDAO");
		//dao.getClassMeta("SWITCH_MSC");
	}

	public void setTemstap(long time) {
		this.time = time;
	}

	public long getTemstap() {
		return this.time;
	}

	public void fulsh() {
		this.cache.flush();
	}
}