/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class CityBo extends BaseManager<City,java.lang.Long>{
	private CityDao cityDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCityDao(CityDao dao) {
		this.cityDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cityDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return cityDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.Long id) {
		cityDao.deleteById(id);
	}
	
	public List<City> findByRequest(PageRequest pageRequest) {
		return cityDao.findByRequest(pageRequest);
	}
}
