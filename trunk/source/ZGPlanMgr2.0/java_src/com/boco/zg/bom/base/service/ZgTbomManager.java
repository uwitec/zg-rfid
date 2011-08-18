/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.service;

import org.springframework.stereotype.Component;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;
import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTbomManager extends BaseManager<ZgTbom,java.lang.String>{
	private ZgTbomDao zgTbomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTbomDao(ZgTbomDao dao) {
		this.zgTbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTbomDao.findByPageRequest(pr);
	}
	
	/** 批量领料计划查询bom组件供选择 */
	public Page findBomForSelectByPageRequest(PageRequest<Map> pageRequest) {
		return zgTbomDao.findBomForSelectByPageRequest(pageRequest);
	}
	public List<ZgTbom> findByProperty(Map<String, Object> map) {
		return zgTbomDao.findByProperty(map);
	}
	/**
	 * 查看是否存在bom未设置车型信息
	 * @return
	 */
	public boolean findNullCarInfoBom(){
		boolean flag=false;
		int nullTotal=zgTbomDao.findNullCarInfoBom().intValue();
		if(nullTotal>0){
			flag=true;//存在未设置车型信息的bom
		}else{
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 获取批量领料的bom供应商
	 * @param idnrks
	 * @return
	 */
	public  List<ZgTcarbomSuppliers> getSuppliersListByIdnrks(String idnrks) {
		Map paramsMap=new HashMap();
		paramsMap.put("idnrks", idnrks);
		return zgTbomDao.getSuppliersListByIdnrks(paramsMap);
	}
	/**
	 * 订单物料备料库
	 * @param pageRequest
	 * @return
	 */
	public Page findByPageRequest1(PageRequest<Map> pageRequest) {
		return zgTbomDao.findByPageRequest1(pageRequest);
	}
	/**
	 * 查询备料库中的批量领料BOM
	 */
	public Page findBomsForBatchOut(PageRequest pageRequest){
		return zgTbomDao.findBomsForBatchOut(pageRequest);
	}
	/**
	 * 批量领料bom出备料库库，更新已出库数量
	 */
	public void updateOutNumForBathchOut(String orderPlanbomId,Double outNumNew){
		zgTbomDao.updateOutNumForBathchOut(orderPlanbomId,outNumNew);
	}
	
}
