package com.boco.frame.sys.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.frame.sys.base.dao.FwOrganizationDao;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOrganizationManager;
import com.boco.frame.sys.model.FwOrganizationEX;

@Component
public class FwOrganizationExDao extends FwOrganizationDao {
	
	public Class getEntityClass() {
		return FwOrganizationManager.class;
	}
	
	public List<FwOrganizationEX> findOrganizationTree() {
		Map map = new HashMap();
		return (List<FwOrganizationEX>)this.getSqlMapClientTemplate().queryForList("FwOrganizationEx.findOrganizationTree",map);
	}

	public Page findPrincipalsList(PageRequest pageRequest) {//departmentId
		
		return pageQuery("FwOrganizationManager.pageSelectPrincipals",pageRequest);
	}

	public void insertFwOrganizationManager(String organizationCuid,
			String oneEmployeeId) {
		FwOrganizationManager fwOrganizationManager=new FwOrganizationManager();
		fwOrganizationManager.setOrgId(organizationCuid);
		fwOrganizationManager.setEmployeeId(oneEmployeeId);
		this.getSqlMapClientTemplate().insert("FwOrganizationManager.insertFW_ORGANIZATION_MANAGER", fwOrganizationManager);
	}

	public List<FwOrganizationManager> findEmployeesByOrgId(String id) {
		FwOrganizationManager fwOrganizationManager=new FwOrganizationManager();
		fwOrganizationManager.setCuid(id);
		return (List<FwOrganizationManager>)this.getSqlMapClientTemplate().queryForList("FwOrganizationManager.findEmployeesByOrgId",fwOrganizationManager);
	}

	public void deleteFwOrganizationManagerByOrgId(String cuid) {
		FwOrganizationManager fwOrganizationManager=new FwOrganizationManager();
		fwOrganizationManager.setOrgId(cuid);
		this.getSqlMapClientTemplate().delete("FwOrganizationManager.deleteFwOrganizationManagerByOrgId", fwOrganizationManager);
	}

	/**
	 * 根据人员id获取其负责的部门列表
	 * @param cuid
	 * @return
	 */
	public List<FwOrganizationManager> getManagerListByUserId(String userId) {
		return this.getSqlMapClientTemplate().queryForList("FwOrganizationManager.getManagerListByUserId",userId);
	}

	public void removeManagerOrgByOperateId(String operatorId) {
		this.getSqlMapClientTemplate().delete("FwOrganizationManager.removeManagerOrgByOperateId", operatorId);

	}

	/**
	 * 获取生产厂的目前的排序日期
	 * @param plant
	 * @return
	 */
	public Date getPxdateByOrgId(String plant) {
		return (Date)this.getSqlMapClientTemplate().queryForObject("FwOrganizationEx.getPxdateByOrgId",plant);

	}

	/**
	 * 切换排序日期到第二天
	 * @param plant
	 */
	public int changePxDateNextDay(String plant) {
		return this.getSqlMapClientTemplate().update("FwOrganizationEx.changePxDateNextDay",plant);

	}

	/**
	 * 重罪该生产组排序日期之后的订单分组的内部排序号，因为之前的提前领料内部排序号让改变，所以要重置
	 * @param plant
	 */
	public Object revertIndexNoByPlant(String plant) {
		return this.getSqlMapClientTemplate().update("FwOrganizationEx.revertIndexNoByPlant",plant);
	}
}
