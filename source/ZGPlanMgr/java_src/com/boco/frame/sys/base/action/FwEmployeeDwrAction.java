/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import java.util.List;
import java.util.Map;

import javacommon.base.BaseDwrAction;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.service.FwEmployeeBo;
import com.boco.frame.sys.service.FwOperatorExBo;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class FwEmployeeDwrAction extends BaseDwrAction{
	private FwEmployeeBo  getFwEmployeeBo() {
		return (FwEmployeeBo)ApplicationContextHolder.getBean("fwEmployeeBo");
	}
	
	private FwOperatorExBo  getFwOperatorExBo() {
		return (FwOperatorExBo)ApplicationContextHolder.getBean("fwOperatorExBo");
	}
	
	
	/**
	 * 用户新增时校验
	 * @param userId
	 * @param rfidCode
	 * @return
	 */
	public String checkEmployeeSaved(String userId,String rfidCode) {
		boolean flag = true;
		FwEmployee entity = new FwEmployee();
		entity.setUserId(userId);
		List<FwEmployee> list = this.getFwEmployeeBo().findByProperty(entity);//查询用户ID是否有相同的
		if(list!=null && list.size()>0){
			return "用户ID重复，不能添加！";
		}
		entity = new FwEmployee();
		entity.setRfidCode(rfidCode);
		list = this.getFwEmployeeBo().findByProperty(entity);//查询RFID卡号是否有相同的
		if(list!=null && list.size()>0){
			return "RFID卡号重复，不能添加！";
		}
		
		return "OK";
	}
	
	/**
	 * 用户编辑提交时校验
	 * @param userId
	 * @param rfidCode
	 * @return
	 */
	public String checkEmployeeUpdate(String cuid,String userId,String rfidCode) {
		boolean flag = true;
		FwEmployee entity = new FwEmployee();
		entity.setUserId(userId);
		entity.setCuid(cuid);
		if(!this.getFwOperatorExBo().checkUserId(entity)){
			return "用户ID重复，不能添加！";
		}
		entity = new FwEmployee();
		entity.setCuid(cuid);
		entity.setRfidCode(rfidCode);
		if(!this.getFwOperatorExBo().checkRfidCode(entity)){
			return "RFID卡号重复，不能添加！";
		}
		
		return "OK";
	}
	

	/**
	 * 根据rfid编号获取用户信息
	 * @param rfidCode
	 * @param lgort
	 * @return
	 */
	public Map getStorageUserByRfidCode(String rfidCode,String lgort){
		Map map=getFwOperatorExBo().getStorageUserByRfidCode(rfidCode,lgort);
		return map;
	}
	
}
