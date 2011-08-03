/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import java.util.List;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.dao.ZgTunitDao;
import com.boco.zg.plan.base.model.ZgTunit;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTunitBo extends BaseManager<ZgTunit,java.lang.String>{
	private ZgTunitDao zgTunitDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTunitDao(ZgTunitDao dao) {
		this.zgTunitDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTunitDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTunitDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTunitDao.deleteById(id);
	}
	
	public List<ZgTunit> findByRequest(PageRequest pageRequest) {
		return zgTunitDao.findByRequest(pageRequest);
	}
}
