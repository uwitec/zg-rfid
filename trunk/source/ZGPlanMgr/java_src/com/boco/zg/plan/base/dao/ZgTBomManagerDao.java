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

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.directwebremoting.dwrp.Batch;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTbackBom;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.util.Constants;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTBomManagerDao extends BaseIbatisDao<ZgTorderPlan,java.lang.String>{
	public Class getEntityClass() {
		return ZgTbackBom.class;
	}
	
	public void saveOrUpdate(ZgTorderPlan entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	/**
	 * 查询换料列表
	 * @param pageRequest
	 * @return
	 */
	public Page pageOrderPlanForChange(PageRequest<Map> pageRequest) {
		return pageQuery("ZgTBomManager.pageOrderPlanForChange","ZgTBomManager.pageOrderPlanForChange_count",pageRequest);
	}
	
	public Page pageOrderPlanForChange1(PageRequest<Map> pageRequest) {
		return pageQuery("ZgTBomManager.pageOrderPlanForChange1","ZgTBomManager.pageOrderPlanForChange1_count",pageRequest);
	}
	
	public Page pageOrderPlanForChange2(PageRequest<Map> pageRequest) {
		return pageQuery("ZgTBomManager.pageOrderPlanForChange2","ZgTBomManager.pageOrderPlanForChange2_count",pageRequest);
	}

	/**
	 * 根据单据编号查找其bom列表
	 * @param id:
	 * @return
	 */
	public List<Map> findBomListByPlanID(String str) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("cuid", str);
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findBomListByPlanID",params);
	}
	/**
	 * 根据领料计划id查找历史审核记录
	 * @param id str 代表orderPlanId
	 * @return
	 */
	public List<Map> findqueryHistoryPlanID(String str) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("cuid", str);
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findqueryHistoryByPlanID",params);
	}

	/**
	 * 根据订单编号查找其BOM列表
	 * @param pageRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> findBomlListByOrderId(PageRequest<Map> pageRequest) {
		String planType=pageRequest.getFilters().get("planType").toString();
//		if(Constants.OrderPlanType.RENEW.value().equals(planType)){
//			return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findBomlListByOrderId1",pageRequest.getFilters());
//		}else {
			return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findBomlListByOrderId",pageRequest.getFilters());
//		}
		
	}
	
	
   public String getRoleCuidByUserId(String userId){
	  Map params=new HashMap();
	  params.put("userId", userId);
	  List list=getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findRoleCuid", params);
	    
	  if(list.size() >= 1) {
			return list.get(0).toString();
		} else {
			return null;
		}
	}

	/**
	 * @param pageRequest
	 * @return
	 */
	public Page pageListBackBom(PageRequest<Map> pageRequest) {
		return pageQuery("ZgTBomManager.pageListBackBom","ZgTBomManager.pageListBackBom_count",pageRequest);

	}

	/**
	 * 获取登陆领料人负责的生产厂
	 * @param operatorId
	 * @return
	 */
	public List<Map> getPlanListByOperatorId(String operatorId) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("operatorId", operatorId);
		return getSqlMapClientTemplate().queryForList("ZgTBomManager.getPlanListByOperatorId", params);
	}

	/**
	 * 检查退料数量
	 * @param bom
	 */
	public boolean checkBackNum(ZgTorderbomEx bom) {
		Map bomMap=new HashMap();
		bomMap.put("orderPlanbomId", bom.getOrderPlanbomId());
		bomMap.put("backNum", bom.getBackNum());
		return getSqlMapClientTemplate().queryForList("ZgTBomManager.checkBackNum", bomMap).size()>0;
	}
}

