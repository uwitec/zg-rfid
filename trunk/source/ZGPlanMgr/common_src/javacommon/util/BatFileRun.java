package javacommon.util;

import java.io.IOException;

/**
 * 执行数据库备份脚本
 * @author Administrator
 *
 */
public class BatFileRun {
	 public static void  runbat(String path){
		  String cmd = "cmd /c start "+path;
		try {
			Process ps = Runtime.getRuntime().exec(cmd);
			System.out.println(ps.getInputStream());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
