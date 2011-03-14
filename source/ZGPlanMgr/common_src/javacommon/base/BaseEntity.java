package javacommon.base;


//静态导入日期转换方法
import cn.org.rapid_framework.util.DateConvertUtils;

/**
 * @author badqiu
 */
public class BaseEntity implements java.io.Serializable {
	
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected static final String TIME_FORMAT = "HH:mm:ss";
	
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
	private String sqlQueryString;
	
	private String equalBmClassIdQuery;
	private String inSubBmClassIdQuery;

	public String getEqualBmClassIdQuery() {
		return equalBmClassIdQuery;
	}

	public void setEqualBmClassIdQuery(String equalBmClassIdQuery) {
		this.equalBmClassIdQuery = equalBmClassIdQuery;
	}

	public String getInSubBmClassIdQuery() {
		return inSubBmClassIdQuery;
	}

	public void setInSubBmClassIdQuery(String inSubBmClassIdQuery) {
		this.inSubBmClassIdQuery = inSubBmClassIdQuery;
	}

	public String getSqlQueryString() {
		return sqlQueryString;
	}

	public void setSqlQueryString(String sqlQueryString) {
		this.sqlQueryString = sqlQueryString;
	}

	public static String date2String(java.util.Date date,String dateFormat) {
		return DateConvertUtils.format(date,dateFormat);
	}
	
	public static <T extends java.util.Date> T string2Date(String dateString,String dateFormat,Class<T> targetResultType) {
		return DateConvertUtils.parse(dateString,dateFormat,targetResultType);
	}
}
