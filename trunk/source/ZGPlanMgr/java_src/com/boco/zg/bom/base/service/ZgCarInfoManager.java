/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.service;

import org.springframework.stereotype.Component;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgCarInfoManager extends BaseManager<ZgCarInfo,java.lang.String>{
	private ZgCarInfoDao zgCarInfoDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgCarInfoDao(ZgCarInfoDao zgCarInfoDao) {
		this.zgCarInfoDao = zgCarInfoDao;
	}
	
	public EntityDao getEntityDao() {
		return this.zgCarInfoDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgCarInfoDao.findByPageRequest(pr);
	}
	
	public List<ZgCarInfo> findByProperty(ZgCarInfo zgCarInfo) {
		return zgCarInfoDao.findByProperty(zgCarInfo);
	}
	
	
}
