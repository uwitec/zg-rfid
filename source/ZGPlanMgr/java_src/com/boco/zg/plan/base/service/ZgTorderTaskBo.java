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

import com.boco.zg.plan.base.dao.ZgTorderTaskDao;
import com.boco.zg.plan.base.model.ZgTorder;
import com.boco.zg.plan.base.model.ZgTorderTask;
import com.boco.zg.plan.base.model.ZgTorderTemp;


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

	/**
	 * 
	 * @param order
	 * @param flag
	 */
	public void updateZgtorderTaskByOrder(ZgTorder order,String flag) {
		ZgTorderTask task=new ZgTorderTask();
		task.setCuid(order.getTaskId());
		task.setPlant(order.getPlant());
		task.setArbpl(order.getArbpl());
		task.setOrderId(order.getCuid());
		task.setPxDate(order.getPxdat());
		task.setPoskey(order.getPoskey());
		task.setPmenge(order.getPmenge());
		task.setAufnr(order.getAufnr());
		task.setFlag(flag);
		update(task);
	}
	
	
	/**
	 * 
	 * @param zgTorderTemp
	 */
	public String saveZgtorderTask(ZgTorderTemp zgTorderTemp) {
		ZgTorderTask task=new ZgTorderTask();
		task.setPlant(zgTorderTemp.getPlant());
		task.setArbpl(zgTorderTemp.getArbpl());
		task.setOrderId(zgTorderTemp.getCuid());
		task.setPxDate(zgTorderTemp.getPxdat());
		task.setPoskey(zgTorderTemp.getPoskey());
		task.setPmenge(zgTorderTemp.getPmenge());
		task.setAufnr(zgTorderTemp.getAufnr());
		task.setFlag(ZgTorderTask.NUMULSTATE);
		return save(task).toString();
	}
	
	/**
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @return
	 */
	public ZgTorderTask findTaskByAufnrArbplPlant(String aufnr, String arbpl,String plant) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("plant", plant);
		return zgTorderTaskDao.findTaskByAufnrArbplPlant(paramsMap);
	}
	
}
