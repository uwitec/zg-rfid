/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.report.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

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

import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;
import com.boco.zg.plan.report.model.LgortReport;
import com.boco.zg.plan.report.model.LgortReportBatch;
import com.boco.zg.plan.report.model.LgortReportBatchStat;
import com.boco.zg.plan.report.model.LgortReportStat;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;


@Component
public class ReportDao extends BaseIbatisDao<Map,java.lang.String>{
	
	Class cl=LgortReport.class;

	@Override
	public Class getEntityClass() {
		return cl;
	}

	public void saveOrUpdate(Map entity) {
	}

	public Page lgortReport(PageRequest<Map> pageRequest) {
		cl=LgortReport.class;
		addPlantWhere(pageRequest);
		
		
		
		return pageQuery("LgortReport.pageSelect",pageRequest);
	}

	/**
	 * 添加生产厂查询
	 * @param pageRequest
	 */
	public void addPlantWhere(PageRequest<Map> pageRequest) {
		String orgId=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "orgId");
		String plantWhere="";
		if(!StringHelper.isEmpty(orgId)){
			Map paramsMap=new HashMap();
			paramsMap.put("orgId", orgId);
			//查询下面的生产厂
			List<Map> planList=this.getSqlMapClientTemplate().queryForList("FwOrganizationEx.getPlantListByOrgId",paramsMap);
			
			for(Map map:planList){
				plantWhere=plantWhere+"instr(zto.plant,'"+IbatisDAOHelper.getStringValue(map, "PLANTID")+"')>0  OR  ";
			}
			
			if(plantWhere.endsWith("OR  ")){
				plantWhere=plantWhere.substring(0,plantWhere.length()-4);
			}else {
				plantWhere="1=2";
			}
		
		}else {
			plantWhere="1=1";
		}
			
		pageRequest.getFilters().put("plantWhere", plantWhere);
	}
	
	public Page lgortReportStat(PageRequest<Map> pageRequest) {
		cl=LgortReportStat.class;
		addPlantWhere(pageRequest);
		return pageQuery("LgortReportStat.pageSelect",pageRequest);
	}

	/**
	 * 仓库出仓汇总（批量领料）
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReportBatchStat(PageRequest<Map> pageRequest) {
		cl=LgortReportBatchStat.class;
		return pageQuery("LgortReportBatchStat.pageSelect",pageRequest);
	}

	/**
	 * 仓库出仓明细（批量领料）
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReportBatch(PageRequest<Map> pageRequest) {
		cl=LgortReportBatch.class;
		return pageQuery("LgortReportBatch.pageSelect",pageRequest);
	}

}
