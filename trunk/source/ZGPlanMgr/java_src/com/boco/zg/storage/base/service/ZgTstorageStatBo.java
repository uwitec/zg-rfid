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
public class ZgTstorageStatBo extends BaseManager<ZgTstorageStat,java.lang.String>{
	private ZgTstorageStatDao zgTstorageStatDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTstorageStatDao(ZgTstorageStatDao dao) {
		this.zgTstorageStatDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTstorageStatDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTstorageStatDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTstorageStatDao.deleteById(id);
	}
	
	public List<ZgTstorageStat> findByRequest(PageRequest pageRequest) {
		return zgTstorageStatDao.findByRequest(pageRequest);
	}
	
	/**
	 * 根据仓库名和半成品编号查询库存记录
	 * @param orderBomId
	 * @param lgort
	 * @return
	 */
	public ZgTstorageStat getByOrderBomId(String orderBomId, String lgort) {
		return zgTstorageStatDao.getByOrderBomId(orderBomId,lgort);
	}
}
