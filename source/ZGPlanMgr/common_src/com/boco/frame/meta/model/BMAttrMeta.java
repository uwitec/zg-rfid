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

import javacommon.util.StringHelper;


public class BMAttrMeta implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 业务模型配置：是，必填
     * 说明：业务类ID
     * 值：和BMClassMeta的bmClassId一致
     */
    private String bmClassId;

    /**
     * 业务模型配置：是，必填
     * 说明：属性ID
     * 值：和数据模型的属性ID一致
     */
    private String attrId;

    /**
     * 业务模型配置：是，必填
     * 说明：中文名称
     * 值：默认和数据模型的属性备注一致，可在业务模型修改
     */
    private String labelCn; // 属性标签名称

    /**
     * 业务模型配置：是，必填
     * 说明：属性的数据类型
     * 值：ATTR_TYPE_ENUM
     */
    private String attrDbType = "string";
    
    private String javaType = "java.lang.String";

    public static class ATTR_TYPE_ENUM {
        public static String STRING = "string";
        public static String INT = "int";//数字
        public static String FLOAT = "float";//浮点
        public static String ENUM = "enum";
        public static String DATE = "date";
        public static String RELATION = "relation";
        public static String STATUS = "state";
    }
    /**
     * BMClassMeta
     */
    private BMClassMeta classMeta;
    /**
     * 业务模型配置：是
     * 说明：属性默认值
     */
    private String defaultValue;

    /**
     * 业务模型配置：是
     * 说明：枚举的ID，在是枚举属性时配置，和枚举保持一致
     */
    private String enumId;
    /**
     * 业务模型配置：是
     * 说明：枚举的类型（enum，state）
     */
    private String enumType;

    /**
     * 业务模型配置：是
     * 说明：是否非空，根据数据模型得出，业务模型呈现但不可修改
     */
    private Boolean isNotNull = false;

    /**
     * 业务模型配置：是
     * 说明：是否系统属性，例如：OBJECT_ID, CUID, CREATE_TIME, MODIFY_TIME
     *      在任何属性组都必须默认包含系统属性，在属性组中不可选
     */
    private Boolean isSystem = false;
    
    /**
     * 业务模型配置：是
     * 说明：是否允许关联打开，在展示设备所属类型时，表格和DETAIL中需要显示但不可链接打开的字段
     */
    private Boolean isNotLink = false;

    /**
     * 业务模型配置：是
     * 说明：是否只读属性
     */
    private Boolean isReadOnly = false; // 是否只读；

    /**
     * 业务模型配置：是
     * 说明：是否可以批量修改
     */
    private Boolean isBatchModify = false; // 是否可以批量修改

    /**
     * 业务模型配置：是
     * 说明：本属性是关联属性时，关联类的属性
     * 值：BMAttrMeta
     */
    private BMAttrMeta relatedMeta;
    
    /**
     * 如果本字段是关联字段 存储关联类的bmClassId
     */
    private String relatedClassMetaId = null;

    private BMAttrGroup attrGroup;
    
    public BMAttrGroup getAttrGroup() {
		return attrGroup;
	}

    
	public void setAttrGroup(BMAttrGroup attrGroup) {
		this.attrGroup = attrGroup;
	}

    /**
     * 业务模型配置：是
     * 说明：属性的数据源类型
     * 值：枚举，手工：0，采集：1
     * 默认值：手工
     */
    private Integer sourceType = 0;

    /**
     * 业务模型配置：是
     * 说明：属性有效正则表达式
     * 值：正则表达式，例如：[0-9]+
     */
    private String validRegExp;

    /**
     * 业务模型配置：是
     * 说明：正则表达式样例
     */
    private String regExpExample;

    /**
     * 业务模型配置：是
     * 说明：属性计算表达式
     * 值：$attrId代表本类的属性值，例如：$attrId1 > 100 and attrId2 > $V
     */
    private String validCalcExp; // 验证是否有效，关系计算表达式：

    /**
     * 业务模型配置：是
     * 说明：属性显示的xtype，仅仅在属性需要特殊显示时配置，会根据数据类型得出
     */
    private String xtype;

    /**
     * 业务模型配置：是
     * 说明：字符串数据类型时，最大长度, 0是不限制
     */
    private int strAttrMaxLen = 0;

    /**
     * 业务模型配置：否
     * 说明：当前属性的属性值填写规则
     * */
    private String fillRule = "";
    /**
     * 联动调用BO名称
     */
    private String relatedFilterName;
    
    public static class ATTR_XTYPE_FIELD {
        public static String TEXT = "text-field";
        public static String ENUM = "enum-field";
        public static String RELATION = "relation-field";
        public static String INT = "int-field";
        public static String FLOAT = "float-field";
//        public static String BOOL = "bool-field";
        public static String DATE = "date-field";
//        public static String TIME = "time-field";
        public static String DATETIME = "datetime-field";
//        public static String MULTI_ENUM = "multi-enum";
    }


    /**
     * 业务模型配置：是
     * 备注
     */
    private String remark;


    public String getAttrId() {
        return attrId;
    }

    public String getAttrDbType() {
        return attrDbType;
    }

    public Class getAttrClassType() {
        Class attrClassType = null;
        if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.INT)) {
            attrClassType = Long.class;
        } else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.FLOAT)) {
            attrClassType = Float.class;
        } else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.STRING)) {
            attrClassType = String.class;
        } else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.DATE)) {
            attrClassType = java.util.Date.class;
        } else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.ENUM)) {
            attrClassType = String.class;
        } else {
            attrClassType = Object.class;
        }

        return attrClassType;
    }
    
    public String getJavaType(){
    	return this.getAttrClassType().getName();
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getEnumId() {
        return enumId;
    }

    public Boolean getIsNotNull() {
        return isNotNull;
    }

    public Boolean getIsReadOnly() {
        if(this.getSourceType()==1){
            return true;
        }else{
            return this.isReadOnly;
        }
    }

    public Boolean getIsRelation() {
        return this.relatedMeta != null;
    }

    public Boolean getIsEnumAttr() {
        return this.enumId != null;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public String getLabelCn() {
        return labelCn != null ? labelCn : attrId;
    }

    public BMAttrMeta getRelatedMeta() {
        return this.relatedMeta;
    }
    public String getRelatedAttrId(String dbClassId) {
        if(this.relatedMeta != null && dbClassId.equals(this.relatedMeta.getAttrGroup().getTableName())){
        	return this.relatedMeta.getAttrId();
        }else{
        	return null;
        }
    }
    public String getXtype() {
        String _xtype = xtype;
        if (_xtype == null) {
            if (this.getIsEnumAttr()) {
                _xtype = ATTR_XTYPE_FIELD.ENUM;
            } else if (this.getIsRelation()) {
                _xtype = ATTR_XTYPE_FIELD.RELATION;
            } else {
                if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.INT)) {
                    _xtype = ATTR_XTYPE_FIELD.INT;
                } else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.FLOAT)) {
                    _xtype = ATTR_XTYPE_FIELD.FLOAT;
                }  else if (this.attrDbType.equalsIgnoreCase(ATTR_TYPE_ENUM.DATE)) {
                    _xtype = ATTR_XTYPE_FIELD.DATETIME;
                } else {
                    _xtype = ATTR_XTYPE_FIELD.TEXT;
                }
            }
        }
        return _xtype;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public String getValidCalcExp() {
        return validCalcExp != null && validCalcExp.trim().length() == 0 ? null : validCalcExp;
    }

    public String getValidRegExp() {
        return this.validRegExp != null && validRegExp.trim().length() == 0 ? null : validRegExp;
    }

    public String getBmClassId() {
        return bmClassId;
    }

    public Boolean getIsBatchModify() {
        return isBatchModify;
    }

    public String getRegExpExample() {
        return regExpExample;
    }

    public int getStrAttrMaxLen() {
        return strAttrMaxLen;
    }

    public String getRemark() {
        return remark;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public void setAttrDbType(String attrDbType) {
        this.attrDbType = attrDbType;
    }

    public void setAttrClassType(Class attrClass) {
        if (attrClass == int.class) {
            this.attrDbType = ATTR_TYPE_ENUM.INT;
        } else if (attrClass == double.class ||attrClass == long.class||attrClass == float.class) {
            this.attrDbType = ATTR_TYPE_ENUM.FLOAT;
        } else if (attrClass == String.class) {
            this.attrDbType = ATTR_TYPE_ENUM.STRING;
        } else if (attrClass == java.sql.Date.class) {
            this.attrDbType = ATTR_TYPE_ENUM.DATE;
        } 
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }

    public void setIsNotNull(Boolean isNotNull) {
        this.isNotNull = isNotNull;
    }

    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public void setLabelCn(String labelCn) {
        this.labelCn = labelCn;
    }

    public void setXtype(String xtype) {
        this.xtype = xtype;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }


    public void setValidCalcExp(String validCalcExp) {
        this.validCalcExp = validCalcExp;
    }

    public void setValidRegExp(String validRegExp) {
        this.validRegExp = validRegExp;
    }

    public void setBmClassId(String bmClassId) {
        this.bmClassId = bmClassId;
    }

    public void setIsBatchModify(Boolean isBatchModify) {
        this.isBatchModify = isBatchModify;
    }

    public void setRegExpExample(String regExpExample) {
        this.regExpExample = regExpExample;
    }

    public void setStrAttrMaxLen(int strAttrMaxLen) {
        this.strAttrMaxLen = strAttrMaxLen;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRelatedMeta(BMAttrMeta relatedMeta) {
        this.relatedMeta = relatedMeta;
    }
    
    /**
     * 关联类的命名
     */
    private String relatedIndex;

    public String getFillRule(){
    	return fillRule;
    }
    
    public void setFillRule(String fillRule){
    	this.fillRule = fillRule;
    }
    
	public BMClassMeta getClassMeta() {
		return classMeta;
	}

	public void setClassMeta(BMClassMeta classMeta) {
		this.classMeta = classMeta;
	}

	public String getRelatedFilterName() {
		return relatedFilterName;
	}

	public void setRelatedFilterName(String relatedFilterName) {
		this.relatedFilterName = relatedFilterName;
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

    public String getDbTypeName() {
        String dbType = this.getAttrDbType();
        String sReturn = "";
        if (dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.STRING)) {
            sReturn = "字符串";
        } else if (dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.INT)) {
            sReturn = "整型";
        } else if (dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.FLOAT)) {
            sReturn = "浮点型";
        } else if (dbType.equalsIgnoreCase(ATTR_TYPE_ENUM.DATE)) {
            sReturn = "日期型";
        } else {
            sReturn = "未知类型";
        }
        return sReturn;
    }

	public Boolean getIsNotLink() {
		return isNotLink;
	}

	public void setIsNotLink(Boolean isNotLink) {
		this.isNotLink = isNotLink;
	}
	
	public String getRelatedClassMetaId() {
		return relatedClassMetaId;
	}

	public void setRelatedClassMetaId(String relatedClassMetaId) {
		this.relatedClassMetaId = relatedClassMetaId;
	}
	
	public String getConstantName() {
		return StringHelper.toUnderscoreName(this.getAttrId()).toUpperCase();
	}


	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	
	public boolean getIsStringColumn() {
		return this.getAttrDbType().equalsIgnoreCase(ATTR_TYPE_ENUM.STRING);
	}
	
	public boolean getIsDateTimeColumn() {
		return this.getAttrDbType().equalsIgnoreCase(ATTR_TYPE_ENUM.DATE);
	}
	
	public boolean isFk() {
		return this.getIsRelation();
	}
	
	public boolean isPk() {
		return this.getClassMeta().getIdAttrMeta().getAttrId().equalsIgnoreCase(this.getAttrId());
	}
	
	public boolean isEnum() {
        return this.enumId != null;
    }
	
	public String getColumnName() {
		return StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(getAttrId()));
	}
	
	public String getColumnNameFirstLower() {
		return StringHelper.uncapitalize(getColumnName());
	}
	
	public String getColumnNameLower() {
		return getColumnNameFirstLower();
	}
	
	public String getColumnNameLowerCase() {
		return getColumnName().toLowerCase();
	}


	public String getRelatedIndex() {
		return relatedIndex;
	}


	public void setRelatedIndex(String relatedIndex) {
		this.relatedIndex = relatedIndex;
	}
	
	public String getValidateString() {
		String result = "";
		if(getIsNotNull()) {
			result = "required";
		}
		return result;
	}
}
