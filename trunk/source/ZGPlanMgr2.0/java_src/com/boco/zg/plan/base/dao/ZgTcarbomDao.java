/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

import java.text.SimpleDateFormat;
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


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;


@Component
public class ZgTcarbomDao extends BaseIbatisDao<ZgTcarbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTcarbom.class;
	}
	
	public void saveOrUpdate(ZgTcarbom entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object  save(ZgTcarbom entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("ZgTcarbom.insertZG_T_CARBOM",entity);
	}
	
	@Override
	public void update(ZgTcarbom entity){
		super.getSqlMapClientTemplate().insert("ZgTcarbom.updateZG_T_CARBOM",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTcarbom.deleteZG_T_CARBOM", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarbom.BM_CLASS_ID);
		return pageQuery("ZgTcarbom.pageSelect",pageRequest);
	}
	
	public List<ZgTcarbom> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTcarbom.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("ZgTcarbom.pageSelect", parameterObject);
	}
	
	
	/**
	 * wjz,跟新意见表，驳回和批准状态都有的
	 * 思路：把审核时间为空的orderPlanId的时间都跟新为本系统时间，还要加上意见内容
	 */
	public void updateRejectComment(ZgTorderPlanComment zgTorderPlanComment){
		super.getSqlMapClientTemplate().update("ZgTorderPlanComment.update_ZgTorderPlanComment", zgTorderPlanComment);
	}
	
	/**
	 * 以orderid来查出ZgTorderPlanComment信息
	 * @param orderplanid
	 * @return
	 */
	public List<ZgTorderPlanComment> selectZgTorderPlanCommentById(String orderplanid){
		
		List<ZgTorderPlanComment> zgTorderPlanCommentlist=super.getSqlMapClientTemplate().queryForList("ZgTorderPlanComment.select_by_orderid", orderplanid);
		
		return zgTorderPlanCommentlist;
	}
	
	/**
	 * 更改中文名
	 */
	public void changeNameForCh(ZgTorderPlanComment zgTorderPlanComment){
		List<FwOperator> fwOperatorList=super.getSqlMapClientTemplate().queryForList("FwOperatorEx.select_by_userid",zgTorderPlanComment.getUserid());
		if(fwOperatorList!=null&&fwOperatorList.size()>0){
			zgTorderPlanComment.setChname(fwOperatorList.get(0).getLabelCn());//把中文名赋值
		}
	}

	/**
	 * @param carbomIds
	 * @param flag
	 */
	public void updateSynFlagByCarBomIds(Map paramsMap) {
		getSqlMapClientTemplate().update("ZgTcarbom.updateSynFlagByCarBomIds",paramsMap);
	}

}
