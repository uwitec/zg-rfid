/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.meta.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.frame.meta.base.model.*;
import com.boco.frame.meta.base.dao.*;
import com.boco.frame.meta.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class TmdDeviceVendor extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "TmdDeviceVendor";
	public static final String ALIAS_LABEL_CN = "labelCn";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_ZIPCODE = "zipcode";
	public static final String ALIAS_CONTACTOR = "contactor";
	public static final String ALIAS_FACISIMILE = "facisimile";
	public static final String ALIAS_ABBREVIATION = "abbreviation";
	public static final String ALIAS_TELEPHONE = "telephone";
	public static final String ALIAS_HOME_PAGE = "homePage";
	public static final String ALIAS_EMAIL_ADDRESS = "emailAddress";
	public static final String ALIAS_CONTACT_ADDRESS = "contactAddress";
	public static final String ALIAS_IMAGE_PATH = "imagePath";
	public static final String ALIAS_FLAG = "flag";
	public static final String ALIAS_PK_CUID = "pkCuid";
	public static final String ALIAS_CUID = "cuid";
	public static final String ALIAS_COL_CUID = "colCuid";
	
	//date formats
	

	//columns START
	private java.lang.String labelCn;
	private java.lang.String remark;
	private java.lang.String zipcode;
	private java.lang.String contactor;
	private java.lang.String facisimile;
	private java.lang.String abbreviation;
	private java.lang.String telephone;
	private java.lang.String homePage;
	private java.lang.String emailAddress;
	private java.lang.String contactAddress;
	private java.lang.String imagePath;
	private java.lang.String flag;
	private java.lang.String pkCuid;
	private java.lang.String cuid;
	private java.lang.String colCuid;
	//columns END


	public TmdDeviceVendor(){
	}

	public TmdDeviceVendor(
		java.lang.String pkCuid
	){
		this.pkCuid = pkCuid;
	}



	public void setPkCuid(java.lang.String value) {
		this.pkCuid = value;
	}
	
	public java.lang.String getPkCuid() {
		return this.pkCuid;
	}


	
	public java.lang.String getLabelCn() {
		return this.labelCn;
	}
	
	public void setLabelCn(java.lang.String value) {
		this.labelCn = value;
	}

	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	
	public java.lang.String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}

	
	public java.lang.String getContactor() {
		return this.contactor;
	}
	
	public void setContactor(java.lang.String value) {
		this.contactor = value;
	}

	
	public java.lang.String getFacisimile() {
		return this.facisimile;
	}
	
	public void setFacisimile(java.lang.String value) {
		this.facisimile = value;
	}

	
	public java.lang.String getAbbreviation() {
		return this.abbreviation;
	}
	
	public void setAbbreviation(java.lang.String value) {
		this.abbreviation = value;
	}

	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}

	
	public java.lang.String getHomePage() {
		return this.homePage;
	}
	
	public void setHomePage(java.lang.String value) {
		this.homePage = value;
	}

	
	public java.lang.String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(java.lang.String value) {
		this.emailAddress = value;
	}

	
	public java.lang.String getContactAddress() {
		return this.contactAddress;
	}
	
	public void setContactAddress(java.lang.String value) {
		this.contactAddress = value;
	}

	
	public java.lang.String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(java.lang.String value) {
		this.imagePath = value;
	}

	
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}

	
	
	public java.lang.String getCuid() {
		return this.cuid;
	}
	
	public void setCuid(java.lang.String value) {
		this.cuid = value;
	}

	
	public java.lang.String getColCuid() {
		return this.colCuid;
	}
	
	public void setColCuid(java.lang.String value) {
		this.colCuid = value;
	}


	public String toString() {
		return new ToStringBuilder(this)
			.append("LabelCn",getLabelCn())
			.append("Remark",getRemark())
			.append("Zipcode",getZipcode())
			.append("Contactor",getContactor())
			.append("Facisimile",getFacisimile())
			.append("Abbreviation",getAbbreviation())
			.append("Telephone",getTelephone())
			.append("HomePage",getHomePage())
			.append("EmailAddress",getEmailAddress())
			.append("ContactAddress",getContactAddress())
			.append("ImagePath",getImagePath())
			.append("Flag",getFlag())
			.append("PkCuid",getPkCuid())
			.append("Cuid",getCuid())
			.append("ColCuid",getColCuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLabelCn())
			.append(getRemark())
			.append(getZipcode())
			.append(getContactor())
			.append(getFacisimile())
			.append(getAbbreviation())
			.append(getTelephone())
			.append(getHomePage())
			.append(getEmailAddress())
			.append(getContactAddress())
			.append(getImagePath())
			.append(getFlag())
			.append(getPkCuid())
			.append(getCuid())
			.append(getColCuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmdDeviceVendor == false) return false;
		if(this == obj) return true;
		TmdDeviceVendor other = (TmdDeviceVendor)obj;
		return new EqualsBuilder()
			.append(getLabelCn(),other.getLabelCn())
			.append(getRemark(),other.getRemark())
			.append(getZipcode(),other.getZipcode())
			.append(getContactor(),other.getContactor())
			.append(getFacisimile(),other.getFacisimile())
			.append(getAbbreviation(),other.getAbbreviation())
			.append(getTelephone(),other.getTelephone())
			.append(getHomePage(),other.getHomePage())
			.append(getEmailAddress(),other.getEmailAddress())
			.append(getContactAddress(),other.getContactAddress())
			.append(getImagePath(),other.getImagePath())
			.append(getFlag(),other.getFlag())
			.append(getPkCuid(),other.getPkCuid())
			.append(getCuid(),other.getCuid())
			.append(getColCuid(),other.getColCuid())
			.isEquals();
	}
}
