/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTgroupOrderPlan;


@Component
public class ZgTgroupOrderPlanExDao extends BaseIbatisDao<ZgTgroupOrderPlan,java.lang.String>{
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
	

}
