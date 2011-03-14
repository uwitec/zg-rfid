/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.service;

import org.springframework.stereotype.Component;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class TmdRacktypeunitManager extends BaseManager<TmdRacktypeunit,java.lang.String>{
	private TmdRacktypeunitDao tmdRacktypeunitDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTmdRacktypeunitDao(TmdRacktypeunitDao dao) {
		this.tmdRacktypeunitDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.tmdRacktypeunitDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return tmdRacktypeunitDao.findByPageRequest(pr);
	}
	
}
