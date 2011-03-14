
package com.boco.zg.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;


@Component
public class ZgTstoragebomExDao extends BaseIbatisDao<ZgTstoragebomEx,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTstoragebomEx.class;
	}

	public void saveOrUpdate(ZgTstoragebomEx entity) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 根据出库或入库单编号，查找它的所进行出库或入库的半成品列表
	 * @param id 入库或出库单编号
	 * @param productType CE:预焊  DE:预装
	 * @param type 1:入库 2 出库
	 */
	public List<ZgTstoragebomEx> findBomDEByStorageId(String id, String productType,String type) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("cuid", id);
		params.put("productType", productType);
		params.put("type", type);
		return getSqlMapClientTemplate().queryForList("ZgTstoragebomEx.findByProperty1",params);
	}

	/**
	 * 根据出库单编号，查找它的所进行出库或入库的半成品列表
	 * @param id 入库或出库单编号
	 * @param productType CE:预焊  DE:预装
	 * @param type 2:出库
	 * @return
	 */
	public List<ZgTstoragebomEx> findBomDEByStorageId1(String id,
			String productType, String type) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("cuid", id);
		params.put("productType", productType);
		params.put("type", type);
		return getSqlMapClientTemplate().queryForList("ZgTstoragebomEx.findByProperty2",params);
	}
	

}
