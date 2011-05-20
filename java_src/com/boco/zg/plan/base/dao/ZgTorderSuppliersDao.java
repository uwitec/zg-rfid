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

import com.boco.zg.plan.base.model.ZgTorderSuppliers;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderSuppliersDao extends BaseIbatisDao<ZgTorderSuppliers,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderSuppliers.class;
	}
	
	public void saveOrUpdate(ZgTorderSuppliers entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderSuppliers entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderSuppliers.insertZG_T_ORDER_SUPPLIERS",entity);
	}
	
	@Override
	public void update(ZgTorderSuppliers entity){
		super.getSqlMapClientTemplate().insert("ZgTorderSuppliers.updateZG_T_ORDER_SUPPLIERS",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderSuppliers.deleteZG_T_ORDER_SUPPLIERS", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderSuppliers.BM_CLASS_ID);
		return pageQuery("ZgTorderSuppliers.pageSelect",pageRequest);
	}
	
	public List<ZgTorderSuppliers> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderSuppliers.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderSuppliers.pageSelect", parameterObject);
	}

	public List<ZgTcarbomSuppliers> getBomSuppliersListByAufnrIdnrk(
			Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderSuppliers.getCarBomSuppliersListByCarbomIds", paramsMap);
	}

}
