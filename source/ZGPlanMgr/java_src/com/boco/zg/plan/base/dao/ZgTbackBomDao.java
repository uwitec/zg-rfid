/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

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
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.dwrp.Batch;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTbackBom;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTbackBomDao extends BaseIbatisDao<ZgTbackBom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTbackBom.class;
	}
	
	public void saveOrUpdate(ZgTbackBom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTbackBom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTbackBom.insertZG_T_BACK_BOM",entity);
	}
	
	@Override
	public void update(ZgTbackBom entity){
		super.getSqlMapClientTemplate().insert("ZgTbackBom.updateZG_T_BACK_BOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTbackBom.deleteZG_T_BACK_BOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTbackBom.BM_CLASS_ID);
		return pageQuery("ZgTbackBom.pageSelect",pageRequest);
	}
	
	public List<ZgTbackBom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTbackBom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTbackBom.pageSelect", parameterObject);
	}

	/**
	 * @param batchNo
	 * @return
	 */
	public Object insertBackBomLog(int batchNo) {
		Map paramsMap=new HashMap();
		paramsMap.put("batchNo", batchNo);
		return getSqlMapClientTemplate().update("ZgTbackBom.insertBackBomLog", paramsMap);
	}

}
