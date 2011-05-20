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

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderBo extends BaseManager<ZgTorder,java.lang.String>{
	private ZgTorderDao zgTorderDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderDao(ZgTorderDao dao) {
		this.zgTorderDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderDao.deleteById(id);
	}
	
	public List<ZgTorder> findByRequest(PageRequest pageRequest) {
		return zgTorderDao.findByRequest(pageRequest);
	}
	/**
	 * @param pageRequest
	 * @return
	 */
	public Page findByPageRequest1(PageRequest<Map> pr) {
		return zgTorderDao.findByPageRequest1(pr);
	}
}
