package com.boco.zg.plan.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;

public class ZgTorderPlanbomDwrAction extends BaseDwrAction {
	
	private static final Log log=LogFactory.getLog(ZgTorderPlanbomDwrAction.class);
	
	private ZgTorderPlanbomExBo getZgTorderPlanbomExBo() {
		return (ZgTorderPlanbomExBo)ApplicationContextHolder.getBean("zgTorderPlanbomExBo");
	}
	
	
	private ZgTorderPlanExBo getZgTorderPlanExBo() {
		return (ZgTorderPlanExBo)ApplicationContextHolder.getBean("zgTorderPlanExBo");
	}
	
	private ZgTorderPlanbomBo getZgTorderPlanbomBo() {
		return (ZgTorderPlanbomBo)ApplicationContextHolder.getBean("zgTorderPlanbomBo");
	}
	
	
	public boolean checkOrderPlanSaved(String orderPlanId) {
		boolean flag = true;
		List<ZgTorderPlanbomEx> list = this.getZgTorderPlanbomExBo().findBomList(orderPlanId);
		for(ZgTorderPlanbomEx obj:list) {
			if(obj.getDepartmentId() == null
					||obj.getPlanDate() == null
					||obj.getPlanStartTime() == null
					||obj.getPlanEndTime() == null) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public boolean saveZgTorderPlanbom(String objcetJOSNs,String orderPlanId){
		//设置日期转换的格式  
		java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy-MM-dd");  
		boolean flag = true;
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				
				String strDate=(String)jsonObject.get("planDate");  
				Date newDate=null;  
				try {  
				     newDate = formate.parse(strDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 移除原有的planDate属性
				jsonObject.remove("planDate");
				// 将日期类型的planDate放到jsonObject中
				jsonObject.put("planDate", newDate);  
				
				
				JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy/MM/dd"}) );
				ZgTorderPlanbom obj = (ZgTorderPlanbom)JSONObject.toBean(jsonObject, ZgTorderPlanbom.class);  
				ZgTorderPlanbom entity = new ZgTorderPlanbom();
				entity.setCuid(obj.getCuid());
				entity.setDepartmentId(StringUtils.trimToNull(obj.getDepartmentId()));
				entity.setUserId(StringUtils.trimToNull(obj.getUserId()));
				entity.setPlanDate(obj.getPlanDate());
				entity.setPlanStartTime(StringUtils.trimToNull(obj.getPlanStartTime()));
				entity.setPlanEndTime(StringUtils.trimToNull(obj.getPlanEndTime()));
				entity.setState("8");
				getZgTorderPlanbomExBo().updateOrderPlan(entity);
			}
			getZgTorderPlanExBo().updateOrderPlanState(orderPlanId, "8");
			
			getZgTorderPlanExBo().updateOrderDeptId(orderPlanId);
			
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	


	private List<ZgTorderPlanbom> getZgTorderPlanbomListByBomIdAndPlanId(
			String orderPlanId, String bomId) {
		ZgTorderPlanbom entity = new ZgTorderPlanbom();
		entity.setOrderPlanId(orderPlanId);
		entity.setBomId(bomId);
		return this.getZgTorderPlanbomBo().findByProperty(entity);
	}
	
	/**
	 * 修改批量领料计划的bom组件数量
	 * 修改的数据只是保存在session中
	 * 逻辑思路: 返回值 
	 *          1：批量计划单为空的
	 *          2：批量计划单领用的BOM的数量有为0的
	 *          3: 有领料人有为空的
	 *          4: 有领料日期为空的
	 *          5：有开始时间为空的
	 *          6: 有结束时间为空的
	 *          9: 其它异常
	 *          10：一切正常
	 * @param objcetJOSNs
	 * @param orderPlanId
	 * @param session
	 * @return
	 */
	public int updateZgTorderPlanbomCarNum(String objcetJOSNs,HttpSession session){
		int flag=10;
		List<ZgTorderPlanbomEx> list=(List<ZgTorderPlanbomEx>) session.getAttribute("bomForBatchList");
		try{
			//如果有东西的时候才去执行
			if(list.size()!=0){
				JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
				for(int i=0;i<josnArray.size();i++){
					JSONObject jsonObject = (JSONObject)josnArray.get(i);
					ZgTorderPlanbomEx zgTorderPlanbomEx = (ZgTorderPlanbomEx)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class); 
					for (ZgTorderPlanbomEx obj:list) {
						if(obj.getCuid().equals(zgTorderPlanbomEx.getCuid())){
							obj.setCarNum(zgTorderPlanbomEx.getCarNum());
							obj.setDepartmentId(zgTorderPlanbomEx.getDepartmentId());
							obj.setUserId(zgTorderPlanbomEx.getUserId());
							obj.setDepartmentId_labelCn(zgTorderPlanbomEx.getDepartmentId_labelCn());
							obj.setUserId_labelCn(zgTorderPlanbomEx.getUserId_labelCn());
							obj.setPlanDate(zgTorderPlanbomEx.getPlanDate());
							obj.setPlanStartTime(zgTorderPlanbomEx.getPlanStartTime());
							obj.setPlanEndTime(zgTorderPlanbomEx.getPlanEndTime());
							obj.setIsModity(true);
							obj.setMeins(zgTorderPlanbomEx.getMeins());
							obj.setMsehl1(zgTorderPlanbomEx.getMsehl1());
							break;
						}
					}
				}
				//进行判断批量计划单中是否有BOM组件的数量为0
				for(ZgTorderPlanbomEx obj:list){
					if(!obj.getIsDel()){
						if(obj.getCarNum()==0){//有数量为0
							flag=2;
							break;
						}
						if(obj.getUserId()==null||obj.getUserId().equals("")){//有领料人为空
							flag=3;
							break;
						}
						if(obj.getPlanDate()==null||obj.getPlanDate().equals("")){//有领料日期为空的
							flag=4;
							break;
						}
						if(obj.getPlanStartTime()==null||obj.getPlanStartTime().equals("")){//有开始时间为空的
							flag=5;
							break;
						}
						if(obj.getPlanEndTime()==null||obj.getPlanEndTime().equals("")){//有结束时间为空的
							flag=6;
							break;
						}
					}
				}
			}else{
				flag=1;
			}
		}catch(Exception e){
			flag = 9;
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 批量领料计划 添加bom组件
	 * @param objcetJOSNs
	 * @param orderPlanId
	 * @param session
	 * @return
	 */
	public boolean addPlanbomByCar(String objcetJOSNs,String orderPlanId,HttpSession session){
		boolean flag = true;
		List<ZgTorderPlanbomEx> 	list=(List<ZgTorderPlanbomEx>) session.getAttribute("bomForBatchList");
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderPlanbomEx zgTorderPlanbomEx = (ZgTorderPlanbomEx)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class);  
				boolean result=false;
				for (ZgTorderPlanbomEx obj : list) {//遍历原来的列表中有没有该bom组件
					if(obj.getBomId().equals(zgTorderPlanbomEx.getBomId())){
						obj.setIsDel(false);
						obj.setIsModity(true);
						result=true;
						break;
					}
				}
				
				if(!result){
					zgTorderPlanbomEx.setCuid(getZgTorderPlanbomExBo().getCUID());
					zgTorderPlanbomEx.setIsModity(true);
					zgTorderPlanbomEx.setCarNum(0l);
					
					list.add(zgTorderPlanbomEx);
				}
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		
		setSessionBomBatchIds(session,list);
		
		return flag;
	}
	
	/**
	 * 批量领料计划 删除bom组件
	 * @param objcetJOSNs
	 * @param orderPlanId
	 * @param session
	 * @return
	 */
	public boolean deletePlanbomByCar(String objcetJOSNs,String orderPlanId,HttpSession session){
		boolean flag = true;
		List<ZgTorderPlanbomEx> 	list=(List<ZgTorderPlanbomEx>) session.getAttribute("bomForBatchList");
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderPlanbomEx zgTorderPlanbomEx = (ZgTorderPlanbomEx)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class);  
				for (int j=0;j<list.size();j++) {//遍历原来的列表中有没有该bom组件
					ZgTorderPlanbomEx obj=list.get(j);
					if(obj.getBomId().equals(zgTorderPlanbomEx.getBomId())){
						obj.setIsDel(true);
						obj.setIsModity(true);
						break;
					}
				}
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		
		setSessionBomBatchIds(session,list);
		
		return flag;
	}
	
	/**
	 * 批量领料计划 删除bom组件
	 * @param objcetJOSNs
	 * @param orderPlanId
	 * @param session
	 * @return
	 */
	public boolean deletePlanbomByCuid(String objcetJOSNs,String orderPlanId,HttpSession session){
		boolean flag = true;
		List<ZgTorderPlanbomEx> 	list=(List<ZgTorderPlanbomEx>) session.getAttribute("bomForBatchList");
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderPlanbomEx zgTorderPlanbomEx = (ZgTorderPlanbomEx)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class);  
				for (int j=0;j<list.size();j++) {//遍历原来的列表中有没有该bom组件
					ZgTorderPlanbomEx obj=list.get(j);
					if(obj.getCuid().equals(zgTorderPlanbomEx.getCuid())){
						obj.setIsDel(true);
						obj.setIsModity(true);
						break;
					}
				}
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		
		setSessionBomBatchIds(session,list);
		
		return flag;
	}
	
	/**
	 * 设置批量领料的组件id，用过展现时过滤
	 * @param session
	 * @param list
	 */
	public void setSessionBomBatchIds(HttpSession session,List<ZgTorderPlanbomEx> list){
		String bomBatchIds="";
		for (ZgTorderPlanbomEx obj : list) {
			if(!obj.getIsDel()){
				bomBatchIds=bomBatchIds+obj.getBomId()+",";
			}
		}
		session.setAttribute("bomBatchIds",bomBatchIds);
	}
	
	public boolean addOrderBomByCar(String objcetJOSNs,String orderPlanId,HttpSession session){
		boolean flag = true;
		List<ZgTorderbom> 	list=(List<ZgTorderbom>) session.getAttribute("orderBomForCar");
		if(list==null || list.size()==0){
			list = new ArrayList();
			session.setAttribute("orderBomForCar", list);
		}
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderbom zgTorderbom = (ZgTorderbom)JSONObject.toBean(jsonObject, ZgTorderbom.class);  
				boolean result=false;
				for (ZgTorderbom obj : list) {//遍历原来的列表中有没有该bom组件
					if(obj.getCuid().equals(zgTorderbom.getCuid())){
						result=true;
						break;
					}
				}
				if(!result){
					list.add(zgTorderbom);
				}
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteOrderBomByCar(String objcetJOSNs,String orderPlanId,HttpSession session){
		boolean flag = true;
		List<ZgTorderPlanbomEx> 	list=(List<ZgTorderPlanbomEx>) session.getAttribute("orderBomForCar");
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderbom zgTorderbom = (ZgTorderbom)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class);  ;  
				for (int j=0;j<list.size();j++) {//遍历原来的列表中有没有该bom组件
					ZgTorderPlanbomEx obj=list.get(j);
					if(obj.getCuid().equals(zgTorderbom.getCuid())){
						list.remove(obj);
						break;
					}
				}
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 领料计划生成仓库领料单时候 校验该领料计划的领料信息是否已经填写完毕
	 * @param orderPlanId
	 * @return
	 */
	public boolean checkOrderPlanDetailInfo(String orderPlanId) {
		boolean flag = true;
		List<ZgTorderPlanbomEx> list = this.getZgTorderPlanbomExBo().findBomList(orderPlanId);
		for(ZgTorderPlanbomEx obj:list) {
			if(obj.getUserId()==null||obj.getDepartmentId() == null
					||obj.getPlanDate() == null
					||obj.getPlanStartTime() == null
					||obj.getPlanEndTime() == null) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 批量领料计划保存审核数量
	 * @param objcetJOSNs
	 * @param orderPlanId
	 * @param session
	 * @return
	 */
	public boolean saveAuditNumForBatchplan(String objcetJOSNs){
		boolean flag = true;
		try{
			JSONArray josnArray = JSONArray.fromObject(objcetJOSNs);
			for(int i=0;i<josnArray.size();i++){
				JSONObject jsonObject = (JSONObject)josnArray.get(i);
				ZgTorderPlanbomEx zgTorderPlanbomEx = (ZgTorderPlanbomEx)JSONObject.toBean(jsonObject, ZgTorderPlanbomEx.class);  
				getZgTorderPlanbomExBo().updateAuditNum(zgTorderPlanbomEx);
			}
		}catch(Exception e){
			flag = false;
			log.error("更新审核数量时出错:"+e);
		}
		
		return flag;
	}
	
}
