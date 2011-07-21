/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	/**
	 * @param taskId
	 * @param idnrk
	 * @param aufnr
	 * @param posnr
	 */
	public ZgTorderTaskbom getTaskbomByTaskIdAufnrIdnrkPosnr(String taskId, String idnrk,String aufnr, String posnr) {
		Map paramsMap=new HashMap<String , Object>();
		paramsMap.put("taskId", taskId);
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("posnr", posnr);
		return zgTorderTaskbomDao.getTaskbomByTaskIdAufnrIdnrkPosnr(paramsMap);
	}


	/**
	 * 根据taskID将他的物料全部设置需求数量为0
	 * @param taskId
	 */
	public void updateTaskBomMengeTO0ByTaskId(String taskId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskId", taskId);
		zgTorderTaskbomDao.updateTaskBomMengeTO0ByTaskId(paramsMap);
	}
	
	/**
	 * 根据taskID将他的物料全部设置需求数量为0
	 * @param taskId
	 */
	public void delTaskBomTaskId(String taskId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskId", taskId);
		zgTorderTaskbomDao.delTaskBomTaskId(paramsMap);
	}
	/**
	 * @param idnrk
	 * @param aufnr
	 * @param posnr
	 * @return
	 */
	public List<ZgTorderTaskbom> getTaskbomAufnrIdnrkPosnr(String idnrk,
			String aufnr, String posnr) {
		Map paramsMap=new HashMap<String , Object>();
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("posnr", posnr);
		return zgTorderTaskbomDao.getTaskbomAufnrIdnrkPosnr(paramsMap);
	}
	/**
	 * @param bomId
	 */
	public void deleteTaskBomByOrderBomId(String bomId) {
		Map paramsMap=new HashMap<String , Object>();
		paramsMap.put("bomId", bomId);
		zgTorderTaskbomDao.deleteTaskBomByOrderBomId(paramsMap);
	}
}
