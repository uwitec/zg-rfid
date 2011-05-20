/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.service;

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

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;
import com.boco.frame.sys.dao.ZgBomCarDao;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class FwEmployeeBo extends BaseManager<FwEmployee,java.lang.String>{
	private FwEmployeeDao fwEmployeeDao;
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setFwEmployeeDao(FwEmployeeDao dao) {
		this.fwEmployeeDao = dao;
	}
	
	public EntityDao getEntityDao() {
		return this.fwEmployeeDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return fwEmployeeDao.findByPageRequest(pr);
	}
	
	
	
	public void removeById(java.lang.String id) {
		fwEmployeeDao.deleteById(id);
	}
	
	public List<FwEmployee> findByRequest(PageRequest pageRequest) {
		return fwEmployeeDao.findByRequest(pageRequest);
	}
	public List<FwOrganization> selectPartAllNameByOnePeople(String cuid) {
		return fwEmployeeDao.selectPartAllNameByOnePeople(cuid);
		
	}
	//用于判断该用户是否含有pagesize属性设置
	public List<Map> findFromPageParam(String userId){//这里的userid是zg_t_PAGEPARAM的属性，与fw_operator的cuid相连
		return fwEmployeeDao.findFromPageParam(userId);
	}
	//pagesize为空时 插入一个pagesize
	public void insertPageSizeValue(String userId){
		fwEmployeeDao.insertPageSizeValue(PageRequestFactory.DEFAULT_PAGE_SIZE, userId);
	}

	public void updateSelfInfo(FwEmployee fwEmployee) {
		fwEmployeeDao.updateSelfInfo(fwEmployee);
		
	}
}
