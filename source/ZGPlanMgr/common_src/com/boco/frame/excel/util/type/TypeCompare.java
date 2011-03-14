package com.boco.frame.excel.util.type;

import com.boco.frame.excel.util.DataException;


//通过类型转换来确认是否符合当前类型，如果转换失败则抛出异常
public class TypeCompare {

	public static boolean typeCompare(Object entity, String typeName)
			throws DataException {
		boolean ret = false;
		try {
			if (typeName == String.class.getName()) {
				String.valueOf(entity);
				return true;
			}
			if (typeName == Integer.class.getName()) {
				Integer.valueOf(entity.toString());
				return true;
			}
			if (typeName == Double.class.getName()) {
				Double.valueOf(entity.toString());
				return true;
			}
			if (typeName == Float.class.getName()) {
				Float.valueOf(entity.toString());
				return true;
			}
			if (typeName == Boolean.class.getName()) {
				Boolean.valueOf(entity.toString());
				return true;
			}

		} catch (Exception e) {
                    throw new DataException("type is not compareable");
		}

		return ret;
	}
}
