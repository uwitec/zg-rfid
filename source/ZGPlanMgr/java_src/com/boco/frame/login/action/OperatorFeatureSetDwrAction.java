package com.boco.frame.login.action;

import javacommon.base.BaseDwrAction;

import javax.servlet.http.HttpServletRequest;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.login.service.OperatorFeatureSetBo;

public class OperatorFeatureSetDwrAction extends BaseDwrAction {

	private OperatorFeatureSetBo getOperatorFeatureSetBo(){
		return (OperatorFeatureSetBo)ApplicationContextHolder.getBean("operatorFeatureSetBo");
	}

	public  void savePageSizeValue(int pageSizeValue,HttpServletRequest request){
		String cuid=super.getSessionUserId(request);
		super.getSessionOperatorInfo(request).setPageSizeValue(pageSizeValue);
		getOperatorFeatureSetBo().savePageSizeValue(pageSizeValue,cuid);//通过cuid把pagesizevalue保存到pageparam表中
	}
	

}
