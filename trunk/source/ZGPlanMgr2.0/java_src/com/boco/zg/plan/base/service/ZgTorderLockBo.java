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

import com.boco.zg.plan.base.dao.ZgTorderLockDao;
import com.boco.zg.plan.base.model.ZgTorderLock;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderLockBo extends BaseManager<ZgTorderLock,java.lang.String>{
	private ZgTorderLockDao zgTorderLockDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderLockDao(ZgTorderLockDao dao) {
		this.zgTorderLockDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderLockDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderLockDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderLockDao.deleteById(id);
	}
	
	public List<ZgTorderLock> findByRequest(PageRequest pageRequest) {
		return zgTorderLockDao.findByRequest(pageRequest);
	}
}
