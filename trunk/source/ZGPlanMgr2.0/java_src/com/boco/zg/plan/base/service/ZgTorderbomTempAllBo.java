/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.dao.ZgTorderbomTempAllDao;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.model.ZgTorderbomTempAll;


/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderbomTempAllBo extends BaseManager<ZgTorderbomTempAll,java.lang.String>{
	private ZgTorderbomTempAllDao zgTorderbomTempAllDao;
	
	private ZgTorderbomBo zgTorderbomBo;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderbomTempAllDao(ZgTorderbomTempAllDao dao) {
		this.zgTorderbomTempAllDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderbomTempAllDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderbomTempAllDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderbomTempAllDao.deleteById(id);
	}
	
	public List<ZgTorderbomTempAll> findByRequest(PageRequest pageRequest) {
		return zgTorderbomTempAllDao.findByRequest(pageRequest);
	}
	
	/**
	 * @param bomTemp
	 */
	public Object saveOrderBom(ZgTorderbomTempAll bomTemp) {
		return zgTorderbomTempAllDao.saveOrderBom(bomTemp);
		
	}
	/**
	 * 设置添加的物料标识为1
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 */
	public List<ZgTorderbomTempAll>  getForAddBomList(int batchNo, String aufnr, String arbpl,String plant,int flag) {
		return zgTorderbomTempAllDao.getForAddBomList(batchNo,aufnr,arbpl,plant,flag);
	}
	
	
	/**
	 * 设置修改的物料标识为2
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @param delete
	 */
	public List<ZgTorderbomTempAll>  getForEditBomList(int batchNo, String aufnr, String arbpl,
			String plant, int flag,String sortf) {
		return zgTorderbomTempAllDao.getForEditBomList(batchNo,aufnr,arbpl,plant,flag,sortf);
	}
	
	
	/**
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @return
	 */
	public List<ZgTorderbomTempAll> getBomListByBatchNoAufnrArbplPlantFlag(int batchNo, String aufnr,
			String arbpl, String plant,int flag) {
		return zgTorderbomTempAllDao.getBomListByBatchNoAufnrArbplPlantFlag(batchNo,aufnr,arbpl,plant,flag);
	}
	
	
	/**
	 * 1.更新orderbom
	 * 2、更新 taskbom
	 * 3、更新 planbom
	 * @param taskId
	 * @param tempBom
	 */
	public void updateOrderBom(ZgTorderbomTempAll tempBom) {
		zgTorderbomTempAllDao.updateOrderBom(tempBom);
	}
	public ZgTorderbomBo getZgTorderbomBo() {
		return zgTorderbomBo;
	}
	public void setZgTorderbomBo(ZgTorderbomBo zgTorderbomBo) {
		this.zgTorderbomBo = zgTorderbomBo;
	}
	/**
	 * @param batchNo
	 * @param aufnr
	 * @param edit
	 * @return
	 */
	public List<ZgTorderbomTempAll> getForPcEditBomList(int batchNo,
			String aufnr, int flag) {
		return zgTorderbomTempAllDao.getForPcEditBomList(batchNo,aufnr,flag);
	}
	/**
	 * @param tempBom
	 */
	public void updateOrderBomForPc(ZgTorderbomTempAll tempBom) {
		zgTorderbomTempAllDao.updateOrderBomForPc(tempBom);
		
	}
	
	/**
	 * @param batchNo
	 * @return
	 */
	public List<Map> getForBatchAddBomList(int batchNo) {
		return zgTorderbomTempAllDao.getForBatchAddBomList(batchNo);
	}
	
	/**
	 * @param batchNo
	 * @return
	 */
	public List<Map> getForBatchEditBomList(int batchNo) {
		return zgTorderbomTempAllDao.getForBatchEditBomList(batchNo);
	}
	/**
	 * @param bom
	 */
	public void saveTbom(Map bom) {
		zgTorderbomTempAllDao.saveTbom(bom);	
	}
	
	/**
	 * @param bom
	 */
	public void updateTbom(Map bom) {
		zgTorderbomTempAllDao.updateTbom(bom);	
	}
}
