package javacommon.dwr;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.Container;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.ServerLoadMonitor;
import org.directwebremoting.impl.ContainerUtil;
import org.directwebremoting.impl.DefaultContainer;
import org.directwebremoting.impl.StartupUtil;
import org.directwebremoting.servlet.DwrServlet;
import org.directwebremoting.servlet.UrlProcessor;
import org.directwebremoting.util.Logger;
import org.directwebremoting.util.ServletLoggingOutput;
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

public class DWRServlet extends HttpServlet {
	private DefaultContainer container;
	private WebContextFactory.WebContextBuilder webContextBuilder;
	public static final Logger log = Logger.getLogger(DwrServlet.class);

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		ServletContext servletContext = servletConfig.getServletContext();
		try {
			StartupUtil.setupLogging(servletConfig, this);
			StartupUtil.logStartup(servletConfig);

			this.container = ContainerUtil
					.createDefaultContainer(servletConfig);
			ContainerUtil.setupDefaultContainer(this.container, servletConfig);

			this.webContextBuilder = StartupUtil.initWebContext(servletConfig,
					servletContext, this.container);
			StartupUtil.initServerContext(servletConfig, servletContext,
					this.container);

			ContainerUtil
					.prepareForWebContextFilter(servletContext, servletConfig,
							this.container, this.webContextBuilder, this);
			//重写读取配置文件方法
			ContainerUtilExt
					.configureContainerFully(this.container, servletConfig);
			
			ContainerUtil.publishContainer(this.container, servletConfig);
		} catch (ExceptionInInitializerError ex) {
		} catch (Exception ex) {
		} finally {
			if (this.webContextBuilder != null) {
				this.webContextBuilder.unset();
			}

			ServletLoggingOutput.unsetExecutionContext();
		}
	}

	public void destroy() {
		shutdown();
		super.destroy();
	}

	public void shutdown() {
		ServerLoadMonitor monitor = (ServerLoadMonitor) this.container
				.getBean(ServerLoadMonitor.class.getName());
		monitor.shutdown();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			this.webContextBuilder.set(request, response, getServletConfig(),
					getServletContext(), this.container);
			ServletLoggingOutput.setExecutionContext(this);

			UrlProcessor processor = (UrlProcessor) this.container
					.getBean(UrlProcessor.class.getName());
			processor.handle(request, response);
		} finally {
			this.webContextBuilder.unset();
			ServletLoggingOutput.unsetExecutionContext();
		}
	}

	public Container getContainer() {
		return this.container;
	}
}