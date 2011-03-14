/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


import org.springframework.stereotype.Component;


@Component
public class FwOrganizationManagerDao extends BaseIbatisDao<FwOrganizationManager,java.lang.String>{
	public Class getEntityClass() {
		return FwOrganizationManager.class;
	}
	
	public void saveOrUpdate(FwOrganizationManager entity) {
		if(entity.getCuid() == null)
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("FwOrganizationManager.pageSelect",pageRequest);
	}

	public List<FwOrganizationManager> getManagerOrgListByOperatorSorftType(
			String operatorId, String sorftType) {
		Map parmsMap=new HashMap();
		parmsMap.put("operatorId", operatorId);
		parmsMap.put("sorftType", sorftType);
		return this.getSqlMapClientTemplate().queryForList("FwOrganizationManager.getManagerOrgListByOperatorSorftType",parmsMap);

	}
	

}
