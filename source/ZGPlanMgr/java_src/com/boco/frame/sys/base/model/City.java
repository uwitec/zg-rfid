/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.boco.frame.sys.base.model;

import java.util.List;

import javacommon.base.service.IVmModelBo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.org.rapid_framework.util.ApplicationContextHolder;

import com.boco.frame.meta.base.model.TmdEnumevalue;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import javacommon.base.model.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.org.rapid_framework.beanutils.BeanUtils;

import com.boco.frame.sys.base.model.*;
import com.boco.frame.sys.base.dao.*;
import com.boco.frame.sys.base.service.*;

/**
 * @author 李智伟 email:v_lizhiwei@boco.com.cn
 * @version 1.0
 * @since 1.0
 */


public class City extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "City";
	public static final String BM_CLASS_ID = "City";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	public static final String ALIAS_NAME = "NAME";
	public static final String ALIAS_ADDRESS = "ADDRESS";
	public static final String ALIAS_ADDRESS1 = "ADDRESS1";
	public static final String ALIAS_ADDRESS2 = "ADDRESS2";
	public static final String ALIAS_ADDRESS3 = "ADDRESS3";
	public static final String ALIAS_ALIAS1 = "ALIAS1";
	public static final String ALIAS_ALIAS2 = "ALIAS2";
	public static final String ALIAS_CREATEDBY2DIMUSER = "CREATEDBY2DIMUSER";
	public static final String ALIAS_CREATEDDATE = "CREATEDDATE";
	public static final String ALIAS_DESCRIPTION = "DESCRIPTION";
	public static final String ALIAS_FAX = "FAX";
	public static final String ALIAS_FULLNAME = "FULLNAME";
	public static final String ALIAS_ISVISIBLE = "ISVISIBLE";
	public static final String ALIAS_LABEL = "LABEL";
	public static final String ALIAS_LASTMODIFIEDBY2DIMUSER = "LASTMODIFIEDBY2DIMUSER";
	public static final String ALIAS_LASTMODIFIEDDATE = "LASTMODIFIEDDATE";
	public static final String ALIAS_LOCATION2FUNCTIONALSTATUS = "LOCATION2FUNCTIONALSTATUS";
	public static final String ALIAS_LOCATION2LOCATIONTYPE = "LOCATION2LOCATIONTYPE";
	public static final String ALIAS_LOCATION2PARENTLOCATION = "LOCATION2PARENTLOCATION";
	public static final String ALIAS_LOCATION2PROVISIONSTATUS = "LOCATION2PROVISIONSTATUS";
	public static final String ALIAS_LOCATION2RPBUILDTEMPLATE = "LOCATION2RPBUILDTEMPLATE";
	public static final String ALIAS_LOCATIONID = "LOCATIONID";
	public static final String ALIAS_MARKEDFORDELETE = "MARKEDFORDELETE";
	public static final String ALIAS_NOTES = "NOTES";
	public static final String ALIAS_OBJECTID = "OBJECTID";
	public static final String ALIAS_PHYSICALX = "PHYSICALX";
	public static final String ALIAS_PHYSICALY = "PHYSICALY";
	public static final String ALIAS_PHYSICALZ = "PHYSICALZ";
	public static final String ALIAS_PROVINCE = "PROVINCE";
	public static final String ALIAS_RELATIVENAME = "RELATIVENAME";
	public static final String ALIAS_RESPONSIBLE = "RESPONSIBLE";
	public static final String ALIAS_SUBSTATUS = "SUBSTATUS";
	public static final String ALIAS_SUBTYPE = "SUBTYPE";
	public static final String ALIAS_TELEPHONE = "TELEPHONE";
	public static final String ALIAS_TOWNCITY = "TOWNCITY";
	public static final String ALIAS_ZIP = "ZIP";
	//date formats
	public static final String FORMAT_CREATEDDATE = DATE_FORMAT;
	public static final String FORMAT_LASTMODIFIEDDATE = DATE_FORMAT;
	
	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		return labelValue;
	}
	
	//columns START
	private java.lang.String name;
	private java.lang.String address;
	private java.lang.String address1;
	private java.lang.String address2;
	private java.lang.String address3;
	private java.lang.String alias1;
	private java.lang.String alias2;
	private java.lang.Long createdby2dimuser;
	private java.util.Date createddate_start;
	private java.util.Date createddate_end;
	private java.util.Date createddate;
	private java.lang.String description;
	private java.lang.String fax;
	private java.lang.String fullname;
	private java.lang.Long isvisible;
	private java.lang.Long label;
	private java.lang.Long lastmodifiedby2dimuser;
	private java.util.Date lastmodifieddate_start;
	private java.util.Date lastmodifieddate_end;
	private java.util.Date lastmodifieddate;
	private java.lang.Long location2functionalstatus;
	private java.lang.Long location2locationtype;
	private java.lang.Long location2parentlocation;
	private java.lang.Long location2provisionstatus;
	private java.lang.Long location2rpbuildtemplate;
	private java.lang.Long locationid;
	private java.lang.Float markedfordelete;
	private java.lang.String notes;
	private java.lang.String objectid;
	private java.lang.String physicalx;
	private java.lang.String physicaly;
	private java.lang.String physicalz;
	private java.lang.String province;
	private java.lang.String relativename;
	private java.lang.String responsible;
	private java.lang.String substatus;
	private java.lang.String subtype;
	private java.lang.String telephone;
	private java.lang.String towncity;
	private java.lang.String zip;
	//columns END
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getAddress1() {
		return this.address1;
	}
	
	public void setAddress1(java.lang.String value) {
		this.address1 = value;
	}
	public java.lang.String getAddress2() {
		return this.address2;
	}
	
	public void setAddress2(java.lang.String value) {
		this.address2 = value;
	}
	public java.lang.String getAddress3() {
		return this.address3;
	}
	
	public void setAddress3(java.lang.String value) {
		this.address3 = value;
	}
	public java.lang.String getAlias1() {
		return this.alias1;
	}
	
	public void setAlias1(java.lang.String value) {
		this.alias1 = value;
	}
	public java.lang.String getAlias2() {
		return this.alias2;
	}
	
	public void setAlias2(java.lang.String value) {
		this.alias2 = value;
	}
	public java.lang.Long getCreatedby2dimuser() {
		return this.createdby2dimuser;
	}
	
	public void setCreatedby2dimuser(java.lang.Long value) {
		this.createdby2dimuser = value;
	}
	public String getCreateddateString() {
		return date2String(getCreateddate(), FORMAT_CREATEDDATE);
	}
	public void setCreateddateString(String value) {
		setCreateddate(string2Date(value, FORMAT_CREATEDDATE,java.util.Date.class));
	}
	public java.util.Date getCreateddate_start() {
		return this.createddate_start;
	}
	
	public void setCreateddate_start(java.util.Date value) {
		this.createddate_start = value;
	}
	
	public java.util.Date getCreateddate_end() {
		return this.createddate_end;
	}
	
	public void setCreateddate_end(java.util.Date value) {
		this.createddate_end = value;
	}
	public java.util.Date getCreateddate() {
		return this.createddate;
	}
	
	public void setCreateddate(java.util.Date value) {
		this.createddate = value;
	}
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	public java.lang.String getFax() {
		return this.fax;
	}
	
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	public java.lang.String getFullname() {
		return this.fullname;
	}
	
	public void setFullname(java.lang.String value) {
		this.fullname = value;
	}
	public java.lang.Long getIsvisible() {
		return this.isvisible;
	}
	
	public void setIsvisible(java.lang.Long value) {
		this.isvisible = value;
	}
	public java.lang.Long getLabel() {
		return this.label;
	}
	
	public void setLabel(java.lang.Long value) {
		this.label = value;
	}
	public java.lang.Long getLastmodifiedby2dimuser() {
		return this.lastmodifiedby2dimuser;
	}
	
	public void setLastmodifiedby2dimuser(java.lang.Long value) {
		this.lastmodifiedby2dimuser = value;
	}
	public String getLastmodifieddateString() {
		return date2String(getLastmodifieddate(), FORMAT_LASTMODIFIEDDATE);
	}
	public void setLastmodifieddateString(String value) {
		setLastmodifieddate(string2Date(value, FORMAT_LASTMODIFIEDDATE,java.util.Date.class));
	}
	public java.util.Date getLastmodifieddate_start() {
		return this.lastmodifieddate_start;
	}
	
	public void setLastmodifieddate_start(java.util.Date value) {
		this.lastmodifieddate_start = value;
	}
	
	public java.util.Date getLastmodifieddate_end() {
		return this.lastmodifieddate_end;
	}
	
	public void setLastmodifieddate_end(java.util.Date value) {
		this.lastmodifieddate_end = value;
	}
	public java.util.Date getLastmodifieddate() {
		return this.lastmodifieddate;
	}
	
	public void setLastmodifieddate(java.util.Date value) {
		this.lastmodifieddate = value;
	}
	public java.lang.Long getLocation2functionalstatus() {
		return this.location2functionalstatus;
	}
	
	public void setLocation2functionalstatus(java.lang.Long value) {
		this.location2functionalstatus = value;
	}
	public java.lang.Long getLocation2locationtype() {
		return this.location2locationtype;
	}
	
	public void setLocation2locationtype(java.lang.Long value) {
		this.location2locationtype = value;
	}
	public java.lang.Long getLocation2parentlocation() {
		return this.location2parentlocation;
	}
	
	public void setLocation2parentlocation(java.lang.Long value) {
		this.location2parentlocation = value;
	}
	public java.lang.Long getLocation2provisionstatus() {
		return this.location2provisionstatus;
	}
	
	public void setLocation2provisionstatus(java.lang.Long value) {
		this.location2provisionstatus = value;
	}
	public java.lang.Long getLocation2rpbuildtemplate() {
		return this.location2rpbuildtemplate;
	}
	
	public void setLocation2rpbuildtemplate(java.lang.Long value) {
		this.location2rpbuildtemplate = value;
	}
	public java.lang.Long getLocationid() {
		return this.locationid;
	}
	
	public void setLocationid(java.lang.Long value) {
		this.locationid = value;
	}
	public java.lang.Float getMarkedfordelete() {
		return this.markedfordelete;
	}
	
	public void setMarkedfordelete(java.lang.Float value) {
		this.markedfordelete = value;
	}
	public java.lang.String getNotes() {
		return this.notes;
	}
	
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	public java.lang.String getObjectid() {
		return this.objectid;
	}
	
	public void setObjectid(java.lang.String value) {
		this.objectid = value;
	}
	public java.lang.String getPhysicalx() {
		return this.physicalx;
	}
	
	public void setPhysicalx(java.lang.String value) {
		this.physicalx = value;
	}
	public java.lang.String getPhysicaly() {
		return this.physicaly;
	}
	
	public void setPhysicaly(java.lang.String value) {
		this.physicaly = value;
	}
	public java.lang.String getPhysicalz() {
		return this.physicalz;
	}
	
	public void setPhysicalz(java.lang.String value) {
		this.physicalz = value;
	}
	public java.lang.String getProvince() {
		return this.province;
	}
	
	public void setProvince(java.lang.String value) {
		this.province = value;
	}
	public java.lang.String getRelativename() {
		return this.relativename;
	}
	
	public void setRelativename(java.lang.String value) {
		this.relativename = value;
	}
	public java.lang.String getResponsible() {
		return this.responsible;
	}
	
	public void setResponsible(java.lang.String value) {
		this.responsible = value;
	}
	public java.lang.String getSubstatus() {
		return this.substatus;
	}
	
	public void setSubstatus(java.lang.String value) {
		this.substatus = value;
	}
	public java.lang.String getSubtype() {
		return this.subtype;
	}
	
	public void setSubtype(java.lang.String value) {
		this.subtype = value;
	}
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	public java.lang.String getTowncity() {
		return this.towncity;
	}
	
	public void setTowncity(java.lang.String value) {
		this.towncity = value;
	}
	public java.lang.String getZip() {
		return this.zip;
	}
	
	public void setZip(java.lang.String value) {
		this.zip = value;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Name",getName())
			.append("Address",getAddress())
			.append("Address1",getAddress1())
			.append("Address2",getAddress2())
			.append("Address3",getAddress3())
			.append("Alias1",getAlias1())
			.append("Alias2",getAlias2())
			.append("Createdby2dimuser",getCreatedby2dimuser())
			.append("Createddate",getCreateddate())
			.append("Description",getDescription())
			.append("Fax",getFax())
			.append("Fullname",getFullname())
			.append("Isvisible",getIsvisible())
			.append("Label",getLabel())
			.append("Lastmodifiedby2dimuser",getLastmodifiedby2dimuser())
			.append("Lastmodifieddate",getLastmodifieddate())
			.append("Location2functionalstatus",getLocation2functionalstatus())
			.append("Location2locationtype",getLocation2locationtype())
			.append("Location2parentlocation",getLocation2parentlocation())
			.append("Location2provisionstatus",getLocation2provisionstatus())
			.append("Location2rpbuildtemplate",getLocation2rpbuildtemplate())
			.append("Locationid",getLocationid())
			.append("Markedfordelete",getMarkedfordelete())
			.append("Notes",getNotes())
			.append("Objectid",getObjectid())
			.append("Physicalx",getPhysicalx())
			.append("Physicaly",getPhysicaly())
			.append("Physicalz",getPhysicalz())
			.append("Province",getProvince())
			.append("Relativename",getRelativename())
			.append("Responsible",getResponsible())
			.append("Substatus",getSubstatus())
			.append("Subtype",getSubtype())
			.append("Telephone",getTelephone())
			.append("Towncity",getTowncity())
			.append("Zip",getZip())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getName())
			.append(getAddress())
			.append(getAddress1())
			.append(getAddress2())
			.append(getAddress3())
			.append(getAlias1())
			.append(getAlias2())
			.append(getCreatedby2dimuser())
			.append(getCreateddate())
			.append(getDescription())
			.append(getFax())
			.append(getFullname())
			.append(getIsvisible())
			.append(getLabel())
			.append(getLastmodifiedby2dimuser())
			.append(getLastmodifieddate())
			.append(getLocation2functionalstatus())
			.append(getLocation2locationtype())
			.append(getLocation2parentlocation())
			.append(getLocation2provisionstatus())
			.append(getLocation2rpbuildtemplate())
			.append(getLocationid())
			.append(getMarkedfordelete())
			.append(getNotes())
			.append(getObjectid())
			.append(getPhysicalx())
			.append(getPhysicaly())
			.append(getPhysicalz())
			.append(getProvince())
			.append(getRelativename())
			.append(getResponsible())
			.append(getSubstatus())
			.append(getSubtype())
			.append(getTelephone())
			.append(getTowncity())
			.append(getZip())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof City == false) return false;
		if(this == obj) return true;
		City other = (City)obj;
		return new EqualsBuilder()
			.append(getName(),other.getName())
			.append(getAddress(),other.getAddress())
			.append(getAddress1(),other.getAddress1())
			.append(getAddress2(),other.getAddress2())
			.append(getAddress3(),other.getAddress3())
			.append(getAlias1(),other.getAlias1())
			.append(getAlias2(),other.getAlias2())
			.append(getCreatedby2dimuser(),other.getCreatedby2dimuser())
			.append(getCreateddate(),other.getCreateddate())
			.append(getDescription(),other.getDescription())
			.append(getFax(),other.getFax())
			.append(getFullname(),other.getFullname())
			.append(getIsvisible(),other.getIsvisible())
			.append(getLabel(),other.getLabel())
			.append(getLastmodifiedby2dimuser(),other.getLastmodifiedby2dimuser())
			.append(getLastmodifieddate(),other.getLastmodifieddate())
			.append(getLocation2functionalstatus(),other.getLocation2functionalstatus())
			.append(getLocation2locationtype(),other.getLocation2locationtype())
			.append(getLocation2parentlocation(),other.getLocation2parentlocation())
			.append(getLocation2provisionstatus(),other.getLocation2provisionstatus())
			.append(getLocation2rpbuildtemplate(),other.getLocation2rpbuildtemplate())
			.append(getLocationid(),other.getLocationid())
			.append(getMarkedfordelete(),other.getMarkedfordelete())
			.append(getNotes(),other.getNotes())
			.append(getObjectid(),other.getObjectid())
			.append(getPhysicalx(),other.getPhysicalx())
			.append(getPhysicaly(),other.getPhysicaly())
			.append(getPhysicalz(),other.getPhysicalz())
			.append(getProvince(),other.getProvince())
			.append(getRelativename(),other.getRelativename())
			.append(getResponsible(),other.getResponsible())
			.append(getSubstatus(),other.getSubstatus())
			.append(getSubtype(),other.getSubtype())
			.append(getTelephone(),other.getTelephone())
			.append(getTowncity(),other.getTowncity())
			.append(getZip(),other.getZip())
			.isEquals();
	}
}
