/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.materiel.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.frame.sys.base.model.ZgMaterielEx;
import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;


@Component
public class ZgMaterielExDao extends BaseIbatisDao<ZgMaterielEx,java.lang.String>{

	@Override
	public Class getEntityClass() {
		// TODO Auto-generated method stub
		return ZgMaterielEx.class;
	}

	
	public Page listMateridel(PageRequest<Map> pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgMateriel.BM_CLASS_ID);
		return pageQuery("ZgMaterielEx.pageSelect",pageRequest);
	}

	public void saveOrUpdate(ZgMaterielEx entity) {
		// TODO Auto-generated method stub
		
	}



}
