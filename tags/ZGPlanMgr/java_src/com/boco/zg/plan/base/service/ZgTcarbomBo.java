/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.plan.base.service;

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

import com.boco.zg.plan.base.model.*;
import com.boco.zg.plan.base.dao.*;
import com.boco.zg.plan.base.service.*;
import com.boco.zg.plan.base.model.ZgTorderPlanComment;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTcarbomBo extends BaseManager<ZgTcarbom,java.lang.String>{
	private ZgTcarbomDao zgTcarbomDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTcarbomDao(ZgTcarbomDao dao) {
		this.zgTcarbomDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.zgTcarbomDao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return zgTcarbomDao.findByPageRequest(pr);
	}
	
	public void removeById(java.lang.String id) {
		zgTcarbomDao.deleteById(id);
	}
	
	public List<ZgTcarbom> findByRequest(PageRequest pageRequest) {
		return zgTcarbomDao.findByRequest(pageRequest);
	}
	
	/**
	 * wjz
	 */
	public void updateZgTorderPlanComment(ZgTorderPlanComment zgTorderPlanComment){
		//ZgTcarbomDao wwzgTcarbomDao=new ZgTcarbomDao();
		zgTcarbomDao.updateRejectComment(zgTorderPlanComment);
	}
	
	/**
	 * wjz,这里的业务是用来修改uid变成名字的
	 */
	public List<ZgTorderPlanComment> getZgTorderPlanCommentList(String orderplanid){
		List<ZgTorderPlanComment> commentList=zgTcarbomDao.selectZgTorderPlanCommentById(orderplanid);
		
		//进行循环赋值 中文名
	
		return commentList;
	}
	public int getSeq(String seqName) {
		return this.zgTcarbomDao.getSeq(seqName);
	}
	
	
}
