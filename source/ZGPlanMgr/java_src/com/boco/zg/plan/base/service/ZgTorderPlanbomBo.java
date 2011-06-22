/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

import org.springframework.stereotype.Component;

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

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTorderPlanbomBo extends BaseManager<ZgTorderPlanbom,java.lang.String>{
	private ZgTorderPlanbomDao zgTorderPlanbomDao;
	private ZgTorderPlanBo zgTorderPlanBo;
	
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTorderPlanbomDao(ZgTorderPlanbomDao dao) {
		this.zgTorderPlanbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTorderPlanbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTorderPlanbomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTorderPlanbomDao.deleteById(id);
	}
	
	public int removeById1(java.lang.String id) {
		return zgTorderPlanbomDao.deleteById1(id);
	}
	
	public List<ZgTorderPlanbom> findByRequest(PageRequest pageRequest) {
		return zgTorderPlanbomDao.findByRequest(pageRequest);
	}
	public Object save1(ZgTorderPlanbom zgTorderPlanbom) {
		return zgTorderPlanbomDao.save1(zgTorderPlanbom);
		
	}
	
	
	
	/**
	 * @param taskId
	 * @param sortf
	 * @param cuid
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByTaskIdPlanTypeTaskBomId(String taskId,String planType, String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskId", taskId);
		paramsMap.put("planType", planType);
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomByTaskIdPlanTypeTaskBomId(paramsMap);
	}
	
	/**
	 * @param taskId
	 * @param sortf
	 * @param cuid
	 * @return
	 */
	public List<ZgTorderPlanbom> getPlanBomTaskBomId(String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomTaskBomId(paramsMap);
	}
	
	
	/**
	 * @param cuid
	 * @param cuid2
	 * @return
	 */
	public ZgTorderPlanbom getPlanBomByPlanIdTaskBomId(String planId, String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getPlanBomByPlanIdTaskBomId(paramsMap);
	}

	/**
	 * 根据taskid，plantype设置其他领料计划BOM的需求数量为0 并设置退料数据
	 * @param taskId
	 * @param planType
	 */
	public void updatePlanBomCarNumTo0ByPlanId(String planId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		zgTorderPlanbomDao.updatePlanBomCarNumTo0ByPlanId(paramsMap);
		
	}
	
	/**
	 * 根据taskid，plantype删除其bom
	 * @param taskId
	 * @param planType
	 */
	public void delPlanBomPlanId(String planId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planId", planId);
		zgTorderPlanbomDao.delPlanBomPlanId(paramsMap);
		
	}
	/**
	 * @param bomId
	 */
	public int deletePlanBomByOrderBomId(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		return zgTorderPlanbomDao.deletePlanBomByOrderBomId(paramsMap);
		
	}
	/**
	 * @param bomId
	 * @return
	 */
	public  List<ZgTorderPlanbom> getPlanBomByOrderBomId(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		return zgTorderPlanbomDao.getPlanBomByOrderBomId(paramsMap);
	}
	/**
	 * 判断某个物料是否已经开始领料
	 * @param bomId
	 * @return
	 */
	public boolean isStartCar(String bomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("bomId", bomId);
		boolean isStartCar=false;
		List<ZgTorderPlanbom> planBomList= zgTorderPlanbomDao.getPlanBomByOrderBomId(paramsMap);
		for(ZgTorderPlanbom bom:planBomList){
			if(bom.getPlanNum()>0){
				isStartCar=true;
				break;
			}
		}
		return isStartCar;
	}
	
	/**
	 * 获取该物料人工发起的退料数量
	 * @param cuid
	 * @return
	 */
	public Long getManulWaitBackNumByPbId(String planBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planBomId", planBomId);
		return zgTorderPlanbomDao.getManulWaitBackNumByPbId(paramsMap);
	}
	
	/**
	 * 获取该物料人工发起的退料数量
	 * @param cuid
	 * @return
	 */
	public List<ZgTorderPlanbom> getAutoWaitBackBomListByPbId(String planBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planBomId", planBomId);
		return zgTorderPlanbomDao.getAutoWaitBackBomListByPbId(paramsMap);
	}
	
	/**
	 * 工单变更引起的退料，生成退料数据
	 *  退料由两部分组成　1　人工申请的退料　2、工单变更引起的退料，这两部分汇总等于总退料信息
	 * @param planbom
	 * @param manulWaitBackNum 人工引起的退料数量
	 */
	public void generateWaiBackBom(ZgTorderPlanbom planbom,Long manulWaitBackNum ) {
		
		//获取工单变更引起的退料信息
		List<ZgTorderPlanbom> list=getAutoWaitBackBomListByPbId(planbom.getCuid());
		
		if(manulWaitBackNum>=planbom.getWaitBackNum()){//人工申请的退料数量大于待退料数量　则无须生成工单变更退料　之前的工单变更退料置0
			planbom.setWaitBackNum(manulWaitBackNum);
			for(ZgTorderPlanbom pbom:list){
					pbom.setWaitBackNum(0l);
					zgTorderPlanbomDao.update(pbom);
			}
		}else {//人工申请的退料数量小于待退料数量，则需要生成工单退料数量
			
			Long needWaitBackNum=planbom.getWaitBackNum()-manulWaitBackNum;//需要工单变更引起的退料数量
			
			if(list.size()==0){// 测试　之前没有退料信息，则插入
				ZgTorderPlanbom newBom=new ZgTorderPlanbom();
				newBom=(ZgTorderPlanbom) BeanUtils.cloneBean(planbom);
				newBom.setParentId(planbom.getCuid());
				newBom.setWaitBackNum(needWaitBackNum);
				newBom.setCarNum(0l);
				newBom.setCompleteNum(0l);
				newBom.setPlanNum(0l);
				newBom.setMoveNum(0l);
				newBom.setMoveNumIn(0l);
				newBom.setBackNum(0l);
				newBom.setOutNum(0l);
				newBom.setStorageNum(0l);
				save(newBom);
				return;
			}else {//之前已经有工单变更退料的　则更新
				Long oldWaitBackNum=0l;//之前工单变更退料的总数量
				for(ZgTorderPlanbom pbom:list){
					oldWaitBackNum=oldWaitBackNum+pbom.getWaitBackNum();
				}
				
				if(oldWaitBackNum<=needWaitBackNum){//测试过 之前工单变更退料的总数量小于需要工单变更引起的退料数量，则取其中一条退料数量加上
					ZgTorderPlanbom pbom=list.get(0);
					pbom.setWaitBackNum(pbom.getWaitBackNum()+needWaitBackNum-oldWaitBackNum);
					zgTorderPlanbomDao.update(pbom);
				}else {//否则 则需要从原来的工单变更记录中减去相应的数量
					Long curNum=oldWaitBackNum-needWaitBackNum;
					for(ZgTorderPlanbom pbom:list){
						if(pbom.getWaitBackNum()>=curNum){//测试过
							pbom.setWaitBackNum(pbom.getWaitBackNum()-curNum);
							zgTorderPlanbomDao.update(pbom);
							break;
						}else {
							curNum=curNum-pbom.getWaitBackNum();
							pbom.setWaitBackNum(0l);
							zgTorderPlanbomDao.update(pbom);
						}
					}
				}
			}
			
			
			
			
		}
		
		
		
	}
	
	
	/**
	 * 每个BOM有几条领料计划记录（退换料　正常领料）　找到正常领料的主记录
	 * @param valueOf
	 * @return
	 */
	public ZgTorderPlanbom getParentPlanBomByTaskBomId(String taskBomId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("taskBomId", taskBomId);
		return zgTorderPlanbomDao.getParentPlanBomByTaskBomId(paramsMap);
	}

	/**
	 * 退料数量汇总到主记录　如果是换料的则不需要汇总满足，因为换料涉及到（一退一换操作），不影响主记录的继续领料
	 * @param parentPbom
	 * @param childPlanId
	 * @param realnum:本次领料数量
	 */
	public void upDateParentWaitBackNumByPlanBom(ZgTorderPlanbom parentPbom,String childPlanId,Long realNum) {
		ZgTorderPlan plan=zgTorderPlanBo.getById(childPlanId);
		if(!Constants.OrderPlanType.CHANGE.value().equals(plan.getPlanType())){
			Map map=getParentWaitBackNumByPBId(parentPbom.getCuid());
			if(map!=null){
				Long parentWaitBackNum=IbatisDAOHelper.getLongValue(map, "WAIT_BACK_NUM");
				Long parentBackNum=IbatisDAOHelper.getLongValue(map, "BACK_NUM");
				parentPbom.setWaitBackNum(parentWaitBackNum);
				parentPbom.setBackNum(parentBackNum);
				parentPbom.setBackNum(parentBackNum);
				
				if(realNum!=null){
					parentPbom.setPlanNum(parentPbom.getPlanNum()-realNum);
					parentPbom.setCompleteNum(parentPbom.getCompleteNum()-realNum);
					parentPbom.setStorageNum(parentPbom.getStorageNum()-realNum);
				}
				
				update(parentPbom);
			}
		}
		
		
	}

	/**
	 * @param cuid
	 * @return
	 */
	public Map getParentWaitBackNumByPBId(String cuid) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planBomId", cuid);
		return zgTorderPlanbomDao.getParentWaitBackNumByPBId(paramsMap);
	}
	/**
	 * 换料补料 的领料数量汇总到主记录　如果是换料的则不需要汇总，因为换料涉及到（一退一换操作），不影响主记录的继续领料
	 * @param parentPbom
	 * @param realNum
	 */
	public void upDateParentCompleteNumByPlanBom(ZgTorderPlanbom planbom,
			Long realNum,String childPlanId) {
		ZgTorderPlan plan=zgTorderPlanBo.getById(childPlanId);
		if(!Constants.OrderPlanType.CHANGE.value().equals(plan.getPlanType())){
			planbom.setPlanNum(planbom.getPlanNum()+realNum);
			planbom.setCompleteNum(planbom.getCompleteNum()+realNum);
			planbom.setStorageNum(planbom.getStorageNum()+realNum);
			if(planbom.getCarNum() <= planbom.getCompleteNum()&&(!planbom.getState().equals(Constants.CarPlanStatus.DONE.value()))) {
				planbom.setFinishTime(Calendar.getInstance().getTime());
				planbom.setState(Constants.CarPlanStatus.DONE.value());
			}
			update(planbom);
		}
		
		
		
	}
	/**
	 * @return the zgTorderPlanBo
	 */
	public ZgTorderPlanBo getZgTorderPlanBo() {
		return zgTorderPlanBo;
	}
	/**
	 * @param zgTorderPlanBo the zgTorderPlanBo to set
	 */
	public void setZgTorderPlanBo(ZgTorderPlanBo zgTorderPlanBo) {
		this.zgTorderPlanBo = zgTorderPlanBo;
	}
	

	
}
