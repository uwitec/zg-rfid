/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.zg.storage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.boco.zg.storage.base.model.ZgTstorageCanclebom;
import com.boco.zg.storage.base.service.ZgTstorageCanclebomBo;
import com.boco.zg.storage.dao.ZgTstorageCanclebomExDao;
import com.boco.zg.storage.model.ZgTstorageCanclebomEx;

/**
 * @author wengq
 * @version 1.0
 * @since 1.0
 */

@Component
public class ZgTstorageCanclebomExBo extends ZgTstorageCanclebomBo {
	private ZgTstorageCanclebomExDao zgTstorageCanclebomExDao;

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性 */
	public void setZgTstorageCanclebomExDao(
			ZgTstorageCanclebomExDao zgTstorageCanclebomExDao) {
		this.zgTstorageCanclebomExDao = zgTstorageCanclebomExDao;
	}

	/**
	 * 根据入库冲单编号，查找它的冲单半成品
	 * 
	 * @param id
	 *            入库冲单编号
	 * @param productType
	 *            CE:预焊 DE:预装
	 * @return
	 */
	public List<ZgTstorageCanclebomEx> findBomDEByStorageCancleId(String id,
			String productType) {
		return zgTstorageCanclebomExDao.findBomDEByStorageCancleId(id,
				productType);
	}

	/**
	 * 根据冲单表编号，过滤查找半成品
	 * 
	 * @param filters
	 * @return
	 */
	public List<ZgTstorageCanclebomEx> listByCancleId(Map params) {
		return zgTstorageCanclebomExDao.listByCancleId(params);
	}

	/**
	 * 更新zgTstorageCanclebom对象，
	 * 
	 * @param zgTstorageCanclebom
	 * @return
	 */
	public int update1(ZgTstorageCanclebom zgTstorageCanclebom) {
		return zgTstorageCanclebomExDao.update1(zgTstorageCanclebom);
	}
	/**
	 * 判断入库冲单表单状态是否为已提交
	 * @param cuid
	 * @return
	 */
	public boolean checkStateForGenerateStrorageCancelState(String cuid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from zg_t_storage_cancle t where t.cuid = '"
						+ cuid + "'and  t.state = 8");
		return zgTstorageCanclebomExDao.findDynQuery(sql.toString()).size() > 0;
	}
}
