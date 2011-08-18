/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;

import com.boco.frame.meta.dao.IbatisDAOHelper;
import com.boco.zg.plan.base.model.ZgTorderbom;
import com.boco.zg.plan.base.service.ZgTorderbomBo;
import com.boco.zg.plan.service.ZgTorderbomExBo;
import com.boco.zg.storage.base.model.ZgTstorage;
import com.boco.zg.storage.base.model.ZgTstorageStat;
import com.boco.zg.storage.base.model.ZgTstoragebom;
import com.boco.zg.storage.base.service.ZgTstorageBo;
import com.boco.zg.storage.base.service.ZgTstorageStatBo;
import com.boco.zg.storage.base.service.ZgTstoragebomBo;
import com.boco.zg.storage.dao.ZgTstorageExDao;
import com.boco.zg.storage.model.ZgTstoragebomEx;
import com.boco.zg.util.Constants;

@Component
public class ZgTstorageExBo extends ZgTstorageBo {
	private ZgTstorageExDao zgTstorageExDao;

	private ZgTstoragebomExBo zgTstoragebomExBo;
	private ZgTstoragebomBo zgTstoragebomBo;

	private ZgTorderbomExBo zgTorderbomExBo;

	private ZgTorderbomBo zgTorderbomBo;

	private ZgTstorageStatBo zgTstorageStatBo;

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTstorageExDao(ZgTstorageExDao zgTstorageExDao) {
		this.zgTstorageExDao = zgTstorageExDao;
	}

	public void setZgTstoragebomExBo(ZgTstoragebomExBo zgTstoragebomExBo) {
		this.zgTstoragebomExBo = zgTstoragebomExBo;
	}

	public void setZgTorderbomExBo(ZgTorderbomExBo zgTorderbomExBo) {
		this.zgTorderbomExBo = zgTorderbomExBo;
	}

	public void setZgTstorageStatBo(ZgTstorageStatBo zgTstorageStatBo) {
		this.zgTstorageStatBo = zgTstorageStatBo;
	}

	public EntityDao getEntityDao() {
		return this.zgTstorageExDao;
	}

	/**
	 * 更新表单状态
	 * 
	 * @param zgTstorage
	 */
	public void updateState(ZgTstorage zgTstorage) {
		zgTstorageExDao.updateState(zgTstorage);
	}

	/**
	 * 同步session中的出入库半成品到数据中 处理逻辑：
	 * 遍历session中的list,判断该半成品的修改标志是否为true,是则处理（删除标志为true,则删除该半成品，否则更新）
	 * 
	 * @param bomEList
	 */
	public void synSessionBomToDataBase(List<ZgTstoragebomEx> bomEList) {
		for (ZgTstoragebomEx obj : bomEList) {

			ZgTstoragebom zgTstoragebom = new ZgTstoragebom();

			zgTstoragebom.setCuid(obj.getCuid());
			zgTstoragebom.setZgTstorageId(obj.getZgTstorageId());
			zgTstoragebom.setOrderBomId(obj.getOrderBomId());
			zgTstoragebom.setNum(obj.getNum());
			zgTstoragebom.setZbz(obj.getZbz());

			if (obj.getIsModity()) {// 把标记为修改过的bom组件更新到数据库中

				// 设置该半成品的状态为正在出入库，锁定该半成品，直到该出入库存操作提交时才开锁
				ZgTorderbom zgTorderbom = new ZgTorderbom();
				zgTorderbom.setCuid(zgTstoragebom.getOrderBomId());
				zgTorderbom
						.setStorageState(Constants.EBomStorageStatus.STORAGING
								.value());

				if (obj.getIsDel() == true) {// 删除表中的该半成品，并设置该半成品状态为0 ，开锁
					zgTstoragebomExBo.removeById(obj.getCuid());
					zgTorderbom.setStorageState(Constants.EBomStorageStatus.NEW
							.value());
				} else {
					int row = zgTstoragebomExBo.update1(zgTstoragebom);
					if (row <= 0) {// 更新影响的条数小于０ 则说明原来的数据中没有该组件，插入该组件
						zgTstoragebomBo.save(zgTstoragebom);
					}
				}
				zgTorderbomExBo.updateOrderBomState(zgTorderbom);
			}
		}
	}

	public void setZgTstoragebomBo(ZgTstoragebomBo zgTstoragebomBo) {
		this.zgTstoragebomBo = zgTstoragebomBo;
	}

