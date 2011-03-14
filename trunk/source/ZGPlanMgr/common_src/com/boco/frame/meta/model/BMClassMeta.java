/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2006 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *
 *
 */
/**
 *Revision Information:
 *
 *@version 1.0 2008-5-1 release(zhangxu)
 */
package com.boco.frame.meta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.util.StringHelper;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@SuppressWarnings("unchecked")
public class BMClassMeta implements Serializable{
	public static final String RESCLASSSYS="SYSTABLE";
    private static final long serialVersionUID = 1L;
    public static final String DIV_VALUE_SPLIT = "-";

    /**
     * 属性集
     */
    private List<BMAttrGroup> attrGroup = new ArrayList<BMAttrGroup>();
    
    /**
     * 属性组及别名集合,使用此map进行sql语句组装
     */
    private Map<String,BMAttrGroup> attrGroupMap = new HashMap<String, BMAttrGroup>();
    /**
     * 获取本表的关联attrMetas
     */
    private List<BMAttrMeta> relatedAttrMetas = new ArrayList<BMAttrMeta>();
    /**
     * 获取当前业务类关联的制定业务类的属性
     * */
    public List<BMAttrMeta> getRelatedClassAttrMeta(String relatedBmClassId) {
    	List<BMAttrMeta> relatedAttrMetas = this.relatedAttrMetas;
    	List<BMAttrMeta> ret = new ArrayList<BMAttrMeta>();
    	if(relatedAttrMetas.size() > 0){
	    	for(int i = 0 ; i < relatedAttrMetas.size(); i++){
	    		if(relatedBmClassId.equals(relatedAttrMetas.get(i).getRelatedMeta().getBmClassId())){
	    			ret.add(relatedAttrMetas.get(i));
	    		}
	    	}
    	}
        return ret;
    }

    /**
     * 获取所有属性组
     * @return
     */
	public List<BMAttrGroup> getAttrGroup() {
		return attrGroup;
	}

	public void addAttrGroup(BMAttrGroup attrGroup){
		this.attrGroup.add(attrGroup);
		attrGroupMap.put("t"+attrGroupMap.size(), attrGroup);
	}
	
	/**
	 * 获取基类属性组
	 * @return
	 */
	public BMAttrGroup getBaseGroup(){
		BMAttrGroup ret = null;
		for(int i = 0 ; i < this.attrGroup.size(); i++){
			for(int j = 0 ; j < this.attrGroup.get(i).getAttrList().size(); j++){
				if(this.attrGroup.get(i).getAttrList().get(j).getAttrId().equalsIgnoreCase("RELATED_BMCLASSTYPE_CUID")){
					ret = this.attrGroup.get(i);
					break;
				}
			}
			if(this.attrGroup.get(i).getIsBasic()){
				ret = this.attrGroup.get(i);
				break;
			}
		}
		
		//TODO 模型正式发布时此段代码要去掉 start
		if(ret == null){
			ret = this.attrGroup.get(0);
		}
		//TODO 模型正式发布时此段代码要去掉 end
		return ret;
	}
	/**
	 * 获取扩展属性组
	 * @return
	 */
	public List<BMAttrGroup> getExtendGroups(){
		List<BMAttrGroup> ret = new ArrayList<BMAttrGroup>();
		for(int i = 0 ; i < this.attrGroup.size(); i++){
			if(!this.attrGroup.get(i).getIsBasic()){
				ret.add(this.attrGroup.get(i));
			}
		}
		return ret;
	}

	public List<BMAttrMeta> getRelatedAttrMetas() {
		return relatedAttrMetas;
	}

	public void setRelatedAttrMetas(List<BMAttrMeta> relatedAttrMetas) {
		this.relatedAttrMetas = relatedAttrMetas;
	}
	public void addRelatedAttrMeta(BMAttrMeta attrMeta){
		this.relatedAttrMetas.add(attrMeta);
	}
    /**
     * 本表关联attrId序号map   key:relatedAttrId  value:1,2,3...
     */
    private Map<String,String> relatedAttrMetaNum = new HashMap<String,String>(); 
    /**
     * 业务模型配置：是，必填
     * 说明：业务类ID
     * 值：不可修改，全局唯一；非拆分的动态类，则和dbClassId一致
     */
    private String bmClassId;

