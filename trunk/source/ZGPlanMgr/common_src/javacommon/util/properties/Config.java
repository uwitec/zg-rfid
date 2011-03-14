package javacommon.util.properties;

import java.util.Properties;



/**
 * 读取properties工具类
 * @author wengqin
 *
 */
public class Config {
	
	private static Properties config = ParseProperties.getInstance("config");

    /**
     * @return the string value bound with the key.
     */
    public static String getString(String key) {
        return config.getProperty(key);
    }
    
    /**
     * @param key 
     * @param defaultValue 
     * @return the string value bound with the key.
     */
    public static String getString(String key,String defaultValue) {
        String value = getString(key);
        if(null == value || "".equals(value.trim())){
            return defaultValue;
        }else{
            return value;
        }
    }    
    
   
}
