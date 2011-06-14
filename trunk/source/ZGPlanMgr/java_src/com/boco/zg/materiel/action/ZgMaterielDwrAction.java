package com.boco.zg.materiel.action;

import java.util.Map;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.materiel.base.service.ZgMaterielBo;

import javacommon.base.BaseDwrAction;

public class ZgMaterielDwrAction extends BaseDwrAction{
	
	private ZgMaterielBo getZgMaterielBo(){
		return (ZgMaterielBo)ApplicationContextHolder.getBean("zgMaterielBo");
	}
	
	/**
	 * 前台保存物料组导航树
	 * @param materielName
	 * @param parentId 就是要增加的节点的父节点cuid
	 * @param typeStr
	 * @param id
	 * @param lgort
	 * @return
	 */
	public  String saveMateriel(String materielName,String parentId,String lgorts,String id){
		String[] lgortArr=lgorts.split(",");
		for(String lgort:lgortArr){
			Map parentMap=getZgMaterielBo().getByIdAndLgort(parentId,lgort);
			ZgMateriel entity =new ZgMateriel();
			entity.setMaterielName(materielName);
			entity.setParentId(IbatisDAOHelper.getStringValue(parentMap, "CUID", ""));
			entity.setAdvance(IbatisDAOHelper.getStringValue(parentMap, "ADVANCE", ""));
			entity.setId(id);
			entity.setType(new Long(2));
			entity.setLgort(lgort);
			String cuid=getZgMaterielBo().saveMateriel(entity);
		}
		return id;
		
	}
	
	/**
	 * 思路：通过parentId找到父节点或者祖父节点所属的仓库
	 * @param cuid
	 * @return
	 */
	private String findLgortByParentId(String parentId){
		String lgort=getZgMaterielBo().findLgortByParentId(parentId);
		if(lgort==null||lgort==""){
			lgort=getZgMaterielBo().findIdByParentId(parentId);
			return lgort;
		}
		return lgort;
	}
	public boolean findMaterielData(String cuid){
		return getZgMaterielBo().findMaterielData(cuid);
	}
	public void delMateriel(String id){
		getZgMaterielBo().delMateriel(id);
	}
	public void updateMateriel(String cuid,String materielName,String id,String parentOrgId,String lgorts){
		String[] lgortArr=lgorts.split(",");
		getZgMaterielBo().delMateriel(id);
		for(String lgort:lgortArr){
			Map parentMap=getZgMaterielBo().getByIdAndLgort(parentOrgId,lgort);
			ZgMateriel entity =new ZgMateriel();
			entity.setMaterielName(materielName);
			entity.setParentId(IbatisDAOHelper.getStringValue(parentMap, "CUID", ""));
			entity.setAdvance(IbatisDAOHelper.getStringValue(parentMap, "ADVANCE", ""));
			entity.setId(id);
			entity.setType(new Long(2));
			entity.setLgort(lgort);
			getZgMaterielBo().saveMateriel(entity);
		}
		
	}
	/**
	 * 后台判断物料组树节点是否是叶子节点
	 * @param cuid
	 * @return
	 */
	public boolean isLeafNode(String cuid){
		return getZgMaterielBo().isLeaf(cuid);
	}
	
	/**
	 * 后台判断物料组树节点是否是叶子节点
	 * @param cuid
	 * @return
	 */
	public boolean hasBom(String id){
		return getZgMaterielBo().hasBom(id);
	}
	/**
	 * 物料组新增节点时验证是否同名
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean validId(String parentId,String id){
		return getZgMaterielBo().validId1(parentId,id);
	}
	
	/**
	 * 物料组新增节点时验证是否同ID
	 * @param parentId
	 * @param materielName
	 * @return
	 */
	public boolean validName(String parentId,String materielName){
		return getZgMaterielBo().validName(parentId,materielName);
	}
	
	/**
	 * 物料组更新节点时验证是否同名
	 * @param cuid
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean validIdForUpdate(String cuid,String parentId,String id){
		return getZgMaterielBo().validIdForUpdate(cuid,parentId,id);
	}
	/**
	 * 物料组更新节点时验证是否同ID
	 * @param cuid
	 * @param parentId
	 * @param materielName
	 * @return
	 */
	public boolean validNameForUpdate(String cuid,String parentId,String materielName){
		return getZgMaterielBo().validNameForUpdate(cuid, parentId, materielName);
	}
	/**
	 * 判读是否是仓库，也就是判断是不是二级节点 仓库不可以编辑
	 * @param cuid
	 * @return
	 */
	public boolean isLorgNode(String id){
		return getZgMaterielBo().isLorgNode(id);
	}
	
	public boolean updateMaterielLevel(String marerielId,String level) {
		return getZgMaterielBo().updateMaterielLevel(marerielId,level);
	}

}
