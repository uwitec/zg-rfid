/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

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

import com.boco.frame.sys.base.model.FwOperatorRole;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class FwOperatorRoleDao extends BaseIbatisDao<FwOperatorRole,java.lang.String>{
	public Class getEntityClass() {
		return FwOperatorRole.class;
	}
	
	public void saveOrUpdate(FwOperatorRole entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(FwOperatorRole entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("FwOperatorRole.insertFW_OPERATOR_ROLE",entity);
	}
	
	@Override
	public void update(FwOperatorRole entity){
		super.getSqlMapClientTemplate().insert("FwOperatorRole.updateFW_OPERATOR_ROLE",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("FwOperatorRole.deleteFW_OPERATOR_ROLE", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwOperatorRole.BM_CLASS_ID);
		return pageQuery("FwOperatorRole.pageSelect",pageRequest);
	}
	
	public List<FwOperatorRole> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwOperatorRole.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("FwOperatorRole.pageSelect", parameterObject);
	}

	public void deleteByOperateId(String cuid) {
		super.getSqlMapClientTemplate().delete("FwOperatorRole.deleteFW_OPERATOR_ROLE_BY_OPERATOERID", cuid);
	}

}
