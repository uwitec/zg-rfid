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

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderPlanGroupBo extends BaseManager<ZgTorderPlanGroup,java.lang.String>{
	private ZgTorderPlanGroupDao zgTorderPlanGroupDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanGroupDao(ZgTorderPlanGroupDao dao) {
		this.zgTorderPlanGroupDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanGroupDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderPlanGroupDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderPlanGroupDao.deleteById(id);
	}
	
	public List<ZgTorderPlanGroup> findByRequest(PageRequest pageRequest) {
		return zgTorderPlanGroupDao.findByRequest(pageRequest);
	}
	
	/**
	 * isCurDay 判断日期是否是当前日期，如果 0　是则查询当前日期及当前日期之后有特殊领料的订单　否则只查询当前日期的数据
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderPlanGroup> findGroupByList(PageRequest<Map> pageRequest) {
		String isCurDay= pageRequest.getFilters().get("isCurDay")==null?"0":pageRequest.getFilters().get("isCurDay").toString();
		
		//临时添加的查看之前领料的功能
		String viewDate=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "viewDate","");
		
		if("0".equals(isCurDay)&&StringHelper.isEmpty(viewDate)){
			return zgTorderPlanGroupDao.findGroupByList(pageRequest);
		}else {
			return zgTorderPlanGroupDao.findGroupByList1(pageRequest);
		}
		
	}
	public List<ZgTorderPlanGroup> findGroupByListForPsbhChange(
			PageRequest<Map> pageRequest) {
		String isCurDay= pageRequest.getFilters().get("isCurDay")==null?"0":pageRequest.getFilters().get("isCurDay").toString();
		if("0".equals(isCurDay)){
			return zgTorderPlanGroupDao.findGroupByList(pageRequest);
		}else {
			return zgTorderPlanGroupDao.findGroupByList1(pageRequest);
		}
	}
}
