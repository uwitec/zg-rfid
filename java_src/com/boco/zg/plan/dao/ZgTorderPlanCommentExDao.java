package com.boco.zg.plan.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import javacommon.base.BaseIbatisDao;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.model.ZgTorderPlanEx;

@Component
public class ZgTorderPlanCommentExDao extends BaseIbatisDao<ZgTorderPlanEx,java.lang.String>{


	public Class getEntityClass() {
		return ZgTorderPlanComment.class;
	}

	public void saveOrUpdate(ZgTorderPlanEx entity) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 获取审核信息
	 * @param pr
	 * @return
	 */
	public Page findAuditingByPageRequest(PageRequest<Map> pr) {//state
		((Map)pr.getFilters()).put("BM_CLASS_ID", ZgTorderPlan.BM_CLASS_ID);
		return pageQuery("ZgTorderPlanComment.pageSelectAuditing",pr);
	}
	
	
}
