/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.base.service;

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

import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTstoragebomBo extends BaseManager<ZgTstoragebom,java.lang.String>{
	private ZgTstoragebomDao zgTstoragebomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTstoragebomDao(ZgTstoragebomDao dao) {
		this.zgTstoragebomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTstoragebomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTstoragebomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTstoragebomDao.deleteById(id);
	}
	
	public List<ZgTstoragebom> findByRequest(PageRequest pageRequest) {
		return zgTstoragebomDao.findByRequest(pageRequest);
	}
	
	public int update1(ZgTstoragebom zgTstoragebom) {
		return zgTstoragebomDao.update1(zgTstoragebom);
		
	}
}
