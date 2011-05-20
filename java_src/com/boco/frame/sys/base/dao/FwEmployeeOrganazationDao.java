/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

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

import com.boco.frame.sys.base.model.FwEmployeeOrganazation;
import com.boco.frame.sys.base.model.FwOperatorRole;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;




@Component
public class FwEmployeeOrganazationDao extends BaseIbatisDao<FwEmployeeOrganazation,java.lang.String>{
	public Class getEntityClass() {
		return FwEmployeeOrganazation.class;
	}
	
	
	public void saveOrUpdate(FwEmployeeOrganazation entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(FwEmployeeOrganazation entity){
		FwEmployee fwEmployee=new FwEmployee();
		fwEmployee.setOrgId(entity.getOrgId());
		fwEmployee.setCuid(entity.getEmployeeId());
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		return super.getSqlMapClientTemplate().insert("FwEmployee.insertFW_EMPLOYEE2",fwEmployee);
	}
	
	@Override
	public void update(FwEmployeeOrganazation entity){
		super.getSqlMapClientTemplate().insert("FwEmployeeOrganazation.updateFW_EMPLOYEE_ORG",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("FwEmployeeOrganazation.deleteFW_EMPLOYEE_ORG", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwOperatorRole.BM_CLASS_ID);
		return pageQuery("FwEmployeeOrganazation.pageSelect",pageRequest);
	}
	
	public List<FwEmployeeOrganazation> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwOperatorRole.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("FwEmployeeOrganazation.pageSelect", parameterObject);
	}

	public void deleteByEmployeeId(String cuid) {
		super.getSqlMapClientTemplate().update("FwEmployee.updateFW_EMPLOYEE_For_Orgid_byEMPLOYEEID", cuid);
	}
	
		
	public List findByEmpoyeeId(String EmployeeId){
		return super.getSqlMapClientTemplate().queryForList("FwEmployeeOrganazation.findFW_EMPLOYEE_ORG_BY_EMPLOYEEID",EmployeeId);
		
	}


	public Object insert(FwEmployeeOrganazation fwEmployeeOrganazation) {
		return null;
	}


}