    /**
     * 业务模型配置：是，必填
     * 说明：中文名称
     * 默认值：和数据模型的备注一致
     */
    private String labelCn;
    /**
     * 资源外观图标，大小有客户决定
     */
    private String iconPath;
    /**
     * 资源外观图标，大小固定为24*24
     */
    private String iconSmallPath;

    /**
     * 业务模型配置：是
     * 说明：业务类型
     * 值：枚举，传输：1，话务：2，IP：3，其他：0
     * 默认值：其他
     */
    private Integer bussinessType = 0;

    /**
     * 业务模型配置：是，必填
     * 说明：本类单一显示名称属性
     * 默认值：LABEL_CN
     */
    private String labelAttrId = "LABEL_CN";

    /**
     * 业务模型配置：是
     * 说明：本类显示名称由多个属性构成名称时使用的分隔符
     * 默认值：-
     */
    private String labelJoinChar = "-";

    /**
     * 业务模型配置：是
     * 说明：本类显示名称构成的属性列表，在多个属性构成名称时使用
     * 值：attrId
     */
    private List<String> constructLabelAttrIds = new ArrayList();

//    /**
//     * 业务模型配置：是
//     * 说明：本类和直接上级类关联的AttrIds
//     * 值：String[]
//     */
//    @Deprecated
//    private List<String> parentClassAttrIds = new ArrayList();


    /**
     * 业务模型配置：是
     * 说明：基表类唯一的UID属性
     */
    private String cuidAttrId = "CUID";

    /**
     * 业务模型配置：是
     * 说明：对象选择时的xtype
     */
    private String objSelectXType = "drm-complex-select";

    /**
     * 业务模型配置：是，必填
     * 说明：唯一性描述属性（非CUID的描述属性）
     * 值：key：定义的名称，value：attrId[]
     * 默认：key：default，value：LABEL_CN属性
     */
    private Map<String, String[]> uniqueAttrIds = new HashMap();

    /**
     * 业务模型配置：是
     * 说明：动态类的关联的统计类
     * 值：key : bmClassId（关联类的bmClassId）, value: attrId（关联类和本类关联的attrId）
     */
    private List<BMStatClass> statClasses = new ArrayList<BMStatClass>();

    /**
     * 业务模型配置：是，actionId是枚举下拉选择
     * 说明：动态类的支持的关联操作
     * 值：actionId?para1=xxx,para2=xxx
     */
    private List<String> relatedActionUrls;

    /**
     * 业务模型配置：是，必填
     * 说明：动态类包含的全部属性列表
     * 值：key：attrId，value：BMAttrMeta
     */
    private Map<String, BMAttrMeta> attrMetas = new LinkedHashMap<String, BMAttrMeta>(); // 全部属性

    /**
     * 业务模型配置：是
     * 备注
     */
    private String remark;

    /**
     * 业务模型配置：是
     * 是否显示增加按钮
     */
    private Boolean isShowAddBtn = true;

    /**
     * 业务模型配置：是
     * 是否显示修改按钮
     */
    private Boolean isShowEditBtn = true;

    /**
     * 业务模型配置：是
     * 是否显示删除按钮
     */
    private Boolean isShowDeleteBtn = true;
    /**
     * 业务模型配置：是
     * 是否显示导出按钮
     */
    private Boolean isShowExportBtn = true;
    /**
     * 业务模型配置：是
     * 是否显示导入按钮
     */
    private Boolean isShowImportBtn = true;

//    /*
//         本类中所有有上下级关系的属性对应根属性ID（Map<属性ID，根属性ID>）
//     */
//    @Deprecated
//    private Map<String, String> allRootAttrMap = new HashMap<String, String>();

    /**
     * 是否是虚类
     */
    private Boolean isVirtual;
    /**
     * 主键列(主属性集主键列,如果没有,默认为第一个属性集的主键列)
     */
    private String pkCol;
    /**
     * 类型关系描述
     */
    private List<BMRelationClass> relationClassList = new ArrayList<BMRelationClass>();
    
    /**
     * 所属大类
     */
    private String relatedRes;


    public String getBmClassId() {
        return bmClassId;
    }

