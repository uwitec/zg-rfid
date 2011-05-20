/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.service;

import org.springframework.stereotype.Component;

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

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;


@Component
public class FwOperatorExBo extends FwOperatorBo{
	
	public List<FwOperator> findUserByOrgId(String orgId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("orgId", orgId);
		return ((FwOperatorDao)this.getEntityDao()).getSqlMapClientTemplate().queryForList("FwOperatorEx.findUserByOrgId",paramMap);
	}

	/**
	 * 根据rfid编号获取用户信息
	 * @param rfidCode
	 * @return
	 */
	public FwOperator getUserByRfidCode(String rfidCode) {
		return (FwOperator)((FwOperatorDao)this.getEntityDao()).getSqlMapClientTemplate().queryForObject("FwOperatorEx.getUserByRfidCode", rfidCode);
	}
	
	/**
	 * 根据rfid编号获取仓管员信息
	 * @param rfidCode
	 * @return
	 */
	public Map getStorageUserByRfidCode(String rfidCode,String lgort) {
		Map params=new HashMap();
		params.put("rfidCode", rfidCode);
		params.put("lgort", lgort);
		return (Map)((FwOperatorDao)this.getEntityDao()).getSqlMapClientTemplate().queryForObject("FwOperatorEx.getStorageUserByRfidCode", params);
	}

	/**
	 * 验证cuid是否重复
	 * @param entity
	 * @return
	 */
	public boolean checkUserId(FwEmployee entity) {
		StringBuffer stringBuffer =new StringBuffer();
		stringBuffer.append("select * from fw_operator t where t.cuid!='"+entity.getCuid()+"' and t.user_id='"+entity.getUserId()+"'");
		return ((FwOperatorDao)this.getEntityDao()).findDynQuery(stringBuffer.toString()).size()<=0;
	
	}
	
	/**
	 * 验证rfidCode是否重复
	 * @param entity
	 * @return
	 */
	public boolean checkRfidCode(FwEmployee entity) {
		StringBuffer stringBuffer =new StringBuffer();
		stringBuffer.append("select * from fw_operator t where t.cuid!='"+entity.getCuid()+"' and t.rfidcode='"+entity.getRfidCode()+"'");
		return ((FwOperatorDao)this.getEntityDao()).findDynQuery(stringBuffer.toString()).size()<=0;
	}
	
}
