/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.zg.bom.base.model.ZgCarInfo;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

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

import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.model.ZgTorderbomEx;
import com.boco.zg.util.Constants;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ZgTorderbomExDao extends BaseIbatisDao<ZgTorderPlanbomEx,java.lang.String>{
	public Class getEntityClass() {
		return ZgTorderbom.class;
	}
	

	public void saveOrUpdate(ZgTorderPlanbomEx entity) {
	}

	



	/**
	 * 更新半成品库存状态
	 * @param zgTorderbomEx
	 */
	public void updateOrderBomState(ZgTorderbom zgTorderbom) {
		getSqlMapClientTemplate().update("ZgTorderbomEx.updateZG_T_ORDERBOM_State",zgTorderbom);
	}

	
	
    /**
     * 根据生产线和订单编码查找入库半成品列表
     * @param params
     * @return
     */
	public List<ZgTorderbomEx> findBomlListByArbplOrderId(Map params) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findByProperty2",params);
	}

	/**
	 * 根据生产线，订单编码 ，仓库 查找可以出库的半成品
	 * @param filters
	 * @return
	 */
	public List<ZgTorderbomEx> findBomlListByArbplOrderId1(Map filters) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findByProperty1",filters);

	}


	/**
	 * 入库管理获取订单生产线列表
	 * @return
	 */
	public List<ZgTorderbomEx> findOrderArbplList(Map params) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findOrderArbplList",params);
	}


	/**
	 * 出库管理获取订单生产线列表
	 * @return
	 */
	public List<ZgTorderbomEx> findOrderArbplList1(Map params) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.findOrderArbplList1",params);

	}


	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public  List<ZgTorderbomEx> getBomListByProperty(PageRequest<Map> pageRequest) {
		String planType=IbatisDAOHelper.getStringValue(pageRequest.getFilters(),"planType");
		if(Constants.NEEDPLANSORTF.contains(planType)){
			return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByProperty",pageRequest.getFilters());
		}else {
			return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByProperty1",pageRequest.getFilters());
		}
		

	}

	/**
	 * 根据定单分组查找其bom组件(提前领料)
	 * @param pageRequest
	 * @return
	 */
	public List<ZgTorderbomEx> getBomListByPropertyAdvance(
			PageRequest<Map> pageRequest) {
		String planType=IbatisDAOHelper.getStringValue(pageRequest.getFilters(),"planType");
		if(Constants.NEEDPLANSORTF.contains(planType)){
			return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByPropertyAdvance",pageRequest.getFilters());
		}else {
			return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByPropertyAdvance1",pageRequest.getFilters());
		}
		
		
	}
	
	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public  List<Map> getBomListByGroupId(PageRequest<Map> pageRequest) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByGroupId",pageRequest.getFilters());

	}
	
	/**
	 * 根据定单分组查找其bom组件
	 * @param pageRequest
	 * @return
	 */
	public  List<Map> getBomListByGroupId1(PageRequest<Map> pageRequest) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByGroupId1",pageRequest.getFilters());

	}

	/**
	 * 根据定单分组查找其bom组件(提前领料)
	 * @param pageRequest
	 * @return
	 */
	public List<Map> getBomListByGroupIdAdvance(
			PageRequest<Map> pageRequest) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByGroupIdAdvance",pageRequest.getFilters());
	}
	

	/**
	 * 根据定单分组查找其bom组件(提前领料)
	 * @param pageRequest
	 * @return
	 */
	public List<Map> getBomListByGroupIdAdvance1(
			PageRequest<Map> pageRequest) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByGroupIdAdvance1",pageRequest.getFilters());
	}



	/**
	 * 根据订单编号获取其物料详细信息
	 * @param orderId1 源订单
	 * @param orderId2 目标订单
	 * @param both 
	 * @param isDel 订单是否删除状态 -1 为删除状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getBomListByOrderId(String orderTaskId1,String orderTaskId2,String both,String isDel) {
		Map paraMap=new HashMap();
		paraMap.put("orderTaskId1", orderTaskId1);
		paraMap.put("orderTaskId2", orderTaskId2);
		paraMap.put("both", both);
		paraMap.put("isDel", isDel);
		paraMap.put("sortf", Constants.NEEDPLANSORTF);
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByOrderId",paraMap);
	}
	
	/**
	 * 根据订单编号获取其物料详细信息
	 * @param orderId1 目标订单
	 * @param orderId2 源订单
	 * @param both
	 * @param isDel 订单是否删除状态 -1 为删除状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getBomListByOrderId1(String orderTaskId1,String orderTaskId2,String both,String isDel) {
		Map paraMap=new HashMap();
		paraMap.put("orderTaskId1", orderTaskId1);
		paraMap.put("orderTaskId2", orderTaskId2);
		paraMap.put("both", both);
		paraMap.put("isDel", isDel);
		paraMap.put("sortf", Constants.NEEDPLANSORTF);
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByOrderId1",paraMap);
	}

	/**
	 * 检查移单数量是否大于当前剩余的库存数量 订单是删除状态 
	 * @param zgTorderbom
	 * @return
	 */
	public boolean checkStorageNum(ZgTorderPlanbom planbom) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.checkStorageNum",planbom).size()>0;

	}

	/**
	 * 检查移单后目标订单的领取数量是否大于需求数量
	 * @param zgTorderbom
	 * @param targetOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checktargetCarNum(ZgTorderPlanbom planbom,String targetOrderTaskId) {
		Map paraMap=new HashMap();
		paraMap.put("targetOrderTaskId", targetOrderTaskId);
		paraMap.put("idnrk", planbom.getIdnrk());
		paraMap.put("moveNum", planbom.getMoveNum());
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.checktargetCarNum",paraMap).size()>0;

	}


	/**
	 * 改变源订单转单后的BOM数量
	 * @param zgTorderbom
	 * @return
	 */
	public boolean updateSourceBomNum(ZgTorderPlanbom planbom) {
		return 	getSqlMapClientTemplate().update("ZgTorderbomEx.updateSourceBomNum",planbom)==1;
	}


	/**
	 *  改变目标订单的 bom为数量
	 * @param zgTorderbom
	 * @param targetOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateTargetBomNum(ZgTorderbom zgTorderbom,
			String targetOrderId) {
		Map paraMap=new HashMap();
		paraMap.put("targerOrderId", targetOrderId);
		paraMap.put("idnrk", zgTorderbom.getIdnrk());
		paraMap.put("matkl", zgTorderbom.getMatkl());
		paraMap.put("posnr", zgTorderbom.getPosnr());
		paraMap.put("lgort", zgTorderbom.getLgort());
		paraMap.put("moveNum", zgTorderbom.getMoveNum());
		return 	getSqlMapClientTemplate().update("ZgTorderbomEx.updateTargetBomNum",paraMap)==1;
	}


	/**
	 * 检查移单数量是否大于当前剩余的库存数量 订单是删除状态 
	 * @param zgTorderbom
	 * @return
	 */
	public boolean checkStorageNum1(ZgTorderbom zgTorderbom) {
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.checkStorageNum1",zgTorderbom).size()>0;
	}


	/**
	 *  根据装车计划编号 获取其下面的 bom物料
	 * @param carPlanId
	 * @return
	 */
	public List<Map> getBomLIstCarPlanId(String carPlanId) {
		Map paraMap=new HashMap();
		paraMap.put("carPlanId", carPlanId);
		return  getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomLIstCarPlanId",paraMap);

	}
	/**
	 * 入库管理获取订单生产线列表
	 * @param pageRequest
	 * @return
	 */
	public Page findOrderArbplByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		return pageQuery("ZgTorderbomEx.findOrderArbplByPageRequest","ZgTorderbomEx.countByOrder",pageRequest);
		}
	
	/**
	 * 入库管理获取订单生产线列表
	 * @param pageRequest
	 * @return
	 */
	public Page findOrderArbplByPageRequest1(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", ZgTorder.BM_CLASS_ID);
		return pageQuery("ZgTorderbomEx.findOrderArbplByPageRequest1","ZgTorderbomEx.countByOrder1",pageRequest);
	}
