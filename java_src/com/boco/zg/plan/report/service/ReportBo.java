/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.report.service;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;
import com.boco.zg.plan.report.dao.ReportDao;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ReportBo extends BaseManager<Map,java.lang.String>{
	private ReportDao reportDao;

	@Override
	protected EntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	/**
	 * 仓库物料报表
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReport(PageRequest<Map> pageRequest) {
		return reportDao.lgortReport(pageRequest);
	}
	
	/**
	 * 仓库物料报表江总
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReportStat(PageRequest<Map> pageRequest) {
		return reportDao.lgortReportStat(pageRequest);
	}

	/**
	 * 仓库出仓汇总（批量领料）
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReportBatchStat(PageRequest<Map> pageRequest) {
		return reportDao.lgortReportBatchStat(pageRequest);
	}

	/**
	 * 仓库出仓明细（批量领料）
	 * @param pageRequest
	 * @return
	 */
	public Page lgortReportBatch(PageRequest<Map> pageRequest) {
		return reportDao.lgortReportBatch(pageRequest);
	}
	
	/**
	 * 添加生产厂查询
	 * @param pageRequest
	 */
	public void addPlantWhere(PageRequest<Map> pageRequest) {
		reportDao.addPlantWhere(pageRequest);
	}
}
