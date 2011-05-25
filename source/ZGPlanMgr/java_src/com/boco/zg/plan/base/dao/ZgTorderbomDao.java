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

import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderbomDao extends BaseIbatisDao<ZgTorderbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderbom.class;
	}

	public void saveOrUpdate(ZgTorderbom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(ZgTorderbom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTorderbom.insertZG_T_ORDERBOM",entity);
	}
	
	@Override
	public void update(ZgTorderbom entity){
		super.getSqlMapClientTemplate().insert("ZgTorderbom.updateZG_T_ORDERBOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderbom.deleteZG_T_ORDERBOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbom.BM_CLASS_ID);
		return pageQuery("ZgTorderbom.pageSelect",pageRequest);
	}
	
	public List<ZgTorderbom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorderbom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTorderbom.pageSelect", parameterObject);
	}
 
	/**
	 * 获取已经提交的领料计划的bom组件
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderbomEx> findBomList(PageRequest<Map> pageRequest) {
		Map<String, Object> parameteMap=pageRequest.getFilters();
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findByProperty", parameteMap);
	}
	
	public String findOverTimeValue(){
		String sql="select * from zg_t_param t where t.paramname='OVER_TIME'";
		List<Map> list=findDynQuery(sql);
		return list.get(0).get("PARAMVALUE").toString();
	}
	public void saveOverTimeValue(String paramvalue){
		String sql="update zg_t_param t set paramvalue='"+paramvalue+"' where t.paramname='OVER_TIME'";
		findDynQuery(sql);
	}

	/**
	 * @param aufnr
	 * @param idnrk
	 * @param posnr
	 * @return
	 */
	public List<ZgTorderbom> getOrderBomByAufnrIdnrkPosnr(String aufnr,String idnrk, String posnr) {
		Map<String,Object> params=new HashMap<String , Object>();
		params.put("aufnr", aufnr);
		params.put("idnrk", idnrk);
		params.put("posnr", posnr);
		return getSqlMapClientTemplate().queryForList("ZgTorderbom.getOrderBomByAufnrIdnrkPosnr", params);
	}

	/**
	 * 根据aufnr arbpl plant 对比BOM，获取在rfid中应该删除的BOM
	 * @param paramsMap
	 * @return
	 */
	public List<ZgTorderbom> getBomListByBatchNoAufnrArbplPlant(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderbom.getBomListByBatchNoAufnrArbplPlant", paramsMap);
	}

	
	/**
	 * @param paramsMap
	 * @return
	 */
	public List<ZgTorderbom> getorderBomByTaskidSortfs(Map paramsMap) {
		return getSqlMapClientTemplate().queryForList("ZgTorderbom.getorderBomByTaskidSortfs",paramsMap);
	}

	/**
	 * @param paramsMap
	 */
	public void saveZgtorderTaskBomByOrderIdTaskId(Map paramsMap) {
		 getSqlMapClientTemplate().insert("ZgTorderbom.saveZgtorderTaskBomByOrderIdTaskId",paramsMap);
	}

}
