package com.boco.zg.virtualorg.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.virtualorg.base.model.ZgTvirtualorg;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorgEmployee;

import javacommon.base.BaseIbatisDao;

@Component
public class ZgTvirtualorgEmployeeDao extends BaseIbatisDao<ZgTvirtualorgEmployee,java.lang.String>{

	@Override
	public Class getEntityClass() {
		return ZgTvirtualorgEmployee.class;
	}

	public void saveOrUpdate(ZgTvirtualorgEmployee entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	
	/**
	 * 查看领料人
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public Page findByRequest(PageRequest pageRequest){
		return pageQuery("ZgTvirtualorgEmployee.pageSelect",pageRequest);
	}

	public void insertZgTvirtualorgEmployee(String virtualorgId,
			String oneUserId) {
		ZgTvirtualorgEmployee zgTvirtualorgEmployee=new ZgTvirtualorgEmployee();
		zgTvirtualorgEmployee.setOrgId(virtualorgId);
		zgTvirtualorgEmployee.setUserId(oneUserId);
		this.getSqlMapClientTemplate().insert("ZgTvirtualorgEmployee.insert_ZgTvirtualorgEmployee", zgTvirtualorgEmployee);
		
	}
	public List<ZgTvirtualorgEmployee> getListById(String cuid){
		List<ZgTvirtualorgEmployee> list=getSqlMapClientTemplate().queryForList("ZgTvirtualorgEmployee.getById", cuid);
		return list;
	}

}
