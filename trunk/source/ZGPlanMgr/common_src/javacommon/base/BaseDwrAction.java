package javacommon.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.pojo.OperatorInfo;
import com.boco.frame.meta.service.TmdEnumeBo;

public class BaseDwrAction {
	
	public String getSessionUserId(HttpServletRequest request) {
		OperatorInfo operatorInfo = (OperatorInfo)request.getSession().getAttribute("operatorInfo");
		return operatorInfo != null?operatorInfo.getOperatorId():null;
	}
	public String getSessionOperatorId(HttpServletRequest request) {
		OperatorInfo operatorInfo = (OperatorInfo)request.getSession().getAttribute("operatorInfo");
		return operatorInfo != null?operatorInfo.getOperatorId():null;
	}
	public OperatorInfo getSessionOperatorInfo(HttpServletRequest request) {
		return (OperatorInfo)request.getSession().getAttribute("operatorInfo");
	}
}
