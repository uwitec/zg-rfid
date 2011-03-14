package javacommon.base.dao;

import java.util.List;
import java.util.Map;

import javacommon.base.model.AutoComplate;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class AutoComplateDao extends SqlMapClientDaoSupport {
	
	public List<AutoComplate> findByProperty(Map map) {
		return this.getSqlMapClientTemplate().queryForList("AutoComplate.findByProperty",map);
	}

}
