package com.boco.frame.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.boco.frame.sys.base.dao.FwMenuDao;
import com.boco.frame.sys.model.FwMenuEx;

@Component
public class FwMenuExDao extends FwMenuDao {
	
	public List<FwMenuEx> findMenuTree() {
		return (List<FwMenuEx>)this.getSqlMapClientTemplate().queryForList("FwMenuEx.findMenuTree");
	}
	
	public List<FwMenuEx> findMenuTreeByOperatorId(String operatorId) {
		Map map = new HashMap();
		map.put("operatorId", operatorId);
		return (List<FwMenuEx>)this.getSqlMapClientTemplate().queryForList("FwMenuEx.findMenuTreeByOperatorId",map);
	}

	public String getSeqById(String id) {
		StringBuffer sBuffer=new StringBuffer();
		Object obj = this.getSqlMapClientTemplate().queryForObject("FwMenuEx.getSeqById",id);
		return obj==null?"":obj.toString();
	}
}
