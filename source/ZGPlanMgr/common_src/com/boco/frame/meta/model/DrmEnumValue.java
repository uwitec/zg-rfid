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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DrmEnumValue<T> implements Serializable{
    private String enumName;
    private T enumValue;
    private T color;
    public T getColor() {
		return color;
	}

	public void setColor(T color) {
		this.color = color;
	}

	public DrmEnumValue() {
    }

    public DrmEnumValue(String enumName, T enumValue){
        this.enumName = enumName;
        this.enumValue = enumValue;
    }

    public String getEnumName() {
        return enumName;
    }

    public T getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(T enumValue) {
        if(enumValue == null){
            throw new RuntimeException("");
        }
        this.enumValue = enumValue;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public boolean isEnumValueEqual(Object _enumValue){
        if(_enumValue == null) return false;
        String evalue = this.enumValue.toString();
        String _evalue = _enumValue.toString();
       return _evalue.equals(evalue);
    }

    @Override
	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DrmEnumValue other = (DrmEnumValue) obj;
		if (enumName == null) {
			if (other.enumName != null)
				return false;
		} else if (!enumName.equals(other.enumName))
			return false;
		if (enumValue == null) {
			if (other.enumValue != null)
				return false;
		} else if (!enumValue.equals(other.enumValue))
			return false;
		return true;
	}

}
