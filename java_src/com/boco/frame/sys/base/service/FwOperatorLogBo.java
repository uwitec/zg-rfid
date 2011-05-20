/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.service;

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

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author system email:mysunshines@163.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class FwOperatorLogBo extends BaseManager<FwOperatorLog,java.lang.String>{
	private FwOperatorLogDao fwOperatorLogDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setFwOperatorLogDao(FwOperatorLogDao dao) {
		this.fwOperatorLogDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.fwOperatorLogDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return fwOperatorLogDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		fwOperatorLogDao.deleteById(id);
	}
	
	public List<FwOperatorLog> findByRequest(PageRequest pageRequest) {
		return fwOperatorLogDao.findByRequest(pageRequest);
	}
}
