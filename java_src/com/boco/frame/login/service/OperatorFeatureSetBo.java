package com.boco.frame.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.boco.frame.sys.base.dao.FwEmployeeDao;
import com.boco.frame.sys.base.dao.FwOperatorDao;
import com.boco.frame.sys.base.model.FwEmployee;
import com.boco.frame.sys.base.model.FwOperator;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

@Component
public class OperatorFeatureSetBo extends BaseManager<FwOperator,java.lang.String>{

	private FwEmployeeDao fwEmployeeDao;
	

	public void setFwEmployeeDao(FwEmployeeDao fwEmployeeDao) {
		this.fwEmployeeDao = fwEmployeeDao;
	}
	public EntityDao getEntityDao() {
		return this.fwEmployeeDao;
	}
	public void savePageSizeValue(int pageSizeValue,String userId){
		//设置个性时，判断插入还是更新
		List<Map> paramList=fwEmployeeDao.findFromPageParam(userId);
		if(paramList.size()==0||paramList==null)
		{
			fwEmployeeDao.insertPageSizeValue(pageSizeValue,userId);
		}else{
			fwEmployeeDao.savePageSizeValue(pageSizeValue,userId);
		}
	}
	
}
