package com.boco.zg.plan.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javacommon.util.StringHelper;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.dao.ZgTcarbomDao;
import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.zg.plan.base.model.ZgTcarbomSuppliers;
import com.boco.zg.plan.base.model.ZgTcarplan;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.service.ZgTcarbomBo;
import com.boco.zg.plan.base.service.ZgTcarbomSuppliersBo;
import com.boco.zg.plan.base.service.ZgTcarplanBo;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.base.service.ZgTorderSuppliersBo;
import com.boco.zg.plan.dao.ZgTcarbomExDao;
import com.boco.zg.plan.dao.ZgTorderbomExDao;
import com.boco.zg.plan.model.ZgTcarbomEx;
import com.boco.zg.util.Constants;

@Component
public class ZgTcarbomExBo extends ZgTcarbomBo{
	
	private ZgTcarplanBo zgTcarplanBo;
	private ZgTcarbomBo zgTcarbomBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	private ZgTorderPlanbomBo zgTorderPlanbomBo;
	
	private ZgTcarbomExDao zgTcarbomExDao;
	
	private ZgTcarplanExBo zgTcarplanExBo;
	
	private ZgTcarbomSuppliersBo zgTcarbomSuppliersBo;
	
	private ZgTorderbomExDao zgTorderbomExDao;
	
