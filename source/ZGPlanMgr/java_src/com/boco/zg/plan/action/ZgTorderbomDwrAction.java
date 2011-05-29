package com.boco.zg.plan.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.request.RequestScope;

import javacommon.base.BaseDwrAction;
import javacommon.util.StringHelper;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.zg.plan.base.model.ZgTorderPlan;
import com.boco.zg.plan.base.model.ZgTorderPlanGroup;
import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderPlanBo;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanGroupExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.storage.service.ZgTstorageExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.inject.Context;

public class ZgTorderbomDwrAction extends BaseDwrAction {

	private ZgTorderbomExBo getZgTorderbomExBo() {
		return (ZgTorderbomExBo) ApplicationContextHolder
				.getBean("zgTorderbomExBo");
	}
	

	private ZgTstorageExBo getZgTstorageExBo() {
		return (ZgTstorageExBo) ApplicationContextHolder
				.getBean("zgTstorageExBo");
	}
	
	private ZgTorderPlanExBo getZgTorderPlanExBo(){
		return (ZgTorderPlanExBo) ApplicationContextHolder.getBean("zgTorderPlanExBo");
	}
	
	private ZgTorderPlanGroupExBo getZgTorderPlanGroupExBo(){
		return (ZgTorderPlanGroupExBo) ApplicationContextHolder.getBean("zgTorderPlanGroupExBo");
	}
	
	private ZgTorderPlanBo getZgTorderPlanBo(){
		return (ZgTorderPlanBo) ApplicationContextHolder.getBean("zgTorderPlanBo");
	}
	
	private ZgTorderbomBo getzgTorderbomBo(){
		return (ZgTorderbomBo) ApplicationContextHolder.getBean("zgTorderbomBo");
	}
	
	

	public String changeSortF(String[] sortfs) {
		for (int j = 0; j < sortfs.length; j++) {
			if (!StringUtils.isBlank(sortfs[j])) {
				String[] s = sortfs[j].split("_");
				getZgTorderbomExBo().updateOrderBomSortf(s[0], s[1]);
			}
		}
		return "success";
	}
	
	/**
	 * 订单提交后修改他的制作标识
	 * 		1 修改bom制作标识
	 * 		2 如果已经生成领料计划的，要把该bom迁移到相应的类型领料计划中
	 * 		3 重新计划领料计划进度
	 * @param sortfs
	 * @return
	 */
	public String changeSortF1(String[] sortfs) {
		String orderId="";
		for (int j = 0; j < sortfs.length; j++) {
			if (!StringUtils.isBlank(sortfs[j])) {
				String[] s = sortfs[j].split("_");
				getZgTorderbomExBo().updateOrderBomSortf1(s[0], s[1]);
			}
		}
		if(sortfs.length>0){//重新计算领料进度
			String[] s = sortfs[0].split("_");
			ZgTorderbom orderbom = (ZgTorderbom) getzgTorderbomBo().getById(s[0]);
			getZgTorderbomExBo().doZgtorderProcess(orderbom.getOrderId(),"order");
		}
		
		return "success";
	}

	/**
	 * 新建入库表单时
	 * 校验所输入数值是否超过半成品需求数量
	 * 
	 * @return
	 */
	public boolean checkForGeneratebomcreate(HttpSession session) {
		List<ZgTstoragebomEx> bomEList = (List<ZgTstoragebomEx>) session
				.getAttribute("bomEList");
		ZgTstoragebomEx zgTstoragebomEx = new ZgTstoragebomEx();
		boolean result = true;
		for (int i = 0; i < bomEList.size(); i++) {
			zgTstoragebomEx = bomEList.get(i);
			String idnrk = zgTstoragebomEx.getIdnrk();
			String aufnr = zgTstoragebomEx.getOrderAufnr();
			Long num = zgTstoragebomEx.getNum();
			boolean flag = getZgTorderbomExBo().checkStateForGenerateRightNum(num,
					idnrk, aufnr);
			if (!flag) {
				result = false;
				break;
			}
		}
		
		return result;
	}

