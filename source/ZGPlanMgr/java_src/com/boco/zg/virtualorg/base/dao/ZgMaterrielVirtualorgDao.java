package com.boco.zg.virtualorg.base.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import javacommon.base.BaseIbatisDao;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;
import com.boco.zg.virtualorg.base.model.ZgTvirtualorgEmployee;

@Component
public class ZgMaterrielVirtualorgDao extends BaseIbatisDao<ZgMaterrielVirtualorg,java.lang.String>{

	@Override
	public Class getEntityClass() {
		return ZgMaterrielVirtualorg.class;
	}

	public void saveOrUpdate(ZgMaterrielVirtualorg entity) {
		prepareObjectForSaveOrUpdate(entity);
		if(entity.getCuid()== null){
			save(entity);
		}else{
			update(entity);
		}
	}
	
	public void insertZgTvirtualorgMateriel(String virtualorgId,
			String oneMaterielId) {
		ZgMaterrielVirtualorg zgMaterrielVirtualorg=new ZgMaterrielVirtualorg();
		zgMaterrielVirtualorg.setMatkl(oneMaterielId);
		zgMaterrielVirtualorg.setOrgId(virtualorgId);
		this.getSqlMapClientTemplate().insert("ZgMaterrielVirtualorg.insert_ZgMaterrielVirtualorg", zgMaterrielVirtualorg);
	}
	
	public void deleteZgTvirtualorgMateriel(String ZgTvirtualorgMaterielCuid){
		this.getSqlMapClientTemplate().delete("ZgMaterrielVirtualorg.delete_ZgMaterrielVirtualorg", ZgTvirtualorgMaterielCuid);
	}
	
	/**
	 * 查看物料组
	 * 思路:一定要选择虚拟领料组才能新增，否则提示要选择虚拟领料组
	 * @return
	 */
	public Page findByRequest(PageRequest pageRequest){
		return pageQuery("ZgMaterrielVirtualorg.pageSelect",pageRequest);
	}
	public List<ZgMaterrielVirtualorg> getListById(String cuid){
		List<ZgMaterrielVirtualorg> list=getSqlMapClientTemplate().queryForList("ZgMaterrielVirtualorg.getById", cuid);
		return list;
	}
	
	public void deleteZgMaterielVirtualorgByOrgId(String orgid){
		getSqlMapClientTemplate().delete("ZgMaterrielVirtualorg.deleteZgMaterielVirtualorgByOrgId", orgid);
	}
}