/**
	 * 根据订单编号和物料编码物料列表 
	 * @param idnrk
	 * @param orderId
	 * @return
	 */
	public List<Map> getBomListByOrderIdAndIdnrk(String idnrk,ZgTorder order) {
		Map paramsMap=new HashMap();
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("orderId", order.getCuid());
//		if(Constants.OrderStatus.DEL.value().equals(order.getOrderState())){//订单是删除状态 则库存量可以全部使用
			return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByOrderIdAndIdnrk",paramsMap);
//		}else {//能使用的数量是completenum-carnum-moveNum-backNum
//			return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getBomListByOrderIdAndIdnrk1",paramsMap);
//		}
	}


	/**
	 * 获取移单目标BOM列表
	 * @param idnrk
	 * @param order
	 * @return
	 */
	public List<Map> getTargetBomListByOrderIdAndIdnrk(String idnrk,String orderTaskId) {
		Map paramsMap=new HashMap();
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("orderTaskId", orderTaskId);
		paramsMap.put("sortf", Constants.NEEDPLANSORTF);
		return getSqlMapClientTemplate().queryForList("ZgTorderbomEx.getTargetBomListByOrderIdAndIdnrk",paramsMap);

	}


	/** 更新物料的自有物料组编号
	 * 
	 * @param idnrk
	 * @param matkl
	 */
	public void setSelfMatkl(String idnrk, String matkl) {
		Map paramsMap=new HashMap();
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("matkl", matkl);
		getSqlMapClientTemplate().update("ZgTorderbomEx.updateOrderbomSelfMatkl",paramsMap);
		getSqlMapClientTemplate().update("ZgTorderbomEx.updateBomSelfMatkl",paramsMap);
		
	}


	/**
	 * @param aufnr
	 * @param idnrk
	 * @param posnr
	 * @param lgort
	 */
	public void setLgortByAufnrIdnrkPosnr(String aufnr, String idnrk,
			String posnr, String lgort) {
		Map paramsMap=new HashMap();
		paramsMap.put("idnrk", idnrk);
		paramsMap.put("aufnr", aufnr);
		paramsMap.put("posnr", posnr);
		paramsMap.put("lgort", lgort);
		getSqlMapClientTemplate().update("ZgTorderbomEx.updateLgortByAufnrIdnrkPosnr",paramsMap);
		
	}

}
