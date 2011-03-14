/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
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
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;

/**
 * @author 翁钦
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTBomManagerBo extends BaseManager<ZgTorderPlan,java.lang.String>{
	private ZgTBomManagerDao zgTBomManagerDao;

	/* (non-Javadoc)
	 * @see javacommon.base.BaseManager#getEntityDao()
	 */
	@Override
	protected EntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public String getCuid() {
		return zgTBomManagerDao.queryBySql("SELECT 'A'||to_char(SYSTIMESTAMP,'yyyymmddhhmissff') cuid FROM DUAL").get(0).get("CUID").toString();
	}

	public void setZgTBomManagerDao(ZgTBomManagerDao zgTBomManagerDao) {
		this.zgTBomManagerDao = zgTBomManagerDao;
	}

	/**
	 * 查询换料列表
	 * @param pageRequest
	 * @return
	 */
	public Page pageOrderPlanForChange(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.pageOrderPlanForChange(pageRequest);
	}

	/**
	 * 根据单据编号查找其bom列表
	 * @param id
	 * @return
	 */
	public List<Map> findBomListByPlanID(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.findBomListByPlanID(pageRequest.getFilters());
	}

	/**
	 * 根据订单编号查找其BOM列表
	 * @param pageRequest
	 */
	public List<Map> findBomlListByOrderId(PageRequest<Map> pageRequest) {
		return zgTBomManagerDao.findBomlListByOrderId(pageRequest);
	}
	
}
