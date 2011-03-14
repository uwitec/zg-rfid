package com.boco.zg.bom.base.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.base.model.EnumModel;
import javacommon.base.model.RelatedModel;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.boco.zg.bom.base.model.*;
import com.boco.zg.bom.base.dao.*;
import com.boco.zg.bom.base.service.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ZgCarInfo extends BaseEntity {

	public static final String TABLE_ALIAS = "ZG_CARINFO";
	public static final String BM_CLASS_ID = "ZG_CARINFO";
	public String getBM_CLASS_ID(){
		return BM_CLASS_ID;
	}
	
	public static final String ALIAS_CUID = "CUID";
	public static final String ALIAS_CODE = "车型编号";
	public static final String ALIAS_LABEL_CN = "车辆名称";
	public static final String ALIAS_DESCRIPTION = "描述";

	/**
	 * get meta labelValue with , split
	 * @return
	 */
	public String getLabelValue() {
		String labelValue="";
		labelValue+=this.getLabelCn()==null?"":this.getLabelCn().toString();
		return labelValue;
	}
	
	//columns START
	private String cuid;
	private String code;
	private String labelCn;
	private String description;
	
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabelCn() {
		return labelCn;
	}
	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}
	public ZgCarInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ZgCarInfo(String cuid, String code, String labelCn,
			String description) {
		super();
		this.cuid = cuid;
		this.code = code;
		this.labelCn = labelCn;
		this.description = description;
	}

}


