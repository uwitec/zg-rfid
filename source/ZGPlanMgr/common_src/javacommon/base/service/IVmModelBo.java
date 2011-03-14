package javacommon.base.service;

import java.util.List;
import java.util.Map;

import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.model.BMAttrMeta;

public interface IVmModelBo {

	/**
	 * 根据用户ID及类型ID获取当前列表的表格字段
	 * @param bmClassId
	 * @param userId
	 * @return
	 */
	public Map<String,BMAttrMeta> getAttrsByUser(String bmClassId,String userId);
	/**
	 * 根据用户ID及类型ID获取当前列表的表格Meta
	 * @param bmClassId
	 * @param userId
	 * @return
	 */
	public Map<String,BMAttrMeta> getAttrMetasByUser(String bmClassId,String userId);
	
	/**
	 * 根据枚举编码获取枚举列表
	 * @param dicCode
	 * @return
	 */
	public List<TmdEnumevalue> getEnumValue(String dicCode);
}
