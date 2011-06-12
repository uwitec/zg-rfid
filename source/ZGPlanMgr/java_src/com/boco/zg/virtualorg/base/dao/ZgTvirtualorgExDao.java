package com.boco.zg.virtualorg.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.util.Constants;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorg;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorgEx;

import javacommon.base.BaseIbatisDao;

@Component
public class ZgTvirtualorgExDao extends BaseIbatisDao<ZgTvirtualorgEx,java.lang.String>{

	@Override
	public Class getEntityClass() {
		return ZgTvirtualorgEx.class;
	}

	public void saveOrUpdate(ZgTvirtualorgEx entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
		
	}
	
	public Page findByPageRequest(PageRequest pageRequest){
		return pageQuery("ZgTvirtualorgEx.pageSelect",pageRequest);
	}

	/**
	 * 获取领料人所在的领料组
	 * @param operatorId
	 * @return
	 */
	public List<ZgTvirtualorgEx> getVirtualOrgByOperatorId(String operatorId) {
		Map paramsMap=new HashMap();
		paramsMap.put("operatorId", operatorId);
		return getSqlMapClientTemplate().queryForList("ZgTvirtualorgEx.getVirtualOrgByOperatorId",paramsMap);
	}

	/**
	 * 获取领料员所能领取的生产厂
	 * @param operatorId
	 * @return
	 */
	public List<Map> getPlantListByOperatorId(String operatorId,String orderPlanType) {
		Map paramsMap=new HashMap();
		paramsMap.put("operatorId", operatorId);
		if(!Constants.OrderPlanType.CHANGE.value().equals(orderPlanType)&&!Constants.OrderPlanType.RENEW.value().equals(orderPlanType)&&!Constants.OrderPlanType.BACK.value().equals(orderPlanType)){
			paramsMap.put("planType", orderPlanType);
		}
		return getSqlMapClientTemplate().queryForList("ZgTvirtualorgEx.getPlantListByOperatorId",paramsMap);
	}

}
