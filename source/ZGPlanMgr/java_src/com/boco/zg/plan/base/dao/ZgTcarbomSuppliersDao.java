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

import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTcarbomSuppliersDao extends BaseIbatisDao<ZgTcarbomSuppliers,java.lang.String>{
	public Class getEntityClass() {
		return ZgTcarbomSuppliers.class;
	}
	
	public void saveOrUpdate(ZgTcarbomSuppliers entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTcarbomSuppliers entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarbomSuppliers.insertZG_T_CARBOM_SUPPLIERS",entity);
	}
	
	@Override
	public void update(ZgTcarbomSuppliers entity){
		super.getSqlMapClientTemplate().insert("ZgTcarbomSuppliers.updateZG_T_CARBOM_SUPPLIERS",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTcarbomSuppliers.deleteZG_T_CARBOM_SUPPLIERS", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarbomSuppliers.BM_CLASS_ID);
		return pageQuery("ZgTcarbomSuppliers.pageSelect",pageRequest);
	}
	
	public List<ZgTcarbomSuppliers> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarbomSuppliers.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTcarbomSuppliers.pageSelect", parameterObject);
	}

	public List<ZgTcarbomSuppliers> getCarBomSuppliersListByCarbomIds(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTcarbomSuppliers.getCarBomSuppliersListByCarbomIds", paramsMap);
	}

}
