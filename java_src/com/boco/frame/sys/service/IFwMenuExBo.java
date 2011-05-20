package com.boco.frame.sys.service;

import net.sf.json.JSONArray;

import com.boco.frame.login.pojo.OperatorInfo;

public interface IFwMenuExBo {
	/**
	 * 根据登录信息查询具有权限的菜单树
	 * @param operatorInfo
	 * @return
	 */
	public JSONArray findMenuTreeByOperatorInfo(OperatorInfo operatorInfo);
}
