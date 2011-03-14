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

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanbomDao extends BaseIbatisDao<ZgTorderPlanbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderPlanbom.class;
	}
	
	public void saveOrUpdate(ZgTorderPlanbom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderPlanbom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderPlanbom.insertZG_T_ORDER_PLANBOM",entity);
	}
	
	@Override
	public void update(ZgTorderPlanbom entity){
		super.getSqlMapClientTemplate().insert("ZgTorderPlanbom.updateZG_T_ORDER_PLANBOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanbom.deleteZG_T_ORDER_PLANBOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanbom.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanbom.pageSelect",pageRequest);
	}
	
	public List<ZgTorderPlanbom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderPlanbom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.pageSelect", parameterObject);
	}

	/**
	 * 获取待退料的bom
	 * @param aufnr
	 * @param matnr
	 * @return
	 */
	public List<ZgTorderPlanbom> getWaitBackBomListByAufnrIdnrk(String aufnr,
			String matnr) {
		Map paramsMap = new HashMap();
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("idnrk", matnr);
		return getSqlMapClientTemplate().queryForList("ZgTorderPlanbom.getWaitBackBomListByAufnrIdnrk",paramsMap);
	}

}
