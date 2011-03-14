/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;

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

@Component
public class ZgTgroupOrderPlanBo extends BaseManager<ZgTgroupOrderPlan,java.lang.String>{
	private ZgTgroupOrderPlanDao zgTgroupOrderPlanDao;
	
	public EntityDao getEntityDao() {
		return this.zgTgroupOrderPlanDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTgroupOrderPlanDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTgroupOrderPlanDao.deleteById(id);
	}
	
	public void setZgTgroupOrderPlanDao(ZgTgroupOrderPlanDao zgTgroupOrderPlanDao) {
		this.zgTgroupOrderPlanDao = zgTgroupOrderPlanDao;
	}
}
