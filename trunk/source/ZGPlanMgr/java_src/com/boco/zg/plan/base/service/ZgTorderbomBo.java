/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderbomBo extends BaseManager<ZgTorderbom,java.lang.String>{
	private ZgTorderbomDao zgTorderbomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderbomDao(ZgTorderbomDao dao) {
		this.zgTorderbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderbomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderbomDao.deleteById(id);
	}
	
	public List<ZgTorderbom> findByRequest(PageRequest pageRequest) {
		return zgTorderbomDao.findByRequest(pageRequest);
	}
	
	/**
	 *  根据aufnr arbpl plant 对比BOM，获取在rfid中应该删除的BOM
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @return
	 */
	public List<ZgTorderbom> getBomListByBatchNoAufnrArbplPlant(int batchNo,
			String aufnr, String arbpl, String plant) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("batchNo", batchNo);
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("plant", plant);
		return zgTorderbomDao.getBomListByBatchNoAufnrArbplPlant(paramsMap);
	}
	
	/**
	 * @param orderId
	 * @param taskId
	 * @param sortf
	 * @param arbpl
	 * @param plant
	 * @param batchNo
	 */
	public void saveZgtorderTaskBomByOrderIdTaskId(String orderId,
			String taskId, String sortf, String arbpl, String plant, int batchNo) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("orderId", orderId);
		paramsMap.put("taskId", taskId);
		paramsMap.put("sortf", sortf);
		paramsMap.put("arbpl", arbpl);
		paramsMap.put("plant", plant);
		paramsMap.put("batchNo", batchNo);
		zgTorderbomDao.saveZgtorderTaskBomByOrderIdTaskId(paramsMap);
		
	}
	/**
	 * 更新orderbom的需求数量
	 * @param cuid
	 * @param psmng
	 */
	public void updateMengeByOrder(String cuid, Long psmng) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("orderId", cuid);
		paramsMap.put("psmng", psmng);
		zgTorderbomDao.updateMengeByOrder(paramsMap);
	}
}