	private ZgTorderSuppliersBo zgTorderSuppliersBo;
	
	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}
	
	public List<Map> findBomDEByCarPlanId(String carPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ob.matnr1 as MATNR                                       ");
		sql.append("  from zg_t_order o,zg_t_orderbom ob,zg_t_order_planbom pb,zg_t_carbom cb");
		sql.append(" where o.cuid = ob.order_id                                              ");
		sql.append("   and ob.cuid = pb.order_bom_id                                         ");
		sql.append("   and pb.cuid = cb.order_planbom_id                                     ");
		sql.append("   and cb.car_plan_id = '"+carPlanId+"'                                  ");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	public List<Map> findBomByMatnrs(String carPlanId,String[] matnrs) {
		String s = "";
		for(String matnr : matnrs) {
			s += "'"+matnr+"',";
		}
		s = s.substring(0,s.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("select cb.cuid,                                                           ");
		sql.append("       o.aufnr,                                                           ");
		sql.append("       o.pxdat,                                                           ");
		sql.append("       o.kdauf,                                                           ");
		sql.append("       o.plant,                                                           ");
		sql.append("       o.arbpl,                                                           ");
		sql.append("       ob.matnr,                                                          ");
		sql.append("      ob.maktx2 maktx1,                                                         ");
		sql.append("       ob.msehl1,                                                         ");
		sql.append("       nvl(pb.car_num,0)-nvl(pb.plan_num,0) menge,                                                          ");
		sql.append("       pb.complete_num,                                                   ");
		sql.append("       pb.car_num,                                                        ");
		sql.append("       cb.plan_num,                                                       ");
		sql.append("       cb.real_num  , cb.car_plan_id , cb.order_planbom_id                                                    ");
		sql.append("  from zg_t_order o, zg_t_orderbom ob,zg_t_order_planbom pb,zg_t_carbom cb");
		sql.append(" where o.cuid = ob.order_id                                               ");
		sql.append("   and ob.cuid = pb.order_bom_id                                          ");
		sql.append("   and pb.cuid = cb.order_planbom_id                                      ");
		sql.append("   and ob.matnr1 in ("+s+")                                               ");
		sql.append("   and cb.car_plan_id = '"+carPlanId+"'                                   ");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	public List<Map> findBomByCarPlanId(String carPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select cb.cuid,                                                           ");
		sql.append("       o.aufnr,                                                           ");
		sql.append("       o.pxdat,                                                           ");
		sql.append("       o.kdauf,                                                           ");
		sql.append("       o.plant,                                                           ");
		sql.append("       o.arbpl,                                                           ");
		sql.append("       ob.matnr,                                                          ");
		sql.append("       ob.maktx2 maktx1,                                                         ");
		sql.append("       ob.msehl1,                                                         ");
		sql.append("       ob.idnrk,                                                          ");
		sql.append("       nvl(pb.car_num,0)-nvl(pb.plan_num,0)+cb.plan_num menge,            ");
		//sql.append("       ob.menge,                                                          ");
		sql.append("       pb.plan_num,                                                       ");
		sql.append("       pb.complete_num,                                                   ");
		sql.append("       pb.car_num,                                                        ");
		sql.append("       pb.cuid as order_planbom_id,                                       ");
		sql.append("       cb.plan_num as car_plan_num,                                       ");
		sql.append("       cb.real_num  , cb.car_plan_id                                      ");
		sql.append("  from zg_t_order o, zg_t_orderbom ob,zg_t_order_planbom pb,zg_t_carbom cb");
		sql.append(" where o.cuid = ob.order_id                                               ");
		sql.append("   and ob.cuid = pb.order_bom_id                                          ");
		sql.append("   and pb.cuid = cb.order_planbom_id                                      ");
		sql.append("   and cb.car_plan_id = '"+carPlanId+"'                                   ");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	public List<Map> findCarPlanBomByCarPlanId(String carPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select cb.cuid,                                                           ");//修改数据时需要参数
		sql.append("       pb.cuid as order_planbom_id,                                       ");//修改数据时需要参数
		sql.append("       cb.car_plan_id,                                                    ");//修改数据时需要参数
		sql.append("       o.aufnr,                                                           ");//呈现：订单编号
		sql.append("       pb.car_num,                                                        ");//呈现：需求数量
		sql.append("       pb.complete_num,                                                   ");//呈现：完成数量
		sql.append("       decode(sign(pb.car_num-pb.complete_num),-1,                            ");//呈现：本次需求数量
		sql.append("       0,nvl(pb.car_num,0)-nvl(pb.plan_num,0)+cb.plan_num) max_value,     ");
		sql.append("       cb.plan_num as car_plan_num,                                       ");//呈现：计划领取数量
		sql.append("       o.pxdat,                                                           ");//呈现：排序日期
		sql.append("       o.kdauf,                                                           ");//呈现：销售订单号
		sql.append("       o.plant,                                                           ");//呈现：生产厂
		sql.append("       o.arbpl,                                                           ");//呈现：生产线
		sql.append("       ob.matnr,                                                          ");//呈现：物料号
		sql.append("       ob.maktx2 maktx1,                                                         ");//呈现：物料描述
		sql.append("       ob.msehl1,                                                         ");//呈现：度量单位
		sql.append("       ob.idnrk,                                                          ");//展现逻辑：分组表示
		sql.append("       fo.label_cn                                                           ");
		sql.append("  from zg_t_order o, zg_t_orderbom ob,zg_t_order_planbom pb,zg_t_carbom cb,fw_organization fo,zg_t_order_taskbom taskbom");
		sql.append(" where o.cuid = ob.order_id                                               ");
		sql.append("    and ob.cuid=taskbom.order_bom_id   and pb.taskbom_id=taskbom.cuid                                         ");
		sql.append("   and pb.cuid = cb.order_planbom_id                                      ");
		sql.append("   and cb.car_plan_id = '"+carPlanId+"'                                   ");
		sql.append("   and o.arbpl=fo.cuid(+)                                                 ");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	public List<Map> findStoragePlanBomByCarPlanId(String carPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select cb.cuid,                                                           ");//修改数据时需要参数
		sql.append("       pb.cuid as order_planbom_id,                                       ");//修改数据时需要参数
		sql.append("       cb.car_plan_id,                                                    ");//修改数据时需要参数
		sql.append("       o.aufnr,                                                           ");//呈现：订单编号
		sql.append("       pb.car_num,                                                        ");//呈现：总需求数量
		sql.append("       pb.complete_num,                                                   ");//呈现：仓库领料完成数量
		sql.append("       decode(sign(pb.car_num - pb.complete_num),-1,                      ");//呈现：可领取数量
		sql.append("       0,decode(sign(pb.car_num - pb.complete_num - cb.plan_num),-1,      ");
		sql.append("       pb.car_num - pb.complete_num,cb.plan_num)) as max_value,           ");
		sql.append("       cb.plan_num as car_plan_num,                                       ");//呈现：装车计划领取数量
		sql.append("       cb.real_num as real_num,                                           ");//呈现：实际领取数量
		sql.append("       o.pxdat,                                                           ");//呈现：排序日期
		sql.append("       o.kdauf,                                                           ");//呈现：销售订单号
		sql.append("       o.plant,                                                           ");//呈现：生产厂
		sql.append("       o.arbpl,                                                           ");//呈现：生产线
		sql.append("       ob.matnr,                                                          ");//呈现：物料号
		sql.append("       ob.maktx2 maktx1,                                                         ");//呈现：物料描述
		sql.append("       ob.msehl1,                                                         ");//呈现：度量单位
		sql.append("       ob.idnrk,                                                          ");//展现逻辑：分组表示
		sql.append("       fo.label_cn                                                        ");//生产线的中文名
		sql.append("  from zg_t_order o, zg_t_orderbom ob,zg_t_order_planbom pb,zg_t_carbom cb,fw_organization fo");
		sql.append(" where o.cuid = ob.order_id                                               ");
		sql.append("   and ob.cuid = pb.order_bom_id                                          ");
		sql.append("   and pb.cuid = cb.order_planbom_id                                      ");
		sql.append("   and cb.car_plan_id = '"+carPlanId+"'                                   ");
		sql.append("   and o.arbpl=fo.cuid(+)                                                 ");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}
	
	/**
	 * 修改装车计划数量
	 * @param carbomList
	 * @param isPlanNumChange 计划领料数量是否需要改变（针对订单领料：领料计划保存时，计划领料数量需要改变，领料计划确认提交时，不需要改变）
	 */
	public void updateCarboms(String orderPlanBomIds,List<ZgTcarbomEx> carbomList,boolean isPlanNumChange) {
		if(carbomList != null && carbomList.size()>0) {
			
			for(ZgTcarbomEx bom : carbomList) {
				if(StringHelper.isEmpty(bom.getCuid())) continue ;
				
				if((!StringHelper.isEmpty(orderPlanBomIds))&&(!orderPlanBomIds.contains(bom.getOrderPlanbomId()))){
					if(log.isInfoEnabled()){
						log.info("提交领料计划时　领料计划删除BOM：orderplabbomid:"+bom.getOrderPlanbomId()+"  carbomId:"+bom.getCuid());
					}
					zgTcarplanExBo.deleteBom(bom.getCuid(), bom.getOrderPlanbomId());
					continue;
				}
				
				if(null!=bom){
					String cuid = bom.getCuid();
					ZgTcarbom newbom = this.getById(cuid);
					Long planNum = bom.getPlanNum();
					Long realNum = bom.getRealNum();
					if(planNum != null) {
						if(isPlanNumChange){
							newbom.setPlanNum(planNum);
						}
						
					}
					if(realNum != null) {
						newbom.setRealNum(realNum);
					}
					this.update(newbom);
					
					if(null!=bom.getSupList()){
						//保存具体供应商的bom数量
						for(ZgTcarbomSuppliers sup:bom.getSupList()){
							if(!StringHelper.isEmpty(sup.getCuid())) {
								zgTcarbomSuppliersBo.update(sup);
							}else {
								if(sup.getCarNum()!=null){
									sup.setCarBomId(cuid);
									zgTcarbomSuppliersBo.save(sup);
								}
								
							}
							
						}
					}
					
				}
			}
		}else {
			throw new RuntimeException("装车bom为空！");
		}
	}
	
	/**
	 * 生成装车计划时，原来已经存在的bom列表处理
	 * 处理逻辑:
	 * 		orderplanbom表:plan_num=plan_num-(c_plan_num(旧)-c_plan_num(新))  注： c_plan_num为装车计划bom表中的计划装车数量   
	 * 		carbom表:plan_num=15; 
	 * 		更改 carplan中的计划状态，如果为保存，则为保存状态  如果为提交，则type变为2(变成领料计划) 状态置为0（未处理状态）   
	 * @param carbomList
	 * @param state
	 */
	public void saveEditCarPlan(List<ZgTcarbomEx> carbomList, String state,String planType) {
		if(carbomList != null && carbomList.size()>0) {
			String carPlanId = carbomList.get(0).getCarPlanId();
			ZgTcarplan carplan = zgTcarplanBo.getById(carPlanId);
			for(ZgTcarbomEx bom : carbomList) {
				String cuid = bom.getCuid();
				ZgTcarbom oldBom = this.getById(cuid);
				
				
				if(Constants.OrderPlanType.BACK.value().equals(planType)){//退料时不用对计划领料数量做相应的变化
				}else {//
					//修改ZgTorderPlanbom中的bom表记录plan_num=plan_num-(c_plan_num(旧)-c_plan_num(新))  注： c_plan_num为装车计划bom表中的计划装车数量  
					String orderPlanbomId = bom.getOrderPlanbomId();
					ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(orderPlanbomId);
					Long planNum = planbom.getPlanNum()-(oldBom.getPlanNum()-bom.getPlanNum());
					planbom.setPlanNum(planNum);
					if(Constants.CarPlanStatus.SUBMIT.value().equals(state)){//提交
						planbom.setState(Constants.CarPlanStatus.SUBMIT.value());
					}
					zgTorderPlanbomBo.update(planbom);
				}
				
				
				//更新carbom表的计划领数量
				Long  planNum = bom.getPlanNum();
				Long realNum = bom.getRealNum();
				if(planNum != null) {
					oldBom.setPlanNum(planNum);
				}
				if(realNum != null) {
					oldBom.setRealNum(realNum);
				}
				this.update(oldBom);
				
				if(null!=bom.getSupList()){
					//保存具体供应商的bom数量
					for(ZgTcarbomSuppliers sup:bom.getSupList()){
						if(sup.getCarNum()==null){
							sup.setCarNum(0l);
						}
						zgTcarbomSuppliersBo.update(sup);
					}
				}
				
			}
			if(Constants.CarPlanStatus.SAVE.value().equals(state)){//保存
				carplan.setCarState(state);
			}else {//提交
				carplan.setCarState(Constants.CarPlanStatus.NEW.value());
				carplan.setType(Constants.CarPlanType.STOREGETDATA.value());
			}
			zgTcarplanBo.update(carplan);
		}else {
		//	throw new RuntimeException("装车bom为空！");
		}
	}
	
	/**
	 * 生成装车计划时，新追加的bom
	 * 处理逻辑:
	 * 		orderplanbom表:plan_num=plan_num-c_plan_num  注： c_plan_num为装车计划bom表中的计划装车数量   
	 * @param carbomList
	 * @param state
	 */
	public void saveNewCarPlan(List<ZgTcarbomEx> carbomList, String state,String planType) {
		for(ZgTcarbomEx bom:carbomList) {
			//仓库
			ZgTorderPlanbom planbom = zgTorderPlanbomBo.getById(bom.getOrderPlanbomId());
			
			String carBomId=(String)save(bom);
			
			if(log.isInfoEnabled()){
				log.info("OrderPlanbomId:"+bom.getOrderPlanbomId()+" 添加装车计划BOM(carBomId):"+carBomId);
			}
			
			if(null!=bom.getSupList()){
				//保存具体供应商bom数量
				for (ZgTcarbomSuppliers sup:bom.getSupList()) {
					if(sup.getCarNum()==null){
						sup.setCarNum(0l);
					}
					sup.setCarBomId(carBomId);
					zgTcarbomSuppliersBo.save(sup);
//					}
				}
			}
			
			if(Constants.OrderPlanType.BACK.value().equals(planType)){
			}else {
				//修改orderplanbom表的数量和状态
				planbom.setPlanNum(planbom.getPlanNum()+bom.getPlanNum());
				zgTorderPlanbomBo.update(planbom);
			}
			
		}
	}
	
	

	public void setZgTcarbomBo(ZgTcarbomBo zgTcarbomBo) {
		this.zgTcarbomBo = zgTcarbomBo;
	}

	/**
	 * 批量装车计划 根据装车计划id查询bom组件
	 * @param carPlanId
	 * @return
	 */
	public List<Map> findBomList(String carPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select cb.cuid,b.label_cn, b.maktx, b.MSEHL,b.idnrk, cb.plan_num, cb.real_num, pb.zbz     ");                                                    
		sql.append("   from zg_t_carbom cb, zg_t_bom b, zg_t_order_planbom pb                 ");                                      
		sql.append("       where cb.car_plan_id = '"+ carPlanId +"'                             ");
		sql.append("         and cb.order_planbom_id = pb.cuid  and pb.bom_id = b.cuid        ");                                                    
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}

	public void updateCarbomsForBatch(List<ZgTcarbom> carbomList) {
		// TODO Auto-generated method stub
		
	}

	public void setZgTcarplanBo(ZgTcarplanBo zgTcarplanBo) {
		this.zgTcarplanBo = zgTcarplanBo;
	}

	public void setZgTorderPlanbomBo(ZgTorderPlanbomBo zgTorderPlanbomBo) {
		this.zgTorderPlanbomBo = zgTorderPlanbomBo;
	}
	
	/**
	 * 	预装，总装，预焊生成装车计划时候对bom组件的状态校验
	 * @param orderPlanBomIds
	 * @return
	 */
	public boolean checkStateForGenerateCarPlan(String orderPlanBomIds,int size) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from zg_t_order_planbom t where t.cuid in ('"+orderPlanBomIds+"') and t.state!='9'    ");                                                    
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString()).size()==size;
	}

	/**
	 * 根据编号查找领料计划bom表记录
	 * @param orderPlanBomIds
	 * @return
	 */
	public List<Map> getZgTcatBomListByIds(String orderPlanBomIds) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,nvl(t.car_num,0)-nvl(t.plan_num,0) mengeNum  from zg_t_order_planbom t where t.cuid in ('"+orderPlanBomIds+"') ");                                                    
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
	}

	/**
	 * 根据
	 * @param cuids
	 * @return
	 */
	public List<Map> getrPlanbomList(String cuids) {
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select t.cuid,t.car_num,nvl(t.plan_num,0) plan_num,nvl(t.complete_num,0) complete_num from zg_t_order_planbom t where t.cuid   in ('"+cuids+"')");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sqlBuffer.toString());
	}

	/**
	 * 根据装车计划编号删除其所属bom
	 * @param carPlanId
	 */
	public void deleteBomByCarPlanId(String carPlanId) {
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("delete from zg_t_carbom t where t.car_plan_id='"+carPlanId+"'");
		((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sqlBuffer.toString());
	}

	public Object  save(ZgTcarbomEx bom) {
		return zgTcarbomExDao.save(bom);
	}

	public ZgTcarbomExDao getZgTcarbomExDao() {
		return zgTcarbomExDao;
	}

	public void setZgTcarbomExDao(ZgTcarbomExDao zgTcarbomExDao) {
		this.zgTcarbomExDao = zgTcarbomExDao;
	}
	
	public List<Map> getCarBomList(String cuids) {
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select * from zg_t_carbom t where t.cuid in ('"+cuids+"')");
		return ((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sqlBuffer.toString());
	}

	/**
	 * 检查数量是否大于需求数量
	 * @param orderPlanbomId
	 * @param carPlanNum
	 * @return num 为超出的数量
	 */
	public Long checkForBomCarNum(String orderPlanbomId, Long carPlanNum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select t.car_num- (nvl(t.complete_num,0)+"+carPlanNum+") num from zg_t_order_planbom t  where t.cuid='"+orderPlanbomId+"'");
		List<Map> list=((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()>0){
			return Long.parseLong(list.get(0).get("NUM").toString());
		}
		return 0l;
	}
	
	/**
	 * 检查退料数量是否大于待退料数量
	 * @param orderPlanbomId
	 * @param carPlanNum
	 * @return num 为超出的数量
	 */
	public Long checkForBomCarNumForBack(String orderPlanbomId, Long carPlanNum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select nvl(t.wait_back_num,0)- "+carPlanNum+" num from zg_t_order_planbom t  where t.cuid='"+orderPlanbomId+"'");
		List<Map> list=((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sql.toString());
		if(list.size()>0){
			return Long.parseLong(list.get(0).get("NUM").toString());
		}
		return 0l;
	}

	public ZgTcarbomSuppliersBo getZgTcarbomSuppliersBo() {
		return zgTcarbomSuppliersBo;
	}

	public void setZgTcarbomSuppliersBo(ZgTcarbomSuppliersBo zgTcarbomSuppliersBo) {
		this.zgTcarbomSuppliersBo = zgTcarbomSuppliersBo;
	}

	/**
	 * 更新领料计划表领料状态
	 * @param carPlanId
	 * @param value
	 */
	public void updateZgtcarPlanState(String carPlanId, String state,Date carDate,String storageUserId) {
		ZgTcarplan zgTcarplan=new ZgTcarplan();
		zgTcarplan.setCarState(state);
		zgTcarplan.setCuid(carPlanId);
		zgTcarplan.setStorageUserId(storageUserId);
		zgTcarplan.setCarDate(carDate);
		zgTcarplanBo.updateState(zgTcarplan);
		
	}

	/**
	 * 根据装车计划编号 获取其下面的 bom物料
	 * @param carPlanId
	 * @return
	 */
	public List<Map> getBomLIstCarPlanId(String carPlanId) {
		return zgTorderbomExDao.getBomLIstCarPlanId(carPlanId);                                      
	}

	public ZgTorderbomExDao getZgTorderbomExDao() {
		return zgTorderbomExDao;
	}

	public void setZgTorderbomExDao(ZgTorderbomExDao zgTorderbomExDao) {
		this.zgTorderbomExDao = zgTorderbomExDao;
	}

	/**
	 * @param carbomList
	 * @param value
	 */
	public void saveSupBomNums(List<ZgTcarbomEx> carbomList, String value) {
		for(ZgTcarbomEx bom:carbomList) {
			if(null!=bom.getSupList()){
				//保存具体供应商的bom数量
				for(ZgTcarbomSuppliers sup:bom.getSupList()){
					if(sup.getCarNum()!=null){
						sup.setCarBomId(bom.getCuid());
						zgTcarbomSuppliersBo.save(sup);
					}
				}
			}
		}
		
	}

	/**
	 * 获取领料的供应商信息
	 * @param bomList
	 */
	public void doPressSupliers(List<Map> bomList) {
		//获取相就在的供应商信息
		if(bomList.size()>0){
			String carBomIds="";
			for(Map bom:bomList){
				carBomIds=carBomIds+bom.get("CUID").toString()+"','";
			}
			
			carBomIds=carBomIds.substring(0,carBomIds.length()-3);
				
			
			//获取供应商信息
			List<ZgTcarbomSuppliers> suppliersLst=zgTcarbomSuppliersBo.getCarBomSuppliersListByCarbomIds(carBomIds);
			
			for(Map bom:bomList){
				List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
				for(ZgTcarbomSuppliers sup:suppliersLst){
					if(bom.get("CUID").toString().equals(sup.getCarBomId())){
						tempList.add(sup);
					}
				}
				bom.put("subList", tempList);
			}
			
		}
	}

	/**
	 * 获取可选择的供应商信息
	 * @param bomList
	 * @param aufnrs
	 * @param idnrks
	 */
	public void doPressSupliers(List<Map> bomList, String aufnrs, String idnrks) {
		//获取供应商信息
		List<ZgTcarbomSuppliers> suppliersLst=zgTorderSuppliersBo.getBomSuppliersListByAufnrIdnrk(aufnrs,idnrks);
		
		for(Map bom:bomList){
			List<ZgTcarbomSuppliers> tempList=new ArrayList<ZgTcarbomSuppliers>();
			String aufnr1=bom.get("AUFNR").toString();
			String idnrk=bom.get("IDNRK").toString();
			for(ZgTcarbomSuppliers sup:suppliersLst){
				if(aufnr1.equals(sup.getAufnr())&&idnrk.equals(sup.getIdnrk())){
					tempList.add(sup);
				}
			}
			
			if(tempList.size()==1){//只有一个供应商，则默认该供应商数量和领料数量一样
				tempList.get(0).setCarNum(bom.get("CAR_PLAN_NUM")==null?0l:Long.parseLong(bom.get("CAR_PLAN_NUM").toString()));
			}
			bom.put("subList", tempList);
		}
		
	}

	public ZgTorderSuppliersBo getZgTorderSuppliersBo() {
		return zgTorderSuppliersBo;
	}

	public void setZgTorderSuppliersBo(ZgTorderSuppliersBo zgTorderSuppliersBo) {
		this.zgTorderSuppliersBo = zgTorderSuppliersBo;
	}

	/**
	 * 判断装车计划是否已经全部领完
	 * @param carPlanId
	 * @return
	 */
	public boolean isFinishedCarPlan(String carPlanId) {
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select * from zg_t_carbom t where t.car_plan_id='"+carPlanId+"' and t.storage_user_id is null");
		List<Map> list=((ZgTcarbomDao)this.getEntityDao()).findDynQuery(sqlBuffer.toString());
		return list.size()==0;
	}

	/**
	 * @return the zgTcarplanExBo
	 */
	public ZgTcarplanExBo getZgTcarplanExBo() {
		return zgTcarplanExBo;
	}

	/**
	 * @param zgTcarplanExBo the zgTcarplanExBo to set
	 */
	public void setZgTcarplanExBo(ZgTcarplanExBo zgTcarplanExBo) {
		this.zgTcarplanExBo = zgTcarplanExBo;
	}
}