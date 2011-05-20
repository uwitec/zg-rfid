package sap.service;


import javacommon.base.dao.BaseDao;
import javacommon.util.BatFileRun;
import javacommon.util.properties.Config;
import sap.SapClient;
import cn.org.rapid_framework.util.ApplicationContextHolder;

public class SapService {
	
	private SapClient getSapClient() {
		return (SapClient)ApplicationContextHolder.getBean("sapClient");
	}
	
	private static BaseDao getBaseDao() {
		return (BaseDao) ApplicationContextHolder
				.getBean("baseDao");
	}
	
	public void processSynBom(){
		int batchNo=getBaseDao().getSeq("SEQ_BATCH_NO");
		getSapClient().businessHandler("5", "",batchNo);
		
		//oracle数据库备份
		BatFileRun.runbat(Config.getString("oracleDbBackPath"));
	}

}
