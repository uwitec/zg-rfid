package com.boco.frame.login.action;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.service.FwOperatorExBo;
import com.boco.zg.plan.service.ZgTorderExBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;

public class LoginDwrAction extends BaseDwrAction {
	
	private FwOperatorExBo getFwOperatorExBo() {
		return (FwOperatorExBo)ApplicationContextHolder.getBean("fwOperatorExBo");
	}
	
	/**
	 * 根据rfid编号获取用户信息
	 * @param rfidCode
	 * @return
	 */
	public FwOperator getUserByRfidCode(String rfidCode){
		return getFwOperatorExBo().getUserByRfidCode(rfidCode);
	}

}
