package com.boco.frame.meta.service;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import com.boco.frame.meta.base.dao.TmdEnumevalueDao;
import com.boco.frame.meta.base.model.TmdEnumevalue;

@Component
public class TmdEnumeBo extends BaseManager<TmdEnumevalue,java.lang.String>{
	
	private TmdEnumevalueDao tmdEnumevalueDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTmdEnumevalueDao(TmdEnumevalueDao dao) {
		this.tmdEnumevalueDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.tmdEnumevalueDao;
	}

}
