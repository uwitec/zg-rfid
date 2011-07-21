/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.bom.base.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


import org.springframework.stereotype.Component;


@Component
public class ZgCarInfoDao extends BaseIbatisDao<ZgCarInfo,java.lang.String>{
	public Class getEntityClass() {
		return ZgCarInfo.class;
	}
	
	public void saveOrUpdate(ZgCarInfo entity) {
		if(entity.getCuid() == null)
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("ZgCarInfo.pageSelect",pageRequest);
	}


}
