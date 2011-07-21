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
import com.boco.frame.sys.base.model.ZgMateriel;
import com.boco.zg.materiel.base.service.ZgMaterielBo;
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
	
	private ZgTorderPlanDao zgTorderPlanDao;
	
	public void setZgTorderPlanDao(ZgTorderPlanDao zgTorderPlanDao) {
		this.zgTorderPlanDao = zgTorderPlanDao;
	}
	
	private ZgTorderTaskBo zgTorderTaskBo;
	
	private ZgTorderPlanGroupBo zgTorderPlanGroupBo;
	
	private ZgTgroupOrderPlanBo zgTgroupOrderPlanBo;
	 
	private ZgMaterielBo zgMaterielBo;
	
	
	
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
			
			//查找该物料的物料等级
			String materielLevel=zgMaterielBo.getMaterielLevelByTaskBomId(planbom.getTaskBomId());
			
			ZgTorderTask task=getZgTorderTaskBo().getById(planbom.getOrderTaskId());
			
			//查找未开始审核的退换料计划
			ZgTorderPlan planNotAudit=zgTorderPlanBo.getPlanNotAudit(planbom.getOrderTaskId(),materielLevel,task.getPlant());
			
			List<ZgTorderPlanbom> planBomNotAuditList=new ArrayList<ZgTorderPlanbom>();
			if(planNotAudit!=null){
				planBomNotAuditList=getAutoWaitBackBomListNotAuditByPbId(planbom.getCuid(),planNotAudit.getCuid());
			}
			
			
			
			Long needWaitBackNum=planbom.getWaitBackNum()-manulWaitBackNum;//需要工单变更引起的退料数量
			
			if(planBomNotAuditList.size()==0){// 测试　没有未审核的退料信息，则插入
				if(needWaitBackNum>0){
					Long oldWaitBackNum=0l;//之前工单变更退料的总数量
					for(ZgTorderPlanbom pbom:list){
						oldWaitBackNum=oldWaitBackNum+pbom.getWaitBackNum();
					}
					if(oldWaitBackNum<=needWaitBackNum){//之前工单变更退料的总数量小于需要工单变更引起的退料数量 则插入一打新的退料记录
						genereteNewBackPlanInfo(planbom, needWaitBackNum-oldWaitBackNum,planNotAudit,materielLevel);
					}else {//否则，从之前的退料信息中减数量
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
					
					return;
				}
				
			}else {//之前存在退料信息　且未审核　则更新
				Long oldWaitBackNum=0l;//之前工单变更退料的总数量
				for(ZgTorderPlanbom pbom:list){
					oldWaitBackNum=oldWaitBackNum+pbom.getWaitBackNum();
				}
				
				if(oldWaitBackNum<=needWaitBackNum){//测试过 之前工单变更退料的总数量小于需要工单变更引起的退料数量，则取其中一条退料数量加上
					if(planBomNotAuditList.size()>0){//有未审核的记录，直接在譔记录上增加数量
						ZgTorderPlanbom pbom=planBomNotAuditList.get(0);
						pbom.setWaitBackNum(pbom.getWaitBackNum()+needWaitBackNum-oldWaitBackNum);
						zgTorderPlanbomDao.update(pbom);
					}else {
						genereteNewBackPlanInfo(planbom, needWaitBackNum,planNotAudit,materielLevel);
						
						return;
					}
					
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
	 * 生成新的退料计划
	 * 	插入orderplan
	 * 插入orderplanbom
	 * @param planbom
	 * @param needWaitBackNum
	 */
	private void genereteNewBackPlanInfo(ZgTorderPlanbom planbom,	Long needWaitBackNum,ZgTorderPlan planNotAudit,String materelLevel) {
		ZgTorderPlan oldPlan=getZgTorderPlanBo().getById(planbom.getOrderPlanId());
		ZgTorderTask task=zgTorderTaskBo.getById(oldPlan.getOrderTaskId());
		String orderPlanId="";
		if(planNotAudit==null){//空计划则插入
			//生成领料计划
			ZgTorderPlan  backPlan=(ZgTorderPlan) BeanUtils.cloneBean(oldPlan);
			backPlan.setCreate_date(Calendar.getInstance().getTime());
			backPlan.setUserId(Constants.SYSTEMUSER);
			backPlan.setOrderTaskId(planbom.getOrderTaskId());
			backPlan.setPlanType(oldPlan.getPlanType()+"BACK");
			backPlan.setState(Constants.ChangePlanStatus.WAITAUDITING.value());
			backPlan.setExtend1(materelLevel);
			backPlan.setZbz("BOM物料替换变更退料");
			
			
			try {
				orderPlanId=(String) getZgTorderPlanBo().save(backPlan);
			} catch (Exception e) {//并发的时候插入偶尔会不成功,主鍵重复
				orderPlanId=(String) zgTorderPlanDao.save(backPlan);
			}
			
			//插入计划分组表
			ZgTorderPlanGroup group=new ZgTorderPlanGroup();
			group.setLabelCn(task.getAufnr());
			group.setState(Constants.ChangePlanStatus.WAITAUDITING.value());
			group.setPercent(0d);
			group.setPlanType(oldPlan.getPlanType()+"BACK");
			group.setPsbh(10000l);
			group.setIndexNo(10000l);
			String groupId=(String) zgTorderPlanGroupBo.save(group);
			
			
			ZgTgroupOrderPlan gop=new ZgTgroupOrderPlan();
			gop.setOrderPlanId(orderPlanId);
			gop.setGroupId(groupId);
			zgTgroupOrderPlanBo.save(gop);
			
		}else {
			orderPlanId=planNotAudit.getCuid();
		}
		
		ZgTorderPlanbom newBom=new ZgTorderPlanbom();
		newBom=(ZgTorderPlanbom) BeanUtils.cloneBean(planbom);
		newBom.setParentId(planbom.getCuid());
		newBom.setOrderPlanId(orderPlanId);
		newBom.setWaitBackNum(needWaitBackNum);
		newBom.setCarNum(0l);
		newBom.setCompleteNum(0l);
		newBom.setPlanNum(0l);
		newBom.setMoveNum(0l);
		newBom.setMoveNumIn(0l);
		newBom.setBackNum(0l);
		newBom.setOutNum(0l);
		newBom.setStorageNum(0l);
		newBom.setBomType(Constants.QUALITY);
		save(newBom);
		
		newBom.setBomType(Constants.NOTQUALITY);
		newBom.setWaitBackNum(0l);
		save(newBom);
	}
	
	
	/**
	 * 获取没有审核的工单变更引起的退料信息
	 * @param cuid
	 * @return
	 */
	private List<ZgTorderPlanbom> getAutoWaitBackBomListNotAuditByPbId(
			String planBomId,String orderPlanId) {
		Map paramsMap=new HashMap<String, Object>();
		paramsMap.put("planBomId", planBomId);
		paramsMap.put("orderPlanId", orderPlanId);
		return zgTorderPlanbomDao.getAutoWaitBackBomListByPbId(paramsMap);
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
	/**
	 * @return the zgTorderTaskBo
	 */
	public ZgTorderTaskBo getZgTorderTaskBo() {
		return zgTorderTaskBo;
	}
	/**
	 * @param zgTorderTaskBo the zgTorderTaskBo to set
	 */
	public void setZgTorderTaskBo(ZgTorderTaskBo zgTorderTaskBo) {
		this.zgTorderTaskBo = zgTorderTaskBo;
	}
	/**
	 * @return the zgTorderPlanGroupBo
	 */
	public ZgTorderPlanGroupBo getZgTorderPlanGroupBo() {
		return zgTorderPlanGroupBo;
	}
	/**
	 * @param zgTorderPlanGroupBo the zgTorderPlanGroupBo to set
	 */
	public void setZgTorderPlanGroupBo(ZgTorderPlanGroupBo zgTorderPlanGroupBo) {
		this.zgTorderPlanGroupBo = zgTorderPlanGroupBo;
	}
	/**
	 * @return the zgTgroupOrderPlanBo
	 */
	public ZgTgroupOrderPlanBo getZgTgroupOrderPlanBo() {
		return zgTgroupOrderPlanBo;
	}
	/**
	 * @param zgTgroupOrderPlanBo the zgTgroupOrderPlanBo to set
	 */
	public void setZgTgroupOrderPlanBo(ZgTgroupOrderPlanBo zgTgroupOrderPlanBo) {
		this.zgTgroupOrderPlanBo = zgTgroupOrderPlanBo;
	}
	/**
	 * @return the zgMaterielBo
	 */
	public ZgMaterielBo getZgMaterielBo() {
		return zgMaterielBo;
	}
	/**
	 * @param zgMaterielBo the zgMaterielBo to set
	 */
	public void setZgMaterielBo(ZgMaterielBo zgMaterielBo) {
		this.zgMaterielBo = zgMaterielBo;
	}
	

	
}
