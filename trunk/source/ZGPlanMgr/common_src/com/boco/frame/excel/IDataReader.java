package com.boco.frame.excel;

import com.boco.frame.excel.template.Template;
import com.boco.frame.excel.util.DataSet;

/**
 * IDataReader接口
 * @author xh
 * 说明：用于读源数据转换成通用数据格式的接口
 */
public interface IDataReader {
	DataSet readData(Template temp,Object params);
}
