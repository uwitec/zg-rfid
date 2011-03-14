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

import org.directwebremoting.dwrp.Batch;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTbackBom;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
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

	/**
	 * 根据单据编号查找其bom列表
	 * @param id
	 * @return
	 */
	public List<Map> findBomListByPlanID(Map<String, String> params) {
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findBomListByPlanID");
	}

	/**
	 * 根据订单编号查找其BOM列表
	 * @param pageRequest
	 * @return
	 */
	public List<Map> findBomlListByOrderId(PageRequest<Map> pageRequest) {
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findBomlListByOrderId",pageRequest.getFilters());
	}
	
	


}
