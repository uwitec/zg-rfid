import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/** 
 * @author wengq 
 * @date: Jul 19, 2011 9:43:45 AM
 * @vresion V 1.0 
 * @reade me:
 *
 */
public class Test {
	static Long tryCou=1l;
	static Long finallyCou=1l;
	private static void foo() {  
		List list=new ArrayList<Map>();
		List list1=new ArrayList<Map>();
	    try {  
	    
	    	tryCou++;
	        System.out.println("try:"+tryCou);  
	    	for (long i = 0; i < 100000; i++) {
	    		Map map=new HashMap<String, Object>();
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		list.add(map);
			}
	        foo();  
	    } catch (Throwable e) {  
	        System.out.println("catch");  
	        foo();  
	    } finally {  
	    	finallyCou++;
	        System.out.println("finally:"+finallyCou);  
	    	for (long i = 0; i < 100000; i++) {
	    		Map map=new HashMap<String, Object>();
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		map.put("1", 1);
	    		list1.add(map);
			}
	        foo();  
	    }  
	}  
	  
	public static void main(String[] args) {  
	    foo();  
	}  
}