	/**
	 * 更新zgtorderbom表记录，（更新入库数量和该表的相关的半成品的状态） 更新库存统计表
	 * 
	 * @param bomEList
	 * @param zgTstorage
	 * @param type
	 *            操作类型 1/入库 2/出库
	 * @param productType
	 *            CE/预焊 DE/预装
	 */
	public void updateOrderBomAndStorageStat(List<ZgTstoragebomEx> bomEList,
			ZgTstorage zgTstorage, String type, String productType) {
		for (ZgTstoragebomEx obj : bomEList) {
			// 更新zgtorderbom表记录，（更新入库数量和该表的相关的半成品的状态）
			ZgTorderbom zgTorderbom = zgTorderbomBo
					.getById(obj.getOrderBomId());

			if (Constants.InOutStorageType.IN.value().equals(type)) {// 入库，则更新zgTorderbom的入库数量
				zgTorderbom.setStorageNum(zgTorderbom.getStorageNum()
						+ obj.getNum());

			}

			if (zgTorderbom.getMenge().equals(zgTorderbom.getStorageNum())) {
				zgTorderbom.setStorageState(Constants.EBomStorageStatus.DONE
						.value());
			} else {
				zgTorderbom.setStorageState(Constants.EBomStorageStatus.NEW
						.value());
			}
			zgTorderbomBo.update(zgTorderbom);

			// 更新库存统计表
			ZgTstorageStat zgTstorageStat = zgTstorageStatBo.getByOrderBomId(
					obj.getOrderBomId(), zgTstorage.getLgort());

			if (null != zgTstorageStat) {
				if (Constants.InOutStorageType.IN.value().equals(type)) {
					zgTstorageStat.setNum(zgTstorageStat.getNum()
							+ obj.getNum());
				} else if (Constants.InOutStorageType.OUT.value().equals(type)) {
					zgTstorageStat.setOutNum(zgTstorageStat.getOutNum()
							+ obj.getNum());
				}

				zgTstorageStatBo.update(zgTstorageStat);
			} else {	
				if (Constants.InOutStorageType.IN.value().equals(type)) {
					zgTstorageStat = new ZgTstorageStat();
					zgTstorageStat.setOrderId(zgTstorage.getOrderId());
					zgTstorageStat.setOrderBomId(obj.getOrderBomId());
					zgTstorageStat.setLgort(zgTstorage.getLgort());
					zgTstorageStat.setProductType(productType);
					zgTstorageStat.setNum(obj.getNum());
					zgTstorageStatBo.save(zgTstorageStat);
				} else {

				}
			}
		}
	}

	public void setZgTorderbomBo(ZgTorderbomBo zgTorderbomBo) {
		this.zgTorderbomBo = zgTorderbomBo;
	}

	public static void main(String[] args) {
		Long long1 = new Long(1);
		Long long2 = new Long(1);
		if (long1.equals(long2)) {
			System.out.println("aa");
		}
	}

	/**
	 * 修改出入库表时，检验订单的状态
	 * @param cuid
	 * @return
	 */
	public boolean checkStateForGenerateStorage(String cuid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from zg_t_storage t where t.cuid ='"
						+ cuid
						+ "' and t.state = 8 ");
		return zgTstorageExDao.findDynQuery(sql.toString()).size() > 0;
	}
	/**
	 * 更新组成半成品的bom的备料库已出库数量
	 * bom已出库数量=半成品入库数量*单台用量+原有已出库数量
	 * @param bomEList
	 */
	public void updateOrderPlanBomOutNum(List<ZgTstoragebomEx> bomEList) {
		Double outNum;
		String bomOrderbomId;//组成半成品的orderbomcuid
		String idnrk;
		String orderId;
		String zdtyl;
		for(int i=0;i<bomEList.size();i++){
			outNum=bomEList.get(i).getNum();
			if(outNum==null||outNum<=0){// 该半成品入库数为0
				break;
			}
			idnrk=bomEList.get(i).getIdnrk();//半成品组件号
			orderId=bomEList.get(i).getOrderId();
			List<Map> bomListForProduct=zgTstorageExDao.getBomListForProduct(orderId,idnrk);//获取某个半成品的所有bom
			
			//更新bom的出库数量
			for(int j=0;j<bomListForProduct.size();j++){
				Map map=bomListForProduct.get(j);
				zdtyl=IbatisDAOHelper.getStringValue(map, "ZDTYL");
//				zdtyl=map.get("")==null?"":map.get("").toString();
				if(zdtyl==null||Long.parseLong(zdtyl)<=0){//半成品组件需要的bom单台用量为0 或为空
					continue;
				}
				bomOrderbomId=IbatisDAOHelper.getStringValue(map, "CUID");
				//半成品入库，逐个更新组成这个半成品的所有bom的出库数量
				zgTstorageExDao.updateBomOutNumForProductIn(bomOrderbomId,orderId,outNum*Double.parseDouble(zdtyl));
			}
		}
	}
	
	/**
	 * 判断组成半成品的bom的数量是否够出库
	 * @param bomEList
	 */
	public List<String> isEnoughBomForProductOut(String orderId, String idnrk,String inNum) {
		List<String> resultStrList=new ArrayList<String>();
		List<Map> bomListForProduct=zgTstorageExDao.getBomListForProduct(orderId,idnrk);//获取某个半成品的所有bom
		if(bomListForProduct==null||bomListForProduct.size()==0){
			resultStrList.add("BOM领料未完成");
			return resultStrList;
		}
		String zdtyl;
		Long completeNum;
		Long moveNum;
		Long backNum;
		Long outNumOld;
		String idnrkBom;
		Long outNum=inNum==null?0:Long.parseLong(inNum);
		for(int k=0;k<bomListForProduct.size();k++){
			Map map=bomListForProduct.get(k);
			zdtyl=IbatisDAOHelper.getStringValue(map, "ZDTYL");
			if(zdtyl==null||Long.parseLong(zdtyl)<=0){//半成品组件需要的bom单台用量为0 或为空
				continue;
			}
			completeNum=Long.parseLong(IbatisDAOHelper.getStringValue(map, "COMPLETE_NUM"));
			moveNum=Long.parseLong(IbatisDAOHelper.getStringValue(map, "MOVE_NUM"));
			backNum=Long.parseLong(IbatisDAOHelper.getStringValue(map, "BACK_NUM"));
			outNumOld=Long.parseLong(IbatisDAOHelper.getStringValue(map, "OUT_NUM"));
			idnrkBom=IbatisDAOHelper.getStringValue(map, "IDNRK");
			if((outNum*Long.parseLong(zdtyl))>completeNum-outNumOld){//BOM数量不够
				resultStrList.add(idnrkBom+"库存数量不够");
				return resultStrList;
			}
		}
		return resultStrList;
	}
	
	
}
