/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;
import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


import org.springframework.stereotype.Component;


@Component
public class ZgTbomDao extends BaseIbatisDao<ZgTbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTbom.class;
	}
	
	public void saveOrUpdate(ZgTbom entity) {
		if(entity.getCuid() == null)
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("ZgTbom.pageSelect",pageRequest);
	}

	/** 批量领料计划查询bom组件供选择 */
	public Page findBomForSelectByPageRequest(PageRequest<Map> pageRequest) {
		return pageQuery("ZgTbomEx.pageSelect",pageRequest);
	}

	public List<ZgTbom> findByProperty(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("ZgTbom.findByCar",map);
	}

	public Number findNullCarInfoBom() {
		return (Number)getSqlMapClientTemplate().queryForObject("ZgTbomEx.findNullCarInfoBom");
	}


	/**
	 * 获取批量领料的bom供应商
	 * @param idnrks
	 * @return
	 */
	public List<ZgTcarbomSuppliers> getSuppliersListByIdnrks(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTbomEx.getSuppliersListByIdnrks", paramsMap);
	}

	/**
	 * 订单物料备料库
	 * @param pageRequest
	 * @return
	 */
	public Page findByPageRequest1(PageRequest<Map> pageRequest) {
		return  pageQuery("ZgTbom.pageSelect1","ZgTbom.count1",pageRequest);
	}
	
	/**
	 * 批量领料查询BOM
	 */
	public Page findBomsForBatchOut(PageRequest pageRequest) {
		return pageQuery("ZgTbom.findBomsForBatchOut","ZgTbom.countBomsForBatchOut",pageRequest);
	}
	/**
	 * 批量领料bom出备料库库，更新已出库数量
	 * @param orderPlanbomId  ZG_T_ORDER_PLANBOM cuid
	 * @param outNumNew  出库数量
	 */
	public void updateOutNumForBathchOut(String orderPlanbomId,Long outNumNew){
		Map paramsMap=new HashMap();
		paramsMap.put("orderPlanbomId", orderPlanbomId);
		paramsMap.put("outNumNew", outNumNew);
		getSqlMapClientTemplate().update("ZgTbom.updateOutNumForBathchOut", paramsMap);
	}

}
