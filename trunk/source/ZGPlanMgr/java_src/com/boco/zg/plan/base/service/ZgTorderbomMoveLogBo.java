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

import com.boco.zg.plan.base.dao.ZgTorderbomMoveLogDao;
import com.boco.zg.plan.base.model.ZgTorderbomMoveLog;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderbomMoveLogBo extends BaseManager<ZgTorderbomMoveLog,java.lang.String>{
	private ZgTorderbomMoveLogDao zgTorderbomMoveLogDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderbomMoveLogDao(ZgTorderbomMoveLogDao dao) {
		this.zgTorderbomMoveLogDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderbomMoveLogDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderbomMoveLogDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderbomMoveLogDao.deleteById(id);
	}
	
	public List<ZgTorderbomMoveLog> findByRequest(PageRequest pageRequest) {
		return zgTorderbomMoveLogDao.findByRequest(pageRequest);
	}
}
