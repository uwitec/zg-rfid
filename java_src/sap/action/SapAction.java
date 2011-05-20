package sap.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.boco.zg.util.TimeFormatHelper;

import javacommon.base.BaseStruts2Action;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ApplicationContextHolder;
import sap.SapClient;
import sap.service.HandlerSapDataService;

public class SapAction extends BaseStruts2Action  {
	protected static final String GET_SAP_DATA = "/zg/sap/getSapData.jsp";
	protected static final String LIST_LOG= "/zg/sap/list_SapLogList.jsp";

	SapClient sapClient= (SapClient) ApplicationContextHolder.getBean("sapClient");;

	public String toLoadSapDateUI(){
		String dateStr=TimeFormatHelper.getFormatDate(Calendar.getInstance().getTime(), TimeFormatHelper.DATE_FORMAT);
		getRequest().setAttribute("dateStr", dateStr);
		return GET_SAP_DATA;
	}
	
	public String listLogList(){
		PageRequest<Map> pageRequest = newPageRequest(null);
		return LIST_LOG;
	}
	

}
