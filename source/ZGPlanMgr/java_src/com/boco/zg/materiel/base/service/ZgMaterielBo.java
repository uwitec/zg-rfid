/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.materiel.base.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.materiel.base.model.*;
import com.boco.zg.materiel.base.dao.*;
import com.boco.zg.materiel.base.service.*;
import com.boco.zg.virtualorg.base.dao.ZgMaterrielVirtualorgDao;
import com.boco.zg.virtualorg.base.model.ZgMaterrielVirtualorg;

/**
 * @author 吴俊璋
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgMaterielBo extends BaseManager<ZgMateriel,java.lang.String>{
	private ZgMaterielDao zgMaterielDao;
	private ZgMaterielExDao zgMaterielExDao;
	private ZgMaterrielVirtualorgDao zgMaterrielVirtualorgDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgMaterielDao(ZgMaterielDao dao) {
		this.zgMaterielDao = dao;
	}
	public void setZgMaterrielVirtualorgDao(
			ZgMaterrielVirtualorgDao zgMaterrielVirtualorgDao) {
		this.zgMaterrielVirtualorgDao = zgMaterrielVirtualorgDao;
	}
	public EntityDao getEntityDao() {
		return this.zgMaterielDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgMaterielDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgMaterielDao.deleteById(id);
	}
	
	public List<ZgMateriel> findByRequest(PageRequest pageRequest) {
		return zgMaterielDao.findByRequest(pageRequest);
	}
	public Page findByRequestForMateriel(PageRequest<Map> pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	public ZgMaterrielVirtualorg findOneInfoMaterielVirtualorgCuid(String materielVirtualorgId) {
		return zgMaterielDao.findOneInfoMaterielVirtualorgCuid(materielVirtualorgId);
	}
	
	public String checkAllMaterielName(String materielName){
		return zgMaterielDao.checkAllMaterielName(materielName);
	}
	
	/**
	 * 思路:
	 * 1:更新ZG_MATERIEL表的主键为cuid的某条数据=>拿出这里的materielCuid
	 * 2:删除ZG_MATERIEL_VIRTUALORG表主键为cuid的某条数据
	 * 	 用materielCuid来循环插入N个虚拟组
	 */
	public void updateMaterielAndInsertZgMaterielVirtualorg(ZgMateriel zgMateriel,
														String materielVirtualorgId,
													String allVirtualorgId){
		zgMaterielDao.updateMateriel(zgMateriel);//zgMateriel的主键cuid要有值
		
		zgMaterrielVirtualorgDao.deleteZgTvirtualorgMateriel(materielVirtualorgId);
		
		allVirtualorgId+=",";
		int num=repeatCountNum(allVirtualorgId,",");//数组的长度
	    for(int i=0;i<num;i++){
	    	String oneVirtualorgId=allVirtualorgId.substring(0, allVirtualorgId.indexOf(","));
	    	zgMaterrielVirtualorgDao.insertZgTvirtualorgMateriel(oneVirtualorgId,zgMateriel.getCuid());
	    	allVirtualorgId=allVirtualorgId.substring(allVirtualorgId.indexOf(",")+1);
	    }
	}
	
	/**
	 * mainStr 字符串 
	 * subStr 字符
	 * 思路:这个方法是用来计算出数组的长度
	 */
	private int repeatCountNum(String mainStr,String subStr){
	    int count = 0;
	    int offset = 0;
	    do{
	    	offset=mainStr.indexOf(subStr);//在字符串tempStr中查看一下是否有subStr字符
	    	if(offset != -1){//如果有subStr字符的话，就截断读取过的部分
	    		mainStr=mainStr.substring(offset+1);
	    		count++;
	    	}
	    }while(offset != -1);
	    return count;
	 }
	/**
	 * 物料组导航树增加节点
	 * @param entity
	 * @return cuid
	 */
	public String saveMateriel(ZgMateriel entity) {
		return zgMaterielDao.saveMateriel(entity);
	}
	/**
	 * 通过parentId找到父节点或者祖父节点所属的仓库,如果仓库为空 则说明该节点是仓库 ，则findIdByParentId找到ID 并返回
	 * @param parentId
	 * @return
	 */
	public String findLgortByParentId(String parentId) {
		return zgMaterielDao.findLgortByParentId(parentId);
	}
	public String findIdByParentId(String parentId) {
		return zgMaterielDao.findIdByParentId(parentId);
	}
	public void delMateriel(String cuid) {
		zgMaterielDao.delMateriel(cuid);
	}
	public boolean findMaterielData(String cuid) {
		int num=zgMaterielDao.findMaterielDataFromZgMaterielVirtual(cuid).intValue();
		if(num>0){
			return false;
		}
		num=zgMaterielDao.findMaterielDataFromZgTbom(cuid).intValue();
		if(num>0){
			return false;
		}
		return true;
	}
	public ZgMateriel getByCuid(String cuid) {
		return zgMaterielDao.getByCuid(cuid);
	}
	/**
	 * 用于更新物料组树
	 * @param zgMateriel
	 */
	public void updateMateriel(ZgMateriel zgMateriel) {
		zgMaterielDao.updateMaterielForTree(zgMateriel);
	}
	
	public void deleteMaterielByCuid(String cuid) {
		zgMaterielDao.deleteMaterielByCuid(cuid);
	}
	/**
	 *  后台判断物料组树节点是否是叶子节点 count>0 不是叶子节点
	 * @param cuid
	 * @return
	 */
	public boolean isLeaf(String cuid) {
		int count=zgMaterielDao.findChildCount(cuid).intValue();
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 物料组树添加 编辑时 判断是否有重名的Id
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean validId(String parentId, String id) {
		List list=zgMaterielDao.findIdForValid(parentId);
		if(list.contains(id)){
			return false;
		}
		return true;
	}
	/**
	 * 物料组树添加 编辑时 判断是否有重名的materielName
	 * @param parentId
	 * @param materielName
	 * @return
	 */
	public boolean validName(String parentId, String materielName) {
		List list=zgMaterielDao.findNameForValid(parentId);
		if(list.contains(materielName)){
			return false;
		}
		return true;
	}
	
	 /**
	  * 更新物料组导航树时 对节点名称即物料组名称验证
	  * 思路：找到需要更新节点的父节点的所有子节点的名称，去除需要更新的节点的名称，
	  * 然后看余下节点的名称是否包含将要提交的值
	  * @param cuid
	  * @param parentId
	  * @param materielName
	  * @return
	  */
	public boolean validNameForUpdate(String cuid,String parentId,String materielName){
		List list=zgMaterielDao.findNameForValid(parentId);//分不清是自己更新前的名字还是别人的名字
		if(parentId.equals(zgMaterielDao.findParentId(cuid))){//父节点没有改变:更新值与库表值比较
			list.remove(zgMaterielDao. findNameByCuid(cuid));
		}
		if(list.contains(materielName)){
			return false;
		}
		return true;
	}
	
	/**
	 * 更新物料组导航树时 对节点名称即物料组ID验证
	 * 思路：找到需要更新节点的父节点的所有子节点的ID，去除需要更新的节点的ID，
	 * 然后看余下节点的ID是否包含将要提交的值
	 * @param cuid
	 * @param parentId
	 * @param id
	 * @return
	 */
	public boolean validIdForUpdate(String cuid, String parentId, String id) {
		List list=zgMaterielDao.findIdForValid(parentId);
		if(parentId.equals(zgMaterielDao.findParentId(cuid))){
			list.remove(zgMaterielDao.findIdByCuid(cuid));
		}
		if(list.contains(id)){
			return false;
		}
		return true;
	}
	
	public boolean isLorgNode(String cuid) {
		String parentId=zgMaterielDao.findParentId(cuid);
		if(parentId==null){
			return false;
		}
		return true;
	}
	
	/**
	 * 增加提前领料物料组
	 * @param matId
	 */
	public void addAddvanceMat(String matId) {
		zgMaterielDao.addAddvanceMat(matId);
		
	}
	public Page listMateridel(PageRequest<Map> pageRequest) {
		return zgMaterielExDao.listMateridel(pageRequest);
	}
	
	/**
	 * 删除领料物料组所属关系
	 * @param matkl 物料组编号
	 * @param vorgId　领料组主銉
	 */
	public boolean removeVorgById(String matkl,String vorgId) {
		Map paramsMap=new HashMap();
		paramsMap.put("matkl", matkl);
		paramsMap.put("vorgId", vorgId);
		return zgMaterielDao.removeVorgById(paramsMap)>0;

	}
	public ZgMaterielExDao getZgMaterielExDao() {
		return zgMaterielExDao;
	}
	public void setZgMaterielExDao(ZgMaterielExDao zgMaterielExDao) {
		this.zgMaterielExDao = zgMaterielExDao;
	}
	
}
