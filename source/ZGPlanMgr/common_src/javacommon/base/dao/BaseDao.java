package javacommon.base.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javacommon.util.PageRequestExt;
import javacommon.util.StringHelper;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;

@Component
public class BaseDao extends SqlMapClientDaoSupport{
	
    protected final String BASE_NAMESPACE = "Base";
    
    public final String COLUMN_LIST = "columnList";

	public final String TABLE_NAME = "tableName";
	
	/**
	 * 自定义sql语句的翻页封装,主要用于sql树配置
	 * @param sql
	 * @param pageRequest
	 * @return
	 */
	public Page pageDynQuery(String sql, PageRequestExt pageRequest) {
		int totalNum = pageRequest.getTotalNum();
		if(totalNum<=0){
			String countSql = "select count(*) from ( "+sql+" )";
			totalNum = ((Number) this.getSqlMapClientTemplate().queryForObject(BASE_NAMESPACE+".calculate",countSql)).intValue();
		}
		Page page = new Page(pageRequest,totalNum);
		List list = getSqlMapClientTemplate().queryForList(BASE_NAMESPACE+".queryDyn", sql,page.getFirstResult(),page.getPageSize());
		page.setResult(list);
		return page;
	}
	
	/**
	 * 动态执行语句 返回Map对象
	 * @param sql
	 * @return
	 */
	public List<Map> findDynQuery(String sql) {
		List list = getSqlMapClientTemplate().queryForList(BASE_NAMESPACE+".queryDyn", sql);
		return list;
	}
	
	public List findAll(String bmClassId){
		String className = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(bmClassId));
		return getSqlMapClientTemplate().queryForList(className+".findAll");
	}
	
	public void insertDynamicTable(String tableName,Map paramMap) {
		List list = new ArrayList();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			Object value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value);
			list.add(map);
		}
		Map newMap = new HashMap();
		newMap.put(TABLE_NAME, tableName);
		newMap.put(COLUMN_LIST, list);
		getSqlMapClientTemplate().insert(BASE_NAMESPACE+".insertDynamicTable", newMap);
	}

	public int getSeq(String seqName) {
		Integer date = (Integer) getSqlMapClientTemplate().queryForObject(BASE_NAMESPACE + ".getSeq", seqName);
		return date;
	}

	public void executeSql(String sql) {
		getSqlMapClientTemplate().queryForList(BASE_NAMESPACE+".executeSql",sql);
	}
	
	public List<Map> queryBySql(String sql) {
		return getSqlMapClientTemplate().queryForList(BASE_NAMESPACE+".executeSql",sql);
	}
}
