package com.boco.frame.sys.model;

import com.boco.frame.sys.base.model.FwMenu;

public class FwMenuEx extends FwMenu {
	
	public static final String TABLE_ALIAS = "FwMenuEx";

	public static final String ALIAS_CHILD_NUM = "childNum";
	
	private Integer childNum;

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
}
