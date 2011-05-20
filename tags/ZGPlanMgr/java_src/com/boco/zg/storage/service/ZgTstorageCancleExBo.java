/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.service;

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

import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.*;
import com.boco.zg.storage.base.dao.*;
import com.boco.zg.storage.base.service.*;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.util.Constants;

/**
 * @author wengq
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTstorageCancleExBo extends ZgTstorageCancleBo{
	private ZgTstorageCancleDao zgTstorageCancleDao;
	
	private ZgTstorageCanclebomExBo zgTstorageCanclebomExBo;
	
	private ZgTorderbomBo zgTorderbomBo;
	
	private ZgTstorageStatBo zgTstorageStatBo;
	
	private ZgTorderbomExBo zgTorderbomExBo;
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setZgTstorageCancleDao(ZgTstorageCancleDao dao) {
		this.zgTstorageCancleDao = dao;
	}
	public void setZgTstorageCanclebomExBo(
			ZgTstorageCanclebomExBo zgTstorageCanclebomExBo) {
		this.zgTstorageCanclebomExBo = zgTstorageCanclebomExBo;
	}

	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}
	
	public ZgTorderbomBo getZgTorderbomBo() {
		return zgTorderbomBo;
	}
	public void setZgTorderbomBo(ZgTorderbomBo zgTorderbomBo) {
		this.zgTorderbomBo = zgTorderbomBo;
	}
	public void setZgTstorageStatBo(ZgTstorageStatBo zgTstorageStatBo) {
		this.zgTstorageStatBo = zgTstorageStatBo;
	}

	
	/**
	 * 同步session中的出入库半成品到数据中
	 * 处理逻辑：
	 * 	 遍历session中的list,判断该半成品的修改标志是否为true,是则处理（删除标志为true,则删除该半成品，否则更新）
	 * 	
	 * @param bomECancleList
	 */
	public void synSessionBomToDataBase(List<ZgTstorageCanclebomEx> bomECancleList) {
		for (ZgTstorageCanclebomEx obj : bomECancleList) {
					
				ZgTstorageCanclebom zgTstorageCanclebom=new ZgTstorageCanclebom();
					
				zgTstorageCanclebom.setCuid(obj.getCuid());
				zgTstorageCanclebom.setStorageId(obj.getStorageId());
				zgTstorageCanclebom.setStorageCancleId(obj.getStorageCancleId());
				zgTstorageCanclebom.setNum(obj.getNum());
				zgTstorageCanclebom.setOrderBomId(obj.getOrderBomId());
				zgTstorageCanclebom.setZbz(obj.getZbz());
				
					
				if(obj.getIsModity()){//把标记为修改过的bom组件更新到数据库中
						
					//设置该半成品的状态为正在出入库，锁定该半成品，直到该出入库存操作提交时才开锁
					ZgTorderbom zgTorderbom=new ZgTorderbom();
					zgTorderbom.setCuid(obj.getOrderBomId());
					zgTorderbom.setStorageState(Constants.EBomStorageStatus.CANCLE.value());
					  
						
					if(obj.getIsDel()==true){//删除表中的该半成品，并设置该半成品状态为0 ，开锁
						zgTstorageCanclebomExBo.removeById(obj.getCuid());
						zgTorderbom.setStorageState(Constants.EBomStorageStatus.NEW.value());
					}else {
						int row=zgTstorageCanclebomExBo.update1(zgTstorageCanclebom);
						if(row<=0){//更新影响的条数小于０  则说明原来的数据中没有该组件，插入该组件
							zgTstorageCanclebomExBo.save(zgTstorageCanclebom);
						}
					}
					zgTorderbomExBo.updateOrderBomState(zgTorderbom);
				}
			}
	}
	
	/**
	 * 更新zgtorderbom中的半成品状态
	 * 更新库存统计表
	 * @param bomECancleList
	 * @param zgTstorageCancle
	 * @param productType
	 */
	public void updateOrderBomAndStorageStat(List<ZgTstorageCanclebomEx> bomECancleList,ZgTstorageCancle zgTstorageCancle, String productType) {
		for (ZgTstorageCanclebomEx obj : bomECancleList) {
			if(!obj.getIsDel()){
			//  更新zgtorderbom表记录，（更新入库数量和该表的相关的半成品的状态）
				ZgTorderbom zgTorderbom=zgTorderbomBo.getById(obj.getOrderBomId());
				zgTorderbom.setStorageNum(zgTorderbom.getStorageNum()-obj.getNum());
				if(zgTorderbom.getMenge().equals(zgTorderbom.getStorageNum())){
					zgTorderbom.setStorageState(Constants.EBomStorageStatus.DONE.value());
				}else {
					zgTorderbom.setStorageState(Constants.EBomStorageStatus.NEW.value());
				}
				zgTorderbomBo.update(zgTorderbom);
				
				
				//  更新库存统计表
				ZgTstorageStat zgTstorageStat=zgTstorageStatBo.getByOrderBomId(obj.getOrderBomId(),obj.getLgort());
				zgTstorageStat.setNum(zgTstorageStat.getNum()-obj.getNum());
					
				zgTstorageStatBo.update(zgTstorageStat);
			}
			
		}
	
	}

	
}
