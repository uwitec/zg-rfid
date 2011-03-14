package javacommon.base.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.dao.BaseDao;
import javacommon.util.PageRequestExt;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.org.rapid_framework.page.Page;

import com.boco.frame.meta.base.dao.TmdEnumevalueDao;
import com.boco.frame.meta.base.model.TmdEnumevalue;
import com.boco.frame.meta.dao.ClassMetaDAO;
import com.boco.frame.meta.model.BMAttrMeta;
import com.boco.frame.meta.model.BMClassMeta;
import com.boco.frame.sys.base.dao.TvmAttrDao;
import com.boco.frame.sys.base.model.TvmAttr;

@Component
public class VmModelBo implements IVmModelBo{
	@Autowired
	private TvmAttrDao attrDao;
	@Autowired
	private TmdEnumevalueDao enumevalueDao;
	@Autowired
	private ClassMetaDAO metaDao;

	@Autowired
	private BaseDao baseDao;
	
	public Map<String,BMAttrMeta> getAttrsByUser(String bmClassId,String userId){
		userId = StringUtils.trimToNull(userId);
		if(userId==null){
			userId = "sysadmin";
		}
		bmClassId = StringUtils.trimToNull(bmClassId);
		if(bmClassId==null){
			throw new RuntimeException("bmClassId不能为空!");
		}
		Map<String,BMAttrMeta> attrMap = new LinkedHashMap<String,BMAttrMeta>();
		TvmAttr obj = new TvmAttr();
		obj.setSqlQueryString(TvmAttr.RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP+"bm_class_id='"+bmClassId+"' and "+TvmAttr.RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP+"related_user_cuid='"+userId+"'");
		List<TvmAttr> list = this.attrDao.findByProperty(obj, "t0_LABEL_CN", true);
		BMClassMeta classMeta= metaDao.getClassMeta(bmClassId);
		if(list.size()==0){//如果没有配置模板，则默认显示名称属性
			//attrMap.put("LABEL_CN", "LABEL_CN");
			for(BMAttrMeta attrMeta : classMeta.getAttrMetas().values()) {
				if(!attrMeta.getIsSystem()) {
					attrMap.put(attrMeta.getAttrId(), attrMeta);
				}
			}
		}
		for(TvmAttr attr:list){
			attrMap.put(attr.getLabelCn(), classMeta.getAttrMeta(attr.getLabelCn()));
		}
		return attrMap;
	}
	
	public Map<String,BMAttrMeta> getAttrMetasByUser(String bmClassId,String userId){
		userId = StringUtils.trimToNull(userId);
		if(userId==null){
			userId = "0";
		}
		bmClassId = StringUtils.trimToNull(bmClassId);
		if(bmClassId==null){
			throw new RuntimeException("bmClassId不能为空!");
		}
		Map<String,BMAttrMeta> attrMap = new LinkedHashMap<String,BMAttrMeta>();
		TvmAttr obj = new TvmAttr();
		//obj.setSqlQueryString(TvmAttr.RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP+"bm_class_id='"+bmClassId+"' and "+TvmAttr.RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP+"related_user_cuid='"+userId+"'");
		obj.setSqlQueryString(TvmAttr.RELATED_GROUP_CUID_T_VM_GROUP_T_VM_GROUP+"bm_class_id='"+bmClassId+"'" );
		List<TvmAttr> list = this.attrDao.findByProperty(obj);
		BMClassMeta classMeta= metaDao.getClassMeta(bmClassId);
		/*if(list.size()==0){//如果没有配置模板，则默认显示名称属性
			for(BMAttrMeta attrMeta : classMeta.getAttrMetas().values()) {
				if(!attrMeta.getIsSystem()) {
					attrMap.put(attrMeta.getAttrId(), attrMeta);
				}
			}
		}*/
		for(TvmAttr attr:list){
			attrMap.put(attr.getLabelCn(), classMeta.getAttrMeta(attr.getLabelCn()));
		}
		return attrMap;
	}
	
	public List<TmdEnumevalue> getEnumValue(String dicCode) {
		TmdEnumevalue entity = new TmdEnumevalue();
		entity.setSqlQueryString("t.related_et_cuid in (select t1.cuid from t_md_enumetype t1 where t1.dic_code = '"+dicCode+"')");
		List<TmdEnumevalue> list = enumevalueDao.findByProperty(entity);
		return list;
	}
	
	public Page test(){
		String sql="select cuid from fw_employee t";
		PageRequestExt pageRequest = new PageRequestExt();
		pageRequest.setTotalNum(5);
		pageRequest.setPageNumber(2);
		pageRequest.setPageSize(4);
		Page page = this.attrDao.pageDynQuery(sql, pageRequest);
		return page;
	}
	
	public List test2(){
		PageRequestExt pageRequest = new PageRequestExt();
		pageRequest.setTotalNum(5);
		pageRequest.setPageNumber(2);
		pageRequest.setPageSize(4);
		return baseDao.findAll("FW_EMPLOYEE");
	}
	
	public static void main(String[] args) throws Exception{
		String[] paths = { "classpath*:spring/*.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		VmModelBo man = (VmModelBo)ctx.getBean("vmModelManager");
		List<Object> list = man.test2();
		for(Object map:list){
			System.out.println(map.toString());
		}
	}

}
