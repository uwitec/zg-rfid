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

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTbackBomTemp;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTbackBomTempDao extends BaseIbatisDao<ZgTbackBomTemp,java.lang.String>{
	public Class getEntityClass() {
		return ZgTbackBomTemp.class;
	}
	
	public void saveOrUpdate(ZgTbackBomTemp entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTbackBomTemp entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTbackBomTemp.insertZG_T_BACK_BOM_TEMP",entity);
	}
	
	@Override
	public void update(ZgTbackBomTemp entity){
		super.getSqlMapClientTemplate().insert("ZgTbackBomTemp.updateZG_T_BACK_BOM_TEMP",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTbackBomTemp.deleteZG_T_BACK_BOM_TEMP", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTbackBomTemp.BM_CLASS_ID);
		return pageQuery("ZgTbackBomTemp.pageSelect",pageRequest);
	}
	
	public List<ZgTbackBomTemp> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTbackBomTemp.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTbackBomTemp.pageSelect", parameterObject);
	}

}
