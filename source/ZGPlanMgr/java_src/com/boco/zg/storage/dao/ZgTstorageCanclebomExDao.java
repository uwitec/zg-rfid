/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.dao;

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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.storage.base.model.ZgTstorageCanclebom;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
@SuppressWarnings("unchecked")
public class ZgTstorageCanclebomExDao extends BaseIbatisDao<ZgTstorageCanclebomEx,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTstorageCanclebomEx.class;
	}
	

	public void saveOrUpdate(ZgTstorageCanclebomEx entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}


	/**
	 * 根据入库冲单编号，查找它的冲单半成品
	 * @param id 入库冲单编号
	 * @param productType CE:预焊  DE:预装
	 * @return
	 */
	public List<ZgTstorageCanclebomEx> findBomDEByStorageCancleId(String id,String productType) {
		Map params=new HashMap();
		params.put("cuid", id);
		params.put("productType", productType);
		return getSqlMapClientTemplate().queryForList("ZgTstorageCanclebomEx.pageSelect", params);
	}


	/**
	 * 根据冲单表编号，过滤查找半成品
	 * @param filters
	 * @return
	 */
	public List<ZgTstorageCanclebomEx> listByCancleId(Map params) {
		return getSqlMapClientTemplate().queryForList("ZgTstorageCanclebomEx.findByProperty", params);
	}


	public int update1(ZgTstorageCanclebom zgTstorageCanclebom) {
		return getSqlMapClientTemplate().update("ZgTstorageCanclebom.updateZG_T_STORAGE_CANCLEBOM",zgTstorageCanclebom);
	}

}
