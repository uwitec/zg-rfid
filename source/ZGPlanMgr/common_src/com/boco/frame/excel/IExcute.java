package com.boco.frame.excel;

import com.boco.frame.excel.template.Template;
import com.boco.frame.excel.util.DataSet;

/**
 * IExcute接口
 * @author xh
 * 说明：用于执行中间数据转换到目标源的接口
 */
public interface IExcute {
	Object excute(DataSet ds,Template temp,Object params);
}
