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

import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderPlanCommentDao extends BaseIbatisDao<ZgTorderPlanComment,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderPlanComment.class;
	}
	
	public void saveOrUpdate(ZgTorderPlanComment entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	

	
	@Override
	public void update(ZgTorderPlanComment entity){
		super.getSqlMapClientTemplate().insert("ZgTorderPlanComment.updateZG_T_ORDER_PLAN",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("ZgTorderPlanComment.deleteZG_T_ORDER_PLAN", id);
	}
	
	
	public void savePlanComment(ZgTorderPlanComment zgTorderPlanComment) {
		
		 super.getSqlMapClientTemplate().insert("ZgTorderPlanComment.insert_ZgTorderPlanComment_orderId_userId",zgTorderPlanComment).toString();
	}

	
}
