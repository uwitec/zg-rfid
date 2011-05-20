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
public class ZgTcarbomSuppliersBo extends BaseManager<ZgTcarbomSuppliers,java.lang.String>{
	private ZgTcarbomSuppliersDao zgTcarbomSuppliersDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTcarbomSuppliersDao(ZgTcarbomSuppliersDao dao) {
		this.zgTcarbomSuppliersDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTcarbomSuppliersDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTcarbomSuppliersDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTcarbomSuppliersDao.deleteById(id);
	}
	
	public List<ZgTcarbomSuppliers> findByRequest(PageRequest pageRequest) {
		return zgTcarbomSuppliersDao.findByRequest(pageRequest);
	}
	
	public  List<ZgTcarbomSuppliers> getCarBomSuppliersListByCarbomIds(String carBomIds) {
		Map paramsMap=new HashMap();
		paramsMap.put("carBomIds", carBomIds);
		return zgTcarbomSuppliersDao.getCarBomSuppliersListByCarbomIds(paramsMap);
	}
}
