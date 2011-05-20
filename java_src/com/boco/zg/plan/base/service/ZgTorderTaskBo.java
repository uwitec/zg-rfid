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

import com.boco.zg.plan.base.dao.ZgTorderTaskDao;
import com.boco.zg.plan.base.model.ZgTorderTask;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderTaskBo extends BaseManager<ZgTorderTask,java.lang.String>{
	private ZgTorderTaskDao zgTorderTaskDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderTaskDao(ZgTorderTaskDao dao) {
		this.zgTorderTaskDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderTaskDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderTaskDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderTaskDao.deleteById(id);
	}
	
	public List<ZgTorderTask> findByRequest(PageRequest pageRequest) {
		return zgTorderTaskDao.findByRequest(pageRequest);
	}
}
