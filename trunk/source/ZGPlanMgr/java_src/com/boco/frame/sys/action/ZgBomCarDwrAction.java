package com.boco.frame.sys.action;

import javacommon.base.BaseDwrAction;
import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.sys.base.model.ZgBomCar;
import com.boco.frame.sys.service.ZgBomCarBo;

public class ZgBomCarDwrAction  extends BaseDwrAction{

	private ZgBomCarBo getZgBomCarBo(){
		return (ZgBomCarBo)ApplicationContextHolder.getBean("zgBomCarBo");
	}
	
	
	public void updateCarInfoInBom(String bomId,String cuid,String code){
		getZgBomCarBo().updateByBomId(bomId,cuid,code);
	}
	
	
	public void updateCarNumInBom(String bomId,String carNumStr){
		Long carNum=Long.parseLong(carNumStr);
		ZgBomCar entity=new ZgBomCar();
		entity.setBomId(bomId);
		entity.setCarNum(carNum);
		getZgBomCarBo().updateCarNum(entity);
	}
}
