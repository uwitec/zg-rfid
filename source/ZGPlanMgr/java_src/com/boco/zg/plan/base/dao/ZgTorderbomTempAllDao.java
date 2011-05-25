/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.apache.poi.poifs.storage.BATBlock;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTorderbomTempAll;



@Component
public class ZgTorderbomTempAllDao extends BaseIbatisDao<ZgTorderbomTempAll,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderbomTempAll.class;
	}
	
	public void saveOrUpdate(ZgTorderbomTempAll entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderbomTempAll entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderbomTempAll.insertZG_T_ORDERBOM_TEMP_ALL",entity);
	}
	
	@Override
	public void update(ZgTorderbomTempAll entity){
		super.getSqlMapClientTemplate().insert("ZgTorderbomTempAll.updateZG_T_ORDERBOM_TEMP_ALL",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderbomTempAll.deleteZG_T_ORDERBOM_TEMP_ALL", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbomTempAll.BM_CLASS_ID);
		return pageQuery("ZgTorderbomTempAll.pageSelect",pageRequest);
	}
	
	public List<ZgTorderbomTempAll> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbomTempAll.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderbomTempAll.pageSelect", parameterObject);
	}


	/**
	 * @param bomTemp
	 * @return
	 */
	public Object saveOrderBom(ZgTorderbomTempAll bomTemp) {
		return getSqlMapClientTemplate().insert("ZgTorderbomTempAll.insertZG_T_ORDERBOM",bomTemp);
	}

	/**
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 */
	public List<ZgTorderbomTempAll> getForAddBomList(int batchNo, String aufnr, String arbpl,
			String plant,int flag) {
		Map<String,Object> params=new HashMap<String , Object>();
		params.put("batchNo", batchNo);
		params.put("aufnr", aufnr);
		params.put("arbpl", arbpl);
		params.put("plant", plant);
		params.put("flag", flag);
		return getSqlMapClientTemplate().queryForList("ZgTorderbomTempAll.getForAddBomList",params);
		
	}

	/**
	 * 设置修改的物料标识为2
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @param flag
	 */
	public List<ZgTorderbomTempAll> getForEditBomList(int batchNo, String aufnr, String arbpl,String plant, int flag) {
		Map<String,Object> params=new HashMap<String , Object>();
		params.put("batchNo", batchNo);
		params.put("aufnr", aufnr);
		params.put("arbpl", arbpl);
		params.put("plant", plant);
		params.put("flag", flag);
		return getSqlMapClientTemplate().queryForList("ZgTorderbomTempAll.getForEditBomList",params);
	}

	/**
	 * @param batchNo
	 * @param aufnr
	 * @param arbpl
	 * @param plant
	 * @param flag
	 * @return
	 */
	public List<ZgTorderbomTempAll> getBomListByBatchNoAufnrArbplPlantFlag(int batchNo, String aufnr,
			String arbpl, String plant, int flag) {
		Map<String,Object> params=new HashMap<String , Object>();
		params.put("batchNo", batchNo);
		params.put("aufnr", aufnr);
		params.put("arbpl", arbpl);
		params.put("plant", plant);
		params.put("flag", flag);
		
		return getSqlMapClientTemplate().queryForList("ZgTorderbomTempAll.getBomListByBatchNoAufnrArbplPlantFlag",params);
	}

	/**
	 * @param tempBom
	 */
	public void updateOrderBom(ZgTorderbomTempAll tempBom) {
		getSqlMapClientTemplate().update("ZgTorderbomTempAll.updateZG_T_ORDERBOMBYIDNRKPOSNR",tempBom);
	}

}
