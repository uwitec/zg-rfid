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

import com.boco.zg.plan.base.dao.ZgTorderTaskbomDao;
import com.boco.zg.plan.base.model.ZgTorderTaskbom;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderTaskbomBo extends BaseManager<ZgTorderTaskbom,java.lang.String>{
	private ZgTorderTaskbomDao zgTorderTaskbomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderTaskbomDao(ZgTorderTaskbomDao dao) {
		this.zgTorderTaskbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderTaskbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderTaskbomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderTaskbomDao.deleteById(id);
	}
	
	public List<ZgTorderTaskbom> findByRequest(PageRequest pageRequest) {
		return zgTorderTaskbomDao.findByRequest(pageRequest);
	}
}
