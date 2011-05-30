/**
 * 
 */
package test;

import java.io.File;

/** 
 * @author wengq 
 * @date: May 29, 2011 10:13:39 PM
 * @vresion V 1.0 
 * @reade me:
 *
 */
public class FileHelper {
	public static void main(String[] args) {
		File file=new File("D:\\Users\\Administrator\\workspace\\ZGPlanMgrZGNew\\web\\WEB-INF\\lib");
		File[] childFiles=file.listFiles();
		for (File temp:childFiles) {
			System.out.println("${basedir}\\web\\WEB-INF\\lib\\"+temp.getName()+";");
		}
		
	}
}