    public Integer getBussinessType() {
        return bussinessType;
    }

    public String getLabelCn() {
        return labelCn != null ? labelCn : bmClassId;
    }

    public Map<String, String[]> getUniqueAttrIds() {
        return uniqueAttrIds;
    }

    public String getLabelJoinChar() {
        return labelJoinChar;
    }

    public List<String> getConstructLabelAttrIds() {
        return constructLabelAttrIds;
    }

    public String getLabelAttrId() {
        String _labelAttrId = labelAttrId;
        if (constructLabelAttrIds != null && constructLabelAttrIds.size() >= 1) {
        	if(constructLabelAttrIds.contains("LABEL_CN")){
        		_labelAttrId = "LABEL_CN";
        	}else{
        		_labelAttrId = constructLabelAttrIds.get(0);
        	}
        }
        return _labelAttrId;
    }

    public String _getLabelAttrId() {
        String _labelAttrId = labelAttrId;
        if (constructLabelAttrIds != null && constructLabelAttrIds.size() > 1) {
            for(int i=0;i<constructLabelAttrIds.size();i++){
                if(i==0){
                    _labelAttrId = constructLabelAttrIds.get(i);
                }else{
                     _labelAttrId = _labelAttrId + this.labelJoinChar + constructLabelAttrIds.get(i);
                }

            }
        } else if (constructLabelAttrIds != null && constructLabelAttrIds.size() == 1) {
            _labelAttrId = constructLabelAttrIds.get(0);
        }
        return _labelAttrId;
    }


    public String getCuidAttrId() {
        return cuidAttrId;
    }

    public String getObjSelectXType() {
        return objSelectXType;
    }

    public List<String> getRelatedActionUrls() {
        return relatedActionUrls;
    }

    public String getRemark() {
        return remark;
    }

    public BMAttrMeta getAttrMeta(String attrId) {
        return this.attrMetas.get(attrId);
    }

    public void setBmClassId(String bmClassId) {
        this.bmClassId = bmClassId;
    }

    public void setLabelCn(String labelCn) {
        this.labelCn = labelCn;
    }

    public void setBussinessType(Integer bussinessType) {
        this.bussinessType = bussinessType;
    }

    public void setLabelJoinChar(String labelJoinChar) {
        this.labelJoinChar = labelJoinChar;
    }

    public void setConstructLabelAttrIds(List constructLabelAttrIds) {
        this.constructLabelAttrIds = constructLabelAttrIds;
    }

    public void setLabelAttrId(String labelAttrId) {
        this.labelAttrId = labelAttrId;
    }

    public void setCuidAttrId(String cuidAttrId) {
        this.cuidAttrId = cuidAttrId;
    }

    public void setObjSelectXType(String objSelectXType) {
        this.objSelectXType = objSelectXType;
    }

