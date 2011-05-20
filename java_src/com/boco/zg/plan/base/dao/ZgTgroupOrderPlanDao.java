/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTgroupOrderPlan;


@Component
public class ZgTgroupOrderPlanDao extends BaseIbatisDao<ZgTgroupOrderPlan,java.lang.String>{
	public Class getEntityClass() {
		return ZgTgroupOrderPlan.class;
	}
	
	public void saveOrUpdate(ZgTgroupOrderPlan entity) {
		if(entity.getCuid() == null)
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("ZgTgroupOrderPlan.pageSelect",pageRequest);
	}

	/**
	 * 根据订单分组编号获取订单号
	 * @param groupIdList
	 * @return
	 */
	public List<Map> getListOrderNOByGroupIds(List<String> groupList) {
		Map params=new HashMap();
		params.put("groupIds", groupList);
		return getSqlMapClientTemplate().queryForList("ZgTgroupOrderPlan.findOrderNOListByGroupIds",params);
	}

	/**
	 * 根据领料计划分组编号删除该分组与领料计划的关系表记录
	 * @param groupId
	 */
	public int deleteGroupRelation(String groupId) {
		return getSqlMapClientTemplate().delete("ZgTgroupOrderPlan.deleteGroupRelationByGroupId",groupId);
	}
	

}
