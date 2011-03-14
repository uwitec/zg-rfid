/**
 * 
 */
package com.boco.zg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/** 
 * @author wengq 
 * @date: Oct 25, 2010 10:50:26 AM
 * @vresion V 1.0 
 * @reade me:
 *
 */
public class FileHelper {
	public static void createDataFile(String name, String suffix, Properties properties)
    {
        File cfg = new File(name+"."+suffix);
        if(!cfg.exists())
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(cfg, false);
                properties.store(fos, "for tests only !");
                fos.close();
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
            }
        }
    }

}
