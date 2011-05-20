/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.dao;

import java.text.SimpleDateFormat;
import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;


import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

import com.boco.zg.plan.base.model.ZgTcarbom;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.model.FwOrganization;
import com.boco.frame.sys.base.model.Province;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;
import com.boco.zg.plan.model.ZgTcarbomEx;


@Component
public class ZgTcarbomExDao extends BaseIbatisDao<ZgTcarbom,java.lang.String>{
	public Class getEntityClass() {
		return ZgTcarbom.class;
	}
	
	

	public Object save(ZgTcarbomEx bom) {
		return getSqlMapClientTemplate().insert("ZgTcarbomEx.insertZG_T_CARBOMEx",bom);
	}



	public void saveOrUpdate(ZgTcarbom entity) {
		// TODO Auto-generated method stub
		
	}

}
