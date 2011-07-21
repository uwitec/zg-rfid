/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.action;

import java.util.Date;

import javacommon.base.BaseDwrAction;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.service.FwOrganizationExBo;
import com.boco.zg.util.TimeFormatHelper;

/**
 * @author wengqin 
 * @version 1.0
 * @since 1.0
 */


public class FwOrganizationDwrAction extends BaseDwrAction{
	private FwOrganizationExBo  getFwOrganizationExBo() {
		return (FwOrganizationExBo)ApplicationContextHolder.getBean("fwOrganizationExBo");
	}
	
	public String changePxDateNextDay(String plant,String pxDate){
	//	if(TimeFormatHelper.getFormatDate(new Date(), TimeFormatHelper.DATE_FORMAT).equals(pxDate)){
	//		return "排序日期已经是今天，不能切换下一天，请确认";
	//	}
		
		//判断页面上的排序日期与数据库日期是否同步
		Date systemPxDate=getFwOrganizationExBo().getPxdateByOrgId(plant);
		if(TimeFormatHelper.getFormatDate(systemPxDate,TimeFormatHelper.DATE_FORMAT).equals(pxDate)){
			getFwOrganizationExBo().changePxDateNextDay(plant);
			
			//该生产厂排序日期之后的订单分组内部排序号全部罪成10000
			getFwOrganizationExBo().revertIndexNoByPlant(plant);
			return "OK";
		}else {
			return "目前排序日期与系统中实际的排序日期不同步，请刷新页面";
		}
		
	}
	
	
}
