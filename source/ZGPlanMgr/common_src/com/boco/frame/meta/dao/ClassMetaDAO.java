/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2009 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *  @Author  ������
 *  @Date    Aug 5, 2009 4:02:05 PM
 *  @Version 3.0
 */
package com.boco.frame.meta.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.boco.frame.cache.ICacheService;
import com.boco.frame.meta.model.BMAttrGroup;
import com.boco.frame.meta.model.BMAttrMeta;
import com.boco.frame.meta.model.BMClassMeta;
import com.boco.frame.meta.model.BMEnumMeta;
import com.boco.frame.meta.model.BMRelationClass;
import com.boco.frame.meta.model.BMStatClass;
import com.boco.frame.meta.model.DrmEnumValue;
import com.boco.frame.meta.model.BMAttrMeta.ATTR_TYPE_ENUM;
import com.boco.frame.sys.base.dao.ProvinceDao;
import com.boco.frame.sys.base.model.FwOperator;
import com.boco.frame.sys.base.model.Province;

@Component
public class ClassMetaDAO extends SqlMapClientDaoSupport {
	/**
	 * 历史类型
	 */
	public static final String HISTORY_CLASS_ID = "T_SYS_RES_HISTORY";
	/**
	 * 历史表后缀
	 */
	public static final String HISTORY_END = "_H";
	public final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("cacheService") 
	private ICacheService cache ;
	//private IrmsCacheStore cache = IrmsCacheStore.getInstance();
	private static final String NAME_SPACE = "ClassMetaNameSpace";
	
	public static final String CACHE_KEY_PREFIX_GET_CLASSMETA=ClassMetaDAO.class.getName()+"getClassMeta";
	public static final String CACHE_KEY_PREFIX_GET_ENUMMETA=ClassMetaDAO.class.getName()+"getEnumMeta";
	public static final String CACHE_KEY_PREFIX_GET_ALL_ENUMMETA=ClassMetaDAO.class.getName()+"getAllEnumMeta";
	public static final String CACHE_KEY_PREFIX_GET_ALL_CLASSMETA=ClassMetaDAO.class.getName()+"getAllClassMeta";
	public static final String CENTER_TABLE = "T_MD_RES_RES";
	public static final String LEFT_ATTR_NAME = "RELATED_LEFT_CUID";
	public static final String RIGHT_ATTR_NAME = "RELATED_RIGHT_CUID";
	public ClassMetaDAO(){
		super();
	}
	