    public void setRelatedActionUrls(List<String> relatedActionUrls) {
        this.relatedActionUrls = relatedActionUrls;
    }
//
//    public void setParentClassAttrIds(List parentClassAttrIds) {
//        this.parentClassAttrIds = parentClassAttrIds;
//    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setUniqueAttrIds(Map<String, String[]> uniqueAttrIds) {
        this.uniqueAttrIds = uniqueAttrIds;
    }

    public void setIsShowAddBtn(Boolean isShowAddBtn) {
        this.isShowAddBtn = isShowAddBtn;
    }

    public void setIsShowDeleteBtn(Boolean isShowDeleteBtn) {
        this.isShowDeleteBtn = isShowDeleteBtn;
    }

    public void setIsShowEditBtn(Boolean isShowEditBtn) {
        this.isShowEditBtn = isShowEditBtn;
    }

    public void addAttrMeta(BMAttrMeta attrMeta) {
        this.attrMetas.put(attrMeta.getAttrId(), attrMeta);
    }

    public void addUniqueAttrIds(String uniqueName, String[] attrIds) {
        this.uniqueAttrIds.put(uniqueName, attrIds);
    }

    public void addLabelAttrId(String attrId) {
        this.constructLabelAttrIds.add(attrId);
    }

    private List<String> allAttrIds = new ArrayList<String>();
    
    public List<String> getAllAttrIds() {
        List<String> enumNotNullAttrIds = new ArrayList();
        List<String> relationNotNullAttrIds = new ArrayList();
        List<String> enumNullAttrIds = new ArrayList();
        List<String> relationNullAttrIds = new ArrayList();
        List<String> systemAttrIds = new ArrayList();
        List<String> miscAttrIds = new ArrayList();

        for (BMAttrMeta attrMeta : this.attrMetas.values()) {
            if (constructLabelAttrIds.contains(attrMeta.getAttrId())) {
                continue;
            } else if (attrMeta.getIsRelation() && attrMeta.getIsNotNull()) {
                relationNotNullAttrIds.add(attrMeta.getAttrId());
            } else if (attrMeta.getIsEnumAttr() && attrMeta.getIsNotNull()) {
                enumNotNullAttrIds.add(attrMeta.getAttrId());
            } else if (attrMeta.getIsRelation() && !attrMeta.getIsNotNull()) {
                relationNullAttrIds.add(attrMeta.getAttrId());
            } else if (attrMeta.getIsEnumAttr() && !attrMeta.getIsNotNull()) {
                enumNullAttrIds.add(attrMeta.getAttrId());
            } else if (attrMeta.getIsSystem()) {
                systemAttrIds.add(attrMeta.getAttrId());
            } else {
                miscAttrIds.add(attrMeta.getAttrId());
            }
        }

        allAttrIds = new ArrayList();
        allAttrIds.addAll(constructLabelAttrIds);
        allAttrIds.addAll(relationNotNullAttrIds);
        allAttrIds.addAll(enumNotNullAttrIds);
        allAttrIds.addAll(relationNullAttrIds);
        allAttrIds.addAll(enumNullAttrIds);
        allAttrIds.addAll(miscAttrIds);
        allAttrIds.addAll(systemAttrIds);
        return allAttrIds;
    }

    public Map<String, String> getSpaceAttrs() {
        Map<String, String> spaceAttrIds = new HashMap();
        Iterator<BMAttrMeta> it = attrMetas.values().iterator();
        while (it.hasNext()) {
            BMAttrMeta attrMeta = it.next();
            if (attrMeta.getIsRelation()) {
                BMAttrMeta relatedAttrMeta = attrMeta.getRelatedMeta();
                String relatedBmClassId = relatedAttrMeta.getBmClassId();
                String relatedAttrId = relatedAttrMeta.getAttrId();
                if ("DISTRICT".equals(relatedBmClassId) && "CUID".equals(relatedAttrId)) {
                    spaceAttrIds.put("DISTRICT", attrMeta.getAttrId());
                } else if ("SITE".equals(relatedBmClassId) && "CUID".equals(relatedAttrId)) {
                    spaceAttrIds.put("SITE", attrMeta.getAttrId());
                } else if ("ROOM".equals(relatedBmClassId) && "CUID".equals(relatedAttrId)) {
                    spaceAttrIds.put("ROOM", attrMeta.getAttrId());
                }
            }
        }
        return spaceAttrIds;
    }




    public List<String> getSystemAttrIds() {
        List<String> systemAttrIds = new ArrayList();
        for (BMAttrMeta attrMeta : this.attrMetas.values()) {
            if (attrMeta.getIsSystem()) {
                systemAttrIds.add(attrMeta.getAttrId());
            }
        }
        return systemAttrIds;
    }

    @Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public int compareTo(Object obj) {
        int compare = 0;
        boolean isAscend = true;
        Comparable source = this.labelCn;
        Comparable target = ((BMClassMeta) obj).getLabelCn();
        if (source != null && target != null) {
            compare = isAscend ? source.compareTo(target) : target.compareTo(source);
        } else if (source != null) {
            compare = isAscend ? 1 : -1;
        } else if (target != null) {
            compare = isAscend ? -1 : 1;
        }
        return compare;
    }

    public Boolean getIsShowEditBtn() {
        return isShowEditBtn;
    }

    public Boolean getIsShowDeleteBtn() {
        return isShowDeleteBtn;
    }

    public Boolean getIsShowAddBtn() {
        return isShowAddBtn;
    }

	public Boolean getIsShowExportBtn() {
		return isShowExportBtn;
	}

	public void setIsShowExportBtn(Boolean isShowExportBtn) {
		this.isShowExportBtn = isShowExportBtn;
	}

	public Boolean getIsShowImportBtn() {
		return isShowImportBtn;
	}

	public void setIsShowImportBtn(Boolean isShowImportBtn) {
		this.isShowImportBtn = isShowImportBtn;
	}

	public Boolean getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getPkCol() {
		return pkCol;
	}

	public void setPkCol(String pkCol) {
		this.pkCol = pkCol;
	}
	public BMAttrGroup getAttrGroupByLabelAttr(String labelAttr){
		return new BMAttrGroup();
	}

	public Map<String, String> getRelatedAttrMetaNum() {
		return relatedAttrMetaNum;
	}

	public void setRelatedAttrMetaNum(Map<String, String> relatedAttrMetaNum) {
		this.relatedAttrMetaNum = relatedAttrMetaNum;
	}

	public List<BMStatClass> getStatClasses() {
		return statClasses;
	}

	public void setStatClasses(List<BMStatClass> statClasses) {
		this.statClasses = statClasses;
	}
	/**
	 * 获取可统计对象集合
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Sep 1, 2009 5:22:25 PM
	 * @return
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<BMStatClass> getStatableClasses(){
		List<BMStatClass> ret = new ArrayList<BMStatClass>();
		if(this.statClasses!=null){
			for(int i = 0 ; i < this.statClasses.size(); i++){
				if("1".equals(this.statClasses.get(i).getStatType())){
					ret.add(this.statClasses.get(i));
				}
			}
		}
		return ret;
	}
	/**
	 * 获取级联对象集合
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Sep 1, 2009 5:22:49 PM
	 * @return
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<BMStatClass> getCascadeClasses(){
		List<BMStatClass> ret = new ArrayList<BMStatClass>();
		if(this.statClasses!=null){
			for(int i = 0 ; i < this.statClasses.size(); i++){
				if("1".equals(this.statClasses.get(i).getCascadeType())){
					ret.add(this.statClasses.get(i));
				}
			}
		}
		return ret;
	}
	/**
	 * 获取约束对象集合
	 * @Company BOCO
	 * @Author  王晓雨
	 * @Date    Sep 1, 2009 5:23:21 PM
	 * @return
	 * @Copyright Copyright (c) 2009
	 * @Version 3.0
	 */
	public List<BMStatClass> getConstraintClasses(){
		List<BMStatClass> ret = new ArrayList<BMStatClass>();
		if(this.statClasses!=null){
			for(int i = 0 ; i < this.statClasses.size(); i++){
				if("2".equals(this.statClasses.get(i).getCascadeType())){
					ret.add(this.statClasses.get(i));
				}
			}
		}
		return ret;
	}

	public List<BMRelationClass> getRelationClassList() {
		return relationClassList;
	}
	public void setRelationClassList(List<BMRelationClass> relationClassList) {
		this.relationClassList = relationClassList;
	}
	public void addRelationClass(BMRelationClass relationClass){
		this.relationClassList.add(relationClass);
	}

	public String getRelatedRes() {
		return relatedRes;
	}

	public void setRelatedRes(String relatedRes) {
		this.relatedRes = relatedRes;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconSmallPath() {
		return iconSmallPath;
	}

	public void setIconSmallPath(String iconSmallPath) {
		this.iconSmallPath = iconSmallPath;
	}
	public List<String> getAllNotNullAttrs(){
		List<String> list = new ArrayList<String>();
		for(BMAttrMeta attrMeta : this.attrMetas.values()){
			if(attrMeta.getIsNotNull()){
				list.add(attrMeta.getAttrId());
			}
		}
		return list;
	}
	public void setAttrMetas(Map<String, BMAttrMeta> attrMetas){
		this.attrMetas=attrMetas;
		
	}

	public Map<String, BMAttrMeta> getAttrMetas() {
		return attrMetas;
	}

	public Map<String, BMAttrGroup> getAttrGroupMap() {
		return attrGroupMap;
	}

	public void setAttrGroupMap(Map<String, BMAttrGroup> attrGroupMap) {
		this.attrGroupMap = attrGroupMap;
	}
	
	/**
	 * 当前模型的类路径
	 */
	private Class bmClass;
	/**
	 * 当前模型的类名称，根据bmclassId转换得到
	 */
	private String className;
	
	/**
	 * 当前类的主键属性
	 */
	private BMAttrMeta idAttrMeta;
	
	public Class getBmClass() {
		return bmClass;
	}

	public void setBmClass(Class bmClass) {
		this.bmClass = bmClass;
	}

	public String getClassName() {
		this.className = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(this.getBmClassId()));
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	public BMAttrMeta getIdAttrMeta() {
		idAttrMeta = new BMAttrMeta();
		if("LOCATION".equalsIgnoreCase(this.getRelatedRes())&&"CRAMER".equalsIgnoreCase(this.getFlag())){
			idAttrMeta.setAttrId("LOCATIONID");
			idAttrMeta.setAttrClassType(int.class);
			idAttrMeta.setLabelCn("LOCATIONID");
		}else{
			idAttrMeta.setAttrId("CUID");
			idAttrMeta.setAttrClassType(String.class);
			idAttrMeta.setLabelCn("CUID");
			
		}
		idAttrMeta.setBmClassId(this.getBmClassId());
		idAttrMeta.setClassMeta(this);
		idAttrMeta.setIsSystem(true);
		return idAttrMeta;
	}
	
	/**
	 * 返回表的别名及对应名称属性
	 */
	public Map<String,String> getLabelAttrIdsMap(){
		Map<String,String> labelMap = new HashMap<String,String>();
		Map<String,BMAttrGroup> groupKeyMap = this.getAttrGroupMap();
		for(String key:groupKeyMap.keySet()){
			BMAttrGroup group = groupKeyMap.get(key);
			for(BMAttrMeta attr:group.getAttrList()){
				if(this.getConstructLabelAttrIds().contains(attr.getAttrId())){
					labelMap.put(key, attr.getAttrId());
				}
			}
		}
		return labelMap;
	}
	
	/**
	 * 返回表的别名及对应名称属性
	 */
	public Map<String,BMAttrMeta> getLabelAttrMetasMap(){
		Map<String,BMAttrMeta> labelMap = new HashMap<String,BMAttrMeta>();
		Map<String,BMAttrGroup> groupKeyMap = this.getAttrGroupMap();
		for(String key:groupKeyMap.keySet()){
			BMAttrGroup group = groupKeyMap.get(key);
			for(BMAttrMeta attr:group.getAttrList()){
				if(this.getConstructLabelAttrIds().contains(attr.getAttrId())){
					labelMap.put(key, attr);
				}
			}
		}
		return labelMap;
	}
	
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 如果表中没有这个字段，则返回空。关联字段的名称
	 * @return
	 */
	public Map getRelatedBmClassIdQuery(){
		Map map = new HashMap();
		Map<String,BMAttrGroup> groupKeyMap = this.getAttrGroupMap();
		if("CRAMER".equalsIgnoreCase(this.getFlag())&&"LOCATION".equalsIgnoreCase(this.getRelatedRes())){
			for(String key:groupKeyMap.keySet()){
				BMAttrGroup group = groupKeyMap.get(key);
				for(BMAttrMeta attr:group.getAttrList()){
					if("LOCATION2LOCATIONTYPE".equalsIgnoreCase(attr.getAttrId())){
						map.put("colName", "m."+key+"_LOCATION2LOCATIONTYPE");
						map.put("sql", "in (select l.LOCATIONTYPEID from locationtype l where l.name = #BM_CLASS_ID#)");
						return map;
					}
				}
			}
			return null;
		}else{
			for(String key:groupKeyMap.keySet()){
				BMAttrGroup group = groupKeyMap.get(key);
				for(BMAttrMeta attr:group.getAttrList()){
					if("RELATED_BM_CLASS_ID".equalsIgnoreCase(attr.getAttrId())){
						map.put("colName", "m."+key+"_RELATED_BM_CLASS_ID");
						map.put("sql", " = #BM_CLASS_ID# ");
						map.put("sqlsub", "in (select p.bm_classid from t_md_abstract_type p start with p.bm_classid=#BM_CLASS_ID# connect by prior  p.cuid =p.parent_type_cuid )");
						return map;
					}
				}
			}
			return null;
		}
	}

}
