package javacommon.dwr;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.directwebremoting.Container;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.Configurator;
import org.directwebremoting.impl.ContainerUtil;
import org.directwebremoting.impl.DwrXmlConfigurator;
import org.directwebremoting.util.LocalUtil;
import org.directwebremoting.util.Logger;
import org.xml.sax.SAXException;

/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2006 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 */
/**
 * Revision Information:
 * 
 * @version 1.0 2009-5-11 release(zhangyinghui.Bright)
 */

public class ContainerUtilExt extends ContainerUtil {
	public static final Logger log = Logger.getLogger(ContainerUtilExt.class);

	public static void configureContainerFully(Container container,
			ServletConfig servletConfig) throws IOException,
			ParserConfigurationException, SAXException {
		configureFromSystemDwrXml(container);
		boolean foundConfig = configureFromInitParams(container, servletConfig);

		boolean skip = Boolean.valueOf(
				servletConfig.getInitParameter("skipDefaultConfig"))
				.booleanValue();
		IOException delayedIOException = null;
		if ((!(foundConfig)) && (!(skip))) {
			try {
				configureFromDefaultDwrXml(container);
			} catch (IOException ex) {
				delayedIOException = ex;
			}
		}

		if (!(configureFromAnnotations(container))) {
			log.debug("Java5 AnnotationsConfigurator disabled");

			if (delayedIOException != null) {
				throw delayedIOException;
			}
		}
	}

	public static boolean configureFromInitParams(Container container,
			ServletConfig servletConfig) throws IOException,
			ParserConfigurationException, SAXException {
		Enumeration en = servletConfig.getInitParameterNames();
		boolean foundConfig = false;
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String value = servletConfig.getInitParameter(name);
			Set<String> pathSet = new HashSet<String>();
			if (name.startsWith("config")) {
				foundConfig = true;
				//如果有通配符配置dwr的xml文件
				if(StringUtils.contains(value, "*")){
					ServletContext servletContext = WebContextFactory.get().getServletContext();
					String path = StringUtils.substringBeforeLast(value, "/")+"/";
					Set set = servletContext.getResourcePaths(path);
					Iterator it = set.iterator();
					while(it.hasNext()){
						String fileName = (String)it.next();
						if(StringUtils.indexOf(fileName, ".xml")>0){
							pathSet.add(fileName);
						}
					}
				}else{
					StringTokenizer st = new StringTokenizer(value, "\n,");
					while (st.hasMoreTokens()) {
						String fileName = st.nextToken().trim();
						pathSet.add(fileName);
					}
				}
				for(String fileName:pathSet){
					DwrXmlConfigurator local = new DwrXmlConfigurator();
					local.setServletResourceName(fileName);
					local.configure(container);
				}
			} else if (name.equals("customConfigurator")) {
				foundConfig = true;
				try {
					Configurator configurator = (Configurator) LocalUtil
							.classNewInstance("customConfigurator", value,
									Configurator.class);
					configurator.configure(container);
					log.debug("Loaded config from: " + value);
				} catch (Exception ex) {
					log.warn("Failed to start custom configurator", ex);
				}
			}
		}

		return foundConfig;
	}
}
