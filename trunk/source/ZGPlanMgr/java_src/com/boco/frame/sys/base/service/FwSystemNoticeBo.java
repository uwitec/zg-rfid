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

import com.boco.frame.sys.base.dao.FwSystemNoticeDao;
import com.boco.frame.sys.base.model.FwSystemNotice;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class FwSystemNoticeBo extends BaseManager<FwSystemNotice,java.lang.String>{
	private FwSystemNoticeDao fwSystemNoticeDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setFwSystemNoticeDao(FwSystemNoticeDao dao) {
		this.fwSystemNoticeDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.fwSystemNoticeDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return fwSystemNoticeDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		fwSystemNoticeDao.deleteById(id);
	}
	
	public List<FwSystemNotice> findByRequest(PageRequest pageRequest) {
		return fwSystemNoticeDao.findByRequest(pageRequest);
	}
	
	
	/**
	 * 根据用户编号获取其系统公告
	 * @param operatorId
	 */
	public List<FwSystemNotice> getListByOperatorId(String operatorId) {
		return fwSystemNoticeDao.getListByOperatorId(operatorId);
	}
}
