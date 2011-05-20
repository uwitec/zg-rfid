/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.service;

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

import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.dao.ZgTstoragebomExDao;
import com.boco.zg.storage.model.ZgTstoragebomEx;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTstoragebomExBo extends ZgTstoragebomBo{
	private ZgTstoragebomExDao zgTstoragebomExDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTstoragebomExDao(ZgTstoragebomExDao zgTstoragebomExDao) {
		this.zgTstoragebomExDao = zgTstoragebomExDao;
	}
	
	public EntityDao getEntityDao() {
		return this.zgTstoragebomExDao;
	}

	
	/**
	 * 根据入库单编号，查找它的所进行出库或入库的半成品列表
	 * @param id 入库或出库单编号
	 * @param productType CE:预焊  DE:预装
	 * @param type 1:入库
	 * @return
	 */
	public List<ZgTstoragebomEx>  findBomDEByStorageId(String id,String productType,String type) {
		return zgTstoragebomExDao.findBomDEByStorageId(id,productType,type);
		
	}

	/**
	 * 根据生产线和订单编码查找半成品列表
	 * @param storageId
	 * @param orderId
	 * @param arbpl
	 * @param productType
	 * @return
	 */
	public List<ZgTstoragebomEx> findBomlListByArbplOrderId(String storageId,
			String orderId, String arbpl, String productType) {
		return null;
	}

	/**
	 * 根据出库单编号，查找它的所进行出库或入库的半成品列表
	 * @param id 入库或出库单编号
	 * @param productType CE:预焊  DE:预装
	 * @param type 2:出库
	 * @return
	 */
	public List<ZgTstoragebomEx> findBomDEByStorageId1(String id,
			String productType, String type) {
		return zgTstoragebomExDao.findBomDEByStorageId1(id,productType,type);

	}



}
