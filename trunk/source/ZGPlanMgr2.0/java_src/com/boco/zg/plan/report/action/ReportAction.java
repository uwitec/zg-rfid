/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.report.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javacommon.base.BaseStruts2Action;
import javacommon.base.service.IVmModelBo;

import javax.servlet.ServletException;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.service.FwOrganizationBo;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.common.service.CommonService;
import com.boco.zg.plan.report.service.ReportBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.Key;

/**
 * @author wengqin
 * @version 1.0
 * @since 1.0
 */


public class ReportAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERYLGORTREPORT= "/zg/plan/report/lgort/query_lgort.jsp";
	protected static final String LISTLGORTREPORTSTAT= "/zg/plan/report/lgort/list_lgort_stat.jsp";
	protected static final String LISTLGORTREPORT= "/zg/plan/report/lgort/list_lgort.jsp";
	protected static final String EXPORT_STORECAR="!/frame/excel/all/DataTrans/export.do";

	protected static final String QUERYLGORTREPORTBATCH= "/zg/plan/report/lgort/query_lgort_batch.jsp";
	protected static final String LISTLGORTREPORTBATCHSTAT= "/zg/plan/report/lgort/list_lgort_batch_stat.jsp";
	protected static final String LISTLGORTREPORTBATCH= "/zg/plan/report/lgort/list_lgort_batch.jsp";

	private ReportBo reportBo;
	
	private FwOrganizationBo fwOrganizationBo;
	
	private List<FwOrganization> arbplList;
	
	public List<FwOrganization> getArbplList() {
		FwOrganization fwOrganization=new FwOrganization();
		fwOrganization.setType("3");
		return fwOrganizationBo.findByProperty(fwOrganization);
	}
	
	public List<Map> getOrgList(){
		return fwOrganizationBo.getOrgList();
	}
	
	private List<FwOrganization> orgs;
	public List<FwOrganization> getOrgs() {
		if(orgs == null) {
			FwOrganization fwOrganization=new FwOrganization();
			fwOrganization.setType("4");
			
			orgs = fwOrganizationBo.findByProperty(fwOrganization, "t0_LABEL_CN", true);
		}
		return orgs;
	}
	
	public void prepare() throws Exception {
	}
	
	public Object getModel() {
		return null;
	}
	
	/**
	 * 仓库报表
	 * @return
	 */
	public String queryLgortReport(){
		CommonService.defultDateTimeSet(getRequest(), "date_start", "date_end",0,0);
		getRequest().setAttribute("needSortf", Constants.NEEDPLANSORTF);
		
		return  QUERYLGORTREPORT;
	}
	
	/**
	 * 仓库出仓报表汇总
	 * @return
	 */
	public String lgortReportStat(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		Page page = reportBo.lgortReportStat(pageRequest);
		savePage(page,pageRequest);
		return LISTLGORTREPORTSTAT;
	}
	
	public String lgortReportStatExport() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		getRequest().getSession().setAttribute("ex_template","lgortReportStatExport");
//		reportBo.addPlantWhere(pageRequest);
		getRequest().getSession().setAttribute("ex_in", pageRequest.getFilters());
		
		return EXPORT_STORECAR;
	}
	
	/**
	 * 仓库出仓报表
	 * @return
	 */
	public String lgortReport(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		Page page = reportBo.lgortReport(pageRequest);
		savePage(page,pageRequest);
		return LISTLGORTREPORT;
	}
	
	public String lgortReportExport() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		getRequest().getSession().setAttribute("ex_template","lgortReportExport");
//		reportBo.addPlantWhere(pageRequest);
		
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		getRequest().getSession().setAttribute("ex_in", pageRequest.getFilters());
		
		return EXPORT_STORECAR;
	}
	
	/**
	 * 批量领料汇总报表导出
	 * @return
	 */
	public String lgortReportBatchStatExport() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		getRequest().getSession().setAttribute("ex_template","lgortReportBatchStatExport");
		getRequest().getSession().setAttribute("ex_in", pageRequest.getFilters());
		
		return EXPORT_STORECAR;
	}
	
	/**
	 * 批量领料报表导出
	 * @return
	 */
	public String lgortReportBatchExport() {
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		getRequest().getSession().setAttribute("ex_template","lgortReportBatchExport");
		getRequest().getSession().setAttribute("ex_in", pageRequest.getFilters());
		
		return EXPORT_STORECAR;
	}
	
	
	
	
	/**
	 * 仓库报表(批量)
	 * @return
	 */
	public String queryLgortReportBatch(){
		CommonService.defultDateTimeSet(getRequest(), "date_start", "date_end",0,0);
		return  QUERYLGORTREPORTBATCH;
	}
	
	/**
	 * 仓库出仓报表(批量)
	 * @return
	 */
	public String lgortReportBatch(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		Page page = reportBo.lgortReportBatch(pageRequest);
		savePage(page,pageRequest);
		return LISTLGORTREPORTBATCH;
	}
	
	
	/**
	 * 仓库出仓报表汇总
	 * @return
	 */
	public String lgortReportBatchStat(){
		PageRequest<Map> pageRequest = newPageRequest(DEFAULT_SORT_COLUMNS);
		//pageRequest.getFilters().put("key",value);     //add custom filter
		String lgort=IbatisDAOHelper.getStringValue(pageRequest.getFilters(), "lgort","");
		lgort=lgort.replace(",", "','");
		pageRequest.getFilters().put("lgort", lgort);
		
		Page page = reportBo.lgortReportBatchStat(pageRequest);
		savePage(page,pageRequest);
		return LISTLGORTREPORTBATCHSTAT;
	}
	

	public void setReportBo(ReportBo reportBo) {
		this.reportBo = reportBo;
	}

	public void setFwOrganizationBo(FwOrganizationBo fwOrganizationBo) {
		this.fwOrganizationBo = fwOrganizationBo;
	}
	
	
	

}
