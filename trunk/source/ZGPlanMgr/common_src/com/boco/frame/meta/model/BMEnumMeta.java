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
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class BMEnumMeta<T>  implements Serializable{
    private String enumId;
    private String enumLabelCn;
    private List<DrmEnumValue> enums = new ArrayList();
    private int defaultEnumIndex = 0;
    private static final long serialVersionUID = 1L;
    private String type;
    private String enumCode;
    public BMEnumMeta() {
    }

    public BMEnumMeta(String enumId, String enumLabelCn){
        this.enumId = enumId;
        this.enumLabelCn = enumLabelCn;
    }

    public String getEnumId() {
        return enumId;
    }

    public List<DrmEnumValue> getEnums() {
        return enums;
    }

    public String getEnumLabelCn() {
        return enumLabelCn;
    }

    public int getDefaultEnumIndex() {
        return defaultEnumIndex;
    }

    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }

    public void setEnums(List<DrmEnumValue> enums) {
        this.enums = enums;
    }

    public void setEnumLabelCn(String enumLabelCn) {
        this.enumLabelCn = enumLabelCn;
    }

    public void setDefaultEnumIndex(int defaultEnumIndex) {
        this.defaultEnumIndex = defaultEnumIndex;
    }

    public void addEnum(DrmEnumValue drmEnum){
        enums.add(drmEnum);
    }

    public T getEnumValue(String enumName){
        Object enumValue = null;
        if(enumName == null || enumName.trim().length() == 0){
            throw new RuntimeException("");
        }

        for(DrmEnumValue drmEnum : enums){
            if(enumName.equals(drmEnum.getEnumName())){
                enumValue = drmEnum.getEnumValue();
                break;
            }
        }

        if(enumValue == null){
            throw new RuntimeException("enumId=" + enumId + ",enumName=" + enumName + "");
        }
        return (T)enumValue;
    }

    public String getEnumName(T enumValue){
        String enumName = null;
        if(enumValue == null){
            throw new RuntimeException("");
        }

        for(DrmEnumValue drmEnum : enums){
            if(drmEnum.isEnumValueEqual(enumValue)){
                enumName = drmEnum.getEnumName();
                break;
            }
        }

        if(enumName == null){
            enumName = "无效枚举";
        }
        return enumName;
   }
	@Override
	public String toString(){
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnumCode() {
		return enumCode;
	}

	public void setEnumCode(String enumCode) {
		this.enumCode = enumCode;
	}
	
}
