package com.boco.frame.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.boco.frame.sys.base.dao.FwRoleDao;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.service.FwRoleBo;

@Component
public class FwRoleExBo extends FwRoleBo {
	
	public List<FwOperator> findUserByRoleId(String roleId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("roleId", roleId);
		return ((FwRoleDao)this.getEntityDao()).getSqlMapClientTemplate().queryForList("FwRoleEx.findUserByRoleId",paramMap);
	}
}