	/**
	 * 修改入库表单（保存）提交的时候检验 校验表单状态是否为已提交，若不是，则调用同一个类下的方法进行半成品状态及数量的检验,若通过，返回TRUE
	 * @param cuid
	 * @param session
	 * @return
	 */
	public String checkForGenerateOrderChanged(String cuid, HttpSession session) {
		List<ZgTstoragebomEx> bomEList = (List<ZgTstoragebomEx>) session
				.getAttribute("bomEList");
		String result = "OK";
		boolean flag = (this).getZgTstorageExBo().checkStateForGenerateStorage(
				cuid);
		if (flag) {
			result = "该表单已经被提交，请确认!";
		} else {
			flag = this.checkForGeneratebomcreate(session);
			if (!flag) {
				result = "入库数量不能大于需求总数，请确认!";
			}
		}

		return result;
	}

	/**
	 * 新建出库表单（保存）提交的时候检验
	 * @param session
	 * @return
	 */
	public boolean checkForGenerateStrogeOutCreate(String lgort,HttpSession session) {
		List<ZgTstoragebomEx> bomEList = (List<ZgTstoragebomEx>) session
				.getAttribute("bomEList");
		ZgTstoragebomEx zgTstoragebomEx = new ZgTstoragebomEx();
		boolean result = true;
		for (int i = 0; i < bomEList.size(); i++) {
			zgTstoragebomEx = bomEList.get(i);
			String aufnr = zgTstoragebomEx.getOrderAufnr();
			String idnrk = zgTstoragebomEx.getIdnrk();
			Long num = zgTstoragebomEx.getNum();
			boolean flag = getZgTorderbomExBo().checkStateForGenerateBomNum(aufnr,idnrk, num,lgort);
			if (!flag) {
				result = false;
				break;
			}
		}
	
		return result;
	}

	/**
	 * 修改出库表单（保存）提交的时候检验 校验表单状态是否为已提交，若不是，则调用同一个类下的方法进行半成品状态及数量的检验,若通过，返回TRUE
	 * @param cuid
	 * @param session
	 * @return
	 */
	public String checkForGenerateStorageOutChanged(String cuid,String lgort,HttpSession session) {
		List<ZgTstoragebomEx> bomEList = (List<ZgTstoragebomEx>) session.getAttribute("bomEList");
		String result = "OK";
		boolean flag = (this).getZgTstorageExBo().checkStateForGenerateStorage(
				cuid);
		if (flag) {
			result = "该表单已经被提交，请确认!";
		} else {
			flag = this.checkForGenerateStrogeOutCreate(lgort,session);
			if (!flag) {
				result = "实际出库数量不能大于可出库数量，请确认!";
			}
		}
		return result;
	}
	
	/**
	 * bom移单
	 * 	移单处理
	 * 	重新计划移单后的领料进度
	 * @param objcetJOSNs
	 * @param sourceOrderId
	 * @return
	 */
	public String bomMove(HttpServletRequest request,String objcetJOSNs,String sourceOrderTaskId,String targetOrderTaskId){
		//设置日期转换的格式  
		boolean flag = true;
		String msg="失败";
		try{
			OperatorInfo operatorInfo=getSessionOperatorInfo(request);
			msg=getZgTorderbomExBo().bomMove(objcetJOSNs, sourceOrderTaskId,targetOrderTaskId,operatorInfo);
			
			getZgTorderbomExBo().doZgtorderProcess(sourceOrderTaskId,"order");
			
			getZgTorderbomExBo().doZgtorderProcess(targetOrderTaskId,"order");
			
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return StringHelper.isEmpty(msg)?"OK":msg;
	}
	
	/**
	 * 更新物料的自有物料组编号
	 * @param idnrk
	 * @param matkl
	 * @return
	 */
	public String setSelfMatkl(String idnrk,String matkl){
		getZgTorderbomExBo().setSelfMatkl(idnrk,matkl);
		return "OK";
	}


	


	
	

}
