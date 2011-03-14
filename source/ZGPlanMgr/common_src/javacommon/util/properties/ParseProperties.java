package javacommon.util.properties;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

/**
 * 读取properties工具类
 * @author wengqin
 *
 */
public class ParseProperties extends Properties {
	private static final Log log = LogFactory.getLog(ParseProperties.class);
	private static final long serialVersionUID = 1L;
	public static final String ENCODING_KEY = "ENCODING";
	/** Default encoding for properties. */
	private static final String ENCODING_PROPERTIES = "ISO-8859-1";
	/** Default share-it encoding if key not present in properties. */
	public static final String ENCODING_DEFAULT = ENCODING_PROPERTIES;
	/** property file */
	private File propFile;
	/** lastModifiedTime */
	private long lastModifiedTime;
	private String path;
	private List<ParseProperties> subProperties = new ArrayList<ParseProperties>();;
	private boolean isFolder = false;
	public static boolean GLOBEL_ENABLE_RELOAD = true;

	public String getPath() {
		return path;
	}

	public ParseProperties() {
		this(new Properties());
	}

	@Override
	public String getProperty(String key) {
		if(this.isFolder){//dir type
			for(ParseProperties prop : this.subProperties){
				if(prop.containsKey(key)){
					return prop.getProperty(key);
				}
			}
		}
		return super.getProperty(key);
	}

	/**
	 * @throws NullPointerException if <tt>defaults</tt> is <tt>null</tt>.
	 */
	public ParseProperties(Properties defaults) {
		super(defaults);
		//Make sure we have a proper default for the encoding.
		defaults.setProperty(ENCODING_KEY, ENCODING_DEFAULT);
	}

	public void load(InputStream inStream) throws IOException {
		super.load(inStream);
		String encoding = super.getProperty(ENCODING_KEY);
		if (ENCODING_PROPERTIES.equals(encoding)) {
			return;
		}
		//Convert properties
		try {
			Map.Entry[] entries = new Map.Entry[entrySet().size()];
			entrySet().toArray(entries);
			for (int i = entries.length; --i >= 0; ) {
				Map.Entry entry = entries[i];
				String value = (String) entry.getValue();
				value = new String(value.getBytes(ENCODING_PROPERTIES),encoding);
				setProperty((String) entry.getKey(), value);
			}
		}
		catch (UnsupportedEncodingException ignored) {}
	}
	/**
	 * 读取文件
	 * @param propertyFileLocation 文件路径
	 * @return
	 * @throws IOException
	 */
	protected static ParseProperties getInstance(String propertyFileLocation){
		ParseProperties p =  new ParseProperties();
		p.path = propertyFileLocation;
		try {
			ClassPathResource cpr = new ClassPathResource(propertyFileLocation);
			InputStream is = null;
			try{
				URL resourceUrl = cpr.getURL();
				if (ResourceUtils.URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
					p.propFile = cpr.getFile();
					if(p.propFile.isDirectory()){
						p.isFolder = true;
						String []propFileNames = p.propFile.list();
						for(String name :propFileNames){
							p.subProperties.add(ParseProperties.getInstance(propertyFileLocation+"/"+name));
						}
					}else{
						is = new FileInputStream(p.propFile.getAbsolutePath());
						p.lastModifiedTime = p.propFile.lastModified();
					}
				}else if (ResourceUtils.isJarURL(resourceUrl)) {
					if(propertyFileLocation.endsWith(".xml")||propertyFileLocation.endsWith(".properties")){
						is = cpr.getInputStream();
					}else{
						p.isFolder= true;
						URLConnection connection = resourceUrl.openConnection();
						JarFile jarFile = null;
						if(connection instanceof JarURLConnection){
							jarFile = ((JarURLConnection)connection).getJarFile();
							Enumeration<JarEntry> e = jarFile.entries();
							while (e.hasMoreElements()) {
								String name = e.nextElement().getName();
								if (name.startsWith(propertyFileLocation) && (name.endsWith(".properties") ||name.endsWith(".xml"))){
									p.subProperties.add(ParseProperties.getInstance(name));
								}
							}
						}
					}
				}
			}catch(IOException ioe){
				log.error("鍔犺浇灞炴�ф枃浠禰"+propertyFileLocation+"]鏃跺嚭閿�",ioe);
			}
			if(propertyFileLocation.endsWith(".xml")){
				p.loadFromXML(is);
			}else if(propertyFileLocation.endsWith(".properties")){
				p.load(is);			
			}
		} catch (Exception e) {
			log.error("鍔犺浇灞炴�ф枃浠禰"+propertyFileLocation+"]鏃跺嚭閿�",e);
		} 
		return p;
	}




}
