/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.service;

import org.springframework.stereotype.Component;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.sys.base.dao.FwOrganizationManagerDao;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class FwOrganizationManagerBo extends BaseManager<FwOrganizationManager,java.lang.String>{
	private FwOrganizationManagerDao fwOrganizationManagerDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setFwOrganizationManagerDao(FwOrganizationManagerDao dao) {
		this.fwOrganizationManagerDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.fwOrganizationManagerDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return fwOrganizationManagerDao.findByPageRequest(pr);
	}
	
	public List<FwOrganizationManager> getManagerOrgListByOperatorSorftType(String operatorId,String sorftType){
		return fwOrganizationManagerDao.getManagerOrgListByOperatorSorftType(operatorId,sorftType);
	}
	
}
