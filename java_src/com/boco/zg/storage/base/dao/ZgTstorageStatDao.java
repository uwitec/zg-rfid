/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.storage.base.model.ZgTstorageStat;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTstorageStatDao extends BaseIbatisDao<ZgTstorageStat,java.lang.String>{
	public Class getEntityClass() {
		return ZgTstorageStat.class;
	}
	
	public void saveOrUpdate(ZgTstorageStat entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTstorageStat entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTstorageStat.insertZG_T_STORAGE_STAT",entity);
	}
	
	@Override
	public void update(ZgTstorageStat entity){
		super.getSqlMapClientTemplate().insert("ZgTstorageStat.updateZG_T_STORAGE_STAT",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTstorageStat.deleteZG_T_STORAGE_STAT", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTstorageStat.BM_CLASS_ID);
		return pageQuery("ZgTstorageStat.pageSelect",pageRequest);
	}
	
	public List<ZgTstorageStat> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTstorageStat.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTstorageStat.pageSelect", parameterObject);
	}


	/**
	 * 根据仓库名和半成品编号查询库存记录
	 * @param orderBomId
	 * @param lgort
	 * @return
	 */
	public ZgTstorageStat getByOrderBomId(String orderBomId, String lgort) {
		Map params=new HashMap();
		params.put("orderBomId", orderBomId);
		params.put("lgort", lgort);
		return (ZgTstorageStat) getSqlMapClientTemplate().queryForObject("ZgTstorageStat.getByOrderBomId", params);
	}

}
