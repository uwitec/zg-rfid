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

import com.boco.zg.storage.base.model.ZgTstorage;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTstorageExDao extends BaseIbatisDao<ZgTstorage,java.lang.String>{
	
	public Class getEntityClass() {
		return ZgTstorage.class;
	}
	
	public void saveOrUpdate(ZgTstorage entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}

	/**
	 * 更新表单状态
	 * @param zgTstorage
	 */
	public void updateState(ZgTstorage zgTstorage) {
		getSqlMapClientTemplate().update("ZgTstorageEx.updateZG_T_STORAGE_STATE", zgTstorage);
		
	}
	/**
	 * 获取某个半成品的所有bom
	 * @param orderId 订单号
	 * @param idnrk 组件号
	 * @return
	 */
	public List<Map> getBomListForProduct(String orderId, String idnrk) {
		Map params=new HashMap();
		params.put("orderId",orderId);
		params.put("idnrk",idnrk);
		return getSqlMapClientTemplate().queryForList("ZgTstoragebomEx.getBomListForProduct",params);
	}
	/**
	 * 更新组成这个半成品的bom的出库数量
	 * @param bomOrderbomId bom表cuid
	 * @param orderId 订单id
	 */
	public void updateBomOutNumForProductIn(String bomOrderbomId, String orderId,Double outNum) {
		Map params=new HashMap();
		params.put("orderId",orderId);
		params.put("bomOrderbomId",bomOrderbomId);
		params.put("outNum",outNum);
		getSqlMapClientTemplate().update("ZgTstoragebomEx.updateBomOutNumForProductIn",params);
	}

}
