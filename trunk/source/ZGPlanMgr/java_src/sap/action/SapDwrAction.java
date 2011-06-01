package sap.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javacommon.base.BaseDwrAction;
import javacommon.base.dao.BaseDao;

import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;

import sap.SapClient;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.zg.plan.base.model.ZgTorderPlanbom;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderPlanbomBo;
import com.boco.zg.plan.model.ZgTorderPlanbomEx;
import com.boco.zg.plan.service.ZgTorderPlanExBo;
import com.boco.zg.plan.service.ZgTorderPlanbomExBo;

public class SapDwrAction extends BaseDwrAction {
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}
	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
	
	
	public String loadSapData(String dataType,String date,int batchNo,String orderplant) {
		date=date.replace("-", "");
		getSapClient().businessHandler(dataType, date,batchNo,orderplant);
		return "已启动接口获取数据，编号："+batchNo+"，具体结果请查看日志列表";
	}
	
	
	public int getBatchNo(){
		return getBaseDao().getSeq("SEQ_BATCH_NO");
	}
	
}
