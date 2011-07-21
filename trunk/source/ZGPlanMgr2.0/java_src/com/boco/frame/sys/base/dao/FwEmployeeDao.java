/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseIbatisDao;
import javacommon.base.dao.BaseDao;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganization;


@Component
public class FwEmployeeDao extends BaseIbatisDao<FwEmployee,java.lang.String>{
	public Class getEntityClass() {
		return FwEmployee.class;
	}
	
	public void saveOrUpdate(FwEmployee entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	@Override
	public Object save(FwEmployee entity){
		BeanUtils.setProperty(entity, "relatedBmClassId", entity.BM_CLASS_ID);
		super.getSqlMapClientTemplate().insert("FwEmployee.insertFW_OPERATOR",entity);
		return super.getSqlMapClientTemplate().insert("FwEmployee.insertFW_EMPLOYEE",entity);
	}
	
	@Override
	public void update(FwEmployee entity){
		super.getSqlMapClientTemplate().insert("FwEmployee.updateFW_OPERATOR",entity);
		super.getSqlMapClientTemplate().insert("FwEmployee.updateFW_EMPLOYEE",entity);
	}
	
	@Override
	public void deleteById(java.lang.String id) {
		super.getSqlMapClientTemplate().delete("FwEmployee.deleteFW_OPERATOR", id);
		super.getSqlMapClientTemplate().delete("FwEmployee.deleteFW_EMPLOYEE", id);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwEmployee.BM_CLASS_ID);
		return pageQuery("FwEmployee.pageSelect",pageRequest);
	}
	
	public List<FwEmployee> findByRequest(PageRequest pageRequest) {
		((Map)pageRequest.getFilters()).put("BM_CLASS_ID", FwEmployee.BM_CLASS_ID);
		Map otherFilters = new HashMap();
		otherFilters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = new MapAndObject(otherFilters,pageRequest.getFilters());
		return getSqlMapClientTemplate().queryForList("FwEmployee.pageSelect", parameterObject);
	}

	public List<FwOrganization> selectPartAllNameByOnePeople(String cuid) {
		FwOrganization fwOrganization1=new FwOrganization();
		fwOrganization1.setCuid(cuid);
		return getSqlMapClientTemplate().queryForList("FwOrganization.selectPartAllNameByOnePeople", fwOrganization1);
	}

	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
	public void savePageSizeValue(int pageSizeValue, String userId){
		String sqlString ="update zg_t_operatorinfoset set pagesize="+pageSizeValue+" where userid='"+userId+"'";
		getBaseDao().executeSql(sqlString);
	}
	public List<Map> findFromPageParam(String userId){
		String sqlString ="select *from zg_t_operatorinfoset where userid='"+userId+"'";
		List<Map> list=getBaseDao().findDynQuery(sqlString);
		return list;
	}
	public void insertPageSizeValue(int pageSizeValue,String userId){
		String sqlString="insert into zg_t_operatorinfoset ( pagesize,userid )values ("+pageSizeValue+",'"+userId+"')";
		getBaseDao().executeSql(sqlString);
	}

	public void updateSelfInfo(FwEmployee fwEmployee) {
		super.getSqlMapClientTemplate().insert("FwEmployee.updateFW_OPERATOR_self",fwEmployee);
		super.getSqlMapClientTemplate().insert("FwEmployee.updateFW_EMPLOYEE_self",fwEmployee);
		
	}
	
}