	private BMClassMeta getBmClassMeta(String bmClassId,Map temp,String cacheKey){
		BMClassMeta classMeta = new BMClassMeta();
		classMeta.setBmClassId(bmClassId);
		/**
		 * 历史表ClassMeta
		 */
		boolean isHistory = false;
		if(bmClassId.endsWith(HISTORY_END)){
			bmClassId = bmClassId.substring(0,bmClassId.length()-2);
			isHistory = true;
		}
		List<Map> allAttrs = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllAttrs",bmClassId);
		List<Map> allUniqueAttrIds = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllUniqueAttrIds",bmClassId);
		//资源图标
		List<Map> iconPaths = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getIconPath",bmClassId);
		if(iconPaths!=null&&iconPaths.size()>0){
			Map map = iconPaths.get(0);
			String path = map.get("IMAGE_PATH").toString();
			String smallPath = StringUtils.substringBeforeLast(path, ".")+"_small."+StringUtils.substringAfterLast(path, ".");
			classMeta.setIconPath(path);
			classMeta.setIconSmallPath(smallPath);
		}
		List<Map> statClassAttrs = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getStatClassAttr",bmClassId);
		List<Map> parentType = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getParentType",bmClassId);
		Map<String,BMAttrGroup> attrGroupList = new HashMap<String,BMAttrGroup>();
		if(parentType.size() == 0){
			return null;
		}
		String parentClassId = IbatisDAOHelper.getStringValue(parentType.get(0), "PARENT_BM_CLASSID");
		classMeta.setRelatedRes(IbatisDAOHelper.getStringValue(parentType.get(0), "RELATED_RESCLASS_CUID"));
		classMeta.setFlag(IbatisDAOHelper.getStringValue(parentType.get(0), "FLAG"));
		/**
		 * 构造名称属性集合
		 */
		List<Map> nameAttrs = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getNameAttr",bmClassId);
		List<String> nameAttrList = new ArrayList<String>();
		for(int n = 0 ; n < nameAttrs.size();n++){
			String nameAttr = nameAttrs.get(n).get("COLUMN_NAME").toString().toUpperCase();
			if(!nameAttrList.contains(nameAttr)){
				nameAttrList.add(nameAttr);
			}
		}
		if(nameAttrList.size()>0){
			classMeta.setConstructLabelAttrIds(nameAttrList);
		}
		/**
		 * 构造唯一属性集合
		 */
		Map<String, String[]> uniqueAttrIds = new HashMap<String, String[]>();
		if(allUniqueAttrIds.size() != 0){
			Map<String, List<String>> uniqueAttrIdsTemp = new HashMap<String, List<String>>();
			for(int num = 0 ; num < allUniqueAttrIds.size(); num++){
				Map dbo = allUniqueAttrIds.get(num);
				String label = IbatisDAOHelper.getStringValue(dbo,"LABEL_CN");
				String columnName = IbatisDAOHelper.getStringValue(dbo,"COLUMN_NAME").toUpperCase();
				List<String> list = uniqueAttrIdsTemp.get(label);
				if(list == null){
					List<String> columnList = new ArrayList<String>();
					columnList.add(columnName);
					uniqueAttrIdsTemp.put(label, columnList);
				}else{
					List<String>  columnList = uniqueAttrIdsTemp.get(label);
					columnList.add(columnName);
					uniqueAttrIdsTemp.put(label, columnList);
				}
			}
			for(String key : uniqueAttrIdsTemp.keySet()){
				uniqueAttrIds.put(key, uniqueAttrIdsTemp.get(key).toArray(new String[uniqueAttrIdsTemp.get(key).size()]));
			}
		}
		classMeta.setUniqueAttrIds(uniqueAttrIds);
		/**
		 * 构造关系属性(复杂关联)
		 */

		List<Map> leftRelationAttrs = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllRelation",classMeta.getBmClassId());
		for(int i = 0 ; i < leftRelationAttrs.size(); i++){
			Map map = leftRelationAttrs.get(i);
			BMRelationClass relationClass = new BMRelationClass();
			/**
			 * 当前类型
			 */
			String curType = classMeta.getBmClassId();
			String leftType = IbatisDAOHelper.getStringValue(map,"LEFTTYPE");
			String rightType = IbatisDAOHelper.getStringValue(map,"RIGHTTYPE");
			String leftAttrName = IbatisDAOHelper.getStringValue(map,"LEFT_ATTR_NAME");
			String rightAttrName = IbatisDAOHelper.getStringValue(map,"RIGHT_ATTR_NAME");
			String leftInhibit = IbatisDAOHelper.getStringValue(map,"LEFT_INHIBIT");
			String rightInhibit = IbatisDAOHelper.getStringValue(map,"RIGHT_INHIBIT");
			String leftTypeName = IbatisDAOHelper.getStringValue(map,"LEFTNAME");
			String rightTypeName = IbatisDAOHelper.getStringValue(map,"RIGHTNAME");
			List<String> upperTypeList = new ArrayList<String>();
			if(!curType.equalsIgnoreCase(leftType)){
				upperTypeList = getUpperOrLowerType(curType,true);
			}
			if(curType.equalsIgnoreCase(leftType) || upperTypeList.contains(leftType)){
				relationClass.setLeftBmClassId(leftType);
				relationClass.setRightBmClassId(rightType);
				relationClass.setLeftTypeName(leftTypeName);
				relationClass.setRightTypeName(rightTypeName);
				if(StringUtils.trimToNull(leftAttrName)== null){
					relationClass.setLeftAttr(LEFT_ATTR_NAME);
				}else{
					relationClass.setLeftAttr(leftAttrName);
				}
				if(StringUtils.trimToNull(rightAttrName)== null){
					relationClass.setRightAttr(RIGHT_ATTR_NAME);
				}else{
					relationClass.setRightAttr(rightAttrName);
				}
				relationClass.setRelationType(leftInhibit+":"+rightInhibit);
				relationClass.setInclude(true);
			}else{
				relationClass.setLeftBmClassId(rightType);
				relationClass.setRightBmClassId(leftType);
				relationClass.setLeftTypeName(rightTypeName);
				relationClass.setRightTypeName(leftTypeName);
				if(StringUtils.trimToNull(rightAttrName)== null){
					relationClass.setLeftAttr(RIGHT_ATTR_NAME);
				}else{
					relationClass.setLeftAttr(rightAttrName);
				}
				if(StringUtils.trimToNull(leftAttrName)== null){
					relationClass.setRightAttr(LEFT_ATTR_NAME);
				}else{
					relationClass.setRightAttr(leftAttrName);
				}
				relationClass.setRelationType(rightInhibit+":"+leftInhibit);
				relationClass.setInclude(false);
			}
			if(StringUtils.trimToNull(IbatisDAOHelper.getStringValue(map,"CENTER_TABLE_NAME"))== null){
				relationClass.setCenterTableName(CENTER_TABLE);
			}else{
				relationClass.setCenterTableName(IbatisDAOHelper.getStringValue(map,"CENTER_TABLE_NAME"));
			}
			relationClass.setRelationName(IbatisDAOHelper.getStringValue(map,"LABEL_CN"));
			relationClass.setRelationCode(IbatisDAOHelper.getStringValue(map,"DIC_CODE"));
			classMeta.addRelationClass(relationClass);
		}
		if(allAttrs.size() > 0){
			Map<String,Integer> relatedClassIndex =new HashMap<String, Integer>();
			int relatedIndex = -1;
			Map attrFirst = allAttrs.get(0);
			for(int k = 0 ; k < allAttrs.size() ;k++){
				Map attr = allAttrs.get(k);
				if(isHistory){
					classMeta.setLabelCn(IbatisDAOHelper.getStringValue(attrFirst,"LABEL_CN_T3")+"历史");
				}else{
					classMeta.setLabelCn(IbatisDAOHelper.getStringValue(attrFirst,"LABEL_CN_T3"));
				}
				//classMeta.setLabelAttrId(IbatisDAOHelper.getStringValue(attrFirst,"COLUMN_NAME_R"));
				classMeta.setLabelJoinChar(IbatisDAOHelper.getStringValue(attrFirst,"LABEL_JOIN_CHAR_T3"));
				if(StringUtils.trimToNull(IbatisDAOHelper.getStringValue(attrFirst,"OBJECT_SELECT_XTYPE_T3")) != null){
					classMeta.setObjSelectXType(IbatisDAOHelper.getStringValue(attrFirst,"OBJECT_SELECT_XTYPE_T3"));
				}
				classMeta.setIsShowAddBtn("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_SHOW_ADD_BTN_T3")));
				classMeta.setIsShowDeleteBtn("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_SHOW_DELETE_BTN_T3")));
				classMeta.setIsShowEditBtn("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_SHOW_EDIT_BTN_T3")));
				classMeta.setIsShowExportBtn("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_SHOW_EXPORT_BTN_T3")));
				classMeta.setIsShowImportBtn("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_SHOW_IMPORT_BTN_T3")));
				classMeta.setIsVirtual("1".equals(IbatisDAOHelper.getStringValue(attrFirst,"IS_VIRTUAL_T3")));
				
				BMAttrMeta attrMeta = new BMAttrMeta();
				attrMeta.setBmClassId(classMeta.getBmClassId());
				attrMeta.setAttrId(IbatisDAOHelper.getStringValue(attr,"COLUMN_NAME").toUpperCase());
				attrMeta.setLabelCn(IbatisDAOHelper.getStringValue(attr,"LABEL_CN"));
				String dbType = IbatisDAOHelper.getStringValue(attr,"DATA_TYPE");
				String attrDbType = "";
				if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.STRING)){
					/**
					 * 字符串
					 */
					attrDbType = ATTR_TYPE_ENUM.STRING;
				}else if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.INT)){
					/**
					 * 数字
					 */
					attrDbType = ATTR_TYPE_ENUM.INT;
				}else if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.FLOAT)){
					/**
					 * 浮点型
					 */
					attrDbType = ATTR_TYPE_ENUM.FLOAT;
				}else if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.DATE)){
					/**
					 * 日期
					 */
					attrDbType = ATTR_TYPE_ENUM.DATE;
				}else if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.ENUM)){
					/**
					 * 枚举
					 */
					attrDbType = ATTR_TYPE_ENUM.ENUM;
				}else if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.RELATION)){
					/**
					 * 约束(关联)
					 */
					attrDbType = ATTR_TYPE_ENUM.STRING;
				}
				attrMeta.setAttrDbType(attrDbType);
				attrMeta.setDefaultValue(IbatisDAOHelper.getStringValue(attr,"DEFAULT_VALUE"));
				if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.ENUM) || dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.STATUS)){
					attrMeta.setEnumId(IbatisDAOHelper.getStringValue(attr,"ENUM_KEY"));
				}
				attrMeta.setIsReadOnly("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_READABLE")));
				attrMeta.setIsSystem("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_SYSTEM")));
				if(attrMeta.getAttrId().equalsIgnoreCase(classMeta.getIdAttrMeta().getAttrId())){
					attrMeta.setIsSystem(true);
				}
				attrMeta.setIsNotNull("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_NOTNULL")));
				attrMeta.setStrAttrMaxLen(Integer.parseInt(attr.get("STR_ATTR_MAXLEN")==null?"0":attr.get("STR_ATTR_MAXLEN").toString()));
				attrMeta.setSourceType(Integer.valueOf(attr.get("DATA_SOURCE")==null?"0":attr.get("DATA_SOURCE").toString()));
				attrMeta.setValidRegExp(IbatisDAOHelper.getStringValue(attr,"VERIFY_EXPRESSION"));
				attrMeta.setValidCalcExp(IbatisDAOHelper.getStringValue(attr,"CALCULATE_EXPRESSION"));
				attrMeta.setXtype(IbatisDAOHelper.getStringValue(attr,"XTYPE"));
				attrMeta.setRemark(IbatisDAOHelper.getStringValue(attr,"DESCRIPTION"));
				/**
				 * 构造关联属性
				 */
				if(dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.RELATION)){
					relatedIndex++;
					List<Map> relatedAttrs = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getRelatedAttrs",attr.get("CUID"));
					BMAttrMeta relatedAttrMeta = null;
					String filter = "";
					if(relatedAttrs.size() != 0){
						/**
						 * 属性集表名
						 */
						Object relatedBmClassId = relatedAttrs.get(0).get("BM_CLASSID");
						Object obj = relatedAttrs.get(0).get("FILTER");
						if(obj != null){
							filter = ""+relatedAttrs.get(0).get("FILTER");
						}
						/**
						 * 默认关联到对方表的主键:CUID
						 */
						String key = classMeta.getBmClassId()+classMeta.getLabelCn()+attrMeta.getAttrId();
						if(relatedBmClassId != null){
							int tempIndex = relatedClassIndex.get(relatedBmClassId.toString())==null?0:relatedClassIndex.get(relatedBmClassId.toString());
							relatedClassIndex.put(relatedBmClassId.toString(), tempIndex+1 );
							String value = relatedBmClassId+".CUID";
							if(relatedBmClassId.toString().equalsIgnoreCase(classMeta.getBmClassId())){
								relatedAttrMeta = classMeta.getIdAttrMeta();
							}else {
								if(!(value).equals(temp.get(key))){
									temp.put(key, value);
									BMClassMeta relatedClassMeta = this.getClassMeta(relatedBmClassId.toString(),temp);
									relatedAttrMeta = relatedClassMeta.getIdAttrMeta();
								}else{
									BMClassMeta relatedClassMeta = this.getClassMeta(relatedBmClassId.toString(),temp);
									relatedAttrMeta = relatedClassMeta.getIdAttrMeta();
								}
							}
							attrMeta.setRelatedIndex(relatedIndex+"_"+relatedClassIndex.get(relatedBmClassId.toString()));
						}
					}
					attrMeta.setRelatedMeta(relatedAttrMeta);
					attrMeta.setRelatedFilterName(filter);
				}
				/**
				 * 构造统计关联属性
				 */
				List<BMStatClass> statClasses = new ArrayList<BMStatClass>();
				if(statClassAttrs != null && statClassAttrs.size() > 0)
				for(int si = 0; si < statClassAttrs.size(); si++){
					Map statClass = statClassAttrs.get(si);
					BMStatClass stat = new BMStatClass();
					stat.setAttrId(IbatisDAOHelper.getStringValue(statClass,"COLUMN_NAME").toUpperCase());
					stat.setBmClassId(IbatisDAOHelper.getStringValue(statClass,"BM_CLASSID"));
					stat.setCascadeType(IbatisDAOHelper.getStringValue(statClass,"CASCADE_TYPE"));
					stat.setStatType(IbatisDAOHelper.getStringValue(statClass,"STAT_TYPE"));
					stat.setFilter(IbatisDAOHelper.getStringValue(statClass,"FILTER"));
					if(stat.getAttrId().equalsIgnoreCase(attrMeta.getAttrId())){
						attrMeta.setRelatedFilterName(stat.getFilter());
					}
					statClasses.add(stat);
				}
				classMeta.setStatClasses(statClasses);
				/**
				 * 构造属性集
				 */
				if(attrGroupList.get(attr.get("CUID_T1")) != null){
					attrGroupList.get(attr.get("CUID_T1")).addAttrMeta(attrMeta);
					attrMeta.setAttrGroup(attrGroupList.get(attr.get("CUID_T1")));
				}else{
					BMAttrGroup attrGroup = new BMAttrGroup();
					attrGroup.setCuid(IbatisDAOHelper.getStringValue(attr,"CUID_T1"));
					attrGroup.setDescription(IbatisDAOHelper.getStringValue(attr,"DESCRIPTION_T1"));
					attrGroup.setIsBasic("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_BASIC_T1")));
					attrGroup.setLabelCn(IbatisDAOHelper.getStringValue(attr,"LABEL_CN_T1"));
					if(isHistory){
						attrGroup.setTableName(IbatisDAOHelper.getStringValue(attr,"TABLE_NAME_T1")+HISTORY_END);
					}else{
						attrGroup.setTableName(IbatisDAOHelper.getStringValue(attr,"TABLE_NAME_T1"));
					}
					attrGroup.setPkCol(classMeta.getIdAttrMeta().getAttrId());
					/**
					 * 固化属性组
					 */
					if("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_STATIC_T1"))){
						attrGroup.setIsStatic(true);
						attrMeta.setIsReadOnly(true);
					}else{
						attrGroup.setIsStatic(false);
					}
					/**
					 * 系统属性组
					 */
					if("1".equals(IbatisDAOHelper.getStringValue(attr,"IS_SYSTEM_T1"))){
						attrGroup.setIsSystem(true);
					}else{
						attrGroup.setIsSystem(false);
					}
					attrGroup.addAttrMeta(attrMeta);
					attrGroupList.put(attrGroup.getCuid(), attrGroup);
					if(attrGroup.getIsBasic()){
						/**
						 * 设置抽象类型的主键为主属性集的主键
						 */
						classMeta.setCuidAttrId(classMeta.getIdAttrMeta().getAttrId());
					}
				}
		        attrMeta.setClassMeta(classMeta);
				classMeta.addAttrMeta(attrMeta);
			}
			classMeta.getAttrGroup().addAll(attrGroupList.values());
		}
		/**
		 * 获取上级的属性组
		 */
		List<BMAttrGroup> parentAttrGroup = null;
		if(parentClassId != null){
			/**
			 * 拷贝上级类型的属性
			 */
			if(isHistory){
				parentClassId = parentClassId + HISTORY_END;
			}
			BMClassMeta parentClassMeta = this.getClassMeta(parentClassId);
			if(parentClassMeta != null){
				classMeta.setCuidAttrId(classMeta.getIdAttrMeta().getAttrId());
				Map curType = parentType.get(0);
				classMeta.setLabelCn(IbatisDAOHelper.getStringValue(curType,"LABEL_CN"));
				//classMeta.setLabelAttrId(parentClassMeta.getLabelAttrId());
				classMeta.setLabelJoinChar(IbatisDAOHelper.getStringValue(curType,"LABEL_JOIN_CHAR"));
				classMeta.setObjSelectXType(IbatisDAOHelper.getStringValue(curType,"OBJECT_SELECT_XTYPE"));
				classMeta.setIsShowAddBtn("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_SHOW_ADD_BTN")));
				classMeta.setIsShowDeleteBtn("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_SHOW_DELETE_BTN")));
				classMeta.setIsShowEditBtn("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_SHOW_EDIT_BTN")));
				classMeta.setIsShowExportBtn("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_SHOW_EXPORT_BTN")));
				classMeta.setIsShowImportBtn("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_SHOW_IMPORT_BTN")));
				classMeta.setIsVirtual("1".equals(IbatisDAOHelper.getStringValue(curType,"IS_VIRTUAL")));
				/**
				 * 唯一属性
				 */
				if(parentClassMeta.getUniqueAttrIds() != null && parentClassMeta.getUniqueAttrIds().size() != 0){
					classMeta.getUniqueAttrIds().putAll(parentClassMeta.getUniqueAttrIds());
				}
				/**
				 * 关系属性(复杂关联)
				 */
				//classMeta.getRelationClassList().addAll(parentClassMeta.getRelationClassList());
				/**
				 * 统计关联属性
				 */
				if(parentClassMeta.getStatClasses() != null && parentClassMeta.getStatClasses().size()!= 0){
					classMeta.getStatClasses().addAll(parentClassMeta.getStatClasses());
				}
				parentAttrGroup = parentClassMeta.getAttrGroup();
			}
		}
		if(parentAttrGroup != null){
			classMeta.getAttrGroup().addAll(parentAttrGroup);
			for(int i = 0 ; i < parentAttrGroup.size(); i++){
				for(int j = 0 ; j < parentAttrGroup.get(i).getAttrList().size(); j++){
					try {
						BMAttrMeta attrMetaTemp = parentAttrGroup.get(i).getAttrList().get(j);
						BMAttrMeta attrMeta = (BMAttrMeta)cn.org.rapid_framework.beanutils.BeanUtils.cloneBean(attrMetaTemp);
						attrMeta.setClassMeta(classMeta);
						classMeta.addAttrMeta(attrMeta);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					//parentAttrGroup.get(i).getAttrList().get(j).setClassMeta(classMeta);
					//classMeta.addAttrMeta(parentAttrGroup.get(i).getAttrList().get(j));
				}
			}
		}
		/**
		 * 如果classMeta的默认主键为空,设置默认主键为第一个属性集的主键
		 * 构造属性组别名
		 */
		BMAttrGroup basicAttrGroup = null;
		if(classMeta !=null && classMeta.getAttrGroup().size()>0){
			Map<String,BMAttrGroup> map = new HashMap<String,BMAttrGroup>();
			if(classMeta.getBmClassId().equalsIgnoreCase("FW_EMPLOYEE")){
				System.out.println("111");
			}
			for(BMAttrGroup attrGroup : classMeta.getAttrGroup()){
				if(attrGroup.getIsBasic()){
					basicAttrGroup = attrGroup;
				}
				map.put("t"+map.size(), attrGroup);
			}
			classMeta.setAttrGroupMap(map);
			if(StringUtils.trimToNull(classMeta.getPkCol()) == null){
				classMeta.setPkCol(classMeta.getIdAttrMeta().getAttrId());
			}
			Map<String,BMAttrMeta> allAttrMetas = classMeta.getAttrMetas();
			int i = 1;
			for(BMAttrMeta attrMeta : allAttrMetas.values()){
				if(attrMeta.getIsRelation()){
					classMeta.addRelatedAttrMeta(attrMeta);
					classMeta.getRelatedAttrMetaNum().put(attrMeta.getAttrId(), (i++)+"");
				}
			}
		}
		/**
		 * 将被关联表主键attrMeta设置为完整的attrMeta(原来的只有cuid和bmClassId)
		 */
		List<BMAttrMeta> relatedAttrMetaCheck = classMeta.getRelatedAttrMetas();
		for(int i = 0 ; i < relatedAttrMetaCheck.size(); i++){
			BMAttrMeta relatedMeta = relatedAttrMetaCheck.get(i).getRelatedMeta();
			if(relatedMeta.getBmClassId().equalsIgnoreCase(classMeta.getBmClassId()) && relatedMeta.getAttrId().equalsIgnoreCase(classMeta.getIdAttrMeta().getAttrId())){
				relatedAttrMetaCheck.get(i).setRelatedMeta(classMeta.getAttrMeta(classMeta.getIdAttrMeta().getAttrId()));
			}
		}
		/**
		 * 是否添加过历史meta
		 */
		boolean isAddHistory = false;
		/**
		 * 为每个属性组添加字段:cuid(以后要加:CREATE_TIME,LAST_MODIFY_TIME等字段)
		 * 并且当是获取历史meta时,判断是否添加过历史meta
		 */
		for(int i = 0 ; i < classMeta.getAttrGroup().size(); i++){
			BMAttrGroup attrGroup = classMeta.getAttrGroup().get(i);
			if("T_RES_HISTORY".equalsIgnoreCase(attrGroup.getTableName())){
				isAddHistory = true;
			}
			boolean hasCUID = false;
			for(BMAttrMeta attrMeta:attrGroup.getAttrList()){
				if(classMeta.getIdAttrMeta().getAttrId().equalsIgnoreCase(attrMeta.getAttrId())){
					hasCUID = true;
				}
			}
			if(!hasCUID){
				BMAttrMeta cuidAttrMeta = new BMAttrMeta();
				cuidAttrMeta.setBmClassId(classMeta.getBmClassId());
				cuidAttrMeta.setAttrId(classMeta.getIdAttrMeta().getAttrId());
				cuidAttrMeta.setClassMeta(classMeta);
				cuidAttrMeta.setAttrDbType(ATTR_TYPE_ENUM.STRING);
				cuidAttrMeta.setIsSystem(true);
				cuidAttrMeta.setIsNotNull(true);
				cuidAttrMeta.setIsReadOnly(true);
				cuidAttrMeta.setAttrGroup(attrGroup);
				attrGroup.addAttrMeta(cuidAttrMeta);
				cuidAttrMeta.setClassMeta(classMeta);
				classMeta.addAttrMeta(cuidAttrMeta);
			}
		}
		if(isHistory && !isAddHistory){
			BMClassMeta hisClassMeta = this.getClassMeta(HISTORY_CLASS_ID, temp);
			classMeta.getAttrGroup().addAll(hisClassMeta.getAttrGroup());
			classMeta.getAttrMetas().putAll(hisClassMeta.getAttrMetas());
		}
		addColumns(classMeta);
		if(classMeta!=null){
			logger.info("放入元素;"+new Date().toLocaleString()+":"+classMeta.getBmClassId());
		}
		if(classMeta != null){
			cache.putElement(cacheKey, classMeta);
		}
		return classMeta;
	}
	@SuppressWarnings("unchecked")
	private BMClassMeta getClassMeta(String bmClassId,Map temp){
		if(StringUtils.trimToNull(bmClassId)==null){
			return null;
		}
		String cacheKey = CACHE_KEY_PREFIX_GET_CLASSMETA+bmClassId;
		if(cache.getValue(cacheKey) != null){
			BMClassMeta cacheMeta = null;
			try {
				if(cache.getValue(cacheKey) instanceof BMClassMeta){
					cacheMeta = (BMClassMeta)cache.getValue(cacheKey);
				}else{
					log.error("["+bmClassId+"]获取缓存Meta类型错误，重新刷新系统缓存!");
					return this.getBmClassMeta(bmClassId, temp, cacheKey);
				}
			} catch (Exception e) {
				log.error("["+bmClassId+"]获取缓存Meta错误,提示:清除系统缓存!");
				e.printStackTrace();
				throw new RuntimeException("["+bmClassId+"]获取缓存Meta错误,提示:清除系统缓存!");
			}
			if(cacheMeta != null && !cacheMeta.getBmClassId().equals(bmClassId)){
				log.error("["+bmClassId+"]获取缓存Meta与期望的不一致,重新刷新系统缓存!");
				return this.getBmClassMeta(bmClassId, temp, cacheKey);
			}
			return cacheMeta;
		}
		
		return this.getBmClassMeta(bmClassId, temp, cacheKey);
	}
	
	
	/**
	 * 根据bmClassId获取BMClassMeta
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Aug 6, 2009 9:03:25 PM
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	@SuppressWarnings("unchecked")
	public BMClassMeta getClassMeta(String bmClassId){
		/**
		 * 关联查询缓存
		 * key:当前表的bmClassId+labelCn+attrId    value:relatedBmClassId+"."+columnName
		 */
		Map<String,String> tempMap = new HashMap<String,String>();
		BMClassMeta classMeta = this.getClassMeta(bmClassId, tempMap);
		return classMeta;
	}
	/**
	 * 添加CREATE_TIME,LAST_MODIFY_TIME,CREATE_USER,ISDELETE,ISVERSION等字段
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Sep 8, 2009 7:03:03 PM
	 * @param classMeta
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	private void addColumns(BMClassMeta classMeta){
	}
	
	/**
	 * 根据enumId获取BMEnumMeta
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Aug 6, 2009 9:03:25 PM
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	@SuppressWarnings("unchecked")
	public BMEnumMeta getEnumMeta(String enumId){
		BMEnumMeta enumMeta = null;
		String cacheKey = CACHE_KEY_PREFIX_GET_ENUMMETA+enumId;
		if(cache.getValue(cacheKey) != null){
			enumMeta = (BMEnumMeta) cache.getValue(cacheKey);
			return enumMeta;
		}
		try {
			List<Map> enums = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getEnumByEnumId",enumId);
			if(enums.size() != 0){
				enumMeta = new BMEnumMeta();
				enumMeta.setEnumId(enumId);
				enumMeta.setEnumLabelCn(enums.get(0).get("LABEL_CN").toString());
				enumMeta.setType(enums.get(0).get("TYPE")==null?null:enums.get(0).get("TYPE").toString());
				enumMeta.setEnumCode(enums.get(0).get("DIC_CODE")==null?null:enums.get(0).get("DIC_CODE").toString());
				List<DrmEnumValue> enumObjects = new ArrayList<DrmEnumValue>();
				for(int i = 0 ; i < enums.size(); i++){
					if("enum".equals(enumMeta.getType())){
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						enumObjects.add(enumValue);
					}else{
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						enumValue.setColor(enums.get(i).get("COLOR"));
						enumObjects.add(enumValue);
					}
				}
				enumMeta.setEnums(enumObjects);
				//enumMeta.setDefaultEnumIndex(Integer.parseInt(enums.get(0).get("DEFAULT_VALUE_T3")==null?"1":enums.get(0).get("DEFAULT_VALUE_T3").toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cache.putElement(cacheKey, enumMeta);
		return enumMeta;
	}
	
	/**
	 * 根据enumId获取BMEnumMeta
	 * @Company BOCO
	 * @Author  张颖慧
	 * @Date    Aug 6, 2009 9:03:25 PM
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	
	public BMEnumMeta getEnumMetaByCode(String enumCode){
		BMEnumMeta enumMeta = null;
		String cacheKey = CACHE_KEY_PREFIX_GET_ENUMMETA+enumCode;
		if(cache.getValue(cacheKey) != null){
			enumMeta = (BMEnumMeta) cache.getValue(cacheKey);
			return enumMeta;
		}
		try {
			List<Map> enums = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getEnumByEnumName",enumCode);
			if(enums.size() != 0){
				enumMeta = new BMEnumMeta();
				enumMeta.setEnumId(enums.get(0).get("CUID").toString());
				enumMeta.setEnumLabelCn(enums.get(0).get("LABEL_CN").toString());
				enumMeta.setType(enums.get(0).get("TYPE")==null?null:enums.get(0).get("TYPE").toString());
				enumMeta.setEnumCode(enums.get(0).get("DIC_CODE")==null?null:enums.get(0).get("DIC_CODE").toString());
				List<DrmEnumValue> enumObjects = new ArrayList<DrmEnumValue>();
				for(int i = 0 ; i < enums.size(); i++){
					if("enum".equals(enumMeta.getType())){
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						enumObjects.add(enumValue);
					}else{
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						enumValue.setColor(enums.get(i).get("COLOR"));
						enumObjects.add(enumValue);
					}
				}
				enumMeta.setEnums(enumObjects);
				//enumMeta.setDefaultEnumIndex(Integer.parseInt(enums.get(0).get("DEFAULT_VALUE_T3")==null?"1":enums.get(0).get("DEFAULT_VALUE_T3").toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cache.putElement(cacheKey, enumMeta);
		return enumMeta;
	}
	
	
	public Map<String, BMEnumMeta> getAllEnumMeta(){
		Map<String,BMEnumMeta> enumMetaMap = new HashMap<String,BMEnumMeta>();
		String cacheKey = CACHE_KEY_PREFIX_GET_ALL_ENUMMETA;
		if(cache.getValue(cacheKey) != null){
			return (Map<String,BMEnumMeta>) cache.getValue(cacheKey);
		}
		BMEnumMeta enumMeta = null;
		try {
			List<Map> enums = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllEnums");
			if(enums.size() != 0){
				for(int i = 0 ; i < enums.size(); i++){
					String enumId = enums.get(i).get("RELATED_ET_CUID_T2").toString();
					enumMeta = enumMetaMap.get(enumId);
					if(enumMeta == null){
						enumMeta = new BMEnumMeta();
						enumMeta.setEnumId(enumId);
						enumMeta.setEnumLabelCn(enums.get(i).get("LABEL_CN").toString());
						enumMeta.setType(enums.get(i).get("TYPE")==null?null:enums.get(i).get("TYPE").toString());
						enumMeta.setEnumCode(enums.get(0).get("DIC_CODE")==null?null:enums.get(0).get("DIC_CODE").toString());
					}
					if("enum".equals(enumMeta.getType())){
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						if(!enumMeta.getEnums().contains(enumValue)){
							enumMeta.addEnum(enumValue);
						}else{
							continue;
						}
					}else{
						DrmEnumValue enumValue = new DrmEnumValue();
						enumValue.setEnumName(enums.get(i).get("NAME_T2")==null?null:enums.get(i).get("NAME_T2").toString());
						enumValue.setEnumValue(enums.get(i).get("VALUE_T2"));
						enumValue.setColor(enums.get(i).get("COLOR"));
						if(!enumMeta.getEnums().contains(enumValue)){
							enumMeta.addEnum(enumValue);
						}else{
							continue;
						}
					}
					enumMeta.setDefaultEnumIndex(Integer.parseInt(enums.get(i).get("DEFAULT_VALUE_T3")==null?"1":enums.get(i).get("DEFAULT_VALUE_T3").toString()));
					enumMetaMap.put(enumMeta.getEnumId(), enumMeta);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cache.putElement(cacheKey, enumMetaMap);
		return enumMetaMap;
	}
	@SuppressWarnings("unchecked")
	public Map<String, BMClassMeta> getAllClassMeta(){
		Map<String, BMClassMeta> allClassMetas = new HashMap<String,BMClassMeta>();
		String cacheKey = CACHE_KEY_PREFIX_GET_ALL_CLASSMETA;
		if(cache.getValue(cacheKey) != null){
			Map<String, BMClassMeta> map = (Map<String, BMClassMeta>)cache.getValue(cacheKey);
			if(map.size()!=0){
				return map;
			}
		}
		List<Map> allAbstractTypes = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllAbstractTypes");
		if(allAbstractTypes != null && allAbstractTypes.size() > 0){
			for(int i = 0 ; i < allAbstractTypes.size(); i++){
				Object obj = allAbstractTypes.get(i).get("BM_CLASSID");
				if(obj != null){
					String bmClassId = allAbstractTypes.get(i).get("BM_CLASSID").toString();
					allClassMetas.put(bmClassId, getClassMeta(bmClassId));
				}
			}
		}
		cache.putElement(cacheKey, allClassMetas);
		return allClassMetas;
	}
	
	public BMClassMeta refreshClassMeta(String bmClassId){
		String cacheKey = CACHE_KEY_PREFIX_GET_CLASSMETA+bmClassId;
		cache.removeElement(cacheKey);
		return this.getClassMeta(bmClassId);
	}
	public List<String> getUpperOrLowerType(String bmClassId,boolean isUpper){
		if(StringUtils.trimToNull(bmClassId) == null){
			throw new RuntimeException("bmClassId为空不能获取上级或下级");
		}
		List<Map> list = null;
		if(isUpper){
			list = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllParentType",bmClassId);
		}else{
			list = getSqlMapClientTemplate().queryForList(NAME_SPACE + ".getAllChildType",bmClassId);
		}
		List<String> ret = new ArrayList<String>();
		if(list != null && list.size() > 0){
			for(int i = 0 ; i < list.size() ; i++){
				ret.add(IbatisDAOHelper.getStringValue(list.get(i), "BM_CLASSID"));
			}
		}else{
			ret.add(bmClassId);
		}
		return ret;
	}
	public static void main(String[] args){
		String[] paths = { "classpath*:spring/*.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		ProvinceDao dao  = (ProvinceDao)ctx.getBean("provinceDao");
		Province entity  = new Province();
		entity.setEqualBmClassIdQuery("true");
		entity.setInSubBmClassIdQuery("true");
		List<Province> list = dao.findByProperty(entity);
		for(Province e:list){
			System.out.println(e.getName());
		}
	}
}
