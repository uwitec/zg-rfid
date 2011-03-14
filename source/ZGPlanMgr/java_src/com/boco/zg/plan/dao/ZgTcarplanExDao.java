/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

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

import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTcarplanExDao extends BaseIbatisDao<ZgTcarplan,java.lang.String>{
	public Class getEntityClass() {
		return ZgTcarplan.class;
	}

	public void saveOrUpdate(ZgTcarplan entity) {
	}

	public Object save1(ZgTcarplan zgTcarplan) {
		return getSqlMapClientTemplate().insert("ZgTcarplan.insertZG_T_CARPLAN1",zgTcarplan);
	}

	/**
	 * 查找某一计划分组下面的某一仓库下的所有bom
	 * @param groupId
	 * @param lgort
	 * @param operatorId
	 * @return
	 */
	public List<Map> getBomListByLgort(String groupId, String lgort,
			String operatorId) {
		Map paramsMap=new HashMap();
		paramsMap.put("groupId", groupId);
		paramsMap.put("lgort", lgort);
		paramsMap.put("operatorId", operatorId);
		return getSqlMapClientTemplate().queryForList("ZgTcarplanEx.getBomListByLgort",paramsMap);
	}
	
	

}
