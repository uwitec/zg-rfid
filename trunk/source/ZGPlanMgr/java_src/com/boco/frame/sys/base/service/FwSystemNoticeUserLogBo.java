/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.service;

import java.util.List;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.sys.base.dao.FwSystemNoticeUserLogDao;
import com.boco.frame.sys.base.model.FwSystemNoticeUserLog;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class FwSystemNoticeUserLogBo extends BaseManager<FwSystemNoticeUserLog,java.lang.String>{
	private FwSystemNoticeUserLogDao fwSystemNoticeUserLogDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setFwSystemNoticeUserLogDao(FwSystemNoticeUserLogDao dao) {
		this.fwSystemNoticeUserLogDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.fwSystemNoticeUserLogDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return fwSystemNoticeUserLogDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		fwSystemNoticeUserLogDao.deleteById(id);
	}
	
	public List<FwSystemNoticeUserLog> findByRequest(PageRequest pageRequest) {
		return fwSystemNoticeUserLogDao.findByRequest(pageRequest);
	}
}
