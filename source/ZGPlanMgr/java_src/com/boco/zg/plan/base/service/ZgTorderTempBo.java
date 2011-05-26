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

import com.boco.zg.plan.base.dao.ZgTorderTempDao;
import com.boco.zg.plan.base.model.ZgTorderTemp;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderTempBo extends BaseManager<ZgTorderTemp,java.lang.String>{
	private ZgTorderTempDao zgTorderTempDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderTempDao(ZgTorderTempDao dao) {
		this.zgTorderTempDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderTempDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderTempDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderTempDao.deleteById(id);
	}
	
	public List<ZgTorderTemp> findByRequest(PageRequest pageRequest) {
		return zgTorderTempDao.findByRequest(pageRequest);
	}
	
	/**
	 * @param zgTorderTemp
	 */
	public String saveZgTOrder(ZgTorderTemp zgTorderTemp) {
		return zgTorderTempDao.saveZgTOrder(zgTorderTemp);
	}
	/**
	 * @param zgTorderTemp
	 * @return
	 */
	public int updateZgTOrder(ZgTorderTemp zgTorderTemp) {
		return zgTorderTempDao.updateZgTOrder(zgTorderTemp);
	}
	/**
	 * @param sapOrder
	 */
	public int updateZgTOrderForChange(ZgTorderTemp sapOrder) {
		return zgTorderTempDao.updateZgTOrderForChange(sapOrder);
		
	}
}
